package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class In<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  public static final TableAluOperation inCTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      if ((a & 0x0080) == 0)
        resetS();
      else
        setS();
      if (a == 0)
        setZ();
      else
        resetZ();
      if (parity[a])
        setPV();
      else
        resetPV();
      resetN();
      resetH();
      return new AluResult(a, data);
    }
  };
  private final Register<T> a;
  private final Register<T> bc;
  private final Register<T> memptr;
  private final IO<T> io;

  public In(OpcodeReference target, ImmutableOpcodeReference source, Register<T> a, Register<T> bc, Register<T> flag, Register<T> memptr, IO<T> io) {
    super(target, source, flag);
    this.a = a;
    this.bc = bc;
    this.memptr = memptr;
    this.io = io;
  }

  public int execute() {
    T port = source.read();

    boolean equalsN = !(source instanceof Register);
    if (equalsN) {
      port = port.or(a.read().left(8));
      memptr.write(port.plus1());
    } else {
      port = bc.read();
    }

    T value = io.in(port);

    target.write(value);

    if (!equalsN) {
      inCTableAluOperation.executeWithCarry(value, flag);
    }

    return cyclesCost;
  }
}
