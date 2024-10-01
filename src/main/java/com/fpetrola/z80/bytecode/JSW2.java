package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.MiniZX;

public class JSW2 extends MiniZX {
  private static int initialRoom = 33;

  protected void customizeMemory() {
    this.mem[34795] = initialRoom;
  }

  @Override
  protected byte[] getProgramBytes() {
    return gzipDecompressFromBase64("");
  }

  public void $34762() {
    label295:
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
      } while (super.L != 0);

      this.HL(34274);
      int var13 = this.HL();
      int var14 = this.mem(var13, 34833) | 1;
      int var15 = this.HL();
      this.wMem(var15, var14, 34833);

      label287:
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
          if (super.A != 0 && super.A != 211 && super.A != 9 && super.A != 45 && super.A != 36) {
            super.C = 0;
            if (super.A != 8 && super.A != 41) {
              if (super.A != 44) {
                if (super.A != 5) {
                  super.C = 16;
                }
              } else {
                super.A = 37;
                int var307 = this.DE();
                this.wMem(var307, super.A, 34928);
              }
            }

            super.A = super.E;
            int var293 = super.A & 1;
            super.A = var293;
            super.F = super.A;
            int var294 = super.A;
            int var295 = this.rlc(var294);
            super.A = var295;
            int var296 = super.A;
            int var297 = this.rlc(var296);
            super.A = var297;
            int var298 = super.A;
            int var299 = this.rlc(var298);
            super.A = var299;
            int var300 = super.A | super.C;
            super.A = var300;
            super.F = super.A;
            super.C = super.A;
            super.B = 0;
            this.HL(33841);
            int var301 = this.HL();
            int var302 = this.BC();
            int var303 = var301 + var302 & '\uffff';
            this.HL(var303);
            int var304 = this.DE();
            this.push(var304);
            int var305 = super.D & 1;
            super.F = var305;
            super.D = 64;
            if (super.F != 0) {
              super.D = 72;
            }

            super.B = 8;
            this.$38555();
            int var306 = this.pop();
            this.DE(var306);
          }

