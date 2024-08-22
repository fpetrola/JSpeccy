package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  public void $34463() {
    HL(16384);
    DE(16385);
    BC(6911);
    int var1 = HL();
    wMem(var1, 0);
    ldir();
    IX(34187);

    label145:
    while(true) {
      int var2 = A ^ A;
      A = var2;
      F = A;
      wMem(34254, A);
      wMem(34273, A);
      wMem(34253, A);
      wMem(34257, A);
      wMem(34251, A);
      wMem(34272, A);
      wMem(34271, A);
      A = 7;
      wMem(34252, A);
      A = 208;
      wMem(34255, A);
      A = 33;
      wMem(33824, A);
      HL(23988);
      HL(34172);
      int var3 = HL();
      wMem(var3, 48);
      int var4 = HL() + 1;
      HL(var4);
      int var5 = HL();
      wMem(var5, 48);
      int var6 = HL() + 1;
      HL(var6);
      int var7 = HL();
      wMem(var7, 48);
      H = 164;
      int var8 = mem(41983);
      A = var8;
      L = A;
      wMem(34270, A);

      while(true) {
        int var9 = HL();
        int var10 = mem[var9];
        int var11 = var10 | 64;
        int var12 = HL();
        wMem(var12, var11);
        int var13 = HL();
        wMem(var13, var10);
        int var14 = L + 1;
        L = var14;
        if (F == 0) {
          HL(34274);
          int var15 = HL();
          int var16 = mem[var15];
          int var17 = var16 | 1;
          int var18 = HL();
          wMem(var18, var17);
          int var19 = HL();
          wMem(var19, var16);

          label111:
          while(true) {
            HL(16384);
            DE(16385);
            BC(6143);
            int var20 = HL();
            wMem(var20, 0);
            ldir();
            HL(38912);
            BC(768);
            ldir();
            HL(23136);
            DE(23137);
            BC(31);
            int var21 = HL();
            wMem(var21, 70);
            ldir();
            IX(33876);
            DE(20576);
            C = 32;
            $38528();
            DE(22528);

            do {
              int var22 = DE();
              int var23 = mem(var22);
              A = var23;
              int var24 = A | A;
              A = var24;
              F = A;
              if (F != 0) {
                int var127 = A - 211;
                F = var127;
                if (F != 0) {
                  int var128 = A - 9;
                  F = var128;
                  if (F != 0) {
                    int var129 = A - 45;
                    F = var129;
                    if (F != 0) {
                      int var130 = A - 36;
                      F = var130;
                      if (F != 0) {
                        C = 0;
                        int var131 = A - 8;
                        F = var131;
                        if (F != 0) {
                          int var141 = A - 41;
                          F = var141;
                          if (F != 0) {
                            int var142 = A - 44;
                            F = var142;
                            int var143 = A - 5;
                            F = var143;
                            if (F != 0) {
                              C = 16;
                            }
                          }
                        }

                        A = E;
                        int var132 = A & 1;
                        A = var132;
                        F = A;
                        int var133 = A << 1;
                        A = var133;
                        int var134 = A << 1;
                        A = var134;
                        int var135 = A << 1;
                        A = var135;
                        int var136 = A | C;
                        A = var136;
                        F = A;
                        C = A;
                        B = 0;
                        HL(33841);
                        int var137 = HL();
                        int var138 = BC();
                        int var139 = var137 + var138;
                        HL(var139);
                        int var140 = D & 1;
                        F = var140;
                        D = 64;
                        if (F != 0) {
                          D = 72;
                        }

                        B = 8;
                        $38555();
                      }
                    }
                  }
                }
              }

              int var25 = DE() + 1;
              DE(var25);
              A = D;
              int var26 = A - 90;
              F = var26;
            } while(F != 0);

            BC(31);
            int var27 = A ^ A;
            A = var27;
            F = A;

            do {
              int var28 = A | E;
              A = var28;
              F = A;
              int var29 = B + -1;
              B = var29;
            } while(B != 0);

            int var30 = A & 32;
            A = var30;
            F = A;
            if (F == 0) {
              A = 1;
              wMem(34254, A);
            }

            HL(34299);
            $38562();
            if (F != 0) {
              break;
            }

            int var113 = A ^ A;
            A = var113;
            F = A;
            wMem(34276, A);

            while(true) {
              $35563();
              HL(23136);
              DE(23137);
              BC(31);
              int var114 = HL();
              wMem(var114, 79);
              ldir();
              int var115 = mem(34276);
              A = var115;
              IX(33876);
              E = A;
              D = 0;
              int var116 = IX();
              int var117 = DE();
              int var118 = var116 + var117;
              IX(var118);
              DE(20576);
              C = 32;
              $38528();
              int var119 = mem(34276);
              A = var119;
              int var120 = A & 31;
              A = var120;
              F = A;
              int var121 = A + 50;
              A = var121;
              F = A;
              $38622();
              BC(45054);
              int var122 = A & 1;
              A = var122;
              F = A;
              int var123 = A - 1;
              F = var123;
              if (F != 0) {
                break label111;
              }

              int var124 = mem(34276);
              A = var124;
              int var125 = A + 1;
              A = var125;
              int var126 = A - 224;
              F = var126;
              wMem(34276, A);
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
          int var31 = mem(33824);
          A = var31;
          int var32 = A | 192;
          A = var32;
          F = A;
          H = A;
          L = 0;
          DE(32768);
          BC(256);
          ldir();
          IX(33008);
          DE(33024);
          A = 8;

          do {
            int var33 = IX();
            int var34 = mem(var33);
            L = var34;
            int var35 = L & -129;
            L = var35;
            H = 20;
            int var36 = HL() * 2;
            HL(var36);
            int var37 = HL() * 2;
            HL(var37);
            int var38 = HL() * 2;
            HL(var38);
            BC(2);
            ldir();
            int var39 = IX() + 1;
            int var40 = mem(var39);
            C = var40;
            int var41 = HL();
            wMem(var41, C);
            BC(6);
            ldir();
            int var42 = IX() + 1;
            IX(var42);
            int var43 = IX() + 1;
            IX(var43);
            int var44 = A + -1;
            A = var44;
            F = A;
          } while(F != 0);

          HL(34255);
          DE(34263);
          BC(7);
          ldir();
          $36147();
          HL(20480);
          DE(20481);
          BC(2047);
          int var45 = HL();
          wMem(var45, 0);
          ldir();
          IX(32896);
          C = 32;
          DE(20480);
          $38528();
          IX(34132);
          DE(20576);
          C = 32;
          $38528();
          int var46 = mem(32990);
          A = var46;
          C = 254;
          int var47 = A ^ A;
          A = var47;
          F = A;
          wMem(34262, A);
          $35211();
          HL(24064);
          DE(23552);
          BC(512);
          ldir();
          HL(28672);
          DE(24576);
          BC(4096);
          ldir();
          $37056();
          int var48 = mem(34271);
          A = var48;
          int var49 = A - 3;
          F = var49;
          if (F != 0) {
            $36307();
          }

          int var50 = mem(34255);
          A = var50;
          int var51 = A - 225;
          F = var51;
          if (F >= 0) {
            $38064();
          }

          int var52 = mem(34271);
          A = var52;
          int var53 = A - 3;
          F = var53;
          if (F != 0) {
            $38344();
          }

          int var54 = mem(34271);
          A = var54;
          int var55 = A - 2;
          F = var55;
          if (F == 0) {
            $38276();
          }

          $38196();
          $37310();
          $38137();
          $37841();
          HL(24576);
          DE(16384);
          BC(4096);
          ldir();
          int var56 = mem(34271);
          A = var56;
          int var57 = A & 2;
          A = var57;
          F = A;
          int var58 = A >> 1;
          A = var58;
          HL(34258);
          int var59 = HL();
          int var60 = mem(var59);
          int var61 = A | var60;
          A = var61;
          int var62 = HL();
          mem(var62);
          int var63 = HL();
          mem(var63);
          F = A;
          int var64 = HL();
          mem(var64);
          int var65 = HL();
          wMem(var65, A);
          int var66 = mem(34253);
          A = var66;
          int var67 = A | A;
          A = var67;
          F = A;
          if (F != 0) {
            int var107 = A + -1;
            A = var107;
            F = A;
            wMem(34253, A);
            int var108 = A << 1;
            A = var108;
            int var109 = A << 1;
            A = var109;
            int var110 = A << 1;
            A = var110;
            int var111 = A & 56;
            A = var111;
            F = A;
            HL(23552);
            DE(23553);
            BC(511);
            int var112 = HL();
            wMem(var112, A);
            ldir();
          }

          HL(23552);
          DE(22528);
          BC(512);
          ldir();
          IX(34175);
          DE(20601);
          C = 6;
          $38528();
          IX(34172);
          DE(20592);
          C = 3;
          $38528();
          int var68 = mem(34251);
          A = var68;
          int var69 = A + 1;
          A = var69;
          wMem(34251, A);
          if (F != 0) {
            break;
          }

          IX(34175);
          int var82 = IX() + 4;
          int var83 = mem(var82);
          A = var83;
          int var84 = A - 58;
          F = var84;
          if (F != 0) {
            break;
          }

          int var85 = IX() + 4;
          wMem(var85, 48);
          int var86 = IX() + 3;
          int var87 = mem(var86);
          A = var87;
          int var88 = A - 54;
          F = var88;
          if (F != 0) {
            break;
          }

          int var89 = IX() + 3;
          wMem(var89, 48);
          int var90 = IX();
          int var91 = mem(var90);
          A = var91;
          int var92 = A - 49;
          F = var92;
          if (F == 0) {
            int var98 = IX() + 1;
            int var99 = mem(var98);
            A = var99;
            int var100 = A - 51;
            F = var100;
            if (F != 0) {
              break;
            }

            int var101 = IX() + 5;
            int var102 = mem(var101);
            A = var102;
            int var103 = A - 112;
            F = var103;
            if (F == 0) {
              continue label145;
            }

            int var104 = IX();
            wMem(var104, 32);
            int var105 = IX() + 1;
            wMem(var105, 49);
            int var106 = IX() + 5;
            wMem(var106, 112);
            break;
          }

          int var93 = IX() + 1;
          int var94 = mem(var93);
          A = var94;
          int var95 = A - 58;
          F = var95;
          if (F == 0) {
            int var96 = IX() + 1;
            wMem(var96, 48);
            int var97 = IX();
            wMem(var97, 49);
          }
          break;
        }
      }

      BC(65278);
      E = A;
      B = 127;
      int var70 = A | E;
      A = var70;
      F = A;
      int var71 = A & 1;
      A = var71;
      F = A;
      if (F != 0) {
        int var72 = mem(34272);
        A = var72;
        int var73 = A + 1;
        A = var73;
        wMem(34272, A);
        if (F != 0) {
          B = 253;
          int var80 = A & 31;
          A = var80;
          F = A;
          int var81 = A - 31;
          F = var81;
          DE(0);
        }

        while(true) {
          B = 2;
          int var74 = A & 31;
          A = var74;
          F = A;
          int var75 = A - 31;
          F = var75;
          int var76 = E + 1;
          E = var76;
          if (F == 0) {
            int var77 = D + 1;
            D = var77;
            if (F == 0) {
              int var78 = mem(34275);
              A = var78;
              int var79 = A - 10;
              F = var79;
              if (F != 0) {
                $35563();
              }
            }
          }
        }
      }
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
      int var6 = A + 3;
      A = var6;
      F = A;
      int var7 = A & 7;
      A = var7;
      F = A;
      D = A;
      int var8 = HL();
      int var9 = mem(var8);
      A = var9;
      int var10 = A + 24;
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
    } while(F != 0);

  }

  public void $36147() {
    $36203();
    IX(24064);
    A = 112;
    wMem(36189, A);
    $36171();
    IX(24320);
    A = 120;
    wMem(36189, A);
  }

  public void $36171() {
    C = 0;

    do {
      E = C;
      int var1 = IX();
      int var2 = mem(var1);
      A = var2;
      HL(32928);
      BC(54);
      cpir();
      C = E;
      B = 8;
      D = 120;

      do {
        int var3 = HL();
        int var4 = mem(var3);
        A = var4;
        int var5 = DE();
        wMem(var5, A);
        int var6 = HL() + 1;
        HL(var6);
        int var7 = D + 1;
        D = var7;
        int var8 = B + -1;
        B = var8;
      } while(B != 0);

      int var9 = IX() + 1;
      IX(var9);
      int var10 = C + 1;
      C = var10;
    } while(F != 0);

  }

  public void $36203() {
    HL(32768);
    IX(24064);

    do {
      int var1 = HL();
      int var2 = mem(var1);
      A = var2;
      int var3 = A << 1;
      A = var3;
      int var4 = A << 1;
      A = var4;
      $36288();
      int var5 = HL();
      int var6 = mem(var5);
      A = var6;
      int var7 = A >> 1;
      A = var7;
      int var8 = A >> 1;
      A = var8;
      int var9 = A >> 1;
      A = var9;
      int var10 = A >> 1;
      A = var10;
      $36288();
      int var11 = HL();
      int var12 = mem(var11);
      A = var12;
      int var13 = A >> 1;
      A = var13;
      int var14 = A >> 1;
      A = var14;
      $36288();
      int var15 = HL();
      int var16 = mem(var15);
      A = var16;
      $36288();
      int var17 = HL() + 1;
      HL(var17);
      A = L;
      int var18 = A & 128;
      A = var18;
      F = A;
    } while(F == 0);

    int var19 = mem(32985);
    A = var19;
    int var20 = A | A;
    A = var20;
    F = A;
    if (F != 0) {
      B = A;
      int var34 = mem(32973);
      A = var34;

      do {
        int var35 = HL();
        wMem(var35, A);
        int var36 = HL() + 1;
        HL(var36);
        int var37 = B + -1;
        B = var37;
      } while(B != 0);
    }

    int var21 = mem(32989);
    A = var21;
    int var22 = A | A;
    A = var22;
    F = A;
    if (F != 0) {
      int var23 = mem(32986);
      A = var23;
      int var24 = A & 1;
      A = var24;
      F = A;
      int var25 = A << 1;
      A = var25;
      int var26 = A + 223;
      A = var26;
      F = A;
      E = A;
      D = 255;
      int var27 = mem(32989);
      A = var27;
      B = A;
      int var28 = mem(32964);
      A = var28;

      do {
        int var29 = HL();
        wMem(var29, A);
        int var30 = HL();
        int var31 = DE();
        int var32 = var30 + var31;
        HL(var32);
        int var33 = B + -1;
        B = var33;
      } while(B != 0);

    }
  }

  public void $36288() {
    int var1 = A & 3;
    A = var1;
    F = A;
    C = A;
    int var2 = A << 1;
    A = var2;
    int var3 = A << 1;
    A = var3;
    int var4 = A << 1;
    A = var4;
    int var5 = A + C;
    A = var5;
    F = A;
    int var6 = A + 160;
    A = var6;
    F = A;
    E = A;
    D = 128;
    int var7 = DE();
    int var8 = mem(var7);
    A = var8;
    int var9 = IX();
    wMem(var9, A);
    int var10 = IX() + 1;
    IX(var10);
  }

  public void $36508() {
    int var1 = A & 240;
    A = var1;
    F = A;
    L = A;
    int var2 = A ^ A;
    A = var2;
    F = A;
    H = A;
    int var3 = mem(34259);
    A = var3;
    int var4 = A & 31;
    A = var4;
    F = A;
    int var5 = A | L;
    A = var5;
    F = A;
    L = A;
  }

  public void $37056() {
    IX(33024);

    while(true) {
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
            int var74 = IX();
            int var75 = mem[var74];
            int var76 = var75 & 128;
            int var77 = IX();
            wMem(var77, var76);
            F = var75;
            if (F != 0) {
              int var98 = IX() + 1;
              int var99 = mem(var98);
              A = var99;
              int var100 = A & 128;
              F = var100;
              if (F != 0) {
                int var104 = A - 2;
                A = var104;
                F = A;
                int var105 = A - 148;
                F = var105;
                if (F < 0) {
                  int var106 = A - 2;
                  A = var106;
                  F = A;
                  int var107 = A - 128;
                  F = var107;
                  if (F == 0) {
                    int var108 = A ^ A;
                    A = var108;
                    F = A;
                  }
                }
              } else {
                int var101 = A + 2;
                A = var101;
                F = A;
                int var102 = A - 18;
                F = var102;
                if (F < 0) {
                  int var103 = A + 2;
                  A = var103;
                  F = A;
                }
              }
            } else {
              int var78 = IX() + 1;
              int var79 = mem(var78);
              A = var79;
              int var80 = A & 128;
              F = var80;
              if (F == 0) {
                int var94 = A - 2;
                A = var94;
                F = A;
                int var95 = A - 20;
                F = var95;
                if (F < 0) {
                  int var96 = A - 2;
                  A = var96;
                  F = A;
                  int var97 = A | A;
                  A = var97;
                  F = A;
                  if (F == 0) {
                    A = 128;
                  }
                }
              } else {
                int var81 = A + 2;
                A = var81;
                F = A;
                int var82 = A - 146;
                F = var82;
                if (F < 0) {
                  int var93 = A + 2;
                  A = var93;
                  F = A;
                }
              }
            }

            int var83 = IX() + 1;
            wMem(var83, A);
            int var84 = A & 127;
            A = var84;
            F = A;
            int var85 = IX() + 7;
            int var86 = mem(var85);
            int var87 = A - var86;
            F = var87;
            int var88 = IX() + 7;
            mem(var88);
            if (F == 0) {
              int var89 = IX();
              int var90 = mem(var89);
              A = var90;
              int var91 = A ^ 128;
              A = var91;
              F = A;
              int var92 = IX();
              wMem(var92, A);
            }
          } else {
            label81: {
              int var42 = IX();
              int var43 = mem(var42);
              A = var43;
              int var44 = A ^ 8;
              A = var44;
              F = A;
              int var45 = IX();
              wMem(var45, A);
              int var46 = A & 24;
              A = var46;
              F = A;
              if (F != 0) {
                int var70 = IX();
                int var71 = mem(var70);
                A = var71;
                int var72 = A + 32;
                A = var72;
                F = A;
                int var73 = IX();
                wMem(var73, A);
              }

              int var47 = IX() + 3;
              int var48 = mem(var47);
              A = var48;
              int var49 = IX() + 4;
              int var50 = mem(var49);
              int var51 = A + var50;
              A = var51;
              int var52 = IX() + 4;
              mem(var52);
              int var53 = IX() + 4;
              mem(var53);
              F = A;
              int var54 = IX() + 4;
              mem(var54);
              int var55 = IX() + 3;
              wMem(var55, A);
              int var56 = IX() + 7;
              int var57 = mem(var56);
              int var58 = A - var57;
              F = var58;
              int var59 = IX() + 7;
              mem(var59);
              if (F < 0) {
                int var63 = IX() + 6;
                int var64 = mem(var63);
                int var65 = A - var64;
                F = var65;
                int var66 = IX() + 6;
                mem(var66);
                if (F != 0 && F >= 0) {
                  break label81;
                }

                int var67 = IX() + 6;
                int var68 = mem(var67);
                A = var68;
                int var69 = IX() + 3;
                wMem(var69, A);
              }

              int var60 = IX() + 4;
              int var61 = mem(var60);
              A = var61;
              int var62 = IX() + 4;
              wMem(var62, A);
            }
          }
        } else {
          int var9 = IX();
          int var10 = mem[var9];
          int var11 = var10 & 128;
          int var12 = IX();
          wMem(var12, var11);
          F = var10;
          if (F == 0) {
            int var27 = IX();
            int var28 = mem(var27);
            A = var28;
            int var29 = A - 32;
            A = var29;
            F = A;
            int var30 = A & 127;
            A = var30;
            F = A;
            int var31 = IX();
            wMem(var31, A);
            int var32 = A - 96;
            F = var32;
            if (F >= 0) {
              int var33 = IX() + 2;
              int var34 = mem(var33);
              A = var34;
              int var35 = A & 31;
              A = var35;
              F = A;
              int var36 = IX() + 6;
              int var37 = mem(var36);
              int var38 = A - var37;
              F = var38;
              int var39 = IX() + 6;
              mem(var39);
              if (F != 0) {
                int var10000 = IX() + 2;
                var10000 = IX() + 2;
              } else {
                int var40 = IX();
                wMem(var40, 129);
              }
            }
          } else {
            int var13 = IX();
            int var14 = mem(var13);
            A = var14;
            int var15 = A + 32;
            A = var15;
            F = A;
            int var16 = A | 128;
            A = var16;
            F = A;
            int var17 = IX();
            wMem(var17, A);
            int var18 = A - 160;
            F = var18;
            if (F < 0) {
              int var19 = IX() + 2;
              int var20 = mem(var19);
              A = var20;
              int var21 = A & 31;
              A = var21;
              F = A;
              int var22 = IX() + 7;
              int var23 = mem(var22);
              int var24 = A - var23;
              F = var24;
              int var25 = IX() + 7;
              mem(var25);
              if (F != 0) {
                int var110 = IX() + 2;
              } else {
                int var26 = IX();
                wMem(var26, 97);
              }
            }
          }
        }
      }

      DE(8);
      int var5 = IX();
      int var6 = DE();
      int var7 = var5 + var6;
      IX(var7);
    }
  }

  public void $37310() {
    IX(33024);

    while(true) {
      int var1 = IX();
      int var2 = mem(var1);
      A = var2;
      int var3 = A - 255;
      F = var3;
      if (F == 0) {
        return;
      }

      int var4 = A & 7;
      A = var4;
      F = A;
      if (F != 0) {
        int var8 = A - 3;
        F = var8;
        if (F != 0) {
          int var145 = A - 4;
          F = var145;
          if (F != 0) {
            int var201 = IX() + 3;
            int var202 = mem(var201);
            E = var202;
            D = 130;
            int var203 = DE();
            int var204 = mem(var203);
            A = var204;
            L = A;
            int var205 = IX() + 2;
            int var206 = mem(var205);
            A = var206;
            int var207 = A & 31;
            A = var207;
            F = A;
            int var208 = A + L;
            A = var208;
            F = A;
            L = A;
            A = E;
            int var209 = A << 1;
            A = var209;
            int var210 = A & 1;
            A = var210;
            F = A;
            int var211 = A | 92;
            A = var211;
            F = A;
            H = A;
            DE(31);
            int var212 = IX() + 1;
            int var213 = mem(var212);
            A = var213;
            int var214 = A & 15;
            A = var214;
            F = A;
            int var215 = A + 56;
            A = var215;
            F = A;
            int var216 = A & 71;
            A = var216;
            F = A;
            C = A;
            int var217 = HL();
            int var218 = mem(var217);
            A = var218;
            int var219 = A & 56;
            A = var219;
            F = A;
            int var220 = A ^ C;
            A = var220;
            F = A;
            C = A;
            int var221 = HL();
            wMem(var221, C);
            int var222 = HL() + 1;
            HL(var222);
            int var223 = HL();
            wMem(var223, C);
            int var224 = HL();
            int var225 = DE();
            int var226 = var224 + var225;
            HL(var226);
            int var227 = HL();
            wMem(var227, C);
            int var228 = HL() + 1;
            HL(var228);
            int var229 = HL();
            wMem(var229, C);
            int var230 = IX() + 3;
            int var231 = mem(var230);
            A = var231;
            int var232 = A & 14;
            A = var232;
            F = A;
            if (F != 0) {
              int var264 = HL();
              int var265 = DE();
              int var266 = var264 + var265;
              HL(var266);
              int var267 = HL();
              wMem(var267, C);
              int var268 = HL() + 1;
              HL(var268);
              int var269 = HL();
              wMem(var269, C);
            }

            C = 1;
            int var233 = IX() + 1;
            int var234 = mem(var233);
            A = var234;
            int var235 = IX();
            int var236 = mem(var235);
            int var237 = A & var236;
            A = var237;
            int var238 = IX();
            mem(var238);
            int var239 = IX();
            mem(var239);
            F = A;
            int var240 = IX();
            mem(var240);
            int var241 = IX() + 2;
            int var242 = mem(var241);
            int var243 = A | var242;
            A = var243;
            int var244 = IX() + 2;
            mem(var244);
            int var245 = IX() + 2;
            mem(var245);
            F = A;
            int var246 = IX() + 2;
            mem(var246);
            int var247 = A & 224;
            A = var247;
            F = A;
            E = A;
            int var248 = IX() + 5;
            int var249 = mem(var248);
            D = var249;
            H = 130;
            int var250 = IX() + 3;
            int var251 = mem(var250);
            L = var251;
            int var252 = IX() + 2;
            int var253 = mem(var252);
            A = var253;
            int var254 = A & 31;
            A = var254;
            F = A;
            int var255 = HL();
            int var256 = mem(var255);
            int var257 = A | var256;
            A = var257;
            int var258 = HL();
            mem(var258);
            int var259 = HL();
            mem(var259);
            F = A;
            int var260 = HL();
            mem(var260);
            int var261 = HL() + 1;
            HL(var261);
            int var262 = HL();
            int var263 = mem(var262);
            H = var263;
            L = A;
            $37974();
          } else {
            int var146 = IX();
            int var147 = mem[var146];
            int var148 = var147 & 128;
            int var149 = IX();
            wMem(var149, var148);
            F = var147;
            if (F == 0) {
              int var273 = IX() + 4;
              var273 = IX() + 4;
              C = 44;
            } else {
              int var275 = IX() + 4;
              C = 244;
            }

            int var150 = IX() + 4;
            int var151 = mem(var150);
            A = var151;
            int var152 = A - C;
            F = var152;
            if (F != 0) {
              int var153 = A & 224;
              A = var153;
              F = A;
              if (F == 0) {
                int var154 = IX() + 2;
                int var155 = mem(var154);
                E = var155;
                D = 130;
                int var156 = DE();
                int var157 = mem(var156);
                A = var157;
                int var158 = IX() + 4;
                int var159 = mem(var158);
                int var160 = A + var159;
                A = var160;
                int var161 = IX() + 4;
                mem(var161);
                int var162 = IX() + 4;
                mem(var162);
                F = A;
                int var163 = IX() + 4;
                mem(var163);
                L = A;
                A = E;
                int var164 = A & 128;
                A = var164;
                F = A;
                int var165 = A << 1;
                A = var165;
                int var166 = A | 92;
                A = var166;
                F = A;
                H = A;
                int var167 = IX() + 5;
                wMem(var167, 0);
                int var168 = HL();
                int var169 = mem(var168);
                A = var169;
                int var170 = A & 7;
                A = var170;
                F = A;
                int var171 = A - 7;
                F = var171;
                if (F == 0) {
                  int var276 = IX() + 5;
                  var276 = IX() + 5;
                }

                int var172 = HL();
                int var173 = mem(var172);
                A = var173;
                int var174 = A | 7;
                A = var174;
                F = A;
                int var175 = HL();
                wMem(var175, A);
                int var176 = DE() + 1;
                DE(var176);
                int var177 = DE();
                int var178 = mem(var177);
                A = var178;
                H = A;
                int var179 = H + -1;
                H = var179;
                F = H;
                int var180 = IX() + 6;
                int var181 = mem(var180);
                A = var181;
                int var182 = HL();
                wMem(var182, A);
                int var183 = H + 1;
                H = var183;
                int var184 = HL();
                int var185 = mem(var184);
                A = var185;
                int var186 = IX() + 5;
                int var187 = mem(var186);
                int var188 = A & var187;
                A = var188;
                int var189 = IX() + 5;
                mem(var189);
                int var190 = IX() + 5;
                mem(var190);
                F = A;
                int var191 = IX() + 5;
                mem(var191);
                int var192 = HL();
                wMem(var192, 255);
                int var193 = H + 1;
                H = var193;
                int var194 = IX() + 6;
                int var195 = mem(var194);
                A = var195;
                int var196 = HL();
                wMem(var196, A);
              }
            } else {
              BC(640);
              int var197 = mem(32990);
              A = var197;

              do {
                int var198 = A ^ 24;
                A = var198;
                F = A;

                do {
                  int var199 = B + -1;
                  B = var199;
                } while(B != 0);

                B = C;
                int var200 = C + -1;
                C = var200;
                F = C;
              } while(F != 0);
            }
          }
        } else {
          IY(33280);
          int var9 = IX() + 9;
          wMem(var9, 0);
          int var10 = IX() + 2;
          int var11 = mem(var10);
          A = var11;
          int var12 = IX() + 3;
          wMem(var12, A);
          int var13 = IX() + 5;
          wMem(var13, 128);

          while(true) {
            label107: {
              int var14 = IY();
              int var15 = mem(var14);
              A = var15;
              int var16 = IX() + 3;
              int var17 = mem(var16);
              int var18 = A + var17;
              A = var18;
              int var19 = IX() + 3;
              mem(var19);
              int var20 = IX() + 3;
              mem(var20);
              F = A;
              int var21 = IX() + 3;
              mem(var21);
              L = A;
              int var22 = IY() + 1;
              int var23 = mem(var22);
              H = var23;
              int var24 = mem(34262);
              A = var24;
              int var25 = A | A;
              A = var25;
              F = A;
              if (F == 0) {
                int var132 = IX() + 5;
                int var133 = mem(var132);
                A = var133;
                int var134 = HL();
                int var135 = mem(var134);
                int var136 = A & var135;
                A = var136;
                int var137 = HL();
                mem(var137);
                int var138 = HL();
                mem(var138);
                F = A;
                int var139 = HL();
                mem(var139);
                if (F == 0) {
                  break label107;
                }

                int var140 = IX() + 9;
                int var141 = mem(var140);
                A = var141;
                wMem(34262, A);
                int var142 = IX() + 11;
                int var143 = mem[var142];
                int var144 = var143 | 1;
                wMem(var142, var144);
                wMem(var142, var143);
              }

              int var26 = IX() + 9;
              int var27 = mem(var26);
              int var28 = A - var27;
              F = var28;
              int var29 = IX() + 9;
              mem(var29);
              if (F == 0) {
                int var120 = IX() + 11;
                int var121 = mem[var120];
                int var122 = var121 & 1;
                wMem(var120, var122);
                F = var121;
                if (F != 0) {
                  int var123 = IX() + 3;
                  int var124 = mem(var123);
                  B = var124;
                  int var125 = IX() + 5;
                  int var126 = mem(var125);
                  A = var126;
                  C = 1;
                  int var127 = A - 4;
                  F = var127;
                  if (F >= 0) {
                    C = 0;
                    int var129 = A - 16;
                    F = var129;
                    if (F >= 0) {
                      int var130 = B + -1;
                      B = var130;
                      F = B;
                      C = 3;
                      int var131 = A - 64;
                      F = var131;
                      if (F >= 0) {
                        C = 2;
                      }
                    }
                  }

                  A = IYL;
                  int var128 = A - 16;
                  A = var128;
                  F = A;
                  wMem(34255, A);
                  $36508();
                }
              }
            }

            int var30 = IX() + 5;
            int var31 = mem(var30);
            A = var31;
            int var32 = HL();
            int var33 = mem(var32);
            int var34 = A | var33;
            A = var34;
            int var35 = HL();
            mem(var35);
            int var36 = HL();
            mem(var36);
            F = A;
            int var37 = HL();
            mem(var37);
            int var38 = HL();
            wMem(var38, A);
            int var39 = IX() + 9;
            int var40 = mem(var39);
            A = var40;
            int var41 = IX() + 1;
            int var42 = mem(var41);
            int var43 = A + var42;
            A = var43;
            int var44 = IX() + 1;
            mem(var44);
            int var45 = IX() + 1;
            mem(var45);
            F = A;
            int var46 = IX() + 1;
            mem(var46);
            L = A;
            int var47 = L | 128;
            L = var47;
            H = 131;
            int var48 = HL();
            int var49 = mem(var48);
            E = var49;
            D = 0;
            int var50 = IY();
            int var51 = DE();
            int var52 = var50 + var51;
            IY(var52);
            int var53 = L & -129;
            L = var53;
            int var54 = HL();
            int var55 = mem(var54);
            A = var55;
            int var56 = A | A;
            A = var56;
            F = A;
            if (F != 0) {
              B = A;
              int var109 = IX() + 1;
              int var110 = mem[var109];
              int var111 = var110 & 128;
              wMem(var109, var111);
              F = var110;
              if (F != 0) {
                do {
                  int var116 = IX() + 5;
                  int var117 = mem[var116];
                  int var118 = var117 & 1;
                  wMem(var116, var118);
                  F = var117;
                  if (F != 0) {
                    int var270 = IX() + 3;
                    var270 = IX() + 3;
                  }

                  int var119 = B + -1;
                  B = var119;
                } while(B != 0);
              } else {
                do {
                  int var112 = IX() + 5;
                  int var113 = mem[var112];
                  int var114 = var113 & 128;
                  wMem(var112, var114);
                  F = var113;
                  if (F != 0) {
                    int var10000 = IX() + 3;
                  }

                  int var115 = B + -1;
                  B = var115;
                } while(B != 0);
              }
            }

            int var57 = IX() + 9;
            int var58 = mem(var57);
            A = var58;
            int var59 = IX() + 4;
            int var60 = mem(var59);
            int var61 = A - var60;
            F = var61;
            int var62 = IX() + 4;
            mem(var62);
            if (F == 0) {
              int var63 = mem(34262);
              A = var63;
              int var64 = A & 128;
              F = var64;
              if (F != 0) {
                int var105 = A + 1;
                A = var105;
                wMem(34262, A);
                int var106 = IX() + 11;
                int var107 = mem[var106];
                int var108 = var107 & -2;
                wMem(var106, var108);
                wMem(var106, var107);
              } else {
                int var65 = IX() + 11;
                int var66 = mem[var65];
                int var67 = var66 & 1;
                wMem(var65, var67);
                F = var66;
                if (F != 0) {
                  int var68 = mem(34256);
                  A = var68;
                  int var69 = A & 2;
                  F = var69;
                  if (F != 0) {
                    int var70 = A >> 1;
                    A = var70;
                    int var71 = IX();
                    int var72 = mem(var71);
                    int var73 = A ^ var72;
                    A = var73;
                    int var74 = IX();
                    mem(var74);
                    int var75 = IX();
                    mem(var75);
                    F = A;
                    int var76 = IX();
                    mem(var76);
                    int var77 = A << 1;
                    A = var77;
                    int var78 = A << 1;
                    A = var78;
                    int var79 = A & 2;
                    A = var79;
                    F = A;
                    int var80 = A + -1;
                    A = var80;
                    F = A;
                    HL(34262);
                    int var81 = HL();
                    int var82 = mem(var81);
                    int var83 = A + var82;
                    A = var83;
                    int var84 = HL();
                    mem(var84);
                    int var85 = HL();
                    mem(var85);
                    F = A;
                    int var86 = HL();
                    mem(var86);
                    int var87 = HL();
                    wMem(var87, A);
                    int var88 = mem(33003);
                    A = var88;
                    C = A;
                    int var89 = mem(33824);
                    A = var89;
                    int var90 = A - C;
                    F = var90;
                    if (F == 0) {
                      int var101 = HL();
                      int var102 = mem(var101);
                      A = var102;
                      int var103 = A - 12;
                      F = var103;
                      if (F < 0) {
                        int var104 = HL();
                        wMem(var104, 12);
                      }
                    }

                    int var91 = HL();
                    int var92 = mem(var91);
                    A = var92;
                    int var93 = IX() + 4;
                    int var94 = mem(var93);
                    int var95 = A - var94;
                    F = var95;
                    int var96 = IX() + 4;
                    mem(var96);
                    if (F >= 0 && F != 0) {
                      int var97 = HL();
                      wMem(var97, 240);
                      int var98 = mem(34255);
                      A = var98;
                      int var99 = A & 248;
                      A = var99;
                      F = A;
                      wMem(34255, A);
                      int var100 = A ^ A;
                      A = var100;
                      F = A;
                      wMem(34257, A);
                    }
                  }
                }
              }
              break;
            }

            int var272 = IX() + 9;
          }
        }
      }

      DE(8);
      int var5 = IX();
      int var6 = DE();
      int var7 = var5 + var6;
      IX(var7);
    }
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
        int var11 = A << 1;
        A = var11;
        int var12 = A & 1;
        A = var12;
        F = A;
        int var13 = A + 92;
        A = var13;
        F = A;
        D = A;
        int var14 = H + 1;
        H = var14;
        int var15 = HL();
        int var16 = mem(var15);
        E = var16;
        int var17 = H + -1;
        H = var17;
        F = H;
        int var18 = DE();
        int var19 = mem(var18);
        A = var19;
        int var20 = A & 7;
        A = var20;
        F = A;
        int var21 = A - 7;
        F = var21;
        if (F != 0) {
          int var22 = mem(34251);
          A = var22;
          int var23 = A + L;
          A = var23;
          F = A;
          int var24 = A & 3;
          A = var24;
          F = A;
          int var25 = A + 3;
          A = var25;
          F = A;
          C = A;
          int var26 = DE();
          int var27 = mem(var26);
          A = var27;
          int var28 = A & 248;
          A = var28;
          F = A;
          int var29 = A | C;
          A = var29;
          F = A;
          int var30 = DE();
          wMem(var30, A);
          int var31 = HL();
          int var32 = mem(var31);
          A = var32;
          int var33 = A << 1;
          A = var33;
          int var34 = A << 1;
          A = var34;
          int var35 = A << 1;
          A = var35;
          int var36 = A << 1;
          A = var36;
          int var37 = A & 8;
          A = var37;
          F = A;
          int var38 = A + 96;
          A = var38;
          F = A;
          D = A;
          HL(32993);
          B = 8;
          $38555();
        } else {
          IX(34172);

          while(true) {
            int var39 = IX() + 2;
            int var40 = mem(var39);
            A = var40;
            int var41 = A - 58;
            F = var41;
            if (F != 0) {
              int var42 = mem(32990);
              A = var42;
              C = 128;

              do {
                int var43 = A ^ 24;
                A = var43;
                F = A;
                E = A;
                A = 144;
                int var44 = A - C;
                A = var44;
                F = A;
                B = A;
                A = E;

                do {
                  int var45 = B + -1;
                  B = var45;
                } while(B != 0);

                int var46 = C + -1;
                C = var46;
                F = C;
                int var47 = C + -1;
                C = var47;
                F = C;
              } while(F != 0);

              int var48 = mem(34270);
              A = var48;
              int var49 = A + 1;
              A = var49;
              wMem(34270, A);
              if (F == 0) {
                A = 1;
                wMem(34271, A);
              }

              int var50 = HL();
              int var51 = mem[var50];
              int var52 = var51 & -65;
              int var53 = HL();
              wMem(var53, var52);
              int var54 = HL();
              wMem(var54, var51);
              break;
            }

            int var55 = IX() + 2;
            wMem(var55, 48);
          }
        }
      }

      int var8 = L + 1;
      L = var8;
    } while(F != 0);

  }

  public void $37974() {
    B = 16;

    do {
      int var1 = C & 1;
      F = var1;
      int var2 = DE();
      int var3 = mem(var2);
      A = var3;
      if (F != 0) {
        int var35 = HL();
        int var36 = mem(var35);
        int var37 = A & var36;
        A = var37;
        int var38 = HL();
        mem(var38);
        int var39 = HL();
        mem(var39);
        F = A;
        int var40 = HL();
        mem(var40);
        if (F != 0) {
          return;
        }

        int var41 = DE();
        int var42 = mem(var41);
        A = var42;
        int var43 = HL();
        int var44 = mem(var43);
        int var45 = A | var44;
        A = var45;
        int var46 = HL();
        mem(var46);
        int var47 = HL();
        mem(var47);
        F = A;
        int var48 = HL();
        mem(var48);
      }

      int var4 = HL();
      wMem(var4, A);
      int var5 = L + 1;
      L = var5;
      int var6 = DE() + 1;
      DE(var6);
      int var7 = C & 1;
      F = var7;
      int var8 = DE();
      int var9 = mem(var8);
      A = var9;
      if (F != 0) {
        int var21 = HL();
        int var22 = mem(var21);
        int var23 = A & var22;
        A = var23;
        int var24 = HL();
        mem(var24);
        int var25 = HL();
        mem(var25);
        F = A;
        int var26 = HL();
        mem(var26);
        if (F != 0) {
          return;
        }

        int var27 = DE();
        int var28 = mem(var27);
        A = var28;
        int var29 = HL();
        int var30 = mem(var29);
        int var31 = A | var30;
        A = var31;
        int var32 = HL();
        mem(var32);
        int var33 = HL();
        mem(var33);
        F = A;
        int var34 = HL();
        mem(var34);
      }

      int var10 = HL();
      wMem(var10, A);
      int var11 = L + -1;
      L = var11;
      F = L;
      int var12 = H + 1;
      H = var12;
      int var13 = DE() + 1;
      DE(var13);
      A = H;
      int var14 = A & 7;
      A = var14;
      F = A;
      if (F == 0) {
        A = H;
        int var17 = A - 8;
        A = var17;
        F = A;
        H = A;
        A = L;
        int var18 = A + 32;
        A = var18;
        F = A;
        L = A;
        int var19 = A & 224;
        A = var19;
        F = A;
        if (F == 0) {
          A = H;
          int var20 = A + 8;
          A = var20;
          F = A;
          H = A;
        }
      }

      int var15 = B + -1;
      B = var15;
    } while(B != 0);

    int var16 = A ^ A;
    A = var16;
    F = A;
  }

  public void $38137() {
    A = H;
    int var1 = A & 1;
    A = var1;
    F = A;
    int var2 = A << 1;
    A = var2;
    int var3 = A << 1;
    A = var3;
    int var4 = A << 1;
    A = var4;
    int var5 = A + 112;
    A = var5;
    F = A;
    H = A;
    E = L;
    D = H;
    int var6 = mem(32985);
    A = var6;
    int var7 = A | A;
    A = var7;
    F = A;
    if (F != 0) {
      B = A;
      int var8 = mem(32982);
      A = var8;
      int var9 = A | A;
      A = var9;
      F = A;
      if (F == 0) {
        int var21 = HL();
        int var22 = mem(var21);
        A = var22;
        int var23 = H + 1;
        H = var23;
        int var24 = H + 1;
        H = var24;
        int var25 = HL();
        int var26 = mem(var25);
        C = var26;
      } else {
        int var10 = HL();
        int var11 = mem(var10);
        A = var11;
        int var12 = H + 1;
        H = var12;
        int var13 = H + 1;
        H = var13;
        int var14 = HL();
        int var15 = mem(var14);
        C = var15;
      }

      do {
        int var16 = DE();
        wMem(var16, A);
        int var17 = HL();
        wMem(var17, C);
        int var18 = L + 1;
        L = var18;
        int var19 = E + 1;
        E = var19;
        int var20 = B + -1;
        B = var20;
      } while(B != 0);

    }
  }

  public void $38196() {
    int var1 = mem(33824);
    A = var1;
    int var2 = A - 35;
    F = var2;
    if (F == 0) {
      int var13 = mem(34271);
      A = var13;
      int var14 = A | A;
      A = var14;
      F = A;
      if (F == 0) {
        int var18 = mem(34251);
        A = var18;
        int var19 = A & 2;
        A = var19;
        F = A;
        int var20 = A >> 1;
        A = var20;
        int var21 = A >> 1;
        A = var21;
        int var22 = A >> 1;
        A = var22;
        int var23 = A >> 1;
        A = var23;
        int var24 = A | 128;
        A = var24;
        F = A;
        E = A;
        int var25 = mem(34255);
        A = var25;
        int var26 = A - 208;
        F = var26;
        if (F != 0) {
          E = 192;
          int var27 = A - 192;
          F = var27;
          if (F < 0) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974();
        HL(17733);
        HL(1799);
      } else {
        int var15 = mem(34259);
        A = var15;
        int var16 = A & 31;
        A = var16;
        F = A;
        int var17 = A - 6;
        F = var17;
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
        int var7 = A >> 1;
        A = var7;
        int var8 = A >> 1;
        A = var8;
        int var9 = A >> 1;
        A = var9;
        E = A;
        int var10 = mem(34271);
        A = var10;
        int var11 = A - 3;
        F = var11;
        if (F == 0) {
          int var12 = E | 64;
          E = var12;
        }

        D = 166;
        IX(33488);
        BC(4124);
        $38504();
        HL(1799);
      }
    }
  }

  public void $38344() {
    B = 0;
    int var1 = mem(32986);
    A = var1;
    int var2 = A & 1;
    A = var2;
    F = A;
    int var3 = A + 64;
    A = var3;
    F = A;
    E = A;
    D = 0;
    int var4 = HL();
    int var5 = DE();
    int var6 = var4 + var5;
    HL(var6);
    int var7 = mem(32964);
    A = var7;
    int var8 = HL();
    int var9 = mem(var8);
    int var10 = A - var9;
    F = var10;
    int var11 = HL();
    mem(var11);
    if (F == 0) {
      int var13 = mem(34257);
      A = var13;
      int var14 = A | A;
      A = var14;
      F = A;
      if (F == 0) {
        int var15 = mem(34258);
        A = var15;
        int var16 = A & 3;
        A = var16;
        F = A;
        int var17 = A << 1;
        A = var17;
        int var18 = A << 1;
        A = var18;
        B = A;
        int var19 = mem(32986);
        A = var19;
        int var20 = A & 1;
        A = var20;
        F = A;
        int var21 = A + -1;
        A = var21;
        F = A;
        int var22 = A ^ 12;
        A = var22;
        F = A;
        int var23 = A ^ B;
        A = var23;
        F = A;
        int var24 = A & 12;
        A = var24;
        F = A;
        B = A;
      }
    }

    DE(31);
    C = 15;
    $38430();
    int var12 = HL() + 1;
    HL(var12);
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

  public void $38504() {
    do {
      int var1 = IX();
      int var2 = mem(var1);
      A = var2;
      int var3 = IX() + 1;
      int var4 = mem(var3);
      H = var4;
      int var5 = A | C;
      A = var5;
      F = A;
      L = A;
      int var6 = DE();
      int var7 = mem(var6);
      A = var7;
      int var8 = HL();
      int var9 = mem(var8);
      int var10 = A | var9;
      A = var10;
      int var11 = HL();
      mem(var11);
      int var12 = HL();
      mem(var12);
      F = A;
      int var13 = HL();
      mem(var13);
      int var14 = HL();
      wMem(var14, A);
      int var15 = HL() + 1;
      HL(var15);
      int var16 = DE() + 1;
      DE(var16);
      int var17 = DE();
      int var18 = mem(var17);
      A = var18;
      int var19 = HL();
      int var20 = mem(var19);
      int var21 = A | var20;
      A = var21;
      int var22 = HL();
      mem(var22);
      int var23 = HL();
      mem(var23);
      F = A;
      int var24 = HL();
      mem(var24);
      int var25 = HL();
      wMem(var25, A);
      int var26 = IX() + 1;
      IX(var26);
      int var27 = IX() + 1;
      IX(var27);
      int var28 = DE() + 1;
      DE(var28);
      int var29 = B + -1;
      B = var29;
    } while(B != 0);

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
    } while(F != 0);

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
    } while(B != 0);

  }

  public void $38562() {
    while(true) {
      int var1 = HL();
      int var2 = mem(var1);
      A = var2;
      int var3 = A - 255;
      F = var3;
      if (F == 0) {
        return;
      }

      BC(100);
      int var4 = A ^ A;
      A = var4;
      F = A;
      int var5 = HL();
      int var6 = mem(var5);
      E = var6;
      D = E;

      while(true) {
        int var7 = D + -1;
        D = var7;
        F = D;
        if (F == 0) {
          D = E;
          int var12 = A ^ 24;
          A = var12;
          F = A;
        }

        int var8 = B + -1;
        B = var8;
        if (B == 0) {
          A = C;
          int var9 = A - 50;
          F = var9;
          if (F == 0) {
          }

          int var10 = C + -1;
          C = var10;
          F = C;
          if (F == 0) {
            $38601();
            if (F != 0) {
              return;
            }

            int var11 = HL() + 1;
            HL(var11);
            break;
          }
        }
      }
    }
  }

  public void $38601() {
    int var1 = mem(34254);
    A = var1;
    int var2 = A | A;
    A = var2;
    F = A;
    if (F != 0) {
      int var5 = A & 16;
      F = var5;
      if (F != 0) {
        return;
      }
    }

    BC(45054);
    int var3 = A & 1;
    A = var3;
    F = A;
    int var4 = A - 1;
    F = var4;
  }

  public void $38622() {
    E = A;
    C = 254;

    do {
      D = A;
      int var1 = D & -17;
      D = var1;
      int var2 = D & -9;
      D = var2;
      B = E;

      do {
        int var3 = A - B;
        F = var3;
        if (F == 0) {
          D = 24;
        }

        int var4 = B + -1;
        B = var4;
      } while(B != 0);

      int var5 = A + -1;
      A = var5;
      F = A;
    } while(F != 0);

  }

  public void $38555() {
  }

  public void $35211() {
  }

  public void $36307() {
  }

  public void $38064() {
  }

  public void $38276() {
  }
}
