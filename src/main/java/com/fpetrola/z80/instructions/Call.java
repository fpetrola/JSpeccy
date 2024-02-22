package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.MethodMaker;

public class Call<T extends WordNumber> extends ConditionalInstruction<T> {
  private final Register<T> sp;
  private final Memory<T> memory;

  Call(ImmutableOpcodeReference positionOpcodeReference, Condition condition, Register<T> pc, Register<T> sp, Memory<T> memory) {
    super(positionOpcodeReference, condition, pc);
    this.sp = sp;
    this.memory = memory;
  }

  public T beforeJump(T jumpAddress) {
    Push.doPush(pc.read().plus(length), sp, memory);
    return jumpAddress;
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    int jumpLabel = jumpAddress.intValue();

    String labelName = createLabelName(jumpLabel);
    MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
    if (method != null) {
      Runnable runnable = () -> mm.invoke(labelName);
      executeConditional(byteCodeGenerator, runnable, condition);
    }
    return 0;
  }
}
