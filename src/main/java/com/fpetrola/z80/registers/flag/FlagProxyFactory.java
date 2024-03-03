package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FlagProxyFactory {
  public <T extends WordNumber> Register createFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    Register o = (Register) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{FlagRegister.class, Register.class}, new AdapterInvocationHandler<T>(tableFlagRegister));
    return o;
  }

  public <T extends WordNumber> Register createDummyFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    Register o = (Register) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{FlagRegister.class, Register.class}, new DummyInvocationHandler<T>(tableFlagRegister));
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

  public class AdapterInvocationHandler<T extends WordNumber> implements InvocationHandler {
    private final TableFlagRegister tableFlagRegister;
    WordNumber registerData;

    public AdapterInvocationHandler(TableFlagRegister tableFlagRegister) {
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

      registerData = WordNumber.createValue(tableFlagRegister.read());
      if (method.getDeclaringClass().equals(FlagRegister.class)) {

        if (args != null) {
          WordNumber registerData2 = registerData;
          if (args[0] instanceof WordNumber && (args.length == 1 || args[1] instanceof WordNumber))
            registerData2 = registerData.aluOperation2((WordNumber) args[0], args.length > 1 ? (WordNumber) args[1] : null, method.getName());

          if (result instanceof Integer) {
            WordNumber value = WordNumber.createValue((Integer) result);
            WordNumber wordNumber = registerData2.aluOperation(value, method.getName());
            return wordNumber;
          }
        }
        if (result != null)
          if (result instanceof Integer)
            return WordNumber.createValue((Integer) result);
      }
      return result;
    }
  }

  public class DummyInvocationHandler<T extends WordNumber> implements InvocationHandler {
    private final TableFlagRegister tableFlagRegister;

    public DummyInvocationHandler(TableFlagRegister tableFlagRegister) {
      this.tableFlagRegister = tableFlagRegister;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object invoke = method.invoke(tableFlagRegister, adaptArgs(args));
      return invoke != null ? invoke instanceof Integer integer ? WordNumber.createValue(integer) : invoke : null;
    }
  }
}
