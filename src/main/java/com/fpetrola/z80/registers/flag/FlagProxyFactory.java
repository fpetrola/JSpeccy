package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.IntegerRegisterPair;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FlagProxyFactory {
  public static <T extends WordNumber> Register createFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    Register o = (Register) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IFlagRegister.class, Register.class}, new InvocationHandler() {
      WordNumber registerData = WordNumber.createValue(tableFlagRegister.read());

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        if (method.getName().equals("read")) {
          return registerData;
        } else if (method.getName().equals("EXAFAF")) {
          result = WordNumber.<T>createValue((Integer) method.invoke(tableFlagRegister, new IntegerRegisterPair((RegisterPair<T>) args[0]), new IntegerRegisterPair((RegisterPair<T>) args[1])));
        } else
          result = method.invoke(tableFlagRegister, adaptArgs(args));

        registerData = WordNumber.createValue(tableFlagRegister.read());

        if (args != null)
          if (result instanceof Integer)
            return mergeResultWithArgs(args, (Integer) result);

        if (result != null)
          if (result instanceof Integer)
            return WordNumber.createValue((Integer) result);

        return result;
      }

      private static TraceableWordNumber mergeResultWithArgs(Object[] args, Integer result) {
        TraceableWordNumber value = null;
        for (int i = args.length - 1; i >= 0; i--) {
          if (args[i] instanceof TraceableWordNumber arg) {
            value = value == null ? arg : value.nullOperation(arg);
          }
        }
        return value == null ? WordNumber.createValue(result) : value.nullOperation(WordNumber.createValue(result));
      }
    });
    return o;
  }

  private static Object[] adaptArgs(Object[] args) {
    Object[] objects = null;
    if (args != null) {
      objects = new Object[args.length];
      for (int i = 0; i < args.length; i++)
        objects[i] = args[i] instanceof WordNumber ? Integer.valueOf(((WordNumber) args[i]).intValue()) : args[i];
    }
    return objects;
  }
}
