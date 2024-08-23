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
import com.fpetrola.z80.transformations.SpectrumApplication;
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
            Map<Integer, Runnable> convertedRoutines = new HashMap<>();
            convertedRoutines.put(37974, () -> $37974());
            convertedRoutines.put(38545, () -> $38545());
            convertedRoutines.put(38528, () -> $38528());
            convertedRoutines.put(38430, () -> $38430());
            convertedRoutines.put(38276, () -> $38276());
            convertedRoutines.put(38137, () -> $38137());
            convertedRoutines.put(35563, () -> $35563());
            convertedRoutines.put(36203, () -> $36203());
            convertedRoutines.put(36288, () -> $36288());
            convertedRoutines.put(37056, () -> $37056());
            convertedRoutines.put(37310, () -> $37310());
            convertedRoutines.put(36508, () -> $36508());
//            convertedRoutines.put(37841, () -> $37841());
            convertedRoutines.put(38555, () -> $38555());

            T jumpAddress2 = calculateJumpAddress();
            if (condition.conditionMet(this)) {
              Runnable runnable = convertedRoutines.get(jumpAddress.intValue());
              if (runnable != null) {
                T retAddress = pc.read().plus(length);
                retAddress = (T) new ReturnAddressWordNumber(retAddress.intValue());
                boolean replacing = true;
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

  public void $38545() {
    H = 7;
    L = A;
    int var1 = L | 128;
    L = var1;
    int var2 = HL() * 2 & '\uffff';
    HL(var2);
    int var3 = HL() * 2 & '\uffff';
    HL(var3);
    int var4 = HL() * 2 & '\uffff';
    HL(var4);
    B = 8;

    $38555();
  }

  private void $38555() {
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
    } while(B != 0);
  }

  public void $37841() {
    H = 164;
    int var1 = mem(41983);
    A = var1;
    L = A;

    do {
      int var2 = HL();
      int var3 = mem(var2);
      C = var3;
      int var4 = C & -129;
      C = var4;
      int var5 = mem(33824);
      A = var5;
      int var6 = A | 64;
      A = var6;
      F = A;
      int var7 = A - C;
      F = var7;
      if (F == 0) {
        int var9 = HL();
        int var10 = mem(var9);
        A = var10;
        int var11 = A;
        int var12 = rlc(var11);
        A = var12;
        int var13 = A & 1;
        A = var13;
        F = A;
        int var14 = A + 92 & 255;
        A = var14;
        F = A;
        D = A;
        int var15 = H + 1;
        H = var15;
        int var16 = HL();
        int var17 = mem(var16);
        E = var17;
        int var18 = H + -1;
        H = var18;
        F = H;
        int var19 = DE();
        int var20 = mem(var19);
        A = var20;
        int var21 = A & 7;
        A = var21;
        F = A;
        int var22 = A - 7;
        F = var22;
        if (F != 0) {
          int var23 = mem(34251);
          A = var23;
          int var24 = A + L & 255;
          A = var24;
          F = A;
          int var25 = A & 3;
          A = var25;
          F = A;
          int var26 = A + 3 & 255;
          A = var26;
          F = A;
          C = A;
          int var27 = DE();
          int var28 = mem(var27);
          A = var28;
          int var29 = A & 248;
          A = var29;
          F = A;
          int var30 = A | C;
          A = var30;
          F = A;
          int var31 = DE();
          wMem(var31, A);
          int var32 = HL();
          int var33 = mem(var32);
          A = var33;
          int var34 = A;
          int var35 = rlc(var34);
          A = var35;
          int var36 = A;
          int var37 = rlc(var36);
          A = var37;
          int var38 = A;
          int var39 = rlc(var38);
          A = var39;
          int var40 = A;
          int var41 = rlc(var40);
          A = var41;
          int var42 = A & 8;
          A = var42;
          F = A;
          int var43 = A + 96 & 255;
          A = var43;
          F = A;
          D = A;
          HL(32993);
          B = 8;
          $38555();
        } else {
          IX(34172);

          while(true) {
            int var44 = IX() + 2;
            int var45 = mem(var44);
            A = var45;
            int var46 = A - 58;
            F = var46;
            if (F != 0) {
              int var47 = mem(32990);
              A = var47;
              C = 128;

              do {
                int var48 = A ^ 24;
                A = var48;
                F = A;
                E = A;
                A = 144;
                int var49 = A - C;
                A = var49;
                F = A;
                B = A;
                A = E;

                do {
                  int var50 = B + -1;
                  B = var50;
                } while(B != 0);

                int var51 = C + -1;
                C = var51;
                F = C;
                int var52 = C + -1;
                C = var52;
                F = C;
              } while(F != 0);

              int var53 = mem(34270);
              A = var53;
              int var54 = A + 1;
              A = var54;
              wMem(34270, A);
              if (F == 0) {
                A = 1;
                wMem(34271, A);
              }

              int var55 = HL();
              int var56 = mem[var55];
              int var57 = var56 & -65;
              int var58 = HL();
              wMem(var58, var57);
              int var59 = HL();
              wMem(var59, var56);
              break;
            }

            int var60 = IX() + 2;
            wMem(var60, 48);
          }
        }
      }

      int var8 = L + 1;
      L = var8;
    } while(F != 0);

  }

  public void $36508() {
    A = A & 240;
    L = A;
    A = A ^ A;
    int carry = (L & 128) >> 7;
    L = L << 1;
    A = (A + 92 + carry) & 0xff;
    H = A;
    A = mem(34259);
    A = A & 31;
    A = A | L;
    L = A;
    wMem16(34259, HL());
  }

  public void $37310() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1);
      super.A = var2;
      int var3 = super.A - 255;
      super.F = var3;
      if (super.F == 0) {
        return;
      }

      int var4 = super.A & 7;
      super.A = var4;
      super.F = super.A;
      if (super.F != 0) {
        int var8 = super.A - 3;
        super.F = var8;
        if (super.F != 0) {
          int var148 = super.A - 4;
          super.F = var148;
          if (super.F != 0) {
            int var203 = this.IX() + 3;
            int var204 = this.mem(var203);
            super.E = var204;
            super.D = 130;
            int var205 = this.DE();
            int var206 = this.mem(var205);
            super.A = var206;
            super.L = super.A;
            int var207 = this.IX() + 2;
            int var208 = this.mem(var207);
            super.A = var208;
            int var209 = super.A & 31;
            super.A = var209;
            super.F = super.A;
            int var210 = super.A + super.L & 255;
            super.A = var210;
            super.F = super.A;
            super.L = super.A;
            super.A = super.E;
            int var211 = super.A;
            int var212 = this.rlc(var211);
            super.A = var212;
            int var213 = super.A & 1;
            super.A = var213;
            super.F = super.A;
            int var214 = super.A | 92;
            super.A = var214;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var215 = this.IX() + 1;
            int var216 = this.mem(var215);
            super.A = var216;
            int var217 = super.A & 15;
            super.A = var217;
            super.F = super.A;
            int var218 = super.A + 56 & 255;
            super.A = var218;
            super.F = super.A;
            int var219 = super.A & 71;
            super.A = var219;
            super.F = super.A;
            super.C = super.A;
            int var220 = this.HL();
            int var221 = this.mem(var220);
            super.A = var221;
            int var222 = super.A & 56;
            super.A = var222;
            super.F = super.A;
            int var223 = super.A ^ super.C;
            super.A = var223;
            super.F = super.A;
            super.C = super.A;
            int var224 = this.HL();
            this.wMem(var224, super.C);
            int var225 = this.HL() + 1;
            this.HL(var225);
            int var226 = this.HL();
            this.wMem(var226, super.C);
            int var227 = this.HL();
            int var228 = this.DE();
            int var229 = var227 + var228 & '\uffff';
            this.HL(var229);
            int var230 = this.HL();
            this.wMem(var230, super.C);
            int var231 = this.HL() + 1;
            this.HL(var231);
            int var232 = this.HL();
            this.wMem(var232, super.C);
            int var233 = this.IX() + 3;
            int var234 = this.mem(var233);
            super.A = var234;
            int var235 = super.A & 14;
            super.A = var235;
            super.F = super.A;
            if (super.F != 0) {
              int var267 = this.HL();
              int var268 = this.DE();
              int var269 = var267 + var268 & '\uffff';
              this.HL(var269);
              int var270 = this.HL();
              this.wMem(var270, super.C);
              int var271 = this.HL() + 1;
              this.HL(var271);
              int var272 = this.HL();
              this.wMem(var272, super.C);
            }

            super.C = 1;
            int var236 = this.IX() + 1;
            int var237 = this.mem(var236);
            super.A = var237;
            int var238 = this.IX();
            int var239 = this.mem(var238);
            int var240 = super.A & var239;
            super.A = var240;
            int var241 = this.IX();
            this.mem(var241);
            int var242 = this.IX();
            this.mem(var242);
            super.F = super.A;
            int var243 = this.IX();
            this.mem(var243);
            int var244 = this.IX() + 2;
            int var245 = this.mem(var244);
            int var246 = super.A | var245;
            super.A = var246;
            int var247 = this.IX() + 2;
            this.mem(var247);
            int var248 = this.IX() + 2;
            this.mem(var248);
            super.F = super.A;
            int var249 = this.IX() + 2;
            this.mem(var249);
            int var250 = super.A & 224;
            super.A = var250;
            super.F = super.A;
            super.E = super.A;
            int var251 = this.IX() + 5;
            int var252 = this.mem(var251);
            super.D = var252;
            super.H = 130;
            int var253 = this.IX() + 3;
            int var254 = this.mem(var253);
            super.L = var254;
            int var255 = this.IX() + 2;
            int var256 = this.mem(var255);
            super.A = var256;
            int var257 = super.A & 31;
            super.A = var257;
            super.F = super.A;
            int var258 = this.HL();
            int var259 = this.mem(var258);
            int var260 = super.A | var259;
            super.A = var260;
            int var261 = this.HL();
            this.mem(var261);
            int var262 = this.HL();
            this.mem(var262);
            super.F = super.A;
            int var263 = this.HL();
            this.mem(var263);
            int var264 = this.HL() + 1;
            this.HL(var264);
            int var265 = this.HL();
            int var266 = this.mem(var265);
            super.H = var266;
            super.L = super.A;
            this.$37974();
          } else {
            int var149 = this.IX();
            int var150 = this.mem(var149) & 128;
            super.F = var150;
            if (super.F == 0) {
              int var276 = this.IX() + 4;
              var276 = this.IX() + 4;
              super.C = 44;
            } else {
              int var278 = this.IX() + 4;
              super.C = 244;
            }

            int var151 = this.IX() + 4;
            int var152 = this.mem(var151);
            super.A = var152;
            int var153 = super.A - super.C;
            super.F = var153;
            if (super.F != 0) {
              int var154 = super.A & 224;
              super.A = var154;
              super.F = super.A;
              if (super.F == 0) {
                int var155 = this.IX() + 2;
                int var156 = this.mem(var155);
                super.E = var156;
                super.D = 130;
                int var157 = this.DE();
                int var158 = this.mem(var157);
                super.A = var158;
                int var159 = this.IX() + 4;
                int var160 = this.mem(var159);
                int var161 = super.A + var160 & 255;
                super.A = var161;
                int var162 = this.IX() + 4;
                this.mem(var162);
                int var163 = this.IX() + 4;
                this.mem(var163);
                super.F = super.A;
                int var164 = this.IX() + 4;
                this.mem(var164);
                super.L = super.A;
                super.A = super.E;
                int var165 = super.A & 128;
                super.A = var165;
                super.F = super.A;
                int var166 = super.A;
                int var167 = this.rlc(var166);
                super.A = var167;
                int var168 = super.A | 92;
                super.A = var168;
                super.F = super.A;
                super.H = super.A;
                int var169 = this.IX() + 5;
                this.wMem(var169, 0);
                int var170 = this.HL();
                int var171 = this.mem(var170);
                super.A = var171;
                int var172 = super.A & 7;
                super.A = var172;
                super.F = super.A;
                int var173 = super.A - 7;
                super.F = var173;
                if (super.F == 0) {
                  int var279 = this.IX() + 5;
                  var279 = this.IX() + 5;
                }

                int var174 = this.HL();
                int var175 = this.mem(var174);
                super.A = var175;
                int var176 = super.A | 7;
                super.A = var176;
                super.F = super.A;
                int var177 = this.HL();
                this.wMem(var177, super.A);
                int var178 = this.DE() + 1;
                this.DE(var178);
                int var179 = this.DE();
                int var180 = this.mem(var179);
                super.A = var180;
                super.H = super.A;
                int var181 = super.H + -1;
                super.H = var181;
                super.F = super.H;
                int var182 = this.IX() + 6;
                int var183 = this.mem(var182);
                super.A = var183;
                int var184 = this.HL();
                this.wMem(var184, super.A);
                int var185 = super.H + 1;
                super.H = var185;
                int var186 = this.HL();
                int var187 = this.mem(var186);
                super.A = var187;
                int var188 = this.IX() + 5;
                int var189 = this.mem(var188);
                int var190 = super.A & var189;
                super.A = var190;
                int var191 = this.IX() + 5;
                this.mem(var191);
                int var192 = this.IX() + 5;
                this.mem(var192);
                super.F = super.A;
                int var193 = this.IX() + 5;
                this.mem(var193);
                int var194 = this.HL();
                this.wMem(var194, 255);
                int var195 = super.H + 1;
                super.H = var195;
                int var196 = this.IX() + 6;
                int var197 = this.mem(var196);
                super.A = var197;
                int var198 = this.HL();
                this.wMem(var198, super.A);
              }
            } else {
              this.BC(640);
              int var199 = this.mem(32990);
              super.A = var199;

              do {
                int var200 = super.A ^ 24;
                super.A = var200;
                super.F = super.A;

                do {
                  int var201 = super.B + -1;
                  super.B = var201;
                } while(super.B != 0);

                super.B = super.C;
                int var202 = super.C + -1;
                super.C = var202;
                super.F = super.C;
              } while(super.F != 0);
            }
          }
        } else {
          this.IY(33280);
          int var9 = this.IX() + 9;
          this.wMem(var9, 0);
          int var10 = this.IX() + 2;
          int var11 = this.mem(var10);
          super.A = var11;
          int var12 = this.IX() + 3;
          this.wMem(var12, super.A);
          int var13 = this.IX() + 5;
          this.wMem(var13, 128);

          while(true) {
            label107: {
              int var14 = this.IY();
              int var15 = this.mem(var14);
              super.A = var15;
              int var16 = this.IX() + 3;
              int var17 = this.mem(var16);
              int var18 = super.A + var17 & 255;
              super.A = var18;
              int var19 = this.IX() + 3;
              this.mem(var19);
              int var20 = this.IX() + 3;
              this.mem(var20);
              super.F = super.A;
              int var21 = this.IX() + 3;
              this.mem(var21);
              super.L = super.A;
              int var22 = this.IY() + 1;
              int var23 = this.mem(var22);
              super.H = var23;
              int var24 = this.mem(34262);
              super.A = var24;
              int var25 = super.A | super.A;
              super.A = var25;
              super.F = super.A;
              if (super.F == 0) {
                int var136 = this.IX() + 5;
                int var137 = this.mem(var136);
                super.A = var137;
                int var138 = this.HL();
                int var139 = this.mem(var138);
                int var140 = super.A & var139;
                super.A = var140;
                int var141 = this.HL();
                this.mem(var141);
                int var142 = this.HL();
                this.mem(var142);
                super.F = super.A;
                int var143 = this.HL();
                this.mem(var143);
                if (super.F == 0) {
                  break label107;
                }

                int var144 = this.IX() + 9;
                int var145 = this.mem(var144);
                super.A = var145;
                this.wMem(34262, super.A);
                int var146 = this.IX() + 11;
                int var147 = this.mem(var146) | 1;
                this.wMem(var146, var147);
              }

              int var26 = this.IX() + 9;
              int var27 = this.mem(var26);
              int var28 = super.A - var27;
              super.F = var28;
              int var29 = this.IX() + 9;
              this.mem(var29);
              if (super.F == 0) {
                int var124 = this.IX() + 11;
                int var125 = this.mem(var124) & 1;
                super.F = var125;
                if (super.F != 0) {
                  int var126 = this.IX() + 3;
                  int var127 = this.mem(var126);
                  super.B = var127;
                  int var128 = this.IX() + 5;
                  int var129 = this.mem(var128);
                  super.A = var129;
                  super.C = 1;
                  int var130 = super.A - 4;
                  super.F = var130;
                  if (super.F >= 0) {
                    super.C = 0;
                    int var133 = super.A - 16;
                    super.F = var133;
                    if (super.F >= 0) {
                      int var134 = super.B + -1;
                      super.B = var134;
                      super.F = super.B;
                      super.C = 3;
                      int var135 = super.A - 64;
                      super.F = var135;
                      if (super.F >= 0) {
                        super.C = 2;
                      }
                    }
                  }

                  int var131 = this.BC();
                  this.wMem16(34258, var131);
                  super.A = super.IYL;
                  int var132 = super.A - 16 & 255;
                  super.A = var132;
                  super.F = super.A;
                  this.wMem(34255, super.A);
                  this.$36508();
                }
              }
            }

            int var30 = this.IX() + 5;
            int var31 = this.mem(var30);
            super.A = var31;
            int var32 = this.HL();
            int var33 = this.mem(var32);
            int var34 = super.A | var33;
            super.A = var34;
            int var35 = this.HL();
            this.mem(var35);
            int var36 = this.HL();
            this.mem(var36);
            super.F = super.A;
            int var37 = this.HL();
            this.mem(var37);
            int var38 = this.HL();
            this.wMem(var38, super.A);
            int var39 = this.IX() + 9;
            int var40 = this.mem(var39);
            super.A = var40;
            int var41 = this.IX() + 1;
            int var42 = this.mem(var41);
            int var43 = super.A + var42 & 255;
            super.A = var43;
            int var44 = this.IX() + 1;
            this.mem(var44);
            int var45 = this.IX() + 1;
            this.mem(var45);
            super.F = super.A;
            int var46 = this.IX() + 1;
            this.mem(var46);
            super.L = super.A;
            int var47 = super.L | 128;
            super.L = var47;
            super.H = 131;
            int var48 = this.HL();
            int var49 = this.mem(var48);
            super.E = var49;
            super.D = 0;
            int var50 = this.IY();
            int var51 = this.DE();
            int var52 = var50 + var51 & '\uffff';
            this.IY(var52);
            int var53 = super.L & -129;
            super.L = var53;
            int var54 = this.HL();
            int var55 = this.mem(var54);
            super.A = var55;
            int var56 = super.A | super.A;
            super.A = var56;
            super.F = super.A;
            if (super.F != 0) {
              super.B = super.A;
              int var110 = this.IX() + 1;
              int var111 = this.mem(var110) & 128;
              super.F = var111;
              if (super.F != 0) {
                do {
                  int var118 = this.IX() + 5;
                  int var119 = this.mem(var118);
                  int var120 = this.rlc(var119);
                  this.wMem(var118, var120);
                  int var121 = this.IX() + 5;
                  int var122 = this.mem(var121) & 1;
                  super.F = var122;
                  if (super.F != 0) {
                    int var273 = this.IX() + 3;
                    var273 = this.IX() + 3;
                  }

                  int var123 = super.B + -1;
                  super.B = var123;
                } while(super.B != 0);
              } else {
                do {
                  int var112 = this.IX() + 5;
                  int var113 = this.mem(var112);
                  int var114 = this.rrc(var113);
                  this.wMem(var112, var114);
                  int var115 = this.IX() + 5;
                  int var116 = this.mem(var115) & 128;
                  super.F = var116;
                  if (super.F != 0) {
                    int var10000 = this.IX() + 3;
                  }

                  int var117 = super.B + -1;
                  super.B = var117;
                } while(super.B != 0);
              }
            }

            int var57 = this.IX() + 9;
            int var58 = this.mem(var57);
            super.A = var58;
            int var59 = this.IX() + 4;
            int var60 = this.mem(var59);
            int var61 = super.A - var60;
            super.F = var61;
            int var62 = this.IX() + 4;
            this.mem(var62);
            if (super.F == 0) {
              int var63 = this.mem(34262);
              super.A = var63;
              int var64 = super.A & 128;
              super.F = var64;
              if (super.F != 0) {
                int var107 = super.A + 1;
                super.A = var107;
                this.wMem(34262, super.A);
                int var108 = this.IX() + 11;
                int var109 = this.mem(var108) & -2;
                this.wMem(var108, var109);
              } else {
                int var65 = this.IX() + 11;
                int var66 = this.mem(var65) & 1;
                super.F = var66;
                if (super.F != 0) {
                  int var67 = this.mem(34256);
                  super.A = var67;
                  int var68 = super.A & 2;
                  super.F = var68;
                  if (super.F != 0) {
                    int var69 = super.A;
                    int var70 = this.rrc(var69);
                    super.A = var70;
                    int var71 = this.IX();
                    int var72 = this.mem(var71);
                    int var73 = super.A ^ var72;
                    super.A = var73;
                    int var74 = this.IX();
                    this.mem(var74);
                    int var75 = this.IX();
                    this.mem(var75);
                    super.F = super.A;
                    int var76 = this.IX();
                    this.mem(var76);
                    int var77 = super.A;
                    int var78 = this.rlc(var77);
                    super.A = var78;
                    int var79 = super.A;
                    int var80 = this.rlc(var79);
                    super.A = var80;
                    int var81 = super.A & 2;
                    super.A = var81;
                    super.F = super.A;
                    int var82 = super.A + -1;
                    super.A = var82;
                    super.F = super.A;
                    this.HL(34262);
                    int var83 = this.HL();
                    int var84 = this.mem(var83);
                    int var85 = super.A + var84 & 255;
                    super.A = var85;
                    int var86 = this.HL();
                    this.mem(var86);
                    int var87 = this.HL();
                    this.mem(var87);
                    super.F = super.A;
                    int var88 = this.HL();
                    this.mem(var88);
                    int var89 = this.HL();
                    this.wMem(var89, super.A);
                    int var90 = this.mem(33003);
                    super.A = var90;
                    super.C = super.A;
                    int var91 = this.mem(33824);
                    super.A = var91;
                    int var92 = super.A - super.C;
                    super.F = var92;
                    if (super.F == 0) {
                      int var103 = this.HL();
                      int var104 = this.mem(var103);
                      super.A = var104;
                      int var105 = super.A - 12;
                      super.F = var105;
                      if (super.F < 0) {
                        int var106 = this.HL();
                        this.wMem(var106, 12);
                      }
                    }

                    int var93 = this.HL();
                    int var94 = this.mem(var93);
                    super.A = var94;
                    int var95 = this.IX() + 4;
                    int var96 = this.mem(var95);
                    int var97 = super.A - var96;
                    super.F = var97;
                    int var98 = this.IX() + 4;
                    this.mem(var98);
                    if (super.F >= 0 && super.F != 0) {
                      int var99 = this.HL();
                      this.wMem(var99, 240);
                      int var100 = this.mem(34255);
                      super.A = var100;
                      int var101 = super.A & 248;
                      super.A = var101;
                      super.F = super.A;
                      this.wMem(34255, super.A);
                      int var102 = super.A ^ super.A;
                      super.A = var102;
                      super.F = super.A;
                      this.wMem(34257, super.A);
                    }
                  }
                }
              }
              break;
            }

            int var275 = this.IX() + 9;
          }
        }
      }

      this.DE(8);
      int var5 = this.IX();
      int var6 = this.DE();
      int var7 = var5 + var6 & '\uffff';
      this.IX(var7);
    }
  }
  public void $35563() {
    HL(22528);
    int var1 = HL();
    int var2 = mem(var1);
    A = var2;
    int var3 = A & 7;
    A = var3;
    F = A;

    do {
      int var4 = HL();
      int var5 = mem(var4);
      A = var5;
      int var6 = A + 3 & 255;
      A = var6;
      F = A;
      int var7 = A & 7;
      A = var7;
      F = A;
      D = A;
      int var8 = HL();
      int var9 = mem(var8);
      A = var9;
      int var10 = A + 24 & 255;
      A = var10;
      F = A;
      int var11 = A & 184;
      A = var11;
      F = A;
      int var12 = A | D;
      A = var12;
      F = A;
      int var13 = HL();
      wMem(var13, A);
      int var14 = HL() + 1;
      HL(var14);
      A = H;
      int var15 = A - 91;
      F = var15;
    } while (F != 0);

  }

  public void $37056() {
    IX(33024);

    while (true) {
      int var1 = IX();
      int var2 = mem(var1);
      A = var2;
      int var3 = A - 255;
      F = var3;
      if (F == 0) {
        return;
      }

      int var4 = A & 3;
      A = var4;
      F = A;
      if (F != 0) {
        int var8 = A - 1;
        F = var8;
        if (F != 0) {
          int var41 = A - 2;
          F = var41;
          if (F != 0) {
            F = mem[IX()] & 128;
            if (F != 0) {
              int var98 = IX() + 1;
              int var99 = mem(var98);
              A = var99;
              int var100 = A & 128;
              F = var100;
              if (F != 0) {
                A = A - 2;
                F = A;
                F = A - 148;
                if (F < 0) {
                  A = A - 2;
                  F = A;
                  F = A - 128;
                  if (F == 0) {
                    A = A ^ A;
                    F = A;
                  }
                }
              } else {
                A = A + 2;
                F = A;
                F = A - 18;
                if (F < 0) {
                  A = A + 2;
                  F = A;
                }
              }
            } else {
              A = mem(IX() + 1);
              F = A & 128;
              if (F == 0) {
                A = A - 2;
                F = A;
                F = A - 20;
                if (F < 0) {
                  A = A - 2;
                  F = A;
                  A = A | A;
                  F = A;
                  if (F == 0) {
                    A = 128;
                  }
                }
              } else {
                A = A + 2;
                F = A;
                F = A - 146;
                if (F < 0) {
                  A = A + 2;
                  F = A;
                }
              }
            }

            wMem(IX() + 1, A);
            A = A & 127;
            F = A;
            F = A - mem(IX() + 7);
            mem(IX() + 7);
            if (F == 0) {
              A = mem(IX());
              A = A ^ 128;
              F = A;
              wMem(IX(), A);
            }
          } else {
            label81:
            {
              A = mem(IX());
              A = A ^ 8;
              F = A;
              wMem(IX(), A);
              A = A & 24;
              F = A;
              if (F != 0) {
                A = mem(IX());
                A = (A + 32) & 0xFF;
                F = A;
                wMem(IX(), A);
              }

              A = mem(IX() + 3);
              A = (A + mem(IX() + 4)) & 0xFF;
              wMem(IX() + 3, A);
              F = A - mem(IX() + 7);
              if (F < 0) {
                F = A - mem(IX() + 6);
                if (F != 0 && F >= 0) {
                  break label81;
                }

                A = mem(IX() + 6);
                wMem(IX() + 3, A);
              }

              A = mem(IX() + 4);
              A = (-A & 0xFF);
              wMem(IX() + 4, A);
            }
          }
        } else {
          int i = mem[IX()] & 128;
          F = i;
          if (F == 0) {
            A = mem(IX());
            A = A - 32;
            F = A;
            A = A & 127;
            F = A;
            wMem(IX(), A);
            F = A - 96;
            if (F >= 0) {
              A = mem(IX() + 2);
              A = A & 31;
              F = A;
              F = A - mem(IX() + 6);
              mem(IX() + 6);
              if (F != 0) {
                int var10000 = IX() + 2;
                int mem1 = mem(var10000);
                wMem(var10000, mem1 - 1);
              } else {
                int var40 = IX();
                wMem(var40, 129);
              }
            }
          } else {
            A = mem(IX());
            A = (A + 32) & 0xff;
            F = A;
            A = A | 128;
            F = A;
            wMem(IX(), A);
            F = A - 160;
            if (F < 0) {
              A = mem(IX() + 2);
              A = A & 31;
              F = A;
              F = A - mem(IX() + 7);
              if (F != 0) {
                int var10000 = IX() + 2;
                int mem1 = mem(var10000);
                wMem(var10000, mem1 + 1);
              } else {
                wMem(IX(), 97);
              }
            }
          }
        }
      }

      DE(8);
      IX(IX() + DE());
    }
  }

  public void $36288() {
    int var1 = A & 3;
    A = var1;
    F = A;
    C = A;
    int var2 = A;
    int var3 = rlc(var2);
    A = var3;
    int var4 = A;
    int var5 = rlc(var4);
    A = var5;
    int var6 = A;
    int var7 = rlc(var6);
    A = var7;
    int var8 = A + C;
    A = var8;
    F = A;
    int var9 = A + 160;
    A = var9;
    F = A;
    E = A;
    D = 128;
    int var10 = DE();
    int var11 = mem(var10);
    A = var11;
    int var12 = IX();
    wMem(var12, A);
    int var13 = IX() + 1;
    IX(var13);
  }


  public void $36203() {
    this.HL(32768);
    this.IX(24064);

    do {
      int var1 = this.HL();
      int var2 = this.mem(var1);
      super.A = var2;
      int var3 = super.A;
      int var4 = this.rlc(var3);
      super.A = var4;
      int var5 = super.A;
      int var6 = this.rlc(var5);
      super.A = var6;
      this.$36288();
      int var7 = this.HL();
      int var8 = this.mem(var7);
      super.A = var8;
      int var9 = super.A;
      int var10 = this.rrc(var9);
      super.A = var10;
      int var11 = super.A;
      int var12 = this.rrc(var11);
      super.A = var12;
      int var13 = super.A;
      int var14 = this.rrc(var13);
      super.A = var14;
      int var15 = super.A;
      int var16 = this.rrc(var15);
      super.A = var16;
      this.$36288();
      int var17 = this.HL();
      int var18 = this.mem(var17);
      super.A = var18;
      int var19 = super.A;
      int var20 = this.rrc(var19);
      super.A = var20;
      int var21 = super.A;
      int var22 = this.rrc(var21);
      super.A = var22;
      this.$36288();
      int var23 = this.HL();
      int var24 = this.mem(var23);
      super.A = var24;
      this.$36288();
      int var25 = this.HL() + 1;
      this.HL(var25);
      super.A = super.L;
      int var26 = super.A & 128;
      super.A = var26;
      super.F = super.A;
    } while (super.F == 0);

    int var27 = this.mem(32985);
    super.A = var27;
    int var28 = super.A | super.A;
    super.A = var28;
    super.F = super.A;
    if (super.F != 0) {
      int var44 = this.mem16(32983);
      this.HL(var44);
      super.B = super.A;
      int var45 = this.mem(32973);
      super.A = var45;

      do {
        int var46 = this.HL();
        this.wMem(var46, super.A);
        int var47 = this.HL() + 1;
        this.HL(var47);
        int var48 = super.B + -1;
        super.B = var48;
      } while (super.B != 0);
    }

    int var29 = this.mem(32989);
    super.A = var29;
    int var30 = super.A | super.A;
    super.A = var30;
    super.F = super.A;
    if (super.F != 0) {
      int var31 = this.mem16(32987);
      this.HL(var31);
      int var32 = this.mem(32986);
      super.A = var32;
      int var33 = super.A & 1;
      super.A = var33;
      super.F = super.A;
      int var34 = super.A;
      int var35 = this.rlc(var34);
      super.A = var35;
      int var36 = super.A + 223;
      super.A = var36;
      super.F = super.A;
      super.E = super.A;
      super.D = 255;
      int var37 = this.mem(32989);
      super.A = var37;
      super.B = super.A;
      int var38 = this.mem(32964);
      super.A = var38;

      do {
        int var39 = this.HL();
        this.wMem(var39, super.A);
        int var40 = this.HL();
        int var41 = this.DE();
        int var42 = var40 + var41;
        this.HL(var42);
        int var43 = super.B + -1;
        super.B = var43;
      } while (super.B != 0);

    }
  }

  public void $38137() {
    HL(mem16(32983));
    A = H;
    int var1 = A & 1;
    A = var1;
    F = A;
    int var2 = A;
    int var3 = rlc(var2);
    A = var3;
    int var4 = A;
    int var5 = rlc(var4);
    A = var5;
    int var6 = A;
    int var7 = rlc(var6);
    A = var7;
    int var8 = A + 112;
    A = var8;
    F = A;
    H = A;
    E = L;
    D = H;
    int var9 = mem(32985);
    A = var9;
    int var10 = A | A;
    A = var10;
    F = A;
    if (F != 0) {
      B = A;
      int var11 = mem(32982);
      A = var11;
      int var12 = A | A;
      A = var12;
      F = A;
      if (F == 0) {
        int var32 = HL();
        int var33 = mem(var32);
        A = var33;
        int var34 = A;
        int var35 = rlc(var34);
        A = var35;
        int var36 = A;
        int var37 = rlc(var36);
        A = var37;
        int var38 = H + 1;
        H = var38;
        int var39 = H + 1;
        H = var39;
        int var40 = HL();
        int var41 = mem(var40);
        C = var41;
        int var42 = C;
        int var43 = rrc(var42);
        C = var43;
        int var44 = C;
        int var45 = rrc(var44);
        C = var45;
      } else {
        int var13 = HL();
        int var14 = mem(var13);
        A = var14;
        int var15 = A;
        int var16 = rrc(var15);
        A = var16;
        int var17 = A;
        int var18 = rrc(var17);
        A = var18;
        int var19 = H + 1;
        H = var19;
        int var20 = H + 1;
        H = var20;
        int var21 = HL();
        int var22 = mem(var21);
        C = var22;
        int var23 = C;
        int var24 = rlc(var23);
        C = var24;
        int var25 = C;
        int var26 = rlc(var25);
        C = var26;
      }

      do {
        int var27 = DE();
        wMem(var27, A);
        int var28 = HL();
        wMem(var28, C);
        int var29 = L + 1;
        L = var29;
        int var30 = E + 1;
        E = var30;
        int var31 = B + -1;
        B = var31;
      } while (B != 0);

    }
  }

  public void $38137A() {
    HL(mem16(32983));
    A = H;
    A = A & 1;
    F = A;
    A = rlc(A);
    A = rlc(A);
    A = rlc(A);
    A = A + 112;
    F = A;
    H = A;
    E = L;
    D = H;
    A = mem(32985);
    A = A | A;
    F = A;
    if (F != 0) {
      B = A;
      A = mem(32982);
      A = A | A;
      F = A;
      if (F != 0) {
        A = mem(HL());
        A = rrc(A);
        A = rrc(A);
        H = H + 1;
        H = H + 1;
        C = mem(HL());
        C = rlc(C);
        C = rlc(C);
      } else {
        A = mem(HL());
        A = rlc(A);
        A = rlc(A);
        H = H + 1;
        H = H + 1;
        C = mem(HL());
        C = rrc(C);
        C = rrc(C);
      }
      while (true) {
        wMem(DE(), A);
        wMem(HL(), C);
        L = L + 1;
        E = E + 1;
        B = B + -1;
        if (B == 0) {
          break;
        }
      }
    }
  }

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
