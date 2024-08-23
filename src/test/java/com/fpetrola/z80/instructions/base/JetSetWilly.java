package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.transformations.SpectrumApplication;

import java.util.HashMap;
import java.util.Map;

public class JetSetWilly extends SpectrumApplication {
  protected boolean replacing = true;

  protected Map<Integer, Runnable> getConvertedRoutines() {
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
    convertedRoutines.put(37841, () -> $37841());
    convertedRoutines.put(38555, () -> $38555());
    convertedRoutines.put(36147, () -> $36147());
    convertedRoutines.put(36171, () -> $36171());
    //convertedRoutines.put(36307, () -> $36307());


    return convertedRoutines;
  }

  public void $36307() {
    super.A = this.mem(34262);
    super.A = super.A + -1 & 255;
    super.F = super.A & 128;
    super.A = this.mem(34257);
    super.F = super.A - 1;
    if (super.F == 0) {
      super.A = this.mem(34261);
      super.A = super.A & 254;
      super.F = super.A;
      super.A = super.A - 8 & 255;
      super.F = super.A;
      this.HL(34255);
      super.A = super.A + this.mem(this.HL()) & 255;
      this.mem(this.HL());
      this.mem(this.HL());
      super.F = super.A;
      this.mem(this.HL());
      this.wMem(this.HL(), super.A);
      super.F = super.A - 240;
      this.$36508();
      super.A = this.mem(32946);
      super.F = super.A - this.mem(this.HL());
      this.mem(this.HL());
      this.HL(this.HL() + 1 & '\uffff');
      super.F = super.A - this.mem(this.HL());
      this.mem(this.HL());
      super.A = this.mem(34261);
      super.A = super.A + 1 & 255;
      super.F = super.A;
      this.wMem(34261, super.A);
      super.A = super.A - 8 & 255;
      super.F = super.A;
      super.A = -super.A & 255;
      super.A = super.A + 1 & 255;
      super.F = super.A;
      super.A = this.rlc(super.A);
      super.A = this.rlc(super.A);
      super.A = this.rlc(super.A);
      super.D = super.A;
      super.C = 32;
      super.A = this.mem(32990);

      do {
        super.A = super.A ^ 24;
        super.F = super.A;
        super.B = super.D;

        do {
          super.B = super.B + -1;
        } while (super.B != 0);

        super.C = super.C + -1 & 255;
        super.F = super.C;
      } while (super.F != 0);

      super.A = this.mem(34261);
      super.F = super.A - 18;
      super.F = super.A - 16;
      if (super.F != 0) {
        super.F = super.A - 13;
      }
    }

    super.A = this.mem(34255);
    super.A = super.A & 14;
    super.F = super.A;
    if (super.F == 0) {
      this.HL(this.mem16(34259));
      this.DE(64);
      this.HL(this.HL() + this.DE() & '\uffff');
      super.F = super.H & 2;
      super.A = this.mem(32955);
      super.F = super.A - this.mem(this.HL());
      this.mem(this.HL());
      if (super.F != 0) {
        this.HL(this.HL() + 1 & '\uffff');
        super.A = this.mem(32955);
        super.F = super.A - this.mem(this.HL());
        this.mem(this.HL());
        if (super.F != 0) {
          super.A = this.mem(32928);
          super.F = super.A - this.mem(this.HL());
          this.mem(this.HL());
          super.F = super.A - this.mem(this.HL());
          this.mem(this.HL());
        }
      }
    }

    super.A = this.mem(34257);
    super.F = super.A - 1;
    this.HL(34256);
    this.wMem(this.HL(), this.mem(this.HL()) & -3);
    super.A = this.mem(34257);
    super.A = super.A | super.A;
    super.F = super.A;
    super.A = super.A + 1 & 255;
    super.F = super.A;
    super.F = super.A - 16;
    if (super.F == 0) {
      super.A = 12;
    }

    this.wMem(34257, super.A);
    super.A = this.rlc(super.A);
    super.A = this.rlc(super.A);
    super.A = this.rlc(super.A);
    super.A = this.rlc(super.A);
    super.D = super.A;
    super.C = 32;
    super.A = this.mem(32990);

    do {
      super.A = super.A ^ 24;
      super.F = super.A;
      super.B = super.D;

      do {
        super.B = super.B + -1;
      } while (super.B != 0);

      super.C = super.C + -1 & 255;
      super.F = super.C;
    } while (super.F != 0);

    super.A = this.mem(34255);
    super.A = super.A + 8 & 255;
    super.F = super.A;
    this.wMem(34255, super.A);
  }

  public void $36508() {
    int var1 = super.A & 240;
    super.A = var1;
    super.F = super.A;
    super.L = super.A;
    int var2 = super.A ^ super.A;
    super.A = var2;
    super.F = super.A;
    int var3 = super.L;
    int var4 = this.rlc(var3) & 0xFE; //FIXME:
    super.L = var4;
    int var5 = (super.A + carry + 92) & 255; //FIXME:
    super.A = var5;
    super.F = super.A;
    super.H = super.A;
    int var6 = this.mem(34259);
    super.A = var6;
    int var7 = super.A & 31;
    super.A = var7;
    super.F = super.A;
    int var8 = super.A | super.L;
    super.A = var8;
    super.F = super.A;
    super.L = super.A;
    int var9 = this.HL();
    this.wMem16(34259, var9);
  }

