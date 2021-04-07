package z80core;

import com.pretosmind.emu.z80.State;
import com.pretosmind.emu.z80.registers.Composed16BitRegister;
import com.pretosmind.emu.z80.registers.Plain16BitRegister;
import com.pretosmind.emu.z80.registers.RegisterBank;

public class StateImpl extends State {

	public StateImpl(Z80 z80) {
		super(createBank(z80));
	}

	private static RegisterBank createBank(Z80 z80) {
		Composed16BitRegister af = new Composed16BitRegister("A", "F", () -> z80.getRegA(), (v) -> z80.setRegA(v), () -> z80.getFlags(), (v) -> z80.setFlags(v));
		Composed16BitRegister bc = new Composed16BitRegister("B", "C", () -> z80.getRegB(), (v) -> z80.setRegB(v), () -> z80.getRegC(), (v) -> z80.setRegC(v));
		Composed16BitRegister de = new Composed16BitRegister("D", "E", () -> z80.getRegD(), (v) -> z80.setRegD(v), () -> z80.getRegE(), (v) -> z80.setRegE(v));
		Composed16BitRegister hl = new Composed16BitRegister("H", "L", () -> z80.getRegH(), (v) -> z80.setRegH(v), () -> z80.getRegL(), (v) -> z80.setRegL(v));
		Composed16BitRegister _af = new Composed16BitRegister("A", "F", () -> z80.getRegAx(), (v) -> z80.setRegAx(v), () -> z80.getRegFx(), (v) -> z80.setRegFx(v));
		Composed16BitRegister _bc = new Composed16BitRegister("B", "C", () -> z80.getRegBx(), (v) -> z80.setRegBx(v), () -> z80.getRegCx(), (v) -> z80.setRegCx(v));
		Composed16BitRegister _de = new Composed16BitRegister("D", "E", () -> z80.getRegDx(), (v) -> z80.setRegDx(v), () -> z80.getRegEx(), (v) -> z80.setRegEx(v));
		Composed16BitRegister _hl = new Composed16BitRegister("H", "L", () -> z80.getRegHx(), (v) -> z80.setRegHx(v), () -> z80.getRegLx(), (v) -> z80.setRegLx(v));
		Plain16BitRegister pc = new Plain16BitRegister() {
			public int read() {
				return z80.getRegPC();
			};
			
			public void write(int value) {
				z80.setRegPC(value);
			};
		};
		Plain16BitRegister sp = new Plain16BitRegister() {
			public int read() {
				return z80.getRegSP();
			};
		};
		Composed16BitRegister ix = new Composed16BitRegister("IXH", "IXL", () -> z80.getRegIX() >>> 8, (v) -> z80.setRegIX(v), () -> z80.getRegIX() & 0x00ff, (v) -> z80.setRegFx(v));
		Composed16BitRegister iy = new Composed16BitRegister("IYH", "IYL", () -> z80.getRegIY() >>> 8, (v) -> z80.setRegA(v), () -> z80.getRegIY() & 0x00ff, (v) -> z80.setRegFx(v));

		return new RegisterBank(af, bc, de, hl, _af, _bc, _de, _hl, pc, sp, ix, iy);
	}
}
