package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.IntegerRegisterPair;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FlagProxyFactory {
  public static <T extends WordNumber> Register createFlagRegisterProxy(TableFlagRegister tableFlagRegister) {
    Register o = (Register) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IFlagRegister.class, Register.class}, new InvocationHandler() {
      WordNumber registerData = WordNumber.createValue(tableFlagRegister.read());

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object[] objects = null;
        if (args != null) {
          objects = new Object[args.length];
          for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof WordNumber)
              objects[i] = ((WordNumber) args[i]).intValue();
            else
              objects[i] = args[i];
          }
        }
        Object result;
        if (method.getName().equals("read")) {
          return registerData;
        } else if (method.getName().equals("EXAFAF")) {
          result = EXAFAF(method, (RegisterPair<T>) args[0], (RegisterPair<T>) args[1]);
        } else
          result = method.invoke(tableFlagRegister, objects);

        registerData.set(tableFlagRegister.read());

        if (args != null)
          if (result instanceof Integer) {
            TraceableWordNumber value = WordNumber.createValue((Integer) result);
            for (int i = args.length - 1; i >= 0; i--) {
              if (args[i] instanceof TraceableWordNumber) {
                value.merge((TraceableWordNumber) args[i], value);
              }
            }
            return value;
          }

        if (result != null)
          if (result instanceof Integer)
            return WordNumber.createValue((Integer) result);

        return result;
      }

      public T EXAFAF(Method method, RegisterPair<T> AF1, RegisterPair<T> AF2) throws InvocationTargetException, IllegalAccessException {
        RegisterPair<Integer> p1 = new IntegerRegisterPair(AF1);
        RegisterPair<Integer> p2 = new IntegerRegisterPair(AF2);
        T value = WordNumber.createValue((Integer) method.invoke(tableFlagRegister, p1, p2));
        return value;
      }
    });
    return o;
  }
}
