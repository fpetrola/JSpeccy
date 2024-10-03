package com.fpetrola.z80.minizx;

public class JetSetWilly extends MiniZX {
  public void $34762() {
    label295:
    while(true) {
      A ^= A;
      mem[34254] = A & 255;
      mem[34273] = A & 255;
      mem[34253] = A & 255;
      mem[34257] = A & 255;
      mem[34251] = A & 255;
      mem[34272] = A & 255;
      mem[34271] = A & 255;
      A = 7;
      mem[34252] = A & 255;
      A = 208;
      mem[34255] = A & 255;
      A = 33;
      mem[33824] = A & 255;
      HL(23988);
      wMem16(34259, HL());
      HL(34172);
      int var1 = HL();
      mem[var1] = 48;
      HL(HL() + 1 & 65535);
      var1 = HL();
      mem[var1] = 48;
      HL(HL() + 1 & 65535);
      var1 = HL();
      mem[var1] = 48;
      H = 164;
      A = mem[41983];
      L = A;
      mem[34270] = A & 255;

      do {
        var1 = HL();
        int var10000 = mem[var1] | 64;
        var1 = HL();
        int var2 = var10000 & 255;
        mem[var1] = var2;
        L = L + 1 & 255;
      } while(L != 0);

      HL(34274);
      var1 = HL();
      int var60 = mem[var1] | 1;
      var1 = HL();
      int var57 = var60 & 255;
      mem[var1] = var57;

      label287:
      while(true) {
        HL(16384);
        DE(16385);
        BC(6143);
        var1 = HL();
        mem[var1] = 0;
        ldir();
        HL(38912);
        BC(768);
        ldir();
        HL(23136);
        DE(23137);
        BC(31);
        var1 = HL();
        mem[var1] = 70;
        ldir();
        IX(33876);
        DE(20576);
        C = 32;
        $38528();
        DE(22528);

        do {
          var1 = DE();
          A = mem[var1];
          A |= A;
          if (A << 1 != 0 && A != 211 && A != 9 && A != 45 && A != 36) {
            C = 0;
            if (A != 8 && A != 41) {
              if (A != 44) {
                if (A != 5) {
                  C = 16;
                }
              } else {
                A = 37;
                var1 = DE();
                mem[var1] = A & 255;
              }
            }

            A = E;
            A &= 1;
            A = rlc(A);
            A = rlc(A);
            A = rlc(A);
            A |= C;
            C = A;
            B = 0;
            HL(33841);
            HL(HL() + BC() & 65535);
            push(DE());
            F = D & 1;
            D = 64;
            if (F != 0) {
              D = 72;
            }

            B = 8;
            $38555();
            DE(pop());
          }

          DE(DE() + 1 & 65535);
          A = D;
        } while(A != 90);

        BC(31);
        A ^= A;

        do {
          E = in(BC());
          A |= E;
          B = B - 1 & 255;
        } while(B != 0);

        A &= 32;
        if (A << 1 == 0) {
          A = 1;
          mem[34254] = A & 255;
        }

        HL(34299);
        $38562();
        if (F != 0) {
          break;
        }

        A ^= A;
        mem[34276] = A & 255;

        while(true) {
          $35563();
          HL(23136);
          DE(23137);
          BC(31);
          var1 = HL();
          mem[var1] = 79;
          ldir();
          A = mem[34276];
          IX(33876);
          E = A;
          D = 0;
          IX(IX() + DE() & 65535);
          DE(20576);
          C = 32;
          $38528();
          A = mem[34276];
          A &= 31;
          A = A + 50 & 255;
          $38622();
          BC(45054);
          A = in(BC());
          A &= 1;
          if (A != 1) {
            break label287;
          }

          A = mem[34276];
          A = A + 1 & 255;
          F = A - 224;
          mem[34276] = A & 255;
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
        A = mem[33824];
        A |= 192;
        H = A;
        L = 0;
        DE(32768);
        BC(256);
        ldir();
        IX(33008);
        DE(33024);
        A = 8;

        do {
          var1 = IX();
          L = mem[var1];
          L &= -129;
          H = 20;
          HL(HL() * 2 & 65535);
          HL(HL() * 2 & 65535);
          HL(HL() * 2 & 65535);
          BC(2);
          ldir();
          var1 = IX() + 1;
          C = mem[var1];
          var1 = HL();
          mem[var1] = C & 255;
          BC(6);
          ldir();
          IX(IX() + 1 & 65535);
          IX(IX() + 1 & 65535);
          A = A - 1 & 255;
        } while(A != 0);

        HL(34255);
        DE(34263);
        BC(7);
        ldir();
        $36147();
        HL(20480);
        DE(20481);
        BC(2047);
        var1 = HL();
        mem[var1] = 0;
        ldir();
        IX(32896);
        C = 32;
        DE(20480);
        $38528();
        IX(34132);
        DE(20576);
        C = 32;
        $38528();
        A = mem[32990];
        C = 254;
        A ^= A;
        mem[34262] = A & 255;

        while(true) {
          label306: {
            label226: {
              label302: {
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
                A = mem[34271];
                if (A != 3) {
                  $36307();
                  if (isNextPC(37048)) {
                    break label302;
                  }

                  if (isNextPC(38043) || isNextPC(38061) || isNextPC(38134) || isNextPC(38095)) {
                    break;
                  }
                }

                A = mem[34255];
                if (A >= 225) {
                  $38064();
                  if (isNextPC(38095)) {
                    break;
                  }
                }

                A = mem[34271];
                if (A != 3) {
                  $38344();
                  if (isNextPC(37048)) {
                    break label302;
                  }
                }

                A = mem[34271];
                if (A == 2) {
                  $38276();
                }

                F = A - 2;
                $38196();
                if (!isNextPC(37048)) {
                  $37310();
                  if (!isNextPC(37048)) {
                    $38137();
                    $37841();
                    break label226;
                  }
                }
              }


            }

            HL(24576);
            DE(16384);
            BC(4096);
            ldir();
            A = mem[34271];
            A &= 2;
            A = rrc(A);
            HL(34258);
            var1 = HL();
            A |= mem[var1];
            var1 = HL();
            mem[var1] = A & 255;
            A = mem[34253];
            A |= A;
            if (A << 1 != 0) {
              A = A - 1 & 255;
              mem[34253] = A & 255;
              A = rlc(A);
              A = rlc(A);
              A = rlc(A);
              A &= 56;
              HL(23552);
              DE(23553);
              BC(511);
              var1 = HL();
              mem[var1] = A & 255;
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
            A = mem[34251];
            A = A + 1 & 255;
            F = A;
            mem[34251] = A & 255;
            if (F == 0) {
              IX(34175);
              var1 = IX() + 4;
              mem[var1] = mem[var1] + 1 & 255 & 255;
              var1 = IX() + 4;
              A = mem[var1];
              if (A == 58) {
                var1 = IX() + 4;
                mem[var1] = 48;
                var1 = IX() + 3;
                mem[var1] = mem[var1] + 1 & 255 & 255;
                var1 = IX() + 3;
                A = mem[var1];
                if (A == 54) {
                  var1 = IX() + 3;
                  mem[var1] = 48;
                  var1 = IX();
                  A = mem[var1];
                  if (A == 49) {
                    var1 = IX() + 1;
                    mem[var1] = mem[var1] + 1 & 255 & 255;
                    var1 = IX() + 1;
                    A = mem[var1];
                    if (A == 51) {
                      var1 = IX() + 5;
                      A = mem[var1];
                      if (A == 112) {
                        continue label295;
                      }

                      var1 = IX();
                      mem[var1] = 32;
                      var1 = IX() + 1;
                      mem[var1] = 49;
                      var1 = IX() + 5;
                      mem[var1] = 112;
                    }
                  } else {
                    var1 = IX() + 1;
                    mem[var1] = mem[var1] + 1 & 255 & 255;
                    var1 = IX() + 1;
                    A = mem[var1];
                    if (A == 58) {
                      var1 = IX() + 1;
                      mem[var1] = 48;
                      var1 = IX();
                      mem[var1] = 49;
                    }
                  }
                }
              }
            }

            BC(65278);
            A = in(BC());
            E = A;
            B = 127;
            A = in(BC());
            A |= E;
            A &= 1;
            if (A << 1 == 0) {
              continue label295;
            }

            A = mem[34272];
            A = A + 1 & 255;
            F = A;
            mem[34272] = A & 255;
            if (F != 0) {
              B = 253;
              A = in(BC());
              A &= 31;
              if (A == 31) {
                break label306;
              }

              DE(0);
            }

            while(true) {
              B = 2;
              A = in(BC());
              A &= 31;
              if (A != 31) {
                HL(39424);
                DE(23040);
                BC(256);
                ldir();
                A = mem[32990];
                break;
              }

              E = E + 1 & 255;
              if (E == 0) {
                D = D + 1 & 255;
                if (D == 0) {
                  A = mem[34275];
                  if (A != 10) {
                    $35563();
                  }

                  F = A - 10;
                }
              }
            }
          }

          A = mem[34257];
          if (A == 255) {
            A = 71;

            do {
              HL(22528);
              DE(22529);
              BC(511);
              var1 = HL();
              mem[var1] = A & 255;
              ldir();
              E = A;
              A = ~A;
              A &= 7;
              A = rlc(A);
              A = rlc(A);
              A = rlc(A);
              A |= 7;
              D = A;
              C = E;
              C = rrc(C);
              C = rrc(C);
              C = rrc(C);
              A |= 16;
              A ^= A;

              do {
                A ^= 24;
                B = D;

                do {
                  B = B - 1 & 255;
                } while(B != 0);

                C = C - 1 & 255;
              } while(C != 0);

              A = E;
              A = A - 1 & 255;
            } while(A != 63);

            HL(34252);
            var1 = HL();
            A = mem[var1];
            A |= A;
            if (A << 1 == 0) {
              HL(16384);
              DE(16385);
              BC(4095);
              var1 = HL();
              mem[var1] = 0;
              ldir();
              A ^= A;
              mem[34276] = A & 255;
              DE(40256);
              HL(18575);
              C = 0;
              $37974();
              DE(40032);
              HL(18639);
              C = 0;
              $37974();

              do {
                A = mem[34276];
                C = A;
                B = 130;
                var1 = BC();
                A = mem[var1];
                A |= 15;
                L = A;
                BC(BC() + 1 & 65535);
                var1 = BC();
                A = mem[var1];
                A = A - 32 & 255;
                H = A;
                DE(40000);
                C = 0;
                $37974();
                A = mem[34276];
                A = ~A;
                E = A;
                A ^= A;
                BC(64);

                do {
                  A ^= 24;
                  B = E;

                  do {
                    B = B - 1 & 255;
                  } while(B != 0);

                  C = C - 1 & 255;
                } while(C != 0);

                HL(22528);
                DE(22529);
                BC(511);
                A = mem[34276];
                A &= 12;
                A = rlc(A);
                A |= 71;
                var1 = HL();
                mem[var1] = A & 255;
                ldir();
                A &= 250;
                A |= 2;
                mem[22991] = A & 255;
                mem[22992] = A & 255;
                mem[23023] = A & 255;
                mem[23024] = A & 255;
                A = mem[34276];
                A = A + 4 & 255;
                mem[34276] = A & 255;
              } while(A != 196);

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
                B = B - 1 & 255;
                if (B == 0) {
                  A = C;
                  A &= 7;
                  A |= 64;
                  mem[22730] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22731] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22732] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22733] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22738] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22739] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22740] = A & 255;
                  A = A + 1 & 255;
                  A &= 7;
                  A |= 64;
                  mem[22741] = A & 255;
                  C = C - 1 & 255;
                  if (C == 0) {
                    D = D - 1 & 255;
                    if (D == 0) {
                      continue label295;
                    }
                  }
                }
              }
            }

