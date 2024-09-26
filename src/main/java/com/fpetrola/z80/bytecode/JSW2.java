package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.SpectrumApplication;

public class JSW2 extends SpectrumApplication {
  public JSW2() {
    super();
  }

  public void $34762(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    label282:
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
      } while(L != 0);

      HL(34274);
      int var13 = HL();
      int var14 = mem(var13, 34833) | 1;
      int var15 = HL();
      wMem(var15, var14, 34833);

      label274:
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
        $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        DE(22528);

        do {
          int var18 = DE();
          int var19 = mem(var18, 34884);
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
                int var314 = DE();
                wMem(var314, A, 34928);
              }
            }

            A = E;
            int var300 = A & 1;
            A = var300;
            F = A;
            int var301 = A;
            int var302 = rlc(var301);
            A = var302;
            int var303 = A;
            int var304 = rlc(var303);
            A = var304;
            int var305 = A;
            int var306 = rlc(var305);
            A = var306;
            int var307 = A | C;
            A = var307;
            F = A;
            C = A;
            B = 0;
            HL(33841);
            int var308 = HL();
            int var309 = BC();
            int var310 = var308 + var309 & '\uffff';
            HL(var310);
            int var311 = DE();
            push(var311);
            int var312 = D & 1;
            F = var312;
            D = 64;
            if (F != 0) {
              D = 72;
            }

            B = 8;
            $38555(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
            DE();
            int var313 = pop();
            DE(var313);
          }

          int var21 = DE() + 1 & '\uffff';
          DE(var21);
          A = D;
        } while(A != 90);

        BC(31);
        int var22 = A ^ A;
        A = var22;
        F = A;

        do {
          int var23 = BC();
          int var24 = in(var23);
          E = var24;
          int var25 = A | E;
          A = var25;
          int var26 = B - 1 & 255;
          B = var26;
        } while(B != 0);

        int var27 = A & 32;
        A = var27;
        if (A == 0) {
          A = 1;
          wMem(34254, A, 34981);
        }

        HL(34299);
        $38562(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        if (F != 0) {
          break;
        }

        int var285 = A ^ A;
        A = var285;
        F = A;
        wMem(34276, A, 34994);

        while(true) {
          $35563(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
          HL(23136);
          DE(23137);
          BC(31);
          int var286 = HL();
          wMem(var286, 79, 35009);
          ldir();
          int var287 = mem(34276, 35013);
          A = var287;
          IX(33876);
          E = A;
          D = 0;
          int var288 = IX();
          int var289 = DE();
          int var290 = var288 + var289 & '\uffff';
          IX(var290);
          DE(20576);
          C = 32;
          $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
          int var291 = mem(34276, 35033);
          A = var291;
          int var292 = A & 31;
          A = var292;
          F = A;
          int var293 = A + 50 & 255;
          A = var293;
          $38622(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
          BC(45054);
          int var294 = BC();
          int var295 = in(var294);
          A = var295;
          int var296 = A & 1;
          A = var296;
          F = A;
          if (A != 1) {
            break label274;
          }

          int var297 = mem(34276, 35054);
          A = var297;
          int var298 = A + 1 & 255;
          A = var298;
          F = A;
          int var299 = A - 224;
          F = var299;
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
        } while(A != 0);

        HL(34255);
        DE(34263);
        BC(7);
        ldir();
        $36147(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        HL(20480);
        DE(20481);
        BC(2047);
        int var42 = HL();
        wMem(var42, 0, 35169);
        ldir();
        IX(32896);
        C = 32;
        DE(20480);
        $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        IX(34132);
        DE(20576);
        C = 32;
        $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        int var43 = mem(32990, 35197);
        A = var43;
        C = 254;
        int var44 = A ^ A;
        A = var44;
        F = A;
        wMem(34262, A, 35205);

        while(true) {
          label292: {
            label213: {
              label289: {
                $35211(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                HL(24064);
                DE(23552);
                BC(512);
                ldir();
                HL(28672);
                DE(24576);
                BC(4096);
                ldir();
                $37056(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                int var45 = mem(34271, 35273);
                A = var45;
                if (A != 3) {
                  $36307(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  if (decPops()) {
                    break label289;
                  }
                }

                int var46 = mem(34255, 35281);
                A = var46;
                if (A >= 225) {
                  $38064(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  if (decPops()) {
                    break;
                  }
                }

                int var47 = mem(34271, 35289);
                A = var47;
                if (A != 3) {
                  $38344(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  if (decPops()) {
                    break label289;
                  }
                }

                int var48 = mem(34271, 35297);
                A = var48;
                if (A == 2) {
                  $38276(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                }

                $38196(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                if (!decPops()) {
                  $37310(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  if (!decPops()) {
                    $38137(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                    $37841(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                    break label213;
                  }
                }
              }

              A = 255;
              wMem(34257, A, 37050);
            }

            HL(24576);
            DE(16384);
            BC(4096);
            ldir();
            int var49 = mem(34271, 35328);
            A = var49;
            int var50 = A & 2;
            A = var50;
            F = A;
            int var51 = A;
            int var52 = rrc(var51);
            A = var52;
            HL(34258);
            int var53 = HL();
            int var54 = mem(var53, 35337);
            int var55 = A | var54;
            A = var55;
            int var56 = HL();
            mem(var56, 35337);
            F = A;
            int var57 = HL();
            wMem(var57, A, 35338);
            int var58 = mem(34253, 35339);
            A = var58;
            int var59 = A | A;
            A = var59;
            if (A != 0) {
              int var276 = A - 1 & 255;
              A = var276;
              F = A;
              wMem(34253, A, 35346);
              int var277 = A;
              int var278 = rlc(var277);
              A = var278;
              int var279 = A;
              int var280 = rlc(var279);
              A = var280;
              int var281 = A;
              int var282 = rlc(var281);
              A = var282;
              int var283 = A & 56;
              A = var283;
              F = A;
              HL(23552);
              DE(23553);
              BC(511);
              int var284 = HL();
              wMem(var284, A, 35363);
              ldir();
            }

            HL(23552);
            DE(22528);
            BC(512);
            ldir();
            IX(34175);
            DE(20601);
            C = 6;
            $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
            IX(34172);
            DE(20592);
            C = 3;
            $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
            int var60 = mem(34251, 35401);
            A = var60;
            int var61 = A + 1 & 255;
            A = var61;
            F = A;
            wMem(34251, A, 35405);
            if (F == 0) {
              IX(34175);
              int var245 = IX() + 4;
              int var246 = mem(var245, 35414) + 1;
              wMem(var245, var246, 35414);
              int var247 = var246 & 255;
              wMem(var245, var247, 35414);
              int var248 = IX() + 4;
              int var249 = mem(var248, 35417);
              A = var249;
              if (A == 58) {
                int var250 = IX() + 4;
                wMem(var250, 48, 35424);
                int var251 = IX() + 3;
                int var252 = mem(var251, 35428) + 1;
                wMem(var251, var252, 35428);
                int var253 = var252 & 255;
                wMem(var251, var253, 35428);
                int var254 = IX() + 3;
                int var255 = mem(var254, 35431);
                A = var255;
                if (A == 54) {
                  int var256 = IX() + 3;
                  wMem(var256, 48, 35438);
                  int var257 = IX();
                  int var258 = mem(var257, 35442);
                  A = var258;
                  if (A == 49) {
                    int var266 = IX() + 1;
                    int var267 = mem(var266, 35449) + 1;
                    wMem(var266, var267, 35449);
                    int var268 = var267 & 255;
                    wMem(var266, var268, 35449);
                    int var269 = IX() + 1;
                    int var270 = mem(var269, 35452);
                    A = var270;
                    if (A == 51) {
                      int var271 = IX() + 5;
                      int var272 = mem(var271, 35459);
                      A = var272;
                      if (A == 112) {
                        continue label282;
                      }

                      int var273 = IX();
                      wMem(var273, 32, 35467);
                      int var274 = IX() + 1;
                      wMem(var274, 49, 35471);
                      int var275 = IX() + 5;
                      wMem(var275, 112, 35475);
                    }
                  } else {
                    int var259 = IX() + 1;
                    int var260 = mem(var259, 35481) + 1;
                    wMem(var259, var260, 35481);
                    int var261 = var260 & 255;
                    wMem(var259, var261, 35481);
                    int var262 = IX() + 1;
                    int var263 = mem(var262, 35484);
                    A = var263;
                    if (A == 58) {
                      int var264 = IX() + 1;
                      wMem(var264, 48, 35491);
                      int var265 = IX();
                      wMem(var265, 49, 35495);
                    }
                  }
                }
              }
            }

            BC(65278);
            int var62 = BC();
            int var63 = in(var62);
            A = var63;
            E = A;
            B = 127;
            int var64 = BC();
            int var65 = in(var64);
            A = var65;
            int var66 = A | E;
            A = var66;
            F = A;
            int var67 = A & 1;
            A = var67;
            if (A == 0) {
              continue label282;
            }

            int var68 = mem(34272, 35515);
            A = var68;
            int var69 = A + 1 & 255;
            A = var69;
            F = A;
            wMem(34272, A, 35519);
            if (F != 0) {
              B = 253;
              int var242 = BC();
              int var243 = in(var242);
              A = var243;
              int var244 = A & 31;
              A = var244;
              F = A;
              if (A == 31) {
                break label292;
              }

              DE(0);
            }

            while(true) {
              B = 2;
              int var70 = BC();
              int var71 = in(var70);
              A = var71;
              int var72 = A & 31;
              A = var72;
              F = A;
              if (A != 31) {
                HL(39424);
                DE(23040);
                BC(256);
                ldir();
                int var73 = mem(32990, 35602);
                A = var73;
                break;
              }

              int var239 = E + 1 & 255;
              E = var239;
              if (E == 0) {
                int var240 = D + 1 & 255;
                D = var240;
                if (D == 0) {
                  int var241 = mem(34275, 35553);
                  A = var241;
                  if (A != 10) {
                    $35563(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  }
                }
              }
            }
          }

          int var74 = mem(34257, 35607);
          A = var74;
          if (A == 255) {
            A = 71;

            do {
              HL(22528);
              DE(22529);
              BC(511);
              int var75 = HL();
              wMem(var75, A, 35852);
              ldir();
              E = A;
              int var76 = ~A;
              A = var76;
              F = A;
              int var77 = A & 7;
              A = var77;
              F = A;
              int var78 = A;
              int var79 = rlc(var78);
              A = var79;
              int var80 = A;
              int var81 = rlc(var80);
              A = var81;
              int var82 = A;
              int var83 = rlc(var82);
              A = var83;
              int var84 = A | 7;
              A = var84;
              F = A;
              D = A;
              C = E;
              int var85 = C;
              int var86 = rrc(var85);
              C = var86;
              int var87 = C;
              int var88 = rrc(var87);
              C = var88;
              int var89 = C;
              int var90 = rrc(var89);
              C = var90;
              int var91 = A | 16;
              A = var91;
              F = A;
              int var92 = A ^ A;
              A = var92;
              F = A;

              do {
                int var93 = A ^ 24;
                A = var93;
                F = A;
                B = D;

                do {
                  int var94 = B - 1 & 255;
                  B = var94;
                } while(B != 0);

                int var95 = C - 1 & 255;
                C = var95;
              } while(C != 0);

              A = E;
              int var96 = A - 1 & 255;
              A = var96;
              F = A;
            } while(A != 63);

            HL(34252);
            int var97 = HL();
            int var98 = mem(var97, 35894);
            A = var98;
            int var99 = A | A;
            A = var99;
            if (A == 0) {
              HL(16384);
              DE(16385);
              BC(4095);
              int var100 = HL();
              wMem(var100, 0, 35923);
              ldir();
              int var101 = A ^ A;
              A = var101;
              F = A;
              wMem(34276, A, 35928);
              DE(40256);
              HL(18575);
              C = 0;
              $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
              DE(40032);
              HL(18639);
              C = 0;
              $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);

              do {
                int var102 = mem(34276, 35953);
                A = var102;
                C = A;
                B = 130;
                int var103 = BC();
                int var104 = mem(var103, 35959);
                A = var104;
                int var105 = A | 15;
                A = var105;
                F = A;
                L = A;
                int var106 = BC() + 1 & '\uffff';
                BC(var106);
                int var107 = BC();
                int var108 = mem(var107, 35964);
                A = var108;
                int var109 = A - 32 & 255;
                A = var109;
                F = A;
                H = A;
                DE(40000);
                C = 0;
                $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                int var110 = mem(34276, 35976);
                A = var110;
                int var111 = ~A;
                A = var111;
                F = A;
                E = A;
                int var112 = A ^ A;
                A = var112;
                F = A;
                BC(64);

                do {
                  int var113 = A ^ 24;
                  A = var113;
                  F = A;
                  B = E;

                  do {
                    int var114 = B - 1 & 255;
                    B = var114;
                  } while(B != 0);

                  int var115 = C - 1 & 255;
                  C = var115;
                } while(C != 0);

                HL(22528);
                DE(22529);
                BC(511);
                int var116 = mem(34276, 36004);
                A = var116;
                int var117 = A & 12;
                A = var117;
                F = A;
                int var118 = A;
                int var119 = rlc(var118);
                A = var119;
                int var120 = A | 71;
                A = var120;
                F = A;
                int var121 = HL();
                wMem(var121, A, 36012);
                ldir();
                int var122 = A & 250;
                A = var122;
                F = A;
                int var123 = A | 2;
                A = var123;
                F = A;
                wMem(22991, A, 36019);
                wMem(22992, A, 36022);
                wMem(23023, A, 36025);
                wMem(23024, A, 36028);
                int var124 = mem(34276, 36031);
                A = var124;
                int var125 = A + 4 & 255;
                A = var125;
                F = A;
                wMem(34276, A, 36036);
              } while(A != 196);

              IX(34164);
              C = 4;
              DE(16586);
              $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
              IX(34168);
              C = 4;
              DE(16594);
              $38528(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
              BC(0);
              D = 6;

              while(true) {
                int var126 = B - 1 & 255;
                B = var126;
                if (B == 0) {
                  A = C;
                  int var127 = A & 7;
                  A = var127;
                  F = A;
                  int var128 = A | 64;
                  A = var128;
                  F = A;
                  wMem(22730, A, 36079);
                  int var129 = A + 1 & 255;
                  A = var129;
                  F = A;
                  int var130 = A & 7;
                  A = var130;
                  F = A;
                  int var131 = A | 64;
                  A = var131;
                  F = A;
                  wMem(22731, A, 36087);
                  int var132 = A + 1 & 255;
                  A = var132;
                  F = A;
                  int var133 = A & 7;
                  A = var133;
                  F = A;
                  int var134 = A | 64;
                  A = var134;
                  F = A;
                  wMem(22732, A, 36095);
                  int var135 = A + 1 & 255;
                  A = var135;
                  F = A;
                  int var136 = A & 7;
                  A = var136;
                  F = A;
                  int var137 = A | 64;
                  A = var137;
                  F = A;
                  wMem(22733, A, 36103);
                  int var138 = A + 1 & 255;
                  A = var138;
                  F = A;
                  int var139 = A & 7;
                  A = var139;
                  F = A;
                  int var140 = A | 64;
                  A = var140;
                  F = A;
                  wMem(22738, A, 36111);
                  int var141 = A + 1 & 255;
                  A = var141;
                  F = A;
                  int var142 = A & 7;
                  A = var142;
                  F = A;
                  int var143 = A | 64;
                  A = var143;
                  F = A;
                  wMem(22739, A, 36119);
                  int var144 = A + 1 & 255;
                  A = var144;
                  F = A;
                  int var145 = A & 7;
                  A = var145;
                  F = A;
                  int var146 = A | 64;
                  A = var146;
                  F = A;
                  wMem(22740, A, 36127);
                  int var147 = A + 1 & 255;
                  A = var147;
                  F = A;
                  int var148 = A & 7;
                  A = var148;
                  F = A;
                  int var149 = A | 64;
                  A = var149;
                  F = A;
                  wMem(22741, A, 36135);
                  int var150 = C - 1 & 255;
                  C = var150;
                  if (C == 0) {
                    int var151 = D - 1 & 255;
                    D = var151;
                    if (D == 0) {
                      continue label282;
                    }
                  }
                }
              }
            }

            int var152 = HL();
            int var153 = mem(var152, 35899) - 1 & 255;
            int var154 = HL();
            wMem(var154, var153, 35899);
            HL(34263);
            DE(34255);
            BC(7);
            ldir();
            break;
          }

          B = 191;
          HL(34274);
          int var155 = BC();
          int var156 = in(var155);
          A = var156;
          int var157 = A & 31;
          A = var157;
          F = A;
          if (A != 31) {
            int var234 = HL();
            if ((mem(var234, 35630) & 1) == 0) {
              int var235 = HL();
              int var236 = mem(var235, 35632);
              A = var236;
              int var237 = A ^ 3;
              A = var237;
              F = A;
              int var238 = HL();
              wMem(var238, A, 35635);
            }
          } else {
            int var158 = HL();
            int var159 = mem(var158, 35638) & -2;
            int var160 = HL();
            wMem(var160, var159, 35638);
          }

          int var161 = HL();
          if ((mem(var161, 35642) & 2) == 0) {
            int var209 = A ^ A;
            A = var209;
            F = A;
            wMem(34272, A, 35645);
            int var210 = mem(34273, 35648);
            A = var210;
            int var211 = A + 1 & 255;
            A = var211;
            F = A;
            wMem(34273, A, 35652);
            int var212 = A & 126;
            A = var212;
            F = A;
            int var213 = A;
            int var214 = rrc(var213);
            A = var214;
            E = A;
            D = 0;
            HL(34399);
            int var215 = HL();
            int var216 = DE();
            int var217 = var215 + var216 & '\uffff';
            HL(var217);
            int var218 = mem(34252, 35665);
            A = var218;
            int var219 = A;
            int var220 = rlc(var219);
            A = var220;
            int var221 = A;
            int var222 = rlc(var221);
            A = var222;
            int var223 = A - 28 & 255;
            A = var223;
            F = A;
            int var224 = -A & 255;
            A = var224;
            int var225 = HL();
            int var226 = mem(var225, 35674);
            int var227 = A + var226 & 255;
            A = var227;
            int var228 = HL();
            mem(var228, 35674);
            F = A;
            D = A;
            int var229 = mem(32990, 35676);
            A = var229;
            E = D;
            BC(3);

            while(true) {
              int var230 = E - 1 & 255;
              E = var230;
              if (E == 0) {
                E = D;
                int var233 = A ^ 24;
                A = var233;
              }

              int var231 = B - 1 & 255;
              B = var231;
              if (B == 0) {
                int var232 = C - 1 & 255;
                C = var232;
                if (C == 0) {
                  break;
                }
              }
            }
          }

          BC(61438);
          int var162 = BC();
          int var163 = in(var162);
          A = var163;
          if ((A & 2) == 0) {
            int var199 = A & 16;
            A = var199;
            F = A;
            int var200 = A ^ 16;
            A = var200;
            F = A;
            int var201 = A;
            int var202 = rlc(var201);
            A = var202;
            D = A;
            int var203 = mem(34275, 35712);
            A = var203;
            if (A == 10) {
              BC(63486);
              int var204 = BC();
              int var205 = in(var204);
              A = var205;
              int var206 = ~A;
              A = var206;
              F = A;
              int var207 = A & 31;
              A = var207;
              F = A;
              int var208 = A | D;
              A = var208;
              F = A;
              wMem(33824, A, 35729);
              break;
            }
          }

          int var164 = mem(34275, 35735);
          A = var164;
          if (A != 10) {
            int var165 = mem(33824, 35743);
            A = var165;
            if (A == 28) {
              int var166 = mem(34255, 35751);
              A = var166;
              if (A == 208) {
                int var167 = mem(34275, 35759);
                A = var167;
                int var168 = A;
                int var169 = rlc(var168);
                A = var169;
                E = A;
                D = 0;
                IX(34279);
                int var170 = IX();
                int var171 = DE();
                int var172 = var170 + var171 & '\uffff';
                IX(var172);
                BC(64510);
                int var173 = BC();
                int var174 = in(var173);
                A = var174;
                int var175 = A & 31;
                A = var175;
                F = A;
                int var176 = IX();
                mem(var176, 35779);
                int var177 = IX();
                mem(var177, 35779);
                int var178 = IX();
                int var179 = mem(var178, 35782);
                if (A != var179) {
                  if (A != 31) {
                    int var194 = IX();
                    mem(var194, 35789);
                    int var195 = IX();
                    mem(var195, 35789);
                    int var196 = IX();
                    int var197 = mem(var196, 35792);
                    if (A != var197) {
                      int var198 = A ^ A;
                      A = var198;
                      F = A;
                      wMem(34275, A, 35796);
                    }
                  }
                } else {
                  B = 223;
                  int var180 = BC();
                  int var181 = in(var180);
                  A = var181;
                  int var182 = A & 31;
                  A = var182;
                  F = A;
                  int var183 = IX() + 1;
                  mem(var183, 35808);
                  int var184 = IX() + 1;
                  mem(var184, 35808);
                  int var185 = IX() + 1;
                  int var186 = mem(var185, 35811);
                  if (A != var186) {
                    if (A != 31) {
                      int var189 = IX();
                      mem(var189, 35818);
                      int var190 = IX();
                      mem(var190, 35818);
                      int var191 = IX();
                      int var192 = mem(var191, 35821);
                      if (A != var192) {
                        int var193 = A ^ A;
                        A = var193;
                        F = A;
                        wMem(34275, A, 35825);
                      }
                    }
                  } else {
                    int var187 = mem(34275, 35831);
                    A = var187;
                    int var188 = A + 1 & 255;
                    A = var188;
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

  public void $35211(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(34252, 35211);
    A = var1;
    HL(20640);
    int var2 = A | A;
    A = var2;
    if (A != 0) {
      B = A;

      do {
        C = 0;
        int var3 = HL();
        push(var3);
        int var4 = BC();
        push(var4);
        int var5 = mem(34273, 35224);
        A = var5;
        int var6 = A;
        int var7 = rlc(var6);
        A = var7;
        int var8 = A;
        int var9 = rlc(var8);
        A = var9;
        int var10 = A;
        int var11 = rlc(var10);
        A = var11;
        int var12 = A & 96;
        A = var12;
        F = A;
        E = A;
        D = 157;
        $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        BC();
        int var13 = pop();
        BC(var13);
        HL();
        int var14 = pop();
        HL(var14);
        int var15 = HL() + 1 & '\uffff';
        HL(var15);
        int var16 = HL() + 1 & '\uffff';
        HL(var16);
        int var17 = B - 1 & 255;
        B = var17;
      } while(B != 0);

    }
  }

  public void $35563(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
    } while(A != 91);

  }

  public void $36147(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    $36203(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    IX(24064);
    A = 112;
    wMem(36189, A, 36156);
    $36171(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    IX(24320);
    A = 120;
    wMem(36189, A, 36168);
    $36171(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
  }

  public void $36171(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
        int var8 = B - 1 & 255;
        B = var8;
      } while(B != 0);

      int var9 = IX() + 1 & '\uffff';
      IX(var9);
      int var10 = C + 1 & 255;
      C = var10;
    } while(C != 0);

  }

  public void $36203(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
      $36288(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
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
      $36288(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
      int var17 = HL();
      int var18 = mem(var17, 36224);
      A = var18;
      int var19 = A;
      int var20 = rrc(var19);
      A = var20;
      int var21 = A;
      int var22 = rrc(var21);
      A = var22;
      $36288(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
      int var23 = HL();
      int var24 = mem(var23, 36230);
      A = var24;
      $36288(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
      int var25 = HL() + 1 & '\uffff';
      HL(var25);
      A = L;
      int var26 = A & 128;
      A = var26;
    } while(A == 0);

    int var27 = mem(32985, 36240);
    A = var27;
    int var28 = A | A;
    A = var28;
    if (A != 0) {
      int var44 = mem16(32983, 36246);
      HL(var44);
      B = A;
      int var45 = mem(32973, 36250);
      A = var45;

      do {
        int var46 = HL();
        wMem(var46, A, 36253);
        int var47 = HL() + 1 & '\uffff';
        HL(var47);
        int var48 = B - 1 & 255;
        B = var48;
      } while(B != 0);
    }

    int var29 = mem(32989, 36257);
    A = var29;
    int var30 = A | A;
    A = var30;
    if (A != 0) {
      int var31 = mem16(32987, 36262);
      HL(var31);
      int var32 = mem(32986, 36265);
      A = var32;
      int var33 = A & 1;
      A = var33;
      F = A;
      int var34 = A;
      int var35 = rlc(var34);
      A = var35;
      int var36 = A + 223 & 255;
      A = var36;
      F = A;
      E = A;
      D = 255;
      int var37 = mem(32989, 36276);
      A = var37;
      B = A;
      int var38 = mem(32964, 36280);
      A = var38;

      do {
        int var39 = HL();
        wMem(var39, A, 36283);
        int var40 = HL();
        int var41 = DE();
        int var42 = var40 + var41 & '\uffff';
        HL(var42);
        int var43 = B - 1 & 255;
        B = var43;
      } while(B != 0);

    }
  }

  public void $36288(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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

  public void $36307(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(34262, 36307);
    A = var1;
    int var2 = A - 1 & 255;
    A = var2;
    F = A;
    if ((A & 128) != 0) {
      int var3 = mem(34257, 36316);
      A = var3;
      if (A == 1) {
        int var52 = mem(34261, 36323);
        A = var52;
        int var53 = A & 254;
        A = var53;
        F = A;
        int var54 = A - 8 & 255;
        A = var54;
        F = A;
        HL(34255);
        int var55 = HL();
        int var56 = mem(var55, 36333);
        int var57 = A + var56 & 255;
        A = var57;
        int var58 = HL();
        mem(var58, 36333);
        F = A;
        int var59 = HL();
        wMem(var59, A, 36334);
        if (A >= 240) {
          return;
        }

        $36508(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        int var60 = mem(32946, 36343);
        A = var60;
        int var61 = HL();
        mem(var61, 36346);
        int var62 = HL();
        mem(var62, 36346);
        int var63 = HL();
        int var64 = mem(var63, 36347);
        if (A == var64) {
          return;
        }

        int var65 = HL() + 1 & '\uffff';
        HL(var65);
        int var66 = HL();
        mem(var66, 36351);
        int var67 = HL();
        mem(var67, 36351);
        int var68 = HL();
        int var69 = mem(var68, 36352);
        if (A == var69) {
          return;
        }

        int var70 = mem(34261, 36355);
        A = var70;
        int var71 = A + 1 & 255;
        A = var71;
        F = A;
        wMem(34261, A, 36359);
        int var72 = A - 8 & 255;
        A = var72;
        if (A < 0) {
          int var85 = -A & 255;
          A = var85;
        }

        int var73 = A + 1 & 255;
        A = var73;
        F = A;
        int var74 = A;
        int var75 = rlc(var74);
        A = var75;
        int var76 = A;
        int var77 = rlc(var76);
        A = var77;
        int var78 = A;
        int var79 = rlc(var78);
        A = var79;
        D = A;
        C = 32;
        int var80 = mem(32990, 36376);
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

        int var84 = mem(34261, 36389);
        A = var84;
        if (A == 18 || A != 16 && A != 13) {
          return;
        }
      }

      int var4 = mem(34255, 36406);
      A = var4;
      int var5 = A & 14;
      A = var5;
      if (A == 0) {
        int var27 = mem16(34259, 36413);
        HL(var27);
        DE(64);
        int var28 = HL();
        int var29 = DE();
        int var30 = var28 + var29 & '\uffff';
        HL(var30);
        if ((H & 2) != 0) {
          return;
        }

        int var31 = mem(32955, 36425);
        A = var31;
        int var32 = HL();
        mem(var32, 36428);
        int var33 = HL();
        mem(var33, 36428);
        int var34 = HL();
        int var35 = mem(var34, 36429);
        if (A != var35) {
          int var36 = HL() + 1 & '\uffff';
          HL(var36);
          int var37 = mem(32955, 36432);
          A = var37;
          int var38 = HL();
          mem(var38, 36435);
          int var39 = HL();
          mem(var39, 36435);
          int var40 = HL();
          int var41 = mem(var40, 36436);
          if (A != var41) {
            int var42 = mem(32928, 36438);
            A = var42;
            int var43 = HL();
            int var44 = mem(var43, 36441);
            int var45 = HL();
            mem(var45, 36441);
            int var46 = A - var44;
            F = var46;
            int var47 = HL() - 1 & '\uffff';
            HL(var47);
            if (F != 0) {
              return;
            }

            int var48 = HL();
            mem(var48, 36446);
            int var49 = HL();
            mem(var49, 36446);
            int var50 = HL();
            int var51 = mem(var50, 36447);
            if (A != var51) {
              return;
            }
          }
        }
      }

      int var6 = mem(34257, 36450);
      A = var6;
      if (A != 1) {
        HL(34256);
        int var7 = HL();
        int var8 = mem(var7, 36461) & -3;
        int var9 = HL();
        wMem(var9, var8, 36461);
        int var10 = mem(34257, 36463);
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

          wMem(34257, A, 36477);
          int var13 = A;
          int var14 = rlc(var13);
          A = var14;
          int var15 = A;
          int var16 = rlc(var15);
          A = var16;
          int var17 = A;
          int var18 = rlc(var17);
          A = var18;
          int var19 = A;
          int var20 = rlc(var19);
          A = var20;
          D = A;
          C = 32;
          int var21 = mem(32990, 36487);
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

          int var25 = mem(34255, 36500);
          A = var25;
          int var26 = A + 8 & 255;
          A = var26;
          F = A;
          wMem(34255, A, 36505);
          $36508(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        }
      }
    }

  }

  public void $36508(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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

  public void $37056(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    IX(33024);

    while(true) {
      int var1 = IX();
      int var2 = mem(var1, 37060);
      A = var2;
      if (A == 255) {
        return;
      }

      int var3 = A & 3;
      A = var3;
      if (A != 0) {
        if (A != 1) {
          if (A != 2) {
            int var70 = IX();
            if ((mem(var70, 37085) & 128) != 0) {
              int var88 = IX() + 1;
              int var89 = mem(var88, 37087);
              A = var89;
              if ((A & 128) != 0) {
                int var92 = A - 2 & 255;
                A = var92;
                F = A;
                if (A < 148) {
                  int var93 = A - 2 & 255;
                  A = var93;
                  F = A;
                  if (A == 128) {
                    int var94 = A ^ A;
                    A = var94;
                  }
                }
              } else {
                int var90 = A + 2 & 255;
                A = var90;
                F = A;
                if (A < 18) {
                  int var91 = A + 2 & 255;
                  A = var91;
                }
              }
            } else {
              int var71 = IX() + 1;
              int var72 = mem(var71, 37119);
              A = var72;
              if ((A & 128) == 0) {
                int var85 = A - 2 & 255;
                A = var85;
                F = A;
                if (A < 20) {
                  int var86 = A - 2 & 255;
                  A = var86;
                  F = A;
                  int var87 = A | A;
                  A = var87;
                  if (A == 0) {
                    A = 128;
                  }
                }
              } else {
                int var73 = A + 2 & 255;
                A = var73;
                F = A;
                if (A < 146) {
                  int var84 = A + 2 & 255;
                  A = var84;
                  F = A;
                }
              }
            }

            int var74 = IX() + 1;
            wMem(var74, A, 37149);
            int var75 = A & 127;
            A = var75;
            F = A;
            int var76 = IX() + 7;
            mem(var76, 37154);
            int var77 = IX() + 7;
            mem(var77, 37154);
            int var78 = IX() + 7;
            int var79 = mem(var78, 37157);
            if (A == var79) {
              int var80 = IX();
              int var81 = mem(var80, 37160);
              A = var81;
              int var82 = A ^ 128;
              A = var82;
              F = A;
              int var83 = IX();
              wMem(var83, A, 37165);
            }
          } else {
            label81: {
              int var39 = IX();
              int var40 = mem(var39, 37247);
              A = var40;
              int var41 = A ^ 8;
              A = var41;
              F = A;
              int var42 = IX();
              wMem(var42, A, 37252);
              int var43 = A & 24;
              A = var43;
              if (A != 0) {
                int var66 = IX();
                int var67 = mem(var66, 37259);
                A = var67;
                int var68 = A + 32 & 255;
                A = var68;
                F = A;
                int var69 = IX();
                wMem(var69, A, 37264);
              }

              int var44 = IX() + 3;
              int var45 = mem(var44, 37267);
              A = var45;
              int var46 = IX() + 4;
              int var47 = mem(var46, 37270);
              int var48 = A + var47 & 255;
              A = var48;
              int var49 = IX() + 4;
              mem(var49, 37270);
              F = A;
              int var50 = IX() + 3;
              wMem(var50, A, 37273);
              int var51 = IX() + 7;
              mem(var51, 37276);
              int var52 = IX() + 7;
              mem(var52, 37276);
              int var53 = IX() + 7;
              int var54 = mem(var53, 37279);
              if (A < var54) {
                int var59 = IX() + 6;
                mem(var59, 37281);
                int var60 = IX() + 6;
                mem(var60, 37281);
                int var61 = IX() + 6;
                int var62 = mem(var61, 37284);
                if (A != var62 && F >= 0) {
                  break label81;
                }

                int var63 = IX() + 6;
                int var64 = mem(var63, 37288);
                A = var64;
                int var65 = IX() + 3;
                wMem(var65, A, 37291);
              }

              int var55 = IX() + 4;
              int var56 = mem(var55, 37294);
              A = var56;
              int var57 = -A & 255;
              A = var57;
              int var58 = IX() + 4;
              wMem(var58, A, 37299);
            }
          }
        } else {
          int var7 = IX();
          if ((mem(var7, 37175) & 128) == 0) {
            int var24 = IX();
            int var25 = mem(var24, 37177);
            A = var25;
            int var26 = A - 32 & 255;
            A = var26;
            F = A;
            int var27 = A & 127;
            A = var27;
            F = A;
            int var28 = IX();
            wMem(var28, A, 37184);
            if (A >= 96) {
              int var29 = IX() + 2;
              int var30 = mem(var29, 37191);
              A = var30;
              int var31 = A & 31;
              A = var31;
              F = A;
              int var32 = IX() + 6;
              mem(var32, 37196);
              int var33 = IX() + 6;
              mem(var33, 37196);
              int var34 = IX() + 6;
              int var35 = mem(var34, 37199);
              if (A != var35) {
                int var37 = IX() + 2;
                int var38 = mem(var37, 37201) - 1 & 255;
                wMem(var37, var38, 37201);
              } else {
                int var36 = IX();
                wMem(var36, 129, 37206);
              }
            }
          } else {
            int var8 = IX();
            int var9 = mem(var8, 37212);
            A = var9;
            int var10 = A + 32 & 255;
            A = var10;
            F = A;
            int var11 = A | 128;
            A = var11;
            F = A;
            int var12 = IX();
            wMem(var12, A, 37219);
            if (A < 160) {
              int var13 = IX() + 2;
              int var14 = mem(var13, 37226);
              A = var14;
              int var15 = A & 31;
              A = var15;
              F = A;
              int var16 = IX() + 7;
              mem(var16, 37231);
              int var17 = IX() + 7;
              mem(var17, 37231);
              int var18 = IX() + 7;
              int var19 = mem(var18, 37234);
              if (A != var19) {
                int var21 = IX() + 2;
                int var22 = mem(var21, 37236) + 1;
                wMem(var21, var22, 37236);
                int var23 = var22 & 255;
                wMem(var21, var23, 37236);
              } else {
                int var20 = IX();
                wMem(var20, 97, 37241);
              }
            }
          }
        }
      }

      DE(8);
      int var4 = IX();
      int var5 = DE();
      int var6 = var4 + var5 & '\uffff';
      IX(var6);
    }
  }

  public void $37310(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    IX(33024);

    while(true) {
      int var1 = IX();
      int var2 = mem(var1, 37314);
      A = var2;
      if (A == 255) {
        return;
      }

      int var3 = A & 7;
      A = var3;
      if (A != 0) {
        if (A != 3) {
          if (A != 4) {
            int var186 = IX() + 3;
            int var187 = mem(var186, 37334);
            E = var187;
            D = 130;
            int var188 = DE();
            int var189 = mem(var188, 37339);
            A = var189;
            L = A;
            int var190 = IX() + 2;
            int var191 = mem(var190, 37341);
            A = var191;
            int var192 = A & 31;
            A = var192;
            F = A;
            int var193 = A + L & 255;
            A = var193;
            F = A;
            L = A;
            A = E;
            int var194 = A;
            int var195 = rlc(var194);
            A = var195;
            int var196 = A & 1;
            A = var196;
            F = A;
            int var197 = A | 92;
            A = var197;
            F = A;
            H = A;
            DE(31);
            int var198 = IX() + 1;
            int var199 = mem(var198, 37358);
            A = var199;
            int var200 = A & 15;
            A = var200;
            F = A;
            int var201 = A + 56 & 255;
            A = var201;
            F = A;
            int var202 = A & 71;
            A = var202;
            F = A;
            C = A;
            int var203 = HL();
            int var204 = mem(var203, 37368);
            A = var204;
            int var205 = A & 56;
            A = var205;
            F = A;
            int var206 = A ^ C;
            A = var206;
            F = A;
            C = A;
            int var207 = HL();
            wMem(var207, C, 37373);
            int var208 = HL() + 1 & '\uffff';
            HL(var208);
            int var209 = HL();
            wMem(var209, C, 37375);
            int var210 = HL();
            int var211 = DE();
            int var212 = var210 + var211 & '\uffff';
            HL(var212);
            int var213 = HL();
            wMem(var213, C, 37377);
            int var214 = HL() + 1 & '\uffff';
            HL(var214);
            int var215 = HL();
            wMem(var215, C, 37379);
            int var216 = IX() + 3;
            int var217 = mem(var216, 37380);
            A = var217;
            int var218 = A & 14;
            A = var218;
            if (A != 0) {
              int var244 = HL();
              int var245 = DE();
              int var246 = var244 + var245 & '\uffff';
              HL(var246);
              int var247 = HL();
              wMem(var247, C, 37388);
              int var248 = HL() + 1 & '\uffff';
              HL(var248);
              int var249 = HL();
              wMem(var249, C, 37390);
            }

            C = 1;
            int var219 = IX() + 1;
            int var220 = mem(var219, 37393);
            A = var220;
            int var221 = IX();
            int var222 = mem(var221, 37396);
            int var223 = A & var222;
            A = var223;
            int var224 = IX();
            mem(var224, 37396);
            F = A;
            int var225 = IX() + 2;
            int var226 = mem(var225, 37399);
            int var227 = A | var226;
            A = var227;
            int var228 = IX() + 2;
            mem(var228, 37399);
            F = A;
            int var229 = A & 224;
            A = var229;
            F = A;
            E = A;
            int var230 = IX() + 5;
            int var231 = mem(var230, 37405);
            D = var231;
            H = 130;
            int var232 = IX() + 3;
            int var233 = mem(var232, 37410);
            L = var233;
            int var234 = IX() + 2;
            int var235 = mem(var234, 37413);
            A = var235;
            int var236 = A & 31;
            A = var236;
            F = A;
            int var237 = HL();
            int var238 = mem(var237, 37418);
            int var239 = A | var238;
            A = var239;
            int var240 = HL();
            mem(var240, 37418);
            F = A;
            int var241 = HL() + 1 & '\uffff';
            HL(var241);
            int var242 = HL();
            int var243 = mem(var242, 37420);
            H = var243;
            L = A;
            $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
            if (F != 0) {
              incPops();
              return;
            }

            incPops();
          } else {
            int var132 = IX();
            if ((mem(var132, 37435) & 128) == 0) {
              int var184 = IX() + 4;
              int var185 = mem(var184, 37437) - 1 & 255;
              wMem(var184, var185, 37437);
              C = 44;
            } else {
              int var133 = IX() + 4;
              int var134 = mem(var133, 37444) + 1;
              wMem(var133, var134, 37444);
              int var135 = var134 & 255;
              wMem(var133, var135, 37444);
              C = 244;
            }

            int var136 = IX() + 4;
            int var137 = mem(var136, 37449);
            A = var137;
            if (A != C) {
              int var138 = A & 224;
              A = var138;
              if (A == 0) {
                int var139 = IX() + 2;
                int var140 = mem(var139, 37479);
                E = var140;
                D = 130;
                int var141 = DE();
                int var142 = mem(var141, 37484);
                A = var142;
                int var143 = IX() + 4;
                int var144 = mem(var143, 37485);
                int var145 = A + var144 & 255;
                A = var145;
                int var146 = IX() + 4;
                mem(var146, 37485);
                F = A;
                L = A;
                A = E;
                int var147 = A & 128;
                A = var147;
                F = A;
                int var148 = A;
                int var149 = rlc(var148);
                A = var149;
                int var150 = A | 92;
                A = var150;
                F = A;
                H = A;
                int var151 = IX() + 5;
                wMem(var151, 0, 37496);
                int var152 = HL();
                int var153 = mem(var152, 37500);
                A = var153;
                int var154 = A & 7;
                A = var154;
                F = A;
                if (A == 7) {
                  int var178 = IX() + 5;
                  int var179 = mem(var178, 37507) - 1 & 255;
                  wMem(var178, var179, 37507);
                }

                int var155 = HL();
                int var156 = mem(var155, 37510);
                A = var156;
                int var157 = A | 7;
                A = var157;
                F = A;
                int var158 = HL();
                wMem(var158, A, 37513);
                int var159 = DE() + 1 & '\uffff';
                DE(var159);
                int var160 = DE();
                int var161 = mem(var160, 37515);
                A = var161;
                H = A;
                int var162 = H - 1 & 255;
                H = var162;
                F = H;
                int var163 = IX() + 6;
                int var164 = mem(var163, 37518);
                A = var164;
                int var165 = HL();
                wMem(var165, A, 37521);
                int var166 = H + 1 & 255;
                H = var166;
                F = H;
                int var167 = HL();
                int var168 = mem(var167, 37523);
                A = var168;
                int var169 = IX() + 5;
                int var170 = mem(var169, 37524);
                int var171 = A & var170;
                A = var171;
                int var172 = IX() + 5;
                mem(var172, 37524);
                if (A != 0) {
                  return;
                }

                int var173 = HL();
                wMem(var173, 255, 37530);
                int var174 = H + 1 & 255;
                H = var174;
                F = H;
                int var175 = IX() + 6;
                int var176 = mem(var175, 37533);
                A = var176;
                int var177 = HL();
                wMem(var177, A, 37536);
              }
            } else {
              BC(640);
              int var180 = mem(32990, 37458);
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
          IY(33280);
          int var7 = IX() + 9;
          wMem(var7, 0, 37544);
          int var8 = IX() + 2;
          int var9 = mem(var8, 37548);
          A = var9;
          int var10 = IX() + 3;
          wMem(var10, A, 37551);
          int var11 = IX() + 5;
          wMem(var11, 128, 37554);

          while(true) {
            label115: {
              int var12 = IY();
              int var13 = mem(var12, 37558);
              A = var13;
              int var14 = IX() + 3;
              int var15 = mem(var14, 37561);
              int var16 = A + var15 & 255;
              A = var16;
              int var17 = IX() + 3;
              mem(var17, 37561);
              F = A;
              L = A;
              int var18 = IY() + 1;
              int var19 = mem(var18, 37565);
              H = var19;
              int var20 = mem(34262, 37568);
              A = var20;
              int var21 = A | A;
              A = var21;
              if (A == 0) {
                int var122 = IX() + 5;
                int var123 = mem(var122, 37574);
                A = var123;
                int var124 = HL();
                int var125 = mem(var124, 37577);
                int var126 = A & var125;
                A = var126;
                int var127 = HL();
                mem(var127, 37577);
                if (A == 0) {
                  break label115;
                }

                int var128 = IX() + 9;
                int var129 = mem(var128, 37580);
                A = var129;
                wMem(34262, A, 37583);
                int var130 = IX() + 11;
                int var131 = mem(var130, 37586) | 1;
                wMem(var130, var131, 37586);
              }

              int var22 = IX() + 9;
              mem(var22, 37590);
              int var23 = IX() + 9;
              mem(var23, 37590);
              int var24 = IX() + 9;
              int var25 = mem(var24, 37593);
              if (A == var25) {
                int var112 = IX() + 11;
                if ((mem(var112, 37599) & 1) != 0) {
                  int var113 = IX() + 3;
                  int var114 = mem(var113, 37601);
                  B = var114;
                  int var115 = IX() + 5;
                  int var116 = mem(var115, 37604);
                  A = var116;
                  C = 1;
                  if (A >= 4) {
                    C = 0;
                    if (A >= 16) {
                      int var121 = B - 1 & 255;
                      B = var121;
                      F = B;
                      C = 3;
                      if (A >= 64) {
                        C = 2;
                      }
                    }
                  }

                  int var117 = BC();
                  wMem16(34258, var117, 37628);
                  A = IYL;
                  int var118 = A - 16 & 255;
                  A = var118;
                  F = A;
                  wMem(34255, A, 37636);
                  int var119 = HL();
                  push(var119);
                  $36508(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
                  HL();
                  int var120 = pop();
                  HL(var120);
                }
              }
            }

            int var26 = IX() + 5;
            int var27 = mem(var26, 37646);
            A = var27;
            int var28 = HL();
            int var29 = mem(var28, 37649);
            int var30 = A | var29;
            A = var30;
            int var31 = HL();
            mem(var31, 37649);
            F = A;
            int var32 = HL();
            wMem(var32, A, 37650);
            int var33 = IX() + 9;
            int var34 = mem(var33, 37651);
            A = var34;
            int var35 = IX() + 1;
            int var36 = mem(var35, 37654);
            int var37 = A + var36 & 255;
            A = var37;
            int var38 = IX() + 1;
            mem(var38, 37654);
            F = A;
            L = A;
            int var39 = L | 128;
            L = var39;
            H = 131;
            int var40 = HL();
            int var41 = mem(var40, 37662);
            E = var41;
            D = 0;
            int var42 = IY();
            int var43 = DE();
            int var44 = var42 + var43 & '\uffff';
            IY(var44);
            int var45 = L & -129;
            L = var45;
            int var46 = HL();
            int var47 = mem(var46, 37669);
            A = var47;
            int var48 = A | A;
            A = var48;
            if (A != 0) {
              B = A;
              int var96 = IX() + 1;
              if ((mem(var96, 37678) & 128) != 0) {
                do {
                  int var105 = IX() + 5;
                  int var106 = mem(var105, 37680);
                  int var107 = rlc(var106);
                  wMem(var105, var107, 37680);
                  int var108 = IX() + 5;
                  if ((mem(var108, 37688) & 1) != 0) {
                    int var110 = IX() + 3;
                    int var111 = mem(var110, 37690) - 1 & 255;
                    wMem(var110, var111, 37690);
                  }

                  int var109 = B - 1 & 255;
                  B = var109;
                } while(B != 0);
              } else {
                do {
                  int var97 = IX() + 5;
                  int var98 = mem(var97, 37697);
                  int var99 = rrc(var98);
                  wMem(var97, var99, 37697);
                  int var100 = IX() + 5;
                  if ((mem(var100, 37705) & 128) != 0) {
                    int var102 = IX() + 3;
                    int var103 = mem(var102, 37707) + 1;
                    wMem(var102, var103, 37707);
                    int var104 = var103 & 255;
                    wMem(var102, var104, 37707);
                  }

                  int var101 = B - 1 & 255;
                  B = var101;
                } while(B != 0);
              }
            }

            int var49 = IX() + 9;
            int var50 = mem(var49, 37712);
            A = var50;
            int var51 = IX() + 4;
            mem(var51, 37715);
            int var52 = IX() + 4;
            mem(var52, 37715);
            int var53 = IX() + 4;
            int var54 = mem(var53, 37718);
            if (A == var54) {
              int var55 = mem(34262, 37726);
              A = var55;
              if ((A & 128) != 0) {
                int var90 = A + 1 & 255;
                A = var90;
                F = A;
                wMem(34262, A, 37734);
                int var91 = IX() + 11;
                int var92 = mem(var91, 37737) & -2;
                wMem(var91, var92, 37737);
              } else {
                int var56 = IX() + 11;
                if ((mem(var56, 37747) & 1) != 0) {
                  int var57 = mem(34256, 37749);
                  A = var57;
                  if ((A & 2) != 0) {
                    int var58 = A;
                    int var59 = rrc(var58);
                    A = var59;
                    int var60 = IX();
                    int var61 = mem(var60, 37757);
                    int var62 = A ^ var61;
                    A = var62;
                    int var63 = IX();
                    mem(var63, 37757);
                    F = A;
                    int var64 = A;
                    int var65 = rlc(var64);
                    A = var65;
                    int var66 = A;
                    int var67 = rlc(var66);
                    A = var67;
                    int var68 = A & 2;
                    A = var68;
                    F = A;
                    int var69 = A - 1 & 255;
                    A = var69;
                    F = A;
                    HL(34262);
                    int var70 = HL();
                    int var71 = mem(var70, 37768);
                    int var72 = A + var71 & 255;
                    A = var72;
                    int var73 = HL();
                    mem(var73, 37768);
                    F = A;
                    int var74 = HL();
                    wMem(var74, A, 37769);
                    int var75 = mem(33003, 37770);
                    A = var75;
                    C = A;
                    int var76 = mem(33824, 37774);
                    A = var76;
                    if (A == C) {
                      int var87 = HL();
                      int var88 = mem(var87, 37780);
                      A = var88;
                      if (A < 12) {
                        int var89 = HL();
                        wMem(var89, 12, 37785);
                      }
                    }

                    int var77 = HL();
                    int var78 = mem(var77, 37787);
                    A = var78;
                    int var79 = IX() + 4;
                    mem(var79, 37788);
                    int var80 = IX() + 4;
                    mem(var80, 37788);
                    int var81 = IX() + 4;
                    int var82 = mem(var81, 37791);
                    if (A >= var82 && F != 0) {
                      int var83 = HL();
                      wMem(var83, 240, 37795);
                      int var84 = mem(34255, 37797);
                      A = var84;
                      int var85 = A & 248;
                      A = var85;
                      F = A;
                      wMem(34255, A, 37802);
                      int var86 = A ^ A;
                      A = var86;
                      F = A;
                      wMem(34257, A, 37806);
                    }
                  }
                }
              }
              break;
            }

            int var93 = IX() + 9;
            int var94 = mem(var93, 37720) + 1;
            wMem(var93, var94, 37720);
            int var95 = var94 & 255;
            wMem(var93, var95, 37720);
          }
        }
      }

      DE(8);
      int var4 = IX();
      int var5 = DE();
      int var6 = var4 + var5 & '\uffff';
      IX(var6);
    }
  }

  public void $37841(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
      if (A == C) {
        int var8 = HL();
        int var9 = mem(var8, 37858);
        A = var9;
        int var10 = A;
        int var11 = rlc(var10);
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
        int var15 = HL();
        int var16 = mem(var15, 37866);
        E = var16;
        int var17 = H - 1 & 255;
        H = var17;
        F = H;
        int var18 = DE();
        int var19 = mem(var18, 37868);
        A = var19;
        int var20 = A & 7;
        A = var20;
        F = A;
        if (A != 7) {
          int var21 = mem(34251, 37936);
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
          int var25 = DE();
          int var26 = mem(var25, 37945);
          A = var26;
          int var27 = A & 248;
          A = var27;
          F = A;
          int var28 = A | C;
          A = var28;
          F = A;
          int var29 = DE();
          wMem(var29, A, 37949);
          int var30 = HL();
          int var31 = mem(var30, 37950);
          A = var31;
          int var32 = A;
          int var33 = rlc(var32);
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
          int var40 = A & 8;
          A = var40;
          F = A;
          int var41 = A + 96 & 255;
          A = var41;
          F = A;
          D = A;
          int var42 = HL();
          push(var42);
          HL(32993);
          B = 8;
          $38555(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
          HL();
          int var43 = pop();
          HL(var43);
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
            if (A != 58) {
              int var49 = mem(32990, 37897);
              A = var49;
              C = 128;

              do {
                int var50 = A ^ 24;
                A = var50;
                F = A;
                E = A;
                A = 144;
                int var51 = A - C & 255;
                A = var51;
                F = A;
                B = A;
                A = E;

                do {
                  int var52 = B - 1 & 255;
                  B = var52;
                } while(B != 0);

                int var53 = C - 1 & 255;
                C = var53;
                F = C;
                int var54 = C - 1 & 255;
                C = var54;
              } while(C != 0);

              int var55 = mem(34270, 37918);
              A = var55;
              int var56 = A + 1 & 255;
              A = var56;
              F = A;
              wMem(34270, A, 37922);
              if (F == 0) {
                A = 1;
                wMem(34271, A, 37929);
              }

              int var57 = HL();
              int var58 = mem(var57, 37932) & -65;
              int var59 = HL();
              wMem(var59, var58, 37932);
              break;
            }

            int var60 = IX() + 2;
            wMem(var60, 48, 37889);
            int var61 = IX() - 1 & '\uffff';
            IX(var61);
          }
        }
      }

      int var7 = L + 1 & 255;
      L = var7;
    } while(L != 0);

  }

  public void $37974(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    B = 16;

    do {
      int var1 = C & 1;
      F = var1;
      int var2 = DE();
      int var3 = mem(var2, 37978);
      A = var3;
      if (F != 0) {
        int var31 = HL();
        int var32 = mem(var31, 37981);
        int var33 = A & var32;
        A = var33;
        int var34 = HL();
        mem(var34, 37981);
        if (A != 0) {
          return;
        }

        int var35 = DE();
        int var36 = mem(var35, 37983);
        A = var36;
        int var37 = HL();
        int var38 = mem(var37, 37984);
        int var39 = A | var38;
        A = var39;
        int var40 = HL();
        mem(var40, 37984);
        F = A;
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
        if (A != 0) {
          return;
        }

        int var25 = DE();
        int var26 = mem(var25, 37995);
        A = var26;
        int var27 = HL();
        int var28 = mem(var27, 37996);
        int var29 = A | var28;
        A = var29;
        int var30 = HL();
        mem(var30, 37996);
        F = A;
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

  public void $38064(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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

  public void $38137(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
    if (A != 0) {
      B = A;
      int var12 = mem(32982, 38157);
      A = var12;
      int var13 = A | A;
      A = var13;
      if (A == 0) {
        int var33 = HL();
        int var34 = mem(var33, 38163);
        A = var34;
        int var35 = A;
        int var36 = rlc(var35);
        A = var36;
        int var37 = A;
        int var38 = rlc(var37);
        A = var38;
        int var39 = H + 1 & 255;
        H = var39;
        F = H;
        int var40 = H + 1 & 255;
        H = var40;
        F = H;
        int var41 = HL();
        int var42 = mem(var41, 38170);
        C = var42;
        int var43 = C;
        int var44 = rrc(var43);
        C = var44;
        int var45 = C;
        int var46 = rrc(var45);
        C = var46;
      } else {
        int var14 = HL();
        int var15 = mem(var14, 38182);
        A = var15;
        int var16 = A;
        int var17 = rrc(var16);
        A = var17;
        int var18 = A;
        int var19 = rrc(var18);
        A = var19;
        int var20 = H + 1 & 255;
        H = var20;
        F = H;
        int var21 = H + 1 & 255;
        H = var21;
        F = H;
        int var22 = HL();
        int var23 = mem(var22, 38189);
        C = var23;
        int var24 = C;
        int var25 = rlc(var24);
        C = var25;
        int var26 = C;
        int var27 = rlc(var26);
        C = var27;
      }

      do {
        int var28 = DE();
        wMem(var28, A, 38175);
        int var29 = HL();
        wMem(var29, C, 38176);
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

  public void $38196(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(33824, 38196);
    A = var1;
    if (A == 35) {
      int var15 = mem(34271, 38203);
      A = var15;
      int var16 = A | A;
      A = var16;
      if (A == 0) {
        int var19 = mem(34251, 38209);
        A = var19;
        int var20 = A & 2;
        A = var20;
        F = A;
        int var21 = A;
        int var22 = rrc(var21);
        A = var22;
        int var23 = A;
        int var24 = rrc(var23);
        A = var24;
        int var25 = A;
        int var26 = rrc(var25);
        A = var26;
        int var27 = A;
        int var28 = rrc(var27);
        A = var28;
        int var29 = A | 128;
        A = var29;
        F = A;
        E = A;
        int var30 = mem(34255, 38221);
        A = var30;
        if (A != 208) {
          E = 192;
          if (A < 192) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        if (F != 0) {
          incPops();
        } else {
          incPops();
          HL(17733);
          int var31 = HL();
          wMem16(23918, var31, 38252);
          HL(1799);
          int var32 = HL();
          wMem16(23950, var32, 38258);
        }
      } else {
        int var17 = mem(34259, 38262);
        A = var17;
        int var18 = A & 31;
        A = var18;
        F = A;
        if (A < 6) {
          A = 2;
          wMem(34271, A, 38272);
        }
      }
    } else {
      int var2 = mem(33824, 38298);
      A = var2;
      if (A == 33) {
        int var3 = mem(34251, 38304);
        A = var3;
        int var4 = A & 1;
        A = var4;
        F = A;
        int var5 = A;
        int var6 = rrc(var5);
        A = var6;
        int var7 = A;
        int var8 = rrc(var7);
        A = var8;
        int var9 = A;
        int var10 = rrc(var9);
        A = var10;
        E = A;
        int var11 = mem(34271, 38313);
        A = var11;
        if (A == 3) {
          int var14 = E | 64;
          E = var14;
        }

        D = 166;
        IX(33488);
        BC(4124);
        $38504(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
        HL(1799);
        int var12 = HL();
        wMem16(23996, var12, 38337);
        int var13 = HL();
        wMem16(24028, var13, 38340);
      }
    }
  }

  public void $38276(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(33824, 38276);
    A = var1;
    if (A == 33) {
      int var2 = mem(34259, 38282);
      A = var2;
      if (A == 188) {
        int var3 = A ^ A;
        A = var3;
        F = A;
        wMem(34251, A, 38289);
        A = 3;
        wMem(34271, A, 38294);
      }
    }
  }

  public void $38344(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
    mem(var9, 38363);
    int var10 = HL();
    mem(var10, 38363);
    int var11 = HL();
    int var12 = mem(var11, 38364);
    if (A == var12) {
      int var44 = mem(34257, 38366);
      A = var44;
      int var45 = A | A;
      A = var45;
      if (A == 0) {
        int var46 = mem(34258, 38372);
        A = var46;
        int var47 = A & 3;
        A = var47;
        F = A;
        int var48 = A;
        int var49 = rlc(var48);
        A = var49;
        int var50 = A;
        int var51 = rlc(var50);
        A = var51;
        B = A;
        int var52 = mem(32986, 38380);
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

    int var13 = mem16(34259, 38392);
    HL(var13);
    DE(31);
    C = 15;
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    decPops();
    int var14 = HL() + 1 & '\uffff';
    HL(var14);
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    int var15 = HL();
    int var16 = DE();
    int var17 = var15 + var16 & '\uffff';
    HL(var17);
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    int var18 = HL() + 1 & '\uffff';
    HL(var18);
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    int var19 = mem(34255, 38415);
    A = var19;
    int var20 = A + B & 255;
    A = var20;
    F = A;
    C = A;
    int var21 = HL();
    int var22 = DE();
    int var23 = var21 + var22 & '\uffff';
    HL(var23);
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    int var24 = HL() + 1 & '\uffff';
    HL(var24);
    $38430(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
    int var25 = mem(34255, 38455);
    A = var25;
    int var26 = A + B & 255;
    A = var26;
    F = A;
    IXH = 130;
    IXL = A;
    int var27 = mem(34256, 38464);
    A = var27;
    int var28 = A & 1;
    A = var28;
    F = A;
    int var29 = A;
    int var30 = rrc(var29);
    A = var30;
    E = A;
    int var31 = mem(34258, 38471);
    A = var31;
    int var32 = A & 3;
    A = var32;
    F = A;
    int var33 = A;
    int var34 = rrc(var33);
    A = var34;
    int var35 = A;
    int var36 = rrc(var35);
    A = var36;
    int var37 = A;
    int var38 = rrc(var37);
    A = var38;
    int var39 = A | E;
    A = var39;
    F = A;
    E = A;
    D = 157;
    int var40 = mem(33824, 38483);
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
    int var41 = mem(34259, 38498);
    A = var41;
    int var42 = A & 31;
    A = var42;
    F = A;
    C = A;
    $38504(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
  }

  public void $38430(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(32928, 38430);
    A = var1;
    int var2 = HL();
    mem(var2, 38433);
    int var3 = HL();
    mem(var3, 38433);
    int var4 = HL();
    int var5 = mem(var4, 38434);
    if (A == var5) {
      A = C;
      int var11 = A & 15;
      A = var11;
      if (A != 0) {
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
    mem(var7, 38450);
    int var8 = HL();
    mem(var8, 38450);
    int var9 = HL();
    int var10 = mem(var9, 38451);
    if (A == var10) {
      incPops();
    } else {
      incPops();
    }
  }

  public void $38504(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    do {
      int var1 = IX();
      int var2 = mem(var1, 38504);
      A = var2;
      int var3 = IX() + 1;
      int var4 = mem(var3, 38507);
      H = var4;
      int var5 = A | C;
      A = var5;
      F = A;
      L = A;
      int var6 = DE();
      int var7 = mem(var6, 38512);
      A = var7;
      int var8 = HL();
      int var9 = mem(var8, 38513);
      int var10 = A | var9;
      A = var10;
      int var11 = HL();
      mem(var11, 38513);
      F = A;
      int var12 = HL();
      wMem(var12, A, 38514);
      int var13 = HL() + 1 & '\uffff';
      HL(var13);
      int var14 = DE() + 1 & '\uffff';
      DE(var14);
      int var15 = DE();
      int var16 = mem(var15, 38517);
      A = var16;
      int var17 = HL();
      int var18 = mem(var17, 38518);
      int var19 = A | var18;
      A = var19;
      int var20 = HL();
      mem(var20, 38518);
      F = A;
      int var21 = HL();
      wMem(var21, A, 38519);
      int var22 = IX() + 1 & '\uffff';
      IX(var22);
      int var23 = IX() + 1 & '\uffff';
      IX(var23);
      int var24 = DE() + 1 & '\uffff';
      DE(var24);
      int var25 = B - 1 & 255;
      B = var25;
    } while(B != 0);

  }

  public void $38528(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    do {
      int var1 = IX();
      int var2 = mem(var1, 38528);
      A = var2;
      $38545(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
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
    } while(C != 0);

  }

  public void $38545(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
    $38555(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
  }

  public void $38555(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
      int var6 = B - 1 & 255;
      B = var6;
    } while(B != 0);

  }

  public void $38562(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    while(true) {
      int var1 = HL();
      int var2 = mem(var1, 38562);
      A = var2;
      if (A == 255) {
        return;
      }

      BC(100);
      int var3 = A ^ A;
      A = var3;
      F = A;
      int var4 = HL();
      int var5 = mem(var4, 38570);
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
            int var11 = rlc(var10);
            E = var11;
          }

          int var8 = C - 1 & 255;
          C = var8;
          if (C == 0) {
            $38601(A,  F,  B,  C,  D,  E,  H,  L,  IXH,  IXL,  IYH,  IYL);
            if (F != 0) {
              return;
            }

            int var9 = HL() + 1 & '\uffff';
            HL(var9);
            break;
          }
        }
      }
    }
  }

  public void $38601(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
    int var1 = mem(34254, 38601);
    A = var1;
    int var2 = A | A;
    A = var2;
    if (A != 0) {
      int var6 = in(31);
      A = var6;
      if ((A & 16) != 0) {
        return;
      }
    }

    BC(45054);
    int var3 = BC();
    int var4 = in(var3);
    A = var4;
    int var5 = A & 1;
    A = var5;
    F = A;
  }

  public void $38622(int A, int F, int B, int C, int D, int E, int H, int L, int IXH, int IXL, int IYH, int IYL) {
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
