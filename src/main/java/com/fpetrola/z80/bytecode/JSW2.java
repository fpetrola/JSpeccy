package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.MiniZX;

import java.util.HashMap;
import java.util.Map;

public class JSW2 extends MiniZX {
  private static int initialRoom = 33;

  public static void main(String[] args) {
    JSW2 jetSetWilly = new JSW2();
    jetSetWilly.$34762();
  }

  public JSW2() {
    super();
  }

  protected void customizeMemory() {
    this.mem[34795] = initialRoom;
  }

  @Override
  protected byte[] getProgramBytes() {
    return gzipDecompressFromBase64("");
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
    label305:
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

      label297:
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
          if (super.A != 0 && super.A != 211) {
            int var320 = super.A - 211;
            super.F = var320;
            if (super.A != 9) {
              int var321 = super.A - 9;
              super.F = var321;
              if (super.A != 45) {
                int var322 = super.A - 45;
                super.F = var322;
                if (super.A != 36) {
                  int var323 = super.A - 36;
                  super.F = var323;
                  super.C = 0;
                  if (super.A != 8) {
                    int var338 = super.A - 8;
                    super.F = var338;
                    if (super.A != 41) {
                      int var339 = super.A - 41;
                      super.F = var339;
                      if (super.A != 44) {
                        int var341 = super.A - 44;
                        super.F = var341;
                        if (super.A != 5) {
                          int var342 = super.A - 5;
                          super.F = var342;
                          super.C = 16;
                        }
                      } else {
                        super.A = 37;
                        int var340 = this.DE();
                        this.wMem(var340, super.A, 34928);
                      }
                    }
                  }

                  super.A = super.E;
                  int var324 = super.A & 1;
                  super.A = var324;
                  super.F = super.A;
                  int var325 = super.A;
                  int var326 = this.rlc(var325);
                  super.A = var326;
                  int var327 = super.A;
                  int var328 = this.rlc(var327);
                  super.A = var328;
                  int var329 = super.A;
                  int var330 = this.rlc(var329);
                  super.A = var330;
                  int var331 = super.A | super.C;
                  super.A = var331;
                  super.F = super.A;
                  super.C = super.A;
                  super.B = 0;
                  this.HL(33841);
                  int var332 = this.HL();
                  int var333 = this.BC();
                  int var334 = var332 + var333 & '\uffff';
                  this.HL(var334);
                  int var335 = this.DE();
                  this.push(var335);
                  int var336 = super.D & 1;
                  super.F = var336;
                  super.D = 64;
                  if (super.F != 0) {
                    super.D = 72;
                  }

                  super.B = 8;
                  this.$38555();
                  int var337 = this.pop();
                  this.DE(var337);
                }
              }
            }
          }

          int var21 = this.DE() + 1 & '\uffff';
          this.DE(var21);
          super.A = super.D;
        } while (super.A != 90);

        int var22 = super.A - 90;
        super.F = var22;
        this.BC(31);
        int var23 = super.A ^ super.A;
        super.A = var23;
        super.F = super.A;

        do {
          int var24 = this.BC();
          int var25 = this.in(var24);
          super.E = var25;
          int var26 = super.A | super.E;
          super.A = var26;
          int var27 = super.B - 1 & 255;
          super.B = var27;
        } while (super.B != 0);

        int var28 = super.A & 32;
        super.A = var28;
        if (super.A == 0) {
          super.A = 1;
          this.wMem(34254, super.A, 34981);
        }

        this.HL(34299);
        this.$38562();
        if (super.F != 0) {
          break;
        }

        int var303 = super.A ^ super.A;
        super.A = var303;
        super.F = super.A;
        this.wMem(34276, super.A, 34994);

        while (true) {
          this.$35563();
          this.HL(23136);
          this.DE(23137);
          this.BC(31);
          int var304 = this.HL();
          this.wMem(var304, 79, 35009);
          this.ldir();
          int var305 = this.mem(34276, 35013);
          super.A = var305;
          this.IX(33876);
          super.E = super.A;
          super.D = 0;
          int var306 = this.IX();
          int var307 = this.DE();
          int var308 = var306 + var307 & '\uffff';
          this.IX(var308);
          this.DE(20576);
          super.C = 32;
          this.$38528();
          int var309 = this.mem(34276, 35033);
          super.A = var309;
          int var310 = super.A & 31;
          super.A = var310;
          super.F = super.A;
          int var311 = super.A + 50;
          int var312 = var311 & 255;
          super.A = var312;
          super.F = var311;
          this.$38622();
          this.BC(45054);
          int var313 = this.BC();
          int var314 = this.in(var313);
          super.A = var314;
          int var315 = super.A & 1;
          super.A = var315;
          super.F = super.A;
          if (super.A != 1) {
            break label297;
          }

          int var316 = super.A - 1;
          super.F = var316;
          int var317 = this.mem(34276, 35054);
          super.A = var317;
          int var318 = super.A + 1 & 255;
          super.A = var318;
          super.F = super.A;
          int var319 = super.A - 224;
          super.F = var319;
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
        int var29 = this.mem(33824, 35090);
        super.A = var29;
        int var30 = super.A | 192;
        super.A = var30;
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
          int var31 = this.IX();
          int var32 = this.mem(var31, 35115);
          super.L = var32;
          int var33 = super.L & -129;
          super.L = var33;
          super.H = 20;
          int var34 = this.HL() * 2 & '\uffff';
          this.HL(var34);
          int var35 = this.HL() * 2 & '\uffff';
          this.HL(var35);
          int var36 = this.HL() * 2 & '\uffff';
          this.HL(var36);
          this.BC(2);
          this.ldir();
          int var37 = this.IX() + 1;
          int var38 = this.mem(var37, 35130);
          super.C = var38;
          int var39 = this.HL();
          this.wMem(var39, super.C, 35133);
          this.BC(6);
          this.ldir();
          int var40 = this.IX() + 1 & '\uffff';
          this.IX(var40);
          int var41 = this.IX() + 1 & '\uffff';
          this.IX(var41);
          int var42 = super.A - 1 & 255;
          super.A = var42;
        } while (super.A != 0);

        this.HL(34255);
        this.DE(34263);
        this.BC(7);
        this.ldir();
        this.$36147();
        this.HL(20480);
        this.DE(20481);
        this.BC(2047);
        int var43 = this.HL();
        this.wMem(var43, 0, 35169);
        this.ldir();
        this.IX(32896);
        super.C = 32;
        this.DE(20480);
        this.$38528();
        this.IX(34132);
        this.DE(20576);
        super.C = 32;
        this.$38528();
        int var44 = this.mem(32990, 35197);
        super.A = var44;
        super.C = 254;
        int var45 = super.A ^ super.A;
        super.A = var45;
        super.F = super.A;
        this.wMem(34262, super.A, 35205);

