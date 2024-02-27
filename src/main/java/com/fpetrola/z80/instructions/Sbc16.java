package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Sbc16<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  private final IFlagRegister<T> flag;
  final InstructionFactory instructionFactory;

  Sbc16(OpcodeReference target, ImmutableOpcodeReference source, IFlagRegister<T> flag, InstructionFactory instructionFactory) {
    super(target, source, flag::ALU16BitSBC);
    this.flag = flag;
    this.instructionFactory = instructionFactory;
  }


  public int execute() {
    if (source instanceof RegisterPair<T> && target instanceof RegisterPair<T>) {
      T lastFlag = flag.read();

      RegisterPair source1 = (RegisterPair) source;
      RegisterPair target1 = (RegisterPair) target;

      final T value1 = source.read();
      final T value2 = target.read();
      aluOperation.execute(value1, value2);
      T computedFlag = flag.read();

      flag.write(lastFlag);

      instructionFactory.Sbc(target1.getLow(), source1.getLow()).execute();
      instructionFactory.Sbc(target1.getHigh(), source1.getHigh()).execute();
      flag.write(computedFlag);
    } else {
      final T value1 = source.read();
      final T value2 = target.read();
      target.write(aluOperation.execute(value1, value2));
    }
    return cyclesCost;
  }
}
