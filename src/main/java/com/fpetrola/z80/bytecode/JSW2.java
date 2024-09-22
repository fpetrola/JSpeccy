package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  private int A= 0;

  public void $34762() {
    label282:
    while(true) {
      int var1 = A ^ A;
      A = var1;
      F = A;
      this.wMem(34254, A, 34763);
      this.wMem(34273, A, 34766);
      this.wMem(34253, A, 34769);
      this.wMem(34257, A, 34772);
      this.wMem(34251, A, 34775);
      this.wMem(34272, A, 34778);
      this.wMem(34271, A, 34781);
      A = 7;
      this.wMem(34252, A, 34786);
      A = 208;
      this.wMem(34255, A, 34791);
      A = 33;
      this.wMem(33824, A, 34796);
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
      H = 164;
      int var8 = this.mem(41983, 34818);
      A = var8;
      L = A;
      this.wMem(34270, A, 34822);

      do {
        int var9 = this.HL();
        int var10 = this.mem(var9, 34825) | 64;
        int var11 = this.HL();
        this.wMem(var11, var10, 34825);
        int var12 = L + 1 & 255;
        L = var12;
      } while(L != 0);

      this.HL(34274);
      int var13 = this.HL();
      int var14 = this.mem(var13, 34833) | 1;
      int var15 = this.HL();
      this.wMem(var15, var14, 34833);

      label274:
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
        C = 32;
        this.$38528();
        this.DE(22528);

        do {
          int var18 = this.DE();
          int var19 = this.mem(var18, 34884);
          A = var19;
          int var20 = A | A;
          A = var20;
          if (A != 0 && A != 211 && A != 9 && A != 45 && A != 36) {
            C = 0;
            if (A != 8 && A != 41) {
              if (A != 44) {
                if (A != 5) {
                  C = 16;
                }
              } else {
                A = 37;
                int var303 = this.DE();
                this.wMem(var303, A, 34928);
              }
            }

            A = E;
            int var291 = A & 1;
            A = var291;
            F = A;
            int var292 = A;
            int var293 = this.rlc(var292);
            A = var293;
            int var294 = A;
            int var295 = this.rlc(var294);
            A = var295;
            int var296 = A;
            int var297 = this.rlc(var296);
            A = var297;
            int var298 = A | C;
            A = var298;
            F = A;
            C = A;
            B = 0;
            this.HL(33841);
            int var299 = this.HL();
            int var300 = this.BC();
            int var301 = var299 + var300 & '\uffff';
            this.HL(var301);
            int var302 = D & 1;
            F = var302;
            D = 64;
            if (F != 0) {
              D = 72;
            }

            B = 8;
            this.$38555();
          }

          int var21 = this.DE() + 1 & '\uffff';
          this.DE(var21);
          A = D;
        } while(A != 90);

        this.BC(31);
        int var22 = A ^ A;
        A = var22;
        F = A;

        do {
          int var23 = this.in(C);
          E = var23;
          int var24 = A | E;
          A = var24;
          int var25 = B - 1 & 255;
          B = var25;
        } while(B != 0);

        int var26 = A & 32;
        A = var26;
        if (A == 0) {
          A = 1;
          this.wMem(34254, A, 34981);
        }

        this.HL(34299);
        this.$38562();
        if (F != 0) {
          break;
        }

        int var277 = A ^ A;
        A = var277;
        F = A;
        this.wMem(34276, A, 34994);

        while(true) {
          this.$35563();
          this.HL(23136);
          this.DE(23137);
          this.BC(31);
          int var278 = this.HL();
          this.wMem(var278, 79, 35009);
          this.ldir();
          int var279 = this.mem(34276, 35013);
          A = var279;
          this.IX(33876);
          E = A;
          D = 0;
          int var280 = this.IX();
          int var281 = this.DE();
          int var282 = var280 + var281 & '\uffff';
          this.IX(var282);
          this.DE(20576);
          C = 32;
          this.$38528();
          int var283 = this.mem(34276, 35033);
          A = var283;
          int var284 = A & 31;
          A = var284;
          F = A;
          int var285 = A + 50 & 255;
          A = var285;
          this.$38622();
          this.BC(45054);
          int var286 = this.in(C);
          A = var286;
          int var287 = A & 1;
          A = var287;
          F = A;
          if (A != 1) {
            break label274;
          }

          int var288 = this.mem(34276, 35054);
          A = var288;
          int var289 = A + 1 & 255;
          A = var289;
          F = A;
          int var290 = A - 224;
          F = var290;
          this.wMem(34276, A, 35060);
          if (F == 0) {
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
        int var27 = this.mem(33824, 35090);
        A = var27;
        int var28 = A | 192;
        A = var28;
        F = A;
        H = A;
        L = 0;
        this.DE(32768);
        this.BC(256);
        this.ldir();
        this.IX(33008);
        this.DE(33024);
        A = 8;

        do {
          int var29 = this.IX();
          int var30 = this.mem(var29, 35115);
          L = var30;
          int var31 = L & -129;
          L = var31;
          H = 20;
          int var32 = this.HL() * 2 & '\uffff';
          this.HL(var32);
          int var33 = this.HL() * 2 & '\uffff';
          this.HL(var33);
          int var34 = this.HL() * 2 & '\uffff';
          this.HL(var34);
          this.BC(2);
          this.ldir();
          int var35 = this.IX() + 1;
          int var36 = this.mem(var35, 35130);
          C = var36;
          int var37 = this.HL();
          this.wMem(var37, C, 35133);
          this.BC(6);
          this.ldir();
          int var38 = this.IX() + 1 & '\uffff';
          this.IX(var38);
          int var39 = this.IX() + 1 & '\uffff';
          this.IX(var39);
          int var40 = A - 1 & 255;
          A = var40;
        } while(A != 0);

        this.HL(34255);
        this.DE(34263);
        this.BC(7);
        this.ldir();
        this.$36147();
        this.HL(20480);
        this.DE(20481);
        this.BC(2047);
        int var41 = this.HL();
        this.wMem(var41, 0, 35169);
        this.ldir();
        this.IX(32896);
        C = 32;
        this.DE(20480);
        this.$38528();
        this.IX(34132);
        this.DE(20576);
        C = 32;
        this.$38528();
        int var42 = this.mem(32990, 35197);
        A = var42;
        C = 254;
        int var43 = A ^ A;
        A = var43;
        F = A;
        this.wMem(34262, A, 35205);

        while(true) {
          label292: {
            label213: {
              label289: {
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
                int var44 = this.mem(34271, 35273);
                A = var44;
                if (A != 3) {
                  this.$36307();
                  if (this.decPops()) {
                    break label289;
                  }
                }

                int var45 = this.mem(34255, 35281);
                A = var45;
                if (A >= 225) {
                  this.$38064();
                  if (this.decPops()) {
                    break;
                  }
                }

                int var46 = this.mem(34271, 35289);
                A = var46;
                if (A != 3) {
                  this.$38344();
                  if (this.decPops()) {
                    break label289;
                  }
                }

                int var47 = this.mem(34271, 35297);
                A = var47;
                if (A == 2) {
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

              A = 255;
              this.wMem(34257, A, 37050);
            }

            this.HL(24576);
            this.DE(16384);
            this.BC(4096);
            this.ldir();
            int var48 = this.mem(34271, 35328);
            A = var48;
            int var49 = A & 2;
            A = var49;
            F = A;
            int var50 = A;
            int var51 = this.rrc(var50);
            A = var51;
            this.HL(34258);
            int var52 = this.HL();
            int var53 = this.mem(var52, 35337);
            int var54 = A | var53;
            A = var54;
            int var55 = this.HL();
            this.mem(var55, 35337);
            F = A;
            int var56 = this.HL();
            this.wMem(var56, A, 35338);
            int var57 = this.mem(34253, 35339);
            A = var57;
            int var58 = A | A;
            A = var58;
            if (A != 0) {
              int var268 = A - 1 & 255;
              A = var268;
              F = A;
              this.wMem(34253, A, 35346);
              int var269 = A;
              int var270 = this.rlc(var269);
              A = var270;
              int var271 = A;
              int var272 = this.rlc(var271);
              A = var272;
              int var273 = A;
              int var274 = this.rlc(var273);
              A = var274;
              int var275 = A & 56;
              A = var275;
              F = A;
              this.HL(23552);
              this.DE(23553);
              this.BC(511);
              int var276 = this.HL();
              this.wMem(var276, A, 35363);
              this.ldir();
            }

            this.HL(23552);
            this.DE(22528);
            this.BC(512);
            this.ldir();
            this.IX(34175);
            this.DE(20601);
            C = 6;
            this.$38528();
            this.IX(34172);
            this.DE(20592);
            C = 3;
            this.$38528();
            int var59 = this.mem(34251, 35401);
            A = var59;
            int var60 = A + 1 & 255;
            A = var60;
            F = A;
            this.wMem(34251, A, 35405);
            if (F == 0) {
              this.IX(34175);
              int var237 = this.IX() + 4;
              int var238 = this.mem(var237, 35414) + 1;
              this.wMem(var237, var238, 35414);
              int var239 = var238 & 255;
              this.wMem(var237, var239, 35414);
              int var240 = this.IX() + 4;
              int var241 = this.mem(var240, 35417);
              A = var241;
              if (A == 58) {
                int var242 = this.IX() + 4;
                this.wMem(var242, 48, 35424);
                int var243 = this.IX() + 3;
                int var244 = this.mem(var243, 35428) + 1;
                this.wMem(var243, var244, 35428);
                int var245 = var244 & 255;
                this.wMem(var243, var245, 35428);
                int var246 = this.IX() + 3;
                int var247 = this.mem(var246, 35431);
                A = var247;
                if (A == 54) {
                  int var248 = this.IX() + 3;
                  this.wMem(var248, 48, 35438);
                  int var249 = this.IX();
                  int var250 = this.mem(var249, 35442);
                  A = var250;
                  if (A == 49) {
                    int var258 = this.IX() + 1;
                    int var259 = this.mem(var258, 35449) + 1;
                    this.wMem(var258, var259, 35449);
                    int var260 = var259 & 255;
                    this.wMem(var258, var260, 35449);
                    int var261 = this.IX() + 1;
                    int var262 = this.mem(var261, 35452);
                    A = var262;
                    if (A == 51) {
                      int var263 = this.IX() + 5;
                      int var264 = this.mem(var263, 35459);
                      A = var264;
                      if (A == 112) {
                        continue label282;
                      }

                      int var265 = this.IX();
                      this.wMem(var265, 32, 35467);
                      int var266 = this.IX() + 1;
                      this.wMem(var266, 49, 35471);
                      int var267 = this.IX() + 5;
                      this.wMem(var267, 112, 35475);
                    }
                  } else {
                    int var251 = this.IX() + 1;
                    int var252 = this.mem(var251, 35481) + 1;
                    this.wMem(var251, var252, 35481);
                    int var253 = var252 & 255;
                    this.wMem(var251, var253, 35481);
                    int var254 = this.IX() + 1;
                    int var255 = this.mem(var254, 35484);
                    A = var255;
                    if (A == 58) {
                      int var256 = this.IX() + 1;
                      this.wMem(var256, 48, 35491);
                      int var257 = this.IX();
                      this.wMem(var257, 49, 35495);
                    }
                  }
                }
              }
            }

            this.BC(65278);
            int var61 = this.in(C);
            A = var61;
            E = A;
            B = 127;
            int var62 = this.in(C);
            A = var62;
            int var63 = A | E;
            A = var63;
            F = A;
            int var64 = A & 1;
            A = var64;
            if (A == 0) {
              continue label282;
            }

            int var65 = this.mem(34272, 35515);
            A = var65;
            int var66 = A + 1 & 255;
            A = var66;
            F = A;
            this.wMem(34272, A, 35519);
            if (F != 0) {
              B = 253;
              int var235 = this.in(C);
              A = var235;
              int var236 = A & 31;
              A = var236;
              F = A;
              if (A == 31) {
                break label292;
              }

              this.DE(0);
            }

            while(true) {
              B = 2;
              int var67 = this.in(C);
              A = var67;
              int var68 = A & 31;
              A = var68;
              F = A;
              if (A != 31) {
                this.HL(39424);
                this.DE(23040);
                this.BC(256);
                this.ldir();
                int var69 = this.mem(32990, 35602);
                A = var69;
                break;
              }

              int var232 = E + 1 & 255;
              E = var232;
              if (E == 0) {
                int var233 = D + 1 & 255;
                D = var233;
                if (D == 0) {
                  int var234 = this.mem(34275, 35553);
                  A = var234;
                  if (A != 10) {
                    this.$35563();
                  }
                }
              }
            }
          }

          int var70 = this.mem(34257, 35607);
          A = var70;
          if (A == 255) {
            A = 71;

            do {
              this.HL(22528);
              this.DE(22529);
              this.BC(511);
              int var71 = this.HL();
              this.wMem(var71, A, 35852);
              this.ldir();
              E = A;
              int var72 = ~A;
              A = var72;
              F = A;
              int var73 = A & 7;
              A = var73;
              F = A;
              int var74 = A;
              int var75 = this.rlc(var74);
              A = var75;
              int var76 = A;
              int var77 = this.rlc(var76);
              A = var77;
              int var78 = A;
              int var79 = this.rlc(var78);
              A = var79;
              int var80 = A | 7;
              A = var80;
              F = A;
              D = A;
              C = E;
              int var81 = C;
              int var82 = this.rrc(var81);
              C = var82;
              int var83 = C;
              int var84 = this.rrc(var83);
              C = var84;
              int var85 = C;
              int var86 = this.rrc(var85);
              C = var86;
              int var87 = A | 16;
              A = var87;
              F = A;
              int var88 = A ^ A;
              A = var88;
              F = A;

              do {
                int var89 = A ^ 24;
                A = var89;
                F = A;
                B = D;

                do {
                  int var90 = B - 1 & 255;
                  B = var90;
                } while(B != 0);

                int var91 = C - 1 & 255;
                C = var91;
              } while(C != 0);

              A = E;
              int var92 = A - 1 & 255;
              A = var92;
              F = A;
            } while(A != 63);

            this.HL(34252);
            int var93 = this.HL();
            int var94 = this.mem(var93, 35894);
            A = var94;
            int var95 = A | A;
            A = var95;
            if (A == 0) {
              this.HL(16384);
              this.DE(16385);
              this.BC(4095);
              int var96 = this.HL();
              this.wMem(var96, 0, 35923);
              this.ldir();
              int var97 = A ^ A;
              A = var97;
              F = A;
              this.wMem(34276, A, 35928);
              this.DE(40256);
              this.HL(18575);
              C = 0;
              this.$37974();
              this.DE(40032);
              this.HL(18639);
              C = 0;
              this.$37974();

              do {
                int var98 = this.mem(34276, 35953);
                A = var98;
                C = A;
                B = 130;
                int var99 = this.BC();
                int var100 = this.mem(var99, 35959);
                A = var100;
                int var101 = A | 15;
                A = var101;
                F = A;
                L = A;
                int var102 = this.BC() + 1 & '\uffff';
                this.BC(var102);
                int var103 = this.BC();
                int var104 = this.mem(var103, 35964);
                A = var104;
                int var105 = A - 32 & 255;
                A = var105;
                F = A;
                H = A;
                this.DE(40000);
                C = 0;
                this.$37974();
                int var106 = this.mem(34276, 35976);
                A = var106;
                int var107 = ~A;
                A = var107;
                F = A;
                E = A;
                int var108 = A ^ A;
                A = var108;
                F = A;
                this.BC(64);

                do {
                  int var109 = A ^ 24;
                  A = var109;
                  F = A;
                  B = E;

                  do {
                    int var110 = B - 1 & 255;
                    B = var110;
                  } while(B != 0);

                  int var111 = C - 1 & 255;
                  C = var111;
                } while(C != 0);

                this.HL(22528);
                this.DE(22529);
                this.BC(511);
                int var112 = this.mem(34276, 36004);
                A = var112;
                int var113 = A & 12;
                A = var113;
                F = A;
                int var114 = A;
                int var115 = this.rlc(var114);
                A = var115;
                int var116 = A | 71;
                A = var116;
                F = A;
                int var117 = this.HL();
                this.wMem(var117, A, 36012);
                this.ldir();
                int var118 = A & 250;
                A = var118;
                F = A;
                int var119 = A | 2;
                A = var119;
                F = A;
                this.wMem(22991, A, 36019);
                this.wMem(22992, A, 36022);
                this.wMem(23023, A, 36025);
                this.wMem(23024, A, 36028);
                int var120 = this.mem(34276, 36031);
                A = var120;
                int var121 = A + 4 & 255;
                A = var121;
                F = A;
                this.wMem(34276, A, 36036);
              } while(A != 196);

              this.IX(34164);
              C = 4;
              this.DE(16586);
              this.$38528();
              this.IX(34168);
              C = 4;
              this.DE(16594);
              this.$38528();
              this.BC(0);
              D = 6;

              while(true) {
                int var122 = B - 1 & 255;
                B = var122;
                if (B == 0) {
                  A = C;
                  int var123 = A & 7;
                  A = var123;
                  F = A;
                  int var124 = A | 64;
                  A = var124;
                  F = A;
                  this.wMem(22730, A, 36079);
                  int var125 = A + 1 & 255;
                  A = var125;
                  F = A;
                  int var126 = A & 7;
                  A = var126;
                  F = A;
                  int var127 = A | 64;
                  A = var127;
                  F = A;
                  this.wMem(22731, A, 36087);
                  int var128 = A + 1 & 255;
                  A = var128;
                  F = A;
                  int var129 = A & 7;
                  A = var129;
                  F = A;
                  int var130 = A | 64;
                  A = var130;
                  F = A;
                  this.wMem(22732, A, 36095);
                  int var131 = A + 1 & 255;
                  A = var131;
                  F = A;
                  int var132 = A & 7;
                  A = var132;
                  F = A;
                  int var133 = A | 64;
                  A = var133;
                  F = A;
                  this.wMem(22733, A, 36103);
                  int var134 = A + 1 & 255;
                  A = var134;
                  F = A;
                  int var135 = A & 7;
                  A = var135;
                  F = A;
                  int var136 = A | 64;
                  A = var136;
                  F = A;
                  this.wMem(22738, A, 36111);
                  int var137 = A + 1 & 255;
                  A = var137;
                  F = A;
                  int var138 = A & 7;
                  A = var138;
                  F = A;
                  int var139 = A | 64;
                  A = var139;
                  F = A;
                  this.wMem(22739, A, 36119);
                  int var140 = A + 1 & 255;
                  A = var140;
                  F = A;
                  int var141 = A & 7;
                  A = var141;
                  F = A;
                  int var142 = A | 64;
                  A = var142;
                  F = A;
                  this.wMem(22740, A, 36127);
                  int var143 = A + 1 & 255;
                  A = var143;
                  F = A;
                  int var144 = A & 7;
                  A = var144;
                  F = A;
                  int var145 = A | 64;
                  A = var145;
                  F = A;
                  this.wMem(22741, A, 36135);
                  int var146 = C - 1 & 255;
                  C = var146;
                  if (C == 0) {
                    int var147 = D - 1 & 255;
                    D = var147;
                    if (D == 0) {
                      continue label282;
                    }
                  }
                }
              }
            }

            int var148 = this.HL();
            int var149 = this.mem(var148, 35899) - 1;
            int var150 = this.HL();
            this.wMem(var150, var149, 35899);
            int var151 = var149 & 255;
            int var152 = this.HL();
            this.wMem(var152, var151, 35899);
            this.HL(34263);
            this.DE(34255);
            this.BC(7);
            this.ldir();
            break;
          }

          B = 191;
          this.HL(34274);
          int var153 = this.in(C);
          A = var153;
          int var154 = A & 31;
          A = var154;
          F = A;
          if (A != 31) {
            int var227 = this.HL();
            if ((this.mem(var227, 35630) & 1) == 0) {
              int var228 = this.HL();
              int var229 = this.mem(var228, 35632);
              A = var229;
              int var230 = A ^ 3;
              A = var230;
              F = A;
              int var231 = this.HL();
              this.wMem(var231, A, 35635);
            }
          } else {
            int var155 = this.HL();
            int var156 = this.mem(var155, 35638) & -2;
            int var157 = this.HL();
            this.wMem(var157, var156, 35638);
          }

          int var158 = this.HL();
          if ((this.mem(var158, 35642) & 2) == 0) {
            int var202 = A ^ A;
            A = var202;
            F = A;
            this.wMem(34272, A, 35645);
            int var203 = this.mem(34273, 35648);
            A = var203;
            int var204 = A + 1 & 255;
            A = var204;
            F = A;
            this.wMem(34273, A, 35652);
            int var205 = A & 126;
            A = var205;
            F = A;
            int var206 = A;
            int var207 = this.rrc(var206);
            A = var207;
            E = A;
            D = 0;
            this.HL(34399);
            int var208 = this.HL();
            int var209 = this.DE();
            int var210 = var208 + var209 & '\uffff';
            this.HL(var210);
            int var211 = this.mem(34252, 35665);
            A = var211;
            int var212 = A;
            int var213 = this.rlc(var212);
            A = var213;
            int var214 = A;
            int var215 = this.rlc(var214);
            A = var215;
            int var216 = A - 28 & 255;
            A = var216;
            F = A;
            int var217 = -A & 255;
            A = var217;
            int var218 = this.HL();
            int var219 = this.mem(var218, 35674);
            int var220 = A + var219 & 255;
            A = var220;
            int var221 = this.HL();
            this.mem(var221, 35674);
            F = A;
            D = A;
            int var222 = this.mem(32990, 35676);
            A = var222;
            E = D;
            this.BC(3);

            while(true) {
              int var223 = E - 1 & 255;
              E = var223;
              if (E == 0) {
                E = D;
                int var226 = A ^ 24;
                A = var226;
              }

              int var224 = B - 1 & 255;
              B = var224;
              if (B == 0) {
                int var225 = C - 1 & 255;
                C = var225;
                if (C == 0) {
                  break;
                }
              }
            }
          }

          this.BC(61438);
          int var159 = this.in(C);
          A = var159;
          if ((A & 2) == 0) {
            int var193 = A & 16;
            A = var193;
            F = A;
            int var194 = A ^ 16;
            A = var194;
            F = A;
            int var195 = A;
            int var196 = this.rlc(var195);
            A = var196;
            D = A;
            int var197 = this.mem(34275, 35712);
            A = var197;
            if (A == 10) {
              this.BC(63486);
              int var198 = this.in(C);
              A = var198;
              int var199 = ~A;
              A = var199;
              F = A;
              int var200 = A & 31;
              A = var200;
              F = A;
              int var201 = A | D;
              A = var201;
              F = A;
              this.wMem(33824, A, 35729);
              break;
            }
          }

          int var160 = this.mem(34275, 35735);
          A = var160;
          if (A != 10) {
            int var161 = this.mem(33824, 35743);
            A = var161;
            if (A == 28) {
              int var162 = this.mem(34255, 35751);
              A = var162;
              if (A == 208) {
                int var163 = this.mem(34275, 35759);
                A = var163;
                int var164 = A;
                int var165 = this.rlc(var164);
                A = var165;
                E = A;
                D = 0;
                this.IX(34279);
                int var166 = this.IX();
                int var167 = this.DE();
                int var168 = var166 + var167 & '\uffff';
                this.IX(var168);
                this.BC(64510);
                int var169 = this.in(C);
                A = var169;
                int var170 = A & 31;
                A = var170;
                F = A;
                int var171 = this.IX();
                this.mem(var171, 35779);
                int var172 = this.IX();
                this.mem(var172, 35779);
                int var173 = this.IX();
                int var174 = this.mem(var173, 35782);
                if (A != var174) {
                  if (A != 31) {
                    int var188 = this.IX();
                    this.mem(var188, 35789);
                    int var189 = this.IX();
                    this.mem(var189, 35789);
                    int var190 = this.IX();
                    int var191 = this.mem(var190, 35792);
                    if (A != var191) {
                      int var192 = A ^ A;
                      A = var192;
                      F = A;
                      this.wMem(34275, A, 35796);
                    }
                  }
                } else {
                  B = 223;
                  int var175 = this.in(C);
                  A = var175;
                  int var176 = A & 31;
                  A = var176;
                  F = A;
                  int var177 = this.IX() + 1;
                  this.mem(var177, 35808);
                  int var178 = this.IX() + 1;
                  this.mem(var178, 35808);
                  int var179 = this.IX() + 1;
                  int var180 = this.mem(var179, 35811);
                  if (A != var180) {
                    if (A != 31) {
                      int var183 = this.IX();
                      this.mem(var183, 35818);
                      int var184 = this.IX();
                      this.mem(var184, 35818);
                      int var185 = this.IX();
                      int var186 = this.mem(var185, 35821);
                      if (A != var186) {
                        int var187 = A ^ A;
                        A = var187;
                        F = A;
                        this.wMem(34275, A, 35825);
                      }
                    }
                  } else {
                    int var181 = this.mem(34275, 35831);
                    A = var181;
                    int var182 = A + 1 & 255;
                    A = var182;
                    F = A;
                    this.wMem(34275, A, 35835);
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
    A = var1;
    this.HL(20640);
    int var2 = A | A;
    A = var2;
    if (A != 0) {
      B = A;

      do {
        C = 0;
        int var3 = this.mem(34273, 35224);
        A = var3;
        int var4 = A;
        int var5 = this.rlc(var4);
        A = var5;
        int var6 = A;
        int var7 = this.rlc(var6);
        A = var7;
        int var8 = A;
        int var9 = this.rlc(var8);
        A = var9;
        int var10 = A & 96;
        A = var10;
        F = A;
        E = A;
        D = 157;
        this.$37974();
        int var11 = this.HL() + 1 & '\uffff';
        this.HL(var11);
        int var12 = this.HL() + 1 & '\uffff';
        this.HL(var12);
        int var13 = B - 1 & 255;
        B = var13;
      } while(B != 0);

    }
  }

  public void $35563() {
    this.HL(22528);
    int var1 = this.HL();
    int var2 = this.mem(var1, 35566);
    A = var2;
    int var3 = A & 7;
    A = var3;
    F = A;

    do {
      int var4 = this.HL();
      int var5 = this.mem(var4, 35571);
      A = var5;
      int var6 = A + 3 & 255;
      A = var6;
      F = A;
      int var7 = A & 7;
      A = var7;
      F = A;
      D = A;
      int var8 = this.HL();
      int var9 = this.mem(var8, 35577);
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
      int var13 = this.HL();
      this.wMem(var13, A, 35583);
      int var14 = this.HL() + 1 & '\uffff';
      this.HL(var14);
      A = H;
    } while(A != 91);

  }

  public void $36147() {
    this.$36203();
    this.IX(24064);
    A = 112;
    this.wMem(36189, A, 36156);
    this.$36171();
    this.IX(24320);
    A = 120;
    this.wMem(36189, A, 36168);
    this.$36171();
  }

  public void $36171() {
    C = 0;

    do {
      E = C;
      int var1 = this.IX();
      int var2 = this.mem(var1, 36174);
      A = var2;
      this.HL(32928);
      this.BC(54);
      this.cpir();
      C = E;
      B = 8;
      D = 120;

      do {
        int var3 = this.HL();
        int var4 = this.mem(var3, 36190);
        A = var4;
        int var5 = this.DE();
        this.wMem(var5, A, 36191);
        int var6 = this.HL() + 1 & '\uffff';
        this.HL(var6);
        int var7 = D + 1 & 255;
        D = var7;
        int var8 = B - 1 & 255;
        B = var8;
      } while(B != 0);

      int var9 = this.IX() + 1 & '\uffff';
      this.IX(var9);
      int var10 = C + 1 & 255;
      C = var10;
    } while(C != 0);

  }

  public void $36203() {
    this.HL(32768);
    this.IX(24064);

    do {
      int var1 = this.HL();
      int var2 = this.mem(var1, 36210);
      A = var2;
      int var3 = A;
      int var4 = this.rlc(var3);
      A = var4;
      int var5 = A;
      int var6 = this.rlc(var5);
      A = var6;
      this.$36288();
      int var7 = this.HL();
      int var8 = this.mem(var7, 36216);
      A = var8;
      int var9 = A;
      int var10 = this.rrc(var9);
      A = var10;
      int var11 = A;
      int var12 = this.rrc(var11);
      A = var12;
      int var13 = A;
      int var14 = this.rrc(var13);
      A = var14;
      int var15 = A;
      int var16 = this.rrc(var15);
      A = var16;
      this.$36288();
      int var17 = this.HL();
      int var18 = this.mem(var17, 36224);
      A = var18;
      int var19 = A;
      int var20 = this.rrc(var19);
      A = var20;
      int var21 = A;
      int var22 = this.rrc(var21);
      A = var22;
      this.$36288();
      int var23 = this.HL();
      int var24 = this.mem(var23, 36230);
      A = var24;
      this.$36288();
      int var25 = this.HL() + 1 & '\uffff';
      this.HL(var25);
      A = L;
      int var26 = A & 128;
      A = var26;
    } while(A == 0);

    int var27 = this.mem(32985, 36240);
    A = var27;
    int var28 = A | A;
    A = var28;
    if (A != 0) {
      int var44 = this.mem16(32983, 36246);
      this.HL(var44);
      B = A;
      int var45 = this.mem(32973, 36250);
      A = var45;

      do {
        int var46 = this.HL();
        this.wMem(var46, A, 36253);
        int var47 = this.HL() + 1 & '\uffff';
        this.HL(var47);
        int var48 = B - 1 & 255;
        B = var48;
      } while(B != 0);
    }

    int var29 = this.mem(32989, 36257);
    A = var29;
    int var30 = A | A;
    A = var30;
    if (A != 0) {
      int var31 = this.mem16(32987, 36262);
      this.HL(var31);
      int var32 = this.mem(32986, 36265);
      A = var32;
      int var33 = A & 1;
      A = var33;
      F = A;
      int var34 = A;
      int var35 = this.rlc(var34);
      A = var35;
      int var36 = A + 223 & 255;
      A = var36;
      F = A;
      E = A;
      D = 255;
      int var37 = this.mem(32989, 36276);
      A = var37;
      B = A;
      int var38 = this.mem(32964, 36280);
      A = var38;

      do {
        int var39 = this.HL();
        this.wMem(var39, A, 36283);
        int var40 = this.HL();
        int var41 = this.DE();
        int var42 = var40 + var41 & '\uffff';
        this.HL(var42);
        int var43 = B - 1 & 255;
        B = var43;
      } while(B != 0);

    }
  }

  public void $36288() {
    int var1 = A & 3;
    A = var1;
    F = A;
    C = A;
    int var2 = A;
    int var3 = this.rlc(var2);
    A = var3;
    int var4 = A;
    int var5 = this.rlc(var4);
    A = var5;
    int var6 = A;
    int var7 = this.rlc(var6);
    A = var7;
    int var8 = A + C & 255;
    A = var8;
    F = A;
    int var9 = A + 160 & 255;
    A = var9;
    F = A;
    E = A;
    D = 128;
    int var10 = this.DE();
    int var11 = this.mem(var10, 36300);
    A = var11;
    int var12 = this.IX();
    this.wMem(var12, A, 36301);
    int var13 = this.IX() + 1 & '\uffff';
    this.IX(var13);
  }

  public void $36307() {
    int var1 = this.mem(34262, 36307);
    A = var1;
    int var2 = A - 1 & 255;
    A = var2;
    F = A;
    if ((A & 128) != 0) {
      int var3 = this.mem(34257, 36316);
      A = var3;
      if (A == 1) {
        int var51 = this.mem(34261, 36323);
        A = var51;
        int var52 = A & 254;
        A = var52;
        F = A;
        int var53 = A - 8 & 255;
        A = var53;
        F = A;
        this.HL(34255);
        int var54 = this.HL();
        int var55 = this.mem(var54, 36333);
        int var56 = A + var55 & 255;
        A = var56;
        int var57 = this.HL();
        this.mem(var57, 36333);
        F = A;
        int var58 = this.HL();
        this.wMem(var58, A, 36334);
        if (A >= 240) {
          return;
        }

        this.$36508();
        int var59 = this.mem(32946, 36343);
        A = var59;
        int var60 = this.HL();
        this.mem(var60, 36346);
        int var61 = this.HL();
        this.mem(var61, 36346);
        int var62 = this.HL();
        int var63 = this.mem(var62, 36347);
        if (A == var63) {
          return;
        }

        int var64 = this.HL() + 1 & '\uffff';
        this.HL(var64);
        int var65 = this.HL();
        this.mem(var65, 36351);
        int var66 = this.HL();
        this.mem(var66, 36351);
        int var67 = this.HL();
        int var68 = this.mem(var67, 36352);
        if (A == var68) {
          return;
        }

        int var69 = this.mem(34261, 36355);
        A = var69;
        int var70 = A + 1 & 255;
        A = var70;
        F = A;
        this.wMem(34261, A, 36359);
        int var71 = A - 8 & 255;
        A = var71;
        int var72 = -A & 255;
        A = var72;
        int var73 = A + 1 & 255;
        A = var73;
        F = A;
        int var74 = A;
        int var75 = this.rlc(var74);
        A = var75;
        int var76 = A;
        int var77 = this.rlc(var76);
        A = var77;
        int var78 = A;
        int var79 = this.rlc(var78);
        A = var79;
        D = A;
        C = 32;
        int var80 = this.mem(32990, 36376);
        A = var80;

        do {
          int var81 = A ^ 24;
          A = var81;
          F = A;
          B = D;

          do {
            int var82 = B - 1 & 255;
            B = var82;
          } while(B != 0);

          int var83 = C - 1 & 255;
          C = var83;
        } while(C != 0);

        int var84 = this.mem(34261, 36389);
        A = var84;
        if (A == 18 || A != 16 && A != 13) {
          return;
        }
      }

      int var4 = this.mem(34255, 36406);
      A = var4;
      int var5 = A & 14;
      A = var5;
      if (A == 0) {
        int var27 = this.mem16(34259, 36413);
        this.HL(var27);
        this.DE(64);
        int var28 = this.HL();
        int var29 = this.DE();
        int var30 = var28 + var29 & '\uffff';
        this.HL(var30);
        if ((H & 2) != 0) {
          return;
        }

        int var31 = this.mem(32955, 36425);
        A = var31;
        int var32 = this.HL();
        this.mem(var32, 36428);
        int var33 = this.HL();
        this.mem(var33, 36428);
        int var34 = this.HL();
        int var35 = this.mem(var34, 36429);
        if (A != var35) {
          int var36 = this.HL() + 1 & '\uffff';
          this.HL(var36);
          int var37 = this.mem(32955, 36432);
          A = var37;
          int var38 = this.HL();
          this.mem(var38, 36435);
          int var39 = this.HL();
          this.mem(var39, 36435);
          int var40 = this.HL();
          int var41 = this.mem(var40, 36436);
          if (A != var41) {
            int var42 = this.mem(32928, 36438);
            A = var42;
            int var43 = this.HL();
            int var44 = this.mem(var43, 36441);
            int var45 = this.HL();
            this.mem(var45, 36441);
            int var46 = A - var44;
            F = var46;
            if (F != 0) {
              return;
            }

            int var47 = this.HL();
            this.mem(var47, 36446);
            int var48 = this.HL();
            this.mem(var48, 36446);
            int var49 = this.HL();
            int var50 = this.mem(var49, 36447);
            if (A != var50) {
              return;
            }
          }
        }
      }

      int var6 = this.mem(34257, 36450);
      A = var6;
      if (A != 1) {
        this.HL(34256);
        int var7 = this.HL();
        int var8 = this.mem(var7, 36461) & -3;
        int var9 = this.HL();
        this.wMem(var9, var8, 36461);
        int var10 = this.mem(34257, 36463);
        A = var10;
        int var11 = A | A;
        A = var11;
        if (A != 0) {
          int var12 = A + 1 & 255;
          A = var12;
          F = A;
          if (A == 16) {
            A = 12;
          }

          this.wMem(34257, A, 36477);
          int var13 = A;
          int var14 = this.rlc(var13);
          A = var14;
          int var15 = A;
          int var16 = this.rlc(var15);
          A = var16;
          int var17 = A;
          int var18 = this.rlc(var17);
          A = var18;
          int var19 = A;
          int var20 = this.rlc(var19);
          A = var20;
          D = A;
          C = 32;
          int var21 = this.mem(32990, 36487);
          A = var21;

          do {
            int var22 = A ^ 24;
            A = var22;
            F = A;
            B = D;

            do {
              int var23 = B - 1 & 255;
              B = var23;
            } while(B != 0);

            int var24 = C - 1 & 255;
            C = var24;
          } while(C != 0);

          int var25 = this.mem(34255, 36500);
          A = var25;
          int var26 = A + 8 & 255;
          A = var26;
          F = A;
          this.wMem(34255, A, 36505);
          this.$36508();
        }
      }
    }

  }

  public void $36508() {
    int var1 = A & 240;
    A = var1;
    F = A;
    L = A;
    int var2 = A ^ A;
    A = var2;
    F = A;
    int var3 = L;
    int var4 = this.rlc(var3);
    L = var4;
    int var5 = A + 92 & 255;
    A = var5;
    F = A;
    H = A;
    int var6 = this.mem(34259, 36517);
    A = var6;
    int var7 = A & 31;
    A = var7;
    F = A;
    int var8 = A | L;
    A = var8;
    F = A;
    L = A;
    int var9 = this.HL();
    this.wMem16(34259, var9, 36524);
  }

  public void $37056() {
    this.IX(33024);

    while(true) {
      int var1 = this.IX();
      int var2 = this.mem(var1, 37060);
      A = var2;
      if (A == 255) {
        return;
      }

      int var3 = A & 3;
      A = var3;
      if (A != 0) {
        if (A != 1) {
          if (A != 2) {
            int var71 = this.IX();
            if ((this.mem(var71, 37085) & 128) != 0) {
              int var89 = this.IX() + 1;
              int var90 = this.mem(var89, 37087);
              A = var90;
              if ((A & 128) != 0) {
                int var93 = A - 2 & 255;
                A = var93;
                F = A;
                if (A < 148) {
                  int var94 = A - 2 & 255;
                  A = var94;
                  F = A;
                  if (A == 128) {
                    int var95 = A ^ A;
                    A = var95;
                  }
                }
              } else {
                int var91 = A + 2 & 255;
                A = var91;
                F = A;
                if (A < 18) {
                  int var92 = A + 2 & 255;
                  A = var92;
                }
              }
            } else {
              int var72 = this.IX() + 1;
              int var73 = this.mem(var72, 37119);
              A = var73;
              if ((A & 128) == 0) {
                int var86 = A - 2 & 255;
                A = var86;
                F = A;
                if (A < 20) {
                  int var87 = A - 2 & 255;
                  A = var87;
                  F = A;
                  int var88 = A | A;
                  A = var88;
                  if (A == 0) {
                    A = 128;
                  }
                }
              } else {
                int var74 = A + 2 & 255;
                A = var74;
                F = A;
                if (A < 146) {
                  int var85 = A + 2 & 255;
                  A = var85;
                  F = A;
                }
              }
            }

            int var75 = this.IX() + 1;
            this.wMem(var75, A, 37149);
            int var76 = A & 127;
            A = var76;
            F = A;
            int var77 = this.IX() + 7;
            this.mem(var77, 37154);
            int var78 = this.IX() + 7;
            this.mem(var78, 37154);
            int var79 = this.IX() + 7;
            int var80 = this.mem(var79, 37157);
            if (A == var80) {
              int var81 = this.IX();
              int var82 = this.mem(var81, 37160);
              A = var82;
              int var83 = A ^ 128;
              A = var83;
              F = A;
              int var84 = this.IX();
              this.wMem(var84, A, 37165);
            }
          } else {
            label81: {
              int var40 = this.IX();
              int var41 = this.mem(var40, 37247);
              A = var41;
              int var42 = A ^ 8;
              A = var42;
              F = A;
              int var43 = this.IX();
              this.wMem(var43, A, 37252);
              int var44 = A & 24;
              A = var44;
              if (A != 0) {
                int var67 = this.IX();
                int var68 = this.mem(var67, 37259);
                A = var68;
                int var69 = A + 32 & 255;
                A = var69;
                F = A;
                int var70 = this.IX();
                this.wMem(var70, A, 37264);
              }

              int var45 = this.IX() + 3;
              int var46 = this.mem(var45, 37267);
              A = var46;
              int var47 = this.IX() + 4;
              int var48 = this.mem(var47, 37270);
              int var49 = A + var48 & 255;
              A = var49;
              int var50 = this.IX() + 4;
              this.mem(var50, 37270);
              F = A;
              int var51 = this.IX() + 3;
              this.wMem(var51, A, 37273);
              int var52 = this.IX() + 7;
              this.mem(var52, 37276);
              int var53 = this.IX() + 7;
              this.mem(var53, 37276);
              int var54 = this.IX() + 7;
              int var55 = this.mem(var54, 37279);
              if (A < var55) {
                int var60 = this.IX() + 6;
                this.mem(var60, 37281);
                int var61 = this.IX() + 6;
                this.mem(var61, 37281);
                int var62 = this.IX() + 6;
                int var63 = this.mem(var62, 37284);
                if (A != var63 && F >= 0) {
                  break label81;
                }

                int var64 = this.IX() + 6;
                int var65 = this.mem(var64, 37288);
                A = var65;
                int var66 = this.IX() + 3;
                this.wMem(var66, A, 37291);
              }

              int var56 = this.IX() + 4;
              int var57 = this.mem(var56, 37294);
              A = var57;
              int var58 = -A & 255;
              A = var58;
              int var59 = this.IX() + 4;
              this.wMem(var59, A, 37299);
            }
          }
        } else {
          int var7 = this.IX();
          if ((this.mem(var7, 37175) & 128) == 0) {
            int var24 = this.IX();
            int var25 = this.mem(var24, 37177);
            A = var25;
            int var26 = A - 32 & 255;
            A = var26;
            F = A;
            int var27 = A & 127;
            A = var27;
            F = A;
            int var28 = this.IX();
            this.wMem(var28, A, 37184);
            if (A >= 96) {
              int var29 = this.IX() + 2;
              int var30 = this.mem(var29, 37191);
              A = var30;
              int var31 = A & 31;
              A = var31;
              F = A;
              int var32 = this.IX() + 6;
              this.mem(var32, 37196);
              int var33 = this.IX() + 6;
              this.mem(var33, 37196);
              int var34 = this.IX() + 6;
              int var35 = this.mem(var34, 37199);
              if (A != var35) {
                int var37 = this.IX() + 2;
                int var38 = this.mem(var37, 37201) - 1;
                this.wMem(var37, var38, 37201);
                int var39 = var38 & 255;
                this.wMem(var37, var39, 37201);
              } else {
                int var36 = this.IX();
                this.wMem(var36, 129, 37206);
              }
            }
          } else {
            int var8 = this.IX();
            int var9 = this.mem(var8, 37212);
            A = var9;
            int var10 = A + 32 & 255;
            A = var10;
            F = A;
            int var11 = A | 128;
            A = var11;
            F = A;
            int var12 = this.IX();
            this.wMem(var12, A, 37219);
            if (A < 160) {
              int var13 = this.IX() + 2;
              int var14 = this.mem(var13, 37226);
              A = var14;
              int var15 = A & 31;
              A = var15;
              F = A;
              int var16 = this.IX() + 7;
              this.mem(var16, 37231);
              int var17 = this.IX() + 7;
              this.mem(var17, 37231);
              int var18 = this.IX() + 7;
              int var19 = this.mem(var18, 37234);
              if (A != var19) {
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
      A = var2;
      if (A == 255) {
        return;
      }

      int var3 = A & 7;
      A = var3;
      if (A != 0) {
        if (A != 3) {
          if (A != 4) {
            int var187 = this.IX() + 3;
            int var188 = this.mem(var187, 37334);
            E = var188;
            D = 130;
            int var189 = this.DE();
            int var190 = this.mem(var189, 37339);
            A = var190;
            L = A;
            int var191 = this.IX() + 2;
            int var192 = this.mem(var191, 37341);
            A = var192;
            int var193 = A & 31;
            A = var193;
            F = A;
            int var194 = A + L & 255;
            A = var194;
            F = A;
            L = A;
            A = E;
            int var195 = A;
            int var196 = this.rlc(var195);
            A = var196;
            int var197 = A & 1;
            A = var197;
            F = A;
            int var198 = A | 92;
            A = var198;
            F = A;
            H = A;
            this.DE(31);
            int var199 = this.IX() + 1;
            int var200 = this.mem(var199, 37358);
            A = var200;
            int var201 = A & 15;
            A = var201;
            F = A;
            int var202 = A + 56 & 255;
            A = var202;
            F = A;
            int var203 = A & 71;
            A = var203;
            F = A;
            C = A;
            int var204 = this.HL();
            int var205 = this.mem(var204, 37368);
            A = var205;
            int var206 = A & 56;
            A = var206;
            F = A;
            int var207 = A ^ C;
            A = var207;
            F = A;
            C = A;
            int var208 = this.HL();
            this.wMem(var208, C, 37373);
            int var209 = this.HL() + 1 & '\uffff';
            this.HL(var209);
            int var210 = this.HL();
            this.wMem(var210, C, 37375);
            int var211 = this.HL();
            int var212 = this.DE();
            int var213 = var211 + var212 & '\uffff';
            this.HL(var213);
            int var214 = this.HL();
            this.wMem(var214, C, 37377);
            int var215 = this.HL() + 1 & '\uffff';
            this.HL(var215);
            int var216 = this.HL();
            this.wMem(var216, C, 37379);
            int var217 = this.IX() + 3;
            int var218 = this.mem(var217, 37380);
            A = var218;
            int var219 = A & 14;
            A = var219;
            if (A != 0) {
              int var245 = this.HL();
              int var246 = this.DE();
              int var247 = var245 + var246 & '\uffff';
              this.HL(var247);
              int var248 = this.HL();
              this.wMem(var248, C, 37388);
              int var249 = this.HL() + 1 & '\uffff';
              this.HL(var249);
              int var250 = this.HL();
              this.wMem(var250, C, 37390);
            }

            C = 1;
            int var220 = this.IX() + 1;
            int var221 = this.mem(var220, 37393);
            A = var221;
            int var222 = this.IX();
            int var223 = this.mem(var222, 37396);
            int var224 = A & var223;
            A = var224;
            int var225 = this.IX();
            this.mem(var225, 37396);
            F = A;
            int var226 = this.IX() + 2;
            int var227 = this.mem(var226, 37399);
            int var228 = A | var227;
            A = var228;
            int var229 = this.IX() + 2;
            this.mem(var229, 37399);
            F = A;
            int var230 = A & 224;
            A = var230;
            F = A;
            E = A;
            int var231 = this.IX() + 5;
            int var232 = this.mem(var231, 37405);
            D = var232;
            H = 130;
            int var233 = this.IX() + 3;
            int var234 = this.mem(var233, 37410);
            L = var234;
            int var235 = this.IX() + 2;
            int var236 = this.mem(var235, 37413);
            A = var236;
            int var237 = A & 31;
            A = var237;
            F = A;
            int var238 = this.HL();
            int var239 = this.mem(var238, 37418);
            int var240 = A | var239;
            A = var240;
            int var241 = this.HL();
            this.mem(var241, 37418);
            F = A;
            int var242 = this.HL() + 1 & '\uffff';
            this.HL(var242);
            int var243 = this.HL();
            int var244 = this.mem(var243, 37420);
            H = var244;
            L = A;
            this.$37974();
            if (F != 0) {
              this.incPops();
              return;
            }

            this.incPops();
          } else {
            int var131 = this.IX();
            if ((this.mem(var131, 37435) & 128) == 0) {
              int var184 = this.IX() + 4;
              int var185 = this.mem(var184, 37437) - 1;
              this.wMem(var184, var185, 37437);
              int var186 = var185 & 255;
              this.wMem(var184, var186, 37437);
              C = 44;
            } else {
              int var132 = this.IX() + 4;
              int var133 = this.mem(var132, 37444) + 1;
              this.wMem(var132, var133, 37444);
              int var134 = var133 & 255;
              this.wMem(var132, var134, 37444);
              C = 244;
            }

            int var135 = this.IX() + 4;
            int var136 = this.mem(var135, 37449);
            A = var136;
            if (A != C) {
              int var137 = A & 224;
              A = var137;
              if (A == 0) {
                int var138 = this.IX() + 2;
                int var139 = this.mem(var138, 37479);
                E = var139;
                D = 130;
                int var140 = this.DE();
                int var141 = this.mem(var140, 37484);
                A = var141;
                int var142 = this.IX() + 4;
                int var143 = this.mem(var142, 37485);
                int var144 = A + var143 & 255;
                A = var144;
                int var145 = this.IX() + 4;
                this.mem(var145, 37485);
                F = A;
                L = A;
                A = E;
                int var146 = A & 128;
                A = var146;
                F = A;
                int var147 = A;
                int var148 = this.rlc(var147);
                A = var148;
                int var149 = A | 92;
                A = var149;
                F = A;
                H = A;
                int var150 = this.IX() + 5;
                this.wMem(var150, 0, 37496);
                int var151 = this.HL();
                int var152 = this.mem(var151, 37500);
                A = var152;
                int var153 = A & 7;
                A = var153;
                F = A;
                if (A == 7) {
                  int var177 = this.IX() + 5;
                  int var178 = this.mem(var177, 37507) - 1;
                  this.wMem(var177, var178, 37507);
                  int var179 = var178 & 255;
                  this.wMem(var177, var179, 37507);
                }

                int var154 = this.HL();
                int var155 = this.mem(var154, 37510);
                A = var155;
                int var156 = A | 7;
                A = var156;
                F = A;
                int var157 = this.HL();
                this.wMem(var157, A, 37513);
                int var158 = this.DE() + 1 & '\uffff';
                this.DE(var158);
                int var159 = this.DE();
                int var160 = this.mem(var159, 37515);
                A = var160;
                H = A;
                int var161 = H - 1 & 255;
                H = var161;
                F = H;
                int var162 = this.IX() + 6;
                int var163 = this.mem(var162, 37518);
                A = var163;
                int var164 = this.HL();
                this.wMem(var164, A, 37521);
                int var165 = H + 1 & 255;
                H = var165;
                F = H;
                int var166 = this.HL();
                int var167 = this.mem(var166, 37523);
                A = var167;
                int var168 = this.IX() + 5;
                int var169 = this.mem(var168, 37524);
                int var170 = A & var169;
                A = var170;
                int var171 = this.IX() + 5;
                this.mem(var171, 37524);
                if (A != 0) {
                  return;
                }

                int var172 = this.HL();
                this.wMem(var172, 255, 37530);
                int var173 = H + 1 & 255;
                H = var173;
                F = H;
                int var174 = this.IX() + 6;
                int var175 = this.mem(var174, 37533);
                A = var175;
                int var176 = this.HL();
                this.wMem(var176, A, 37536);
              }
            } else {
              this.BC(640);
              int var180 = this.mem(32990, 37458);
              A = var180;

              do {
                int var181 = A ^ 24;
                A = var181;

                do {
                  int var182 = B - 1 & 255;
                  B = var182;
                } while(B != 0);

                B = C;
                int var183 = C - 1 & 255;
                C = var183;
              } while(C != 0);
            }
          }
        } else {
          this.IY(33280);
          int var7 = this.IX() + 9;
          this.wMem(var7, 0, 37544);
          int var8 = this.IX() + 2;
          int var9 = this.mem(var8, 37548);
          A = var9;
          int var10 = this.IX() + 3;
          this.wMem(var10, A, 37551);
          int var11 = this.IX() + 5;
          this.wMem(var11, 128, 37554);

          while(true) {
            label115: {
              int var12 = this.IY();
              int var13 = this.mem(var12, 37558);
              A = var13;
              int var14 = this.IX() + 3;
              int var15 = this.mem(var14, 37561);
              int var16 = A + var15 & 255;
              A = var16;
              int var17 = this.IX() + 3;
              this.mem(var17, 37561);
              F = A;
              L = A;
              int var18 = this.IY() + 1;
              int var19 = this.mem(var18, 37565);
              H = var19;
              int var20 = this.mem(34262, 37568);
              A = var20;
              int var21 = A | A;
              A = var21;
              if (A == 0) {
                int var121 = this.IX() + 5;
                int var122 = this.mem(var121, 37574);
                A = var122;
                int var123 = this.HL();
                int var124 = this.mem(var123, 37577);
                int var125 = A & var124;
                A = var125;
                int var126 = this.HL();
                this.mem(var126, 37577);
                if (A == 0) {
                  break label115;
                }

                int var127 = this.IX() + 9;
                int var128 = this.mem(var127, 37580);
                A = var128;
                this.wMem(34262, A, 37583);
                int var129 = this.IX() + 11;
                int var130 = this.mem(var129, 37586) | 1;
                this.wMem(var129, var130, 37586);
              }

              int var22 = this.IX() + 9;
              this.mem(var22, 37590);
              int var23 = this.IX() + 9;
              this.mem(var23, 37590);
              int var24 = this.IX() + 9;
              int var25 = this.mem(var24, 37593);
              if (A == var25) {
                int var113 = this.IX() + 11;
                if ((this.mem(var113, 37599) & 1) != 0) {
                  int var114 = this.IX() + 3;
                  int var115 = this.mem(var114, 37601);
                  B = var115;
                  int var116 = this.IX() + 5;
                  int var117 = this.mem(var116, 37604);
                  A = var117;
                  C = 1;
                  if (A >= 4) {
                    C = 0;
                    if (A >= 16) {
                      int var120 = B - 1 & 255;
                      B = var120;
                      F = B;
                      C = 3;
                      if (A >= 64) {
                        C = 2;
                      }
                    }
                  }

                  int var118 = this.BC();
                  this.wMem16(34258, var118, 37628);
                  A = IYL;
                  int var119 = A - 16 & 255;
                  A = var119;
                  F = A;
                  this.wMem(34255, A, 37636);
                  this.$36508();
                }
              }
            }

            int var26 = this.IX() + 5;
            int var27 = this.mem(var26, 37646);
            A = var27;
            int var28 = this.HL();
            int var29 = this.mem(var28, 37649);
            int var30 = A | var29;
            A = var30;
            int var31 = this.HL();
            this.mem(var31, 37649);
            F = A;
            int var32 = this.HL();
            this.wMem(var32, A, 37650);
            int var33 = this.IX() + 9;
            int var34 = this.mem(var33, 37651);
            A = var34;
            int var35 = this.IX() + 1;
            int var36 = this.mem(var35, 37654);
            int var37 = A + var36 & 255;
            A = var37;
            int var38 = this.IX() + 1;
            this.mem(var38, 37654);
            F = A;
            L = A;
            int var39 = L | 128;
            L = var39;
            H = 131;
            int var40 = this.HL();
            int var41 = this.mem(var40, 37662);
            E = var41;
            D = 0;
            int var42 = this.IY();
            int var43 = this.DE();
            int var44 = var42 + var43 & '\uffff';
            this.IY(var44);
            int var45 = L & -129;
            L = var45;
            int var46 = this.HL();
            int var47 = this.mem(var46, 37669);
            A = var47;
            int var48 = A | A;
            A = var48;
            if (A != 0) {
              B = A;
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
                    int var111 = this.mem(var110, 37690) - 1;
                    this.wMem(var110, var111, 37690);
                    int var112 = var111 & 255;
                    this.wMem(var110, var112, 37690);
                  }

                  int var109 = B - 1 & 255;
                  B = var109;
                } while(B != 0);
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

                  int var101 = B - 1 & 255;
                  B = var101;
                } while(B != 0);
              }
            }

            int var49 = this.IX() + 9;
            int var50 = this.mem(var49, 37712);
            A = var50;
            int var51 = this.IX() + 4;
            this.mem(var51, 37715);
            int var52 = this.IX() + 4;
            this.mem(var52, 37715);
            int var53 = this.IX() + 4;
            int var54 = this.mem(var53, 37718);
            if (A == var54) {
              int var55 = this.mem(34262, 37726);
              A = var55;
              if ((A & 128) != 0) {
                int var90 = A + 1 & 255;
                A = var90;
                F = A;
                this.wMem(34262, A, 37734);
                int var91 = this.IX() + 11;
                int var92 = this.mem(var91, 37737) & -2;
                this.wMem(var91, var92, 37737);
              } else {
                int var56 = this.IX() + 11;
                if ((this.mem(var56, 37747) & 1) != 0) {
                  int var57 = this.mem(34256, 37749);
                  A = var57;
                  if ((A & 2) != 0) {
                    int var58 = A;
                    int var59 = this.rrc(var58);
                    A = var59;
                    int var60 = this.IX();
                    int var61 = this.mem(var60, 37757);
                    int var62 = A ^ var61;
                    A = var62;
                    int var63 = this.IX();
                    this.mem(var63, 37757);
                    F = A;
                    int var64 = A;
                    int var65 = this.rlc(var64);
                    A = var65;
                    int var66 = A;
                    int var67 = this.rlc(var66);
                    A = var67;
                    int var68 = A & 2;
                    A = var68;
                    F = A;
                    int var69 = A - 1 & 255;
                    A = var69;
                    F = A;
                    this.HL(34262);
                    int var70 = this.HL();
                    int var71 = this.mem(var70, 37768);
                    int var72 = A + var71 & 255;
                    A = var72;
                    int var73 = this.HL();
                    this.mem(var73, 37768);
                    F = A;
                    int var74 = this.HL();
                    this.wMem(var74, A, 37769);
                    int var75 = this.mem(33003, 37770);
                    A = var75;
                    C = A;
                    int var76 = this.mem(33824, 37774);
                    A = var76;
                    if (A == C) {
                      int var87 = this.HL();
                      int var88 = this.mem(var87, 37780);
                      A = var88;
                      if (A < 12) {
                        int var89 = this.HL();
                        this.wMem(var89, 12, 37785);
                      }
                    }

                    int var77 = this.HL();
                    int var78 = this.mem(var77, 37787);
                    A = var78;
                    int var79 = this.IX() + 4;
                    this.mem(var79, 37788);
                    int var80 = this.IX() + 4;
                    this.mem(var80, 37788);
                    int var81 = this.IX() + 4;
                    int var82 = this.mem(var81, 37791);
                    if (A >= var82 && F != 0) {
                      int var83 = this.HL();
                      this.wMem(var83, 240, 37795);
                      int var84 = this.mem(34255, 37797);
                      A = var84;
                      int var85 = A & 248;
                      A = var85;
                      F = A;
                      this.wMem(34255, A, 37802);
                      int var86 = A ^ A;
                      A = var86;
                      F = A;
                      this.wMem(34257, A, 37806);
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
    H = 164;
    int var1 = this.mem(41983, 37843);
    A = var1;
    L = A;

    do {
      int var2 = this.HL();
      int var3 = this.mem(var2, 37847);
      C = var3;
      int var4 = C & -129;
      C = var4;
      int var5 = this.mem(33824, 37850);
      A = var5;
      int var6 = A | 64;
      A = var6;
      F = A;
      if (A == C) {
        int var8 = this.HL();
        int var9 = this.mem(var8, 37858);
        A = var9;
        int var10 = A;
        int var11 = this.rlc(var10);
        A = var11;
        int var12 = A & 1;
        A = var12;
        F = A;
        int var13 = A + 92 & 255;
        A = var13;
        F = A;
        D = A;
        int var14 = H + 1 & 255;
        H = var14;
        F = H;
        int var15 = this.HL();
        int var16 = this.mem(var15, 37866);
        E = var16;
        int var17 = H - 1 & 255;
        H = var17;
        F = H;
        int var18 = this.DE();
        int var19 = this.mem(var18, 37868);
        A = var19;
        int var20 = A & 7;
        A = var20;
        F = A;
        if (A != 7) {
          int var21 = this.mem(34251, 37936);
          A = var21;
          int var22 = A + L & 255;
          A = var22;
          F = A;
          int var23 = A & 3;
          A = var23;
          F = A;
          int var24 = A + 3 & 255;
          A = var24;
          F = A;
          C = A;
          int var25 = this.DE();
          int var26 = this.mem(var25, 37945);
          A = var26;
          int var27 = A & 248;
          A = var27;
          F = A;
          int var28 = A | C;
          A = var28;
          F = A;
          int var29 = this.DE();
          this.wMem(var29, A, 37949);
          int var30 = this.HL();
          int var31 = this.mem(var30, 37950);
          A = var31;
          int var32 = A;
          int var33 = this.rlc(var32);
          A = var33;
          int var34 = A;
          int var35 = this.rlc(var34);
          A = var35;
          int var36 = A;
          int var37 = this.rlc(var36);
          A = var37;
          int var38 = A;
          int var39 = this.rlc(var38);
          A = var39;
          int var40 = A & 8;
          A = var40;
          F = A;
          int var41 = A + 96 & 255;
          A = var41;
          F = A;
          D = A;
          this.HL(32993);
          B = 8;
          this.$38555();
        } else {
          this.IX(34172);

          while(true) {
            int var42 = this.IX() + 2;
            int var43 = this.mem(var42, 37879) + 1;
            this.wMem(var42, var43, 37879);
            int var44 = var43 & 255;
            this.wMem(var42, var44, 37879);
            int var45 = this.IX() + 2;
            int var46 = this.mem(var45, 37882);
            A = var46;
            if (A != 58) {
              int var47 = this.mem(32990, 37897);
              A = var47;
              C = 128;

              do {
                int var48 = A ^ 24;
                A = var48;
                F = A;
                E = A;
                A = 144;
                int var49 = A - C & 255;
                A = var49;
                F = A;
                B = A;
                A = E;

                do {
                  int var50 = B - 1 & 255;
                  B = var50;
                } while(B != 0);

                int var51 = C - 1 & 255;
                C = var51;
                F = C;
                int var52 = C - 1 & 255;
                C = var52;
              } while(C != 0);

              int var53 = this.mem(34270, 37918);
              A = var53;
              int var54 = A + 1 & 255;
              A = var54;
              F = A;
              this.wMem(34270, A, 37922);
              if (F == 0) {
                A = 1;
                this.wMem(34271, A, 37929);
              }

              int var55 = this.HL();
              int var56 = this.mem(var55, 37932) & -65;
              int var57 = this.HL();
              this.wMem(var57, var56, 37932);
              break;
            }

            int var58 = this.IX() + 2;
            this.wMem(var58, 48, 37889);
          }
        }
      }

      int var7 = L + 1 & 255;
      L = var7;
    } while(L != 0);

  }

  public void $37974() {
    B = 16;

    do {
      int var1 = C & 1;
      F = var1;
      int var2 = this.DE();
      int var3 = this.mem(var2, 37978);
      A = var3;
      if (F != 0) {
        int var31 = this.HL();
        int var32 = this.mem(var31, 37981);
        int var33 = A & var32;
        A = var33;
        int var34 = this.HL();
        this.mem(var34, 37981);
        if (A != 0) {
          return;
        }

        int var35 = this.DE();
        int var36 = this.mem(var35, 37983);
        A = var36;
        int var37 = this.HL();
        int var38 = this.mem(var37, 37984);
        int var39 = A | var38;
        A = var39;
        int var40 = this.HL();
        this.mem(var40, 37984);
        F = A;
      }

      int var4 = this.HL();
      this.wMem(var4, A, 37985);
      int var5 = L + 1 & 255;
      L = var5;
      F = L;
      int var6 = this.DE() + 1 & '\uffff';
      this.DE(var6);
      int var7 = C & 1;
      F = var7;
      int var8 = this.DE();
      int var9 = this.mem(var8, 37990);
      A = var9;
      if (F != 0) {
        int var21 = this.HL();
        int var22 = this.mem(var21, 37993);
        int var23 = A & var22;
        A = var23;
        int var24 = this.HL();
        this.mem(var24, 37993);
        if (A != 0) {
          return;
        }

        int var25 = this.DE();
        int var26 = this.mem(var25, 37995);
        A = var26;
        int var27 = this.HL();
        int var28 = this.mem(var27, 37996);
        int var29 = A | var28;
        A = var29;
        int var30 = this.HL();
        this.mem(var30, 37996);
        F = A;
      }

      int var10 = this.HL();
      this.wMem(var10, A, 37997);
      int var11 = L - 1 & 255;
      L = var11;
      F = L;
      int var12 = H + 1 & 255;
      H = var12;
      F = H;
      int var13 = this.DE() + 1 & '\uffff';
      this.DE(var13);
      A = H;
      int var14 = A & 7;
      A = var14;
      if (A == 0) {
        A = H;
        int var17 = A - 8 & 255;
        A = var17;
        F = A;
        H = A;
        A = L;
        int var18 = A + 32 & 255;
        A = var18;
        F = A;
        L = A;
        int var19 = A & 224;
        A = var19;
        if (A == 0) {
          A = H;
          int var20 = A + 8 & 255;
          A = var20;
          F = A;
          H = A;
        }
      }

      int var15 = B - 1 & 255;
      B = var15;
    } while(B != 0);

    int var16 = A ^ A;
    A = var16;
  }

  public void $38064() {
    int var1 = this.mem(33003, 38064);
    A = var1;
    this.wMem(33824, A, 38067);
    int var2 = this.mem(34259, 38070);
    A = var2;
    int var3 = A & 31;
    A = var3;
    F = A;
    int var4 = A + 160 & 255;
    A = var4;
    F = A;
    this.wMem(34259, A, 38077);
    A = 93;
    this.wMem(34260, A, 38082);
    A = 208;
    this.wMem(34255, A, 38087);
    int var5 = A ^ A;
    A = var5;
    F = A;
    this.wMem(34257, A, 38091);
    this.incPops();
  }

  public void $38137() {
    int var1 = this.mem16(32983, 38137);
    this.HL(var1);
    A = H;
    int var2 = A & 1;
    A = var2;
    F = A;
    int var3 = A;
    int var4 = this.rlc(var3);
    A = var4;
    int var5 = A;
    int var6 = this.rlc(var5);
    A = var6;
    int var7 = A;
    int var8 = this.rlc(var7);
    A = var8;
    int var9 = A + 112 & 255;
    A = var9;
    F = A;
    H = A;
    E = L;
    D = H;
    int var10 = this.mem(32985, 38151);
    A = var10;
    int var11 = A | A;
    A = var11;
    if (A != 0) {
      B = A;
      int var12 = this.mem(32982, 38157);
      A = var12;
      int var13 = A | A;
      A = var13;
      if (A == 0) {
        int var33 = this.HL();
        int var34 = this.mem(var33, 38163);
        A = var34;
        int var35 = A;
        int var36 = this.rlc(var35);
        A = var36;
        int var37 = A;
        int var38 = this.rlc(var37);
        A = var38;
        int var39 = H + 1 & 255;
        H = var39;
        F = H;
        int var40 = H + 1 & 255;
        H = var40;
        F = H;
        int var41 = this.HL();
        int var42 = this.mem(var41, 38170);
        C = var42;
        int var43 = C;
        int var44 = this.rrc(var43);
        C = var44;
        int var45 = C;
        int var46 = this.rrc(var45);
        C = var46;
      } else {
        int var14 = this.HL();
        int var15 = this.mem(var14, 38182);
        A = var15;
        int var16 = A;
        int var17 = this.rrc(var16);
        A = var17;
        int var18 = A;
        int var19 = this.rrc(var18);
        A = var19;
        int var20 = H + 1 & 255;
        H = var20;
        F = H;
        int var21 = H + 1 & 255;
        H = var21;
        F = H;
        int var22 = this.HL();
        int var23 = this.mem(var22, 38189);
        C = var23;
        int var24 = C;
        int var25 = this.rlc(var24);
        C = var25;
        int var26 = C;
        int var27 = this.rlc(var26);
        C = var27;
      }

      do {
        int var28 = this.DE();
        this.wMem(var28, A, 38175);
        int var29 = this.HL();
        this.wMem(var29, C, 38176);
        int var30 = L + 1 & 255;
        L = var30;
        F = L;
        int var31 = E + 1 & 255;
        E = var31;
        int var32 = B - 1 & 255;
        B = var32;
      } while(B != 0);

    }
  }

  public void $38196() {
    int var1 = this.mem(33824, 38196);
    A = var1;
    if (A == 35) {
      int var15 = this.mem(34271, 38203);
      A = var15;
      int var16 = A | A;
      A = var16;
      if (A == 0) {
        int var19 = this.mem(34251, 38209);
        A = var19;
        int var20 = A & 2;
        A = var20;
        F = A;
        int var21 = A;
        int var22 = this.rrc(var21);
        A = var22;
        int var23 = A;
        int var24 = this.rrc(var23);
        A = var24;
        int var25 = A;
        int var26 = this.rrc(var25);
        A = var26;
        int var27 = A;
        int var28 = this.rrc(var27);
        A = var28;
        int var29 = A | 128;
        A = var29;
        F = A;
        E = A;
        int var30 = this.mem(34255, 38221);
        A = var30;
        if (A != 208) {
          E = 192;
          if (A < 192) {
            E = 224;
          }
        }

        D = 156;
        this.HL(26734);
        C = 1;
        this.$37974();
        if (F != 0) {
          this.incPops();
        } else {
          this.incPops();
          this.HL(17733);
          int var31 = this.HL();
          this.wMem16(23918, var31, 38252);
          this.HL(1799);
          int var32 = this.HL();
          this.wMem16(23950, var32, 38258);
        }
      } else {
        int var17 = this.mem(34259, 38262);
        A = var17;
        int var18 = A & 31;
        A = var18;
        F = A;
        if (A < 6) {
          A = 2;
          this.wMem(34271, A, 38272);
        }
      }
    } else {
      int var2 = this.mem(33824, 38298);
      A = var2;
      if (A == 33) {
        int var3 = this.mem(34251, 38304);
        A = var3;
        int var4 = A & 1;
        A = var4;
        F = A;
        int var5 = A;
        int var6 = this.rrc(var5);
        A = var6;
        int var7 = A;
        int var8 = this.rrc(var7);
        A = var8;
        int var9 = A;
        int var10 = this.rrc(var9);
        A = var10;
        E = A;
        int var11 = this.mem(34271, 38313);
        A = var11;
        if (A == 3) {
          int var14 = E | 64;
          E = var14;
        }

        D = 166;
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
    A = var1;
    if (A == 33) {
      int var2 = this.mem(34259, 38282);
      A = var2;
      if (A == 188) {
        int var3 = A ^ A;
        A = var3;
        F = A;
        this.wMem(34251, A, 38289);
        A = 3;
        this.wMem(34271, A, 38294);
      }
    }
  }

  public void $38344() {
    int var1 = this.mem16(34259, 38344);
    this.HL(var1);
    B = 0;
    int var2 = this.mem(32986, 38349);
    A = var2;
    int var3 = A & 1;
    A = var3;
    F = A;
    int var4 = A + 64 & 255;
    A = var4;
    F = A;
    E = A;
    D = 0;
    int var5 = this.HL();
    int var6 = this.DE();
    int var7 = var5 + var6 & '\uffff';
    this.HL(var7);
    int var8 = this.mem(32964, 38360);
    A = var8;
    int var9 = this.HL();
    this.mem(var9, 38363);
    int var10 = this.HL();
    this.mem(var10, 38363);
    int var11 = this.HL();
    int var12 = this.mem(var11, 38364);
    if (A == var12) {
      int var44 = this.mem(34257, 38366);
      A = var44;
      int var45 = A | A;
      A = var45;
      if (A == 0) {
        int var46 = this.mem(34258, 38372);
        A = var46;
        int var47 = A & 3;
        A = var47;
        F = A;
        int var48 = A;
        int var49 = this.rlc(var48);
        A = var49;
        int var50 = A;
        int var51 = this.rlc(var50);
        A = var51;
        B = A;
        int var52 = this.mem(32986, 38380);
        A = var52;
        int var53 = A & 1;
        A = var53;
        F = A;
        int var54 = A - 1 & 255;
        A = var54;
        F = A;
        int var55 = A ^ 12;
        A = var55;
        F = A;
        int var56 = A ^ B;
        A = var56;
        F = A;
        int var57 = A & 12;
        A = var57;
        F = A;
        B = A;
      }
    }

    int var13 = this.mem16(34259, 38392);
    this.HL(var13);
    this.DE(31);
    C = 15;
    this.$38430();
    this.decPops();
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
    A = var19;
    int var20 = A + B & 255;
    A = var20;
    F = A;
    C = A;
    int var21 = this.HL();
    int var22 = this.DE();
    int var23 = var21 + var22 & '\uffff';
    this.HL(var23);
    this.$38430();
    int var24 = this.HL() + 1 & '\uffff';
    this.HL(var24);
    this.$38430();
    int var25 = this.mem(34255, 38455);
    A = var25;
    int var26 = A + B & 255;
    A = var26;
    F = A;
    IXH = 130;
    IXL = A;
    int var27 = this.mem(34256, 38464);
    A = var27;
    int var28 = A & 1;
    A = var28;
    F = A;
    int var29 = A;
    int var30 = this.rrc(var29);
    A = var30;
    E = A;
    int var31 = this.mem(34258, 38471);
    A = var31;
    int var32 = A & 3;
    A = var32;
    F = A;
    int var33 = A;
    int var34 = this.rrc(var33);
    A = var34;
    int var35 = A;
    int var36 = this.rrc(var35);
    A = var36;
    int var37 = A;
    int var38 = this.rrc(var37);
    A = var38;
    int var39 = A | E;
    A = var39;
    F = A;
    E = A;
    D = 157;
    int var40 = this.mem(33824, 38483);
    A = var40;
    if (A == 29) {
      D = 182;
      A = E;
      int var43 = A ^ 128;
      A = var43;
      F = A;
      E = A;
    }

    B = 16;
    int var41 = this.mem(34259, 38498);
    A = var41;
    int var42 = A & 31;
    A = var42;
    F = A;
    C = A;
    this.$38504();
  }

  public void $38430() {
    int var1 = this.mem(32928, 38430);
    A = var1;
    int var2 = this.HL();
    this.mem(var2, 38433);
    int var3 = this.HL();
    this.mem(var3, 38433);
    int var4 = this.HL();
    int var5 = this.mem(var4, 38434);
    if (A == var5) {
      A = C;
      int var11 = A & 15;
      A = var11;
      if (A != 0) {
        int var12 = this.mem(32928, 38441);
        A = var12;
        int var13 = A | 7;
        A = var13;
        F = A;
        int var14 = this.HL();
        this.wMem(var14, A, 38446);
      }
    }

    int var6 = this.mem(32955, 38447);
    A = var6;
    int var7 = this.HL();
    this.mem(var7, 38450);
    int var8 = this.HL();
    this.mem(var8, 38450);
    int var9 = this.HL();
    int var10 = this.mem(var9, 38451);
    if (A == var10) {
      this.incPops();
    } else {
      this.incPops();
    }
  }

  public void $38504() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1, 38504);
      A = var2;
      int var3 = this.IX() + 1;
      int var4 = this.mem(var3, 38507);
      H = var4;
      int var5 = A | C;
      A = var5;
      F = A;
      L = A;
      int var6 = this.DE();
      int var7 = this.mem(var6, 38512);
      A = var7;
      int var8 = this.HL();
      int var9 = this.mem(var8, 38513);
      int var10 = A | var9;
      A = var10;
      int var11 = this.HL();
      this.mem(var11, 38513);
      F = A;
      int var12 = this.HL();
      this.wMem(var12, A, 38514);
      int var13 = this.HL() + 1 & '\uffff';
      this.HL(var13);
      int var14 = this.DE() + 1 & '\uffff';
      this.DE(var14);
      int var15 = this.DE();
      int var16 = this.mem(var15, 38517);
      A = var16;
      int var17 = this.HL();
      int var18 = this.mem(var17, 38518);
      int var19 = A | var18;
      A = var19;
      int var20 = this.HL();
      this.mem(var20, 38518);
      F = A;
      int var21 = this.HL();
      this.wMem(var21, A, 38519);
      int var22 = this.IX() + 1 & '\uffff';
      this.IX(var22);
      int var23 = this.IX() + 1 & '\uffff';
      this.IX(var23);
      int var24 = this.DE() + 1 & '\uffff';
      this.DE(var24);
      int var25 = B - 1 & 255;
      B = var25;
    } while(B != 0);

  }

  public void $38528() {
    do {
      int var1 = this.IX();
      int var2 = this.mem(var1, 38528);
      A = var2;
      this.$38545();
      int var3 = this.IX() + 1 & '\uffff';
      this.IX(var3);
      int var4 = E + 1 & 255;
      E = var4;
      F = E;
      A = D;
      int var5 = A - 8 & 255;
      A = var5;
      F = A;
      D = A;
      int var6 = C - 1 & 255;
      C = var6;
    } while(C != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    int var1 = L | 128;
    L = var1;
    int var2 = this.HL() * 2 & '\uffff';
    this.HL(var2);
    int var3 = this.HL() * 2 & '\uffff';
    this.HL(var3);
    int var4 = this.HL() * 2 & '\uffff';
    this.HL(var4);
    B = 8;
    this.$38555();
  }

  public void $38555() {
    do {
      int var1 = this.HL();
      int var2 = this.mem(var1, 38555);
      A = var2;
      int var3 = this.DE();
      this.wMem(var3, A, 38556);
      int var4 = this.HL() + 1 & '\uffff';
      this.HL(var4);
      int var5 = D + 1 & 255;
      D = var5;
      int var6 = B - 1 & 255;
      B = var6;
    } while(B != 0);

  }

  public void $38562() {
    while(true) {
      int var1 = this.HL();
      int var2 = this.mem(var1, 38562);
      A = var2;
      if (A == 255) {
        return;
      }

      this.BC(100);
      int var3 = A ^ A;
      A = var3;
      F = A;
      int var4 = this.HL();
      int var5 = this.mem(var4, 38570);
      E = var5;
      D = E;

      while(true) {
        int var6 = D - 1 & 255;
        D = var6;
        if (D == 0) {
          D = E;
          int var12 = A ^ 24;
          A = var12;
        }

        int var7 = B - 1 & 255;
        B = var7;
        if (B == 0) {
          A = C;
          if (A == 50) {
            int var10 = E;
            int var11 = this.rlc(var10);
            E = var11;
          }

          int var8 = C - 1 & 255;
          C = var8;
          if (C == 0) {
            this.$38601();
            if (F != 0) {
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
    A = var1;
    int var2 = A | A;
    A = var2;
    if (A != 0) {
      int var5 = this.in(31);
      A = var5;
      if ((A & 16) != 0) {
        return;
      }
    }

    this.BC(45054);
    int var3 = this.in(C);
    A = var3;
    int var4 = A & 1;
    A = var4;
    F = A;
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
        if (A == B) {
          D = 24;
        }

        int var3 = B - 1 & 255;
        B = var3;
      } while(B != 0);

      int var4 = A - 1 & 255;
      A = var4;
    } while(A != 0);

  }
}
