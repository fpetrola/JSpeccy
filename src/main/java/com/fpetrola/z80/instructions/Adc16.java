package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Adc16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  private final FlagRegister<T> flag;
  final InstructionFactory instructionFactory;

  Adc16(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag, InstructionFactory instructionFactory) {
    super(target, source, flag::ALU16BitADC);
    this.flag = flag;
    this.instructionFactory = instructionFactory;
  }

  public int execute() {
    T lastFlag = flag.read();

    RegisterPair source1 = (RegisterPair) source;
    RegisterPair target1 = (RegisterPair) target;

    final T value1 = source.read();
    final T value2 = target.read();
    binaryAluOperation.execute(value1, value2);
    T computedFlag = flag.read();

    flag.write(lastFlag);

    instructionFactory.Adc(target1.getLow(), source1.getLow()).execute();
    instructionFactory.Adc(target1.getHigh(), source1.getHigh()).execute();

    flag.write(computedFlag);

    return cyclesCost;
  }
}
