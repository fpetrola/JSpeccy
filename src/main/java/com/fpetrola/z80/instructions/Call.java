package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;

public class Call<T extends WordNumber> extends ConditionalInstruction<T> {

  public Call(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    spy.pause();

    T position = target.read();
    setJumpAddress(position);

    if (condition.conditionMet())
      Push.doPush(pc.read().plus(length), sp, memory);
    else
      position = null;

    setNextPC(position);

    spy.doContinue();

    return cyclesCost;
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
