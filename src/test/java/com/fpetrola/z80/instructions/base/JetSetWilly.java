package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.transformations.SpectrumApplication;

import java.util.HashMap;
import java.util.Map;

public class JetSetWilly extends SpectrumApplication {
  protected boolean replacing = false;

  protected Map<Integer, Runnable> getConvertedRoutines() {
    Map<Integer, Runnable> convertedRoutines = new HashMap<>();
    convertedRoutines.put(35211, () -> $35211());
    convertedRoutines.put(35563, () -> $35563());
    convertedRoutines.put(36147, () -> $36147());
    convertedRoutines.put(36171, () -> $36171());
    convertedRoutines.put(36203, () -> $36203());
    convertedRoutines.put(36288, () -> $36288());
    convertedRoutines.put(36508, () -> $36508());
    convertedRoutines.put(37056, () -> $37056());
    convertedRoutines.put(37310, () -> $37310());
    convertedRoutines.put(37841, () -> $37841());
    convertedRoutines.put(37974, () -> $37974());
    convertedRoutines.put(38064, () -> $38064());
    convertedRoutines.put(38137, () -> $38137());
    convertedRoutines.put(38276, () -> $38276());
    convertedRoutines.put(38430, () -> $38430());
    convertedRoutines.put(38528, () -> $38528());
    convertedRoutines.put(38545, () -> $38545());
    convertedRoutines.put(38555, () -> $38555());
    convertedRoutines.put(34463, () -> $34463());


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
            int var1 = this.mem(34262, 36307);
            super.A = var1;
            int var2 = super.A + -1 & 255;
            super.A = var2;
            super.F = super.A;
            int var3 = super.A & 128;
            super.F = var3;
            if (super.F != 0) {
              int var264 = this.mem(34257, 36316);
              super.A = var264;
              int var265 = super.A - 1;
              super.F = var265;
              if (super.F == 0) {
                int var331 = this.mem(34261, 36323);
                super.A = var331;
                int var332 = super.A & 254;
                super.A = var332;
                super.F = super.A;
                int var333 = super.A - 8 & 255;
                super.A = var333;
                super.F = super.A;
                this.HL(34255);
                int var334 = this.HL();
                int var335 = this.mem(var334, 36333);
                int var336 = super.A + var335 & 255;
                super.A = var336;
                int var337 = this.HL();
                this.mem(var337, 36333);
                int var338 = this.HL();
                this.mem(var338, 36333);
                super.F = super.A;
                int var339 = this.HL();
                this.mem(var339, 36333);
                int var340 = this.HL();
                this.wMem(var340, super.A, 36334);
                int var341 = super.A - 240;
                super.F = var341;
                if (super.F >= 0) {
                  int var342 = this.mem(33003, 38064);
                  super.A = var342;
                  this.wMem(33824, super.A, 38067);
                  int var343 = this.mem(34259, 38070);
                  super.A = var343;
                  int var344 = super.A & 31;
                  super.A = var344;
                  super.F = super.A;
                  int var345 = super.A + 160 & 255;
                  super.A = var345;
                  super.F = super.A;
                  this.wMem(34259, super.A, 38077);
                  super.A = 93;
                  this.wMem(34260, super.A, 38082);
                  super.A = 208;
                  this.wMem(34255, super.A, 38087);
                  int var346 = super.A ^ super.A;
                  super.A = var346;
                  super.F = super.A;
                  this.wMem(34257, super.A, 38091);
                  break label251;
                }

                this.$36508();
                int var347 = this.mem(32946, 36343);
                super.A = var347;
                int var348 = this.HL();
                int var349 = this.mem(var348, 36346);
                int var350 = super.A - var349;
                super.F = var350;
                int var351 = this.HL();
                this.mem(var351, 36346);
                if (super.F == 0) {
                  break label250;
                }

                int var358 = this.HL() + 1 & '\uffff';
                this.HL(var358);
                int var359 = this.HL();
                int var360 = this.mem(var359, 36351);
                int var361 = super.A - var360;
                super.F = var361;
                int var362 = this.HL();
                this.mem(var362, 36351);
                if (super.F == 0) {
                  break label250;
                }

                int var363 = this.mem(34261, 36355);
                super.A = var363;
                int var364 = super.A + 1 & 255;
                super.A = var364;
                super.F = super.A;
                this.wMem(34261, super.A, 36359);
                int var365 = super.A - 8 & 255;
                super.A = var365;
                super.F = super.A;
                int var366 = -super.A & 255;
                super.A = var366;
                int var367 = super.A + 1 & 255;
                super.A = var367;
                super.F = super.A;
                int var368 = super.A;
                int var369 = this.rlc(var368);
                super.A = var369;
                int var370 = super.A;
                int var371 = this.rlc(var370);
                super.A = var371;
                int var372 = super.A;
                int var373 = this.rlc(var372);
                super.A = var373;
                super.D = super.A;
                super.C = 32;
                int var374 = this.mem(32990, 36376);
                super.A = var374;

                do {
                  int var375 = super.A ^ 24;
                  super.A = var375;
                  super.F = super.A;
                  super.B = super.D;

                  do {
                    int var376 = super.B + -1 & 255;
                    super.B = var376;
                  } while (super.B != 0);

                  int var377 = super.C + -1 & 255;
                  super.C = var377;
                  super.F = super.C;
                } while (super.F != 0);

                int var378 = this.mem(34261, 36389);
                super.A = var378;
                int var379 = super.A - 18;
                super.F = var379;
                if (super.F == 0) {
                  super.A = 6;
                  this.wMem(34257, super.A, 36530);
                  return;
                }

                int var380 = super.A - 16;
                super.F = var380;
                if (super.F != 0) {
                  int var381 = super.A - 13;
                  super.F = var381;
                  if (super.F != 0) {
                    break label246;
                  }
                }
              }

              int var266 = this.mem(34255, 36406);
              super.A = var266;
              int var267 = super.A & 14;
              super.A = var267;
              super.F = super.A;
              if (super.F != 0) {
                break label266;
              }

              int var300 = this.mem16(34259, 36413);
              this.HL(var300);
              this.DE(64);
              int var301 = this.HL();
              int var302 = this.DE();
              int var303 = var301 + var302 & '\uffff';
              this.HL(var303);
              int var304 = super.H & 2;
              super.F = var304;
              if (super.F != 0) {
                break label251;
              }

              int var311 = this.mem(32955, 36425);
              super.A = var311;
              int var312 = this.HL();
              int var313 = this.mem(var312, 36428);
              int var314 = super.A - var313;
              super.F = var314;
              int var315 = this.HL();
              this.mem(var315, 36428);
              if (super.F == 0) {
                break label266;
              }

              int var316 = this.HL() + 1 & '\uffff';
              this.HL(var316);
              int var317 = this.mem(32955, 36432);
              super.A = var317;
              int var318 = this.HL();
              int var319 = this.mem(var318, 36435);
              int var320 = super.A - var319;
              super.F = var320;
              int var321 = this.HL();
              this.mem(var321, 36435);
              if (super.F == 0) {
                break label266;
              }

              int var322 = this.mem(32928, 36438);
              super.A = var322;
              int var323 = this.HL();
              int var324 = this.mem(var323, 36441);
              int var325 = super.A - var324;
              super.F = var325;
              int var326 = this.HL();
              this.mem(var326, 36441);
              if (super.F == 0) {
                int var327 = this.HL();
                int var328 = this.mem(var327, 36446);
                int var329 = super.A - var328;
                super.F = var329;
                int var330 = this.HL();
                this.mem(var330, 36446);
                if (super.F == 0) {
                  break label266;
                }
              }
            }

            super.E = 255;
            int var4 = this.mem(34262, 36566);
            super.A = var4;
            int var5 = super.A + -1 & 255;
            super.A = var5;
            super.F = super.A;
            int var6 = super.A & 128;
            super.F = var6;
            if (super.F != 0) {
              label263:
              {
                int var138 = this.mem(34257, 36574);
                super.A = var138;
                int var139 = super.A - 12;
                super.F = var139;
                if (super.F >= 0) {
                  super.A = 255;
                  this.wMem(34257, super.A, 37050);
                  this.IX(33024);

                  while (true) {
                    int var140 = this.IX();
                    int var141 = this.mem(var140, 37060);
                    super.A = var141;
                    int var142 = super.A - 255;
                    super.F = var142;
                    if (super.F == 0) {
                      return;
                    }

                    int var143 = super.A & 3;
                    super.A = var143;
                    super.F = super.A;
                    if (super.F != 0) {
                      int var147 = super.A - 1;
                      super.F = var147;
                      if (super.F != 0) {
                        int var184 = super.A - 2;
                        super.F = var184;
                        if (super.F != 0) {
                          int var218 = this.IX();
                          int var219 = this.mem(var218, 37081) & 128;
                          super.F = var219;
                          if (super.F != 0) {
                            int var240 = this.IX() + 1;
                            int var241 = this.mem(var240, 37087);
                            super.A = var241;
                            int var242 = super.A & 128;
                            super.F = var242;
                            if (super.F != 0) {
                              int var246 = super.A - 2 & 255;
                              super.A = var246;
                              super.F = super.A;
                              int var247 = super.A - 148;
                              super.F = var247;
                              if (super.F < 0) {
                                int var248 = super.A - 2 & 255;
                                super.A = var248;
                                super.F = super.A;
                                int var249 = super.A - 128;
                                super.F = var249;
                                if (super.F == 0) {
                                  int var250 = super.A ^ super.A;
                                  super.A = var250;
                                  super.F = super.A;
                                }
                              }
                            } else {
                              int var243 = super.A + 2 & 255;
                              super.A = var243;
                              super.F = super.A;
                              int var244 = super.A - 18;
                              super.F = var244;
                              if (super.F < 0) {
                                int var245 = super.A + 2 & 255;
                                super.A = var245;
                                super.F = super.A;
                              }
                            }
                          } else {
                            int var220 = this.IX() + 1;
                            int var221 = this.mem(var220, 37119);
                            super.A = var221;
                            int var222 = super.A & 128;
                            super.F = var222;
                            if (super.F == 0) {
                              int var236 = super.A - 2 & 255;
                              super.A = var236;
                              super.F = super.A;
                              int var237 = super.A - 20;
                              super.F = var237;
                              if (super.F < 0) {
                                int var238 = super.A - 2 & 255;
                                super.A = var238;
                                super.F = super.A;
                                int var239 = super.A | super.A;
                                super.A = var239;
                                super.F = super.A;
                                if (super.F == 0) {
                                  super.A = 128;
                                }
                              }
                            } else {
                              int var223 = super.A + 2 & 255;
                              super.A = var223;
                              super.F = super.A;
                              int var224 = super.A - 146;
                              super.F = var224;
                              if (super.F < 0) {
                                int var235 = super.A + 2 & 255;
                                super.A = var235;
                                super.F = super.A;
                              }
                            }
                          }

                          int var225 = this.IX() + 1;
                          this.wMem(var225, super.A, 37149);
                          int var226 = super.A & 127;
                          super.A = var226;
                          super.F = super.A;
                          int var227 = this.IX() + 7;
                          int var228 = this.mem(var227, 37154);
                          int var229 = super.A - var228;
                          super.F = var229;
                          int var230 = this.IX() + 7;
                          this.mem(var230, 37154);
                          if (super.F == 0) {
                            int var231 = this.IX();
                            int var232 = this.mem(var231, 37160);
                            super.A = var232;
                            int var233 = super.A ^ 128;
                            super.A = var233;
                            super.F = super.A;
                            int var234 = this.IX();
                            this.wMem(var234, super.A, 37165);
                          }
                        } else {
                          label265:
                          {
                            int var185 = this.IX();
                            int var186 = this.mem(var185, 37247);
                            super.A = var186;
                            int var187 = super.A ^ 8;
                            super.A = var187;
                            super.F = super.A;
                            int var188 = this.IX();
                            this.wMem(var188, super.A, 37252);
                            int var189 = super.A & 24;
                            super.A = var189;
                            super.F = super.A;
                            if (super.F != 0) {
                              int var214 = this.IX();
                              int var215 = this.mem(var214, 37259);
                              super.A = var215;
                              int var216 = super.A + 32 & 255;
                              super.A = var216;
                              super.F = super.A;
                              int var217 = this.IX();
                              this.wMem(var217, super.A, 37264);
                            }

                            int var190 = this.IX() + 3;
                            int var191 = this.mem(var190, 37267);
                            super.A = var191;
                            int var192 = this.IX() + 4;
                            int var193 = this.mem(var192, 37270);
                            int var194 = super.A + var193 & 255;
                            super.A = var194;
                            int var195 = this.IX() + 4;
                            this.mem(var195, 37270);
                            int var196 = this.IX() + 4;
                            this.mem(var196, 37270);
                            super.F = super.A;
                            int var197 = this.IX() + 4;
                            this.mem(var197, 37270);
                            int var198 = this.IX() + 3;
                            this.wMem(var198, super.A, 37273);
                            int var199 = this.IX() + 7;
                            int var200 = this.mem(var199, 37276);
                            int var201 = super.A - var200;
                            super.F = var201;
                            int var202 = this.IX() + 7;
                            this.mem(var202, 37276);
                            if (super.F < 0) {
                              int var207 = this.IX() + 6;
                              int var208 = this.mem(var207, 37281);
                              int var209 = super.A - var208;
                              super.F = var209;
                              int var210 = this.IX() + 6;
                              this.mem(var210, 37281);
                              if (super.F != 0 && super.F >= 0) {
                                break label265;
                              }

                              int var211 = this.IX() + 6;
                              int var212 = this.mem(var211, 37288);
                              super.A = var212;
                              int var213 = this.IX() + 3;
                              this.wMem(var213, super.A, 37291);
                            }

                            int var203 = this.IX() + 4;
                            int var204 = this.mem(var203, 37294);
                            super.A = var204;
                            int var205 = -super.A & 255;
                            super.A = var205;
                            int var206 = this.IX() + 4;
                            this.wMem(var206, super.A, 37299);
                          }
                        }
                      } else {
                        int var148 = this.IX();
                        int var149 = this.mem(var148, 37171) & 128;
                        super.F = var149;
                        if (super.F == 0) {
                          int var167 = this.IX();
                          int var168 = this.mem(var167, 37177);
                          super.A = var168;
                          int var169 = super.A - 32 & 255;
                          super.A = var169;
                          super.F = super.A;
                          int var170 = super.A & 127;
                          super.A = var170;
                          super.F = super.A;
                          int var171 = this.IX();
                          this.wMem(var171, super.A, 37184);
                          int var172 = super.A - 96;
                          super.F = var172;
                          if (super.F >= 0) {
                            int var173 = this.IX() + 2;
                            int var174 = this.mem(var173, 37191);
                            super.A = var174;
                            int var175 = super.A & 31;
                            super.A = var175;
                            super.F = super.A;
                            int var176 = this.IX() + 6;
                            int var177 = this.mem(var176, 37196);
                            int var178 = super.A - var177;
                            super.F = var178;
                            int var179 = this.IX() + 6;
                            this.mem(var179, 37196);
                            if (super.F != 0) {
                              int var181 = this.IX() + 2;
                              int var182 = this.mem(var181, 37201) + -1;
                              this.wMem(var181, var182, 37201);
                              int var183 = var182 & 255;
                              this.wMem(var181, var183, 37201);
                              int var382 = this.IX() + 2;
                            } else {
                              int var180 = this.IX();
                              this.wMem(var180, 129, 37206);
                            }
                          }
                        } else {
                          int var150 = this.IX();
                          int var151 = this.mem(var150, 37212);
                          super.A = var151;
                          int var152 = super.A + 32 & 255;
                          super.A = var152;
                          super.F = super.A;
                          int var153 = super.A | 128;
                          super.A = var153;
                          super.F = super.A;
                          int var154 = this.IX();
                          this.wMem(var154, super.A, 37219);
                          int var155 = super.A - 160;
                          super.F = var155;
                          if (super.F < 0) {
                            int var156 = this.IX() + 2;
                            int var157 = this.mem(var156, 37226);
                            super.A = var157;
                            int var158 = super.A & 31;
                            super.A = var158;
                            super.F = super.A;
                            int var159 = this.IX() + 7;
                            int var160 = this.mem(var159, 37231);
                            int var161 = super.A - var160;
                            super.F = var161;
                            int var162 = this.IX() + 7;
                            this.mem(var162, 37231);
                            if (super.F != 0) {
                              int var164 = this.IX() + 2;
                              int var165 = this.mem(var164, 37236) + 1;
                              this.wMem(var164, var165, 37236);
                              int var166 = var165 & 255;
                              this.wMem(var164, var166, 37236);
                              int var383 = this.IX() + 2;
                            } else {
                              int var163 = this.IX();
                              this.wMem(var163, 97, 37241);
                            }
                          }
                        }
                      }
                    }

                    this.DE(8);
                    int var144 = this.IX();
                    int var145 = this.DE();
                    int var146 = var144 + var145 & '\uffff';
                    this.IX(var146);
                  }
                }

                int var251 = super.A ^ super.A;
                super.A = var251;
                super.F = super.A;
                this.wMem(34257, super.A, 36583);
                int var252 = this.mem(32973, 36586);
                super.A = var252;
                int var253 = this.HL();
                int var254 = this.mem(var253, 36589);
                int var255 = super.A - var254;
                super.F = var255;
                int var256 = this.HL();
                this.mem(var256, 36589);
                if (super.F != 0) {
                  int var259 = this.HL() + 1 & '\uffff';
                  this.HL(var259);
                  int var260 = this.HL();
                  int var261 = this.mem(var260, 36593);
                  int var262 = super.A - var261;
                  super.F = var262;
                  int var263 = this.HL();
                  this.mem(var263, 36593);
                  if (super.F != 0) {
                    break label263;
                  }
                }

                int var257 = this.mem(32982, 36596);
                super.A = var257;
                int var258 = super.A - 3 & 255;
                super.A = var258;
                super.F = super.A;
                super.E = super.A;
              }
            }

            this.BC(57342);
            int var7 = this.in(super.C);
            super.A = var7;
            int var8 = super.A & 31;
            super.A = var8;
            super.F = super.A;
            int var9 = super.A | 32;
            super.A = var9;
            super.F = super.A;
            int var10 = super.A & super.E;
            super.A = var10;
            super.F = super.A;
            super.E = super.A;
            int var11 = this.mem(34271, 36613);
            super.A = var11;
            int var12 = super.A & 2;
            super.A = var12;
            super.F = super.A;
            int var13 = super.A;
            int var14 = this.rrc(var13);
            super.A = var14;
            int var15 = super.A ^ super.E;
            super.A = var15;
            super.F = super.A;
            super.E = super.A;
            this.BC(64510);
            int var16 = this.in(super.C);
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
            int var22 = this.in(super.C);
            super.A = var22;
            int var23 = super.A;
            int var24 = this.rrc(var23);
            super.A = var24;
            int var25 = super.A | 247;
            super.A = var25;
            super.F = super.A;
            int var26 = super.A & super.E;
            super.A = var26;
            super.F = super.A;
            super.E = super.A;
            super.B = 239;
            int var27 = this.in(super.C);
            super.A = var27;
            int var28 = super.A | 251;
            super.A = var28;
            super.F = super.A;
            int var29 = super.A & super.E;
            super.A = var29;
            super.F = super.A;
            super.E = super.A;
            int var30 = this.in(super.C);
            super.A = var30;
            int var31 = super.A;
            int var32 = this.rrc(var31);
            super.A = var32;
            int var33 = super.A | 251;
            super.A = var33;
            super.F = super.A;
            int var34 = super.A & super.E;
            super.A = var34;
            super.F = super.A;
            super.E = super.A;
            int var35 = this.mem(34254, 36658);
            super.A = var35;
            int var36 = super.A | super.A;
            super.A = var36;
            super.F = super.A;
            if (true || super.F != 0) {
              this.BC(31);
              int var135 = this.in(super.C);
              super.A = var135;
              int var136 = super.A & 3;
              super.A = var136;
              super.F = super.A;
              A = ~super.A;
              super.F = super.A;
              int var137 = super.A & super.E;
              super.A = var137;
              super.F = super.A;
              super.E = super.A;
            }

            super.C = 0;
            super.A = super.E;
            int var37 = super.A & 42;
            super.A = var37;
            super.F = super.A;
            int var38 = super.A - 42;
            super.F = var38;
            if (super.F != 0) {
              super.C = 4;
              int var134 = super.A ^ super.A;
              super.A = var134;
              super.F = super.A;
              this.wMem(34272, super.A, 36686);
            }

            super.A = super.E;
            int var39 = super.A & 21;
            super.A = var39;
            super.F = super.A;
            int var40 = super.A - 21;
            super.F = var40;
            if (super.F != 0) {
              int var132 = super.C | 8;
              super.C = var132;
              int var133 = super.A ^ super.A;
              super.A = var133;
              super.F = super.A;
              this.wMem(34272, super.A, 36699);
            }

            int var41 = this.mem(34256, 36702);
            super.A = var41;
            int var42 = super.A + super.C & 255;
            super.A = var42;
            super.F = super.A;
            super.C = super.A;
            super.B = 0;
            this.HL(33825);
            int var43 = this.HL();
            int var44 = this.BC();
            int var45 = var43 + var44 & '\uffff';
            this.HL(var45);
            int var46 = this.HL();
            int var47 = this.mem(var46, 36713);
            super.A = var47;
            this.wMem(34256, super.A, 36714);
            this.BC(32510);
            int var48 = this.in(super.C);
            super.A = var48;
            int var49 = super.A & 31;
            super.A = var49;
            super.F = super.A;
            int var50 = super.A - 31;
            super.F = var50;
            if (super.F == 0) {
              super.B = 239;
              int var126 = this.in(super.C);
              super.A = var126;
              int var127 = super.A & 1;
              super.F = var127;
              if (super.F != 0) {
                int var128 = this.mem(34254, 36736);
                super.A = var128;
                int var129 = super.A | super.A;
                super.A = var129;
                super.F = super.A;
                if (false && super.F == 0) {
                  break label246;
                }

                this.BC(31);
                int var130 = this.in(super.C);
                super.A = var130;
                int var131 = super.A & 16;
                super.F = var131;
                if (super.F == 0) {
                  break label246;
                }
              }
            }

            int var51 = this.mem(34271, 36751);
            super.A = var51;
            int var52 = super.A & 2;
            super.F = var52;
            if (super.F == 0) {
              int var116 = super.A ^ super.A;
              super.A = var116;
              super.F = super.A;
              this.wMem(34261, super.A, 36759);
              this.wMem(34272, super.A, 36762);
              int var117 = super.A + 1 & 255;
              super.A = var117;
              super.F = super.A;
              this.wMem(34257, super.A, 36766);
              int var118 = this.mem(34262, 36769);
              super.A = var118;
              int var119 = super.A + -1 & 255;
              super.A = var119;
              super.F = super.A;
              int var120 = super.A & 128;
              super.F = var120;
              if (super.F == 0) {
                super.A = 240;
                this.wMem(34262, super.A, 36779);
                int var121 = this.mem(34255, 36782);
                super.A = var121;
                int var122 = super.A & 240;
                super.A = var122;
                super.F = super.A;
                this.wMem(34255, super.A, 36787);
                this.HL(34256);
                int var123 = this.HL();
                int var124 = this.mem(var123, 36793) | 2;
                int var125 = this.HL();
                this.wMem(var125, var124, 36793);
                return;
              }
            }
            break label246;
          }

