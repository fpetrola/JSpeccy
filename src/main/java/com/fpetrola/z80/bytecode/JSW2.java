package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.MiniZX;

public class JSW2 extends MiniZX {

  private int doReturn;

  public static void main(String[] args) {
  }

 /* private void extracted(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL, int Ax, int Fx) {
    label205:
    while (true) {
      A = 0;
      mem[34254] = A & 0xff;
      mem[34273] = A & 0xff;
      mem[34253] = A & 0xff;
      mem[34257] = A & 0xff;
      mem[34251] = A & 0xff;
      mem[34272] = A & 0xff;
      mem[34271] = A & 0xff;
      A = 7;
      mem[34252] = A & 0xff;
      A = 208;
      mem[34255] = A & 0xff;
      A = 33;
      mem[33824] = A & 0xff;
      H = 23988 >> 8;
      L = 23988 & 0xFF;
      int var2 = pair(H, L);
      mem[34259] = var2 & 0xFF;
      mem[34259 + 1] = var2 >> 8;
      H = 34172 >> 8;
      L = 34172 & 0xFF;
      int var3 = pair(H, L);
      mem[var3] = 48 & 0xff;
      int var4 = pair(H, L) + 1 & '\uffff';
      H = var4 >> 8;
      L = var4 & 0xFF;
      int address2 = pair(H, L);
      mem[address2] = 48 & 0xff;
      int value4 = pair(H, L) + 1 & '\uffff';
      H = value4 >> 8;
      L = value4 & 0xFF;
      int var7 = pair(H, L);
      mem[var7] = 48 & 0xff;
      H = 164;
      A = mem[41983];
      L = A;
      mem[34270] = A & 0xff;

      do {
        int address = pair(H, L);
        int address1 = pair(H, L);
        mem[address1] = (mem[address] | 64) & 0xff;
        L = L + 1 & 255;
        F = L;
      } while (F != 0);

      H = 34274 >> 8;
      L = 34274 & 0xFF;
      int var13 = pair(H, L);
      int var14 = mem[var13] | 1;
      int var15 = pair(H, L);
      mem[var15] = var14 & 0xff;

      label197:
      while (true) {
        H = 16384 >> 8;
        L = 16384 & 0xFF;
        D = 16385 >> 8;
        E = 16385 & 0xFF;
        B = 6143 >> 8;
        C = 6143 & 0xFF;
        int var16 = pair(H, L);
        mem[var16] = 0 & 0xff;
        while (pair(B, C) != 0) {
          int address6 = pair(H, L);
          int address15 = pair(D, E);
          mem[address15] = mem[address6] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        H = 38912 >> 8;
        L = 38912 & 0xFF;
        B = 768 >> 8;
        C = 768 & 0xFF;
        while (pair(B, C) != 0) {
          int address5 = pair(H, L);
          int address14 = pair(D, E);
          mem[address14] = mem[address5] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        H = 23136 >> 8;
        L = 23136 & 0xFF;
        D = 23137 >> 8;
        E = 23137 & 0xFF;
        B = 31 >> 8;
        C = 31 & 0xFF;
        int var17 = pair(H, L);
        mem[var17] = 70 & 0xff;
        while (pair(B, C) != 0) {
          int address4 = pair(H, L);
          int address13 = pair(D, E);
          mem[address13] = mem[address4] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        IXH = 33876 >> 8;
        IXL = 33876 & 0xFF;
        D = 20576 >> 8;
        E = 20576 & 0xFF;
        C = 32;
        do {
          int address1 = pair(IXH, IXL);
          A = mem[address1];
          H = 7;
          L = A;
          L = L | 128;
          int value3 = pair(H, L) * 2 & '\uffff';
          H = value3 >> 8;
          L = value3 & 0xFF;
          int value2 = pair(H, L) * 2 & '\uffff';
          H = value2 >> 8;
          L = value2 & 0xFF;
          int value1 = pair(H, L) * 2 & '\uffff';
          H = value1 >> 8;
          L = value1 & 0xFF;
          B = 8;

          do {
            int address = pair(H, L);
            A = mem[address];
            int address11 = pair(D, E);
            mem[address11] = A & 0xff;
            int value = pair(H, L) + 1 & '\uffff';
            H = value >> 8;
            L = value & 0xFF;
            D = D + 1 & 255;
            F = D;
            B = B - 1 & 0xff;
          } while (B != 0);
          int value = pair(IXH, IXL) + 1 & '\uffff';
          IXH = value >> 8;
          IXL = value & 0xFF;
          E = E + 1 & 255;
          A = D;
          A = A - 8 & 255;
          D = A;
          C = C - 1 & 255;
          F = C;
        } while (F != 0);

        D = 22528 >> 8;
        E = 22528 & 0xFF;

        do {
          int var18 = pair(D, E);
          A = mem[var18];
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
                          int var258 = pair(D, E);
                          mem[var258] = A & 0xff;
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
                    H = 33841 >> 8;
                    L = 33841 & 0xFF;
                    int var252 = pair(H, L);
                    int var253 = pair(B, C);
                    int var254 = var252 + var253 & '\uffff';
                    H = var254 >> 8;
                    L = var254 & 0xFF;
                    int lastDE = pair(D, E);
                    F = D & 1;
                    D = 64;
                    if (F != 0) {
                      D = 72;
                    }

                    B = 8;
                    do {
                      int address = pair(H, L);
                      A = mem[address];
                      int address1 = pair(D, E);
                      mem[address1] = A & 0xff;
                      int value = pair(H, L) + 1 & '\uffff';
                      H = value >> 8;
                      L = value & 0xFF;
                      D = D + 1 & 255;
                      F = D;
                      B = B - 1 & 0xff;
                    } while (B != 0);
                    D = lastDE >> 8;
                    E = lastDE & 0xFF;
                  }
                }
              }
            }
          }

          int var21 = pair(D, E) + 1 & '\uffff';
          D = var21 >> 8;
          E = var21 & 0xFF;
          A = D;
          F = A - 90;
        } while (F != 0);

        B = 31 >> 8;
        C = 31 & 0xFF;
        A = 0;

        do {
          E = in(pair(B, C));
          A = A | E;
          B = B - 1 & 255;
        } while (B != 0);

        A = A & 32;
        F = A;
        if (F == 0) {
          A = 1;
          mem[34254] = A & 0xff;
        }

        H = 34299 >> 8;
        L = 34299 & 0xFF;
        boolean completed = false;
        while (true) {
          int var111 = pair(H, L);
          A = mem[var111];
          F = A - 255;
          if (F == 0) {
            break;
          }

          B = 100 >> 8;
          C = 100 & 0xFF;
          A = 0;
          int var5 = pair(H, L);
          E = mem[var5];
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
              int temp1 = pair(Ax, Fx);
              int value1 = pair(A, F);
              Ax = value1 >> 8;
              Fx = value1 & 0xFF;
              A = temp1 >> 8;
              F = temp1 & 0xFF;
              A = C;
              F = A - 50;
              if (F == 0) {
                int var12 = E;
                E = rlc(var12);
              }

              temp1 = pair(Ax, Fx);
              int value = pair(A, F);
              Ax = value >> 8;
              Fx = value & 0xFF;
              A = temp1 >> 8;
              F = temp1 & 0xFF;
              C = C - 1 & 255;
              F = C;
              if (F == 0) {
                boolean finished = false;
                A = mem[34254];
                F = A;
                if (F != 0) {
                  A = in(31);
                  F = A & 16;
                  if (F != 0) {
                    finished = true;
                  }
                }
                if (!finished) {
                  B = 45054 >> 8;
                  C = 45054 & 0xFF;
                  A = in(pair(B, C));
                  A = A & 1;
                  F = A - 1;
                }

                if (F != 0) {
                  completed = true;
                  break;
                }

                int var11 = pair(H, L) + 1 & '\uffff';
                H = var11 >> 8;
                L = var11 & 0xFF;
                break;
              }
            }
          }
          if (completed) break;
        }
        if (F != 0) {
          break;
        }

        A = 0;
        mem[34276] = A & 0xff;

        while (true) {
          H = 22528 >> 8;
          L = 22528 & 0xFF;
          int var1 = pair(H, L);
          A = mem[var1];
          A = A & 7;

          do {
            int var41 = pair(H, L);
            A = mem[var41];
            A = A + 3 & 255;
            A = A & 7;
            D = A;
            int var8 = pair(H, L);
            A = mem[var8];
            A = A + 24 & 255;
            A = A & 184;
            A = A | D;
            int var131 = pair(H, L);
            mem[var131] = A & 0xff;
            int var141 = pair(H, L) + 1 & '\uffff';
            H = var141 >> 8;
            L = var141 & 0xFF;
            A = H;
            F = A - 91;
          } while (F != 0);

          H = 23136 >> 8;
          L = 23136 & 0xFF;
          D = 23137 >> 8;
          E = 23137 & 0xFF;
          B = 31 >> 8;
          C = 31 & 0xFF;
          int var225 = pair(H, L);
          mem[var225] = 79 & 0xff;
          while (pair(B, C) != 0) {
            int address3 = pair(H, L);
            int address12 = pair(D, E);
            mem[address12] = mem[address3] & 0xff;
            B = pair(B, C) - 1 >> 8;
            C = pair(B, C) - 1 & 0xFF;
            H = pair(H, L) + 1 >> 8;
            L = pair(H, L) + 1 & 0xFF;
            D = pair(D, E) + 1 >> 8;
            E = pair(D, E) + 1 & 0xFF;
          }
          A = mem[34276];
          IXH = 33876 >> 8;
          IXL = 33876 & 0xFF;
          E = A;
          D = 0;
          int var227 = pair(IXH, IXL);
          int var228 = pair(D, E);
          int var229 = var227 + var228 & '\uffff';
          IXH = var229 >> 8;
          IXL = var229 & 0xFF;
          D = 20576 >> 8;
          E = 20576 & 0xFF;
          C = 32;
          do {
            int address = pair(IXH, IXL);
            A = mem[address];
            H = 7;
            L = A;
            L = L | 128;
            int value3 = pair(H, L) * 2 & '\uffff';
            H = value3 >> 8;
            L = value3 & 0xFF;
            int value2 = pair(H, L) * 2 & '\uffff';
            H = value2 >> 8;
            L = value2 & 0xFF;
            int value1 = pair(H, L) * 2 & '\uffff';
            H = value1 >> 8;
            L = value1 & 0xFF;
            B = 8;

            do {
              int address11 = pair(H, L);
              A = mem[address11];
              int address1 = pair(D, E);
              mem[address1] = A & 0xff;
              int value = pair(H, L) + 1 & '\uffff';
              H = value >> 8;
              L = value & 0xFF;
              D = D + 1 & 255;
              F = D;
              B = B - 1 & 0xff;
            } while (B != 0);
            int value = pair(IXH, IXL) + 1 & '\uffff';
            IXH = value >> 8;
            IXL = value & 0xFF;
            E = E + 1 & 255;
            A = D;
            A = A - 8 & 255;
            D = A;
            C = C - 1 & 255;
            F = C;
          } while (F != 0);

          A = mem[34276];
          A = A & 31;
          A = A + 50 & 255;

          B = 45054 >> 8;
          C = 45054 & 0xFF;
          A = in(pair(B, C));
          A = A & 1;
          F = A - 1;
          if (F != 0) {
            break label197;
          }

          A = mem[34276];
          A = A + 1 & 255;
          F = A - 224;
          mem[34276] = A & 0xff;
          if (F == 0) {
            break;
          }
        }
      }

      H = 34181 >> 8;
      L = 34181 & 0xFF;
      D = 34175 >> 8;
      E = 34175 & 0xFF;
      B = 6 >> 8;
      C = 6 & 0xFF;
      while (pair(B, C) != 0) {
        int address20 = pair(H, L);
        int address110 = pair(D, E);
        mem[address110] = mem[address20] & 0xff;
        B = pair(B, C) - 1 >> 8;
        C = pair(B, C) - 1 & 0xFF;
        H = pair(H, L) + 1 >> 8;
        L = pair(H, L) + 1 & 0xFF;
        D = pair(D, E) + 1 >> 8;
        E = pair(D, E) + 1 & 0xFF;
      }
      H = 39424 >> 8;
      L = 39424 & 0xFF;
      D = 23040 >> 8;
      E = 23040 & 0xFF;
      B = 256 >> 8;
      C = 256 & 0xFF;
      while (pair(B, C) != 0) {
        int address19 = pair(H, L);
        int address191 = pair(D, E);
        mem[address191] = mem[address19] & 0xff;
        B = pair(B, C) - 1 >> 8;
        C = pair(B, C) - 1 & 0xFF;
        H = pair(H, L) + 1 >> 8;
        L = pair(H, L) + 1 & 0xFF;
        D = pair(D, E) + 1 >> 8;
        E = pair(D, E) + 1 & 0xFF;
      }

      while (true) {
        A = mem[33824];
        A = A | 192;
        H = A;
        L = 0;
        D = 32768 >> 8;
        E = 32768 & 0xFF;
        B = 256 >> 8;
        C = 256 & 0xFF;
        while (pair(B, C) != 0) {
          int address18 = pair(H, L);
          int address181 = pair(D, E);
          mem[address181] = mem[address18] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        IXH = 33008 >> 8;
        IXL = 33008 & 0xFF;
        D = 33024 >> 8;
        E = 33024 & 0xFF;
        A = 8;

        do {
          int var30 = pair(IXH, IXL);
          L = mem[var30];
          L = L & -129;
          H = 20;
          int var33 = pair(H, L) * 2 & '\uffff';
          H = var33 >> 8;
          L = var33 & 0xFF;
          int var34 = pair(H, L) * 2 & '\uffff';
          H = var34 >> 8;
          L = var34 & 0xFF;
          int var35 = pair(H, L) * 2 & '\uffff';
          H = var35 >> 8;
          L = var35 & 0xFF;
          B = 2 >> 8;
          C = 2 & 0xFF;
          while (pair(B, C) != 0) {
            int address3 = pair(H, L);
            int address11 = pair(D, E);
            mem[address11] = mem[address3] & 0xff;
            B = pair(B, C) - 1 >> 8;
            C = pair(B, C) - 1 & 0xFF;
            H = pair(H, L) + 1 >> 8;
            L = pair(H, L) + 1 & 0xFF;
            D = pair(D, E) + 1 >> 8;
            E = pair(D, E) + 1 & 0xFF;
          }
          int var36 = pair(IXH, IXL) + 1;
          C = mem[var36];
          int var38 = pair(H, L);
          mem[var38] = C & 0xff;
          B = 6 >> 8;
          C = 6 & 0xFF;
          while (pair(B, C) != 0) {
            int address = pair(H, L);
            int address1 = pair(D, E);
            mem[address1] = mem[address] & 0xff;
            B = pair(B, C) - 1 >> 8;
            C = pair(B, C) - 1 & 0xFF;
            H = pair(H, L) + 1 >> 8;
            L = pair(H, L) + 1 & 0xFF;
            D = pair(D, E) + 1 >> 8;
            E = pair(D, E) + 1 & 0xFF;
          }
          int var39 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var39 >> 8;
          IXL = var39 & 0xFF;
          int var40 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var40 >> 8;
          IXL = var40 & 0xFF;
          A = A - 1 & 255;
          F = A;
        } while (F != 0);

        H = 34255 >> 8;
        L = 34255 & 0xFF;
        D = 34263 >> 8;
        E = 34263 & 0xFF;
        B = 7 >> 8;
        C = 7 & 0xFF;
        while (pair(B, C) != 0) {
          int address17 = pair(H, L);
          int address171 = pair(D, E);
          mem[address171] = mem[address17] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        H = 32768 >> 8;
        L = 32768 & 0xFF;
        IXH = 24064 >> 8;
        IXL = 24064 & 0xFF;

        do {
          int var16 = pair(H, L);
          A = mem[var16];
          int var33 = A;
          A = rlc(var33);
          int var52 = A;
          A = rlc(var52);
          A = A & 3;
          C = A;
          int var27 = A;
          A = rlc(var27);
          int var44 = A;
          A = rlc(var44);
          int var63 = A;
          A = rlc(var63);
          A = A + C & 255;
          A = A + 160 & 255;
          E = A;
          D = 128;
          int var103 = pair(D, E);
          A = mem[var103];
          int var123 = pair(IXH, IXL);
          mem[var123] = A & 0xff;
          int var135 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var135 >> 8;
          IXL = var135 & 0xFF;
          int var71 = pair(H, L);
          A = mem[var71];
          int var92 = A;
          A = rrc(var92);
          int var112 = A;
          A = rrc(var112);
          int var132 = A;
          A = rrc(var132);
          int var151 = A;
          A = rrc(var151);
          A = A & 3;
          C = A;
          int var26 = A;
          A = rlc(var26);
          int var43 = A;
          A = rlc(var43);
          int var62 = A;
          A = rlc(var62);
          A = A + C & 255;
          A = A + 160 & 255;
          E = A;
          D = 128;
          int var102 = pair(D, E);
          A = mem[var102];
          int var122 = pair(IXH, IXL);
          mem[var122] = A & 0xff;
          int var134 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var134 >> 8;
          IXL = var134 & 0xFF;
          int var17 = pair(H, L);
          A = mem[var17];
          int var19 = A;
          A = rrc(var19);
          int var21 = A;
          A = rrc(var21);
          A = A & 3;
          C = A;
          int var24 = A;
          A = rlc(var24);
          int var42 = A;
          A = rlc(var42);
          int var61 = A;
          A = rlc(var61);
          A = A + C & 255;
          A = A + 160 & 255;
          E = A;
          D = 128;
          int var101 = pair(D, E);
          A = mem[var101];
          int var121 = pair(IXH, IXL);
          mem[var121] = A & 0xff;
          int var133 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var133 >> 8;
          IXL = var133 & 0xFF;
          int var23 = pair(H, L);
          A = mem[var23];
          A = A & 3;
          C = A;
          int var22 = A;
          A = rlc(var22);
          int var41 = A;
          A = rlc(var41);
          int var6 = A;
          A = rlc(var6);
          A = A + C & 255;
          A = A + 160 & 255;
          E = A;
          D = 128;
          int var10 = pair(D, E);
          A = mem[var10];
          int var12 = pair(IXH, IXL);
          mem[var12] = A & 0xff;
          int var131 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var131 >> 8;
          IXL = var131 & 0xFF;
          int var25 = pair(H, L) + 1 & '\uffff';
          H = var25 >> 8;
          L = var25 & 0xFF;
          A = L;
          A = A & 128;
          F = A;
        } while (F == 0);

        A = mem[32985];
        F = A;
        if (F != 0) {
          int var44 = mem[32983 + 1] * 256 + mem[32983];
          H = var44 >> 8;
          L = var44 & 0xFF;
          B = A;
          A = mem[32973];

          do {
            int var46 = pair(H, L);
            mem[var46] = A & 0xff;
            int var47 = pair(H, L) + 1 & '\uffff';
            H = var47 >> 8;
            L = var47 & 0xFF;
            B = B - 1 & 255;
          } while (B != 0);
        }

        A = mem[32989];
        F = A;
        if (F != 0) {
          int var311 = mem[32987 + 1] * 256 + mem[32987];
          H = var311 >> 8;
          L = var311 & 0xFF;
          A = mem[32986];
          A = A & 1;
          int var34 = A;
          A = rlc(var34);
          A = A + 223 & 255;
          E = A;
          D = 255;
          A = mem[32989];
          B = A;
          A = mem[32964];

          do {
            int var39 = pair(H, L);
            mem[var39] = A & 0xff;
            int var40 = pair(H, L);
            int var411 = pair(D, E);
            int var421 = var40 + var411 & '\uffff';
            H = var421 >> 8;
            L = var421 & 0xFF;
            B = B - 1 & 255;
          } while (B != 0);
        }
        IXH = 24064 >> 8;
        IXL = 24064 & 0xFF;
        A = 112;
        mem[36189] = A & 0xff;
        C = 0;

        do {
          E = C;
          int var12 = pair(IXH, IXL);
          A = mem[var12];
          H = 32928 >> 8;
          L = 32928 & 0xFF;
          B = 54 >> 8;
          C = 54 & 0xFF;
          int result = -1;
          while (pair(B, C) != 0 && result != A) {
            int address = pair(H, L);
            result = mem[address];
            H = pair(H, L) + 1 >> 8;
            L = pair(H, L) + 1 & 0xFF;
            B = pair(B, C) - 1 >> 8;
            C = pair(B, C) - 1 & 0xFF;
          }
          C = E;
          B = 8;
          D = 112;

          do {
            int var32 = pair(H, L);
            A = mem[var32];
            int var51 = pair(D, E);
            mem[var51] = A & 0xff;
            int var61 = pair(H, L) + 1 & '\uffff';
            H = var61 >> 8;
            L = var61 & 0xFF;
            D = D + 1 & 255;
            F = D;
            B = B - 1 & 255;
          } while (B != 0);

          int var91 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var91 >> 8;
          IXL = var91 & 0xFF;
          C = C + 1 & 255;
          F = C;
        } while (F != 0);
        IXH = 24320 >> 8;
        IXL = 24320 & 0xFF;
        A = 120;
        mem[36189] = A & 0xff;
        C = 0;

        do {
          E = C;
          int var11 = pair(IXH, IXL);
          A = mem[var11];
          H = 32928 >> 8;
          L = 32928 & 0xFF;
          B = 54 >> 8;
          C = 54 & 0xFF;
          int result = -1;
          while (pair(B, C) != 0 && result != A) {
            int address = pair(H, L);
            result = mem[address];
            H = pair(H, L) + 1 >> 8;
            L = pair(H, L) + 1 & 0xFF;
            B = pair(B, C) - 1 >> 8;
            C = pair(B, C) - 1 & 0xFF;
          }
          C = E;
          B = 8;
          D = 120;

          do {
            int var31 = pair(H, L);
            A = mem[var31];
            int var5 = pair(D, E);
            mem[var5] = A & 0xff;
            int var6 = pair(H, L) + 1 & '\uffff';
            H = var6 >> 8;
            L = var6 & 0xFF;
            D = D + 1 & 255;
            F = D;
            B = B - 1 & 255;
          } while (B != 0);

          int var9 = pair(IXH, IXL) + 1 & '\uffff';
          IXH = var9 >> 8;
          IXL = var9 & 0xFF;
          C = C + 1 & 255;
          F = C;
        } while (F != 0);
        H = 20480 >> 8;
        L = 20480 & 0xFF;
        D = 20481 >> 8;
        E = 20481 & 0xFF;
        B = 2047 >> 8;
        C = 2047 & 0xFF;
        int var42 = pair(H, L);
        mem[var42] = 0 & 0xff;
        while (pair(B, C) != 0) {
          int address16 = pair(H, L);
          int address161 = pair(D, E);
          mem[address161] = mem[address16] & 0xff;
          B = pair(B, C) - 1 >> 8;
          C = pair(B, C) - 1 & 0xFF;
          H = pair(H, L) + 1 >> 8;
          L = pair(H, L) + 1 & 0xFF;
          D = pair(D, E) + 1 >> 8;
          E = pair(D, E) + 1 & 0xFF;
        }
        IXH = 32896 >> 8;
        IXL = 32896 & 0xFF;
        C = 32;
        D = 20480 >> 8;
        E = 20480 & 0xFF;
        do {
          int address4 = pair(IXH, IXL);
          A = mem[address4];
          H = 7;
          L = A;
          L = L | 128;
          int value3 = pair(H, L) * 2 & '\uffff';
          H = value3 >> 8;
          L = value3 & 0xFF;
          int value2 = pair(H, L) * 2 & '\uffff';
          H = value2 >> 8;
          L = value2 & 0xFF;
          int value1 = pair(H, L) * 2 & '\uffff';
          H = value1 >> 8;
          L = value1 & 0xFF;
          B = 8;

          do {
            int address = pair(H, L);
            A = mem[address];
            int address1 = pair(D, E);
            mem[address1] = A & 0xff;
            int value = pair(H, L) + 1 & '\uffff';
            H = value >> 8;
            L = value & 0xFF;
            D = D + 1 & 255;
            F = D;
            B = B - 1 & 0xff;
          } while (B != 0);
          int value = pair(IXH, IXL) + 1 & '\uffff';
          IXH = value >> 8;
          IXL = value & 0xFF;
          E = E + 1 & 255;
          A = D;
          A = A - 8 & 255;
          D = A;
          C = C - 1 & 255;
          F = C;
        } while (F != 0);

        IXH = 34132 >> 8;
        IXL = 34132 & 0xFF;
        D = 20576 >> 8;
        E = 20576 & 0xFF;
        C = 32;
        do {
          int address3 = pair(IXH, IXL);
          A = mem[address3];
          H = 7;
          L = A;
          L = L | 128;
          int value3 = pair(H, L) * 2 & '\uffff';
          H = value3 >> 8;
          L = value3 & 0xFF;
          int value2 = pair(H, L) * 2 & '\uffff';
          H = value2 >> 8;
          L = value2 & 0xFF;
          int value1 = pair(H, L) * 2 & '\uffff';
          H = value1 >> 8;
          L = value1 & 0xFF;
          B = 8;

          do {
            int address = pair(H, L);
            A = mem[address];
            int address1 = pair(D, E);
            mem[address1] = A & 0xff;
            int value = pair(H, L) + 1 & '\uffff';
            H = value >> 8;
            L = value & 0xFF;
            D = D + 1 & 255;
            F = D;
            B = B - 1 & 0xff;
          } while (B != 0);
          int value = pair(IXH, IXL) + 1 & '\uffff';
          IXH = value >> 8;
          IXL = value & 0xFF;
          E = E + 1 & 255;
          A = D;
          A = A - 8 & 255;
          D = A;
          C = C - 1 & 255;
          F = C;
        } while (F != 0);

        A = mem[32990];
        C = 254;
        A = 0;
        mem[34262] = A & 0xff;

        while (true) {
          label215:
          {
            A = mem[34252];
            H = 20640 >> 8;
            L = 20640 & 0xFF;
            F = A;
            if (F != 0) {
              B = A;

              do {
                C = 0;
                int lastHL1 = pair(H, L); //FIXME
                int lastBC = pair(B, C);//FIXME
                A = mem[34273];
                int var48 = A;
                A = rlc(var48);
                int var64 = A;
                A = rlc(var64);
                int var86 = A;
                A = rlc(var86);
                A = A & 96;
                E = A;
                D = 157;
                boolean finished = false;
                B = 16;

                do {
                  F = C & 1;
                  int var211 = pair(D, E);
                  A = mem[var211];
                  if (F != 0) {
                    int var35 = pair(H, L);
                    int var36 = mem[var35];
                    A = A & var36;
                    F = A;
                    if (F != 0) {
                      finished = true;
                      break;
                    }

                    int var41 = pair(D, E);
                    A = mem[var41];
                    int var43 = pair(H, L);
                    int var44 = mem[var43];
                    A = A | var44;
                  }

                  int var41 = pair(H, L);
                  mem[var41] = A & 0xff;
                  L = L + 1 & 255;
                  F = L;
                  int var6 = pair(D, E) + 1 & '\uffff';
                  D = var6 >> 8;
                  E = var6 & 0xFF;
                  F = C & 1;
                  int var8 = pair(D, E);
                  A = mem[var8];
                  if (F != 0) {
                    int var21 = pair(H, L);
                    int var22 = mem[var21];
                    A = A & var22;
                    F = A;
                    if (F != 0) {
                      finished = true;
                      break;
                    }

                    int var27 = pair(D, E);
                    A = mem[var27];
                    int var29 = pair(H, L);
                    int var30 = mem[var29];
                    A = A | var30;
                  }

                  int var10 = pair(H, L);
                  mem[var10] = A & 0xff;
                  L = L - 1 & 255;
                  H = H + 1 & 255;
                  F = H;
                  int var131 = pair(D, E) + 1 & '\uffff';
                  D = var131 >> 8;
                  E = var131 & 0xFF;
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
                if (!finished) {
                  A = 0;
                }

                //FIXME
                H = lastHL1 >> 8;
                L = lastHL1 & 0xFF;
                //FIXME
                B = lastBC >> 8;
                C = lastBC & 0xFF;
                int var112 = pair(H, L) + 1 & '\uffff';
                H = var112 >> 8;
                L = var112 & 0xFF;
                int var121 = pair(H, L) + 1 & '\uffff';
                H = var121 >> 8;
                L = var121 & 0xFF;
                B = B - 1 & 255;
              } while (B != 0);

            }
            H = 24064 >> 8;
            L = 24064 & 0xFF;
            D = 23552 >> 8;
            E = 23552 & 0xFF;
            B = 512 >> 8;
            C = 512 & 0xFF;
            while (pair(B, C) != 0) {
              int address10 = pair(H, L);
              int address15 = pair(D, E);
              mem[address15] = mem[address10] & 0xff;
              B = pair(B, C) - 1 >> 8;
              C = pair(B, C) - 1 & 0xFF;
              H = pair(H, L) + 1 >> 8;
              L = pair(H, L) + 1 & 0xFF;
              D = pair(D, E) + 1 >> 8;
              E = pair(D, E) + 1 & 0xFF;
            }
            H = 28672 >> 8;
            L = 28672 & 0xFF;
            D = 24576 >> 8;
            E = 24576 & 0xFF;
            B = 4096 >> 8;
            C = 4096 & 0xFF;
            while (pair(B, C) != 0) {
              int address9 = pair(H, L);
              int address14 = pair(D, E);
              mem[address14] = mem[address9] & 0xff;
              B = pair(B, C) - 1 >> 8;
              C = pair(B, C) - 1 & 0xFF;
              H = pair(H, L) + 1 >> 8;
              L = pair(H, L) + 1 & 0xFF;
              D = pair(D, E) + 1 >> 8;
              E = pair(D, E) + 1 & 0xFF;
            }
            IXH = 33024 >> 8;
            IXL = 33024 & 0xFF;

            while (true) {
              int var114 = pair(IXH, IXL);
              A = mem[var114];
              F = A - 255;
              if (F == 0) {
                break;
              }

              A = A & 3;
              F = A;
              if (F != 0) {
                F = A - 1;
                if (F != 0) {
                  F = A - 2;
                  if (F != 0) {
                    int var791 = pair(IXH, IXL);
                    F = mem[var791] & 128;
                    if (F != 0) {
                      int var101 = pair(IXH, IXL) + 1;
                      A = mem[var101];
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
                      int var811 = pair(IXH, IXL) + 1;
                      A = mem[var811];
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

                    int var86 = pair(IXH, IXL) + 1;
                    mem[var86] = A & 0xff;
                    A = A & 127;
                    int var88 = pair(IXH, IXL) + 7;
                    int var891 = mem[var88];
                    F = A - var891;
                    if (F == 0) {
                      int var92 = pair(IXH, IXL);
                      A = mem[var92];
                      A = A ^ 128;
                      int var951 = pair(IXH, IXL);
                      mem[var951] = A & 0xff;
                    }
                  } else {
                    label81:
                    {
                      int var461 = pair(IXH, IXL);
                      A = mem[var461];
                      A = A ^ 8;
                      int var491 = pair(IXH, IXL);
                      mem[var491] = A & 0xff;
                      A = A & 24;
                      F = A;
                      if (F != 0) {
                        int var751 = pair(IXH, IXL);
                        A = mem[var751];
                        A = A + 32 & 255;
                        int var781 = pair(IXH, IXL);
                        mem[var781] = A & 0xff;
                      }

                      int var511 = pair(IXH, IXL) + 3;
                      A = mem[var511];
                      int var53 = pair(IXH, IXL) + 4;
                      int var541 = mem[var53];
                      A = A + var541 & 255;
                      int var592 = pair(IXH, IXL) + 3;
                      mem[var592] = A & 0xff;
                      int var602 = pair(IXH, IXL) + 7;
                      int var611 = mem[var602];
                      F = A - var611;
                      if (F < 0) {
                        int var681 = pair(IXH, IXL) + 6;
                        int var691 = mem[var681];
                        F = A - var691;
                        if (F != 0 && F >= 0) {
                          break label81;
                        }

                        int var721 = pair(IXH, IXL) + 6;
                        A = mem[var721];
                        int var741 = pair(IXH, IXL) + 3;
                        mem[var741] = A & 0xff;
                      }

                      int var64 = pair(IXH, IXL) + 4;
                      A = mem[var64];
                      A = -A & 255;
                      int var67 = pair(IXH, IXL) + 4;
                      mem[var67] = A & 0xff;
                    }
                  }
                } else {
                  int var92 = pair(IXH, IXL);
                  F = mem[var92] & 128;
                  if (F == 0) {
                    int var281 = pair(IXH, IXL);
                    A = mem[var281];
                    A = A - 32 & 255;
                    A = A & 127;
                    int var322 = pair(IXH, IXL);
                    mem[var322] = A & 0xff;
                    F = A - 96;
                    if (F >= 0) {
                      int var341 = pair(IXH, IXL) + 2;
                      A = mem[var341];
                      A = A & 31;
                      int var372 = pair(IXH, IXL) + 6;
                      int var382 = mem[var372];
                      F = A - var382;
                      if (F != 0) {
                        int var422 = pair(IXH, IXL) + 2;
                        int var432 = mem[var422] - 1 & 255;
                        mem[var422] = var432 & 0xff;
                      } else {
                        int var413 = pair(IXH, IXL);
                        mem[var413] = 129 & 0xff;
                      }
                    }
                  } else {
                    int var1141 = pair(IXH, IXL);
                    A = mem[var1141];
                    A = A + 32 & 255;
                    A = A | 128;
                    int var154 = pair(IXH, IXL);
                    mem[var154] = A & 0xff;
                    F = A - 160;
                    if (F < 0) {
                      int var174 = pair(IXH, IXL) + 2;
                      A = mem[var174];
                      A = A & 31;
                      int var207 = pair(IXH, IXL) + 7;
                      int var215 = mem[var207];
                      F = A - var215;
                      if (F != 0) {
                        int var252 = pair(IXH, IXL) + 2;
                        int var264 = mem[var252] + 1;
                        int var272 = var264 & 255;
                        mem[var252] = var272 & 0xff;
                      } else {
                        int var241 = pair(IXH, IXL);
                        mem[var241] = 97 & 0xff;
                      }
                    }
                  }
                }
              }

              D = 8 >> 8;
              E = 8 & 0xFF;
              int var53 = pair(IXH, IXL);
              int var64 = pair(D, E);
              int var710 = var53 + var64 & '\uffff';
              IXH = var710 >> 8;
              IXL = var710 & 0xFF;
            }
            A = mem[34271];
            F = A - 3;
            if (F != 0) {
              boolean finished = false;
              label250:
              {
                label251:
                {
                  label246:
                  {
                    label266:
                    {
                      A = mem[34262];
                      A = A - 1 & 255;
                      F = A & 128;
                      if (F != 0) {
                        A = mem[34257];
                        F = A - 1;
                        if (F == 0) {
                          A = mem[34261];
                          A = A & 254;
                          A = A - 8 & 255;
                          H = 34255 >> 8;
                          L = 34255 & 0xFF;
                          int address21 = pair(H, L);
                          A = A + mem[address21] & 255;
                          int address3 = pair(H, L);
                          mem[address3] = A & 0xff;
                          F = A - 240;
                          if (F >= 0) {
                            A = mem[33003];
                            mem[33824] = A & 0xff;
                            A = mem[34259];
                            A = A & 31;
                            A = A + 160 & 255;
                            mem[34259] = A & 0xff;
                            A = 93;
                            mem[34260] = A & 0xff;
                            A = 208;
                            mem[34255] = A & 0xff;
                            A = 0;
                            mem[34257] = A & 0xff;
                            break label251;
                          }

                          A = A & 240;
                          L = A;
                          A = 0;
                          int var31 = L;
                          L = rlc(var31) & 0xFE;
                          A = (A + carry + 92) & 255;
                          H = A;
                          A = mem[34259];
                          A = A & 31;
                          A = A | L;
                          L = A;
                          int var9 = pair(H, L);
                          mem[34259 + 1] = var9 >> 8;
                          mem[34259] = var9 & 0xFF;
                          A = mem[32946];
                          int address1 = pair(H, L);
                          F = A - mem[address1];
                          if (F == 0) {
                            break label250;
                          }

                          int value = pair(H, L) + 1 & '\uffff';
                          H = value >> 8;
                          L = value & 0xFF;
                          int address = pair(H, L);
                          F = A - mem[address];
                          if (F == 0) {
                            break label250;
                          }

                          A = mem[34261];
                          A = A + 1 & 255;
                          mem[34261] = A & 0xff;
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
                          A = mem[32990];

                          do {
                            A = A ^ 24;
                            B = D;

                            do {
                              B = B - 1 & 255;
                            } while (B != 0);

                            C = C - 1 & 255;
                            F = C;
                          } while (F != 0);

                          A = mem[34261];
                          F = A - 18;
                          if (F == 0) {
                            A = 6;
                            mem[34257] = A & 0xff;
                            finished = true;
                          } else {
                            F = A - 16;
                            if (F != 0) {
                              F = A - 13;
                              if (F != 0) {
                                break label246;
                              }
                            }
                          }

                        }
                        if (!finished) {
                          A = mem[34255];
                          A = A & 14;
                          F = A;
                          if (F != 0) {
                            break label266;
                          }

                          H = mem[34259 + 1] * 256 + mem[34259] >> 8;
                          L = mem[34259 + 1] * 256 + mem[34259] & 0xFF;
                          D = 64 >> 8;
                          E = 64 & 0xFF;
                          int value2 = pair(H, L) + pair(D, E) & '\uffff';
                          H = value2 >> 8;
                          L = value2 & 0xFF;
                          F = H & 2;
                          if (F != 0) {
                            break label251;
                          }

                          A = mem[32955];
                          int address1 = pair(H, L);
                          F = A - mem[address1];
                          if (F == 0) {
                            break label266;
                          }

                          int value1 = pair(H, L) + 1 & '\uffff';
                          H = value1 >> 8;
                          L = value1 & 0xFF;
                          A = mem[32955];
                          int address = pair(H, L);
                          F = A - mem[address];
                          if (F == 0) {
                            break label266;
                          }

                          A = mem[32928];
                          int var323 = pair(H, L);
                          int var324 = mem[var323];
                          F = A - var324;
                          //FIXME
                          int value = pair(H, L) - 1 & 0xffff;
                          H = value >> 8;
                          L = value & 0xFF;
                          if (F == 0) {
                            int var327 = pair(H, L);
                            int var328 = mem[var327];
                            F = A - var328;
                            if (F == 0) {
                              break label266;
                            }
                          }
                        }

                      }
                      if (!finished) {
                        E = 255;
                        A = mem[34262];
                        A = A - 1 & 255;
                        F = A & 128;
                        if (F != 0) {
                          label263:
                          {
                            A = mem[34257];
                            F = A - 12;
                            if (F >= 0) {
                              A = 255;
                              mem[34257] = A & 0xff;
                              IXH = 33024 >> 8;
                              IXL = 33024 & 0xFF;

                              while (true) {
                                int var140 = pair(IXH, IXL);
                                A = mem[var140];
                                F = A - 255;
                                if (F == 0) {
                                  finished = true;
                                  break;
                                }

                                A = A & 3;
                                F = A;
                                if (F != 0) {
                                  F = A - 1;
                                  if (F != 0) {
                                    F = A - 2;
                                    if (F != 0) {
                                      int var218 = pair(IXH, IXL);
                                      F = mem[var218] & 128;
                                      if (F != 0) {
                                        int var240 = pair(IXH, IXL) + 1;
                                        A = mem[var240];
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
                                        int address = pair(IXH, IXL) + 1;
                                        A = mem[address];
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

                                      int address3 = pair(IXH, IXL) + 1;
                                      mem[address3] = A & 0xff;
                                      A = A & 127;
                                      int address1 = pair(IXH, IXL) + 7;
                                      F = A - mem[address1];
                                      if (F == 0) {
                                        int address = pair(IXH, IXL);
                                        A = mem[address];
                                        A = A ^ 128;
                                        int address21 = pair(IXH, IXL);
                                        mem[address21] = A & 0xff;
                                      }
                                    } else {
                                      label265:
                                      {
                                        int address5 = pair(IXH, IXL);
                                        A = mem[address5];
                                        A = A ^ 8;
                                        int address8 = pair(IXH, IXL);
                                        mem[address8] = A & 0xff;
                                        A = A & 24;
                                        F = A;
                                        if (F != 0) {
                                          int address = pair(IXH, IXL);
                                          A = mem[address];
                                          A = A + 32 & 255;
                                          int address1 = pair(IXH, IXL);
                                          mem[address1] = A & 0xff;
                                        }

                                        int address4 = pair(IXH, IXL) + 3;
                                        A = mem[address4];
                                        int address3 = pair(IXH, IXL) + 4;
                                        A = A + mem[address3] & 255;
                                        int address7 = pair(IXH, IXL) + 3;
                                        mem[address7] = A & 0xff;
                                        int address21 = pair(IXH, IXL) + 7;
                                        F = A - mem[address21];
                                        if (F < 0) {
                                          int address1 = pair(IXH, IXL) + 6;
                                          F = A - mem[address1];
                                          if (F != 0 && F >= 0) {
                                            break label265;
                                          }

                                          int address = pair(IXH, IXL) + 6;
                                          A = mem[address];
                                          int address6 = pair(IXH, IXL) + 3;
                                          mem[address6] = A & 0xff;
                                        }

                                        int address = pair(IXH, IXL) + 4;
                                        A = mem[address];
                                        A = -A & 255;
                                        int address1 = pair(IXH, IXL) + 4;
                                        mem[address1] = A & 0xff;
                                      }
                                    }
                                  } else {
                                    int var148 = pair(IXH, IXL);
                                    F = mem[var148] & 128;
                                    if (F == 0) {
                                      int var167 = pair(IXH, IXL);
                                      A = mem[var167];
                                      A = A - 32 & 255;
                                      A = A & 127;
                                      int var171 = pair(IXH, IXL);
                                      mem[var171] = A & 0xff;
                                      F = A - 96;
                                      if (F >= 0) {
                                        int var173 = pair(IXH, IXL) + 2;
                                        A = mem[var173];
                                        A = A & 31;
                                        int address = pair(IXH, IXL) + 6;
                                        F = A - mem[address];
                                        if (F != 0) {
                                          int var181 = pair(IXH, IXL) + 2;
                                          int var182 = mem[var181] - 1 & 255; //FIXME
                                          mem[var181] = var182 & 0xff;
                                        } else {
                                          int var180 = pair(IXH, IXL);
                                          mem[var180] = 129 & 0xff;
                                        }
                                      }
                                    } else {
                                      int var150 = pair(IXH, IXL);
                                      A = mem[var150];
                                      A = A + 32 & 255;
                                      A = A | 128;
                                      int var154 = pair(IXH, IXL);
                                      mem[var154] = A & 0xff;
                                      F = A - 160;
                                      if (F < 0) {
                                        int var156 = pair(IXH, IXL) + 2;
                                        A = mem[var156];
                                        A = A & 31;
                                        int var159 = pair(IXH, IXL) + 7;
                                        int var160 = mem[var159];
                                        F = A - var160;
                                        if (F != 0) {
                                          int address1 = pair(IXH, IXL) + 2;
                                          int var166 = mem[address1] + 1 & 0xff;
                                          int address = pair(IXH, IXL) + 2;
                                          mem[address] = var166 & 0xff;
                                        } else {
                                          int var163 = pair(IXH, IXL);
                                          mem[var163] = 97 & 0xff;
                                        }
                                      }
                                    }
                                  }
                                }

                                D = 8 >> 8;
                                E = 8 & 0xFF;
                                int var144 = pair(IXH, IXL);
                                int var145 = pair(D, E);
                                int var146 = var144 + var145 & '\uffff';
                                IXH = var146 >> 8;
                                IXL = var146 & 0xFF;
                              }
                            }
                            if (!finished) {
                              A = 0;
                              mem[34257] = A & 0xff;
                              A = mem[32973];
                              int address = pair(H, L);
                              int var254 = mem[address];
                              F = A - var254;
                              if (F != 0) {
                                int var259 = pair(H, L) + 1 & '\uffff';
                                H = var259 >> 8;
                                L = var259 & 0xFF;
                                int var260 = pair(H, L);
                                int var261 = mem[var260];
                                F = A - var261;
                                if (F != 0) {
                                  break label263;
                                }
                              }

                              A = mem[32982];
                              A = A - 3 & 255;
                              E = A;
                            }

                          }
                        }
                        if (!finished) {
                          B = 57342 >> 8;
                          C = 57342 & 0xFF;
                          A = in(pair(B, C));
                          A = A & 31;
                          A = A | 32;
                          A = A & E;
                          E = A;
                          A = mem[34271];
                          A = A & 2;
                          int var131 = A;
                          A = rrc(var131);
                          A = A ^ E;
                          E = A;
                          B = 64510 >> 8;
                          C = 64510 & 0xFF;
                          A = in(pair(B, C));
                          A = A & 31;
                          int var18 = A;
                          A = rlc(var18);
                          A = A | 1;
                          A = A & E;
                          E = A;
                          B = 231;
                          A = in(pair(B, C));
                          int var23 = A;
                          A = rrc(var23);
                          A = A | 247;
                          A = A & E;
                          E = A;
                          B = 239;
                          A = in(pair(B, C));
                          A = A | 251;
                          A = A & E;
                          E = A;
                          A = in(pair(B, C));
                          int var31 = A;
                          A = rrc(var31);
                          A = A | 251;
                          A = A & E;
                          E = A;
                          A = mem[34254];
                          F = A;
                          if (F != 0) {
                            B = 31 >> 8;
                            C = 31 & 0xFF;
                            A = in(pair(B, C));
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
                            mem[34272] = A & 0xff;
                          }

                          A = E;
                          A = A & 21;
                          F = A - 21;
                          if (F != 0) {
                            C = C | 8;
                            A = 0;
                            mem[34272] = A & 0xff;
                          }

                          A = mem[34256];
                          A = A + C & 255;
                          C = A;
                          B = 0;
                          H = 33825 >> 8;
                          L = 33825 & 0xFF;
                          int var43 = pair(H, L);
                          int var44 = pair(B, C);
                          int var45 = var43 + var44 & '\uffff';
                          H = var45 >> 8;
                          L = var45 & 0xFF;
                          int var46 = pair(H, L);
                          A = mem[var46];
                          mem[34256] = A & 0xff;
                          B = 32510 >> 8;
                          C = 32510 & 0xFF;
                          A = in(pair(B, C));
                          A = A & 31;
                          F = A - 31;
                          if (F == 0) {
                            B = 239;
                            A = in(pair(B, C));
                            F = A & 1;
                            if (F != 0) {
                              A = mem[34254];
                              F = A;
                              if (F == 0) {
                                break label246;
                              }

                              B = 31 >> 8;
                              C = 31 & 0xFF;
                              A = in(pair(B, C));
                              F = A & 16;
                              if (F == 0) {
                                break label246;
                              }
                            }
                          }

                          A = mem[34271];
                          F = A & 2;
                          if (F == 0) {
                            A = 0;
                            mem[34261] = A & 0xff;
                            mem[34272] = A & 0xff;
                            A = A + 1 & 255;
                            mem[34257] = A & 0xff;
                            A = mem[34262];
                            A = A - 1 & 255;
                            F = A & 128;
                            if (F == 0) {
                              A = 240;
                              mem[34262] = A & 0xff;
                              A = mem[34255];
                              A = A & 240;
                              mem[34255] = A & 0xff;
                              H = 34256 >> 8;
                              L = 34256 & 0xFF;
                              int var123 = pair(H, L);
                              int var124 = mem[var123] | 2;
                              int var125 = pair(H, L);
                              mem[var125] = var124 & 0xff;
                              finished = true;
                            }
                          }
                          if (!finished) {
                            break label246;
                          }
                        }

                      }

                    }
                    if (!finished) {
                      A = mem[34257];
                      F = A - 1;
                      if (F != 0) {
                        H = 34256 >> 8;
                        L = 34256 & 0xFF;
                        int var270 = pair(H, L);
                        int var271 = mem[var270] & -3;
                        int var272 = pair(H, L);
                        mem[var272] = var271 & 0xff;
                        A = mem[34257];
                        F = A;
                        if (F == 0) {
                          A = 2;
                          mem[34257] = A & 0xff;
                          finished = true;
                        } else {
                          A = A + 1 & 255;
                          F = A - 16;
                          if (F == 0) {
                            A = 12;
                          }
                          mem[34257] = A & 0xff;
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
                          A = mem[32990];
                          do {
                            A = A ^ 24;
                            B = D;

                            do {
                              B = B - 1 & 255;
                            } while (B != 0);

                            C = C - 1 & 255;
                            F = C;
                          } while (F != 0);
                          A = mem[34255];
                          A = A + 8 & 255;
                          mem[34255] = A & 0xff;
                          A = A & 240;
                          L = A;
                          A = 0;
                          int var293 = L;
                          carry = 0;//FIXME
                          L = rl(var293);
                          A = (A + 92 + carry) & 255;
                          H = A;
                          A = mem[34259];
                          A = A & 31;
                          A = A | L;
                          L = A;
                          int var299 = pair(H, L);
                          mem[34259] = var299 & 0xFF;
                          mem[34259 + 1] = var299 >> 8;
                          finished = true;
                        }

                      }
                    }

                  }
                  if (!finished) {
                    A = mem[34256];
                    A = A & 2;
                    F = A;
                    if (F == 0) {
                      finished = true;
                    } else {
                      A = mem[34262];
                      A = A - 1 & 255;
                      F = A & 128;
                      if (F == 0) {
                        finished = true;
                      } else {
                        A = mem[34256];
                        A = A & 1;
                        F = A;
                        if (F != 0) {
                          A = mem[34258];
                          F = A;
                          if (F != 0) {
                            A = A - 1 & 255;
                            mem[34258] = A & 0xff;
                            finished = true;
                          } else {
                            A = mem[34257];
                            B = 0 >> 8;
                            C = 0 & 0xFF;
                            F = A;
                            if (F == 0) {
                              int var100 = mem[34259 + 1] * 256 + mem[34259];
                              H = var100 >> 8;
                              L = var100 & 0xFF;
                              B = 0 >> 8;
                              C = 0 & 0xFF;
                              A = mem[32986];
                              A = A - 1 & 255;
                              A = A | 161;
                              A = A ^ 224;
                              E = A;
                              D = 0;
                              int var105 = pair(H, L);
                              int var106 = pair(D, E);
                              int var107 = var105 + var106 & '\uffff';
                              H = var107 >> 8;
                              L = var107 & 0xFF;
                              A = mem[32964];
                              int var109 = pair(H, L);
                              int var110 = mem[var109];
                              F = A - var110;
                              if (F == 0) {
                                B = 32 >> 8;
                                C = 32 & 0xFF;
                                A = mem[32986];
                                F = A;
                                if (F == 0) {
                                  B = 65504 >> 8;
                                  C = 65504 & 0xFF;
                                }
                              }
                            }//FIXME
                            int var67 = mem[34259 + 1] * 256 + mem[34259];
                            H = var67 >> 8;
                            L = var67 & 0xFF;
                            A = L;
                            A = A & 31;
                            F = A;
                            if (F == 0) { //FIXME
                              A = mem[33001];
                              mem[33824] = A & 0xff;
                              A = mem[34259];
                              A = A | 31;
                              A = A & 254;
                              mem[34259] = A & 0xff;
                              doReturn = 1;
                              finished = true;
                            } else {
                              int var69 = pair(H, L);
                              int var70 = pair(B, C);
                              int var71 = var69 + var70 & '\uffff';
                              H = var71 >> 8;
                              L = var71 & 0xFF;
                              //FIXME
                              int value = pair(H, L) - 1 & 0xffff;
                              H = value >> 8;
                              L = value & 0xFF;
                              D = 32 >> 8;
                              E = 32 & 0xFF;
                              int var72 = pair(H, L);
                              int var73 = pair(D, E);
                              int var74 = var72 + var73 & '\uffff';
                              H = var74 >> 8;
                              L = var74 & 0xFF;
                              A = mem[32946];
                              int var76 = pair(H, L);
                              int var77 = mem[var76];
                              F = A - var77;
                              if (F == 0) {
                                finished = true;
                              } else {
                                A = mem[34255];
                                C = C >> 1;
                                A = A + C & 255;
                                B = A;
                                A = A & 15;
                                F = A;
                                if (F != 0) {
                                  A = mem[32946];
                                  int var89 = pair(H, L);
                                  int var90 = pair(D, E);
                                  int var91 = var89 + var90 & '\uffff';
                                  H = var91 >> 8;
                                  L = var91 & 0xFF;
                                  int var92 = pair(H, L);
                                  int var93 = mem[var92];
                                  F = A - var93;
                                  if (F == 0) {
                                    finished = true;
                                  } else {
                                    carry = 0;
                                    int var97 = pair(H, L);
                                    int var98 = pair(D, E);
                                    int var99 = ((var97 - var98) + carry) & '\uffff';
                                    H = var99 >> 8;
                                    L = var99 & 0xFF;
                                  }

                                }
                                if (!finished) {
                                  carry = 0;
                                  int var84 = pair(H, L);
                                  int var85 = pair(D, E);
                                  int var86 = ((var84 - var85) + carry) & '\uffff';
                                  H = var86 >> 8;
                                  L = var86 & 0xFF;
                                  int var87 = pair(H, L);
                                  mem[34259] = var87 & 0xFF;
                                  mem[34259 + 1] = var87 >> 8;
                                  A = B;
                                  mem[34255] = A & 0xff;
                                  A = 3;
                                  mem[34258] = A & 0xff;
                                  finished = true;
                                }
                              }
                            }
                          }

                        } else {
                          A = mem[34258];
                          F = A - 3;
                          if (F != 0) {
                            A = A + 1 & 255;
                            mem[34258] = A & 0xff;
                            finished = true;
                          } else {
                            A = mem[34257];
                            B = 0 >> 8;
                            C = 0 & 0xFF;
                            F = A;
                            if (F == 0) {
                              int var121 = mem[34259 + 1] * 256 + mem[34259];
                              H = var121 >> 8;
                              L = var121 & 0xFF;
                              A = mem[32986];
                              A = A - 1 & 255;
                              A = A | 157;
                              A = A ^ 191;
                              E = A;
                              D = 0;
                              int var126 = pair(H, L);
                              int var127 = pair(D, E);
                              int var128 = var126 + var127 & '\uffff';
                              H = var128 >> 8;
                              L = var128 & 0xFF;
                              A = mem[32964];
                              int var130 = pair(H, L);
                              int var131 = mem[var130];
                              F = A - var131;
                              if (F == 0) {
                                B = 32 >> 8;
                                C = 32 & 0xFF;
                                A = mem[32986];
                                F = A;
                                if (F != 0) {
                                  B = 65504 >> 8;
                                  C = 65504 & 0xFF;
                                }
                              }
                            }
                            int var64 = mem[34259 + 1] * 256 + mem[34259];
                            H = var64 >> 8;
                            L = var64 & 0xFF;
                            int var65 = pair(H, L);
                            int var66 = pair(B, C);
                            int var67 = var65 + var66 & '\uffff';
                            H = var67 >> 8;
                            L = var67 & 0xFF;
                            int var68 = pair(H, L) + 1 & '\uffff';
                            H = var68 >> 8;
                            L = var68 & 0xFF;
                            int var69 = pair(H, L) + 1 & '\uffff';
                            H = var69 >> 8;
                            L = var69 & 0xFF;
                            A = L;
                            A = A & 31;
                            F = A;
                            if (F != 0) {
                              D = 32 >> 8;
                              E = 32 & 0xFF;
                              A = mem[32946];
                              int var86 = pair(H, L);
                              int var87 = pair(D, E);
                              int var88 = var86 + var87 & '\uffff';
                              H = var88 >> 8;
                              L = var88 & 0xFF;
                              int var89 = pair(H, L);
                              int var90 = mem[var89];
                              F = A - var90;
                              if (F == 0) {
                                finished = true;
                              } else {
                                A = mem[34255];
                                int var94 = C;
                                C = var94 >> 1;
                                A = A + C & 255;
                                B = A;
                                A = A & 15;
                                F = A;
                                if (F != 0) {
                                  A = mem[32946];
                                  int var110 = pair(H, L);
                                  int var111 = pair(D, E);
                                  int var112 = var110 + var111 & '\uffff';
                                  H = var112 >> 8;
                                  L = var112 & 0xFF;
                                  int var113 = pair(H, L);
                                  int var114 = mem[var113];
                                  F = A - var114;
                                  if (F == 0) {
                                    finished = true;
                                  } else {
                                    carry = 0;
                                    int var118 = pair(H, L);
                                    int var119 = pair(D, E);
                                    int var120 = ((var118 - var119) + carry) & '\uffff';
                                    H = var120 >> 8;
                                    L = var120 & 0xFF;
                                  }

                                }
                                if (!finished) {
                                  A = mem[32946];
                                  carry = 0;
                                  int var100 = pair(H, L);
                                  int var101 = pair(D, E);
                                  int var102 = ((var100 - var101) + carry) & '\uffff';
                                  H = var102 >> 8;
                                  L = var102 & 0xFF;
                                  int var103 = pair(H, L);
                                  int var104 = mem[var103];
                                  F = A - var104;
                                  if (F == 0) {
                                    finished = true;
                                  } else {
                                    //FIXME
                                    int value = pair(H, L) - 1 & 0xFFFF;
                                    H = value >> 8;
                                    L = value & 0xFF;
                                    int var107 = pair(H, L);
                                    mem[34259] = var107 & 0xFF;
                                    mem[34259 + 1] = var107 >> 8;
                                    A = 0;
                                    mem[34258] = A & 0xff;
                                    A = B;
                                    mem[34255] = A & 0xff;
                                    finished = true;
                                  }
                                }
                              }

                            }
                          }
                        }
                      }
                    }

                  }

                }
                if (!finished) {
                  A = mem[33004];
                  mem[33824] = A & 0xff;
                  A = 0;
                  mem[34255] = A & 0xff;
                  A = mem[34257];
                  F = A - 11;
                  if (F < 0) {
                    A = 2;
                    mem[34257] = A & 0xff;
                  }

                  A = mem[34259];
                  A = A & 31;
                  mem[34259] = A & 0xff;
                  A = 92;
                  mem[34260] = A & 0xff;
                  doReturn = 1;
                  finished = true;
                }

              }
              if (!finished) {
                A = mem[34255];
                A = A + 16 & 255;
                A = A & 240;
                mem[34255] = A & 0xff;
                A = A & 240;
                L = A;
                A = 0;
                int var31 = L;
                L = rlc(var31) & 0xFE;
                A = (A + carry + 92) & 255;
                H = A;
                A = mem[34259];
                A = A & 31;
                A = A | L;
                L = A;
                int var9 = pair(H, L);
                mem[34259 + 1] = var9 >> 8;
                mem[34259] = var9 & 0xFF;
                A = 2;
                mem[34257] = A & 0xff;
                H = 34256 >> 8;
                L = 34256 & 0xFF;
                int var355 = pair(H, L);
                int var356 = mem[var355] & -3;
                int var357 = pair(H, L);
                mem[var357] = var356 & 0xff;
              }

              if (doReturn == 1) {
                doReturn = 0;
                break;
              }
            }

            A = mem[34255];
            F = A - 225;
            if (F >= 0) {
              A = mem[33003];
              mem[33824] = A & 0xff;
              A = mem[34259];
              A = A & 31;
              A = A + 160 & 255;
              mem[34259] = A & 0xff;
              A = 93;
              mem[34260] = A & 0xff;
              A = 208;
              mem[34255] = A & 0xff;
              A = 0;
              mem[34257] = A & 0xff;
            }

            A = mem[34271];
            F = A - 3;
            if (F != 0) {
              int var1 = mem[34259 + 1] * 256 + mem[34259];
              H = var1 >> 8;
              L = var1 & 0xFF;
              B = 0;
              A = mem[32986];
              A = A & 1;
              A = A + 64 & 255;
              E = A;
              D = 0;
              int var5 = pair(H, L);
              int var6 = pair(D, E);
              int var711 = var5 + var6 & '\uffff';
              H = var711 >> 8;
              L = var711 & 0xFF;
              A = mem[32964];
              int var9 = pair(H, L);
              int var10 = mem[var9];
              F = A - var10;
              if (F == 0) {
                A = mem[34257];
                F = A;
                if (F == 0) {
                  A = mem[34258];
                  A = A & 3;
                  int var78 = A;
                  A = rlc(var78);
                  int var80 = A;
                  A = rlc(var80);
                  B = A;
                  A = mem[32986];
                  A = A & 1;
                  A = A - 1 & 255;
                  A = A ^ 12;
                  A = A ^ B;
                  A = A & 12;
                  B = A;
                }
              }

              int var131 = mem[34259 + 1] * 256 + mem[34259];
              H = var131 >> 8;
              L = var131 & 0xFF;
              D = 31 >> 8;
              E = 31 & 0xFF;
              C = 15;
              A = mem[32928];
              int var211 = pair(H, L);
              int var39 = mem[var211];
              F = A - var39;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var147 = pair(H, L);
                  mem[var147] = A & 0xff;
                }
              }

              A = mem[32955];
              int var77 = pair(H, L);
              int var85 = mem[var77];
              F = A - var85;
              int var141 = pair(H, L) + 1 & '\uffff';
              H = var141 >> 8;
              L = var141 & 0xFF;
              A = mem[32928];
              int var210 = pair(H, L);
              int var38 = mem[var210];
              F = A - var38;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var146 = pair(H, L);
                  mem[var146] = A & 0xff;
                }
              }

              A = mem[32955];
              int var76 = pair(H, L);
              int var84 = mem[var76];
              F = A - var84;
              int var151 = pair(H, L);
              int var16 = pair(D, E);
              int var17 = var151 + var16 & '\uffff';
              H = var17 >> 8;
              L = var17 & 0xFF;
              A = mem[32928];
              int var28 = pair(H, L);
              int var36 = mem[var28];
              F = A - var36;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var145 = pair(H, L);
                  mem[var145] = A & 0xff;
                }
              }

              A = mem[32955];
              int var75 = pair(H, L);
              int var83 = mem[var75];
              F = A - var83;
              int var18 = pair(H, L) + 1 & '\uffff';
              H = var18 >> 8;
              L = var18 & 0xFF;
              A = mem[32928];
              int var27 = pair(H, L);
              int var34 = mem[var27];
              F = A - var34;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var144 = pair(H, L);
                  mem[var144] = A & 0xff;
                }
              }

              A = mem[32955];
              int var74 = pair(H, L);
              int var82 = mem[var74];
              F = A - var82;
              A = mem[34255];
              A = A + B & 255;
              C = A;
              int var21 = pair(H, L);
              int var22 = pair(D, E);
              int var23 = var21 + var22 & '\uffff';
              H = var23 >> 8;
              L = var23 & 0xFF;
              A = mem[32928];
              int var26 = pair(H, L);
              int var32 = mem[var26];
              F = A - var32;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var143 = pair(H, L);
                  mem[var143] = A & 0xff;
                }
              }

              A = mem[32955];
              int var73 = pair(H, L);
              int var81 = mem[var73];
              F = A - var81;
              int var24 = pair(H, L) + 1 & '\uffff';
              H = var24 >> 8;
              L = var24 & 0xFF;
              A = mem[32928];
              int var25 = pair(H, L);
              int var31 = mem[var25];
              F = A - var31;
              if (F == 0) {
                A = C;
                A = A & 15;
                F = A;
                if (F != 0) {
                  A = mem[32928];
                  A = A | 7;
                  int var142 = pair(H, L);
                  mem[var142] = A & 0xff;
                }
              }

              A = mem[32955];
              int var72 = pair(H, L);
              int var8 = mem[var72];
              F = A - var8;
              A = mem[34255];
              A = A + B & 255;
              IXH = 130;
              IXL = A;
              A = mem[34256];
              A = A & 1;
              int var29 = A;
              A = rrc(var29);
              E = A;
              A = mem[34258];
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
              A = mem[33824];
              F = A - 29;
              if (F == 0) {
                D = 182;
                A = E;
                A = A ^ 128;
                E = A;
              }

              B = 16;
              A = mem[34259];
              A = A & 31;
              C = A;

              do {
                int var44 = pair(IXH, IXL);
                A = mem[var44];
                int var46 = pair(IXH, IXL) + 1;
                H = mem[var46];
                A = A | C;
                L = A;
                int var49 = pair(D, E);
                A = mem[var49];
                int var51 = pair(H, L);
                int var52 = mem[var51];
                A = A | var52;
                int var57 = pair(H, L);
                mem[var57] = A & 0xff;
                int var58 = pair(H, L) + 1 & '\uffff';
                H = var58 >> 8;
                L = var58 & 0xFF;
                int var59 = pair(D, E) + 1 & '\uffff';
                D = var59 >> 8;
                E = var59 & 0xFF;
                int var60 = pair(D, E);
                A = mem[var60];
                int var62 = pair(H, L);
                int var63 = mem[var62];
                A = A | var63;
                int var68 = pair(H, L);
                mem[var68] = A & 0xff;
                int var69 = pair(IXH, IXL) + 1 & '\uffff';
                IXH = var69 >> 8;
                IXL = var69 & 0xFF;
                int var70 = pair(IXH, IXL) + 1 & '\uffff';
                IXH = var70 >> 8;
                IXL = var70 & 0xFF;
                int var71 = pair(D, E) + 1 & '\uffff';
                D = var71 >> 8;
                E = var71 & 0xFF;
                B = B - 1 & 255;
              } while (B != 0);

            }

            A = mem[34271];
            F = A - 2;
            if (F == 0) {
              int a = A;
              int f = F;
              int A1;
              int F1;
              a = mem[33824];
              f = a - 33;
              if (f == 0) {
                a = mem[34259];
                f = a - 188;
                if (f == 0) {
                  a = 0;
                  mem[34251] = A & 0xff;
                  a = 3;
                  mem[34271] = A & 0xff;
                }
              }
            }

            A = mem[33824];
            F = A - 35;
            if (F == 0) {
              A = mem[34271];
              F = A;
              if (F == 0) {
                A = mem[34251];
                A = A & 2;
                int var25 = A;
                A = rrc(var25);
                int var271 = A;
                A = rrc(var271);
                int var291 = A;
                A = rrc(var291);
                int var311 = A;
                A = rrc(var311);
                A = A | 128;
                E = A;
                A = mem[34255];
                F = A - 208;
                if (F != 0) {
                  E = 192;
                  F = A - 192;
                  if (F < 0) {
                    E = 224;
                  }
                }

                D = 156;
                H = 26734 >> 8;
                L = 26734 & 0xFF;
                C = 1;
                boolean finished = false;
                B = 16;

                do {
                  F = C & 1;
                  int var211 = pair(D, E);
                  A = mem[var211];
                  if (F != 0) {
                    int var35 = pair(H, L);
                    int var36 = mem[var35];
                    A = A & var36;
                    F = A;
                    if (F != 0) {
                      finished = true;
                      break;
                    }

                    int var41 = pair(D, E);
                    A = mem[var41];
                    int var43 = pair(H, L);
                    int var44 = mem[var43];
                    A = A | var44;
                  }

                  int var41 = pair(H, L);
                  mem[var41] = A & 0xff;
                  L = L + 1 & 255;
                  F = L;
                  int var6 = pair(D, E) + 1 & '\uffff';
                  D = var6 >> 8;
                  E = var6 & 0xFF;
                  F = C & 1;
                  int var8 = pair(D, E);
                  A = mem[var8];
                  if (F != 0) {
                    int var21 = pair(H, L);
                    int var22 = mem[var21];
                    A = A & var22;
                    F = A;
                    if (F != 0) {
                      finished = true;
                      break;
                    }

                    int var27 = pair(D, E);
                    A = mem[var27];
                    int var29 = pair(H, L);
                    int var30 = mem[var29];
                    A = A | var30;
                  }

                  int var10 = pair(H, L);
                  mem[var10] = A & 0xff;
                  L = L - 1 & 255;
                  H = H + 1 & 255;
                  F = H;
                  int var131 = pair(D, E) + 1 & '\uffff';
                  D = var131 >> 8;
                  E = var131 & 0xFF;
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
                if (!finished) {
                  A = 0;
                }

                H = 17733 >> 8;
                L = 17733 & 0xFF;
                int var361 = pair(H, L);
                mem[23918 + 1] = var361 >> 8;
                mem[23918] = var361 & 0xFF;
                H = 1799 >> 8;
                L = 1799 & 0xFF;
                int var371 = pair(H, L);
                mem[23950 + 1] = var371 >> 8;
                mem[23950] = var371 & 0xFF;
              } else {
                A = mem[34259];
                A = A & 31;
                F = A - 6;
                if (F < 0) {
                  A = 2;
                  mem[34271] = A & 0xff;
                }
              }
            } else {
              A = mem[33824];
              F = A - 33;
              if (F == 0) {
                A = mem[34251];
                A = A & 1;
                int var72 = A;
                A = rrc(var72);
                int var91 = A;
                A = rrc(var91);
                int var111 = A;
                A = rrc(var111);
                E = A;
                A = mem[34271];
                F = A - 3;
                if (F == 0) {
                  E = E | 64;
                }

                D = 166;
                IXH = 33488 >> 8;
                IXL = 33488 & 0xFF;
                B = 4124 >> 8;
                C = 4124 & 0xFF;
                do {
                  int var1 = pair(IXH, IXL);
                  A = mem[var1];
                  int var31 = pair(IXH, IXL) + 1;
                  H = mem[var31];
                  A = A | C;
                  L = A;
                  int var6 = pair(D, E);
                  A = mem[var6];
                  int var8 = pair(H, L);
                  int var9 = mem[var8];
                  A = A | var9;
                  int var141 = pair(H, L);
                  mem[var141] = A & 0xff;
                  int var152 = pair(H, L) + 1 & '\uffff';
                  H = var152 >> 8;
                  L = var152 & 0xFF;
                  int var16 = pair(D, E) + 1 & '\uffff';
                  D = var16 >> 8;
                  E = var16 & 0xFF;
                  int var17 = pair(D, E);
                  A = mem[var17];
                  int var19 = pair(H, L);
                  int var20 = mem[var19];
                  A = A | var20;
                  int var25 = pair(H, L);
                  mem[var25] = A & 0xff;
                  int var26 = pair(IXH, IXL) + 1 & '\uffff';
                  IXH = var26 >> 8;
                  IXL = var26 & 0xFF;
                  int var27 = pair(IXH, IXL) + 1 & '\uffff';
                  IXH = var27 >> 8;
                  IXL = var27 & 0xFF;
                  int var28 = pair(D, E) + 1 & '\uffff';
                  D = var28 >> 8;
                  E = var28 & 0xFF;
                  B = B - 1 & 0xff;
                } while (B != 0);

                H = 1799 >> 8;
                L = 1799 & 0xFF;
                int var151 = pair(H, L);
                mem[23996 + 1] = var151 >> 8;
                mem[23996] = var151 & 0xFF;
                int var162 = pair(H, L);
                mem[24028 + 1] = var162 >> 8;
                mem[24028] = var162 & 0xFF;
              }
            }
            IXH = 33024 >> 8;
            IXL = 33024 & 0xFF;

            while (true) {
              int var173 = pair(IXH, IXL);
              A = mem[var173];
              F = A - 255;
              if (F == 0) {
                break;
              }

              A = A & 7;
              F = A;
              if (F != 0) {
                F = A - 3;
                if (F != 0) {
                  F = A - 4;
                  if (F != 0) {
                    int var221 = pair(IXH, IXL) + 3;
                    E = mem[var221];
                    D = 130;
                    int var2231 = pair(D, E);
                    A = mem[var2231];
                    L = A;
                    int var225 = pair(IXH, IXL) + 2;
                    A = mem[var225];
                    A = A & 31;
                    A = A + L & 255;
                    L = A;
                    A = E;
                    int var229 = A;
                    A = rlc(var229);
                    A = A & 1;
                    A = A | 92;
                    H = A;
                    D = 31 >> 8;
                    E = 31 & 0xFF;
                    int var233 = pair(IXH, IXL) + 1;
                    A = mem[var233];
                    A = A & 15;
                    A = A + 56 & 255;
                    A = A & 71;
                    C = A;
                    int var238 = pair(H, L);
                    A = mem[var238];
                    A = A & 56;
                    A = A ^ C;
                    C = A;
                    int var242 = pair(H, L);
                    mem[var242] = C & 0xff;
                    int var243 = pair(H, L) + 1 & '\uffff';
                    H = var243 >> 8;
                    L = var243 & 0xFF;
                    int var244 = pair(H, L);
                    mem[var244] = C & 0xff;
                    int var245 = pair(H, L);
                    int var246 = pair(D, E);
                    int var247 = var245 + var246 & '\uffff';
                    H = var247 >> 8;
                    L = var247 & 0xFF;
                    int var248 = pair(H, L);
                    mem[var248] = C & 0xff;
                    int var249 = pair(H, L) + 1 & '\uffff';
                    H = var249 >> 8;
                    L = var249 & 0xFF;
                    int var250 = pair(H, L);
                    mem[var250] = C & 0xff;
                    int var251 = pair(IXH, IXL) + 3;
                    A = mem[var251];
                    A = A & 14;
                    F = A;
                    if (F != 0) {
                      int var285 = pair(H, L);
                      int var286 = pair(D, E);
                      int var287 = var285 + var286 & '\uffff';
                      H = var287 >> 8;
                      L = var287 & 0xFF;
                      int var288 = pair(H, L);
                      mem[var288] = C & 0xff;
                      int var289 = pair(H, L) + 1 & '\uffff';
                      H = var289 >> 8;
                      L = var289 & 0xFF;
                      int var290 = pair(H, L);
                      mem[var290] = C & 0xff;
                    }

                    C = 1;
                    int var254 = pair(IXH, IXL) + 1;
                    A = mem[var254];
                    int var256 = pair(IXH, IXL);
                    int var257 = mem[var256];
                    A = A & var257;
                    int var262 = pair(IXH, IXL) + 2;
                    int var263 = mem[var262];
                    A = A | var263;
                    A = A & 224;
                    E = A;
                    int var269 = pair(IXH, IXL) + 5;
                    D = mem[var269];
                    H = 130;
                    int var271 = pair(IXH, IXL) + 3;
                    L = mem[var271];
                    int var273 = pair(IXH, IXL) + 2;
                    A = mem[var273];
                    A = A & 31;
                    int var276 = pair(H, L);
                    int var277 = mem[var276];
                    A = A | var277;
                    int var282 = pair(H, L) + 1 & '\uffff';
                    H = var282 >> 8;
                    L = var282 & 0xFF;
                    int var283 = pair(H, L);
                    H = mem[var283];
                    L = A;
                    boolean finished = false;
                    B = 16;

                    do {
                      F = C & 1;
                      int var23 = pair(D, E);
                      A = mem[var23];
                      if (F != 0) {
                        int var351 = pair(H, L);
                        int var361 = mem[var351];
                        A = A & var361;
                        F = A;
                        if (F != 0) {
                          finished = true;
                          break;
                        }

                        int var412 = pair(D, E);
                        A = mem[var412];
                        int var431 = pair(H, L);
                        int var441 = mem[var431];
                        A = A | var441;
                      }

                      int var48 = pair(H, L);
                      mem[var48] = A & 0xff;
                      L = L + 1 & 255;
                      F = L;
                      int var6 = pair(D, E) + 1 & '\uffff';
                      D = var6 >> 8;
                      E = var6 & 0xFF;
                      F = C & 1;
                      int var81 = pair(D, E);
                      A = mem[var81];
                      if (F != 0) {
                        int var211 = pair(H, L);
                        int var2211 = mem[var211];
                        A = A & var2211;
                        F = A;
                        if (F != 0) {
                          finished = true;
                          break;
                        }

                        int var2711 = pair(D, E);
                        A = mem[var2711];
                        int var291 = pair(H, L);
                        int var30 = mem[var291];
                        A = A | var30;
                      }

                      int var10 = pair(H, L);
                      mem[var10] = A & 0xff;
                      L = L - 1 & 255;
                      H = H + 1 & 255;
                      F = H;
                      int var132 = pair(D, E) + 1 & '\uffff';
                      D = var132 >> 8;
                      E = var132 & 0xFF;
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
                    if (!finished) {
                      A = 0;
                    }

                  } else {
                    int var158 = pair(IXH, IXL);
                    F = mem[var158] & 128;
                    if (F == 0) {
                      int var2181 = pair(IXH, IXL) + 4;
                      int var219 = mem[var2181] - 1;
                      mem[var2181] = var219 & 0xff;
                      int var2201 = var219 & 255;
                      mem[var2181] = var2201 & 0xff;
                      C = 44;
                    } else {
                      int var160 = pair(IXH, IXL) + 4;
                      int var1611 = mem[var160] + 1;
                      mem[var160] = var1611 & 0xff;
                      int var162 = var1611 & 255;
                      mem[var160] = var162 & 0xff;
                      C = 244;
                    }

                    int var163 = pair(IXH, IXL) + 4;
                    A = mem[var163];
                    F = A - C;
                    if (F != 0) {
                      A = A & 224;
                      F = A;
                      if (F == 0) {
                        int var167 = pair(IXH, IXL) + 2;
                        E = mem[var167];
                        D = 130;
                        int var169 = pair(D, E);
                        A = mem[var169];
                        int var171 = pair(IXH, IXL) + 4;
                        int var172 = mem[var171];
                        A = A + var172 & 255;
                        L = A;
                        A = E;
                        A = A & 128;
                        int var1781 = A;
                        A = rlc(var1781);
                        A = A | 92;
                        H = A;
                        int var1811 = pair(IXH, IXL) + 5;
                        mem[var1811] = 0 & 0xff;
                        int var182 = pair(H, L);
                        A = mem[var182];
                        A = A & 7;
                        F = A - 7;
                        if (F == 0) {
                          int var211 = pair(IXH, IXL) + 5;
                          int var2121 = mem[var211] - 1;
                          mem[var211] = var2121 & 0xff;
                          int var2131 = var2121 & 255;
                          mem[var211] = var2131 & 0xff;
                        }

                        int var1861 = pair(H, L);
                        A = mem[var1861];
                        A = A | 7;
                        int var189 = pair(H, L);
                        mem[var189] = A & 0xff;
                        int var190 = pair(D, E) + 1 & '\uffff';
                        D = var190 >> 8;
                        E = var190 & 0xFF;
                        int var1911 = pair(D, E);
                        A = mem[var1911];
                        H = A;
                        H = H - 1 & 255;
                        F = H;
                        int var194 = pair(IXH, IXL) + 6;
                        A = mem[var194];
                        int var1961 = pair(H, L);
                        mem[var1961] = A & 0xff;
                        H = H + 1 & 255;
                        F = H;
                        int var1981 = pair(H, L);
                        A = mem[var1981];
                        int var200 = pair(IXH, IXL) + 5;
                        int var2011 = mem[var200];
                        A = A & var2011;
                        int var2061 = pair(H, L);
                        mem[var2061] = 255 & 0xff;
                        H = H + 1 & 255;
                        F = H;
                        int var208 = pair(IXH, IXL) + 6;
                        A = mem[var208];
                        int var210 = pair(H, L);
                        mem[var210] = A & 0xff;
                      }
                    } else {
                      B = 640 >> 8;
                      C = 640 & 0xFF;
                      A = mem[32990];

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
                  IYH = 33280 >> 8;
                  IYL = 33280 & 0xFF;
                  int var911 = pair(IXH, IXL) + 9;
                  mem[var911] = 0 & 0xff;
                  int var10 = pair(IXH, IXL) + 2;
                  A = mem[var10];
                  int var1211 = pair(IXH, IXL) + 3;
                  mem[var1211] = A & 0xff;
                  int var1321 = pair(IXH, IXL) + 5;
                  mem[var1321] = 128 & 0xff;

                  while (true) {
                    label107:
                    {
                      int var143 = pair(IYH, IYL);
                      A = mem[var143];
                      int var162 = pair(IXH, IXL) + 3;
                      int var17 = mem[var162];
                      A = A + var17 & 255;
                      L = A;
                      int var221 = pair(IYH, IYL) + 1;
                      H = mem[var221];
                      A = mem[34262];
                      F = A;
                      if (F == 0) {
                        int var145 = pair(IXH, IXL) + 5;
                        A = mem[var145];
                        int var147 = pair(H, L);
                        int var148 = mem[var147];
                        A = A & var148;
                        F = A;
                        if (F == 0) {
                          break label107;
                        }

                        int var153 = pair(IXH, IXL) + 9;
                        A = mem[var153];
                        mem[34262] = A & 0xff;
                        int var155 = pair(IXH, IXL) + 11;
                        int var156 = mem[var155] | 1;
                        mem[var155] = var156 & 0xff;
                      }

                      int var261 = pair(IXH, IXL) + 9;
                      int var271 = mem[var261];
                      F = A - var271;
                      if (F == 0) {
                        int var133 = pair(IXH, IXL) + 11;
                        F = mem[var133] & 1;
                        if (F != 0) {
                          int var135 = pair(IXH, IXL) + 3;
                          B = mem[var135];
                          int var137 = pair(IXH, IXL) + 5;
                          A = mem[var137];
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

                          int var140 = pair(B, C);
                          mem[34258] = var140 & 0xFF;
                          mem[34258 + 1] = var140 >> 8;
                          A = IYL;
                          A = A - 16 & 255;
                          mem[34255] = A & 0xff;
                          int lastHL1 = pair(H, L);
                          A = A & 240;
                          L = A;
                          A = 0;
                          int var39 = L;
                          L = rlc(var39) & 0xFE;
                          A = (A + carry + 92) & 255;
                          H = A;
                          A = mem[34259];
                          A = A & 31;
                          A = A | L;
                          L = A;
                          int var91 = pair(H, L);
                          mem[34259 + 1] = var91 >> 8;
                          mem[34259] = var91 & 0xFF;
                          H = lastHL1 >> 8;
                          L = lastHL1 & 0xFF;
                        }
                      }
                    }

                    int var30 = pair(IXH, IXL) + 5;
                    A = mem[var30];
                    int var321 = pair(H, L);
                    int var332 = mem[var321];
                    A = A | var332;
                    int var381 = pair(H, L);
                    mem[var381] = A & 0xff;
                    int var39 = pair(IXH, IXL) + 9;
                    A = mem[var39];
                    int var412 = pair(IXH, IXL) + 1;
                    int var421 = mem[var412];
                    A = A + var421 & 255;
                    L = A;
                    L = L | 128;
                    H = 131;
                    int var48 = pair(H, L);
                    E = mem[var48];
                    D = 0;
                    int var50 = pair(IYH, IYL);
                    int var51 = pair(D, E);
                    int var52 = var50 + var51 & '\uffff';
                    IYH = var52 >> 8;
                    IYL = var52 & 0xFF;
                    L = L & -129;
                    int var54 = pair(H, L);
                    A = mem[var54];
                    F = A;
                    if (F != 0) {
                      B = A;
                      int var113 = pair(IXH, IXL) + 1;
                      F = mem[var113] & 128;
                      if (F != 0) {
                        do {
                          int var124 = pair(IXH, IXL) + 5;
                          int var125 = mem[var124];
                          int var126 = rlc(var125);
                          mem[var124] = var126 & 0xff;
                          int var127 = pair(IXH, IXL) + 5;
                          F = mem[var127] & 1;
                          if (F != 0) {
                            int var130 = pair(IXH, IXL) + 3;
                            int var1311 = mem[var130] - 1;
                            mem[var130] = var1311 & 0xff;
                            int var132 = var1311 & 255;
                            mem[var130] = var132 & 0xff;
                          }

                          B = B - 1 & 255;
                        } while (B != 0);
                      } else {
                        do {
                          int var115 = pair(IXH, IXL) + 5;
                          int var116 = mem[var115];
                          int var117 = rrc(var116);
                          mem[var115] = var117 & 0xff;
                          int var118 = pair(IXH, IXL) + 5;
                          F = mem[var118] & 128;
                          if (F != 0) {
                            int var121 = pair(IXH, IXL) + 3;
                            int var122 = mem[var121] + 1;
                            mem[var121] = var122 & 0xff;
                            int var123 = var122 & 255;
                            mem[var121] = var123 & 0xff;
                          }

                          B = B - 1 & 255;
                        } while (B != 0);
                      }
                    }

                    int var571 = pair(IXH, IXL) + 9;
                    A = mem[var571];
                    int var591 = pair(IXH, IXL) + 4;
                    int var601 = mem[var591];
                    F = A - var601;
                    if (F == 0) {
                      A = mem[34262];
                      F = A & 128;
                      if (F != 0) {
                        A = A + 1 & 255;
                        mem[34262] = A & 0xff;
                        int var108 = pair(IXH, IXL) + 11;
                        int var109 = mem[var108] & -2;
                        mem[var108] = var109 & 0xff;
                      } else {
                        int var65 = pair(IXH, IXL) + 11;
                        F = mem[var65] & 1;
                        if (F != 0) {
                          A = mem[34256];
                          F = A & 2;
                          if (F != 0) {
                            int var69 = A;
                            A = rrc(var69);
                            int var711 = pair(IXH, IXL);
                            int var72 = mem[var711];
                            A = A ^ var72;
                            int var77 = A;
                            A = rlc(var77);
                            int var79 = A;
                            A = rlc(var79);
                            A = A & 2;
                            A = A - 1 & 255;
                            H = 34262 >> 8;
                            L = 34262 & 0xFF;
                            int var83 = pair(H, L);
                            int var84 = mem[var83];
                            A = A + var84 & 255;
                            int var89 = pair(H, L);
                            mem[var89] = A & 0xff;
                            A = mem[33003];
                            C = A;
                            A = mem[33824];
                            F = A - C;
                            if (F == 0) {
                              int var103 = pair(H, L);
                              A = mem[var103];
                              F = A - 12;
                              if (F < 0) {
                                int var106 = pair(H, L);
                                mem[var106] = 12 & 0xff;
                              }
                            }

                            int var93 = pair(H, L);
                            A = mem[var93];
                            int var95 = pair(IXH, IXL) + 4;
                            int var96 = mem[var95];
                            F = A - var96;
                            if (F >= 0 && F != 0) {
                              int var99 = pair(H, L);
                              mem[var99] = 240 & 0xff;
                              A = mem[34255];
                              A = A & 248;
                              mem[34255] = A & 0xff;
                              A = 0;
                              mem[34257] = A & 0xff;
                            }
                          }
                        }
                      }
                      break;
                    }

                    int var110 = pair(IXH, IXL) + 9;
                    int var111 = mem[var110] + 1;
                    mem[var110] = var111 & 0xff;
                    int var112 = var111 & 255;
                    mem[var110] = var112 & 0xff;
                  }
                }
              }

              D = 8 >> 8;
              E = 8 & 0xFF;
              int var51 = pair(IXH, IXL);
              int var6 = pair(D, E);
              int var72 = var51 + var6 & '\uffff';
              IXH = var72 >> 8;
              IXL = var72 & 0xFF;
            }
            int var12 = mem[32983 + 1] * 256 + mem[32983];
            H = var12 >> 8;
            L = var12 & 0xFF;
            A = H;
            A = A & 1;
            int var331 = A;
            A = rlc(var331);
            int var5 = A;
            A = rlc(var5);
            int var71 = A;
            A = rlc(var71);
            A = A + 112 & 255;
            H = A;
            E = L;
            D = H;
            A = mem[32985];
            F = A;
            if (F != 0) {
              B = A;
              A = mem[32982];
              F = A;
              if (F == 0) {
                int var33 = pair(H, L);
                A = mem[var33];
                int var35 = A;
                A = rlc(var35);
                int var37 = A;
                A = rlc(var37);
                H = H + 1 & 255;
                H = H + 1 & 255;
                F = H;
                int var411 = pair(H, L);
                C = mem[var411];
                int var43 = C;
                C = rrc(var43);
                int var451 = C;
                C = rrc(var451);
              } else {
                int var142 = pair(H, L);
                A = mem[var142];
                int var161 = A;
                A = rrc(var161);
                int var18 = A;
                A = rrc(var18);
                H = H + 1 & 255;
                H = H + 1 & 255;
                F = H;
                int var22 = pair(H, L);
                C = mem[var22];
                int var24 = C;
                C = rlc(var24);
                int var26 = C;
                C = rlc(var26);
              }

              do {
                int var28 = pair(D, E);
                mem[var28] = A & 0xff;
                int var29 = pair(H, L);
                mem[var29] = C & 0xff;
                L = L + 1 & 255;
                E = E + 1 & 255;
                F = E;
                B = B - 1 & 0xff;
              } while (B != 0);

            }
            H = 164;
            A = mem[41983];
            L = A;

            do {
              int var21 = pair(H, L);
              C = mem[var21];
              C = C & -129;
              A = mem[33824];
              A = A | 64;
              F = A - C;
              if (F == 0) {
                int var9 = pair(H, L);
                A = mem[var9];
                int var11 = A;
                A = rlc(var11);
                A = A & 1;
                A = A + 92 & 255;
                D = A;
                H = H + 1 & 255;
                F = H;
                int var16 = pair(H, L);
                E = mem[var16];
                H = H - 1 & 255;
                F = H;
                int var19 = pair(D, E);
                A = mem[var19];
                A = A & 7;
                F = A - 7;
                if (F != 0) {
                  A = mem[34251];
                  A = A + L & 255;
                  A = A & 3;
                  A = A + 3 & 255;
                  C = A;
                  int var27 = pair(D, E);
                  A = mem[var27];
                  A = A & 248;
                  A = A | C;
                  int var31 = pair(D, E);
                  mem[var31] = A & 0xff;
                  int var32 = pair(H, L);
                  A = mem[var32];
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
                  int lastHL = pair(H, L); //FIXME:
                  H = 32993 >> 8;
                  L = 32993 & 0xFF;
                  B = 8;
                  do {
                    int address = pair(H, L);
                    A = mem[address];
                    int address1 = pair(D, E);
                    mem[address1] = A & 0xff;
                    int value = pair(H, L) + 1 & '\uffff';
                    H = value >> 8;
                    L = value & 0xFF;
                    D = D + 1 & 255;
                    F = D;
                    B = B - 1 & 0xff;
                  } while (B != 0);
                  H = lastHL >> 8;
                  L = lastHL & 0xFF;
                } else {
                  IXH = 34172 >> 8;
                  IXL = 34172 & 0xFF;

                  while (true) {
                    int var44 = pair(IXH, IXL) + 2;
                    int var45 = mem[var44] + 1;
                    mem[var44] = var45 & 0xff;
                    int var46 = var45 & 255;
                    mem[var44] = var46 & 0xff;
                    int var47 = pair(IXH, IXL) + 2;
                    A = mem[var47];
                    F = A - 58;
                    if (F != 0) {
                      A = mem[32990];
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

                      A = mem[34270];
                      A = A + 1 & 255;
                      mem[34270] = A & 0xff;
                      if (F == 0) {
                        A = 1;
                        mem[34271] = A & 0xff;
                      }

                      int var581 = pair(H, L);
                      int var59 = mem[var581] & -65;
                      int var60 = pair(H, L);
                      mem[var60] = var59 & 0xff;
                      break;
                    }

                    int var61 = pair(IXH, IXL) + 2;
                    mem[var61] = 48 & 0xff;
                  }
                }
              }

              L = L + 1 & 255;
              F = L;
            } while (F != 0);

            H = 24576 >> 8;
            L = 24576 & 0xFF;
            D = 16384 >> 8;
            E = 16384 & 0xFF;
            B = 4096 >> 8;
            C = 4096 & 0xFF;
            while (pair(B, C) != 0) {
              int address4 = pair(H, L);
              int address13 = pair(D, E);
              mem[address13] = mem[address4] & 0xff;
              B = pair(B, C) - 1 >> 8;
              C = pair(B, C) - 1 & 0xFF;
              H = pair(H, L) + 1 >> 8;
              L = pair(H, L) + 1 & 0xFF;
              D = pair(D, E) + 1 >> 8;
              E = pair(D, E) + 1 & 0xFF;
            }
            A = mem[34271];
            A = A & 2;
            int var55 = A;
            A = rrc(var55);
            H = 34258 >> 8;
            L = 34258 & 0xFF;
            int var57 = pair(H, L);
            int var58 = mem[var57];
            A = A | var58;
            int var63 = pair(H, L);
            mem[var63] = A & 0xff;
            A = mem[34253];
            F = A;
            if (F != 0) {
              A = A - 1 & 255;
              mem[34253] = A & 0xff;
              int var216 = A;
              A = rlc(var216);
              int var218 = A;
              A = rlc(var218);
              int var220 = A;
              A = rlc(var220);
              A = A & 56;
              H = 23552 >> 8;
              L = 23552 & 0xFF;
              D = 23553 >> 8;
              E = 23553 & 0xFF;
              B = 511 >> 8;
              C = 511 & 0xFF;
              int var223 = pair(H, L);
              mem[var223] = A & 0xff;
              while (pair(B, C) != 0) {
                int address = pair(H, L);
                int address1 = pair(D, E);
                mem[address1] = mem[address] & 0xff;
                B = pair(B, C) - 1 >> 8;
                C = pair(B, C) - 1 & 0xFF;
                H = pair(H, L) + 1 >> 8;
                L = pair(H, L) + 1 & 0xFF;
                D = pair(D, E) + 1 >> 8;
                E = pair(D, E) + 1 & 0xFF;
              }
            }

            H = 23552 >> 8;
            L = 23552 & 0xFF;
            D = 22528 >> 8;
            E = 22528 & 0xFF;
            B = 512 >> 8;
            C = 512 & 0xFF;
            while (pair(B, C) != 0) {
              int address3 = pair(H, L);
              int address12 = pair(D, E);
              mem[address12] = mem[address3] & 0xff;
              B = pair(B, C) - 1 >> 8;
              C = pair(B, C) - 1 & 0xFF;
              H = pair(H, L) + 1 >> 8;
              L = pair(H, L) + 1 & 0xFF;
              D = pair(D, E) + 1 >> 8;
              E = pair(D, E) + 1 & 0xFF;
            }
            IXH = 34175 >> 8;
            IXL = 34175 & 0xFF;
            D = 20601 >> 8;
            E = 20601 & 0xFF;
            C = 6;
            do {
              int address1 = pair(IXH, IXL);
              A = mem[address1];
              H = 7;
              L = A;
              L = L | 128;
              int value3 = pair(H, L) * 2 & '\uffff';
              H = value3 >> 8;
              L = value3 & 0xFF;
              int value2 = pair(H, L) * 2 & '\uffff';
              H = value2 >> 8;
              L = value2 & 0xFF;
              int value1 = pair(H, L) * 2 & '\uffff';
              H = value1 >> 8;
              L = value1 & 0xFF;
              B = 8;

              do {
                int address = pair(H, L);
                A = mem[address];
                int address11 = pair(D, E);
                mem[address11] = A & 0xff;
                int value = pair(H, L) + 1 & '\uffff';
                H = value >> 8;
                L = value & 0xFF;
                D = D + 1 & 255;
                F = D;
                B = B - 1 & 0xff;
              } while (B != 0);
              int value = pair(IXH, IXL) + 1 & '\uffff';
              IXH = value >> 8;
              IXL = value & 0xFF;
              E = E + 1 & 255;
              A = D;
              A = A - 8 & 255;
              D = A;
              C = C - 1 & 255;
              F = C;
            } while (F != 0);

            IXH = 34172 >> 8;
            IXL = 34172 & 0xFF;
            D = 20592 >> 8;
            E = 20592 & 0xFF;
            C = 3;
            do {
              int address = pair(IXH, IXL);
              A = mem[address];
              H = 7;
              L = A;
              L = L | 128;
              int value3 = pair(H, L) * 2 & '\uffff';
              H = value3 >> 8;
              L = value3 & 0xFF;
              int value2 = pair(H, L) * 2 & '\uffff';
              H = value2 >> 8;
              L = value2 & 0xFF;
              int value1 = pair(H, L) * 2 & '\uffff';
              H = value1 >> 8;
              L = value1 & 0xFF;
              B = 8;

              do {
                int address11 = pair(H, L);
                A = mem[address11];
                int address1 = pair(D, E);
                mem[address1] = A & 0xff;
                int value = pair(H, L) + 1 & '\uffff';
                H = value >> 8;
                L = value & 0xFF;
                D = D + 1 & 255;
                F = D;
                B = B - 1 & 0xff;
              } while (B != 0);
              int value = pair(IXH, IXL) + 1 & '\uffff';
              IXH = value >> 8;
              IXL = value & 0xFF;
              E = E + 1 & 255;
              A = D;
              A = A - 8 & 255;
              D = A;
              C = C - 1 & 255;
              F = C;
            } while (F != 0);

            A = mem[34251];
            A = A + 1 & 255;
            mem[34251] = A & 0xff;
            if (F == 0) {
              IXH = 34175 >> 8;
              IXL = 34175 & 0xFF;
              int var178 = pair(IXH, IXL) + 4;
              int var179 = mem[var178] + 1;
              mem[var178] = var179 & 0xff;
              int var180 = var179 & 255;
              mem[var178] = var180 & 0xff;
              int var181 = pair(IXH, IXL) + 4;
              A = mem[var181];
              F = A - 58;
              if (F == 0) {
                int var184 = pair(IXH, IXL) + 4;
                mem[var184] = 48 & 0xff;
                int var185 = pair(IXH, IXL) + 3;
                int var186 = mem[var185] + 1;
                mem[var185] = var186 & 0xff;
                int var187 = var186 & 255;
                mem[var185] = var187 & 0xff;
                int var188 = pair(IXH, IXL) + 3;
                A = mem[var188];
                F = A - 54;
                if (F == 0) {
                  int var191 = pair(IXH, IXL) + 3;
                  mem[var191] = 48 & 0xff;
                  int var192 = pair(IXH, IXL);
                  A = mem[var192];
                  F = A - 49;
                  if (F == 0) {
                    int var203 = pair(IXH, IXL) + 1;
                    int var204 = mem[var203] + 1;
                    mem[var203] = var204 & 0xff;
                    int var205 = var204 & 255;
                    mem[var203] = var205 & 0xff;
                    int var206 = pair(IXH, IXL) + 1;
                    A = mem[var206];
                    F = A - 51;
                    if (F == 0) {
                      int var209 = pair(IXH, IXL) + 5;
                      A = mem[var209];
                      F = A - 112;
                      if (F == 0) {
                        continue label205;
                      }

                      int var212 = pair(IXH, IXL);
                      mem[var212] = 32 & 0xff;
                      int var213 = pair(IXH, IXL) + 1;
                      mem[var213] = 49 & 0xff;
                      int var214 = pair(IXH, IXL) + 5;
                      mem[var214] = 112 & 0xff;
                    }
                  } else {
                    int var195 = pair(IXH, IXL) + 1;
                    int var196 = mem[var195] + 1;
                    mem[var195] = var196 & 0xff;
                    int var197 = var196 & 255;
                    mem[var195] = var197 & 0xff;
                    int var198 = pair(IXH, IXL) + 1;
                    A = mem[var198];
                    F = A - 58;
                    if (F == 0) {
                      int var201 = pair(IXH, IXL) + 1;
                      mem[var201] = 48 & 0xff;
                      int var202 = pair(IXH, IXL);
                      mem[var202] = 49 & 0xff;
                    }
                  }
                }
              }
            }

            B = 65278 >> 8;
            C = 65278 & 0xFF;
            A = in(pair(B, C));
            E = A;
            B = 127;
            A = in(pair(B, C));
            A = A | E;
            A = A & 1;
            F = A;
            if (F == 0) {
              continue label205;
            }

            A = mem[34272];
            A = A + 1 & 255;
            mem[34272] = A & 0xff;
            if (F != 0) {
              B = 253;
              A = in(pair(B, C));
              A = A & 31;
              F = A - 31;
              if (F == 0) {
                break label215;
              }

              D = 0 >> 8;
              E = 0 & 0xFF;
            }

            while (true) {
              B = 2;
              A = in(pair(B, C));
              A = A & 31;
              F = A - 31;
              if (F != 0) {
                H = 39424 >> 8;
                L = 39424 & 0xFF;
                D = 23040 >> 8;
                E = 23040 & 0xFF;
                B = 256 >> 8;
                C = 256 & 0xFF;
                while (pair(B, C) != 0) {
                  int address = pair(H, L);
                  int address1 = pair(D, E);
                  mem[address1] = mem[address] & 0xff;
                  B = pair(B, C) - 1 >> 8;
                  C = pair(B, C) - 1 & 0xFF;
                  H = pair(H, L) + 1 >> 8;
                  L = pair(H, L) + 1 & 0xFF;
                  D = pair(D, E) + 1 >> 8;
                  E = pair(D, E) + 1 & 0xFF;
                }
                A = mem[32990];
                break;
              }

              E = E + 1 & 255;
              F = E;
              if (F == 0) {
                D = D + 1 & 255;
                F = D;
                if (F == 0) {
                  A = mem[34275];
                  F = A - 10;
                  if (F != 0) {
                    H = 22528 >> 8;
                    L = 22528 & 0xFF;
                    int var1 = pair(H, L);
                    A = mem[var1];
                    A = A & 7;

                    do {
                      int var41 = pair(H, L);
                      A = mem[var41];
                      A = A + 3 & 255;
                      A = A & 7;
                      D = A;
                      int var8 = pair(H, L);
                      A = mem[var8];
                      A = A + 24 & 255;
                      A = A & 184;
                      A = A | D;
                      int var131 = pair(H, L);
                      mem[var131] = A & 0xff;
                      int var141 = pair(H, L) + 1 & '\uffff';
                      H = var141 >> 8;
                      L = var141 & 0xFF;
                      A = H;
                      F = A - 91;
                    } while (F != 0);

                  }
                }
              }
            }
          }

          A = mem[34257];
          F = A - 255;
          B = 191;
          H = 34274 >> 8;
          L = 34274 & 0xFF;
          A = in(pair(B, C));
          A = A & 31;
          F = A - 31;
          if (F != 0) {
            int var165 = pair(H, L);
            F = mem[var165] & 1;
            if (F == 0) {
              int var167 = pair(H, L);
              A = mem[var167];
              A = A ^ 3;
              int var170 = pair(H, L);
              mem[var170] = A & 0xff;
            }
          } else {
            int var83 = pair(H, L);
            int var84 = mem[var83] & -2;
            int var85 = pair(H, L);
            mem[var85] = var84 & 0xff;
          }

          int var86 = pair(H, L);
          F = mem[var86] & 2;
          if (F == 0) {
            A = 0;
            mem[34272] = A & 0xff;
            A = mem[34273];
            A = A + 1 & 255;
            mem[34273] = A & 0xff;
            A = A & 126;
            int var142 = A;
            A = rrc(var142);
            E = A;
            D = 0;
            H = 34399 >> 8;
            L = 34399 & 0xFF;
            int var144 = pair(H, L);
            int var145 = pair(D, E);
            int var146 = var144 + var145 & '\uffff';
            H = var146 >> 8;
            L = var146 & 0xFF;
            A = mem[34252];
            int var148 = A;
            A = rlc(var148);
            int var150 = A;
            A = rlc(var150);
            A = A - 28 & 255;
            A = -A & 255;
            int var154 = pair(H, L);
            int var155 = mem[var154];
            A = A + var155 & 255;
            D = A;
            A = mem[32990];
            E = D;
            B = 3 >> 8;
            C = 3 & 0xFF;

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

          B = 61438 >> 8;
          C = 61438 & 0xFF;
          A = in(pair(B, C));
          F = A & 2;
          if (F == 0) {
            A = A & 16;
            A = A ^ 16;
            int var130 = A;
            A = rlc(var130);
            D = A;
            A = mem[34275];
            F = A - 10;
            if (F == 0) {
              B = 63486 >> 8;
              C = 63486 & 0xFF;
              A = in(pair(B, C));
              A = ~A;
              A = A & 31;
              A = A | D;
              mem[33824] = A & 0xff;
              break;
            }
          }

          A = mem[34275];
          F = A - 10;
          if (F != 0) {
            A = mem[33824];
            F = A - 28;
            if (F == 0) {
              A = mem[34255];
              F = A - 208;
              if (F == 0) {
                A = mem[34275];
                int var97 = A;
                A = rlc(var97);
                E = A;
                D = 0;
                IXH = 34279 >> 8;
                IXL = 34279 & 0xFF;
                int var99 = pair(IXH, IXL);
                int var100 = pair(D, E);
                int var101 = var99 + var100 & '\uffff';
                IXH = var101 >> 8;
                IXL = var101 & 0xFF;
                B = 64510 >> 8;
                C = 64510 & 0xFF;
                A = in(pair(B, C));
                A = A & 31;
                int var104 = pair(IXH, IXL);
                int var105 = mem[var104];
                F = A - var105;
                if (F != 0) {
                  F = A - 31;
                  if (F != 0) {
                    int var123 = pair(IXH, IXL);
                    int var124 = mem[var123];
                    F = A - var124;
                    if (F != 0) {
                      A = 0;
                      mem[34275] = A & 0xff;
                    }
                  }
                } else {
                  B = 223;
                  A = in(pair(B, C));
                  A = A & 31;
                  int var110 = pair(IXH, IXL) + 1;
                  int var111 = mem[var110];
                  F = A - var111;
                  if (F != 0) {
                    F = A - 31;
                    if (F != 0) {
                      int var117 = pair(IXH, IXL);
                      int var118 = mem[var117];
                      F = A - var118;
                      int var120 = pair(IXH, IXL);
                      if (F != 0) {
                        A = 0;
                        mem[34275] = A & 0xff;
                      }
                    }
                  } else {
                    A = mem[34275];
                    A = A + 1 & 255;
                    mem[34275] = A & 0xff;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
*/
  private int pair(int B, int C) {
    return ((B & 0xFF) << 8) | (C & 0xFF);
  }

  @Override
  protected byte[] getProgramBytes() {
    return null;
  }

}
