package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.MiniZX;

import java.util.function.Function;

public class JSW2 extends MiniZX {

  private int doReturn;

  public static void main(String[] args) {
  }

  @Override
  protected Function<Integer, Integer> getMemFunction() {
    return index -> {
      return mem[index];
    };
  }

  protected void checkSyncJava(int address, int value, int pc) {
  }

  @Override
  protected byte[] getProgramBytes() {
    return new byte[0];
  }

  public void $36307(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    label250:
    {
      label251:
      {
        label246:
        {
          label266:
          {
            A = mem(34262, 36307);
            A = A - 1 & 255;
            F = A & 128;
            if (F != 0) {
              A = mem(34257, 36316);
              F = A - 1;
              if (F == 0) {
                A = mem(34261, 36323);
                A = A & 254;
                A = A - 8 & 255;
                HL(34255);
                A = A + mem(HL(), 36333) & 255;
                wMem(HL(), A, 36334);
                F = A - 240;
                if (F >= 0) {
                  A = mem(33003, 38064);
                  wMem(33824, A, 38067);
                  A = mem(34259, 38070);
                  A = A & 31;
                  A = A + 160 & 255;
                  wMem(34259, A, 38077);
                  A = 93;
                  wMem(34260, A, 38082);
                  A = 208;
                  wMem(34255, A, 38087);
                  A = 0;
                  wMem(34257, A, 38091);
                  break label251;
                }

                $36508(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
                A = mem(32946, 36343);
                F = A - mem(HL(), 36346);
                if (F == 0) {
                  break label250;
                }

                HL(HL() + 1 & '\uffff');
                F = A - mem(HL(), 36351);
                if (F == 0) {
                  break label250;
                }

                A = mem(34261, 36355);
                A = A + 1 & 255;
                wMem(34261, A, 36359);
                A = A - 8;
                F = A;
                if (F < 0) { //FIXME jp p
                  A = -A & 255;
                }
                A = A + 1 & 255;
                A = rlc(A);
                A = rlc(A);
                A = rlc(A);
                D = A;
                C = 32;
                A = mem(32990, 36376);

                do {
                  A = A ^ 24;
                  B = D;

                  do {
                    B = B - 1 & 255;
                  } while (B != 0);

                  C = C - 1 & 255;
                  F = C;
                } while (F != 0);

                A = mem(34261, 36389);
                F = A - 18;
                if (F == 0) {
                  A = 6;
                  wMem(34257, A, 36530);
                  return;
                }

                F = A - 16;
                if (F != 0) {
                  F = A - 13;
                  if (F != 0) {
                    break label246;
                  }
                }
              }

              A = mem(34255, 36406);
              A = A & 14;
              F = A;
              if (F != 0) {
                break label266;
              }

              HL(mem16(34259, 36413));
              DE(64);
              HL(HL() + DE() & '\uffff');
              F = H & 2;
              if (F != 0) {
                break label251;
              }

              A = mem(32955, 36425);
              F = A - mem(HL(), 36428);
              if (F == 0) {
                break label266;
              }

              HL(HL() + 1 & '\uffff');
              A = mem(32955, 36432);
              F = A - mem(HL(), 36435);
              if (F == 0) {
                break label266;
              }

              A = mem(32928, 36438);
              int var323 = HL();
              int var324 = mem(var323, 36441);
              F = A - var324;
              HL(HL() - 1 & 0xffff);//FIXME
              if (F == 0) {
                int var327 = HL();
                int var328 = mem(var327, 36446);
                F = A - var328;
                if (F == 0) {
                  break label266;
                }
              }
            }

            E = 255;
            A = mem(34262, 36566);
            A = A - 1 & 255;
            F = A & 128;
            if (F != 0) {
              label263:
              {
                A = mem(34257, 36574);
                F = A - 12;
                if (F >= 0) {
                  A = 255;
                  wMem(34257, A, 37050);
                  IX(33024);

                  while (true) {
                    int var140 = IX();
                    A = mem(var140, 37060);
                    F = A - 255;
                    if (F == 0) {
                      return;
                    }

                    A = A & 3;
                    F = A;
                    if (F != 0) {
                      F = A - 1;
                      if (F != 0) {
                        F = A - 2;
                        if (F != 0) {
                          int var218 = IX();
                          F = mem(var218, 37081) & 128;
                          if (F != 0) {
                            int var240 = IX() + 1;
                            A = mem(var240, 37087);
                            F = A & 128;
                            if (F != 0) {
                              A = A - 2 & 255;
                              F = A - 148;
                              if (F < 0) {
                                A = A - 2 & 255;
                                F = A - 128;
                                if (F == 0) {
                                  A = 0;
                                }
                              }
                            } else {
                              A = A + 2 & 255;
                              F = A - 18;
                              if (F < 0) {
                                A = A + 2 & 255;
                              }
                            }
                          } else {
                            A = mem(IX() + 1, 37119);
                            F = A & 128;
                            if (F == 0) {
                              A = A - 2 & 255;
                              F = A - 20;
                              if (F < 0) {
                                A = A - 2 & 255;
                                F = A;
                                if (F == 0) {
                                  A = 128;
                                }
                              }
                            } else {
                              A = A + 2 & 255;
                              F = A - 146;
                              if (F < 0) {
                                A = A + 2 & 255;
                              }
                            }
                          }

                          wMem(IX() + 1, A, 37149);
                          A = A & 127;
                          F = A - mem(IX() + 7, 37154);
                          if (F == 0) {
                            A = mem(IX(), 37160);
                            A = A ^ 128;
                            wMem(IX(), A, 37165);
                          }
                        } else {
                          label265:
                          {
                            A = mem(IX(), 37247);
                            A = A ^ 8;
                            wMem(IX(), A, 37252);
                            A = A & 24;
                            F = A;
                            if (F != 0) {
                              A = mem(IX(), 37259);
                              A = A + 32 & 255;
                              wMem(IX(), A, 37264);
                            }

                            A = mem(IX() + 3, 37267);
                            A = A + mem(IX() + 4, 37270) & 255;
                            wMem(IX() + 3, A, 37273);
                            F = A - mem(IX() + 7, 37276);
                            if (F < 0) {
                              F = A - mem(IX() + 6, 37281);
                              if (F != 0 && F >= 0) {
                                break label265;
                              }

                              A = mem(IX() + 6, 37288);
                              wMem(IX() + 3, A, 37291);
                            }

                            A = mem(IX() + 4, 37294);
                            A = -A & 255;
                            wMem(IX() + 4, A, 37299);
                          }
                        }
                      } else {
                        int var148 = IX();
                        F = mem(var148, 37171) & 128;
                        if (F == 0) {
                          int var167 = IX();
                          A = mem(var167, 37177);
                          A = A - 32 & 255;
                          A = A & 127;
                          int var171 = IX();
                          wMem(var171, A, 37184);
                          F = A - 96;
                          if (F >= 0) {
                            int var173 = IX() + 2;
                            A = mem(var173, 37191);
                            A = A & 31;
                            F = A - mem(IX() + 6, 37196);
                            if (F != 0) {
                              int var181 = IX() + 2;
                              int var182 = mem(var181, 37201) - 1 & 255; //FIXME
                              wMem(var181, var182, 37201);
                            } else {
                              int var180 = IX();
                              wMem(var180, 129, 37206);
                            }
                          }
                        } else {
                          int var150 = IX();
                          A = mem(var150, 37212);
                          A = A + 32 & 255;
                          A = A | 128;
                          int var154 = IX();
                          wMem(var154, A, 37219);
                          F = A - 160;
                          if (F < 0) {
                            int var156 = IX() + 2;
                            A = mem(var156, 37226);
                            A = A & 31;
                            int var159 = IX() + 7;
                            int var160 = mem(var159, 37231);
                            F = A - var160;
                            if (F != 0) {
                              int var166 = mem(IX() + 2) + 1 & 0xff;
                              wMem(IX() + 2, var166, 37236);
                            } else {
                              int var163 = IX();
                              wMem(var163, 97, 37241);
                            }
                          }
                        }
                      }
                    }

                    DE(8);
                    int var144 = IX();
                    int var145 = DE();
                    int var146 = var144 + var145 & '\uffff';
                    IX(var146);
                  }
                }

                A = 0;
                wMem(34257, A, 36583);
                A = mem(32973, 36586);
                int var254 = mem(HL(), 36589);
                F = A - var254;
                if (F != 0) {
                  int var259 = HL() + 1 & '\uffff';
                  HL(var259);
                  int var260 = HL();
                  int var261 = mem(var260, 36593);
                  F = A - var261;
                  if (F != 0) {
                    break label263;
                  }
                }

                A = mem(32982, 36596);
                A = A - 3 & 255;
                E = A;
              }
            }

            BC(57342);
            A = in(BC());
            A = A & 31;
            A = A | 32;
            A = A & E;
            E = A;
            A = mem(34271, 36613);
            A = A & 2;
            int var13 = A;
            A = rrc(var13);
            A = A ^ E;
            E = A;
            BC(64510);
            A = in(BC());
            A = A & 31;
            int var18 = A;
            A = rlc(var18);
            A = A | 1;
            A = A & E;
            E = A;
            B = 231;
            A = in(BC());
            int var23 = A;
            A = rrc(var23);
            A = A | 247;
            A = A & E;
            E = A;
            B = 239;
            A = in(BC());
            A = A | 251;
            A = A & E;
            E = A;
            A = in(BC());
            int var31 = A;
            A = rrc(var31);
            A = A | 251;
            A = A & E;
            E = A;
            A = mem(34254, 36658);
            F = A;
            if (F != 0) {
              BC(31);
              A = in(BC());
              A = A & 3;
              A = ~A;
              A = A & E;
              E = A;
            }

            C = 0;
            A = E;
            A = A & 42;
            F = A - 42;
            if (F != 0) {
              C = 4;
              A = 0;
              wMem(34272, A, 36686);
            }

            A = E;
            A = A & 21;
            F = A - 21;
            if (F != 0) {
              C = C | 8;
              A = 0;
              wMem(34272, A, 36699);
            }

            A = mem(34256, 36702);
            A = A + C & 255;
            C = A;
            B = 0;
            HL(33825);
            int var43 = HL();
            int var44 = BC();
            int var45 = var43 + var44 & '\uffff';
            HL(var45);
            int var46 = HL();
            A = mem(var46, 36713);
            wMem(34256, A, 36714);
            BC(32510);
            A = in(BC());
            A = A & 31;
            F = A - 31;
            if (F == 0) {
              B = 239;
              A = in(BC());
              F = A & 1;
              if (F != 0) {
                A = mem(34254, 36736);
                F = A;
                if (F == 0) {
                  break label246;
                }

                BC(31);
                A = in(BC());
                F = A & 16;
                if (F == 0) {
                  break label246;
                }
              }
            }

            A = mem(34271, 36751);
            F = A & 2;
            if (F == 0) {
              A = 0;
              wMem(34261, A, 36759);
              wMem(34272, A, 36762);
              A = A + 1 & 255;
              wMem(34257, A, 36766);
              A = mem(34262, 36769);
              A = A - 1 & 255;
              F = A & 128;
              if (F == 0) {
                A = 240;
                wMem(34262, A, 36779);
                A = mem(34255, 36782);
                A = A & 240;
                wMem(34255, A, 36787);
                HL(34256);
                int var123 = HL();
                int var124 = mem(var123, 36793) | 2;
                int var125 = HL();
                wMem(var125, var124, 36793);
                return;
              }
            }
            break label246;
          }

          A = mem(34257, 36450);
          F = A - 1;
          if (F != 0) {
            HL(34256);
            int var270 = HL();
            int var271 = mem(var270, 36461) & -3;
            int var272 = HL();
            wMem(var272, var271, 36461);
            A = mem(34257, 36463);
            F = A;
            if (F == 0) {
              A = 2;
              wMem(34257, A, 36536);
              return;
            }

            A = A + 1 & 255;
            F = A - 16;
            if (F == 0) {
              A = 12;
            }

            wMem(34257, A, 36477);
            int var277 = A;
            A = rlc(var277);
            int var279 = A;
            A = rlc(var279);
            int var281 = A;
            A = rlc(var281);
            int var283 = A;
            A = rlc(var283);
            D = A;
            C = 32;
            A = mem(32990, 36487);

            do {
              A = A ^ 24;
              B = D;

              do {
                B = B - 1 & 255;
              } while (B != 0);

              C = C - 1 & 255;
              F = C;
            } while (F != 0);

            A = mem(34255, 36500);
            A = A + 8 & 255;
            wMem(34255, A, 36505);
            A = A & 240;
            L = A;
            A = 0;
            int var293 = L;
            carry = 0;//FIXME
            L = rl(var293);
            A = (A + 92 + carry) & 255;
            H = A;
            A = mem(34259, 36517);
            A = A & 31;
            A = A | L;
            L = A;
            int var299 = HL();
            wMem16(34259, var299, 36524);
            return;
          }
        }

        A = mem(34256, 36796);
        A = A & 2;
        F = A;
        if (F == 0) {
          return;
        }

        A = mem(34262, 36802);
        A = A - 1 & 255;
        F = A & 128;
        if (F == 0) {
          return;
        }

        A = mem(34256, 36809);
        A = A & 1;
        F = A;
        if (F != 0) {
          A = mem(34258, 36817);
          F = A;
          if (F != 0) {
            A = A - 1 & 255;
            wMem(34258, A, 36824);
            return;
          }

          A = mem(34257, 36828);
          BC(0);
          F = A;
          if (F == 0) {
            int var100 = mem16(34259, 36838);
            HL(var100);
            BC(0);
            A = mem(32986, 36844);
            A = A - 1 & 255;
            A = A | 161;
            A = A ^ 224;
            E = A;
            D = 0;
            int var105 = HL();
            int var106 = DE();
            int var107 = var105 + var106 & '\uffff';
            HL(var107);
            A = mem(32964, 36856);
            int var109 = HL();
            int var110 = mem(var109, 36859);
            F = A - var110;
            if (F == 0) {
              BC(32);
              A = mem(32986, 36865);
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
          A = A & 31;
          F = A;
          if (F == 0) { //FIXME
            $38026(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            doReturn = 1;
            return;
          }
          int var69 = HL();
          int var70 = BC();
          int var71 = var69 + var70 & '\uffff';
          HL(var71);
          HL(HL() - 1 & 0xffff); //FIXME
          DE(32);
          int var72 = HL();
          int var73 = DE();
          int var74 = var72 + var73 & '\uffff';
          HL(var74);
          A = mem(32946, 36889);
          int var76 = HL();
          int var77 = mem(var76, 36892);
          F = A - var77;
          if (F == 0) {
            return;
          }

          A = mem(34255, 36894);
          C = C >> 1;
          A = A + C & 255;
          B = A;
          A = A & 15;
          F = A;
          if (F != 0) {
            A = mem(32946, 36905);
            int var89 = HL();
            int var90 = DE();
            int var91 = var89 + var90 & '\uffff';
            HL(var91);
            int var92 = HL();
            int var93 = mem(var92, 36909);
            F = A - var93;
            if (F == 0) {
              return;
            }

            carry = 0;
            int var97 = HL();
            int var98 = DE();
            int var99 = ((var97 - var98) + carry) & '\uffff';
            HL(var99);
          }

          carry = 0;
          int var84 = HL();
          int var85 = DE();
          int var86 = ((var84 - var85) + carry) & '\uffff';
          HL(var86);
          int var87 = HL();
          wMem16(34259, var87, 36917);
          A = B;
          wMem(34255, A, 36921);
          A = 3;
          wMem(34258, A, 36926);
          return;
        }

        A = mem(34258, 36930);
        F = A - 3;
        if (F != 0) {
          A = A + 1 & 255;
          wMem(34258, A, 36938);
          return;
        }

        A = mem(34257, 36942);
        BC(0);
        F = A;
        if (F == 0) {
          int var121 = mem16(34259, 36951);
          HL(var121);
          A = mem(32986, 36954);
          A = A - 1 & 255;
          A = A | 157;
          A = A ^ 191;
          E = A;
          D = 0;
          int var126 = HL();
          int var127 = DE();
          int var128 = var126 + var127 & '\uffff';
          HL(var128);
          A = mem(32964, 36966);
          int var130 = HL();
          int var131 = mem(var130, 36969);
          F = A - var131;
          if (F == 0) {
            BC(32);
            A = mem(32986, 36975);
            F = A;
            if (F != 0) {
              BC(65504);
            }
          }
        }

        int var64 = mem16(34259, 36984);
        HL(var64);
        int var65 = HL();
        int var66 = BC();
        int var67 = var65 + var66 & '\uffff';
        HL(var67);
        int var68 = HL() + 1 & '\uffff';
        HL(var68);
        int var69 = HL() + 1 & '\uffff';
        HL(var69);
        A = L;
        A = A & 31;
        F = A;
        if (F != 0) {
          DE(32);
          A = mem(32946, 36999);
          int var86 = HL();
          int var87 = DE();
          int var88 = var86 + var87 & '\uffff';
          HL(var88);
          int var89 = HL();
          int var90 = mem(var89, 37003);
          F = A - var90;
          if (F == 0) {
            return;
          }

          A = mem(34255, 37005);
          int var94 = C;
          C = var94 >> 1;
          A = A + C & 255;
          B = A;
          A = A & 15;
          F = A;
          if (F != 0) {
            A = mem(32946, 37016);
            int var110 = HL();
            int var111 = DE();
            int var112 = var110 + var111 & '\uffff';
            HL(var112);
            int var113 = HL();
            int var114 = mem(var113, 37020);
            F = A - var114;
            if (F == 0) {
              return;
            }

            carry = 0;
            int var118 = HL();
            int var119 = DE();
            int var120 = ((var118 - var119) + carry) & '\uffff';
            HL(var120);
          }

          A = mem(32946, 37025);
          carry = 0;
          int var100 = HL();
          int var101 = DE();
          int var102 = ((var100 - var101) + carry) & '\uffff';
          HL(var102);
          int var103 = HL();
          int var104 = mem(var103, 37031);
          F = A - var104;
          if (F == 0) {
            return;
          }

          HL(HL() - 1 & 0xFFFF); //FIXME

          int var107 = HL();
          wMem16(34259, var107, 37034);
          A = 0;
          wMem(34258, A, 37038);
          A = B;
          wMem(34255, A, 37042);
          return;
        }
      }

      A = mem(33004, 38098);
      wMem(33824, A, 38101);
      A = 0;
      wMem(34255, A, 38105);
      A = mem(34257, 38108);
      F = A - 11;
      if (F < 0) {
        A = 2;
        wMem(34257, A, 38117);
      }

      A = mem(34259, 38120);
      A = A & 31;
      wMem(34259, A, 38125);
      A = 92;
      wMem(34260, A, 38130);
      doReturn = 1;
      return;
    }

    A = mem(34255, 36540);
    A = A + 16 & 255;
    A = A & 240;
    wMem(34255, A, 36547);
    $36508(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    A = 2;
    wMem(34257, A, 36555);
    HL(34256);
    int var355 = HL();
    int var356 = mem(var355, 36561) & -3;
    int var357 = HL();
    wMem(var357, var356, 36561);
  }

  public void $38026(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(33001, 38026);
    wMem(33824, A, 38029);
    A = mem(34259, 38032);
    A = A | 31;
    A = A & 254;
    wMem(34259, A, 38039);
  }

  public void $34762(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    label205:
    while (true) {
      A = 0;
      wMem(34254, A, 34763);
      wMem(34273, A, 34766);
      wMem(34253, A, 34769);
      wMem(34257, A, 34772);
      wMem(34251, A, 34775);
      wMem(34272, A, 34778);
      wMem(34271, A, 34781);
      A = 7;
      wMem(34252, A, 34786);
      A = 208;
      wMem(34255, A, 34791);
      A = 33;
      wMem(33824, A, 34796);
      HL(23988);
      int var2 = HL();
      wMem16(34259, var2, 34802);
      HL(34172);
      int var3 = HL();
      wMem(var3, 48, 34808);
      int var4 = HL() + 1 & '\uffff';
      HL(var4);
      wMem(HL(), 48, 34811);
      HL(HL() + 1 & '\uffff');
      int var7 = HL();
      wMem(var7, 48, 34814);
      H = 164;
      A = mem(41983, 34818);
      L = A;
      wMem(34270, A, 34822);

      do {
        wMem(HL(), mem(HL(), 34825) | 64, 34825);
        L = L + 1 & 255;
        F = L;
      } while (F != 0);

      HL(34274);
      int var13 = HL();
      int var14 = mem(var13, 34833) | 1;
      int var15 = HL();
      wMem(var15, var14, 34833);

      label197:
      while (true) {
        HL(16384);
        DE(16385);
        BC(6143);
        int var16 = HL();
        wMem(var16, 0, 34844);
        ldir();
        HL(38912);
        BC(768);
        ldir();
        HL(23136);
        DE(23137);
        BC(31);
        int var17 = HL();
        wMem(var17, 70, 34865);
        ldir();
        IX(33876);
        DE(20576);
        C = 32;
        $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        DE(22528);

        do {
          int var18 = DE();
          A = mem(var18, 34884);
          F = A;
          if (F != 0) {
            F = A - 211;
            if (F != 0) {
              F = A - 9;
              if (F != 0) {
                F = A - 45;
                if (F != 0) {
                  F = A - 36;
                  if (F != 0) {
                    C = 0;
                    F = A - 8;
                    if (F != 0) {
                      F = A - 41;
                      if (F != 0) {
                        F = A - 44;
                        if (F != 0) {
                          F = A - 5;
                          if (F != 0) {
                            C = 16;
                          }
                        } else {
                          A = 37;
                          int var258 = DE();
                          wMem(var258, A, 34928);
                        }
                      }
                    }

                    A = E;
                    A = A & 1;
                    int var245 = A;
                    A = rlc(var245);
                    int var247 = A;
                    A = rlc(var247);
                    int var249 = A;
                    A = rlc(var249);
                    A = A | C;
                    C = A;
                    B = 0;
                    HL(33841);
                    int var252 = HL();
                    int var253 = BC();
                    int var254 = var252 + var253 & '\uffff';
                    HL(var254);
                    int lastDE = DE();
                    F = D & 1;
                    D = 64;
                    if (F != 0) {
                      D = 72;
                    }

                    B = 8;
                    $38555(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
                    DE(lastDE);
                  }
                }
              }
            }
          }

          int var21 = DE() + 1 & '\uffff';
          DE(var21);
          A = D;
          F = A - 90;
        } while (F != 0);

        BC(31);
        A = 0;

        do {
          E = in(BC());
          A = A | E;
          B = B - 1 & 255;
        } while (B != 0);

        A = A & 32;
        F = A;
        if (F == 0) {
          A = 1;
          wMem(34254, A, 34981);
        }

        HL(34299);
        $38562(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        if (F != 0) {
          break;
        }

        A = 0;
        wMem(34276, A, 34994);

        while (true) {
          $35563(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
          HL(23136);
          DE(23137);
          BC(31);
          int var225 = HL();
          wMem(var225, 79, 35009);
          ldir();
          A = mem(34276, 35013);
          IX(33876);
          E = A;
          D = 0;
          int var227 = IX();
          int var228 = DE();
          int var229 = var227 + var228 & '\uffff';
          IX(var229);
          DE(20576);
          C = 32;
          $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
          A = mem(34276, 35033);
          A = A & 31;
          A = A + 50 & 255;
          $38622(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
          BC(45054);
          A = in(BC());
          A = A & 1;
          F = A - 1;
          if (F != 0) {
            break label197;
          }

          A = mem(34276, 35054);
          A = A + 1 & 255;
          F = A - 224;
          wMem(34276, A, 35060);
          if (F == 0) {
            break;
          }
        }
      }

      HL(34181);
      DE(34175);
      BC(6);
      ldir();
      HL(39424);
      DE(23040);
      BC(256);
      ldir();

      while (true) {
        A = mem(33824, 35090);
        A = A | 192;
        H = A;
        L = 0;
        DE(32768);
        BC(256);
        ldir();
        IX(33008);
        DE(33024);
        A = 8;

        do {
          int var30 = IX();
          L = mem(var30, 35115);
          L = L & -129;
          H = 20;
          int var33 = HL() * 2 & '\uffff';
          HL(var33);
          int var34 = HL() * 2 & '\uffff';
          HL(var34);
          int var35 = HL() * 2 & '\uffff';
          HL(var35);
          BC(2);
          ldir();
          int var36 = IX() + 1;
          C = mem(var36, 35130);
          int var38 = HL();
          wMem(var38, C, 35133);
          BC(6);
          ldir();
          int var39 = IX() + 1 & '\uffff';
          IX(var39);
          int var40 = IX() + 1 & '\uffff';
          IX(var40);
          A = A - 1 & 255;
          F = A;
        } while (F != 0);

        HL(34255);
        DE(34263);
        BC(7);
        ldir();
        $36147(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        HL(20480);
        DE(20481);
        BC(2047);
        int var42 = HL();
        wMem(var42, 0, 35169);
        ldir();
        IX(32896);
        C = 32;
        DE(20480);
        $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        IX(34132);
        DE(20576);
        C = 32;
        $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        A = mem(32990, 35197);
        C = 254;
        A = 0;
        wMem(34262, A, 35205);

        while (true) {
          label215:
          {
            $35211(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            HL(24064);
            DE(23552);
            BC(512);
            ldir();
            HL(28672);
            DE(24576);
            BC(4096);
            ldir();
            $37056(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            A = mem(34271, 35273);
            F = A - 3;
            if (F != 0) {
              $36307(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
              if (doReturn == 1) {
                doReturn = 0;
                break;
              }
            }

            A = mem(34255, 35281);
            F = A - 225;
            if (F >= 0) {
              $38064(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            }

            A = mem(34271, 35289);
            F = A - 3;
            if (F != 0) {
              $38344(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            }

            A = mem(34271, 35297);
            F = A - 2;
            if (F == 0) {
              $38276(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            }

            $38196(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            $37310(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            $38137(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            $37841(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            HL(24576);
            DE(16384);
            BC(4096);
            ldir();
            A = mem(34271, 35328);
            A = A & 2;
            int var55 = A;
            A = rrc(var55);
            HL(34258);
            int var57 = HL();
            int var58 = mem(var57, 35337);
            A = A | var58;
            int var63 = HL();
            wMem(var63, A, 35338);
            A = mem(34253, 35339);
            F = A;
            if (F != 0) {
              A = A - 1 & 255;
              wMem(34253, A, 35346);
              int var216 = A;
              A = rlc(var216);
              int var218 = A;
              A = rlc(var218);
              int var220 = A;
              A = rlc(var220);
              A = A & 56;
              HL(23552);
              DE(23553);
              BC(511);
              int var223 = HL();
              wMem(var223, A, 35363);
              ldir();
            }

            HL(23552);
            DE(22528);
            BC(512);
            ldir();
            IX(34175);
            DE(20601);
            C = 6;
            $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            IX(34172);
            DE(20592);
            C = 3;
            $38528(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            A = mem(34251, 35401);
            A = A + 1 & 255;
            wMem(34251, A, 35405);
            if (F == 0) {
              IX(34175);
              int var178 = IX() + 4;
              int var179 = mem(var178, 35414) + 1;
              wMem(var178, var179, 35414);
              int var180 = var179 & 255;
              wMem(var178, var180, 35414);
              int var181 = IX() + 4;
              A = mem(var181, 35417);
              F = A - 58;
              if (F == 0) {
                int var184 = IX() + 4;
                wMem(var184, 48, 35424);
                int var185 = IX() + 3;
                int var186 = mem(var185, 35428) + 1;
                wMem(var185, var186, 35428);
                int var187 = var186 & 255;
                wMem(var185, var187, 35428);
                int var188 = IX() + 3;
                A = mem(var188, 35431);
                F = A - 54;
                if (F == 0) {
                  int var191 = IX() + 3;
                  wMem(var191, 48, 35438);
                  int var192 = IX();
                  A = mem(var192, 35442);
                  F = A - 49;
                  if (F == 0) {
                    int var203 = IX() + 1;
                    int var204 = mem(var203, 35449) + 1;
                    wMem(var203, var204, 35449);
                    int var205 = var204 & 255;
                    wMem(var203, var205, 35449);
                    int var206 = IX() + 1;
                    A = mem(var206, 35452);
                    F = A - 51;
                    if (F == 0) {
                      int var209 = IX() + 5;
                      A = mem(var209, 35459);
                      F = A - 112;
                      if (F == 0) {
                        continue label205;
                      }

                      int var212 = IX();
                      wMem(var212, 32, 35467);
                      int var213 = IX() + 1;
                      wMem(var213, 49, 35471);
                      int var214 = IX() + 5;
                      wMem(var214, 112, 35475);
                    }
                  } else {
                    int var195 = IX() + 1;
                    int var196 = mem(var195, 35481) + 1;
                    wMem(var195, var196, 35481);
                    int var197 = var196 & 255;
                    wMem(var195, var197, 35481);
                    int var198 = IX() + 1;
                    A = mem(var198, 35484);
                    F = A - 58;
                    if (F == 0) {
                      int var201 = IX() + 1;
                      wMem(var201, 48, 35491);
                      int var202 = IX();
                      wMem(var202, 49, 35495);
                    }
                  }
                }
              }
            }

            BC(65278);
            A = in(BC());
            E = A;
            B = 127;
            A = in(BC());
            A = A | E;
            A = A & 1;
            F = A;
            if (F == 0) {
              continue label205;
            }

            A = mem(34272, 35515);
            A = A + 1 & 255;
            wMem(34272, A, 35519);
            if (F != 0) {
              B = 253;
              A = in(BC());
              A = A & 31;
              F = A - 31;
              if (F == 0) {
                break label215;
              }

              DE(0);
            }

            while (true) {
              B = 2;
              A = in(BC());
              A = A & 31;
              F = A - 31;
              if (F != 0) {
                HL(39424);
                DE(23040);
                BC(256);
                ldir();
                A = mem(32990, 35602);
                break;
              }

              E = E + 1 & 255;
              F = E;
              if (F == 0) {
                D = D + 1 & 255;
                F = D;
                if (F == 0) {
                  A = mem(34275, 35553);
                  F = A - 10;
                  if (F != 0) {
                    $35563(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
                  }
                }
              }
            }
          }

          A = mem(34257, 35607);
          F = A - 255;
          B = 191;
          HL(34274);
          A = in(BC());
          A = A & 31;
          F = A - 31;
          if (F != 0) {
            int var165 = HL();
            F = mem(var165, 35628) & 1;
            if (F == 0) {
              int var167 = HL();
              A = mem(var167, 35632);
              A = A ^ 3;
              int var170 = HL();
              wMem(var170, A, 35635);
            }
          } else {
            int var83 = HL();
            int var84 = mem(var83, 35638) & -2;
            int var85 = HL();
            wMem(var85, var84, 35638);
          }

          int var86 = HL();
          F = mem(var86, 35640) & 2;
          if (F == 0) {
            A = 0;
            wMem(34272, A, 35645);
            A = mem(34273, 35648);
            A = A + 1 & 255;
            wMem(34273, A, 35652);
            A = A & 126;
            int var142 = A;
            A = rrc(var142);
            E = A;
            D = 0;
            HL(34399);
            int var144 = HL();
            int var145 = DE();
            int var146 = var144 + var145 & '\uffff';
            HL(var146);
            A = mem(34252, 35665);
            int var148 = A;
            A = rlc(var148);
            int var150 = A;
            A = rlc(var150);
            A = A - 28 & 255;
            A = -A & 255;
            int var154 = HL();
            int var155 = mem(var154, 35674);
            A = A + var155 & 255;
            D = A;
            A = mem(32990, 35676);
            E = D;
            BC(3);

            while (true) {
              E = E - 1 & 255;
              F = E;
              if (F == 0) {
                E = D;
                A = A ^ 24;
              }

              B = B - 1 & 255;
              if (B == 0) {
                C = C - 1 & 255;
                F = C;
                if (F == 0) {
                  break;
                }
              }
            }
          }

          BC(61438);
          A = in(BC());
          F = A & 2;
          if (F == 0) {
            A = A & 16;
            A = A ^ 16;
            int var130 = A;
            A = rlc(var130);
            D = A;
            A = mem(34275, 35712);
            F = A - 10;
            if (F == 0) {
              BC(63486);
              A = in(BC());
              A = ~A;
              A = A & 31;
              A = A | D;
              wMem(33824, A, 35729);
              break;
            }
          }

          A = mem(34275, 35735);
          F = A - 10;
          if (F != 0) {
            A = mem(33824, 35743);
            F = A - 28;
            if (F == 0) {
              A = mem(34255, 35751);
              F = A - 208;
              if (F == 0) {
                A = mem(34275, 35759);
                int var97 = A;
                A = rlc(var97);
                E = A;
                D = 0;
                IX(34279);
                int var99 = IX();
                int var100 = DE();
                int var101 = var99 + var100 & '\uffff';
                IX(var101);
                BC(64510);
                A = in(BC());
                A = A & 31;
                int var104 = IX();
                int var105 = mem(var104, 35779);
                F = A - var105;
                if (F != 0) {
                  F = A - 31;
                  if (F != 0) {
                    int var123 = IX();
                    int var124 = mem(var123, 35789);
                    F = A - var124;
                    if (F != 0) {
                      A = 0;
                      wMem(34275, A, 35796);
                    }
                  }
                } else {
                  B = 223;
                  A = in(BC());
                  A = A & 31;
                  int var110 = IX() + 1;
                  int var111 = mem(var110, 35808);
                  F = A - var111;
                  if (F != 0) {
                    F = A - 31;
                    if (F != 0) {
                      int var117 = IX();
                      int var118 = mem(var117, 35818);
                      F = A - var118;
                      int var120 = IX();
                      if (F != 0) {
                        A = 0;
                        wMem(34275, A, 35825);
                      }
                    }
                  } else {
                    A = mem(34275, 35831);
                    A = A + 1 & 255;
                    wMem(34275, A, 35835);
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public void $36508(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = A & 240;
    L = A;
    A = 0;
    int var3 = L;
    L = rlc(var3) & 0xFE;
    A = (A + carry + 92) & 255;
    H = A;
    A = mem(34259);
    A = A & 31;
    A = A | L;
    L = A;
    int var9 = HL();
    wMem16(34259, var9);
  }

  public void $35563(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    HL(22528);
    int var1 = HL();
    A = mem(var1);
    A = A & 7;

    do {
      int var4 = HL();
      A = mem(var4);
      A = A + 3 & 255;
      A = A & 7;
      D = A;
      int var8 = HL();
      A = mem(var8);
      A = A + 24 & 255;
      A = A & 184;
      A = A | D;
      int var13 = HL();
      wMem(var13, A);
      int var14 = HL() + 1 & '\uffff';
      HL(var14);
      A = H;
      F = A - 91;
    } while (F != 0);

  }

  public void $36147(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    $36203(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    IX(24064);
    A = 112;
    wMem(36189, A, 36156);
    $36171(112);
    IX(24320);
    A = 120;
    wMem(36189, A, 36168);
    $36171(120);
  }

  private void $36171(int d) {
    C = 0;

    do {
      E = C;
      int var1 = IX();
      A = mem(var1, 36174);
      HL(32928);
      BC(54);
      cpir();
      C = E;
      B = 8;
      D = d;

      do {
        int var3 = HL();
        A = mem(var3, 36190);
        int var5 = DE();
        wMem(var5, A, 36191);
        int var6 = HL() + 1 & '\uffff';
        HL(var6);
        D = D + 1 & 255;
        F = D;
        B = B - 1 & 255;
      } while (B != 0);

      int var9 = IX() + 1 & '\uffff';
      IX(var9);
      C = C + 1 & 255;
      F = C;
    } while (F != 0);
  }

  public void $36203(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    HL(32768);
    IX(24064);

    do {
      int var1 = HL();
      A = mem(var1, 36210);
      int var3 = A;
      A = rlc(var3);
      int var5 = A;
      A = rlc(var5);
      $36288(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
      int var7 = HL();
      A = mem(var7, 36216);
      int var9 = A;
      A = rrc(var9);
      int var11 = A;
      A = rrc(var11);
      int var13 = A;
      A = rrc(var13);
      int var15 = A;
      A = rrc(var15);
      $36288(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
      int var17 = HL();
      A = mem(var17, 36224);
      int var19 = A;
      A = rrc(var19);
      int var21 = A;
      A = rrc(var21);
      $36288(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
      int var23 = HL();
      A = mem(var23, 36230);
      $36288(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
      int var25 = HL() + 1 & '\uffff';
      HL(var25);
      A = L;
      A = A & 128;
      F = A;
    } while (F == 0);

    A = mem(32985, 36240);
    F = A;
    if (F != 0) {
      int var44 = mem16(32983, 36246);
      HL(var44);
      B = A;
      A = mem(32973, 36250);

      do {
        int var46 = HL();
        wMem(var46, A, 36253);
        int var47 = HL() + 1 & '\uffff';
        HL(var47);
        B = B - 1 & 255;
      } while (B != 0);
    }

    A = mem(32989, 36257);
    F = A;
    if (F != 0) {
      int var31 = mem16(32987, 36262);
      HL(var31);
      A = mem(32986, 36265);
      A = A & 1;
      int var34 = A;
      A = rlc(var34);
      A = A + 223 & 255;
      E = A;
      D = 255;
      A = mem(32989, 36276);
      B = A;
      A = mem(32964, 36280);

      do {
        int var39 = HL();
        wMem(var39, A, 36283);
        int var40 = HL();
        int var41 = DE();
        int var42 = var40 + var41 & '\uffff';
        HL(var42);
        B = B - 1 & 255;
      } while (B != 0);
    }
  }

  public void $36288(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = A & 3;
    C = A;
    int var2 = A;
    A = rlc(var2);
    int var4 = A;
    A = rlc(var4);
    int var6 = A;
    A = rlc(var6);
    A = A + C & 255;
    A = A + 160 & 255;
    E = A;
    D = 128;
    int var10 = DE();
    A = mem(var10, 36300);
    int var12 = IX();
    wMem(var12, A, 36301);
    int var13 = IX() + 1 & '\uffff';
    IX(var13);
  }

  public void $37056(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    IX(33024);

    while (true) {
      int var1 = IX();
      A = mem(var1, 37060);
      F = A - 255;
      if (F == 0) {
        return;
      }

      A = A & 3;
      F = A;
      if (F != 0) {
        F = A - 1;
        if (F != 0) {
          F = A - 2;
          if (F != 0) {
            int var79 = IX();
            F = mem(var79, 37081) & 128;
            if (F != 0) {
              int var101 = IX() + 1;
              A = mem(var101, 37087);
              F = A & 128;
              if (F != 0) {
                A = A - 2 & 255;
                F = A - 148;
                if (F < 0) {
                  A = A - 2 & 255;
                  F = A - 128;
                  if (F == 0) {
                    A = 0;
                  }
                }
              } else {
                A = A + 2 & 255;
                F = A - 18;
                if (F < 0) {
                  A = A + 2 & 255;
                }
              }
            } else {
              int var81 = IX() + 1;
              A = mem(var81, 37119);
              F = A & 128;
              if (F == 0) {
                A = A - 2 & 255;
                F = A - 20;
                if (F < 0) {
                  A = A - 2 & 255;
                  F = A;
                  if (F == 0) {
                    A = 128;
                  }
                }
              } else {
                A = A + 2 & 255;
                F = A - 146;
                if (F < 0) {
                  A = A + 2 & 255;
                }
              }
            }

            int var86 = IX() + 1;
            wMem(var86, A, 37149);
            A = A & 127;
            int var88 = IX() + 7;
            int var89 = mem(var88, 37154);
            F = A - var89;
            if (F == 0) {
              int var92 = IX();
              A = mem(var92, 37160);
              A = A ^ 128;
              int var95 = IX();
              wMem(var95, A, 37165);
            }
          } else {
            label81:
            {
              int var46 = IX();
              A = mem(var46, 37247);
              A = A ^ 8;
              int var49 = IX();
              wMem(var49, A, 37252);
              A = A & 24;
              F = A;
              if (F != 0) {
                int var75 = IX();
                A = mem(var75, 37259);
                A = A + 32 & 255;
                int var78 = IX();
                wMem(var78, A, 37264);
              }

              int var51 = IX() + 3;
              A = mem(var51, 37267);
              int var53 = IX() + 4;
              int var54 = mem(var53, 37270);
              A = A + var54 & 255;
              int var59 = IX() + 3;
              wMem(var59, A, 37273);
              int var60 = IX() + 7;
              int var61 = mem(var60, 37276);
              F = A - var61;
              if (F < 0) {
                int var68 = IX() + 6;
                int var69 = mem(var68, 37281);
                F = A - var69;
                if (F != 0 && F >= 0) {
                  break label81;
                }

                int var72 = IX() + 6;
                A = mem(var72, 37288);
                int var74 = IX() + 3;
                wMem(var74, A, 37291);
              }

              int var64 = IX() + 4;
              A = mem(var64, 37294);
              A = -A & 255;
              int var67 = IX() + 4;
              wMem(var67, A, 37299);
            }
          }
        } else {
          int var9 = IX();
          F = mem(var9, 37171) & 128;
          if (F == 0) {
            int var28 = IX();
            A = mem(var28, 37177);
            A = A - 32 & 255;
            A = A & 127;
            int var32 = IX();
            wMem(var32, A, 37184);
            F = A - 96;
            if (F >= 0) {
              int var34 = IX() + 2;
              A = mem(var34, 37191);
              A = A & 31;
              int var37 = IX() + 6;
              int var38 = mem(var37, 37196);
              F = A - var38;
              if (F != 0) {
                int var42 = IX() + 2;
                int var43 = mem(var42, 37201) - 1 & 255;
                wMem(var42, var43, 37201);
              } else {
                int var41 = IX();
                wMem(var41, 129, 37206);
              }
            }
          } else {
            int var11 = IX();
            A = mem(var11, 37212);
            A = A + 32 & 255;
            A = A | 128;
            int var15 = IX();
            wMem(var15, A, 37219);
            F = A - 160;
            if (F < 0) {
              int var17 = IX() + 2;
              A = mem(var17, 37226);
              A = A & 31;
              int var20 = IX() + 7;
              int var21 = mem(var20, 37231);
              F = A - var21;
              if (F != 0) {
                int var25 = IX() + 2;
                int var26 = mem(var25, 37236) + 1;
                int var27 = var26 & 255;
                wMem(var25, var27, 37236);
              } else {
                int var24 = IX();
                wMem(var24, 97, 37241);
              }
            }
          }
        }
      }

      DE(8);
      int var5 = IX();
      int var6 = DE();
      int var7 = var5 + var6 & '\uffff';
      IX(var7);
    }
  }

  public void $37310(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    IX(33024);

    while (true) {
      int var1 = IX();
      A = mem(var1, 37314);
      F = A - 255;
      if (F == 0) {
        return;
      }

      A = A & 7;
      F = A;
      if (F != 0) {
        F = A - 3;
        if (F != 0) {
          F = A - 4;
          if (F != 0) {
            int var221 = IX() + 3;
            E = mem(var221, 37334);
            D = 130;
            int var223 = DE();
            A = mem(var223, 37339);
            L = A;
            int var225 = IX() + 2;
            A = mem(var225, 37341);
            A = A & 31;
            A = A + L & 255;
            L = A;
            A = E;
            int var229 = A;
            A = rlc(var229);
            A = A & 1;
            A = A | 92;
            H = A;
            DE(31);
            int var233 = IX() + 1;
            A = mem(var233, 37358);
            A = A & 15;
            A = A + 56 & 255;
            A = A & 71;
            C = A;
            int var238 = HL();
            A = mem(var238, 37368);
            A = A & 56;
            A = A ^ C;
            C = A;
            int var242 = HL();
            wMem(var242, C, 37373);
            int var243 = HL() + 1 & '\uffff';
            HL(var243);
            int var244 = HL();
            wMem(var244, C, 37375);
            int var245 = HL();
            int var246 = DE();
            int var247 = var245 + var246 & '\uffff';
            HL(var247);
            int var248 = HL();
            wMem(var248, C, 37377);
            int var249 = HL() + 1 & '\uffff';
            HL(var249);
            int var250 = HL();
            wMem(var250, C, 37379);
            int var251 = IX() + 3;
            A = mem(var251, 37380);
            A = A & 14;
            F = A;
            if (F != 0) {
              int var285 = HL();
              int var286 = DE();
              int var287 = var285 + var286 & '\uffff';
              HL(var287);
              int var288 = HL();
              wMem(var288, C, 37388);
              int var289 = HL() + 1 & '\uffff';
              HL(var289);
              int var290 = HL();
              wMem(var290, C, 37390);
            }

            C = 1;
            int var254 = IX() + 1;
            A = mem(var254, 37393);
            int var256 = IX();
            int var257 = mem(var256, 37396);
            A = A & var257;
            int var262 = IX() + 2;
            int var263 = mem(var262, 37399);
            A = A | var263;
            A = A & 224;
            E = A;
            int var269 = IX() + 5;
            D = mem(var269, 37405);
            H = 130;
            int var271 = IX() + 3;
            L = mem(var271, 37410);
            int var273 = IX() + 2;
            A = mem(var273, 37413);
            A = A & 31;
            int var276 = HL();
            int var277 = mem(var276, 37418);
            A = A | var277;
            int var282 = HL() + 1 & '\uffff';
            HL(var282);
            int var283 = HL();
            H = mem(var283, 37420);
            L = A;
            $37974(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
          } else {
            int var158 = IX();
            F = mem(var158, 37431) & 128;
            if (F == 0) {
              int var218 = IX() + 4;
              int var219 = mem(var218, 37437) - 1;
              wMem(var218, var219, 37437);
              int var220 = var219 & 255;
              wMem(var218, var220, 37437);
              C = 44;
            } else {
              int var160 = IX() + 4;
              int var161 = mem(var160, 37444) + 1;
              wMem(var160, var161, 37444);
              int var162 = var161 & 255;
              wMem(var160, var162, 37444);
              C = 244;
            }

            int var163 = IX() + 4;
            A = mem(var163, 37449);
            F = A - C;
            if (F != 0) {
              A = A & 224;
              F = A;
              if (F == 0) {
                int var167 = IX() + 2;
                E = mem(var167, 37479);
                D = 130;
                int var169 = DE();
                A = mem(var169, 37484);
                int var171 = IX() + 4;
                int var172 = mem(var171, 37485);
                A = A + var172 & 255;
                L = A;
                A = E;
                A = A & 128;
                int var178 = A;
                A = rlc(var178);
                A = A | 92;
                H = A;
                int var181 = IX() + 5;
                wMem(var181, 0, 37496);
                int var182 = HL();
                A = mem(var182, 37500);
                A = A & 7;
                F = A - 7;
                if (F == 0) {
                  int var211 = IX() + 5;
                  int var212 = mem(var211, 37507) - 1;
                  wMem(var211, var212, 37507);
                  int var213 = var212 & 255;
                  wMem(var211, var213, 37507);
                }

                int var186 = HL();
                A = mem(var186, 37510);
                A = A | 7;
                int var189 = HL();
                wMem(var189, A, 37513);
                int var190 = DE() + 1 & '\uffff';
                DE(var190);
                int var191 = DE();
                A = mem(var191, 37515);
                H = A;
                H = H - 1 & 255;
                F = H;
                int var194 = IX() + 6;
                A = mem(var194, 37518);
                int var196 = HL();
                wMem(var196, A, 37521);
                H = H + 1 & 255;
                F = H;
                int var198 = HL();
                A = mem(var198, 37523);
                int var200 = IX() + 5;
                int var201 = mem(var200, 37524);
                A = A & var201;
                int var206 = HL();
                wMem(var206, 255, 37530);
                H = H + 1 & 255;
                F = H;
                int var208 = IX() + 6;
                A = mem(var208, 37533);
                int var210 = HL();
                wMem(var210, A, 37536);
              }
            } else {
              BC(640);
              A = mem(32990, 37458);

              do {
                A = A ^ 24;

                do {
                  B = B - 1 & 255;
                } while (B != 0);

                B = C;
                C = C - 1 & 255;
                F = C;
              } while (F != 0);
            }
          }
        } else {
          IY(33280);
          int var9 = IX() + 9;
          wMem(var9, 0, 37544);
          int var10 = IX() + 2;
          A = mem(var10, 37548);
          int var12 = IX() + 3;
          wMem(var12, A, 37551);
          int var13 = IX() + 5;
          wMem(var13, 128, 37554);

          while (true) {
            label107:
            {
              int var14 = IY();
              A = mem(var14, 37558);
              int var16 = IX() + 3;
              int var17 = mem(var16, 37561);
              A = A + var17 & 255;
              L = A;
              int var22 = IY() + 1;
              H = mem(var22, 37565);
              A = mem(34262, 37568);
              F = A;
              if (F == 0) {
                int var145 = IX() + 5;
                A = mem(var145, 37574);
                int var147 = HL();
                int var148 = mem(var147, 37577);
                A = A & var148;
                F = A;
                if (F == 0) {
                  break label107;
                }

                int var153 = IX() + 9;
                A = mem(var153, 37580);
                wMem(34262, A, 37583);
                int var155 = IX() + 11;
                int var156 = mem(var155, 37586) | 1;
                wMem(var155, var156, 37586);
              }

              int var26 = IX() + 9;
              int var27 = mem(var26, 37590);
              F = A - var27;
              if (F == 0) {
                int var133 = IX() + 11;
                F = mem(var133, 37595) & 1;
                if (F != 0) {
                  int var135 = IX() + 3;
                  B = mem(var135, 37601);
                  int var137 = IX() + 5;
                  A = mem(var137, 37604);
                  C = 1;
                  F = A - 4;
                  if (F >= 0) {
                    C = 0;
                    F = A - 16;
                    if (F >= 0) {
                      B = B - 1 & 255;
                      C = 3;
                      F = A - 64;
                      if (F >= 0) {
                        C = 2;
                      }
                    }
                  }

                  int var140 = BC();
                  wMem16(34258, var140, 37628);
                  A = IYL;
                  A = A - 16 & 255;
                  wMem(34255, A, 37636);
                  int lastHL = HL();
                  $36508(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
                  HL(lastHL);
                }
              }
            }

            int var30 = IX() + 5;
            A = mem(var30, 37646);
            int var32 = HL();
            int var33 = mem(var32, 37649);
            A = A | var33;
            int var38 = HL();
            wMem(var38, A, 37650);
            int var39 = IX() + 9;
            A = mem(var39, 37651);
            int var41 = IX() + 1;
            int var42 = mem(var41, 37654);
            A = A + var42 & 255;
            L = A;
            L = L | 128;
            H = 131;
            int var48 = HL();
            E = mem(var48, 37662);
            D = 0;
            int var50 = IY();
            int var51 = DE();
            int var52 = var50 + var51 & '\uffff';
            IY(var52);
            L = L & -129;
            int var54 = HL();
            A = mem(var54, 37669);
            F = A;
            if (F != 0) {
              B = A;
              int var113 = IX() + 1;
              F = mem(var113, 37674) & 128;
              if (F != 0) {
                do {
                  int var124 = IX() + 5;
                  int var125 = mem(var124, 37680);
                  int var126 = rlc(var125);
                  wMem(var124, var126, 37680);
                  int var127 = IX() + 5;
                  F = mem(var127, 37684) & 1;
                  if (F != 0) {
                    int var130 = IX() + 3;
                    int var131 = mem(var130, 37690) - 1;
                    wMem(var130, var131, 37690);
                    int var132 = var131 & 255;
                    wMem(var130, var132, 37690);
                  }

                  B = B - 1 & 255;
                } while (B != 0);
              } else {
                do {
                  int var115 = IX() + 5;
                  int var116 = mem(var115, 37697);
                  int var117 = rrc(var116);
                  wMem(var115, var117, 37697);
                  int var118 = IX() + 5;
                  F = mem(var118, 37701) & 128;
                  if (F != 0) {
                    int var121 = IX() + 3;
                    int var122 = mem(var121, 37707) + 1;
                    wMem(var121, var122, 37707);
                    int var123 = var122 & 255;
                    wMem(var121, var123, 37707);
                  }

                  B = B - 1 & 255;
                } while (B != 0);
              }
            }

            int var57 = IX() + 9;
            A = mem(var57, 37712);
            int var59 = IX() + 4;
            int var60 = mem(var59, 37715);
            F = A - var60;
            if (F == 0) {
              A = mem(34262, 37726);
              F = A & 128;
              if (F != 0) {
                A = A + 1 & 255;
                wMem(34262, A, 37734);
                int var108 = IX() + 11;
                int var109 = mem(var108, 37737) & -2;
                wMem(var108, var109, 37737);
              } else {
                int var65 = IX() + 11;
                F = mem(var65, 37743) & 1;
                if (F != 0) {
                  A = mem(34256, 37749);
                  F = A & 2;
                  if (F != 0) {
                    int var69 = A;
                    A = rrc(var69);
                    int var71 = IX();
                    int var72 = mem(var71, 37757);
                    A = A ^ var72;
                    int var77 = A;
                    A = rlc(var77);
                    int var79 = A;
                    A = rlc(var79);
                    A = A & 2;
                    A = A - 1 & 255;
                    HL(34262);
                    int var83 = HL();
                    int var84 = mem(var83, 37768);
                    A = A + var84 & 255;
                    int var89 = HL();
                    wMem(var89, A, 37769);
                    A = mem(33003, 37770);
                    C = A;
                    A = mem(33824, 37774);
                    F = A - C;
                    if (F == 0) {
                      int var103 = HL();
                      A = mem(var103, 37780);
                      F = A - 12;
                      if (F < 0) {
                        int var106 = HL();
                        wMem(var106, 12, 37785);
                      }
                    }

                    int var93 = HL();
                    A = mem(var93, 37787);
                    int var95 = IX() + 4;
                    int var96 = mem(var95, 37788);
                    F = A - var96;
                    if (F >= 0 && F != 0) {
                      int var99 = HL();
                      wMem(var99, 240, 37795);
                      A = mem(34255, 37797);
                      A = A & 248;
                      wMem(34255, A, 37802);
                      A = 0;
                      wMem(34257, A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var110 = IX() + 9;
            int var111 = mem(var110, 37720) + 1;
            wMem(var110, var111, 37720);
            int var112 = var111 & 255;
            wMem(var110, var112, 37720);
          }
        }
      }

      DE(8);
      int var5 = IX();
      int var6 = DE();
      int var7 = var5 + var6 & '\uffff';
      IX(var7);
    }
  }

  public void $37841(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    H = 164;
    A = mem(41983);
    L = A;

    do {
      int var2 = HL();
      C = mem(var2);
      C = C & -129;
      A = mem(33824);
      A = A | 64;
      F = A - C;
      if (F == 0) {
        int var9 = HL();
        A = mem(var9);
        int var11 = A;
        A = rlc(var11);
        A = A & 1;
        A = A + 92 & 255;
        D = A;
        H = H + 1 & 255;
        F = H;
        int var16 = HL();
        E = mem(var16);
        H = H - 1 & 255;
        F = H;
        int var19 = DE();
        A = mem(var19);
        A = A & 7;
        F = A - 7;
        if (F != 0) {
          A = mem(34251);
          A = A + L & 255;
          A = A & 3;
          A = A + 3 & 255;
          C = A;
          int var27 = DE();
          A = mem(var27);
          A = A & 248;
          A = A | C;
          int var31 = DE();
          wMem(var31, A);
          int var32 = HL();
          A = mem(var32);
          int var34 = A;
          A = rlc(var34);
          int var36 = A;
          A = rlc(var36);
          int var38 = A;
          A = rlc(var38);
          int var40 = A;
          A = rlc(var40);
          A = A & 8;
          A = A + 96 & 255;
          D = A;
          int lastHL = HL(); //FIXME:
          HL(32993);
          B = 8;
          $38555(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
          HL(lastHL);
        } else {
          IX(34172);

          while (true) {
            int var44 = IX() + 2;
            int var45 = mem(var44) + 1;
            wMem(var44, var45);
            int var46 = var45 & 255;
            wMem(var44, var46);
            int var47 = IX() + 2;
            A = mem(var47);
            F = A - 58;
            if (F != 0) {
              A = mem(32990);
              C = 128;

              do {
                A = A ^ 24;
                E = A;
                A = 144;
                A = A - C & 255;
                B = A;
                A = E;

                do {
                  B = B - 1 & 0xff;
                } while (B != 0);

                C = C - 1 & 255;
                C = C - 1 & 255;
                F = C;
              } while (F != 0);

              A = mem(34270);
              A = A + 1 & 255;
              wMem(34270, A);
              if (F == 0) {
                A = 1;
                wMem(34271, A);
              }

              int var58 = HL();
              int var59 = mem(var58) & -65;
              int var60 = HL();
              wMem(var60, var59);
              break;
            }

            int var61 = IX() + 2;
            wMem(var61, 48);
          }
        }
      }

      L = L + 1 & 255;
      F = L;
    } while (F != 0);

  }

  public void $37974(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    B = 16;

    do {
      F = C & 1;
      int var2 = DE();
      A = mem(var2, 37978);
      if (F != 0) {
        int var35 = HL();
        int var36 = mem(var35, 37981);
        A = A & var36;
        F = A;
        if (F != 0) {
          return;
        }

        int var41 = DE();
        A = mem(var41, 37983);
        int var43 = HL();
        int var44 = mem(var43, 37984);
        A = A | var44;
      }

      int var4 = HL();
      wMem(var4, A, 37985);
      L = L + 1 & 255;
      F = L;
      int var6 = DE() + 1 & '\uffff';
      DE(var6);
      F = C & 1;
      int var8 = DE();
      A = mem(var8, 37990);
      if (F != 0) {
        int var21 = HL();
        int var22 = mem(var21, 37993);
        A = A & var22;
        F = A;
        if (F != 0) {
          return;
        }

        int var27 = DE();
        A = mem(var27, 37995);
        int var29 = HL();
        int var30 = mem(var29, 37996);
        A = A | var30;
      }

      int var10 = HL();
      wMem(var10, A, 37997);
      L = L - 1 & 255;
      H = H + 1 & 255;
      F = H;
      int var13 = DE() + 1 & '\uffff';
      DE(var13);
      A = H;
      A = A & 7;
      F = A;
      if (F == 0) {
        A = H;
        A = A - 8 & 255;
        H = A;
        A = L;
        A = A + 32 & 255;
        L = A;
        A = A & 224;
        F = A;
        if (F == 0) {
          A = H;
          A = A + 8 & 255;
          H = A;
        }
      }

      B = B - 1 & 255;
    } while (B != 0);

    A = 0;
  }

  public void $38137(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem16(32983);
    HL(var1);
    A = H;
    A = A & 1;
    int var3 = A;
    A = rlc(var3);
    int var5 = A;
    A = rlc(var5);
    int var7 = A;
    A = rlc(var7);
    A = A + 112 & 255;
    H = A;
    E = L;
    D = H;
    A = mem(32985);
    F = A;
    if (F != 0) {
      B = A;
      A = mem(32982);
      F = A;
      if (F == 0) {
        int var33 = HL();
        A = mem(var33);
        int var35 = A;
        A = rlc(var35);
        int var37 = A;
        A = rlc(var37);
        H = H + 1 & 255;
        H = H + 1 & 255;
        F = H;
        int var41 = HL();
        C = mem(var41);
        int var43 = C;
        C = rrc(var43);
        int var45 = C;
        C = rrc(var45);
      } else {
        int var14 = HL();
        A = mem(var14);
        int var16 = A;
        A = rrc(var16);
        int var18 = A;
        A = rrc(var18);
        H = H + 1 & 255;
        H = H + 1 & 255;
        F = H;
        int var22 = HL();
        C = mem(var22);
        int var24 = C;
        C = rlc(var24);
        int var26 = C;
        C = rlc(var26);
      }

      do {
        int var28 = DE();
        wMem(var28, A);
        int var29 = HL();
        wMem(var29, C);
        L = L + 1 & 255;
        E = E + 1 & 255;
        F = E;
        B = B - 1 & 0xff;
      } while (B != 0);

    }
  }

  public void $38196(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(33824);
    F = A - 35;
    if (F == 0) {
      A = mem(34271);
      F = A;
      if (F == 0) {
        A = mem(34251);
        A = A & 2;
        int var25 = A;
        A = rrc(var25);
        int var27 = A;
        A = rrc(var27);
        int var29 = A;
        A = rrc(var29);
        int var31 = A;
        A = rrc(var31);
        A = A | 128;
        E = A;
        A = mem(34255);
        F = A - 208;
        if (F != 0) {
          E = 192;
          F = A - 192;
          if (F < 0) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        HL(17733);
        int var36 = HL();
        wMem16(23918, var36);
        HL(1799);
        int var37 = HL();
        wMem16(23950, var37);
      } else {
        A = mem(34259);
        A = A & 31;
        F = A - 6;
        if (F < 0) {
          A = 2;
          wMem(34271, A);
        }
      }
    } else {
      A = mem(33824);
      F = A - 33;
      if (F == 0) {
        A = mem(34251);
        A = A & 1;
        int var7 = A;
        A = rrc(var7);
        int var9 = A;
        A = rrc(var9);
        int var11 = A;
        A = rrc(var11);
        E = A;
        A = mem(34271);
        F = A - 3;
        if (F == 0) {
          E = E | 64;
        }

        D = 166;
        IX(33488);
        BC(4124);
        $38504(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        HL(1799);
        int var15 = HL();
        wMem16(23996, var15);
        int var16 = HL();
        wMem16(24028, var16);
      }
    }
  }

  public void $38344(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem16(34259, 38344);
    HL(var1);
    B = 0;
    A = mem(32986, 38349);
    A = A & 1;
    A = A + 64 & 255;
    E = A;
    D = 0;
    int var5 = HL();
    int var6 = DE();
    int var7 = var5 + var6 & '\uffff';
    HL(var7);
    A = mem(32964, 38360);
    int var9 = HL();
    int var10 = mem(var9, 38363);
    F = A - var10;
    if (F == 0) {
      A = mem(34257, 38366);
      F = A;
      if (F == 0) {
        A = mem(34258, 38372);
        A = A & 3;
        int var78 = A;
        A = rlc(var78);
        int var80 = A;
        A = rlc(var80);
        B = A;
        A = mem(32986, 38380);
        A = A & 1;
        A = A - 1 & 255;
        A = A ^ 12;
        A = A ^ B;
        A = A & 12;
        B = A;
      }
    }

    int var13 = mem16(34259, 38392);
    HL(var13);
    DE(31);
    C = 15;
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    int var14 = HL() + 1 & '\uffff';
    HL(var14);
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    int var15 = HL();
    int var16 = DE();
    int var17 = var15 + var16 & '\uffff';
    HL(var17);
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    int var18 = HL() + 1 & '\uffff';
    HL(var18);
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    A = mem(34255, 38415);
    A = A + B & 255;
    C = A;
    int var21 = HL();
    int var22 = DE();
    int var23 = var21 + var22 & '\uffff';
    HL(var23);
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    int var24 = HL() + 1 & '\uffff';
    HL(var24);
    $38430(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
    A = mem(34255, 38455);
    A = A + B & 255;
    IXH = 130;
    IXL = A;
    A = mem(34256, 38464);
    A = A & 1;
    int var29 = A;
    A = rrc(var29);
    E = A;
    A = mem(34258, 38471);
    A = A & 3;
    int var33 = A;
    A = rrc(var33);
    int var35 = A;
    A = rrc(var35);
    int var37 = A;
    A = rrc(var37);
    A = A | E;
    E = A;
    D = 157;
    A = mem(33824, 38483);
    F = A - 29;
    if (F == 0) {
      D = 182;
      A = E;
      A = A ^ 128;
      E = A;
    }

    B = 16;
    A = mem(34259, 38498);
    A = A & 31;
    C = A;

    do {
      int var44 = IX();
      A = mem(var44, 38504);
      int var46 = IX() + 1;
      H = mem(var46, 38507);
      A = A | C;
      L = A;
      int var49 = DE();
      A = mem(var49, 38512);
      int var51 = HL();
      int var52 = mem(var51, 38513);
      A = A | var52;
      int var57 = HL();
      wMem(var57, A, 38514);
      int var58 = HL() + 1 & '\uffff';
      HL(var58);
      int var59 = DE() + 1 & '\uffff';
      DE(var59);
      int var60 = DE();
      A = mem(var60, 38517);
      int var62 = HL();
      int var63 = mem(var62, 38518);
      A = A | var63;
      int var68 = HL();
      wMem(var68, A, 38519);
      int var69 = IX() + 1 & '\uffff';
      IX(var69);
      int var70 = IX() + 1 & '\uffff';
      IX(var70);
      int var71 = DE() + 1 & '\uffff';
      DE(var71);
      B = B - 1 & 255;
    } while (B != 0);

  }

  public void $38430(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(32928);
    int var2 = HL();
    int var3 = mem(var2);
    F = A - var3;
    if (F == 0) {
      A = C;
      A = A & 15;
      F = A;
      if (F != 0) {
        A = mem(32928);
        A = A | 7;
        int var14 = HL();
        wMem(var14, A);
      }
    }

    A = mem(32955);
    int var7 = HL();
    int var8 = mem(var7);
    F = A - var8;
  }

  public void $38504(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    do {
      int var1 = IX();
      A = mem(var1);
      int var3 = IX() + 1;
      H = mem(var3);
      A = A | C;
      L = A;
      int var6 = DE();
      A = mem(var6);
      int var8 = HL();
      int var9 = mem(var8);
      A = A | var9;
      int var14 = HL();
      wMem(var14, A);
      int var15 = HL() + 1 & '\uffff';
      HL(var15);
      int var16 = DE() + 1 & '\uffff';
      DE(var16);
      int var17 = DE();
      A = mem(var17);
      int var19 = HL();
      int var20 = mem(var19);
      A = A | var20;
      int var25 = HL();
      wMem(var25, A);
      int var26 = IX() + 1 & '\uffff';
      IX(var26);
      int var27 = IX() + 1 & '\uffff';
      IX(var27);
      int var28 = DE() + 1 & '\uffff';
      DE(var28);
      B = B - 1 & 0xff;
    } while (B != 0);

  }

  public void $38528(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    do {
      A = mem(IX(), 38528);
      $38545(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
      IX(IX() + 1 & '\uffff');
      E = E + 1 & 255;
      A = D;
      A = A - 8 & 255;
      D = A;
      C = C - 1 & 255;
      F = C;
    } while (F != 0);

  }

  public void $38545(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    H = 7;
    L = A;
    L = L | 128;
    HL(HL() * 2 & '\uffff');
    HL(HL() * 2 & '\uffff');
    HL(HL() * 2 & '\uffff');
    B = 8;

    $38555(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
  }

  public void $38562(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    while (true) {
      int var1 = HL();
      A = mem(var1, 38562);
      F = A - 255;
      if (F == 0) {
        return;
      }

      BC(100);
      A = 0;
      int var5 = HL();
      E = mem(var5, 38570);
      D = E;

      while (true) {
        D = D - 1 & 255;
        F = D;
        if (F == 0) {
          D = E;
          A = A ^ 24;
        }

        B = B - 1 & 255;
        if (B == 0) {
          int temp1 = AFx();
          AFx(AF());
          AF(temp1);
          A = C;
          F = A - 50;
          if (F == 0) {
            int var12 = E;
            E = rlc(var12);
          }

          temp1 = AFx();
          AFx(AF());
          AF(temp1);
          C = C - 1 & 255;
          F = C;
          if (F == 0) {
            $38601(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
            if (F != 0) {
              return;
            }

            int var11 = HL() + 1 & '\uffff';
            HL(var11);
            break;
          }
        }
      }
    }
  }

  public void $38601(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(34254, 38601);
    F = A;
    if (F != 0) {
      A = in(31);
      F = A & 16;
      if (F != 0) {
        return;
      }
    }

    BC(45054);
    A = in(BC());
    A = A & 1;
    F = A - 1;
  }

  public void $38622(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {

  }

  public void $38555(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    do {
      A = mem(HL(), 38555);
      wMem(DE(), A, 38556);
      HL(HL() + 1 & '\uffff');
      D = D + 1 & 255;
      F = D;
      B = B - 1 & 0xff;
    } while (B != 0);
  }

  public void $35211(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(34252, 35211);
    HL(20640);
    F = A;
    if (F != 0) {
      B = A;

      do {
        C = 0;
        int lastHL = HL(); //FIXME
        int lastBC = BC();//FIXME
        A = mem(34273, 35224);
        int var4 = A;
        A = rlc(var4);
        int var6 = A;
        A = rlc(var6);
        int var8 = A;
        A = rlc(var8);
        A = A & 96;
        E = A;
        D = 157;
        $37974(A, F, B, C, D, E, H, L, IXH, IXL, IYH, IYL);
        HL(lastHL);//FIXME
        BC(lastBC);//FIXME
        int var11 = HL() + 1 & '\uffff';
        HL(var11);
        int var12 = HL() + 1 & '\uffff';
        HL(var12);
        B = B - 1 & 255;
      } while (B != 0);

    }
  }

  public void $38064(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(33003);
    wMem(33824, A);
    A = mem(34259);
    A = A & 31;
    A = A + 160 & 255;
    wMem(34259, A);
    A = 93;
    wMem(34260, A);
    A = 208;
    wMem(34255, A);
    A = 0;
    wMem(34257, A);
  }

  public void $38276(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    A = mem(33824);
    F = A - 33;
    if (F == 0) {
      A = mem(34259);
      F = A - 188;
      if (F == 0) {
        A = 0;
        wMem(34251, A);
        A = 3;
        wMem(34271, A);
      }
    }
  }
}