          int var268 = this.mem(34257, 36450);
          super.A = var268;
          int var269 = super.A - 1;
          super.F = var269;
          if (super.F != 0) {
            this.HL(34256);
            int var270 = this.HL();
            int var271 = this.mem(var270, 36461) & -3;
            int var272 = this.HL();
            this.wMem(var272, var271, 36461);
            int var273 = this.mem(34257, 36463);
            super.A = var273;
            int var274 = super.A | super.A;
            super.A = var274;
            super.F = super.A;
            if (super.F == 0) {
              super.A = 2;
              this.wMem(34257, super.A, 36536);
              return;
            }

            int var275 = super.A + 1 & 255;
            super.A = var275;
            super.F = super.A;
            int var276 = super.A - 16;
            super.F = var276;
            if (super.F == 0) {
              super.A = 12;
            }

            this.wMem(34257, super.A, 36477);
            int var277 = super.A;
            int var278 = this.rlc(var277);
            super.A = var278;
            int var279 = super.A;
            int var280 = this.rlc(var279);
            super.A = var280;
            int var281 = super.A;
            int var282 = this.rlc(var281);
            super.A = var282;
            int var283 = super.A;
            int var284 = this.rlc(var283);
            super.A = var284;
            super.D = super.A;
            super.C = 32;
            int var285 = this.mem(32990, 36487);
            super.A = var285;

            do {
              int var286 = super.A ^ 24;
              super.A = var286;
              super.F = super.A;
              super.B = super.D;

              do {
                int var287 = super.B + -1 & 255;
                super.B = var287;
              } while (super.B != 0);

              int var288 = super.C + -1 & 255;
              super.C = var288;
              super.F = super.C;
            } while (super.F != 0);

            int var289 = this.mem(34255, 36500);
            super.A = var289;
            int var290 = super.A + 8 & 255;
            super.A = var290;
            super.F = super.A;
            this.wMem(34255, super.A, 36505);
            int var291 = super.A & 240;
            super.A = var291;
            super.F = super.A;
            super.L = super.A;
            int var292 = super.A ^ super.A;
            super.A = var292;
            super.F = super.A;
            int var293 = super.L;
            int var294 = this.rlc(var293);
            super.L = var294;
            int var295 = super.A + 92 & 255;
            super.A = var295;
            super.F = super.A;
            super.H = super.A;
            int var296 = this.mem(34259, 36517);
            super.A = var296;
            int var297 = super.A & 31;
            super.A = var297;
            super.F = super.A;
            int var298 = super.A | super.L;
            super.A = var298;
            super.F = super.A;
            super.L = super.A;
            int var299 = this.HL();
            this.wMem16(34259, var299, 36524);
            return;
          }
        }

        int var53 = this.mem(34256, 36796);
        super.A = var53;
        int var54 = super.A & 2;
        super.A = var54;
        super.F = super.A;
        if (super.F == 0) {
          return;
        }

        int var55 = this.mem(34262, 36802);
        super.A = var55;
        int var56 = super.A + -1 & 255;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A & 128;
        super.F = var57;
        if (super.F == 0) {
          return;
        }

        int var58 = this.mem(34256, 36809);
        super.A = var58;
        int var59 = super.A & 1;
        super.A = var59;
        super.F = super.A;
        if (super.F != 0) {
          int var63 = this.mem(34258, 36817);
          super.A = var63;
          int var64 = super.A | super.A;
          super.A = var64;
          super.F = super.A;
          if (super.F != 0) {
            int var115 = super.A + -1 & 255;
            super.A = var115;
            super.F = super.A;
            this.wMem(34258, super.A, 36824);
            return;
          }

          int var65 = this.mem(34257, 36828);
          super.A = var65;
          this.BC(0);
          int var66 = super.A - 0;
          super.F = var66;
          if (super.F == 0) {
            int var100 = this.mem16(34259, 36838);
            this.HL(var100);
            this.BC(0);
            int var101 = this.mem(32986, 36844);
            super.A = var101;
            int var102 = super.A + -1 & 255;
            super.A = var102;
            super.F = super.A;
            int var103 = super.A | 161;
            super.A = var103;
            super.F = super.A;
            int var104 = super.A ^ 224;
            super.A = var104;
            super.F = super.A;
            super.E = super.A;
            super.D = 0;
            int var105 = this.HL();
            int var106 = this.DE();
            int var107 = var105 + var106 & '\uffff';
            this.HL(var107);
            int var108 = this.mem(32964, 36856);
            super.A = var108;
            int var109 = this.HL();
            int var110 = this.mem(var109, 36859);
            int var111 = super.A - var110;
            super.F = var111;
            int var112 = this.HL();
            this.mem(var112, 36859);
            if (super.F == 0) {
              this.BC(32);
              int var113 = this.mem(32986, 36865);
              super.A = var113;
              int var114 = super.A | super.A;
              super.A = var114;
              super.F = super.A;
              if (super.F == 0) {
                this.BC(65504);
              }
            }
          }

          int var67 = this.mem16(34259, 36874);
          this.HL(var67);
          super.A = super.L;
          int var68 = super.A & 31;
          super.A = var68;
          super.F = super.A;
          int var69 = this.HL();
          int var70 = this.BC();
          int var71 = var69 + var70 & '\uffff';
          this.HL(var71);
          this.DE(32);
          int var72 = this.HL();
          int var73 = this.DE();
          int var74 = var72 + var73 & '\uffff';
          this.HL(var74);
          int var75 = this.mem(32946, 36889);
          super.A = var75;
          int var76 = this.HL();
          int var77 = this.mem(var76, 36892);
          int var78 = super.A - var77;
          super.F = var78;
          int var79 = this.HL();
          this.mem(var79, 36892);
          if (super.F == 0) {
            return;
          }

          int var80 = this.mem(34255, 36894);
          super.A = var80;
          C = C >> 1;
          int var81 = super.A + super.C & 255;
          super.A = var81;
          super.F = super.A;
          super.B = super.A;
          int var82 = super.A & 15;
          super.A = var82;
          super.F = super.A;
          if (super.F != 0) {
            int var88 = this.mem(32946, 36905);
            super.A = var88;
            int var89 = this.HL();
            int var90 = this.DE();
            int var91 = var89 + var90 & '\uffff';
            this.HL(var91);
            int var92 = this.HL();
            int var93 = this.mem(var92, 36909);
            int var94 = super.A - var93;
            super.F = var94;
            int var95 = this.HL();
            this.mem(var95, 36909);
            if (super.F == 0) {
              return;
            }

            int var96 = super.A | super.A;
            super.A = var96;
            carry = A != 0 ? 1 : 0;

            super.F = super.A;
            int var97 = this.HL();
            int var98 = this.DE();
            int var99 = (var97 - var98) - carry & '\uffff';
            this.HL(var99);
          }

          int var83 = super.A | super.A;
          super.A = var83;
          carry = A != 0 ? 1 : 0;

          super.F = super.A;
          int var84 = this.HL();
          int var85 = this.DE();
          int var86 = (var84 - var85) - carry & '\uffff';
          this.HL(var86);
          int var87 = this.HL();
          this.wMem16(34259, var87, 36917);
          super.A = super.B;
          this.wMem(34255, super.A, 36921);
          super.A = 3;
          this.wMem(34258, super.A, 36926);
          return;
        }

        int var60 = this.mem(34258, 36930);
        super.A = var60;
        int var61 = super.A - 3;
        super.F = var61;
        if (super.F != 0) {
          int var136 = super.A + 1 & 255;
          super.A = var136;
          super.F = super.A;
          this.wMem(34258, super.A, 36938);
          return;
        }

        int var62 = this.mem(34257, 36942);
        super.A = var62;
        this.BC(0);
        int var63 = super.A | super.A;
        super.A = var63;
        super.F = super.A;
        if (super.F == 0) {
          int var121 = this.mem16(34259, 36951);
          this.HL(var121);
          int var122 = this.mem(32986, 36954);
          super.A = var122;
          int var123 = super.A + -1 & 255;
          super.A = var123;
          super.F = super.A;
          int var124 = super.A | 157;
          super.A = var124;
          super.F = super.A;
          int var125 = super.A ^ 191;
          super.A = var125;
          super.F = super.A;
          super.E = super.A;
          super.D = 0;
          int var126 = this.HL();
          int var127 = this.DE();
          int var128 = var126 + var127 & '\uffff';
          this.HL(var128);
          int var129 = this.mem(32964, 36966);
          super.A = var129;
          int var130 = this.HL();
          int var131 = this.mem(var130, 36969);
          int var132 = super.A - var131;
          super.F = var132;
          int var133 = this.HL();
          this.mem(var133, 36969);
          if (super.F == 0) {
            this.BC(32);
            int var134 = this.mem(32986, 36975);
            super.A = var134;
            int var135 = super.A | super.A;
            super.A = var135;
            super.F = super.A;
            if (super.F != 0) {
              this.BC(65504);
            }
          }
        }

        int var64 = this.mem16(34259, 36984);
        this.HL(var64);
        int var65 = this.HL();
        int var66 = this.BC();
        int var67 = var65 + var66 & '\uffff';
        this.HL(var67);
        int var68 = this.HL() + 1 & '\uffff';
        this.HL(var68);
        int var69 = this.HL() + 1 & '\uffff';
        this.HL(var69);
        super.A = super.L;
        int var70 = super.A & 31;
        super.A = var70;
        super.F = super.A;
        if (super.F != 0) {
          this.DE(32);
          int var85 = this.mem(32946, 36999);
          super.A = var85;
          int var86 = this.HL();
          int var87 = this.DE();
          int var88 = var86 + var87 & '\uffff';
          this.HL(var88);
          int var89 = this.HL();
          int var90 = this.mem(var89, 37003);
          int var91 = super.A - var90;
          super.F = var91;
          int var92 = this.HL();
          this.mem(var92, 37003);
          if (super.F == 0) {
            return;
          }

          int var93 = this.mem(34255, 37005);
          super.A = var93;
          int var94 = super.C;
          int var95 = var94 >> 1;
          super.C = var95;
          int var96 = super.A + super.C & 255;
          super.A = var96;
          super.F = super.A;
          super.B = super.A;
          int var97 = super.A & 15;
          super.A = var97;
          super.F = super.A;
          if (super.F != 0) {
            int var109 = this.mem(32946, 37016);
            super.A = var109;
            int var110 = this.HL();
            int var111 = this.DE();
            int var112 = var110 + var111 & '\uffff';
            this.HL(var112);
            int var113 = this.HL();
            int var114 = this.mem(var113, 37020);
            int var115 = super.A - var114;
            super.F = var115;
            int var116 = this.HL();
            this.mem(var116, 37020);
            if (super.F == 0) {
              return;
            }

            int var117 = super.A | super.A;
            super.A = var117;
            carry = A != 0 ? 1 : 0;

            super.F = super.A;
            int var118 = this.HL();
            int var119 = this.DE();
            int var120 = (var118 - var119) - carry & '\uffff';
            this.HL(var120);
          }

          int var98 = this.mem(32946, 37025);
          super.A = var98;
          int var99 = super.A | super.A;
          super.A = var99;
          carry = A != 0 ? 1 : 0;

          super.F = super.A;
          int var100 = this.HL();
          int var101 = this.DE();
          int var102 = (var100 - var101) - carry & '\uffff';
          this.HL(var102);
          int var103 = this.HL();
          int var104 = this.mem(var103, 37031);
          int var105 = super.A - var104;
          super.F = var105;
          int var106 = this.HL();
          this.mem(var106, 37031);
          if (super.F == 0) {
            return;
          }

          int var107 = this.HL();
          this.wMem16(34259, var107, 37034);
          int var108 = super.A ^ super.A;
          super.A = var108;
          super.F = super.A;
          this.wMem(34258, super.A, 37038);
          super.A = super.B;
          this.wMem(34255, super.A, 37042);
          return;
        }
      }

      int var305 = this.mem(33004, 38098);
      super.A = var305;
      this.wMem(33824, super.A, 38101);
      int var306 = super.A ^ super.A;
      super.A = var306;
      super.F = super.A;
      this.wMem(34255, super.A, 38105);
      int var307 = this.mem(34257, 38108);
      super.A = var307;
      int var308 = super.A - 11;
      super.F = var308;
      if (super.F < 0) {
        super.A = 2;
        this.wMem(34257, super.A, 38117);
      }

      int var309 = this.mem(34259, 38120);
      super.A = var309;
      int var310 = super.A & 31;
      super.A = var310;
      super.F = super.A;
      this.wMem(34259, super.A, 38125);
      super.A = 92;
      this.wMem(34260, super.A, 38130);
      return;
    }

    int var352 = this.mem(34255, 36540);
    super.A = var352;
    int var353 = super.A + 16 & 255;
    super.A = var353;
    super.F = super.A;
    int var354 = super.A & 240;
    super.A = var354;
    super.F = super.A;
    this.wMem(34255, super.A, 36547);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A, 36555);
    this.HL(34256);
    int var355 = this.HL();
    int var356 = this.mem(var355, 36561) & -3;
    int var357 = this.HL();
    this.wMem(var357, var356, 36561);
  }

  public void $36307B() {
    label250:
    {
      label251:
      {
        label246:
        {
          label266:
          {
            int var1 = this.mem(34262, 36307);
            super.A = var1;
            int var2 = super.A + -1 & 255;
            super.A = var2;
            super.F = super.A;
            int var3 = super.A & 128;
            super.F = var3;
            if (super.F != 0) {
              int var264 = this.mem(34257, 36316);
              super.A = var264;
              int var265 = super.A - 1;
              super.F = var265;
              if (super.F == 0) {
                int var331 = this.mem(34261, 36323);
                super.A = var331;
                int var332 = super.A & 254;
                super.A = var332;
                super.F = super.A;
                int var333 = super.A - 8 & 255;
                super.A = var333;
                super.F = super.A;
                this.HL(34255);
                int var334 = this.HL();
                int var335 = this.mem(var334, 36333);
                int var336 = super.A + var335 & 255;
                super.A = var336;
                int var337 = this.HL();
                this.mem(var337, 36333);
                int var338 = this.HL();
                this.mem(var338, 36333);
                super.F = super.A;
                int var339 = this.HL();
                this.mem(var339, 36333);
                int var340 = this.HL();
                this.wMem(var340, super.A, 36334);
                int var341 = super.A - 240;
                super.F = var341;
                if (super.F >= 0) {
                  int var342 = this.mem(33003, 38064);
                  super.A = var342;
                  this.wMem(33824, super.A, 38067);
                  int var343 = this.mem(34259, 38070);
                  super.A = var343;
                  int var344 = super.A & 31;
                  super.A = var344;
                  super.F = super.A;
                  int var345 = super.A + 160 & 255;
                  super.A = var345;
                  super.F = super.A;
                  this.wMem(34259, super.A, 38077);
                  super.A = 93;
                  this.wMem(34260, super.A, 38082);
                  super.A = 208;
                  this.wMem(34255, super.A, 38087);
                  int var346 = super.A ^ super.A;
                  super.A = var346;
                  super.F = super.A;
                  this.wMem(34257, super.A, 38091);
                  break label251;
                }

                this.$36508();
                int var347 = this.mem(32946, 36343);
                super.A = var347;
                int var348 = this.HL();
                int var349 = this.mem(var348, 36346);
                int var350 = super.A - var349;
                super.F = var350;
                int var351 = this.HL();
                this.mem(var351, 36346);
                if (super.F == 0) {
                  break label250;
                }

                int var358 = this.HL() + 1 & '\uffff';
                this.HL(var358);
                int var359 = this.HL();
                int var360 = this.mem(var359, 36351);
                int var361 = super.A - var360;
                super.F = var361;
                int var362 = this.HL();
                this.mem(var362, 36351);
                if (super.F == 0) {
                  break label250;
                }

                int var363 = this.mem(34261, 36355);
                super.A = var363;
                int var364 = super.A + 1 & 255;
                super.A = var364;
                super.F = super.A;
                this.wMem(34261, super.A, 36359);
                int var365 = super.A - 8 & 255;
                super.A = var365;
                super.F = super.A;
                int var366 = -super.A & 255;
                super.A = var366;
                int var367 = super.A + 1 & 255;
                super.A = var367;
                super.F = super.A;
                int var368 = super.A;
                int var369 = this.rlc(var368);
                super.A = var369;
                int var370 = super.A;
                int var371 = this.rlc(var370);
                super.A = var371;
                int var372 = super.A;
                int var373 = this.rlc(var372);
                super.A = var373;
                super.D = super.A;
                super.C = 32;
                int var374 = this.mem(32990, 36376);
                super.A = var374;

                do {
                  int var375 = super.A ^ 24;
                  super.A = var375;
                  super.F = super.A;
                  super.B = super.D;

                  do {
                    int var376 = super.B + -1 & 0xff;
                    super.B = var376;
                  } while (super.B != 0);

                  int var377 = super.C + -1 & 255;
                  super.C = var377;
                  super.F = super.C;
                } while (super.F != 0);

                int var378 = this.mem(34261, 36389);
                super.A = var378;
                int var379 = super.A - 18;
                super.F = var379;
                if (super.F == 0) {
                  super.A = 6;
                  this.wMem(34257, super.A, 36530);
                  return;
                }

                int var380 = super.A - 16;
                super.F = var380;
                if (super.F != 0) {
                  int var381 = super.A - 13;
                  super.F = var381;
                  if (super.F != 0) {
                    break label246;
                  }
                }
              }

              int var266 = this.mem(34255, 36406);
              super.A = var266;
              int var267 = super.A & 14;
              super.A = var267;
              super.F = super.A;
              if (super.F != 0) {
                break label266;
              }

              int var300 = this.mem16(34259, 36413);
              this.HL(var300);
              this.DE(64);
              int var301 = this.HL();
              int var302 = this.DE();
              int var303 = var301 + var302 & '\uffff';
              this.HL(var303);
              int var304 = super.H & 2;
              super.F = var304;
              if (super.F != 0) {
                break label251;
              }

              int var311 = this.mem(32955, 36425);
              super.A = var311;
              int var312 = this.HL();
              int var313 = this.mem(var312, 36428);
              int var314 = super.A - var313;
              super.F = var314;
              int var315 = this.HL();
              this.mem(var315, 36428);
              if (super.F == 0) {
                break label266;
              }

              int var316 = this.HL() + 1 & '\uffff';
              this.HL(var316);
              int var317 = this.mem(32955, 36432);
              super.A = var317;
              int var318 = this.HL();
              int var319 = this.mem(var318, 36435);
              int var320 = super.A - var319;
              super.F = var320;
              int var321 = this.HL();
              this.mem(var321, 36435);
              if (super.F == 0) {
                break label266;
              }

              int var322 = this.mem(32928, 36438);
              super.A = var322;
              int var323 = this.HL();
              int var324 = this.mem(var323, 36441);
              int var325 = super.A - var324;
              super.F = var325;
              int var326 = this.HL();
              this.mem(var326, 36441);
              if (super.F == 0) {
                int var327 = this.HL();
                int var328 = this.mem(var327, 36446);
                int var329 = super.A - var328;
                super.F = var329;
                int var330 = this.HL();
                this.mem(var330, 36446);
                if (super.F == 0) {
                  break label266;
                }
              }
            }

            super.E = 255;
            int var4 = this.mem(34262, 36566);
            super.A = var4;
            int var5 = super.A + -1 & 255;
            super.A = var5;
            super.F = super.A;
            int var6 = super.A & 128;
            super.F = var6;
            if (super.F != 0) {
              label263:
              {
                int var138 = this.mem(34257, 36574);
                super.A = var138;
                int var139 = super.A - 12;
                super.F = var139;
                if (super.F >= 0) {
                  super.A = 255;
                  this.wMem(34257, super.A, 37050);
                  this.IX(33024);

                  while (true) {
                    int var140 = this.IX();
                    int var141 = this.mem(var140, 37060);
                    super.A = var141;
                    int var142 = super.A - 255;
                    super.F = var142;
                    if (super.F == 0) {
                      return;
                    }

                    int var143 = super.A & 3;
                    super.A = var143;
                    super.F = super.A;
                    if (super.F != 0) {
                      int var147 = super.A - 1;
                      super.F = var147;
                      if (super.F != 0) {
                        int var184 = super.A - 2;
                        super.F = var184;
                        if (super.F != 0) {
                          int var218 = this.IX();
                          int var219 = this.mem(var218, 37081) & 128;
                          super.F = var219;
                          if (super.F != 0) {
                            int var240 = this.IX() + 1;
                            int var241 = this.mem(var240, 37087);
                            super.A = var241;
                            int var242 = super.A & 128;
                            super.F = var242;
                            if (super.F != 0) {
                              int var246 = super.A - 2 & 255;
                              super.A = var246;
                              super.F = super.A;
                              int var247 = super.A - 148;
                              super.F = var247;
                              if (super.F < 0) {
                                int var248 = super.A - 2 & 255;
                                super.A = var248;
                                super.F = super.A;
                                int var249 = super.A - 128;
                                super.F = var249;
                                if (super.F == 0) {
                                  int var250 = super.A ^ super.A;
                                  super.A = var250;
                                  super.F = super.A;
                                }
                              }
                            } else {
                              int var243 = super.A + 2 & 255;
                              super.A = var243;
                              super.F = super.A;
                              int var244 = super.A - 18;
                              super.F = var244;
                              if (super.F < 0) {
                                int var245 = super.A + 2 & 255;
                                super.A = var245;
                                super.F = super.A;
                              }
                            }
                          } else {
                            int var220 = this.IX() + 1;
                            int var221 = this.mem(var220, 37119);
                            super.A = var221;
                            int var222 = super.A & 128;
                            super.F = var222;
                            if (super.F == 0) {
                              int var236 = super.A - 2 & 255;
                              super.A = var236;
                              super.F = super.A;
                              int var237 = super.A - 20;
                              super.F = var237;
                              if (super.F < 0) {
                                int var238 = super.A - 2 & 255;
                                super.A = var238;
                                super.F = super.A;
                                int var239 = super.A | super.A;
                                super.A = var239;
                                super.F = super.A;
                                if (super.F == 0) {
                                  super.A = 128;
                                }
                              }
                            } else {
                              int var223 = super.A + 2 & 255;
                              super.A = var223;
                              super.F = super.A;
                              int var224 = super.A - 146;
                              super.F = var224;
                              if (super.F < 0) {
                                int var235 = super.A + 2 & 255;
                                super.A = var235;
                                super.F = super.A;
                              }
                            }
                          }

                          int var225 = this.IX() + 1;
                          this.wMem(var225, super.A, 37149);
                          int var226 = super.A & 127;
                          super.A = var226;
                          super.F = super.A;
                          int var227 = this.IX() + 7;
                          int var228 = this.mem(var227, 37154);
                          int var229 = super.A - var228;
                          super.F = var229;
                          int var230 = this.IX() + 7;
                          this.mem(var230, 37154);
                          if (super.F == 0) {
                            int var231 = this.IX();
                            int var232 = this.mem(var231, 37160);
                            super.A = var232;
                            int var233 = super.A ^ 128;
                            super.A = var233;
                            super.F = super.A;
                            int var234 = this.IX();
                            this.wMem(var234, super.A, 37165);
                          }
                        } else {
                          label265:
                          {
                            int var185 = this.IX();
                            int var186 = this.mem(var185, 37247);
                            super.A = var186;
                            int var187 = super.A ^ 8;
                            super.A = var187;
                            super.F = super.A;
                            int var188 = this.IX();
                            this.wMem(var188, super.A, 37252);
                            int var189 = super.A & 24;
                            super.A = var189;
                            super.F = super.A;
                            if (super.F != 0) {
                              int var214 = this.IX();
                              int var215 = this.mem(var214, 37259);
                              super.A = var215;
                              int var216 = super.A + 32 & 255;
                              super.A = var216;
                              super.F = super.A;
                              int var217 = this.IX();
                              this.wMem(var217, super.A, 37264);
                            }

                            int var190 = this.IX() + 3;
                            int var191 = this.mem(var190, 37267);
                            super.A = var191;
                            int var192 = this.IX() + 4;
                            int var193 = this.mem(var192, 37270);
                            int var194 = super.A + var193 & 255;
                            super.A = var194;
                            int var195 = this.IX() + 4;
                            this.mem(var195, 37270);
                            int var196 = this.IX() + 4;
                            this.mem(var196, 37270);
                            super.F = super.A;
                            int var197 = this.IX() + 4;
                            this.mem(var197, 37270);
                            int var198 = this.IX() + 3;
                            this.wMem(var198, super.A, 37273);
                            int var199 = this.IX() + 7;
                            int var200 = this.mem(var199, 37276);
                            int var201 = super.A - var200;
                            super.F = var201;
                            int var202 = this.IX() + 7;
                            this.mem(var202, 37276);
                            if (super.F < 0) {
                              int var207 = this.IX() + 6;
                              int var208 = this.mem(var207, 37281);
                              int var209 = super.A - var208;
                              super.F = var209;
                              int var210 = this.IX() + 6;
                              this.mem(var210, 37281);
                              if (super.F != 0 && super.F >= 0) {
                                break label265;
                              }

                              int var211 = this.IX() + 6;
                              int var212 = this.mem(var211, 37288);
                              super.A = var212;
                              int var213 = this.IX() + 3;
                              this.wMem(var213, super.A, 37291);
                            }

                            int var203 = this.IX() + 4;
                            int var204 = this.mem(var203, 37294);
                            super.A = var204;
                            int var205 = -super.A & 255;
                            super.A = var205;
                            int var206 = this.IX() + 4;
                            this.wMem(var206, super.A, 37299);
                          }
                        }
                      } else {
                        int var148 = this.IX();
                        int var149 = this.mem(var148, 37171) & 128;
                        super.F = var149;
                        if (super.F == 0) {
                          int var167 = this.IX();
                          int var168 = this.mem(var167, 37177);
                          super.A = var168;
                          int var169 = super.A - 32 & 255;
                          super.A = var169;
                          super.F = super.A;
                          int var170 = super.A & 127;
                          super.A = var170;
                          super.F = super.A;
                          int var171 = this.IX();
                          this.wMem(var171, super.A, 37184);
                          int var172 = super.A - 96;
                          super.F = var172;
                          if (super.F >= 0) {
                            int var173 = this.IX() + 2;
                            int var174 = this.mem(var173, 37191);
                            super.A = var174;
                            int var175 = super.A & 31;
                            super.A = var175;
                            super.F = super.A;
                            int var176 = this.IX() + 6;
                            int var177 = this.mem(var176, 37196);
                            int var178 = super.A - var177;
                            super.F = var178;
                            int var179 = this.IX() + 6;
                            this.mem(var179, 37196);
                            if (super.F != 0) {
                              int var181 = this.IX() + 2;
                              int var182 = this.mem(var181, 37201) + -1;
                              this.wMem(var181, var182, 37201);
                              int var183 = var182 & 255;
                              this.wMem(var181, var183, 37201);
                              int var10000 = this.IX() + 2;
                            } else {
                              int var180 = this.IX();
                              this.wMem(var180, 129, 37206);
                            }
                          }
                        } else {
                          int var150 = this.IX();
                          int var151 = this.mem(var150, 37212);
                          super.A = var151;
                          int var152 = super.A + 32 & 255;
                          super.A = var152;
                          super.F = super.A;
                          int var153 = super.A | 128;
                          super.A = var153;
                          super.F = super.A;
                          int var154 = this.IX();
                          this.wMem(var154, super.A, 37219);
                          int var155 = super.A - 160;
                          super.F = var155;
                          if (super.F < 0) {
                            int var156 = this.IX() + 2;
                            int var157 = this.mem(var156, 37226);
                            super.A = var157;
                            int var158 = super.A & 31;
                            super.A = var158;
                            super.F = super.A;
                            int var159 = this.IX() + 7;
                            int var160 = this.mem(var159, 37231);
                            int var161 = super.A - var160;
                            super.F = var161;
                            int var162 = this.IX() + 7;
                            this.mem(var162, 37231);
                            if (super.F != 0) {
                              int var164 = this.IX() + 2;
                              int var165 = this.mem(var164, 37236) + 1;
                              this.wMem(var164, var165, 37236);
                              int var166 = var165 & 255;
                              this.wMem(var164, var166, 37236);
                              int var382 = this.IX() + 2;
                            } else {
                              int var163 = this.IX();
                              this.wMem(var163, 97, 37241);
                            }
                          }
                        }
                      }
                    }

                    this.DE(8);
                    int var144 = this.IX();
                    int var145 = this.DE();
                    int var146 = var144 + var145 & '\uffff';
                    this.IX(var146);
                  }
                }

                int var251 = super.A ^ super.A;
                super.A = var251;
                super.F = super.A;
                this.wMem(34257, super.A, 36583);
                int var252 = this.mem(32973, 36586);
                super.A = var252;
                int var253 = this.HL();
                int var254 = this.mem(var253, 36589);
                int var255 = super.A - var254;
                super.F = var255;
                int var256 = this.HL();
                this.mem(var256, 36589);
                if (super.F != 0) {
                  int var259 = this.HL() + 1 & '\uffff';
                  this.HL(var259);
                  int var260 = this.HL();
                  int var261 = this.mem(var260, 36593);
                  int var262 = super.A - var261;
                  super.F = var262;
                  int var263 = this.HL();
                  this.mem(var263, 36593);
                  if (super.F != 0) {
                    break label263;
                  }
                }

                int var257 = this.mem(32982, 36596);
                super.A = var257;
                int var258 = super.A - 3 & 255;
                super.A = var258;
                super.F = super.A;
                super.E = super.A;
              }
            }

            this.BC(57342);
            int var7 = this.in(super.C);
            super.A = var7;
            int var8 = super.A & 31;
            super.A = var8;
            super.F = super.A;
            int var9 = super.A | 32;
            super.A = var9;
            super.F = super.A;
            int var10 = super.A & super.E;
            super.A = var10;
            super.F = super.A;
            super.E = super.A;
            int var11 = this.mem(34271, 36613);
            super.A = var11;
            int var12 = super.A & 2;
            super.A = var12;
            super.F = super.A;
            int var13 = super.A;
            int var14 = this.rrc(var13);
            super.A = var14;
            int var15 = super.A ^ super.E;
            super.A = var15;
            super.F = super.A;
            super.E = super.A;
            this.BC(64510);
            int var16 = this.in(super.C);
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
            int var22 = this.in(super.C);
            super.A = var22;
            int var23 = super.A;
            int var24 = this.rrc(var23);
            super.A = var24;
            int var25 = super.A | 247;
            super.A = var25;
            super.F = super.A;
            int var26 = super.A & super.E;
            super.A = var26;
            super.F = super.A;
            super.E = super.A;
            super.B = 239;
            int var27 = this.in(super.C);
            super.A = var27;
            int var28 = super.A | 251;
            super.A = var28;
            super.F = super.A;
            int var29 = super.A & super.E;
            super.A = var29;
            super.F = super.A;
            super.E = super.A;
            int var30 = this.in(super.C);
            super.A = var30;
            int var31 = super.A;
            int var32 = this.rrc(var31);
            super.A = var32;
            int var33 = super.A | 251;
            super.A = var33;
            super.F = super.A;
            int var34 = super.A & super.E;
            super.A = var34;
            super.F = super.A;
            super.E = super.A;
            int var35 = this.mem(34254, 36658);
            super.A = var35;
            int var36 = super.A | super.A;
            super.A = var36;
            super.F = super.A;
            if (true || super.F != 0) {
              this.BC(31);
              int var135 = this.in(super.C);
              super.A = var135;
              int var136 = super.A & 3;
              super.A = (~var136) & 0xff;
              super.F = super.A;
              int var137 = super.A & super.E;
              super.A = var137;
              super.F = super.A;
              super.E = super.A;
            }

            super.C = 0;
            super.A = super.E;
            int var37 = super.A & 42;
            super.A = var37;
            super.F = super.A;
            int var38 = super.A - 42;
            super.F = var38;
            if (super.F != 0) {
              super.C = 4;
              int var134 = super.A ^ super.A;
              super.A = var134;
              super.F = super.A;
              this.wMem(34272, super.A, 36686);
            }

            super.A = super.E;
            int var39 = super.A & 21;
            super.A = var39;
            super.F = super.A;
            int var40 = super.A - 21;
            super.F = var40;
            if (super.F != 0) {
              int var132 = super.C | 8;
              super.C = var132;
              int var133 = super.A ^ super.A;
              super.A = var133;
              super.F = super.A;
              this.wMem(34272, super.A, 36699);
            }

            int var41 = this.mem(34256, 36702);
            super.A = var41;
            int var42 = super.A + super.C & 255;
            super.A = var42;
            super.F = super.A;
            super.C = super.A;
            super.B = 0;
            this.HL(33825);
            int var43 = this.HL();
            int var44 = this.BC();
            int var45 = var43 + var44 & '\uffff';
            this.HL(var45);
            int var46 = this.HL();
            int var47 = this.mem(var46, 36713);
            super.A = var47;
            this.wMem(34256, super.A, 36714);
            this.BC(32510);
            int var48 = this.in(super.C);
            super.A = var48;
            int var49 = super.A & 31;
            super.A = var49;
            super.F = super.A;
            int var50 = super.A - 31;
            super.F = var50;
            if (super.F == 0) {
              super.B = 239;
              int var126 = this.in(super.C);
              super.A = var126;
              int var127 = super.A & 1;
              super.F = var127;
              if (super.F != 0) {
                int var128 = this.mem(34254, 36736);
                super.A = var128;
                int var129 = super.A | super.A;
                super.A = var129;
                super.F = super.A;
                if (super.F != 0) {
                  break label246;
                }

                this.BC(31);
                int var130 = this.in(super.C);
                super.A = var130;
                int var131 = super.A & 16;
                super.F = var131;
                if (super.F == 0) {
                  break label246;
                }
              }
            }

            int var51 = this.mem(34271, 36751);
            super.A = var51;
            int var52 = super.A & 2;
            super.F = var52;
            if (super.F == 0) {
              int var116 = super.A ^ super.A;
              super.A = var116;
              super.F = super.A;
              this.wMem(34261, super.A, 36759);
              this.wMem(34272, super.A, 36762);
              int var117 = super.A + 1 & 255;
              super.A = var117;
              super.F = super.A;
              this.wMem(34257, super.A, 36766);
              int var118 = this.mem(34262, 36769);
              super.A = var118;
              int var119 = super.A + -1 & 255;
              super.A = var119;
              super.F = super.A;
              int var120 = super.A & 128;
              super.F = var120;
              if (super.F == 0) {
                super.A = 240;
                this.wMem(34262, super.A, 36779);
                int var121 = this.mem(34255, 36782);
                super.A = var121;
                int var122 = super.A & 240;
                super.A = var122;
                super.F = super.A;
                this.wMem(34255, super.A, 36787);
                this.HL(34256);
                int var123 = this.HL();
                int var124 = this.mem(var123, 36793) | 2;
                int var125 = this.HL();
                this.wMem(var125, var124, 36793);
                return;
              }
            }
            break label246;
          }

          int var268 = this.mem(34257, 36450);
          super.A = var268;
          int var269 = super.A - 1;
          super.F = var269;
          if (super.F != 0) {
            this.HL(34256);
            int var270 = this.HL();
            int var271 = this.mem(var270, 36461) & -3;
            int var272 = this.HL();
            this.wMem(var272, var271, 36461);
            int var273 = this.mem(34257, 36463);
            super.A = var273;
            int var274 = super.A | super.A;
            super.A = var274;
            super.F = super.A;
            if (super.F == 0) {
              super.A = 2;
              this.wMem(34257, super.A, 36536);
              return;
            }

            int var275 = super.A + 1 & 255;
            super.A = var275;
            super.F = super.A;
            int var276 = super.A - 16;
            super.F = var276;
            if (super.F == 0) {
              super.A = 12;
            }

            this.wMem(34257, super.A, 36477);
            int var277 = super.A;
            int var278 = this.rlc(var277);
            super.A = var278;
            int var279 = super.A;
            int var280 = this.rlc(var279);
            super.A = var280;
            int var281 = super.A;
            int var282 = this.rlc(var281);
            super.A = var282;
            int var283 = super.A;
            int var284 = this.rlc(var283);
            super.A = var284;
            super.D = super.A;
            super.C = 32;
            int var285 = this.mem(32990, 36487);
            super.A = var285;

            do {
              int var286 = super.A ^ 24;
              super.A = var286;
              super.F = super.A;
              super.B = super.D;

              do {
                int var287 = super.B + -1 & 0xff;
                super.B = var287;
              } while (super.B != 0);

              int var288 = super.C + -1 & 255;
              super.C = var288;
              super.F = super.C;
            } while (super.F != 0);

            int var289 = this.mem(34255, 36500);
            super.A = var289;
            int var290 = super.A + 8 & 255;
            super.A = var290;
            super.F = super.A;
            this.wMem(34255, super.A, 36505);
            int var291 = super.A & 240;
            super.A = var291;
            super.F = super.A;
            super.L = super.A;
            int var292 = super.A ^ super.A;
            super.A = var292;
            super.F = super.A;
            int var293 = super.L;
            int var294 = this.rlc(var293);
            super.L = var294;
            int var295 = super.A + 92 & 255;
            super.A = var295;
            super.F = super.A;
            super.H = super.A;
            int var296 = this.mem(34259, 36517);
            super.A = var296;
            int var297 = super.A & 31;
            super.A = var297;
            super.F = super.A;
            int var298 = super.A | super.L;
            super.A = var298;
            super.F = super.A;
            super.L = super.A;
            int var299 = this.HL();
            this.wMem16(34259, var299, 36524);
            return;
          }
        }

        int var53 = this.mem(34256, 36796);
        super.A = var53;
        int var54 = super.A & 2;
        super.A = var54;
        super.F = super.A;
        if (super.F == 0) {
          return;
        }

        int var55 = this.mem(34262, 36802);
        super.A = var55;
        int var56 = super.A + -1 & 255;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A & 128;
        super.F = var57;
        if (super.F == 0) {
          return;
        }

        int var58 = this.mem(34256, 36809);
        super.A = var58;
        int var59 = super.A & 1;
        super.A = var59;
        super.F = super.A;
        if (super.F != 0) {
          int var63 = this.mem(34258, 36817);
          super.A = var63;
          int var64 = super.A | super.A;
          super.A = var64;
          super.F = super.A;
          if (super.F != 0) {
            int var115 = super.A + -1 & 255;
            super.A = var115;
            super.F = super.A;
            this.wMem(34258, super.A, 36824);
            return;
          }

          int var65 = this.mem(34257, 36828);
          super.A = var65;
          this.BC(0);
          int var66 = super.A - 0;
          super.F = var66;
          if (super.F == 0) {
            int var100 = this.mem16(34259, 36838);
            this.HL(var100);
            this.BC(0);
            int var101 = this.mem(32986, 36844);
            super.A = var101;
            int var102 = super.A + -1 & 255;
            super.A = var102;
            super.F = super.A;
            int var103 = super.A | 161;
            super.A = var103;
            super.F = super.A;
            int var104 = super.A ^ 224;
            super.A = var104;
            super.F = super.A;
            super.E = super.A;
            super.D = 0;
            int var105 = this.HL();
            int var106 = this.DE();
            int var107 = var105 + var106 & '\uffff';
            this.HL(var107);
            int var108 = this.mem(32964, 36856);
            super.A = var108;
            int var109 = this.HL();
            int var110 = this.mem(var109, 36859);
            int var111 = super.A - var110;
            super.F = var111;
            int var112 = this.HL();
            this.mem(var112, 36859);
            if (super.F == 0) {
              this.BC(32);
              int var113 = this.mem(32986, 36865);
              super.A = var113;
              int var114 = super.A | super.A;
              super.A = var114;
              super.F = super.A;
              if (super.F == 0) {
                this.BC(65504);
              }
            }
          }

          int var67 = this.mem16(34259, 36874);
          this.HL(var67);
          super.A = super.L;
          int var68 = super.A & 31;
          super.A = var68;
          super.F = super.A;
          int var69 = this.HL();
          int var70 = this.BC();
          int var71 = var69 + var70 & '\uffff';
          this.HL(var71);
          this.DE(32);
          int var72 = this.HL();
          int var73 = this.DE();
          int var74 = var72 + var73 & '\uffff';
          this.HL(var74);
          int var75 = this.mem(32946, 36889);
          super.A = var75;
          int var76 = this.HL();
          int var77 = this.mem(var76, 36892);
          int var78 = super.A - var77;
          super.F = var78;
          int var79 = this.HL();
          this.mem(var79, 36892);
          if (super.F == 0) {
            return;
          }

          int var80 = this.mem(34255, 36894);
          super.A = var80;
          int var81 = super.A + super.C & 255;
          super.A = var81;
          super.F = super.A;
          super.B = super.A;
          int var82 = super.A & 15;
          super.A = var82;
          super.F = super.A;
          if (super.F != 0) {
            int var88 = this.mem(32946, 36905);
            super.A = var88;
            int var89 = this.HL();
            int var90 = this.DE();
            int var91 = var89 + var90 & '\uffff';
            this.HL(var91);
            int var92 = this.HL();
            int var93 = this.mem(var92, 36909);
            int var94 = super.A - var93;
            super.F = var94;
            int var95 = this.HL();
            this.mem(var95, 36909);
            if (super.F == 0) {
              return;
            }

            int var96 = super.A | super.A;
            super.A = var96;
            super.F = super.A;
            int var97 = this.HL();
            int var98 = this.DE();
            int var99 = var97 - var98 & '\uffff';
            this.HL(var99);
          }

          int var83 = super.A | super.A;
          super.A = var83;
          super.F = super.A;
          int var84 = this.HL();
          int var85 = this.DE();
          int var86 = var84 - var85 & '\uffff';
          this.HL(var86);
          int var87 = this.HL();
          this.wMem16(34259, var87, 36917);
          super.A = super.B;
          this.wMem(34255, super.A, 36921);
          super.A = 3;
          this.wMem(34258, super.A, 36926);
          return;
        }

        int var60 = this.mem(34258, 36930);
        super.A = var60;
        int var61 = super.A - 3;
        super.F = var61;
        int var62 = super.A + 1 & 255;
        super.A = var62;
        super.F = super.A;
        this.wMem(34258, super.A, 36938);
        return;
      }

      int var305 = this.mem(33004, 38098);
      super.A = var305;
      this.wMem(33824, super.A, 38101);
      int var306 = super.A ^ super.A;
      super.A = var306;
      super.F = super.A;
      this.wMem(34255, super.A, 38105);
      int var307 = this.mem(34257, 38108);
      super.A = var307;
      int var308 = super.A - 11;
      super.F = var308;
      if (super.F < 0) {
        super.A = 2;
        this.wMem(34257, super.A, 38117);
      }

      int var309 = this.mem(34259, 38120);
      super.A = var309;
      int var310 = super.A & 31;
      super.A = var310;
      super.F = super.A;
      this.wMem(34259, super.A, 38125);
      super.A = 92;
      this.wMem(34260, super.A, 38130);
      return;
    }

    int var352 = this.mem(34255, 36540);
    super.A = var352;
    int var353 = super.A + 16 & 255;
    super.A = var353;
    super.F = super.A;
    int var354 = super.A & 240;
    super.A = var354;
    super.F = super.A;
    this.wMem(34255, super.A, 36547);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A, 36555);
    this.HL(34256);
    int var355 = this.HL();
    int var356 = this.mem(var355, 36561) & -3;
    int var357 = this.HL();
    this.wMem(var357, var356, 36561);
  }


  public void $36307A() {
    label222:
    {
      label223:
      {
        label218:
        {
          label237:
          {
            int var1 = this.mem(34262, 36307);
            super.A = var1;
            int var2 = super.A + -1 & 255;
            super.A = var2;
            super.F = super.A;
            int var3 = super.A & 128;
            super.F = var3;
            if (super.F != 0) {
              int var211 = this.mem(34257, 36316);
              super.A = var211;
              int var212 = super.A - 1;
              super.F = var212;
              if (super.F == 0) {
                int var278 = this.mem(34261, 36323);
                super.A = var278;
                int var279 = super.A & 254;
                super.A = var279;
                super.F = super.A;
                int var280 = super.A - 8 & 255;
                super.A = var280;
                super.F = super.A;
                this.HL(34255);
                int var281 = this.HL();
                int var282 = this.mem(var281, 36333);
                int var283 = super.A + var282 & 255;
                super.A = var283;
                int var284 = this.HL();
                this.mem(var284, 36333);
                int var285 = this.HL();
                this.mem(var285, 36333);
                super.F = super.A;
                int var286 = this.HL();
                this.mem(var286, 36333);
                int var287 = this.HL();
                this.wMem(var287, super.A, 36334);
                int var288 = super.A - 240;
                super.F = var288;
                if (super.F >= 0) {
                  int var289 = this.mem(33003, 38064);
                  super.A = var289;
                  this.wMem(33824, super.A, 38067);
                  int var290 = this.mem(34259, 38070);
                  super.A = var290;
                  int var291 = super.A & 31;
                  super.A = var291;
                  super.F = super.A;
                  int var292 = super.A + 160 & 255;
                  super.A = var292;
                  super.F = super.A;
                  this.wMem(34259, super.A, 38077);
                  super.A = 93;
                  this.wMem(34260, super.A, 38082);
                  super.A = 208;
                  this.wMem(34255, super.A, 38087);
                  int var293 = super.A ^ super.A;
                  super.A = var293;
                  super.F = super.A;
                  this.wMem(34257, super.A, 38091);
                  break label223;
                }

                this.$36508();
                int var294 = this.mem(32946, 36343);
                super.A = var294;
                int var295 = this.HL();
                int var296 = this.mem(var295, 36346);
                int var297 = super.A - var296;
                super.F = var297;
                int var298 = this.HL();
                this.mem(var298, 36346);
                if (super.F == 0) {
                  break label222;
                }

                int var305 = this.HL() + 1 & '\uffff';
                this.HL(var305);
                int var306 = this.HL();
                int var307 = this.mem(var306, 36351);
                int var308 = super.A - var307;
                super.F = var308;
                int var309 = this.HL();
                this.mem(var309, 36351);
                if (super.F == 0) {
                  break label222;
                }

                int var310 = this.mem(34261, 36355);
                super.A = var310;
                int var311 = super.A + 1 & 255;
                super.A = var311;
                super.F = super.A;
                this.wMem(34261, super.A, 36359);
                int var312 = super.A - 8 & 255;
                super.A = var312;
                super.F = super.A;
                int var313 = -super.A & 255;
                super.A = var313;
                int var314 = super.A + 1 & 255;
                super.A = var314;
                super.F = super.A;
                int var315 = super.A;
                int var316 = this.rlc(var315);
                super.A = var316;
                int var317 = super.A;
                int var318 = this.rlc(var317);
                super.A = var318;
                int var319 = super.A;
                int var320 = this.rlc(var319);
                super.A = var320;
                super.D = super.A;
                super.C = 32;
                int var321 = this.mem(32990, 36376);
                super.A = var321;

                do {
                  int var322 = super.A ^ 24;
                  super.A = var322;
                  super.F = super.A;
                  super.B = super.D;

                  do {
                    int var323 = super.B + -1 & 0xff;
                    super.B = var323;
                  } while (super.B != 0);

                  int var324 = super.C + -1 & 255;
                  super.C = var324;
                  super.F = super.C;
                } while (super.F != 0);

                int var325 = this.mem(34261, 36389);
                super.A = var325;
                int var326 = super.A - 18;
                super.F = var326;
                if (super.F == 0) {
                  super.A = 6;
                  this.wMem(34257, super.A, 36530);
                  return;
                }

                int var327 = super.A - 16;
                super.F = var327;
                if (super.F != 0) {
                  int var328 = super.A - 13;
                  super.F = var328;
                  if (super.F != 0) {
                    break label218;
                  }
                }
              }

              int var213 = this.mem(34255, 36406);
              super.A = var213;
              int var214 = super.A & 14;
              super.A = var214;
              super.F = super.A;
              if (super.F != 0) {
                break label237;
              }

              int var247 = this.mem16(34259, 36413);
              this.HL(var247);
              this.DE(64);
              int var248 = this.HL();
              int var249 = this.DE();
              int var250 = var248 + var249 & '\uffff';
              this.HL(var250);
              int var251 = super.H & 2;
              super.F = var251;
              if (super.F != 0) {
                break label223;
              }

              int var258 = this.mem(32955, 36425);
              super.A = var258;
              int var259 = this.HL();
              int var260 = this.mem(var259, 36428);
              int var261 = super.A - var260;
              super.F = var261;
              int var262 = this.HL();
              this.mem(var262, 36428);
              if (super.F == 0) {
                break label237;
              }

              int var263 = this.HL() + 1 & '\uffff';
              this.HL(var263);
              int var264 = this.mem(32955, 36432);
              super.A = var264;
              int var265 = this.HL();
              int var266 = this.mem(var265, 36435);
              int var267 = super.A - var266;
              super.F = var267;
              int var268 = this.HL();
              this.mem(var268, 36435);
              if (super.F == 0) {
                break label237;
              }

              int var269 = this.mem(32928, 36438);
              super.A = var269;
              int var270 = this.HL();
              int var271 = this.mem(var270, 36441);
              int var272 = super.A - var271;
              super.F = var272;
              int var273 = this.HL();
              this.mem(var273, 36441);
              if (super.F == 0) {
                int var274 = this.HL();
                int var275 = this.mem(var274, 36446);
                int var276 = super.A - var275;
                super.F = var276;
                int var277 = this.HL();
                this.mem(var277, 36446);
                if (super.F == 0) {
                  break label237;
                }
              }
            }

            super.E = 255;
            int var4 = this.mem(34262, 36566);
            super.A = var4;
            int var5 = super.A + -1 & 255;
            super.A = var5;
            super.F = super.A;
            int var6 = super.A & 128;
            super.F = var6;
            if (super.F != 0) {
              label234:
              {
                int var85 = this.mem(34257, 36574);
                super.A = var85;
                int var86 = super.A - 12;
                super.F = var86;
                if (super.F >= 0) {
                  super.A = 255;
                  this.wMem(34257, super.A, 37050);
                  this.IX(33024);

                  while (true) {
                    int var87 = this.IX();
                    int var88 = this.mem(var87, 37060);
                    super.A = var88;
                    int var89 = super.A - 255;
                    super.F = var89;
                    if (super.F == 0) {
                      return;
                    }

                    int var90 = super.A & 3;
                    super.A = var90;
                    super.F = super.A;
                    if (super.F != 0) {
                      int var94 = super.A - 1;
                      super.F = var94;
                      if (super.F != 0) {
                        int var131 = super.A - 2;
                        super.F = var131;
                        if (super.F != 0) {
                          int var165 = this.IX();
                          int var166 = this.mem(var165, 37081) & 128;
                          super.F = var166;
                          if (super.F != 0) {
                            int var187 = this.IX() + 1;
                            int var188 = this.mem(var187, 37087);
                            super.A = var188;
                            int var189 = super.A & 128;
                            super.F = var189;
                            if (super.F != 0) {
                              int var193 = super.A - 2 & 255;
                              super.A = var193;
                              super.F = super.A;
                              int var194 = super.A - 148;
                              super.F = var194;
                              if (super.F < 0) {
                                int var195 = super.A - 2 & 255;
                                super.A = var195;
                                super.F = super.A;
                                int var196 = super.A - 128;
                                super.F = var196;
                                if (super.F == 0) {
                                  int var197 = super.A ^ super.A;
                                  super.A = var197;
                                  super.F = super.A;
                                }
                              }
                            } else {
                              int var190 = super.A + 2 & 255;
                              super.A = var190;
                              super.F = super.A;
                              int var191 = super.A - 18;
                              super.F = var191;
                              if (super.F < 0) {
                                int var192 = super.A + 2 & 255;
                                super.A = var192;
                                super.F = super.A;
                              }
                            }
                          } else {
                            int var167 = this.IX() + 1;
                            int var168 = this.mem(var167, 37119);
                            super.A = var168;
                            int var169 = super.A & 128;
                            super.F = var169;
                            if (super.F == 0) {
                              int var183 = super.A - 2 & 255;
                              super.A = var183;
                              super.F = super.A;
                              int var184 = super.A - 20;
                              super.F = var184;
                              if (super.F < 0) {
                                int var185 = super.A - 2 & 255;
                                super.A = var185;
                                super.F = super.A;
                                int var186 = super.A | super.A;
                                super.A = var186;
                                super.F = super.A;
                                if (super.F == 0) {
                                  super.A = 128;
                                }
                              }
                            } else {
                              int var170 = super.A + 2 & 255;
                              super.A = var170;
                              super.F = super.A;
                              int var171 = super.A - 146;
                              super.F = var171;
                              if (super.F < 0) {
                                int var182 = super.A + 2 & 255;
                                super.A = var182;
                                super.F = super.A;
                              }
                            }
                          }

                          int var172 = this.IX() + 1;
                          this.wMem(var172, super.A, 37149);
                          int var173 = super.A & 127;
                          super.A = var173;
                          super.F = super.A;
                          int var174 = this.IX() + 7;
                          int var175 = this.mem(var174, 37154);
                          int var176 = super.A - var175;
                          super.F = var176;
                          int var177 = this.IX() + 7;
                          this.mem(var177, 37154);
                          if (super.F == 0) {
                            int var178 = this.IX();
                            int var179 = this.mem(var178, 37160);
                            super.A = var179;
                            int var180 = super.A ^ 128;
                            super.A = var180;
                            super.F = super.A;
                            int var181 = this.IX();
                            this.wMem(var181, super.A, 37165);
                          }
                        } else {
                          label236:
                          {
                            int var132 = this.IX();
                            int var133 = this.mem(var132, 37247);
                            super.A = var133;
                            int var134 = super.A ^ 8;
                            super.A = var134;
                            super.F = super.A;
                            int var135 = this.IX();
                            this.wMem(var135, super.A, 37252);
                            int var136 = super.A & 24;
                            super.A = var136;
                            super.F = super.A;
                            if (super.F != 0) {
                              int var161 = this.IX();
                              int var162 = this.mem(var161, 37259);
                              super.A = var162;
                              int var163 = super.A + 32 & 255;
                              super.A = var163;
                              super.F = super.A;
                              int var164 = this.IX();
                              this.wMem(var164, super.A, 37264);
                            }

                            int var137 = this.IX() + 3;
                            int var138 = this.mem(var137, 37267);
                            super.A = var138;
                            int var139 = this.IX() + 4;
                            int var140 = this.mem(var139, 37270);
                            int var141 = super.A + var140 & 255;
                            super.A = var141;
                            int var142 = this.IX() + 4;
                            this.mem(var142, 37270);
                            int var143 = this.IX() + 4;
                            this.mem(var143, 37270);
                            super.F = super.A;
                            int var144 = this.IX() + 4;
                            this.mem(var144, 37270);
                            int var145 = this.IX() + 3;
                            this.wMem(var145, super.A, 37273);
                            int var146 = this.IX() + 7;
                            int var147 = this.mem(var146, 37276);
                            int var148 = super.A - var147;
                            super.F = var148;
                            int var149 = this.IX() + 7;
                            this.mem(var149, 37276);
                            if (super.F < 0) {
                              int var154 = this.IX() + 6;
                              int var155 = this.mem(var154, 37281);
                              int var156 = super.A - var155;
                              super.F = var156;
                              int var157 = this.IX() + 6;
                              this.mem(var157, 37281);
                              if (super.F != 0 && super.F >= 0) {
                                break label236;
                              }

                              int var158 = this.IX() + 6;
                              int var159 = this.mem(var158, 37288);
                              super.A = var159;
                              int var160 = this.IX() + 3;
                              this.wMem(var160, super.A, 37291);
                            }

                            int var150 = this.IX() + 4;
                            int var151 = this.mem(var150, 37294);
                            super.A = var151;
                            int var152 = -super.A & 255;
                            super.A = var152;
                            int var153 = this.IX() + 4;
                            this.wMem(var153, super.A, 37299);
                          }
                        }
                      } else {
                        int var95 = this.IX();
                        int var96 = this.mem(var95, 37171) & 128;
                        super.F = var96;
                        if (super.F == 0) {
                          int var114 = this.IX();
                          int var115 = this.mem(var114, 37177);
                          super.A = var115;
                          int var116 = super.A - 32 & 255;
                          super.A = var116;
                          super.F = super.A;
                          int var117 = super.A & 127;
                          super.A = var117;
                          super.F = super.A;
                          int var118 = this.IX();
                          this.wMem(var118, super.A, 37184);
                          int var119 = super.A - 96;
                          super.F = var119;
                          if (super.F >= 0) {
                            int var120 = this.IX() + 2;
                            int var121 = this.mem(var120, 37191);
                            super.A = var121;
                            int var122 = super.A & 31;
                            super.A = var122;
                            super.F = super.A;
                            int var123 = this.IX() + 6;
                            int var124 = this.mem(var123, 37196);
                            int var125 = super.A - var124;
                            super.F = var125;
                            int var126 = this.IX() + 6;
                            this.mem(var126, 37196);
                            if (super.F != 0) {
                              int var128 = this.IX() + 2;
                              int var129 = this.mem(var128, 37201) + -1;
                              this.wMem(var128, var129, 37201);
                              int var130 = var129 & 255;
                              this.wMem(var128, var130, 37201);
                              int var10000 = this.IX() + 2;
                            } else {
                              int var127 = this.IX();
                              this.wMem(var127, 129, 37206);
                            }
                          }
                        } else {
                          int var97 = this.IX();
                          int var98 = this.mem(var97, 37212);
                          super.A = var98;
                          int var99 = super.A + 32 & 255;
                          super.A = var99;
                          super.F = super.A;
                          int var100 = super.A | 128;
                          super.A = var100;
                          super.F = super.A;
                          int var101 = this.IX();
                          this.wMem(var101, super.A, 37219);
                          int var102 = super.A - 160;
                          super.F = var102;
                          if (super.F < 0) {
                            int var103 = this.IX() + 2;
                            int var104 = this.mem(var103, 37226);
                            super.A = var104;
                            int var105 = super.A & 31;
                            super.A = var105;
                            super.F = super.A;
                            int var106 = this.IX() + 7;
                            int var107 = this.mem(var106, 37231);
                            int var108 = super.A - var107;
                            super.F = var108;
                            int var109 = this.IX() + 7;
                            this.mem(var109, 37231);
                            if (super.F != 0) {
                              int var111 = this.IX() + 2;
                              int var112 = this.mem(var111, 37236) + 1;
                              this.wMem(var111, var112, 37236);
                              int var113 = var112 & 255;
                              this.wMem(var111, var113, 37236);
                              int var329 = this.IX() + 2;
                            } else {
                              int var110 = this.IX();
                              this.wMem(var110, 97, 37241);
                            }
                          }
                        }
                      }
                    }

                    this.DE(8);
                    int var91 = this.IX();
                    int var92 = this.DE();
                    int var93 = var91 + var92 & '\uffff';
                    this.IX(var93);
                  }
                }

                int var198 = super.A ^ super.A;
                super.A = var198;
                super.F = super.A;
                this.wMem(34257, super.A, 36583);
                int var199 = this.mem(32973, 36586);
                super.A = var199;
                int var200 = this.HL();
                int var201 = this.mem(var200, 36589);
                int var202 = super.A - var201;
                super.F = var202;
                int var203 = this.HL();
                this.mem(var203, 36589);
                if (super.F != 0) {
                  int var206 = this.HL() + 1 & '\uffff';
                  this.HL(var206);
                  int var207 = this.HL();
                  int var208 = this.mem(var207, 36593);
                  int var209 = super.A - var208;
                  super.F = var209;
                  int var210 = this.HL();
                  this.mem(var210, 36593);
                  if (super.F != 0) {
                    break label234;
                  }
                }

                int var204 = this.mem(32982, 36596);
                super.A = var204;
                int var205 = super.A - 3 & 255;
                super.A = var205;
                super.F = super.A;
                super.E = super.A;
              }
            }

            this.BC(57342);
            int var7 = this.in(super.C);
            super.A = var7;
            int var8 = super.A & 31;
            super.A = var8;
            super.F = super.A;
            int var9 = super.A | 32;
            super.A = var9;
            super.F = super.A;
            int var10 = super.A & super.E;
            super.A = var10;
            super.F = super.A;
            super.E = super.A;
            int var11 = this.mem(34271, 36613);
            super.A = var11;
            int var12 = super.A & 2;
            super.A = var12;
            super.F = super.A;
            int var13 = super.A;
            int var14 = this.rrc(var13);
            super.A = var14;
            int var15 = super.A ^ super.E;
            super.A = var15;
            super.F = super.A;
            super.E = super.A;
            this.BC(64510);
            int var16 = this.in(super.C);
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
            int var22 = this.in(super.C);
            super.A = var22;
            int var23 = super.A;
            int var24 = this.rrc(var23);
            super.A = var24;
            int var25 = super.A | 247;
            super.A = var25;
            super.F = super.A;
            int var26 = super.A & super.E;
            super.A = var26;
            super.F = super.A;
            super.E = super.A;
            super.B = 239;
            int var27 = this.in(super.C);
            super.A = var27;
            int var28 = super.A | 251;
            super.A = var28;
            super.F = super.A;
            int var29 = super.A & super.E;
            super.A = var29;
            super.F = super.A;
            super.E = super.A;
            int var30 = this.in(super.C);
            super.A = var30;
            int var31 = super.A;
            int var32 = this.rrc(var31);
            super.A = var32;
            int var33 = super.A | 251;
            super.A = var33;
            super.F = super.A;
            int var34 = super.A & super.E;
            super.A = var34;
            super.F = super.A;
            super.E = super.A;
            int var35 = this.mem(34254, 36658);
            super.A = var35;
            int var36 = super.A | super.A;
            super.A = var36;
            super.F = super.A;
            if (super.F == 0) {
              this.BC(31);
              int var82 = this.in(super.C);
              super.A = var82;
              int var83 = super.A & 3;
              super.A = (~var83) & 0xff;
              super.F = super.A;
              int var84 = super.A & super.E;
              super.A = var84;
              super.F = super.A;
              super.E = super.A;
            }

            super.C = 0;
            super.A = super.E;
            int var37 = super.A & 42;
            super.A = var37;
            super.F = super.A;
            int var38 = super.A - 42;
            super.F = var38;
            if (super.F != 0) {
              super.C = 4;
              int var81 = super.A ^ super.A;
              super.A = var81;
              super.F = super.A;
              this.wMem(34272, super.A, 36686);
            }

            super.A = super.E;
            int var39 = super.A & 21;
            super.A = var39;
            super.F = super.A;
            int var40 = super.A - 21;
            super.F = var40;
            if (super.F != 0) {
              int var79 = super.C | 8;
              super.C = var79;
              int var80 = super.A ^ super.A;
              super.A = var80;
              super.F = super.A;
              this.wMem(34272, super.A, 36699);
            }

            int var41 = this.mem(34256, 36702);
            super.A = var41;
            int var42 = super.A + super.C & 255;
            super.A = var42;
            super.F = super.A;
            super.C = super.A;
            super.B = 0;
            this.HL(33825);
            int var43 = this.HL();
            int var44 = this.BC();
            int var45 = var43 + var44 & '\uffff';
            this.HL(var45);
            int var46 = this.HL();
            int var47 = this.mem(var46, 36713);
            super.A = var47;
            this.wMem(34256, super.A, 36714);
            this.BC(32510);
            int var48 = this.in(super.C);
            super.A = var48;
            int var49 = super.A & 31;
            super.A = var49;
            super.F = super.A;
            int var50 = super.A - 31;
            super.F = var50;
            if (super.F == 0) {
              super.B = 239;
              int var73 = this.in(super.C);
              super.A = var73;
              int var74 = super.A & 1;
              super.F = var74;
              if (super.F != 0) {
                int var75 = this.mem(34254, 36736);
                super.A = var75;
                int var76 = super.A | super.A;
                super.A = var76;
                super.F = super.A;
                if (super.F != 0) {
                  break label218;
                }

                this.BC(31);
                int var77 = this.in(super.C);
                super.A = var77;
                int var78 = super.A & 16;
                super.F = var78;
                if (super.F == 0) {
                  break label218;
                }
              }
            }

            int var51 = this.mem(34271, 36751);
            super.A = var51;
            int var52 = super.A & 2;
            super.F = var52;
            if (super.F == 0) {
              int var63 = super.A ^ super.A;
              super.A = var63;
              super.F = super.A;
              this.wMem(34261, super.A, 36759);
              this.wMem(34272, super.A, 36762);
              int var64 = super.A + 1 & 255;
              super.A = var64;
              super.F = super.A;
              this.wMem(34257, super.A, 36766);
              int var65 = this.mem(34262, 36769);
              super.A = var65;
              int var66 = super.A + -1 & 255;
              super.A = var66;
              super.F = super.A;
              int var67 = super.A & 128;
              super.F = var67;
              if (super.F == 0) {
                super.A = 240;
                this.wMem(34262, super.A, 36779);
                int var68 = this.mem(34255, 36782);
                super.A = var68;
                int var69 = super.A & 240;
                super.A = var69;
                super.F = super.A;
                this.wMem(34255, super.A, 36787);
                this.HL(34256);
                int var70 = this.HL();
                int var71 = this.mem(var70, 36793) | 2;
                int var72 = this.HL();
                this.wMem(var72, var71, 36793);
                return;
              }
            }
            break label218;
          }

          int var215 = this.mem(34257, 36450);
          super.A = var215;
          int var216 = super.A - 1;
          super.F = var216;
          if (super.F != 0) {
            this.HL(34256);
            int var217 = this.HL();
            int var218 = this.mem(var217, 36461) & -3;
            int var219 = this.HL();
            this.wMem(var219, var218, 36461);
            int var220 = this.mem(34257, 36463);
            super.A = var220;
            int var221 = super.A | super.A;
            super.A = var221;
            super.F = super.A;
            if (super.F == 0) {
              super.A = 2;
              this.wMem(34257, super.A, 36536);
              return;
            }

            int var222 = super.A + 1 & 255;
            super.A = var222;
            super.F = super.A;
            int var223 = super.A - 16;
            super.F = var223;
            if (super.F == 0) {
              super.A = 12;
            }

            this.wMem(34257, super.A, 36477);
            int var224 = super.A;
            int var225 = this.rlc(var224);
            super.A = var225;
            int var226 = super.A;
            int var227 = this.rlc(var226);
            super.A = var227;
            int var228 = super.A;
            int var229 = this.rlc(var228);
            super.A = var229;
            int var230 = super.A;
            int var231 = this.rlc(var230);
            super.A = var231;
            super.D = super.A;
            super.C = 32;
            int var232 = this.mem(32990, 36487);
            super.A = var232;

            do {
              int var233 = super.A ^ 24;
              super.A = var233;
              super.F = super.A;
              super.B = super.D;

              do {
                int var234 = super.B + -1 & 0xff;
                super.B = var234;
              } while (super.B != 0);

              int var235 = super.C + -1 & 255;
              super.C = var235;
              super.F = super.C;
            } while (super.F != 0);

            int var236 = this.mem(34255, 36500);
            super.A = var236;
            int var237 = super.A + 8 & 255;
            super.A = var237;
            super.F = super.A;
            this.wMem(34255, super.A, 36505);
            int var238 = super.A & 240;
            super.A = var238;
            super.F = super.A;
            super.L = super.A;
            int var239 = super.A ^ super.A;
            super.A = var239;
            super.F = super.A;
            int var240 = super.L;

//            int var4 = this.rlc(L) & 0xFE; //FIXME:
//            super.L = var4;
//            int var242 = (super.A + carry + 92) & 255; //FIXME:
//            super.A = var242;

            int var241 = this.rlc(var240);
            super.L = var241;
            int var242 = super.A + 92 & 255;
            super.A = var242;
            super.F = super.A;
            super.H = super.A;
            int var243 = this.mem(34259, 36517);
            super.A = var243;
            int var244 = super.A & 31;
            super.A = var244;
            super.F = super.A;
            int var245 = super.A | super.L;
            super.A = var245;
            super.F = super.A;
            super.L = super.A;
            int var246 = this.HL();
            this.wMem16(34259, var246, 36524);
            return;
          }
        }

        int var53 = this.mem(34256, 36796);
        super.A = var53;
        int var54 = super.A & 2;
        super.A = var54;
        super.F = super.A;
        if (super.F == 0) {
          return;
        }

        int var55 = this.mem(34262, 36802);
        super.A = var55;
        int var56 = super.A + -1 & 255;
        super.A = var56;
        super.F = super.A;
        int var57 = super.A & 128;
        super.F = var57;
        if (super.F == 0) {
          return;
        }

        int var58 = this.mem(34256, 36809);
        super.A = var58;
        int var59 = super.A & 1;
        super.A = var59;
        super.F = super.A;
        int var60 = this.mem(34258, 36817);
        super.A = var60;
        int var61 = super.A | super.A;
        super.A = var61;
        super.F = super.A;
        int var62 = super.A + -1 & 255;
        super.A = var62;
        super.F = super.A;
        this.wMem(34258, super.A, 36824);
        return;
      }

      int var252 = this.mem(33004, 38098);
      super.A = var252;
      this.wMem(33824, super.A, 38101);
      int var253 = super.A ^ super.A;
      super.A = var253;
      super.F = super.A;
      this.wMem(34255, super.A, 38105);
      int var254 = this.mem(34257, 38108);
      super.A = var254;
      int var255 = super.A - 11;
      super.F = var255;
      if (super.F < 0) {
        super.A = 2;
        this.wMem(34257, super.A, 38117);
      }

      int var256 = this.mem(34259, 38120);
      super.A = var256;
      int var257 = super.A & 31;
      super.A = var257;
      super.F = super.A;
      this.wMem(34259, super.A, 38125);
      super.A = 92;
      this.wMem(34260, super.A, 38130);
      return;
    }

    int var299 = this.mem(34255, 36540);
    super.A = var299;
    int var300 = super.A + 16 & 255;
    super.A = var300;
    super.F = super.A;
    int var301 = super.A & 240;
    super.A = var301;
    super.F = super.A;
    this.wMem(34255, super.A, 36547);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A, 36555);
    this.HL(34256);
    int var302 = this.HL();
    int var303 = this.mem(var302, 36561) & -3;
    int var304 = this.HL();
    this.wMem(var304, var303, 36561);
  }

  public void $34463() {
    HL(16384);
    DE(16385);
    BC(6911);
    int var1 = HL();
    wMem(var1, 0, 34472);
    ldir();
    IX(34187);

    label272:
    while (true) {
      int var2 = A ^ A;
      A = var2;
      F = A;
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
      wMem(34255, A, 34791); //FIXME: room
      A = 22;
      wMem(33824, A, 34796);
      HL(23988);
      int var3 = HL();
      wMem16(34259, var3, 34802);
      HL(34172);
      int var4 = HL();
      wMem(var4, 48, 34808);
      int var5 = HL() + 1 & '\uffff';
      HL(var5);
      int var6 = HL();
      wMem(var6, 48, 34811);
      int var7 = HL() + 1 & '\uffff';
      HL(var7);
      int var8 = HL();
      wMem(var8, 48, 34814);
      H = 164;
      int var9 = mem(41983, 34818);
      A = var9;
      L = A;
      wMem(34270, A, 34822);

      do {
        int var10 = HL();
        int var11 = mem(var10, 34825) | 64;
        int var12 = HL();
        wMem(var12, var11, 34825);
        int var13 = L + 1 & 255;
        L = var13;
        F = L;
      } while (F != 0);

      HL(34274);
      int var14 = HL();
      int var15 = mem(var14, 34833) | 1;
      int var16 = HL();
      wMem(var16, var15, 34833);

      label165:
      while (true) {
        HL(16384);
        DE(16385);
        BC(6143);
        int var17 = HL();
        wMem(var17, 0, 34844);
        ldir();
        HL(38912);
        BC(768);
        ldir();
        HL(23136);
        DE(23137);
        BC(31);
        int var18 = HL();
        wMem(var18, 70, 34865);
        ldir();
        IX(33876);
        DE(20576);
        C = 32;
        $38528();
        DE(22528);

        do {
          int var19 = DE();
          int var20 = mem(var19, 34884);
          A = var20;
          int var21 = A | A;
          A = var21;
          F = A;
          if (F != 0) {
            int var321 = A - 211;
            F = var321;
            if (F != 0) {
              int var322 = A - 9;
              F = var322;
              if (F != 0) {
                int var323 = A - 45;
                F = var323;
                if (F != 0) {
                  int var324 = A - 36;
                  F = var324;
                  if (F != 0) {
                    C = 0;
                    int var325 = A - 8;
                    F = var325;
                    if (F != 0) {
                      int var338 = A - 41;
                      F = var338;
                      if (F != 0) {
                        int var339 = A - 44;
                        F = var339;
                        int var340 = A - 5;
                        F = var340;
                        if (F != 0) {
                          C = 16;
                        }
                      }
                    }

                    A = E;
                    int var326 = A & 1;
                    A = var326;
                    F = A;
                    int var327 = A;
                    int var328 = rlc(var327);
                    A = var328;
                    int var329 = A;
                    int var330 = rlc(var329);
                    A = var330;
                    int var331 = A;
                    int var332 = rlc(var331);
                    A = var332;
                    int var333 = A | C;
                    A = var333;
                    F = A;
                    C = A;
                    B = 0;
                    HL(33841);
                    int var334 = HL();
                    int var335 = BC();
                    int var336 = var334 + var335 & '\uffff';
                    HL(var336);
                    int lastDE = DE;
                    int var337 = D & 1;
                    F = var337;
                    D = 64;
                    if (F != 0) {
                      D = 72;
                    }

                    B = 8;
                    $38555();
                    DE(lastDE);
                  }
                }
              }
            }
          }

          int var22 = DE() + 1 & '\uffff';
          DE(var22);
          A = D;
          int var23 = A - 90;
          F = var23;
        } while (F != 0);

        BC(31);
        int var24 = A ^ A;
        A = var24;
        F = A;

        do {
          int var25 = in(C);
          E = var25;
          int var26 = A | E;
          A = var26;
          F = A;
          int var27 = B + -1 & 0xff;
          B = var27;
        } while (B != 0);

        int var28 = A & 32;
        A = var28;
        F = A;
        if (F == 0) {
          A = 1;
          wMem(34254, A, 34981);
        }

        HL(34299);
        $38562();
        if (F != 0) {
          break;
        }

        int var306 = A ^ A;
        A = var306;
        F = A;
        wMem(34276, A, 34994);

        while (true) {
          $35563();
          HL(23136);
          DE(23137);
          BC(31);
          int var307 = HL();
          wMem(var307, 79, 35009);
          ldir();
          int var308 = mem(34276, 35013);
          A = var308;
          IX(33876);
          E = A;
          D = 0;
          int var309 = IX();
          int var310 = DE();
          int var311 = var309 + var310 & '\uffff';
          IX(var311);
          DE(20576);
          C = 32;
          $38528();
          int var312 = mem(34276, 35033);
          A = var312;
          int var313 = A & 31;
          A = var313;
          F = A;
          int var314 = A + 50 & 255;
          A = var314;
          F = A;
          $38622();
          BC(45054);
          int var315 = in(C);
          A = var315;
          int var316 = A & 1;
          A = var316;
          F = A;
          int var317 = A - 1;
          F = var317;
          if (F == 0) {
            break label165;
          }

          int var318 = mem(34276, 35054);
          A = var318;
          int var319 = A + 1 & 255;
          A = var319;
          F = A;
          int var320 = A - 224;
          F = var320;
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
        int var29 = mem(33824, 35090);
        A = var29;
        int var30 = A | 192;
        A = var30;
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
          int var31 = IX();
          int var32 = mem(var31, 35115);
          L = var32;
          int var33 = L & -129;
          L = var33;
          H = 20;
          int var34 = HL() * 2 & '\uffff';
          HL(var34);
          int var35 = HL() * 2 & '\uffff';
          HL(var35);
          int var36 = HL() * 2 & '\uffff';
          HL(var36);
          BC(2);
          ldir();
          int var37 = IX() + 1;
          int var38 = mem(var37, 35130);
          C = var38;
          int var39 = HL();
          wMem(var39, C, 35133);
          BC(6);
          ldir();
          int var40 = IX() + 1 & '\uffff';
          IX(var40);
          int var41 = IX() + 1 & '\uffff';
          IX(var41);
          int var42 = A + -1 & 255;
          A = var42;
          F = A;
        } while (F != 0);

        HL(34255);
        DE(34263);
        BC(7);
        ldir();
        $36147();
        HL(20480);
        DE(20481);
        BC(2047);
        int var43 = HL();
        wMem(var43, 0, 35169);
        ldir();
        IX(32896);
        C = 32;
        DE(20480);
        $38528();
        IX(34132);
        DE(20576);
        C = 32;
        $38528();
        int var44 = mem(32990, 35197);
        A = var44;
        C = 254;
        int var45 = A ^ A;
        A = var45;
        F = A;
        wMem(34262, A, 35205);

        while (true) {
          label284:
          {
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
            int var46 = mem(34271, 35273);
            A = var46;
            int var47 = A - 3;
            F = var47;
            if (F != 0) {
              $36307();
            }

            int var48 = mem(34255, 35281);
            A = var48;
            int var49 = A - 225;
            F = var49;
            if (F >= 0) {
              $38064();
            }

            int var50 = mem(34271, 35289);
            A = var50;
            int var51 = A - 3;
            F = var51;
            if (F != 0) {
              $38344();
            }

            int var52 = mem(34271, 35297);
            A = var52;
            int var53 = A - 2;
            F = var53;
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
            int var54 = mem(34271, 35328);
            A = var54;
            int var55 = A & 2;
            A = var55;
            F = A;
            int var56 = A;
            int var57 = rrc(var56);
            A = var57;
            HL(34258);
            int var58 = HL();
            int var59 = mem(var58, 35337);
            int var60 = A | var59;
            A = var60;
            int var61 = HL();
            mem(var61, 35337);
            int var62 = HL();
            mem(var62, 35337);
            F = A;
            int var63 = HL();
            mem(var63, 35337);
            int var64 = HL();
            wMem(var64, A, 35338);
            int var65 = mem(34253, 35339);
            A = var65;
            int var66 = A | A;
            A = var66;
            F = A;
            if (F != 0) {
              int var297 = A + -1 & 255;
              A = var297;
              F = A;
              wMem(34253, A, 35346);
              int var298 = A;
              int var299 = rlc(var298);
              A = var299;
              int var300 = A;
              int var301 = rlc(var300);
              A = var301;
              int var302 = A;
              int var303 = rlc(var302);
              A = var303;
              int var304 = A & 56;
              A = var304;
              F = A;
              HL(23552);
              DE(23553);
              BC(511);
              int var305 = HL();
              wMem(var305, A, 35363);
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
            int var67 = mem(34251, 35401);
            A = var67;
            int var68 = A + 1 & 255;
            A = var68;
            F = A;
            wMem(34251, A, 35405);
            if (F == 0) {
              IX(34175);
              int var260 = IX() + 4;
              int var261 = mem(var260, 35414) + 1;
              wMem(var260, var261, 35414);
              int var262 = var261 & 255;
              wMem(var260, var262, 35414);
              int var263 = IX() + 4;
              int var264 = mem(var263, 35417);
              A = var264;
              int var265 = A - 58;
              F = var265;
              if (F == 0) {
                int var266 = IX() + 4;
                wMem(var266, 48, 35424);
                int var267 = IX() + 3;
                int var268 = mem(var267, 35428) + 1;
                wMem(var267, var268, 35428);
                int var269 = var268 & 255;
                wMem(var267, var269, 35428);
                int var270 = IX() + 3;
                int var271 = mem(var270, 35431);
                A = var271;
                int var272 = A - 54;
                F = var272;
                if (F == 0) {
                  int var273 = IX() + 3;
                  wMem(var273, 48, 35438);
                  int var274 = IX();
                  int var275 = mem(var274, 35442);
                  A = var275;
                  int var276 = A - 49;
                  F = var276;
                  if (F == 0) {
                    int var285 = IX() + 1;
                    int var286 = mem(var285, 35449) + 1;
                    wMem(var285, var286, 35449);
                    int var287 = var286 & 255;
                    wMem(var285, var287, 35449);
                    int var288 = IX() + 1;
                    int var289 = mem(var288, 35452);
                    A = var289;
                    int var290 = A - 51;
                    F = var290;
                    if (F == 0) {
                      int var291 = IX() + 5;
                      int var292 = mem(var291, 35459);
                      A = var292;
                      int var293 = A - 112;
                      F = var293;
                      if (F == 0) {
                        continue label272;
                      }

                      int var294 = IX();
                      wMem(var294, 32, 35467);
                      int var295 = IX() + 1;
                      wMem(var295, 49, 35471);
                      int var296 = IX() + 5;
                      wMem(var296, 112, 35475);
                    }
                  } else {
                    int var277 = IX() + 1;
                    int var278 = mem(var277, 35481) + 1;
                    wMem(var277, var278, 35481);
                    int var279 = var278 & 255;
                    wMem(var277, var279, 35481);
                    int var280 = IX() + 1;
                    int var281 = mem(var280, 35484);
                    A = var281;
                    int var282 = A - 58;
                    F = var282;
                    if (F == 0) {
                      int var283 = IX() + 1;
                      wMem(var283, 48, 35491);
                      int var284 = IX();
                      wMem(var284, 49, 35495);
                    }
                  }
                }
              }
            }

            BC(65278);
            int var69 = in(C);
            A = var69;
            E = A;
            B = 127;
            int var70 = in(C);
            A = var70;
            int var71 = A | E;
            A = var71;
            F = A;
            int var72 = A & 1;
            A = var72;
            F = A;
            if (F == 0) {
              continue label272;
            }

            int var73 = mem(34272, 35515);
            A = var73;
            int var74 = A + 1 & 255;
            A = var74;
            F = A;
            wMem(34272, A, 35519);
            if (F != 0) {
              B = 253;
              int var257 = in(C);
              A = var257;
              int var258 = A & 31;
              A = var258;
              F = A;
              int var259 = A - 31;
              F = var259;
              if (F == 0) {
                break label284;
              }

              DE(0);
            }

            while (true) {
              B = 2;
              int var75 = in(C);
              A = var75;
              int var76 = A & 31;
              A = var76;
              F = A;
              int var77 = A - 31;
              F = var77;
              if (true || F != 0) {
                HL(39424);
                DE(23040);
                BC(256);
                ldir();
                int var78 = mem(32990, 35602);
                A = var78;
                break;
              }

              int var253 = E + 1 & 255;
              E = var253;
              F = E;
              if (F == 0) {
                int var254 = D + 1 & 255;
                D = var254;
                F = D;
                if (F == 0) {
                  int var255 = mem(34275, 35553);
                  A = var255;
                  int var256 = A - 10;
                  F = var256;
                  if (F != 0) {
                    $35563();
                  }
                }
              }
            }
          }

          int var79 = mem(34257, 35607);
          A = var79;
          int var80 = A - 255;
          F = var80;
          if (F == 0) {
            A = 71;

            do {
              HL(22528);
              DE(22529);
              BC(511);
              int var81 = HL();
              wMem(var81, A, 35852);
              ldir();
              E = A;
              int var82 = A & 7;
              A = var82;
              F = A;
              int var83 = A;
              int var84 = rlc(var83);
              A = var84;
              int var85 = A;
              int var86 = rlc(var85);
              A = var86;
              int var87 = A;
              int var88 = rlc(var87);
              A = var88;
              int var89 = A | 7;
              A = var89;
              F = A;
              D = A;
              C = E;
              int var90 = C;
              int var91 = rrc(var90);
              C = var91;
              int var92 = C;
              int var93 = rrc(var92);
              C = var93;
              int var94 = C;
              int var95 = rrc(var94);
              C = var95;
              int var96 = A | 16;
              A = var96;
              F = A;
              int var97 = A ^ A;
              A = var97;
              F = A;

              do {
                int var98 = A ^ 24;
                A = var98;
                F = A;
                B = D;

                do {
                  int var99 = B + -1;
                  B = var99;
                } while (B != 0);

                int var100 = C + -1 & 255;
                C = var100;
                F = C;
              } while (F != 0);

              A = E;
              int var101 = A + -1 & 255;
              A = var101;
              F = A;
              int var102 = A - 63;
              F = var102;
            } while (F != 0);

            HL(34252);
            int var103 = HL();
            int var104 = mem(var103, 35894);
            A = var104;
            int var105 = A | A;
            A = var105;
            F = A;
            if (F == 0) {
              HL(16384);
              DE(16385);
              BC(4095);
              int var106 = HL();
              wMem(var106, 0, 35923);
              ldir();
              int var107 = A ^ A;
              A = var107;
              F = A;
              wMem(34276, A, 35928);
              DE(40256);
              HL(18575);
              C = 0;
              $37974();
              DE(40032);
              HL(18639);
              C = 0;
              $37974();

              do {
                int var108 = mem(34276, 35953);
                A = var108;
                C = A;
                B = 130;
                int var109 = BC();
                int var110 = mem(var109, 35959);
                A = var110;
                int var111 = A | 15;
                A = var111;
                F = A;
                L = A;
                int var112 = BC() + 1 & '\uffff';
                BC(var112);
                int var113 = BC();
                int var114 = mem(var113, 35964);
                A = var114;
                int var115 = A - 32 & 255;
                A = var115;
                F = A;
                H = A;
                DE(40000);
                C = 0;
                $37974();
                int var116 = mem(34276, 35976);
                A = var116;
                E = A;
                int var117 = A ^ A;
                A = var117;
                F = A;
                BC(64);

                do {
                  int var118 = A ^ 24;
                  A = var118;
                  F = A;
                  B = E;

                  do {
                    int var119 = B + -1;
                    B = var119;
                  } while (B != 0);

                  int var120 = C + -1 & 255;
                  C = var120;
                  F = C;
                } while (F != 0);

                HL(22528);
                DE(22529);
                BC(511);
                int var121 = mem(34276, 36004);
                A = var121;
                int var122 = A & 12;
                A = var122;
                F = A;
                int var123 = A;
                int var124 = rlc(var123);
                A = var124;
                int var125 = A | 71;
                A = var125;
                F = A;
                int var126 = HL();
                wMem(var126, A, 36012);
                ldir();
                int var127 = A & 250;
                A = var127;
                F = A;
                int var128 = A | 2;
                A = var128;
                F = A;
                wMem(22991, A, 36019);
                wMem(22992, A, 36022);
                wMem(23023, A, 36025);
                wMem(23024, A, 36028);
                int var129 = mem(34276, 36031);
                A = var129;
                int var130 = A + 4 & 255;
                A = var130;
                F = A;
                wMem(34276, A, 36036);
                int var131 = A - 196;
                F = var131;
              } while (F != 0);

              IX(34164);
              C = 4;
              DE(16586);
              $38528();
              IX(34168);
              C = 4;
              DE(16594);
              $38528();
              BC(0);
              D = 6;

              while (true) {
                int var132 = B + -1;
                B = var132;
                if (B == 0) {
                  A = C;
                  int var133 = A & 7;
                  A = var133;
                  F = A;
                  int var134 = A | 64;
                  A = var134;
                  F = A;
                  wMem(22730, A, 36079);
                  int var135 = A + 1 & 255;
                  A = var135;
                  F = A;
                  int var136 = A & 7;
                  A = var136;
                  F = A;
                  int var137 = A | 64;
                  A = var137;
                  F = A;
                  wMem(22731, A, 36087);
                  int var138 = A + 1 & 255;
                  A = var138;
                  F = A;
                  int var139 = A & 7;
                  A = var139;
                  F = A;
                  int var140 = A | 64;
                  A = var140;
                  F = A;
                  wMem(22732, A, 36095);
                  int var141 = A + 1 & 255;
                  A = var141;
                  F = A;
                  int var142 = A & 7;
                  A = var142;
                  F = A;
                  int var143 = A | 64;
                  A = var143;
                  F = A;
                  wMem(22733, A, 36103);
                  int var144 = A + 1 & 255;
                  A = var144;
                  F = A;
                  int var145 = A & 7;
                  A = var145;
                  F = A;
                  int var146 = A | 64;
                  A = var146;
                  F = A;
                  wMem(22738, A, 36111);
                  int var147 = A + 1 & 255;
                  A = var147;
                  F = A;
                  int var148 = A & 7;
                  A = var148;
                  F = A;
                  int var149 = A | 64;
                  A = var149;
                  F = A;
                  wMem(22739, A, 36119);
                  int var150 = A + 1 & 255;
                  A = var150;
                  F = A;
                  int var151 = A & 7;
                  A = var151;
                  F = A;
                  int var152 = A | 64;
                  A = var152;
                  F = A;
                  wMem(22740, A, 36127);
                  int var153 = A + 1 & 255;
                  A = var153;
                  F = A;
                  int var154 = A & 7;
                  A = var154;
                  F = A;
                  int var155 = A | 64;
                  A = var155;
                  F = A;
                  wMem(22741, A, 36135);
                  int var156 = C + -1 & 255;
                  C = var156;
                  F = C;
                  if (F == 0) {
                    int var157 = D + -1 & 255;
                    D = var157;
                    F = D;
                    if (F == 0) {
                      continue label272;
                    }
                  }
                }
              }
            }

            int var158 = HL();
            int var159 = mem(var158, 35899) + -1;
            int var160 = HL();
            wMem(var160, var159, 35899);
            int var161 = var159 & 255;
            int var162 = HL();
            wMem(var162, var161, 35899);
            HL(34263);
            DE(34255);
            BC(7);
            ldir();
            break;
          }

          B = 191;
          HL(34274);
          int var163 = in(C);
          A = var163;
          int var164 = A & 31;
          A = var164;
          F = A;
          int var165 = A - 31;
          F = var165;
          if (F != 0) {
            int var247 = HL();
            int var248 = mem(var247, 35628) & 1;
            F = var248;
            if (F == 0) {
              int var249 = HL();
              int var250 = mem(var249, 35632);
              A = var250;
              int var251 = A ^ 3;
              A = var251;
              F = A;
              int var252 = HL();
              wMem(var252, A, 35635);
            }
          } else {
            int var166 = HL();
            int var167 = mem(var166, 35638) & -2;
            int var168 = HL();
            wMem(var168, var167, 35638);
          }

          int var169 = HL();
          int var170 = mem(var169, 35640) & 2;
          F = var170;
          if (false && F == 0) {
            int var220 = A ^ A;
            A = var220;
            F = A;
            wMem(34272, A, 35645);
            int var221 = mem(34273, 35648);
            A = var221;
            int var222 = A + 1 & 255;
            A = var222;
            F = A;
            wMem(34273, A, 35652);
            int var223 = A & 126;
            A = var223;
            F = A;
            int var224 = A;
            int var225 = rrc(var224);
            A = var225;
            E = A;
            D = 0;
            HL(34399);
            int var226 = HL();
            int var227 = DE();
            int var228 = var226 + var227 & '\uffff';
            HL(var228);
            int var229 = mem(34252, 35665);
            A = var229;
            int var230 = A;
            int var231 = rlc(var230);
            A = var231;
            int var232 = A;
            int var233 = rlc(var232);
            A = var233;
            int var234 = A - 28 & 255;
            A = var234;
            F = A;
            int var235 = -A & 255;
            A = var235;
            int var236 = HL();
            int var237 = mem(var236, 35674);
            int var238 = A + var237 & 255;
            A = var238;
            int var239 = HL();
            mem(var239, 35674);
            int var240 = HL();
            mem(var240, 35674);
            F = A;
            int var241 = HL();
            mem(var241, 35674);
            D = A;
            int var242 = mem(32990, 35676);
            A = var242;
            E = D;
            BC(3);

            while (true) {
              int var243 = E + -1 & 255;
              E = var243;
              F = E;
              if (F == 0) {
                E = D;
                int var246 = A ^ 24;
                A = var246;
                F = A;
              }

              int var244 = B + -1;
              B = var244;
              if (B == 0) {
                int var245 = C + -1 & 255;
                C = var245;
                F = C;
                if (F == 0) {
                  break;
                }
              }
            }
          }

          BC(61438);
          int var171 = in(C);
          A = var171;
          int var172 = A & 2;
          F = var172;
          if (F == 0) {
            int var211 = A & 16;
            A = var211;
            F = A;
            int var212 = A ^ 16;
            A = var212;
            F = A;
            int var213 = A;
            int var214 = rlc(var213);
            A = var214;
            D = A;
            int var215 = mem(34275, 35712);
            A = var215;
            int var216 = A - 10;
            F = var216;
            if (F == 0) {
              BC(63486);
              int var217 = in(C);
              A = var217;
              int var218 = A & 31;
              A = var218;
              F = A;
              int var219 = A | D;
              A = var219;
              F = A;
              wMem(33824, A, 35729);
              break;
            }
          }

          int var173 = mem(34275, 35735);
          A = var173;
          int var174 = A - 10;
          F = var174;
          if (F != 0) {
            int var175 = mem(33824, 35743);
            A = var175;
            int var176 = A - 28;
            F = var176;
            if (F == 0) {
              int var177 = mem(34255, 35751);
              A = var177;
              int var178 = A - 208;
              F = var178;
              if (F == 0) {
                int var179 = mem(34275, 35759);
                A = var179;
                int var180 = A;
                int var181 = rlc(var180);
                A = var181;
                E = A;
                D = 0;
                IX(34279);
                int var182 = IX();
                int var183 = DE();
                int var184 = var182 + var183 & '\uffff';
                IX(var184);
                BC(64510);
                int var185 = in(C);
                A = var185;
                int var186 = A & 31;
                A = var186;
                F = A;
                int var187 = IX();
                int var188 = mem(var187, 35779);
                int var189 = A - var188;
                F = var189;
                int var190 = IX();
                mem(var190, 35779);
                if (F != 0) {
                  int var205 = A - 31;
                  F = var205;
                  if (F != 0) {
                    int var206 = IX();
                    int var207 = mem(var206, 35789);
                    int var208 = A - var207;
                    F = var208;
                    int var209 = IX();
                    mem(var209, 35789);
                    if (F != 0) {
                      int var210 = A ^ A;
                      A = var210;
                      F = A;
                      wMem(34275, A, 35796);
                    }
                  }
                } else {
                  B = 223;
                  int var191 = in(C);
                  A = var191;
                  int var192 = A & 31;
                  A = var192;
                  F = A;
                  int var193 = IX() + 1;
                  int var194 = mem(var193, 35808);
                  int var195 = A - var194;
                  F = var195;
                  int var196 = IX() + 1;
                  mem(var196, 35808);
                  if (F != 0) {
                    int var199 = A - 31;
                    F = var199;
                    if (F != 0) {
                      int var200 = IX();
                      int var201 = mem(var200, 35818);
                      int var202 = A - var201;
                      F = var202;
                      int var203 = IX();
                      mem(var203, 35818);
                      if (F != 0) {
                        int var204 = A ^ A;
                        A = var204;
                        F = A;
                        wMem(34275, A, 35825);
                      }
                    }
                  } else {
                    int var197 = mem(34275, 35831);
                    A = var197;
                    int var198 = A + 1 & 255;
                    A = var198;
                    F = A;
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


  public void $36540() {
    int var1 = this.mem(34255);
    super.A = var1;
    int var2 = super.A + 16 & 255;
    super.A = var2;
    super.F = super.A;
    int var3 = super.A & 240;
    super.A = var3;
    super.F = super.A;
    this.wMem(34255, super.A);
    this.$36508();
    super.A = 2;
    this.wMem(34257, super.A);
    this.HL(34256);
    int var4 = this.HL();
    int var5 = this.mem(var4) & -3;
    int var6 = this.HL();
    this.wMem(var6, var5);
  }

  public void $36528() {
    super.A = 6;
    this.wMem(34257, super.A);
  }

  public void $36534() {
    super.A = 2;
    this.wMem(34257, super.A);
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
        int var48 = super.B + -1 & 0xff;
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
        int var43 = super.B + -1 & 0xff;
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
                  int var216 = super.B + -1 & 0xff;
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

                  int var129 = super.B + -1 & 0xff;
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

                  int var120 = super.B + -1 & 0xff;
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
          int lastHL = HL(); //FIXME:
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
                  int var53 = super.B + -1 & 0xff;
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

      int var15 = super.B + -1 & 0xff;
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
        int var32 = super.B + -1 & 0xff;
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
    this.$38430();
    this.$38455();
  }

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
      int var20 = IX();
      int var21 = mem(var20, 38504);
      A = var21;
      int var22 = IX() + 1;
      int var23 = mem(var22, 38507);
      H = var23;
      int var24 = A | C;
      A = var24;
      F = A;
      L = A;
      int var25 = DE();
      int var26 = mem(var25, 38512);
      A = var26;
      int var27 = HL();
      int var28 = mem(var27, 38513);
      int var29 = A | var28;
      A = var29;
      int var30 = HL();
      mem(var30, 38513);
      int var31 = HL();
      mem(var31, 38513);
      F = A;
      int var32 = HL();
      mem(var32, 38513);
      int var33 = HL();
      wMem(var33, A, 38514);
      int var34 = HL() + 1 & '\uffff';
      HL(var34);
      int var35 = DE() + 1 & '\uffff';
      DE(var35);
      int var36 = DE();
      int var37 = mem(var36, 38517);
      A = var37;
      int var38 = HL();
      int var39 = mem(var38, 38518);
      int var40 = A | var39;
      A = var40;
      int var41 = HL();
      mem(var41, 38518);
      int var42 = HL();
      mem(var42, 38518);
      F = A;
      int var43 = HL();
      mem(var43, 38518);
      int var44 = HL();
      wMem(var44, A, 38519);
      int var45 = IX() + 1 & '\uffff';
      IX(var45);
      int var46 = IX() + 1 & '\uffff';
      IX(var46);
      int var47 = DE() + 1 & '\uffff';
      DE(var47);
      int var48 = B + -1;
      B = var48;
    } while (B != 0);

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
      int var29 = super.B + -1 & 0xff;
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
      int var10 = super.B + -1 & 0xff;
      super.B = var10;
    } while (super.B != 0);

  }

  public void $38562() {
//    while (true) {
//      int var1 = this.HL();
//      int var2 = this.mem(var1);
//      super.A = var2;
//      int var3 = super.A - 255;
//      super.F = var3;
//      if (super.F == 0) {
//        return;
//      }
//
//      this.BC(100);
//      int var4 = super.A ^ super.A;
//      super.A = var4;
//      super.F = super.A;
//      int var5 = this.HL();
//      int var6 = this.mem(var5);
//      super.E = var6;
//      super.D = super.E;
//
//      while (true) {
//        int var7 = super.D + -1 & 255;
//        super.D = var7;
//        super.F = super.D;
//        if (super.F == 0) {
//          super.D = super.E;
//          int var12 = super.A ^ 24;
//          super.A = var12;
//          super.F = super.A;
//        }
//
//        int var8 = super.B + -1 & 0xff;
//        super.B = var8;
//        if (super.B == 0) {
//          int lastA = A;
//          super.A = super.C;
//          int var9 = super.A - 50;
//          super.F = var9;
//          if (super.F == 0) {
//            E= rlc(E);
//          }
//
//          A= lastA;
//          int var10 = super.C + -1 & 255;
//          super.C = var10;
//          super.F = super.C;
//          if (super.F == 0) {
//            this.$38601();
//            if (super.F != 0) {
//              return;
//            }
//
//            int var11 = this.HL() + 1 & '\uffff';
//            this.HL(var11);
//            break;
//          }
//        }
//      }
//    }
  }

  public void $38622() {
//    super.E = super.A;
//    super.C = 254;
//
//    do {
//      super.D = super.A;
//      int var1 = super.D & -17;
//      super.D = var1;
//      int var2 = super.D & -9;
//      super.D = var2;
//      super.B = super.E;
//
//      do {
//        int var3 = super.A - super.B;
//        super.F = var3;
//        if (super.F == 0) {
//          super.D = 24;
//        }
//
//        int var4 = super.B + -1;
//        super.B = var4;
//      } while (super.B != 0);
//
//      int var5 = super.A + -1 & 255;
//      super.A = var5;
//      super.F = super.A;
//    } while (super.F != 0);

  }

  public void $38555() {
    do {
      int var1 = this.HL();
      int var2 = this.mem(var1);
      super.A = var2;
      int var3 = this.DE();
      this.wMem(var3, super.A);
      int var4 = this.HL() + 1 & '\uffff';
      this.HL(var4);
      int var5 = super.D + 1 & 255;
      super.D = var5;
      super.F = super.D;
      int var6 = super.B + -1 & 0xff;
      super.B = var6;
    } while (super.B != 0);

  }

  public void $35211() {
    super.A = this.mem(34252);
    this.HL(20640);
    super.A = super.A | super.A;
    super.F = super.A;
    if (super.F != 0) {
      super.B = super.A;

      do {
        super.C = 0;
        int lastHL = HL(); //FIXME
        int lastBC = BC();//FIXME
        super.A = this.mem(34273);
        super.A = this.rlc(super.A);
        super.A = this.rlc(super.A);
        super.A = this.rlc(super.A);
        super.A = super.A & 96;
        super.F = super.A;
        super.E = super.A;
        super.D = 157;
        this.$37974();
        HL(lastHL);//FIXME
        BC(lastBC);//FIXME
        this.HL(this.HL() + 1 & '\uffff');
        this.HL(this.HL() + 1 & '\uffff');
        super.B = super.B + -1 & 0xff;
      } while (super.B != 0);
    }
  }

  public void $38064() {
    int var1 = this.mem(33003);
    super.A = var1;
    this.wMem(33824, super.A);
    int var2 = this.mem(34259);
    super.A = var2;
    int var3 = super.A & 31;
    super.A = var3;
    super.F = super.A;
    int var4 = super.A + 160 & 255;
    super.A = var4;
    super.F = super.A;
    this.wMem(34259, super.A);
    super.A = 93;
    this.wMem(34260, super.A);
    super.A = 208;
    this.wMem(34255, super.A);
    int var5 = super.A ^ super.A;
    super.A = var5;
    super.F = super.A;
    this.wMem(34257, super.A);
  }

  public void $38276() {
    int var1 = this.mem(33824);
    super.A = var1;
    int var2 = super.A - 33;
    super.F = var2;
    if (super.F == 0) {
      int var3 = this.mem(34259);
      super.A = var3;
      int var4 = super.A - 188;
      super.F = var4;
      if (super.F == 0) {
        int var5 = super.A ^ super.A;
        super.A = var5;
        super.F = super.A;
        this.wMem(34251, super.A);
        super.A = 3;
        this.wMem(34271, super.A);
      }
    }
  }
}
