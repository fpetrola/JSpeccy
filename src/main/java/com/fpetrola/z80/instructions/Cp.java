package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class Cp<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  private final FlagRegister<T> flag;

  Cp(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source);
    this.flag = flag;
  }

  public int execute() {
    final T value1 = target.read();
    final T value2 = source.read();

    flag.ALU8BitCp(value2, value1);

    return cyclesCost;
  }

  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, source, false);
    Variable a = byteCodeGenerator.registers.get(RegisterName.A.name());
    Variable targetVariable = byteCodeGenerator.registers.get(RegisterName.F.name());

    targetVariable.set(a.sub(sourceVariable));
    return 0;
  }
}
