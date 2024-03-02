package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Sbc16<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  private final IFlagRegister<T> flag;
  final InstructionFactory instructionFactory;
  private Sbc sbc;
  private Sbc sbc1;

  Sbc16(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, IFlagRegister<T> flag, InstructionFactory instructionFactory) {
    super(target, source, flag::ALU16BitSBC);
    this.flag = flag;
    this.instructionFactory = instructionFactory;
    if (source instanceof RegisterPair<T> && target instanceof RegisterPair<T>) {
      RegisterPair source1 = (RegisterPair) source;
      RegisterPair target1 = (RegisterPair) target;
      sbc = instructionFactory.Sbc(target1.getLow(), source1.getLow());
      sbc1 = instructionFactory.Sbc(target1.getHigh(), source1.getHigh());
    }
  }


  public int execute() {
    T lastFlag = flag.read();
    final T value1 = source.read();
    final T value2 = target.read();
    T execute = aluOperation.execute(value1, value2);

    if (source instanceof RegisterPair<T> && target instanceof RegisterPair<T>) {
      T computedFlag = flag.read();
      flag.write(lastFlag);
      sbc.execute();
      sbc1.execute();
      flag.write(computedFlag);
    } else
      target.write(execute);

    return cyclesCost;
  }
}
