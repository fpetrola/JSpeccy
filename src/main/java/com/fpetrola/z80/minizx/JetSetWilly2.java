package com.fpetrola.z80.minizx;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.minizx.emulation.Helper;
import com.fpetrola.z80.minizx.emulation.MiniZXWithEmulation;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class JetSetWilly2 extends MiniZX {
  static Semaphore semaphore = new Semaphore(2);

  volatile int checking;
  volatile int checkingEmu;
  volatile static Stack<StateSync> stateSync = new Stack();
  private MiniZXWithEmulation miniZXWithEmulation;
  private static OOZ80<WordNumber> ooz80 = Helper.createOOZ80(io);
  private int doReturn;

  public static void main(String[] args) {
    JetSetWilly2 jetSetWilly = new JetSetWilly2();
    jetSetWilly.$34762();
  }

  @Override
  protected Function<Integer, Integer> getMemFunction() {
    return index -> {
      return mem[index];
//      WordNumber datum = ooz80.getState().getMemory().getData()[index];
//      if (datum == null)
//        datum = WordNumber.createValue(0);
//      return datum.intValue();
    };
  }

  @Override
  public void init() {
    super.init();

//    Register<WordNumber> pc = ooz80.getState().getPc();
//    Memory<WordNumber> memory = ooz80.getState().getMemory();
//    memory.addMemoryWriteListener((MemoryWriteListener<WordNumber>) (address, value) -> {
//      checkSyncEmu(address.intValue(), value.intValue(), pc.read().intValue());
//    });
//    memory.addMemoryReadListener((MemoryReadListener<WordNumber>) (address, value) -> {
//      checkSyncEmu(address.intValue(), value.intValue(), pc.read().intValue());
//    });
//
//    miniZXWithEmulation = new MiniZXWithEmulation(ooz80, this);
//    miniZXWithEmulation.copyStateBackToEmulation();
//    pc.write(WordNumber.createValue(34762));
//    new Thread(() -> miniZXWithEmulation.emulate()).start();
  }

  protected void checkSyncEmu(int address, int value, int pc) {
    System.out.println("sync emu: " + pc);
    while (checking == 0) ;
    if (checking != pc)
      System.out.print("");
    else {
      checkMatching(pc);
      checking = 0;
    }
  }

  protected void checkSyncJava(int address, int value, int pc) {
//    System.out.println("sync java: " + pc);
//
//    checking = pc;
//    while (checking != 0) ;
  }

  private void checkMatching(int pc) {
    if (!miniZXWithEmulation.stateIsMatching()) {
      System.out.println("not matching at: " + pc);
    } else {
      System.out.println("ok at: " + pc);
    }
    stateSync.clear();
  }

  @Override
  protected byte[] getProgramBytes() {
    String jsw = "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";
    return gzipDecompressFromBase64(jsw);
  }

  protected Map<Integer, Runnable> getConvertedRoutines() {
    Map<Integer, Runnable> convertedRoutines = new HashMap<>();
//    convertedRoutines.put(35211, () -> $35211());
//    convertedRoutines.put(35563, () -> $35563());
//    convertedRoutines.put(36147, () -> $36147());
//    convertedRoutines.put(36171, () -> $36171(120));
//    convertedRoutines.put(36203, () -> $36203());
//    convertedRoutines.put(36288, () -> $36288());
//    convertedRoutines.put(36508, () -> $36508());
//    convertedRoutines.put(37056, () -> $37056());
//    convertedRoutines.put(37310, () -> $37310());
//    convertedRoutines.put(37841, () -> $37841());
//    convertedRoutines.put(37974, () -> $37974());
//    convertedRoutines.put(38064, () -> $38064());
//    convertedRoutines.put(38137, () -> $38137());
//    convertedRoutines.put(38276, () -> $38276());
//    convertedRoutines.put(38430, () -> $38430());
//    convertedRoutines.put(38528, () -> $38528());
//    convertedRoutines.put(38545, () -> $38545());
//    convertedRoutines.put(38555, () -> $38555());
//    convertedRoutines.put(34463, () -> $34463());

    return convertedRoutines;
  }
  public void $34762() {
    label283:
    while(true) {
      int var1 = super.A ^ super.A;
      super.A = var1;
      super.F = super.A;
      this.wMem(34254, super.A, 34763);
      this.wMem(34273, super.A, 34766);
      this.wMem(34253, super.A, 34769);
      this.wMem(34257, super.A, 34772);
      this.wMem(34251, super.A, 34775);
      this.wMem(34272, super.A, 34778);
      this.wMem(34271, super.A, 34781);
      super.A = 7;
      this.wMem(34252, super.A, 34786);
      super.A = 208;
      this.wMem(34255, super.A, 34791);
      super.A = 33;
      this.wMem(33824, super.A, 34796);
      this.HL(23988);
      int var2 = this.HL();
      this.wMem16(34259, var2, 34802);
      this.HL(34172);
      int var3 = this.HL();
      this.wMem(var3, 48, 34808);
      int var4 = this.HL() + 1 & '\uffff';
      this.HL(var4);
      int var5 = this.HL();
      this.wMem(var5, 48, 34811);
      int var6 = this.HL() + 1 & '\uffff';
      this.HL(var6);
      int var7 = this.HL();
      this.wMem(var7, 48, 34814);
      super.H = 164;
      int var8 = this.mem(41983, 34818);
      super.A = var8;
      super.L = super.A;
      this.wMem(34270, super.A, 34822);

      do {
        int var9 = this.HL();
        int var10 = this.mem(var9, 34825) | 64;
        int var11 = this.HL();
        this.wMem(var11, var10, 34825);
        int var12 = super.L + 1 & 255;
        super.L = var12;
      } while(super.L != 0);

      this.HL(34274);
      int var13 = this.HL();
      int var14 = this.mem(var13, 34833) | 1;
      int var15 = this.HL();
      this.wMem(var15, var14, 34833);

      label275:
      while(true) {
        this.HL(16384);
        this.DE(16385);
        this.BC(6143);
        int var16 = this.HL();
        this.wMem(var16, 0, 34844);
        this.ldir();
        this.HL(38912);
        this.BC(768);
        this.ldir();
        this.HL(23136);
        this.DE(23137);
        this.BC(31);
        int var17 = this.HL();
        this.wMem(var17, 70, 34865);
        this.ldir();
        this.IX(33876);
        this.DE(20576);
        super.C = 32;
        this.$38528();
        this.DE(22528);

        do {
          int var18 = this.DE();
          int var19 = this.mem(var18, 34884);
          super.A = var19;
          int var20 = super.A | super.A;
          super.A = var20;
          if (super.A != 0 && super.A != 211 && super.A != 9 && super.A != 45 && super.A != 36) {
            super.C = 0;
            if (super.A != 8 && super.A != 41) {
              if (super.A != 44) {
                if (super.A != 5) {
                  super.C = 16;
                }
              } else {
                super.A = 37;
                int var314 = this.DE();
                this.wMem(var314, super.A, 34928);
              }
            }

            super.A = super.E;
            int var300 = super.A & 1;
            super.A = var300;
            super.F = super.A;
            int var301 = super.A;
            int var302 = this.rlc(var301);
            super.A = var302;
            int var303 = super.A;
            int var304 = this.rlc(var303);
            super.A = var304;
            int var305 = super.A;
            int var306 = this.rlc(var305);
            super.A = var306;
            int var307 = super.A | super.C;
            super.A = var307;
            super.F = super.A;
            super.C = super.A;
            super.B = 0;
            this.HL(33841);
            int var308 = this.HL();
            int var309 = this.BC();
            int var310 = var308 + var309 & '\uffff';
            this.HL(var310);
            int var311 = this.DE();
            this.push(var311);
            int var312 = super.D & 1;
            super.F = var312;
            super.D = 64;
            if (super.F != 0) {
              super.D = 72;
            }

            super.B = 8;
            this.$38555();
            int var313 = this.pop();
            this.DE(var313);
          }

          int var21 = this.DE() + 1 & '\uffff';
          this.DE(var21);
          super.A = super.D;
        } while(super.A != 90);

        this.BC(31);
        int var22 = super.A ^ super.A;
        super.A = var22;
        super.F = super.A;

        do {
          int var23 = this.BC();
          int var24 = this.in(var23);
          super.E = var24;
          int var25 = super.A | super.E;
          super.A = var25;
          int var26 = super.B - 1 & 255;
          super.B = var26;
        } while(super.B != 0);

        int var27 = super.A & 32;
        super.A = var27;
        if (super.A == 0) {
          super.A = 1;
          this.wMem(34254, super.A, 34981);
        }

        this.HL(34299);
        this.$38562();
        if (super.F != 0) {
          break;
        }

        int var285 = super.A ^ super.A;
        super.A = var285;
        super.F = super.A;
        this.wMem(34276, super.A, 34994);

        while(true) {
          this.$35563();
          this.HL(23136);
          this.DE(23137);
          this.BC(31);
          int var286 = this.HL();
          this.wMem(var286, 79, 35009);
          this.ldir();
          int var287 = this.mem(34276, 35013);
          super.A = var287;
          this.IX(33876);
          super.E = super.A;
          super.D = 0;
          int var288 = this.IX();
          int var289 = this.DE();
          int var290 = var288 + var289 & '\uffff';
          this.IX(var290);
          this.DE(20576);
          super.C = 32;
          this.$38528();
          int var291 = this.mem(34276, 35033);
          super.A = var291;
          int var292 = super.A & 31;
          super.A = var292;
          super.F = super.A;
          int var293 = super.A + 50 & 255;
          super.A = var293;
          this.$38622();
          this.BC(45054);
          int var294 = this.BC();
          int var295 = this.in(var294);
          super.A = var295;
          int var296 = super.A & 1;
          super.A = var296;
          super.F = super.A;
          if (super.A != 1) {
            break label275;
          }

          int var297 = this.mem(34276, 35054);
          super.A = var297;
          int var298 = super.A + 1 & 255;
          super.A = var298;
          super.F = super.A;
          int var299 = super.A - 224;
          super.F = var299;
          this.wMem(34276, super.A, 35060);
          if (super.F == 0) {
            break;
          }
        }
      }

      this.HL(34181);
      this.DE(34175);
      this.BC(6);
      this.ldir();
      this.HL(39424);
      this.DE(23040);
      this.BC(256);
      this.ldir();

      while(true) {
        int var28 = this.mem(33824, 35090);
        super.A = var28;
        int var29 = super.A | 192;
        super.A = var29;
        super.F = super.A;
        super.H = super.A;
        super.L = 0;
        this.DE(32768);
        this.BC(256);
        this.ldir();
        this.IX(33008);
        this.DE(33024);
        super.A = 8;

        do {
          int var30 = this.IX();
          int var31 = this.mem(var30, 35115);
          super.L = var31;
          int var32 = super.L & -129;
          super.L = var32;
          super.H = 20;
          int var33 = this.HL() * 2 & '\uffff';
          this.HL(var33);
          int var34 = this.HL() * 2 & '\uffff';
          this.HL(var34);
          int var35 = this.HL() * 2 & '\uffff';
          this.HL(var35);
          this.BC(2);
          this.ldir();
          int var36 = this.IX() + 1;
          int var37 = this.mem(var36, 35130);
          super.C = var37;
          int var38 = this.HL();
          this.wMem(var38, super.C, 35133);
          this.BC(6);
          this.ldir();
          int var39 = this.IX() + 1 & '\uffff';
          this.IX(var39);
          int var40 = this.IX() + 1 & '\uffff';
          this.IX(var40);
          int var41 = super.A - 1 & 255;
          super.A = var41;
        } while(super.A != 0);

        this.HL(34255);
        this.DE(34263);
        this.BC(7);
        this.ldir();
        this.$36147();
        this.HL(20480);
        this.DE(20481);
        this.BC(2047);
        int var42 = this.HL();
        this.wMem(var42, 0, 35169);
        this.ldir();
        this.IX(32896);
        super.C = 32;
        this.DE(20480);
        this.$38528();
        this.IX(34132);
        this.DE(20576);
        super.C = 32;
        this.$38528();
        int var43 = this.mem(32990, 35197);
        super.A = var43;
        super.C = 254;
        int var44 = super.A ^ super.A;
        super.A = var44;
        super.F = super.A;
        this.wMem(34262, super.A, 35205);

        while(true) {
          label293: {
            this.$35211();
            this.HL(24064);
            this.DE(23552);
            this.BC(512);
            this.ldir();
            this.HL(28672);
            this.DE(24576);
            this.BC(4096);
            this.ldir();
            this.$37056();
            int var45 = this.mem(34271, 35273);
            super.A = var45;
            if (super.A != 3) {
              this.$36307();
              if (this.decPops()) {
                break;
              }
            }

            int var46 = this.mem(34255, 35281);
            super.A = var46;
            if (super.A >= 225) {
              this.$38064();
              if (this.decPops()) {
                break;
              }
            }

            label213: {
              label290: {
                int var47 = this.mem(34271, 35289);
                super.A = var47;
                if (super.A != 3) {
                  this.$38344();
                  if (this.decPops()) {
                    break label290;
                  }
                }

                int var48 = this.mem(34271, 35297);
                super.A = var48;
                if (super.A == 2) {
                  this.$38276();
                }

                this.$38196();
                if (!this.decPops()) {
                  this.$37310();
                  if (!this.decPops()) {
                    this.$38137();
                    this.$37841();
                    break label213;
                  }
                }
              }

              super.A = 255;
              this.wMem(34257, super.A, 37050);
            }

            this.HL(24576);
            this.DE(16384);
            this.BC(4096);
            this.ldir();
            int var49 = this.mem(34271, 35328);
            super.A = var49;
            int var50 = super.A & 2;
            super.A = var50;
            super.F = super.A;
            int var51 = super.A;
            int var52 = this.rrc(var51);
            super.A = var52;
            this.HL(34258);
            int var53 = this.HL();
            int var54 = this.mem(var53, 35337);
            int var55 = super.A | var54;
            super.A = var55;
            int var56 = this.HL();
            this.mem(var56, 35337);
            super.F = super.A;
            int var57 = this.HL();
            this.wMem(var57, super.A, 35338);
            int var58 = this.mem(34253, 35339);
            super.A = var58;
            int var59 = super.A | super.A;
            super.A = var59;
            if (super.A != 0) {
              int var276 = super.A - 1 & 255;
              super.A = var276;
              super.F = super.A;
              this.wMem(34253, super.A, 35346);
              int var277 = super.A;
              int var278 = this.rlc(var277);
              super.A = var278;
              int var279 = super.A;
              int var280 = this.rlc(var279);
              super.A = var280;
              int var281 = super.A;
              int var282 = this.rlc(var281);
              super.A = var282;
              int var283 = super.A & 56;
              super.A = var283;
              super.F = super.A;
              this.HL(23552);
              this.DE(23553);
              this.BC(511);
              int var284 = this.HL();
              this.wMem(var284, super.A, 35363);
              this.ldir();
            }

            this.HL(23552);
            this.DE(22528);
            this.BC(512);
            this.ldir();
            this.IX(34175);
            this.DE(20601);
            super.C = 6;
            this.$38528();
            this.IX(34172);
            this.DE(20592);
            super.C = 3;
            this.$38528();
            int var60 = this.mem(34251, 35401);
            super.A = var60;
            int var61 = super.A + 1 & 255;
            super.A = var61;
            super.F = super.A;
            this.wMem(34251, super.A, 35405);
            if (super.F == 0) {
              this.IX(34175);
              int var245 = this.IX() + 4;
              int var246 = this.mem(var245, 35414) + 1;
              this.wMem(var245, var246, 35414);
              int var247 = var246 & 255;
              this.wMem(var245, var247, 35414);
              int var248 = this.IX() + 4;
              int var249 = this.mem(var248, 35417);
              super.A = var249;
              if (super.A == 58) {
                int var250 = this.IX() + 4;
                this.wMem(var250, 48, 35424);
                int var251 = this.IX() + 3;
                int var252 = this.mem(var251, 35428) + 1;
                this.wMem(var251, var252, 35428);
                int var253 = var252 & 255;
                this.wMem(var251, var253, 35428);
                int var254 = this.IX() + 3;
                int var255 = this.mem(var254, 35431);
                super.A = var255;
                if (super.A == 54) {
                  int var256 = this.IX() + 3;
                  this.wMem(var256, 48, 35438);
                  int var257 = this.IX();
                  int var258 = this.mem(var257, 35442);
                  super.A = var258;
                  if (super.A == 49) {
                    int var266 = this.IX() + 1;
                    int var267 = this.mem(var266, 35449) + 1;
                    this.wMem(var266, var267, 35449);
                    int var268 = var267 & 255;
                    this.wMem(var266, var268, 35449);
                    int var269 = this.IX() + 1;
                    int var270 = this.mem(var269, 35452);
                    super.A = var270;
                    if (super.A == 51) {
                      int var271 = this.IX() + 5;
                      int var272 = this.mem(var271, 35459);
                      super.A = var272;
                      if (super.A == 112) {
                        continue label283;
                      }

                      int var273 = this.IX();
                      this.wMem(var273, 32, 35467);
                      int var274 = this.IX() + 1;
                      this.wMem(var274, 49, 35471);
                      int var275 = this.IX() + 5;
                      this.wMem(var275, 112, 35475);
                    }
                  } else {
                    int var259 = this.IX() + 1;
                    int var260 = this.mem(var259, 35481) + 1;
                    this.wMem(var259, var260, 35481);
                    int var261 = var260 & 255;
                    this.wMem(var259, var261, 35481);
                    int var262 = this.IX() + 1;
                    int var263 = this.mem(var262, 35484);
                    super.A = var263;
                    if (super.A == 58) {
                      int var264 = this.IX() + 1;
                      this.wMem(var264, 48, 35491);
                      int var265 = this.IX();
                      this.wMem(var265, 49, 35495);
                    }
                  }
                }
              }
            }

            this.BC(65278);
            int var62 = this.BC();
            int var63 = this.in(var62);
            super.A = var63;
            super.E = super.A;
            super.B = 127;
            int var64 = this.BC();
            int var65 = this.in(var64);
            super.A = var65;
            int var66 = super.A | super.E;
            super.A = var66;
            super.F = super.A;
            int var67 = super.A & 1;
            super.A = var67;
            if (super.A == 0) {
              continue label283;
            }

            int var68 = this.mem(34272, 35515);
            super.A = var68;
            int var69 = super.A + 1 & 255;
            super.A = var69;
            super.F = super.A;
            this.wMem(34272, super.A, 35519);
            if (super.F != 0) {
              super.B = 253;
              int var242 = this.BC();
              int var243 = this.in(var242);
              super.A = var243;
              int var244 = super.A & 31;
              super.A = var244;
              super.F = super.A;
              if (super.A == 31) {
                break label293;
              }

              this.DE(0);
            }

            while(true) {
              super.B = 2;
              int var70 = this.BC();
              int var71 = this.in(var70);
              super.A = var71;
              int var72 = super.A & 31;
              super.A = var72;
              super.F = super.A;
              if (super.A != 31) {
                this.HL(39424);
                this.DE(23040);
                this.BC(256);
                this.ldir();
                int var73 = this.mem(32990, 35602);
                super.A = var73;
                break;
              }

              int var239 = super.E + 1 & 255;
              super.E = var239;
              if (super.E == 0) {
                int var240 = super.D + 1 & 255;
                super.D = var240;
                if (super.D == 0) {
                  int var241 = this.mem(34275, 35553);
                  super.A = var241;
                  if (super.A != 10) {
                    this.$35563();
                  }
                }
              }
            }
          }

          int var74 = this.mem(34257, 35607);
          super.A = var74;
          if (super.A == 255) {
            super.A = 71;

            do {
              this.HL(22528);
              this.DE(22529);
              this.BC(511);
              int var75 = this.HL();
              this.wMem(var75, super.A, 35852);
              this.ldir();
              super.E = super.A;
              int var76 = ~super.A;
              super.A = var76;
              super.F = super.A;
              int var77 = super.A & 7;
              super.A = var77;
              super.F = super.A;
              int var78 = super.A;
              int var79 = this.rlc(var78);
              super.A = var79;
              int var80 = super.A;
              int var81 = this.rlc(var80);
              super.A = var81;
              int var82 = super.A;
              int var83 = this.rlc(var82);
              super.A = var83;
              int var84 = super.A | 7;
              super.A = var84;
              super.F = super.A;
              super.D = super.A;
              super.C = super.E;
              int var85 = super.C;
              int var86 = this.rrc(var85);
              super.C = var86;
              int var87 = super.C;
              int var88 = this.rrc(var87);
              super.C = var88;
              int var89 = super.C;
              int var90 = this.rrc(var89);
              super.C = var90;
              int var91 = super.A | 16;
              super.A = var91;
              super.F = super.A;
              int var92 = super.A ^ super.A;
              super.A = var92;
              super.F = super.A;

              do {
                int var93 = super.A ^ 24;
                super.A = var93;
                super.F = super.A;
                super.B = super.D;

                do {
                  int var94 = super.B - 1 & 255;
                  super.B = var94;
                } while(super.B != 0);

                int var95 = super.C - 1 & 255;
                super.C = var95;
              } while(super.C != 0);

              super.A = super.E;
              int var96 = super.A - 1 & 255;
              super.A = var96;
              super.F = super.A;
            } while(super.A != 63);

            this.HL(34252);
            int var97 = this.HL();
            int var98 = this.mem(var97, 35894);
            super.A = var98;
            int var99 = super.A | super.A;
            super.A = var99;
            if (super.A == 0) {
              this.HL(16384);
              this.DE(16385);
              this.BC(4095);
              int var100 = this.HL();
              this.wMem(var100, 0, 35923);
              this.ldir();
              int var101 = super.A ^ super.A;
              super.A = var101;
              super.F = super.A;
              this.wMem(34276, super.A, 35928);
              this.DE(40256);
              this.HL(18575);
              super.C = 0;
              this.$37974();
              this.DE(40032);
              this.HL(18639);
              super.C = 0;
              this.$37974();

              do {
                int var102 = this.mem(34276, 35953);
                super.A = var102;
                super.C = super.A;
                super.B = 130;
                int var103 = this.BC();
                int var104 = this.mem(var103, 35959);
                super.A = var104;
                int var105 = super.A | 15;
                super.A = var105;
                super.F = super.A;
                super.L = super.A;
                int var106 = this.BC() + 1 & '\uffff';
                this.BC(var106);
                int var107 = this.BC();
                int var108 = this.mem(var107, 35964);
                super.A = var108;
                int var109 = super.A - 32 & 255;
                super.A = var109;
                super.F = super.A;
                super.H = super.A;
                this.DE(40000);
                super.C = 0;
                this.$37974();
                int var110 = this.mem(34276, 35976);
                super.A = var110;
                int var111 = ~super.A;
                super.A = var111;
                super.F = super.A;
                super.E = super.A;
                int var112 = super.A ^ super.A;
                super.A = var112;
                super.F = super.A;
                this.BC(64);

                do {
                  int var113 = super.A ^ 24;
                  super.A = var113;
                  super.F = super.A;
                  super.B = super.E;

                  do {
                    int var114 = super.B - 1 & 255;
                    super.B = var114;
                  } while(super.B != 0);

                  int var115 = super.C - 1 & 255;
                  super.C = var115;
                } while(super.C != 0);

                this.HL(22528);
                this.DE(22529);
                this.BC(511);
                int var116 = this.mem(34276, 36004);
                super.A = var116;
                int var117 = super.A & 12;
                super.A = var117;
                super.F = super.A;
                int var118 = super.A;
                int var119 = this.rlc(var118);
                super.A = var119;
                int var120 = super.A | 71;
                super.A = var120;
                super.F = super.A;
                int var121 = this.HL();
                this.wMem(var121, super.A, 36012);
                this.ldir();
                int var122 = super.A & 250;
                super.A = var122;
                super.F = super.A;
                int var123 = super.A | 2;
                super.A = var123;
                super.F = super.A;
                this.wMem(22991, super.A, 36019);
                this.wMem(22992, super.A, 36022);
                this.wMem(23023, super.A, 36025);
                this.wMem(23024, super.A, 36028);
                int var124 = this.mem(34276, 36031);
                super.A = var124;
                int var125 = super.A + 4 & 255;
                super.A = var125;
                super.F = super.A;
                this.wMem(34276, super.A, 36036);
              } while(super.A != 196);

              this.IX(34164);
              super.C = 4;
              this.DE(16586);
              this.$38528();
              this.IX(34168);
              super.C = 4;
              this.DE(16594);
              this.$38528();
              this.BC(0);
              super.D = 6;

              while(true) {
                int var126 = super.B - 1 & 255;
                super.B = var126;
                if (super.B == 0) {
                  super.A = super.C;
                  int var127 = super.A & 7;
                  super.A = var127;
                  super.F = super.A;
                  int var128 = super.A | 64;
                  super.A = var128;
                  super.F = super.A;
                  this.wMem(22730, super.A, 36079);
                  int var129 = super.A + 1 & 255;
                  super.A = var129;
                  super.F = super.A;
                  int var130 = super.A & 7;
                  super.A = var130;
                  super.F = super.A;
                  int var131 = super.A | 64;
                  super.A = var131;
                  super.F = super.A;
                  this.wMem(22731, super.A, 36087);
                  int var132 = super.A + 1 & 255;
                  super.A = var132;
                  super.F = super.A;
                  int var133 = super.A & 7;
                  super.A = var133;
                  super.F = super.A;
                  int var134 = super.A | 64;
                  super.A = var134;
                  super.F = super.A;
                  this.wMem(22732, super.A, 36095);
                  int var135 = super.A + 1 & 255;
                  super.A = var135;
                  super.F = super.A;
                  int var136 = super.A & 7;
                  super.A = var136;
                  super.F = super.A;
                  int var137 = super.A | 64;
                  super.A = var137;
                  super.F = super.A;
                  this.wMem(22733, super.A, 36103);
                  int var138 = super.A + 1 & 255;
                  super.A = var138;
                  super.F = super.A;
                  int var139 = super.A & 7;
                  super.A = var139;
                  super.F = super.A;
                  int var140 = super.A | 64;
                  super.A = var140;
                  super.F = super.A;
                  this.wMem(22738, super.A, 36111);
                  int var141 = super.A + 1 & 255;
                  super.A = var141;
                  super.F = super.A;
                  int var142 = super.A & 7;
                  super.A = var142;
                  super.F = super.A;
                  int var143 = super.A | 64;
                  super.A = var143;
                  super.F = super.A;
                  this.wMem(22739, super.A, 36119);
                  int var144 = super.A + 1 & 255;
                  super.A = var144;
                  super.F = super.A;
                  int var145 = super.A & 7;
                  super.A = var145;
                  super.F = super.A;
                  int var146 = super.A | 64;
                  super.A = var146;
                  super.F = super.A;
                  this.wMem(22740, super.A, 36127);
                  int var147 = super.A + 1 & 255;
                  super.A = var147;
                  super.F = super.A;
                  int var148 = super.A & 7;
                  super.A = var148;
                  super.F = super.A;
                  int var149 = super.A | 64;
                  super.A = var149;
                  super.F = super.A;
                  this.wMem(22741, super.A, 36135);
                  int var150 = super.C - 1 & 255;
                  super.C = var150;
                  if (super.C == 0) {
                    int var151 = super.D - 1 & 255;
                    super.D = var151;
                    if (super.D == 0) {
                      continue label283;
                    }
                  }
                }
              }
            }

            int var152 = this.HL();
            int var153 = this.mem(var152, 35899) - 1 & 255;
            int var154 = this.HL();
            this.wMem(var154, var153, 35899);
            this.HL(34263);
            this.DE(34255);
            this.BC(7);
            this.ldir();
            break;
          }

          super.B = 191;
          this.HL(34274);
          int var155 = this.BC();
          int var156 = this.in(var155);
          super.A = var156;
          int var157 = super.A & 31;
          super.A = var157;
          super.F = super.A;
          if (super.A != 31) {
            int var234 = this.HL();
            if ((this.mem(var234, 35630) & 1) == 0) {
              int var235 = this.HL();
              int var236 = this.mem(var235, 35632);
              super.A = var236;
              int var237 = super.A ^ 3;
              super.A = var237;
              super.F = super.A;
              int var238 = this.HL();
              this.wMem(var238, super.A, 35635);
            }
          } else {
            int var158 = this.HL();
            int var159 = this.mem(var158, 35638) & -2;
            int var160 = this.HL();
            this.wMem(var160, var159, 35638);
          }

          int var161 = this.HL();
          if ((this.mem(var161, 35642) & 2) == 0) {
            int var209 = super.A ^ super.A;
            super.A = var209;
            super.F = super.A;
            this.wMem(34272, super.A, 35645);
            int var210 = this.mem(34273, 35648);
            super.A = var210;
            int var211 = super.A + 1 & 255;
            super.A = var211;
            super.F = super.A;
            this.wMem(34273, super.A, 35652);
            int var212 = super.A & 126;
            super.A = var212;
            super.F = super.A;
            int var213 = super.A;
            int var214 = this.rrc(var213);
            super.A = var214;
            super.E = super.A;
            super.D = 0;
            this.HL(34399);
            int var215 = this.HL();
            int var216 = this.DE();
            int var217 = var215 + var216 & '\uffff';
            this.HL(var217);
            int var218 = this.mem(34252, 35665);
            super.A = var218;
            int var219 = super.A;
            int var220 = this.rlc(var219);
            super.A = var220;
            int var221 = super.A;
            int var222 = this.rlc(var221);
            super.A = var222;
            int var223 = super.A - 28 & 255;
            super.A = var223;
            super.F = super.A;
            int var224 = -super.A & 255;
            super.A = var224;
            int var225 = this.HL();
            int var226 = this.mem(var225, 35674);
            int var227 = super.A + var226 & 255;
            super.A = var227;
            int var228 = this.HL();
            this.mem(var228, 35674);
            super.F = super.A;
            super.D = super.A;
            int var229 = this.mem(32990, 35676);
            super.A = var229;
            super.E = super.D;
            this.BC(3);

            while(true) {
              int var230 = super.E - 1 & 255;
              super.E = var230;
              if (super.E == 0) {
                super.E = super.D;
                int var233 = super.A ^ 24;
                super.A = var233;
              }

              int var231 = super.B - 1 & 255;
              super.B = var231;
              if (super.B == 0) {
                int var232 = super.C - 1 & 255;
                super.C = var232;
                if (super.C == 0) {
                  break;
                }
              }
            }
          }

          this.BC(61438);
          int var162 = this.BC();
          int var163 = this.in(var162);
          super.A = var163;
          if ((super.A & 2) == 0) {
            int var199 = super.A & 16;
            super.A = var199;
            super.F = super.A;
            int var200 = super.A ^ 16;
            super.A = var200;
            super.F = super.A;
            int var201 = super.A;
            int var202 = this.rlc(var201);
            super.A = var202;
            super.D = super.A;
            int var203 = this.mem(34275, 35712);
            super.A = var203;
            if (super.A == 10) {
              this.BC(63486);
              int var204 = this.BC();
              int var205 = this.in(var204);
              super.A = var205;
              int var206 = ~super.A;
              super.A = var206;
              super.F = super.A;
              int var207 = super.A & 31;
              super.A = var207;
              super.F = super.A;
              int var208 = super.A | super.D;
              super.A = var208;
              super.F = super.A;
              this.wMem(33824, super.A, 35729);
              break;
            }
          }

          int var164 = this.mem(34275, 35735);
          super.A = var164;
          if (super.A != 10) {
            int var165 = this.mem(33824, 35743);
            super.A = var165;
            if (super.A == 28) {
              int var166 = this.mem(34255, 35751);
              super.A = var166;
              if (super.A == 208) {
                int var167 = this.mem(34275, 35759);
                super.A = var167;
                int var168 = super.A;
                int var169 = this.rlc(var168);
                super.A = var169;
                super.E = super.A;
                super.D = 0;
                this.IX(34279);
                int var170 = this.IX();
                int var171 = this.DE();
                int var172 = var170 + var171 & '\uffff';
                this.IX(var172);
                this.BC(64510);
                int var173 = this.BC();
                int var174 = this.in(var173);
                super.A = var174;
                int var175 = super.A & 31;
                super.A = var175;
                super.F = super.A;
                int var176 = this.IX();
                this.mem(var176, 35779);
                int var177 = this.IX();
                this.mem(var177, 35779);
                int var178 = this.IX();
                int var179 = this.mem(var178, 35782);
                if (super.A != var179) {
                  if (super.A != 31) {
                    int var194 = this.IX();
                    this.mem(var194, 35789);
                    int var195 = this.IX();
                    this.mem(var195, 35789);
                    int var196 = this.IX();
                    int var197 = this.mem(var196, 35792);
                    if (super.A != var197) {
                      int var198 = super.A ^ super.A;
                      super.A = var198;
                      super.F = super.A;
                      this.wMem(34275, super.A, 35796);
                    }
                  }
                } else {
                  super.B = 223;
                  int var180 = this.BC();
                  int var181 = this.in(var180);
                  super.A = var181;
                  int var182 = super.A & 31;
                  super.A = var182;
                  super.F = super.A;
                  int var183 = this.IX() + 1;
                  this.mem(var183, 35808);
                  int var184 = this.IX() + 1;
                  this.mem(var184, 35808);
                  int var185 = this.IX() + 1;
                  int var186 = this.mem(var185, 35811);
                  if (super.A != var186) {
                    if (super.A != 31) {
                      int var189 = this.IX();
                      this.mem(var189, 35818);
                      int var190 = this.IX();
                      this.mem(var190, 35818);
                      int var191 = this.IX();
                      int var192 = this.mem(var191, 35821);
                      if (super.A != var192) {
                        int var193 = super.A ^ super.A;
                        super.A = var193;
                        super.F = super.A;
                        this.wMem(34275, super.A, 35825);
                      }
                    }
                  } else {
                    int var187 = this.mem(34275, 35831);
                    super.A = var187;
                    int var188 = super.A + 1 & 255;
                    super.A = var188;
                    super.F = super.A;
                    this.wMem(34275, super.A, 35835);
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public void $35211() {
    int var1 = this.mem(34252, 35211);
    super.A = var1;
    this.HL(20640);
    int var2 = super.A | super.A;
    super.A = var2;
    if (super.A != 0) {
      super.B = super.A;

      do {
        super.C = 0;
        int var3 = this.HL();
        this.push(var3);
        int var4 = this.BC();
        this.push(var4);
        int var5 = this.mem(34273, 35224);
        super.A = var5;
        int var6 = super.A;
        int var7 = this.rlc(var6);
        super.A = var7;
        int var8 = super.A;
        int var9 = this.rlc(var8);
        super.A = var9;
        int var10 = super.A;
        int var11 = this.rlc(var10);
        super.A = var11;
        int var12 = super.A & 96;
        super.A = var12;
        super.F = super.A;
        super.E = super.A;
        super.D = 157;
        this.$37974();
        int var13 = this.pop();
        this.BC(var13);
        int var14 = this.pop();
        this.HL(var14);
        int var15 = this.HL() + 1 & '\uffff';
        this.HL(var15);
        int var16 = this.HL() + 1 & '\uffff';
        this.HL(var16);
        int var17 = super.B - 1 & 255;
        super.B = var17;
      } while(super.B != 0);

    }
  }

  public void $35563() {
    this.HL(22528);
    int var1 = this.HL();
    int var2 = this.mem(var1, 35566);
    super.A = var2;
    int var3 = super.A & 7;
    super.A = var3;
    super.F = super.A;

    do {
      int var4 = this.HL();
      int var5 = this.mem(var4, 35571);
      super.A = var5;
      int var6 = super.A + 3 & 255;
      super.A = var6;
      super.F = super.A;
      int var7 = super.A & 7;
      super.A = var7;
      super.F = super.A;
      super.D = super.A;
      int var8 = this.HL();
      int var9 = this.mem(var8, 35577);
      super.A = var9;
      int var10 = super.A + 24 & 255;
      super.A = var10;
      super.F = super.A;
      int var11 = super.A & 184;
      super.A = var11;
      super.F = super.A;
      int var12 = super.A | super.D;
      super.A = var12;
      super.F = super.A;
      int var13 = this.HL();
      this.wMem(var13, super.A, 35583);
      int var14 = this.HL() + 1 & '\uffff';
      this.HL(var14);
      super.A = super.H;
    } while(super.A != 91);

  }

  public void $36147() {
    this.$36203();
    this.IX(24064);
    super.A = 112;
    this.wMem(36189, super.A, 36156);
    this.$36171();
    this.IX(24320);
    super.A = 120;
    this.wMem(36189, super.A, 36168);
    this.$36171();
  }

  public void $36171() {
    super.C = 0;

    do {
      super.E = super.C;
      int var1 = this.IX();
      int var2 = this.mem(var1, 36174);
      super.A = var2;
      this.HL(32928);
      this.BC(54);
      this.cpir();
      super.C = super.E;
      super.B = 8;
      super.D = this.mem(36189, 36189);

      do {
        int var3 = this.HL();
        int var4 = this.mem(var3, 36190);
        super.A = var4;
        int var5 = this.DE();
        this.wMem(var5, super.A, 36191);
        int var6 = this.HL() + 1 & '\uffff';
        this.HL(var6);
        int var7 = super.D + 1 & 255;
        super.D = var7;
        int var8 = super.B - 1 & 255;
        super.B = var8;
      } while(super.B != 0);

      int var9 = this.IX() + 1 & '\uffff';
      this.IX(var9);
      int var10 = super.C + 1 & 255;
      super.C = var10;
    } while(super.C != 0);

  }

  public void $36203() {
    this.HL(32768);
    this.IX(24064);

    do {
      int var1 = this.HL();
      int var2 = this.mem(var1, 36210);
      super.A = var2;
      int var3 = super.A;
      int var4 = this.rlc(var3);
      super.A = var4;
      int var5 = super.A;
      int var6 = this.rlc(var5);
      super.A = var6;
      this.$36288();
      int var7 = this.HL();
      int var8 = this.mem(var7, 36216);
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
      int var18 = this.mem(var17, 36224);
      super.A = var18;
      int var19 = super.A;
      int var20 = this.rrc(var19);
      super.A = var20;
      int var21 = super.A;
      int var22 = this.rrc(var21);
      super.A = var22;
      this.$36288();
      int var23 = this.HL();
      int var24 = this.mem(var23, 36230);
      super.A = var24;
      this.$36288();
      int var25 = this.HL() + 1 & '\uffff';
      this.HL(var25);
      super.A = super.L;
      int var26 = super.A & 128;
      super.A = var26;
    } while(super.A == 0);

    int var27 = this.mem(32985, 36240);
    super.A = var27;
    int var28 = super.A | super.A;
    super.A = var28;
    if (super.A != 0) {
      int var44 = this.mem16(32983, 36246);
      this.HL(var44);
      super.B = super.A;
      int var45 = this.mem(32973, 36250);
      super.A = var45;

      do {
        int var46 = this.HL();
        this.wMem(var46, super.A, 36253);
        int var47 = this.HL() + 1 & '\uffff';
        this.HL(var47);
        int var48 = super.B - 1 & 255;
        super.B = var48;
      } while(super.B != 0);
    }

    int var29 = this.mem(32989, 36257);
    super.A = var29;
    int var30 = super.A | super.A;
    super.A = var30;
    if (super.A != 0) {
      int var31 = this.mem16(32987, 36262);
      this.HL(var31);
      int var32 = this.mem(32986, 36265);
      super.A = var32;
      int var33 = super.A & 1;
      super.A = var33;
      super.F = super.A;
      int var34 = super.A;
      int var35 = this.rlc(var34);
      super.A = var35;
      int var36 = super.A + 223 & 255;
      super.A = var36;
      super.F = super.A;
      super.E = super.A;
      super.D = 255;
      int var37 = this.mem(32989, 36276);
      super.A = var37;
      super.B = super.A;
      int var38 = this.mem(32964, 36280);
      super.A = var38;

      do {
        int var39 = this.HL();
        this.wMem(var39, super.A, 36283);
        int var40 = this.HL();
        int var41 = this.DE();
        int var42 = var40 + var41 & '\uffff';
        this.HL(var42);
        int var43 = super.B - 1 & 255;
        super.B = var43;
      } while(super.B != 0);

    }
  }

  public void $36288() {
    int var1 = super.A & 3;
    super.A = var1;
    super.F = super.A;
    super.C = super.A;
    int var2 = super.A;
    int var3 = this.rlc(var2);
    super.A = var3;
    int var4 = super.A;
    int var5 = this.rlc(var4);
    super.A = var5;
    int var6 = super.A;
    int var7 = this.rlc(var6);
    super.A = var7;
    int var8 = super.A + super.C & 255;
    super.A = var8;
    super.F = super.A;
    int var9 = super.A + 160 & 255;
    super.A = var9;
    super.F = super.A;
    super.E = super.A;
    super.D = 128;
    int var10 = this.DE();
    int var11 = this.mem(var10, 36300);
    super.A = var11;
    int var12 = this.IX();
    this.wMem(var12, super.A, 36301);
    int var13 = this.IX() + 1 & '\uffff';
    this.IX(var13);
  }

  public void $36307() {
    label221: {
      label261: {
        label224: {
          label217: {
            label238: {
              int var1 = this.mem(34262, 36307);
              super.A = var1;
              int var2 = super.A - 1 & 255;
              super.A = var2;
              super.F = super.A;
              if ((super.A & 128) != 0) {
                int var224 = this.mem(34257, 36316);
                super.A = var224;
                if (super.A == 1) {
                  int var273 = this.mem(34261, 36323);
                  super.A = var273;
                  int var274 = super.A & 254;
                  super.A = var274;
                  super.F = super.A;
                  int var275 = super.A - 8 & 255;
                  super.A = var275;
                  super.F = super.A;
                  this.HL(34255);
                  int var276 = this.HL();
                  int var277 = this.mem(var276, 36333);
                  int var278 = super.A + var277 & 255;
                  super.A = var278;
                  int var279 = this.HL();
                  this.mem(var279, 36333);
                  super.F = super.A;
                  int var280 = this.HL();
                  this.wMem(var280, super.A, 36334);
                  if (super.A >= 240) {
                    return;
                  }

                  this.$36508();
                  int var281 = this.mem(32946, 36343);
                  super.A = var281;
                  int var282 = this.HL();
                  this.mem(var282, 36346);
                  int var283 = this.HL();
                  this.mem(var283, 36346);
                  int var284 = this.HL();
                  int var285 = this.mem(var284, 36347);
                  if (super.A == var285) {
                    break label261;
                  }

                  int var292 = this.HL() + 1 & '\uffff';
                  this.HL(var292);
                  int var293 = this.HL();
                  this.mem(var293, 36351);
                  int var294 = this.HL();
                  this.mem(var294, 36351);
                  int var295 = this.HL();
                  int var296 = this.mem(var295, 36352);
                  if (super.A == var296) {
                    break label261;
                  }

                  int var297 = this.mem(34261, 36355);
                  super.A = var297;
                  int var298 = super.A + 1 & 255;
                  super.A = var298;
                  super.F = super.A;
                  this.wMem(34261, super.A, 36359);
                  int var299 = super.A - 8 & 255;
                  super.A = var299;
                  if (super.A < 0) {
                    int var312 = -super.A & 255;
                    super.A = var312;
                  }

                  int var300 = super.A + 1 & 255;
                  super.A = var300;
                  super.F = super.A;
                  int var301 = super.A;
                  int var302 = this.rlc(var301);
                  super.A = var302;
                  int var303 = super.A;
                  int var304 = this.rlc(var303);
                  super.A = var304;
                  int var305 = super.A;
                  int var306 = this.rlc(var305);
                  super.A = var306;
                  super.D = super.A;
                  super.C = 32;
                  int var307 = this.mem(32990, 36376);
                  super.A = var307;

                  do {
                    int var308 = super.A ^ 24;
                    super.A = var308;
                    super.F = super.A;
                    super.B = super.D;

                    do {
                      int var309 = super.B - 1 & 255;
                      super.B = var309;
                    } while(super.B != 0);

                    int var310 = super.C - 1 & 255;
                    super.C = var310;
                  } while(super.C != 0);

                  int var311 = this.mem(34261, 36389);
                  super.A = var311;
                  if (super.A == 18) {
                    break label221;
                  }

                  if (super.A != 16 && super.A != 13) {
                    break label217;
                  }
                }

                int var225 = this.mem(34255, 36406);
                super.A = var225;
                int var226 = super.A & 14;
                super.A = var226;
                if (super.A != 0) {
                  break label238;
                }

                int var248 = this.mem16(34259, 36413);
                this.HL(var248);
                this.DE(64);
                int var249 = this.HL();
                int var250 = this.DE();
                int var251 = var249 + var250 & '\uffff';
                this.HL(var251);
                if ((super.H & 2) != 0) {
                  break label224;
                }

                int var252 = this.mem(32955, 36425);
                super.A = var252;
                int var253 = this.HL();
                this.mem(var253, 36428);
                int var254 = this.HL();
                this.mem(var254, 36428);
                int var255 = this.HL();
                int var256 = this.mem(var255, 36429);
                if (super.A == var256) {
                  break label238;
                }

                int var257 = this.HL() + 1 & '\uffff';
                this.HL(var257);
                int var258 = this.mem(32955, 36432);
                super.A = var258;
                int var259 = this.HL();
                this.mem(var259, 36435);
                int var260 = this.HL();
                this.mem(var260, 36435);
                int var261 = this.HL();
                int var262 = this.mem(var261, 36436);
                if (super.A == var262) {
                  break label238;
                }

                int var263 = this.mem(32928, 36438);
                super.A = var263;
                int var264 = this.HL();
                int var265 = this.mem(var264, 36441);
                int var266 = this.HL();
                this.mem(var266, 36441);
                int var267 = super.A - var265;
                super.F = var267;
                int var268 = this.HL() - 1 & '\uffff';
                this.HL(var268);
                if (super.F == 0) {
                  int var269 = this.HL();
                  this.mem(var269, 36446);
                  int var270 = this.HL();
                  this.mem(var270, 36446);
                  int var271 = this.HL();
                  int var272 = this.mem(var271, 36447);
                  if (super.A == var272) {
                    break label238;
                  }
                }
              }

              super.E = 255;
              int var3 = this.mem(34262, 36566);
              super.A = var3;
              int var4 = super.A - 1 & 255;
              super.A = var4;
              super.F = super.A;
              if ((super.A & 128) != 0) {
                label237: {
                  int var210 = this.mem(34257, 36574);
                  super.A = var210;
                  if (super.A >= 12) {
                    this.incPops();
                    return;
                  }

                  int var211 = super.A ^ super.A;
                  super.A = var211;
                  super.F = super.A;
                  this.wMem(34257, super.A, 36583);
                  int var212 = this.mem(32973, 36586);
                  super.A = var212;
                  int var213 = this.HL();
                  this.mem(var213, 36589);
                  int var214 = this.HL();
                  this.mem(var214, 36589);
                  int var215 = this.HL();
                  int var216 = this.mem(var215, 36590);
                  if (super.A != var216) {
                    int var219 = this.HL() + 1 & '\uffff';
                    this.HL(var219);
                    int var220 = this.HL();
                    this.mem(var220, 36593);
                    int var221 = this.HL();
                    this.mem(var221, 36593);
                    int var222 = this.HL();
                    int var223 = this.mem(var222, 36594);
                    if (super.A != var223) {
                      break label237;
                    }
                  }

                  int var217 = this.mem(32982, 36596);
                  super.A = var217;
                  int var218 = super.A - 3 & 255;
                  super.A = var218;
                  super.F = super.A;
                  super.E = super.A;
                }
              }

              this.BC(57342);
              int var5 = this.BC();
              int var6 = this.in(var5);
              super.A = var6;
              int var7 = super.A & 31;
              super.A = var7;
              super.F = super.A;
              int var8 = super.A | 32;
              super.A = var8;
              super.F = super.A;
              int var9 = super.A & super.E;
              super.A = var9;
              super.F = super.A;
              super.E = super.A;
              int var10 = this.mem(34271, 36613);
              super.A = var10;
              int var11 = super.A & 2;
              super.A = var11;
              super.F = super.A;
              int var12 = super.A;
              int var13 = this.rrc(var12);
              super.A = var13;
              int var14 = super.A ^ super.E;
              super.A = var14;
              super.F = super.A;
              super.E = super.A;
              this.BC(64510);
              int var15 = this.BC();
              int var16 = this.in(var15);
              super.A = var16;
              int var17 = super.A & 31;
              super.A = var17;
              super.F = super.A;
              int var18 = super.A;
              int var19 = this.rlc(var18);
              super.A = var19;
              int var20 = super.A | 1;
              super.A = var20;
              super.F = super.A;
              int var21 = super.A & super.E;
              super.A = var21;
              super.F = super.A;
              super.E = super.A;
              super.B = 231;
              int var22 = this.BC();
              int var23 = this.in(var22);
              super.A = var23;
              int var24 = super.A;
              int var25 = this.rrc(var24);
              super.A = var25;
              int var26 = super.A | 247;
              super.A = var26;
              super.F = super.A;
              int var27 = super.A & super.E;
              super.A = var27;
              super.F = super.A;
              super.E = super.A;
              super.B = 239;
              int var28 = this.BC();
              int var29 = this.in(var28);
              super.A = var29;
              int var30 = super.A | 251;
              super.A = var30;
              super.F = super.A;
              int var31 = super.A & super.E;
              super.A = var31;
              super.F = super.A;
              super.E = super.A;
              int var32 = this.BC();
              int var33 = this.in(var32);
              super.A = var33;
              int var34 = super.A;
              int var35 = this.rrc(var34);
              super.A = var35;
              int var36 = super.A | 251;
              super.A = var36;
              super.F = super.A;
              int var37 = super.A & super.E;
              super.A = var37;
              super.F = super.A;
              super.E = super.A;
              int var38 = this.mem(34254, 36658);
              super.A = var38;
              int var39 = super.A | super.A;
              super.A = var39;
              if (super.A != 0) {
                this.BC(31);
                int var205 = this.BC();
                int var206 = this.in(var205);
                super.A = var206;
                int var207 = super.A & 3;
                super.A = var207;
                super.F = super.A;
                int var208 = ~super.A;
                super.A = var208;
                super.F = super.A;
                int var209 = super.A & super.E;
                super.A = var209;
                super.F = super.A;
                super.E = super.A;
              }

              super.C = 0;
              super.A = super.E;
              int var40 = super.A & 42;
              super.A = var40;
              super.F = super.A;
              if (super.A != 42) {
                super.C = 4;
                int var204 = super.A ^ super.A;
                super.A = var204;
                super.F = super.A;
                this.wMem(34272, super.A, 36686);
              }

              super.A = super.E;
              int var41 = super.A & 21;
              super.A = var41;
              super.F = super.A;
              if (super.A != 21) {
                int var202 = super.C | 8;
                super.C = var202;
                int var203 = super.A ^ super.A;
                super.A = var203;
                super.F = super.A;
                this.wMem(34272, super.A, 36699);
              }

              int var42 = this.mem(34256, 36702);
              super.A = var42;
              int var43 = super.A + super.C & 255;
              super.A = var43;
              super.F = super.A;
              super.C = super.A;
              super.B = 0;
              this.HL(33825);
              int var44 = this.HL();
              int var45 = this.BC();
              int var46 = var44 + var45 & '\uffff';
              this.HL(var46);
              int var47 = this.HL();
              int var48 = this.mem(var47, 36713);
              super.A = var48;
              this.wMem(34256, super.A, 36714);
              this.BC(32510);
              int var49 = this.BC();
              int var50 = this.in(var49);
              super.A = var50;
              int var51 = super.A & 31;
              super.A = var51;
              super.F = super.A;
              if (super.A == 31) {
                super.B = 239;
                int var196 = this.BC();
                int var197 = this.in(var196);
                super.A = var197;
                if ((super.A & 1) != 0) {
                  int var198 = this.mem(34254, 36736);
                  super.A = var198;
                  int var199 = super.A | super.A;
                  super.A = var199;
                  if (super.A == 0) {
                    break label217;
                  }

                  this.BC(31);
                  int var200 = this.BC();
                  int var201 = this.in(var200);
                  super.A = var201;
                  if ((super.A & 16) == 0) {
                    break label217;
                  }
                }
              }

              int var52 = this.mem(34271, 36751);
              super.A = var52;
              if ((super.A & 2) == 0) {
                int var187 = super.A ^ super.A;
                super.A = var187;
                super.F = super.A;
                this.wMem(34261, super.A, 36759);
                this.wMem(34272, super.A, 36762);
                int var188 = super.A + 1 & 255;
                super.A = var188;
                super.F = super.A;
                this.wMem(34257, super.A, 36766);
                int var189 = this.mem(34262, 36769);
                super.A = var189;
                int var190 = super.A - 1 & 255;
                super.A = var190;
                super.F = super.A;
                if ((super.A & 128) == 0) {
                  super.A = 240;
                  this.wMem(34262, super.A, 36779);
                  int var191 = this.mem(34255, 36782);
                  super.A = var191;
                  int var192 = super.A & 240;
                  super.A = var192;
                  super.F = super.A;
                  this.wMem(34255, super.A, 36787);
                  this.HL(34256);
                  int var193 = this.HL();
                  int var194 = this.mem(var193, 36793) | 2;
                  int var195 = this.HL();
                  this.wMem(var195, var194, 36793);
                  return;
                }
              }
              break label217;
            }

            int var227 = this.mem(34257, 36450);
            super.A = var227;
            if (super.A != 1) {
              this.HL(34256);
              int var228 = this.HL();
              int var229 = this.mem(var228, 36461) & -3;
              int var230 = this.HL();
              this.wMem(var230, var229, 36461);
              int var231 = this.mem(34257, 36463);
              super.A = var231;
              int var232 = super.A | super.A;
              super.A = var232;
              if (super.A == 0) {
                super.A = 2;
                this.wMem(34257, super.A, 36536);
                return;
              }

              int var233 = super.A + 1 & 255;
              super.A = var233;
              super.F = super.A;
              if (super.A == 16) {
                super.A = 12;
              }

              this.wMem(34257, super.A, 36477);
              int var234 = super.A;
              int var235 = this.rlc(var234);
              super.A = var235;
              int var236 = super.A;
              int var237 = this.rlc(var236);
              super.A = var237;
              int var238 = super.A;
              int var239 = this.rlc(var238);
              super.A = var239;
              int var240 = super.A;
              int var241 = this.rlc(var240);
              super.A = var241;
              super.D = super.A;
              super.C = 32;
              int var242 = this.mem(32990, 36487);
              super.A = var242;

              do {
                int var243 = super.A ^ 24;
                super.A = var243;
                super.F = super.A;
                super.B = super.D;

                do {
                  int var244 = super.B - 1 & 255;
                  super.B = var244;
                } while(super.B != 0);

                int var245 = super.C - 1 & 255;
                super.C = var245;
              } while(super.C != 0);

              int var246 = this.mem(34255, 36500);
              super.A = var246;
              int var247 = super.A + 8 & 255;
              super.A = var247;
              super.F = super.A;
              this.wMem(34255, super.A, 36505);
              this.$36508();
              break label221;
            }
          }

          int var53 = this.mem(34256, 36796);
          super.A = var53;
          int var54 = super.A & 2;
          super.A = var54;
          if (super.A == 0) {
            return;
          }

          int var55 = this.mem(34262, 36802);
          super.A = var55;
          int var56 = super.A - 1 & 255;
          super.A = var56;
          super.F = super.A;
          if ((super.A & 128) == 0) {
            return;
          }

          int var57 = this.mem(34256, 36809);
          super.A = var57;
          int var58 = super.A & 1;
          super.A = var58;
          if (super.A != 0) {
            int var129 = this.mem(34258, 36817);
            super.A = var129;
            int var130 = super.A | super.A;
            super.A = var130;
            if (super.A != 0) {
              int var186 = super.A - 1 & 255;
              super.A = var186;
              super.F = super.A;
              this.wMem(34258, super.A, 36824);
              return;
            }

            int var131 = this.mem(34257, 36828);
            super.A = var131;
            this.BC(0);
            if (super.A == 0) {
              int var171 = this.mem16(34259, 36838);
              this.HL(var171);
              this.BC(0);
              int var172 = this.mem(32986, 36844);
              super.A = var172;
              int var173 = super.A - 1 & 255;
              super.A = var173;
              super.F = super.A;
              int var174 = super.A | 161;
              super.A = var174;
              super.F = super.A;
              int var175 = super.A ^ 224;
              super.A = var175;
              super.F = super.A;
              super.E = super.A;
              super.D = 0;
              int var176 = this.HL();
              int var177 = this.DE();
              int var178 = var176 + var177 & '\uffff';
              this.HL(var178);
              int var179 = this.mem(32964, 36856);
              super.A = var179;
              int var180 = this.HL();
              this.mem(var180, 36859);
              int var181 = this.HL();
              this.mem(var181, 36859);
              int var182 = this.HL();
              int var183 = this.mem(var182, 36860);
              if (super.A == var183) {
                this.BC(32);
                int var184 = this.mem(32986, 36865);
                super.A = var184;
                int var185 = super.A | super.A;
                super.A = var185;
                if (super.A == 0) {
                  this.BC(65504);
                }
              }
            }

            int var132 = this.mem16(34259, 36874);
            this.HL(var132);
            super.A = super.L;
            int var133 = super.A & 31;
            super.A = var133;
            if (super.A != 0) {
              int var138 = this.HL();
              int var139 = this.BC();
              int var140 = var138 + var139 & '\uffff';
              this.HL(var140);
              int var141 = this.HL() - 1 & '\uffff';
              this.HL(var141);
              this.DE(32);
              int var142 = this.HL();
              int var143 = this.DE();
              int var144 = var142 + var143 & '\uffff';
              this.HL(var144);
              int var145 = this.mem(32946, 36889);
              super.A = var145;
              int var146 = this.HL();
              this.mem(var146, 36892);
              int var147 = this.HL();
              this.mem(var147, 36892);
              int var148 = this.HL();
              int var149 = this.mem(var148, 36893);
              if (super.A == var149) {
                return;
              }

              int var150 = this.mem(34255, 36894);
              super.A = var150;
              int var151 = super.C >> 1;
              super.C = var151;
              int var152 = super.A + super.C & 255;
              super.A = var152;
              super.F = super.A;
              super.B = super.A;
              int var153 = super.A & 15;
              super.A = var153;
              if (super.A != 0) {
                int var159 = this.mem(32946, 36905);
                super.A = var159;
                int var160 = this.HL();
                int var161 = this.DE();
                int var162 = var160 + var161 & '\uffff';
                this.HL(var162);
                int var163 = this.HL();
                this.mem(var163, 36909);
                int var164 = this.HL();
                this.mem(var164, 36909);
                int var165 = this.HL();
                int var166 = this.mem(var165, 36910);
                if (super.A == var166) {
                  return;
                }

                int var167 = super.A | super.A;
                super.A = var167;
                super.F = super.A;
                carry =0;
                int var168 = this.HL();
                int var169 = this.DE();
                int var170 = ((var168 - var169)+ carry) & '\uffff';
                this.HL(var170);
              }

              int var154 = super.A | super.A;
              super.A = var154;
              super.F = super.A;
              carry =0;
              int var155 = this.HL();
              int var156 = this.DE();
              int var157 = ((var155 - var156) + carry)& '\uffff';
              this.HL(var157);
              int var158 = this.HL();
              this.wMem16(34259, var158, 36917);
              super.A = super.B;
              this.wMem(34255, super.A, 36921);
              super.A = 3;
              this.wMem(34258, super.A, 36926);
              return;
            }

            int var134 = this.mem(33001, 38026);
            super.A = var134;
            this.wMem(33824, super.A, 38029);
            int var135 = this.mem(34259, 38032);
            super.A = var135;
            int var136 = super.A | 31;
            super.A = var136;
            super.F = super.A;
            int var137 = super.A & 254;
            super.A = var137;
            super.F = super.A;
            this.wMem(34259, super.A, 38039);
            incPops();
            return;
          } else {
            int var59 = this.mem(34258, 36930);
            super.A = var59;
            if (super.A != 3) {
              int var128 = super.A + 1 & 255;
              super.A = var128;
              super.F = super.A;
              this.wMem(34258, super.A, 36938);
              return;
            }

            int var60 = this.mem(34257, 36942);
            super.A = var60;
            this.BC(0);
            int var61 = super.A | super.A;
            super.A = var61;
            if (super.A == 0) {
              int var113 = this.mem16(34259, 36951);
              this.HL(var113);
              int var114 = this.mem(32986, 36954);
              super.A = var114;
              int var115 = super.A - 1 & 255;
              super.A = var115;
              super.F = super.A;
              int var116 = super.A | 157;
              super.A = var116;
              super.F = super.A;
              int var117 = super.A ^ 191;
              super.A = var117;
              super.F = super.A;
              super.E = super.A;
              super.D = 0;
              int var118 = this.HL();
              int var119 = this.DE();
              int var120 = var118 + var119 & '\uffff';
              this.HL(var120);
              int var121 = this.mem(32964, 36966);
              super.A = var121;
              int var122 = this.HL();
              this.mem(var122, 36969);
              int var123 = this.HL();
              this.mem(var123, 36969);
              int var124 = this.HL();
              int var125 = this.mem(var124, 36970);
              if (super.A == var125) {
                this.BC(32);
                int var126 = this.mem(32986, 36975);
                super.A = var126;
                int var127 = super.A | super.A;
                super.A = var127;
                if (super.A != 0) {
                  this.BC(65504);
                }
              }
            }

            int var62 = this.mem16(34259, 36984);
            this.HL(var62);
            int var63 = this.HL();
            int var64 = this.BC();
            int var65 = var63 + var64 & '\uffff';
            this.HL(var65);
            int var66 = this.HL() + 1 & '\uffff';
            this.HL(var66);
            int var67 = this.HL() + 1 & '\uffff';
            this.HL(var67);
            super.A = super.L;
            int var68 = super.A & 31;
            super.A = var68;
            if (super.A != 0) {
              this.DE(32);
              int var77 = this.mem(32946, 36999);
              super.A = var77;
              int var78 = this.HL();
              int var79 = this.DE();
              int var80 = var78 + var79 & '\uffff';
              this.HL(var80);
              int var81 = this.HL();
              this.mem(var81, 37003);
              int var82 = this.HL();
              this.mem(var82, 37003);
              int var83 = this.HL();
              int var84 = this.mem(var83, 37004);
              if (super.A == var84) {
                return;
              }

              int var85 = this.mem(34255, 37005);
              super.A = var85;
              int var86 = super.C >> 1;
              super.C = var86;
              int var87 = super.A + super.C & 255;
              super.A = var87;
              super.F = super.A;
              super.B = super.A;
              int var88 = super.A & 15;
              super.A = var88;
              if (super.A != 0) {
                int var101 = this.mem(32946, 37016);
                super.A = var101;
                int var102 = this.HL();
                int var103 = this.DE();
                int var104 = var102 + var103 & '\uffff';
                this.HL(var104);
                int var105 = this.HL();
                this.mem(var105, 37020);
                int var106 = this.HL();
                this.mem(var106, 37020);
                int var107 = this.HL();
                int var108 = this.mem(var107, 37021);
                if (super.A == var108) {
                  return;
                }

                int var109 = super.A | super.A;
                super.A = var109;
                super.F = super.A;
                carry =0;
                int var110 = this.HL();
                int var111 = this.DE();
                int var112 = ((var110 - var111) + carry) & '\uffff';
                this.HL(var112);
              }

              int var89 = this.mem(32946, 37025);
              super.A = var89;
              int var90 = super.A | super.A;
              super.A = var90;
              super.F = super.A;
              carry =0;
              int var91 = this.HL();
              int var92 = this.DE();
              int var93 = ((var91 - var92) +carry) & '\uffff';
              this.HL(var93);
              int var94 = this.HL();
              this.mem(var94, 37031);
              int var95 = this.HL();
              this.mem(var95, 37031);
              int var96 = this.HL();
              int var97 = this.mem(var96, 37032);
              if (super.A == var97) {
                return;
              }

              int var98 = this.HL() - 1 & '\uffff';
              this.HL(var98);
              int var99 = this.HL();
              this.wMem16(34259, var99, 37034);
              int var100 = super.A ^ super.A;
              super.A = var100;
              super.F = super.A;
              this.wMem(34258, super.A, 37038);
              super.A = super.B;
              this.wMem(34255, super.A, 37042);
              return;
            }
          }

          int var69 = this.mem(33002, 38046);
          super.A = var69;
          this.wMem(33824, super.A, 38049);
          int var70 = this.mem(34259, 38052);
          super.A = var70;
          int var71 = super.A & 224;
          super.A = var71;
          super.F = super.A;
          this.wMem(34259, super.A, 38057);
          incPops();
          return;
//          this.$38064();
        }

        int var72 = this.mem(33004, 38098);
        super.A = var72;
        this.wMem(33824, super.A, 38101);
        int var73 = super.A ^ super.A;
        super.A = var73;
        super.F = super.A;
        this.wMem(34255, super.A, 38105);
        int var74 = this.mem(34257, 38108);
        super.A = var74;
        if (super.A < 11) {
          super.A = 2;
          this.wMem(34257, super.A, 38117);
        }

        int var75 = this.mem(34259, 38120);
        super.A = var75;
        int var76 = super.A & 31;
        super.A = var76;
        super.F = super.A;
        this.wMem(34259, super.A, 38125);
        super.A = 92;
        this.wMem(34260, super.A, 38130);
        incPops();
        return;
      }

      int var286 = this.mem(34255, 36540);
      super.A = var286;
      int var287 = super.A + 16 & 255;
      super.A = var287;
      super.F = super.A;
      int var288 = super.A & 240;
      super.A = var288;
      super.F = super.A;
      this.wMem(34255, super.A, 36547);
      this.$36508();
      super.A = 2;
      this.wMem(34257, super.A, 36555);
      this.HL(34256);
      int var289 = this.HL();
      int var290 = this.mem(var289, 36561) & -3;
      int var291 = this.HL();
      this.wMem(var291, var290, 36561);
      return;
    }

    super.A = 6;
    this.wMem(34257, super.A, 36530);
  }

  public void $36508() {
    int var1 = super.A & 240;
    super.A = var1;
    super.F = super.A;
    super.L = super.A;
    int var2 = super.A ^ super.A;
    super.A = var2;
    super.F = super.A;
    carry= 0;
    int var3 = super.L;
    int var4 = this.rl(var3);
    super.L = var4;
    int var5 =  (A + 92 + carry) & 255;
    super.A = var5;
    super.F = super.A;
    super.H = super.A;
    int var6 = this.mem(34259, 36517);
    super.A = var6;
    int var7 = super.A & 31;
    super.A = var7;
    super.F = super.A;
    int var8 = super.A | super.L;
    super.A = var8;
    super.F = super.A;
    super.L = super.A;
    int var9 = this.HL();
    this.wMem16(34259, var9, 36524);
  }

  public void $37056() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1, 37060);
      super.A = var2;
      if (super.A == 255) {
        return;
      }

      int var3 = super.A & 3;
      super.A = var3;
      if (super.A != 0) {
        if (super.A != 1) {
          if (super.A != 2) {
            int var70 = this.IX();
            if ((this.mem(var70, 37085) & 128) != 0) {
              int var88 = this.IX() + 1;
              int var89 = this.mem(var88, 37087);
              super.A = var89;
              if ((super.A & 128) != 0) {
                int var92 = super.A - 2 & 255;
                super.A = var92;
                super.F = super.A;
                if (super.A < 148) {
                  int var93 = super.A - 2 & 255;
                  super.A = var93;
                  super.F = super.A;
                  if (super.A == 128) {
                    int var94 = super.A ^ super.A;
                    super.A = var94;
                  }
                }
              } else {
                int var90 = super.A + 2 & 255;
                super.A = var90;
                super.F = super.A;
                if (super.A < 18) {
                  int var91 = super.A + 2 & 255;
                  super.A = var91;
                }
              }
            } else {
              int var71 = this.IX() + 1;
              int var72 = this.mem(var71, 37119);
              super.A = var72;
              if ((super.A & 128) == 0) {
                int var85 = super.A - 2 & 255;
                super.A = var85;
                super.F = super.A;
                if (super.A < 20) {
                  int var86 = super.A - 2 & 255;
                  super.A = var86;
                  super.F = super.A;
                  int var87 = super.A | super.A;
                  super.A = var87;
                  if (super.A == 0) {
                    super.A = 128;
                  }
                }
              } else {
                int var73 = super.A + 2 & 255;
                super.A = var73;
                super.F = super.A;
                if (super.A < 146) {
                  int var84 = super.A + 2 & 255;
                  super.A = var84;
                  super.F = super.A;
                }
              }
            }

            int var74 = this.IX() + 1;
            this.wMem(var74, super.A, 37149);
            int var75 = super.A & 127;
            super.A = var75;
            super.F = super.A;
            int var76 = this.IX() + 7;
            this.mem(var76, 37154);
            int var77 = this.IX() + 7;
            this.mem(var77, 37154);
            int var78 = this.IX() + 7;
            int var79 = this.mem(var78, 37157);
            if (super.A == var79) {
              int var80 = this.IX();
              int var81 = this.mem(var80, 37160);
              super.A = var81;
              int var82 = super.A ^ 128;
              super.A = var82;
              super.F = super.A;
              int var83 = this.IX();
              this.wMem(var83, super.A, 37165);
            }
          } else {
            label81: {
              int var39 = this.IX();
              int var40 = this.mem(var39, 37247);
              super.A = var40;
              int var41 = super.A ^ 8;
              super.A = var41;
              super.F = super.A;
              int var42 = this.IX();
              this.wMem(var42, super.A, 37252);
              int var43 = super.A & 24;
              super.A = var43;
              if (super.A != 0) {
                int var66 = this.IX();
                int var67 = this.mem(var66, 37259);
                super.A = var67;
                int var68 = super.A + 32 & 255;
                super.A = var68;
                super.F = super.A;
                int var69 = this.IX();
                this.wMem(var69, super.A, 37264);
              }

              int var44 = this.IX() + 3;
              int var45 = this.mem(var44, 37267);
              super.A = var45;
              int var46 = this.IX() + 4;
              int var47 = this.mem(var46, 37270);
              int var48 = super.A + var47 & 255;
              super.A = var48;
              int var49 = this.IX() + 4;
              this.mem(var49, 37270);
              super.F = super.A;
              int var50 = this.IX() + 3;
              this.wMem(var50, super.A, 37273);
              int var51 = this.IX() + 7;
              this.mem(var51, 37276);
              int var52 = this.IX() + 7;
              this.mem(var52, 37276);
              int var53 = this.IX() + 7;
              int var54 = this.mem(var53, 37279);
              if (super.A < var54) {
                int var59 = this.IX() + 6;
                this.mem(var59, 37281);
                int var60 = this.IX() + 6;
                this.mem(var60, 37281);
                int var61 = this.IX() + 6;
                int var62 = this.mem(var61, 37284);
                if (super.A != var62 && super.F >= 0) {
                  break label81;
                }

                int var63 = this.IX() + 6;
                int var64 = this.mem(var63, 37288);
                super.A = var64;
                int var65 = this.IX() + 3;
                this.wMem(var65, super.A, 37291);
              }

              int var55 = this.IX() + 4;
              int var56 = this.mem(var55, 37294);
              super.A = var56;
              int var57 = -super.A & 255;
              super.A = var57;
              int var58 = this.IX() + 4;
              this.wMem(var58, super.A, 37299);
            }
          }
        } else {
          int var7 = this.IX();
          if ((this.mem(var7, 37175) & 128) == 0) {
            int var24 = this.IX();
            int var25 = this.mem(var24, 37177);
            super.A = var25;
            int var26 = super.A - 32 & 255;
            super.A = var26;
            super.F = super.A;
            int var27 = super.A & 127;
            super.A = var27;
            super.F = super.A;
            int var28 = this.IX();
            this.wMem(var28, super.A, 37184);
            if (super.A >= 96) {
              int var29 = this.IX() + 2;
              int var30 = this.mem(var29, 37191);
              super.A = var30;
              int var31 = super.A & 31;
              super.A = var31;
              super.F = super.A;
              int var32 = this.IX() + 6;
              this.mem(var32, 37196);
              int var33 = this.IX() + 6;
              this.mem(var33, 37196);
              int var34 = this.IX() + 6;
              int var35 = this.mem(var34, 37199);
              if (super.A != var35) {
                int var37 = this.IX() + 2;
                int var38 = this.mem(var37, 37201) - 1 & 255;
                this.wMem(var37, var38, 37201);
              } else {
                int var36 = this.IX();
                this.wMem(var36, 129, 37206);
              }
            }
          } else {
            int var8 = this.IX();
            int var9 = this.mem(var8, 37212);
            super.A = var9;
            int var10 = super.A + 32 & 255;
            super.A = var10;
            super.F = super.A;
            int var11 = super.A | 128;
            super.A = var11;
            super.F = super.A;
            int var12 = this.IX();
            this.wMem(var12, super.A, 37219);
            if (super.A < 160) {
              int var13 = this.IX() + 2;
              int var14 = this.mem(var13, 37226);
              super.A = var14;
              int var15 = super.A & 31;
              super.A = var15;
              super.F = super.A;
              int var16 = this.IX() + 7;
              this.mem(var16, 37231);
              int var17 = this.IX() + 7;
              this.mem(var17, 37231);
              int var18 = this.IX() + 7;
              int var19 = this.mem(var18, 37234);
              if (super.A != var19) {
                int var21 = this.IX() + 2;
                int var22 = this.mem(var21, 37236) + 1;
                this.wMem(var21, var22, 37236);
                int var23 = var22 & 255;
                this.wMem(var21, var23, 37236);
              } else {
                int var20 = this.IX();
                this.wMem(var20, 97, 37241);
              }
            }
          }
        }
      }

      this.DE(8);
      int var4 = this.IX();
      int var5 = this.DE();
      int var6 = var4 + var5 & '\uffff';
      this.IX(var6);
    }
  }

  public void $37310() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1, 37314);
      super.A = var2;
      if (super.A == 255) {
        return;
      }

      int var3 = super.A & 7;
      super.A = var3;
      if (super.A != 0) {
        if (super.A != 3) {
          if (super.A != 4) {
            int var186 = this.IX() + 3;
            int var187 = this.mem(var186, 37334);
            super.E = var187;
            super.D = 130;
            int var188 = this.DE();
            int var189 = this.mem(var188, 37339);
            super.A = var189;
            super.L = super.A;
            int var190 = this.IX() + 2;
            int var191 = this.mem(var190, 37341);
            super.A = var191;
            int var192 = super.A & 31;
            super.A = var192;
            super.F = super.A;
            int var193 = super.A + super.L & 255;
            super.A = var193;
            super.F = super.A;
            super.L = super.A;
            super.A = super.E;
            int var194 = super.A;
            int var195 = this.rlc(var194);
            super.A = var195;
            int var196 = super.A & 1;
            super.A = var196;
            super.F = super.A;
            int var197 = super.A | 92;
            super.A = var197;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var198 = this.IX() + 1;
            int var199 = this.mem(var198, 37358);
            super.A = var199;
            int var200 = super.A & 15;
            super.A = var200;
            super.F = super.A;
            int var201 = super.A + 56 & 255;
            super.A = var201;
            super.F = super.A;
            int var202 = super.A & 71;
            super.A = var202;
            super.F = super.A;
            super.C = super.A;
            int var203 = this.HL();
            int var204 = this.mem(var203, 37368);
            super.A = var204;
            int var205 = super.A & 56;
            super.A = var205;
            super.F = super.A;
            int var206 = super.A ^ super.C;
            super.A = var206;
            super.F = super.A;
            super.C = super.A;
            int var207 = this.HL();
            this.wMem(var207, super.C, 37373);
            int var208 = this.HL() + 1 & '\uffff';
            this.HL(var208);
            int var209 = this.HL();
            this.wMem(var209, super.C, 37375);
            int var210 = this.HL();
            int var211 = this.DE();
            int var212 = var210 + var211 & '\uffff';
            this.HL(var212);
            int var213 = this.HL();
            this.wMem(var213, super.C, 37377);
            int var214 = this.HL() + 1 & '\uffff';
            this.HL(var214);
            int var215 = this.HL();
            this.wMem(var215, super.C, 37379);
            int var216 = this.IX() + 3;
            int var217 = this.mem(var216, 37380);
            super.A = var217;
            int var218 = super.A & 14;
            super.A = var218;
            if (super.A != 0) {
              int var244 = this.HL();
              int var245 = this.DE();
              int var246 = var244 + var245 & '\uffff';
              this.HL(var246);
              int var247 = this.HL();
              this.wMem(var247, super.C, 37388);
              int var248 = this.HL() + 1 & '\uffff';
              this.HL(var248);
              int var249 = this.HL();
              this.wMem(var249, super.C, 37390);
            }

            super.C = 1;
            int var219 = this.IX() + 1;
            int var220 = this.mem(var219, 37393);
            super.A = var220;
            int var221 = this.IX();
            int var222 = this.mem(var221, 37396);
            int var223 = super.A & var222;
            super.A = var223;
            int var224 = this.IX();
            this.mem(var224, 37396);
            super.F = super.A;
            int var225 = this.IX() + 2;
            int var226 = this.mem(var225, 37399);
            int var227 = super.A | var226;
            super.A = var227;
            int var228 = this.IX() + 2;
            this.mem(var228, 37399);
            super.F = super.A;
            int var229 = super.A & 224;
            super.A = var229;
            super.F = super.A;
            super.E = super.A;
            int var230 = this.IX() + 5;
            int var231 = this.mem(var230, 37405);
            super.D = var231;
            super.H = 130;
            int var232 = this.IX() + 3;
            int var233 = this.mem(var232, 37410);
            super.L = var233;
            int var234 = this.IX() + 2;
            int var235 = this.mem(var234, 37413);
            super.A = var235;
            int var236 = super.A & 31;
            super.A = var236;
            super.F = super.A;
            int var237 = this.HL();
            int var238 = this.mem(var237, 37418);
            int var239 = super.A | var238;
            super.A = var239;
            int var240 = this.HL();
            this.mem(var240, 37418);
            super.F = super.A;
            int var241 = this.HL() + 1 & '\uffff';
            this.HL(var241);
            int var242 = this.HL();
            int var243 = this.mem(var242, 37420);
            super.H = var243;
            super.L = super.A;
            this.$37974();
//            if (super.F != 0) {
//              return;
//            }

          } else {
            int var132 = this.IX();
            if ((this.mem(var132, 37435) & 128) == 0) {
              int var184 = this.IX() + 4;
              int var185 = this.mem(var184, 37437) - 1 & 255;
              this.wMem(var184, var185, 37437);
              super.C = 44;
            } else {
              int var133 = this.IX() + 4;
              int var134 = this.mem(var133, 37444) + 1;
              this.wMem(var133, var134, 37444);
              int var135 = var134 & 255;
              this.wMem(var133, var135, 37444);
              super.C = 244;
            }

            int var136 = this.IX() + 4;
            int var137 = this.mem(var136, 37449);
            super.A = var137;
            if (super.A != super.C) {
              int var138 = super.A & 224;
              super.A = var138;
              if (super.A == 0) {
                int var139 = this.IX() + 2;
                int var140 = this.mem(var139, 37479);
                super.E = var140;
                super.D = 130;
                int var141 = this.DE();
                int var142 = this.mem(var141, 37484);
                super.A = var142;
                int var143 = this.IX() + 4;
                int var144 = this.mem(var143, 37485);
                int var145 = super.A + var144 & 255;
                super.A = var145;
                int var146 = this.IX() + 4;
                this.mem(var146, 37485);
                super.F = super.A;
                super.L = super.A;
                super.A = super.E;
                int var147 = super.A & 128;
                super.A = var147;
                super.F = super.A;
                int var148 = super.A;
                int var149 = this.rlc(var148);
                super.A = var149;
                int var150 = super.A | 92;
                super.A = var150;
                super.F = super.A;
                super.H = super.A;
                int var151 = this.IX() + 5;
                this.wMem(var151, 0, 37496);
                int var152 = this.HL();
                int var153 = this.mem(var152, 37500);
                super.A = var153;
                int var154 = super.A & 7;
                super.A = var154;
                super.F = super.A;
                if (super.A == 7) {
                  int var178 = this.IX() + 5;
                  int var179 = this.mem(var178, 37507) - 1 & 255;
                  this.wMem(var178, var179, 37507);
                }

                int var155 = this.HL();
                int var156 = this.mem(var155, 37510);
                super.A = var156;
                int var157 = super.A | 7;
                super.A = var157;
                super.F = super.A;
                int var158 = this.HL();
                this.wMem(var158, super.A, 37513);
                int var159 = this.DE() + 1 & '\uffff';
                this.DE(var159);
                int var160 = this.DE();
                int var161 = this.mem(var160, 37515);
                super.A = var161;
                super.H = super.A;
                int var162 = super.H - 1 & 255;
                super.H = var162;
                super.F = super.H;
                int var163 = this.IX() + 6;
                int var164 = this.mem(var163, 37518);
                super.A = var164;
                int var165 = this.HL();
                this.wMem(var165, super.A, 37521);
                int var166 = super.H + 1 & 255;
                super.H = var166;
                super.F = super.H;
                int var167 = this.HL();
                int var168 = this.mem(var167, 37523);
                super.A = var168;
                int var169 = this.IX() + 5;
                int var170 = this.mem(var169, 37524);
                int var171 = super.A & var170;
                super.A = var171;
                int var172 = this.IX() + 5;
                this.mem(var172, 37524);
                if (super.A != 0) {
                  this.incPops();
                  return;
                }

                int var173 = this.HL();
                this.wMem(var173, 255, 37530);
                int var174 = super.H + 1 & 255;
                super.H = var174;
                super.F = super.H;
                int var175 = this.IX() + 6;
                int var176 = this.mem(var175, 37533);
                super.A = var176;
                int var177 = this.HL();
                this.wMem(var177, super.A, 37536);
              }
            } else {
              this.BC(640);
              int var180 = this.mem(32990, 37458);
              super.A = var180;

              do {
                int var181 = super.A ^ 24;
                super.A = var181;

                do {
                  int var182 = super.B - 1 & 255;
                  super.B = var182;
                } while(super.B != 0);

                super.B = super.C;
                int var183 = super.C - 1 & 255;
                super.C = var183;
              } while(super.C != 0);
            }
          }
        } else {
          this.IY(33280);
          int var7 = this.IX() + 9;
          this.wMem(var7, 0, 37544);
          int var8 = this.IX() + 2;
          int var9 = this.mem(var8, 37548);
          super.A = var9;
          int var10 = this.IX() + 3;
          this.wMem(var10, super.A, 37551);
          int var11 = this.IX() + 5;
          this.wMem(var11, 128, 37554);

          while(true) {
            label115: {
              int var12 = this.IY();
              int var13 = this.mem(var12, 37558);
              super.A = var13;
              int var14 = this.IX() + 3;
              int var15 = this.mem(var14, 37561);
              int var16 = super.A + var15 & 255;
              super.A = var16;
              int var17 = this.IX() + 3;
              this.mem(var17, 37561);
              super.F = super.A;
              super.L = super.A;
              int var18 = this.IY() + 1;
              int var19 = this.mem(var18, 37565);
              super.H = var19;
              int var20 = this.mem(34262, 37568);
              super.A = var20;
              int var21 = super.A | super.A;
              super.A = var21;
              if (super.A == 0) {
                int var122 = this.IX() + 5;
                int var123 = this.mem(var122, 37574);
                super.A = var123;
                int var124 = this.HL();
                int var125 = this.mem(var124, 37577);
                int var126 = super.A & var125;
                super.A = var126;
                int var127 = this.HL();
                this.mem(var127, 37577);
                if (super.A == 0) {
                  break label115;
                }

                int var128 = this.IX() + 9;
                int var129 = this.mem(var128, 37580);
                super.A = var129;
                this.wMem(34262, super.A, 37583);
                int var130 = this.IX() + 11;
                int var131 = this.mem(var130, 37586) | 1;
                this.wMem(var130, var131, 37586);
              }

              int var22 = this.IX() + 9;
              this.mem(var22, 37590);
              int var23 = this.IX() + 9;
              this.mem(var23, 37590);
              int var24 = this.IX() + 9;
              int var25 = this.mem(var24, 37593);
              if (super.A == var25) {
                int var112 = this.IX() + 11;
                if ((this.mem(var112, 37599) & 1) != 0) {
                  int var113 = this.IX() + 3;
                  int var114 = this.mem(var113, 37601);
                  super.B = var114;
                  int var115 = this.IX() + 5;
                  int var116 = this.mem(var115, 37604);
                  super.A = var116;
                  super.C = 1;
                  if (super.A >= 4) {
                    super.C = 0;
                    if (super.A >= 16) {
                      int var121 = super.B - 1 & 255;
                      super.B = var121;
                      super.F = super.B;
                      super.C = 3;
                      if (super.A >= 64) {
                        super.C = 2;
                      }
                    }
                  }

                  int var117 = this.BC();
                  this.wMem16(34258, var117, 37628);
                  super.A = super.IYL;
                  int var118 = super.A - 16 & 255;
                  super.A = var118;
                  super.F = super.A;
                  this.wMem(34255, super.A, 37636);
                  int var119 = this.HL();
                  this.push(var119);
                  this.$36508();
                  int var120 = this.pop();
                  this.HL(var120);
                }
              }
            }

            int var26 = this.IX() + 5;
            int var27 = this.mem(var26, 37646);
            super.A = var27;
            int var28 = this.HL();
            int var29 = this.mem(var28, 37649);
            int var30 = super.A | var29;
            super.A = var30;
            int var31 = this.HL();
            this.mem(var31, 37649);
            super.F = super.A;
            int var32 = this.HL();
            this.wMem(var32, super.A, 37650);
            int var33 = this.IX() + 9;
            int var34 = this.mem(var33, 37651);
            super.A = var34;
            int var35 = this.IX() + 1;
            int var36 = this.mem(var35, 37654);
            int var37 = super.A + var36 & 255;
            super.A = var37;
            int var38 = this.IX() + 1;
            this.mem(var38, 37654);
            super.F = super.A;
            super.L = super.A;
            int var39 = super.L | 128;
            super.L = var39;
            super.H = 131;
            int var40 = this.HL();
            int var41 = this.mem(var40, 37662);
            super.E = var41;
            super.D = 0;
            int var42 = this.IY();
            int var43 = this.DE();
            int var44 = var42 + var43 & '\uffff';
            this.IY(var44);
            int var45 = super.L & -129;
            super.L = var45;
            int var46 = this.HL();
            int var47 = this.mem(var46, 37669);
            super.A = var47;
            int var48 = super.A | super.A;
            super.A = var48;
            if (super.A != 0) {
              super.B = super.A;
              int var96 = this.IX() + 1;
              if ((this.mem(var96, 37678) & 128) != 0) {
                do {
                  int var105 = this.IX() + 5;
                  int var106 = this.mem(var105, 37680);
                  int var107 = this.rlc(var106);
                  this.wMem(var105, var107, 37680);
                  int var108 = this.IX() + 5;
                  if ((this.mem(var108, 37688) & 1) != 0) {
                    int var110 = this.IX() + 3;
                    int var111 = this.mem(var110, 37690) - 1 & 255;
                    this.wMem(var110, var111, 37690);
                  }

                  int var109 = super.B - 1 & 255;
                  super.B = var109;
                } while(super.B != 0);
              } else {
                do {
                  int var97 = this.IX() + 5;
                  int var98 = this.mem(var97, 37697);
                  int var99 = this.rrc(var98);
                  this.wMem(var97, var99, 37697);
                  int var100 = this.IX() + 5;
                  if ((this.mem(var100, 37705) & 128) != 0) {
                    int var102 = this.IX() + 3;
                    int var103 = this.mem(var102, 37707) + 1;
                    this.wMem(var102, var103, 37707);
                    int var104 = var103 & 255;
                    this.wMem(var102, var104, 37707);
                  }

                  int var101 = super.B - 1 & 255;
                  super.B = var101;
                } while(super.B != 0);
              }
            }

            int var49 = this.IX() + 9;
            int var50 = this.mem(var49, 37712);
            super.A = var50;
            int var51 = this.IX() + 4;
            this.mem(var51, 37715);
            int var52 = this.IX() + 4;
            this.mem(var52, 37715);
            int var53 = this.IX() + 4;
            int var54 = this.mem(var53, 37718);
            if (super.A == var54) {
              int var55 = this.mem(34262, 37726);
              super.A = var55;
              if ((super.A & 128) != 0) {
                int var90 = super.A + 1 & 255;
                super.A = var90;
                super.F = super.A;
                this.wMem(34262, super.A, 37734);
                int var91 = this.IX() + 11;
                int var92 = this.mem(var91, 37737) & -2;
                this.wMem(var91, var92, 37737);
              } else {
                int var56 = this.IX() + 11;
                if ((this.mem(var56, 37747) & 1) != 0) {
                  int var57 = this.mem(34256, 37749);
                  super.A = var57;
                  if ((super.A & 2) != 0) {
                    int var58 = super.A;
                    int var59 = this.rrc(var58);
                    super.A = var59;
                    int var60 = this.IX();
                    int var61 = this.mem(var60, 37757);
                    int var62 = super.A ^ var61;
                    super.A = var62;
                    int var63 = this.IX();
                    this.mem(var63, 37757);
                    super.F = super.A;
                    int var64 = super.A;
                    int var65 = this.rlc(var64);
                    super.A = var65;
                    int var66 = super.A;
                    int var67 = this.rlc(var66);
                    super.A = var67;
                    int var68 = super.A & 2;
                    super.A = var68;
                    super.F = super.A;
                    int var69 = super.A - 1 & 255;
                    super.A = var69;
                    super.F = super.A;
                    this.HL(34262);
                    int var70 = this.HL();
                    int var71 = this.mem(var70, 37768);
                    int var72 = super.A + var71 & 255;
                    super.A = var72;
                    int var73 = this.HL();
                    this.mem(var73, 37768);
                    super.F = super.A;
                    int var74 = this.HL();
                    this.wMem(var74, super.A, 37769);
                    int var75 = this.mem(33003, 37770);
                    super.A = var75;
                    super.C = super.A;
                    int var76 = this.mem(33824, 37774);
                    super.A = var76;
                    if (super.A == super.C) {
                      int var87 = this.HL();
                      int var88 = this.mem(var87, 37780);
                      super.A = var88;
                      if (super.A < 12) {
                        int var89 = this.HL();
                        this.wMem(var89, 12, 37785);
                      }
                    }

                    int var77 = this.HL();
                    int var78 = this.mem(var77, 37787);
                    super.A = var78;
                    int var79 = this.IX() + 4;
                    this.mem(var79, 37788);
                    int var80 = this.IX() + 4;
                    this.mem(var80, 37788);
                    int var81 = this.IX() + 4;
                    int var82 = this.mem(var81, 37791);
                    if (super.A >= var82 && super.F != 0) {
                      int var83 = this.HL();
                      this.wMem(var83, 240, 37795);
                      int var84 = this.mem(34255, 37797);
                      super.A = var84;
                      int var85 = super.A & 248;
                      super.A = var85;
                      super.F = super.A;
                      this.wMem(34255, super.A, 37802);
                      int var86 = super.A ^ super.A;
                      super.A = var86;
                      super.F = super.A;
                      this.wMem(34257, super.A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var93 = this.IX() + 9;
            int var94 = this.mem(var93, 37720) + 1;
            this.wMem(var93, var94, 37720);
            int var95 = var94 & 255;
            this.wMem(var93, var95, 37720);
          }
        }
      }

      this.DE(8);
      int var4 = this.IX();
      int var5 = this.DE();
      int var6 = var4 + var5 & '\uffff';
      this.IX(var6);
    }
  }

  public void $37841() {
    super.H = 164;
    int var1 = this.mem(41983, 37843);
    super.A = var1;
    super.L = super.A;

    do {
      int var2 = this.HL();
      int var3 = this.mem(var2, 37847);
      super.C = var3;
      int var4 = super.C & -129;
      super.C = var4;
      int var5 = this.mem(33824, 37850);
      super.A = var5;
      int var6 = super.A | 64;
      super.A = var6;
      super.F = super.A;
      if (super.A == super.C) {
        int var8 = this.HL();
        int var9 = this.mem(var8, 37858);
        super.A = var9;
        int var10 = super.A;
        int var11 = this.rlc(var10);
        super.A = var11;
        int var12 = super.A & 1;
        super.A = var12;
        super.F = super.A;
        int var13 = super.A + 92 & 255;
        super.A = var13;
        super.F = super.A;
        super.D = super.A;
        int var14 = super.H + 1 & 255;
        super.H = var14;
        super.F = super.H;
        int var15 = this.HL();
        int var16 = this.mem(var15, 37866);
        super.E = var16;
        int var17 = super.H - 1 & 255;
        super.H = var17;
        super.F = super.H;
        int var18 = this.DE();
        int var19 = this.mem(var18, 37868);
        super.A = var19;
        int var20 = super.A & 7;
        super.A = var20;
        super.F = super.A;
        if (super.A != 7) {
          int var21 = this.mem(34251, 37936);
          super.A = var21;
          int var22 = super.A + super.L & 255;
          super.A = var22;
          super.F = super.A;
          int var23 = super.A & 3;
          super.A = var23;
          super.F = super.A;
          int var24 = super.A + 3 & 255;
          super.A = var24;
          super.F = super.A;
          super.C = super.A;
          int var25 = this.DE();
          int var26 = this.mem(var25, 37945);
          super.A = var26;
          int var27 = super.A & 248;
          super.A = var27;
          super.F = super.A;
          int var28 = super.A | super.C;
          super.A = var28;
          super.F = super.A;
          int var29 = this.DE();
          this.wMem(var29, super.A, 37949);
          int var30 = this.HL();
          int var31 = this.mem(var30, 37950);
          super.A = var31;
          int var32 = super.A;
          int var33 = this.rlc(var32);
          super.A = var33;
          int var34 = super.A;
          int var35 = this.rlc(var34);
          super.A = var35;
          int var36 = super.A;
          int var37 = this.rlc(var36);
          super.A = var37;
          int var38 = super.A;
          int var39 = this.rlc(var38);
          super.A = var39;
          int var40 = super.A & 8;
          super.A = var40;
          super.F = super.A;
          int var41 = super.A + 96 & 255;
          super.A = var41;
          super.F = super.A;
          super.D = super.A;
          int var42 = this.HL();
          this.push(var42);
          this.HL(32993);
          super.B = 8;
          this.$38555();
          int var43 = this.pop();
          this.HL(var43);
        } else {
          this.IX(34172);

          while(true) {
            int var44 = this.IX() + 2;
            int var45 = this.mem(var44, 37879) + 1;
            this.wMem(var44, var45, 37879);
            int var46 = var45 & 255;
            this.wMem(var44, var46, 37879);
            int var47 = this.IX() + 2;
            int var48 = this.mem(var47, 37882);
            super.A = var48;
            if (super.A != 58) {
              int var49 = this.mem(32990, 37897);
              super.A = var49;
              super.C = 128;

              do {
                int var50 = super.A ^ 24;
                super.A = var50;
                super.F = super.A;
                super.E = super.A;
                super.A = 144;
                int var51 = super.A - super.C & 255;
                super.A = var51;
                super.F = super.A;
                super.B = super.A;
                super.A = super.E;

                do {
                  int var52 = super.B - 1 & 255;
                  super.B = var52;
                } while(super.B != 0);

                int var53 = super.C - 1 & 255;
                super.C = var53;
                super.F = super.C;
                int var54 = super.C - 1 & 255;
                super.C = var54;
              } while(super.C != 0);

              int var55 = this.mem(34270, 37918);
              super.A = var55;
              int var56 = super.A + 1 & 255;
              super.A = var56;
              super.F = super.A;
              this.wMem(34270, super.A, 37922);
              if (super.F == 0) {
                super.A = 1;
                this.wMem(34271, super.A, 37929);
              }

              int var57 = this.HL();
              int var58 = this.mem(var57, 37932) & -65;
              int var59 = this.HL();
              this.wMem(var59, var58, 37932);
              break;
            }

            int var60 = this.IX() + 2;
            this.wMem(var60, 48, 37889);
            int var61 = this.IX() - 1 & '\uffff';
            this.IX(var61);
          }
        }
      }

      int var7 = super.L + 1 & 255;
      super.L = var7;
    } while(super.L != 0);

  }

  public void $37974() {
    super.B = 16;

    do {
      int var1 = super.C & 1;
      super.F = var1;
      int var2 = this.DE();
      int var3 = this.mem(var2, 37978);
      super.A = var3;
      if (super.F != 0) {
        int var31 = this.HL();
        int var32 = this.mem(var31, 37981);
        int var33 = super.A & var32;
        super.A = var33;
        int var34 = this.HL();
        this.mem(var34, 37981);
        if (super.A != 0) {
          return;
        }

        int var35 = this.DE();
        int var36 = this.mem(var35, 37983);
        super.A = var36;
        int var37 = this.HL();
        int var38 = this.mem(var37, 37984);
        int var39 = super.A | var38;
        super.A = var39;
        int var40 = this.HL();
        this.mem(var40, 37984);
        super.F = super.A;
      }

      int var4 = this.HL();
      this.wMem(var4, super.A, 37985);
      int var5 = super.L + 1 & 255;
      super.L = var5;
      super.F = super.L;
      int var6 = this.DE() + 1 & '\uffff';
      this.DE(var6);
      int var7 = super.C & 1;
      super.F = var7;
      int var8 = this.DE();
      int var9 = this.mem(var8, 37990);
      super.A = var9;
      if (super.F != 0) {
        int var21 = this.HL();
        int var22 = this.mem(var21, 37993);
        int var23 = super.A & var22;
        super.A = var23;
        int var24 = this.HL();
        this.mem(var24, 37993);
        if (super.A != 0) {
          return;
        }

        int var25 = this.DE();
        int var26 = this.mem(var25, 37995);
        super.A = var26;
        int var27 = this.HL();
        int var28 = this.mem(var27, 37996);
        int var29 = super.A | var28;
        super.A = var29;
        int var30 = this.HL();
        this.mem(var30, 37996);
        super.F = super.A;
      }

      int var10 = this.HL();
      this.wMem(var10, super.A, 37997);
      int var11 = super.L - 1 & 255;
      super.L = var11;
      super.F = super.L;
      int var12 = super.H + 1 & 255;
      super.H = var12;
      super.F = super.H;
      int var13 = this.DE() + 1 & '\uffff';
      this.DE(var13);
      super.A = super.H;
      int var14 = super.A & 7;
      super.A = var14;
      if (super.A == 0) {
        super.A = super.H;
        int var17 = super.A - 8 & 255;
        super.A = var17;
        super.F = super.A;
        super.H = super.A;
        super.A = super.L;
        int var18 = super.A + 32 & 255;
        super.A = var18;
        super.F = super.A;
        super.L = super.A;
        int var19 = super.A & 224;
        super.A = var19;
        if (super.A == 0) {
          super.A = super.H;
          int var20 = super.A + 8 & 255;
          super.A = var20;
          super.F = super.A;
          super.H = super.A;
        }
      }

      int var15 = super.B - 1 & 255;
      super.B = var15;
    } while(super.B != 0);

    int var16 = super.A ^ super.A;
    super.A = var16;
  }

  public void $38064() {
    int var1 = this.mem(33003, 38064);
    super.A = var1;
    this.wMem(33824, super.A, 38067);
    int var2 = this.mem(34259, 38070);
    super.A = var2;
    int var3 = super.A & 31;
    super.A = var3;
    super.F = super.A;
    int var4 = super.A + 160 & 255;
    super.A = var4;
    super.F = super.A;
    this.wMem(34259, super.A, 38077);
    super.A = 93;
    this.wMem(34260, super.A, 38082);
    super.A = 208;
    this.wMem(34255, super.A, 38087);
    int var5 = super.A ^ super.A;
    super.A = var5;
    super.F = super.A;
    this.wMem(34257, super.A, 38091);
    this.incPops();
  }

  public void $38137() {
    int var1 = this.mem16(32983, 38137);
    this.HL(var1);
    super.A = super.H;
    int var2 = super.A & 1;
    super.A = var2;
    super.F = super.A;
    int var3 = super.A;
    int var4 = this.rlc(var3);
    super.A = var4;
    int var5 = super.A;
    int var6 = this.rlc(var5);
    super.A = var6;
    int var7 = super.A;
    int var8 = this.rlc(var7);
    super.A = var8;
    int var9 = super.A + 112 & 255;
    super.A = var9;
    super.F = super.A;
    super.H = super.A;
    super.E = super.L;
    super.D = super.H;
    int var10 = this.mem(32985, 38151);
    super.A = var10;
    int var11 = super.A | super.A;
    super.A = var11;
    if (super.A != 0) {
      super.B = super.A;
      int var12 = this.mem(32982, 38157);
      super.A = var12;
      int var13 = super.A | super.A;
      super.A = var13;
      if (super.A == 0) {
        int var33 = this.HL();
        int var34 = this.mem(var33, 38163);
        super.A = var34;
        int var35 = super.A;
        int var36 = this.rlc(var35);
        super.A = var36;
        int var37 = super.A;
        int var38 = this.rlc(var37);
        super.A = var38;
        int var39 = super.H + 1 & 255;
        super.H = var39;
        super.F = super.H;
        int var40 = super.H + 1 & 255;
        super.H = var40;
        super.F = super.H;
        int var41 = this.HL();
        int var42 = this.mem(var41, 38170);
        super.C = var42;
        int var43 = super.C;
        int var44 = this.rrc(var43);
        super.C = var44;
        int var45 = super.C;
        int var46 = this.rrc(var45);
        super.C = var46;
      } else {
        int var14 = this.HL();
        int var15 = this.mem(var14, 38182);
        super.A = var15;
        int var16 = super.A;
        int var17 = this.rrc(var16);
        super.A = var17;
        int var18 = super.A;
        int var19 = this.rrc(var18);
        super.A = var19;
        int var20 = super.H + 1 & 255;
        super.H = var20;
        super.F = super.H;
        int var21 = super.H + 1 & 255;
        super.H = var21;
        super.F = super.H;
        int var22 = this.HL();
        int var23 = this.mem(var22, 38189);
        super.C = var23;
        int var24 = super.C;
        int var25 = this.rlc(var24);
        super.C = var25;
        int var26 = super.C;
        int var27 = this.rlc(var26);
        super.C = var27;
      }

      do {
        int var28 = this.DE();
        this.wMem(var28, super.A, 38175);
        int var29 = this.HL();
        this.wMem(var29, super.C, 38176);
        int var30 = super.L + 1 & 255;
        super.L = var30;
        super.F = super.L;
        int var31 = super.E + 1 & 255;
        super.E = var31;
        int var32 = super.B - 1 & 255;
        super.B = var32;
      } while(super.B != 0);

    }
  }

  public void $38196() {
    int var1 = this.mem(33824, 38196);
    super.A = var1;
    if (super.A == 35) {
      int var15 = this.mem(34271, 38203);
      super.A = var15;
      int var16 = super.A | super.A;
      super.A = var16;
      if (super.A == 0) {
        int var19 = this.mem(34251, 38209);
        super.A = var19;
        int var20 = super.A & 2;
        super.A = var20;
        super.F = super.A;
        int var21 = super.A;
        int var22 = this.rrc(var21);
        super.A = var22;
        int var23 = super.A;
        int var24 = this.rrc(var23);
        super.A = var24;
        int var25 = super.A;
        int var26 = this.rrc(var25);
        super.A = var26;
        int var27 = super.A;
        int var28 = this.rrc(var27);
        super.A = var28;
        int var29 = super.A | 128;
        super.A = var29;
        super.F = super.A;
        super.E = super.A;
        int var30 = this.mem(34255, 38221);
        super.A = var30;
        if (super.A != 208) {
          super.E = 192;
          if (super.A < 192) {
            super.E = 224;
          }
        }

        super.D = 156;
        this.HL(26734);
        super.C = 1;
        this.$37974();
        if (false && super.F != 0) {
          this.incPops();
        } else {
          this.HL(17733);
          int var31 = this.HL();
          this.wMem16(23918, var31, 38252);
          this.HL(1799);
          int var32 = this.HL();
          this.wMem16(23950, var32, 38258);
        }
      } else {
        int var17 = this.mem(34259, 38262);
        super.A = var17;
        int var18 = super.A & 31;
        super.A = var18;
        super.F = super.A;
        if (super.A < 6) {
          super.A = 2;
          this.wMem(34271, super.A, 38272);
        }
      }
    } else {
      int var2 = this.mem(33824, 38298);
      super.A = var2;
      if (super.A == 33) {
        int var3 = this.mem(34251, 38304);
        super.A = var3;
        int var4 = super.A & 1;
        super.A = var4;
        super.F = super.A;
        int var5 = super.A;
        int var6 = this.rrc(var5);
        super.A = var6;
        int var7 = super.A;
        int var8 = this.rrc(var7);
        super.A = var8;
        int var9 = super.A;
        int var10 = this.rrc(var9);
        super.A = var10;
        super.E = super.A;
        int var11 = this.mem(34271, 38313);
        super.A = var11;
        if (super.A == 3) {
          int var14 = super.E | 64;
          super.E = var14;
        }

        super.D = 166;
        this.IX(33488);
        this.BC(4124);
        this.$38504();
        this.HL(1799);
        int var12 = this.HL();
        this.wMem16(23996, var12, 38337);
        int var13 = this.HL();
        this.wMem16(24028, var13, 38340);
      }
    }
  }

  public void $38276() {
    int var1 = this.mem(33824, 38276);
    super.A = var1;
    if (super.A == 33) {
      int var2 = this.mem(34259, 38282);
      super.A = var2;
      if (super.A == 188) {
        int var3 = super.A ^ super.A;
        super.A = var3;
        super.F = super.A;
        this.wMem(34251, super.A, 38289);
        super.A = 3;
        this.wMem(34271, super.A, 38294);
      }
    }
  }

  public void $38344() {
    int var1 = this.mem16(34259, 38344);
    this.HL(var1);
    super.B = 0;
    int var2 = this.mem(32986, 38349);
    super.A = var2;
    int var3 = super.A & 1;
    super.A = var3;
    super.F = super.A;
    int var4 = super.A + 64 & 255;
    super.A = var4;
    super.F = super.A;
    super.E = super.A;
    super.D = 0;
    int var5 = this.HL();
    int var6 = this.DE();
    int var7 = var5 + var6 & '\uffff';
    this.HL(var7);
    int var8 = this.mem(32964, 38360);
    super.A = var8;
    int var9 = this.HL();
    this.mem(var9, 38363);
    int var10 = this.HL();
    this.mem(var10, 38363);
    int var11 = this.HL();
    int var12 = this.mem(var11, 38364);
    if (super.A == var12) {
      int var44 = this.mem(34257, 38366);
      super.A = var44;
      int var45 = super.A | super.A;
      super.A = var45;
      if (super.A == 0) {
        int var46 = this.mem(34258, 38372);
        super.A = var46;
        int var47 = super.A & 3;
        super.A = var47;
        super.F = super.A;
        int var48 = super.A;
        int var49 = this.rlc(var48);
        super.A = var49;
        int var50 = super.A;
        int var51 = this.rlc(var50);
        super.A = var51;
        super.B = super.A;
        int var52 = this.mem(32986, 38380);
        super.A = var52;
        int var53 = super.A & 1;
        super.A = var53;
        super.F = super.A;
        int var54 = super.A - 1 & 255;
        super.A = var54;
        super.F = super.A;
        int var55 = super.A ^ 12;
        super.A = var55;
        super.F = super.A;
        int var56 = super.A ^ super.B;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A & 12;
        super.A = var57;
        super.F = super.A;
        super.B = super.A;
      }
    }

    int var13 = this.mem16(34259, 38392);
    this.HL(var13);
    this.DE(31);
    super.C = 15;
    this.$38430();
    this.decPops();
    int var14 = this.HL() + 1 & '\uffff';
    this.HL(var14);
    this.$38430();
    this.decPops();
    int var15 = this.HL();
    int var16 = this.DE();
    int var17 = var15 + var16 & '\uffff';
    this.HL(var17);
    this.$38430();
    int var18 = this.HL() + 1 & '\uffff';
    this.HL(var18);
    this.$38430();
    this.decPops();
    int var19 = this.mem(34255, 38415);
    super.A = var19;
    int var20 = super.A + super.B & 255;
    super.A = var20;
    super.F = super.A;
    super.C = super.A;
    int var21 = this.HL();
    int var22 = this.DE();
    int var23 = var21 + var22 & '\uffff';
    this.HL(var23);
    this.$38430();
    int var24 = this.HL() + 1 & '\uffff';
    this.HL(var24);
    this.$38430();
    this.decPops();
    int var25 = this.mem(34255, 38455);
    super.A = var25;
    int var26 = super.A + super.B & 255;
    super.A = var26;
    super.F = super.A;
    super.IXH = 130;
    super.IXL = super.A;
    int var27 = this.mem(34256, 38464);
    super.A = var27;
    int var28 = super.A & 1;
    super.A = var28;
    super.F = super.A;
    int var29 = super.A;
    int var30 = this.rrc(var29);
    super.A = var30;
    super.E = super.A;
    int var31 = this.mem(34258, 38471);
    super.A = var31;
    int var32 = super.A & 3;
    super.A = var32;
    super.F = super.A;
    int var33 = super.A;
    int var34 = this.rrc(var33);
    super.A = var34;
    int var35 = super.A;
    int var36 = this.rrc(var35);
    super.A = var36;
    int var37 = super.A;
    int var38 = this.rrc(var37);
    super.A = var38;
    int var39 = super.A | super.E;
    super.A = var39;
    super.F = super.A;
    super.E = super.A;
    super.D = 157;
    int var40 = this.mem(33824, 38483);
    super.A = var40;
    if (super.A == 29) {
      super.D = 182;
      super.A = super.E;
      int var43 = super.A ^ 128;
      super.A = var43;
      super.F = super.A;
      super.E = super.A;
    }

    super.B = 16;
    int var41 = this.mem(34259, 38498);
    super.A = var41;
    int var42 = super.A & 31;
    super.A = var42;
    super.F = super.A;
    super.C = super.A;
    this.$38504();
  }

  public void $38430() {
    int var1 = this.mem(32928, 38430);
    super.A = var1;
    int var2 = this.HL();
    this.mem(var2, 38433);
    int var3 = this.HL();
    this.mem(var3, 38433);
    int var4 = this.HL();
    int var5 = this.mem(var4, 38434);
    if (super.A == var5) {
      super.A = super.C;
      int var11 = super.A & 15;
      super.A = var11;
      if (super.A != 0) {
        int var12 = this.mem(32928, 38441);
        super.A = var12;
        int var13 = super.A | 7;
        super.A = var13;
        super.F = super.A;
        int var14 = this.HL();
        this.wMem(var14, super.A, 38446);
      }
    }

    int var6 = this.mem(32955, 38447);
    super.A = var6;
    int var7 = this.HL();
    this.mem(var7, 38450);
    int var8 = this.HL();
    this.mem(var8, 38450);
    int var9 = this.HL();
    int var10 = this.mem(var9, 38451);
    if (super.A == var10) {
      this.incPops();
    } else {
    }
  }

  public void $38504() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1, 38504);
      super.A = var2;
      int var3 = this.IX() + 1;
      int var4 = this.mem(var3, 38507);
      super.H = var4;
      int var5 = super.A | super.C;
      super.A = var5;
      super.F = super.A;
      super.L = super.A;
      int var6 = this.DE();
      int var7 = this.mem(var6, 38512);
      super.A = var7;
      int var8 = this.HL();
      int var9 = this.mem(var8, 38513);
      int var10 = super.A | var9;
      super.A = var10;
      int var11 = this.HL();
      this.mem(var11, 38513);
      super.F = super.A;
      int var12 = this.HL();
      this.wMem(var12, super.A, 38514);
      int var13 = this.HL() + 1 & '\uffff';
      this.HL(var13);
      int var14 = this.DE() + 1 & '\uffff';
      this.DE(var14);
      int var15 = this.DE();
      int var16 = this.mem(var15, 38517);
      super.A = var16;
      int var17 = this.HL();
      int var18 = this.mem(var17, 38518);
      int var19 = super.A | var18;
      super.A = var19;
      int var20 = this.HL();
      this.mem(var20, 38518);
      super.F = super.A;
      int var21 = this.HL();
      this.wMem(var21, super.A, 38519);
      int var22 = this.IX() + 1 & '\uffff';
      this.IX(var22);
      int var23 = this.IX() + 1 & '\uffff';
      this.IX(var23);
      int var24 = this.DE() + 1 & '\uffff';
      this.DE(var24);
      int var25 = super.B - 1 & 255;
      super.B = var25;
    } while(super.B != 0);

  }

  public void $38528() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1, 38528);
      super.A = var2;
      this.$38545();
      int var3 = this.IX() + 1 & '\uffff';
      this.IX(var3);
      int var4 = super.E + 1 & 255;
      super.E = var4;
      super.F = super.E;
      super.A = super.D;
      int var5 = super.A - 8 & 255;
      super.A = var5;
      super.F = super.A;
      super.D = super.A;
      int var6 = super.C - 1 & 255;
      super.C = var6;
    } while(super.C != 0);

  }

  public void $38545() {
    super.H = 7;
    super.L = super.A;
    int var1 = super.L | 128;
    super.L = var1;
    int var2 = this.HL() * 2 & '\uffff';
    this.HL(var2);
    int var3 = this.HL() * 2 & '\uffff';
    this.HL(var3);
    int var4 = this.HL() * 2 & '\uffff';
    this.HL(var4);
    super.B = 8;
    this.$38555();
  }

  public void $38555() {
    do {
      int var1 = this.HL();
      int var2 = this.mem(var1, 38555);
      super.A = var2;
      int var3 = this.DE();
      this.wMem(var3, super.A, 38556);
      int var4 = this.HL() + 1 & '\uffff';
      this.HL(var4);
      int var5 = super.D + 1 & 255;
      super.D = var5;
      int var6 = super.B - 1 & 255;
      super.B = var6;
    } while(super.B != 0);

  }

  public void $38562() {
    while(true) {
      int var1 = this.HL();
      int var2 = this.mem(var1, 38562);
      super.A = var2;
      if (super.A == 255) {
        return;
      }

      this.BC(100);
      int var3 = super.A ^ super.A;
      super.A = var3;
      super.F = super.A;
      int var4 = this.HL();
      int var5 = this.mem(var4, 38570);
      super.E = var5;
      super.D = super.E;

      while(true) {
        int var6 = super.D - 1 & 255;
        super.D = var6;
        if (super.D == 0) {
          super.D = super.E;
          int var12 = super.A ^ 24;
          super.A = var12;
        }

        int var7 = super.B - 1 & 255;
        super.B = var7;
        if (super.B == 0) {
          this.exAF();
          super.A = super.C;
          if (super.A == 50) {
            int var10 = super.E;
            int var11 = this.rlc(var10);
            super.E = var11;
          }

          this.exAF();
          int var8 = super.C - 1 & 255;
          super.C = var8;
          if (super.C == 0) {
            this.$38601();
            if (super.F != 0) {
              return;
            }

            int var9 = this.HL() + 1 & '\uffff';
            this.HL(var9);
            break;
          }
        }
      }
    }
  }

  public void $38601() {
    int var1 = this.mem(34254, 38601);
    super.A = var1;
    int var2 = super.A | super.A;
    super.A = var2;
    if (super.A != 0) {
      int var6 = this.in(31);
      super.A = var6;
      if ((super.A & 16) != 0) {
        return;
      }
    }

    this.BC(45054);
    int var3 = this.BC();
    int var4 = this.in(var3);
    super.A = var4;
    int var5 = super.A & 1;
    super.A = var5;
    super.F = super.A;
  }

  public void $38622() {
    super.E = super.A;
    super.C = 254;

    do {
      super.D = super.A;
      int var1 = super.D & -17;
      super.D = var1;
      int var2 = super.D & -9;
      super.D = var2;
      super.B = super.E;

      do {
        if (super.A == super.B) {
          super.D = 24;
        }

        int var3 = super.B - 1 & 255;
        super.B = var3;
      } while(super.B != 0);

      int var4 = super.A - 1 & 255;
      super.A = var4;
    } while(super.A != 0);

  }
}
