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

public class JetSetWilly extends MiniZX {
  static Semaphore semaphore = new Semaphore(2);

  volatile int checking;
  volatile int checkingEmu;
  volatile static Stack<StateSync> stateSync = new Stack();
  private MiniZXWithEmulation miniZXWithEmulation;
  private static OOZ80<WordNumber> ooz80 = Helper.createOOZ80(io);
  private int doReturn;

  public static void main(String[] args) {
    JetSetWilly jetSetWilly = new JetSetWilly();
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

  public void $36307() {
    label250:
    {
      label251:
      {
        label246:
        {
          label266:
          {
            int var1 = mem(34262, 36307);
            A = var1;
            int var2 = A + -1 & 255;
            A = var2;
            F = A;
            int var3 = A & 128;
            F = var3;
            if (F != 0) {
              int var264 = mem(34257, 36316);
              A = var264;
              int var265 = A - 1;
              F = var265;
              if (F == 0) {
                int var331 = mem(34261, 36323);
                A = var331;
                int var332 = A & 254;
                A = var332;
                F = A;
                int var333 = A - 8 & 255;
                A = var333;
                F = A;
                HL(34255);
                int var334 = pair(H, L);
                int var335 = mem(var334, 36333);
                int var336 = A + var335 & 255;
                A = var336;
                int var337 = pair(H, L);
                int var338 = pair(H, L);
                F = A;
                int var339 = pair(H, L);
                int var340 = pair(H, L);
                wMem(var340, A, 36334);
                int var341 = A - 240;
                F = var341;
                if (F >= 0) {
                  int var342 = mem(33003, 38064);
                  A = var342;
                  wMem(33824, A, 38067);
                  int var343 = mem(34259, 38070);
                  A = var343;
                  int var344 = A & 31;
                  A = var344;
                  F = A;
                  int var345 = A + 160 & 255;
                  A = var345;
                  F = A;
                  wMem(34259, A, 38077);
                  A = 93;
                  wMem(34260, A, 38082);
                  A = 208;
                  wMem(34255, A, 38087);
                  int var346 = A ^ A;
                  A = var346;
                  F = A;
                  wMem(34257, A, 38091);
                  break label251;
                }

                $36508();
                int var347 = mem(32946, 36343);
                A = var347;
                int var348 = pair(H, L);
                int var349 = mem(var348, 36346);
                int var350 = A - var349;
                F = var350;
                int var351 = pair(H, L);
                if (F == 0) {
                  break label250;
                }

                int var358 = pair(H, L) + 1 & '\uffff';
                HL(var358);
                int var359 = pair(H, L);
                int var360 = mem(var359, 36351);
                int var361 = A - var360;
                F = var361;
                int var362 = pair(H, L);
                if (F == 0) {
                  break label250;
                }

                int var363 = mem(34261, 36355);
                A = var363;
                int var364 = A + 1 & 255;
                A = var364;
                F = A;
                wMem(34261, A, 36359);
                int var365 = A - 8;
                A = var365;
                F = A;
                if (F < 0) { //FIXED jp p
                  int var366 = -A & 255;
                  A = var366;
                }
                int var367 = A + 1 & 255;
                A = var367;
                F = A;
                int var368 = A;
                int var369 = rlc(var368);
                A = var369;
                int var370 = A;
                int var371 = rlc(var370);
                A = var371;
                int var372 = A;
                int var373 = rlc(var372);
                A = var373;
                D = A;
                C = 32;
                int var374 = mem(32990, 36376);
                A = var374;

                do {
                  int var375 = A ^ 24;
                  A = var375;
                  F = A;
                  B = D;

                  do {
                    int var376 = B + -1 & 255;
                    B = var376;
                  } while (B != 0);

                  int var377 = C + -1 & 255;
                  C = var377;
                  F = C;
                } while (F != 0);

                int var378 = mem(34261, 36389);
                A = var378;
                int var379 = A - 18;
                F = var379;
                if (F == 0) {
                  A = 6;
                  wMem(34257, A, 36530);
                  return;
                }

                int var380 = A - 16;
                F = var380;
                if (F != 0) {
                  int var381 = A - 13;
                  F = var381;
                  if (F != 0) {
                    break label246;
                  }
                }
              }

              int var266 = mem(34255, 36406);
              A = var266;
              int var267 = A & 14;
              A = var267;
              F = A;
              if (F != 0) {
                break label266;
              }

              int var300 = mem16(34259, 36413);
              HL(var300);
              DE(64);
              int var301 = pair(H, L);
              int var302 = pair(D, E);
              int var303 = var301 + var302 & '\uffff';
              HL(var303);
              int var304 = H & 2;
              F = var304;
              if (F != 0) {
                break label251;
              }

              int var311 = mem(32955, 36425);
              A = var311;
              int var312 = pair(H, L);
              int var313 = mem(var312, 36428);
              int var314 = A - var313;
              F = var314;
              if (F == 0) {
                break label266;
              }

              int var316 = pair(H, L) + 1 & '\uffff';
              HL(var316);
              int var317 = mem(32955, 36432);
              A = var317;
              int var318 = pair(H, L);
              int var319 = mem(var318, 36435);
              int var320 = A - var319;
              F = var320;
              if (F == 0) {
                break label266;
              }

              int var322 = mem(32928, 36438);
              A = var322;
              int var323 = pair(H, L);
              int var324 = mem(var323, 36441);
              int var325 = A - var324;
              F = var325;
              HL(HL()-1&0xffff);//FIXED
              if (F == 0) {
                int var327 = pair(H, L);
                int var328 = mem(var327, 36446);
                int var329 = A - var328;
                F = var329;
                if (F == 0) {
                  break label266;
                }
              }
            }

            E = 255;
            int var4 = mem(34262, 36566);
            A = var4;
            int var5 = A + -1 & 255;
            A = var5;
            F = A;
            int var6 = A & 128;
            F = var6;
            if (F != 0) {
              label263:
              {
                int var138 = mem(34257, 36574);
                A = var138;
                int var139 = A - 12;
                F = var139;
                if (F >= 0) {
                  A = 255;
                  wMem(34257, A, 37050);
                  IX(33024);

                  while (true) {
                    int var140 = pair(IXH, IXL);
                    int var141 = mem(var140, 37060);
                    A = var141;
                    int var142 = A - 255;
                    F = var142;
                    if (F == 0) {
                      return;
                    }

                    int var143 = A & 3;
                    A = var143;
                    F = A;
                    if (F != 0) {
                      int var147 = A - 1;
                      F = var147;
                      if (F != 0) {
                        int var184 = A - 2;
                        F = var184;
                        if (F != 0) {
                          int var218 = pair(IXH, IXL);
                          int var219 = mem(var218, 37081) & 128;
                          F = var219;
                          if (F != 0) {
                            int var240 = pair(IXH, IXL) + 1;
                            int var241 = mem(var240, 37087);
                            A = var241;
                            int var242 = A & 128;
                            F = var242;
                            if (F != 0) {
                              int var246 = A - 2 & 255;
                              A = var246;
                              F = A;
                              int var247 = A - 148;
                              F = var247;
                              if (F < 0) {
                                int var248 = A - 2 & 255;
                                A = var248;
                                F = A;
                                int var249 = A - 128;
                                F = var249;
                                if (F == 0) {
                                  int var250 = A ^ A;
                                  A = var250;
                                  F = A;
                                }
                              }
                            } else {
                              int var243 = A + 2 & 255;
                              A = var243;
                              F = A;
                              int var244 = A - 18;
                              F = var244;
                              if (F < 0) {
                                int var245 = A + 2 & 255;
                                A = var245;
                                F = A;
                              }
                            }
                          } else {
                            int var220 = pair(IXH, IXL) + 1;
                            int var221 = mem(var220, 37119);
                            A = var221;
                            int var222 = A & 128;
                            F = var222;
                            if (F == 0) {
                              int var236 = A - 2 & 255;
                              A = var236;
                              F = A;
                              int var237 = A - 20;
                              F = var237;
                              if (F < 0) {
                                int var238 = A - 2 & 255;
                                A = var238;
                                F = A;
                                int var239 = A | A;
                                A = var239;
                                F = A;
                                if (F == 0) {
                                  A = 128;
                                }
                              }
                            } else {
                              int var223 = A + 2 & 255;
                              A = var223;
                              F = A;
                              int var224 = A - 146;
                              F = var224;
                              if (F < 0) {
                                int var235 = A + 2 & 255;
                                A = var235;
                                F = A;
                              }
                            }
                          }

                          int var225 = pair(IXH, IXL) + 1;
                          wMem(var225, A, 37149);
                          int var226 = A & 127;
                          A = var226;
                          F = A;
                          int var227 = pair(IXH, IXL) + 7;
                          int var228 = mem(var227, 37154);
                          int var229 = A - var228;
                          F = var229;
                          if (F == 0) {
                            int var231 = pair(IXH, IXL);
                            int var232 = mem(var231, 37160);
                            A = var232;
                            int var233 = A ^ 128;
                            A = var233;
                            F = A;
                            int var234 = pair(IXH, IXL);
                            wMem(var234, A, 37165);
                          }
                        } else {
                          label265:
                          {
                            int var185 = pair(IXH, IXL);
                            int var186 = mem(var185, 37247);
                            A = var186;
                            int var187 = A ^ 8;
                            A = var187;
                            F = A;
                            int var188 = pair(IXH, IXL);
                            wMem(var188, A, 37252);
                            int var189 = A & 24;
                            A = var189;
                            F = A;
                            if (F != 0) {
                              int var214 = pair(IXH, IXL);
                              int var215 = mem(var214, 37259);
                              A = var215;
                              int var216 = A + 32 & 255;
                              A = var216;
                              F = A;
                              int var217 = pair(IXH, IXL);
                              wMem(var217, A, 37264);
                            }

                            int var190 = pair(IXH, IXL) + 3;
                            int var191 = mem(var190, 37267);
                            A = var191;
                            int var192 = pair(IXH, IXL) + 4;
                            int var193 = mem(var192, 37270);
                            int var194 = A + var193 & 255;
                            A = var194;
                            F = A;
                            int var198 = pair(IXH, IXL) + 3;
                            wMem(var198, A, 37273);
                            int var199 = pair(IXH, IXL) + 7;
                            int var200 = mem(var199, 37276);
                            int var201 = A - var200;
                            F = var201;
                            if (F < 0) {
                              int var207 = pair(IXH, IXL) + 6;
                              int var208 = mem(var207, 37281);
                              int var209 = A - var208;
                              F = var209;
                              if (F != 0 && F >= 0) {
                                break label265;
                              }

                              int var211 = pair(IXH, IXL) + 6;
                              int var212 = mem(var211, 37288);
                              A = var212;
                              int var213 = pair(IXH, IXL) + 3;
                              wMem(var213, A, 37291);
                            }

                            int var203 = pair(IXH, IXL) + 4;
                            int var204 = mem(var203, 37294);
                            A = var204;
                            int var205 = -A & 255;
                            A = var205;
                            int var206 = pair(IXH, IXL) + 4;
                            wMem(var206, A, 37299);
                          }
                        }
                      } else {
                        int var148 = pair(IXH, IXL);
                        int var149 = mem(var148, 37171) & 128;
                        F = var149;
                        if (F == 0) {
                          int var167 = pair(IXH, IXL);
                          int var168 = mem(var167, 37177);
                          A = var168;
                          int var169 = A - 32 & 255;
                          A = var169;
                          F = A;
                          int var170 = A & 127;
                          A = var170;
                          F = A;
                          int var171 = pair(IXH, IXL);
                          wMem(var171, A, 37184);
                          int var172 = A - 96;
                          F = var172;
                          if (F >= 0) {
                            int var173 = pair(IXH, IXL) + 2;
                            int var174 = mem(var173, 37191);
                            A = var174;
                            int var175 = A & 31;
                            A = var175;
                            F = A;
                            int var176 = pair(IXH, IXL) + 6;
                            int var177 = mem(var176, 37196);
                            int var178 = A - var177;
                            F = var178;
                            if (F != 0) {
                              int var181 = pair(IXH, IXL) + 2;
                              int var182 = mem(var181, 37201) + -1 & 255; //FIXED
                              wMem(var181, var182, 37201);
                              int var382 = pair(IXH, IXL) + 2;
                            } else {
                              int var180 = pair(IXH, IXL);
                              wMem(var180, 129, 37206);
                            }
                          }
                        } else {
                          int var150 = pair(IXH, IXL);
                          int var151 = mem(var150, 37212);
                          A = var151;
                          int var152 = A + 32 & 255;
                          A = var152;
                          F = A;
                          int var153 = A | 128;
                          A = var153;
                          F = A;
                          int var154 = pair(IXH, IXL);
                          wMem(var154, A, 37219);
                          int var155 = A - 160;
                          F = var155;
                          if (F < 0) {
                            int var156 = pair(IXH, IXL) + 2;
                            int var157 = mem(var156, 37226);
                            A = var157;
                            int var158 = A & 31;
                            A = var158;
                            F = A;
                            int var159 = pair(IXH, IXL) + 7;
                            int var160 = mem(var159, 37231);
                            int var161 = A - var160;
                            F = var161;
                            if (F != 0) {
                              int var166 = mem(pair(IXH, IXL) + 2) + 1 & 0xff ;
                              wMem(pair(IXH, IXL) + 2, var166, 37236);
                            } else {
                              int var163 = pair(IXH, IXL);
                              wMem(var163, 97, 37241);
                            }
                          }
                        }
                      }
                    }

                    DE(8);
                    int var144 = pair(IXH, IXL);
                    int var145 = pair(D, E);
                    int var146 = var144 + var145 & '\uffff';
                    IX(var146);
                  }
                }

                int var251 = A ^ A;
                A = var251;
                F = A;
                wMem(34257, A, 36583);
                int var252 = mem(32973, 36586);
                A = var252;
                int var253 = pair(H, L);
                int var254 = mem(var253, 36589);
                int var255 = A - var254;
                F = var255;
                if (F != 0) {
                  int var259 = pair(H, L) + 1 & '\uffff';
                  HL(var259);
                  int var260 = pair(H, L);
                  int var261 = mem(var260, 36593);
                  int var262 = A - var261;
                  F = var262;
                  if (F != 0) {
                    break label263;
                  }
                }

                int var257 = mem(32982, 36596);
                A = var257;
                int var258 = A - 3 & 255;
                A = var258;
                F = A;
                E = A;
              }
            }

            BC(57342);
            int var7 = in(BC());
            A = var7;
            int var8 = A & 31;
            A = var8;
            F = A;
            int var9 = A | 32;
            A = var9;
            F = A;
            int var10 = A & E;
            A = var10;
            F = A;
            E = A;
            int var11 = mem(34271, 36613);
            A = var11;
            int var12 = A & 2;
            A = var12;
            F = A;
            int var13 = A;
            int var14 = rrc(var13);
            A = var14;
            int var15 = A ^ E;
            A = var15;
            F = A;
            E = A;
            BC(64510);
            int var16 = in(BC());
            A = var16;
            int var17 = A & 31;
            A = var17;
            F = A;
            int var18 = A;
            int var19 = rlc(var18);
            A = var19;
            int var20 = A | 1;
            A = var20;
            F = A;
            int var21 = A & E;
            A = var21;
            F = A;
            E = A;
            B = 231;
            int var22 = in(BC());
            A = var22;
            int var23 = A;
            int var24 = rrc(var23);
            A = var24;
            int var25 = A | 247;
            A = var25;
            F = A;
            int var26 = A & E;
            A = var26;
            F = A;
            E = A;
            B = 239;
            int var27 = in(BC());
            A = var27;
            int var28 = A | 251;
            A = var28;
            F = A;
            int var29 = A & E;
            A = var29;
            F = A;
            E = A;
            int var30 = in(BC());
            A = var30;
            int var31 = A;
            int var32 = rrc(var31);
            A = var32;
            int var33 = A | 251;
            A = var33;
            F = A;
            int var34 = A & E;
            A = var34;
            F = A;
            E = A;
            int var35 = mem(34254, 36658);
            A = var35;
            int var36 = A | A;
            A = var36;
            F = A;
            if (F != 0) {
              BC(31);
              int var135 = in(BC());
              A = var135;
              int var136 = A & 3;
              A = var136;
              F = A;
              A = ~A;
              F = A;
              int var137 = A & E;
              A = var137;
              F = A;
              E = A;
            }

            C = 0;
            A = E;
            int var37 = A & 42;
            A = var37;
            F = A;
            int var38 = A - 42;
            F = var38;
            if (F != 0) {
              C = 4;
              int var134 = A ^ A;
              A = var134;
              F = A;
              wMem(34272, A, 36686);
            }

            A = E;
            int var39 = A & 21;
            A = var39;
            F = A;
            int var40 = A - 21;
            F = var40;
            if (F != 0) {
              int var132 = C | 8;
              C = var132;
              int var133 = A ^ A;
              A = var133;
              F = A;
              wMem(34272, A, 36699);
            }

            int var41 = mem(34256, 36702);
            A = var41;
            int var42 = A + C & 255;
            A = var42;
            F = A;
            C = A;
            B = 0;
            HL(33825);
            int var43 = pair(H, L);
            int var44 = pair(B, C);
            int var45 = var43 + var44 & '\uffff';
            HL(var45);
            int var46 = pair(H, L);
            int var47 = mem(var46, 36713);
            A = var47;
            wMem(34256, A, 36714);
            BC(32510);
            int var48 = in(BC());
            A = var48;
            int var49 = A & 31;
            A = var49;
            F = A;
            int var50 = A - 31;
            F = var50;
            if (F == 0) {
              B = 239;
              int var126 = in(BC());
              A = var126;
              int var127 = A & 1;
              F = var127;
              if (F != 0) {
                int var128 = mem(34254, 36736);
                A = var128;
                int var129 = A | A;
                A = var129;
                F = A;
                if (F == 0) {
                  break label246;
                }

                BC(31);
                int var130 = in(BC());
                A = var130;
                int var131 = A & 16;
                F = var131;
                if (F == 0) {
                  break label246;
                }
              }
            }

            int var51 = mem(34271, 36751);
            A = var51;
            int var52 = A & 2;
            F = var52;
            if (F == 0) {
              int var116 = A ^ A;
              A = var116;
              F = A;
              wMem(34261, A, 36759);
              wMem(34272, A, 36762);
              int var117 = A + 1 & 255;
              A = var117;
              F = A;
              wMem(34257, A, 36766);
              int var118 = mem(34262, 36769);
              A = var118;
              int var119 = A + -1 & 255;
              A = var119;
              F = A;
              int var120 = A & 128;
              F = var120;
              if (F == 0) {
                A = 240;
                wMem(34262, A, 36779);
                int var121 = mem(34255, 36782);
                A = var121;
                int var122 = A & 240;
                A = var122;
                F = A;
                wMem(34255, A, 36787);
                HL(34256);
                int var123 = pair(H, L);
                int var124 = mem(var123, 36793) | 2;
                int var125 = pair(H, L);
                wMem(var125, var124, 36793);
                return;
              }
            }
            break label246;
          }

          int var268 = mem(34257, 36450);
          A = var268;
          int var269 = A - 1;
          F = var269;
          if (F != 0) {
            HL(34256);
            int var270 = pair(H, L);
            int var271 = mem(var270, 36461) & -3;
            int var272 = pair(H, L);
            wMem(var272, var271, 36461);
            int var273 = mem(34257, 36463);
            A = var273;
            int var274 = A | A;
            A = var274;
            F = A;
            if (F == 0) {
              A = 2;
              wMem(34257, A, 36536);
              return;
            }

            int var275 = A + 1 & 255;
            A = var275;
            F = A;
            int var276 = A - 16;
            F = var276;
            if (F == 0) {
              A = 12;
            }

            wMem(34257, A, 36477);
            int var277 = A;
            int var278 = rlc(var277);
            A = var278;
            int var279 = A;
            int var280 = rlc(var279);
            A = var280;
            int var281 = A;
            int var282 = rlc(var281);
            A = var282;
            int var283 = A;
            int var284 = rlc(var283);
            A = var284;
            D = A;
            C = 32;
            int var285 = mem(32990, 36487);
            A = var285;

            do {
              int var286 = A ^ 24;
              A = var286;
              F = A;
              B = D;

              do {
                int var287 = B + -1 & 255;
                B = var287;
              } while (B != 0);

              int var288 = C + -1 & 255;
              C = var288;
              F = C;
            } while (F != 0);

            int var289 = mem(34255, 36500);
            A = var289;
            int var290 = A + 8 & 255;
            A = var290;
            F = A;
            wMem(34255, A, 36505);
            int var291 = A & 240;
            A = var291;
            F = A;
            L = A;
            int var292 = A ^ A;
            A = var292;
            F = A;
            int var293 = L;
            carry= 0;//FIXME
            int var294 = rl(var293);
            L = var294;
            int var295 = (A + 92 + carry) & 255; //FIXME
            A = var295;
            F = A;
            H = A;
            int var296 = mem(34259, 36517);
            A = var296;
            int var297 = A & 31;
            A = var297;
            F = A;
            int var298 = A | L;
            A = var298;
            F = A;
            L = A;
            int var299 = pair(H, L);
            wMem16(34259, var299, 36524);
            return;
          }
        }

        int var53 = mem(34256, 36796);
        A = var53;
        int var54 = A & 2;
        A = var54;
        F = A;
        if (F == 0) {
          return;
        }

        int var55 = mem(34262, 36802);
        A = var55;
        int var56 = A + -1 & 255;
        A = var56;
        F = A;
        int var57 = A & 128;
        F = var57;
        if (F == 0) {
          return;
        }

        int var58 = mem(34256, 36809);
        A = var58;
        int var59 = A & 1;
        A = var59;
        F = A;
        if (F != 0) {
          int var63 = mem(34258, 36817);
          A = var63;
          int var64 = A | A;
          A = var64;
          F = A;
          if (F != 0) {
            int var115 = A + -1 & 255;
            A = var115;
            F = A;
            wMem(34258, A, 36824);
            return;
          }

          int var65 = mem(34257, 36828);
          A = var65;
          BC(0);
          int var66 = A - 0;
          F = var66;
          if (F == 0) {
            int var100 = mem16(34259, 36838);
            HL(var100);
            BC(0);
            int var101 = mem(32986, 36844);
            A = var101;
            int var102 = A + -1 & 255;
            A = var102;
            F = A;
            int var103 = A | 161;
            A = var103;
            F = A;
            int var104 = A ^ 224;
            A = var104;
            F = A;
            E = A;
            D = 0;
            int var105 = pair(H, L);
            int var106 = pair(D, E);
            int var107 = var105 + var106 & '\uffff';
            HL(var107);
            int var108 = mem(32964, 36856);
            A = var108;
            int var109 = pair(H, L);
            int var110 = mem(var109, 36859);
            int var111 = A - var110;
            F = var111;
            if (F == 0) {
              BC(32);
              int var113 = mem(32986, 36865);
              A = var113;
              int var114 = A | A;
              A = var114;
              F = A;
              if (F == 0) {
                BC(65504);
              }
            }
          }
//FIXME
          int var67 = mem16(34259, 36874);
          HL(var67);
          A = L;
          int var68 = A & 31;
          A = var68;
          F = A;
          if (F==0){ //FIXME
            $38026();
            doReturn = 1;
            return;
          }
          int var69 = pair(H, L);
          int var70 = pair(B, C);
          int var71 = var69 + var70 & '\uffff';
          HL(var71);
          HL(HL()-1 &0xffff); //FIXED
          DE(32);
          int var72 = pair(H, L);
          int var73 = pair(D, E);
          int var74 = var72 + var73 & '\uffff';
          HL(var74);
          int var75 = mem(32946, 36889  );
          A = var75;
          int var76 = pair(H, L);
          int var77 = mem(var76, 36892);
          int var78 = A - var77;
          F = var78;
          if (F == 0) {
            return;
          }

          int var80 = mem(34255, 36894);
          A = var80;
          C = C >> 1;
          int var81 = A + C & 255;
          A = var81;
          F = A;
          B = A;
          int var82 = A & 15;
          A = var82;
          F = A;
          if (F != 0) {
            int var88 = mem(32946, 36905);
            A = var88;
            int var89 = pair(H, L);
            int var90 = pair(D, E);
            int var91 = var89 + var90 & '\uffff';
            HL(var91);
            int var92 = pair(H, L);
            int var93 = mem(var92, 36909);
            int var94 = A - var93;
            F = var94;
            if (F == 0) {
              return;
            }

            int var96 = A | A;
            A = var96;
            carry =0;

            F = A;
            int var97 = pair(H, L);
            int var98 = pair(D, E);
            int var99 = ((var97 - var98) + carry) & '\uffff';
            HL(var99);
          }

          int var83 = A | A;
          A = var83;
          carry =0;

          F = A;
          int var84 = pair(H, L);
          int var85 = pair(D, E);
          int var86 = ((var84 - var85) + carry) & '\uffff';
          HL(var86);
          int var87 = pair(H, L);
          wMem16(34259, var87, 36917);
          A = B;
          wMem(34255, A, 36921);
          A = 3;
          wMem(34258, A, 36926);
          return;
        }

        int var60 = mem(34258, 36930);
        A = var60;
        int var61 = A - 3;
        F = var61;
        if (F != 0) {
          int var136 = A + 1 & 255;
          A = var136;
          F = A;
          wMem(34258, A, 36938);
          return;
        }

        int var62 = mem(34257, 36942);
        A = var62;
        BC(0);
        int var63 = A | A;
        A = var63;
        F = A;
        if (F == 0) {
          int var121 = mem16(34259, 36951);
          HL(var121);
          int var122 = mem(32986, 36954);
          A = var122;
          int var123 = A + -1 & 255;
          A = var123;
          F = A;
          int var124 = A | 157;
          A = var124;
          F = A;
          int var125 = A ^ 191;
          A = var125;
          F = A;
          E = A;
          D = 0;
          int var126 = pair(H, L);
          int var127 = pair(D, E);
          int var128 = var126 + var127 & '\uffff';
          HL(var128);
          int var129 = mem(32964, 36966);
          A = var129;
          int var130 = pair(H, L);
          int var131 = mem(var130, 36969);
          int var132 = A - var131;
          F = var132;
          if (F == 0) {
            BC(32);
            int var134 = mem(32986, 36975);
            A = var134;
            int var135 = A | A;
            A = var135;
            F = A;
            if (F != 0) {
              BC(65504);
            }
          }
        }

        int var64 = mem16(34259, 36984);
        HL(var64);
        int var65 = pair(H, L);
        int var66 = pair(B, C);
        int var67 = var65 + var66 & '\uffff';
        HL(var67);
        int var68 = pair(H, L) + 1 & '\uffff';
        HL(var68);
        int var69 = pair(H, L) + 1 & '\uffff';
        HL(var69);
        A = L;
        int var70 = A & 31;
        A = var70;
        F = A;
        if (F != 0) {
          DE(32);
          int var85 = mem(32946, 36999);
          A = var85;
          int var86 = pair(H, L);
          int var87 = pair(D, E);
          int var88 = var86 + var87 & '\uffff';
          HL(var88);
          int var89 = pair(H, L);
          int var90 = mem(var89, 37003);
          int var91 = A - var90;
          F = var91;
          if (F == 0) {
            return;
          }

          int var93 = mem(34255, 37005);
          A = var93;
          int var94 = C;
          int var95 = var94 >> 1;
          C = var95;
          int var96 = A + C & 255;
          A = var96;
          F = A;
          B = A;
          int var97 = A & 15;
          A = var97;
          F = A;
          if (F != 0) {
            int var109 = mem(32946, 37016);
            A = var109;
            int var110 = pair(H, L);
            int var111 = pair(D, E);
            int var112 = var110 + var111 & '\uffff';
            HL(var112);
            int var113 = pair(H, L);
            int var114 = mem(var113, 37020);
            int var115 = A - var114;
            F = var115;
            if (F == 0) {
              return;
            }

            int var117 = A | A;
            A = var117;
            carry =0;

            F = A;
            int var118 = pair(H, L);
            int var119 = pair(D, E);
            int var120 = ((var118 - var119) + carry) & '\uffff';
            HL(var120);
          }

          int var98 = mem(32946, 37025);
          A = var98;
          int var99 = A | A;
          A = var99;
          carry =0;

          F = A;
          int var100 = pair(H, L);
          int var101 = pair(D, E);
          int var102 = ((var100 - var101) + carry) & '\uffff';
          HL(var102);
          int var103 = pair(H, L);
          int var104 = mem(var103, 37031);
          int var105 = A - var104;
          F = var105;
          if (F == 0) {
            return;
          }

          HL(HL()-1&0xFFFF); //FIXED

          int var107 = pair(H, L);
          wMem16(34259, var107, 37034);
          int var108 = A ^ A;
          A = var108;
          F = A;
          wMem(34258, A, 37038);
          A = B;
          wMem(34255, A, 37042);
          return;
        }
      }

      int var305 = mem(33004, 38098);
      A = var305;
      wMem(33824, A, 38101);
      int var306 = A ^ A;
      A = var306;
      F = A;
      wMem(34255, A, 38105);
      int var307 = mem(34257, 38108);
      A = var307;
      int var308 = A - 11;
      F = var308;
      if (F < 0) {
        A = 2;
        wMem(34257, A, 38117);
      }

      int var309 = mem(34259, 38120);
      A = var309;
      int var310 = A & 31;
      A = var310;
      F = A;
      wMem(34259, A, 38125);
      A = 92;
      wMem(34260, A, 38130);
      doReturn= 1;
      return;
    }

    int var352 = mem(34255, 36540);
    A = var352;
    int var353 = A + 16 & 255;
    A = var353;
    F = A;
    int var354 = A & 240;
    A = var354;
    F = A;
    wMem(34255, A, 36547);
    $36508();
    A = 2;
    wMem(34257, A, 36555);
    HL(34256);
    int var355 = pair(H, L);
    int var356 = mem(var355, 36561) & -3;
    int var357 = pair(H, L);
    wMem(var357, var356, 36561);
  }

  public void $38026() {
    int var1 = this.mem(33001, 38026);
    super.A = var1;
    this.wMem(33824, super.A, 38029);
    int var2 = this.mem(34259, 38032);
    super.A = var2;
    int var3 = super.A | 31;
    super.A = var3;
    super.F = super.A;
    int var4 = super.A & 254;
    super.A = var4;
    super.F = super.A;
    this.wMem(34259, super.A, 38039);
  }

  public void $34762() {
    label205:
    while (true) {
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
        super.F = super.L;
      } while (super.F != 0);

      this.HL(34274);
      int var13 = this.HL();
      int var14 = this.mem(var13, 34833) | 1;
      int var15 = this.HL();
      this.wMem(var15, var14, 34833);

      label197:
      while (true) {
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
          super.F = super.A;
          if (super.F != 0) {
            int var239 = super.A - 211;
            super.F = var239;
            if (super.F != 0) {
              int var240 = super.A - 9;
              super.F = var240;
              if (super.F != 0) {
                int var241 = super.A - 45;
                super.F = var241;
                if (super.F != 0) {
                  int var242 = super.A - 36;
                  super.F = var242;
                  if (super.F != 0) {
                    super.C = 0;
                    int var243 = super.A - 8;
                    super.F = var243;
                    if (super.F != 0) {
                      int var256 = super.A - 41;
                      super.F = var256;
                      if (super.F != 0) {
                        int var257 = super.A - 44;
                        super.F = var257;
                        if (super.F != 0) {
                          int var259 = super.A - 5;
                          super.F = var259;
                          if (super.F != 0) {
                            super.C = 16;
                          }
                        } else {
                          super.A = 37;
                          int var258 = this.DE();
                          this.wMem(var258, super.A, 34928);
                        }
                      }
                    }

                    super.A = super.E;
                    int var244 = super.A & 1;
                    super.A = var244;
                    super.F = super.A;
                    int var245 = super.A;
                    int var246 = this.rlc(var245);
                    super.A = var246;
                    int var247 = super.A;
                    int var248 = this.rlc(var247);
                    super.A = var248;
                    int var249 = super.A;
                    int var250 = this.rlc(var249);
                    super.A = var250;
                    int var251 = super.A | super.C;
                    super.A = var251;
                    super.F = super.A;
                    super.C = super.A;
                    super.B = 0;
                    this.HL(33841);
                    int var252 = this.HL();
                    int var253 = this.BC();
                    int var254 = var252 + var253 & '\uffff';
                    this.HL(var254);
                    int lastDE = DE();
                    int var255 = super.D & 1;
                    super.F = var255;
                    super.D = 64;
                    if (super.F != 0) {
                      super.D = 72;
                    }

                    super.B = 8;
                    this.$38555();
                    DE(lastDE);
                  }
                }
              }
            }
          }

          int var21 = this.DE() + 1 & '\uffff';
          this.DE(var21);
          super.A = super.D;
          int var22 = super.A - 90;
          super.F = var22;
        } while (super.F != 0);

        this.BC(31);
        int var23 = super.A ^ super.A;
        super.A = var23;
        super.F = super.A;

        do {
          int var24 = this.in(BC());
          super.E = var24;
          int var25 = super.A | super.E;
          super.A = var25;
          super.F = super.A;
          int var26 = super.B + -1 & 255;
          super.B = var26;
        } while (super.B != 0);

        int var27 = super.A & 32;
        super.A = var27;
        super.F = super.A;
        if (super.F == 0) {
          super.A = 1;
          this.wMem(34254, super.A, 34981);
        }

        this.HL(34299);
        this.$38562();
        if (super.F != 0) {
          break;
        }

        int var224 = super.A ^ super.A;
        super.A = var224;
        super.F = super.A;
        this.wMem(34276, super.A, 34994);

        while (true) {
          this.$35563();
          this.HL(23136);
          this.DE(23137);
          this.BC(31);
          int var225 = this.HL();
          this.wMem(var225, 79, 35009);
          this.ldir();
          int var226 = this.mem(34276, 35013);
          super.A = var226;
          this.IX(33876);
          super.E = super.A;
          super.D = 0;
          int var227 = this.IX();
          int var228 = this.DE();
          int var229 = var227 + var228 & '\uffff';
          this.IX(var229);
          this.DE(20576);
          super.C = 32;
          this.$38528();
          int var230 = this.mem(34276, 35033);
          super.A = var230;
          int var231 = super.A & 31;
          super.A = var231;
          super.F = super.A;
          int var232 = super.A + 50 & 255;
          super.A = var232;
          super.F = super.A;
          this.$38622();
          this.BC(45054);
          int var233 = this.in(BC());
          super.A = var233;
          int var234 = super.A & 1;
          super.A = var234;
          super.F = super.A;
          int var235 = super.A - 1;
          super.F = var235;
          if (super.F != 0) {
            break label197;
          }

          int var236 = this.mem(34276, 35054);
          super.A = var236;
          int var237 = super.A + 1 & 255;
          super.A = var237;
          super.F = super.A;
          int var238 = super.A - 224;
          super.F = var238;
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

      while (true) {
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
          int var41 = super.A + -1 & 255;
          super.A = var41;
          super.F = super.A;
        } while (super.F != 0);

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

        while (true) {
          label215:
          {
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
            int var46 = super.A - 3;
            super.F = var46;
            if (super.F != 0) {
              this.$36307();
              if (doReturn == 1) {
                doReturn= 0;
                break;
              }
            }

            int var47 = this.mem(34255, 35281);
            super.A = var47;
            int var48 = super.A - 225;
            super.F = var48;
            if (super.F >= 0) {
              this.$38064();
            }

            int var49 = this.mem(34271, 35289);
            super.A = var49;
            int var50 = super.A - 3;
            super.F = var50;
            if (super.F != 0) {
              this.$38344();
            }

            int var51 = this.mem(34271, 35297);
            super.A = var51;
            int var52 = super.A - 2;
            super.F = var52;
            if (super.F == 0) {
              this.$38276();
            }

            this.$38196();
            this.$37310();
            this.$38137();
            this.$37841();
            this.HL(24576);
            this.DE(16384);
            this.BC(4096);
            this.ldir();
            int var53 = this.mem(34271, 35328);
            super.A = var53;
            int var54 = super.A & 2;
            super.A = var54;
            super.F = super.A;
            int var55 = super.A;
            int var56 = this.rrc(var55);
            super.A = var56;
            this.HL(34258);
            int var57 = this.HL();
            int var58 = this.mem(var57, 35337);
            int var59 = super.A | var58;
            super.A = var59;
            int var60 = this.HL();
            int var61 = this.HL();
            super.F = super.A;
            int var62 = this.HL();
            int var63 = this.HL();
            this.wMem(var63, super.A, 35338);
            int var64 = this.mem(34253, 35339);
            super.A = var64;
            int var65 = super.A | super.A;
            super.A = var65;
            super.F = super.A;
            if (super.F != 0) {
              int var215 = super.A + -1 & 255;
              super.A = var215;
              super.F = super.A;
              this.wMem(34253, super.A, 35346);
              int var216 = super.A;
              int var217 = this.rlc(var216);
              super.A = var217;
              int var218 = super.A;
              int var219 = this.rlc(var218);
              super.A = var219;
              int var220 = super.A;
              int var221 = this.rlc(var220);
              super.A = var221;
              int var222 = super.A & 56;
              super.A = var222;
              super.F = super.A;
              this.HL(23552);
              this.DE(23553);
              this.BC(511);
              int var223 = this.HL();
              this.wMem(var223, super.A, 35363);
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
            int var66 = this.mem(34251, 35401);
            super.A = var66;
            int var67 = super.A + 1 & 255;
            super.A = var67;
            super.F = super.A;
            this.wMem(34251, super.A, 35405);
            if (super.F == 0) {
              this.IX(34175);
              int var178 = this.IX() + 4;
              int var179 = this.mem(var178, 35414) + 1;
              this.wMem(var178, var179, 35414);
              int var180 = var179 & 255;
              this.wMem(var178, var180, 35414);
              int var181 = this.IX() + 4;
              int var182 = this.mem(var181, 35417);
              super.A = var182;
              int var183 = super.A - 58;
              super.F = var183;
              if (super.F == 0) {
                int var184 = this.IX() + 4;
                this.wMem(var184, 48, 35424);
                int var185 = this.IX() + 3;
                int var186 = this.mem(var185, 35428) + 1;
                this.wMem(var185, var186, 35428);
                int var187 = var186 & 255;
                this.wMem(var185, var187, 35428);
                int var188 = this.IX() + 3;
                int var189 = this.mem(var188, 35431);
                super.A = var189;
                int var190 = super.A - 54;
                super.F = var190;
                if (super.F == 0) {
                  int var191 = this.IX() + 3;
                  this.wMem(var191, 48, 35438);
                  int var192 = this.IX();
                  int var193 = this.mem(var192, 35442);
                  super.A = var193;
                  int var194 = super.A - 49;
                  super.F = var194;
                  if (super.F == 0) {
                    int var203 = this.IX() + 1;
                    int var204 = this.mem(var203, 35449) + 1;
                    this.wMem(var203, var204, 35449);
                    int var205 = var204 & 255;
                    this.wMem(var203, var205, 35449);
                    int var206 = this.IX() + 1;
                    int var207 = this.mem(var206, 35452);
                    super.A = var207;
                    int var208 = super.A - 51;
                    super.F = var208;
                    if (super.F == 0) {
                      int var209 = this.IX() + 5;
                      int var210 = this.mem(var209, 35459);
                      super.A = var210;
                      int var211 = super.A - 112;
                      super.F = var211;
                      if (super.F == 0) {
                        continue label205;
                      }

                      int var212 = this.IX();
                      this.wMem(var212, 32, 35467);
                      int var213 = this.IX() + 1;
                      this.wMem(var213, 49, 35471);
                      int var214 = this.IX() + 5;
                      this.wMem(var214, 112, 35475);
                    }
                  } else {
                    int var195 = this.IX() + 1;
                    int var196 = this.mem(var195, 35481) + 1;
                    this.wMem(var195, var196, 35481);
                    int var197 = var196 & 255;
                    this.wMem(var195, var197, 35481);
                    int var198 = this.IX() + 1;
                    int var199 = this.mem(var198, 35484);
                    super.A = var199;
                    int var200 = super.A - 58;
                    super.F = var200;
                    if (super.F == 0) {
                      int var201 = this.IX() + 1;
                      this.wMem(var201, 48, 35491);
                      int var202 = this.IX();
                      this.wMem(var202, 49, 35495);
                    }
                  }
                }
              }
            }

            this.BC(65278);
            int var68 = this.in(BC());
            super.A = var68;
            super.E = super.A;
            super.B = 127;
            int var69 = this.in(BC());
            super.A = var69;
            int var70 = super.A | super.E;
            super.A = var70;
            super.F = super.A;
            int var71 = super.A & 1;
            super.A = var71;
            super.F = super.A;
            if (super.F == 0) {
              continue label205;
            }

            int var72 = this.mem(34272, 35515);
            super.A = var72;
            int var73 = super.A + 1 & 255;
            super.A = var73;
            super.F = super.A;
            this.wMem(34272, super.A, 35519);
            if (super.F != 0) {
              super.B = 253;
              int var175 = this.in(BC());
              super.A = var175;
              int var176 = super.A & 31;
              super.A = var176;
              super.F = super.A;
              int var177 = super.A - 31;
              super.F = var177;
              if (super.F == 0) {
                break label215;
              }

              this.DE(0);
            }

            while (true) {
              super.B = 2;
              int var74 = this.in(BC());
              super.A = var74;
              int var75 = super.A & 31;
              super.A = var75;
              super.F = super.A;
              int var76 = super.A - 31;
              super.F = var76;
              if (super.F != 0) {
                this.HL(39424);
                this.DE(23040);
                this.BC(256);
                this.ldir();
                int var77 = this.mem(32990, 35602);
                super.A = var77;
                break;
              }

              int var171 = super.E + 1 & 255;
              super.E = var171;
              super.F = super.E;
              if (super.F == 0) {
                int var172 = super.D + 1 & 255;
                super.D = var172;
                super.F = super.D;
                if (super.F == 0) {
                  int var173 = this.mem(34275, 35553);
                  super.A = var173;
                  int var174 = super.A - 10;
                  super.F = var174;
                  if (super.F != 0) {
                    this.$35563();
                  }
                }
              }
            }
          }

          int var78 = this.mem(34257, 35607);
          super.A = var78;
          int var79 = super.A - 255;
          super.F = var79;
          super.B = 191;
          this.HL(34274);
          int var80 = this.in(BC());
          super.A = var80;
          int var81 = super.A & 31;
          super.A = var81;
          super.F = super.A;
          int var82 = super.A - 31;
          super.F = var82;
          if (super.F != 0) {
            int var165 = this.HL();
            int var166 = this.mem(var165, 35628) & 1;
            super.F = var166;
            if (super.F == 0) {
              int var167 = this.HL();
              int var168 = this.mem(var167, 35632);
              super.A = var168;
              int var169 = super.A ^ 3;
              super.A = var169;
              super.F = super.A;
              int var170 = this.HL();
              this.wMem(var170, super.A, 35635);
            }
          } else {
            int var83 = this.HL();
            int var84 = this.mem(var83, 35638) & -2;
            int var85 = this.HL();
            this.wMem(var85, var84, 35638);
          }

          int var86 = this.HL();
          int var87 = this.mem(var86, 35640) & 2;
          super.F = var87;
          if (super.F == 0) {
            int var138 = super.A ^ super.A;
            super.A = var138;
            super.F = super.A;
            this.wMem(34272, super.A, 35645);
            int var139 = this.mem(34273, 35648);
            super.A = var139;
            int var140 = super.A + 1 & 255;
            super.A = var140;
            super.F = super.A;
            this.wMem(34273, super.A, 35652);
            int var141 = super.A & 126;
            super.A = var141;
            super.F = super.A;
            int var142 = super.A;
            int var143 = this.rrc(var142);
            super.A = var143;
            super.E = super.A;
            super.D = 0;
            this.HL(34399);
            int var144 = this.HL();
            int var145 = this.DE();
            int var146 = var144 + var145 & '\uffff';
            this.HL(var146);
            int var147 = this.mem(34252, 35665);
            super.A = var147;
            int var148 = super.A;
            int var149 = this.rlc(var148);
            super.A = var149;
            int var150 = super.A;
            int var151 = this.rlc(var150);
            super.A = var151;
            int var152 = super.A - 28 & 255;
            super.A = var152;
            super.F = super.A;
            int var153 = -super.A & 255;
            super.A = var153;
            int var154 = this.HL();
            int var155 = this.mem(var154, 35674);
            int var156 = super.A + var155 & 255;
            super.A = var156;
            int var157 = this.HL();
            int var158 = this.HL();
            super.F = super.A;
            int var159 = this.HL();
            super.D = super.A;
            int var160 = this.mem(32990, 35676);
            super.A = var160;
            super.E = super.D;
            this.BC(3);

            while (true) {
              int var161 = super.E + -1 & 255;
              super.E = var161;
              super.F = super.E;
              if (super.F == 0) {
                super.E = super.D;
                int var164 = super.A ^ 24;
                super.A = var164;
                super.F = super.A;
              }

              int var162 = super.B + -1 & 255;
              super.B = var162;
              if (super.B == 0) {
                int var163 = super.C + -1 & 255;
                super.C = var163;
                super.F = super.C;
                if (super.F == 0) {
                  break;
                }
              }
            }
          }

          this.BC(61438);
          int var88 = this.in(BC());
          super.A = var88;
          int var89 = super.A & 2;
          super.F = var89;
          if (super.F == 0) {
            int var128 = super.A & 16;
            super.A = var128;
            super.F = super.A;
            int var129 = super.A ^ 16;
            super.A = var129;
            super.F = super.A;
            int var130 = super.A;
            int var131 = this.rlc(var130);
            super.A = var131;
            super.D = super.A;
            int var132 = this.mem(34275, 35712);
            super.A = var132;
            int var133 = super.A - 10;
            super.F = var133;
            if (super.F == 0) {
              this.BC(63486);
              int var134 = this.in(BC());
              super.A = var134;
              int var135 = ~super.A;
              super.A = var135;
              super.F = super.A;
              int var136 = super.A & 31;
              super.A = var136;
              super.F = super.A;
              int var137 = super.A | super.D;
              super.A = var137;
              super.F = super.A;
              this.wMem(33824, super.A, 35729);
              break;
            }
          }

          int var90 = this.mem(34275, 35735);
          super.A = var90;
          int var91 = super.A - 10;
          super.F = var91;
          if (super.F != 0) {
            int var92 = this.mem(33824, 35743);
            super.A = var92;
            int var93 = super.A - 28;
            super.F = var93;
            if (super.F == 0) {
              int var94 = this.mem(34255, 35751);
              super.A = var94;
              int var95 = super.A - 208;
              super.F = var95;
              if (super.F == 0) {
                int var96 = this.mem(34275, 35759);
                super.A = var96;
                int var97 = super.A;
                int var98 = this.rlc(var97);
                super.A = var98;
                super.E = super.A;
                super.D = 0;
                this.IX(34279);
                int var99 = this.IX();
                int var100 = this.DE();
                int var101 = var99 + var100 & '\uffff';
                this.IX(var101);
                this.BC(64510);
                int var102 = this.in(BC());
                super.A = var102;
                int var103 = super.A & 31;
                super.A = var103;
                super.F = super.A;
                int var104 = this.IX();
                int var105 = this.mem(var104, 35779);
                int var106 = super.A - var105;
                super.F = var106;
                int var107 = this.IX();
                if (super.F != 0) {
                  int var122 = super.A - 31;
                  super.F = var122;
                  if (super.F != 0) {
                    int var123 = this.IX();
                    int var124 = this.mem(var123, 35789);
                    int var125 = super.A - var124;
                    super.F = var125;
                    int var126 = this.IX();
                    if (super.F != 0) {
                      int var127 = super.A ^ super.A;
                      super.A = var127;
                      super.F = super.A;
                      this.wMem(34275, super.A, 35796);
                    }
                  }
                } else {
                  super.B = 223;
                  int var108 = this.in(BC());
                  super.A = var108;
                  int var109 = super.A & 31;
                  super.A = var109;
                  super.F = super.A;
                  int var110 = this.IX() + 1;
                  int var111 = this.mem(var110, 35808);
                  int var112 = super.A - var111;
                  super.F = var112;
                  int var113 = this.IX() + 1;
                  if (super.F != 0) {
                    int var116 = super.A - 31;
                    super.F = var116;
                    if (super.F != 0) {
                      int var117 = this.IX();
                      int var118 = this.mem(var117, 35818);
                      int var119 = super.A - var118;
                      super.F = var119;
                      int var120 = this.IX();
                      if (super.F != 0) {
                        int var121 = super.A ^ super.A;
                        super.A = var121;
                        super.F = super.A;
                        this.wMem(34275, super.A, 35825);
                      }
                    }
                  } else {
                    int var114 = this.mem(34275, 35831);
                    super.A = var114;
                    int var115 = super.A + 1 & 255;
                    super.A = var115;
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

  public void $36508() {
    int var1 = A & 240;
    A = var1;
    F = A;
    L = A;
    A = A ^ A;
    F = A;
    //FIXME:
    L = rlc(L) & 0xFE;
    //FIXME:
    A = (A + carry + 92) & 255;
    F = A;
    H = A;
    int var6 = mem(34259);
    A = var6;
    int var7 = A & 31;
    A = var7;
    F = A;
    int var8 = A | L;
    A = var8;
    F = A;
    L = A;
    int var9 = pair(H, L);
    wMem16(34259, var9);
  }

  public void $35563() {
    HL(22528);
    int var1 = pair(H, L);
    int var2 = mem(var1);
    A = var2;
    int var3 = A & 7;
    A = var3;
    F = A;

    do {
      int var4 = pair(H, L);
      int var5 = mem(var4);
      A = var5;
      int var6 = A + 3 & 255;
      A = var6;
      F = A;
      int var7 = A & 7;
      A = var7;
      F = A;
      D = A;
      int var8 = pair(H, L);
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
      int var13 = pair(H, L);
      wMem(var13, A);
      int var14 = pair(H, L) + 1 & '\uffff';
      HL(var14);
      A = H;
      int var15 = A - 91;
      F = var15;
    } while (F != 0);

  }

  public void $36147() {
    this.$36203();
    this.IX(24064);
    super.A = 112;
    this.wMem(36189, super.A, 36156);
    this.$36171(112);
    this.IX(24320);
    super.A = 120;
    this.wMem(36189, super.A, 36168);
    $36171(120);
  }

  private void $36171(int d) {
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
      super.D = d;

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
        super.F = super.D;
        int var8 = super.B + -1 & 255;
        super.B = var8;
      } while(super.B != 0);

      int var9 = this.IX() + 1 & '\uffff';
      this.IX(var9);
      int var10 = super.C + 1 & 255;
      super.C = var10;
      super.F = super.C;
    } while(super.F != 0);
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
      super.F = super.A;
    } while(super.F == 0);

    int var27 = this.mem(32985, 36240);
    super.A = var27;
    int var28 = super.A | super.A;
    super.A = var28;
    super.F = super.A;
    if (super.F != 0) {
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
        int var48 = super.B + -1 & 255;
        super.B = var48;
      } while(super.B != 0);
    }

    int var29 = this.mem(32989, 36257);
    super.A = var29;
    int var30 = super.A | super.A;
    super.A = var30;
    super.F = super.A;
    if (super.F != 0) {
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
        int var43 = super.B + -1 & 255;
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

  public void $37056() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1, 37060);
      super.A = var2;
      int var3 = super.A - 255;
      super.F = var3;
      if (super.F == 0) {
        return;
      }

      int var4 = super.A & 3;
      super.A = var4;
      super.F = super.A;
      if (super.F != 0) {
        int var8 = super.A - 1;
        super.F = var8;
        if (super.F != 0) {
          int var45 = super.A - 2;
          super.F = var45;
          if (super.F != 0) {
            int var79 = this.IX();
            int var80 = this.mem(var79, 37081) & 128;
            super.F = var80;
            if (super.F != 0) {
              int var101 = this.IX() + 1;
              int var102 = this.mem(var101, 37087);
              super.A = var102;
              int var103 = super.A & 128;
              super.F = var103;
              if (super.F != 0) {
                int var107 = super.A - 2 & 255;
                super.A = var107;
                super.F = super.A;
                int var108 = super.A - 148;
                super.F = var108;
                if (super.F < 0) {
                  int var109 = super.A - 2 & 255;
                  super.A = var109;
                  super.F = super.A;
                  int var110 = super.A - 128;
                  super.F = var110;
                  if (super.F == 0) {
                    int var111 = super.A ^ super.A;
                    super.A = var111;
                    super.F = super.A;
                  }
                }
              } else {
                int var104 = super.A + 2 & 255;
                super.A = var104;
                super.F = super.A;
                int var105 = super.A - 18;
                super.F = var105;
                if (super.F < 0) {
                  int var106 = super.A + 2 & 255;
                  super.A = var106;
                  super.F = super.A;
                }
              }
            } else {
              int var81 = this.IX() + 1;
              int var82 = this.mem(var81, 37119);
              super.A = var82;
              int var83 = super.A & 128;
              super.F = var83;
              if (super.F == 0) {
                int var97 = super.A - 2 & 255;
                super.A = var97;
                super.F = super.A;
                int var98 = super.A - 20;
                super.F = var98;
                if (super.F < 0) {
                  int var99 = super.A - 2 & 255;
                  super.A = var99;
                  super.F = super.A;
                  int var100 = super.A | super.A;
                  super.A = var100;
                  super.F = super.A;
                  if (super.F == 0) {
                    super.A = 128;
                  }
                }
              } else {
                int var84 = super.A + 2 & 255;
                super.A = var84;
                super.F = super.A;
                int var85 = super.A - 146;
                super.F = var85;
                if (super.F < 0) {
                  int var96 = super.A + 2 & 255;
                  super.A = var96;
                  super.F = super.A;
                }
              }
            }

            int var86 = this.IX() + 1;
            this.wMem(var86, super.A, 37149);
            int var87 = super.A & 127;
            super.A = var87;
            super.F = super.A;
            int var88 = this.IX() + 7;
            int var89 = this.mem(var88, 37154);
            int var90 = super.A - var89;
            super.F = var90;
            int var91 = this.IX() + 7;
            if (super.F == 0) {
              int var92 = this.IX();
              int var93 = this.mem(var92, 37160);
              super.A = var93;
              int var94 = super.A ^ 128;
              super.A = var94;
              super.F = super.A;
              int var95 = this.IX();
              this.wMem(var95, super.A, 37165);
            }
          } else {
            label81: {
              int var46 = this.IX();
              int var47 = this.mem(var46, 37247);
              super.A = var47;
              int var48 = super.A ^ 8;
              super.A = var48;
              super.F = super.A;
              int var49 = this.IX();
              this.wMem(var49, super.A, 37252);
              int var50 = super.A & 24;
              super.A = var50;
              super.F = super.A;
              if (super.F != 0) {
                int var75 = this.IX();
                int var76 = this.mem(var75, 37259);
                super.A = var76;
                int var77 = super.A + 32 & 255;
                super.A = var77;
                super.F = super.A;
                int var78 = this.IX();
                this.wMem(var78, super.A, 37264);
              }

              int var51 = this.IX() + 3;
              int var52 = this.mem(var51, 37267);
              super.A = var52;
              int var53 = this.IX() + 4;
              int var54 = this.mem(var53, 37270);
              int var55 = super.A + var54 & 255;
              super.A = var55;
              int var56 = this.IX() + 4;
              int var57 = this.IX() + 4;
              super.F = super.A;
              int var58 = this.IX() + 4;
              int var59 = this.IX() + 3;
              this.wMem(var59, super.A, 37273);
              int var60 = this.IX() + 7;
              int var61 = this.mem(var60, 37276);
              int var62 = super.A - var61;
              super.F = var62;
              int var63 = this.IX() + 7;
              if (super.F < 0) {
                int var68 = this.IX() + 6;
                int var69 = this.mem(var68, 37281);
                int var70 = super.A - var69;
                super.F = var70;
                int var71 = this.IX() + 6;
                if (super.F != 0 && super.F >= 0) {
                  break label81;
                }

                int var72 = this.IX() + 6;
                int var73 = this.mem(var72, 37288);
                super.A = var73;
                int var74 = this.IX() + 3;
                this.wMem(var74, super.A, 37291);
              }

              int var64 = this.IX() + 4;
              int var65 = this.mem(var64, 37294);
              super.A = var65;
              int var66 = -super.A & 255;
              super.A = var66;
              int var67 = this.IX() + 4;
              this.wMem(var67, super.A, 37299);
            }
          }
        } else {
          int var9 = this.IX();
          int var10 = this.mem(var9, 37171) & 128;
          super.F = var10;
          if (super.F == 0) {
            int var28 = this.IX();
            int var29 = this.mem(var28, 37177);
            super.A = var29;
            int var30 = super.A - 32 & 255;
            super.A = var30;
            super.F = super.A;
            int var31 = super.A & 127;
            super.A = var31;
            super.F = super.A;
            int var32 = this.IX();
            this.wMem(var32, super.A, 37184);
            int var33 = super.A - 96;
            super.F = var33;
            if (super.F >= 0) {
              int var34 = this.IX() + 2;
              int var35 = this.mem(var34, 37191);
              super.A = var35;
              int var36 = super.A & 31;
              super.A = var36;
              super.F = super.A;
              int var37 = this.IX() + 6;
              int var38 = this.mem(var37, 37196);
              int var39 = super.A - var38;
              super.F = var39;
              int var40 = this.IX() + 6;
              if (super.F != 0) {
                int var42 = this.IX() + 2;
                int var43 = this.mem(var42, 37201) + -1& 255;
                this.wMem(var42, var43, 37201);
                int var10000 = this.IX() + 2;
              } else {
                int var41 = this.IX();
                this.wMem(var41, 129, 37206);
              }
            }
          } else {
            int var11 = this.IX();
            int var12 = this.mem(var11, 37212);
            super.A = var12;
            int var13 = super.A + 32 & 255;
            super.A = var13;
            super.F = super.A;
            int var14 = super.A | 128;
            super.A = var14;
            super.F = super.A;
            int var15 = this.IX();
            this.wMem(var15, super.A, 37219);
            int var16 = super.A - 160;
            super.F = var16;
            if (super.F < 0) {
              int var17 = this.IX() + 2;
              int var18 = this.mem(var17, 37226);
              super.A = var18;
              int var19 = super.A & 31;
              super.A = var19;
              super.F = super.A;
              int var20 = this.IX() + 7;
              int var21 = this.mem(var20, 37231);
              int var22 = super.A - var21;
              super.F = var22;
              int var23 = this.IX() + 7;
              if (super.F != 0) {
                int var25 = this.IX() + 2;
                int var26 = this.mem(var25, 37236) + 1;
                int var27 = var26 & 255;
                this.wMem(var25, var27, 37236);
              } else {
                int var24 = this.IX();
                this.wMem(var24, 97, 37241);
              }
            }
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

  public void $37310() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1, 37314);
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
          int var157 = super.A - 4;
          super.F = var157;
          if (super.F != 0) {
            int var221 = this.IX() + 3;
            int var222 = this.mem(var221, 37334);
            super.E = var222;
            super.D = 130;
            int var223 = this.DE();
            int var224 = this.mem(var223, 37339);
            super.A = var224;
            super.L = super.A;
            int var225 = this.IX() + 2;
            int var226 = this.mem(var225, 37341);
            super.A = var226;
            int var227 = super.A & 31;
            super.A = var227;
            super.F = super.A;
            int var228 = super.A + super.L & 255;
            super.A = var228;
            super.F = super.A;
            super.L = super.A;
            super.A = super.E;
            int var229 = super.A;
            int var230 = this.rlc(var229);
            super.A = var230;
            int var231 = super.A & 1;
            super.A = var231;
            super.F = super.A;
            int var232 = super.A | 92;
            super.A = var232;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var233 = this.IX() + 1;
            int var234 = this.mem(var233, 37358);
            super.A = var234;
            int var235 = super.A & 15;
            super.A = var235;
            super.F = super.A;
            int var236 = super.A + 56 & 255;
            super.A = var236;
            super.F = super.A;
            int var237 = super.A & 71;
            super.A = var237;
            super.F = super.A;
            super.C = super.A;
            int var238 = this.HL();
            int var239 = this.mem(var238, 37368);
            super.A = var239;
            int var240 = super.A & 56;
            super.A = var240;
            super.F = super.A;
            int var241 = super.A ^ super.C;
            super.A = var241;
            super.F = super.A;
            super.C = super.A;
            int var242 = this.HL();
            this.wMem(var242, super.C, 37373);
            int var243 = this.HL() + 1 & '\uffff';
            this.HL(var243);
            int var244 = this.HL();
            this.wMem(var244, super.C, 37375);
            int var245 = this.HL();
            int var246 = this.DE();
            int var247 = var245 + var246 & '\uffff';
            this.HL(var247);
            int var248 = this.HL();
            this.wMem(var248, super.C, 37377);
            int var249 = this.HL() + 1 & '\uffff';
            this.HL(var249);
            int var250 = this.HL();
            this.wMem(var250, super.C, 37379);
            int var251 = this.IX() + 3;
            int var252 = this.mem(var251, 37380);
            super.A = var252;
            int var253 = super.A & 14;
            super.A = var253;
            super.F = super.A;
            if (super.F != 0) {
              int var285 = this.HL();
              int var286 = this.DE();
              int var287 = var285 + var286 & '\uffff';
              this.HL(var287);
              int var288 = this.HL();
              this.wMem(var288, super.C, 37388);
              int var289 = this.HL() + 1 & '\uffff';
              this.HL(var289);
              int var290 = this.HL();
              this.wMem(var290, super.C, 37390);
            }

            super.C = 1;
            int var254 = this.IX() + 1;
            int var255 = this.mem(var254, 37393);
            super.A = var255;
            int var256 = this.IX();
            int var257 = this.mem(var256, 37396);
            int var258 = super.A & var257;
            super.A = var258;
            int var259 = this.IX();
            int var260 = this.IX();
            super.F = super.A;
            int var261 = this.IX();
            int var262 = this.IX() + 2;
            int var263 = this.mem(var262, 37399);
            int var264 = super.A | var263;
            super.A = var264;
            int var265 = this.IX() + 2;
            int var266 = this.IX() + 2;
            super.F = super.A;
            int var267 = this.IX() + 2;
            int var268 = super.A & 224;
            super.A = var268;
            super.F = super.A;
            super.E = super.A;
            int var269 = this.IX() + 5;
            int var270 = this.mem(var269, 37405);
            super.D = var270;
            super.H = 130;
            int var271 = this.IX() + 3;
            int var272 = this.mem(var271, 37410);
            super.L = var272;
            int var273 = this.IX() + 2;
            int var274 = this.mem(var273, 37413);
            super.A = var274;
            int var275 = super.A & 31;
            super.A = var275;
            super.F = super.A;
            int var276 = this.HL();
            int var277 = this.mem(var276, 37418);
            int var278 = super.A | var277;
            super.A = var278;
            int var279 = this.HL();
            int var280 = this.HL();
            super.F = super.A;
            int var281 = this.HL();
            int var282 = this.HL() + 1 & '\uffff';
            this.HL(var282);
            int var283 = this.HL();
            int var284 = this.mem(var283, 37420);
            super.H = var284;
            super.L = super.A;
            this.$37974();
          } else {
            int var158 = this.IX();
            int var159 = this.mem(var158, 37431) & 128;
            super.F = var159;
            if (super.F == 0) {
              int var218 = this.IX() + 4;
              int var219 = this.mem(var218, 37437) + -1;
              this.wMem(var218, var219, 37437);
              int var220 = var219 & 255;
              this.wMem(var218, var220, 37437);
              int var293 = this.IX() + 4;
              super.C = 44;
            } else {
              int var160 = this.IX() + 4;
              int var161 = this.mem(var160, 37444) + 1;
              this.wMem(var160, var161, 37444);
              int var162 = var161 & 255;
              this.wMem(var160, var162, 37444);
              int var294 = this.IX() + 4;
              super.C = 244;
            }

            int var163 = this.IX() + 4;
            int var164 = this.mem(var163, 37449);
            super.A = var164;
            int var165 = super.A - super.C;
            super.F = var165;
            if (super.F != 0) {
              int var166 = super.A & 224;
              super.A = var166;
              super.F = super.A;
              if (super.F == 0) {
                int var167 = this.IX() + 2;
                int var168 = this.mem(var167, 37479);
                super.E = var168;
                super.D = 130;
                int var169 = this.DE();
                int var170 = this.mem(var169, 37484);
                super.A = var170;
                int var171 = this.IX() + 4;
                int var172 = this.mem(var171, 37485);
                int var173 = super.A + var172 & 255;
                super.A = var173;
                int var174 = this.IX() + 4;
                int var175 = this.IX() + 4;
                super.F = super.A;
                int var176 = this.IX() + 4;
                super.L = super.A;
                super.A = super.E;
                int var177 = super.A & 128;
                super.A = var177;
                super.F = super.A;
                int var178 = super.A;
                int var179 = this.rlc(var178);
                super.A = var179;
                int var180 = super.A | 92;
                super.A = var180;
                super.F = super.A;
                super.H = super.A;
                int var181 = this.IX() + 5;
                this.wMem(var181, 0, 37496);
                int var182 = this.HL();
                int var183 = this.mem(var182, 37500);
                super.A = var183;
                int var184 = super.A & 7;
                super.A = var184;
                super.F = super.A;
                int var185 = super.A - 7;
                super.F = var185;
                if (super.F == 0) {
                  int var211 = this.IX() + 5;
                  int var212 = this.mem(var211, 37507) + -1;
                  this.wMem(var211, var212, 37507);
                  int var213 = var212 & 255;
                  this.wMem(var211, var213, 37507);
                  int var295 = this.IX() + 5;
                }

                int var186 = this.HL();
                int var187 = this.mem(var186, 37510);
                super.A = var187;
                int var188 = super.A | 7;
                super.A = var188;
                super.F = super.A;
                int var189 = this.HL();
                this.wMem(var189, super.A, 37513);
                int var190 = this.DE() + 1 & '\uffff';
                this.DE(var190);
                int var191 = this.DE();
                int var192 = this.mem(var191, 37515);
                super.A = var192;
                super.H = super.A;
                int var193 = super.H + -1 & 255;
                super.H = var193;
                super.F = super.H;
                int var194 = this.IX() + 6;
                int var195 = this.mem(var194, 37518);
                super.A = var195;
                int var196 = this.HL();
                this.wMem(var196, super.A, 37521);
                int var197 = super.H + 1 & 255;
                super.H = var197;
                super.F = super.H;
                int var198 = this.HL();
                int var199 = this.mem(var198, 37523);
                super.A = var199;
                int var200 = this.IX() + 5;
                int var201 = this.mem(var200, 37524);
                int var202 = super.A & var201;
                super.A = var202;
                int var203 = this.IX() + 5;
                int var204 = this.IX() + 5;
                super.F = super.A;
                int var205 = this.IX() + 5;
                int var206 = this.HL();
                this.wMem(var206, 255, 37530);
                int var207 = super.H + 1 & 255;
                super.H = var207;
                super.F = super.H;
                int var208 = this.IX() + 6;
                int var209 = this.mem(var208, 37533);
                super.A = var209;
                int var210 = this.HL();
                this.wMem(var210, super.A, 37536);
              }
            } else {
              this.BC(640);
              int var214 = this.mem(32990, 37458);
              super.A = var214;

              do {
                int var215 = super.A ^ 24;
                super.A = var215;
                super.F = super.A;

                do {
                  int var216 = super.B + -1 & 255;
                  super.B = var216;
                } while(super.B != 0);

                super.B = super.C;
                int var217 = super.C + -1 & 255;
                super.C = var217;
                super.F = super.C;
              } while(super.F != 0);
            }
          }
        } else {
          this.IY(33280);
          int var9 = this.IX() + 9;
          this.wMem(var9, 0, 37544);
          int var10 = this.IX() + 2;
          int var11 = this.mem(var10, 37548);
          super.A = var11;
          int var12 = this.IX() + 3;
          this.wMem(var12, super.A, 37551);
          int var13 = this.IX() + 5;
          this.wMem(var13, 128, 37554);

          while(true) {
            label107: {
              int var14 = this.IY();
              int var15 = this.mem(var14, 37558);
              super.A = var15;
              int var16 = this.IX() + 3;
              int var17 = this.mem(var16, 37561);
              int var18 = super.A + var17 & 255;
              super.A = var18;
              int var19 = this.IX() + 3;
              int var20 = this.IX() + 3;
              super.F = super.A;
              int var21 = this.IX() + 3;
              super.L = super.A;
              int var22 = this.IY() + 1;
              int var23 = this.mem(var22, 37565);
              super.H = var23;
              int var24 = this.mem(34262, 37568);
              super.A = var24;
              int var25 = super.A | super.A;
              super.A = var25;
              super.F = super.A;
              if (super.F == 0) {
                int var145 = this.IX() + 5;
                int var146 = this.mem(var145, 37574);
                super.A = var146;
                int var147 = this.HL();
                int var148 = this.mem(var147, 37577);
                int var149 = super.A & var148;
                super.A = var149;
                int var150 = this.HL();
                int var151 = this.HL();
                super.F = super.A;
                int var152 = this.HL();
                if (super.F == 0) {
                  break label107;
                }

                int var153 = this.IX() + 9;
                int var154 = this.mem(var153, 37580);
                super.A = var154;
                this.wMem(34262, super.A, 37583);
                int var155 = this.IX() + 11;
                int var156 = this.mem(var155, 37586) | 1;
                this.wMem(var155, var156, 37586);
              }

              int var26 = this.IX() + 9;
              int var27 = this.mem(var26, 37590);
              int var28 = super.A - var27;
              super.F = var28;
              int var29 = this.IX() + 9;
              if (super.F == 0) {
                int var133 = this.IX() + 11;
                int var134 = this.mem(var133, 37595) & 1;
                super.F = var134;
                if (super.F != 0) {
                  int var135 = this.IX() + 3;
                  int var136 = this.mem(var135, 37601);
                  super.B = var136;
                  int var137 = this.IX() + 5;
                  int var138 = this.mem(var137, 37604);
                  super.A = var138;
                  super.C = 1;
                  int var139 = super.A - 4;
                  super.F = var139;
                  if (super.F >= 0) {
                    super.C = 0;
                    int var142 = super.A - 16;
                    super.F = var142;
                    if (super.F >= 0) {
                      int var143 = super.B + -1 & 255;
                      super.B = var143;
                      super.F = super.B;
                      super.C = 3;
                      int var144 = super.A - 64;
                      super.F = var144;
                      if (super.F >= 0) {
                        super.C = 2;
                      }
                    }
                  }

                  int var140 = this.BC();
                  this.wMem16(34258, var140, 37628);
                  super.A = super.IYL;
                  int var141 = super.A - 16 & 255;
                  super.A = var141;
                  super.F = super.A;
                  this.wMem(34255, super.A, 37636);
                  int lastHL = HL();
                  this.$36508();
                  HL(lastHL);
                }
              }
            }

            int var30 = this.IX() + 5;
            int var31 = this.mem(var30, 37646);
            super.A = var31;
            int var32 = this.HL();
            int var33 = this.mem(var32, 37649);
            int var34 = super.A | var33;
            super.A = var34;
            int var35 = this.HL();
            int var36 = this.HL();
            super.F = super.A;
            int var37 = this.HL();
            int var38 = this.HL();
            this.wMem(var38, super.A, 37650);
            int var39 = this.IX() + 9;
            int var40 = this.mem(var39, 37651);
            super.A = var40;
            int var41 = this.IX() + 1;
            int var42 = this.mem(var41, 37654);
            int var43 = super.A + var42 & 255;
            super.A = var43;
            int var44 = this.IX() + 1;
            int var45 = this.IX() + 1;
            super.F = super.A;
            int var46 = this.IX() + 1;
            super.L = super.A;
            int var47 = super.L | 128;
            super.L = var47;
            super.H = 131;
            int var48 = this.HL();
            int var49 = this.mem(var48, 37662);
            super.E = var49;
            super.D = 0;
            int var50 = this.IY();
            int var51 = this.DE();
            int var52 = var50 + var51 & '\uffff';
            this.IY(var52);
            int var53 = super.L & -129;
            super.L = var53;
            int var54 = this.HL();
            int var55 = this.mem(var54, 37669);
            super.A = var55;
            int var56 = super.A | super.A;
            super.A = var56;
            super.F = super.A;
            if (super.F != 0) {
              super.B = super.A;
              int var113 = this.IX() + 1;
              int var114 = this.mem(var113, 37674) & 128;
              super.F = var114;
              if (super.F != 0) {
                do {
                  int var124 = this.IX() + 5;
                  int var125 = this.mem(var124, 37680);
                  int var126 = this.rlc(var125);
                  this.wMem(var124, var126, 37680);
                  int var127 = this.IX() + 5;
                  int var128 = this.mem(var127, 37684) & 1;
                  super.F = var128;
                  if (super.F != 0) {
                    int var130 = this.IX() + 3;
                    int var131 = this.mem(var130, 37690) + -1;
                    this.wMem(var130, var131, 37690);
                    int var132 = var131 & 255;
                    this.wMem(var130, var132, 37690);
                    int var291 = this.IX() + 3;
                  }

                  int var129 = super.B + -1 & 255;
                  super.B = var129;
                } while(super.B != 0);
              } else {
                do {
                  int var115 = this.IX() + 5;
                  int var116 = this.mem(var115, 37697);
                  int var117 = this.rrc(var116);
                  this.wMem(var115, var117, 37697);
                  int var118 = this.IX() + 5;
                  int var119 = this.mem(var118, 37701) & 128;
                  super.F = var119;
                  if (super.F != 0) {
                    int var121 = this.IX() + 3;
                    int var122 = this.mem(var121, 37707) + 1;
                    this.wMem(var121, var122, 37707);
                    int var123 = var122 & 255;
                    this.wMem(var121, var123, 37707);
                    int var10000 = this.IX() + 3;
                  }

                  int var120 = super.B + -1 & 255;
                  super.B = var120;
                } while(super.B != 0);
              }
            }

            int var57 = this.IX() + 9;
            int var58 = this.mem(var57, 37712);
            super.A = var58;
            int var59 = this.IX() + 4;
            int var60 = this.mem(var59, 37715);
            int var61 = super.A - var60;
            super.F = var61;
            int var62 = this.IX() + 4;
            if (super.F == 0) {
              int var63 = this.mem(34262, 37726);
              super.A = var63;
              int var64 = super.A & 128;
              super.F = var64;
              if (super.F != 0) {
                int var107 = super.A + 1 & 255;
                super.A = var107;
                super.F = super.A;
                this.wMem(34262, super.A, 37734);
                int var108 = this.IX() + 11;
                int var109 = this.mem(var108, 37737) & -2;
                this.wMem(var108, var109, 37737);
              } else {
                int var65 = this.IX() + 11;
                int var66 = this.mem(var65, 37743) & 1;
                super.F = var66;
                if (super.F != 0) {
                  int var67 = this.mem(34256, 37749);
                  super.A = var67;
                  int var68 = super.A & 2;
                  super.F = var68;
                  if (super.F != 0) {
                    int var69 = super.A;
                    int var70 = this.rrc(var69);
                    super.A = var70;
                    int var71 = this.IX();
                    int var72 = this.mem(var71, 37757);
                    int var73 = super.A ^ var72;
                    super.A = var73;
                    int var74 = this.IX();
                    int var75 = this.IX();
                    super.F = super.A;
                    int var76 = this.IX();
                    int var77 = super.A;
                    int var78 = this.rlc(var77);
                    super.A = var78;
                    int var79 = super.A;
                    int var80 = this.rlc(var79);
                    super.A = var80;
                    int var81 = super.A & 2;
                    super.A = var81;
                    super.F = super.A;
                    int var82 = super.A + -1 & 255;
                    super.A = var82;
                    super.F = super.A;
                    this.HL(34262);
                    int var83 = this.HL();
                    int var84 = this.mem(var83, 37768);
                    int var85 = super.A + var84 & 255;
                    super.A = var85;
                    int var86 = this.HL();
                    int var87 = this.HL();
                    super.F = super.A;
                    int var88 = this.HL();
                    int var89 = this.HL();
                    this.wMem(var89, super.A, 37769);
                    int var90 = this.mem(33003, 37770);
                    super.A = var90;
                    super.C = super.A;
                    int var91 = this.mem(33824, 37774);
                    super.A = var91;
                    int var92 = super.A - super.C;
                    super.F = var92;
                    if (super.F == 0) {
                      int var103 = this.HL();
                      int var104 = this.mem(var103, 37780);
                      super.A = var104;
                      int var105 = super.A - 12;
                      super.F = var105;
                      if (super.F < 0) {
                        int var106 = this.HL();
                        this.wMem(var106, 12, 37785);
                      }
                    }

                    int var93 = this.HL();
                    int var94 = this.mem(var93, 37787);
                    super.A = var94;
                    int var95 = this.IX() + 4;
                    int var96 = this.mem(var95, 37788);
                    int var97 = super.A - var96;
                    super.F = var97;
                    int var98 = this.IX() + 4;
                    if (super.F >= 0 && super.F != 0) {
                      int var99 = this.HL();
                      this.wMem(var99, 240, 37795);
                      int var100 = this.mem(34255, 37797);
                      super.A = var100;
                      int var101 = super.A & 248;
                      super.A = var101;
                      super.F = super.A;
                      this.wMem(34255, super.A, 37802);
                      int var102 = super.A ^ super.A;
                      super.A = var102;
                      super.F = super.A;
                      this.wMem(34257, super.A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var110 = this.IX() + 9;
            int var111 = this.mem(var110, 37720) + 1;
            this.wMem(var110, var111, 37720);
            int var112 = var111 & 255;
            this.wMem(var110, var112, 37720);
            int var292 = this.IX() + 9;
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

  public void $37841() {
    H = 164;
    int var1 = mem(41983);
    A = var1;
    L = A;

    do {
      int var2 = pair(H, L);
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
        int var9 = pair(H, L);
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
        int var15 = H + 1 & 255;
        H = var15;
        F = H;
        int var16 = pair(H, L);
        int var17 = mem(var16);
        E = var17;
        int var18 = H + -1 & 255;
        H = var18;
        F = H;
        int var19 = pair(D, E);
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
          int var27 = pair(D, E);
          int var28 = mem(var27);
          A = var28;
          int var29 = A & 248;
          A = var29;
          F = A;
          int var30 = A | C;
          A = var30;
          F = A;
          int var31 = pair(D, E);
          wMem(var31, A);
          int var32 = pair(H, L);
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
          int lastHL = pair(H, L); //FIXED:
          HL(32993);
          B = 8;
          $38555();
          HL(lastHL);
        } else {
          IX(34172);

          while (true) {
            int var44 = pair(IXH, IXL) + 2;
            int var45 = mem(var44) + 1;
            wMem(var44, var45);
            int var46 = var45 & 255;
            wMem(var44, var46);
            int var47 = pair(IXH, IXL) + 2;
            int var48 = mem(var47);
            A = var48;
            int var49 = A - 58;
            F = var49;
            if (F != 0) {
              int var50 = mem(32990);
              A = var50;
              C = 128;

              do {
                int var51 = A ^ 24;
                A = var51;
                F = A;
                E = A;
                A = 144;
                int var52 = A - C & 255;
                A = var52;
                F = A;
                B = A;
                A = E;

                do {
                  int var53 = B + -1 & 0xff;
                  B = var53;
                } while (B != 0);

                int var54 = C + -1 & 255;
                C = var54;
                F = C;
                int var55 = C + -1 & 255;
                C = var55;
                F = C;
              } while (F != 0);

              int var56 = mem(34270);
              A = var56;
              int var57 = A + 1 & 255;
              A = var57;
              F = A;
              wMem(34270, A);
              if (F == 0) {
                A = 1;
                wMem(34271, A);
              }

              int var58 = pair(H, L);
              int var59 = mem(var58) & -65;
              int var60 = pair(H, L);
              wMem(var60, var59);
              break;
            }

            int var61 = pair(IXH, IXL) + 2;
            wMem(var61, 48);
          }
        }
      }

      int var8 = L + 1 & 255;
      L = var8;
      F = L;
    } while (F != 0);

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
        int var35 = this.HL();
        int var36 = this.mem(var35, 37981);
        int var37 = super.A & var36;
        super.A = var37;
        int var38 = this.HL();
        int var39 = this.HL();
        super.F = super.A;
        int var40 = this.HL();
        if (super.F != 0) {
          return;
        }

        int var41 = this.DE();
        int var42 = this.mem(var41, 37983);
        super.A = var42;
        int var43 = this.HL();
        int var44 = this.mem(var43, 37984);
        int var45 = super.A | var44;
        super.A = var45;
        int var46 = this.HL();
        int var47 = this.HL();
        super.F = super.A;
        int var48 = this.HL();
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
        int var25 = this.HL();
        super.F = super.A;
        int var26 = this.HL();
        if (super.F != 0) {
          return;
        }

        int var27 = this.DE();
        int var28 = this.mem(var27, 37995);
        super.A = var28;
        int var29 = this.HL();
        int var30 = this.mem(var29, 37996);
        int var31 = super.A | var30;
        super.A = var31;
        int var32 = this.HL();
        int var33 = this.HL();
        super.F = super.A;
        int var34 = this.HL();
      }

      int var10 = this.HL();
      this.wMem(var10, super.A, 37997);
      int var11 = super.L + -1 & 255;
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
      super.F = super.A;
      if (super.F == 0) {
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
        super.F = super.A;
        if (super.F == 0) {
          super.A = super.H;
          int var20 = super.A + 8 & 255;
          super.A = var20;
          super.F = super.A;
          super.H = super.A;
        }
      }

      int var15 = super.B + -1 & 255;
      super.B = var15;
    } while(super.B != 0);

    int var16 = super.A ^ super.A;
    super.A = var16;
    super.F = super.A;
  }

  public void $38137() {
    int var1 = mem16(32983);
    HL(var1);
    A = H;
    int var2 = A & 1;
    A = var2;
    F = A;
    int var3 = A;
    int var4 = rlc(var3);
    A = var4;
    int var5 = A;
    int var6 = rlc(var5);
    A = var6;
    int var7 = A;
    int var8 = rlc(var7);
    A = var8;
    int var9 = A + 112 & 255;
    A = var9;
    F = A;
    H = A;
    E = L;
    D = H;
    int var10 = mem(32985);
    A = var10;
    int var11 = A | A;
    A = var11;
    F = A;
    if (F != 0) {
      B = A;
      int var12 = mem(32982);
      A = var12;
      int var13 = A | A;
      A = var13;
      F = A;
      if (F == 0) {
        int var33 = pair(H, L);
        int var34 = mem(var33);
        A = var34;
        int var35 = A;
        int var36 = rlc(var35);
        A = var36;
        int var37 = A;
        int var38 = rlc(var37);
        A = var38;
        int var39 = H + 1 & 255;
        H = var39;
        F = H;
        int var40 = H + 1 & 255;
        H = var40;
        F = H;
        int var41 = pair(H, L);
        int var42 = mem(var41);
        C = var42;
        int var43 = C;
        int var44 = rrc(var43);
        C = var44;
        int var45 = C;
        int var46 = rrc(var45);
        C = var46;
      } else {
        int var14 = pair(H, L);
        int var15 = mem(var14);
        A = var15;
        int var16 = A;
        int var17 = rrc(var16);
        A = var17;
        int var18 = A;
        int var19 = rrc(var18);
        A = var19;
        int var20 = H + 1 & 255;
        H = var20;
        F = H;
        int var21 = H + 1 & 255;
        H = var21;
        F = H;
        int var22 = pair(H, L);
        int var23 = mem(var22);
        C = var23;
        int var24 = C;
        int var25 = rlc(var24);
        C = var25;
        int var26 = C;
        int var27 = rlc(var26);
        C = var27;
      }

      do {
        int var28 = pair(D, E);
        wMem(var28, A);
        int var29 = pair(H, L);
        wMem(var29, C);
        int var30 = L + 1 & 255;
        L = var30;
        F = L;
        int var31 = E + 1 & 255;
        E = var31;
        F = E;
        int var32 = B + -1 & 0xff;
        B = var32;
      } while (B != 0);

    }
  }

  public void $38196() {
    int var1 = mem(33824);
    A = var1;
    int var2 = A - 35;
    F = var2;
    if (F == 0) {
      int var18 = mem(34271);
      A = var18;
      int var19 = A | A;
      A = var19;
      F = A;
      if (F == 0) {
        int var23 = mem(34251);
        A = var23;
        int var24 = A & 2;
        A = var24;
        F = A;
        int var25 = A;
        int var26 = rrc(var25);
        A = var26;
        int var27 = A;
        int var28 = rrc(var27);
        A = var28;
        int var29 = A;
        int var30 = rrc(var29);
        A = var30;
        int var31 = A;
        int var32 = rrc(var31);
        A = var32;
        int var33 = A | 128;
        A = var33;
        F = A;
        E = A;
        int var34 = mem(34255);
        A = var34;
        int var35 = A - 208;
        F = var35;
        if (F != 0) {
          E = 192;
          int var38 = A - 192;
          F = var38;
          if (F < 0) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974();
        HL(17733);
        int var36 = pair(H, L);
        wMem16(23918, var36);
        HL(1799);
        int var37 = pair(H, L);
        wMem16(23950, var37);
      } else {
        int var20 = mem(34259);
        A = var20;
        int var21 = A & 31;
        A = var21;
        F = A;
        int var22 = A - 6;
        F = var22;
        if (F < 0) {
          A = 2;
          wMem(34271, A);
        }
      }
    } else {
      int var3 = mem(33824);
      A = var3;
      int var4 = A - 33;
      F = var4;
      if (F == 0) {
        int var5 = mem(34251);
        A = var5;
        int var6 = A & 1;
        A = var6;
        F = A;
        int var7 = A;
        int var8 = rrc(var7);
        A = var8;
        int var9 = A;
        int var10 = rrc(var9);
        A = var10;
        int var11 = A;
        int var12 = rrc(var11);
        A = var12;
        E = A;
        int var13 = mem(34271);
        A = var13;
        int var14 = A - 3;
        F = var14;
        if (F == 0) {
          int var17 = E | 64;
          E = var17;
        }

        D = 166;
        IX(33488);
        BC(4124);
        $38504();
        HL(1799);
        int var15 = pair(H, L);
        wMem16(23996, var15);
        int var16 = pair(H, L);
        wMem16(24028, var16);
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
    int var10 = this.mem(var9, 38363);
    int var11 = super.A - var10;
    super.F = var11;
    int var12 = this.HL();
    if (super.F == 0) {
      int var74 = this.mem(34257, 38366);
      super.A = var74;
      int var75 = super.A | super.A;
      super.A = var75;
      super.F = super.A;
      if (super.F == 0) {
        int var76 = this.mem(34258, 38372);
        super.A = var76;
        int var77 = super.A & 3;
        super.A = var77;
        super.F = super.A;
        int var78 = super.A;
        int var79 = this.rlc(var78);
        super.A = var79;
        int var80 = super.A;
        int var81 = this.rlc(var80);
        super.A = var81;
        super.B = super.A;
        int var82 = this.mem(32986, 38380);
        super.A = var82;
        int var83 = super.A & 1;
        super.A = var83;
        super.F = super.A;
        int var84 = super.A + -1 & 255;
        super.A = var84;
        super.F = super.A;
        int var85 = super.A ^ 12;
        super.A = var85;
        super.F = super.A;
        int var86 = super.A ^ super.B;
        super.A = var86;
        super.F = super.A;
        int var87 = super.A & 12;
        super.A = var87;
        super.F = super.A;
        super.B = super.A;
      }
    }

    int var13 = this.mem16(34259, 38392);
    this.HL(var13);
    this.DE(31);
    super.C = 15;
    this.$38430();
    int var14 = this.HL() + 1 & '\uffff';
    this.HL(var14);
    this.$38430();
    int var15 = this.HL();
    int var16 = this.DE();
    int var17 = var15 + var16 & '\uffff';
    this.HL(var17);
    this.$38430();
    int var18 = this.HL() + 1 & '\uffff';
    this.HL(var18);
    this.$38430();
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
    int var41 = super.A - 29;
    super.F = var41;
    if (super.F == 0) {
      super.D = 182;
      super.A = super.E;
      int var73 = super.A ^ 128;
      super.A = var73;
      super.F = super.A;
      super.E = super.A;
    }

    super.B = 16;
    int var42 = this.mem(34259, 38498);
    super.A = var42;
    int var43 = super.A & 31;
    super.A = var43;
    super.F = super.A;
    super.C = super.A;

    do {
      int var44 = this.IX();
      int var45 = this.mem(var44, 38504);
      super.A = var45;
      int var46 = this.IX() + 1;
      int var47 = this.mem(var46, 38507);
      super.H = var47;
      int var48 = super.A | super.C;
      super.A = var48;
      super.F = super.A;
      super.L = super.A;
      int var49 = this.DE();
      int var50 = this.mem(var49, 38512);
      super.A = var50;
      int var51 = this.HL();
      int var52 = this.mem(var51, 38513);
      int var53 = super.A | var52;
      super.A = var53;
      int var54 = this.HL();
      int var55 = this.HL();
      super.F = super.A;
      int var56 = this.HL();
      int var57 = this.HL();
      this.wMem(var57, super.A, 38514);
      int var58 = this.HL() + 1 & '\uffff';
      this.HL(var58);
      int var59 = this.DE() + 1 & '\uffff';
      this.DE(var59);
      int var60 = this.DE();
      int var61 = this.mem(var60, 38517);
      super.A = var61;
      int var62 = this.HL();
      int var63 = this.mem(var62, 38518);
      int var64 = super.A | var63;
      super.A = var64;
      int var65 = this.HL();
      int var66 = this.HL();
      super.F = super.A;
      int var67 = this.HL();
      int var68 = this.HL();
      this.wMem(var68, super.A, 38519);
      int var69 = this.IX() + 1 & '\uffff';
      this.IX(var69);
      int var70 = this.IX() + 1 & '\uffff';
      this.IX(var70);
      int var71 = this.DE() + 1 & '\uffff';
      this.DE(var71);
      int var72 = super.B + -1 & 255;
      super.B = var72;
    } while(super.B != 0);

  }
/*

  public void $38455() {
    int var1 = mem(34255, 38455);
    A = var1;
    int var2 = A + B & 255;
    A = var2;
    F = A;
    IXH = 130;
    IXL = A;
    int var3 = mem(34256, 38464);
    A = var3;
    int var4 = A & 1;
    A = var4;
    F = A;
    int var5 = A;
    int var6 = rrc(var5);
    A = var6;
    E = A;
    int var7 = mem(34258, 38471);
    A = var7;
    int var8 = A & 3;
    A = var8;
    F = A;
    int var9 = A;
    int var10 = rrc(var9);
    A = var10;
    int var11 = A;
    int var12 = rrc(var11);
    A = var12;
    int var13 = A;
    int var14 = rrc(var13);
    A = var14;
    int var15 = A | E;
    A = var15;
    F = A;
    E = A;
    D = 157;
    int var16 = mem(33824, 38483);
    A = var16;
    int var17 = A - 29;
    F = var17;
    if (F == 0) {
      D = 182;
      A = E;
      int var49 = A ^ 128;
      A = var49;
      F = A;
      E = A;
    }

    B = 16;
    int var18 = mem(34259, 38498);
    A = var18;
    int var19 = A & 31;
    A = var19;
    F = A;
    C = A;

    do {
      int var20 = pair(IXH, IXL);
      int var21 = mem(var20, 38504);
      A = var21;
      int var22 = pair(IXH, IXL) + 1;
      int var23 = mem(var22, 38507);
      H = var23;
      int var24 = A | C;
      A = var24;
      F = A;
      L = A;
      int var25 = pair(D, E);
      int var26 = mem(var25, 38512);
      A = var26;
      int var27 = pair(H, L);
      int var28 = mem(var27, 38513);
      int var29 = A | var28;
      A = var29;
      int var30 = pair(H, L);
      int var31 = pair(H, L);
      F = A;
      int var32 = pair(H, L);
      int var33 = pair(H, L);
      wMem(var33, A, 38514);
      int var34 = pair(H, L) + 1 & '\uffff';
      HL(var34);
      int var35 = pair(D, E) + 1 & '\uffff';
      DE(var35);
      int var36 = pair(D, E);
      int var37 = mem(var36, 38517);
      A = var37;
      int var38 = pair(H, L);
      int var39 = mem(var38, 38518);
      int var40 = A | var39;
      A = var40;
      int var41 = pair(H, L);
      int var42 = pair(H, L);
      F = A;
      int var43 = pair(H, L);
      int var44 = pair(H, L);
      wMem(var44, A, 38519);
      int var45 = pair(IXH, IXL) + 1 & '\uffff';
      IX(var45);
      int var46 = pair(IXH, IXL) + 1 & '\uffff';
      IX(var46);
      int var47 = pair(D, E) + 1 & '\uffff';
      DE(var47);
      int var48 = B + -1;
      B = var48;
    } while (B != 0);

  }
*/

  public void $38430() {
    int var1 = mem(32928);
    A = var1;
    int var2 = pair(H, L);
    int var3 = mem(var2);
    int var4 = A - var3;
    F = var4;
    int var5 = pair(H, L);
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
        int var14 = pair(H, L);
        wMem(var14, A);
      }
    }

    int var6 = mem(32955);
    A = var6;
    int var7 = pair(H, L);
    int var8 = mem(var7);
    int var9 = A - var8;
    F = var9;
    int var10 = pair(H, L);
  }

  public void $38504() {
    do {
      int var1 = pair(IXH, IXL);
      int var2 = mem(var1);
      A = var2;
      int var3 = pair(IXH, IXL) + 1;
      int var4 = mem(var3);
      H = var4;
      int var5 = A | C;
      A = var5;
      F = A;
      L = A;
      int var6 = pair(D, E);
      int var7 = mem(var6);
      A = var7;
      int var8 = pair(H, L);
      int var9 = mem(var8);
      int var10 = A | var9;
      A = var10;
      int var11 = pair(H, L);
      int var12 = pair(H, L);
      F = A;
      int var13 = pair(H, L);
      int var14 = pair(H, L);
      wMem(var14, A);
      int var15 = pair(H, L) + 1 & '\uffff';
      HL(var15);
      int var16 = pair(D, E) + 1 & '\uffff';
      DE(var16);
      int var17 = pair(D, E);
      int var18 = mem(var17);
      A = var18;
      int var19 = pair(H, L);
      int var20 = mem(var19);
      int var21 = A | var20;
      A = var21;
      int var22 = pair(H, L);
      int var23 = pair(H, L);
      F = A;
      int var24 = pair(H, L);
      int var25 = pair(H, L);
      wMem(var25, A);
      int var26 = pair(IXH, IXL) + 1 & '\uffff';
      IX(var26);
      int var27 = pair(IXH, IXL) + 1 & '\uffff';
      IX(var27);
      int var28 = pair(D, E) + 1 & '\uffff';
      DE(var28);
      int var29 = B + -1 & 0xff;
      B = var29;
    } while (B != 0);

  }

  public void $38528() {
    do {
      A = mem(pair(IXH, IXL), 38528);
      $38545();
      IX(pair(IXH, IXL) + 1 & '\uffff');
      E = E + 1 & 255;
      F = E;
      A = D;
      A = A - 8 & 255;
      F = A;
      D = A;
      C = C + -1 & 255;
      F = C;
    } while (F != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    L = L | 128;
    HL(pair(H, L) * 2 & '\uffff');
    HL(pair(H, L) * 2 & '\uffff');
    HL(pair(H, L) * 2 & '\uffff');
    B = 8;

    $38555();
  }

  public void $38562() {
    while (true) {
      int var1 = this.HL();
      int var2 = this.mem(var1, 38562);
      super.A = var2;
      int var3 = super.A - 255;
      super.F = var3;
      if (super.F == 0) {
        return;
      }

      this.BC(100);
      int var4 = super.A ^ super.A;
      super.A = var4;
      super.F = super.A;
      int var5 = this.HL();
      int var6 = this.mem(var5, 38570);
      super.E = var6;
      super.D = super.E;

      while (true) {
        int var7 = super.D + -1 & 255;
        super.D = var7;
        super.F = super.D;
        if (super.F == 0) {
          super.D = super.E;
          int var14 = super.A ^ 24;
          super.A = var14;
          super.F = super.A;
        }

        int var8 = super.B + -1 & 255;
        super.B = var8;
        if (super.B == 0) {
          int temp1 = AFx();
          AFx(AF());
          AF(temp1);
          super.A = super.C;
          int var9 = super.A - 50;
          super.F = var9;
          if (super.F == 0) {
            int var12 = super.E;
            int var13 = this.rlc(var12);
            super.E = var13;
          }

          temp1 = AFx();
          AFx(AF());
          AF(temp1);
          int var10 = super.C + -1 & 255;
          super.C = var10;
          super.F = super.C;
          if (super.F == 0) {
            this.$38601();
            if (super.F != 0) {
              return;
            }

            int var11 = this.HL() + 1 & '\uffff';
            this.HL(var11);
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
    super.F = super.A;
    if (super.F != 0) {
      int var6 = this.in(31);
      super.A = var6;
      int var7 = super.A & 16;
      super.F = var7;
      if (super.F != 0) {
        return;
      }
    }

    this.BC(45054);
    int var3 = this.in(BC()); //FIXED
    super.A = var3;
    int var4 = super.A & 1;
    super.A = var4;
    super.F = super.A;
    int var5 = super.A - 1;
    super.F = var5;
  }

  public void $38622() {
//    E = A;
//    C = 254;
//
//    do {
//      D = A;
//      int var1 = D & -17;
//      D = var1;
//      int var2 = D & -9;
//      D = var2;
//      B = E;
//
//      do {
//        int var3 = A - B;
//        F = var3;
//        if (F == 0) {
//          D = 24;
//        }
//
//        int var4 = B + -1;
//        B = var4;
//      } while (B != 0);
//
//      int var5 = A + -1 & 255;
//      A = var5;
//      F = A;
//    } while (F != 0);

  }

  public void $38555() {
    do {
      A = mem(pair(H, L), 38555);
      wMem(pair(D, E), A, 38556);
      HL(pair(H, L) + 1 & '\uffff');
      D = D + 1 & 255;
      F = D;
      B = B + -1 & 0xff;
    } while (B != 0);
  }

  public void $35211() {
    int var1 = this.mem(34252, 35211);
    super.A = var1;
    this.HL(20640);
    int var2 = super.A | super.A;
    super.A = var2;
    super.F = super.A;
    if (super.F != 0) {
      super.B = super.A;

      do {
        super.C = 0;
        int lastHL = pair(H, L); //FIXED
        int lastBC = pair(B, C);//FIXED
        int var3 = this.mem(34273, 35224);
        super.A = var3;
        int var4 = super.A;
        int var5 = this.rlc(var4);
        super.A = var5;
        int var6 = super.A;
        int var7 = this.rlc(var6);
        super.A = var7;
        int var8 = super.A;
        int var9 = this.rlc(var8);
        super.A = var9;
        int var10 = super.A & 96;
        super.A = var10;
        super.F = super.A;
        super.E = super.A;
        super.D = 157;
        this.$37974();
        HL(lastHL);//FIXED
        BC(lastBC);//FIXED
        int var11 = this.HL() + 1 & '\uffff';
        this.HL(var11);
        int var12 = this.HL() + 1 & '\uffff';
        this.HL(var12);
        int var13 = super.B + -1 & 255;
        super.B = var13;
      } while(super.B != 0);

    }
  }

  public void $38064() {
    int var1 = mem(33003);
    A = var1;
    wMem(33824, A);
    int var2 = mem(34259);
    A = var2;
    int var3 = A & 31;
    A = var3;
    F = A;
    int var4 = A + 160 & 255;
    A = var4;
    F = A;
    wMem(34259, A);
    A = 93;
    wMem(34260, A);
    A = 208;
    wMem(34255, A);
    int var5 = A ^ A;
    A = var5;
    F = A;
    wMem(34257, A);
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
}
