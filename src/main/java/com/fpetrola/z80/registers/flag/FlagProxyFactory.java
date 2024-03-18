package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FlagProxyFactory {
  public <T extends WordNumber> FlagRegister createFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    return createDummyFlagRegisterProxy(tableFlagRegister);
    //return (FlagRegister) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{FlagRegister.class, Register.class}, new AdapterInvocationHandler<T>(tableFlagRegister));
  }

  public <T extends WordNumber, S extends FlagRegister> S createDummyFlagRegisterProxy(FlagRegister flagRegister) {
    return createProxy(new DummyInvocationHandler<>(flagRegister));
  }

  public static <T extends WordNumber, S extends FlagRegister> S createProxy(InvocationHandler h) {
    FlagRegister o = (FlagRegister) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{FlagRegister.class, Register.class, Delegate.class}, h);
    return (S) o;
  }

  public static Object[] adaptArgs(Object[] args) {
    Object[] objects = null;
    if (args != null) {
      objects = new Object[args.length];
      for (int i = 0; i < args.length; i++) {
        Object arg = args[i];
        if (arg == null)
          objects[i] = 0;
        else
          objects[i] = arg instanceof WordNumber ? Integer.valueOf(((WordNumber) arg).intValue()) : arg;
      }
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
      if (method.getName().equals("getName")) {
        return RegisterName.F.name();
      } else if (method.getName().equals("toString")) {
        return tableFlagRegister.data + "";
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
    private final FlagRegister flagRegister;
    private boolean enabled = true;

    public DummyInvocationHandler(FlagRegister flagRegister) {
      this.flagRegister = flagRegister;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equals("enable"))
        enabled = true;
      else if (method.getName().equals("disable"))
        enabled = false;
      else {
        if (enabled) {
          if (method.getName().equals("clone"))
            return proxy;
          else if (method.getName().equals("getLength"))
            return 0;
          else if (method.getName().equals("hashCode"))
            return flagRegister.hashCode();
          else {
            Object invoke = method.invoke(flagRegister, adaptArgs(args));
            return invoke != null ? invoke instanceof Integer integer ? WordNumber.createValue(integer) : invoke : null;
          }
        }
      }

      return WordNumber.createValue(0);
    }
  }
}