  public void $35563() {
    this.HL(22528);
    int var1 = this.HL();
    int var2 = this.mem(var1);
    super.A = var2;
    int var3 = super.A & 7;
    super.A = var3;
    super.F = super.A;

    do {
      int var4 = this.HL();
      int var5 = this.mem(var4);
      super.A = var5;
      int var6 = super.A + 3 & 255;
      super.A = var6;
      super.F = super.A;
      int var7 = super.A & 7;
      super.A = var7;
      super.F = super.A;
      super.D = super.A;
      int var8 = this.HL();
      int var9 = this.mem(var8);
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
      this.wMem(var13, super.A);
      int var14 = this.HL() + 1 & '\uffff';
      this.HL(var14);
      super.A = super.H;
      int var15 = super.A - 91;
      super.F = var15;
    } while (super.F != 0);

  }

  public void $36147() {
    this.$36203();
    this.IX(24064);
    super.A = 112;
    this.wMem(36189, super.A);
    this.$36171();
    this.IX(24320);
    super.A = 120;
    this.wMem(36189, super.A);
    this.$36171();
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
      D = mem(36189); //FIXME:

      do {
        int var3 = HL();
        int var4 = mem(var3);
        A = var4;
        int var5 = DE();
        wMem(var5, A);
        int var6 = HL() + 1 & '\uffff';
        HL(var6);
        int var7 = D + 1 & 255;
        D = var7;
        F = D;
        int var8 = B + -1;
        B = var8;
      } while (B != 0);

      int var9 = IX() + 1 & '\uffff';
      IX(var9);
      int var10 = C + 1 & 255;
      C = var10;
      F = C;
    } while (F != 0);

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
      int var25 = this.HL() + 1 & '\uffff';
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
        int var47 = this.HL() + 1 & '\uffff';
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
      int var36 = super.A + 223 & 255;
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
        int var42 = var40 + var41 & '\uffff';
        this.HL(var42);
        int var43 = super.B + -1;
        super.B = var43;
      } while (super.B != 0);

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
    int var11 = this.mem(var10);
    super.A = var11;
    int var12 = this.IX();
    this.wMem(var12, super.A);
    int var13 = this.IX() + 1 & '\uffff';
    this.IX(var13);
  }

  public void $37056() {
    this.IX(33024);

    while (true) {
      int var1 = this.IX();
      int var2 = this.mem(var1);
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
            int var80 = this.mem(var79) & 128;
            super.F = var80;
            if (super.F != 0) {
              int var101 = this.IX() + 1;
              int var102 = this.mem(var101);
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
              int var82 = this.mem(var81);
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
            this.wMem(var86, super.A);
            int var87 = super.A & 127;
            super.A = var87;
            super.F = super.A;
            int var88 = this.IX() + 7;
            int var89 = this.mem(var88);
            int var90 = super.A - var89;
            super.F = var90;
            int var91 = this.IX() + 7;
            this.mem(var91);
            if (super.F == 0) {
              int var92 = this.IX();
              int var93 = this.mem(var92);
              super.A = var93;
              int var94 = super.A ^ 128;
              super.A = var94;
              super.F = super.A;
              int var95 = this.IX();
              this.wMem(var95, super.A);
            }
          } else {
            label81:
            {
              int var46 = this.IX();
              int var47 = this.mem(var46);
              super.A = var47;
              int var48 = super.A ^ 8;
              super.A = var48;
              super.F = super.A;
              int var49 = this.IX();
              this.wMem(var49, super.A);
              int var50 = super.A & 24;
              super.A = var50;
              super.F = super.A;
              if (super.F != 0) {
                int var75 = this.IX();
                int var76 = this.mem(var75);
                super.A = var76;
                int var77 = super.A + 32 & 255;
                super.A = var77;
                super.F = super.A;
                int var78 = this.IX();
                this.wMem(var78, super.A);
              }

              int var51 = this.IX() + 3;
              int var52 = this.mem(var51);
              super.A = var52;
              int var53 = this.IX() + 4;
              int var54 = this.mem(var53);
              int var55 = super.A + var54 & 255;
              super.A = var55;
              int var56 = this.IX() + 4;
              this.mem(var56);
              int var57 = this.IX() + 4;
              this.mem(var57);
              super.F = super.A;
              int var58 = this.IX() + 4;
              this.mem(var58);
              int var59 = this.IX() + 3;
              this.wMem(var59, super.A);
              int var60 = this.IX() + 7;
              int var61 = this.mem(var60);
              int var62 = super.A - var61;
              super.F = var62;
              int var63 = this.IX() + 7;
              this.mem(var63);
              if (super.F < 0) {
                int var68 = this.IX() + 6;
                int var69 = this.mem(var68);
                int var70 = super.A - var69;
                super.F = var70;
                int var71 = this.IX() + 6;
                this.mem(var71);
                if (super.F != 0 && super.F >= 0) {
                  break label81;
                }

                int var72 = this.IX() + 6;
                int var73 = this.mem(var72);
                super.A = var73;
                int var74 = this.IX() + 3;
                this.wMem(var74, super.A);
              }

              int var64 = this.IX() + 4;
              int var65 = this.mem(var64);
              super.A = var65;
              int var66 = -super.A & 255;
              super.A = var66;
              int var67 = this.IX() + 4;
              this.wMem(var67, super.A);
            }
          }
        } else {
          int var9 = this.IX();
          int var10 = this.mem(var9) & 128;
          super.F = var10;
          if (super.F == 0) {
            int var28 = this.IX();
            int var29 = this.mem(var28);
            super.A = var29;
            int var30 = super.A - 32 & 255;
            super.A = var30;
            super.F = super.A;
            int var31 = super.A & 127;
            super.A = var31;
            super.F = super.A;
            int var32 = this.IX();
            this.wMem(var32, super.A);
            int var33 = super.A - 96;
            super.F = var33;
            if (super.F >= 0) {
              int var34 = this.IX() + 2;
              int var35 = this.mem(var34);
              super.A = var35;
              int var36 = super.A & 31;
              super.A = var36;
              super.F = super.A;
              int var37 = this.IX() + 6;
              int var38 = this.mem(var37);
              int var39 = super.A - var38;
              super.F = var39;
              int var40 = this.IX() + 6;
              this.mem(var40);
              if (super.F != 0) {
                int var42 = this.IX() + 2;
                int var43 = this.mem(var42) + -1;
                this.wMem(var42, var43);
                int var44 = var43 & 255;
                this.wMem(var42, var44);
                int var10000 = this.IX() + 2;
              } else {
                int var41 = this.IX();
                this.wMem(var41, 129);
              }
            }
          } else {
            int var11 = this.IX();
            int var12 = this.mem(var11);
            super.A = var12;
            int var13 = super.A + 32 & 255;
            super.A = var13;
            super.F = super.A;
            int var14 = super.A | 128;
            super.A = var14;
            super.F = super.A;
            int var15 = this.IX();
            this.wMem(var15, super.A);
            int var16 = super.A - 160;
            super.F = var16;
            if (super.F < 0) {
              int var17 = this.IX() + 2;
              int var18 = this.mem(var17);
              super.A = var18;
              int var19 = super.A & 31;
              super.A = var19;
              super.F = super.A;
              int var20 = this.IX() + 7;
              int var21 = this.mem(var20);
              int var22 = super.A - var21;
              super.F = var22;
              int var23 = this.IX() + 7;
              this.mem(var23);
              if (super.F != 0) {
                int var25 = this.IX() + 2;
                int var26 = this.mem(var25) + 1;
                this.wMem(var25, var26);
                int var27 = var26 & 255;
                this.wMem(var25, var27);
                int var112 = this.IX() + 2;
              } else {
                int var24 = this.IX();
                this.wMem(var24, 97);
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

    while (true) {
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
          int var157 = super.A - 4;
          super.F = var157;
          if (super.F != 0) {
            int var221 = this.IX() + 3;
            int var222 = this.mem(var221);
            super.E = var222;
            super.D = 130;
            int var223 = this.DE();
            int var224 = this.mem(var223);
            super.A = var224;
            super.L = super.A;
            int var225 = this.IX() + 2;
            int var226 = this.mem(var225);
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
            int var234 = this.mem(var233);
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
            int var239 = this.mem(var238);
            super.A = var239;
            int var240 = super.A & 56;
            super.A = var240;
            super.F = super.A;
            int var241 = super.A ^ super.C;
            super.A = var241;
            super.F = super.A;
            super.C = super.A;
            int var242 = this.HL();
            this.wMem(var242, super.C);
            int var243 = this.HL() + 1 & '\uffff';
            this.HL(var243);
            int var244 = this.HL();
            this.wMem(var244, super.C);
            int var245 = this.HL();
            int var246 = this.DE();
            int var247 = var245 + var246 & '\uffff';
            this.HL(var247);
            int var248 = this.HL();
            this.wMem(var248, super.C);
            int var249 = this.HL() + 1 & '\uffff';
            this.HL(var249);
            int var250 = this.HL();
            this.wMem(var250, super.C);
            int var251 = this.IX() + 3;
            int var252 = this.mem(var251);
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
              this.wMem(var288, super.C);
              int var289 = this.HL() + 1 & '\uffff';
              this.HL(var289);
              int var290 = this.HL();
              this.wMem(var290, super.C);
            }

            super.C = 1;
            int var254 = this.IX() + 1;
            int var255 = this.mem(var254);
            super.A = var255;
            int var256 = this.IX();
            int var257 = this.mem(var256);
            int var258 = super.A & var257;
            super.A = var258;
            int var259 = this.IX();
            this.mem(var259);
            int var260 = this.IX();
            this.mem(var260);
            super.F = super.A;
            int var261 = this.IX();
            this.mem(var261);
            int var262 = this.IX() + 2;
            int var263 = this.mem(var262);
            int var264 = super.A | var263;
            super.A = var264;
            int var265 = this.IX() + 2;
            this.mem(var265);
            int var266 = this.IX() + 2;
            this.mem(var266);
            super.F = super.A;
            int var267 = this.IX() + 2;
            this.mem(var267);
            int var268 = super.A & 224;
            super.A = var268;
            super.F = super.A;
            super.E = super.A;
            int var269 = this.IX() + 5;
            int var270 = this.mem(var269);
            super.D = var270;
            super.H = 130;
            int var271 = this.IX() + 3;
            int var272 = this.mem(var271);
            super.L = var272;
            int var273 = this.IX() + 2;
            int var274 = this.mem(var273);
            super.A = var274;
            int var275 = super.A & 31;
            super.A = var275;
            super.F = super.A;
            int var276 = this.HL();
            int var277 = this.mem(var276);
            int var278 = super.A | var277;
            super.A = var278;
            int var279 = this.HL();
            this.mem(var279);
            int var280 = this.HL();
            this.mem(var280);
            super.F = super.A;
            int var281 = this.HL();
            this.mem(var281);
            int var282 = this.HL() + 1 & '\uffff';
            this.HL(var282);
            int var283 = this.HL();
            int var284 = this.mem(var283);
            super.H = var284;
            super.L = super.A;
            this.$37974();
          } else {
            int var158 = this.IX();
            int var159 = this.mem(var158) & 128;
            super.F = var159;
            if (super.F == 0) {
              int var218 = this.IX() + 4;
              int var219 = this.mem(var218) + -1;
              this.wMem(var218, var219);
              int var220 = var219 & 255;
              this.wMem(var218, var220);
              int var293 = this.IX() + 4;
              super.C = 44;
            } else {
              int var160 = this.IX() + 4;
              int var161 = this.mem(var160) + 1;
              this.wMem(var160, var161);
              int var162 = var161 & 255;
              this.wMem(var160, var162);
              int var294 = this.IX() + 4;
              super.C = 244;
            }

            int var163 = this.IX() + 4;
            int var164 = this.mem(var163);
            super.A = var164;
            int var165 = super.A - super.C;
            super.F = var165;
            if (super.F != 0) {
              int var166 = super.A & 224;
              super.A = var166;
              super.F = super.A;
              if (super.F == 0) {
                int var167 = this.IX() + 2;
                int var168 = this.mem(var167);
                super.E = var168;
                super.D = 130;
                int var169 = this.DE();
                int var170 = this.mem(var169);
                super.A = var170;
                int var171 = this.IX() + 4;
                int var172 = this.mem(var171);
                int var173 = super.A + var172 & 255;
                super.A = var173;
                int var174 = this.IX() + 4;
                this.mem(var174);
                int var175 = this.IX() + 4;
                this.mem(var175);
                super.F = super.A;
                int var176 = this.IX() + 4;
                this.mem(var176);
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
                this.wMem(var181, 0);
                int var182 = this.HL();
                int var183 = this.mem(var182);
                super.A = var183;
                int var184 = super.A & 7;
                super.A = var184;
                super.F = super.A;
                int var185 = super.A - 7;
                super.F = var185;
                if (super.F == 0) {
                  int var211 = this.IX() + 5;
                  int var212 = this.mem(var211) + -1;
                  this.wMem(var211, var212);
                  int var213 = var212 & 255;
                  this.wMem(var211, var213);
                  int var295 = this.IX() + 5;
                }

                int var186 = this.HL();
                int var187 = this.mem(var186);
                super.A = var187;
                int var188 = super.A | 7;
                super.A = var188;
                super.F = super.A;
                int var189 = this.HL();
                this.wMem(var189, super.A);
                int var190 = this.DE() + 1 & '\uffff';
                this.DE(var190);
                int var191 = this.DE();
                int var192 = this.mem(var191);
                super.A = var192;
                super.H = super.A;
                int var193 = super.H + -1 & 255;
                super.H = var193;
                super.F = super.H;
                int var194 = this.IX() + 6;
                int var195 = this.mem(var194);
                super.A = var195;
                int var196 = this.HL();
                this.wMem(var196, super.A);
                int var197 = super.H + 1 & 255;
                super.H = var197;
                super.F = super.H;
                int var198 = this.HL();
                int var199 = this.mem(var198);
                super.A = var199;
                int var200 = this.IX() + 5;
                int var201 = this.mem(var200);
                int var202 = super.A & var201;
                super.A = var202;
                int var203 = this.IX() + 5;
                this.mem(var203);
                int var204 = this.IX() + 5;
                this.mem(var204);
                super.F = super.A;
                int var205 = this.IX() + 5;
                this.mem(var205);
                int var206 = this.HL();
                this.wMem(var206, 255);
                int var207 = super.H + 1 & 255;
                super.H = var207;
                super.F = super.H;
                int var208 = this.IX() + 6;
                int var209 = this.mem(var208);
                super.A = var209;
                int var210 = this.HL();
                this.wMem(var210, super.A);
              }
            } else {
              this.BC(640);
              int var214 = this.mem(32990);
              super.A = var214;

              do {
                int var215 = super.A ^ 24;
                super.A = var215;
                super.F = super.A;

                do {
                  int var216 = super.B + -1;
                  super.B = var216;
                } while (super.B != 0);

                super.B = super.C;
                int var217 = super.C + -1 & 255;
                super.C = var217;
                super.F = super.C;
              } while (super.F != 0);
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

          while (true) {
            label107:
            {
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
                int var145 = this.IX() + 5;
                int var146 = this.mem(var145);
                super.A = var146;
                int var147 = this.HL();
                int var148 = this.mem(var147);
                int var149 = super.A & var148;
                super.A = var149;
                int var150 = this.HL();
                this.mem(var150);
                int var151 = this.HL();
                this.mem(var151);
                super.F = super.A;
                int var152 = this.HL();
                this.mem(var152);
                if (super.F == 0) {
                  break label107;
                }

                int var153 = this.IX() + 9;
                int var154 = this.mem(var153);
                super.A = var154;
                this.wMem(34262, super.A);
                int var155 = this.IX() + 11;
                int var156 = this.mem(var155) | 1;
                this.wMem(var155, var156);
              }

              int var26 = this.IX() + 9;
              int var27 = this.mem(var26);
              int var28 = super.A - var27;
              super.F = var28;
              int var29 = this.IX() + 9;
              this.mem(var29);
              if (super.F == 0) {
                int var133 = this.IX() + 11;
                int var134 = this.mem(var133) & 1;
                super.F = var134;
                if (super.F != 0) {
                  int var135 = this.IX() + 3;
                  int var136 = this.mem(var135);
                  super.B = var136;
                  int var137 = this.IX() + 5;
                  int var138 = this.mem(var137);
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
                  this.wMem16(34258, var140);
                  super.A = super.IYL;
                  int var141 = super.A - 16 & 255;
                  super.A = var141;
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
              int var113 = this.IX() + 1;
              int var114 = this.mem(var113) & 128;
              super.F = var114;
              if (super.F != 0) {
                do {
                  int var124 = this.IX() + 5;
                  int var125 = this.mem(var124);
                  int var126 = this.rlc(var125);
                  this.wMem(var124, var126);
                  int var127 = this.IX() + 5;
                  int var128 = this.mem(var127) & 1;
                  super.F = var128;
                  if (super.F != 0) {
                    int var130 = this.IX() + 3;
                    int var131 = this.mem(var130) + -1;
                    this.wMem(var130, var131);
                    int var132 = var131 & 255;
                    this.wMem(var130, var132);
                    int var291 = this.IX() + 3;
                  }

                  int var129 = super.B + -1;
                  super.B = var129;
                } while (super.B != 0);
              } else {
                do {
                  int var115 = this.IX() + 5;
                  int var116 = this.mem(var115);
                  int var117 = this.rrc(var116);
                  this.wMem(var115, var117);
                  int var118 = this.IX() + 5;
                  int var119 = this.mem(var118) & 128;
                  super.F = var119;
                  if (super.F != 0) {
                    int var121 = this.IX() + 3;
                    int var122 = this.mem(var121) + 1;
                    this.wMem(var121, var122);
                    int var123 = var122 & 255;
                    this.wMem(var121, var123);
                    int var10000 = this.IX() + 3;
                  }

                  int var120 = super.B + -1;
                  super.B = var120;
                } while (super.B != 0);
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
                int var107 = super.A + 1 & 255;
                super.A = var107;
                super.F = super.A;
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
                    int var82 = super.A + -1 & 255;
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

            int var110 = this.IX() + 9;
            int var111 = this.mem(var110) + 1;
            this.wMem(var110, var111);
            int var112 = var111 & 255;
            this.wMem(var110, var112);
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
    super.H = 164;
    int var1 = this.mem(41983);
    super.A = var1;
    super.L = super.A;

    do {
      int var2 = this.HL();
      int var3 = this.mem(var2);
      super.C = var3;
      int var4 = super.C & 127;
      super.C = var4;
      int var5 = this.mem(33824);
      super.A = var5;
      int var6 = super.A | 64;
      super.A = var6;
      super.F = super.A;
      int var7 = super.A - super.C;
      super.F = var7;
      if (super.F == 0) {
        int var9 = this.HL();
        int var10 = this.mem(var9);
        super.A = var10;
        int var11 = super.A;
        int var12 = this.rlc(var11);
        super.A = var12;
        int var13 = super.A & 1;
        super.A = var13;
        super.F = super.A;
        int var14 = super.A + 92 & 255;
        super.A = var14;
        super.F = super.A;
        super.D = super.A;
        int var15 = super.H + 1 & 255;
        super.H = var15;
        super.F = super.H;
        int var16 = this.HL();
        int var17 = this.mem(var16);
        super.E = var17;
        int var18 = super.H + -1 & 255;
        super.H = var18;
        super.F = super.H;
        int var19 = this.DE();
        int var20 = this.mem(var19);
        super.A = var20;
        int var21 = super.A & 7;
        super.A = var21;
        super.F = super.A;
        int var22 = super.A - 7;
        super.F = var22;
        if (super.F != 0) {
          int var23 = this.mem(34251);
          super.A = var23;
          int var24 = super.A + super.L & 255;
          super.A = var24;
          super.F = super.A;
          int var25 = super.A & 3;
          super.A = var25;
          super.F = super.A;
          int var26 = super.A + 3 & 255;
          super.A = var26;
          super.F = super.A;
          super.C = super.A;
          int var27 = this.DE();
          int var28 = this.mem(var27);
          super.A = var28;
          int var29 = super.A & 248;
          super.A = var29;
          super.F = super.A;
          int var30 = super.A | super.C;
          super.A = var30;
          super.F = super.A;
          int var31 = this.DE();
          this.wMem(var31, super.A);
          int var32 = this.HL();
          int var33 = this.mem(var32);
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
          int var40 = super.A;
          int var41 = this.rlc(var40);
          super.A = var41;
          int var42 = super.A & 8;
          super.A = var42;
          super.F = super.A;
          int var43 = super.A + 96 & 255;
          super.A = var43;
          super.F = super.A;
          super.D = super.A;
          int lastHL = HL();
          this.HL(32993);
          super.B = 8;
          this.$38555();
          HL(lastHL);
        } else {
          this.IX(34172);

          while (true) {
            int var44 = this.IX() + 2;
            int var45 = this.mem(var44) + 1;
            this.wMem(var44, var45);
            int var46 = var45 & 255;
            this.wMem(var44, var46);
            int var47 = this.IX() + 2;
            int var48 = this.mem(var47);
            super.A = var48;
            int var49 = super.A - 58;
            super.F = var49;
            if (super.F != 0) {
              int var50 = this.mem(32990);
              super.A = var50;
              super.C = 128;

              do {
                int var51 = super.A ^ 24;
                super.A = var51;
                super.F = super.A;
                super.E = super.A;
                super.A = 144;
                int var52 = super.A - super.C & 255;
                super.A = var52;
                super.F = super.A;
                super.B = super.A;
                super.A = super.E;

                do {
                  int var53 = super.B + -1;
                  super.B = var53;
                } while (super.B != 0);

                int var54 = super.C + -1 & 255;
                super.C = var54;
                super.F = super.C;
                int var55 = super.C + -1 & 255;
                super.C = var55;
                super.F = super.C;
              } while (super.F != 0);

              int var56 = this.mem(34270);
              super.A = var56;
              int var57 = super.A + 1 & 255;
              super.A = var57;
              super.F = super.A;
              this.wMem(34270, super.A);
              if (super.F == 0) {
                super.A = 1;
                this.wMem(34271, super.A);
              }

              int var58 = this.HL();
              int var59 = this.mem(var58) & -65;
              int var60 = this.HL();
              this.wMem(var60, var59);
              break;
            }

            int var61 = this.IX() + 2;
            this.wMem(var61, 48);
          }
        }
      }

      int var8 = super.L + 1 & 255;
      super.L = var8;
      super.F = super.L;
    } while (super.F != 0);

  }

  public void $37974() {
    super.B = 16;

    do {
      int var1 = super.C & 1;
      super.F = var1;
      int var2 = this.DE();
      int var3 = this.mem(var2);
      super.A = var3;
      if (super.F != 0) {
        int var35 = this.HL();
        int var36 = this.mem(var35);
        int var37 = super.A & var36;
        super.A = var37;
        int var38 = this.HL();
        this.mem(var38);
        int var39 = this.HL();
        this.mem(var39);
        super.F = super.A;
        int var40 = this.HL();
        this.mem(var40);
        if (super.F != 0) {
          return;
        }

        int var41 = this.DE();
        int var42 = this.mem(var41);
        super.A = var42;
        int var43 = this.HL();
        int var44 = this.mem(var43);
        int var45 = super.A | var44;
        super.A = var45;
        int var46 = this.HL();
        this.mem(var46);
        int var47 = this.HL();
        this.mem(var47);
        super.F = super.A;
        int var48 = this.HL();
        this.mem(var48);
      }

      int var4 = this.HL();
      this.wMem(var4, super.A);
      int var5 = super.L + 1 & 255;
      super.L = var5;
      super.F = super.L;
      int var6 = this.DE() + 1 & '\uffff';
      this.DE(var6);
      int var7 = super.C & 1;
      super.F = var7;
      int var8 = this.DE();
      int var9 = this.mem(var8);
      super.A = var9;
      if (super.F != 0) {
        int var21 = this.HL();
        int var22 = this.mem(var21);
        int var23 = super.A & var22;
        super.A = var23;
        int var24 = this.HL();
        this.mem(var24);
        int var25 = this.HL();
        this.mem(var25);
        super.F = super.A;
        int var26 = this.HL();
        this.mem(var26);
        if (super.F != 0) {
          return;
        }

        int var27 = this.DE();
        int var28 = this.mem(var27);
        super.A = var28;
        int var29 = this.HL();
        int var30 = this.mem(var29);
        int var31 = super.A | var30;
        super.A = var31;
        int var32 = this.HL();
        this.mem(var32);
        int var33 = this.HL();
        this.mem(var33);
        super.F = super.A;
        int var34 = this.HL();
        this.mem(var34);
      }

      int var10 = this.HL();
      this.wMem(var10, super.A);
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

      int var15 = super.B + -1;
      super.B = var15;
    } while (super.B != 0);

    int var16 = super.A ^ super.A;
    super.A = var16;
    super.F = super.A;
  }

  public void $38137() {
    int var1 = this.mem16(32983);
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
    int var10 = this.mem(32985);
    super.A = var10;
    int var11 = super.A | super.A;
    super.A = var11;
    super.F = super.A;
    if (super.F != 0) {
      super.B = super.A;
      int var12 = this.mem(32982);
      super.A = var12;
      int var13 = super.A | super.A;
      super.A = var13;
      super.F = super.A;
      if (super.F == 0) {
        int var33 = this.HL();
        int var34 = this.mem(var33);
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
        int var42 = this.mem(var41);
        super.C = var42;
        int var43 = super.C;
        int var44 = this.rrc(var43);
        super.C = var44;
        int var45 = super.C;
        int var46 = this.rrc(var45);
        super.C = var46;
      } else {
        int var14 = this.HL();
        int var15 = this.mem(var14);
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
        int var23 = this.mem(var22);
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
        this.wMem(var28, super.A);
        int var29 = this.HL();
        this.wMem(var29, super.C);
        int var30 = super.L + 1 & 255;
        super.L = var30;
        super.F = super.L;
        int var31 = super.E + 1 & 255;
        super.E = var31;
        super.F = super.E;
        int var32 = super.B + -1;
        super.B = var32;
      } while (super.B != 0);

    }
  }

  public void $38196() {
    int var1 = this.mem(33824);
    super.A = var1;
    int var2 = super.A - 35;
    super.F = var2;
    if (super.F == 0) {
      int var18 = this.mem(34271);
      super.A = var18;
      int var19 = super.A | super.A;
      super.A = var19;
      super.F = super.A;
      if (super.F == 0) {
        int var23 = this.mem(34251);
        super.A = var23;
        int var24 = super.A & 2;
        super.A = var24;
        super.F = super.A;
        int var25 = super.A;
        int var26 = this.rrc(var25);
        super.A = var26;
        int var27 = super.A;
        int var28 = this.rrc(var27);
        super.A = var28;
        int var29 = super.A;
        int var30 = this.rrc(var29);
        super.A = var30;
        int var31 = super.A;
        int var32 = this.rrc(var31);
        super.A = var32;
        int var33 = super.A | 128;
        super.A = var33;
        super.F = super.A;
        super.E = super.A;
        int var34 = this.mem(34255);
        super.A = var34;
        int var35 = super.A - 208;
        super.F = var35;
        if (super.F != 0) {
          super.E = 192;
          int var38 = super.A - 192;
          super.F = var38;
          if (super.F < 0) {
            super.E = 224;
          }
        }

        super.D = 156;
        this.HL(26734);
        super.C = 1;
        this.$37974();
        this.HL(17733);
        int var36 = this.HL();
        this.wMem16(23918, var36);
        this.HL(1799);
        int var37 = this.HL();
        this.wMem16(23950, var37);
      } else {
        int var20 = this.mem(34259);
        super.A = var20;
        int var21 = super.A & 31;
        super.A = var21;
        super.F = super.A;
        int var22 = super.A - 6;
        super.F = var22;
        if (super.F < 0) {
          super.A = 2;
          this.wMem(34271, super.A);
        }
      }
    } else {
      int var3 = this.mem(33824);
      super.A = var3;
      int var4 = super.A - 33;
      super.F = var4;
      if (super.F == 0) {
        int var5 = this.mem(34251);
        super.A = var5;
        int var6 = super.A & 1;
        super.A = var6;
        super.F = super.A;
        int var7 = super.A;
        int var8 = this.rrc(var7);
        super.A = var8;
        int var9 = super.A;
        int var10 = this.rrc(var9);
        super.A = var10;
        int var11 = super.A;
        int var12 = this.rrc(var11);
        super.A = var12;
        super.E = super.A;
        int var13 = this.mem(34271);
        super.A = var13;
        int var14 = super.A - 3;
        super.F = var14;
        if (super.F == 0) {
          int var17 = super.E | 64;
          super.E = var17;
        }

        super.D = 166;
        this.IX(33488);
        this.BC(4124);
        this.$38504();
        this.HL(1799);
        int var15 = this.HL();
        this.wMem16(23996, var15);
        int var16 = this.HL();
        this.wMem16(24028, var16);
      }
    }
  }

  public void $38344() {
    int var1 = this.mem16(34259);
    this.HL(var1);
    super.B = 0;
    int var2 = this.mem(32986);
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
    int var8 = this.mem(32964);
    super.A = var8;
    int var9 = this.HL();
    int var10 = this.mem(var9);
    int var11 = super.A - var10;
    super.F = var11;
    int var12 = this.HL();
    this.mem(var12);
    if (super.F == 0) {
      int var15 = this.mem(34257);
      super.A = var15;
      int var16 = super.A | super.A;
      super.A = var16;
      super.F = super.A;
      if (super.F == 0) {
        int var17 = this.mem(34258);
        super.A = var17;
        int var18 = super.A & 3;
        super.A = var18;
        super.F = super.A;
        int var19 = super.A;
        int var20 = this.rlc(var19);
        super.A = var20;
        int var21 = super.A;
        int var22 = this.rlc(var21);
        super.A = var22;
        super.B = super.A;
        int var23 = this.mem(32986);
        super.A = var23;
        int var24 = super.A & 1;
        super.A = var24;
        super.F = super.A;
        int var25 = super.A + -1 & 255;
        super.A = var25;
        super.F = super.A;
        int var26 = super.A ^ 12;
        super.A = var26;
        super.F = super.A;
        int var27 = super.A ^ super.B;
        super.A = var27;
        super.F = super.A;
        int var28 = super.A & 12;
        super.A = var28;
        super.F = super.A;
        super.B = super.A;
      }
    }

    int var13 = this.mem16(34259);
    this.HL(var13);
    this.DE(31);
    super.C = 15;
    this.$38430();
    int var14 = this.HL() + 1 & '\uffff';
    this.HL(var14);
  }

  public void $38430() {
    int var1 = this.mem(32928);
    super.A = var1;
    int var2 = this.HL();
    int var3 = this.mem(var2);
    int var4 = super.A - var3;
    super.F = var4;
    int var5 = this.HL();
    this.mem(var5);
    if (super.F == 0) {
      super.A = super.C;
      int var11 = super.A & 15;
      super.A = var11;
      super.F = super.A;
      if (super.F != 0) {
        int var12 = this.mem(32928);
        super.A = var12;
        int var13 = super.A | 7;
        super.A = var13;
        super.F = super.A;
        int var14 = this.HL();
        this.wMem(var14, super.A);
      }
    }

    int var6 = this.mem(32955);
    super.A = var6;
    int var7 = this.HL();
    int var8 = this.mem(var7);
    int var9 = super.A - var8;
    super.F = var9;
    int var10 = this.HL();
    this.mem(var10);
  }

  public void $38504() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1);
      super.A = var2;
      int var3 = this.IX() + 1;
      int var4 = this.mem(var3);
      super.H = var4;
      int var5 = super.A | super.C;
      super.A = var5;
      super.F = super.A;
      super.L = super.A;
      int var6 = this.DE();
      int var7 = this.mem(var6);
      super.A = var7;
      int var8 = this.HL();
      int var9 = this.mem(var8);
      int var10 = super.A | var9;
      super.A = var10;
      int var11 = this.HL();
      this.mem(var11);
      int var12 = this.HL();
      this.mem(var12);
      super.F = super.A;
      int var13 = this.HL();
      this.mem(var13);
      int var14 = this.HL();
      this.wMem(var14, super.A);
      int var15 = this.HL() + 1 & '\uffff';
      this.HL(var15);
      int var16 = this.DE() + 1 & '\uffff';
      this.DE(var16);
      int var17 = this.DE();
      int var18 = this.mem(var17);
      super.A = var18;
      int var19 = this.HL();
      int var20 = this.mem(var19);
      int var21 = super.A | var20;
      super.A = var21;
      int var22 = this.HL();
      this.mem(var22);
      int var23 = this.HL();
      this.mem(var23);
      super.F = super.A;
      int var24 = this.HL();
      this.mem(var24);
      int var25 = this.HL();
      this.wMem(var25, super.A);
      int var26 = this.IX() + 1 & '\uffff';
      this.IX(var26);
      int var27 = this.IX() + 1 & '\uffff';
      this.IX(var27);
      int var28 = this.DE() + 1 & '\uffff';
      this.DE(var28);
      int var29 = super.B + -1;
      super.B = var29;
    } while (super.B != 0);

  }

  public void $38528() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1);
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
      int var6 = super.C + -1 & 255;
      super.C = var6;
      super.F = super.C;
    } while (super.F != 0);

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

    do {
      int var5 = this.HL();
      int var6 = this.mem(var5);
      super.A = var6;
      int var7 = this.DE();
      this.wMem(var7, super.A);
      int var8 = this.HL() + 1 & '\uffff';
      this.HL(var8);
      int var9 = super.D + 1 & 255;
      super.D = var9;
      super.F = super.D;
      int var10 = super.B + -1;
      super.B = var10;
    } while (super.B != 0);

  }

  public void $38562() {
    while (true) {
      int var1 = this.HL();
      int var2 = this.mem(var1);
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
      int var6 = this.mem(var5);
      super.E = var6;
      super.D = super.E;

      while (true) {
        int var7 = super.D + -1 & 255;
        super.D = var7;
        super.F = super.D;
        if (super.F == 0) {
          super.D = super.E;
          int var12 = super.A ^ 24;
          super.A = var12;
          super.F = super.A;
        }

        int var8 = super.B + -1;
        super.B = var8;
        if (super.B == 0) {
          super.A = super.C;
          int var9 = super.A - 50;
          super.F = var9;
          if (super.F == 0) {
          }

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
    int var1 = this.mem(34254);
    super.A = var1;
    int var2 = super.A | super.A;
    super.A = var2;
    super.F = super.A;
    if (super.F != 0) {
      int var5 = super.A & 16;
      super.F = var5;
      if (super.F != 0) {
        return;
      }
    }

    this.BC(45054);
    int var3 = super.A & 1;
    super.A = var3;
    super.F = super.A;
    int var4 = super.A - 1;
    super.F = var4;
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
        int var3 = super.A - super.B;
        super.F = var3;
        if (super.F == 0) {
          super.D = 24;
        }

        int var4 = super.B + -1;
        super.B = var4;
      } while (super.B != 0);

      int var5 = super.A + -1 & 255;
      super.A = var5;
      super.F = super.A;
    } while (super.F != 0);

  }

  public void $38555() {
    do {
      super.A = this.mem(this.HL());
      this.wMem(this.DE(), super.A);
      this.HL(this.HL() + 1 & '\uffff');
      super.D = super.D + 1 & 255;
      super.F = super.D;
      super.B = super.B + -1;
    } while (super.B != 0);

  }

  public void $35211() {
  }

  public void $38064() {
  }

  public void $38276() {
  }
}
