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
  public <T extends WordNumber> Register createFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    Register o = (Register) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IFlagRegister.class, Register.class}, new MyInvocationHandler<T>(tableFlagRegister));
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

  public class MyInvocationHandler<T extends WordNumber> implements InvocationHandler {
    private final TableFlagRegister tableFlagRegister;
    TraceableWordNumber registerData;

    public MyInvocationHandler(TableFlagRegister tableFlagRegister) {
      this.tableFlagRegister = tableFlagRegister;
      registerData = WordNumber.createValue(tableFlagRegister.read());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object result;
      if (method.getName().equals("toString")) {
        return tableFlagRegister.data;
      } else if (method.getName().equals("hashCode")) {
        return tableFlagRegister.data.hashCode();
      } else if (method.getName().equals("equals")) {
        return tableFlagRegister.data.equals(args[0]);
      } else if (method.getName().equals("read")) {
        return registerData;
      } else
        result = method.invoke(tableFlagRegister, adaptArgs(args));

      if (method.getDeclaringClass().equals(IFlagRegister.class)) {
        registerData = WordNumber.createValue(tableFlagRegister.read());
        if (args != null) {
          TraceableWordNumber value = concatArgsOperations(args);
          if (value != null)
            registerData = value.aluOperation(registerData);

          if (result instanceof Integer)
            return merge2((Integer) result, value);
        }
        if (result != null)
          if (result instanceof Integer)
            return WordNumber.createValue((Integer) result);
      } else
        System.out.println("dddd");
      return result;
    }

    private static TraceableWordNumber merge2(Integer result, TraceableWordNumber value) {
      return value == null ? WordNumber.createValue(result) : value.aluOperation(WordNumber.createValue(result));
    }

    private static TraceableWordNumber concatArgsOperations(Object[] args) {
      TraceableWordNumber value = null;
      for (int i = args.length - 1; i >= 0; i--) {
        if (args[i] instanceof TraceableWordNumber arg) {
          value = value == null ? arg : value.aluOperation(arg);
        }
      }
      return value;
    }
  }
}
