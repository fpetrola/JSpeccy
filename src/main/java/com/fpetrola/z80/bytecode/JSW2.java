package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  public void $36147() {
    $36203();
    IX = 24064;
    A = 112;
    mem[361890] = A;
    $36171();
    IX = 24320;
    A = 120;
    mem[361890] = A;
    C = 0;

    do {
      E = C;
      int var1 = mem[IX];
      A = var1;
      HL = 32928;
      BC = 54;
      cpir();
      C = E;
      B = 8;
      D = 120;

      do {
        int var2 = mem[HL];
        A = var2;
        mem[DE] = A;
        int var3 = HL + 1;
        HL = var3;
        int var4 = D + 1;
        D = var4;
        int var5 = B + -1;
        B = var5;
      } while (B != 0);

      int var6 = IX + 1;
      IX = var6;
      int var7 = C + 1;
      C = var7;
    } while (F != 0);

  }

  public void $36203() {
    HL = 32768;
    IX = 24064;

    do {
      int var1 = mem[HL];
      A = var1;
      $36288();
      int var2 = mem[HL];
      A = var2;
      $36288();
      int var3 = mem[HL];
      A = var3;
      $36288();
      int var4 = mem[HL];
      A = var4;
      $36288();
      int var5 = HL + 1;
      HL = var5;
      A = L;
      int var6 = A & 128;
      A = var6;
      F = A;
    } while (F == 0);

    int var7 = mem[329850];
    A = var7;
    int var8 = A | A;
    A = var8;
    F = A;
    if (F != 0) {
      B = A;
      int var18 = mem[329730];
      A = var18;

      do {
        mem[HL] = A;
        int var19 = HL + 1;
        HL = var19;
        int var20 = B + -1;
        B = var20;
      } while (B != 0);
    }

    int var9 = mem[329890];
    A = var9;
    int var10 = A | A;
    A = var10;
    F = A;
    if (F != 0) {
      int var11 = mem[329860];
      A = var11;
      int var12 = A & 1;
      A = var12;
      F = A;
      int var13 = A + 223;
      A = var13;
      F = A;
      E = A;
      D = 255;
      int var14 = mem[329890];
      A = var14;
      B = A;
      int var15 = mem[329640];
      A = var15;

      do {
        mem[HL] = A;
        int var16 = HL + DE;
        HL = var16;
        int var17 = B + -1;
        B = var17;
      } while (B != 0);

    }
  }

  public void $36171() {
  }

  public void $36288() {
    int var1 = A & 3;
    A = var1;
    F = A;
    C = A;
    int var2 = A + C;
    A = var2;
    F = A;
    int var3 = A + 160;
    A = var3;
    F = A;
    E = A;
    D = 128;
    int var4 = mem[DE];
    A = var4;
    mem[IX] = A;
    int var5 = IX + 1;
    IX = var5;
  }

  public void $38528() {
    do {
      int var1 = mem[IX];
      A = var1;
      $38545();
      int var2 = IX + 1;
      IX = var2;
      int var3 = E + 1;
      E = var3;
      A = D;
      int var4 = A - 8;
      A = var4;
      F = A;
      D = A;
      int var5 = C + -1;
      C = var5;
      F = C;
    } while (F != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    int var1 = L | 128;
    L = var1;
    int var2 = HL * 2;
    HL = var2;
    int var3 = HL * 2;
    HL = var3;
    int var4 = HL * 2;
    HL = var4;
    B = 8;

    do {
      int var5 = mem[HL];
      A = var5;
      mem[DE] = A;
      int var6 = HL + 1;
      HL = var6;
      int var7 = D + 1;
      D = var7;
      int var8 = B + -1;
      B = var8;
    } while (B != 0);

  }

  public void $38562() {
    while (true) {
      int var1 = mem[HL];
      A = var1;
      int var2 = A - 255;
      F = var2;
      if (F == 0) {
        return;
      }

      BC = 100;
      int var3 = A ^ A;
      A = var3;
      F = A;
      int var4 = mem[HL];
      E = var4;
      D = E;

      while (true) {
        int var5 = D + -1;
        D = var5;
        F = D;
        if (F == 0) {
          D = E;
          int var10 = A ^ 24;
          A = var10;
          F = A;
        }

        int var6 = B + -1;
        B = var6;
        if (B == 0) {
          A = C;
          int var7 = A - 50;
          F = var7;
          if (F == 0) {
          }

          int var8 = C + -1;
          C = var8;
          F = C;
          if (F == 0) {
            $38601();
            if (F != 0) {
              return;
            }

            int var9 = HL + 1;
            HL = var9;
            break;
          }
        }
      }
    }
  }

  public void $38601() {
    int var1 = mem[342540];
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

    BC = 45054;
    int var3 = A & 1;
    A = var3;
    F = A;
    int var4 = A - 1;
    F = var4;
  }

  public void $35211() {
    int var1 = mem[342520];
    A = var1;
    HL = 20640;
    int var2 = A | A;
    A = var2;
    F = A;
    if (F != 0) {
      B = A;

      do {
        C = 0;
        int var3 = mem[342730];
        A = var3;
        int var4 = A & 96;
        A = var4;
        F = A;
        E = A;
        D = 157;
        $37974();
        int var5 = HL + 1;
        HL = var5;
        int var6 = HL + 1;
        HL = var6;
        int var7 = B + -1;
        B = var7;
      } while (B != 0);

    }
  }

  public void $37974() {
  }

  public void $35245() {
    $35211();
    HL = 24064;
    DE = 23552;
    BC = 512;
    ldir();
    HL = 28672;
    DE = 24576;
    BC = 4096;
    ldir();
    $37056();
    int var1 = mem[342710];
    A = var1;
    int var2 = A - 3;
    F = var2;
    if (F != 0) {
      $36307();
    }

  }

  public void $37056() {
    IX = 33024;
    int var1 = mem[IX];
    A = var1;
    int var2 = A - 255;
    F = var2;
    if (F != 0) {
      ;
    }
  }

  public void $36307() {
    label92:
    {
      int var1 = mem[342620];
      A = var1;
      int var2 = A + -1;
      A = var2;
      F = A;
      int var3 = A & 128;
      F = var3;
      int var4 = mem[342570];
      A = var4;
      int var5 = A - 1;
      F = var5;
      if (F == 0) {
        int var78 = mem[342610];
        A = var78;
        int var79 = A & 254;
        A = var79;
        F = A;
        int var80 = A - 8;
        A = var80;
        F = A;
        HL = 34255;
        int var81 = mem[HL];
        int var82 = A + var81;
        A = var82;
        int var10000 = mem[HL];
        var10000 = mem[HL];
        F = A;
        var10000 = mem[HL];
        mem[HL] = A;
        int var83 = A - 240;
        F = var83;
        $36508();
        int var84 = mem[329460];
        A = var84;
        int var85 = mem[HL];
        int var86 = A - var85;
        F = var86;
        var10000 = mem[HL];
        int var87 = HL + 1;
        HL = var87;
        int var88 = mem[HL];
        int var89 = A - var88;
        F = var89;
        var10000 = mem[HL];
        int var90 = mem[342610];
        A = var90;
        int var91 = A + 1;
        A = var91;
        mem[342610] = A;
        int var92 = A - 8;
        A = var92;
        F = A;
        int var93 = A + 1;
        A = var93;
        D = A;
        C = 32;
        int var94 = mem[329900];
        A = var94;

        do {
          int var95 = A ^ 24;
          A = var95;
          F = A;
          B = D;

          do {
            int var96 = B + -1;
            B = var96;
          } while (B != 0);

          int var97 = C + -1;
          C = var97;
          F = C;
        } while (F != 0);

        int var98 = mem[342610];
        A = var98;
        int var99 = A - 18;
        F = var99;
        int var100 = A - 16;
        F = var100;
        if (F != 0) {
          int var101 = A - 13;
          F = var101;
          if (F != 0) {
            break label92;
          }
        }
      }

      int var6 = mem[342550];
      A = var6;
      int var7 = A & 14;
      A = var7;
      F = A;
      if (F == 0) {
        DE = 64;
        int var64 = HL + DE;
        HL = var64;
        int var65 = H & 2;
        F = var65;
        int var66 = mem[329550];
        A = var66;
        int var67 = mem[HL];
        int var68 = A - var67;
        F = var68;
        int var106 = mem[HL];
        if (F != 0) {
          int var69 = HL + 1;
          HL = var69;
          int var70 = mem[329550];
          A = var70;
          int var71 = mem[HL];
          int var72 = A - var71;
          F = var72;
          var106 = mem[HL];
          if (F != 0) {
            int var73 = mem[329280];
            A = var73;
            int var74 = mem[HL];
            int var75 = A - var74;
            F = var75;
            int var76 = mem[HL];
            int var77 = A - var76;
            F = var77;
            var106 = mem[HL];
          }
        }
      }

      int var8 = mem[342570];
      A = var8;
      int var9 = A - 1;
      F = var9;
      if (F != 0) {
        HL = 34256;
        int var47 = mem[HL];
        int var48 = var47 & -3;
        mem[HL] = var48;
        mem[HL] = var47;
        int var49 = mem[342570];
        A = var49;
        int var50 = A | A;
        A = var50;
        F = A;
        if (F == 0) {
          A = 2;
          mem[342570] = A;
          return;
        }

        int var51 = A + 1;
        A = var51;
        int var52 = A - 16;
        F = var52;
        if (F == 0) {
          A = 12;
        }

        mem[342570] = A;
        D = A;
        C = 32;
        int var53 = mem[329900];
        A = var53;

        do {
          int var54 = A ^ 24;
          A = var54;
          F = A;
          B = D;

          do {
            int var55 = B + -1;
            B = var55;
          } while (B != 0);

          int var56 = C + -1;
          C = var56;
          F = C;
        } while (F != 0);

        int var57 = mem[342550];
        A = var57;
        int var58 = A + 8;
        A = var58;
        F = A;
        mem[342550] = A;
        int var59 = A & 240;
        A = var59;
        F = A;
        L = A;
        int var60 = A ^ A;
        A = var60;
        F = A;
        H = A;
        int var61 = mem[342590];
        A = var61;
        int var62 = A & 31;
        A = var62;
        F = A;
        int var63 = A | L;
        A = var63;
        F = A;
        L = A;
        return;
      }
    }

    int var10 = mem[342560];
    A = var10;
    int var11 = A & 2;
    A = var11;
    F = A;
    if (F != 0) {
      int var12 = mem[342620];
      A = var12;
      int var13 = A + -1;
      A = var13;
      F = A;
      int var14 = A & 128;
      F = var14;
      if (F != 0) {
        int var15 = mem[342560];
        A = var15;
        int var16 = A & 1;
        A = var16;
        F = A;
        int var17 = mem[342580];
        A = var17;
        int var18 = A | A;
        A = var18;
        F = A;
        if (F != 0) {
          int var46 = A + -1;
          A = var46;
          F = A;
          mem[342580] = A;
        } else {
          int var19 = mem[342570];
          A = var19;
          BC = 0;
          int var20 = A - 0;
          F = var20;
          if (F == 0) {
            BC = 0;
            int var36 = mem[329860];
            A = var36;
            int var37 = A + -1;
            A = var37;
            F = A;
            int var38 = A | 161;
            A = var38;
            F = A;
            int var39 = A ^ 224;
            A = var39;
            F = A;
            E = A;
            D = 0;
            int var40 = HL + DE;
            HL = var40;
            int var41 = mem[329640];
            A = var41;
            int var42 = mem[HL];
            int var43 = A - var42;
            F = var43;
            int var109 = mem[HL];
            if (F == 0) {
              BC = 32;
              int var44 = mem[329860];
              A = var44;
              int var45 = A | A;
              A = var45;
              F = A;
              if (F == 0) {
                BC = 65504;
              }
            }
          }

          A = L;
          int var21 = A & 31;
          A = var21;
          F = A;
          int var22 = HL + BC;
          HL = var22;
          DE = 32;
          int var23 = HL + DE;
          HL = var23;
          int var24 = mem[329460];
          A = var24;
          int var25 = mem[HL];
          int var26 = A - var25;
          F = var26;
          int var110 = mem[HL];
          if (F != 0) {
            int var27 = mem[342550];
            A = var27;
            int var28 = A + C;
            A = var28;
            F = A;
            B = A;
            int var29 = A & 15;
            A = var29;
            F = A;
            if (F != 0) {
              int var31 = mem[329460];
              A = var31;
              int var32 = HL + DE;
              HL = var32;
              int var33 = mem[HL];
              int var34 = A - var33;
              F = var34;
              var110 = mem[HL];
              if (F == 0) {
                return;
              }

              int var35 = A | A;
              A = var35;
              F = A;
            }

            int var30 = A | A;
            A = var30;
            F = A;
            A = B;
            mem[342550] = A;
            A = 3;
            mem[342580] = A;
          }
        }
      }
    }
  }

  public void $38196() {
    int var1 = mem[338240];
    A = var1;
    int var2 = A - 35;
    F = var2;
    int var3 = mem[342710];
    A = var3;
    int var4 = A | A;
    A = var4;
    F = A;
    int var5 = mem[342510];
    A = var5;
    int var6 = A & 2;
    A = var6;
    F = A;
    int var7 = A | 128;
    A = var7;
    F = A;
    E = A;
    int var8 = mem[342550];
    A = var8;
    int var9 = A - 208;
    F = var9;
    if (F != 0) {
      E = 192;
      int var10 = A - 192;
      F = var10;
      if (F < 0) {
        E = 224;
      }
    }

    D = 156;
    HL = 26734;
    C = 1;
    $37974();
    HL = 17733;
    HL = 1799;
  }

  public void $35317() {
    HL = 24576;
    DE = 16384;
    BC = 4096;
    ldir();
    int var1 = mem[342710];
    A = var1;
    int var2 = A & 2;
    A = var2;
    F = A;
    HL = 34258;
    int var3 = mem[HL];
    int var4 = A | var3;
    A = var4;
    int var10000 = mem[HL];
    var10000 = mem[HL];
    F = A;
    var10000 = mem[HL];
    mem[HL] = A;
    int var5 = mem[342530];
    A = var5;
    int var6 = A | A;
    A = var6;
    F = A;
    if (F != 0) {
      int var39 = A + -1;
      A = var39;
      F = A;
      mem[342530] = A;
      int var40 = A & 56;
      A = var40;
      F = A;
      HL = 23552;
      DE = 23553;
      BC = 511;
      mem[HL] = A;
      ldir();
    }

    HL = 23552;
    DE = 22528;
    BC = 512;
    ldir();
    IX = 34175;
    DE = 20601;
    C = 6;
    $38528();
    IX = 34172;
    DE = 20592;
    C = 3;
    $38528();
    int var7 = mem[342510];
    A = var7;
    int var8 = A + 1;
    A = var8;
    mem[342510] = A;
    if (F == 0) {
      IX = 34175;
      int var21 = IX + 4;
      int var22 = mem[var21];
      A = var22;
      int var23 = A - 58;
      F = var23;
      if (F == 0) {
        int var24 = IX + 4;
        mem[var24] = 48;
        int var25 = IX + 3;
        int var26 = mem[var25];
        A = var26;
        int var27 = A - 54;
        F = var27;
        if (F == 0) {
          int var28 = IX + 3;
          mem[var28] = 48;
          int var29 = mem[IX];
          A = var29;
          int var30 = A - 49;
          F = var30;
          int var31 = IX + 1;
          int var32 = mem[var31];
          A = var32;
          int var33 = A - 51;
          F = var33;
          if (F == 0) {
            int var34 = IX + 5;
            int var35 = mem[var34];
            A = var35;
            int var36 = A - 112;
            F = var36;
            mem[IX] = 32;
            int var37 = IX + 1;
            mem[var37] = 49;
            int var38 = IX + 5;
            mem[var38] = 112;
          }
        }
      }
    }

    BC = 65278;
    E = A;
    B = 127;
    int var9 = A | E;
    A = var9;
    F = A;
    int var10 = A & 1;
    A = var10;
    F = A;
    int var11 = mem[342720];
    A = var11;
    int var12 = A + 1;
    A = var12;
    mem[342720] = A;
    if (F != 0) {
      B = 253;
      int var19 = A & 31;
      A = var19;
      F = A;
      int var20 = A - 31;
      F = var20;
      DE = 0;
    }

    while (true) {
      B = 2;
      int var13 = A & 31;
      A = var13;
      F = A;
      int var14 = A - 31;
      F = var14;
      int var15 = E + 1;
      E = var15;
      if (F == 0) {
        int var16 = D + 1;
        D = var16;
        if (F == 0) {
          int var17 = mem[342750];
          A = var17;
          int var18 = A - 10;
          F = var18;
          if (F != 0) {
            $35563();
          }
        }
      }
    }
  }

  public void $35563() {
    HL = 22528;
    int var1 = mem[HL];
    A = var1;
    int var2 = A & 7;
    A = var2;
    F = A;

    do {
      int var3 = mem[HL];
      A = var3;
      int var4 = A + 3;
      A = var4;
      F = A;
      int var5 = A & 7;
      A = var5;
      F = A;
      D = A;
      int var6 = mem[HL];
      A = var6;
      int var7 = A + 24;
      A = var7;
      F = A;
      int var8 = A & 184;
      A = var8;
      F = A;
      int var9 = A | D;
      A = var9;
      F = A;
      mem[HL] = A;
      int var10 = HL + 1;
      HL = var10;
      A = H;
      int var11 = A - 91;
      F = var11;
    } while (F != 0);

  }

  public void $36508() {
  }

  public void $38064() {
    int var1 = mem[330030];
    A = var1;
    mem[338240] = A;
    int var2 = mem[342590];
    A = var2;
    int var3 = A & 31;
    A = var3;
    F = A;
    int var4 = A + 160;
    A = var4;
    F = A;
    mem[342590] = A;
    A = 93;
    mem[342600] = A;
    A = 208;
    mem[342550] = A;
    int var5 = A ^ A;
    A = var5;
    F = A;
    mem[342570] = A;
  }

  public void $38344() {
    B = 0;
    int var1 = mem[329860];
    A = var1;
    int var2 = A & 1;
    A = var2;
    F = A;
    int var3 = A + 64;
    A = var3;
    F = A;
    E = A;
    D = 0;
    int var4 = HL + DE;
    HL = var4;
    int var5 = mem[329640];
    A = var5;
    int var6 = mem[HL];
    int var7 = A - var6;
    F = var7;
    int var10000 = mem[HL];
    if (F == 0) {
      int var8 = mem[342570];
      A = var8;
      int var9 = A | A;
      A = var9;
      F = A;
      if (F == 0) {
        int var10 = mem[342580];
        A = var10;
        int var11 = A & 3;
        A = var11;
        F = A;
        B = A;
        int var12 = mem[329860];
        A = var12;
        int var13 = A & 1;
        A = var13;
        F = A;
        int var14 = A + -1;
        A = var14;
        F = A;
        int var15 = A ^ 12;
        A = var15;
        F = A;
        int var16 = A ^ B;
        A = var16;
        F = A;
        int var17 = A & 12;
        A = var17;
        F = A;
        B = A;
      }
    }

    DE = 31;
    C = 15;
    $38430();
  }

  public void $38430() {
    int var1 = mem[329280];
    A = var1;
    int var2 = mem[HL];
    int var3 = A - var2;
    F = var3;
    int var10000 = mem[HL];
    if (F == 0) {
      A = C;
      int var7 = A & 15;
      A = var7;
      F = A;
      if (F != 0) {
        int var8 = mem[329280];
        A = var8;
        int var9 = A | 7;
        A = var9;
        F = A;
        mem[HL] = A;
      }
    }

    int var4 = mem[329550];
    A = var4;
    int var5 = mem[HL];
    int var6 = A - var5;
    F = var6;
    var10000 = mem[HL];
  }

  public void $34835() {
    label65:
    while (true) {
      HL = 16384;
      DE = 16385;
      BC = 6143;
      mem[HL] = 0;
      ldir();
      HL = 38912;
      BC = 768;
      ldir();
      HL = 23136;
      DE = 23137;
      BC = 31;
      mem[HL] = 70;
      ldir();
      IX = 33876;
      DE = 20576;
      C = 32;
      $38528();
      DE = 22528;

      do {
        int var1 = mem[DE];
        A = var1;
        int var2 = A | A;
        A = var2;
        F = A;
        if (F != 0) {
          int var34 = A - 211;
          F = var34;
          if (F != 0) {
            int var35 = A - 9;
            F = var35;
            if (F != 0) {
              int var36 = A - 45;
              F = var36;
              if (F != 0) {
                int var37 = A - 36;
                F = var37;
                if (F != 0) {
                  C = 0;
                  int var38 = A - 8;
                  F = var38;
                  if (F != 0) {
                    int var43 = A - 41;
                    F = var43;
                    if (F != 0) {
                      int var44 = A - 44;
                      F = var44;
                      int var45 = A - 5;
                      F = var45;
                      if (F != 0) {
                        C = 16;
                      }
                    }
                  }

                  A = E;
                  int var39 = A & 1;
                  A = var39;
                  F = A;
                  int var40 = A | C;
                  A = var40;
                  F = A;
                  C = A;
                  B = 0;
                  HL = 33841;
                  int var41 = HL + BC;
                  HL = var41;
                  int var42 = D & 1;
                  F = var42;
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

        int var3 = DE + 1;
        DE = var3;
        A = D;
        int var4 = A - 90;
        F = var4;
      } while (F != 0);

      BC = 31;
      int var5 = A ^ A;
      A = var5;
      F = A;

      do {
        int var6 = A | E;
        A = var6;
        F = A;
        int var7 = B + -1;
        B = var7;
      } while (B != 0);

      int var8 = A & 32;
      A = var8;
      F = A;
      if (F == 0) {
        A = 1;
        mem[342540] = A;
      }

      HL = 34299;
      $38562();
      if (F == 0) {
        int var23 = A ^ A;
        A = var23;
        F = A;
        mem[342760] = A;

        while (true) {
          $35563();
          HL = 23136;
          DE = 23137;
          BC = 31;
          mem[HL] = 79;
          ldir();
          int var24 = mem[342760];
          A = var24;
          IX = 33876;
          E = A;
          D = 0;
          int var25 = IX + DE;
          IX = var25;
          DE = 20576;
          C = 32;
          $38528();
          int var26 = mem[342760];
          A = var26;
          int var27 = A & 31;
          A = var27;
          F = A;
          int var28 = A + 50;
          A = var28;
          F = A;
          $38622();
          BC = 45054;
          int var29 = A & 1;
          A = var29;
          F = A;
          int var30 = A - 1;
          F = var30;
          if (F != 0) {
            break;
          }

          int var31 = mem[342760];
          A = var31;
          int var32 = A + 1;
          A = var32;
          int var33 = A - 224;
          F = var33;
          mem[342760] = A;
          if (F == 0) {
            continue label65;
          }
        }
      }

      HL = 34181;
      DE = 34175;
      BC = 6;
      ldir();
      HL = 39424;
      DE = 23040;
      BC = 256;
      ldir();
      int var9 = mem[338240];
      A = var9;
      int var10 = A | 192;
      A = var10;
      F = A;
      H = A;
      L = 0;
      DE = 32768;
      BC = 256;
      ldir();
      IX = 33008;
      DE = 33024;
      A = 8;

      do {
        int var11 = mem[IX];
        L = var11;
        int var12 = L & -129;
        L = var12;
        H = 20;
        int var13 = HL * 2;
        HL = var13;
        int var14 = HL * 2;
        HL = var14;
        int var15 = HL * 2;
        HL = var15;
        BC = 2;
        ldir();
        int var16 = IX + 1;
        int var17 = mem[var16];
        C = var17;
        mem[HL] = C;
        BC = 6;
        ldir();
        int var18 = IX + 1;
        IX = var18;
        int var19 = IX + 1;
        IX = var19;
        int var20 = A + -1;
        A = var20;
        F = A;
      } while (F != 0);

      HL = 34255;
      DE = 34263;
      BC = 7;
      ldir();
      $36147();
      HL = 20480;
      DE = 20481;
      BC = 2047;
      mem[HL] = 0;
      ldir();
      IX = 32896;
      C = 32;
      DE = 20480;
      $38528();
      IX = 34132;
      DE = 20576;
      C = 32;
      $38528();
      int var21 = mem[329900];
      A = var21;
      C = 254;
      int var22 = A ^ A;
      A = var22;
      F = A;
      mem[342620] = A;
      return;
    }
  }

  public void $38555() {
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
      } while (B != 0);

      int var5 = A + -1;
      A = var5;
      F = A;
    } while (F != 0);

  }

  public void $38276() {
    int var1 = mem[338240];
    A = var1;
    int var2 = A - 33;
    F = var2;
    if (F == 0) {
      int var3 = mem[342590];
      A = var3;
      int var4 = A - 188;
      F = var4;
      if (F == 0) {
        int var5 = A ^ A;
        A = var5;
        F = A;
        mem[342510] = A;
        A = 3;
        mem[342710] = A;
      }
    }
  }
}
