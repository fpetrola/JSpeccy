package com.fpetrola.z80.bytecode;//

import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  public void $34463() {
    this.HL(16384);
    this.DE(16385);
    this.BC(6911);
    int var1 = this.HL();
    this.wMem(var1, 0);
    this.ldir();
    this.IX(34187);

    label145:
    while(true) {
      int var2 = super.A ^ super.A;
      super.A = var2;
      super.F = super.A;
      this.wMem(34254, super.A);
      this.wMem(34273, super.A);
      this.wMem(34253, super.A);
      this.wMem(34257, super.A);
      this.wMem(34251, super.A);
      this.wMem(34272, super.A);
      this.wMem(34271, super.A);
      super.A = 7;
      this.wMem(34252, super.A);
      super.A = 208;
      this.wMem(34255, super.A);
      super.A = 33;
      this.wMem(33824, super.A);
      this.HL(23988);
      int var3 = this.HL();
      this.wMem16(34259, var3);
      this.HL(34172);
      int var4 = this.HL();
      this.wMem(var4, 48);
      int var5 = this.HL() + 1;
      this.HL(var5);
      int var6 = this.HL();
      this.wMem(var6, 48);
      int var7 = this.HL() + 1;
      this.HL(var7);
      int var8 = this.HL();
      this.wMem(var8, 48);
      super.H = 164;
      int var9 = this.mem(41983);
      super.A = var9;
      super.L = super.A;
      this.wMem(34270, super.A);

      while(true) {
        int var10 = this.HL();
        int var11 = this.mem(var10) | 64;
        int var12 = this.HL();
        this.wMem(var12, var11);
        int var13 = super.L + 1;
        super.L = var13;
        if (super.F == 0) {
          this.HL(34274);
          int var14 = this.HL();
          int var15 = this.mem(var14) | 1;
          int var16 = this.HL();
          this.wMem(var16, var15);

          label111:
          while(true) {
            this.HL(16384);
            this.DE(16385);
            this.BC(6143);
            int var17 = this.HL();
            this.wMem(var17, 0);
            this.ldir();
            this.HL(38912);
            this.BC(768);
            this.ldir();
            this.HL(23136);
            this.DE(23137);
            this.BC(31);
            int var18 = this.HL();
            this.wMem(var18, 70);
            this.ldir();
            this.IX(33876);
            this.DE(20576);
            super.C = 32;
            this.$38528();
            this.DE(22528);

            do {
              int var19 = this.DE();
              int var20 = this.mem(var19);
              super.A = var20;
              int var21 = super.A | super.A;
              super.A = var21;
              super.F = super.A;
              if (super.F != 0) {
                int var136 = super.A - 211;
                super.F = var136;
                if (super.F != 0) {
                  int var137 = super.A - 9;
                  super.F = var137;
                  if (super.F != 0) {
                    int var138 = super.A - 45;
                    super.F = var138;
                    if (super.F != 0) {
                      int var139 = super.A - 36;
                      super.F = var139;
                      if (super.F != 0) {
                        super.C = 0;
                        int var140 = super.A - 8;
                        super.F = var140;
                        if (super.F != 0) {
                          int var153 = super.A - 41;
                          super.F = var153;
                          if (super.F != 0) {
                            int var154 = super.A - 44;
                            super.F = var154;
                            int var155 = super.A - 5;
                            super.F = var155;
                            if (super.F != 0) {
                              super.C = 16;
                            }
                          }
                        }

                        super.A = super.E;
                        int var141 = super.A & 1;
                        super.A = var141;
                        super.F = super.A;
                        int var142 = super.A;
                        int var143 = this.rlc(var142);
                        super.A = var143;
                        int var144 = super.A;
                        int var145 = this.rlc(var144);
                        super.A = var145;
                        int var146 = super.A;
                        int var147 = this.rlc(var146);
                        super.A = var147;
                        int var148 = super.A | super.C;
                        super.A = var148;
                        super.F = super.A;
                        super.C = super.A;
                        super.B = 0;
                        this.HL(33841);
                        int var149 = this.HL();
                        int var150 = this.BC();
                        int var151 = var149 + var150 & '\uffff';
                        this.HL(var151);
                        int var152 = super.D & 1;
                        super.F = var152;
                        super.D = 64;
                        if (super.F != 0) {
                          super.D = 72;
                        }

                        super.B = 8;
                        this.$38555();
                      }
                    }
                  }
                }
              }

              int var22 = this.DE() + 1;
              this.DE(var22);
              super.A = super.D;
              int var23 = super.A - 90;
              super.F = var23;
            } while(super.F != 0);

            this.BC(31);
            int var24 = super.A ^ super.A;
            super.A = var24;
            super.F = super.A;

            do {
              int var25 = super.A | super.E;
              super.A = var25;
              super.F = super.A;
              int var26 = super.B + -1;
              super.B = var26;
            } while(super.B != 0);

            int var27 = super.A & 32;
            super.A = var27;
            super.F = super.A;
            if (super.F == 0) {
              super.A = 1;
              this.wMem(34254, super.A);
            }

            this.HL(34299);
            this.$38562();
            if (super.F != 0) {
              break;
            }

            int var122 = super.A ^ super.A;
            super.A = var122;
            super.F = super.A;
            this.wMem(34276, super.A);

            while(true) {
              this.$35563();
              this.HL(23136);
              this.DE(23137);
              this.BC(31);
              int var123 = this.HL();
              this.wMem(var123, 79);
              this.ldir();
              int var124 = this.mem(34276);
              super.A = var124;
              this.IX(33876);
              super.E = super.A;
              super.D = 0;
              int var125 = this.IX();
              int var126 = this.DE();
              int var127 = var125 + var126 & '\uffff';
              this.IX(var127);
              this.DE(20576);
              super.C = 32;
              this.$38528();
              int var128 = this.mem(34276);
              super.A = var128;
              int var129 = super.A & 31;
              super.A = var129;
              super.F = super.A;
              int var130 = super.A + 50 & 255;
              super.A = var130;
              super.F = super.A;
              this.$38622();
              this.BC(45054);
              int var131 = super.A & 1;
              super.A = var131;
              super.F = super.A;
              int var132 = super.A - 1;
              super.F = var132;
              if (super.F != 0) {
                break label111;
              }

              int var133 = this.mem(34276);
              super.A = var133;
              int var134 = super.A + 1;
              super.A = var134;
              int var135 = super.A - 224;
              super.F = var135;
              this.wMem(34276, super.A);
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
          int var28 = this.mem(33824);
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
            int var31 = this.mem(var30);
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
            int var37 = this.mem(var36);
            super.C = var37;
            int var38 = this.HL();
            this.wMem(var38, super.C);
            this.BC(6);
            this.ldir();
            int var39 = this.IX() + 1;
            this.IX(var39);
            int var40 = this.IX() + 1;
            this.IX(var40);
            int var41 = super.A + -1;
            super.A = var41;
            super.F = super.A;
          } while(super.F != 0);

          this.HL(34255);
          this.DE(34263);
          this.BC(7);
          this.ldir();
          this.$36147();
          this.HL(20480);
          this.DE(20481);
          this.BC(2047);
          int var42 = this.HL();
          this.wMem(var42, 0);
          this.ldir();
          this.IX(32896);
          super.C = 32;
          this.DE(20480);
          this.$38528();
          this.IX(34132);
          this.DE(20576);
          super.C = 32;
          this.$38528();
          int var43 = this.mem(32990);
          super.A = var43;
          super.C = 254;
          int var44 = super.A ^ super.A;
          super.A = var44;
          super.F = super.A;
          this.wMem(34262, super.A);
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
          int var45 = this.mem(34271);
          super.A = var45;
          int var46 = super.A - 3;
          super.F = var46;
          if (super.F != 0) {
            this.$36307();
          }

          int var47 = this.mem(34255);
          super.A = var47;
          int var48 = super.A - 225;
          super.F = var48;
          if (super.F >= 0) {
            this.$38064();
          }

          int var49 = this.mem(34271);
          super.A = var49;
          int var50 = super.A - 3;
          super.F = var50;
          if (super.F != 0) {
            this.$38344();
          }

          int var51 = this.mem(34271);
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
          int var53 = this.mem(34271);
          super.A = var53;
          int var54 = super.A & 2;
          super.A = var54;
          super.F = super.A;
          int var55 = super.A;
          int var56 = this.rrc(var55);
          super.A = var56;
          this.HL(34258);
          int var57 = this.HL();
          int var58 = this.mem(var57);
          int var59 = super.A | var58;
          super.A = var59;
          int var60 = this.HL();
          this.mem(var60);
          int var61 = this.HL();
          this.mem(var61);
          super.F = super.A;
          int var62 = this.HL();
          this.mem(var62);
          int var63 = this.HL();
          this.wMem(var63, super.A);
          int var64 = this.mem(34253);
          super.A = var64;
          int var65 = super.A | super.A;
          super.A = var65;
          super.F = super.A;
          if (super.F != 0) {
            int var113 = super.A + -1;
            super.A = var113;
            super.F = super.A;
            this.wMem(34253, super.A);
            int var114 = super.A;
            int var115 = this.rlc(var114);
            super.A = var115;
            int var116 = super.A;
            int var117 = this.rlc(var116);
            super.A = var117;
            int var118 = super.A;
            int var119 = this.rlc(var118);
            super.A = var119;
            int var120 = super.A & 56;
            super.A = var120;
            super.F = super.A;
            this.HL(23552);
            this.DE(23553);
            this.BC(511);
            int var121 = this.HL();
            this.wMem(var121, super.A);
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
          int var66 = this.mem(34251);
          super.A = var66;
          int var67 = super.A + 1;
          super.A = var67;
          this.wMem(34251, super.A);
          if (super.F != 0) {
            break;
          }

          this.IX(34175);
          int var80 = this.IX() + 4;
          int var81 = this.mem(var80) + 1;
          this.wMem(var80, var81);
          int var82 = this.IX() + 4;
          int var83 = this.mem(var82);
          super.A = var83;
          int var84 = super.A - 58;
          super.F = var84;
          if (super.F != 0) {
            break;
          }

          int var85 = this.IX() + 4;
          this.wMem(var85, 48);
          int var86 = this.IX() + 3;
          int var87 = this.mem(var86) + 1;
          this.wMem(var86, var87);
          int var88 = this.IX() + 3;
          int var89 = this.mem(var88);
          super.A = var89;
          int var90 = super.A - 54;
          super.F = var90;
          if (super.F != 0) {
            break;
          }

          int var91 = this.IX() + 3;
          this.wMem(var91, 48);
          int var92 = this.IX();
          int var93 = this.mem(var92);
          super.A = var93;
          int var94 = super.A - 49;
          super.F = var94;
          if (super.F == 0) {
            int var102 = this.IX() + 1;
            int var103 = this.mem(var102) + 1;
            this.wMem(var102, var103);
            int var104 = this.IX() + 1;
            int var105 = this.mem(var104);
            super.A = var105;
            int var106 = super.A - 51;
            super.F = var106;
            if (super.F != 0) {
              break;
            }

            int var107 = this.IX() + 5;
            int var108 = this.mem(var107);
            super.A = var108;
            int var109 = super.A - 112;
            super.F = var109;
            if (super.F == 0) {
              continue label145;
            }

            int var110 = this.IX();
            this.wMem(var110, 32);
            int var111 = this.IX() + 1;
            this.wMem(var111, 49);
            int var112 = this.IX() + 5;
            this.wMem(var112, 112);
            break;
          }

          int var95 = this.IX() + 1;
          int var96 = this.mem(var95) + 1;
          this.wMem(var95, var96);
          int var97 = this.IX() + 1;
          int var98 = this.mem(var97);
          super.A = var98;
          int var99 = super.A - 58;
          super.F = var99;
          if (super.F == 0) {
            int var100 = this.IX() + 1;
            this.wMem(var100, 48);
            int var101 = this.IX();
            this.wMem(var101, 49);
          }
          break;
        }
      }

      this.BC(65278);
      super.E = super.A;
      super.B = 127;
      int var68 = super.A | super.E;
      super.A = var68;
      super.F = super.A;
      int var69 = super.A & 1;
      super.A = var69;
      super.F = super.A;
      if (super.F != 0) {
        int var70 = this.mem(34272);
        super.A = var70;
        int var71 = super.A + 1;
        super.A = var71;
        this.wMem(34272, super.A);
        if (super.F != 0) {
          super.B = 253;
          int var78 = super.A & 31;
          super.A = var78;
          super.F = super.A;
          int var79 = super.A - 31;
          super.F = var79;
          this.DE(0);
        }

        while(true) {
          super.B = 2;
          int var72 = super.A & 31;
          super.A = var72;
          super.F = super.A;
          int var73 = super.A - 31;
          super.F = var73;
          int var74 = super.E + 1;
          super.E = var74;
          if (super.F == 0) {
            int var75 = super.D + 1;
            super.D = var75;
            if (super.F == 0) {
              int var76 = this.mem(34275);
              super.A = var76;
              int var77 = super.A - 10;
              super.F = var77;
              if (super.F != 0) {
                this.$35563();
              }
            }
          }
        }
      }
    }
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
      int var14 = this.HL() + 1;
      this.HL(var14);
      super.A = super.H;
      int var15 = super.A - 91;
      super.F = var15;
    } while(super.F != 0);

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
  }

  public void $36171() {
    super.C = 0;

    do {
      super.E = super.C;
      int var1 = this.IX();
      int var2 = this.mem(var1);
      super.A = var2;
      this.HL(32928);
      this.BC(54);
      this.cpir();
      super.C = super.E;
      super.B = 8;
      super.D = 120;

      do {
        int var3 = this.HL();
        int var4 = this.mem(var3);
        super.A = var4;
        int var5 = this.DE();
        this.wMem(var5, super.A);
        int var6 = this.HL() + 1;
        this.HL(var6);
        int var7 = super.D + 1;
        super.D = var7;
        int var8 = super.B + -1;
        super.B = var8;
      } while(super.B != 0);

      int var9 = this.IX() + 1;
      this.IX(var9);
      int var10 = super.C + 1;
      super.C = var10;
    } while(super.F != 0);

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
    } while(super.F == 0);

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
      } while(super.B != 0);
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
    int var11 = this.mem(var10);
    super.A = var11;
    int var12 = this.IX();
    this.wMem(var12, super.A);
    int var13 = this.IX() + 1;
    this.IX(var13);
  }

  public void $36508() {
    int var1 = super.A & 240;
    super.A = var1;
    super.F = super.A;
    super.L = super.A;
    int var2 = super.A ^ super.A;
    super.A = var2;
    super.F = super.A;
    int var3 = super.A + 92 & 255;
    super.A = var3;
    super.F = super.A;
    super.H = super.A;
    int var4 = this.mem(34259);
    super.A = var4;
    int var5 = super.A & 31;
    super.A = var5;
    super.F = super.A;
    int var6 = super.A | super.L;
    super.A = var6;
    super.F = super.A;
    super.L = super.A;
    int var7 = this.HL();
    this.wMem16(34259, var7);
  }

  public void $37056() {
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

      int var4 = super.A & 3;
      super.A = var4;
      super.F = super.A;
      if (super.F != 0) {
        int var8 = super.A - 1;
        super.F = var8;
        if (super.F != 0) {
          int var43 = super.A - 2;
          super.F = var43;
          if (super.F != 0) {
            int var76 = this.IX();
            int var77 = this.mem(var76) & 128;
            super.F = var77;
            if (super.F != 0) {
              int var98 = this.IX() + 1;
              int var99 = this.mem(var98);
              super.A = var99;
              int var100 = super.A & 128;
              super.F = var100;
              if (super.F != 0) {
                int var104 = super.A - 2 & 255;
                super.A = var104;
                super.F = super.A;
                int var105 = super.A - 148;
                super.F = var105;
                if (super.F < 0) {
                  int var106 = super.A - 2 & 255;
                  super.A = var106;
                  super.F = super.A;
                  int var107 = super.A - 128;
                  super.F = var107;
                  if (super.F == 0) {
                    int var108 = super.A ^ super.A;
                    super.A = var108;
                    super.F = super.A;
                  }
                }
              } else {
                int var101 = super.A + 2 & 255;
                super.A = var101;
                super.F = super.A;
                int var102 = super.A - 18;
                super.F = var102;
                if (super.F < 0) {
                  int var103 = super.A + 2 & 255;
                  super.A = var103;
                  super.F = super.A;
                }
              }
            } else {
              int var78 = this.IX() + 1;
              int var79 = this.mem(var78);
              super.A = var79;
              int var80 = super.A & 128;
              super.F = var80;
              if (super.F == 0) {
                int var94 = super.A - 2 & 255;
                super.A = var94;
                super.F = super.A;
                int var95 = super.A - 20;
                super.F = var95;
                if (super.F < 0) {
                  int var96 = super.A - 2 & 255;
                  super.A = var96;
                  super.F = super.A;
                  int var97 = super.A | super.A;
                  super.A = var97;
                  super.F = super.A;
                  if (super.F == 0) {
                    super.A = 128;
                  }
                }
              } else {
                int var81 = super.A + 2 & 255;
                super.A = var81;
                super.F = super.A;
                int var82 = super.A - 146;
                super.F = var82;
                if (super.F < 0) {
                  int var93 = super.A + 2 & 255;
                  super.A = var93;
                  super.F = super.A;
                }
              }
            }

            int var83 = this.IX() + 1;
            this.wMem(var83, super.A);
            int var84 = super.A & 127;
            super.A = var84;
            super.F = super.A;
            int var85 = this.IX() + 7;
            int var86 = this.mem(var85);
            int var87 = super.A - var86;
            super.F = var87;
            int var88 = this.IX() + 7;
            this.mem(var88);
            if (super.F == 0) {
              int var89 = this.IX();
              int var90 = this.mem(var89);
              super.A = var90;
              int var91 = super.A ^ 128;
              super.A = var91;
              super.F = super.A;
              int var92 = this.IX();
              this.wMem(var92, super.A);
            }
          } else {
            label81: {
              int var44 = this.IX();
              int var45 = this.mem(var44);
              super.A = var45;
              int var46 = super.A ^ 8;
              super.A = var46;
              super.F = super.A;
              int var47 = this.IX();
              this.wMem(var47, super.A);
              int var48 = super.A & 24;
              super.A = var48;
              super.F = super.A;
              if (super.F != 0) {
                int var72 = this.IX();
                int var73 = this.mem(var72);
                super.A = var73;
                int var74 = super.A + 32 & 255;
                super.A = var74;
                super.F = super.A;
                int var75 = this.IX();
                this.wMem(var75, super.A);
              }

              int var49 = this.IX() + 3;
              int var50 = this.mem(var49);
              super.A = var50;
              int var51 = this.IX() + 4;
              int var52 = this.mem(var51);
              int var53 = super.A + var52 & 255;
              super.A = var53;
              int var54 = this.IX() + 4;
              this.mem(var54);
              int var55 = this.IX() + 4;
              this.mem(var55);
              super.F = super.A;
              int var56 = this.IX() + 4;
              this.mem(var56);
              int var57 = this.IX() + 3;
              this.wMem(var57, super.A);
              int var58 = this.IX() + 7;
              int var59 = this.mem(var58);
              int var60 = super.A - var59;
              super.F = var60;
              int var61 = this.IX() + 7;
              this.mem(var61);
              if (super.F < 0) {
                int var65 = this.IX() + 6;
                int var66 = this.mem(var65);
                int var67 = super.A - var66;
                super.F = var67;
                int var68 = this.IX() + 6;
                this.mem(var68);
                if (super.F != 0 && super.F >= 0) {
                  break label81;
                }

                int var69 = this.IX() + 6;
                int var70 = this.mem(var69);
                super.A = var70;
                int var71 = this.IX() + 3;
                this.wMem(var71, super.A);
              }

              int var62 = this.IX() + 4;
              int var63 = this.mem(var62);
              super.A = var63;
              int var109 = super.A * -1 & 255;
              int var64 = this.IX() + 4;
              this.wMem(var64, super.A);
            }
          }
        } else {
          int var9 = this.IX();
          int var10 = this.mem(var9) & 128;
          super.F = var10;
          if (super.F == 0) {
            int var27 = this.IX();
            int var28 = this.mem(var27);
            super.A = var28;
            int var29 = super.A - 32 & 255;
            super.A = var29;
            super.F = super.A;
            int var30 = super.A & 127;
            super.A = var30;
            super.F = super.A;
            int var31 = this.IX();
            this.wMem(var31, super.A);
            int var32 = super.A - 96;
            super.F = var32;
            if (super.F >= 0) {
              int var33 = this.IX() + 2;
              int var34 = this.mem(var33);
              super.A = var34;
              int var35 = super.A & 31;
              super.A = var35;
              super.F = super.A;
              int var36 = this.IX() + 6;
              int var37 = this.mem(var36);
              int var38 = super.A - var37;
              super.F = var38;
              int var39 = this.IX() + 6;
              this.mem(var39);
              if (super.F != 0) {
                int var41 = this.IX() + 2;
                int var42 = this.mem(var41) + -1;
                this.wMem(var41, var42);
                int var10000 = this.IX() + 2;
              } else {
                int var40 = this.IX();
                this.wMem(var40, 129);
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
          int var154 = super.A - 4;
          super.F = var154;
          if (super.F != 0) {
            int var215 = this.IX() + 3;
            int var216 = this.mem(var215);
            super.E = var216;
            super.D = 130;
            int var217 = this.DE();
            int var218 = this.mem(var217);
            super.A = var218;
            super.L = super.A;
            int var219 = this.IX() + 2;
            int var220 = this.mem(var219);
            super.A = var220;
            int var221 = super.A & 31;
            super.A = var221;
            super.F = super.A;
            int var222 = super.A + super.L & 255;
            super.A = var222;
            super.F = super.A;
            super.L = super.A;
            super.A = super.E;
            int var223 = super.A;
            int var224 = this.rlc(var223);
            super.A = var224;
            int var225 = super.A & 1;
            super.A = var225;
            super.F = super.A;
            int var226 = super.A | 92;
            super.A = var226;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var227 = this.IX() + 1;
            int var228 = this.mem(var227);
            super.A = var228;
            int var229 = super.A & 15;
            super.A = var229;
            super.F = super.A;
            int var230 = super.A + 56 & 255;
            super.A = var230;
            super.F = super.A;
            int var231 = super.A & 71;
            super.A = var231;
            super.F = super.A;
            super.C = super.A;
            int var232 = this.HL();
            int var233 = this.mem(var232);
            super.A = var233;
            int var234 = super.A & 56;
            super.A = var234;
            super.F = super.A;
            int var235 = super.A ^ super.C;
            super.A = var235;
            super.F = super.A;
            super.C = super.A;
            int var236 = this.HL();
            this.wMem(var236, super.C);
            int var237 = this.HL() + 1;
            this.HL(var237);
            int var238 = this.HL();
            this.wMem(var238, super.C);
            int var239 = this.HL();
            int var240 = this.DE();
            int var241 = var239 + var240 & '\uffff';
            this.HL(var241);
            int var242 = this.HL();
            this.wMem(var242, super.C);
            int var243 = this.HL() + 1;
            this.HL(var243);
            int var244 = this.HL();
            this.wMem(var244, super.C);
            int var245 = this.IX() + 3;
            int var246 = this.mem(var245);
            super.A = var246;
            int var247 = super.A & 14;
            super.A = var247;
            super.F = super.A;
            if (super.F != 0) {
              int var279 = this.HL();
              int var280 = this.DE();
              int var281 = var279 + var280 & '\uffff';
              this.HL(var281);
              int var282 = this.HL();
              this.wMem(var282, super.C);
              int var283 = this.HL() + 1;
              this.HL(var283);
              int var284 = this.HL();
              this.wMem(var284, super.C);
            }

            super.C = 1;
            int var248 = this.IX() + 1;
            int var249 = this.mem(var248);
            super.A = var249;
            int var250 = this.IX();
            int var251 = this.mem(var250);
            int var252 = super.A & var251;
            super.A = var252;
            int var253 = this.IX();
            this.mem(var253);
            int var254 = this.IX();
            this.mem(var254);
            super.F = super.A;
            int var255 = this.IX();
            this.mem(var255);
            int var256 = this.IX() + 2;
            int var257 = this.mem(var256);
            int var258 = super.A | var257;
            super.A = var258;
            int var259 = this.IX() + 2;
            this.mem(var259);
            int var260 = this.IX() + 2;
            this.mem(var260);
            super.F = super.A;
            int var261 = this.IX() + 2;
            this.mem(var261);
            int var262 = super.A & 224;
            super.A = var262;
            super.F = super.A;
            super.E = super.A;
            int var263 = this.IX() + 5;
            int var264 = this.mem(var263);
            super.D = var264;
            super.H = 130;
            int var265 = this.IX() + 3;
            int var266 = this.mem(var265);
            super.L = var266;
            int var267 = this.IX() + 2;
            int var268 = this.mem(var267);
            super.A = var268;
            int var269 = super.A & 31;
            super.A = var269;
            super.F = super.A;
            int var270 = this.HL();
            int var271 = this.mem(var270);
            int var272 = super.A | var271;
            super.A = var272;
            int var273 = this.HL();
            this.mem(var273);
            int var274 = this.HL();
            this.mem(var274);
            super.F = super.A;
            int var275 = this.HL();
            this.mem(var275);
            int var276 = this.HL() + 1;
            this.HL(var276);
            int var277 = this.HL();
            int var278 = this.mem(var277);
            super.H = var278;
            super.L = super.A;
            this.$37974();
          } else {
            int var155 = this.IX();
            int var156 = this.mem(var155) & 128;
            super.F = var156;
            if (super.F == 0) {
              int var213 = this.IX() + 4;
              int var214 = this.mem(var213) + -1;
              this.wMem(var213, var214);
              int var285 = this.IX() + 4;
              super.C = 44;
            } else {
              int var157 = this.IX() + 4;
              int var158 = this.mem(var157) + 1;
              this.wMem(var157, var158);
              super.C = 244;
            }

            int var159 = this.IX() + 4;
            int var160 = this.mem(var159);
            super.A = var160;
            int var161 = super.A - super.C;
            super.F = var161;
            if (super.F != 0) {
              int var162 = super.A & 224;
              super.A = var162;
              super.F = super.A;
              if (super.F == 0) {
                int var163 = this.IX() + 2;
                int var164 = this.mem(var163);
                super.E = var164;
                super.D = 130;
                int var165 = this.DE();
                int var166 = this.mem(var165);
                super.A = var166;
                int var167 = this.IX() + 4;
                int var168 = this.mem(var167);
                int var169 = super.A + var168 & 255;
                super.A = var169;
                int var170 = this.IX() + 4;
                this.mem(var170);
                int var171 = this.IX() + 4;
                this.mem(var171);
                super.F = super.A;
                int var172 = this.IX() + 4;
                this.mem(var172);
                super.L = super.A;
                super.A = super.E;
                int var173 = super.A & 128;
                super.A = var173;
                super.F = super.A;
                int var174 = super.A;
                int var175 = this.rlc(var174);
                super.A = var175;
                int var176 = super.A | 92;
                super.A = var176;
                super.F = super.A;
                super.H = super.A;
                int var177 = this.IX() + 5;
                this.wMem(var177, 0);
                int var178 = this.HL();
                int var179 = this.mem(var178);
                super.A = var179;
                int var180 = super.A & 7;
                super.A = var180;
                super.F = super.A;
                int var181 = super.A - 7;
                super.F = var181;
                if (super.F == 0) {
                  int var207 = this.IX() + 5;
                  int var208 = this.mem(var207) + -1;
                  this.wMem(var207, var208);
                  int var286 = this.IX() + 5;
                }

                int var182 = this.HL();
                int var183 = this.mem(var182);
                super.A = var183;
                int var184 = super.A | 7;
                super.A = var184;
                super.F = super.A;
                int var185 = this.HL();
                this.wMem(var185, super.A);
                int var186 = this.DE() + 1;
                this.DE(var186);
                int var187 = this.DE();
                int var188 = this.mem(var187);
                super.A = var188;
                super.H = super.A;
                int var189 = super.H + -1;
                super.H = var189;
                super.F = super.H;
                int var190 = this.IX() + 6;
                int var191 = this.mem(var190);
                super.A = var191;
                int var192 = this.HL();
                this.wMem(var192, super.A);
                int var193 = super.H + 1;
                super.H = var193;
                int var194 = this.HL();
                int var195 = this.mem(var194);
                super.A = var195;
                int var196 = this.IX() + 5;
                int var197 = this.mem(var196);
                int var198 = super.A & var197;
                super.A = var198;
                int var199 = this.IX() + 5;
                this.mem(var199);
                int var200 = this.IX() + 5;
                this.mem(var200);
                super.F = super.A;
                int var201 = this.IX() + 5;
                this.mem(var201);
                int var202 = this.HL();
                this.wMem(var202, 255);
                int var203 = super.H + 1;
                super.H = var203;
                int var204 = this.IX() + 6;
                int var205 = this.mem(var204);
                super.A = var205;
                int var206 = this.HL();
                this.wMem(var206, super.A);
              }
            } else {
              this.BC(640);
              int var209 = this.mem(32990);
              super.A = var209;

              do {
                int var210 = super.A ^ 24;
                super.A = var210;
                super.F = super.A;

                do {
                  int var211 = super.B + -1;
                  super.B = var211;
                } while(super.B != 0);

                super.B = super.C;
                int var212 = super.C + -1;
                super.C = var212;
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
                int var142 = this.IX() + 5;
                int var143 = this.mem(var142);
                super.A = var143;
                int var144 = this.HL();
                int var145 = this.mem(var144);
                int var146 = super.A & var145;
                super.A = var146;
                int var147 = this.HL();
                this.mem(var147);
                int var148 = this.HL();
                this.mem(var148);
                super.F = super.A;
                int var149 = this.HL();
                this.mem(var149);
                if (super.F == 0) {
                  break label107;
                }

                int var150 = this.IX() + 9;
                int var151 = this.mem(var150);
                super.A = var151;
                this.wMem(34262, super.A);
                int var152 = this.IX() + 11;
                int var153 = this.mem(var152) | 1;
                this.wMem(var152, var153);
              }

              int var26 = this.IX() + 9;
              int var27 = this.mem(var26);
              int var28 = super.A - var27;
              super.F = var28;
              int var29 = this.IX() + 9;
              this.mem(var29);
              if (super.F == 0) {
                int var130 = this.IX() + 11;
                int var131 = this.mem(var130) & 1;
                super.F = var131;
                if (super.F != 0) {
                  int var132 = this.IX() + 3;
                  int var133 = this.mem(var132);
                  super.B = var133;
                  int var134 = this.IX() + 5;
                  int var135 = this.mem(var134);
                  super.A = var135;
                  super.C = 1;
                  int var136 = super.A - 4;
                  super.F = var136;
                  if (super.F >= 0) {
                    super.C = 0;
                    int var139 = super.A - 16;
                    super.F = var139;
                    if (super.F >= 0) {
                      int var140 = super.B + -1;
                      super.B = var140;
                      super.F = super.B;
                      super.C = 3;
                      int var141 = super.A - 64;
                      super.F = var141;
                      if (super.F >= 0) {
                        super.C = 2;
                      }
                    }
                  }

                  int var137 = this.BC();
                  this.wMem16(34258, var137);
                  super.A = super.IYL;
                  int var138 = super.A - 16 & 255;
                  super.A = var138;
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
              int var112 = this.IX() + 1;
              int var113 = this.mem(var112) & 128;
              super.F = var113;
              if (super.F != 0) {
                do {
                  int var122 = this.IX() + 5;
                  int var123 = this.mem(var122);
                  int var124 = this.rlc(var123);
                  this.wMem(var122, var124);
                  int var125 = this.IX() + 5;
                  int var126 = this.mem(var125) & 1;
                  super.F = var126;
                  if (super.F != 0) {
                    int var128 = this.IX() + 3;
                    int var129 = this.mem(var128) + -1;
                    this.wMem(var128, var129);
                    int var10000 = this.IX() + 3;
                  }

                  int var127 = super.B + -1;
                  super.B = var127;
                } while(super.B != 0);
              } else {
                do {
                  int var114 = this.IX() + 5;
                  int var115 = this.mem(var114);
                  int var116 = this.rrc(var115);
                  this.wMem(var114, var116);
                  int var117 = this.IX() + 5;
                  int var118 = this.mem(var117) & 128;
                  super.F = var118;
                  if (super.F != 0) {
                    int var120 = this.IX() + 3;
                    int var121 = this.mem(var120) + 1;
                    this.wMem(var120, var121);
                  }

                  int var119 = super.B + -1;
                  super.B = var119;
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

            int var110 = this.IX() + 9;
            int var111 = this.mem(var110) + 1;
            this.wMem(var110, var111);
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
      int var4 = super.C & -129;
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
        int var15 = super.H + 1;
        super.H = var15;
        int var16 = this.HL();
        int var17 = this.mem(var16);
        super.E = var17;
        int var18 = super.H + -1;
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
          this.HL(32993);
          super.B = 8;
          this.$38555();
        } else {
          this.IX(34172);

          while(true) {
            int var44 = this.IX() + 2;
            int var45 = this.mem(var44) + 1;
            this.wMem(var44, var45);
            int var46 = this.IX() + 2;
            int var47 = this.mem(var46);
            super.A = var47;
            int var48 = super.A - 58;
            super.F = var48;
            if (super.F != 0) {
              int var49 = this.mem(32990);
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
                  int var52 = super.B + -1;
                  super.B = var52;
                } while(super.B != 0);

                int var53 = super.C + -1;
                super.C = var53;
                super.F = super.C;
                int var54 = super.C + -1;
                super.C = var54;
                super.F = super.C;
              } while(super.F != 0);

              int var55 = this.mem(34270);
              super.A = var55;
              int var56 = super.A + 1;
              super.A = var56;
              this.wMem(34270, super.A);
              if (super.F == 0) {
                super.A = 1;
                this.wMem(34271, super.A);
              }

              int var57 = this.HL();
              int var58 = this.mem(var57) & -65;
              int var59 = this.HL();
              this.wMem(var59, var58);
              break;
            }

            int var60 = this.IX() + 2;
            this.wMem(var60, 48);
          }
        }
      }

      int var8 = super.L + 1;
      super.L = var8;
    } while(super.F != 0);

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
      int var5 = super.L + 1;
      super.L = var5;
      int var6 = this.DE() + 1;
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
      int var11 = super.L + -1;
      super.L = var11;
      super.F = super.L;
      int var12 = super.H + 1;
      super.H = var12;
      int var13 = this.DE() + 1;
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
    } while(super.B != 0);

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
        int var39 = super.H + 1;
        super.H = var39;
        int var40 = super.H + 1;
        super.H = var40;
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
        int var20 = super.H + 1;
        super.H = var20;
        int var21 = super.H + 1;
        super.H = var21;
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
        int var30 = super.L + 1;
        super.L = var30;
        int var31 = super.E + 1;
        super.E = var31;
        int var32 = super.B + -1;
        super.B = var32;
      } while(super.B != 0);

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
        int var25 = super.A + -1;
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
    int var14 = this.HL() + 1;
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
      int var15 = this.HL() + 1;
      this.HL(var15);
      int var16 = this.DE() + 1;
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
      int var26 = this.IX() + 1;
      this.IX(var26);
      int var27 = this.IX() + 1;
      this.IX(var27);
      int var28 = this.DE() + 1;
      this.DE(var28);
      int var29 = super.B + -1;
      super.B = var29;
    } while(super.B != 0);

  }

  public void $38528() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1);
      super.A = var2;
      this.$38545();
      int var3 = this.IX() + 1;
      this.IX(var3);
      int var4 = super.E + 1;
      super.E = var4;
      super.A = super.D;
      int var5 = super.A - 8 & 255;
      super.A = var5;
      super.F = super.A;
      super.D = super.A;
      int var6 = super.C + -1;
      super.C = var6;
      super.F = super.C;
    } while(super.F != 0);

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
      int var8 = this.HL() + 1;
      this.HL(var8);
      int var9 = super.D + 1;
      super.D = var9;
      int var10 = super.B + -1;
      super.B = var10;
    } while(super.B != 0);

  }

  public void $38562() {
    while(true) {
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

      while(true) {
        int var7 = super.D + -1;
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

          int var10 = super.C + -1;
          super.C = var10;
          super.F = super.C;
          if (super.F == 0) {
            this.$38601();
            if (super.F != 0) {
              return;
            }

            int var11 = this.HL() + 1;
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
      } while(super.B != 0);

      int var5 = super.A + -1;
      super.A = var5;
      super.F = super.A;
    } while(super.F != 0);

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
