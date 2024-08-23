package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Push;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.ReturnAddressWordNumber;
import com.fpetrola.z80.jspeccy.RegistersBase;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.NullInstructionSpy;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;
import gui.ZXScreen;
import snapshots.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

@SuppressWarnings("ALL")
public class MiniZX extends JetSetWilly {
  private State<WordNumber> state;

  public static void main(String[] args) {
    new MiniZX().main();
  }

  public void main() {
    MyMockedIO io = new MyMockedIO();
    OOZ80<WordNumber> ooz80 = createOOZ80(io);

    String fileName = "/home/fernando/detodo/desarrollo/m/zx/zx/jsw2.z80";
    state = ooz80.getState();

    loadSnapshot(fileName, state);
    JFrame screenComponent = createScreenComponent(state);
    screenComponent.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        io.setCurrentKey(e.getKeyCode(), true);
      }

      public void keyReleased(KeyEvent e) {
        io.setCurrentKey(e.getKeyCode(), false);
      }
    });

    int i = 0;
    while (true) {
      if (i++ % 1000000000 == 0) state.setINTLine(true);
      else if (i % 300 == 0) ooz80.execute();
    }
  }

  private static JFrame createScreenComponent(State<WordNumber> state) {
    JFrame frame = new JFrame("Mini ZX Spectrum");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new ZXScreen(state.getMemory().getData()));
    frame.pack();
    frame.setVisible(true);
    return frame;
  }

  private static void loadSnapshot(String fileName, final State<WordNumber> state) {
    try {
      File file = new File(fileName);
      SnapshotFile snap = SnapshotFactory.getSnapshot(file);
      SpectrumState snapState = snap.load(file);

      RegistersBase registersBase = new RegistersBase<>(state) {
        public VirtualRegisterFactory getVirtualRegisterFactory() {
          return new VirtualRegisterFactory(null, null);
        }
      };

      registersBase.setZ80State(snapState.getZ80State());

      MemoryState memoryState = snapState.getMemoryState();

      byte[][] ram = memoryState.getRam();

      int position = 16384;
      position = copyPage(ram, 5, position, state);
      position = copyPage(ram, 2, position, state);
      copyPage(ram, 0, position, state);
    } catch (SnapshotException e) {
      throw new RuntimeException(e);
    }
  }

  private static int copyPage(byte[][] ram, int page, int position, State state) {
    Memory memory = state.getMemory();
    for (int i = 0; i < ram[page].length; i++) {
      memory.write(createValue(position++), createValue(ram[page][i]));
    }
    return position;
  }

  private void copyState(State state) {
    Arrays.stream(RegisterName.values()).forEach(n -> {
      try {
        Field field = getClass().getField(n.name());
        Register register = state.getRegister(n);
        WordNumber read = (WordNumber) register.read();
        if (read != null)
          field.set(this, read.intValue());

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    copyMemoryState(state);
  }

  private void copyMemoryState(State state) {
    Object[] data = state.getMemory().getData();
    for (int i = 16384; i < 0xFFFF; i++) {
      Object datum = data[i];
      if (datum == null)
        datum = createValue(0);

      mem[i] = ((WordNumber) datum).intValue() & 0xFF;
    }
  }

  private void copyStateBack(State state) {
    update16Registers();
    Arrays.stream(RegisterName.values()).forEach(n -> {
      try {
        Field field = getClass().getField(n.name());
        Register register = state.getRegister(n);
        Integer o = (Integer) field.get(this);
        register.write(createValue(o));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    copyMemoryStateBack(state);
  }

  private boolean stateIsMatching(State<WordNumber> state) {
    update16Registers();

    List<RegisterName> list = new ArrayList<>(Arrays.asList(RegisterName.values()));
    list.removeAll(Arrays.asList(RegisterName.PC, RegisterName.F, RegisterName.AF, RegisterName.SP, RegisterName.IR, RegisterName.I, RegisterName.R));
    boolean anyDifferentRegister = list.stream().map(n -> {
      try {
        Field field = getClass().getField(n.name());
        Register<WordNumber> register = state.getRegister(n);
        int o = (Integer) field.get(this);
        if (register.read() != null && o != register.read().intValue()) {
          System.out.println("difference at register: " + register.getName());
        }
        register.write(createValue(o));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      return true;
    }).anyMatch(b -> !b);

    WordNumber[] data = state.getMemory().getData();
    for (int i = 16384; i < 0xFFFF; i++) {
      if ((data[i].intValue() & 0xFF) != mem[i]) {
        System.out.println("difference at: " + i);
      }
    }
//    if (!anyDifferentRegister) {
//      System.out.println("iguales!");
//    }
    return !anyDifferentRegister;
  }

  private void update16Registers() {
    BC(BC());
    DE(DE());
    HL(HL());
    AF(AF());
    IX(IX());
    IY(IY());
  }

  private void copyMemoryStateBack(State state) {
    Object[] data = state.getMemory().getData();
    for (int i = 16384; i < 0xFFFF; i++) {
      data[i] = createValue(mem[i] & 0xFF);
    }
  }

  //  @Override
//  public int mem(int address) {
//    Object[] data = state.getMemory().getData();
//    Object datum = data[address & 0xFFFF];
//    if (datum == null)
//      datum = createValue(0);
//
//    return ((WordNumber) datum).intValue();
//  }
//
//  @Override
//  public void mem(int address, int value) {
//    Object[] data = state.getMemory().getData();
//    data[address & 0xFFFF] = createValue((int) value);
//  }

  public <T extends WordNumber> OOZ80<T> createOOZ80(IO io) {
    var state = new State(io, new MockedMemory());
    InstructionFactory<T> instructionFactory = new InstructionFactory<T>(state) {
      private T nextRetAddress = createValue(0);

      @Override
      public Ret Ret(Condition condition) {
        return new Ret<T>(condition, sp, memory, pc) {
          @Override
          public int execute() {
            T jumpAddress2 = calculateJumpAddress();
            if (condition.conditionMet(this)) {
              final T value = Memory.read16Bits(memory, sp.read());

              if (nextRetAddress.intValue() == value.intValue()) {
                nextRetAddress = createValue(0);
                boolean stateIsMatching = stateIsMatching(state);
                if (!stateIsMatching)
                  System.out.println("not matching");
              }
              jumpAddress2 = beforeJump(jumpAddress2);
              setJumpAddress(jumpAddress2);
              setNextPC(jumpAddress2);
            } else
              setNextPC(null);

            return cyclesCost;
          }
        };
      }

      @Override
      public Call Call(Condition condition, ImmutableOpcodeReference positionOpcodeReference) {
        return new Call<T>(positionOpcodeReference, condition, pc, sp, state.getMemory()) {
          @Override
          public int execute() {
            Map<Integer, Runnable> convertedRoutines = getConvertedRoutines();

            T jumpAddress2 = calculateJumpAddress();
            if (condition.conditionMet(this)) {
              Runnable runnable = convertedRoutines.get(jumpAddress.intValue());
              if (runnable != null) {
                T retAddress = pc.read().plus(length);
                retAddress = (T) new ReturnAddressWordNumber(retAddress.intValue());
                if (!replacing)
                  Push.doPush(retAddress, sp, memory);
                try {
                  copyState(state);
                  runnable.run();
                  if (replacing) {
                    copyStateBack(state);
                    setNextPC(null);
                  }
                } catch (Exception e) {
                  System.out.println("pum!");
                }
                if (!replacing) {
                  setJumpAddress(jumpAddress2);
                  setNextPC(jumpAddress2);
                  nextRetAddress = retAddress;
                }
              } else {
                jumpAddress2 = beforeJump(jumpAddress2);
                setJumpAddress(jumpAddress2);
                setNextPC(jumpAddress2);
              }
            } else
              setNextPC(null);

            return 0;
          }
        };
      }
    };
    var z80 = new OOZ80(state, DefaultInstructionFetcher.getInstructionFetcher(state, new NullInstructionSpy(), instructionFactory));
    return z80;
  }

}
