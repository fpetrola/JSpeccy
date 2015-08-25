

package emulator

import java.util.Arrays
import Z80._
import scala.collection.JavaConversions._
import machine.Clock
import snapshots.Z80State
import scala.beans.BeanProperty
import z80core.IntMode
import z80core.MemIoOps
import z80core.NotifyOps
import z80core.ZXLogger
 
object Z80 {

  implicit def int2FerInt(value: Int): BitContainer = new BitContainer(value);
  implicit def FerInt2Int(value: BitContainer): Int = value.toInt;
  implicit def Bit2Boolean(value: Bit): Boolean= value.intValue;

  @BeanProperty
  var decodedOpcodes: Long= 0
  
  private final val CARRY_MASK = 0x01
  private final val ADDSUB_MASK = 0x02
  private final val PARITY_MASK = 0x04
  private final val OVERFLOW_MASK = 0x04
  private final val BIT3_MASK = 0x08
  private final val HALFCARRY_MASK = 0x10
  private final val BIT5_MASK = 0x20
  private final val ZERO_MASK = 0x40
  private final val SIGN_MASK = 0x80
  private final val FLAG_53_MASK = BIT5_MASK | BIT3_MASK
  private final val FLAG_SZ_MASK = SIGN_MASK | ZERO_MASK
  private final val FLAG_SZHN_MASK = FLAG_SZ_MASK | HALFCARRY_MASK | ADDSUB_MASK
  private final val FLAG_SZP_MASK = FLAG_SZ_MASK | PARITY_MASK
  private final val FLAG_SZHP_MASK = FLAG_SZP_MASK | HALFCARRY_MASK
  private final val sz53n_addTable = new Array[Long](256)
  private final val sz53pn_addTable = new Array[Long](256)
  private final val sz53n_subTable = new Array[Long](256)
  private final val sz53pn_subTable = new Array[Long](256)

  var evenBits: Boolean = _

  for (idx <- 0 until 256) {
    if (idx > 0x7f) {
      if (sz53n_addTable(idx) == null)
        sz53n_addTable(idx) = 0
      sz53n_addTable(idx) |= SIGN_MASK
    }
    evenBits = true
    var mask = 0x01
    while (mask < 0x100) {
      if ((idx & mask) != 0) { 
        evenBits = !evenBits
      }
      mask <<= 1
    }

    if (sz53n_addTable(idx) == null)
      sz53n_addTable(idx) = 0;

    sz53n_addTable(idx) |= (idx & FLAG_53_MASK)
    sz53n_subTable(idx) = sz53n_addTable(idx) | ADDSUB_MASK
    if (evenBits) {
      sz53pn_addTable(idx) = sz53n_addTable(idx) | PARITY_MASK
      sz53pn_subTable(idx) = sz53n_subTable(idx) | PARITY_MASK
    } else {
      sz53pn_addTable(idx) = sz53n_addTable(idx)
      sz53pn_subTable(idx) = sz53n_subTable(idx)
    }
  }

  sz53n_addTable(0) |= ZERO_MASK
  sz53pn_addTable(0) |= ZERO_MASK
  sz53n_subTable(0) |= ZERO_MASK
  sz53pn_subTable(0) |= ZERO_MASK
}

class Z80(val MemIoImpl: MemIoOps, val NotifyImpl: NotifyOps) {

  private final val clock = Clock.getInstance
  private var opCode: Int = _
  private var execDone: Boolean = false 
  @BeanProperty  
  final val regA: BitContainer = BitContainerFactory.createFixed(0)
  final val regB: BitContainer = BitContainerFactory.createFixed(0)
  final val regC: BitContainer = BitContainerFactory.createFixed(0)
  final val regD: BitContainer = BitContainerFactory.createFixed(0)
  final val regE: BitContainer = BitContainerFactory.createFixed(0)
  final val regH: BitContainer = BitContainerFactory.createFixed(0)
  final val regL: BitContainer = BitContainerFactory.createFixed(0)

  private var sz5h3pnFlags: Long = 0
  @BeanProperty
  val carryFlag: Bit = new Bit(false)

  private var flagQ: Boolean = false

  private var lastFlagQ: Boolean = false
  final val regAx: BitContainer = BitContainerFactory.createFixed(0)
  final val regFx: BitContainer = BitContainerFactory.createFixed(0)
  final val regBx: BitContainer = BitContainerFactory.createFixed(0)
  final val regCx: BitContainer = BitContainerFactory.createFixed(0)
  final val regDx: BitContainer = BitContainerFactory.createFixed(0)
  final val regEx: BitContainer = BitContainerFactory.createFixed(0)
  final val regHx: BitContainer = BitContainerFactory.createFixed(0)
  final val regLx: BitContainer = BitContainerFactory.createFixed(0)
  @BeanProperty
  final val regPC: BitContainer = BitContainerFactory.createFixed(0)
  @BeanProperty
  final val regIX: BitContainer = BitContainerFactory.createFixed(0)
  @BeanProperty
  final val regIY: BitContainer = BitContainerFactory.createFixed(0)
  @BeanProperty
  final val regSP: BitContainer = BitContainerFactory.createFixed(0)
  @BeanProperty
  final val regI: BitContainer = BitContainerFactory.createFixed(0)

  final private val regR: BitContainer = BitContainerFactory.createFixed(0)

  private var regRbit7: Boolean = false

  private var ffIFF1: Boolean = false

  private var ffIFF2: Boolean = false
  var pendingEI = false

  private var activeNMI: Boolean = false

  private var activeINT: Boolean = false

  private var modeINT = IntMode.IM0
  var halted: Boolean = false

  private var pinReset: Boolean = false

  final private val memptr: BitContainer = BitContainerFactory.createFixed(0)

  private var breakpointAt: Array[Boolean] = new Array[Boolean](65536)

  Arrays.fill(breakpointAt, false)

  reset()

  def getRegAF(): BitContainer = {
    (regA << 8) |
      (new BitContainer(if (carryFlag) sz5h3pnFlags | CARRY_MASK else sz5h3pnFlags))
  }

  def setRegAF(word: BitContainer) {
    regA := (word >>> 8) & 0xff
    sz5h3pnFlags = (word & 0xfe).intValue
    carryFlag := (word & CARRY_MASK) != 0
  }

  def getRegAFx(): BitContainer = (regAx << 8) | regFx

  def setRegAFx(word: BitContainer) {
    regAx := (word >>> 8) & 0xff
    regFx := word & 0xff
  }

  def getRegBC(): BitContainer = (regB << 8) | regC

  def setRegBC(word: BitContainer) {
    regB := (word >>> 8) & 0xff
    regC := word & 0xff
  }

  private def incRegBC() {
    regC += 1
    if (regC < 0x100) {
      return
    }
    regC := 0
    regB += 1
    if (regB < 0x100) {
      return
    }
    regB := 0
  }

  private def decRegBC() {
    regC -= 1;
    if (regC >= 0) {
      return
    }
    regC := 0xff
    regB -= 1
    if (regB >= 0) {
      return
    }
    regB := 0xff
  }

  def getRegBCx(): BitContainer = (regBx << 8) | regCx

  def setRegBCx(word: BitContainer) {
    regBx := (word >>> 8) & 0xff
    regCx := word & 0xff
  }

  def getRegDE(): BitContainer = (regD << 8) | regE

  def setRegDE(word: BitContainer) {
    regD := (word >>> 8) & 0xff
    regE := word & 0xff
  }

  private def incRegDE() {
    regE += 1
    if (regE < 0x100) {
      return
    }
    regE := 0
    regD += 1
    if (regD < 0x100) {
      return
    }
    regD := 0
  }

  private def decRegDE() {
    regE -= 1
    if (regE >= 0) {
      return
    }
    regE := 0xff
    regD -= 1
    if (regD >= 0) {
      return
    }
    regD := 0xff
  }

  def getRegDEx(): BitContainer = (regDx << 8) | regEx

  def setRegDEx(word: BitContainer) {
    regDx := (word >>> 8) & 0xff
    regEx := word & 0xff
  }

  def getRegHL(): BitContainer = (regH << 8) | regL

  def setRegHL(word: BitContainer) {
    regH := (word >>> 8) & 0xff
    regL := word & 0xff
  }

  private def incRegHL() {
    regL += 1
    if (regL < 0x100) {
      return
    }
    regL := 0
    regH += 1
    if (regH < 0x100) {
      return
    }
    regH := 0
  }

  private def decRegHL() {
    regL -= 1
    if (regL >= 0) {
      return
    }
    regL := 0xff
    regH -= 1
    if (regH >= 0) {
      return
    }
    regH := 0xff
  }

  def getRegHLx(): BitContainer = (regHx << 8) | regLx

  def setRegHLx(word: BitContainer) {
    regHx := (word >>> 8) & 0xff
    regLx := word & 0xff
  }

  def getRegR(): BitContainer = {
    if (regRbit7) (regR & 0x7f) | SIGN_MASK else regR & 0x7f
  }

  def setRegR(value: BitContainer) {
    regR := value & 0x7f
    regRbit7 = (value > 0x7f)
  }

  def getPairIR(): BitContainer = {
    if (regRbit7) {
      return (regI << 8) | ((regR & 0x7f) | SIGN_MASK)
    }
    (regI << 8) | (regR & 0x7f)
  }

  def getMemPtr(): BitContainer = memptr & 0xffff

  def setMemPtr(word: BitContainer) {
    memptr := word & 0xffff
  }

  def isAddSubFlag(): Boolean = (sz5h3pnFlags & ADDSUB_MASK) != 0