        while (true) {
          label316:
          {
            label236:
            {
              label312:
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
                int var46 = this.mem(34271, 35273);
                super.A = var46;
                if (super.A != 3) {
                  this.$36307();
                  if (super.nextAddress == 37048) {
                    super.nextAddress = 0;
                    break label312;
                  }

                  if (super.nextAddress == 38043) {
                    super.nextAddress = 0;
                    break;
                  }

                  if (super.nextAddress == 38061) {
                    super.nextAddress = 0;
                    break;
                  }

                  if (super.nextAddress == 38134) {
                    super.nextAddress = 0;
                    break;
                  }

                  if (super.nextAddress == 38095) {
                    super.nextAddress = 0;
                    break;
                  }
                }

                int var47 = super.A - 3;
                super.F = var47;
                int var48 = this.mem(34255, 35281);
                super.A = var48;
                if (super.A >= 225) {
                  this.$38064();
                  if (super.nextAddress == 38095) {
                    super.nextAddress = 0;
                    break;
                  }
                }

                int var49 = super.A - 225;
                super.F = var49;
                int var50 = this.mem(34271, 35289);
                super.A = var50;
                if (super.A != 3) {
                  this.$38344();
                  if (super.nextAddress == 37048) {
                    super.nextAddress = 0;
                    break label312;
                  }
                }

                int var51 = super.A - 3;
                super.F = var51;
                int var52 = this.mem(34271, 35297);
                super.A = var52;
                if (super.A == 2) {
                  this.$38276();
                }

                int var53 = super.A - 2;
                super.F = var53;
                this.$38196();
                if (super.nextAddress == 37048) {
                  super.nextAddress = 0;
                } else {
                  this.$37310();
                  if (super.nextAddress != 37048) {
                    this.$38137();
                    this.$37841();
                    break label236;
                  }

                  super.nextAddress = 0;
                }
              }

              super.A = 255;
              this.wMem(34257, super.A, 37050);
            }

            this.HL(24576);
            this.DE(16384);
            this.BC(4096);
            this.ldir();
            int var54 = this.mem(34271, 35328);
            super.A = var54;
            int var55 = super.A & 2;
            super.A = var55;
            super.F = super.A;
            int var56 = super.A;
            int var57 = this.rrc(var56);
            super.A = var57;
            this.HL(34258);
            int var58 = this.HL();
            int var59 = this.mem(var58, 35337);
            int var60 = super.A | var59;
            super.A = var60;
            super.F = super.A;
            int var61 = this.HL();
            this.wMem(var61, super.A, 35338);
            int var62 = this.mem(34253, 35339);
            super.A = var62;
            int var63 = super.A | super.A;
            super.A = var63;
            if (super.A != 0) {
              int var294 = super.A - 1 & 255;
              super.A = var294;
              super.F = super.A;
              this.wMem(34253, super.A, 35346);
              int var295 = super.A;
              int var296 = this.rlc(var295);
              super.A = var296;
              int var297 = super.A;
              int var298 = this.rlc(var297);
              super.A = var298;
              int var299 = super.A;
              int var300 = this.rlc(var299);
              super.A = var300;
              int var301 = super.A & 56;
              super.A = var301;
              super.F = super.A;
              this.HL(23552);
              this.DE(23553);
              this.BC(511);
              int var302 = this.HL();
              this.wMem(var302, super.A, 35363);
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
            int var64 = this.mem(34251, 35401);
            super.A = var64;
            int var65 = super.A + 1 & 255;
            super.A = var65;
            super.F = super.A;
            this.wMem(34251, super.A, 35405);
            if (super.F == 0) {
              this.IX(34175);
              int var261 = this.IX() + 4;
              int var262 = this.mem(var261, 35414) + 1 & 255;
              this.wMem(var261, var262, 35414);
              int var263 = this.IX() + 4;
              int var264 = this.mem(var263, 35417);
              super.A = var264;
              if (super.A == 58) {
                int var265 = super.A - 58;
                super.F = var265;
                int var266 = this.IX() + 4;
                this.wMem(var266, 48, 35424);
                int var267 = this.IX() + 3;
                int var268 = this.mem(var267, 35428) + 1 & 255;
                this.wMem(var267, var268, 35428);
                int var269 = this.IX() + 3;
                int var270 = this.mem(var269, 35431);
                super.A = var270;
                if (super.A == 54) {
                  int var271 = super.A - 54;
                  super.F = var271;
                  int var272 = this.IX() + 3;
                  this.wMem(var272, 48, 35438);
                  int var273 = this.IX();
                  int var274 = this.mem(var273, 35442);
                  super.A = var274;
                  if (super.A == 49) {
                    int var282 = super.A - 49;
                    super.F = var282;
                    int var283 = this.IX() + 1;
                    int var284 = this.mem(var283, 35449) + 1 & 255;
                    this.wMem(var283, var284, 35449);
                    int var285 = this.IX() + 1;
                    int var286 = this.mem(var285, 35452);
                    super.A = var286;
                    if (super.A == 51) {
                      int var287 = super.A - 51;
                      super.F = var287;
                      int var288 = this.IX() + 5;
                      int var289 = this.mem(var288, 35459);
                      super.A = var289;
                      if (super.A == 112) {
                        continue label305;
                      }

                      int var290 = super.A - 112;
                      super.F = var290;
                      int var291 = this.IX();
                      this.wMem(var291, 32, 35467);
                      int var292 = this.IX() + 1;
                      this.wMem(var292, 49, 35471);
                      int var293 = this.IX() + 5;
                      this.wMem(var293, 112, 35475);
                    }
                  } else {
                    int var275 = this.IX() + 1;
                    int var276 = this.mem(var275, 35481) + 1 & 255;
                    this.wMem(var275, var276, 35481);
                    int var277 = this.IX() + 1;
                    int var278 = this.mem(var277, 35484);
                    super.A = var278;
                    if (super.A == 58) {
                      int var279 = super.A - 58;
                      super.F = var279;
                      int var280 = this.IX() + 1;
                      this.wMem(var280, 48, 35491);
                      int var281 = this.IX();
                      this.wMem(var281, 49, 35495);
                    }
                  }
                }
              }
            }

            this.BC(65278);
            int var66 = this.BC();
            int var67 = this.in(var66);
            super.A = var67;
            super.E = super.A;
            super.B = 127;
            int var68 = this.BC();
            int var69 = this.in(var68);
            super.A = var69;
            int var70 = super.A | super.E;
            super.A = var70;
            super.F = super.A;
            int var71 = super.A & 1;
            super.A = var71;
            if (super.A == 0) {
              continue label305;
            }

            int var72 = this.mem(34272, 35515);
            super.A = var72;
            int var73 = super.A + 1 & 255;
            super.A = var73;
            super.F = super.A;
            this.wMem(34272, super.A, 35519);
            if (super.F != 0) {
              super.B = 253;
              int var257 = this.BC();
              int var258 = this.in(var257);
              super.A = var258;
              int var259 = super.A & 31;
              super.A = var259;
              super.F = super.A;
              if (super.A == 31) {
                break label316;
              }

              int var260 = super.A - 31;
              super.F = var260;
              this.DE(0);
            }

            while (true) {
              super.B = 2;
              int var74 = this.BC();
              int var75 = this.in(var74);
              super.A = var75;
              int var76 = super.A & 31;
              super.A = var76;
              super.F = super.A;
              if (super.A != 31) {
                this.HL(39424);
                this.DE(23040);
                this.BC(256);
                this.ldir();
                int var77 = this.mem(32990, 35602);
                super.A = var77;
                break;
              }

              int var252 = super.A - 31;
              super.F = var252;
              int var253 = super.E + 1 & 255;
              super.E = var253;
              if (super.E == 0) {
                int var254 = super.D + 1 & 255;
                super.D = var254;
                if (super.D == 0) {
                  int var255 = this.mem(34275, 35553);
                  super.A = var255;
                  if (super.A != 10) {
                    this.$35563();
                  }

                  int var256 = super.A - 10;
                  super.F = var256;
                }
              }
            }
          }

          int var78 = this.mem(34257, 35607);
          super.A = var78;
          if (super.A == 255) {
            super.A = 71;

            do {
              this.HL(22528);
              this.DE(22529);
              this.BC(511);
              int var79 = this.HL();
              this.wMem(var79, super.A, 35852);
              this.ldir();
              super.E = super.A;
              int var80 = ~super.A;
              super.A = var80;
              super.F = super.A;
              int var81 = super.A & 7;
              super.A = var81;
              super.F = super.A;
              int var82 = super.A;
              int var83 = this.rlc(var82);
              super.A = var83;
              int var84 = super.A;
              int var85 = this.rlc(var84);
              super.A = var85;
              int var86 = super.A;
              int var87 = this.rlc(var86);
              super.A = var87;
              int var88 = super.A | 7;
              super.A = var88;
              super.F = super.A;
              super.D = super.A;
              super.C = super.E;
              int var89 = super.C;
              int var90 = this.rrc(var89);
              super.C = var90;
              int var91 = super.C;
              int var92 = this.rrc(var91);
              super.C = var92;
              int var93 = super.C;
              int var94 = this.rrc(var93);
              super.C = var94;
              int var95 = super.A | 16;
              super.A = var95;
              super.F = super.A;
              int var96 = super.A ^ super.A;
              super.A = var96;
              super.F = super.A;

              do {
                int var97 = super.A ^ 24;
                super.A = var97;
                super.F = super.A;
                super.B = super.D;

                do {
                  int var98 = super.B - 1 & 255;
                  super.B = var98;
                } while (super.B != 0);

                int var99 = super.C - 1 & 255;
                super.C = var99;
              } while (super.C != 0);

              super.A = super.E;
              int var100 = super.A - 1 & 255;
              super.A = var100;
              super.F = super.A;
            } while (super.A != 63);

            int var101 = super.A - 63;
            super.F = var101;
            this.HL(34252);
            int var102 = this.HL();
            int var103 = this.mem(var102, 35894);
            super.A = var103;
            int var104 = super.A | super.A;
            super.A = var104;
            if (super.A == 0) {
              this.HL(16384);
              this.DE(16385);
              this.BC(4095);
              int var105 = this.HL();
              this.wMem(var105, 0, 35923);
              this.ldir();
              int var106 = super.A ^ super.A;
              super.A = var106;
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
                int var107 = this.mem(34276, 35953);
                super.A = var107;
                super.C = super.A;
                super.B = 130;
                int var108 = this.BC();
                int var109 = this.mem(var108, 35959);
                super.A = var109;
                int var110 = super.A | 15;
                super.A = var110;
                super.F = super.A;
                super.L = super.A;
                int var111 = this.BC() + 1 & '\uffff';
                this.BC(var111);
                int var112 = this.BC();
                int var113 = this.mem(var112, 35964);
                super.A = var113;
                int var114 = super.A - 32;
                int var115 = var114 & 255;
                super.A = var115;
                super.F = var114;
                super.H = super.A;
                this.DE(40000);
                super.C = 0;
                this.$37974();
                int var116 = this.mem(34276, 35976);
                super.A = var116;
                int var117 = ~super.A;
                super.A = var117;
                super.F = super.A;
                super.E = super.A;
                int var118 = super.A ^ super.A;
                super.A = var118;
                super.F = super.A;
                this.BC(64);

                do {
                  int var119 = super.A ^ 24;
                  super.A = var119;
                  super.F = super.A;
                  super.B = super.E;

                  do {
                    int var120 = super.B - 1 & 255;
                    super.B = var120;
                  } while (super.B != 0);

                  int var121 = super.C - 1 & 255;
                  super.C = var121;
                } while (super.C != 0);

                this.HL(22528);
                this.DE(22529);
                this.BC(511);
                int var122 = this.mem(34276, 36004);
                super.A = var122;
                int var123 = super.A & 12;
                super.A = var123;
                super.F = super.A;
                int var124 = super.A;
                int var125 = this.rlc(var124);
                super.A = var125;
                int var126 = super.A | 71;
                super.A = var126;
                super.F = super.A;
                int var127 = this.HL();
                this.wMem(var127, super.A, 36012);
                this.ldir();
                int var128 = super.A & 250;
                super.A = var128;
                super.F = super.A;
                int var129 = super.A | 2;
                super.A = var129;
                super.F = super.A;
                this.wMem(22991, super.A, 36019);
                this.wMem(22992, super.A, 36022);
                this.wMem(23023, super.A, 36025);
                this.wMem(23024, super.A, 36028);
                int var130 = this.mem(34276, 36031);
                super.A = var130;
                int var131 = super.A + 4;
                int var132 = var131 & 255;
                super.A = var132;
                super.F = var131;
                this.wMem(34276, super.A, 36036);
              } while (super.A != 196);

              int var133 = super.A - 196;
              super.F = var133;
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
                int var134 = super.B - 1 & 255;
                super.B = var134;
                if (super.B == 0) {
                  super.A = super.C;
                  int var135 = super.A & 7;
                  super.A = var135;
                  super.F = super.A;
                  int var136 = super.A | 64;
                  super.A = var136;
                  super.F = super.A;
                  this.wMem(22730, super.A, 36079);
                  int var137 = super.A + 1 & 255;
                  super.A = var137;
                  super.F = super.A;
                  int var138 = super.A & 7;
                  super.A = var138;
                  super.F = super.A;
                  int var139 = super.A | 64;
                  super.A = var139;
                  super.F = super.A;
                  this.wMem(22731, super.A, 36087);
                  int var140 = super.A + 1 & 255;
                  super.A = var140;
                  super.F = super.A;
                  int var141 = super.A & 7;
                  super.A = var141;
                  super.F = super.A;
                  int var142 = super.A | 64;
                  super.A = var142;
                  super.F = super.A;
                  this.wMem(22732, super.A, 36095);
                  int var143 = super.A + 1 & 255;
                  super.A = var143;
                  super.F = super.A;
                  int var144 = super.A & 7;
                  super.A = var144;
                  super.F = super.A;
                  int var145 = super.A | 64;
                  super.A = var145;
                  super.F = super.A;
                  this.wMem(22733, super.A, 36103);
                  int var146 = super.A + 1 & 255;
                  super.A = var146;
                  super.F = super.A;
                  int var147 = super.A & 7;
                  super.A = var147;
                  super.F = super.A;
                  int var148 = super.A | 64;
                  super.A = var148;
                  super.F = super.A;
                  this.wMem(22738, super.A, 36111);
                  int var149 = super.A + 1 & 255;
                  super.A = var149;
                  super.F = super.A;
                  int var150 = super.A & 7;
                  super.A = var150;
                  super.F = super.A;
                  int var151 = super.A | 64;
                  super.A = var151;
                  super.F = super.A;
                  this.wMem(22739, super.A, 36119);
                  int var152 = super.A + 1 & 255;
                  super.A = var152;
                  super.F = super.A;
                  int var153 = super.A & 7;
                  super.A = var153;
                  super.F = super.A;
                  int var154 = super.A | 64;
                  super.A = var154;
                  super.F = super.A;
                  this.wMem(22740, super.A, 36127);
                  int var155 = super.A + 1 & 255;
                  super.A = var155;
                  super.F = super.A;
                  int var156 = super.A & 7;
                  super.A = var156;
                  super.F = super.A;
                  int var157 = super.A | 64;
                  super.A = var157;
                  super.F = super.A;
                  this.wMem(22741, super.A, 36135);
                  int var158 = super.C - 1 & 255;
                  super.C = var158;
                  if (super.C == 0) {
                    int var159 = super.D - 1 & 255;
                    super.D = var159;
                    if (super.D == 0) {
                      continue label305;
                    }
                  }
                }
              }
            }

