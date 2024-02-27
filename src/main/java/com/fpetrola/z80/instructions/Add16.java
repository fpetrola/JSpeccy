package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import org.cojen.maker.Variable;

public class Add16<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  private final IFlagRegister<T> flag;
  final InstructionFactory instructionFactory;

  Add16(OpcodeReference target, ImmutableOpcodeReference source, IFlagRegister<T> flag, InstructionFactory instructionFactory) {
    super(target, source, flag::ALU16BitAdd);
    this.flag = flag;
    this.instructionFactory = instructionFactory;
  }

  public int execute() {
    RegisterPair source1 = (RegisterPair) source;
    RegisterPair target1 = (RegisterPair) target;

    final T value1 = source.read();
    final T value2 = target.read();
    aluOperation.execute(value1, value2);
    T computedFlag = flag.read();

    instructionFactory.Add(target1.getLow(), source1.getLow()).execute();
    instructionFactory.Adc(target1.getHigh(), source1.getHigh()).execute();

    flag.write(computedFlag);

    return cyclesCost;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(sourceVariable);
  }
}
