package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.Call;
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
import com.fpetrola.z80.transformations.SpectrumApplication;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;
import gui.ZXScreen;
import snapshots.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

@SuppressWarnings("ALL")
public class MiniZX extends SpectrumApplication {

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
      if (i++ % 10 == 0) state.setINTLine(true);
      else if (i % 600 == 0) ooz80.execute();
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

  public <T extends WordNumber> OOZ80<T> createOOZ80(IO io) {
    var state = new State(io, new MockedMemory());
    InstructionFactory<T> instructionFactory = new InstructionFactory<T>(state) {
      @Override
      public Call Call(Condition condition, ImmutableOpcodeReference positionOpcodeReference) {
        return new Call<T>(positionOpcodeReference, condition, pc, sp, state.getMemory()) {

          @Override
          public int execute() {
            Map<Integer, Runnable> convertedRoutines = new HashMap<>();
//            convertedRoutines.put(37974, () -> $37974());
//            convertedRoutines.put(38545, () -> $38545());
//            convertedRoutines.put(38528, () -> $38528());
//            convertedRoutines.put(38430, () -> $38430());
//            convertedRoutines.put(38276, () -> $38276());

            calculateJumpAddress();

            Runnable runnable = convertedRoutines.get(jumpAddress.intValue());
            if (runnable != null) {
              try {
                copyState(state);
                runnable.run();
                copyStateBack(state);
                setNextPC(null);
              } catch (Exception e) {
                System.out.println("pum!");
              }
              return 0;
            } else
              return super.execute();
          }
        };
      }
    };
    var z80 = new OOZ80(state, DefaultInstructionFetcher.getInstructionFetcher(state, new NullInstructionSpy(), instructionFactory));
    return z80;
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

      mem[i] = ((WordNumber) datum).intValue();
    }
  }

  private void copyStateBack(State state) {
    BC(BC());
    DE(DE());
    HL(HL());
    AF(AF());
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

  private void copyMemoryStateBack(State state) {
    Object[] data = state.getMemory().getData();
    for (int i = 16384; i < 0xFFFF; i++) {
      data[i] = createValue(mem[i]);
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

  public void $37974() {
    B = 16;

    do {
      F = C & 1;
      A = mem(DE());
      if (F != 0) {
        A = A & mem(HL());
        F = A;
        if (F != 0) {
          return;
        }

        A = mem(DE());
        A = A | mem(HL());
        F = A;
      }

      wMem(HL(), A);
      L = L + 1;
      DE(DE() + 1);
      F = C & 1;
      A = mem(DE());
      if (F != 0) {
        A = A & mem(HL());
        F = A;
        if (F != 0) {
          return;
        }

        A = mem(DE());
        A = A | mem(HL());
        F = A;
      }

      wMem(HL(), A);
      L = L + -1;
      F = L;
      H = H + 1;
      DE(DE() + 1);
      A = H;
      A = A & 7;
      F = A;
      if (F == 0) {
        A = H;
        A = A - 8;
        F = A;
        H = A;
        A = L;
        A = A + 32;
        F = A;
        L = A;
        A = A & 224;
        F = A;
        if (F == 0) {
          A = H;
          A = A + 8;
          F = A;
          H = A;
        }
      }

      B = B + -1;
    } while (B != 0);

    A = A ^ A;
    F = A;
    F |= 0x40;
  }


  public void $38276() {
    int var1 = mem(33824);
    A = var1;
    int var2 = A - 33;
    F = var2;
    if (F == 0) {
      int var3 = mem(34259);
      A = var3;
      int var4 = A - 188;
      F = var4;
      if (F == 0) {
        int var5 = A ^ A;
        A = var5;
        F = A;
        wMem(34251, A);
        A = 3;
        wMem(34271, A);
      }
    }
  }


  public void $38528() {
    do {
      int var1 = IX();
      int var2 = mem(var1);
      A = var2;
      $38545();
      int var3 = IX() + 1;
      IX(var3);
      int var4 = E + 1;
      E = var4;
      A = D;
      int var5 = A - 8;
      A = var5;
      F = A;
      D = A;
      int var6 = C + -1;
      C = var6;
      F = C;
    } while (F != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    int var1 = L | 128;
    L = var1;
    int var2 = HL() * 2;
    HL(var2);
    int var3 = HL() * 2;
    HL(var3);
    int var4 = HL() * 2;
    HL(var4);
    B = 8;

    do {
      int var5 = HL();
      int var6 = mem(var5);
      A = var6;
      int var7 = DE();
      wMem(var7, A);
      int var8 = HL() + 1;
      HL(var8);
      int var9 = D + 1;
      D = var9;
      int var10 = B + -1;
      B = var10;
    } while (B != 0);
  }

  public void $38430() {
    int var1 = mem(32928);
    A = var1;
    int var2 = HL();
    int var3 = mem(var2);
    int var4 = A - var3;
    F = var4;
    int var5 = HL();
    mem(var5);
    if (F == 0) {
      A = C;
      int var11 = A & 15;
      A = var11;
      F = A;
      if (F != 0) {
        int var12 = mem(32928);
        A = var12;
        int var13 = A | 7;
        A = var13;
        F = A;
        int var14 = HL();
        wMem(var14, A);
      }
    }

    int var6 = mem(32955);
    A = var6;
    int var7 = HL();
    int var8 = mem(var7);
    int var9 = A - var8;
    F = var9;
    int var10 = HL();
    mem(var10);
  }
}