          int var21 = this.DE() + 1 & '\uffff';
          this.DE(var21);
          super.A = super.D;
        } while (super.A != 90);

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
        } while (super.B != 0);

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

        int var277 = super.A ^ super.A;
        super.A = var277;
        super.F = super.A;
        this.wMem(34276, super.A, 34994);

        while (true) {
          this.$35563();
          this.HL(23136);
          this.DE(23137);
          this.BC(31);
          int var278 = this.HL();
          this.wMem(var278, 79, 35009);
          this.ldir();
          int var279 = this.mem(34276, 35013);
          super.A = var279;
          this.IX(33876);
          super.E = super.A;
          super.D = 0;
          int var280 = this.IX();
          int var281 = this.DE();
          int var282 = var280 + var281 & '\uffff';
          this.IX(var282);
          this.DE(20576);
          super.C = 32;
          this.$38528();
          int var283 = this.mem(34276, 35033);
          super.A = var283;
          int var284 = super.A & 31;
          super.A = var284;
          super.F = super.A;
          int var285 = super.A + 50;
          int var286 = var285 & 255;
          super.A = var286;
          super.F = var285;
          this.$38622();
          this.BC(45054);
          int var287 = this.BC();
          int var288 = this.in(var287);
          super.A = var288;
          int var289 = super.A & 1;
          super.A = var289;
          super.F = super.A;
          if (super.A != 1) {
            break label287;
          }

          int var290 = this.mem(34276, 35054);
          super.A = var290;
          int var291 = super.A + 1 & 255;
          super.A = var291;
          super.F = super.A;
          int var292 = super.A - 224;
          super.F = var292;
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
          int var41 = super.A - 1 & 255;
          super.A = var41;
        } while (super.A != 0);

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
          label306:
          {
            label226:
            {
              label302:
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
                if (super.A != 3) {
                  this.$36307();
                  if (this.isNextPC(37048)) {
                    break label302;
                  }

                  if (this.isNextPC(38043) || this.isNextPC(38061) || this.isNextPC(38134) || this.isNextPC(38095)) {
                    break;
                  }
                }

                int var46 = this.mem(34255, 35281);
                super.A = var46;
                if (super.A >= 225) {
                  this.$38064();
                  if (this.isNextPC(38095)) {
                    break;
                  }
                }

                int var47 = this.mem(34271, 35289);
                super.A = var47;
                if (super.A != 3) {
                  this.$38344();
                  if (this.isNextPC(37048)) {
                    break label302;
                  }
                }

                int var48 = this.mem(34271, 35297);
                super.A = var48;
                if (super.A == 2) {
                  this.$38276();
                }

                int var49 = super.A - 2;
                super.F = var49;
                this.$38196();
                if (!this.isNextPC(37048)) {
                  this.$37310();
                  if (!this.isNextPC(37048)) {
                    this.$38137();
                    this.$37841();
                    break label226;
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
            int var50 = this.mem(34271, 35328);
            super.A = var50;
            int var51 = super.A & 2;
            super.A = var51;
            super.F = super.A;
            int var52 = super.A;
            int var53 = this.rrc(var52);
            super.A = var53;
            this.HL(34258);
            int var54 = this.HL();
            int var55 = this.mem(var54, 35337);
            int var56 = super.A | var55;
            super.A = var56;
            super.F = super.A;
            int var57 = this.HL();
            this.wMem(var57, super.A, 35338);
            int var58 = this.mem(34253, 35339);
            super.A = var58;
            int var59 = super.A | super.A;
            super.A = var59;
            if (super.A != 0) {
              int var268 = super.A - 1 & 255;
              super.A = var268;
              super.F = super.A;
              this.wMem(34253, super.A, 35346);
              int var269 = super.A;
              int var270 = this.rlc(var269);
              super.A = var270;
              int var271 = super.A;
              int var272 = this.rlc(var271);
              super.A = var272;
              int var273 = super.A;
              int var274 = this.rlc(var273);
              super.A = var274;
              int var275 = super.A & 56;
              super.A = var275;
              super.F = super.A;
              this.HL(23552);
              this.DE(23553);
              this.BC(511);
              int var276 = this.HL();
              this.wMem(var276, super.A, 35363);
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
              int var241 = this.IX() + 4;
              int var242 = this.mem(var241, 35414) + 1 & 255;
              this.wMem(var241, var242, 35414);
              int var243 = this.IX() + 4;
              int var244 = this.mem(var243, 35417);
              super.A = var244;
              if (super.A == 58) {
                int var245 = this.IX() + 4;
                this.wMem(var245, 48, 35424);
                int var246 = this.IX() + 3;
                int var247 = this.mem(var246, 35428) + 1 & 255;
                this.wMem(var246, var247, 35428);
                int var248 = this.IX() + 3;
                int var249 = this.mem(var248, 35431);
                super.A = var249;
                if (super.A == 54) {
                  int var250 = this.IX() + 3;
                  this.wMem(var250, 48, 35438);
                  int var251 = this.IX();
                  int var252 = this.mem(var251, 35442);
                  super.A = var252;
                  if (super.A == 49) {
                    int var259 = this.IX() + 1;
                    int var260 = this.mem(var259, 35449) + 1 & 255;
                    this.wMem(var259, var260, 35449);
                    int var261 = this.IX() + 1;
                    int var262 = this.mem(var261, 35452);
                    super.A = var262;
                    if (super.A == 51) {
                      int var263 = this.IX() + 5;
                      int var264 = this.mem(var263, 35459);
                      super.A = var264;
                      if (super.A == 112) {
                        continue label295;
                      }

                      int var265 = this.IX();
                      this.wMem(var265, 32, 35467);
                      int var266 = this.IX() + 1;
                      this.wMem(var266, 49, 35471);
                      int var267 = this.IX() + 5;
                      this.wMem(var267, 112, 35475);
                    }
                  } else {
                    int var253 = this.IX() + 1;
                    int var254 = this.mem(var253, 35481) + 1 & 255;
                    this.wMem(var253, var254, 35481);
                    int var255 = this.IX() + 1;
                    int var256 = this.mem(var255, 35484);
                    super.A = var256;
                    if (super.A == 58) {
                      int var257 = this.IX() + 1;
                      this.wMem(var257, 48, 35491);
                      int var258 = this.IX();
                      this.wMem(var258, 49, 35495);
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
              continue label295;
            }

            int var68 = this.mem(34272, 35515);
            super.A = var68;
            int var69 = super.A + 1 & 255;
            super.A = var69;
            super.F = super.A;
            this.wMem(34272, super.A, 35519);
            if (super.F != 0) {
              super.B = 253;
              int var238 = this.BC();
              int var239 = this.in(var238);
              super.A = var239;
              int var240 = super.A & 31;
              super.A = var240;
              super.F = super.A;
              if (super.A == 31) {
                break label306;
              }

              this.DE(0);
            }

            while (true) {
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

              int var234 = super.E + 1 & 255;
              super.E = var234;
              if (super.E == 0) {
                int var235 = super.D + 1 & 255;
                super.D = var235;
                if (super.D == 0) {
                  int var236 = this.mem(34275, 35553);
                  super.A = var236;
                  if (super.A != 10) {
                    this.$35563();
                  }

                  int var237 = super.A - 10;
                  super.F = var237;
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
                } while (super.B != 0);

                int var95 = super.C - 1 & 255;
                super.C = var95;
              } while (super.C != 0);

              super.A = super.E;
              int var96 = super.A - 1 & 255;
              super.A = var96;
              super.F = super.A;
            } while (super.A != 63);

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
                int var109 = super.A - 32;
                int var110 = var109 & 255;
                super.A = var110;
                super.F = var109;
                super.H = super.A;
                this.DE(40000);
                super.C = 0;
                this.$37974();
                int var111 = this.mem(34276, 35976);
                super.A = var111;
                int var112 = ~super.A;
                super.A = var112;
                super.F = super.A;
                super.E = super.A;
                int var113 = super.A ^ super.A;
                super.A = var113;
                super.F = super.A;
                this.BC(64);

                do {
                  int var114 = super.A ^ 24;
                  super.A = var114;
                  super.F = super.A;
                  super.B = super.E;

                  do {
                    int var115 = super.B - 1 & 255;
                    super.B = var115;
                  } while (super.B != 0);

                  int var116 = super.C - 1 & 255;
                  super.C = var116;
                } while (super.C != 0);

                this.HL(22528);
                this.DE(22529);
                this.BC(511);
                int var117 = this.mem(34276, 36004);
                super.A = var117;
                int var118 = super.A & 12;
                super.A = var118;
                super.F = super.A;
                int var119 = super.A;
                int var120 = this.rlc(var119);
                super.A = var120;
                int var121 = super.A | 71;
                super.A = var121;
                super.F = super.A;
                int var122 = this.HL();
                this.wMem(var122, super.A, 36012);
                this.ldir();
                int var123 = super.A & 250;
                super.A = var123;
                super.F = super.A;
                int var124 = super.A | 2;
                super.A = var124;
                super.F = super.A;
                this.wMem(22991, super.A, 36019);
                this.wMem(22992, super.A, 36022);
                this.wMem(23023, super.A, 36025);
                this.wMem(23024, super.A, 36028);
                int var125 = this.mem(34276, 36031);
                super.A = var125;
                int var126 = super.A + 4;
                int var127 = var126 & 255;
                super.A = var127;
                super.F = var126;
                this.wMem(34276, super.A, 36036);
              } while (super.A != 196);

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

              while (true) {
                int var128 = super.B - 1 & 255;
                super.B = var128;
                if (super.B == 0) {
                  super.A = super.C;
                  int var129 = super.A & 7;
                  super.A = var129;
                  super.F = super.A;
                  int var130 = super.A | 64;
                  super.A = var130;
                  super.F = super.A;
                  this.wMem(22730, super.A, 36079);
                  int var131 = super.A + 1 & 255;
                  super.A = var131;
                  super.F = super.A;
                  int var132 = super.A & 7;
                  super.A = var132;
                  super.F = super.A;
                  int var133 = super.A | 64;
                  super.A = var133;
                  super.F = super.A;
                  this.wMem(22731, super.A, 36087);
                  int var134 = super.A + 1 & 255;
                  super.A = var134;
                  super.F = super.A;
                  int var135 = super.A & 7;
                  super.A = var135;
                  super.F = super.A;
                  int var136 = super.A | 64;
                  super.A = var136;
                  super.F = super.A;
                  this.wMem(22732, super.A, 36095);
                  int var137 = super.A + 1 & 255;
                  super.A = var137;
                  super.F = super.A;
                  int var138 = super.A & 7;
                  super.A = var138;
                  super.F = super.A;
                  int var139 = super.A | 64;
                  super.A = var139;
                  super.F = super.A;
                  this.wMem(22733, super.A, 36103);
                  int var140 = super.A + 1 & 255;
                  super.A = var140;
                  super.F = super.A;
                  int var141 = super.A & 7;
                  super.A = var141;
                  super.F = super.A;
                  int var142 = super.A | 64;
                  super.A = var142;
                  super.F = super.A;
                  this.wMem(22738, super.A, 36111);
                  int var143 = super.A + 1 & 255;
                  super.A = var143;
                  super.F = super.A;
                  int var144 = super.A & 7;
                  super.A = var144;
                  super.F = super.A;
                  int var145 = super.A | 64;
                  super.A = var145;
                  super.F = super.A;
                  this.wMem(22739, super.A, 36119);
                  int var146 = super.A + 1 & 255;
                  super.A = var146;
                  super.F = super.A;
                  int var147 = super.A & 7;
                  super.A = var147;
                  super.F = super.A;
                  int var148 = super.A | 64;
                  super.A = var148;
                  super.F = super.A;
                  this.wMem(22740, super.A, 36127);
                  int var149 = super.A + 1 & 255;
                  super.A = var149;
                  super.F = super.A;
                  int var150 = super.A & 7;
                  super.A = var150;
                  super.F = super.A;
                  int var151 = super.A | 64;
                  super.A = var151;
                  super.F = super.A;
                  this.wMem(22741, super.A, 36135);
                  int var152 = super.C - 1 & 255;
                  super.C = var152;
                  if (super.C == 0) {
                    int var153 = super.D - 1 & 255;
                    super.D = var153;
                    if (super.D == 0) {
                      continue label295;
                    }
                  }
                }
              }
            }

            int var154 = this.HL();
            int var155 = this.mem(var154, 35899) - 1 & 255;
            int var156 = this.HL();
            this.wMem(var156, var155, 35899);
            this.HL(34263);
            this.DE(34255);
            this.BC(7);
            this.ldir();
            break;
          }

          super.B = 191;
          this.HL(34274);
          int var157 = this.BC();
          int var158 = this.in(var157);
          super.A = var158;
          int var159 = super.A & 31;
          super.A = var159;
          super.F = super.A;
          if (super.A != 31) {
            int var229 = this.HL();
            if ((this.mem(var229, 35628) & 1) == 0) {
              int var230 = this.HL();
              int var231 = this.mem(var230, 35632);
              super.A = var231;
              int var232 = super.A ^ 3;
              super.A = var232;
              super.F = super.A;
              int var233 = this.HL();
              this.wMem(var233, super.A, 35635);
            }
          } else {
            int var160 = this.HL();
            int var161 = this.mem(var160, 35638) & -2;
            int var162 = this.HL();
            this.wMem(var162, var161, 35638);
          }

          int var163 = this.HL();
          if ((this.mem(var163, 35640) & 2) == 0) {
            int var203 = super.A ^ super.A;
            super.A = var203;
            super.F = super.A;
            this.wMem(34272, super.A, 35645);
            int var204 = this.mem(34273, 35648);
            super.A = var204;
            int var205 = super.A + 1 & 255;
            super.A = var205;
            super.F = super.A;
            this.wMem(34273, super.A, 35652);
            int var206 = super.A & 126;
            super.A = var206;
            super.F = super.A;
            int var207 = super.A;
            int var208 = this.rrc(var207);
            super.A = var208;
            super.E = super.A;
            super.D = 0;
            this.HL(34399);
            int var209 = this.HL();
            int var210 = this.DE();
            int var211 = var209 + var210 & '\uffff';
            this.HL(var211);
            int var212 = this.mem(34252, 35665);
            super.A = var212;
            int var213 = super.A;
            int var214 = this.rlc(var213);
            super.A = var214;
            int var215 = super.A;
            int var216 = this.rlc(var215);
            super.A = var216;
            int var217 = super.A - 28;
            int var218 = var217 & 255;
            super.A = var218;
            super.F = var217;
            int var219 = -super.A & 255;
            super.A = var219;
            int var220 = this.HL();
            int var221 = this.mem(var220, 35674);
            int var222 = super.A + var221;
            int var223 = var222 & 255;
            super.A = var223;
            super.F = var222;
            super.D = super.A;
            int var224 = this.mem(32990, 35676);
            super.A = var224;
            super.E = super.D;
            this.BC(3);

            while (true) {
              int var225 = super.E - 1 & 255;
              super.E = var225;
              if (super.E == 0) {
                super.E = super.D;
                int var228 = super.A ^ 24;
                super.A = var228;
              }

              int var226 = super.B - 1 & 255;
              super.B = var226;
              if (super.B == 0) {
                int var227 = super.C - 1 & 255;
                super.C = var227;
                if (super.C == 0) {
                  break;
                }
              }
            }
          }

          this.BC(61438);
          int var164 = this.BC();
          int var165 = this.in(var164);
          super.A = var165;
          if ((super.A & 2) == 0) {
            int var193 = super.A & 16;
            super.A = var193;
            super.F = super.A;
            int var194 = super.A ^ 16;
            super.A = var194;
            super.F = super.A;
            int var195 = super.A;
            int var196 = this.rlc(var195);
            super.A = var196;
            super.D = super.A;
            int var197 = this.mem(34275, 35712);
            super.A = var197;
            if (super.A == 10) {
              this.BC(63486);
              int var198 = this.BC();
              int var199 = this.in(var198);
              super.A = var199;
              int var200 = ~super.A;
              super.A = var200;
              super.F = super.A;
              int var201 = super.A & 31;
              super.A = var201;
              super.F = super.A;
              int var202 = super.A | super.D;
              super.A = var202;
              super.F = super.A;
              this.wMem(33824, super.A, 35729);
              break;
            }
          }

          int var166 = this.mem(34275, 35735);
          super.A = var166;
          if (super.A != 10) {
            int var167 = this.mem(33824, 35743);
            super.A = var167;
            if (super.A == 28) {
              int var168 = this.mem(34255, 35751);
              super.A = var168;
              if (super.A == 208) {
                int var169 = this.mem(34275, 35759);
                super.A = var169;
                int var170 = super.A;
                int var171 = this.rlc(var170);
                super.A = var171;
                super.E = super.A;
                super.D = 0;
                this.IX(34279);
                int var172 = this.IX();
                int var173 = this.DE();
                int var174 = var172 + var173 & '\uffff';
                this.IX(var174);
                this.BC(64510);
                int var175 = this.BC();
                int var176 = this.in(var175);
                super.A = var176;
                int var177 = super.A & 31;
                super.A = var177;
                super.F = super.A;
                int var178 = this.IX();
                int var179 = this.mem(var178, 35779);
                if (super.A != var179) {
                  if (super.A != 31) {
                    int var190 = this.IX();
                    int var191 = this.mem(var190, 35789);
                    if (super.A != var191) {
                      int var192 = super.A ^ super.A;
                      super.A = var192;
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
                  int var184 = this.mem(var183, 35808);
                  if (super.A != var184) {
                    if (super.A != 31) {
                      int var187 = this.IX();
                      int var188 = this.mem(var187, 35818);
                      if (super.A != var188) {
                        int var189 = super.A ^ super.A;
                        super.A = var189;
                        super.F = super.A;
                        this.wMem(34275, super.A, 35825);
                      }
                    }
                  } else {
                    int var185 = this.mem(34275, 35831);
                    super.A = var185;
                    int var186 = super.A + 1 & 255;
                    super.A = var186;
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
      } while (super.B != 0);

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
      int var6 = super.A + 3;
      int var7 = var6 & 255;
      super.A = var7;
      super.F = var6;
      int var8 = super.A & 7;
      super.A = var8;
      super.F = super.A;
      super.D = super.A;
      int var9 = this.HL();
      int var10 = this.mem(var9, 35577);
      super.A = var10;
      int var11 = super.A + 24;
      int var12 = var11 & 255;
      super.A = var12;
      super.F = var11;
      int var13 = super.A & 184;
      super.A = var13;
      super.F = super.A;
      int var14 = super.A | super.D;
      super.A = var14;
      super.F = super.A;
      int var15 = this.HL();
      this.wMem(var15, super.A, 35583);
      int var16 = this.HL() + 1 & '\uffff';
      this.HL(var16);
      super.A = super.H;
    } while (super.A != 91);

    int var17 = super.A - 91;
    super.F = var17;
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
      super.D = mem[36189];

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
      } while (super.B != 0);

      int var9 = this.IX() + 1 & '\uffff';
      this.IX(var9);
      int var10 = super.C + 1 & 255;
      super.C = var10;
    } while (super.C != 0);

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
    } while (super.A == 0);

    int var27 = this.mem(32985, 36240);
    super.A = var27;
    int var28 = super.A | super.A;
    super.A = var28;
    if (super.A != 0) {
      int var45 = this.mem16(32983, 36246);
      this.HL(var45);
      super.B = super.A;
      int var46 = this.mem(32973, 36250);
      super.A = var46;

      do {
        int var47 = this.HL();
        this.wMem(var47, super.A, 36253);
        int var48 = this.HL() + 1 & '\uffff';
        this.HL(var48);
        int var49 = super.B - 1 & 255;
        super.B = var49;
      } while (super.B != 0);
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
      int var36 = super.A + 223;
      int var37 = var36 & 255;
      super.A = var37;
      super.F = var36;
      super.E = super.A;
      super.D = 255;
      int var38 = this.mem(32989, 36276);
      super.A = var38;
      super.B = super.A;
      int var39 = this.mem(32964, 36280);
      super.A = var39;

      do {
        int var40 = this.HL();
        this.wMem(var40, super.A, 36283);
        int var41 = this.HL();
        int var42 = this.DE();
        int var43 = var41 + var42 & '\uffff';
        this.HL(var43);
        int var44 = super.B - 1 & 255;
        super.B = var44;
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
    int var8 = super.A + super.C;
    int var9 = var8 & 255;
    super.A = var9;
    super.F = var8;
    int var10 = super.A + 160;
    int var11 = var10 & 255;
    super.A = var11;
    super.F = var10;
    super.E = super.A;
    super.D = 128;
    int var12 = this.DE();
    int var13 = this.mem(var12, 36300);
    super.A = var13;
    int var14 = this.IX();
    this.wMem(var14, super.A, 36301);
    int var15 = this.IX() + 1 & '\uffff';
    this.IX(var15);
  }

  public void $36307() {
    label216:
    {
      label213:
      {
        label228:
        {
          int var1 = this.mem(34262, 36307);
          super.A = var1;
          int var2 = super.A - 1 & 255;
          super.A = var2;
          super.F = super.A;
          if ((super.A & 128) != 0) {
            int var209 = this.mem(34257, 36316);
            super.A = var209;
            if (super.A == 1) {
              int var257 = this.mem(34261, 36323);
              super.A = var257;
              int var258 = super.A & 254;
              super.A = var258;
              super.F = super.A;
              int var259 = super.A - 8;
              int var260 = var259 & 255;
              super.A = var260;
              super.F = var259;
              this.HL(34255);
              int var261 = this.HL();
              int var262 = this.mem(var261, 36333);
              int var263 = super.A + var262;
              int var264 = var263 & 255;
              super.A = var264;
              super.F = var263;
              int var265 = this.HL();
              this.wMem(var265, super.A, 36334);
              if (super.A >= 240) {
                return;
              }

              int var266 = super.A - 240;
              super.F = var266;
              this.$36508();
              int var267 = this.mem(32946, 36343);
              super.A = var267;
              int var268 = this.HL();
              int var269 = this.mem(var268, 36346);
              if (super.A == var269) {
                break label216;
              }

              int var277 = this.HL() + 1 & '\uffff';
              this.HL(var277);
              int var278 = this.HL();
              int var279 = this.mem(var278, 36351);
              if (super.A == var279) {
                break label216;
              }

              int var280 = this.mem(34261, 36355);
              super.A = var280;
              int var281 = super.A + 1 & 255;
              super.A = var281;
              super.F = super.A;
              this.wMem(34261, super.A, 36359);
              int var282 = super.A - 8;
              int var283 = var282 & 255;
              super.A = var283;
              if (var282 < 0) {
                int var296 = -super.A & 255;
                super.A = var296;
              }

              int var284 = super.A + 1 & 255;
              super.A = var284;
              super.F = super.A;
              int var285 = super.A;
              int var286 = this.rlc(var285);
              super.A = var286;
              int var287 = super.A;
              int var288 = this.rlc(var287);
              super.A = var288;
              int var289 = super.A;
              int var290 = this.rlc(var289);
              super.A = var290;
              super.D = super.A;
              super.C = 32;
              int var291 = this.mem(32990, 36376);
              super.A = var291;

              do {
                int var292 = super.A ^ 24;
                super.A = var292;
                super.F = super.A;
                super.B = super.D;

                do {
                  int var293 = super.B - 1 & 255;
                  super.B = var293;
                } while (super.B != 0);

                int var294 = super.C - 1 & 255;
                super.C = var294;
              } while (super.C != 0);

              int var295 = this.mem(34261, 36389);
              super.A = var295;
              if (super.A == 18) {
                super.A = 6;
                this.wMem(34257, super.A, 36530);
                return;
              }

              if (super.A != 16 && super.A != 13) {
                break label213;
              }
            }

            int var210 = this.mem(34255, 36406);
            super.A = var210;
            int var211 = super.A & 14;
            super.A = var211;
            if (super.A != 0) {
              break label228;
            }

            int var234 = this.mem16(34259, 36413);
            this.HL(var234);
            this.DE(64);
            int var235 = this.HL();
            int var236 = this.DE();
            int var237 = var235 + var236 & '\uffff';
            this.HL(var237);
            if ((super.H & 2) != 0) {
              int var238 = this.mem(33004, 38098);
              super.A = var238;
              this.wMem(33824, super.A, 38101);
              int var239 = super.A ^ super.A;
              super.A = var239;
              super.F = super.A;
              this.wMem(34255, super.A, 38105);
              int var240 = this.mem(34257, 38108);
              super.A = var240;
              if (super.A < 11) {
                super.A = 2;
                this.wMem(34257, super.A, 38117);
              }

              int var241 = this.mem(34259, 38120);
              super.A = var241;
              int var242 = super.A & 31;
              super.A = var242;
              super.F = super.A;
              this.wMem(34259, super.A, 38125);
              super.A = 92;
              this.wMem(34260, super.A, 38130);
              super.nextAddress = 38134;
              return;
            }

            int var243 = this.mem(32955, 36425);
            super.A = var243;
            int var244 = this.HL();
            int var245 = this.mem(var244, 36428);
            if (super.A == var245) {
              break label228;
            }

            int var246 = this.HL() + 1 & '\uffff';
            this.HL(var246);
            int var247 = this.mem(32955, 36432);
            super.A = var247;
            int var248 = this.HL();
            int var249 = this.mem(var248, 36435);
            if (super.A == var249) {
              break label228;
            }

            int var250 = this.mem(32928, 36438);
            super.A = var250;
            int var251 = this.HL();
            int var252 = this.mem(var251, 36441);
            int var253 = super.A - var252;
            super.F = var253;
            int var254 = this.HL() - 1 & '\uffff';
            this.HL(var254);
            if (super.F == 0) {
              int var255 = this.HL();
              int var256 = this.mem(var255, 36446);
              if (super.A == var256) {
                break label228;
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
            label227:
            {
              int var198 = this.mem(34257, 36574);
              super.A = var198;
              if (super.A >= 12) {
                super.nextAddress = 37048;
                return;
              }

              int var199 = super.A ^ super.A;
              super.A = var199;
              super.F = super.A;
              this.wMem(34257, super.A, 36583);
              int var200 = this.mem(32973, 36586);
              super.A = var200;
              int var201 = this.HL();
              int var202 = this.mem(var201, 36589);
              if (super.A != var202) {
                int var206 = this.HL() + 1 & '\uffff';
                this.HL(var206);
                int var207 = this.HL();
                int var208 = this.mem(var207, 36593);
                if (super.A != var208) {
                  break label227;
                }
              }

              int var203 = this.mem(32982, 36596);
              super.A = var203;
              int var204 = super.A - 3;
              int var205 = var204 & 255;
              super.A = var205;
              super.F = var204;
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
            int var193 = this.BC();
            int var194 = this.in(var193);
            super.A = var194;
            int var195 = super.A & 3;
            super.A = var195;
            super.F = super.A;
            int var196 = ~super.A;
            super.A = var196;
            super.F = super.A;
            int var197 = super.A & super.E;
            super.A = var197;
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
            int var192 = super.A ^ super.A;
            super.A = var192;
            super.F = super.A;
            this.wMem(34272, super.A, 36686);
          }

          super.A = super.E;
          int var41 = super.A & 21;
          super.A = var41;
          super.F = super.A;
          if (super.A != 21) {
            int var190 = super.C | 8;
            super.C = var190;
            int var191 = super.A ^ super.A;
            super.A = var191;
            super.F = super.A;
            this.wMem(34272, super.A, 36699);
          }

          int var42 = this.mem(34256, 36702);
          super.A = var42;
          int var43 = super.A + super.C;
          int var44 = var43 & 255;
          super.A = var44;
          super.F = var43;
          super.C = super.A;
          super.B = 0;
          this.HL(33825);
          int var45 = this.HL();
          int var46 = this.BC();
          int var47 = var45 + var46 & '\uffff';
          this.HL(var47);
          int var48 = this.HL();
          int var49 = this.mem(var48, 36713);
          super.A = var49;
          this.wMem(34256, super.A, 36714);
          this.BC(32510);
          int var50 = this.BC();
          int var51 = this.in(var50);
          super.A = var51;
          int var52 = super.A & 31;
          super.A = var52;
          super.F = super.A;
          if (super.A == 31) {
            super.B = 239;
            int var184 = this.BC();
            int var185 = this.in(var184);
            super.A = var185;
            if ((super.A & 1) != 0) {
              int var186 = this.mem(34254, 36736);
              super.A = var186;
              int var187 = super.A | super.A;
              super.A = var187;
              if (super.A == 0) {
                break label213;
              }

              this.BC(31);
              int var188 = this.BC();
              int var189 = this.in(var188);
              super.A = var189;
              if ((super.A & 16) == 0) {
                break label213;
              }
            }
          }

          int var53 = this.mem(34271, 36751);
          super.A = var53;
          if ((super.A & 2) == 0) {
            int var175 = super.A ^ super.A;
            super.A = var175;
            super.F = super.A;
            this.wMem(34261, super.A, 36759);
            this.wMem(34272, super.A, 36762);
            int var176 = super.A + 1 & 255;
            super.A = var176;
            super.F = super.A;
            this.wMem(34257, super.A, 36766);
            int var177 = this.mem(34262, 36769);
            super.A = var177;
            int var178 = super.A - 1 & 255;
            super.A = var178;
            super.F = super.A;
            if ((super.A & 128) == 0) {
              super.A = 240;
              this.wMem(34262, super.A, 36779);
              int var179 = this.mem(34255, 36782);
              super.A = var179;
              int var180 = super.A & 240;
              super.A = var180;
              super.F = super.A;
              this.wMem(34255, super.A, 36787);
              this.HL(34256);
              int var181 = this.HL();
              int var182 = this.mem(var181, 36793) | 2;
              int var183 = this.HL();
              this.wMem(var183, var182, 36793);
              return;
            }
          }
          break label213;
        }

        int var212 = this.mem(34257, 36450);
        super.A = var212;
        if (super.A != 1) {
          this.HL(34256);
          int var213 = this.HL();
          int var214 = this.mem(var213, 36461) & -3;
          int var215 = this.HL();
          this.wMem(var215, var214, 36461);
          int var216 = this.mem(34257, 36463);
          super.A = var216;
          int var217 = super.A | super.A;
          super.A = var217;
          if (super.A == 0) {
            super.A = 2;
            this.wMem(34257, super.A, 36536);
            return;
          }

          int var218 = super.A + 1 & 255;
          super.A = var218;
          super.F = super.A;
          if (super.A == 16) {
            super.A = 12;
          }

          this.wMem(34257, super.A, 36477);
          int var219 = super.A;
          int var220 = this.rlc(var219);
          super.A = var220;
          int var221 = super.A;
          int var222 = this.rlc(var221);
          super.A = var222;
          int var223 = super.A;
          int var224 = this.rlc(var223);
          super.A = var224;
          int var225 = super.A;
          int var226 = this.rlc(var225);
          super.A = var226;
          super.D = super.A;
          super.C = 32;
          int var227 = this.mem(32990, 36487);
          super.A = var227;

          do {
            int var228 = super.A ^ 24;
            super.A = var228;
            super.F = super.A;
            super.B = super.D;

            do {
              int var229 = super.B - 1 & 255;
              super.B = var229;
            } while (super.B != 0);

            int var230 = super.C - 1 & 255;
            super.C = var230;
          } while (super.C != 0);

          int var231 = this.mem(34255, 36500);
          super.A = var231;
          int var232 = super.A + 8;
          int var233 = var232 & 255;
          super.A = var233;
          super.F = var232;
          this.wMem(34255, super.A, 36505);
          this.$36508();
          return;
        }
      }

      int var54 = this.mem(34256, 36796);
      super.A = var54;
      int var55 = super.A & 2;
      super.A = var55;
      if (super.A == 0) {
        return;
      }

      int var56 = this.mem(34262, 36802);
      super.A = var56;
      int var57 = super.A - 1 & 255;
      super.A = var57;
      super.F = super.A;
      if ((super.A & 128) == 0) {
        return;
      }

      int var58 = this.mem(34256, 36809);
      super.A = var58;
      int var59 = super.A & 1;
      super.A = var59;
      if (super.A != 0) {
        int var120 = this.mem(34258, 36817);
        super.A = var120;
        int var121 = super.A | super.A;
        super.A = var121;
        if (super.A != 0) {
          int var174 = super.A - 1 & 255;
          super.A = var174;
          super.F = super.A;
          this.wMem(34258, super.A, 36824);
          return;
        }

        int var122 = this.mem(34257, 36828);
        super.A = var122;
        this.BC(0);
        if (super.A == 0) {
          int var161 = this.mem16(34259, 36838);
          this.HL(var161);
          this.BC(0);
          int var162 = this.mem(32986, 36844);
          super.A = var162;
          int var163 = super.A - 1 & 255;
          super.A = var163;
          super.F = super.A;
          int var164 = super.A | 161;
          super.A = var164;
          super.F = super.A;
          int var165 = super.A ^ 224;
          super.A = var165;
          super.F = super.A;
          super.E = super.A;
          super.D = 0;
          int var166 = this.HL();
          int var167 = this.DE();
          int var168 = var166 + var167 & '\uffff';
          this.HL(var168);
          int var169 = this.mem(32964, 36856);
          super.A = var169;
          int var170 = this.HL();
          int var171 = this.mem(var170, 36859);
          if (super.A == var171) {
            this.BC(32);
            int var172 = this.mem(32986, 36865);
            super.A = var172;
            int var173 = super.A | super.A;
            super.A = var173;
            if (super.A == 0) {
              this.BC(65504);
            }
          }
        }

        int var123 = this.mem16(34259, 36874);
        this.HL(var123);
        super.A = super.L;
        int var124 = super.A & 31;
        super.A = var124;
        if (super.A != 0) {
          int var129 = this.HL();
          int var130 = this.BC();
          int var131 = var129 + var130 & '\uffff';
          this.HL(var131);
          int var132 = this.HL() - 1 & '\uffff';
          this.HL(var132);
          this.DE(32);
          int var133 = this.HL();
          int var134 = this.DE();
          int var135 = var133 + var134 & '\uffff';
          this.HL(var135);
          int var136 = this.mem(32946, 36889);
          super.A = var136;
          int var137 = this.HL();
          int var138 = this.mem(var137, 36892);
          if (super.A == var138) {
            return;
          }

          int var139 = this.mem(34255, 36894);
          super.A = var139;
          int var140 = super.C >> 1;
          int var141 = super.C & 128;
          int var142 = var140 | var141;
          super.C = var142;
          int var143 = super.A + super.C;
          int var144 = var143 & 255;
          super.A = var144;
          super.F = var143;
          super.B = super.A;
          int var145 = super.A & 15;
          super.A = var145;
          if (super.A != 0) {
            int var151 = this.mem(32946, 36905);
            super.A = var151;
            int var152 = this.HL();
            int var153 = this.DE();
            int var154 = var152 + var153 & '\uffff';
            this.HL(var154);
            int var155 = this.HL();
            int var156 = this.mem(var155, 36909);
            if (super.A == var156) {
              return;
            }

            int var157 = super.A | super.A;
            super.A = var157;
            super.F = super.A;
            int var158 = this.HL();
            int var159 = this.DE();
            int var160 = var158 - var159 & '\uffff';
            this.HL(var160);
          }

          int var146 = super.A | super.A;
          super.A = var146;
          super.F = super.A;
          int var147 = this.HL();
          int var148 = this.DE();
          int var149 = var147 - var148 & '\uffff';
          this.HL(var149);
          int var150 = this.HL();
          this.wMem16(34259, var150, 36917);
          super.A = super.B;
          this.wMem(34255, super.A, 36921);
          super.A = 3;
          this.wMem(34258, super.A, 36926);
          return;
        }

        int var125 = this.mem(33001, 38026);
        super.A = var125;
        this.wMem(33824, super.A, 38029);
        int var126 = this.mem(34259, 38032);
        super.A = var126;
        int var127 = super.A | 31;
        super.A = var127;
        super.F = super.A;
        int var128 = super.A & 254;
        super.A = var128;
        super.F = super.A;
        this.wMem(34259, super.A, 38039);
        super.nextAddress = 38043;
        return;
      }

      int var60 = this.mem(34258, 36930);
      super.A = var60;
      if (super.A != 3) {
        int var119 = super.A + 1 & 255;
        super.A = var119;
        super.F = super.A;
        this.wMem(34258, super.A, 36938);
        return;
      }

      int var61 = this.mem(34257, 36942);
      super.A = var61;
      this.BC(0);
      int var62 = super.A | super.A;
      super.A = var62;
      if (super.A == 0) {
        int var106 = this.mem16(34259, 36951);
        this.HL(var106);
        int var107 = this.mem(32986, 36954);
        super.A = var107;
        int var108 = super.A - 1 & 255;
        super.A = var108;
        super.F = super.A;
        int var109 = super.A | 157;
        super.A = var109;
        super.F = super.A;
        int var110 = super.A ^ 191;
        super.A = var110;
        super.F = super.A;
        super.E = super.A;
        super.D = 0;
        int var111 = this.HL();
        int var112 = this.DE();
        int var113 = var111 + var112 & '\uffff';
        this.HL(var113);
        int var114 = this.mem(32964, 36966);
        super.A = var114;
        int var115 = this.HL();
        int var116 = this.mem(var115, 36969);
        if (super.A == var116) {
          this.BC(32);
          int var117 = this.mem(32986, 36975);
          super.A = var117;
          int var118 = super.A | super.A;
          super.A = var118;
          if (super.A != 0) {
            this.BC(65504);
          }
        }
      }

      int var63 = this.mem16(34259, 36984);
      this.HL(var63);
      int var64 = this.HL();
      int var65 = this.BC();
      int var66 = var64 + var65 & '\uffff';
      this.HL(var66);
      int var67 = this.HL() + 1 & '\uffff';
      this.HL(var67);
      int var68 = this.HL() + 1 & '\uffff';
      this.HL(var68);
      super.A = super.L;
      int var69 = super.A & 31;
      super.A = var69;
      if (super.A != 0) {
        this.DE(32);
        int var73 = this.mem(32946, 36999);
        super.A = var73;
        int var74 = this.HL();
        int var75 = this.DE();
        int var76 = var74 + var75 & '\uffff';
        this.HL(var76);
        int var77 = this.HL();
        int var78 = this.mem(var77, 37003);
        if (super.A == var78) {
          return;
        }

        int var79 = this.mem(34255, 37005);
        super.A = var79;
        int var80 = super.C >> 1;
        int var81 = super.C & 128;
        int var82 = var80 | var81;
        super.C = var82;
        int var83 = super.A + super.C;
        int var84 = var83 & 255;
        super.A = var84;
        super.F = var83;
        super.B = super.A;
        int var85 = super.A & 15;
        super.A = var85;
        if (super.A != 0) {
          int var96 = this.mem(32946, 37016);
          super.A = var96;
          int var97 = this.HL();
          int var98 = this.DE();
          int var99 = var97 + var98 & '\uffff';
          this.HL(var99);
          int var100 = this.HL();
          int var101 = this.mem(var100, 37020);
          if (super.A == var101) {
            return;
          }

          int var102 = super.A | super.A;
          super.A = var102;
          super.F = super.A;
          int var103 = this.HL();
          int var104 = this.DE();
          int var105 = var103 - var104 & '\uffff';
          this.HL(var105);
        }

        int var86 = this.mem(32946, 37025);
        super.A = var86;
        int var87 = super.A | super.A;
        super.A = var87;
        super.F = super.A;
        int var88 = this.HL();
        int var89 = this.DE();
        int var90 = var88 - var89 & '\uffff';
        this.HL(var90);
        int var91 = this.HL();
        int var92 = this.mem(var91, 37031);
        if (super.A == var92) {
          return;
        }

        int var93 = this.HL() - 1 & '\uffff';
        this.HL(var93);
        int var94 = this.HL();
        this.wMem16(34259, var94, 37034);
        int var95 = super.A ^ super.A;
        super.A = var95;
        super.F = super.A;
        this.wMem(34258, super.A, 37038);
        super.A = super.B;
        this.wMem(34255, super.A, 37042);
        return;
      }

      int var70 = this.mem(33002, 38046);
      super.A = var70;
      this.wMem(33824, super.A, 38049);
      int var71 = this.mem(34259, 38052);
      super.A = var71;
      int var72 = super.A & 224;
      super.A = var72;
      super.F = super.A;
      this.wMem(34259, super.A, 38057);
      super.nextAddress = 38061;
      return;
    }

    int var270 = this.mem(34255, 36540);
    super.A = var270;
    int var271 = super.A + 16;
    int var272 = var271 & 255;
    super.A = var272;
    super.F = var271;
    int var273 = super.A & 240;
    super.A = var273;
    super.F = super.A;
    this.wMem(34255, super.A, 36547);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A, 36555);
    this.HL(34256);
    int var274 = this.HL();
    int var275 = this.mem(var274, 36561) & -3;
    int var276 = this.HL();
    this.wMem(var276, var275, 36561);
  }

  public void $36508() {
    int var1 = super.A & 240;
    super.A = var1;
    super.F = super.A;
    super.L = super.A;
    int var2 = super.A ^ super.A;
    super.A = var2;
    super.F = super.A;
    carry = 0;
    int var3 = super.L;
    int var4 = this.rl(var3);
    super.L = var4;
    int var5 = super.A + 92;
    int var6 = this.carry() & 255;
    int var7 = var5 + var6;
    super.A = var7;
    super.F = super.A;
    super.H = super.A;
    int var8 = this.mem(34259, 36517);
    super.A = var8;
    int var9 = super.A & 31;
    super.A = var9;
    super.F = super.A;
    int var10 = super.A | super.L;
    super.A = var10;
    super.F = super.A;
    super.L = super.A;
    int var11 = this.HL();
    this.wMem16(34259, var11, 36524);
  }

  public void $37056() {
    this.IX(33024);

    while (true) {
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
            int var64 = this.IX();
            if ((this.mem(var64, 37081) & 128) != 0) {
              int var84 = this.IX() + 1;
              int var85 = this.mem(var84, 37087);
              super.A = var85;
              if ((super.A & 128) != 0) {
                int var90 = super.A - 2;
                int var91 = var90 & 255;
                super.A = var91;
                super.F = var90;
                if (super.A < 148) {
                  int var92 = super.A - 2;
                  int var93 = var92 & 255;
                  super.A = var93;
                  super.F = var92;
                  if (super.A == 128) {
                    int var94 = super.A ^ super.A;
                    super.A = var94;
                    super.F = super.A;
                  }
                }
              } else {
                int var86 = super.A + 2;
                int var87 = var86 & 255;
                super.A = var87;
                super.F = var86;
                if (super.A < 18) {
                  int var88 = super.A + 2;
                  int var89 = var88 & 255;
                  super.A = var89;
                  super.F = var88;
                }
              }
            } else {
              int var65 = this.IX() + 1;
              int var66 = this.mem(var65, 37119);
              super.A = var66;
              if ((super.A & 128) == 0) {
                int var79 = super.A - 2;
                int var80 = var79 & 255;
                super.A = var80;
                super.F = var79;
                if (super.A < 20) {
                  int var81 = super.A - 2;
                  int var82 = var81 & 255;
                  super.A = var82;
                  super.F = var81;
                  int var83 = super.A | super.A;
                  super.A = var83;
                  if (super.A == 0) {
                    super.A = 128;
                  }
                }
              } else {
                int var67 = super.A + 2;
                int var68 = var67 & 255;
                super.A = var68;
                super.F = var67;
                if (super.A < 146) {
                  int var77 = super.A + 2;
                  int var78 = var77 & 255;
                  super.A = var78;
                  super.F = var77;
                }
              }
            }

            int var69 = this.IX() + 1;
            this.wMem(var69, super.A, 37149);
            int var70 = super.A & 127;
            super.A = var70;
            super.F = super.A;
            int var71 = this.IX() + 7;
            int var72 = this.mem(var71, 37154);
            if (super.A == var72) {
              int var73 = this.IX();
              int var74 = this.mem(var73, 37160);
              super.A = var74;
              int var75 = super.A ^ 128;
              super.A = var75;
              super.F = super.A;
              int var76 = this.IX();
              this.wMem(var76, super.A, 37165);
            }
          } else {
            label81:
            {
              int var36 = this.IX();
              int var37 = this.mem(var36, 37247);
              super.A = var37;
              int var38 = super.A ^ 8;
              super.A = var38;
              super.F = super.A;
              int var39 = this.IX();
              this.wMem(var39, super.A, 37252);
              int var40 = super.A & 24;
              super.A = var40;
              if (super.A != 0) {
                int var59 = this.IX();
                int var60 = this.mem(var59, 37259);
                super.A = var60;
                int var61 = super.A + 32;
                int var62 = var61 & 255;
                super.A = var62;
                super.F = var61;
                int var63 = this.IX();
                this.wMem(var63, super.A, 37264);
              }

              int var41 = this.IX() + 3;
              int var42 = this.mem(var41, 37267);
              super.A = var42;
              int var43 = this.IX() + 4;
              int var44 = this.mem(var43, 37270);
              int var45 = super.A + var44;
              int var46 = var45 & 255;
              super.A = var46;
              super.F = var45;
              int var47 = this.IX() + 3;
              this.wMem(var47, super.A, 37273);
              int var48 = this.IX() + 7;
              int var49 = this.mem(var48, 37276);
              if (super.A < var49) {
                int var54 = this.IX() + 6;
                int var55 = this.mem(var54, 37281);
                if (super.A != var55 && super.A >= var55) {
                  break label81;
                }

                int var56 = this.IX() + 6;
                int var57 = this.mem(var56, 37288);
                super.A = var57;
                int var58 = this.IX() + 3;
                this.wMem(var58, super.A, 37291);
              }

              int var50 = this.IX() + 4;
              int var51 = this.mem(var50, 37294);
              super.A = var51;
              int var52 = -super.A & 255;
              super.A = var52;
              int var53 = this.IX() + 4;
              this.wMem(var53, super.A, 37299);
            }
          }
        } else {
          int var7 = this.IX();
          if ((this.mem(var7, 37171) & 128) == 0) {
            int var22 = this.IX();
            int var23 = this.mem(var22, 37177);
            super.A = var23;
            int var24 = super.A - 32;
            int var25 = var24 & 255;
            super.A = var25;
            super.F = var24;
            int var26 = super.A & 127;
            super.A = var26;
            super.F = super.A;
            int var27 = this.IX();
            this.wMem(var27, super.A, 37184);
            if (super.A >= 96) {
              int var28 = this.IX() + 2;
              int var29 = this.mem(var28, 37191);
              super.A = var29;
              int var30 = super.A & 31;
              super.A = var30;
              super.F = super.A;
              int var31 = this.IX() + 6;
              int var32 = this.mem(var31, 37196);
              if (super.A != var32) {
                int var34 = this.IX() + 2;
                int var35 = this.mem(var34, 37201) - 1 & 255;
                this.wMem(var34, var35, 37201);
              } else {
                int var33 = this.IX();
                this.wMem(var33, 129, 37206);
              }
            }
          } else {
            int var8 = this.IX();
            int var9 = this.mem(var8, 37212);
            super.A = var9;
            int var10 = super.A + 32;
            int var11 = var10 & 255;
            super.A = var11;
            super.F = var10;
            int var12 = super.A | 128;
            super.A = var12;
            super.F = super.A;
            int var13 = this.IX();
            this.wMem(var13, super.A, 37219);
            if (super.A < 160) {
              int var14 = this.IX() + 2;
              int var15 = this.mem(var14, 37226);
              super.A = var15;
              int var16 = super.A & 31;
              super.A = var16;
              super.F = super.A;
              int var17 = this.IX() + 7;
              int var18 = this.mem(var17, 37231);
              if (super.A != var18) {
                int var20 = this.IX() + 2;
                int var21 = this.mem(var20, 37236) + 1 & 255;
                this.wMem(var20, var21, 37236);
              } else {
                int var19 = this.IX();
                this.wMem(var19, 97, 37241);
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

    while (true) {
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
            int var174 = this.IX() + 3;
            int var175 = this.mem(var174, 37334);
            super.E = var175;
            super.D = 130;
            int var176 = this.DE();
            int var177 = this.mem(var176, 37339);
            super.A = var177;
            super.L = super.A;
            int var178 = this.IX() + 2;
            int var179 = this.mem(var178, 37341);
            super.A = var179;
            int var180 = super.A & 31;
            super.A = var180;
            super.F = super.A;
            int var181 = super.A + super.L;
            int var182 = var181 & 255;
            super.A = var182;
            super.F = var181;
            super.L = super.A;
            super.A = super.E;
            int var183 = super.A;
            int var184 = this.rlc(var183);
            super.A = var184;
            int var185 = super.A & 1;
            super.A = var185;
            super.F = super.A;
            int var186 = super.A | 92;
            super.A = var186;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var187 = this.IX() + 1;
            int var188 = this.mem(var187, 37358);
            super.A = var188;
            int var189 = super.A & 15;
            super.A = var189;
            super.F = super.A;
            int var190 = super.A + 56;
            int var191 = var190 & 255;
            super.A = var191;
            super.F = var190;
            int var192 = super.A & 71;
            super.A = var192;
            super.F = super.A;
            super.C = super.A;
            int var193 = this.HL();
            int var194 = this.mem(var193, 37368);
            super.A = var194;
            int var195 = super.A & 56;
            super.A = var195;
            super.F = super.A;
            int var196 = super.A ^ super.C;
            super.A = var196;
            super.F = super.A;
            super.C = super.A;
            int var197 = this.HL();
            this.wMem(var197, super.C, 37373);
            int var198 = this.HL() + 1 & '\uffff';
            this.HL(var198);
            int var199 = this.HL();
            this.wMem(var199, super.C, 37375);
            int var200 = this.HL();
            int var201 = this.DE();
            int var202 = var200 + var201 & '\uffff';
            this.HL(var202);
            int var203 = this.HL();
            this.wMem(var203, super.C, 37377);
            int var204 = this.HL() + 1 & '\uffff';
            this.HL(var204);
            int var205 = this.HL();
            this.wMem(var205, super.C, 37379);
            int var206 = this.IX() + 3;
            int var207 = this.mem(var206, 37380);
            super.A = var207;
            int var208 = super.A & 14;
            super.A = var208;
            if (super.A != 0) {
              int var231 = this.HL();
              int var232 = this.DE();
              int var233 = var231 + var232 & '\uffff';
              this.HL(var233);
              int var234 = this.HL();
              this.wMem(var234, super.C, 37388);
              int var235 = this.HL() + 1 & '\uffff';
              this.HL(var235);
              int var236 = this.HL();
              this.wMem(var236, super.C, 37390);
            }

            super.C = 1;
            int var209 = this.IX() + 1;
            int var210 = this.mem(var209, 37393);
            super.A = var210;
            int var211 = this.IX();
            int var212 = this.mem(var211, 37396);
            int var213 = super.A & var212;
            super.A = var213;
            super.F = super.A;
            int var214 = this.IX() + 2;
            int var215 = this.mem(var214, 37399);
            int var216 = super.A | var215;
            super.A = var216;
            super.F = super.A;
            int var217 = super.A & 224;
            super.A = var217;
            super.F = super.A;
            super.E = super.A;
            int var218 = this.IX() + 5;
            int var219 = this.mem(var218, 37405);
            super.D = var219;
            super.H = 130;
            int var220 = this.IX() + 3;
            int var221 = this.mem(var220, 37410);
            super.L = var221;
            int var222 = this.IX() + 2;
            int var223 = this.mem(var222, 37413);
            super.A = var223;
            int var224 = super.A & 31;
            super.A = var224;
            super.F = super.A;
            int var225 = this.HL();
            int var226 = this.mem(var225, 37418);
            int var227 = super.A | var226;
            super.A = var227;
            super.F = super.A;
            int var228 = this.HL() + 1 & '\uffff';
            this.HL(var228);
            int var229 = this.HL();
            int var230 = this.mem(var229, 37420);
            super.H = var230;
            super.L = super.A;
            this.$37974();
            if (super.F != 0) {
              super.nextAddress = 37048;
              return;
            }
          } else {
            int var122 = this.IX();
            if ((this.mem(var122, 37431) & 128) == 0) {
              int var172 = this.IX() + 4;
              int var173 = this.mem(var172, 37437) - 1 & 255;
              this.wMem(var172, var173, 37437);
              super.C = 44;
            } else {
              int var123 = this.IX() + 4;
              int var124 = this.mem(var123, 37444) + 1 & 255;
              this.wMem(var123, var124, 37444);
              super.C = 244;
            }

            int var125 = this.IX() + 4;
            int var126 = this.mem(var125, 37449);
            super.A = var126;
            if (super.A != super.C) {
              int var127 = super.A & 224;
              super.A = var127;
              if (super.A == 0) {
                int var128 = this.IX() + 2;
                int var129 = this.mem(var128, 37479);
                super.E = var129;
                super.D = 130;
                int var130 = this.DE();
                int var131 = this.mem(var130, 37484);
                super.A = var131;
                int var132 = this.IX() + 4;
                int var133 = this.mem(var132, 37485);
                int var134 = super.A + var133;
                int var135 = var134 & 255;
                super.A = var135;
                super.F = var134;
                super.L = super.A;
                super.A = super.E;
                int var136 = super.A & 128;
                super.A = var136;
                super.F = super.A;
                int var137 = super.A;
                int var138 = this.rlc(var137);
                super.A = var138;
                int var139 = super.A | 92;
                super.A = var139;
                super.F = super.A;
                super.H = super.A;
                int var140 = this.IX() + 5;
                this.wMem(var140, 0, 37496);
                int var141 = this.HL();
                int var142 = this.mem(var141, 37500);
                super.A = var142;
                int var143 = super.A & 7;
                super.A = var143;
                super.F = super.A;
                if (super.A == 7) {
                  int var166 = this.IX() + 5;
                  int var167 = this.mem(var166, 37507) - 1 & 255;
                  this.wMem(var166, var167, 37507);
                }

                int var144 = this.HL();
                int var145 = this.mem(var144, 37510);
                super.A = var145;
                int var146 = super.A | 7;
                super.A = var146;
                super.F = super.A;
                int var147 = this.HL();
                this.wMem(var147, super.A, 37513);
                int var148 = this.DE() + 1 & '\uffff';
                this.DE(var148);
                int var149 = this.DE();
                int var150 = this.mem(var149, 37515);
                super.A = var150;
                super.H = super.A;
                int var151 = super.H - 1 & 255;
                super.H = var151;
                super.F = super.H;
                int var152 = this.IX() + 6;
                int var153 = this.mem(var152, 37518);
                super.A = var153;
                int var154 = this.HL();
                this.wMem(var154, super.A, 37521);
                int var155 = super.H + 1 & 255;
                super.H = var155;
                super.F = super.H;
                int var156 = this.HL();
                int var157 = this.mem(var156, 37523);
                super.A = var157;
                int var158 = this.IX() + 5;
                int var159 = this.mem(var158, 37524);
                int var160 = super.A & var159;
                super.A = var160;
                if (super.A != 0) {
                  super.nextAddress = 37048;
                  return;
                }

                int var161 = this.HL();
                this.wMem(var161, 255, 37530);
                int var162 = super.H + 1 & 255;
                super.H = var162;
                super.F = super.H;
                int var163 = this.IX() + 6;
                int var164 = this.mem(var163, 37533);
                super.A = var164;
                int var165 = this.HL();
                this.wMem(var165, super.A, 37536);
              }
            } else {
              this.BC(640);
              int var168 = this.mem(32990, 37458);
              super.A = var168;

              do {
                int var169 = super.A ^ 24;
                super.A = var169;

                do {
                  int var170 = super.B - 1 & 255;
                  super.B = var170;
                } while (super.B != 0);

                super.B = super.C;
                int var171 = super.C - 1 & 255;
                super.C = var171;
              } while (super.C != 0);
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

          while (true) {
            label114:
            {
              int var12 = this.IY();
              int var13 = this.mem(var12, 37558);
              super.A = var13;
              int var14 = this.IX() + 3;
              int var15 = this.mem(var14, 37561);
              int var16 = super.A + var15;
              int var17 = var16 & 255;
              super.A = var17;
              super.F = var16;
              super.L = super.A;
              int var18 = this.IY() + 1;
              int var19 = this.mem(var18, 37565);
              super.H = var19;
              int var20 = this.mem(34262, 37568);
              super.A = var20;
              int var21 = super.A | super.A;
              super.A = var21;
              if (super.A == 0) {
                int var113 = this.IX() + 5;
                int var114 = this.mem(var113, 37574);
                super.A = var114;
                int var115 = this.HL();
                int var116 = this.mem(var115, 37577);
                int var117 = super.A & var116;
                super.A = var117;
                if (super.A == 0) {
                  break label114;
                }

                int var118 = this.IX() + 9;
                int var119 = this.mem(var118, 37580);
                super.A = var119;
                this.wMem(34262, super.A, 37583);
                int var120 = this.IX() + 11;
                int var121 = this.mem(var120, 37586) | 1;
                this.wMem(var120, var121, 37586);
              }

              int var22 = this.IX() + 9;
              int var23 = this.mem(var22, 37590);
              if (super.A == var23) {
                int var102 = this.IX() + 11;
                if ((this.mem(var102, 37595) & 1) != 0) {
                  int var103 = this.IX() + 3;
                  int var104 = this.mem(var103, 37601);
                  super.B = var104;
                  int var105 = this.IX() + 5;
                  int var106 = this.mem(var105, 37604);
                  super.A = var106;
                  super.C = 1;
                  if (super.A >= 4) {
                    super.C = 0;
                    if (super.A >= 16) {
                      int var112 = super.B - 1 & 255;
                      super.B = var112;
                      super.F = super.B;
                      super.C = 3;
                      if (super.A >= 64) {
                        super.C = 2;
                      }
                    }
                  }

                  int var107 = this.BC();
                  this.wMem16(34258, var107, 37628);
                  super.A = super.IYL;
                  int var108 = super.A - 16;
                  int var109 = var108 & 255;
                  super.A = var109;
                  super.F = var108;
                  this.wMem(34255, super.A, 37636);
                  int var110 = this.HL();
                  this.push(var110);
                  this.$36508();
                  int var111 = this.pop();
                  this.HL(var111);
                }
              }
            }

            int var24 = this.IX() + 5;
            int var25 = this.mem(var24, 37646);
            super.A = var25;
            int var26 = this.HL();
            int var27 = this.mem(var26, 37649);
            int var28 = super.A | var27;
            super.A = var28;
            super.F = super.A;
            int var29 = this.HL();
            this.wMem(var29, super.A, 37650);
            int var30 = this.IX() + 9;
            int var31 = this.mem(var30, 37651);
            super.A = var31;
            int var32 = this.IX() + 1;
            int var33 = this.mem(var32, 37654);
            int var34 = super.A + var33;
            int var35 = var34 & 255;
            super.A = var35;
            super.F = var34;
            super.L = super.A;
            int var36 = super.L | 128;
            super.L = var36;
            super.H = 131;
            int var37 = this.HL();
            int var38 = this.mem(var37, 37662);
            super.E = var38;
            super.D = 0;
            int var39 = this.IY();
            int var40 = this.DE();
            int var41 = var39 + var40 & '\uffff';
            this.IY(var41);
            int var42 = super.L & -129;
            super.L = var42;
            int var43 = this.HL();
            int var44 = this.mem(var43, 37669);
            super.A = var44;
            int var45 = super.A | super.A;
            super.A = var45;
            if (super.A != 0) {
              super.B = super.A;
              int var87 = this.IX() + 1;
              if ((this.mem(var87, 37674) & 128) != 0) {
                do {
                  int var95 = this.IX() + 5;
                  int var96 = this.mem(var95, 37680);
                  int var97 = this.rlc(var96);
                  this.wMem(var95, var97, 37680);
                  int var98 = this.IX() + 5;
                  if ((this.mem(var98, 37684) & 1) != 0) {
                    int var100 = this.IX() + 3;
                    int var101 = this.mem(var100, 37690) - 1 & 255;
                    this.wMem(var100, var101, 37690);
                  }

                  int var99 = super.B - 1 & 255;
                  super.B = var99;
                } while (super.B != 0);
              } else {
                do {
                  int var88 = this.IX() + 5;
                  int var89 = this.mem(var88, 37697);
                  int var90 = this.rrc(var89);
                  this.wMem(var88, var90, 37697);
                  int var91 = this.IX() + 5;
                  if ((this.mem(var91, 37701) & 128) != 0) {
                    int var93 = this.IX() + 3;
                    int var94 = this.mem(var93, 37707) + 1 & 255;
                    this.wMem(var93, var94, 37707);
                  }

                  int var92 = super.B - 1 & 255;
                  super.B = var92;
                } while (super.B != 0);
              }
            }

            int var46 = this.IX() + 9;
            int var47 = this.mem(var46, 37712);
            super.A = var47;
            int var48 = this.IX() + 4;
            int var49 = this.mem(var48, 37715);
            if (super.A == var49) {
              int var50 = this.mem(34262, 37726);
              super.A = var50;
              if ((super.A & 128) != 0) {
                int var82 = super.A + 1 & 255;
                super.A = var82;
                super.F = super.A;
                this.wMem(34262, super.A, 37734);
                int var83 = this.IX() + 11;
                int var84 = this.mem(var83, 37737) & -2;
                this.wMem(var83, var84, 37737);
              } else {
                int var51 = this.IX() + 11;
                if ((this.mem(var51, 37743) & 1) != 0) {
                  int var52 = this.mem(34256, 37749);
                  super.A = var52;
                  if ((super.A & 2) != 0) {
                    int var53 = super.A;
                    int var54 = this.rrc(var53);
                    super.A = var54;
                    int var55 = this.IX();
                    int var56 = this.mem(var55, 37757);
                    int var57 = super.A ^ var56;
                    super.A = var57;
                    super.F = super.A;
                    int var58 = super.A;
                    int var59 = this.rlc(var58);
                    super.A = var59;
                    int var60 = super.A;
                    int var61 = this.rlc(var60);
                    super.A = var61;
                    int var62 = super.A & 2;
                    super.A = var62;
                    super.F = super.A;
                    int var63 = super.A - 1 & 255;
                    super.A = var63;
                    super.F = super.A;
                    this.HL(34262);
                    int var64 = this.HL();
                    int var65 = this.mem(var64, 37768);
                    int var66 = super.A + var65;
                    int var67 = var66 & 255;
                    super.A = var67;
                    super.F = var66;
                    int var68 = this.HL();
                    this.wMem(var68, super.A, 37769);
                    int var69 = this.mem(33003, 37770);
                    super.A = var69;
                    super.C = super.A;
                    int var70 = this.mem(33824, 37774);
                    super.A = var70;
                    if (super.A == super.C) {
                      int var79 = this.HL();
                      int var80 = this.mem(var79, 37780);
                      super.A = var80;
                      if (super.A < 12) {
                        int var81 = this.HL();
                        this.wMem(var81, 12, 37785);
                      }
                    }

                    int var71 = this.HL();
                    int var72 = this.mem(var71, 37787);
                    super.A = var72;
                    int var73 = this.IX() + 4;
                    int var74 = this.mem(var73, 37788);
                    if (super.A >= var74 && super.A != var74) {
                      int var75 = this.HL();
                      this.wMem(var75, 240, 37795);
                      int var76 = this.mem(34255, 37797);
                      super.A = var76;
                      int var77 = super.A & 248;
                      super.A = var77;
                      super.F = super.A;
                      this.wMem(34255, super.A, 37802);
                      int var78 = super.A ^ super.A;
                      super.A = var78;
                      super.F = super.A;
                      this.wMem(34257, super.A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var85 = this.IX() + 9;
            int var86 = this.mem(var85, 37720) + 1 & 255;
            this.wMem(var85, var86, 37720);
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
        int var13 = super.A + 92;
        int var14 = var13 & 255;
        super.A = var14;
        super.F = var13;
        super.D = super.A;
        int var15 = super.H + 1 & 255;
        super.H = var15;
        super.F = super.H;
        int var16 = this.HL();
        int var17 = this.mem(var16, 37866);
        super.E = var17;
        int var18 = super.H - 1 & 255;
        super.H = var18;
        super.F = super.H;
        int var19 = this.DE();
        int var20 = this.mem(var19, 37868);
        super.A = var20;
        int var21 = super.A & 7;
        super.A = var21;
        super.F = super.A;
        if (super.A != 7) {
          int var22 = this.mem(34251, 37936);
          super.A = var22;
          int var23 = super.A + super.L;
          int var24 = var23 & 255;
          super.A = var24;
          super.F = var23;
          int var25 = super.A & 3;
          super.A = var25;
          super.F = super.A;
          int var26 = super.A + 3;
          int var27 = var26 & 255;
          super.A = var27;
          super.F = var26;
          super.C = super.A;
          int var28 = this.DE();
          int var29 = this.mem(var28, 37945);
          super.A = var29;
          int var30 = super.A & 248;
          super.A = var30;
          super.F = super.A;
          int var31 = super.A | super.C;
          super.A = var31;
          super.F = super.A;
          int var32 = this.DE();
          this.wMem(var32, super.A, 37949);
          int var33 = this.HL();
          int var34 = this.mem(var33, 37950);
          super.A = var34;
          int var35 = super.A;
          int var36 = this.rlc(var35);
          super.A = var36;
          int var37 = super.A;
          int var38 = this.rlc(var37);
          super.A = var38;
          int var39 = super.A;
          int var40 = this.rlc(var39);
          super.A = var40;
          int var41 = super.A;
          int var42 = this.rlc(var41);
          super.A = var42;
          int var43 = super.A & 8;
          super.A = var43;
          super.F = super.A;
          int var44 = super.A + 96;
          int var45 = var44 & 255;
          super.A = var45;
          super.F = var44;
          super.D = super.A;
          int var46 = this.HL();
          this.push(var46);
          this.HL(32993);
          super.B = 8;
          this.$38555();
          int var47 = this.pop();
          this.HL(var47);
        } else {
          this.IX(34172);

          while (true) {
            int var48 = this.IX() + 2;
            int var49 = this.mem(var48, 37879) + 1 & 255;
            this.wMem(var48, var49, 37879);
            int var50 = this.IX() + 2;
            int var51 = this.mem(var50, 37882);
            super.A = var51;
            if (super.A != 58) {
              int var52 = this.mem(32990, 37897);
              super.A = var52;
              super.C = 128;

              do {
                int var53 = super.A ^ 24;
                super.A = var53;
                super.F = super.A;
                super.E = super.A;
                super.A = 144;
                int var54 = super.A - super.C;
                int var55 = var54 & 255;
                super.A = var55;
                super.F = var54;
                super.B = super.A;
                super.A = super.E;

                do {
                  int var56 = super.B - 1 & 255;
                  super.B = var56;
                } while (super.B != 0);

                int var57 = super.C - 1 & 255;
                super.C = var57;
                super.F = super.C;
                int var58 = super.C - 1 & 255;
                super.C = var58;
              } while (super.C != 0);

              int var59 = this.mem(34270, 37918);
              super.A = var59;
              int var60 = super.A + 1 & 255;
              super.A = var60;
              super.F = super.A;
              this.wMem(34270, super.A, 37922);
              if (super.F == 0) {
                super.A = 1;
                this.wMem(34271, super.A, 37929);
              }

              int var61 = this.HL();
              int var62 = this.mem(var61, 37932) & -65;
              int var63 = this.HL();
              this.wMem(var63, var62, 37932);
              break;
            }

            int var64 = this.IX() + 2;
            this.wMem(var64, 48, 37889);
            int var65 = this.IX() - 1 & '\uffff';
            this.IX(var65);
          }
        }
      }

      int var7 = super.L + 1 & 255;
      super.L = var7;
    } while (super.L != 0);

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
        int var32 = this.HL();
        int var33 = this.mem(var32, 37981);
        int var34 = super.A & var33;
        super.A = var34;
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
        int var24 = this.HL();
        int var25 = this.mem(var24, 37993);
        int var26 = super.A & var25;
        super.A = var26;
        if (super.A != 0) {
          return;
        }

        int var27 = this.DE();
        int var28 = this.mem(var27, 37995);
        super.A = var28;
        int var29 = this.HL();
        int var30 = this.mem(var29, 37996);
        int var31 = super.A | var30;
        super.A = var31;
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
        int var17 = super.A - 8;
        int var18 = var17 & 255;
        super.A = var18;
        super.F = var17;
        super.H = super.A;
        super.A = super.L;
        int var19 = super.A + 32;
        int var20 = var19 & 255;
        super.A = var20;
        super.F = var19;
        super.L = super.A;
        int var21 = super.A & 224;
        super.A = var21;
        if (super.A == 0) {
          super.A = super.H;
          int var22 = super.A + 8;
          int var23 = var22 & 255;
          super.A = var23;
          super.F = var22;
          super.H = super.A;
        }
      }

      int var15 = super.B - 1 & 255;
      super.B = var15;
    } while (super.B != 0);

    int var16 = super.A ^ super.A;
    super.A = var16;
    super.F = super.A;
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
    int var4 = super.A + 160;
    int var5 = var4 & 255;
    super.A = var5;
    super.F = var4;
    this.wMem(34259, super.A, 38077);
    super.A = 93;
    this.wMem(34260, super.A, 38082);
    super.A = 208;
    this.wMem(34255, super.A, 38087);
    int var6 = super.A ^ super.A;
    super.A = var6;
    super.F = super.A;
    this.wMem(34257, super.A, 38091);
    super.nextAddress = 38095;
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
    int var9 = super.A + 112;
    int var10 = var9 & 255;
    super.A = var10;
    super.F = var9;
    super.H = super.A;
    super.E = super.L;
    super.D = super.H;
    int var11 = this.mem(32985, 38151);
    super.A = var11;
    int var12 = super.A | super.A;
    super.A = var12;
    if (super.A != 0) {
      super.B = super.A;
      int var13 = this.mem(32982, 38157);
      super.A = var13;
      int var14 = super.A | super.A;
      super.A = var14;
      if (super.A == 0) {
        int var34 = this.HL();
        int var35 = this.mem(var34, 38163);
        super.A = var35;
        int var36 = super.A;
        int var37 = this.rlc(var36);
        super.A = var37;
        int var38 = super.A;
        int var39 = this.rlc(var38);
        super.A = var39;
        int var40 = super.H + 1 & 255;
        super.H = var40;
        super.F = super.H;
        int var41 = super.H + 1 & 255;
        super.H = var41;
        super.F = super.H;
        int var42 = this.HL();
        int var43 = this.mem(var42, 38170);
        super.C = var43;
        int var44 = super.C;
        int var45 = this.rrc(var44);
        super.C = var45;
        int var46 = super.C;
        int var47 = this.rrc(var46);
        super.C = var47;
      } else {
        int var15 = this.HL();
        int var16 = this.mem(var15, 38182);
        super.A = var16;
        int var17 = super.A;
        int var18 = this.rrc(var17);
        super.A = var18;
        int var19 = super.A;
        int var20 = this.rrc(var19);
        super.A = var20;
        int var21 = super.H + 1 & 255;
        super.H = var21;
        super.F = super.H;
        int var22 = super.H + 1 & 255;
        super.H = var22;
        super.F = super.H;
        int var23 = this.HL();
        int var24 = this.mem(var23, 38189);
        super.C = var24;
        int var25 = super.C;
        int var26 = this.rlc(var25);
        super.C = var26;
        int var27 = super.C;
        int var28 = this.rlc(var27);
        super.C = var28;
      }

      do {
        int var29 = this.DE();
        this.wMem(var29, super.A, 38175);
        int var30 = this.HL();
        this.wMem(var30, super.C, 38176);
        int var31 = super.L + 1 & 255;
        super.L = var31;
        super.F = super.L;
        int var32 = super.E + 1 & 255;
        super.E = var32;
        int var33 = super.B - 1 & 255;
        super.B = var33;
      } while (super.B != 0);

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
        if (super.F != 0) {
          super.nextAddress = 37048;
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
    int var4 = super.A + 64;
    int var5 = var4 & 255;
    super.A = var5;
    super.F = var4;
    super.E = super.A;
    super.D = 0;
    int var6 = this.HL();
    int var7 = this.DE();
    int var8 = var6 + var7 & '\uffff';
    this.HL(var8);
    int var9 = this.mem(32964, 38360);
    super.A = var9;
    int var10 = this.HL();
    int var11 = this.mem(var10, 38363);
    if (super.A == var11) {
      int var45 = this.mem(34257, 38366);
      super.A = var45;
      int var46 = super.A | super.A;
      super.A = var46;
      if (super.A == 0) {
        int var47 = this.mem(34258, 38372);
        super.A = var47;
        int var48 = super.A & 3;
        super.A = var48;
        super.F = super.A;
        int var49 = super.A;
        int var50 = this.rlc(var49);
        super.A = var50;
        int var51 = super.A;
        int var52 = this.rlc(var51);
        super.A = var52;
        super.B = super.A;
        int var53 = this.mem(32986, 38380);
        super.A = var53;
        int var54 = super.A & 1;
        super.A = var54;
        super.F = super.A;
        int var55 = super.A - 1 & 255;
        super.A = var55;
        super.F = super.A;
        int var56 = super.A ^ 12;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A ^ super.B;
        super.A = var57;
        super.F = super.A;
        int var58 = super.A & 12;
        super.A = var58;
        super.F = super.A;
        super.B = super.A;
      }
    }

    int var12 = this.mem16(34259, 38392);
    this.HL(var12);
    this.DE(31);
    super.C = 15;
    this.$38430();
    if (this.isNextPC(37047)) {
      super.nextAddress = 37048;
    } else {
      int var13 = this.HL() + 1 & '\uffff';
      this.HL(var13);
      this.$38430();
      if (this.isNextPC(37047)) {
        super.nextAddress = 37048;
      } else {
        int var14 = this.HL();
        int var15 = this.DE();
        int var16 = var14 + var15 & '\uffff';
        this.HL(var16);
        this.$38430();
        int var17 = this.HL() + 1 & '\uffff';
        this.HL(var17);
        this.$38430();
        if (this.isNextPC(37047)) {
          super.nextAddress = 37048;
        } else {
          int var18 = this.mem(34255, 38415);
          super.A = var18;
          int var19 = super.A + super.B;
          int var20 = var19 & 255;
          super.A = var20;
          super.F = var19;
          super.C = super.A;
          int var21 = this.HL();
          int var22 = this.DE();
          int var23 = var21 + var22 & '\uffff';
          this.HL(var23);
          this.$38430();
          int var24 = this.HL() + 1 & '\uffff';
          this.HL(var24);
          this.$38430();
          if (this.isNextPC(37047)) {
            super.nextAddress = 37048;
          } else {
            int var25 = this.mem(34255, 38455);
            super.A = var25;
            int var26 = super.A + super.B;
            int var27 = var26 & 255;
            super.A = var27;
            super.F = var26;
            super.IXH = 130;
            super.IXL = super.A;
            int var28 = this.mem(34256, 38464);
            super.A = var28;
            int var29 = super.A & 1;
            super.A = var29;
            super.F = super.A;
            int var30 = super.A;
            int var31 = this.rrc(var30);
            super.A = var31;
            super.E = super.A;
            int var32 = this.mem(34258, 38471);
            super.A = var32;
            int var33 = super.A & 3;
            super.A = var33;
            super.F = super.A;
            int var34 = super.A;
            int var35 = this.rrc(var34);
            super.A = var35;
            int var36 = super.A;
            int var37 = this.rrc(var36);
            super.A = var37;
            int var38 = super.A;
            int var39 = this.rrc(var38);
            super.A = var39;
            int var40 = super.A | super.E;
            super.A = var40;
            super.F = super.A;
            super.E = super.A;
            super.D = 157;
            int var41 = this.mem(33824, 38483);
            super.A = var41;
            if (super.A == 29) {
              super.D = 182;
              super.A = super.E;
              int var44 = super.A ^ 128;
              super.A = var44;
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
            this.$38504();
          }
        }
      }
    }
  }

  public void $38430() {
    int var1 = this.mem(32928, 38430);
    super.A = var1;
    int var2 = this.HL();
    int var3 = this.mem(var2, 38433);
    if (super.A == var3) {
      super.A = super.C;
      int var8 = super.A & 15;
      super.A = var8;
      if (super.A != 0) {
        int var9 = this.mem(32928, 38441);
        super.A = var9;
        int var10 = super.A | 7;
        super.A = var10;
        super.F = super.A;
        int var11 = this.HL();
        this.wMem(var11, super.A, 38446);
      }
    }

    int var4 = this.mem(32955, 38447);
    super.A = var4;
    int var5 = this.HL();
    int var6 = this.mem(var5, 38450);
    if (super.A == var6) {
      super.nextAddress = 37047;
    } else {
      int var7 = super.A - var6;
      super.F = var7;
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
      super.F = super.A;
      int var11 = this.HL();
      this.wMem(var11, super.A, 38514);
      int var12 = this.HL() + 1 & '\uffff';
      this.HL(var12);
      int var13 = this.DE() + 1 & '\uffff';
      this.DE(var13);
      int var14 = this.DE();
      int var15 = this.mem(var14, 38517);
      super.A = var15;
      int var16 = this.HL();
      int var17 = this.mem(var16, 38518);
      int var18 = super.A | var17;
      super.A = var18;
      super.F = super.A;
      int var19 = this.HL();
      this.wMem(var19, super.A, 38519);
      int var20 = this.IX() + 1 & '\uffff';
      this.IX(var20);
      int var21 = this.IX() + 1 & '\uffff';
      this.IX(var21);
      int var22 = this.DE() + 1 & '\uffff';
      this.DE(var22);
      int var23 = super.B - 1 & 255;
      super.B = var23;
    } while (super.B != 0);

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
      int var5 = super.A - 8;
      int var6 = var5 & 255;
      super.A = var6;
      super.F = var5;
      super.D = super.A;
      int var7 = super.C - 1 & 255;
      super.C = var7;
    } while (super.C != 0);

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
    } while (super.B != 0);

  }

  public void $38562() {
    while (true) {
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

      while (true) {
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
            int var11 = this.rl(var10);
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
      int var7 = this.in(31);
      super.A = var7;
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
    int var6 = super.A - 1;
    super.F = var6;
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
      } while (super.B != 0);

      int var4 = super.A - 1 & 255;
      super.A = var4;
    } while (super.A != 0);

  }
}