  def setAddSubFlag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= ADDSUB_MASK
    } else {
      sz5h3pnFlags &= ~ADDSUB_MASK
    }
  }

  def isParOverFlag(): Boolean = (sz5h3pnFlags & PARITY_MASK) != 0

  def setParOverFlag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= PARITY_MASK
    } else {
      sz5h3pnFlags &= ~PARITY_MASK
    }
  }

  def isBit3Flag(): Boolean = (sz5h3pnFlags & BIT3_MASK) != 0

  def setBit3Fag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= BIT3_MASK
    } else {
      sz5h3pnFlags &= ~BIT3_MASK
    }
  }

  def isHalfCarryFlag(): Boolean = (sz5h3pnFlags & HALFCARRY_MASK) != 0

  def setHalfCarryFlag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= HALFCARRY_MASK
    } else {
      sz5h3pnFlags &= ~HALFCARRY_MASK
    }
  }

  def isBit5Flag(): Boolean = (sz5h3pnFlags & BIT5_MASK) != 0

  def setBit5Flag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= BIT5_MASK
    } else {
      sz5h3pnFlags &= ~BIT5_MASK
    }
  }

  def isZeroFlag(): Boolean = (sz5h3pnFlags & ZERO_MASK) != 0

  def setZeroFlag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= ZERO_MASK
    } else {
      sz5h3pnFlags &= ~ZERO_MASK
    }
  }

  def isSignFlag(): Boolean = (sz5h3pnFlags & SIGN_MASK) != 0

  def setSignFlag(state: Boolean) {
    if (state) {
      sz5h3pnFlags |= SIGN_MASK
    } else {
      sz5h3pnFlags &= ~SIGN_MASK
    }
  }

  def getFlags(): BitContainer = {
    new BitContainer(if (carryFlag) sz5h3pnFlags | CARRY_MASK else sz5h3pnFlags)
  }

  def setFlags(regF: BitContainer) {
    sz5h3pnFlags = (regF & 0xfe).intValue
    carryFlag := (regF & CARRY_MASK) != 0
  }

  def isIFF1(): Boolean = ffIFF1

  def setIFF1(state: Boolean) {
    ffIFF1 = state
  }

  def isIFF2(): Boolean = ffIFF2

  def setIFF2(state: Boolean) {
    ffIFF2 = state
  }

  def isNMI(): Boolean = activeNMI

  def setNMI(nmi: Boolean) {
    activeNMI = nmi
  }

  def triggerNMI() {
    activeNMI = true
  }

  def isINTLine(): Boolean = activeINT

  def setINTLine(intLine: Boolean) {
    activeINT = intLine
  }

  def getIM() = modeINT

  def setIM(mode: IntMode) {
    modeINT = mode
  }

  def setPinReset() {
    pinReset = true
  }

  def getZ80State(): Z80State = {
    val state = new Z80State()
    state.setRegA(regA)
    state.setRegF(getFlags)
    state.setRegB(regB)
    state.setRegC(regC)
    state.setRegD(regD)
    state.setRegE(regE)
    state.setRegH(regH)
    state.setRegL(regL)
    state.setRegAx(regAx)
    state.setRegFx(regFx)
    state.setRegBx(regBx)
    state.setRegCx(regCx)
    state.setRegDx(regDx)
    state.setRegEx(regEx)
    state.setRegHx(regHx)
    state.setRegLx(regLx)
    state.setRegIX(regIX)
    state.setRegIY(regIY)
    state.setRegSP(regSP)
    state.setRegPC(regPC)
    state.setRegI(regI)
    state.setRegR(getRegR)
    state.setMemPtr(memptr)
    state.setHalted(halted)
    state.setIFF1(ffIFF1)
    state.setIFF2(ffIFF2)
    state.setIM(modeINT)
    state.setINTLine(activeINT)
    state.setPendingEI(pendingEI)
    state.setNMI(activeNMI)
    state.setFlagQ(lastFlagQ)
    state
  }

  def setZ80State(state: Z80State) {
    regA := state.getRegA
    setFlags(state.getRegF)
    regB := state.getRegB
    regC := state.getRegC
    regD := state.getRegD
    regE := state.getRegE
    regH := state.getRegH
    regL := state.getRegL
    regAx := state.getRegAx
    regFx := state.getRegFx
    regBx := state.getRegBx
    regCx := state.getRegCx
    regDx := state.getRegDx
    regEx := state.getRegEx
    regHx := state.getRegHx
    regLx := state.getRegLx
    regIX := state.getRegIX
    regIY := state.getRegIY
    regSP := state.getRegSP
    regPC := state.getRegPC
    regI := state.getRegI
    setRegR(state.getRegR)
    memptr := state.getMemPtr
    halted = state.isHalted
    ffIFF1 = state.isIFF1
    ffIFF2 = state.isIFF2
    modeINT = state.getIM
    activeINT = state.isINTLine
    pendingEI = state.isPendingEI
    activeNMI = state.isNMI
    flagQ = false
    lastFlagQ = state.isFlagQ
  }

  def reset() {
    if (pinReset) {
      pinReset = false
    } else {
      regAx := 0xff
      regA := regAx
      setFlags(0xff)
      regFx := 0xff
      regBx := 0xff
      regB := regBx
      regCx := 0xff
      regC := regCx
      regDx := 0xff
      regD := regDx
      regEx := 0xff
      regE := regEx
      regHx := 0xff
      regH := regHx
      regLx := 0xff
      regL := regLx
      regIY := 0xffff
      regIX := regIY
      regSP := 0xffff
      memptr := 0xffff
    }
    regPC := 0
    regR := 0
    regI := regR
    regRbit7 = false
    ffIFF1 = false
    ffIFF2 = false
    pendingEI = false
    activeNMI = false
    activeINT = false
    halted = false
    setIM(IntMode.IM0)
    lastFlagQ = false
//    ZXLogger.reset("/tmp/zx2.log", this);
  }

  private def rlc(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    carryFlag := (oper8 > 0x7f)
    oper8 := (oper8 << 1) & 0xfe
    if (carryFlag) {
      oper8 |= CARRY_MASK
    }
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def rl(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    val carry = new Bit(carryFlag)
    carryFlag := (oper8 > 0x7f)
    oper8 := (oper8 << 1) & 0xfe
    if (carry) {
      oper8 |= CARRY_MASK
    }
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def sla(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    carryFlag := (oper8 > 0x7f)
    oper8 := (oper8 << 1) & 0xfe
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def sll(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    carryFlag := (oper8 > 0x7f)
    oper8 := ((oper8 << 1) | CARRY_MASK) & 0xff
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def rrc(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    carryFlag := (oper8 & CARRY_MASK) != 0
    oper8 >>>= 1
    if (carryFlag) {
      oper8 |= SIGN_MASK
    }
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def rr(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    val carry = new Bit(carryFlag)
    carryFlag := (oper8 & CARRY_MASK) != 0
    oper8 >>>= 1
    if (carry) {
      oper8 |= SIGN_MASK
    }
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def rrd() {
    val aux = (regA & 0x0f) << 4
    memptr := getRegHL
    val memHL = MemIoImpl.peek8(memptr)
    regA := (regA & 0xf0) | (memHL & 0x0f)
    MemIoImpl.contendedStates(memptr, 4)
    MemIoImpl.poke8(memptr, (memHL >>> 4) | aux)
    sz5h3pnFlags = sz53pn_addTable(regA)
    memptr += 1
    flagQ = true
  }

  private def rld() {
    val aux = regA & 0x0f
    memptr := getRegHL
    val memHL = MemIoImpl.peek8(memptr)
    regA := (regA & 0xf0) | (memHL >>> 4)
    MemIoImpl.contendedStates(memptr, 4)
    MemIoImpl.poke8(memptr, ((memHL << 4) | aux) & 0xff)
    sz5h3pnFlags = sz53pn_addTable(regA)
    memptr += 1
    flagQ = true
  }

  private def sra(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    val sign = oper8 & SIGN_MASK
    carryFlag := (oper8 & CARRY_MASK) != 0
    oper8 := (oper8 >> 1) | sign
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def srl(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    carryFlag := (oper8 & CARRY_MASK) != 0
    oper8 >>>= 1
    sz5h3pnFlags = sz53pn_addTable(oper8)
    flagQ = true
    oper8
  }

  private def inc8(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    oper8 := (oper8 + 1) & 0xff
    sz5h3pnFlags = sz53n_addTable(oper8)
    if ((oper8 & 0x0f) == 0) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (oper8 == 0x80) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    flagQ = true
    oper8
  }

  private def dec8(oper81: BitContainer): BitContainer = {
    val oper8 = BitContainerFactory.create(oper81)
    oper8 := (oper8 - 1) & 0xff
    sz5h3pnFlags = sz53n_subTable(oper8)
    if ((oper8 & 0x0f) == 0x0f) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (oper8 == 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    flagQ = true
    oper8
  }

  private def add(oper8: BitContainer) {
    val res = BitContainerFactory.create(regA + oper8)
    carryFlag := res > 0xff
    res &= 0xff
    sz5h3pnFlags = sz53n_addTable(res)
    if ((res & 0x0f) < (regA & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regA ^ ~oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    regA := res
    flagQ = true
  }

  private def adc(oper8: BitContainer) {
    val res = BitContainerFactory.create(regA + oper8)
    if (carryFlag) {
      res += 1
    }
    carryFlag := res > 0xff
    res &= 0xff
    sz5h3pnFlags = sz53n_addTable(res)
    if (((regA ^ oper8 ^ res) & 0x10) != 0) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regA ^ ~oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    regA := res
    flagQ = true
  }

  private def add16(reg16: BitContainer, oper161: BitContainer): BitContainer = {
    val oper16 = BitContainerFactory.create(oper161)
    oper16 += reg16
    carryFlag := oper16 > 0xffff
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | ((oper16 >>> 8) & FLAG_53_MASK)
    oper16 &= 0xffff
    if ((oper16 & 0x0fff) < (reg16 & 0x0fff)) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    memptr := reg16 + 1
    flagQ = true
    oper16
  }

  private def adc16(reg16: BitContainer) {
    val regHL = getRegHL
    memptr := regHL + 1
    var res = regHL + reg16
    if (carryFlag) {
      res += 1
    }
    carryFlag := res > 0xffff
    res &= 0xffff
    setRegHL(res)
    sz5h3pnFlags = sz53n_addTable(regH)
    if (res != 0) {
      sz5h3pnFlags &= ~ZERO_MASK
    }
    if (((res ^ regHL ^ reg16) & 0x1000) != 0) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regHL ^ ~reg16) & (regHL ^ res)) > 0x7fff) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    flagQ = true
  }

  private def sub(oper8: BitContainer) {
    val res = BitContainerFactory.create(regA - oper8)
    carryFlag := res < 0
    res &= 0xff
    sz5h3pnFlags = sz53n_subTable(res)
    if ((res & 0x0f) > (regA & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regA ^ oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    regA := res
    flagQ = true
  }

  private def sbc(oper8: BitContainer) {
    val res = BitContainerFactory.create(regA - oper8)
    if (carryFlag) {
      res -= 1
    }
    carryFlag := res < 0
    res &= 0xff
    sz5h3pnFlags = sz53n_subTable(res)
    if (((regA ^ oper8 ^ res) & 0x10) != 0) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regA ^ oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    regA := res
    flagQ = true
  }

  private def sbc16(reg16: BitContainer) {
    var regHL = getRegHL
    memptr := regHL + 1
    val res = regHL - reg16
    if (carryFlag) {
      res -= 1
    }
    carryFlag := res < 0
    res &= 0xffff
    setRegHL(res)
    sz5h3pnFlags = sz53n_subTable(regH)
    if (res != 0) {
      sz5h3pnFlags &= ~ZERO_MASK
    }
    if (((res ^ regHL ^ reg16) & 0x1000) != 0) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regHL ^ reg16) & (regHL ^ res)) > 0x7fff) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    flagQ = true
  }

  private def and(oper8: BitContainer) {
    regA &= oper8
    carryFlag := false
    sz5h3pnFlags = sz53pn_addTable(regA) | HALFCARRY_MASK
    flagQ = true
  }

  def xor(oper8: BitContainer) {
    regA := (regA ^ oper8) & 0xff
    carryFlag := false
    sz5h3pnFlags = sz53pn_addTable(regA)
    flagQ = true
  }

  private def or(oper8: BitContainer) {
    regA := (regA | oper8) & 0xff
    carryFlag := false
    sz5h3pnFlags = sz53pn_addTable(regA)
    flagQ = true
  }

  def cp(oper8: BitContainer) {
    val res = regA - (oper8 & 0xff)
    carryFlag := res < 0
    res &= 0xff
    sz5h3pnFlags = (sz53n_addTable(oper8) & FLAG_53_MASK) | (sz53n_subTable(res) & FLAG_SZHN_MASK)
    if ((res & 0x0f) > (regA & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK
    }
    if (((regA ^ oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK
    }
    flagQ = true
  }

  private def daa() {
    val suma: BitContainer = BitContainerFactory.create(0)
    var carry = new Bit(carryFlag)
    if ((sz5h3pnFlags & HALFCARRY_MASK) != 0 || (regA & 0x0f) > 0x09) {
      suma := 6
    }
    if (carry || (regA > 0x99)) {
      suma |= 0x60
    }
    if (regA > 0x99) {
      carry := true
    }
    if ((sz5h3pnFlags & ADDSUB_MASK) != 0) {
      sub(suma)
      sz5h3pnFlags = (sz5h3pnFlags & HALFCARRY_MASK) | sz53pn_subTable(regA)
    } else {
      add(suma)
      sz5h3pnFlags = (sz5h3pnFlags & HALFCARRY_MASK) | sz53pn_addTable(regA)
    }
    carryFlag := carry
    flagQ = true
  }

  private def pop(): BitContainer = {
    val word = MemIoImpl.peek16(regSP)
    regSP := (regSP + 2) & 0xffff
    word
  }

  private def push(word: BitContainer) {
    regSP := (regSP - 1) & 0xffff
    MemIoImpl.poke8(regSP, word >>> 8)
    regSP := (regSP - 1) & 0xffff
    MemIoImpl.poke8(regSP, word)
  }

  private def ldi() {
    val work8 = MemIoImpl.peek8(getRegHL)
    val regDE = getRegDE
    MemIoImpl.poke8(regDE, work8)
    MemIoImpl.contendedStates(regDE, 2)
    incRegHL()
    incRegDE()
    decRegBC()
    work8 += regA
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZ_MASK) | (work8 & BIT3_MASK)
    if ((work8 & ADDSUB_MASK) != 0) {
      sz5h3pnFlags |= BIT5_MASK
    }
    if (regC != 0 || regB != 0) {
      sz5h3pnFlags |= PARITY_MASK
    }
    flagQ = true
  }

  private def ldd() {
    val work8 = MemIoImpl.peek8(getRegHL)
    val regDE = getRegDE
    MemIoImpl.poke8(regDE, work8)
    MemIoImpl.contendedStates(regDE, 2)
    decRegHL()
    decRegDE()
    decRegBC()
    work8 += regA
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZ_MASK) | (work8 & BIT3_MASK)
    if ((work8 & ADDSUB_MASK) != 0) {
      sz5h3pnFlags |= BIT5_MASK
    }
    if (regC != 0 || regB != 0) {
      sz5h3pnFlags |= PARITY_MASK
    }
    flagQ = true
  }

  private def cpi() {
    val regHL = getRegHL
    val memHL = MemIoImpl.peek8(regHL)
    val carry = new Bit(carryFlag)
    cp(memHL)
    carryFlag := carry
    MemIoImpl.contendedStates(regHL, 5)
    incRegHL()
    decRegBC()
    memHL := regA - memHL -
      (if ((sz5h3pnFlags & HALFCARRY_MASK) != 0) 1 else 0)
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHN_MASK) | (memHL & BIT3_MASK)
    if ((memHL & ADDSUB_MASK) != 0) { 
      sz5h3pnFlags |= BIT5_MASK
    }
    if (regC != 0 || regB != 0) {
      sz5h3pnFlags |= PARITY_MASK
    }
    memptr += 1
    flagQ = true
  }

  private def cpd() {
    val regHL = getRegHL
    val memHL = MemIoImpl.peek8(regHL)
    val carry = new Bit(carryFlag)
    cp(memHL)
    carryFlag := carry
    MemIoImpl.contendedStates(regHL, 5)
    decRegHL()
    decRegBC()
    memHL := regA - memHL -
      (if ((sz5h3pnFlags & HALFCARRY_MASK) != 0) 1 else 0)
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHN_MASK) | (memHL & BIT3_MASK)
    if ((memHL & ADDSUB_MASK) != 0) {
      sz5h3pnFlags |= BIT5_MASK
    }
    if (regC != 0 || regB != 0) {
      sz5h3pnFlags |= PARITY_MASK
    }
    memptr -= 1
    flagQ = true
  }

  private def ini() {
    memptr := getRegBC
    MemIoImpl.contendedStates(getPairIR, 1)
    val work8 = MemIoImpl.inPort(memptr)
    MemIoImpl.poke8(getRegHL, work8)
    memptr += 1
    regB := (regB - 1) & 0xff
    incRegHL()
    sz5h3pnFlags = sz53pn_addTable(regB)
    if (work8 > 0x7f) {
      sz5h3pnFlags |= ADDSUB_MASK
    }
    carryFlag := false
    val tmp = work8 + ((regC + 1) & 0xff)
    if (tmp > 0xff) {
      sz5h3pnFlags |= HALFCARRY_MASK
      carryFlag := true
    }
    if ((sz53pn_addTable(((tmp & 0x07) ^ regB)) & PARITY_MASK) ==
      PARITY_MASK) {
      sz5h3pnFlags |= PARITY_MASK
    } else {
      sz5h3pnFlags &= ~PARITY_MASK
    }
    flagQ = true
  }

  private def ind() {
    memptr := getRegBC
    MemIoImpl.contendedStates(getPairIR, 1)
    val work8 = MemIoImpl.inPort(memptr)
    MemIoImpl.poke8(getRegHL, work8)
    memptr -= 1
    regB := (regB - 1) & 0xff
    decRegHL()
    sz5h3pnFlags = sz53pn_addTable(regB)
    if (work8 > 0x7f) {
      sz5h3pnFlags |= ADDSUB_MASK
    }
    carryFlag := false
    val tmp = work8 + ((regC - 1) & 0xff)
    if (tmp > 0xff) {
      sz5h3pnFlags |= HALFCARRY_MASK
      carryFlag := true
    }
    if ((sz53pn_addTable(((tmp & 0x07) ^ regB)) & PARITY_MASK) ==
      PARITY_MASK) {
      sz5h3pnFlags |= PARITY_MASK
    } else {
      sz5h3pnFlags &= ~PARITY_MASK
    }
    flagQ = true
  }

  private def outi() {
    MemIoImpl.contendedStates(getPairIR, 1)
    regB := (regB - 1) & 0xff
    memptr := getRegBC
    val work8 = MemIoImpl.peek8(getRegHL)
    MemIoImpl.outPort(memptr, work8)
    memptr += 1
    incRegHL()
    carryFlag := false
    if (work8 > 0x7f) sz5h3pnFlags = sz53n_subTable(regB) else sz5h3pnFlags = sz53n_addTable(regB)
    if ((regL + work8) > 0xff) {
      sz5h3pnFlags |= HALFCARRY_MASK
      carryFlag := true
    }
    if ((sz53pn_addTable((((regL + work8) & 0x07) ^ regB)) & PARITY_MASK) ==
      PARITY_MASK) {
      sz5h3pnFlags |= PARITY_MASK
    }
    flagQ = true
  }

  private def outd() {
    MemIoImpl.contendedStates(getPairIR, 1)
    regB := (regB - 1) & 0xff
    memptr := getRegBC
    val work8 = MemIoImpl.peek8(getRegHL)
    MemIoImpl.outPort(memptr, work8)
    memptr -= 1
    decRegHL()
    carryFlag := false
    if (work8 > 0x7f) sz5h3pnFlags = sz53n_subTable(regB) else sz5h3pnFlags = sz53n_addTable(regB)
    if ((regL + work8) > 0xff) {
      sz5h3pnFlags |= HALFCARRY_MASK
      carryFlag := true
    }
    if ((sz53pn_addTable((((regL + work8) & 0x07) ^ regB)) & PARITY_MASK) ==
      PARITY_MASK) {
      sz5h3pnFlags |= PARITY_MASK
    }
    flagQ = true
  }

  private def bit(mask: BitContainer, reg: BitContainer) {
    val zeroFlag = (mask & reg) == 0
    sz5h3pnFlags = sz53n_addTable(reg) & ~FLAG_SZP_MASK | HALFCARRY_MASK
    if (zeroFlag) {
      sz5h3pnFlags |= (PARITY_MASK | ZERO_MASK)
    }
    if (mask == SIGN_MASK && !zeroFlag) {
      sz5h3pnFlags |= SIGN_MASK
    }
    flagQ = true
  }

  private def interruption() {
    if (halted) {
      halted = false
      regPC := (regPC + 1) & 0xffff
    }
    clock.addTstates(7)
    regR += 1
    ffIFF2 = false
    ffIFF1 = ffIFF2
    push(regPC)
    if (modeINT == IntMode.IM2) regPC := MemIoImpl.peek16((regI << 8) | 0xff) else regPC := 0x0038
    memptr := regPC
  }

  private def nmi() {
    MemIoImpl.fetchOpcode(regPC)
    clock.addTstates(1)
    if (halted) {
      halted = false
      regPC := (regPC + 1) & 0xffff
    }
    regR += 1
    ffIFF1 = false
    push(regPC)
    memptr := 0x0066
    regPC := memptr
  }

  def isBreakpoint(address: Int): Boolean = breakpointAt(address & 0xffff)

  def setBreakpoint(address: Int, state: Boolean) {
    breakpointAt(address & 0xffff) = state
  }

  def resetBreakpoints() {
    Arrays.fill(breakpointAt, false)
  }

  def execute(statesLimit: BitContainer) {
//    ZXLogger.logState("Execute", this);

    while (clock.getTstates < statesLimit) {
      if (activeNMI) {
        activeNMI = false
        lastFlagQ = false
        nmi()
        //continue
      }
      if (activeINT) {
        if (ffIFF1 && !pendingEI) {
          lastFlagQ = false
          interruption()
        }
      }
      regR += 1
      opCode = MemIoImpl.fetchOpcode(regPC)
      if (breakpointAt(regPC)) {
        opCode = NotifyImpl.atAddress(regPC, opCode)
      }
      regPC := (regPC + 1) & 0xffff
      flagQ = false

//      ZXLogger.logOpCode(opCode);
//      ZXLogger.logState("Before opcode", this);
      decodeOpcode(opCode);
      BitContainerFactory.freeInstances
//      ZXLogger.logState("After opcode", this);
      //decodeOpcode(opCode)
      lastFlagQ = flagQ
      if (pendingEI && opCode != 0xFB) {
        pendingEI = false
      }
      if (execDone) {
        NotifyImpl.execDone()
      }
    }
  }

  private def decodeOpcode(opCode: Int) {

    Z80.decodedOpcodes+= 1
    
    opCode match {
      case 0x00 => {
        //break
      }
      case 0x01 => {
        setRegBC(MemIoImpl.peek16(regPC))
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x02 => {
        MemIoImpl.poke8(getRegBC, regA)
        memptr := (regA << 8) | ((regC + 1) & 0xff)
        //break
      }
      case 0x03 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        incRegBC()
        //break
      }
      case 0x04 => {
        regB := inc8(regB)
        //break
      }
      case 0x05 => {
        regB := dec8(regB)
        //break
      }
      case 0x06 => {
        regB := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x07 => {
        carryFlag := (regA > 0x7f)
        regA := (regA << 1) & 0xff
        if (carryFlag) {
          regA |= CARRY_MASK
        }
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (regA & FLAG_53_MASK)
        flagQ = true
        //break
      }
      case 0x08 => {
        val work8 = BitContainerFactory.create(regA)
        regA := regAx
        regAx := work8
        work8 := getFlags
        setFlags(regFx)
        regFx := work8
        //break
      }
      case 0x09 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        setRegHL(add16(getRegHL, getRegBC))
        //break
      }
      case 0x0A => {
        memptr := getRegBC
        regA := MemIoImpl.peek8(memptr)
        memptr += 1
        //break
      }
      case 0x0B => {
        MemIoImpl.contendedStates(getPairIR, 2)
        decRegBC()
        //break
      }
      case 0x0C => {
        regC := inc8(regC)
        //break
      }
      case 0x0D => {
        regC := dec8(regC)
        //break
      }
      case 0x0E => {
        regC := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x0F => {
        carryFlag := (regA & CARRY_MASK) != 0
        regA >>>= 1
        if (carryFlag) {
          regA |= SIGN_MASK
        }
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (regA & FLAG_53_MASK)
        flagQ = true
        //break
      }
      case 0x10 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        val offset = MemIoImpl.peek8(regPC).toByte
        regB -= 1
        if (regB != 0) {
          regB &= 0xff
          MemIoImpl.contendedStates(regPC, 5)
          memptr := (regPC + offset + 1) & 0xffff
          regPC := memptr
        } else {
          regPC := (regPC + 1) & 0xffff
        }
        //break
      }
      case 0x11 => {
        setRegDE(MemIoImpl.peek16(regPC))
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x12 => {
        MemIoImpl.poke8(getRegDE, regA)
        memptr := (regA << 8) | ((regE + 1) & 0xff)
        //break
      }
      case 0x13 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        incRegDE()
        //break
      }
      case 0x14 => {
        regD := inc8(regD)
        //break
      }
      case 0x15 => {
        regD := dec8(regD)
        //break
      }
      case 0x16 => {
        regD := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x17 => {
        val oldCarry = new Bit(carryFlag)
        carryFlag := (regA > 0x7f)
        regA := (regA << 1) & 0xff
        if (oldCarry) {
          regA |= CARRY_MASK
        }
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (regA & FLAG_53_MASK)
        flagQ = true
        //break
      }
      case 0x18 => {
        val offset = MemIoImpl.peek8(regPC).toByte
        MemIoImpl.contendedStates(regPC, 5)
        memptr := (regPC + offset + 1) & 0xffff
        regPC := memptr
        //break
      }
      case 0x19 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        setRegHL(add16(getRegHL, getRegDE))
        //break
      }
      case 0x1A => {
        memptr := getRegDE
        regA := MemIoImpl.peek8(memptr)
        memptr += 1
        //break
      }
      case 0x1B => {
        MemIoImpl.contendedStates(getPairIR, 2)
        decRegDE()
        //break
      }
      case 0x1C => {
        regE := inc8(regE)
        //break
      }
      case 0x1D => {
        regE := dec8(regE)
        //break
      }
      case 0x1E => {
        regE := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x1F => {
        val oldCarry = new Bit(carryFlag)
        carryFlag := (regA & CARRY_MASK) != 0
        regA >>>= 1
        if (oldCarry) {
          regA |= SIGN_MASK
        }
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (regA & FLAG_53_MASK)
        flagQ = true
        //break
      }
      case 0x20 => {
        val offset = MemIoImpl.peek8(regPC).toByte
        if ((sz5h3pnFlags & ZERO_MASK) == 0) {
          MemIoImpl.contendedStates(regPC, 5)
          regPC += offset
          memptr := regPC + 1
        }
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x21 => {
        setRegHL(MemIoImpl.peek16(regPC))
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x22 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, getRegHL)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x23 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        incRegHL()
        //break
      }
      case 0x24 => {
        regH := inc8(regH)
        //break
      }
      case 0x25 => {
        regH := dec8(regH)
        //break
      }
      case 0x26 => {
        regH := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x27 => {
        daa()
        //break
      }
      case 0x28 => {
        val offset = MemIoImpl.peek8(regPC).toByte
        if ((sz5h3pnFlags & ZERO_MASK) != 0) {
          MemIoImpl.contendedStates(regPC, 5)
          regPC += offset
          memptr := regPC + 1
        }
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x29 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        val work16 = getRegHL
        setRegHL(add16(work16, work16))
        //break
      }
      case 0x2A => {
        memptr := MemIoImpl.peek16(regPC)
        setRegHL(MemIoImpl.peek16(memptr))
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x2B => {
        MemIoImpl.contendedStates(getPairIR, 2)
        decRegHL()
        //break
      }
      case 0x2C => {
        regL := inc8(regL)
        //break
      }
      case 0x2D => {
        regL := dec8(regL)
        //break
      }
      case 0x2E => {
        regL := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x2F => {
        regA ^= 0xff
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | HALFCARRY_MASK | (regA & FLAG_53_MASK) |
          ADDSUB_MASK
        flagQ = true
        //break
      }
      case 0x30 => {
        val offset = MemIoImpl.peek8(regPC).toByte
        if (!carryFlag) {
          MemIoImpl.contendedStates(regPC, 5)
          regPC += offset
          memptr := regPC + 1
        }
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x31 => {
        regSP := MemIoImpl.peek16(regPC)
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x32 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke8(memptr, regA)
        memptr := (regA << 8) | ((memptr + 1) & 0xff)
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x33 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        regSP := (regSP + 1) & 0xffff
        //break
      }
      case 0x34 => {
        val work16 = getRegHL
        val work8 = inc8(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x35 => {
        val work16 = getRegHL
        val work8 = dec8(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x36 => {
        MemIoImpl.poke8(getRegHL, MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x37 => {
        var regQ: BitContainer = if (lastFlagQ) new BitContainer(sz5h3pnFlags) else BitContainerFactory.create(0)
        carryFlag := true
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (((regQ ^ sz5h3pnFlags) | regA) & FLAG_53_MASK)
        flagQ = true
        //break
      }
      case 0x38 => {
        val offset = MemIoImpl.peek8(regPC).toByte
        if (carryFlag) {
          MemIoImpl.contendedStates(regPC, 5)
          regPC += offset
          memptr := regPC + 1
        }
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x39 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        setRegHL(add16(getRegHL, regSP))
        //break
      }
      case 0x3A => {
        memptr := MemIoImpl.peek16(regPC)
        regA := MemIoImpl.peek8(memptr)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x3B => {
        MemIoImpl.contendedStates(getPairIR, 2)
        regSP := (regSP - 1) & 0xffff
        //break
      }
      case 0x3C => {
        regA := inc8(regA)
        //break
      }
      case 0x3D => {
        regA := dec8(regA)
        //break
      }
      case 0x3E => {
        regA := MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x3F => {
        var regQ: BitContainer = if (lastFlagQ) new BitContainer(sz5h3pnFlags) else BitContainerFactory.create(0)
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (((regQ ^ sz5h3pnFlags) | regA) & FLAG_53_MASK)
        if (carryFlag) {
          sz5h3pnFlags |= HALFCARRY_MASK
        }
        carryFlag := !carryFlag
        flagQ = true
        //break
      }
      case 0x41 => {
        regB := regC
        //break
      }
      case 0x42 => {
        regB := regD
        //break
      }
      case 0x43 => {
        regB := regE
        //break
      }
      case 0x44 => {
        regB := regH
        //break
      }
      case 0x45 => {
        regB := regL
        //break
      }
      case 0x46 => {
        regB := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x47 => {
        regB := regA
        //break
      }
      case 0x48 => {
        regC := regB
        //break
      }
      case 0x4A => {
        regC := regD
        //break
      }
      case 0x4B => {
        regC := regE
        //break
      }
      case 0x4C => {
        regC := regH
        //break
      }
      case 0x4D => {
        regC := regL
        //break
      }
      case 0x4E => {
        regC := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x4F => {
        regC := regA
        //break
      }
      case 0x50 => {
        regD := regB
        //break
      }
      case 0x51 => {
        regD := regC
        //break
      }
      case 0x53 => {
        regD := regE
        //break
      }
      case 0x54 => {
        regD := regH
        //break
      }
      case 0x55 => {
        regD := regL
        //break
      }
      case 0x56 => {
        regD := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x57 => {
        regD := regA
        //break
      }
      case 0x58 => {
        regE := regB
        //break
      }
      case 0x59 => {
        regE := regC
        //break
      }
      case 0x5A => {
        regE := regD
        //break
      }
      case 0x5C => {
        regE := regH
        //break
      }
      case 0x5D => {
        regE := regL
        //break
      }
      case 0x5E => {
        regE := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x5F => {
        regE := regA
        //break
      }
      case 0x60 => {
        regH := regB
        //break
      }
      case 0x61 => {
        regH := regC
        //break
      }
      case 0x62 => {
        regH := regD
        //break
      }
      case 0x63 => {
        regH := regE
        //break
      }
      case 0x65 => {
        regH := regL
        //break
      }
      case 0x66 => {
        regH := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x67 => {
        regH := regA
        //break
      }
      case 0x68 => {
        regL := regB
        //break
      }
      case 0x69 => {
        regL := regC
        //break
      }
      case 0x6A => {
        regL := regD
        //break
      }
      case 0x6B => {
        regL := regE
        //break
      }
      case 0x6C => {
        regL := regH
        //break
      }
      case 0x6E => {
        regL := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x6F => {
        regL := regA
        //break
      }
      case 0x70 => {
        MemIoImpl.poke8(getRegHL, regB)
        //break
      }
      case 0x71 => {
        MemIoImpl.poke8(getRegHL, regC)
        //break
      }
      case 0x72 => {
        MemIoImpl.poke8(getRegHL, regD)
        //break
      }
      case 0x73 => {
        MemIoImpl.poke8(getRegHL, regE)
        //break
      }
      case 0x74 => {
        MemIoImpl.poke8(getRegHL, regH)
        //break
      }
      case 0x75 => {
        MemIoImpl.poke8(getRegHL, regL)
        //break
      }
      case 0x76 => {
        regPC := (regPC - 1) & 0xffff
        halted = true
        //break
      }
      case 0x77 => {
        MemIoImpl.poke8(getRegHL, regA)
        //break
      }
      case 0x78 => {
        regA := regB
        //break
      }
      case 0x79 => {
        regA := regC
        //break
      }
      case 0x7A => {
        regA := regD
        //break
      }
      case 0x7B => {
        regA := regE
        //break
      }
      case 0x7C => {
        regA := regH
        //break
      }
      case 0x7D => {
        regA := regL
        //break
      }
      case 0x7E => {
        regA := MemIoImpl.peek8(getRegHL)
        //break
      }
      case 0x80 => {
        add(regB)
        //break
      }
      case 0x81 => {
        add(regC)
        //break
      }
      case 0x82 => {
        add(regD)
        //break
      }
      case 0x83 => {
        add(regE)
        //break
      }
      case 0x84 => {
        add(regH)
        //break
      }
      case 0x85 => {
        add(regL)
        //break
      }
      case 0x86 => {
        add(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0x87 => {
        add(regA)
        //break
      }
      case 0x88 => {
        adc(regB)
        //break
      }
      case 0x89 => {
        adc(regC)
        //break
      }
      case 0x8A => {
        adc(regD)
        //break
      }
      case 0x8B => {
        adc(regE)
        //break
      }
      case 0x8C => {
        adc(regH)
        //break
      }
      case 0x8D => {
        adc(regL)
        //break
      }
      case 0x8E => {
        adc(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0x8F => {
        adc(regA)
        //break
      }
      case 0x90 => {
        sub(regB)
        //break
      }
      case 0x91 => {
        sub(regC)
        //break
      }
      case 0x92 => {
        sub(regD)
        //break
      }
      case 0x93 => {
        sub(regE)
        //break
      }
      case 0x94 => {
        sub(regH)
        //break
      }
      case 0x95 => {
        sub(regL)
        //break
      }
      case 0x96 => {
        sub(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0x97 => {
        sub(regA)
        //break
      }
      case 0x98 => {
        sbc(regB)
        //break
      }
      case 0x99 => {
        sbc(regC)
        //break
      }
      case 0x9A => {
        sbc(regD)
        //break
      }
      case 0x9B => {
        sbc(regE)
        //break
      }
      case 0x9C => {
        sbc(regH)
        //break
      }
      case 0x9D => {
        sbc(regL)
        //break
      }
      case 0x9E => {
        sbc(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0x9F => {
        sbc(regA)
        //break
      }
      case 0xA0 => {
        and(regB)
        //break
      }
      case 0xA1 => {
        and(regC)
        //break
      }
      case 0xA2 => {
        and(regD)
        //break
      }
      case 0xA3 => {
        and(regE)
        //break
      }
      case 0xA4 => {
        and(regH)
        //break
      }
      case 0xA5 => {
        and(regL)
        //break
      }
      case 0xA6 => {
        and(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0xA7 => {
        and(regA)
        //break
      }
      case 0xA8 => {
        xor(regB)
        //break
      }
      case 0xA9 => {
        xor(regC)
        //break
      }
      case 0xAA => {
        xor(regD)
        //break
      }
      case 0xAB => {
        xor(regE)
        //break
      }
      case 0xAC => {
        xor(regH)
        //break
      }
      case 0xAD => {
        xor(regL)
        //break
      }
      case 0xAE => {
        xor(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0xAF => {
        xor(regA)
        //break
      }
      case 0xB0 => {
        or(regB)
        //break
      }
      case 0xB1 => {
        or(regC)
        //break
      }
      case 0xB2 => {
        or(regD)
        //break
      }
      case 0xB3 => {
        or(regE)
        //break
      }
      case 0xB4 => {
        or(regH)
        //break
      }
      case 0xB5 => {
        or(regL)
        //break
      }
      case 0xB6 => {
        or(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0xB7 => {
        or(regA)
        //break
      }
      case 0xB8 => {
        cp(regB)
        //break
      }
      case 0xB9 => {
        cp(regC)
        //break
      }
      case 0xBA => {
        cp(regD)
        //break
      }
      case 0xBB => {
        cp(regE)
        //break
      }
      case 0xBC => {
        cp(regH)
        //break
      }
      case 0xBD => {
        cp(regL)
        //break
      } 
      case 0xBE => {
        cp(MemIoImpl.peek8(getRegHL))
        //break
      }
      case 0xBF => {
        cp(regA)
        //break
      }
      case 0xC0 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        if ((sz5h3pnFlags & ZERO_MASK) == 0) {
          memptr := pop()
          regPC := memptr
        }
        //break
      }
      case 0xC1 => {
        setRegBC(pop())
        //break
      }
      case 0xC2 => {
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & ZERO_MASK) == 0) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xC3 => {
        regPC := MemIoImpl.peek16(regPC)
        memptr := regPC
        //break
      }
      case 0xC4 => {
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & ZERO_MASK) == 0) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xC5 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(getRegBC)
        //break
      }
      case 0xC6 => {
        add(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xC7 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x00
        regPC := memptr
        //break
      }
      case 0xC8 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        if ((sz5h3pnFlags & ZERO_MASK) != 0) {
          memptr := pop()
          regPC := memptr
        }
        //break
      }
      case 0xC9 => {
        memptr := pop()
        regPC := memptr
        //break
      }
      case 0xCA => {
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & ZERO_MASK) != 0) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xCB => {
        decodeCB()
        //break
      }
      case 0xCC => {
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & ZERO_MASK) != 0) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xCD => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
        push(regPC + 2)
        regPC := memptr
        //break
      }
      case 0xCE => {
        adc(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xCF => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x08
        regPC := memptr
        //break
      }
      case 0xD0 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        if (!carryFlag) {
          memptr := pop()
          regPC := memptr
        }
        //break
      }
      case 0xD1 => {
        setRegDE(pop())
        //break
      }
      case 0xD2 => {
        memptr := MemIoImpl.peek16(regPC)
        if (!carryFlag) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xD3 => {
        val work8 = MemIoImpl.peek8(regPC)
        memptr := regA << 8
        MemIoImpl.outPort(memptr | work8, regA)
        memptr |= ((work8 + 1) & 0xff)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xD4 => {
        memptr := MemIoImpl.peek16(regPC)
        if (!carryFlag) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xD5 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(getRegDE)
        //break
      }
      case 0xD6 => {
        sub(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xD7 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x10
        regPC := memptr
        //break
      }
      case 0xD8 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        if (carryFlag) {
          memptr := pop()
          regPC := memptr
        }
        //break
      }
      case 0xD9 => {
        val work8 = BitContainerFactory.create(regB)
        regB := regBx
        regBx := work8
        work8 := regC
        regC := regCx
        regCx := work8
        work8 := regD
        regD := regDx
        regDx := work8
        work8 := regE
        regE := regEx
        regEx := work8
        work8 := regH
        regH := regHx
        regHx := work8
        work8 := regL
        regL := regLx
        regLx := work8
        //break
      }
      case 0xDA => {
        memptr := MemIoImpl.peek16(regPC)
        if (carryFlag) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xDB => {
        memptr := (regA << 8) | MemIoImpl.peek8(regPC)
        regA := MemIoImpl.inPort(memptr)
        memptr += 1
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xDC => {
        memptr := MemIoImpl.peek16(regPC)
        if (carryFlag) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xDD => {
        regIX := decodeDDFD(regIX)
        //break
      }
      case 0xDE => {
        sbc(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xDF => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x18
        regPC := memptr
        //break
      }
      case 0xE0 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        if ((sz5h3pnFlags & PARITY_MASK) == 0) {
          memptr := pop()
          regPC := memptr
        }

      case 0xE1 => setRegHL(pop())
      case 0xE2 =>
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & PARITY_MASK) == 0) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xE3 => {
        val work16 = BitContainerFactory.create(regH)
        val work8 = BitContainerFactory.create(regL)
        setRegHL(MemIoImpl.peek16(regSP))
        MemIoImpl.contendedStates((regSP + 1) & 0xffff, 1)
        MemIoImpl.poke8((regSP + 1) & 0xffff, work16)
        MemIoImpl.poke8(regSP, work8)
        MemIoImpl.contendedStates(regSP, 2)
        memptr := getRegHL
        //break
      }
      case 0xE4 =>
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & PARITY_MASK) == 0) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xE5 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(getRegHL)

      case 0xE6 =>
        and(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff

      case 0xE7 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x20
        regPC := memptr

      case 0xE8 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        if ((sz5h3pnFlags & PARITY_MASK) != 0) {
          memptr := pop()
          regPC := memptr
        }

      case 0xE9 => regPC := getRegHL
      case 0xEA =>
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & PARITY_MASK) != 0) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xEB => {
        val work8 = BitContainerFactory.create(regH)
        regH := regD
        regD := work8
        work8 := regL
        regL := regE
        regE := work8
        //break
      }
      case 0xEC =>
        memptr := MemIoImpl.peek16(regPC)
        if ((sz5h3pnFlags & PARITY_MASK) != 0) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xED => decodeED()
      case 0xEE =>
        xor(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff

      case 0xEF =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x28
        regPC := memptr

      case 0xF0 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        if (sz5h3pnFlags < SIGN_MASK) {
          memptr := pop()
          regPC := memptr
        }

      case 0xF1 => setRegAF(pop())
      case 0xF2 =>
        memptr := MemIoImpl.peek16(regPC)
        if (sz5h3pnFlags < SIGN_MASK) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xF3 => {
        ffIFF2 = false
        ffIFF1 = ffIFF2

      }
      case 0xF4 =>
        memptr := MemIoImpl.peek16(regPC)
        if (sz5h3pnFlags < SIGN_MASK) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xF5 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(getRegAF)

      case 0xF6 =>
        or(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff

      case 0xF7 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x30
        regPC := memptr

      case 0xF8 =>
        MemIoImpl.contendedStates(getPairIR, 1)
        if (sz5h3pnFlags > 0x7f) {
          memptr := pop()
          regPC := memptr
        }

      case 0xF9 =>
        MemIoImpl.contendedStates(getPairIR, 2)
        regSP := getRegHL

      case 0xFA =>
        memptr := MemIoImpl.peek16(regPC)
        if (sz5h3pnFlags > 0x7f) {
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xFB =>
        ffIFF2 = true
        ffIFF1 = ffIFF2
        pendingEI = true

      case 0xFC =>
        memptr := MemIoImpl.peek16(regPC)
        if (sz5h3pnFlags > 0x7f) {
          MemIoImpl.contendedStates((regPC + 1) & 0xffff, 1)
          push(regPC + 2)
          regPC := memptr
          //break
        } else
          regPC := (regPC + 2) & 0xffff

      case 0xFD => regIY := decodeDDFD(regIY)
      case 0xFE =>
        cp(MemIoImpl.peek8(regPC))
        regPC := (regPC + 1) & 0xffff

      case 0xFF =>
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regPC)
        memptr := 0x38
        regPC := memptr
    }
  }

  private def decodeCB() {
    regR += 1
    opCode = MemIoImpl.fetchOpcode(regPC)
    regPC := (regPC + 1) & 0xffff
    opCode match {
      case 0x00 => {
        regB := rlc(regB)
        //break
      }
      case 0x01 => {
        regC := rlc(regC)
        //break
      }
      case 0x02 => {
        regD := rlc(regD)
        //break
      }
      case 0x03 => {
        regE := rlc(regE)
        //break
      }
      case 0x04 => {
        regH := rlc(regH)
        //break
      }
      case 0x05 => {
        regL := rlc(regL)
        //break
      }
      case 0x06 => {
        val work16 = getRegHL
        val work8 = rlc(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x07 => {
        regA := rlc(regA)
        //break
      }
      case 0x08 => {
        regB := rrc(regB)
        //break
      }
      case 0x09 => {
        regC := rrc(regC)
        //break
      }
      case 0x0A => {
        regD := rrc(regD)
        //break
      }
      case 0x0B => {
        regE := rrc(regE)
        //break
      }
      case 0x0C => {
        regH := rrc(regH)
        //break
      }
      case 0x0D => {
        regL := rrc(regL)
        //break
      }
      case 0x0E => {
        val work16 = getRegHL
        val work8 = rrc(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x0F => {
        regA := rrc(regA)
        //break
      }
      case 0x10 => {
        regB := rl(regB)
        //break
      }
      case 0x11 => {
        regC := rl(regC)
        //break
      }
      case 0x12 => {
        regD := rl(regD)
        //break
      }
      case 0x13 => {
        regE := rl(regE)
        //break
      }
      case 0x14 => {
        regH := rl(regH)
        //break
      }
      case 0x15 => {
        regL := rl(regL)
        //break
      }
      case 0x16 => {
        val work16 = getRegHL
        val work8 = rl(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x17 => {
        regA := rl(regA)
        //break
      }
      case 0x18 => {
        regB := rr(regB)
        //break
      }
      case 0x19 => {
        regC := rr(regC)
        //break
      }
      case 0x1A => {
        regD := rr(regD)
        //break
      }
      case 0x1B => {
        regE := rr(regE)
        //break
      }
      case 0x1C => {
        regH := rr(regH)
        //break
      }
      case 0x1D => {
        regL := rr(regL)
        //break
      }
      case 0x1E => {
        val work16 = getRegHL
        val work8 = rr(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x1F => {
        regA := rr(regA)
        //break
      }
      case 0x20 => {
        regB := sla(regB)
        //break
      }
      case 0x21 => {
        regC := sla(regC)
        //break
      }
      case 0x22 => {
        regD := sla(regD)
        //break
      }
      case 0x23 => {
        regE := sla(regE)
        //break
      }
      case 0x24 => {
        regH := sla(regH)
        //break
      }
      case 0x25 => {
        regL := sla(regL)
        //break
      }
      case 0x26 => {
        val work16 = getRegHL
        val work8 = sla(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x27 => {
        regA := sla(regA)
        //break
      }
      case 0x28 => {
        regB := sra(regB)
        //break
      }
      case 0x29 => {
        regC := sra(regC)
        //break
      }
      case 0x2A => {
        regD := sra(regD)
        //break
      }
      case 0x2B => {
        regE := sra(regE)
        //break
      }
      case 0x2C => {
        regH := sra(regH)
        //break
      }
      case 0x2D => {
        regL := sra(regL)
        //break
      }
      case 0x2E => {
        val work16 = getRegHL
        val work8 = sra(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x2F => {
        regA := sra(regA)
        //break
      }
      case 0x30 => {
        regB := sll(regB)
        //break
      }
      case 0x31 => {
        regC := sll(regC)
        //break
      }
      case 0x32 => {
        regD := sll(regD)
        //break
      }
      case 0x33 => {
        regE := sll(regE)
        //break
      }
      case 0x34 => {
        regH := sll(regH)
        //break
      }
      case 0x35 => {
        regL := sll(regL)
        //break
      }
      case 0x36 => {
        val work16 = getRegHL
        val work8 = sll(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x37 => {
        regA := sll(regA)
        //break
      }
      case 0x38 => {
        regB := srl(regB)
        //break
      }
      case 0x39 => {
        regC := srl(regC)
        //break
      }
      case 0x3A => {
        regD := srl(regD)
        //break
      }
      case 0x3B => {
        regE := srl(regE)
        //break
      }
      case 0x3C => {
        regH := srl(regH)
        //break
      }
      case 0x3D => {
        regL := srl(regL)
        //break
      }
      case 0x3E => {
        val work16 = getRegHL
        val work8 = srl(MemIoImpl.peek8(work16))
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x3F => {
        regA := srl(regA)
        //break
      }
      case 0x40 => {
        bit(0x01, regB)
        //break
      }
      case 0x41 => {
        bit(0x01, regC)
        //break
      }
      case 0x42 => {
        bit(0x01, regD)
        //break
      }
      case 0x43 => {
        bit(0x01, regE)
        //break
      }
      case 0x44 => {
        bit(0x01, regH)
        //break
      }
      case 0x45 => {
        bit(0x01, regL)
        //break
      }
      case 0x46 => {
        val work16 = getRegHL
        bit(0x01, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x47 => {
        bit(0x01, regA)
        //break
      }
      case 0x48 => {
        bit(0x02, regB)
        //break
      }
      case 0x49 => {
        bit(0x02, regC)
        //break
      }
      case 0x4A => {
        bit(0x02, regD)
        //break
      }
      case 0x4B => {
        bit(0x02, regE)
        //break
      }
      case 0x4C => {
        bit(0x02, regH)
        //break
      }
      case 0x4D => {
        bit(0x02, regL)
        //break
      }
      case 0x4E => {
        val work16 = getRegHL
        bit(0x02, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x4F => {
        bit(0x02, regA)
        //break
      }
      case 0x50 => {
        bit(0x04, regB)
        //break
      }
      case 0x51 => {
        bit(0x04, regC)
        //break
      }
      case 0x52 => {
        bit(0x04, regD)
        //break
      }
      case 0x53 => {
        bit(0x04, regE)
        //break
      }
      case 0x54 => {
        bit(0x04, regH)
        //break
      }
      case 0x55 => {
        bit(0x04, regL)
        //break
      }
      case 0x56 => {
        val work16 = getRegHL
        bit(0x04, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x57 => {
        bit(0x04, regA)
        //break
      }
      case 0x58 => {
        bit(0x08, regB)
        //break
      }
      case 0x59 => {
        bit(0x08, regC)
        //break
      }
      case 0x5A => {
        bit(0x08, regD)
        //break
      }
      case 0x5B => {
        bit(0x08, regE)
        //break
      }
      case 0x5C => {
        bit(0x08, regH)
        //break
      }
      case 0x5D => {
        bit(0x08, regL)
        //break
      }
      case 0x5E => {
        val work16 = getRegHL
        bit(0x08, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x5F => {
        bit(0x08, regA)
        //break
      }
      case 0x60 => {
        bit(0x10, regB)
        //break
      }
      case 0x61 => {
        bit(0x10, regC)
        //break
      }
      case 0x62 => {
        bit(0x10, regD)
        //break
      }
      case 0x63 => {
        bit(0x10, regE)
        //break
      }
      case 0x64 => {
        bit(0x10, regH)
        //break
      }
      case 0x65 => {
        bit(0x10, regL)
        //break
      }
      case 0x66 => {
        val work16 = getRegHL
        bit(0x10, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x67 => {
        bit(0x10, regA)
        //break
      }
      case 0x68 => {
        bit(0x20, regB)
        //break
      }
      case 0x69 => {
        bit(0x20, regC)
        //break
      }
      case 0x6A => {
        bit(0x20, regD)
        //break
      }
      case 0x6B => {
        bit(0x20, regE)
        //break
      }
      case 0x6C => {
        bit(0x20, regH)
        //break
      }
      case 0x6D => {
        bit(0x20, regL)
        //break
      }
      case 0x6E => {
        val work16 = getRegHL
        bit(0x20, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x6F => {
        bit(0x20, regA)
        //break
      }
      case 0x70 => {
        bit(0x40, regB)
        //break
      }
      case 0x71 => {
        bit(0x40, regC)
        //break
      }
      case 0x72 => {
        bit(0x40, regD)
        //break
      }
      case 0x73 => {
        bit(0x40, regE)
        //break
      }
      case 0x74 => {
        bit(0x40, regH)
        //break
      }
      case 0x75 => {
        bit(0x40, regL)
        //break
      }
      case 0x76 => {
        val work16 = getRegHL
        bit(0x40, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x77 => {
        bit(0x40, regA)
        //break
      }
      case 0x78 => {
        bit(0x80, regB)
        //break
      }
      case 0x79 => {
        bit(0x80, regC)
        //break
      }
      case 0x7A => {
        bit(0x80, regD)
        //break
      }
      case 0x7B => {
        bit(0x80, regE)
        //break
      }
      case 0x7C => {
        bit(0x80, regH)
        //break
      }
      case 0x7D => {
        bit(0x80, regL)
        //break
      }
      case 0x7E => {
        val work16 = getRegHL
        bit(0x80, MemIoImpl.peek8(work16))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((memptr >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(work16, 1)
        //break
      }
      case 0x7F => {
        bit(0x80, regA)
        //break
      }
      case 0x80 => {
        regB &= 0xFE
        //break
      }
      case 0x81 => {
        regC &= 0xFE
        //break
      }
      case 0x82 => {
        regD &= 0xFE
        //break
      }
      case 0x83 => {
        regE &= 0xFE
        //break
      }
      case 0x84 => {
        regH &= 0xFE
        //break
      }
      case 0x85 => {
        regL &= 0xFE
        //break
      }
      case 0x86 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xFE
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x87 => {
        regA &= 0xFE
        //break
      }
      case 0x88 => {
        regB &= 0xFD
        //break
      }
      case 0x89 => {
        regC &= 0xFD
        //break
      }
      case 0x8A => {
        regD &= 0xFD
        //break
      }
      case 0x8B => {
        regE &= 0xFD
        //break
      }
      case 0x8C => {
        regH &= 0xFD
        //break
      }
      case 0x8D => {
        regL &= 0xFD
        //break
      }
      case 0x8E => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xFD
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x8F => {
        regA &= 0xFD
        //break
      }
      case 0x90 => {
        regB &= 0xFB
        //break
      }
      case 0x91 => {
        regC &= 0xFB
        //break
      }
      case 0x92 => {
        regD &= 0xFB
        //break
      }
      case 0x93 => {
        regE &= 0xFB
        //break
      }
      case 0x94 => {
        regH &= 0xFB
        //break
      }
      case 0x95 => {
        regL &= 0xFB
        //break
      }
      case 0x96 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xFB
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x97 => {
        regA &= 0xFB
        //break
      }
      case 0x98 => {
        regB &= 0xF7
        //break
      }
      case 0x99 => {
        regC &= 0xF7
        //break
      }
      case 0x9A => {
        regD &= 0xF7
        //break
      }
      case 0x9B => {
        regE &= 0xF7
        //break
      }
      case 0x9C => {
        regH &= 0xF7
        //break
      }
      case 0x9D => {
        regL &= 0xF7
        //break
      }
      case 0x9E => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xF7
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0x9F => {
        regA &= 0xF7
        //break
      }
      case 0xA0 => {
        regB &= 0xEF
        //break
      }
      case 0xA1 => {
        regC &= 0xEF
        //break
      }
      case 0xA2 => {
        regD &= 0xEF
        //break
      }
      case 0xA3 => {
        regE &= 0xEF
        //break
      }
      case 0xA4 => {
        regH &= 0xEF
        //break
      }
      case 0xA5 => {
        regL &= 0xEF
        //break
      }
      case 0xA6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xEF
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xA7 => {
        regA &= 0xEF
        //break
      }
      case 0xA8 => {
        regB &= 0xDF
        //break
      }
      case 0xA9 => {
        regC &= 0xDF
        //break
      }
      case 0xAA => {
        regD &= 0xDF
        //break
      }
      case 0xAB => {
        regE &= 0xDF
        //break
      }
      case 0xAC => {
        regH &= 0xDF
        //break
      }
      case 0xAD => {
        regL &= 0xDF
        //break
      }
      case 0xAE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xDF
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xAF => {
        regA &= 0xDF
        //break
      }
      case 0xB0 => {
        regB &= 0xBF
        //break
      }
      case 0xB1 => {
        regC &= 0xBF
        //break
      }
      case 0xB2 => {
        regD &= 0xBF
        //break
      }
      case 0xB3 => {
        regE &= 0xBF
        //break
      }
      case 0xB4 => {
        regH &= 0xBF
        //break
      }
      case 0xB5 => {
        regL &= 0xBF
        //break
      }
      case 0xB6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0xBF
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xB7 => {
        regA &= 0xBF
        //break
      }
      case 0xB8 => {
        regB &= 0x7F
        //break
      }
      case 0xB9 => {
        regC &= 0x7F
        //break
      }
      case 0xBA => {
        regD &= 0x7F
        //break
      }
      case 0xBB => {
        regE &= 0x7F
        //break
      }
      case 0xBC => {
        regH &= 0x7F
        //break
      }
      case 0xBD => {
        regL &= 0x7F
        //break
      }
      case 0xBE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) & 0x7F
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xBF => {
        regA &= 0x7F
        //break
      }
      case 0xC0 => {
        regB |= 0x01
        //break
      }
      case 0xC1 => {
        regC |= 0x01
        //break
      }
      case 0xC2 => {
        regD |= 0x01
        //break
      }
      case 0xC3 => {
        regE |= 0x01
        //break
      }
      case 0xC4 => {
        regH |= 0x01
        //break
      }
      case 0xC5 => {
        regL |= 0x01
        //break
      }
      case 0xC6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x01
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xC7 => {
        regA |= 0x01
        //break
      }
      case 0xC8 => {
        regB |= 0x02
        //break
      }
      case 0xC9 => {
        regC |= 0x02
        //break
      }
      case 0xCA => {
        regD |= 0x02
        //break
      }
      case 0xCB => {
        regE |= 0x02
        //break
      }
      case 0xCC => {
        regH |= 0x02
        //break
      }
      case 0xCD => {
        regL |= 0x02
        //break
      }
      case 0xCE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x02
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xCF => {
        regA |= 0x02
        //break
      }
      case 0xD0 => {
        regB |= 0x04
        //break
      }
      case 0xD1 => {
        regC |= 0x04
        //break
      }
      case 0xD2 => {
        regD |= 0x04
        //break
      }
      case 0xD3 => {
        regE |= 0x04
        //break
      }
      case 0xD4 => {
        regH |= 0x04
        //break
      }
      case 0xD5 => {
        regL |= 0x04
        //break
      }
      case 0xD6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x04
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xD7 => {
        regA |= 0x04
        //break
      }
      case 0xD8 => {
        regB |= 0x08
        //break
      }
      case 0xD9 => {
        regC |= 0x08
        //break
      }
      case 0xDA => {
        regD |= 0x08
        //break
      }
      case 0xDB => {
        regE |= 0x08
        //break
      }
      case 0xDC => {
        regH |= 0x08
        //break
      }
      case 0xDD => {
        regL |= 0x08
        //break
      }
      case 0xDE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x08
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xDF => {
        regA |= 0x08
        //break
      }
      case 0xE0 => {
        regB |= 0x10
        //break
      }
      case 0xE1 => {
        regC |= 0x10
        //break
      }
      case 0xE2 => {
        regD |= 0x10
        //break
      }
      case 0xE3 => {
        regE |= 0x10
        //break
      }
      case 0xE4 => {
        regH |= 0x10
        //break
      }
      case 0xE5 => {
        regL |= 0x10
        //break
      }
      case 0xE6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x10
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xE7 => {
        regA |= 0x10
        //break
      }
      case 0xE8 => {
        regB |= 0x20
        //break
      }
      case 0xE9 => {
        regC |= 0x20
        //break
      }
      case 0xEA => {
        regD |= 0x20
        //break
      }
      case 0xEB => {
        regE |= 0x20
        //break
      }
      case 0xEC => {
        regH |= 0x20
        //break
      }
      case 0xED => {
        regL |= 0x20
        //break
      }
      case 0xEE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x20
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xEF => {
        regA |= 0x20
        //break
      }
      case 0xF0 => {
        regB |= 0x40
        //break
      }
      case 0xF1 => {
        regC |= 0x40
        //break
      }
      case 0xF2 => {
        regD |= 0x40
        //break
      }
      case 0xF3 => {
        regE |= 0x40
        //break
      }
      case 0xF4 => {
        regH |= 0x40
        //break
      }
      case 0xF5 => {
        regL |= 0x40
        //break
      }
      case 0xF6 => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x40
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xF7 => {
        regA |= 0x40
        //break
      }
      case 0xF8 => {
        regB |= 0x80
        //break
      }
      case 0xF9 => {
        regC |= 0x80
        //break
      }
      case 0xFA => {
        regD |= 0x80
        //break
      }
      case 0xFB => {
        regE |= 0x80
        //break
      }
      case 0xFC => {
        regH |= 0x80
        //break
      }
      case 0xFD => {
        regL |= 0x80
        //break
      }
      case 0xFE => {
        val work16 = getRegHL
        val work8 = MemIoImpl.peek8(work16) | 0x80
        MemIoImpl.contendedStates(work16, 1)
        MemIoImpl.poke8(work16, work8)
        //break
      }
      case 0xFF => {
        regA |= 0x80
        //break
      }
      case _ => {
        //break
      }
    }
  }

  private def decodeDDFD(regIXY1: BitContainer): BitContainer = {
    val regIXY = BitContainerFactory.create(regIXY1)

    regR += 1
    opCode = MemIoImpl.fetchOpcode(regPC)
    regPC := (regPC + 1) & 0xffff
    opCode match {
      case 0x09 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        regIXY := add16(regIXY, getRegBC)
        //break
      }
      case 0x19 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        regIXY := add16(regIXY, getRegDE)
        //break
      }
      case 0x21 => {
        regIXY := MemIoImpl.peek16(regPC)
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x22 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, regIXY)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x23 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        regIXY := (regIXY + 1) & 0xffff
        //break
      }
      case 0x24 => {
        regIXY := (inc8(regIXY >>> 8) << 8) | (regIXY & 0xff)
        //break
      }
      case 0x25 => {
        regIXY := (dec8(regIXY >>> 8) << 8) | (regIXY & 0xff)
        //break
      }
      case 0x26 => {
        regIXY := (MemIoImpl.peek8(regPC) << 8) | (regIXY & 0xff)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x29 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        regIXY := add16(regIXY, regIXY)
        //break
      }
      case 0x2A => {
        memptr := MemIoImpl.peek16(regPC)
        regIXY := MemIoImpl.peek16(memptr)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x2B => {
        MemIoImpl.contendedStates(getPairIR, 2)
        regIXY := (regIXY - 1) & 0xffff
        //break
      }
      case 0x2C => {
        regIXY := (regIXY & 0xff00) | inc8(regIXY & 0xff)
        //break
      }
      case 0x2D => {
        regIXY := (regIXY & 0xff00) | dec8(regIXY & 0xff)
        //break
      }
      case 0x2E => {
        regIXY := (regIXY & 0xff00) | MemIoImpl.peek8(regPC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x34 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        val work8 = MemIoImpl.peek8(memptr)
        MemIoImpl.contendedStates(memptr, 1)
        MemIoImpl.poke8(memptr, inc8(work8))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x35 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        val work8 = MemIoImpl.peek8(memptr)
        MemIoImpl.contendedStates(memptr, 1)
        MemIoImpl.poke8(memptr, dec8(work8))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x36 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        regPC := (regPC + 1) & 0xffff
        val work8 = MemIoImpl.peek8(regPC)
        MemIoImpl.contendedStates(regPC, 2)
        regPC := (regPC + 1) & 0xffff
        MemIoImpl.poke8(memptr, work8)
        //break
      }
      case 0x39 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        regIXY := add16(regIXY, regSP)
        //break
      }
      case 0x44 => {
        regB := regIXY >>> 8
        //break
      }
      case 0x45 => {
        regB := regIXY & 0xff
        //break
      }
      case 0x46 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regB := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x4C => {
        regC := regIXY >>> 8
        //break
      }
      case 0x4D => {
        regC := regIXY & 0xff
        //break
      }
      case 0x4E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regC := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x54 => {
        regD := regIXY >>> 8
        //break
      }
      case 0x55 => {
        regD := regIXY & 0xff
        //break
      }
      case 0x56 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regD := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x5C => {
        regE := regIXY >>> 8
        //break
      }
      case 0x5D => {
        regE := regIXY & 0xff
        //break
      }
      case 0x5E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regE := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x60 => {
        regIXY := (regIXY & 0x00ff) | (regB << 8)
        //break
      }
      case 0x61 => {
        regIXY := (regIXY & 0x00ff) | (regC << 8)
        //break
      }
      case 0x62 => {
        regIXY := (regIXY & 0x00ff) | (regD << 8)
        //break
      }
      case 0x63 => {
        regIXY := (regIXY & 0x00ff) | (regE << 8)
        //break
      }
      case 0x64 => {
        //break
      }
      case 0x65 => {
        regIXY := (regIXY & 0x00ff) | ((regIXY & 0xff) << 8)
        //break
      }
      case 0x66 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regH := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x67 => {
        regIXY := (regIXY & 0x00ff) | (regA << 8)
        //break
      }
      case 0x68 => {
        regIXY := (regIXY & 0xff00) | regB
        //break
      }
      case 0x69 => {
        regIXY := (regIXY & 0xff00) | regC
        //break
      }
      case 0x6A => {
        regIXY := (regIXY & 0xff00) | regD
        //break
      }
      case 0x6B => {
        regIXY := (regIXY & 0xff00) | regE
        //break
      }
      case 0x6C => {
        regIXY := (regIXY & 0xff00) | (regIXY >>> 8)
        //break
      }
      case 0x6D => {
        //break
      }
      case 0x6E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regL := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x6F => {
        regIXY := (regIXY & 0xff00) | regA
        //break
      }
      case 0x70 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regB)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x71 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regC)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x72 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regD)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x73 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regE)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x74 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regH)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x75 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regL)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x77 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        MemIoImpl.poke8(memptr, regA)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x7C => {
        regA := regIXY >>> 8
        //break
      }
      case 0x7D => {
        regA := regIXY & 0xff
        //break
      }
      case 0x7E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        regA := MemIoImpl.peek8(memptr)
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x84 => {
        add(regIXY >>> 8)
        //break
      }
      case 0x85 => {
        add(regIXY & 0xff)
        //break
      }
      case 0x86 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        add(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x8C => {
        adc(regIXY >>> 8)
        //break
      }
      case 0x8D => {
        adc(regIXY & 0xff)
        //break
      }
      case 0x8E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        adc(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x94 => {
        sub(regIXY >>> 8)
        //break
      }
      case 0x95 => {
        sub(regIXY & 0xff)
        //break
      }
      case 0x96 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        sub(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0x9C => {
        sbc(regIXY >>> 8)
        //break
      }
      case 0x9D => {
        sbc(regIXY & 0xff)
        //break
      }
      case 0x9E => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        sbc(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xA4 => {
        and(regIXY >>> 8)
        //break
      }
      case 0xA5 => {
        and(regIXY & 0xff)
        //break
      }
      case 0xA6 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        and(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xAC => {
        xor(regIXY >>> 8)
        //break
      }
      case 0xAD => {
        xor(regIXY & 0xff)
        //break
      }
      case 0xAE => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        xor(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xB4 => {
        or(regIXY >>> 8)
        //break
      }
      case 0xB5 => {
        or(regIXY & 0xff)
        //break
      }
      case 0xB6 => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        or(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xBC => {
        cp(regIXY >>> 8)
        //break
      }
      case 0xBD => {
        cp(regIXY & 0xff)
        //break
      }
      case 0xBE => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        MemIoImpl.contendedStates(regPC, 5)
        cp(MemIoImpl.peek8(memptr))
        regPC := (regPC + 1) & 0xffff
        //break
      }
      case 0xCB => {
        memptr := (regIXY + MemIoImpl.peek8(regPC).toByte) & 0xffff
        regPC := (regPC + 1) & 0xffff
        opCode = MemIoImpl.peek8(regPC)
        MemIoImpl.contendedStates(regPC, 2)
        regPC := (regPC + 1) & 0xffff
        if (opCode < 0x80) {
          decodeDDFDCBto7F(opCode, memptr)
        } else {
          decodeDDFDCBtoFF(opCode, memptr)
        }
        //break
      }
      case 0xE1 => {
        regIXY := pop()
        //break
      }
      case 0xE3 => {
        val work16 = regIXY
        regIXY := MemIoImpl.peek16(regSP)
        MemIoImpl.contendedStates((regSP + 1) & 0xffff, 1)
        MemIoImpl.poke8((regSP + 1) & 0xffff, work16 >>> 8)
        MemIoImpl.poke8(regSP, work16)
        MemIoImpl.contendedStates(regSP, 2)
        memptr := regIXY
        //break
      }
      case 0xE5 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        push(regIXY)
        //break
      }
      case 0xE9 => {
        regPC := regIXY
        //break
      }
      case 0xF9 => {
        MemIoImpl.contendedStates(getPairIR, 2)
        regSP := regIXY
        //break
      }
      case _ => {
        if (breakpointAt(regPC)) {
          opCode = NotifyImpl.atAddress(regPC, opCode)
        }
        decodeOpcode(opCode)
        //break
      }
    }
    regIXY
  }

  private def decodeDDFDCBto7F(opCode: Int, address: BitContainer) {

    opCode match {
      case 0x00 => {
        regB := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x01 => {
        regC := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x02 => {
        regD := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x03 => {
        regE := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x04 => {
        regH := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x05 => {
        regL := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x06 => {
        val work8 = rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x07 => {
        regA := rlc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x08 => {
        regB := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x09 => {
        regC := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x0A => {
        regD := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x0B => {
        regE := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x0C => {
        regH := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x0D => {
        regL := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x0E => {
        val work8 = rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x0F => {
        regA := rrc(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x10 => {
        regB := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x11 => {
        regC := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x12 => {
        regD := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x13 => {
        regE := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x14 => {
        regH := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x15 => {
        regL := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x16 => {
        val work8 = rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x17 => {
        regA := rl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x18 => {
        regB := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x19 => {
        regC := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x1A => {
        regD := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x1B => {
        regE := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x1C => {
        regH := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x1D => {
        regL := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x1E => {
        val work8 = rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x1F => {
        regA := rr(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x20 => {
        regB := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x21 => {
        regC := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x22 => {
        regD := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x23 => {
        regE := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x24 => {
        regH := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x25 => {
        regL := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x26 => {
        val work8 = sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x27 => {
        regA := sla(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x28 => {
        regB := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x29 => {
        regC := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x2A => {
        regD := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x2B => {
        regE := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x2C => {
        regH := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x2D => {
        regL := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x2E => {
        val work8 = sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x2F => {
        regA := sra(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x30 => {
        regB := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x31 => {
        regC := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x32 => {
        regD := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x33 => {
        regE := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x34 => {
        regH := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x35 => {
        regL := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x36 => {
        val work8 = sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x37 => {
        regA := sll(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x38 => {
        regB := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x39 => {
        regC := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x3A => {
        regD := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x3B => {
        regE := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x3C => {
        regH := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x3D => {
        regL := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x3E => {
        val work8 = srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x3F => {
        regA := srl(MemIoImpl.peek8(address))
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x40 | 0x41 | 0x42 | 0x43 | 0x44 | 0x45 | 0x46 | 0x47 => {
        bit(0x01, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x48 | 0x49 | 0x4A | 0x4B | 0x4C | 0x4D | 0x4E | 0x4F => {
        bit(0x02, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x50 | 0x51 | 0x52 | 0x53 | 0x54 | 0x55 | 0x56 | 0x57 => {
        bit(0x04, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x58 | 0x59 | 0x5A | 0x5B | 0x5C | 0x5D | 0x5E | 0x5F => {
        bit(0x08, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x60 | 0x61 | 0x62 | 0x63 | 0x64 | 0x65 | 0x66 | 0x67 => {
        bit(0x10, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x68 | 0x69 | 0x6A | 0x6B | 0x6C | 0x6D | 0x6E | 0x6F => {
        bit(0x20, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x70 | 0x71 | 0x72 | 0x73 | 0x74 | 0x75 | 0x76 | 0x77 => {
        bit(0x40, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
      case 0x78 | 0x79 | 0x7A | 0x7B | 0x7C | 0x7D | 0x7E | 0x7F => {
        bit(0x80, MemIoImpl.peek8(address))
        sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZHP_MASK) | ((address >>> 8) & FLAG_53_MASK)
        MemIoImpl.contendedStates(address, 1)
        //break
      }
    }
  }

  private def decodeDDFDCBtoFF(opCode: Int, address: BitContainer) {

    opCode match {
      case 0x80 => {
        regB := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x81 => {
        regC := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x82 => {
        regD := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x83 => {
        regE := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x84 => {
        regH := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x85 => {
        regL := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x86 => {
        val work8 = MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x87 => {
        regA := MemIoImpl.peek8(address) & 0xFE
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x88 => {
        regB := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x89 => {
        regC := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x8A => {
        regD := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x8B => {
        regE := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x8C => {
        regH := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x8D => {
        regL := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x8E => {
        val work8 = MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x8F => {
        regA := MemIoImpl.peek8(address) & 0xFD
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x90 => {
        regB := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x91 => {
        regC := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x92 => {
        regD := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x93 => {
        regE := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x94 => {
        regH := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x95 => {
        regL := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x96 => {
        val work8 = MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x97 => {
        regA := MemIoImpl.peek8(address) & 0xFB
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0x98 => {
        regB := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0x99 => {
        regC := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0x9A => {
        regD := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0x9B => {
        regE := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0x9C => {
        regH := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0x9D => {
        regL := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0x9E => {
        val work8 = MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0x9F => {
        regA := MemIoImpl.peek8(address) & 0xF7
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xA0 => {
        regB := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xA1 => {
        regC := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xA2 => {
        regD := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xA3 => {
        regE := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xA4 => {
        regH := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xA5 => {
        regL := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xA6 => {
        val work8 = MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xA7 => {
        regA := MemIoImpl.peek8(address) & 0xEF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xA8 => {
        regB := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xA9 => {
        regC := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xAA => {
        regD := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xAB => {
        regE := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xAC => {
        regH := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xAD => {
        regL := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xAE => {
        val work8 = MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xAF => {
        regA := MemIoImpl.peek8(address) & 0xDF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xB0 => {
        regB := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xB1 => {
        regC := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xB2 => {
        regD := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xB3 => {
        regE := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xB4 => {
        regH := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xB5 => {
        regL := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xB6 => {
        val work8 = MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xB7 => {
        regA := MemIoImpl.peek8(address) & 0xBF
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xB8 => {
        regB := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xB9 => {
        regC := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xBA => {
        regD := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xBB => {
        regE := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xBC => {
        regH := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xBD => {
        regL := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xBE => {
        val work8 = MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xBF => {
        regA := MemIoImpl.peek8(address) & 0x7F
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xC0 => {
        regB := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xC1 => {
        regC := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xC2 => {
        regD := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xC3 => {
        regE := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xC4 => {
        regH := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xC5 => {
        regL := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xC6 => {
        val work8 = MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xC7 => {
        regA := MemIoImpl.peek8(address) | 0x01
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xC8 => {
        regB := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xC9 => {
        regC := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xCA => {
        regD := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xCB => {
        regE := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xCC => {
        regH := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xCD => {
        regL := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xCE => {
        val work8 = MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xCF => {
        regA := MemIoImpl.peek8(address) | 0x02
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xD0 => {
        regB := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xD1 => {
        regC := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xD2 => {
        regD := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xD3 => {
        regE := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xD4 => {
        regH := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xD5 => {
        regL := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xD6 => {
        val work8 = MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xD7 => {
        regA := MemIoImpl.peek8(address) | 0x04
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xD8 => {
        regB := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xD9 => {
        regC := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xDA => {
        regD := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xDB => {
        regE := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xDC => {
        regH := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xDD => {
        regL := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xDE => {
        val work8 = MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xDF => {
        regA := MemIoImpl.peek8(address) | 0x08
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xE0 => {
        regB := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xE1 => {
        regC := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xE2 => {
        regD := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xE3 => {
        regE := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xE4 => {
        regH := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xE5 => {
        regL := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xE6 => {
        val work8 = MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xE7 => {
        regA := MemIoImpl.peek8(address) | 0x10
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xE8 => {
        regB := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xE9 => {
        regC := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xEA => {
        regD := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xEB => {
        regE := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xEC => {
        regH := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xED => {
        regL := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xEE => {
        val work8 = MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xEF => {
        regA := MemIoImpl.peek8(address) | 0x20
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xF0 => {
        regB := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xF1 => {
        regC := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xF2 => {
        regD := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xF3 => {
        regE := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xF4 => {
        regH := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xF5 => {
        regL := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xF6 => {
        val work8 = MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xF7 => {
        regA := MemIoImpl.peek8(address) | 0x40
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
      case 0xF8 => {
        regB := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regB)
        //break
      }
      case 0xF9 => {
        regC := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regC)
        //break
      }
      case 0xFA => {
        regD := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regD)
        //break
      }
      case 0xFB => {
        regE := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regE)
        //break
      }
      case 0xFC => {
        regH := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regH)
        //break
      }
      case 0xFD => {
        regL := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regL)
        //break
      }
      case 0xFE => {
        val work8 = MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, work8)
        //break
      }
      case 0xFF => {
        regA := MemIoImpl.peek8(address) | 0x80
        MemIoImpl.contendedStates(address, 1)
        MemIoImpl.poke8(address, regA)
        //break
      }
    }
  }

  private def decodeED() {
    regR += 1
    opCode = MemIoImpl.fetchOpcode(regPC)
    regPC := (regPC + 1) & 0xffff
    opCode match {
      case 0x40 => {
        memptr := getRegBC
        regB := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regB)
        flagQ = true
        //break
      }
      case 0x41 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regB)
        memptr += 1
        //break
      }
      case 0x42 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        sbc16(getRegBC)
        //break
      }
      case 0x43 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, getRegBC)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x44 | 0x4C | 0x54 | 0x5C | 0x64 | 0x6C | 0x74 | 0x7C => {
        val aux = BitContainerFactory.create(regA)
        regA := 0
        sub(aux)
        //break
      }
      case 0x45 | 0x4D | 0x55 | 0x5D | 0x65 | 0x6D | 0x75 | 0x7D => {
        ffIFF1 = ffIFF2
        memptr := pop()
        regPC := memptr
        //break
      }
      case 0x46 | 0x4E | 0x66 | 0x6E => {
        setIM(IntMode.IM0)
        //break
      }
      case 0x47 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        regI := regA
        //break
      }
      case 0x48 => {
        memptr := getRegBC
        regC := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regC)
        flagQ = true
        //break
      }
      case 0x49 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regC)
        memptr += 1
        //break
      }
      case 0x4A => {
        MemIoImpl.contendedStates(getPairIR, 7)
        adc16(getRegBC)
        //break
      }
      case 0x4B => {
        memptr := MemIoImpl.peek16(regPC)
        setRegBC(MemIoImpl.peek16(memptr))
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x4F => {
        MemIoImpl.contendedStates(getPairIR, 1)
        setRegR(regA)
        //break
      }
      case 0x50 => {
        memptr := getRegBC
        regD := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regD)
        flagQ = true
        //break
      }
      case 0x51 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regD)
        memptr += 1
        //break
      }
      case 0x52 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        sbc16(getRegDE)
        //break
      }
      case 0x53 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, getRegDE)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x56 | 0x76 => {
        setIM(IntMode.IM1)
        //break
      }
      case 0x57 => {
        MemIoImpl.contendedStates(getPairIR, 1)
        regA := regI
        sz5h3pnFlags = sz53n_addTable(regA)
        if (ffIFF2) {
          sz5h3pnFlags |= PARITY_MASK
        }
        flagQ = true
        //break
      }
      case 0x58 => {
        memptr := getRegBC
        regE := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regE)
        flagQ = true
        //break
      }
      case 0x59 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regE)
        memptr += 1
        //break
      }
      case 0x5A => {
        MemIoImpl.contendedStates(getPairIR, 7)
        adc16(getRegDE)
        //break
      }
      case 0x5B => {
        memptr := MemIoImpl.peek16(regPC)
        setRegDE(MemIoImpl.peek16(memptr))
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x5E | 0x7E => {
        setIM(IntMode.IM2)
        //break
      }
      case 0x5F => {
        MemIoImpl.contendedStates(getPairIR, 1)
        regA := getRegR
        sz5h3pnFlags = sz53n_addTable(regA)
        if (ffIFF2) {
          sz5h3pnFlags |= PARITY_MASK
        }
        flagQ = true
        //break
      }
      case 0x60 => {
        memptr := getRegBC
        regH := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regH)
        flagQ = true
        //break
      }
      case 0x61 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regH)
        memptr += 1
        //break
      }
      case 0x62 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        sbc16(getRegHL)
        //break
      }
      case 0x63 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, getRegHL)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x67 => {
        rrd()
        //break
      }
      case 0x68 => {
        memptr := getRegBC
        regL := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regL)
        flagQ = true
        //break
      }
      case 0x69 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regL)
        memptr += 1
        //break
      }
      case 0x6A => {
        MemIoImpl.contendedStates(getPairIR, 7)
        adc16(getRegHL)
        //break
      }
      case 0x6B => {
        memptr := MemIoImpl.peek16(regPC)
        setRegHL(MemIoImpl.peek16(memptr))
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x6F => {
        rld()
        //break
      }
      case 0x70 => {
        memptr := getRegBC
        val inPort = MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(inPort)
        flagQ = true
        //break
      }
      case 0x71 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, 0x00)
        memptr += 1
        //break
      }
      case 0x72 => {
        MemIoImpl.contendedStates(getPairIR, 7)
        sbc16(regSP)
        //break
      }
      case 0x73 => {
        memptr := MemIoImpl.peek16(regPC)
        MemIoImpl.poke16(memptr, regSP)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0x78 => {
        memptr := getRegBC
        regA := MemIoImpl.inPort(memptr)
        memptr += 1
        sz5h3pnFlags = sz53pn_addTable(regA)
        flagQ = true
        //break
      }
      case 0x79 => {
        memptr := getRegBC
        MemIoImpl.outPort(memptr, regA)
        memptr += 1
        //break
      }
      case 0x7A => {
        MemIoImpl.contendedStates(getPairIR, 7)
        adc16(regSP)
        //break
      }
      case 0x7B => {
        memptr := MemIoImpl.peek16(regPC)
        regSP := MemIoImpl.peek16(memptr)
        memptr += 1
        regPC := (regPC + 2) & 0xffff
        //break
      }
      case 0xA0 => {
        ldi()
        //break
      }
      case 0xA1 => {
        cpi()
        //break
      }
      case 0xA2 => {
        ini()
        //break
      }
      case 0xA3 => {
        outi()
        //break
      }
      case 0xA8 => {
        ldd()
        //break
      }
      case 0xA9 => {
        cpd()
        //break
      }
      case 0xAA => {
        ind()
        //break
      }
      case 0xAB => {
        outd()
        //break
      }
      case 0xB0 => {
        ldi()
        if ((sz5h3pnFlags & PARITY_MASK) == PARITY_MASK) {
          regPC := (regPC - 2) & 0xffff
          memptr := regPC + 1
          MemIoImpl.contendedStates((getRegDE - 1) & 0xffff, 5)
        }
        //break
      }
      case 0xB1 => {
        cpi()
        if ((sz5h3pnFlags & PARITY_MASK) == PARITY_MASK && (sz5h3pnFlags & ZERO_MASK) == 0) {
          regPC := (regPC - 2) & 0xffff
          memptr := regPC + 1
          MemIoImpl.contendedStates((getRegHL - 1) & 0xffff, 5)
        }
        //break
      }
      case 0xB2 => {
        ini()
        if (regB != 0) {
          regPC := (regPC - 2) & 0xffff
          MemIoImpl.contendedStates((getRegHL - 1) & 0xffff, 5)
        }
        //break
      }
      case 0xB3 => {
        outi()
        if (regB != 0) {
          regPC := (regPC - 2) & 0xffff
          MemIoImpl.contendedStates(getRegBC, 5)
        }
        //break
      }
      case 0xB8 => {
        ldd()
        if ((sz5h3pnFlags & PARITY_MASK) == PARITY_MASK) {
          regPC := (regPC - 2) & 0xffff
          memptr := regPC + 1
          MemIoImpl.contendedStates((getRegDE + 1) & 0xffff, 5)
        }
        //break
      }
      case 0xB9 => {
        cpd()
        if ((sz5h3pnFlags & PARITY_MASK) == PARITY_MASK && (sz5h3pnFlags & ZERO_MASK) == 0) {
          regPC := (regPC - 2) & 0xffff
          memptr := regPC + 1
          MemIoImpl.contendedStates((getRegHL + 1) & 0xffff, 5)
        }
        //break
      }
      case 0xBA => {
        ind()
        if (regB != 0) {
          regPC := (regPC - 2) & 0xffff
          MemIoImpl.contendedStates((getRegHL + 1) & 0xffff, 5)
        }
        //break
      }
      case 0xBB => {
        outd()
        if (regB != 0) {
          regPC := (regPC - 2) & 0xffff
          MemIoImpl.contendedStates(getRegBC, 5)
        }
        //break
      }
      case _ => {
        //break
      }
    }
  }
}