            int var160 = this.HL();
            int var161 = this.mem(var160, 35899) - 1 & 255;
            int var162 = this.HL();
            this.wMem(var162, var161, 35899);
            this.HL(34263);
            this.DE(34255);
            this.BC(7);
            this.ldir();
            break;
          }

          int var163 = super.A - 255;
          super.F = var163;
          super.B = 191;
          this.HL(34274);
          int var164 = this.BC();
          int var165 = this.in(var164);
          super.A = var165;
          int var166 = super.A & 31;
          super.A = var166;
          super.F = super.A;
          if (super.A != 31) {
            int var246 = super.A - 31;
            super.F = var246;
            int var247 = this.HL();
            if ((this.mem(var247, 35628) & 1) == 0) {
              int var248 = this.HL();
              int var249 = this.mem(var248, 35632);
              super.A = var249;
              int var250 = super.A ^ 3;
              super.A = var250;
              super.F = super.A;
              int var251 = this.HL();
              this.wMem(var251, super.A, 35635);
            }
          } else {
            int var167 = this.HL();
            int var168 = this.mem(var167, 35638) & -2;
            int var169 = this.HL();
            this.wMem(var169, var168, 35638);
          }

          int var170 = this.HL();
          if ((this.mem(var170, 35640) & 2) == 0) {
            int var220 = super.A ^ super.A;
            super.A = var220;
            super.F = super.A;
            this.wMem(34272, super.A, 35645);
            int var221 = this.mem(34273, 35648);
            super.A = var221;
            int var222 = super.A + 1 & 255;
            super.A = var222;
            super.F = super.A;
            this.wMem(34273, super.A, 35652);
            int var223 = super.A & 126;
            super.A = var223;
            super.F = super.A;
            int var224 = super.A;
            int var225 = this.rrc(var224);
            super.A = var225;
            super.E = super.A;
            super.D = 0;
            this.HL(34399);
            int var226 = this.HL();
            int var227 = this.DE();
            int var228 = var226 + var227 & '\uffff';
            this.HL(var228);
            int var229 = this.mem(34252, 35665);
            super.A = var229;
            int var230 = super.A;
            int var231 = this.rlc(var230);
            super.A = var231;
            int var232 = super.A;
            int var233 = this.rlc(var232);
            super.A = var233;
            int var234 = super.A - 28;
            int var235 = var234 & 255;
            super.A = var235;
            super.F = var234;
            int var236 = -super.A & 255;
            super.A = var236;
            int var237 = this.HL();
            int var238 = this.mem(var237, 35674);
            int var239 = super.A + var238;
            int var240 = var239 & 255;
            super.A = var240;
            super.F = var239;
            super.D = super.A;
            int var241 = this.mem(32990, 35676);
            super.A = var241;
            super.E = super.D;
            this.BC(3);

            while (true) {
              int var242 = super.E - 1 & 255;
              super.E = var242;
              if (super.E == 0) {
                super.E = super.D;
                int var245 = super.A ^ 24;
                super.A = var245;
              }

              int var243 = super.B - 1 & 255;
              super.B = var243;
              if (super.B == 0) {
                int var244 = super.C - 1 & 255;
                super.C = var244;
                if (super.C == 0) {
                  break;
                }
              }
            }
          }

          this.BC(61438);
          int var171 = this.BC();
          int var172 = this.in(var171);
          super.A = var172;
          if ((super.A & 2) == 0) {
            int var209 = super.A & 16;
            super.A = var209;
            super.F = super.A;
            int var210 = super.A ^ 16;
            super.A = var210;
            super.F = super.A;
            int var211 = super.A;
            int var212 = this.rlc(var211);
            super.A = var212;
            super.D = super.A;
            int var213 = this.mem(34275, 35712);
            super.A = var213;
            if (super.A == 10) {
              int var214 = super.A - 10;
              super.F = var214;
              this.BC(63486);
              int var215 = this.BC();
              int var216 = this.in(var215);
              super.A = var216;
              int var217 = ~super.A;
              super.A = var217;
              super.F = super.A;
              int var218 = super.A & 31;
              super.A = var218;
              super.F = super.A;
              int var219 = super.A | super.D;
              super.A = var219;
              super.F = super.A;
              this.wMem(33824, super.A, 35729);
              break;
            }
          }

          int var173 = this.mem(34275, 35735);
          super.A = var173;
          if (super.A != 10) {
            int var174 = super.A - 10;
            super.F = var174;
            int var175 = this.mem(33824, 35743);
            super.A = var175;
            if (super.A == 28) {
              int var176 = super.A - 28;
              super.F = var176;
              int var177 = this.mem(34255, 35751);
              super.A = var177;
              if (super.A == 208) {
                int var178 = super.A - 208;
                super.F = var178;
                int var179 = this.mem(34275, 35759);
                super.A = var179;
                int var180 = super.A;
                int var181 = this.rlc(var180);
                super.A = var181;
                super.E = super.A;
                super.D = 0;
                this.IX(34279);
                int var182 = this.IX();
                int var183 = this.DE();
                int var184 = var182 + var183 & '\uffff';
                this.IX(var184);
                this.BC(64510);
                int var185 = this.BC();
                int var186 = this.in(var185);
                super.A = var186;
                int var187 = super.A & 31;
                super.A = var187;
                super.F = super.A;
                int var188 = this.IX();
                int var189 = this.mem(var188, 35779);
                if (super.A != var189) {
                  int var203 = super.A - var189;
                  super.F = var203;
                  if (super.A != 31) {
                    int var204 = super.A - 31;
                    super.F = var204;
                    int var205 = this.IX();
                    int var206 = this.mem(var205, 35789);
                    if (super.A != var206) {
                      int var207 = super.A - var206;
                      super.F = var207;
                      int var208 = super.A ^ super.A;
                      super.A = var208;
                      super.F = super.A;
                      this.wMem(34275, super.A, 35796);
                    }
                  }
                } else {
                  super.B = 223;
                  int var190 = this.BC();
                  int var191 = this.in(var190);
                  super.A = var191;
                  int var192 = super.A & 31;
                  super.A = var192;
                  super.F = super.A;
                  int var193 = this.IX() + 1;
                  int var194 = this.mem(var193, 35808);
                  if (super.A != var194) {
                    int var197 = super.A - var194;
                    super.F = var197;
                    if (super.A != 31) {
                      int var198 = super.A - 31;
                      super.F = var198;
                      int var199 = this.IX();
                      int var200 = this.mem(var199, 35818);
                      if (super.A != var200) {
                        int var201 = super.A - var200;
                        super.F = var201;
                        int var202 = super.A ^ super.A;
                        super.A = var202;
                        super.F = super.A;
                        this.wMem(34275, super.A, 35825);
                      }
                    }
                  } else {
                    int var195 = this.mem(34275, 35831);
                    super.A = var195;
                    int var196 = super.A + 1 & 255;
                    super.A = var196;
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
    label221:
    {
      label218:
      {
        label233:
        {
          int var1 = this.mem(34262, 36307);
          super.A = var1;
          int var2 = super.A - 1 & 255;
          super.A = var2;
          super.F = super.A;
          if ((super.A & 128) != 0) {
            int var224 = this.mem(34257, 36316);
            super.A = var224;
            if (super.A == 1) {
              int var278 = super.A - 1;
              super.F = var278;
              int var279 = this.mem(34261, 36323);
              super.A = var279;
              int var280 = super.A & 254;
              super.A = var280;
              super.F = super.A;
              int var281 = super.A - 8;
              int var282 = var281 & 255;
              super.A = var282;
              super.F = var281;
              this.HL(34255);
              int var283 = this.HL();
              int var284 = this.mem(var283, 36333);
              int var285 = super.A + var284;
              int var286 = var285 & 255;
              super.A = var286;
              super.F = var285;
              int var287 = this.HL();
              this.wMem(var287, super.A, 36334);
              if (super.A >= 240) {
                return;
              }

              int var288 = super.A - 240;
              super.F = var288;
              this.$36508();
              int var289 = this.mem(32946, 36343);
              super.A = var289;
              int var290 = this.HL();
              int var291 = this.mem(var290, 36346);
              if (super.A == var291) {
                break label221;
              }

              int var299 = super.A - var291;
              super.F = var299;
              int var300 = this.HL() + 1 & '\uffff';
              this.HL(var300);
              int var301 = this.HL();
              int var302 = this.mem(var301, 36351);
              if (super.A == var302) {
                break label221;
              }

              int var303 = super.A - var302;
              super.F = var303;
              int var304 = this.mem(34261, 36355);
              super.A = var304;
              int var305 = super.A + 1 & 255;
              super.A = var305;
              super.F = super.A;
              this.wMem(34261, super.A, 36359);
              int var306 = super.A - 8;
              int var307 = var306 & 255;
              super.A = var307;
              if (var306 < 0) {
                int var323 = -super.A & 255;
                super.A = var323;
              }

              int var308 = super.A + 1 & 255;
              super.A = var308;
              super.F = super.A;
              int var309 = super.A;
              int var310 = this.rlc(var309);
              super.A = var310;
              int var311 = super.A;
              int var312 = this.rlc(var311);
              super.A = var312;
              int var313 = super.A;
              int var314 = this.rlc(var313);
              super.A = var314;
              super.D = super.A;
              super.C = 32;
              int var315 = this.mem(32990, 36376);
              super.A = var315;

              do {
                int var316 = super.A ^ 24;
                super.A = var316;
                super.F = super.A;
                super.B = super.D;

                do {
                  int var317 = super.B - 1 & 255;
                  super.B = var317;
                } while (super.B != 0);

                int var318 = super.C - 1 & 255;
                super.C = var318;
              } while (super.C != 0);

              int var319 = this.mem(34261, 36389);
              super.A = var319;
              if (super.A == 18) {
                super.A = 6;
                this.wMem(34257, super.A, 36530);
                return;
              }

              int var320 = super.A - 18;
              super.F = var320;
              if (super.A != 16) {
                int var321 = super.A - 16;
                super.F = var321;
                if (super.A != 13) {
                  break label218;
                }

                int var322 = super.A - 13;
                super.F = var322;
              }
            }

            int var225 = this.mem(34255, 36406);
            super.A = var225;
            int var226 = super.A & 14;
            super.A = var226;
            if (super.A != 0) {
              break label233;
            }

            int var251 = this.mem16(34259, 36413);
            this.HL(var251);
            this.DE(64);
            int var252 = this.HL();
            int var253 = this.DE();
            int var254 = var252 + var253 & '\uffff';
            this.HL(var254);
            if ((super.H & 2) != 0) {
              int var255 = this.mem(33004, 38098);
              super.A = var255;
              this.wMem(33824, super.A, 38101);
              int var256 = super.A ^ super.A;
              super.A = var256;
              super.F = super.A;
              this.wMem(34255, super.A, 38105);
              int var257 = this.mem(34257, 38108);
              super.A = var257;
              if (super.A < 11) {
                int var260 = super.A - 11;
                super.F = var260;
                super.A = 2;
                this.wMem(34257, super.A, 38117);
              }

              int var258 = this.mem(34259, 38120);
              super.A = var258;
              int var259 = super.A & 31;
              super.A = var259;
              super.F = super.A;
              this.wMem(34259, super.A, 38125);
              super.A = 92;
              this.wMem(34260, super.A, 38130);
              this.incPops();
              super.nextAddress = 38134;
              return;
            }

            int var261 = this.mem(32955, 36425);
            super.A = var261;
            int var262 = this.HL();
            int var263 = this.mem(var262, 36428);
            if (super.A == var263) {
              break label233;
            }

            int var264 = super.A - var263;
            super.F = var264;
            int var265 = this.HL() + 1 & '\uffff';
            this.HL(var265);
            int var266 = this.mem(32955, 36432);
            super.A = var266;
            int var267 = this.HL();
            int var268 = this.mem(var267, 36435);
            if (super.A == var268) {
              break label233;
            }

            int var269 = super.A - var268;
            super.F = var269;
            int var270 = this.mem(32928, 36438);
            super.A = var270;
            int var271 = this.HL();
            int var272 = this.mem(var271, 36441);
            int var273 = super.A - var272;
            super.F = var273;
            int var274 = this.HL() - 1 & '\uffff';
            this.HL(var274);
            if (super.F == 0) {
              int var275 = this.HL();
              int var276 = this.mem(var275, 36446);
              if (super.A == var276) {
                int var277 = super.A - var276;
                super.F = var277;
                break label233;
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
            label232:
            {
              int var210 = this.mem(34257, 36574);
              super.A = var210;
              if (super.A >= 12) {
                this.incPops();
                super.nextAddress = 37048;
                return;
              }

              int var211 = super.A - 12;
              super.F = var211;
              int var212 = super.A ^ super.A;
              super.A = var212;
              super.F = super.A;
              this.wMem(34257, super.A, 36583);
              int var213 = this.mem(32973, 36586);
              super.A = var213;
              int var214 = this.HL();
              int var215 = this.mem(var214, 36589);
              if (super.A != var215) {
                int var219 = super.A - var215;
                super.F = var219;
                int var220 = this.HL() + 1 & '\uffff';
                this.HL(var220);
                int var221 = this.HL();
                int var222 = this.mem(var221, 36593);
                if (super.A != var222) {
                  break label232;
                }

                int var223 = super.A - var222;
                super.F = var223;
              }

              int var216 = this.mem(32982, 36596);
              super.A = var216;
              int var217 = super.A - 3;
              int var218 = var217 & 255;
              super.A = var218;
              super.F = var217;
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
            int var203 = super.A - 42;
            super.F = var203;
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
            int var200 = super.A - 21;
            super.F = var200;
            int var201 = super.C | 8;
            super.C = var201;
            int var202 = super.A ^ super.A;
            super.A = var202;
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
            int var193 = super.A - 31;
            super.F = var193;
            super.B = 239;
            int var194 = this.BC();
            int var195 = this.in(var194);
            super.A = var195;
            if ((super.A & 1) != 0) {
              int var196 = this.mem(34254, 36736);
              super.A = var196;
              int var197 = super.A | super.A;
              super.A = var197;
              if (super.A == 0) {
                break label218;
              }

              this.BC(31);
              int var198 = this.BC();
              int var199 = this.in(var198);
              super.A = var199;
              if ((super.A & 16) == 0) {
                break label218;
              }
            }
          }

          int var53 = this.mem(34271, 36751);
          super.A = var53;
          if ((super.A & 2) == 0) {
            int var184 = super.A ^ super.A;
            super.A = var184;
            super.F = super.A;
            this.wMem(34261, super.A, 36759);
            this.wMem(34272, super.A, 36762);
            int var185 = super.A + 1 & 255;
            super.A = var185;
            super.F = super.A;
            this.wMem(34257, super.A, 36766);
            int var186 = this.mem(34262, 36769);
            super.A = var186;
            int var187 = super.A - 1 & 255;
            super.A = var187;
            super.F = super.A;
            if ((super.A & 128) == 0) {
              super.A = 240;
              this.wMem(34262, super.A, 36779);
              int var188 = this.mem(34255, 36782);
              super.A = var188;
              int var189 = super.A & 240;
              super.A = var189;
              super.F = super.A;
              this.wMem(34255, super.A, 36787);
              this.HL(34256);
              int var190 = this.HL();
              int var191 = this.mem(var190, 36793) | 2;
              int var192 = this.HL();
              this.wMem(var192, var191, 36793);
              return;
            }
          }
          break label218;
        }

        int var227 = this.mem(34257, 36450);
        super.A = var227;
        if (super.A != 1) {
          int var228 = super.A - 1;
          super.F = var228;
          this.HL(34256);
          int var229 = this.HL();
          int var230 = this.mem(var229, 36461) & -3;
          int var231 = this.HL();
          this.wMem(var231, var230, 36461);
          int var232 = this.mem(34257, 36463);
          super.A = var232;
          int var233 = super.A | super.A;
          super.A = var233;
          if (super.A == 0) {
            super.A = 2;
            this.wMem(34257, super.A, 36536);
            return;
          }

          int var234 = super.A + 1 & 255;
          super.A = var234;
          super.F = super.A;
          if (super.A == 16) {
            int var250 = super.A - 16;
            super.F = var250;
            super.A = 12;
          }

          this.wMem(34257, super.A, 36477);
          int var235 = super.A;
          int var236 = this.rlc(var235);
          super.A = var236;
          int var237 = super.A;
          int var238 = this.rlc(var237);
          super.A = var238;
          int var239 = super.A;
          int var240 = this.rlc(var239);
          super.A = var240;
          int var241 = super.A;
          int var242 = this.rlc(var241);
          super.A = var242;
          super.D = super.A;
          super.C = 32;
          int var243 = this.mem(32990, 36487);
          super.A = var243;

          do {
            int var244 = super.A ^ 24;
            super.A = var244;
            super.F = super.A;
            super.B = super.D;

            do {
              int var245 = super.B - 1 & 255;
              super.B = var245;
            } while (super.B != 0);

            int var246 = super.C - 1 & 255;
            super.C = var246;
          } while (super.C != 0);

          int var247 = this.mem(34255, 36500);
          super.A = var247;
          int var248 = super.A + 8;
          int var249 = var248 & 255;
          super.A = var249;
          super.F = var248;
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
        int var125 = this.mem(34258, 36817);
        super.A = var125;
        int var126 = super.A | super.A;
        super.A = var126;
        if (super.A != 0) {
          int var183 = super.A - 1 & 255;
          super.A = var183;
          super.F = super.A;
          this.wMem(34258, super.A, 36824);
          return;
        }

        int var127 = this.mem(34257, 36828);
        super.A = var127;
        this.BC(0);
        if (super.A == 0) {
          int var168 = super.A - 0;
          super.F = var168;
          int var169 = this.mem16(34259, 36838);
          this.HL(var169);
          this.BC(0);
          int var170 = this.mem(32986, 36844);
          super.A = var170;
          int var171 = super.A - 1 & 255;
          super.A = var171;
          super.F = super.A;
          int var172 = super.A | 161;
          super.A = var172;
          super.F = super.A;
          int var173 = super.A ^ 224;
          super.A = var173;
          super.F = super.A;
          super.E = super.A;
          super.D = 0;
          int var174 = this.HL();
          int var175 = this.DE();
          int var176 = var174 + var175 & '\uffff';
          this.HL(var176);
          int var177 = this.mem(32964, 36856);
          super.A = var177;
          int var178 = this.HL();
          int var179 = this.mem(var178, 36859);
          if (super.A == var179) {
            int var180 = super.A - var179;
            super.F = var180;
            this.BC(32);
            int var181 = this.mem(32986, 36865);
            super.A = var181;
            int var182 = super.A | super.A;
            super.A = var182;
            if (super.A == 0) {
              this.BC(65504);
            }
          }
        }

        int var128 = this.mem16(34259, 36874);
        this.HL(var128);
        super.A = super.L;
        int var129 = super.A & 31;
        super.A = var129;
        if (super.A != 0) {
          int var134 = this.HL();
          int var135 = this.BC();
          int var136 = var134 + var135 & '\uffff';
          this.HL(var136);
          int var137 = this.HL() - 1 & '\uffff';
          this.HL(var137);
          this.DE(32);
          int var138 = this.HL();
          int var139 = this.DE();
          int var140 = var138 + var139 & '\uffff';
          this.HL(var140);
          int var141 = this.mem(32946, 36889);
          super.A = var141;
          int var142 = this.HL();
          int var143 = this.mem(var142, 36892);
          if (super.A == var143) {
            return;
          }

          int var144 = super.A - var143;
          super.F = var144;
          int var145 = this.mem(34255, 36894);
          super.A = var145;
          int var146 = super.C >> 1;
          int var147 = super.C & 128;
          int var148 = var146 | var147;
          super.C = var148;
          int var149 = super.A + super.C;
          int var150 = var149 & 255;
          super.A = var150;
          super.F = var149;
          super.B = super.A;
          int var151 = super.A & 15;
          super.A = var151;
          if (super.A != 0) {
            int var157 = this.mem(32946, 36905);
            super.A = var157;
            int var158 = this.HL();
            int var159 = this.DE();
            int var160 = var158 + var159 & '\uffff';
            this.HL(var160);
            int var161 = this.HL();
            int var162 = this.mem(var161, 36909);
            if (super.A == var162) {
              return;
            }

            int var163 = super.A - var162;
            super.F = var163;
            int var164 = super.A | super.A;
            super.A = var164;
            super.F = super.A;
            int var165 = this.HL();
            int var166 = this.DE();
            int var167 = var165 - var166 & '\uffff';
            this.HL(var167);
          }

          int var152 = super.A | super.A;
          super.A = var152;
          super.F = super.A;
          int var153 = this.HL();
          int var154 = this.DE();
          int var155 = var153 - var154 & '\uffff';
          this.HL(var155);
          int var156 = this.HL();
          this.wMem16(34259, var156, 36917);
          super.A = super.B;
          this.wMem(34255, super.A, 36921);
          super.A = 3;
          this.wMem(34258, super.A, 36926);
          return;
        }

        int var130 = this.mem(33001, 38026);
        super.A = var130;
        this.wMem(33824, super.A, 38029);
        int var131 = this.mem(34259, 38032);
        super.A = var131;
        int var132 = super.A | 31;
        super.A = var132;
        super.F = super.A;
        int var133 = super.A & 254;
        super.A = var133;
        super.F = super.A;
        this.wMem(34259, super.A, 38039);
        this.incPops();
        super.nextAddress = 38043;
        return;
      }

      int var60 = this.mem(34258, 36930);
      super.A = var60;
      if (super.A != 3) {
        int var123 = super.A - 3;
        super.F = var123;
        int var124 = super.A + 1 & 255;
        super.A = var124;
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
        int var109 = this.mem16(34259, 36951);
        this.HL(var109);
        int var110 = this.mem(32986, 36954);
        super.A = var110;
        int var111 = super.A - 1 & 255;
        super.A = var111;
        super.F = super.A;
        int var112 = super.A | 157;
        super.A = var112;
        super.F = super.A;
        int var113 = super.A ^ 191;
        super.A = var113;
        super.F = super.A;
        super.E = super.A;
        super.D = 0;
        int var114 = this.HL();
        int var115 = this.DE();
        int var116 = var114 + var115 & '\uffff';
        this.HL(var116);
        int var117 = this.mem(32964, 36966);
        super.A = var117;
        int var118 = this.HL();
        int var119 = this.mem(var118, 36969);
        if (super.A == var119) {
          int var120 = super.A - var119;
          super.F = var120;
          this.BC(32);
          int var121 = this.mem(32986, 36975);
          super.A = var121;
          int var122 = super.A | super.A;
          super.A = var122;
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

        int var79 = super.A - var78;
        super.F = var79;
        int var80 = this.mem(34255, 37005);
        super.A = var80;
        int var81 = super.C >> 1;
        int var82 = super.C & 128;
        int var83 = var81 | var82;
        super.C = var83;
        int var84 = super.A + super.C;
        int var85 = var84 & 255;
        super.A = var85;
        super.F = var84;
        super.B = super.A;
        int var86 = super.A & 15;
        super.A = var86;
        if (super.A != 0) {
          int var98 = this.mem(32946, 37016);
          super.A = var98;
          int var99 = this.HL();
          int var100 = this.DE();
          int var101 = var99 + var100 & '\uffff';
          this.HL(var101);
          int var102 = this.HL();
          int var103 = this.mem(var102, 37020);
          if (super.A == var103) {
            return;
          }

          int var104 = super.A - var103;
          super.F = var104;
          int var105 = super.A | super.A;
          super.A = var105;
          super.F = super.A;
          int var106 = this.HL();
          int var107 = this.DE();
          int var108 = var106 - var107 & '\uffff';
          this.HL(var108);
        }

        int var87 = this.mem(32946, 37025);
        super.A = var87;
        int var88 = super.A | super.A;
        super.A = var88;
        super.F = super.A;
        int var89 = this.HL();
        int var90 = this.DE();
        int var91 = var89 - var90 & '\uffff';
        this.HL(var91);
        int var92 = this.HL();
        int var93 = this.mem(var92, 37031);
        if (super.A == var93) {
          return;
        }

        int var94 = super.A - var93;
        super.F = var94;
        int var95 = this.HL() - 1 & '\uffff';
        this.HL(var95);
        int var96 = this.HL();
        this.wMem16(34259, var96, 37034);
        int var97 = super.A ^ super.A;
        super.A = var97;
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
      this.incPops();
      super.nextAddress = 38061;
      return;
    }

    int var292 = this.mem(34255, 36540);
    super.A = var292;
    int var293 = super.A + 16;
    int var294 = var293 & 255;
    super.A = var294;
    super.F = var293;
    int var295 = super.A & 240;
    super.A = var295;
    super.F = super.A;
    this.wMem(34255, super.A, 36547);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A, 36555);
    this.HL(34256);
    int var296 = this.HL();
    int var297 = this.mem(var296, 36561) & -3;
    int var298 = this.HL();
    this.wMem(var298, var297, 36561);
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

      int var3 = super.A - 255;
      super.F = var3;
      int var4 = super.A & 3;
      super.A = var4;
      if (super.A != 0) {
        if (super.A != 1) {
          int var41 = super.A - 1;
          super.F = var41;
          if (super.A != 2) {
            int var72 = super.A - 2;
            super.F = var72;
            int var73 = this.IX();
            if ((this.mem(var73, 37081) & 128) != 0) {
              int var96 = this.IX() + 1;
              int var97 = this.mem(var96, 37087);
              super.A = var97;
              if ((super.A & 128) != 0) {
                int var103 = super.A - 2;
                int var104 = var103 & 255;
                super.A = var104;
                super.F = var103;
                if (super.A < 148) {
                  int var105 = super.A - 148;
                  super.F = var105;
                  int var106 = super.A - 2;
                  int var107 = var106 & 255;
                  super.A = var107;
                  super.F = var106;
                  if (super.A == 128) {
                    int var108 = super.A - 128;
                    super.F = var108;
                    int var109 = super.A ^ super.A;
                    super.A = var109;
                    super.F = super.A;
                  }
                }
              } else {
                int var98 = super.A + 2;
                int var99 = var98 & 255;
                super.A = var99;
                super.F = var98;
                if (super.A < 18) {
                  int var100 = super.A - 18;
                  super.F = var100;
                  int var101 = super.A + 2;
                  int var102 = var101 & 255;
                  super.A = var102;
                  super.F = var101;
                }
              }
            } else {
              int var74 = this.IX() + 1;
              int var75 = this.mem(var74, 37119);
              super.A = var75;
              if ((super.A & 128) == 0) {
                int var90 = super.A - 2;
                int var91 = var90 & 255;
                super.A = var91;
                super.F = var90;
                if (super.A < 20) {
                  int var92 = super.A - 20;
                  super.F = var92;
                  int var93 = super.A - 2;
                  int var94 = var93 & 255;
                  super.A = var94;
                  super.F = var93;
                  int var95 = super.A | super.A;
                  super.A = var95;
                  if (super.A == 0) {
                    super.A = 128;
                  }
                }
              } else {
                int var76 = super.A + 2;
                int var77 = var76 & 255;
                super.A = var77;
                super.F = var76;
                if (super.A < 146) {
                  int var87 = super.A - 146;
                  super.F = var87;
                  int var88 = super.A + 2;
                  int var89 = var88 & 255;
                  super.A = var89;
                  super.F = var88;
                }
              }
            }

            int var78 = this.IX() + 1;
            this.wMem(var78, super.A, 37149);
            int var79 = super.A & 127;
            super.A = var79;
            super.F = super.A;
            int var80 = this.IX() + 7;
            int var81 = this.mem(var80, 37154);
            if (super.A == var81) {
              int var82 = super.A - var81;
              super.F = var82;
              int var83 = this.IX();
              int var84 = this.mem(var83, 37160);
              super.A = var84;
              int var85 = super.A ^ 128;
              super.A = var85;
              super.F = super.A;
              int var86 = this.IX();
              this.wMem(var86, super.A, 37165);
            }
          } else {
            label83:
            {
              int var42 = this.IX();
              int var43 = this.mem(var42, 37247);
              super.A = var43;
              int var44 = super.A ^ 8;
              super.A = var44;
              super.F = super.A;
              int var45 = this.IX();
              this.wMem(var45, super.A, 37252);
              int var46 = super.A & 24;
              super.A = var46;
              if (super.A != 0) {
                int var67 = this.IX();
                int var68 = this.mem(var67, 37259);
                super.A = var68;
                int var69 = super.A + 32;
                int var70 = var69 & 255;
                super.A = var70;
                super.F = var69;
                int var71 = this.IX();
                this.wMem(var71, super.A, 37264);
              }

              int var47 = this.IX() + 3;
              int var48 = this.mem(var47, 37267);
              super.A = var48;
              int var49 = this.IX() + 4;
              int var50 = this.mem(var49, 37270);
              int var51 = super.A + var50;
              int var52 = var51 & 255;
              super.A = var52;
              super.F = var51;
              int var53 = this.IX() + 3;
              this.wMem(var53, super.A, 37273);
              int var54 = this.IX() + 7;
              int var55 = this.mem(var54, 37276);
              if (super.A < var55) {
                int var60 = super.A - var55;
                super.F = var60;
                int var61 = this.IX() + 6;
                int var62 = this.mem(var61, 37281);
                if (super.A != var62) {
                  if (super.A >= var62) {
                    break label83;
                  }

                  int var66 = super.A - var62;
                  super.F = var66;
                }

                int var63 = this.IX() + 6;
                int var64 = this.mem(var63, 37288);
                super.A = var64;
                int var65 = this.IX() + 3;
                this.wMem(var65, super.A, 37291);
              }

              int var56 = this.IX() + 4;
              int var57 = this.mem(var56, 37294);
              super.A = var57;
              int var58 = -super.A & 255;
              super.A = var58;
              int var59 = this.IX() + 4;
              this.wMem(var59, super.A, 37299);
            }
          }
        } else {
          int var8 = this.IX();
          if ((this.mem(var8, 37171) & 128) == 0) {
            int var25 = this.IX();
            int var26 = this.mem(var25, 37177);
            super.A = var26;
            int var27 = super.A - 32;
            int var28 = var27 & 255;
            super.A = var28;
            super.F = var27;
            int var29 = super.A & 127;
            super.A = var29;
            super.F = super.A;
            int var30 = this.IX();
            this.wMem(var30, super.A, 37184);
            if (super.A >= 96) {
              int var31 = super.A - 96;
              super.F = var31;
              int var32 = this.IX() + 2;
              int var33 = this.mem(var32, 37191);
              super.A = var33;
              int var34 = super.A & 31;
              super.A = var34;
              super.F = super.A;
              int var35 = this.IX() + 6;
              int var36 = this.mem(var35, 37196);
              if (super.A != var36) {
                int var38 = super.A - var36;
                super.F = var38;
                int var39 = this.IX() + 2;
                int var40 = this.mem(var39, 37201) - 1 & 255;
                this.wMem(var39, var40, 37201);
              } else {
                int var37 = this.IX();
                this.wMem(var37, 129, 37206);
              }
            }
          } else {
            int var9 = this.IX();
            int var10 = this.mem(var9, 37212);
            super.A = var10;
            int var11 = super.A + 32;
            int var12 = var11 & 255;
            super.A = var12;
            super.F = var11;
            int var13 = super.A | 128;
            super.A = var13;
            super.F = super.A;
            int var14 = this.IX();
            this.wMem(var14, super.A, 37219);
            if (super.A < 160) {
              int var15 = super.A - 160;
              super.F = var15;
              int var16 = this.IX() + 2;
              int var17 = this.mem(var16, 37226);
              super.A = var17;
              int var18 = super.A & 31;
              super.A = var18;
              super.F = super.A;
              int var19 = this.IX() + 7;
              int var20 = this.mem(var19, 37231);
              if (super.A != var20) {
                int var22 = super.A - var20;
                super.F = var22;
                int var23 = this.IX() + 2;
                int var24 = this.mem(var23, 37236) + 1 & 255;
                this.wMem(var23, var24, 37236);
              } else {
                int var21 = this.IX();
                this.wMem(var21, 97, 37241);
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
      int var2 = this.mem(var1, 37314);
      super.A = var2;
      if (super.A == 255) {
        return;
      }

      int var3 = super.A - 255;
      super.F = var3;
      int var4 = super.A & 7;
      super.A = var4;
      if (super.A != 0) {
        if (super.A != 3) {
          int var131 = super.A - 3;
          super.F = var131;
          if (super.A != 4) {
            int var186 = super.A - 4;
            super.F = var186;
            int var187 = this.IX() + 3;
            int var188 = this.mem(var187, 37334);
            super.E = var188;
            super.D = 130;
            int var189 = this.DE();
            int var190 = this.mem(var189, 37339);
            super.A = var190;
            super.L = super.A;
            int var191 = this.IX() + 2;
            int var192 = this.mem(var191, 37341);
            super.A = var192;
            int var193 = super.A & 31;
            super.A = var193;
            super.F = super.A;
            int var194 = super.A + super.L;
            int var195 = var194 & 255;
            super.A = var195;
            super.F = var194;
            super.L = super.A;
            super.A = super.E;
            int var196 = super.A;
            int var197 = this.rlc(var196);
            super.A = var197;
            int var198 = super.A & 1;
            super.A = var198;
            super.F = super.A;
            int var199 = super.A | 92;
            super.A = var199;
            super.F = super.A;
            super.H = super.A;
            this.DE(31);
            int var200 = this.IX() + 1;
            int var201 = this.mem(var200, 37358);
            super.A = var201;
            int var202 = super.A & 15;
            super.A = var202;
            super.F = super.A;
            int var203 = super.A + 56;
            int var204 = var203 & 255;
            super.A = var204;
            super.F = var203;
            int var205 = super.A & 71;
            super.A = var205;
            super.F = super.A;
            super.C = super.A;
            int var206 = this.HL();
            int var207 = this.mem(var206, 37368);
            super.A = var207;
            int var208 = super.A & 56;
            super.A = var208;
            super.F = super.A;
            int var209 = super.A ^ super.C;
            super.A = var209;
            super.F = super.A;
            super.C = super.A;
            int var210 = this.HL();
            this.wMem(var210, super.C, 37373);
            int var211 = this.HL() + 1 & '\uffff';
            this.HL(var211);
            int var212 = this.HL();
            this.wMem(var212, super.C, 37375);
            int var213 = this.HL();
            int var214 = this.DE();
            int var215 = var213 + var214 & '\uffff';
            this.HL(var215);
            int var216 = this.HL();
            this.wMem(var216, super.C, 37377);
            int var217 = this.HL() + 1 & '\uffff';
            this.HL(var217);
            int var218 = this.HL();
            this.wMem(var218, super.C, 37379);
            int var219 = this.IX() + 3;
            int var220 = this.mem(var219, 37380);
            super.A = var220;
            int var221 = super.A & 14;
            super.A = var221;
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
            int var222 = this.IX() + 1;
            int var223 = this.mem(var222, 37393);
            super.A = var223;
            int var224 = this.IX();
            int var225 = this.mem(var224, 37396);
            int var226 = super.A & var225;
            super.A = var226;
            super.F = super.A;
            int var227 = this.IX() + 2;
            int var228 = this.mem(var227, 37399);
            int var229 = super.A | var228;
            super.A = var229;
            super.F = super.A;
            int var230 = super.A & 224;
            super.A = var230;
            super.F = super.A;
            super.E = super.A;
            int var231 = this.IX() + 5;
            int var232 = this.mem(var231, 37405);
            super.D = var232;
            super.H = 130;
            int var233 = this.IX() + 3;
            int var234 = this.mem(var233, 37410);
            super.L = var234;
            int var235 = this.IX() + 2;
            int var236 = this.mem(var235, 37413);
            super.A = var236;
            int var237 = super.A & 31;
            super.A = var237;
            super.F = super.A;
            int var238 = this.HL();
            int var239 = this.mem(var238, 37418);
            int var240 = super.A | var239;
            super.A = var240;
            super.F = super.A;
            int var241 = this.HL() + 1 & '\uffff';
            this.HL(var241);
            int var242 = this.HL();
            int var243 = this.mem(var242, 37420);
            super.H = var243;
            super.L = super.A;
            this.$37974();
            if (super.F != 0) {
              this.incPops();
              super.nextAddress = 37048;
              return;
            }
          } else {
            int var132 = this.IX();
            if ((this.mem(var132, 37431) & 128) == 0) {
              int var184 = this.IX() + 4;
              int var185 = this.mem(var184, 37437) - 1 & 255;
              this.wMem(var184, var185, 37437);
              super.C = 44;
            } else {
              int var133 = this.IX() + 4;
              int var134 = this.mem(var133, 37444) + 1 & 255;
              this.wMem(var133, var134, 37444);
              super.C = 244;
            }

            int var135 = this.IX() + 4;
            int var136 = this.mem(var135, 37449);
            super.A = var136;
            if (super.A != super.C) {
              int var137 = super.A & 224;
              super.A = var137;
              if (super.A == 0) {
                int var138 = this.IX() + 2;
                int var139 = this.mem(var138, 37479);
                super.E = var139;
                super.D = 130;
                int var140 = this.DE();
                int var141 = this.mem(var140, 37484);
                super.A = var141;
                int var142 = this.IX() + 4;
                int var143 = this.mem(var142, 37485);
                int var144 = super.A + var143;
                int var145 = var144 & 255;
                super.A = var145;
                super.F = var144;
                super.L = super.A;
                super.A = super.E;
                int var146 = super.A & 128;
                super.A = var146;
                super.F = super.A;
                int var147 = super.A;
                int var148 = this.rlc(var147);
                super.A = var148;
                int var149 = super.A | 92;
                super.A = var149;
                super.F = super.A;
                super.H = super.A;
                int var150 = this.IX() + 5;
                this.wMem(var150, 0, 37496);
                int var151 = this.HL();
                int var152 = this.mem(var151, 37500);
                super.A = var152;
                int var153 = super.A & 7;
                super.A = var153;
                super.F = super.A;
                if (super.A == 7) {
                  int var176 = super.A - 7;
                  super.F = var176;
                  int var177 = this.IX() + 5;
                  int var178 = this.mem(var177, 37507) - 1 & 255;
                  this.wMem(var177, var178, 37507);
                }

                int var154 = this.HL();
                int var155 = this.mem(var154, 37510);
                super.A = var155;
                int var156 = super.A | 7;
                super.A = var156;
                super.F = super.A;
                int var157 = this.HL();
                this.wMem(var157, super.A, 37513);
                int var158 = this.DE() + 1 & '\uffff';
                this.DE(var158);
                int var159 = this.DE();
                int var160 = this.mem(var159, 37515);
                super.A = var160;
                super.H = super.A;
                int var161 = super.H - 1 & 255;
                super.H = var161;
                super.F = super.H;
                int var162 = this.IX() + 6;
                int var163 = this.mem(var162, 37518);
                super.A = var163;
                int var164 = this.HL();
                this.wMem(var164, super.A, 37521);
                int var165 = super.H + 1 & 255;
                super.H = var165;
                super.F = super.H;
                int var166 = this.HL();
                int var167 = this.mem(var166, 37523);
                super.A = var167;
                int var168 = this.IX() + 5;
                int var169 = this.mem(var168, 37524);
                int var170 = super.A & var169;
                super.A = var170;
                if (super.A != 0) {
                  this.incPops();
                  super.nextAddress = 37048;
                  return;
                }

                int var171 = this.HL();
                this.wMem(var171, 255, 37530);
                int var172 = super.H + 1 & 255;
                super.H = var172;
                super.F = super.H;
                int var173 = this.IX() + 6;
                int var174 = this.mem(var173, 37533);
                super.A = var174;
                int var175 = this.HL();
                this.wMem(var175, super.A, 37536);
              }
            } else {
              int var179 = super.A - super.C;
              super.F = var179;
              this.BC(640);
              int var180 = this.mem(32990, 37458);
              super.A = var180;

              do {
                int var181 = super.A ^ 24;
                super.A = var181;

                do {
                  int var182 = super.B - 1 & 255;
                  super.B = var182;
                } while (super.B != 0);

                super.B = super.C;
                int var183 = super.C - 1 & 255;
                super.C = var183;
              } while (super.C != 0);
            }
          }
        } else {
          this.IY(33280);
          int var8 = this.IX() + 9;
          this.wMem(var8, 0, 37544);
          int var9 = this.IX() + 2;
          int var10 = this.mem(var9, 37548);
          super.A = var10;
          int var11 = this.IX() + 3;
          this.wMem(var11, super.A, 37551);
          int var12 = this.IX() + 5;
          this.wMem(var12, 128, 37554);

          while (true) {
            label114:
            {
              int var13 = this.IY();
              int var14 = this.mem(var13, 37558);
              super.A = var14;
              int var15 = this.IX() + 3;
              int var16 = this.mem(var15, 37561);
              int var17 = super.A + var16;
              int var18 = var17 & 255;
              super.A = var18;
              super.F = var17;
              super.L = super.A;
              int var19 = this.IY() + 1;
              int var20 = this.mem(var19, 37565);
              super.H = var20;
              int var21 = this.mem(34262, 37568);
              super.A = var21;
              int var22 = super.A | super.A;
              super.A = var22;
              if (super.A == 0) {
                int var122 = this.IX() + 5;
                int var123 = this.mem(var122, 37574);
                super.A = var123;
                int var124 = this.HL();
                int var125 = this.mem(var124, 37577);
                int var126 = super.A & var125;
                super.A = var126;
                if (super.A == 0) {
                  break label114;
                }

                int var127 = this.IX() + 9;
                int var128 = this.mem(var127, 37580);
                super.A = var128;
                this.wMem(34262, super.A, 37583);
                int var129 = this.IX() + 11;
                int var130 = this.mem(var129, 37586) | 1;
                this.wMem(var129, var130, 37586);
              }

              int var23 = this.IX() + 9;
              int var24 = this.mem(var23, 37590);
              if (super.A == var24) {
                int var107 = super.A - var24;
                super.F = var107;
                int var108 = this.IX() + 11;
                if ((this.mem(var108, 37595) & 1) != 0) {
                  int var109 = this.IX() + 3;
                  int var110 = this.mem(var109, 37601);
                  super.B = var110;
                  int var111 = this.IX() + 5;
                  int var112 = this.mem(var111, 37604);
                  super.A = var112;
                  super.C = 1;
                  if (super.A >= 4) {
                    int var118 = super.A - 4;
                    super.F = var118;
                    super.C = 0;
                    if (super.A >= 16) {
                      int var119 = super.A - 16;
                      super.F = var119;
                      int var120 = super.B - 1 & 255;
                      super.B = var120;
                      super.F = super.B;
                      super.C = 3;
                      if (super.A >= 64) {
                        int var121 = super.A - 64;
                        super.F = var121;
                        super.C = 2;
                      }
                    }
                  }

                  int var113 = this.BC();
                  this.wMem16(34258, var113, 37628);
                  super.A = super.IYL;
                  int var114 = super.A - 16;
                  int var115 = var114 & 255;
                  super.A = var115;
                  super.F = var114;
                  this.wMem(34255, super.A, 37636);
                  int var116 = this.HL();
                  this.push(var116);
                  this.$36508();
                  int var117 = this.pop();
                  this.HL(var117);
                }
              }
            }

            int var25 = this.IX() + 5;
            int var26 = this.mem(var25, 37646);
            super.A = var26;
            int var27 = this.HL();
            int var28 = this.mem(var27, 37649);
            int var29 = super.A | var28;
            super.A = var29;
            super.F = super.A;
            int var30 = this.HL();
            this.wMem(var30, super.A, 37650);
            int var31 = this.IX() + 9;
            int var32 = this.mem(var31, 37651);
            super.A = var32;
            int var33 = this.IX() + 1;
            int var34 = this.mem(var33, 37654);
            int var35 = super.A + var34;
            int var36 = var35 & 255;
            super.A = var36;
            super.F = var35;
            super.L = super.A;
            int var37 = super.L | 128;
            super.L = var37;
            super.H = 131;
            int var38 = this.HL();
            int var39 = this.mem(var38, 37662);
            super.E = var39;
            super.D = 0;
            int var40 = this.IY();
            int var41 = this.DE();
            int var42 = var40 + var41 & '\uffff';
            this.IY(var42);
            int var43 = super.L & -129;
            super.L = var43;
            int var44 = this.HL();
            int var45 = this.mem(var44, 37669);
            super.A = var45;
            int var46 = super.A | super.A;
            super.A = var46;
            if (super.A != 0) {
              super.B = super.A;
              int var92 = this.IX() + 1;
              if ((this.mem(var92, 37674) & 128) != 0) {
                do {
                  int var100 = this.IX() + 5;
                  int var101 = this.mem(var100, 37680);
                  int var102 = this.rlc(var101);
                  this.wMem(var100, var102, 37680);
                  int var103 = this.IX() + 5;
                  if ((this.mem(var103, 37684) & 1) != 0) {
                    int var105 = this.IX() + 3;
                    int var106 = this.mem(var105, 37690) - 1 & 255;
                    this.wMem(var105, var106, 37690);
                  }

                  int var104 = super.B - 1 & 255;
                  super.B = var104;
                } while (super.B != 0);
              } else {
                do {
                  int var93 = this.IX() + 5;
                  int var94 = this.mem(var93, 37697);
                  int var95 = this.rrc(var94);
                  this.wMem(var93, var95, 37697);
                  int var96 = this.IX() + 5;
                  if ((this.mem(var96, 37701) & 128) != 0) {
                    int var98 = this.IX() + 3;
                    int var99 = this.mem(var98, 37707) + 1 & 255;
                    this.wMem(var98, var99, 37707);
                  }

                  int var97 = super.B - 1 & 255;
                  super.B = var97;
                } while (super.B != 0);
              }
            }

            int var47 = this.IX() + 9;
            int var48 = this.mem(var47, 37712);
            super.A = var48;
            int var49 = this.IX() + 4;
            int var50 = this.mem(var49, 37715);
            if (super.A == var50) {
              int var51 = this.mem(34262, 37726);
              super.A = var51;
              if ((super.A & 128) != 0) {
                int var86 = super.A + 1 & 255;
                super.A = var86;
                super.F = super.A;
                this.wMem(34262, super.A, 37734);
                int var87 = this.IX() + 11;
                int var88 = this.mem(var87, 37737) & -2;
                this.wMem(var87, var88, 37737);
              } else {
                int var52 = this.IX() + 11;
                if ((this.mem(var52, 37743) & 1) != 0) {
                  int var53 = this.mem(34256, 37749);
                  super.A = var53;
                  if ((super.A & 2) != 0) {
                    int var54 = super.A;
                    int var55 = this.rrc(var54);
                    super.A = var55;
                    int var56 = this.IX();
                    int var57 = this.mem(var56, 37757);
                    int var58 = super.A ^ var57;
                    super.A = var58;
                    super.F = super.A;
                    int var59 = super.A;
                    int var60 = this.rlc(var59);
                    super.A = var60;
                    int var61 = super.A;
                    int var62 = this.rlc(var61);
                    super.A = var62;
                    int var63 = super.A & 2;
                    super.A = var63;
                    super.F = super.A;
                    int var64 = super.A - 1 & 255;
                    super.A = var64;
                    super.F = super.A;
                    this.HL(34262);
                    int var65 = this.HL();
                    int var66 = this.mem(var65, 37768);
                    int var67 = super.A + var66;
                    int var68 = var67 & 255;
                    super.A = var68;
                    super.F = var67;
                    int var69 = this.HL();
                    this.wMem(var69, super.A, 37769);
                    int var70 = this.mem(33003, 37770);
                    super.A = var70;
                    super.C = super.A;
                    int var71 = this.mem(33824, 37774);
                    super.A = var71;
                    if (super.A == super.C) {
                      int var81 = super.A - super.C;
                      super.F = var81;
                      int var82 = this.HL();
                      int var83 = this.mem(var82, 37780);
                      super.A = var83;
                      if (super.A < 12) {
                        int var84 = super.A - 12;
                        super.F = var84;
                        int var85 = this.HL();
                        this.wMem(var85, 12, 37785);
                      }
                    }

                    int var72 = this.HL();
                    int var73 = this.mem(var72, 37787);
                    super.A = var73;
                    int var74 = this.IX() + 4;
                    int var75 = this.mem(var74, 37788);
                    if (super.A >= var75 && super.A != var75) {
                      int var76 = super.A - var75;
                      super.F = var76;
                      int var77 = this.HL();
                      this.wMem(var77, 240, 37795);
                      int var78 = this.mem(34255, 37797);
                      super.A = var78;
                      int var79 = super.A & 248;
                      super.A = var79;
                      super.F = super.A;
                      this.wMem(34255, super.A, 37802);
                      int var80 = super.A ^ super.A;
                      super.A = var80;
                      super.F = super.A;
                      this.wMem(34257, super.A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var89 = super.A - var50;
            super.F = var89;
            int var90 = this.IX() + 9;
            int var91 = this.mem(var90, 37720) + 1 & 255;
            this.wMem(var90, var91, 37720);
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
        int var8 = super.A - super.C;
        super.F = var8;
        int var9 = this.HL();
        int var10 = this.mem(var9, 37858);
        super.A = var10;
        int var11 = super.A;
        int var12 = this.rlc(var11);
        super.A = var12;
        int var13 = super.A & 1;
        super.A = var13;
        super.F = super.A;
        int var14 = super.A + 92;
        int var15 = var14 & 255;
        super.A = var15;
        super.F = var14;
        super.D = super.A;
        int var16 = super.H + 1 & 255;
        super.H = var16;
        super.F = super.H;
        int var17 = this.HL();
        int var18 = this.mem(var17, 37866);
        super.E = var18;
        int var19 = super.H - 1 & 255;
        super.H = var19;
        super.F = super.H;
        int var20 = this.DE();
        int var21 = this.mem(var20, 37868);
        super.A = var21;
        int var22 = super.A & 7;
        super.A = var22;
        super.F = super.A;
        if (super.A != 7) {
          int var23 = this.mem(34251, 37936);
          super.A = var23;
          int var24 = super.A + super.L;
          int var25 = var24 & 255;
          super.A = var25;
          super.F = var24;
          int var26 = super.A & 3;
          super.A = var26;
          super.F = super.A;
          int var27 = super.A + 3;
          int var28 = var27 & 255;
          super.A = var28;
          super.F = var27;
          super.C = super.A;
          int var29 = this.DE();
          int var30 = this.mem(var29, 37945);
          super.A = var30;
          int var31 = super.A & 248;
          super.A = var31;
          super.F = super.A;
          int var32 = super.A | super.C;
          super.A = var32;
          super.F = super.A;
          int var33 = this.DE();
          this.wMem(var33, super.A, 37949);
          int var34 = this.HL();
          int var35 = this.mem(var34, 37950);
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
          int var42 = super.A;
          int var43 = this.rlc(var42);
          super.A = var43;
          int var44 = super.A & 8;
          super.A = var44;
          super.F = super.A;
          int var45 = super.A + 96;
          int var46 = var45 & 255;
          super.A = var46;
          super.F = var45;
          super.D = super.A;
          int var47 = this.HL();
          this.push(var47);
          this.HL(32993);
          super.B = 8;
          this.$38555();
          int var48 = this.pop();
          this.HL(var48);
        } else {
          int var49 = super.A - 7;
          super.F = var49;
          this.IX(34172);

          while (true) {
            int var50 = this.IX() + 2;
            int var51 = this.mem(var50, 37879) + 1 & 255;
            this.wMem(var50, var51, 37879);
            int var52 = this.IX() + 2;
            int var53 = this.mem(var52, 37882);
            super.A = var53;
            if (super.A != 58) {
              int var54 = this.mem(32990, 37897);
              super.A = var54;
              super.C = 128;

              do {
                int var55 = super.A ^ 24;
                super.A = var55;
                super.F = super.A;
                super.E = super.A;
                super.A = 144;
                int var56 = super.A - super.C;
                int var57 = var56 & 255;
                super.A = var57;
                super.F = var56;
                super.B = super.A;
                super.A = super.E;

                do {
                  int var58 = super.B - 1 & 255;
                  super.B = var58;
                } while (super.B != 0);

                int var59 = super.C - 1 & 255;
                super.C = var59;
                super.F = super.C;
                int var60 = super.C - 1 & 255;
                super.C = var60;
              } while (super.C != 0);

              int var61 = this.mem(34270, 37918);
              super.A = var61;
              int var62 = super.A + 1 & 255;
              super.A = var62;
              super.F = super.A;
              this.wMem(34270, super.A, 37922);
              if (super.F == 0) {
                super.A = 1;
                this.wMem(34271, super.A, 37929);
              }

              int var63 = this.HL();
              int var64 = this.mem(var63, 37932) & -65;
              int var65 = this.HL();
              this.wMem(var65, var64, 37932);
              break;
            }

            int var66 = super.A - 58;
            super.F = var66;
            int var67 = this.IX() + 2;
            this.wMem(var67, 48, 37889);
            int var68 = this.IX() - 1 & '\uffff';
            this.IX(var68);
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
    this.incPops();
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
      int var17 = super.A - 35;
      super.F = var17;
      int var18 = this.mem(34271, 38203);
      super.A = var18;
      int var19 = super.A | super.A;
      super.A = var19;
      if (super.A == 0) {
        int var23 = this.mem(34251, 38209);
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
        int var34 = this.mem(34255, 38221);
        super.A = var34;
        if (super.A != 208) {
          int var37 = super.A - 208;
          super.F = var37;
          super.E = 192;
          if (super.A < 192) {
            int var38 = super.A - 192;
            super.F = var38;
            super.E = 224;
          }
        }

        super.D = 156;
        this.HL(26734);
        super.C = 1;
        this.$37974();
        if (super.F != 0) {
          this.incPops();
          super.nextAddress = 37048;
        } else {
          this.HL(17733);
          int var35 = this.HL();
          this.wMem16(23918, var35, 38252);
          this.HL(1799);
          int var36 = this.HL();
          this.wMem16(23950, var36, 38258);
        }
      } else {
        int var20 = this.mem(34259, 38262);
        super.A = var20;
        int var21 = super.A & 31;
        super.A = var21;
        super.F = super.A;
        if (super.A < 6) {
          int var22 = super.A - 6;
          super.F = var22;
          super.A = 2;
          this.wMem(34271, super.A, 38272);
        }
      }
    } else {
      int var2 = this.mem(33824, 38298);
      super.A = var2;
      if (super.A == 33) {
        int var3 = super.A - 33;
        super.F = var3;
        int var4 = this.mem(34251, 38304);
        super.A = var4;
        int var5 = super.A & 1;
        super.A = var5;
        super.F = super.A;
        int var6 = super.A;
        int var7 = this.rrc(var6);
        super.A = var7;
        int var8 = super.A;
        int var9 = this.rrc(var8);
        super.A = var9;
        int var10 = super.A;
        int var11 = this.rrc(var10);
        super.A = var11;
        super.E = super.A;
        int var12 = this.mem(34271, 38313);
        super.A = var12;
        if (super.A == 3) {
          int var15 = super.A - 3;
          super.F = var15;
          int var16 = super.E | 64;
          super.E = var16;
        }

        super.D = 166;
        this.IX(33488);
        this.BC(4124);
        this.$38504();
        this.HL(1799);
        int var13 = this.HL();
        this.wMem16(23996, var13, 38337);
        int var14 = this.HL();
        this.wMem16(24028, var14, 38340);
      }
    }
  }

  public void $38276() {
    int var1 = this.mem(33824, 38276);
    super.A = var1;
    if (super.A == 33) {
      int var2 = super.A - 33;
      super.F = var2;
      int var3 = this.mem(34259, 38282);
      super.A = var3;
      if (super.A == 188) {
        int var4 = super.A - 188;
        super.F = var4;
        int var5 = super.A ^ super.A;
        super.A = var5;
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
      int var46 = super.A - var11;
      super.F = var46;
      int var47 = this.mem(34257, 38366);
      super.A = var47;
      int var48 = super.A | super.A;
      super.A = var48;
      if (super.A == 0) {
        int var49 = this.mem(34258, 38372);
        super.A = var49;
        int var50 = super.A & 3;
        super.A = var50;
        super.F = super.A;
        int var51 = super.A;
        int var52 = this.rlc(var51);
        super.A = var52;
        int var53 = super.A;
        int var54 = this.rlc(var53);
        super.A = var54;
        super.B = super.A;
        int var55 = this.mem(32986, 38380);
        super.A = var55;
        int var56 = super.A & 1;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A - 1 & 255;
        super.A = var57;
        super.F = super.A;
        int var58 = super.A ^ 12;
        super.A = var58;
        super.F = super.A;
        int var59 = super.A ^ super.B;
        super.A = var59;
        super.F = super.A;
        int var60 = super.A & 12;
        super.A = var60;
        super.F = super.A;
        super.B = super.A;
      }
    }

    int var12 = this.mem16(34259, 38392);
    this.HL(var12);
    this.DE(31);
    super.C = 15;
    this.$38430();
    if (super.nextAddress == 37047) {
      super.nextAddress = 37048;
    } else {
      int var13 = this.HL() + 1 & '\uffff';
      this.HL(var13);
      this.$38430();
      if (super.nextAddress == 37047) {
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
        if (super.nextAddress == 37047) {
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
          if (super.nextAddress == 37047) {
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
              int var44 = super.A - 29;
              super.F = var44;
              super.D = 182;
              super.A = super.E;
              int var45 = super.A ^ 128;
              super.A = var45;
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
      int var8 = super.A - var3;
      super.F = var8;
      super.A = super.C;
      int var9 = super.A & 15;
      super.A = var9;
      if (super.A != 0) {
        int var10 = this.mem(32928, 38441);
        super.A = var10;
        int var11 = super.A | 7;
        super.A = var11;
        super.F = super.A;
        int var12 = this.HL();
        this.wMem(var12, super.A, 38446);
      }
    }

    int var4 = this.mem(32955, 38447);
    super.A = var4;
    int var5 = this.HL();
    int var6 = this.mem(var5, 38450);
    if (super.A == var6) {
      this.incPops();
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

      int var3 = super.A - 255;
      super.F = var3;
      this.BC(100);
      int var4 = super.A ^ super.A;
      super.A = var4;
      super.F = super.A;
      int var5 = this.HL();
      int var6 = this.mem(var5, 38570);
      super.E = var6;
      super.D = super.E;

      while (true) {
        int var7 = super.D - 1 & 255;
        super.D = var7;
        if (super.D == 0) {
          super.D = super.E;
          int var14 = super.A ^ 24;
          super.A = var14;
        }

        int var8 = super.B - 1 & 255;
        super.B = var8;
        if (super.B == 0) {
          this.exAF();
          super.A = super.C;
          if (super.A == 50) {
            int var11 = super.A - 50;
            super.F = var11;
            int var12 = super.E;
            int var13 = this.rl(var12);
            super.E = var13;
          }

          this.exAF();
          int var9 = super.C - 1 & 255;
          super.C = var9;
          if (super.C == 0) {
            this.$38601();
            if (super.F != 0) {
              return;
            }

            int var10 = this.HL() + 1 & '\uffff';
            this.HL(var10);
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
          int var5 = super.A - super.B;
          super.F = var5;
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
