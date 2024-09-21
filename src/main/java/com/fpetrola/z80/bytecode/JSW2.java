package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  public void $34762() {
    label277:
    while(true) {
      int var1 = A ^ A;
      A = var1;
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
      int var5 = HL();
      wMem(var5, 48, 34811);
      int var6 = HL() + 1 & '\uffff';
      HL(var6);
      int var7 = HL();
      wMem(var7, 48, 34814);
      H = 164;
      int var8 = mem(41983, 34818);
      A = var8;
      L = A;
      wMem(34270, A, 34822);

      do {
        int var9 = HL();
        int var10 = mem(var9, 34825) | 64;
        int var11 = HL();
        wMem(var11, var10, 34825);
        int var12 = L + 1 & 255;
        L = var12;
        F = L;
      } while(F != 0);

      HL(34274);
      int var13 = HL();
      int var14 = mem(var13, 34833) | 1;
      int var15 = HL();
      wMem(var15, var14, 34833);

      label269:
      while(true) {
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
        $38528();
        DE(22528);

        do {
          int var18 = DE();
          int var19 = mem(var18, 34884);
          A = var19;
          int var20 = A | A;
          A = var20;
          F = A;
          if (F != 0) {
            int var323 = A - 211;
            F = var323;
            if (F != 0) {
              int var324 = A - 9;
              F = var324;
              if (F != 0) {
                int var325 = A - 45;
                F = var325;
                if (F != 0) {
                  int var326 = A - 36;
                  F = var326;
                  if (F != 0) {
                    C = 0;
                    int var327 = A - 8;
                    F = var327;
                    if (F != 0) {
                      int var340 = A - 41;
                      F = var340;
                      if (F != 0) {
                        int var341 = A - 44;
                        F = var341;
                        if (F != 0) {
                          int var343 = A - 5;
                          F = var343;
                          if (F != 0) {
                            C = 16;
                          }
                        } else {
                          A = 37;
                          int var342 = DE();
                          wMem(var342, A, 34928);
                        }
                      }
                    }

                    A = E;
                    int var328 = A & 1;
                    A = var328;
                    F = A;
                    int var329 = A;
                    int var330 = rlc(var329);
                    A = var330;
                    int var331 = A;
                    int var332 = rlc(var331);
                    A = var332;
                    int var333 = A;
                    int var334 = rlc(var333);
                    A = var334;
                    int var335 = A | C;
                    A = var335;
                    F = A;
                    C = A;
                    B = 0;
                    HL(33841);
                    int var336 = HL();
                    int var337 = BC();
                    int var338 = var336 + var337 & '\uffff';
                    HL(var338);
                    int var339 = D & 1;
                    F = var339;
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

          int var21 = DE() + 1 & '\uffff';
          DE(var21);
          A = D;
          int var22 = A - 90;
          F = var22;
        } while(F != 0);

        BC(31);
        int var23 = A ^ A;
        A = var23;
        F = A;

        do {
          int var24 = in(C);
          E = var24;
          int var25 = A | E;
          A = var25;
          F = A;
          int var26 = B + -1 & 255;
          B = var26;
        } while(B != 0);

        int var27 = A & 32;
        A = var27;
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

        int var308 = A ^ A;
        A = var308;
        F = A;
        wMem(34276, A, 34994);

        while(true) {
          $35563();
          HL(23136);
          DE(23137);
          BC(31);
          int var309 = HL();
          wMem(var309, 79, 35009);
          ldir();
          int var310 = mem(34276, 35013);
          A = var310;
          IX(33876);
          E = A;
          D = 0;
          int var311 = IX();
          int var312 = DE();
          int var313 = var311 + var312 & '\uffff';
          IX(var313);
          DE(20576);
          C = 32;
          $38528();
          int var314 = mem(34276, 35033);
          A = var314;
          int var315 = A & 31;
          A = var315;
          F = A;
          int var316 = A + 50 & 255;
          A = var316;
          F = A;
          $38622();
          BC(45054);
          int var317 = in(C);
          A = var317;
          int var318 = A & 1;
          A = var318;
          F = A;
          int var319 = A - 1;
          F = var319;
          if (F != 0) {
            break label269;
          }

          int var320 = mem(34276, 35054);
          A = var320;
          int var321 = A + 1 & 255;
          A = var321;
          F = A;
          int var322 = A - 224;
          F = var322;
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

      while(true) {
        int var28 = mem(33824, 35090);
        A = var28;
        int var29 = A | 192;
        A = var29;
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
          int var30 = IX();
          int var31 = mem(var30, 35115);
          L = var31;
          int var32 = L & -129;
          L = var32;
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
          int var37 = mem(var36, 35130);
          C = var37;
          int var38 = HL();
          wMem(var38, C, 35133);
          BC(6);
          ldir();
          int var39 = IX() + 1 & '\uffff';
          IX(var39);
          int var40 = IX() + 1 & '\uffff';
          IX(var40);
          int var41 = A - 1 & 255;
          A = var41;
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
        int var42 = HL();
        wMem(var42, 0, 35169);
        ldir();
        IX(32896);
        C = 32;
        DE(20480);
        $38528();
        IX(34132);
        DE(20576);
        C = 32;
        $38528();
        int var43 = mem(32990, 35197);
        A = var43;
        C = 254;
        int var44 = A ^ A;
        A = var44;
        F = A;
        wMem(34262, A, 35205);

        while(true) {
          label287: {
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
            int var45 = mem(34271, 35273);
            A = var45;
            int var46 = A - 3;
            F = var46;
            if (F != 0) {
              $36307();
              if (decPops()) {
                break;
              }
            }

            int var47 = mem(34255, 35281);
            A = var47;
            int var48 = A - 225;
            F = var48;
            if (F >= 0) {
              $38064();
              if (decPops()) {
                break;
              }
            }

            label284: {
              int var49 = mem(34271, 35289);
              A = var49;
              int var50 = A - 3;
              F = var50;
              if (F != 0) {
                $38344();
                if (decPops()) {
                  A = 255;
                  wMem(34257, A, 37050);
                  break label284;
                }
              }

              int var51 = mem(34271, 35297);
              A = var51;
              int var52 = A - 2;
              F = var52;
              if (F == 0) {
                $38276();
              }

              $38196();
              $37310();
              $38137();
              $37841();
            }

            HL(24576);
            DE(16384);
            BC(4096);
            ldir();
            int var53 = mem(34271, 35328);
            A = var53;
            int var54 = A & 2;
            A = var54;
            F = A;
            int var55 = A;
            int var56 = rrc(var55);
            A = var56;
            HL(34258);
            int var57 = HL();
            int var58 = mem(var57, 35337);
            int var59 = A | var58;
            A = var59;
            int var60 = HL();
            mem(var60, 35337);
            int var61 = HL();
            mem(var61, 35337);
            F = A;
            int var62 = HL();
            mem(var62, 35337);
            int var63 = HL();
            wMem(var63, A, 35338);
            int var64 = mem(34253, 35339);
            A = var64;
            int var65 = A | A;
            A = var65;
            F = A;
            if (F != 0) {
              int var299 = A - 1 & 255;
              A = var299;
              F = A;
              wMem(34253, A, 35346);
              int var300 = A;
              int var301 = rlc(var300);
              A = var301;
              int var302 = A;
              int var303 = rlc(var302);
              A = var303;
              int var304 = A;
              int var305 = rlc(var304);
              A = var305;
              int var306 = A & 56;
              A = var306;
              F = A;
              HL(23552);
              DE(23553);
              BC(511);
              int var307 = HL();
              wMem(var307, A, 35363);
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
            int var66 = mem(34251, 35401);
            A = var66;
            int var67 = A + 1 & 255;
            A = var67;
            F = A;
            wMem(34251, A, 35405);
            if (F == 0) {
              IX(34175);
              int var262 = IX() + 4;
              int var263 = mem(var262, 35414) + 1;
              wMem(var262, var263, 35414);
              int var264 = var263 & 255;
              wMem(var262, var264, 35414);
              int var265 = IX() + 4;
              int var266 = mem(var265, 35417);
              A = var266;
              int var267 = A - 58;
              F = var267;
              if (F == 0) {
                int var268 = IX() + 4;
                wMem(var268, 48, 35424);
                int var269 = IX() + 3;
                int var270 = mem(var269, 35428) + 1;
                wMem(var269, var270, 35428);
                int var271 = var270 & 255;
                wMem(var269, var271, 35428);
                int var272 = IX() + 3;
                int var273 = mem(var272, 35431);
                A = var273;
                int var274 = A - 54;
                F = var274;
                if (F == 0) {
                  int var275 = IX() + 3;
                  wMem(var275, 48, 35438);
                  int var276 = IX();
                  int var277 = mem(var276, 35442);
                  A = var277;
                  int var278 = A - 49;
                  F = var278;
                  if (F == 0) {
                    int var287 = IX() + 1;
                    int var288 = mem(var287, 35449) + 1;
                    wMem(var287, var288, 35449);
                    int var289 = var288 & 255;
                    wMem(var287, var289, 35449);
                    int var290 = IX() + 1;
                    int var291 = mem(var290, 35452);
                    A = var291;
                    int var292 = A - 51;
                    F = var292;
                    if (F == 0) {
                      int var293 = IX() + 5;
                      int var294 = mem(var293, 35459);
                      A = var294;
                      int var295 = A - 112;
                      F = var295;
                      if (F == 0) {
                        continue label277;
                      }

                      int var296 = IX();
                      wMem(var296, 32, 35467);
                      int var297 = IX() + 1;
                      wMem(var297, 49, 35471);
                      int var298 = IX() + 5;
                      wMem(var298, 112, 35475);
                    }
                  } else {
                    int var279 = IX() + 1;
                    int var280 = mem(var279, 35481) + 1;
                    wMem(var279, var280, 35481);
                    int var281 = var280 & 255;
                    wMem(var279, var281, 35481);
                    int var282 = IX() + 1;
                    int var283 = mem(var282, 35484);
                    A = var283;
                    int var284 = A - 58;
                    F = var284;
                    if (F == 0) {
                      int var285 = IX() + 1;
                      wMem(var285, 48, 35491);
                      int var286 = IX();
                      wMem(var286, 49, 35495);
                    }
                  }
                }
              }
            }

            BC(65278);
            int var68 = in(C);
            A = var68;
            E = A;
            B = 127;
            int var69 = in(C);
            A = var69;
            int var70 = A | E;
            A = var70;
            F = A;
            int var71 = A & 1;
            A = var71;
            F = A;
            if (F == 0) {
              continue label277;
            }

            int var72 = mem(34272, 35515);
            A = var72;
            int var73 = A + 1 & 255;
            A = var73;
            F = A;
            wMem(34272, A, 35519);
            if (F != 0) {
              B = 253;
              int var259 = in(C);
              A = var259;
              int var260 = A & 31;
              A = var260;
              F = A;
              int var261 = A - 31;
              F = var261;
              if (F == 0) {
                break label287;
              }

              DE(0);
            }

            while(true) {
              B = 2;
              int var74 = in(C);
              A = var74;
              int var75 = A & 31;
              A = var75;
              F = A;
              int var76 = A - 31;
              F = var76;
              if (F != 0) {
                HL(39424);
                DE(23040);
                BC(256);
                ldir();
                int var77 = mem(32990, 35602);
                A = var77;
                break;
              }

              int var255 = E + 1 & 255;
              E = var255;
              F = E;
              if (F == 0) {
                int var256 = D + 1 & 255;
                D = var256;
                F = D;
                if (F == 0) {
                  int var257 = mem(34275, 35553);
                  A = var257;
                  int var258 = A - 10;
                  F = var258;
                  if (F != 0) {
                    $35563();
                  }
                }
              }
            }
          }

          int var78 = mem(34257, 35607);
          A = var78;
          int var79 = A - 255;
          F = var79;
          if (F == 0) {
            A = 71;

            do {
              HL(22528);
              DE(22529);
              BC(511);
              int var80 = HL();
              wMem(var80, A, 35852);
              ldir();
              E = A;
              int var81 = ~A;
              A = var81;
              F = A;
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
                  int var99 = B + -1 & 255;
                  B = var99;
                } while(B != 0);

                int var100 = C - 1 & 255;
                C = var100;
                F = C;
              } while(F != 0);

              A = E;
              int var101 = A - 1 & 255;
              A = var101;
              F = A;
              int var102 = A - 63;
              F = var102;
            } while(F != 0);

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
                int var117 = ~A;
                A = var117;
                F = A;
                E = A;
                int var118 = A ^ A;
                A = var118;
                F = A;
                BC(64);

                do {
                  int var119 = A ^ 24;
                  A = var119;
                  F = A;
                  B = E;

                  do {
                    int var120 = B + -1 & 255;
                    B = var120;
                  } while(B != 0);

                  int var121 = C - 1 & 255;
                  C = var121;
                  F = C;
                } while(F != 0);

                HL(22528);
                DE(22529);
                BC(511);
                int var122 = mem(34276, 36004);
                A = var122;
                int var123 = A & 12;
                A = var123;
                F = A;
                int var124 = A;
                int var125 = rlc(var124);
                A = var125;
                int var126 = A | 71;
                A = var126;
                F = A;
                int var127 = HL();
                wMem(var127, A, 36012);
                ldir();
                int var128 = A & 250;
                A = var128;
                F = A;
                int var129 = A | 2;
                A = var129;
                F = A;
                wMem(22991, A, 36019);
                wMem(22992, A, 36022);
                wMem(23023, A, 36025);
                wMem(23024, A, 36028);
                int var130 = mem(34276, 36031);
                A = var130;
                int var131 = A + 4 & 255;
                A = var131;
                F = A;
                wMem(34276, A, 36036);
                int var132 = A - 196;
                F = var132;
              } while(F != 0);

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

              while(true) {
                int var133 = B + -1 & 255;
                B = var133;
                if (B == 0) {
                  A = C;
                  int var134 = A & 7;
                  A = var134;
                  F = A;
                  int var135 = A | 64;
                  A = var135;
                  F = A;
                  wMem(22730, A, 36079);
                  int var136 = A + 1 & 255;
                  A = var136;
                  F = A;
                  int var137 = A & 7;
                  A = var137;
                  F = A;
                  int var138 = A | 64;
                  A = var138;
                  F = A;
                  wMem(22731, A, 36087);
                  int var139 = A + 1 & 255;
                  A = var139;
                  F = A;
                  int var140 = A & 7;
                  A = var140;
                  F = A;
                  int var141 = A | 64;
                  A = var141;
                  F = A;
                  wMem(22732, A, 36095);
                  int var142 = A + 1 & 255;
                  A = var142;
                  F = A;
                  int var143 = A & 7;
                  A = var143;
                  F = A;
                  int var144 = A | 64;
                  A = var144;
                  F = A;
                  wMem(22733, A, 36103);
                  int var145 = A + 1 & 255;
                  A = var145;
                  F = A;
                  int var146 = A & 7;
                  A = var146;
                  F = A;
                  int var147 = A | 64;
                  A = var147;
                  F = A;
                  wMem(22738, A, 36111);
                  int var148 = A + 1 & 255;
                  A = var148;
                  F = A;
                  int var149 = A & 7;
                  A = var149;
                  F = A;
                  int var150 = A | 64;
                  A = var150;
                  F = A;
                  wMem(22739, A, 36119);
                  int var151 = A + 1 & 255;
                  A = var151;
                  F = A;
                  int var152 = A & 7;
                  A = var152;
                  F = A;
                  int var153 = A | 64;
                  A = var153;
                  F = A;
                  wMem(22740, A, 36127);
                  int var154 = A + 1 & 255;
                  A = var154;
                  F = A;
                  int var155 = A & 7;
                  A = var155;
                  F = A;
                  int var156 = A | 64;
                  A = var156;
                  F = A;
                  wMem(22741, A, 36135);
                  int var157 = C - 1 & 255;
                  C = var157;
                  F = C;
                  if (F == 0) {
                    int var158 = D - 1 & 255;
                    D = var158;
                    F = D;
                    if (F == 0) {
                      continue label277;
                    }
                  }
                }
              }
            }

            int var159 = HL();
            int var160 = mem(var159, 35899) - 1;
            int var161 = HL();
            wMem(var161, var160, 35899);
            int var162 = var160 & 255;
            int var163 = HL();
            wMem(var163, var162, 35899);
            HL(34263);
            DE(34255);
            BC(7);
            ldir();
            break;
          }

          B = 191;
          HL(34274);
          int var164 = in(C);
          A = var164;
          int var165 = A & 31;
          A = var165;
          F = A;
          int var166 = A - 31;
          F = var166;
          if (F != 0) {
            int var249 = HL();
            int var250 = mem(var249, 35628) & 1;
            F = var250;
            if (F == 0) {
              int var251 = HL();
              int var252 = mem(var251, 35632);
              A = var252;
              int var253 = A ^ 3;
              A = var253;
              F = A;
              int var254 = HL();
              wMem(var254, A, 35635);
            }
          } else {
            int var167 = HL();
            int var168 = mem(var167, 35638) & -2;
            int var169 = HL();
            wMem(var169, var168, 35638);
          }

          int var170 = HL();
          int var171 = mem(var170, 35640) & 2;
          F = var171;
          if (F == 0) {
            int var222 = A ^ A;
            A = var222;
            F = A;
            wMem(34272, A, 35645);
            int var223 = mem(34273, 35648);
            A = var223;
            int var224 = A + 1 & 255;
            A = var224;
            F = A;
            wMem(34273, A, 35652);
            int var225 = A & 126;
            A = var225;
            F = A;
            int var226 = A;
            int var227 = rrc(var226);
            A = var227;
            E = A;
            D = 0;
            HL(34399);
            int var228 = HL();
            int var229 = DE();
            int var230 = var228 + var229 & '\uffff';
            HL(var230);
            int var231 = mem(34252, 35665);
            A = var231;
            int var232 = A;
            int var233 = rlc(var232);
            A = var233;
            int var234 = A;
            int var235 = rlc(var234);
            A = var235;
            int var236 = A - 28 & 255;
            A = var236;
            F = A;
            int var237 = -A & 255;
            A = var237;
            int var238 = HL();
            int var239 = mem(var238, 35674);
            int var240 = A + var239 & 255;
            A = var240;
            int var241 = HL();
            mem(var241, 35674);
            int var242 = HL();
            mem(var242, 35674);
            F = A;
            int var243 = HL();
            mem(var243, 35674);
            D = A;
            int var244 = mem(32990, 35676);
            A = var244;
            E = D;
            BC(3);

            while(true) {
              int var245 = E - 1 & 255;
              E = var245;
              F = E;
              if (F == 0) {
                E = D;
                int var248 = A ^ 24;
                A = var248;
                F = A;
              }

              int var246 = B + -1 & 255;
              B = var246;
              if (B == 0) {
                int var247 = C - 1 & 255;
                C = var247;
                F = C;
                if (F == 0) {
                  break;
                }
              }
            }
          }

          BC(61438);
          int var172 = in(C);
          A = var172;
          int var173 = A & 2;
          F = var173;
          if (F == 0) {
            int var212 = A & 16;
            A = var212;
            F = A;
            int var213 = A ^ 16;
            A = var213;
            F = A;
            int var214 = A;
            int var215 = rlc(var214);
            A = var215;
            D = A;
            int var216 = mem(34275, 35712);
            A = var216;
            int var217 = A - 10;
            F = var217;
            if (F == 0) {
              BC(63486);
              int var218 = in(C);
              A = var218;
              int var219 = ~A;
              A = var219;
              F = A;
              int var220 = A & 31;
              A = var220;
              F = A;
              int var221 = A | D;
              A = var221;
              F = A;
              wMem(33824, A, 35729);
              break;
            }
          }

          int var174 = mem(34275, 35735);
          A = var174;
          int var175 = A - 10;
          F = var175;
          if (F != 0) {
            int var176 = mem(33824, 35743);
            A = var176;
            int var177 = A - 28;
            F = var177;
            if (F == 0) {
              int var178 = mem(34255, 35751);
              A = var178;
              int var179 = A - 208;
              F = var179;
              if (F == 0) {
                int var180 = mem(34275, 35759);
                A = var180;
                int var181 = A;
                int var182 = rlc(var181);
                A = var182;
                E = A;
                D = 0;
                IX(34279);
                int var183 = IX();
                int var184 = DE();
                int var185 = var183 + var184 & '\uffff';
                IX(var185);
                BC(64510);
                int var186 = in(C);
                A = var186;
                int var187 = A & 31;
                A = var187;
                F = A;
                int var188 = IX();
                int var189 = mem(var188, 35779);
                int var190 = A - var189;
                F = var190;
                int var191 = IX();
                mem(var191, 35779);
                if (F != 0) {
                  int var206 = A - 31;
                  F = var206;
                  if (F != 0) {
                    int var207 = IX();
                    int var208 = mem(var207, 35789);
                    int var209 = A - var208;
                    F = var209;
                    int var210 = IX();
                    mem(var210, 35789);
                    if (F != 0) {
                      int var211 = A ^ A;
                      A = var211;
                      F = A;
                      wMem(34275, A, 35796);
                    }
                  }
                } else {
                  B = 223;
                  int var192 = in(C);
                  A = var192;
                  int var193 = A & 31;
                  A = var193;
                  F = A;
                  int var194 = IX() + 1;
                  int var195 = mem(var194, 35808);
                  int var196 = A - var195;
                  F = var196;
                  int var197 = IX() + 1;
                  mem(var197, 35808);
                  if (F != 0) {
                    int var200 = A - 31;
                    F = var200;
                    if (F != 0) {
                      int var201 = IX();
                      int var202 = mem(var201, 35818);
                      int var203 = A - var202;
                      F = var203;
                      int var204 = IX();
                      mem(var204, 35818);
                      if (F != 0) {
                        int var205 = A ^ A;
                        A = var205;
                        F = A;
                        wMem(34275, A, 35825);
                      }
                    }
                  } else {
                    int var198 = mem(34275, 35831);
                    A = var198;
                    int var199 = A + 1 & 255;
                    A = var199;
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

  public void $35211() {
    int var1 = mem(34252, 35211);
    A = var1;
    HL(20640);
    int var2 = A | A;
    A = var2;
    F = A;
    if (F != 0) {
      ;
    }
  }

  public void $35563() {
    HL(22528);
    int var1 = HL();
    int var2 = mem(var1, 35566);
    A = var2;
    int var3 = A & 7;
    A = var3;
    F = A;

    do {
      int var4 = HL();
      int var5 = mem(var4, 35571);
      A = var5;
      int var6 = A + 3 & 255;
      A = var6;
      F = A;
      int var7 = A & 7;
      A = var7;
      F = A;
      D = A;
      int var8 = HL();
      int var9 = mem(var8, 35577);
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
      int var13 = HL();
      wMem(var13, A, 35583);
      int var14 = HL() + 1 & '\uffff';
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
    wMem(36189, A, 36156);
    $36171();
    IX(24320);
    A = 120;
    wMem(36189, A, 36168);
    $36171();
  }

  public void $36171() {
    C = 0;

    do {
      E = C;
      int var1 = IX();
      int var2 = mem(var1, 36174);
      A = var2;
      HL(32928);
      BC(54);
      cpir();
      C = E;
      B = 8;
      D = 120;

      do {
        int var3 = HL();
        int var4 = mem(var3, 36190);
        A = var4;
        int var5 = DE();
        wMem(var5, A, 36191);
        int var6 = HL() + 1 & '\uffff';
        HL(var6);
        int var7 = D + 1 & 255;
        D = var7;
        F = D;
        int var8 = B + -1 & 255;
        B = var8;
      } while(B != 0);

      int var9 = IX() + 1 & '\uffff';
      IX(var9);
      int var10 = C + 1 & 255;
      C = var10;
      F = C;
    } while(F != 0);

  }

  public void $36203() {
    HL(32768);
    IX(24064);

    do {
      int var1 = HL();
      int var2 = mem(var1, 36210);
      A = var2;
      int var3 = A;
      int var4 = rlc(var3);
      A = var4;
      int var5 = A;
      int var6 = rlc(var5);
      A = var6;
      $36288();
      int var7 = HL();
      int var8 = mem(var7, 36216);
      A = var8;
      int var9 = A;
      int var10 = rrc(var9);
      A = var10;
      int var11 = A;
      int var12 = rrc(var11);
      A = var12;
      int var13 = A;
      int var14 = rrc(var13);
      A = var14;
      int var15 = A;
      int var16 = rrc(var15);
      A = var16;
      $36288();
      int var17 = HL();
      int var18 = mem(var17, 36224);
      A = var18;
      int var19 = A;
      int var20 = rrc(var19);
      A = var20;
      int var21 = A;
      int var22 = rrc(var21);
      A = var22;
      $36288();
      int var23 = HL();
      int var24 = mem(var23, 36230);
      A = var24;
      $36288();
      int var25 = HL() + 1 & '\uffff';
      HL(var25);
      A = L;
      int var26 = A & 128;
      A = var26;
      F = A;
    } while(F == 0);

    int var27 = mem(32985, 36240);
    A = var27;
    int var28 = A | A;
    A = var28;
    F = A;
    if (F != 0) {
      int var31 = mem16(32983, 36246);
      HL(var31);
      B = A;
      int var32 = mem(32973, 36250);
      A = var32;

      do {
        int var33 = HL();
        wMem(var33, A, 36253);
        int var34 = HL() + 1 & '\uffff';
        HL(var34);
        int var35 = B + -1 & 255;
        B = var35;
      } while(B != 0);
    }

    int var29 = mem(32989, 36257);
    A = var29;
    int var30 = A | A;
    A = var30;
    F = A;
    if (F != 0) {
      ;
    }
  }

  public void $36288() {
    int var1 = A & 3;
    A = var1;
    F = A;
    C = A;
    int var2 = A;
    int var3 = rlc(var2);
    A = var3;
    int var4 = A;
    int var5 = rlc(var4);
    A = var5;
    int var6 = A;
    int var7 = rlc(var6);
    A = var7;
    int var8 = A + C & 255;
    A = var8;
    F = A;
    int var9 = A + 160 & 255;
    A = var9;
    F = A;
    E = A;
    D = 128;
    int var10 = DE();
    int var11 = mem(var10, 36300);
    A = var11;
    int var12 = IX();
    wMem(var12, A, 36301);
    int var13 = IX() + 1 & '\uffff';
    IX(var13);
  }

  public void $36307() {
    int var1 = mem(34262, 36307);
    A = var1;
    int var2 = A - 1 & 255;
    A = var2;
    F = A;
    int var3 = A & 128;
    F = var3;
    if (F != 0) {
      int var4 = mem(34257, 36316);
      A = var4;
      int var5 = A - 1;
      F = var5;
      if (F == 0) {
        int var56 = mem(34261, 36323);
        A = var56;
        int var57 = A & 254;
        A = var57;
        F = A;
        int var58 = A - 8 & 255;
        A = var58;
        F = A;
        HL(34255);
        int var59 = HL();
        int var60 = mem(var59, 36333);
        int var61 = A + var60 & 255;
        A = var61;
        int var62 = HL();
        mem(var62, 36333);
        int var63 = HL();
        mem(var63, 36333);
        F = A;
        int var64 = HL();
        mem(var64, 36333);
        int var65 = HL();
        wMem(var65, A, 36334);
        int var66 = A - 240;
        F = var66;
        if (F >= 0) {
          return;
        }

        $36508();
        int var67 = mem(32946, 36343);
        A = var67;
        int var68 = HL();
        int var69 = mem(var68, 36346);
        int var70 = A - var69;
        F = var70;
        int var71 = HL();
        mem(var71, 36346);
        if (F == 0) {
          return;
        }

        int var72 = HL() + 1 & '\uffff';
        HL(var72);
        int var73 = HL();
        int var74 = mem(var73, 36351);
        int var75 = A - var74;
        F = var75;
        int var76 = HL();
        mem(var76, 36351);
        if (F == 0) {
          return;
        }

        int var77 = mem(34261, 36355);
        A = var77;
        int var78 = A + 1 & 255;
        A = var78;
        F = A;
        wMem(34261, A, 36359);
        int var79 = A - 8 & 255;
        A = var79;
        F = A;
        int var80 = -A & 255;
        A = var80;
        int var81 = A + 1 & 255;
        A = var81;
        F = A;
        int var82 = A;
        int var83 = rlc(var82);
        A = var83;
        int var84 = A;
        int var85 = rlc(var84);
        A = var85;
        int var86 = A;
        int var87 = rlc(var86);
        A = var87;
        D = A;
        C = 32;
        int var88 = mem(32990, 36376);
        A = var88;

        do {
          int var89 = A ^ 24;
          A = var89;
          F = A;
          B = D;

          do {
            int var90 = B + -1 & 255;
            B = var90;
          } while(B != 0);

          int var91 = C - 1 & 255;
          C = var91;
          F = C;
        } while(F != 0);

        int var92 = mem(34261, 36389);
        A = var92;
        int var93 = A - 18;
        F = var93;
        if (F == 0) {
          return;
        }

        int var94 = A - 16;
        F = var94;
        if (F != 0) {
          int var95 = A - 13;
          F = var95;
          if (F != 0) {
            return;
          }
        }
      }

      int var6 = mem(34255, 36406);
      A = var6;
      int var7 = A & 14;
      A = var7;
      F = A;
      if (F == 0) {
        int var31 = mem16(34259, 36413);
        HL(var31);
        DE(64);
        int var32 = HL();
        int var33 = DE();
        int var34 = var32 + var33 & '\uffff';
        HL(var34);
        int var35 = H & 2;
        F = var35;
        if (F != 0) {
          return;
        }

        int var36 = mem(32955, 36425);
        A = var36;
        int var37 = HL();
        int var38 = mem(var37, 36428);
        int var39 = A - var38;
        F = var39;
        int var40 = HL();
        mem(var40, 36428);
        if (F != 0) {
          int var41 = HL() + 1 & '\uffff';
          HL(var41);
          int var42 = mem(32955, 36432);
          A = var42;
          int var43 = HL();
          int var44 = mem(var43, 36435);
          int var45 = A - var44;
          F = var45;
          int var46 = HL();
          mem(var46, 36435);
          if (F != 0) {
            int var47 = mem(32928, 36438);
            A = var47;
            int var48 = HL();
            int var49 = mem(var48, 36441);
            int var50 = A - var49;
            F = var50;
            int var51 = HL();
            mem(var51, 36441);
            if (F != 0) {
              return;
            }

            int var52 = HL();
            int var53 = mem(var52, 36446);
            int var54 = A - var53;
            F = var54;
            int var55 = HL();
            mem(var55, 36446);
            if (F != 0) {
              return;
            }
          }
        }
      }

      int var8 = mem(34257, 36450);
      A = var8;
      int var9 = A - 1;
      F = var9;
      if (F != 0) {
        HL(34256);
        int var10 = HL();
        int var11 = mem(var10, 36461) & -3;
        int var12 = HL();
        wMem(var12, var11, 36461);
        int var13 = mem(34257, 36463);
        A = var13;
        int var14 = A | A;
        A = var14;
        F = A;
        if (F != 0) {
          int var15 = A + 1 & 255;
          A = var15;
          F = A;
          int var16 = A - 16;
          F = var16;
          if (F == 0) {
            A = 12;
          }

          wMem(34257, A, 36477);
          int var17 = A;
          int var18 = rlc(var17);
          A = var18;
          int var19 = A;
          int var20 = rlc(var19);
          A = var20;
          int var21 = A;
          int var22 = rlc(var21);
          A = var22;
          int var23 = A;
          int var24 = rlc(var23);
          A = var24;
          D = A;
          C = 32;
          int var25 = mem(32990, 36487);
          A = var25;

          do {
            int var26 = A ^ 24;
            A = var26;
            F = A;
            B = D;

            do {
              int var27 = B + -1 & 255;
              B = var27;
            } while(B != 0);

            int var28 = C - 1 & 255;
            C = var28;
            F = C;
          } while(F != 0);

          int var29 = mem(34255, 36500);
          A = var29;
          int var30 = A + 8 & 255;
          A = var30;
          F = A;
          wMem(34255, A, 36505);
          $36508();
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
    int var4 = rlc(var3);
    L = var4;
    int var5 = A + 92 & 255;
    A = var5;
    F = A;
    H = A;
    int var6 = mem(34259, 36517);
    A = var6;
    int var7 = A & 31;
    A = var7;
    F = A;
    int var8 = A | L;
    A = var8;
    F = A;
    L = A;
    int var9 = HL();
    wMem16(34259, var9, 36524);
  }

  public void $37056() {
    IX(33024);
    int var1 = IX();
    int var2 = mem(var1, 37060);
    A = var2;
    int var3 = A - 255;
    F = var3;
    if (F != 0) {
      ;
    }
  }

  public void $37310() {
    IX(33024);
    int var1 = IX();
    int var2 = mem(var1, 37314);
    A = var2;
    int var3 = A - 255;
    F = var3;
    if (F != 0) {
      ;
    }
  }

  public void $37841() {
    H = 164;
    int var1 = mem(41983, 37843);
    A = var1;
    L = A;

    do {
      int var2 = HL();
      int var3 = mem(var2, 37847);
      C = var3;
      int var4 = C & -129;
      C = var4;
      int var5 = mem(33824, 37850);
      A = var5;
      int var6 = A | 64;
      A = var6;
      F = A;
      int var7 = A - C;
      F = var7;
      if (F == 0) {
        int var9 = HL();
        int var10 = mem(var9, 37858);
        A = var10;
        int var11 = A;
        int var12 = rlc(var11);
        A = var12;
        int var13 = A & 1;
        A = var13;
        F = A;
        int var14 = A + 92 & 255;
        A = var14;
        F = A;
        D = A;
        int var15 = H + 1 & 255;
        H = var15;
        F = H;
        int var16 = HL();
        int var17 = mem(var16, 37866);
        E = var17;
        int var18 = H - 1 & 255;
        H = var18;
        F = H;
        int var19 = DE();
        int var20 = mem(var19, 37868);
        A = var20;
        int var21 = A & 7;
        A = var21;
        F = A;
        int var22 = A - 7;
        F = var22;
        if (F != 0) {
          int var23 = mem(34251, 37936);
          A = var23;
          int var24 = A + L & 255;
          A = var24;
          F = A;
          int var25 = A & 3;
          A = var25;
          F = A;
          int var26 = A + 3 & 255;
          A = var26;
          F = A;
          C = A;
          int var27 = DE();
          int var28 = mem(var27, 37945);
          A = var28;
          int var29 = A & 248;
          A = var29;
          F = A;
          int var30 = A | C;
          A = var30;
          F = A;
          int var31 = DE();
          wMem(var31, A, 37949);
          int var32 = HL();
          int var33 = mem(var32, 37950);
          A = var33;
          int var34 = A;
          int var35 = rlc(var34);
          A = var35;
          int var36 = A;
          int var37 = rlc(var36);
          A = var37;
          int var38 = A;
          int var39 = rlc(var38);
          A = var39;
          int var40 = A;
          int var41 = rlc(var40);
          A = var41;
          int var42 = A & 8;
          A = var42;
          F = A;
          int var43 = A + 96 & 255;
          A = var43;
          F = A;
          D = A;
          HL(32993);
          B = 8;
          $38555();
        } else {
          IX(34172);

          while(true) {
            int var44 = IX() + 2;
            int var45 = mem(var44, 37879) + 1;
            wMem(var44, var45, 37879);
            int var46 = var45 & 255;
            wMem(var44, var46, 37879);
            int var47 = IX() + 2;
            int var48 = mem(var47, 37882);
            A = var48;
            int var49 = A - 58;
            F = var49;
            if (F != 0) {
              int var50 = mem(32990, 37897);
              A = var50;
              C = 128;

              do {
                int var51 = A ^ 24;
                A = var51;
                F = A;
                E = A;
                A = 144;
                int var52 = A - C & 255;
                A = var52;
                F = A;
                B = A;
                A = E;

                do {
                  int var53 = B + -1 & 255;
                  B = var53;
                } while(B != 0);

                int var54 = C - 1 & 255;
                C = var54;
                F = C;
                int var55 = C - 1 & 255;
                C = var55;
                F = C;
              } while(F != 0);

              int var56 = mem(34270, 37918);
              A = var56;
              int var57 = A + 1 & 255;
              A = var57;
              F = A;
              wMem(34270, A, 37922);
              if (F == 0) {
                A = 1;
                wMem(34271, A, 37929);
              }

              int var58 = HL();
              int var59 = mem(var58, 37932) & -65;
              int var60 = HL();
              wMem(var60, var59, 37932);
              break;
            }

            int var61 = IX() + 2;
            wMem(var61, 48, 37889);
          }
        }
      }

      int var8 = L + 1 & 255;
      L = var8;
      F = L;
    } while(F != 0);

  }

  public void $37974() {
    B = 16;

    do {
      int var1 = C & 1;
      F = var1;
      int var2 = DE();
      int var3 = mem(var2, 37978);
      A = var3;
      if (F != 0) {
        int var27 = HL();
        int var28 = mem(var27, 37981);
        int var29 = A & var28;
        A = var29;
        int var30 = HL();
        mem(var30, 37981);
        int var31 = HL();
        mem(var31, 37981);
        F = A;
        int var32 = HL();
        mem(var32, 37981);
        if (F != 0) {
          return;
        }
      }

      int var4 = HL();
      wMem(var4, A, 37985);
      int var5 = L + 1 & 255;
      L = var5;
      F = L;
      int var6 = DE() + 1 & '\uffff';
      DE(var6);
      int var7 = C & 1;
      F = var7;
      int var8 = DE();
      int var9 = mem(var8, 37990);
      A = var9;
      if (F != 0) {
        int var21 = HL();
        int var22 = mem(var21, 37993);
        int var23 = A & var22;
        A = var23;
        int var24 = HL();
        mem(var24, 37993);
        int var25 = HL();
        mem(var25, 37993);
        F = A;
        int var26 = HL();
        mem(var26, 37993);
        if (F != 0) {
          return;
        }
      }

      int var10 = HL();
      wMem(var10, A, 37997);
      int var11 = L - 1 & 255;
      L = var11;
      F = L;
      int var12 = H + 1 & 255;
      H = var12;
      F = H;
      int var13 = DE() + 1 & '\uffff';
      DE(var13);
      A = H;
      int var14 = A & 7;
      A = var14;
      F = A;
      if (F == 0) {
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
        F = A;
        if (F == 0) {
          A = H;
          int var20 = A + 8 & 255;
          A = var20;
          F = A;
          H = A;
        }
      }

      int var15 = B + -1 & 255;
      B = var15;
    } while(B != 0);

    int var16 = A ^ A;
    A = var16;
    F = A;
  }

  public void $38064() {
    int var1 = mem(33003, 38064);
    A = var1;
    wMem(33824, A, 38067);
    int var2 = mem(34259, 38070);
    A = var2;
    int var3 = A & 31;
    A = var3;
    F = A;
    int var4 = A + 160 & 255;
    A = var4;
    F = A;
    wMem(34259, A, 38077);
    A = 93;
    wMem(34260, A, 38082);
    A = 208;
    wMem(34255, A, 38087);
    int var5 = A ^ A;
    A = var5;
    F = A;
    wMem(34257, A, 38091);
    incPops();
  }

  public void $38137() {
    int var1 = mem16(32983, 38137);
    HL(var1);
    A = H;
    int var2 = A & 1;
    A = var2;
    F = A;
    int var3 = A;
    int var4 = rlc(var3);
    A = var4;
    int var5 = A;
    int var6 = rlc(var5);
    A = var6;
    int var7 = A;
    int var8 = rlc(var7);
    A = var8;
    int var9 = A + 112 & 255;
    A = var9;
    F = A;
    H = A;
    E = L;
    D = H;
    int var10 = mem(32985, 38151);
    A = var10;
    int var11 = A | A;
    A = var11;
    F = A;
    if (F != 0) {
      ;
    }
  }

  public void $38196() {
    int var1 = mem(33824, 38196);
    A = var1;
    int var2 = A - 35;
    F = var2;
    if (F == 0) {
      int var5 = mem(34271, 38203);
      A = var5;
      int var6 = A | A;
      A = var6;
      F = A;
      if (F == 0) {
        int var10 = mem(34251, 38209);
        A = var10;
        int var11 = A & 2;
        A = var11;
        F = A;
        int var12 = A;
        int var13 = rrc(var12);
        A = var13;
        int var14 = A;
        int var15 = rrc(var14);
        A = var15;
        int var16 = A;
        int var17 = rrc(var16);
        A = var17;
        int var18 = A;
        int var19 = rrc(var18);
        A = var19;
        int var20 = A | 128;
        A = var20;
        F = A;
        E = A;
        int var21 = mem(34255, 38221);
        A = var21;
        int var22 = A - 208;
        F = var22;
        if (F != 0) {
          E = 192;
          int var23 = A - 192;
          F = var23;
          if (F < 0) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974();
        return;
      }

      int var7 = mem(34259, 38262);
      A = var7;
      int var8 = A & 31;
      A = var8;
      F = A;
      int var9 = A - 6;
      F = var9;
      if (F >= 0) {
        return;
      }
    }

    int var3 = mem(33824, 38298);
    A = var3;
    int var4 = A - 33;
    F = var4;
    if (F == 0) {
      ;
    }
  }

  public void $38276() {
    int var1 = mem(33824, 38276);
    A = var1;
    int var2 = A - 33;
    F = var2;
    if (F == 0) {
      ;
    }
  }

  public void $38344() {
    int var1 = mem16(34259, 38344);
    HL(var1);
    B = 0;
    int var2 = mem(32986, 38349);
    A = var2;
    int var3 = A & 1;
    A = var3;
    F = A;
    int var4 = A + 64 & 255;
    A = var4;
    F = A;
    E = A;
    D = 0;
    int var5 = HL();
    int var6 = DE();
    int var7 = var5 + var6 & '\uffff';
    HL(var7);
    int var8 = mem(32964, 38360);
    A = var8;
    int var9 = HL();
    int var10 = mem(var9, 38363);
    int var11 = A - var10;
    F = var11;
    int var12 = HL();
    mem(var12, 38363);
    if (F == 0) {
      int var15 = mem(34257, 38366);
      A = var15;
      int var16 = A | A;
      A = var16;
      F = A;
      if (F == 0) {
        int var17 = mem(34258, 38372);
        A = var17;
        int var18 = A & 3;
        A = var18;
        F = A;
        int var19 = A;
        int var20 = rlc(var19);
        A = var20;
        int var21 = A;
        int var22 = rlc(var21);
        A = var22;
        B = A;
        int var23 = mem(32986, 38380);
        A = var23;
        int var24 = A & 1;
        A = var24;
        F = A;
        int var25 = A - 1 & 255;
        A = var25;
        F = A;
        int var26 = A ^ 12;
        A = var26;
        F = A;
        int var27 = A ^ B;
        A = var27;
        F = A;
        int var28 = A & 12;
        A = var28;
        F = A;
        B = A;
      }
    }

    int var13 = mem16(34259, 38392);
    HL(var13);
    DE(31);
    C = 15;
    $38430();
    decPops();
    int var14 = HL() + 1 & '\uffff';
    HL(var14);
    $38430();
    decPops();
  }

  public void $38430() {
    int var1 = mem(32928, 38430);
    A = var1;
    int var2 = HL();
    int var3 = mem(var2, 38433);
    int var4 = A - var3;
    F = var4;
    int var5 = HL();
    mem(var5, 38433);
    if (F == 0) {
      A = C;
      int var11 = A & 15;
      A = var11;
      F = A;
      if (F != 0) {
        int var12 = mem(32928, 38441);
        A = var12;
        int var13 = A | 7;
        A = var13;
        F = A;
        int var14 = HL();
        wMem(var14, A, 38446);
      }
    }

    int var6 = mem(32955, 38447);
    A = var6;
    int var7 = HL();
    int var8 = mem(var7, 38450);
    int var9 = A - var8;
    F = var9;
    int var10 = HL();
    mem(var10, 38450);
  }

  public void $38528() {
    do {
      int var1 = IX();
      int var2 = mem(var1, 38528);
      A = var2;
      $38545();
      int var3 = IX() + 1 & '\uffff';
      IX(var3);
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
      F = C;
    } while(F != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    int var1 = L | 128;
    L = var1;
    int var2 = HL() * 2 & '\uffff';
    HL(var2);
    int var3 = HL() * 2 & '\uffff';
    HL(var3);
    int var4 = HL() * 2 & '\uffff';
    HL(var4);
    B = 8;
    $38555();
  }

  public void $38555() {
    do {
      int var1 = HL();
      int var2 = mem(var1, 38555);
      A = var2;
      int var3 = DE();
      wMem(var3, A, 38556);
      int var4 = HL() + 1 & '\uffff';
      HL(var4);
      int var5 = D + 1 & 255;
      D = var5;
      F = D;
      int var6 = B + -1 & 255;
      B = var6;
    } while(B != 0);

  }

  public void $38562() {
    int var1 = HL();
    int var2 = mem(var1, 38562);
    A = var2;
    int var3 = A - 255;
    F = var3;
    if (F != 0) {
      ;
    }
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

        int var4 = B + -1 & 255;
        B = var4;
      } while(B != 0);

      int var5 = A - 1 & 255;
      A = var5;
      F = A;
    } while(F != 0);

  }
}
