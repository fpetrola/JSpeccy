package jmce;

import com.fpetrola.z80.cpu.DebugEnabledOOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.decoder.FetchNextOpcodeInstruction;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.references.WordNumber;
import jmce.sim.LoadInfo;
import jmce.sim.Memory;
import jmce.sim.SIMException;
import jmce.sim.cpu.AbstractCPU;
import jmce.sim.cpu.AbstractOpcode;
import jmce.sim.cpu.MultiOpcode;

import java.util.List;

public class CPUImplementation<T extends WordNumber> extends AbstractCPU {
  private DebugEnabledOOZ80<T> z80;
  private MemoryImpl memory;

  public CPUImplementation(DebugEnabledOOZ80 z80) {
    super("OOZ80");
    this.z80 = z80;
    memory = new MemoryImpl(z80);
    setMemory(memory);
    addHardware(memory);

    OpCodeDecoder opCodeHandler2 = z80.getOpCodeHandler();
    Instruction[] opcodeLookupTable = opCodeHandler2.getOpcodeLookupTable();
    addOpcodes(opcodeLookupTable, opcodes);

    List<com.fpetrola.z80.registers.Register> all = z80.registerBank.getAll();

    all.forEach(r -> addRegister(new RegisterImpl(r)));
  }

  public String getName() {
    return "CPU1";
  }

  public void stop() {
    z80.stop();
  }

  public int step() throws SIMException {
    z80.step();
    return 0;
  }

  public void start() {
    z80.continueExecution();
  }

  public void setTill(int address) {
    z80.till(address);
  }

  public int pc() throws SIMException {
    return z80.getState().getPc().read().intValue();
  }

  public String decodeAt(int pc) throws SIMException {
    return z80.decodeAt(pc);
  }

  public void pc(int value) throws SIMException {
  }

  public void setWord(int a, int v) throws SIMException {
  }

  public int getWord(int a) throws SIMException {
    return 0;
  }

  @Override
  public void load(Memory m, String name, int base, LoadInfo info) throws SIMException {
  }

  private void addOpcodes(Instruction<T>[] opcodeLookupTable, MultiOpcode opcodes) {
    for (int i = 0; i < opcodeLookupTable.length; i++) {

      if (i == 0xFD)
        System.out.println("dasgdag");

      Instruction<T> o = opcodeLookupTable[i];
      if (o != null)
        if (o instanceof FetchNextOpcodeInstruction) {
          FetchNextOpcodeInstruction flipOpcodeImpl = (FetchNextOpcodeInstruction) o;
          boolean isInverted = flipOpcodeImpl.getIncPc() == 2;
          MultiOpcode multiOpcode = (MultiOpcode) opcodes.getOpcode(i);
          if (multiOpcode == null) {
            if (isInverted)
              multiOpcode = new InvertedMultiOpcode(i);
            else
              multiOpcode = new MultiOpcode(i);
            opcodes.setOpcode(multiOpcode);
          }
          addOpcodes(flipOpcodeImpl.getTable(), multiOpcode);
        } else
          opcodes.setOpcode(new AbstractOpcode(i, o.getLength(), 1, o.toString()) {
            public int exec(int pc) throws SIMException {
//              o.getPC().write(pc);
              return o.execute();
            }
          });
    }
    ;
  }

  public boolean isInterruptEnabled() {
    // TODO Auto-generated method stub
    return false;
  }
}