            var1 = HL();
            var60 = mem[var1] - 1 & 255;
            var1 = HL();
            var57 = var60 & 255;
            mem[var1] = var57;
            HL(34263);
            DE(34255);
            BC(7);
            ldir();
            break;
          }

          B = 191;
          HL(34274);
          A = in(BC());
          A &= 31;
          if (A != 31) {
            var1 = HL();
            if ((mem[var1] & 1) == 0) {
              var1 = HL();
              A = mem[var1];
              A ^= 3;
              var1 = HL();
              mem[var1] = A & 255;
            }
          } else {
            var1 = HL();
            var60 = mem[var1] & -2;
            var1 = HL();
            var57 = var60 & 255;
            mem[var1] = var57;
          }

          var1 = HL();
          if ((mem[var1] & 2) == 0) {
            A ^= A;
            mem[34272] = A & 255;
            A = mem[34273];
            A = A + 1 & 255;
            mem[34273] = A & 255;
            A &= 126;
            A = rrc(A);
            E = A;
            D = 0;
            HL(34399);
            HL(HL() + DE() & 65535);
            A = mem[34252];
            A = rlc(A);
            A = rlc(A);
            A = A - 28 & 255;
            A = -A & 255;
            var1 = HL();
            A = mem[var1] + A & 255;
            D = A;
            A = mem[32990];
            E = D;
            BC(3);

            while(true) {
              E = E - 1 & 255;
              if (E == 0) {
                E = D;
                A ^= 24;
              }

              B = B - 1 & 255;
              if (B == 0) {
                C = C - 1 & 255;
                if (C == 0) {
                  break;
                }
              }
            }
          }

          BC(61438);
          A = in(BC());
          if ((A & 2) == 0) {
            A &= 16;
            A ^= 16;
            A = rlc(A);
            D = A;
            A = mem[34275];
            if (A == 10) {
              BC(63486);
              A = in(BC());
              A = ~A;
              A &= 31;
              A |= D;
              mem[33824] = A & 255;
              break;
            }
          }

          A = mem[34275];
          if (A != 10) {
            A = mem[33824];
            if (A == 28) {
              A = mem[34255];
              if (A == 208) {
                A = mem[34275];
                A = rlc(A);
                E = A;
                D = 0;
                IX(34279);
                IX(IX() + DE() & 65535);
                BC(64510);
                A = in(BC());
                A &= 31;
                var1 = IX();
                if (A != mem[var1]) {
                  if (A != 31) {
                    var1 = IX();
                    if (A != mem[var1]) {
                      A ^= A;
                      mem[34275] = A & 255;
                    }
                  }
                } else {
                  B = 223;
                  A = in(BC());
                  A &= 31;
                  var1 = IX() + 1;
                  if (A != mem[var1]) {
                    if (A != 31) {
                      var1 = IX();
                      if (A != mem[var1]) {
                        A ^= A;
                        mem[34275] = A & 255;
                      }
                    }
                  } else {
                    A = mem[34275];
                    A = A + 1 & 255;
                    mem[34275] = A & 255;
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
    A = mem[34252];
    HL(20640);
    A |= A;
    if (A << 1 != 0) {
      B = A;

      do {
        C = 0;
        push(HL());
        push(BC());
        A = mem[34273];
        A = rlc(A);
        A = rlc(A);
        A = rlc(A);
        A &= 96;
        E = A;
        D = 157;
        $37974();
        BC(pop());
        HL(pop());
        HL(HL() + 1 & 65535);
        HL(HL() + 1 & 65535);
        B = B - 1 & 255;
      } while(B != 0);

    }
  }

  public void $35563() {
    HL(22528);
    int var1 = HL();
    A = mem[var1];
    A &= 7;

    do {
      var1 = HL();
      A = mem[var1];
      A = A + 3 & 255;
      A &= 7;
      D = A;
      var1 = HL();
      A = mem[var1];
      A = A + 24 & 255;
      A &= 184;
      A |= D;
      var1 = HL();
      mem[var1] = A & 255;
      HL(HL() + 1 & 65535);
      A = H;
    } while(A != 91);

    F = A - 91;
  }

  public void $36147() {
    $36203();
    IX(24064);
    A = 112;
    mem[36189] = A & 255;
    $36171();
    IX(24320);
    A = 120;
    mem[36189] = A & 255;
    $36171();
  }

  public void $36171() {
    C = 0;

    do {
      E = C;
      int var1 = IX();
      A = mem[var1];
      HL(32928);
      BC(54);
      cpir();
      C = E;
      B = 8;
      D = mem[36189];

      do {
        var1 = HL();
        A = mem[var1];
        var1 = DE();
        mem[var1] = A & 255;
        HL(HL() + 1 & 65535);
        D = D + 1 & 255;
        B = B - 1 & 255;
      } while(B != 0);

      IX(IX() + 1 & 65535);
      C = C + 1 & 255;
    } while(C != 0);

  }

  public void $36203() {
    HL(32768);
    IX(24064);

    do {
      int var1 = HL();
      A = mem[var1];
      A = rlc(A);
      A = rlc(A);
      $36288();
      var1 = HL();
      A = mem[var1];
      A = rrc(A);
      A = rrc(A);
      A = rrc(A);
      A = rrc(A);
      $36288();
      var1 = HL();
      A = mem[var1];
      A = rrc(A);
      A = rrc(A);
      $36288();
      var1 = HL();
      A = mem[var1];
      $36288();
      HL(HL() + 1 & 65535);
      A = L;
      A &= 128;
    } while(A << 1 == 0);

    A = mem[32985];
    A |= A;
    if (A << 1 != 0) {
      HL(mem16(32983));
      B = A;
      A = mem[32973];

      do {
        int var5 = HL();
        mem[var5] = A & 255;
        HL(HL() + 1 & 65535);
        B = B - 1 & 255;
      } while(B != 0);
    }

    A = mem[32989];
    A |= A;
    if (A << 1 != 0) {
      HL(mem16(32987));
      A = mem[32986];
      A &= 1;
      A = rlc(A);
      A = A + 223 & 255;
      E = A;
      D = 255;
      A = mem[32989];
      B = A;
      A = mem[32964];

      do {
        int var6 = HL();
        mem[var6] = A & 255;
        HL(HL() + DE() & 65535);
        B = B - 1 & 255;
      } while(B != 0);

    }
  }

  public void $36288() {
    A &= 3;
    C = A;
    A = rlc(A);
    A = rlc(A);
    A = rlc(A);
    A = A + C & 255;
    A = A + 160 & 255;
    E = A;
    D = 128;
    int var1 = DE();
    A = mem[var1];
    var1 = IX();
    mem[var1] = A & 255;
    IX(IX() + 1 & 65535);
  }

  public void $36307() {
    label216: {
      label213: {
        label228: {
          A = mem[34262];
          A = A - 1 & 255;
          if ((A & 128) != 0) {
            A = mem[34257];
            if (A == 1) {
              A = mem[34261];
              A &= 254;
              A = A - 8 & 255;
              HL(34255);
              int var1 = HL();
              A = mem[var1] + A & 255;
              var1 = HL();
              mem[var1] = A & 255;
              if (A >= 240) {
                return;
              }

              F = A - 240;
              $36508();
              A = mem[32946];
              var1 = HL();
              if (A == mem[var1]) {
                break label216;
              }

              HL(HL() + 1 & 65535);
              var1 = HL();
              if (A == mem[var1]) {
                break label216;
              }

              A = mem[34261];
              A = A + 1 & 255;
              mem[34261] = A & 255;
              int var10000 = A - 8;
              var1 = A - 8 & 255;
              A = var1;
              if (var10000 < 0) {
                A = -A & 255;
              }

              A = A + 1 & 255;
              A = rlc(A);
              A = rlc(A);
              A = rlc(A);
              D = A;
              C = 32;
              A = mem[32990];

              do {
                A ^= 24;
                B = D;

                do {
                  B = B - 1 & 255;
                } while(B != 0);

                C = C - 1 & 255;
              } while(C != 0);

              A = mem[34261];
              if (A == 18) {
                A = 6;
                mem[34257] = A & 255;
                return;
              }

              if (A != 16 && A != 13) {
                break label213;
              }
            }

            A = mem[34255];
            A &= 14;
            if (A << 1 != 0) {
              break label228;
            }

            HL(mem16(34259));
            DE(64);
            HL(HL() + DE() & 65535);
            if ((H & 2) != 0) {
              A = mem[33004];
              mem[33824] = A & 255;
              A ^= A;
              mem[34255] = A & 255;
              A = mem[34257];
              if (A < 11) {
                A = 2;
                mem[34257] = A & 255;
              }

              A = mem[34259];
              A &= 31;
              mem[34259] = A & 255;
              A = 92;
              mem[34260] = A & 255;
              nextAddress = 38134;
              return;
            }

            A = mem[32955];
            int var7 = HL();
            if (A == mem[var7]) {
              break label228;
            }

            HL(HL() + 1 & 65535);
            A = mem[32955];
            var7 = HL();
            if (A == mem[var7]) {
              break label228;
            }

            A = mem[32928];
            var7 = HL();
            F = A - mem[var7];
            HL(HL() - 1 & 65535);
            if (F == 0) {
              var7 = HL();
              if (A == mem[var7]) {
                break label228;
              }
            }
          }

          E = 255;
          A = mem[34262];
          A = A - 1 & 255;
          if ((A & 128) != 0) {
            label227: {
              A = mem[34257];
              if (A >= 12) {
                nextAddress = 37048;
                return;
              }

              A ^= A;
              mem[34257] = A & 255;
              A = mem[32973];
              int var11 = HL();
              if (A != mem[var11]) {
                HL(HL() + 1 & 65535);
                var11 = HL();
                if (A != mem[var11]) {
                  break label227;
                }
              }

              A = mem[32982];
              A = A - 3 & 255;
              E = A;
            }
          }

          BC(57342);
          A = in(BC());
          A &= 31;
          A |= 32;
          A &= E;
          E = A;
          A = mem[34271];
          A &= 2;
          A = rrc(A);
          A ^= E;
          E = A;
          BC(64510);
          A = in(BC());
          A &= 31;
          A = rlc(A);
          A |= 1;
          A &= E;
          E = A;
          B = 231;
          A = in(BC());
          A = rrc(A);
          A |= 247;
          A &= E;
          E = A;
          B = 239;
          A = in(BC());
          A |= 251;
          A &= E;
          E = A;
          A = in(BC());
          A = rrc(A);
          A |= 251;
          A &= E;
          E = A;
          A = mem[34254];
          A |= A;
          if (A << 1 != 0) {
            BC(31);
            A = in(BC());
            A &= 3;
            A = ~A;
            A &= E;
            E = A;
          }

          C = 0;
          A = E;
          A &= 42;
          if (A != 42) {
            C = 4;
            A ^= A;
            mem[34272] = A & 255;
          }

          A = E;
          A &= 21;
          if (A != 21) {
            C |= 8;
            A ^= A;
            mem[34272] = A & 255;
          }

          A = mem[34256];
          A = A + C & 255;
          C = A;
          B = 0;
          HL(33825);
          HL(HL() + BC() & 65535);
          int var13 = HL();
          A = mem[var13];
          mem[34256] = A & 255;
          BC(32510);
          A = in(BC());
          A &= 31;
          if (A == 31) {
            B = 239;
            A = in(BC());
            if ((A & 1) != 0) {
              A = mem[34254];
              A |= A;
              if (A << 1 == 0) {
                break label213;
              }

              BC(31);
              A = in(BC());
              if ((A & 16) == 0) {
                break label213;
              }
            }
          }

          A = mem[34271];
          if ((A & 2) == 0) {
            A ^= A;
            mem[34261] = A & 255;
            mem[34272] = A & 255;
            A = A + 1 & 255;
            mem[34257] = A & 255;
            A = mem[34262];
            A = A - 1 & 255;
            if ((A & 128) == 0) {
              A = 240;
              mem[34262] = A & 255;
              A = mem[34255];
              A &= 240;
              F = A << 1;
              mem[34255] = A & 255;
              HL(34256);
              var13 = HL();
              int var29 = mem[var13] | 2;
              var13 = HL();
              int var2 = var29 & 255;
              mem[var13] = var2;
              return;
            }
          }
          break label213;
        }

        A = mem[34257];
        if (A != 1) {
          HL(34256);
          int var16 = HL();
          int var30 = mem[var16] & -3;
          var16 = HL();
          int var27 = var30 & 255;
          mem[var16] = var27;
          A = mem[34257];
          A |= A;
          if (A << 1 == 0) {
            A = 2;
            mem[34257] = A & 255;
            return;
          }

          A = A + 1 & 255;
          if (A == 16) {
            A = 12;
          }

      //    mem[34257] = A & 255;
          A = rlc(A);
          A = rlc(A);
          A = rlc(A);
          A = rlc(A);
          D = A;
          C = 32;
          A = mem[32990];

          do {
            A ^= 24;
            B = D;

            do {
              B = B - 1 & 255;
            } while(B != 0);

            C = C - 1 & 255;
          } while(C != 0);

          A = mem[34255];
          A = A + 8 & 255;
          mem[34255] = A & 255;
          $36508();
          return;
        }
      }

      A = mem[34256];
      A &= 2;
      if (A << 1 == 0) {
        return;
      }

      A = mem[34262];
      A = A - 1 & 255;
      if ((A & 128) == 0) {
        return;
      }

      A = mem[34256];
      A &= 1;
      if (A << 1 != 0) {
        A = mem[34258];
        A |= A;
        if (A << 1 != 0) {
          A = A - 1 & 255;
          F = A;
          mem[34258] = A & 255;
          return;
        }

        A = mem[34257];
        BC(0);
        if (A == 0) {
          HL(mem16(34259));
          BC(0);
          A = mem[32986];
          A = A - 1 & 255;
          A |= 161;
          A ^= 224;
          E = A;
          D = 0;
          HL(HL() + DE() & 65535);
          A = mem[32964];
          int var22 = HL();
          if (A == mem[var22]) {
            BC(32);
            A = mem[32986];
            A |= A;
            if (A << 1 == 0) {
              BC(65504);
            }
          }
        }

        HL(mem16(34259));
        A = L;
        A &= 31;
        if (A << 1 != 0) {
          HL(HL() + BC() & 65535);
          HL(HL() - 1 & 65535);
          DE(32);
          HL(HL() + DE() & 65535);
          A = mem[32946];
          int var23 = HL();
          if (A == mem[var23]) {
            return;
          }

          A = mem[34255];
          C = C >> 1 | C & 128;
          A = A + C & 255;
          B = A;
          A &= 15;
          if (A << 1 != 0) {
            A = mem[32946];
            HL(HL() + DE() & 65535);
            var23 = HL();
            if (A == mem[var23]) {
              return;
            }

            A |= A;
            HL(HL() - DE() & 65535);
          }

          A |= A;
          HL(HL() - DE() & 65535);
          wMem16(34259, HL());
          A = B;
          mem[34255] = A & 255;
          A = 3;
          mem[34258] = A & 255;
          return;
        }

        A = mem[33001];
        mem[33824] = A & 255;
        A = mem[34259];
        A |= 31;
        A &= 254;
        mem[34259] = A & 255;
        nextAddress = 38043;
        return;
      }

      A = mem[34258];
      if (A != 3) {
        A = A + 1 & 255;
        F = A;
        mem[34258] = A & 255;
        return;
      }

      A = mem[34257];
      BC(0);
      A |= A;
      if (A << 1 == 0) {
        HL(mem16(34259));
        A = mem[32986];
        A = A - 1 & 255;
        A |= 157;
        A ^= 191;
        E = A;
        D = 0;
        HL(HL() + DE() & 65535);
        A = mem[32964];
        int var18 = HL();
        if (A == mem[var18]) {
          BC(32);
          A = mem[32986];
          A |= A;
          if (A << 1 != 0) {
            BC(65504);
          }
        }
      }

      HL(mem16(34259));
      HL(HL() + BC() & 65535);
      HL(HL() + 1 & 65535);
      HL(HL() + 1 & 65535);
      A = L;
      A &= 31;
      if (A << 1 != 0) {
        DE(32);
        A = mem[32946];
        HL(HL() + DE() & 65535);
        int var19 = HL();
        if (A == mem[var19]) {
          return;
        }

        A = mem[34255];
        C = C >> 1 | C & 128;
        A = A + C & 255;
        B = A;
        A &= 15;
        if (A << 1 != 0) {
          A = mem[32946];
          HL(HL() + DE() & 65535);
          var19 = HL();
          if (A == mem[var19]) {
            return;
          }

          A |= A;
          HL(HL() - DE() & 65535);
        }

        A = mem[32946];
        A |= A;
        HL(HL() - DE() & 65535);
        var19 = HL();
        if (A == mem[var19]) {
          return;
        }

        HL(HL() - 1 & 65535);
        wMem16(34259, HL());
        A ^= A;
        F = A << 1;
        mem[34258] = A & 255;
        A = B;
        mem[34255] = A & 255;
        return;
      }

      A = mem[33002];
      mem[33824] = A & 255;
      A = mem[34259];
      A &= 224;
      mem[34259] = A & 255;
      nextAddress = 38061;
      return;
    }

    A = mem[34255];
    A = A + 16 & 255;
    A &= 240;
    mem[34255] = A & 255;
    $36508();
    A = 2;
    mem[34257] = A & 255;
    HL(34256);
    int var25 = HL();
    int var31 = mem[var25] & -3;
    var25 = HL();
    int var28 = var31 & 255;
    mem[var25] = var28;
  }

  public void $36508() {
    A &= 240;
    L = A;
    A ^= A;
    F = A << 1;
    L = rl(L);
    A = A + 92 + (carry() & 255);
    H = A;
    A = mem[34259];
    A &= 31;
    A |= L;
    F = A << 1;
    L = A;
    wMem16(34259, HL());
  }

  public void $37056() {
    IX(33024);

    while(true) {
      int var1 = IX();
      A = mem[var1];
      if (A == 255) {
        return;
      }

      A &= 3;
      if (A << 1 != 0) {
        if (A != 1) {
          if (A != 2) {
            var1 = IX();
            if ((mem[var1] & 128) != 0) {
              var1 = IX() + 1;
              A = mem[var1];
              if ((A & 128) != 0) {
                A = A - 2 & 255;
                if (A < 148) {
                  A = A - 2 & 255;
                  if (A == 128) {
                    A ^= A;
                  }
                }
              } else {
                A = A + 2 & 255;
                if (A < 18) {
                  A = A + 2 & 255;
                }
              }
            } else {
              var1 = IX() + 1;
              A = mem[var1];
              if ((A & 128) == 0) {
                A = A - 2 & 255;
                if (A < 20) {
                  A = A - 2 & 255;
                  A |= A;
                  if (A << 1 == 0) {
                    A = 128;
                  }
                }
              } else {
                A = A + 2 & 255;
                if (A < 146) {
                  A = A + 2 & 255;
                }
              }
            }

            var1 = IX() + 1;
            mem[var1] = A & 255;
            A &= 127;
            var1 = IX() + 7;
            if (A == mem[var1]) {
              var1 = IX();
              A = mem[var1];
              A ^= 128;
              var1 = IX();
              mem[var1] = A & 255;
            }
          } else {
            label81: {
              var1 = IX();
              A = mem[var1];
              A ^= 8;
              var1 = IX();
              mem[var1] = A & 255;
              A &= 24;
              if (A << 1 != 0) {
                var1 = IX();
                A = mem[var1];
                A = A + 32 & 255;
                var1 = IX();
                mem[var1] = A & 255;
              }

              var1 = IX() + 3;
              A = mem[var1];
              var1 = IX() + 4;
              A = mem[var1] + A & 255;
              var1 = IX() + 3;
              mem[var1] = A & 255;
              var1 = IX() + 7;
              if (A < mem[var1]) {
                var1 = IX() + 6;
                var1 = mem[var1];
                if (A != var1 && A >= var1) {
                  break label81;
                }

                var1 = IX() + 6;
                A = mem[var1];
                var1 = IX() + 3;
                mem[var1] = A & 255;
              }

              var1 = IX() + 4;
              A = mem[var1];
              A = -A & 255;
              var1 = IX() + 4;
              mem[var1] = A & 255;
            }
          }
        } else {
          var1 = IX();
          if ((mem[var1] & 128) == 0) {
            var1 = IX();
            A = mem[var1];
            A = A - 32 & 255;
            A &= 127;
            var1 = IX();
            mem[var1] = A & 255;
            if (A >= 96) {
              var1 = IX() + 2;
              A = mem[var1];
              A &= 31;
              var1 = IX() + 6;
              if (A != mem[var1]) {
                var1 = IX() + 2;
                mem[var1] = mem[var1] - 1 & 255 & 255;
              } else {
                var1 = IX();
                mem[var1] = 129;
              }
            }
          } else {
            var1 = IX();
            A = mem[var1];
            A = A + 32 & 255;
            A |= 128;
            var1 = IX();
            mem[var1] = A & 255;
            if (A < 160) {
              var1 = IX() + 2;
              A = mem[var1];
              A &= 31;
              var1 = IX() + 7;
              if (A != mem[var1]) {
                var1 = IX() + 2;
                mem[var1] = mem[var1] + 1 & 255 & 255;
              } else {
                var1 = IX();
                mem[var1] = 97;
              }
            }
          }
        }
      }

      DE(8);
      IX(IX() + DE() & 65535);
    }
  }

  public void $37310() {
    IX(33024);

    while(true) {
      int var1 = IX();
      A = mem[var1];
      if (A == 255) {
        return;
      }

      A &= 7;
      if (A << 1 != 0) {
        if (A != 3) {
          if (A != 4) {
            var1 = IX() + 3;
            E = mem[var1];
            D = 130;
            var1 = DE();
            A = mem[var1];
            L = A;
            var1 = IX() + 2;
            A = mem[var1];
            A &= 31;
            A = A + L & 255;
            L = A;
            A = E;
            A = rlc(A);
            A &= 1;
            A |= 92;
            H = A;
            DE(31);
            var1 = IX() + 1;
            A = mem[var1];
            A &= 15;
            A = A + 56 & 255;
            A &= 71;
            C = A;
            var1 = HL();
            A = mem[var1];
            A &= 56;
            A ^= C;
            C = A;
            var1 = HL();
            mem[var1] = C & 255;
            HL(HL() + 1 & 65535);
            var1 = HL();
            mem[var1] = C & 255;
            HL(HL() + DE() & 65535);
            var1 = HL();
            mem[var1] = C & 255;
            HL(HL() + 1 & 65535);
            var1 = HL();
            mem[var1] = C & 255;
            var1 = IX() + 3;
            A = mem[var1];
            A &= 14;
            if (A << 1 != 0) {
              HL(HL() + DE() & 65535);
              var1 = HL();
              mem[var1] = C & 255;
              HL(HL() + 1 & 65535);
              var1 = HL();
              mem[var1] = C & 255;
            }

            C = 1;
            var1 = IX() + 1;
            A = mem[var1];
            var1 = IX();
            A &= mem[var1];
            var1 = IX() + 2;
            A |= mem[var1];
            A &= 224;
            E = A;
            var1 = IX() + 5;
            D = mem[var1];
            H = 130;
            var1 = IX() + 3;
            L = mem[var1];
            var1 = IX() + 2;
            A = mem[var1];
            A &= 31;
            var1 = HL();
            A |= mem[var1];
            HL(HL() + 1 & 65535);
            var1 = HL();
            H = mem[var1];
            L = A;
            $37974();
            if (F != 0) {
              nextAddress = 37048;
              return;
            }
          } else {
            var1 = IX();
            if ((mem[var1] & 128) == 0) {
              var1 = IX() + 4;
              mem[var1] = mem[var1] - 1 & 255 & 255;
              C = 44;
            } else {
              var1 = IX() + 4;
              mem[var1] = mem[var1] + 1 & 255 & 255;
              C = 244;
            }

            var1 = IX() + 4;
            A = mem[var1];
            if (A != C) {
              A &= 224;
              if (A << 1 == 0) {
                var1 = IX() + 2;
                E = mem[var1];
                D = 130;
                var1 = DE();
                A = mem[var1];
                var1 = IX() + 4;
                A = mem[var1] + A & 255;
                L = A;
                A = E;
                A &= 128;
                A = rlc(A);
                A |= 92;
                H = A;
                var1 = IX() + 5;
                mem[var1] = 0;
                var1 = HL();
                A = mem[var1];
                A &= 7;
                if (A == 7) {
                  var1 = IX() + 5;
                  mem[var1] = mem[var1] - 1 & 255 & 255;
                }

                var1 = HL();
                A = mem[var1];
                A |= 7;
                var1 = HL();
                mem[var1] = A & 255;
                DE(DE() + 1 & 65535);
                var1 = DE();
                A = mem[var1];
                H = A;
                H = H - 1 & 255;
                var1 = IX() + 6;
                A = mem[var1];
                var1 = HL();
                mem[var1] = A & 255;
                H = H + 1 & 255;
                var1 = HL();
                A = mem[var1];
                var1 = IX() + 5;
                A &= mem[var1];
                if (A << 1 != 0) {
                  nextAddress = 37048;
                  return;
                }

                var1 = HL();
                mem[var1] = 255;
                H = H + 1 & 255;
                var1 = IX() + 6;
                A = mem[var1];
                var1 = HL();
                mem[var1] = A & 255;
              }
            } else {
              BC(640);
              A = mem[32990];

              do {
                A ^= 24;

                do {
                  B = B - 1 & 255;
                } while(B != 0);

                B = C;
                C = C - 1 & 255;
              } while(C != 0);
            }
          }
        } else {
          IY(33280);
          var1 = IX() + 9;
          mem[var1] = 0;
          var1 = IX() + 2;
          A = mem[var1];
          var1 = IX() + 3;
          mem[var1] = A & 255;
          var1 = IX() + 5;
          mem[var1] = 128;

          while(true) {
            label113: {
              var1 = IY();
              A = mem[var1];
              var1 = IX() + 3;
              A = mem[var1] + A & 255;
              L = A;
              var1 = IY() + 1;
              H = mem[var1];
              A = mem[34262];
              A |= A;
              if (A << 1 == 0) {
                var1 = IX() + 5;
                A = mem[var1];
                var1 = HL();
                A &= mem[var1];
                if (A << 1 == 0) {
                  break label113;
                }

                var1 = IX() + 9;
                A = mem[var1];
                mem[34262] = A & 255;
                var1 = IX() + 11;
                mem[var1] = (mem[var1] | 1) & 255;
              }

              var1 = IX() + 9;
              if (A == mem[var1]) {
                var1 = IX() + 11;
                if ((mem[var1] & 1) != 0) {
                  var1 = IX() + 3;
                  B = mem[var1];
                  var1 = IX() + 5;
                  A = mem[var1];
                  C = 1;
                  if (A >= 4) {
                    C = 0;
                    if (A >= 16) {
                      B = B - 1 & 255;
                      C = 3;
                      if (A >= 64) {
                        C = 2;
                      }
                    }
                  }

                  wMem16(34258, BC());
                  A = IYL;
                  A = A - 16 & 255;
                  mem[34255] = A & 255;
                  push(HL());
                  $36508();
                  HL(pop());
                }
              }
            }

            var1 = IX() + 5;
            A = mem[var1];
            var1 = HL();
            A |= mem[var1];
            var1 = HL();
            mem[var1] = A & 255;
            var1 = IX() + 9;
            A = mem[var1];
            var1 = IX() + 1;
            A = mem[var1] + A & 255;
            L = A;
            L |= 128;
            H = 131;
            var1 = HL();
            E = mem[var1];
            D = 0;
            IY(IY() + DE() & 65535);
            L &= -129;
            var1 = HL();
            A = mem[var1];
            A |= A;
            if (A << 1 != 0) {
              B = A;
              var1 = IX() + 1;
              if ((mem[var1] & 128) != 0) {
                do {
                  var1 = IX() + 5;
                  int var86 = rlc(mem[var1]) & 255;
                  mem[var1] = var86;
                  var1 = IX() + 5;
                  if ((mem[var1] & 1) != 0) {
                    var1 = IX() + 3;
                    mem[var1] = mem[var1] - 1 & 255 & 255;
                  }

                  B = B - 1 & 255;
                } while(B != 0);
              } else {
                do {
                  var1 = IX() + 5;
                  int var2 = rrc(mem[var1]) & 255;
                  mem[var1] = var2;
                  var1 = IX() + 5;
                  if ((mem[var1] & 128) != 0) {
                    var1 = IX() + 3;
                    mem[var1] = mem[var1] + 1 & 255 & 255;
                  }

                  B = B - 1 & 255;
                } while(B != 0);
              }
            }

            var1 = IX() + 9;
            A = mem[var1];
            var1 = IX() + 4;
            if (A == mem[var1]) {
              A = mem[34262];
              if ((A & 128) != 0) {
                A = A + 1 & 255;
                mem[34262] = A & 255;
                var1 = IX() + 11;
                mem[var1] = mem[var1] & -2 & 255;
              } else {
                var1 = IX() + 11;
                if ((mem[var1] & 1) != 0) {
                  A = mem[34256];
                  if ((A & 2) != 0) {
                    A = rrc(A);
                    var1 = IX();
                    A ^= mem[var1];
                    A = rlc(A);
                    A = rlc(A);
                    A &= 2;
                    A = A - 1 & 255;
                    HL(34262);
                    var1 = HL();
                    A = mem[var1] + A & 255;
                    var1 = HL();
                    mem[var1] = A & 255;
                    A = mem[33003];
                    C = A;
                    A = mem[33824];
                    if (A == C) {
                      var1 = HL();
                      A = mem[var1];
                      if (A < 12) {
                        var1 = HL();
                        mem[var1] = 12;
                      }
                    }

                    var1 = HL();
                    A = mem[var1];
                    var1 = IX() + 4;
                    var1 = mem[var1];
                    if (A >= var1 && A != var1) {
                      var1 = HL();
                      mem[var1] = 240;
                      A = mem[34255];
                      A &= 248;
                      mem[34255] = A & 255;
                      A ^= A;
                      mem[34257] = A & 255;
                    }
                  }
                }
              }
              break;
            }

            var1 = IX() + 9;
            mem[var1] = mem[var1] + 1 & 255 & 255;
          }
        }
      }

      DE(8);
      IX(IX() + DE() & 65535);
    }
  }

  public void $37841() {
    H = 164;
    A = mem[41983];
    L = A;

    do {
      int var1 = HL();
      C = mem[var1];
      C &= -129;
      A = mem[33824];
      A |= 64;
      if (A == C) {
        var1 = HL();
        A = mem[var1];
        A = rlc(A);
        A &= 1;
        A = A + 92 & 255;
        D = A;
        H = H + 1 & 255;
        var1 = HL();
        E = mem[var1];
        H = H - 1 & 255;
        var1 = DE();
        A = mem[var1];
        A &= 7;
        if (A != 7) {
          A = mem[34251];
          A = A + L & 255;
          A &= 3;
          A = A + 3 & 255;
          C = A;
          var1 = DE();
          A = mem[var1];
          A &= 248;
          A |= C;
          var1 = DE();
          mem[var1] = A & 255;
          var1 = HL();
          A = mem[var1];
          A = rlc(A);
          A = rlc(A);
          A = rlc(A);
          A = rlc(A);
          A &= 8;
          A = A + 96 & 255;
          D = A;
          push(HL());
          HL(32993);
          B = 8;
          $38555();
          HL(pop());
        } else {
          IX(34172);

          while(true) {
            var1 = IX() + 2;
            mem[var1] = mem[var1] + 1 & 255 & 255;
            var1 = IX() + 2;
            A = mem[var1];
            if (A != 58) {
              A = mem[32990];
              C = 128;

              do {
                A ^= 24;
                E = A;
                A = 144;
                A = A - C & 255;
                B = A;
                A = E;

                do {
                  B = B - 1 & 255;
                } while(B != 0);

                C = C - 1 & 255;
                C = C - 1 & 255;
              } while(C != 0);

              A = mem[34270];
              A = A + 1 & 255;
              F = A;
              mem[34270] = A & 255;
              if (F == 0) {
                A = 1;
                mem[34271] = A & 255;
              }

              var1 = HL();
              int var10000 = mem[var1] & -65;
              var1 = HL();
              int var2 = var10000 & 255;
              mem[var1] = var2;
              break;
            }

            var1 = IX() + 2;
            mem[var1] = 48;
            IX(IX() - 1 & 65535);
          }
        }
      }

      L = L + 1 & 255;
    } while(L != 0);

  }

  public void $37974() {
    B = 16;

    do {
      F = C & 1;
      int var1 = DE();
      A = mem[var1];
      if (F != 0) {
        var1 = HL();
        A &= mem[var1];
        if (A << 1 != 0) {
          return;
        }

        var1 = DE();
        A = mem[var1];
        var1 = HL();
        A |= mem[var1];
      }

      var1 = HL();
      mem[var1] = A & 255;
      L = L + 1 & 255;
      DE(DE() + 1 & 65535);
      F = C & 1;
      var1 = DE();
      A = mem[var1];
      if (F != 0) {
        var1 = HL();
        A &= mem[var1];
        if (A << 1 != 0) {
          return;
        }

        var1 = DE();
        A = mem[var1];
        var1 = HL();
        A |= mem[var1];
      }

      var1 = HL();
      mem[var1] = A & 255;
      L = L - 1 & 255;
      H = H + 1 & 255;
      DE(DE() + 1 & 65535);
      A = H;
      A &= 7;
      if (A << 1 == 0) {
        A = H;
        A = A - 8 & 255;
        H = A;
        A = L;
        A = A + 32 & 255;
        L = A;
        A &= 224;
        if (A << 1 == 0) {
          A = H;
          A = A + 8 & 255;
          H = A;
        }
      }

      B = B - 1 & 255;
    } while(B != 0);

    A ^= A;
    F = A << 1;
  }

  public void $38064() {
    A = mem[33003];
    mem[33824] = A & 255;
    A = mem[34259];
    A &= 31;
    A = A + 160 & 255;
    mem[34259] = A & 255;
    A = 93;
    mem[34260] = A & 255;
    A = 208;
    mem[34255] = A & 255;
    A ^= A;
    mem[34257] = A & 255;
    nextAddress = 38095;
  }

  public void $38137() {
    HL(mem16(32983));
    A = H;
    A &= 1;
    A = rlc(A);
    A = rlc(A);
    A = rlc(A);
    A = A + 112 & 255;
    H = A;
    E = L;
    D = H;
    A = mem[32985];
    A |= A;
    if (A << 1 != 0) {
      B = A;
      A = mem[32982];
      A |= A;
      if (A << 1 == 0) {
        int var1 = HL();
        A = mem[var1];
        A = rlc(A);
        A = rlc(A);
        H = H + 1 & 255;
        H = H + 1 & 255;
        var1 = HL();
        C = mem[var1];
        C = rrc(C);
        C = rrc(C);
      } else {
        int var3 = HL();
        A = mem[var3];
        A = rrc(A);
        A = rrc(A);
        H = H + 1 & 255;
        H = H + 1 & 255;
        var3 = HL();
        C = mem[var3];
        C = rlc(C);
        C = rlc(C);
      }

      do {
        int var5 = DE();
        mem[var5] = A & 255;
        var5 = HL();
        mem[var5] = C & 255;
        L = L + 1 & 255;
        E = E + 1 & 255;
        B = B - 1 & 255;
      } while(B != 0);

    }
  }

  public void $38196() {
    A = mem[33824];
    if (A == 35) {
      A = mem[34271];
      A |= A;
      if (A << 1 == 0) {
        A = mem[34251];
        A &= 2;
        A = rrc(A);
        A = rrc(A);
        A = rrc(A);
        A = rrc(A);
        A |= 128;
        E = A;
        A = mem[34255];
        if (A != 208) {
          E = 192;
          if (A < 192) {
            E = 224;
          }
        }

        D = 156;
        HL(26734);
        C = 1;
        $37974();
        if (F != 0) {
          nextAddress = 37048;
        } else {
          HL(17733);
          wMem16(23918, HL());
          HL(1799);
          wMem16(23950, HL());
        }
      } else {
        A = mem[34259];
        A &= 31;
        if (A < 6) {
          A = 2;
          mem[34271] = A & 255;
        }
      }
    } else {
      A = mem[33824];
      if (A == 33) {
        A = mem[34251];
        A &= 1;
        A = rrc(A);
        A = rrc(A);
        A = rrc(A);
        E = A;
        A = mem[34271];
        if (A == 3) {
          E |= 64;
        }

        D = 166;
        IX(33488);
        BC(4124);
        $38504();
        HL(1799);
        wMem16(23996, HL());
        wMem16(24028, HL());
      }
    }
  }

  public void $38276() {
    A = mem[33824];
    if (A == 33) {
      A = mem[34259];
      if (A == 188) {
        A ^= A;
        F = A << 1;
        mem[34251] = A & 255;
        A = 3;
        mem[34271] = A & 255;
      }
    }
  }

  public void $38344() {
    HL(mem16(34259));
    B = 0;
    A = mem[32986];
    A &= 1;
    A = A + 64 & 255;
    E = A;
    D = 0;
    HL(HL() + DE() & 65535);
    A = mem[32964];
    int var1 = HL();
    if (A == mem[var1]) {
      A = mem[34257];
      A |= A;
      if (A << 1 == 0) {
        A = mem[34258];
        A &= 3;
        A = rlc(A);
        A = rlc(A);
        B = A;
        A = mem[32986];
        A &= 1;
        A = A - 1 & 255;
        A ^= 12;
        A ^= B;
        A &= 12;
        B = A;
      }
    }

    HL(mem16(34259));
    DE(31);
    C = 15;
    $38430();
    if (isNextPC(37047)) {
      nextAddress = 37048;
    } else {
      HL(HL() + 1 & 65535);
      $38430();
      if (isNextPC(37047)) {
        nextAddress = 37048;
      } else {
        HL(HL() + DE() & 65535);
        $38430();
        HL(HL() + 1 & 65535);
        $38430();
        if (isNextPC(37047)) {
          nextAddress = 37048;
        } else {
          A = mem[34255];
          A = A + B & 255;
          C = A;
          HL(HL() + DE() & 65535);
          $38430();
          HL(HL() + 1 & 65535);
          $38430();
          if (isNextPC(37047)) {
            nextAddress = 37048;
          } else {
            A = mem[34255];
            A = A + B & 255;
            IXH = 130;
            IXL = A;
            A = mem[34256];
            A &= 1;
            A = rrc(A);
            E = A;
            A = mem[34258];
            A &= 3;
            A = rrc(A);
            A = rrc(A);
            A = rrc(A);
            A |= E;
            E = A;
            D = 157;
            A = mem[33824];
            if (A == 29) {
              D = 182;
              A = E;
              A ^= 128;
              E = A;
            }

            B = 16;
            A = mem[34259];
            A &= 31;
            C = A;
            $38504();
          }
        }
      }
    }
  }

  public void $38430() {
    A = mem[32928];
    int var1 = HL();
    if (A == mem[var1]) {
      A = C;
      A &= 15;
      if (A << 1 != 0) {
        A = mem[32928];
        A |= 7;
        var1 = HL();
        mem[var1] = A & 255;
      }
    }

    A = mem[32955];
    var1 = HL();
    var1 = mem[var1];
    if (A == var1) {
      nextAddress = 37047;
    } else {
      F = A - var1;
    }
  }

  public void $38504() {
    do {
      int var1 = IX();
      A = mem[var1];
      var1 = IX() + 1;
      H = mem[var1];
      A |= C;
      L = A;
      var1 = DE();
      A = mem[var1];
      var1 = HL();
      A |= mem[var1];
      var1 = HL();
      mem[var1] = A & 255;
      HL(HL() + 1 & 65535);
      DE(DE() + 1 & 65535);
      var1 = DE();
      A = mem[var1];
      var1 = HL();
      A |= mem[var1];
      F = A << 1;
      var1 = HL();
      mem[var1] = A & 255;
      IX(IX() + 1 & 65535);
      IX(IX() + 1 & 65535);
      DE(DE() + 1 & 65535);
      B = B - 1 & 255;
    } while(B != 0);

  }

  public void $38528() {
    do {
      int var1 = IX();
      A = mem[var1];
      $38545();
      IX(IX() + 1 & 65535);
      E = E + 1 & 255;
      A = D;
      A = A - 8 & 255;
      D = A;
      C = C - 1 & 255;
    } while(C != 0);

  }

  public void $38545() {
    H = 7;
    L = A;
    L |= 128;
    HL(HL() * 2 & 65535);
    HL(HL() * 2 & 65535);
    HL(HL() * 2 & 65535);
    B = 8;
    $38555();
  }

  public void $38555() {
    do {
      int var1 = HL();
      A = mem[var1];
      var1 = DE();
      mem[var1] = A & 255;
      HL(HL() + 1 & 65535);
      D = D + 1 & 255;
      B = B - 1 & 255;
    } while(B != 0);

  }

  public void $38562() {
    while(true) {
      int var1 = HL();
      A = mem[var1];
      if (A == 255) {
        return;
      }

      BC(100);
      A ^= A;
      var1 = HL();
      E = mem[var1];
      D = E;

      while(true) {
        D = D - 1 & 255;
        if (D == 0) {
          D = E;
          A ^= 24;
        }

        B = B - 1 & 255;
        if (B == 0) {
          exAF();
          A = C;
          if (A == 50) {
            F = A - 50;
            E = rl(E);
          }

          exAF();
          C = C - 1 & 255;
          if (C == 0) {
            $38601();
            if (F != 0) {
              return;
            }

            HL(HL() + 1 & 65535);
            break;
          }
        }
      }
    }
  }

  public void $38601() {
    A = mem[34254];
    A |= A;
    if (A << 1 != 0) {
      A = in(31);
      if ((A & 16) != 0) {
        return;
      }
    }

    BC(45054);
    A = in(BC());
    A &= 1;
    F = A - 1;
  }

  public void $38622() {
    E = A;
    C = 254;

    do {
      D = A;
      D &= -17;
      D &= -9;
      B = E;

      do {
        if (A == B) {
          D = 24;
        }

        B = B - 1 & 255;
      } while(B != 0);

      A = A - 1 & 255;
    } while(A != 0);

  }

  public String getProgramBytes() {
    return "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";
  }

  public void main(String[] var1) {
  }
}
