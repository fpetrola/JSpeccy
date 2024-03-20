package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagProxyFactory;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegister;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.flag.FlagProxyFactory.adaptArgs;

public class VirtualFlagRegister<T extends WordNumber> extends Virtual8BitsRegister<T> implements FlagRegisterDelegate<T> {
  FlagRegister<T> delegate;

  public VirtualFlagRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, VirtualRegister<T> previousVersion, VirtualFetcher<T> virtualFetcher) {
    super(instructionExecutor, name, instruction, previousVersion, virtualFetcher);

    TableFlagRegister tableFlagRegister = new TableFlagRegister("virtualFlag");
    delegate = FlagProxyFactory.createProxy((proxy, method, args) -> {
      if (method.getName().equals("write")) {
        method.invoke(tableFlagRegister, adaptArgs(args));
        return null;
      } else {
        read();
        Object invoke = method.invoke(tableFlagRegister, adaptArgs(args));
        write(createValue(tableFlagRegister.read()));
        return invoke != null ? invoke instanceof Integer integer ? createValue(integer) : invoke : null;
      }
    });
  }

  public FlagRegister<T> getDelegate() {
    return delegate;
  }

  public void write(T value) {
    super.write(value);
    delegate.write(value);
  }
}
