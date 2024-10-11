package com.fpetrola.z80.minizx;

public class JetSetWilly extends MiniZX {
  public String getProgramBytes() {
    return "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";
  }

  public int[] $34762(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    label295:
    while(true) {
      A ^= A;
      AF = reg16low(AF, A);
      mem[34254] = A;
      mem[34273] = A;
      mem[34253] = A;
      mem[34257] = A;
      mem[34251] = A;
      mem[34272] = A;
      mem[34271] = A;
      byte A1 = 7;
      AF = reg16low(AF, A1);
      mem[34252] = A1;
      short A2 = 208;
      AF = reg16low(AF, A2);
      mem[34255] = A2;
      A1 = 33;
      AF = reg16low(AF, A1);
      mem[33824] = A1;
      short HL1 = 23988;
      L = HL1 & 255;
      H = HL1 >> 8;
      wMem16(34259, HL1);
      char HL2 = 34172;
      L = HL2 & 255;
      H = HL2 >> 8;
      mem[HL2] = 48;
      HL = HL2 + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      mem[HL] = 48;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      mem[HL] = 48;
      short H1 = 164;
      HL = reg16low(HL, H1);
      A = mem[41983];
      AF = reg16low(AF, A);
      L = A;
      HL = reg16high(HL, A);
      mem[34270] = A;

      do {
        int var19 = mem[HL] | 64;
        mem[HL] = var19;
        L = L + 1 & 255;
        HL = reg16high(HL, L);
      } while(L != 0);

      HL2 = 34274;
      L = HL2 & 255;
      H = HL2 >> 8;
      int var20 = mem[HL2] | 1;
      mem[HL2] = var20;

      short BC1;
      short DE1;
      byte BC2;
      char IX1;
      byte B1;
      byte C1;
      byte D1;
      label287:
      while(true) {
        HL1 = 16384;
        L = HL1 & 255;
        H = HL1 >> 8;
        DE1 = 16385;
        E = DE1 & 255;
        D = DE1 >> 8;
        BC1 = 6143;
        C = BC1 & 255;
        B = BC1 >> 8;
        mem[HL1] = 0;
        int[] var21 = ldir(HL1, DE1, BC1);
        HL = var21[0];
        DE = var21[1];
        BC = var21[2];
        HL2 = 38912;
        L = HL2 & 255;
        H = HL2 >> 8;
        BC1 = 768;
        C = BC1 & 255;
        B = BC1 >> 8;
        int[] var22 = ldir(HL2, DE, BC1);
        HL = var22[0];
        DE = var22[1];
        BC = var22[2];
        HL1 = 23136;
        L = HL1 & 255;
        H = HL1 >> 8;
        DE1 = 23137;
        E = DE1 & 255;
        D = DE1 >> 8;
        BC2 = 31;
        C = BC2 & 255;
        B = BC2 >> 8;
        mem[HL1] = 70;
        int[] var23 = ldir(HL1, DE1, BC2);
        HL = var23[0];
        DE = var23[1];
        BC = var23[2];
        IX1 = 33876;
        IXL = IX1 & 255;
        IXH = IX1 >> 8;
        DE1 = 20576;
        E = DE1 & 255;
        D = DE1 >> 8;
        C1 = 32;
        BC = reg16high(BC, C1);
        int[] var24 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
        AF = var24[0];
        BC = var24[1];
        DE = var24[2];
        HL = var24[3];
        IX = var24[4];
        IY = var24[5];
        A = var24[6];
        F = var24[7];
        B = var24[8];
        C = var24[9];
        D = var24[10];
        E = var24[11];
        H = var24[12];
        L = var24[13];
        IXL = var24[14];
        IXH = var24[15];
        IYL = var24[16];
        IYH = var24[17];
        DE = 22528;
        E = DE & 255;
        D = DE >> 8;

        do {
          A = mem[DE];
          AF = reg16low(AF, A);
          A |= A;
          AF = reg16low(AF, A);
          if(A << 1 != 0 && A != 211 && A != 9 && A != 45 && A != 36) {
            C1 = 0;
            BC = reg16high(BC, C1);
            if(A != 8 && A != 41) {
              if(A != 44) {
                if(A != 5) {
                  C1 = 16;
                  BC = reg16high(BC, C1);
                }
              } else {
                A1 = 37;
                AF = reg16low(AF, A1);
                mem[DE] = A1;
              }
            }

            AF = reg16low(AF, E);
            A = E & 1;
            AF = reg16low(AF, A);
            int[] var107 = rlc(A, F);
            A = var107[0];
            AF = reg16low(AF, A);
            F = var107[1];
            int[] var108 = rlc(A, F);
            A = var108[0];
            AF = reg16low(AF, A);
            F = var108[1];
            int[] var109 = rlc(A, F);
            A = var109[0];
            AF = reg16low(AF, A);
            F = var109[1];
            A |= C1;
            AF = reg16low(AF, A);
            BC = reg16high(BC, A);
            B1 = 0;
            BC = reg16low(BC, B1);
            HL2 = 33841;
            L = HL2 & 255;
            H = HL2 >> 8;
            HL = HL2 + BC & 65535;
            L = HL & 255;
            H = HL >> 8;
            push(DE);
            F = D & 1;
            AF = reg16high(AF, F);
            D1 = 64;
            DE = reg16low(DE, D1);
            if(F != 0) {
              D1 = 72;
              DE = reg16low(DE, D1);
            }

            B1 = 8;
            BC = reg16low(BC, B1);
            int[] var110 = $38555(AF, BC, DE, HL, IX, IY, A, F, B1, A, D1, E, H, L, IXL, IXH, IYL, IYH);
            AF = var110[0];
            BC = var110[1];
            DE = var110[2];
            HL = var110[3];
            IX = var110[4];
            IY = var110[5];
            A = var110[6];
            F = var110[7];
            B = var110[8];
            C = var110[9];
            D = var110[10];
            E = var110[11];
            H = var110[12];
            L = var110[13];
            IXL = var110[14];
            IXH = var110[15];
            IYL = var110[16];
            IYH = var110[17];
            DE = pop();
            E = DE & 255;
            D = DE >> 8;
          }

          DE = DE + 1 & 65535;
          E = DE & 255;
          D = DE >> 8;
          AF = reg16low(AF, D);
        } while(D != 90);

        BC = 31;
        C = BC & 255;
        B = BC >> 8;
        A = D ^ D;
        AF = reg16low(AF, A);

        do {
          E = in(BC);
          DE = reg16high(DE, E);
          A |= E;
          AF = reg16low(AF, A);
          B = B - 1 & 255;
          BC = reg16low(BC, B);
        } while(B != 0);

        A &= 32;
        AF = reg16low(AF, A);
        if(A << 1 == 0) {
          A = 1;
          AF = reg16low(AF, A);
          mem[34254] = A;
        }

        HL2 = 34299;
        L = HL2 & 255;
        H = HL2 >> 8;
        int[] var25 = $38562(AF, BC, DE, HL2, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
        AF = var25[0];
        BC = var25[1];
        DE = var25[2];
        HL = var25[3];
        IX = var25[4];
        IY = var25[5];
        A = var25[6];
        F = var25[7];
        B = var25[8];
        C = var25[9];
        D = var25[10];
        E = var25[11];
        H = var25[12];
        L = var25[13];
        IXL = var25[14];
        IXH = var25[15];
        IYL = var25[16];
        IYH = var25[17];
        if(F != 0) {
          break;
        }

        A ^= A;
        AF = reg16low(AF, A);
        mem[34276] = A;

        while(true) {
          int[] var103 = $35563(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var103[0];
          BC = var103[1];
          DE = var103[2];
          HL = var103[3];
          IX = var103[4];
          IY = var103[5];
          A = var103[6];
          F = var103[7];
          B = var103[8];
          C = var103[9];
          D = var103[10];
          E = var103[11];
          H = var103[12];
          L = var103[13];
          IXL = var103[14];
          IXH = var103[15];
          IYL = var103[16];
          IYH = var103[17];
          HL1 = 23136;
          L = HL1 & 255;
          H = HL1 >> 8;
          DE1 = 23137;
          E = DE1 & 255;
          D = DE1 >> 8;
          BC2 = 31;
          C = BC2 & 255;
          B = BC2 >> 8;
          mem[HL1] = 79;
          int[] var104 = ldir(HL1, DE1, BC2);
          HL = var104[0];
          DE = var104[1];
          BC = var104[2];
          A = mem[34276];
          AF = reg16low(AF, A);
          IX1 = 33876;
          IXL = IX1 & 255;
          IXH = IX1 >> 8;
          DE = reg16high(DE, A);
          D1 = 0;
          DE = reg16low(DE, D1);
          IX = IX1 + DE & 65535;
          IXL = IX & 255;
          IXH = IX >> 8;
          DE1 = 20576;
          E = DE1 & 255;
          D = DE1 >> 8;
          C1 = 32;
          BC = reg16high(BC, C1);
          int[] var105 = $38528(AF, BC, DE1, HL, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var105[0];
          BC = var105[1];
          DE = var105[2];
          HL = var105[3];
          IX = var105[4];
          IY = var105[5];
          A = var105[6];
          F = var105[7];
          B = var105[8];
          C = var105[9];
          D = var105[10];
          E = var105[11];
          H = var105[12];
          L = var105[13];
          IXL = var105[14];
          IXH = var105[15];
          IYL = var105[16];
          IYH = var105[17];
          A = mem[34276];
          AF = reg16low(AF, A);
          A &= 31;
          AF = reg16low(AF, A);
          A = A + 50 & 255;
          AF = reg16low(AF, A);
          int[] var106 = $38622(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var106[0];
          BC = var106[1];
          DE = var106[2];
          HL = var106[3];
          IX = var106[4];
          IY = var106[5];
          A = var106[6];
          F = var106[7];
          B = var106[8];
          C = var106[9];
          D = var106[10];
          E = var106[11];
          H = var106[12];
          L = var106[13];
          IXL = var106[14];
          IXH = var106[15];
          IYL = var106[16];
          IYH = var106[17];
          BC = 45054;
          C = BC & 255;
          B = BC >> 8;
          A = in(BC);
          AF = reg16low(AF, A);
          A &= 1;
          AF = reg16low(AF, A);
          if(A != 1) {
            break label287;
          }

          A = mem[34276];
          AF = reg16low(AF, A);
          A = A + 1 & 255;
          AF = reg16low(AF, A);
          F = A - 224;
          AF = reg16high(AF, F);
          mem[34276] = A;
          if(F == 0) {
            break;
          }
        }
      }

      HL2 = 34181;
      L = HL2 & 255;
      H = HL2 >> 8;
      char DE2 = 34175;
      E = DE2 & 255;
      D = DE2 >> 8;
      BC2 = 6;
      C = BC2 & 255;
      B = BC2 >> 8;
      int[] var26 = ldir(HL2, DE2, BC2);
      HL = var26[0];
      DE = var26[1];
      BC = var26[2];
      HL2 = 39424;
      L = HL2 & 255;
      H = HL2 >> 8;
      DE1 = 23040;
      E = DE1 & 255;
      D = DE1 >> 8;
      BC1 = 256;
      C = BC1 & 255;
      B = BC1 >> 8;
      int[] var27 = ldir(HL2, DE1, BC1);
      HL = var27[0];
      DE = var27[1];
      BC = var27[2];

      while(true) {
        while(true) {
          A = mem[33824];
          AF = reg16low(AF, A);
          A |= 192;
          AF = reg16low(AF, A);
          HL = reg16low(HL, A);
          byte L1 = 0;
          HL = reg16high(HL, L1);
          DE2 = 32768;
          E = DE2 & 255;
          D = DE2 >> 8;
          BC1 = 256;
          C = BC1 & 255;
          B = BC1 >> 8;
          int[] var28 = ldir(HL, DE2, BC1);
          HL = var28[0];
          DE = var28[1];
          BC = var28[2];
          IX = 33008;
          IXL = IX & 255;
          IXH = IX >> 8;
          DE = 33024;
          E = DE & 255;
          D = DE >> 8;
          A = 8;
          AF = reg16low(AF, A);

          do {
            L = mem[IX];
            HL = reg16high(HL, L);
            L &= -129;
            HL = reg16high(HL, L);
            byte H2 = 20;
            HL = reg16low(HL, H2);
            HL = HL + HL & 65535;
            L = HL & 255;
            H = HL >> 8;
            HL = HL + HL & 65535;
            L = HL & 255;
            H = HL >> 8;
            HL = HL + HL & 65535;
            L = HL & 255;
            H = HL >> 8;
            BC2 = 2;
            C = BC2 & 255;
            B = BC2 >> 8;
            int[] var29 = ldir(HL, DE, BC2);
            HL = var29[0];
            DE = var29[1];
            BC = var29[2];
            int var30 = IX + 1;
            C = mem[var30];
            reg16high(BC, C);
            mem[HL] = C;
            BC2 = 6;
            C = BC2 & 255;
            B = BC2 >> 8;
            int[] var31 = ldir(HL, DE, BC2);
            HL = var31[0];
            DE = var31[1];
            BC = var31[2];
            IX = IX + 1 & 65535;
            IXL = IX & 255;
            IXH = IX >> 8;
            IX = IX + 1 & 65535;
            IXL = IX & 255;
            IXH = IX >> 8;
            A = A - 1 & 255;
            AF = reg16low(AF, A);
          } while(A != 0);

          HL2 = 34255;
          L = HL2 & 255;
          H = HL2 >> 8;
          DE2 = 34263;
          E = DE2 & 255;
          D = DE2 >> 8;
          BC2 = 7;
          C = BC2 & 255;
          B = BC2 >> 8;
          int[] var32 = ldir(HL2, DE2, BC2);
          HL = var32[0];
          DE = var32[1];
          BC = var32[2];
          int[] var33 = $36147(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var33[0];
          BC = var33[1];
          DE = var33[2];
          HL = var33[3];
          IX = var33[4];
          IY = var33[5];
          A = var33[6];
          F = var33[7];
          B = var33[8];
          C = var33[9];
          D = var33[10];
          E = var33[11];
          H = var33[12];
          L = var33[13];
          IXL = var33[14];
          IXH = var33[15];
          IYL = var33[16];
          IYH = var33[17];
          HL1 = 20480;
          L = HL1 & 255;
          H = HL1 >> 8;
          DE1 = 20481;
          E = DE1 & 255;
          D = DE1 >> 8;
          BC1 = 2047;
          C = BC1 & 255;
          B = BC1 >> 8;
          mem[HL1] = 0;
          int[] var34 = ldir(HL1, DE1, BC1);
          HL = var34[0];
          DE = var34[1];
          BC = var34[2];
          IX1 = 32896;
          IXL = IX1 & 255;
          IXH = IX1 >> 8;
          C1 = 32;
          BC = reg16high(BC, C1);
          DE1 = 20480;
          E = DE1 & 255;
          D = DE1 >> 8;
          int[] var35 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var35[0];
          BC = var35[1];
          DE = var35[2];
          HL = var35[3];
          IX = var35[4];
          IY = var35[5];
          A = var35[6];
          F = var35[7];
          B = var35[8];
          C = var35[9];
          D = var35[10];
          E = var35[11];
          H = var35[12];
          L = var35[13];
          IXL = var35[14];
          IXH = var35[15];
          IYL = var35[16];
          IYH = var35[17];
          IX1 = 34132;
          IXL = IX1 & 255;
          IXH = IX1 >> 8;
          DE1 = 20576;
          E = DE1 & 255;
          D = DE1 >> 8;
          C1 = 32;
          BC = reg16high(BC, C1);
          int[] var36 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var36[0];
          BC = var36[1];
          DE = var36[2];
          HL = var36[3];
          IX = var36[4];
          IY = var36[5];
          A = var36[6];
          F = var36[7];
          B = var36[8];
          C = var36[9];
          D = var36[10];
          E = var36[11];
          H = var36[12];
          L = var36[13];
          IXL = var36[14];
          IXH = var36[15];
          IYL = var36[16];
          IYH = var36[17];
          A = mem[32990];
          AF = reg16low(AF, A);
          C = 254;
          BC = reg16high(BC, C);
          A ^= A;
          AF = reg16low(AF, A);
          mem[34262] = A;

          while(true) {
            short B2;
            char BC3;
            label306: {
              label226: {
                label302: {
                  int[] var37 = $35211(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                  AF = var37[0];
                  BC = var37[1];
                  DE = var37[2];
                  HL = var37[3];
                  IX = var37[4];
                  IY = var37[5];
                  A = var37[6];
                  F = var37[7];
                  B = var37[8];
                  C = var37[9];
                  D = var37[10];
                  E = var37[11];
                  H = var37[12];
                  L = var37[13];
                  IXL = var37[14];
                  IXH = var37[15];
                  IYL = var37[16];
                  IYH = var37[17];
                  HL1 = 24064;
                  L = HL1 & 255;
                  H = HL1 >> 8;
                  DE1 = 23552;
                  E = DE1 & 255;
                  D = DE1 >> 8;
                  BC1 = 512;
                  C = BC1 & 255;
                  B = BC1 >> 8;
                  int[] var38 = ldir(HL1, DE1, BC1);
                  HL = var38[0];
                  DE = var38[1];
                  BC = var38[2];
                  HL1 = 28672;
                  L = HL1 & 255;
                  H = HL1 >> 8;
                  DE1 = 24576;
                  E = DE1 & 255;
                  D = DE1 >> 8;
                  BC1 = 4096;
                  C = BC1 & 255;
                  B = BC1 >> 8;
                  int[] var39 = ldir(HL1, DE1, BC1);
                  HL = var39[0];
                  DE = var39[1];
                  BC = var39[2];
                  int[] var40 = $37056(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                  AF = var40[0];
                  BC = var40[1];
                  DE = var40[2];
                  HL = var40[3];
                  IX = var40[4];
                  IY = var40[5];
                  A = var40[6];
                  F = var40[7];
                  B = var40[8];
                  C = var40[9];
                  D = var40[10];
                  E = var40[11];
                  H = var40[12];
                  L = var40[13];
                  IXL = var40[14];
                  IXH = var40[15];
                  IYL = var40[16];
                  IYH = var40[17];
                  A = mem[34271];
                  AF = reg16low(AF, A);
                  if(A != 3) {
                    int[] var102 = $36307(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                    AF = var102[0];
                    BC = var102[1];
                    DE = var102[2];
                    HL = var102[3];
                    IX = var102[4];
                    IY = var102[5];
                    A = var102[6];
                    F = var102[7];
                    B = var102[8];
                    C = var102[9];
                    D = var102[10];
                    E = var102[11];
                    H = var102[12];
                    L = var102[13];
                    IXL = var102[14];
                    IXH = var102[15];
                    IYL = var102[16];
                    IYH = var102[17];
                    if(isNextPC(37048)) {
                      break label302;
                    }

                    if(isNextPC(38043) || isNextPC(38061) || isNextPC(38134) || isNextPC(38095)) {
                      break;
                    }
                  }

                  A = mem[34255];
                  AF = reg16low(AF, A);
                  if(A >= 225) {
                    int[] var101 = $38064(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                    AF = var101[0];
                    BC = var101[1];
                    DE = var101[2];
                    HL = var101[3];
                    IX = var101[4];
                    IY = var101[5];
                    A = var101[6];
                    F = var101[7];
                    B = var101[8];
                    C = var101[9];
                    D = var101[10];
                    E = var101[11];
                    H = var101[12];
                    L = var101[13];
                    IXL = var101[14];
                    IXH = var101[15];
                    IYL = var101[16];
                    IYH = var101[17];
                    if(isNextPC(38095)) {
                      break;
                    }
                  }

                  A = mem[34271];
                  AF = reg16low(AF, A);
                  if(A != 3) {
                    int[] var100 = $38344(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                    AF = var100[0];
                    BC = var100[1];
                    DE = var100[2];
                    HL = var100[3];
                    IX = var100[4];
                    IY = var100[5];
                    A = var100[6];
                    F = var100[7];
                    B = var100[8];
                    C = var100[9];
                    D = var100[10];
                    E = var100[11];
                    H = var100[12];
                    L = var100[13];
                    IXL = var100[14];
                    IXH = var100[15];
                    IYL = var100[16];
                    IYH = var100[17];
                    if(isNextPC(37048)) {
                      break label302;
                    }
                  }

                  A = mem[34271];
                  AF = reg16low(AF, A);
                  if(A == 2) {
                    int[] var99 = $38276(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                    AF = var99[0];
                    BC = var99[1];
                    DE = var99[2];
                    HL = var99[3];
                    IX = var99[4];
                    IY = var99[5];
                    A = var99[6];
                    F = var99[7];
                    B = var99[8];
                    C = var99[9];
                    D = var99[10];
                    E = var99[11];
                    H = var99[12];
                    L = var99[13];
                    IXL = var99[14];
                    IXH = var99[15];
                    IYL = var99[16];
                    IYH = var99[17];
                  }

                  F = A - 2;
                  AF = reg16high(AF, F);
                  int[] var41 = $38196(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                  AF = var41[0];
                  BC = var41[1];
                  DE = var41[2];
                  HL = var41[3];
                  IX = var41[4];
                  IY = var41[5];
                  A = var41[6];
                  F = var41[7];
                  B = var41[8];
                  C = var41[9];
                  D = var41[10];
                  E = var41[11];
                  H = var41[12];
                  L = var41[13];
                  IXL = var41[14];
                  IXH = var41[15];
                  IYL = var41[16];
                  IYH = var41[17];
                  if(!isNextPC(37048)) {
                    int[] var96 = $37310(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                    AF = var96[0];
                    BC = var96[1];
                    DE = var96[2];
                    HL = var96[3];
                    IX = var96[4];
                    IY = var96[5];
                    A = var96[6];
                    F = var96[7];
                    B = var96[8];
                    C = var96[9];
                    D = var96[10];
                    E = var96[11];
                    H = var96[12];
                    L = var96[13];
                    IXL = var96[14];
                    IXH = var96[15];
                    IYL = var96[16];
                    IYH = var96[17];
                    if(!isNextPC(37048)) {
                      int[] var97 = $38137(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                      AF = var97[0];
                      BC = var97[1];
                      DE = var97[2];
                      HL = var97[3];
                      IX = var97[4];
                      IY = var97[5];
                      A = var97[6];
                      F = var97[7];
                      B = var97[8];
                      C = var97[9];
                      D = var97[10];
                      E = var97[11];
                      H = var97[12];
                      L = var97[13];
                      IXL = var97[14];
                      IXH = var97[15];
                      IYL = var97[16];
                      IYH = var97[17];
                      int[] var98 = $37841(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
                      AF = var98[0];
                      BC = var98[1];
                      DE = var98[2];
                      HL = var98[3];
                      IX = var98[4];
                      IY = var98[5];
                      A = var98[6];
                      F = var98[7];
                      B = var98[8];
                      C = var98[9];
                      D = var98[10];
                      E = var98[11];
                      H = var98[12];
                      L = var98[13];
                      IXL = var98[14];
                      IXH = var98[15];
                      IYL = var98[16];
                      IYH = var98[17];
                      break label226;
                    }
                  }
                }

                A2 = 255;
                AF = reg16low(AF, A2);
                mem[34257] = A2;
              }

              HL1 = 24576;
              L = HL1 & 255;
              H = HL1 >> 8;
              DE1 = 16384;
              E = DE1 & 255;
              D = DE1 >> 8;
              BC1 = 4096;
              C = BC1 & 255;
              B = BC1 >> 8;
              int[] var42 = ldir(HL1, DE1, BC1);
              HL = var42[0];
              DE = var42[1];
              BC = var42[2];
              A = mem[34271];
              AF = reg16low(AF, A);
              A &= 2;
              AF = reg16low(AF, A);
              A = rrc(A);
              AF = reg16low(AF, A);
              HL2 = 34258;
              L = HL2 & 255;
              H = HL2 >> 8;
              int var43 = mem[HL2];
              A |= var43;
              AF = reg16low(AF, A);
              mem[HL2] = A;
              A = mem[34253];
              AF = reg16low(AF, A);
              A |= A;
              AF = reg16low(AF, A);
              if(A << 1 != 0) {
                A = A - 1 & 255;
                AF = reg16low(AF, A);
                mem[34253] = A;
                int[] var92 = rlc(A, F);
                A = var92[0];
                AF = reg16low(AF, A);
                F = var92[1];
                int[] var93 = rlc(A, F);
                A = var93[0];
                AF = reg16low(AF, A);
                F = var93[1];
                int[] var94 = rlc(A, F);
                A = var94[0];
                AF = reg16low(AF, A);
                F = var94[1];
                A &= 56;
                AF = reg16low(AF, A);
                HL1 = 23552;
                L = HL1 & 255;
                H = HL1 >> 8;
                DE1 = 23553;
                E = DE1 & 255;
                D = DE1 >> 8;
                BC1 = 511;
                C = BC1 & 255;
                B = BC1 >> 8;
                mem[HL1] = A;
                int[] var95 = ldir(HL1, DE1, BC1);
                HL = var95[0];
                DE = var95[1];
                BC = var95[2];
              }

              HL1 = 23552;
              L = HL1 & 255;
              H = HL1 >> 8;
              DE1 = 22528;
              E = DE1 & 255;
              D = DE1 >> 8;
              BC1 = 512;
              C = BC1 & 255;
              B = BC1 >> 8;
              int[] var44 = ldir(HL1, DE1, BC1);
              HL = var44[0];
              DE = var44[1];
              BC = var44[2];
              IX1 = 34175;
              IXL = IX1 & 255;
              IXH = IX1 >> 8;
              DE1 = 20601;
              E = DE1 & 255;
              D = DE1 >> 8;
              C1 = 6;
              BC = reg16high(BC, C1);
              int[] var45 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
              AF = var45[0];
              BC = var45[1];
              DE = var45[2];
              HL = var45[3];
              IX = var45[4];
              IY = var45[5];
              A = var45[6];
              F = var45[7];
              B = var45[8];
              C = var45[9];
              D = var45[10];
              E = var45[11];
              H = var45[12];
              L = var45[13];
              IXL = var45[14];
              IXH = var45[15];
              IYL = var45[16];
              IYH = var45[17];
              IX1 = 34172;
              IXL = IX1 & 255;
              IXH = IX1 >> 8;
              DE1 = 20592;
              E = DE1 & 255;
              D = DE1 >> 8;
              C1 = 3;
              BC = reg16high(BC, C1);
              int[] var46 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
              AF = var46[0];
              BC = var46[1];
              DE = var46[2];
              HL = var46[3];
              IX = var46[4];
              IY = var46[5];
              A = var46[6];
              F = var46[7];
              B = var46[8];
              C = var46[9];
              D = var46[10];
              E = var46[11];
              H = var46[12];
              L = var46[13];
              IXL = var46[14];
              IXH = var46[15];
              IYL = var46[16];
              IYH = var46[17];
              A = mem[34251];
              AF = reg16low(AF, A);
              A = A + 1 & 255;
              AF = reg16low(AF, A);
              F = A;
              AF = reg16high(AF, A);
              mem[34251] = A;
              if(A == 0) {
                IX = 34175;
                IXL = IX & 255;
                IXH = IX >> 8;
                int var74 = IX + 4;
                int var75 = mem[var74] + 1 & 255;
                mem[var74] = var75;
                int var76 = IX + 4;
                A = mem[var76];
                AF = reg16low(AF, A);
                if(A == 58) {
                  int var77 = IX + 4;
                  mem[var77] = 48;
                  int var78 = IX + 3;
                  int var79 = mem[var78] + 1 & 255;
                  mem[var78] = var79;
                  int var80 = IX + 3;
                  A = mem[var80];
                  AF = reg16low(AF, A);
                  if(A == 54) {
                    int var81 = IX + 3;
                    mem[var81] = 48;
                    A = mem[IX];
                    AF = reg16low(AF, A);
                    if(A == 49) {
                      int var86 = IX + 1;
                      int var87 = mem[var86] + 1 & 255;
                      mem[var86] = var87;
                      int var88 = IX + 1;
                      A = mem[var88];
                      AF = reg16low(AF, A);
                      if(A == 51) {
                        int var89 = IX + 5;
                        A = mem[var89];
                        AF = reg16low(AF, A);
                        if(A == 112) {
                          continue label295;
                        }

                        mem[IX] = 32;
                        int var90 = IX + 1;
                        mem[var90] = 49;
                        int var91 = IX + 5;
                        mem[var91] = 112;
                      }
                    } else {
                      int var82 = IX + 1;
                      int var83 = mem[var82] + 1 & 255;
                      mem[var82] = var83;
                      int var84 = IX + 1;
                      A = mem[var84];
                      AF = reg16low(AF, A);
                      if(A == 58) {
                        int var85 = IX + 1;
                        mem[var85] = 48;
                        mem[IX] = 49;
                      }
                    }
                  }
                }
              }

              BC3 = 65278;
              C = BC3 & 255;
              B = BC3 >> 8;
              A = in(BC3);
              AF = reg16low(AF, A);
              E = A;
              DE = reg16high(DE, A);
              B1 = 127;
              BC = reg16low(BC3, B1);
              A = in(BC);
              AF = reg16low(AF, A);
              A |= E;
              AF = reg16low(AF, A);
              A &= 1;
              AF = reg16low(AF, A);
              if(A << 1 == 0) {
                continue label295;
              }

              A = mem[34272];
              AF = reg16low(AF, A);
              A = A + 1 & 255;
              AF = reg16low(AF, A);
              F = A;
              AF = reg16high(AF, A);
              mem[34272] = A;
              if(A != 0) {
                B2 = 253;
                BC = reg16low(BC, B2);
                A = in(BC);
                AF = reg16low(AF, A);
                A &= 31;
                AF = reg16low(AF, A);
                if(A == 31) {
                  break label306;
                }

                DE = 0;
                E = DE & 255;
                D = DE >> 8;
              }

              while(true) {
                B1 = 2;
                BC = reg16low(BC, B1);
                A = in(BC);
                AF = reg16low(AF, A);
                A &= 31;
                AF = reg16low(AF, A);
                if(A != 31) {
                  HL2 = 39424;
                  L = HL2 & 255;
                  H = HL2 >> 8;
                  DE1 = 23040;
                  E = DE1 & 255;
                  D = DE1 >> 8;
                  BC1 = 256;
                  C = BC1 & 255;
                  B = BC1 >> 8;
                  int[] var47 = ldir(HL2, DE1, BC1);
                  HL = var47[0];
                  DE = var47[1];
                  BC = var47[2];
                  A = mem[32990];
                  AF = reg16low(AF, A);
                  break;
                }

                E = E + 1 & 255;
                DE = reg16high(DE, E);
                if(E == 0) {
                  D = D + 1 & 255;
                  DE = reg16low(DE, D);
                  if(D == 0) {
                    A = mem[34275];
                    AF = reg16low(AF, A);
                    if(A != 10) {
                      int[] var73 = $35563(AF, BC, DE, HL, IX, IY, A, F, B1, C, D, E, H, L, IXL, IXH, IYL, IYH);
                      AF = var73[0];
                      BC = var73[1];
                      DE = var73[2];
                      HL = var73[3];
                      IX = var73[4];
                      IY = var73[5];
                      A = var73[6];
                      F = var73[7];
                      B = var73[8];
                      C = var73[9];
                      D = var73[10];
                      E = var73[11];
                      H = var73[12];
                      L = var73[13];
                      IXL = var73[14];
                      IXH = var73[15];
                      IYL = var73[16];
                      IYH = var73[17];
                    }

                    F = A - 10;
                    AF = reg16high(AF, F);
                  }
                }
              }
            }

            A = mem[34257];
            AF = reg16low(AF, A);
            if(A == 255) {
              A = 71;
              AF = reg16low(AF, A);

              do {
                HL1 = 22528;
                L = HL1 & 255;
                H = HL1 >> 8;
                DE1 = 22529;
                E = DE1 & 255;
                D = DE1 >> 8;
                BC1 = 511;
                C = BC1 & 255;
                B = BC1 >> 8;
                mem[HL1] = A;
                int[] var48 = ldir(HL1, DE1, BC1);
                HL = var48[0];
                DE = var48[1];
                BC = var48[2];
                E = A;
                DE = reg16high(DE, A);
                A = ~A;
                AF = reg16low(AF, A);
                A &= 7;
                AF = reg16low(AF, A);
                int[] var49 = rlc(A, F);
                A = var49[0];
                AF = reg16low(AF, A);
                F = var49[1];
                int[] var50 = rlc(A, F);
                A = var50[0];
                AF = reg16low(AF, A);
                F = var50[1];
                int[] var51 = rlc(A, F);
                A = var51[0];
                AF = reg16low(AF, A);
                F = var51[1];
                A |= 7;
                AF = reg16low(AF, A);
                D = A;
                reg16low(DE, A);
                BC = reg16high(BC, E);
                C = rrc(E);
                BC = reg16high(BC, C);
                C = rrc(C);
                BC = reg16high(BC, C);
                C = rrc(C);
                BC = reg16high(BC, C);
                A |= 16;
                AF = reg16low(AF, A);
                A ^= A;
                AF = reg16low(AF, A);

                do {
                  A ^= 24;
                  AF = reg16low(AF, A);
                  B = D;
                  BC = reg16low(BC, D);

                  do {
                    B = B - 1 & 255;
                    BC = reg16low(BC, B);
                  } while(B != 0);

                  C = C - 1 & 255;
                  BC = reg16high(BC, C);
                } while(C != 0);

                AF = reg16low(AF, E);
                A = E - 1 & 255;
                AF = reg16low(AF, A);
              } while(A != 63);

              HL2 = 34252;
              L = HL2 & 255;
              H = HL2 >> 8;
              A = mem[HL2];
              AF = reg16low(AF, A);
              A |= A;
              AF = reg16low(AF, A);
              if(A << 1 == 0) {
                HL1 = 16384;
                L = HL1 & 255;
                H = HL1 >> 8;
                DE1 = 16385;
                E = DE1 & 255;
                D = DE1 >> 8;
                BC1 = 4095;
                C = BC1 & 255;
                B = BC1 >> 8;
                mem[HL1] = 0;
                int[] var52 = ldir(HL1, DE1, BC1);
                HL = var52[0];
                DE = var52[1];
                BC = var52[2];
                A ^= A;
                AF = reg16low(AF, A);
                mem[34276] = A;
                DE2 = 40256;
                E = DE2 & 255;
                D = DE2 >> 8;
                HL1 = 18575;
                L = HL1 & 255;
                H = HL1 >> 8;
                C1 = 0;
                BC = reg16high(BC, C1);
                int[] var53 = $37974(AF, BC, DE2, HL1, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
                AF = var53[0];
                BC = var53[1];
                DE = var53[2];
                HL = var53[3];
                IX = var53[4];
                IY = var53[5];
                A = var53[6];
                F = var53[7];
                B = var53[8];
                C = var53[9];
                D = var53[10];
                E = var53[11];
                H = var53[12];
                L = var53[13];
                IXL = var53[14];
                IXH = var53[15];
                IYL = var53[16];
                IYH = var53[17];
                DE2 = 40032;
                E = DE2 & 255;
                D = DE2 >> 8;
                HL1 = 18639;
                L = HL1 & 255;
                H = HL1 >> 8;
                C1 = 0;
                BC = reg16high(BC, C1);
                int[] var54 = $37974(AF, BC, DE2, HL1, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
                AF = var54[0];
                BC = var54[1];
                DE = var54[2];
                HL = var54[3];
                IX = var54[4];
                IY = var54[5];
                A = var54[6];
                F = var54[7];
                B = var54[8];
                C = var54[9];
                D = var54[10];
                E = var54[11];
                H = var54[12];
                L = var54[13];
                IXL = var54[14];
                IXH = var54[15];
                IYL = var54[16];
                IYH = var54[17];

                do {
                  A = mem[34276];
                  AF = reg16low(AF, A);
                  BC = reg16high(BC, A);
                  B2 = 130;
                  BC = reg16low(BC, B2);
                  A = mem[BC];
                  AF = reg16low(AF, A);
                  A |= 15;
                  AF = reg16low(AF, A);
                  L = A;
                  HL = reg16high(HL, A);
                  BC = BC + 1 & 65535;
                  C = BC & 255;
                  B = BC >> 8;
                  A = mem[BC];
                  AF = reg16low(AF, A);
                  A = A - 32 & 255;
                  AF = reg16low(AF, A);
                  HL = reg16low(HL, A);
                  DE2 = 40000;
                  E = DE2 & 255;
                  D = DE2 >> 8;
                  C1 = 0;
                  BC = reg16high(BC, C1);
                  int[] var55 = $37974(AF, BC, DE2, HL, IX, IY, A, F, B, C1, D, E, A, L, IXL, IXH, IYL, IYH);
                  AF = var55[0];
                  BC = var55[1];
                  DE = var55[2];
                  HL = var55[3];
                  IX = var55[4];
                  IY = var55[5];
                  A = var55[6];
                  F = var55[7];
                  B = var55[8];
                  C = var55[9];
                  D = var55[10];
                  E = var55[11];
                  H = var55[12];
                  L = var55[13];
                  IXL = var55[14];
                  IXH = var55[15];
                  IYL = var55[16];
                  IYH = var55[17];
                  A = mem[34276];
                  AF = reg16low(AF, A);
                  A = ~A;
                  AF = reg16low(AF, A);
                  E = A;
                  reg16high(DE, A);
                  A ^= A;
                  AF = reg16low(AF, A);
                  BC = 64;
                  C = BC & 255;
                  B = BC >> 8;

                  do {
                    A ^= 24;
                    AF = reg16low(AF, A);
                    B = E;
                    BC = reg16low(BC, E);

                    do {
                      B = B - 1 & 255;
                      BC = reg16low(BC, B);
                    } while(B != 0);

                    C = C - 1 & 255;
                    BC = reg16high(BC, C);
                  } while(C != 0);

                  HL1 = 22528;
                  L = HL1 & 255;
                  H = HL1 >> 8;
                  DE1 = 22529;
                  E = DE1 & 255;
                  D = DE1 >> 8;
                  BC1 = 511;
                  C = BC1 & 255;
                  B = BC1 >> 8;
                  A = mem[34276];
                  AF = reg16low(AF, A);
                  A &= 12;
                  AF = reg16low(AF, A);
                  int[] var56 = rlc(A, F);
                  A = var56[0];
                  AF = reg16low(AF, A);
                  F = var56[1];
                  A |= 71;
                  AF = reg16low(AF, A);
                  mem[HL1] = A;
                  int[] var57 = ldir(HL1, DE1, BC1);
                  HL = var57[0];
                  DE = var57[1];
                  BC = var57[2];
                  A &= 250;
                  AF = reg16low(AF, A);
                  A |= 2;
                  AF = reg16low(AF, A);
                  mem[22991] = A;
                  mem[22992] = A;
                  mem[23023] = A;
                  mem[23024] = A;
                  A = mem[34276];
                  AF = reg16low(AF, A);
                  A = A + 4 & 255;
                  AF = reg16low(AF, A);
                  mem[34276] = A;
                } while(A != 196);

                IX1 = 34164;
                IXL = IX1 & 255;
                IXH = IX1 >> 8;
                C1 = 4;
                BC = reg16high(BC, C1);
                DE1 = 16586;
                E = DE1 & 255;
                D = DE1 >> 8;
                int[] var58 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
                AF = var58[0];
                BC = var58[1];
                DE = var58[2];
                HL = var58[3];
                IX = var58[4];
                IY = var58[5];
                A = var58[6];
                F = var58[7];
                B = var58[8];
                C = var58[9];
                D = var58[10];
                E = var58[11];
                H = var58[12];
                L = var58[13];
                IXL = var58[14];
                IXH = var58[15];
                IYL = var58[16];
                IYH = var58[17];
                IX1 = 34168;
                IXL = IX1 & 255;
                IXH = IX1 >> 8;
                C1 = 4;
                BC = reg16high(BC, C1);
                DE1 = 16594;
                E = DE1 & 255;
                D = DE1 >> 8;
                int[] var59 = $38528(AF, BC, DE1, HL, IX1, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
                AF = var59[0];
                BC = var59[1];
                DE = var59[2];
                HL = var59[3];
                IX = var59[4];
                IY = var59[5];
                A = var59[6];
                F = var59[7];
                B = var59[8];
                C = var59[9];
                D = var59[10];
                E = var59[11];
                H = var59[12];
                L = var59[13];
                IXL = var59[14];
                IXH = var59[15];
                IYL = var59[16];
                IYH = var59[17];
                BC = 0;
                C = BC & 255;
                B = BC >> 8;
                D = 6;
                DE = reg16low(DE, D);

                while(true) {
                  do {
                    B = B - 1 & 255;
                    BC = reg16low(BC, B);
                  } while(B != 0);

                  AF = reg16low(AF, C);
                  A = C & 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22730] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22731] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22732] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22733] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22738] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22739] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22740] = A;
                  A = A + 1 & 255;
                  AF = reg16low(AF, A);
                  A &= 7;
                  AF = reg16low(AF, A);
                  A |= 64;
                  AF = reg16low(AF, A);
                  mem[22741] = A;
                  C = C - 1 & 255;
                  BC = reg16high(BC, C);
                  if(C == 0) {
                    D = D - 1 & 255;
                    DE = reg16low(DE, D);
                    if(D == 0) {
                      continue label295;
                    }
                  }
                }
              }

              int var60 = mem[HL2] - 1 & 255;
              mem[HL2] = var60;
              HL2 = 34263;
              L = HL2 & 255;
              H = HL2 >> 8;
              DE2 = 34255;
              E = DE2 & 255;
              D = DE2 >> 8;
              BC2 = 7;
              C = BC2 & 255;
              B = BC2 >> 8;
              int[] var61 = ldir(HL2, DE2, BC2);
              HL = var61[0];
              DE = var61[1];
              BC = var61[2];
              break;
            }

            B2 = 191;
            BC = reg16low(BC, B2);
            HL = 34274;
            L = HL & 255;
            H = HL >> 8;
            A = in(BC);
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            if(A != 31) {
              if((mem[HL] & 1) == 0) {
                A = mem[HL];
                AF = reg16low(AF, A);
                A ^= 3;
                AF = reg16low(AF, A);
                mem[HL] = A;
              }
            } else {
              int var62 = mem[HL] & -2;
              mem[HL] = var62;
            }

            if((mem[HL] & 2) == 0) {
              A ^= A;
              AF = reg16low(AF, A);
              mem[34272] = A;
              A = mem[34273];
              AF = reg16low(AF, A);
              A = A + 1 & 255;
              AF = reg16low(AF, A);
              mem[34273] = A;
              A &= 126;
              AF = reg16low(AF, A);
              A = rrc(A);
              AF = reg16low(AF, A);
              DE = reg16high(DE, A);
              D1 = 0;
              DE = reg16low(DE, D1);
              HL2 = 34399;
              L = HL2 & 255;
              H = HL2 >> 8;
              HL = HL2 + DE & 65535;
              L = HL & 255;
              H = HL >> 8;
              A = mem[34252];
              AF = reg16low(AF, A);
              int[] var70 = rlc(A, F);
              A = var70[0];
              AF = reg16low(AF, A);
              F = var70[1];
              int[] var71 = rlc(A, F);
              A = var71[0];
              AF = reg16low(AF, A);
              F = var71[1];
              A = A - 28 & 255;
              AF = reg16low(AF, A);
              A = -A & 255;
              AF = reg16low(AF, A);
              int var72 = mem[HL];
              A = A + var72 & 255;
              AF = reg16low(AF, A);
              D = A;
              DE = reg16low(DE, A);
              A = mem[32990];
              AF = reg16low(AF, A);
              E = D;
              DE = reg16high(DE, D);
              BC = 3;
              C = BC & 255;
              B = BC >> 8;

              do {
                do {
                  E = E - 1 & 255;
                  DE = reg16high(DE, E);
                  if(E == 0) {
                    E = D;
                    DE = reg16high(DE, D);
                    A ^= 24;
                    AF = reg16low(AF, A);
                  }

                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);

                C = C - 1 & 255;
                BC = reg16high(BC, C);
              } while(C != 0);
            }

            BC = 61438;
            C = BC & 255;
            B = BC >> 8;
            A = in(BC);
            AF = reg16low(AF, A);
            if((A & 2) == 0) {
              A &= 16;
              AF = reg16low(AF, A);
              A ^= 16;
              AF = reg16low(AF, A);
              int[] var69 = rlc(A, F);
              A = var69[0];
              AF = reg16low(AF, A);
              F = var69[1];
              D = A;
              DE = reg16low(DE, A);
              A = mem[34275];
              AF = reg16low(AF, A);
              if(A == 10) {
                BC3 = 63486;
                C = BC3 & 255;
                B = BC3 >> 8;
                A = in(BC3);
                AF = reg16low(AF, A);
                A = ~A;
                AF = reg16low(AF, A);
                A &= 31;
                AF = reg16low(AF, A);
                A |= D;
                AF = reg16low(AF, A);
                mem[33824] = A;
                break;
              }
            }

            A = mem[34275];
            AF = reg16low(AF, A);
            if(A != 10) {
              A = mem[33824];
              AF = reg16low(AF, A);
              if(A == 28) {
                A = mem[34255];
                AF = reg16low(AF, A);
                if(A == 208) {
                  A = mem[34275];
                  AF = reg16low(AF, A);
                  int[] var63 = rlc(A, F);
                  A = var63[0];
                  AF = reg16low(AF, A);
                  F = var63[1];
                  E = A;
                  DE = reg16high(DE, A);
                  D = 0;
                  DE = reg16low(DE, D);
                  IX1 = 34279;
                  IXL = IX1 & 255;
                  IXH = IX1 >> 8;
                  IX = IX1 + DE & 65535;
                  IXL = IX & 255;
                  IXH = IX >> 8;
                  BC = 64510;
                  C = BC & 255;
                  B = BC >> 8;
                  A = in(BC);
                  AF = reg16low(AF, A);
                  A &= 31;
                  AF = reg16low(AF, A);
                  int var64 = mem[IX];
                  if(A != var64) {
                    if(A != 31) {
                      int var68 = mem[IX];
                      if(A != var68) {
                        A ^= A;
                        AF = reg16low(AF, A);
                        mem[34275] = A;
                      }
                    }
                  } else {
                    B = 223;
                    BC = reg16low(BC, B);
                    A = in(BC);
                    AF = reg16low(AF, A);
                    A &= 31;
                    AF = reg16low(AF, A);
                    int var65 = IX + 1;
                    int var66 = mem[var65];
                    if(A != var66) {
                      if(A != 31) {
                        int var67 = mem[IX];
                        if(A != var67) {
                          A ^= A;
                          AF = reg16low(AF, A);
                          mem[34275] = A;
                        }
                      }
                    } else {
                      A = mem[34275];
                      AF = reg16low(AF, A);
                      A = A + 1 & 255;
                      AF = reg16low(AF, A);
                      mem[34275] = A;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public int[] $35211(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[34252];
    AF = reg16low(AF, A);
    HL = 20640;
    L = HL & 255;
    H = HL >> 8;
    A |= A;
    AF = reg16low(AF, A);
    if(A << 1 == 0) {
      int[] var24 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var24;
    } else {
      B = A;
      BC = reg16low(BC, A);

      do {
        byte C1 = 0;
        BC = reg16high(BC, C1);
        push(HL);
        push(BC);
        A = mem[34273];
        AF = reg16low(AF, A);
        int[] var19 = rlc(A, F);
        A = var19[0];
        AF = reg16low(AF, A);
        F = var19[1];
        int[] var20 = rlc(A, F);
        A = var20[0];
        AF = reg16low(AF, A);
        F = var20[1];
        int[] var21 = rlc(A, F);
        A = var21[0];
        AF = reg16low(AF, A);
        F = var21[1];
        A &= 96;
        AF = reg16low(AF, A);
        DE = reg16high(DE, A);
        short D1 = 157;
        DE = reg16low(DE, D1);
        int[] var22 = $37974(AF, BC, DE, HL, IX, IY, A, F, B, C1, D1, A, H, L, IXL, IXH, IYL, IYH);
        AF = var22[0];
        BC = var22[1];
        DE = var22[2];
        HL = var22[3];
        IX = var22[4];
        IY = var22[5];
        A = var22[6];
        F = var22[7];
        B = var22[8];
        C = var22[9];
        D = var22[10];
        E = var22[11];
        H = var22[12];
        L = var22[13];
        IXL = var22[14];
        IXH = var22[15];
        IYL = var22[16];
        IYH = var22[17];
        BC = pop();
        C = BC & 255;
        B = BC >> 8;
        HL = pop();
        L = HL & 255;
        H = HL >> 8;
        HL = HL + 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        HL = HL + 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);

      int[] var23 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var23;
    }
  }

  public int[] $35563(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    HL = 22528;
    L = HL & 255;
    H = HL >> 8;
    A = mem[HL];
    AF = reg16low(AF, A);
    A &= 7;
    AF = reg16low(AF, A);

    do {
      A = mem[HL];
      AF = reg16low(AF, A);
      A = A + 3 & 255;
      AF = reg16low(AF, A);
      A &= 7;
      AF = reg16low(AF, A);
      D = A;
      DE = reg16low(DE, A);
      A = mem[HL];
      AF = reg16low(AF, A);
      A = A + 24 & 255;
      AF = reg16low(AF, A);
      A &= 184;
      AF = reg16low(AF, A);
      A |= D;
      AF = reg16low(AF, A);
      mem[HL] = A;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      AF = reg16low(AF, H);
    } while(H != 91);

    F = H - 91;
    AF = reg16high(AF, F);
    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, H, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $36147(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    int[] var19 = $36203(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var19[0];
    BC = var19[1];
    DE = var19[2];
    HL = var19[3];
    IX = var19[4];
    IY = var19[5];
    A = var19[6];
    F = var19[7];
    B = var19[8];
    C = var19[9];
    D = var19[10];
    E = var19[11];
    H = var19[12];
    L = var19[13];
    IXL = var19[14];
    IXH = var19[15];
    IYL = var19[16];
    IYH = var19[17];
    short IX1 = 24064;
    IXL = IX1 & 255;
    IXH = IX1 >> 8;
    byte A1 = 112;
    AF = reg16low(AF, A1);
    mem[36189] = A1;
    int[] var20 = $36171(AF, BC, DE, HL, IX1, IY, A1, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var20[0];
    BC = var20[1];
    DE = var20[2];
    HL = var20[3];
    IX = var20[4];
    IY = var20[5];
    A = var20[6];
    F = var20[7];
    B = var20[8];
    C = var20[9];
    D = var20[10];
    E = var20[11];
    H = var20[12];
    L = var20[13];
    IXL = var20[14];
    IXH = var20[15];
    IYL = var20[16];
    IYH = var20[17];
    IX1 = 24320;
    IXL = IX1 & 255;
    IXH = IX1 >> 8;
    A1 = 120;
    AF = reg16low(AF, A1);
    mem[36189] = A1;
    int[] var21 = $36171(AF, BC, DE, HL, IX1, IY, A1, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var21[0];
    BC = var21[1];
    DE = var21[2];
    HL = var21[3];
    IX = var21[4];
    IY = var21[5];
    A = var21[6];
    F = var21[7];
    B = var21[8];
    C = var21[9];
    D = var21[10];
    E = var21[11];
    H = var21[12];
    L = var21[13];
    IXL = var21[14];
    IXH = var21[15];
    IYL = var21[16];
    IYH = var21[17];
    int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var22;
  }

  public int[] $36171(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    C = 0;
    reg16high(BC, C);

    do {
      E = C;
      DE = reg16high(DE, C);
      A = mem[IX];
      AF = reg16low(AF, A);
      char HL1 = 32928;
      L = HL1 & 255;
      H = HL1 >> 8;
      byte BC1 = 54;
      C = BC1 & 255;
      B = BC1 >> 8;
      int[] var19 = cpir(HL1, BC1, A);
      HL = var19[0];
      BC = var19[1];
      BC = reg16high(BC, E);
      B = 8;
      BC = reg16low(BC, B);
      D = mem[36189];
      DE = reg16low(DE, D);

      do {
        A = mem[HL];
        AF = reg16low(AF, A);
        mem[DE] = A;
        HL = HL + 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        D = D + 1 & 255;
        DE = reg16low(DE, D);
        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);

      IX = IX + 1 & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
      C = E + 1 & 255;
      BC = reg16high(BC, C);
    } while(C != 0);

    int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var20;
  }

  public int[] $36203(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    HL = 32768;
    L = HL & 255;
    H = HL >> 8;
    IX = 24064;
    IXL = IX & 255;
    IXH = IX >> 8;

    do {
      A = mem[HL];
      AF = reg16low(AF, A);
      int[] var19 = rlc(A, F);
      A = var19[0];
      AF = reg16low(AF, A);
      F = var19[1];
      int[] var20 = rlc(A, F);
      A = var20[0];
      AF = reg16low(AF, A);
      F = var20[1];
      int[] var21 = $36288(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var21[0];
      BC = var21[1];
      DE = var21[2];
      HL = var21[3];
      IX = var21[4];
      IY = var21[5];
      A = var21[6];
      F = var21[7];
      B = var21[8];
      C = var21[9];
      D = var21[10];
      E = var21[11];
      H = var21[12];
      L = var21[13];
      IXL = var21[14];
      IXH = var21[15];
      IYL = var21[16];
      IYH = var21[17];
      A = mem[HL];
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      int[] var22 = $36288(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var22[0];
      BC = var22[1];
      DE = var22[2];
      HL = var22[3];
      IX = var22[4];
      IY = var22[5];
      A = var22[6];
      F = var22[7];
      B = var22[8];
      C = var22[9];
      D = var22[10];
      E = var22[11];
      H = var22[12];
      L = var22[13];
      IXL = var22[14];
      IXH = var22[15];
      IYL = var22[16];
      IYH = var22[17];
      A = mem[HL];
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      A = rrc(A);
      AF = reg16low(AF, A);
      int[] var23 = $36288(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var23[0];
      BC = var23[1];
      DE = var23[2];
      HL = var23[3];
      IX = var23[4];
      IY = var23[5];
      A = var23[6];
      F = var23[7];
      B = var23[8];
      C = var23[9];
      D = var23[10];
      E = var23[11];
      H = var23[12];
      L = var23[13];
      IXL = var23[14];
      IXH = var23[15];
      IYL = var23[16];
      IYH = var23[17];
      A = mem[HL];
      AF = reg16low(AF, A);
      int[] var24 = $36288(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var24[0];
      BC = var24[1];
      DE = var24[2];
      HL = var24[3];
      IX = var24[4];
      IY = var24[5];
      A = var24[6];
      F = var24[7];
      B = var24[8];
      C = var24[9];
      D = var24[10];
      E = var24[11];
      H = var24[12];
      L = var24[13];
      IXL = var24[14];
      IXH = var24[15];
      IYL = var24[16];
      IYH = var24[17];
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      AF = reg16low(AF, L);
      A = L & 128;
      AF = reg16low(AF, A);
    } while(A << 1 == 0);

    A = mem[32985];
    AF = reg16low(AF, A);
    A |= A;
    AF = reg16low(AF, A);
    if(A << 1 != 0) {
      HL = mem16(32983);
      L = HL & 255;
      H = HL >> 8;
      B = A;
      BC = reg16low(BC, A);
      A = mem[32973];
      AF = reg16low(AF, A);

      do {
        mem[HL] = A;
        HL = HL + 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);
    }

    A = mem[32989];
    AF = reg16low(AF, A);
    A |= A;
    AF = reg16low(AF, A);
    if(A << 1 == 0) {
      int[] var27 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var27;
    } else {
      HL = mem16(32987);
      L = HL & 255;
      H = HL >> 8;
      A = mem[32986];
      AF = reg16low(AF, A);
      A &= 1;
      AF = reg16low(AF, A);
      int[] var25 = rlc(A, F);
      A = var25[0];
      AF = reg16low(AF, A);
      F = var25[1];
      A = A + 223 & 255;
      AF = reg16low(AF, A);
      E = A;
      DE = reg16high(DE, A);
      short D1 = 255;
      DE = reg16low(DE, D1);
      A = mem[32989];
      AF = reg16low(AF, A);
      B = A;
      BC = reg16low(BC, A);
      A = mem[32964];
      AF = reg16low(AF, A);

      do {
        mem[HL] = A;
        HL = HL + DE & 65535;
        L = HL & 255;
        H = HL >> 8;
        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);

      int[] var26 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D1, E, H, L, IXL, IXH, IYL, IYH};
      return var26;
    }
  }

  public int[] $36288(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A &= 3;
    AF = reg16low(AF, A);
    C = A;
    BC = reg16high(BC, A);
    int[] var19 = rlc(A, F);
    A = var19[0];
    AF = reg16low(AF, A);
    F = var19[1];
    int[] var20 = rlc(A, F);
    A = var20[0];
    AF = reg16low(AF, A);
    F = var20[1];
    int[] var21 = rlc(A, F);
    A = var21[0];
    AF = reg16low(AF, A);
    F = var21[1];
    A = A + C & 255;
    AF = reg16low(AF, A);
    A = A + 160 & 255;
    AF = reg16low(AF, A);
    E = A;
    DE = reg16high(DE, A);
    short D1 = 128;
    DE = reg16low(DE, D1);
    A = mem[DE];
    AF = reg16low(AF, A);
    mem[IX] = A;
    IX = IX + 1 & 65535;
    IXL = IX & 255;
    IXH = IX >> 8;
    int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D1, E, H, L, IXL, IXH, IYL, IYH};
    return var22;
  }

  public int[] $36307(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    char HL1;
    byte A2;
    label216: {
      char BC1;
      label213: {
        label228: {
          A = mem[34262];
          AF = reg16low(AF, A);
          A = A - 1 & 255;
          AF = reg16low(AF, A);
          if((A & 128) != 0) {
            A = mem[34257];
            AF = reg16low(AF, A);
            if(A == 1) {
              A = mem[34261];
              AF = reg16low(AF, A);
              A &= 254;
              AF = reg16low(AF, A);
              A = A - 8 & 255;
              AF = reg16low(AF, A);
              HL1 = 34255;
              L = HL1 & 255;
              H = HL1 >> 8;
              int var62 = mem[HL1];
              A = A + var62 & 255;
              AF = reg16low(AF, A);
              mem[HL1] = A;
              if(A >= 240) {
                int[] var63 = new int[]{AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
                return var63;
              }

              F = A - 240;
              AF = reg16high(AF, F);
              int[] var64 = $36508(AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
              AF = var64[0];
              BC = var64[1];
              DE = var64[2];
              HL = var64[3];
              IX = var64[4];
              IY = var64[5];
              A = var64[6];
              F = var64[7];
              B = var64[8];
              C = var64[9];
              D = var64[10];
              E = var64[11];
              H = var64[12];
              L = var64[13];
              IXL = var64[14];
              IXH = var64[15];
              IYL = var64[16];
              IYH = var64[17];
              A = mem[32946];
              AF = reg16low(AF, A);
              int var65 = mem[HL];
              if(A == var65) {
                break label216;
              }

              HL = HL + 1 & 65535;
              L = HL & 255;
              H = HL >> 8;
              int var69 = mem[HL];
              if(A == var69) {
                break label216;
              }

              A = mem[34261];
              AF = reg16low(AF, A);
              A = A + 1 & 255;
              AF = reg16low(AF, A);
              mem[34261] = A;
              int var70 = A - 8;
              A = var70 & 255;
              AF = reg16low(AF, A);
              if(var70 < 0) {
                A = -A & 255;
                AF = reg16low(AF, A);
              }

              A = A + 1 & 255;
              AF = reg16low(AF, A);
              int[] var71 = rlc(A, F);
              A = var71[0];
              AF = reg16low(AF, A);
              F = var71[1];
              int[] var72 = rlc(A, F);
              A = var72[0];
              AF = reg16low(AF, A);
              F = var72[1];
              int[] var73 = rlc(A, F);
              A = var73[0];
              AF = reg16low(AF, A);
              F = var73[1];
              D = A;
              DE = reg16low(DE, A);
              C = 32;
              BC = reg16high(BC, C);
              A = mem[32990];
              AF = reg16low(AF, A);

              do {
                A ^= 24;
                AF = reg16low(AF, A);
                B = D;
                BC = reg16low(BC, D);

                do {
                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);

                C = C - 1 & 255;
                BC = reg16high(BC, C);
              } while(C != 0);

              A = mem[34261];
              AF = reg16low(AF, A);
              if(A == 18) {
                A2 = 6;
                AF = reg16low(AF, A2);
                mem[34257] = A2;
                int[] var74 = new int[]{AF, BC, DE, HL, IX, IY, A2, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
                return var74;
              }

              if(A != 16 && A != 13) {
                break label213;
              }
            }

            A = mem[34255];
            AF = reg16low(AF, A);
            A &= 14;
            AF = reg16low(AF, A);
            if(A << 1 != 0) {
              break label228;
            }

            HL = mem16(34259);
            L = HL & 255;
            H = HL >> 8;
            DE = 64;
            E = DE & 255;
            D = DE >> 8;
            HL = HL + DE & 65535;
            L = HL & 255;
            H = HL >> 8;
            if((H & 2) != 0) {
              A = mem[33004];
              AF = reg16low(AF, A);
              mem[33824] = A;
              A ^= A;
              AF = reg16low(AF, A);
              mem[34255] = A;
              A = mem[34257];
              AF = reg16low(AF, A);
              if(A < 11) {
                A2 = 2;
                AF = reg16low(AF, A2);
                mem[34257] = A2;
              }

              A = mem[34259];
              AF = reg16low(AF, A);
              A &= 31;
              AF = reg16low(AF, A);
              mem[34259] = A;
              A2 = 92;
              AF = reg16low(AF, A2);
              mem[34260] = A2;
              nextAddress = 38134;
              int[] var57 = new int[]{AF, BC, DE, HL, IX, IY, A2, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
              return var57;
            }

            A = mem[32955];
            AF = reg16low(AF, A);
            int var58 = mem[HL];
            if(A == var58) {
              break label228;
            }

            HL = HL + 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            A = mem[32955];
            AF = reg16low(AF, A);
            int var59 = mem[HL];
            if(A == var59) {
              break label228;
            }

            A = mem[32928];
            AF = reg16low(AF, A);
            int var60 = mem[HL];
            F = A - var60;
            AF = reg16high(AF, F);
            HL = HL - 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            if(F == 0) {
              int var61 = mem[HL];
              if(A == var61) {
                break label228;
              }
            }
          }

          E = 255;
          DE = reg16high(DE, E);
          A = mem[34262];
          AF = reg16low(AF, A);
          A = A - 1 & 255;
          AF = reg16low(AF, A);
          if((A & 128) != 0) {
            label227: {
              A = mem[34257];
              AF = reg16low(AF, A);
              if(A >= 12) {
                nextAddress = 37048;
                int[] var48 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
                return var48;
              }

              A ^= A;
              AF = reg16low(AF, A);
              mem[34257] = A;
              A = mem[32973];
              AF = reg16low(AF, A);
              int var46 = mem[HL];
              if(A != var46) {
                HL = HL + 1 & 65535;
                L = HL & 255;
                H = HL >> 8;
                int var47 = mem[HL];
                if(A != var47) {
                  break label227;
                }
              }

              A = mem[32982];
              AF = reg16low(AF, A);
              A = A - 3 & 255;
              AF = reg16low(AF, A);
              E = A;
              DE = reg16high(DE, A);
            }
          }

          BC1 = 57342;
          C = BC1 & 255;
          B = BC1 >> 8;
          A = in(BC1);
          AF = reg16low(AF, A);
          A &= 31;
          AF = reg16low(AF, A);
          A |= 32;
          AF = reg16low(AF, A);
          A &= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          A = mem[34271];
          AF = reg16low(AF, A);
          A &= 2;
          AF = reg16low(AF, A);
          A = rrc(A);
          AF = reg16low(AF, A);
          A ^= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          BC1 = 64510;
          C = BC1 & 255;
          B = BC1 >> 8;
          A = in(BC1);
          AF = reg16low(AF, A);
          A &= 31;
          AF = reg16low(AF, A);
          int[] var19 = rlc(A, F);
          A = var19[0];
          AF = reg16low(AF, A);
          F = var19[1];
          A |= 1;
          AF = reg16low(AF, A);
          A &= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          short B1 = 231;
          BC = reg16low(BC1, B1);
          A = in(BC);
          AF = reg16low(AF, A);
          A = rrc(A);
          AF = reg16low(AF, A);
          A |= 247;
          AF = reg16low(AF, A);
          A &= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          B1 = 239;
          BC = reg16low(BC, B1);
          A = in(BC);
          AF = reg16low(AF, A);
          A |= 251;
          AF = reg16low(AF, A);
          A &= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          A = in(BC);
          AF = reg16low(AF, A);
          A = rrc(A);
          AF = reg16low(AF, A);
          A |= 251;
          AF = reg16low(AF, A);
          A &= E;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          A = mem[34254];
          AF = reg16low(AF, A);
          A |= A;
          AF = reg16low(AF, A);
          if(A << 1 != 0) {
            BC = 31;
            C = BC & 255;
            B = BC >> 8;
            A = in(BC);
            AF = reg16low(AF, A);
            A &= 3;
            AF = reg16low(AF, A);
            A = ~A;
            AF = reg16low(AF, A);
            A &= E;
            AF = reg16low(AF, A);
            E = A;
            DE = reg16high(DE, A);
          }

          C = 0;
          BC = reg16high(BC, C);
          AF = reg16low(AF, E);
          A = E & 42;
          AF = reg16low(AF, A);
          if(A != 42) {
            C = 4;
            BC = reg16high(BC, C);
            A ^= A;
            AF = reg16low(AF, A);
            mem[34272] = A;
          }

          AF = reg16low(AF, E);
          A = E & 21;
          AF = reg16low(AF, A);
          if(A != 21) {
            C |= 8;
            BC = reg16high(BC, C);
            A ^= A;
            AF = reg16low(AF, A);
            mem[34272] = A;
          }

          A = mem[34256];
          AF = reg16low(AF, A);
          A = A + C & 255;
          AF = reg16low(AF, A);
          BC = reg16high(BC, A);
          byte B2 = 0;
          BC = reg16low(BC, B2);
          HL1 = 33825;
          L = HL1 & 255;
          H = HL1 >> 8;
          HL = HL1 + BC & 65535;
          L = HL & 255;
          H = HL >> 8;
          A = mem[HL];
          AF = reg16low(AF, A);
          mem[34256] = A;
          BC = 32510;
          C = BC & 255;
          B = BC >> 8;
          A = in(BC);
          AF = reg16low(AF, A);
          A &= 31;
          AF = reg16low(AF, A);
          if(A == 31) {
            B = 239;
            BC = reg16low(BC, B);
            A = in(BC);
            AF = reg16low(AF, A);
            if((A & 1) != 0) {
              A = mem[34254];
              AF = reg16low(AF, A);
              A |= A;
              AF = reg16low(AF, A);
              if(A << 1 == 0) {
                break label213;
              }

              BC = 31;
              C = BC & 255;
              B = BC >> 8;
              A = in(BC);
              AF = reg16low(AF, A);
              if((A & 16) == 0) {
                break label213;
              }
            }
          }

          A = mem[34271];
          AF = reg16low(AF, A);
          if((A & 2) == 0) {
            A ^= A;
            AF = reg16low(AF, A);
            mem[34261] = A;
            mem[34272] = A;
            A = A + 1 & 255;
            AF = reg16low(AF, A);
            mem[34257] = A;
            A = mem[34262];
            AF = reg16low(AF, A);
            A = A - 1 & 255;
            AF = reg16low(AF, A);
            if((A & 128) == 0) {
              short A1 = 240;
              AF = reg16low(AF, A1);
              mem[34262] = A1;
              A = mem[34255];
              AF = reg16low(AF, A);
              A &= 240;
              AF = reg16low(AF, A);
              F = A << 1;
              AF = reg16high(AF, F);
              mem[34255] = A;
              HL1 = 34256;
              L = HL1 & 255;
              H = HL1 >> 8;
              int var44 = mem[HL1] | 2;
              mem[HL1] = var44;
              int[] var45 = new int[]{AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
              return var45;
            }
          }
          break label213;
        }

        A = mem[34257];
        AF = reg16low(AF, A);
        if(A != 1) {
          HL1 = 34256;
          L = HL1 & 255;
          H = HL1 >> 8;
          int var49 = mem[HL1] & -3;
          mem[HL1] = var49;
          A = mem[34257];
          AF = reg16low(AF, A);
          A |= A;
          AF = reg16low(AF, A);
          if(A << 1 == 0) {
            A2 = 2;
            AF = reg16low(AF, A2);
            mem[34257] = A2;
            int[] var50 = new int[]{AF, BC, DE, HL1, IX, IY, A2, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
            return var50;
          }

          A = A + 1 & 255;
          AF = reg16low(AF, A);
          if(A == 16) {
            A = 12;
            AF = reg16low(AF, A);
          }

          mem[34257] = A;
          int[] var51 = rlc(A, F);
          A = var51[0];
          AF = reg16low(AF, A);
          F = var51[1];
          int[] var52 = rlc(A, F);
          A = var52[0];
          AF = reg16low(AF, A);
          F = var52[1];
          int[] var53 = rlc(A, F);
          A = var53[0];
          AF = reg16low(AF, A);
          F = var53[1];
          int[] var54 = rlc(A, F);
          A = var54[0];
          AF = reg16low(AF, A);
          F = var54[1];
          D = A;
          DE = reg16low(DE, A);
          C = 32;
          BC = reg16high(BC, C);
          A = mem[32990];
          AF = reg16low(AF, A);

          do {
            A ^= 24;
            AF = reg16low(AF, A);
            B = D;
            BC = reg16low(BC, D);

            do {
              B = B - 1 & 255;
              BC = reg16low(BC, B);
            } while(B != 0);

            C = C - 1 & 255;
            BC = reg16high(BC, C);
          } while(C != 0);

          A = mem[34255];
          AF = reg16low(AF, A);
          A = A + 8 & 255;
          AF = reg16low(AF, A);
          mem[34255] = A;
          int[] var55 = $36508(AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var55[0];
          BC = var55[1];
          DE = var55[2];
          HL = var55[3];
          IX = var55[4];
          IY = var55[5];
          A = var55[6];
          F = var55[7];
          B = var55[8];
          C = var55[9];
          D = var55[10];
          E = var55[11];
          H = var55[12];
          L = var55[13];
          IXL = var55[14];
          IXH = var55[15];
          IYL = var55[16];
          IYH = var55[17];
          int[] var56 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var56;
        }
      }

      A = mem[34256];
      AF = reg16low(AF, A);
      A &= 2;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        int[] var43 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var43;
      }

      A = mem[34262];
      AF = reg16low(AF, A);
      A = A - 1 & 255;
      AF = reg16low(AF, A);
      if((A & 128) == 0) {
        int[] var42 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var42;
      }

      A = mem[34256];
      AF = reg16low(AF, A);
      A &= 1;
      AF = reg16low(AF, A);
      byte DE1;
      if(A << 1 != 0) {
        A = mem[34258];
        AF = reg16low(AF, A);
        A |= A;
        AF = reg16low(AF, A);
        if(A << 1 != 0) {
          A = A - 1 & 255;
          AF = reg16low(AF, A);
          AF = reg16high(AF, A);
          mem[34258] = A;
          int[] var41 = new int[]{AF, BC, DE, HL, IX, IY, A, A, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var41;
        }

        A = mem[34257];
        AF = reg16low(AF, A);
        BC1 = 0;
        C = BC1 & 255;
        B = BC1 >> 8;
        if(A == 0) {
          HL = mem16(34259);
          L = HL & 255;
          H = HL >> 8;
          BC1 = 0;
          C = BC1 & 255;
          B = BC1 >> 8;
          A = mem[32986];
          AF = reg16low(AF, A);
          A = A - 1 & 255;
          AF = reg16low(AF, A);
          A |= 161;
          AF = reg16low(AF, A);
          A ^= 224;
          AF = reg16low(AF, A);
          E = A;
          DE = reg16high(DE, A);
          D = 0;
          DE = reg16low(DE, D);
          HL = HL + DE & 65535;
          L = HL & 255;
          H = HL >> 8;
          A = mem[32964];
          AF = reg16low(AF, A);
          int var40 = mem[HL];
          if(A == var40) {
            BC1 = 32;
            C = BC1 & 255;
            B = BC1 >> 8;
            A = mem[32986];
            AF = reg16low(AF, A);
            A |= A;
            AF = reg16low(AF, A);
            if(A << 1 == 0) {
              BC1 = 65504;
              C = BC1 & 255;
              B = BC1 >> 8;
            }
          }
        }

        HL = mem16(34259);
        L = HL & 255;
        H = HL >> 8;
        AF = reg16low(AF, L);
        A = L & 31;
        AF = reg16low(AF, A);
        if(A << 1 != 0) {
          HL = HL + BC1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          HL = HL - 1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          DE1 = 32;
          E = DE1 & 255;
          D = DE1 >> 8;
          HL = HL + DE1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          A = mem[32946];
          AF = reg16low(AF, A);
          int var33 = mem[HL];
          if(A == var33) {
            int[] var39 = new int[]{AF, BC1, DE1, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
            return var39;
          }

          A = mem[34255];
          AF = reg16low(AF, A);
          int var34 = C >> 1;
          int var35 = C & 128;
          C = var34 | var35;
          BC = reg16high(BC1, C);
          A = A + C & 255;
          AF = reg16low(AF, A);
          B = A;
          BC = reg16low(BC, A);
          A &= 15;
          AF = reg16low(AF, A);
          if(A << 1 != 0) {
            A = mem[32946];
            AF = reg16low(AF, A);
            HL = HL + DE1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            int var37 = mem[HL];
            if(A == var37) {
              int[] var38 = new int[]{AF, BC, DE1, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
              return var38;
            }

            A |= A;
            AF = reg16low(AF, A);
            HL = HL - DE1 & 65535;
            L = HL & 255;
            H = HL >> 8;
          }

          A |= A;
          AF = reg16low(AF, A);
          HL = HL - DE1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          wMem16(34259, HL);
          AF = reg16low(AF, B);
          mem[34255] = B;
          A2 = 3;
          AF = reg16low(AF, A2);
          mem[34258] = A2;
          int[] var36 = new int[]{AF, BC, DE1, HL, IX, IY, A2, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var36;
        }

        A = mem[33001];
        AF = reg16low(AF, A);
        mem[33824] = A;
        A = mem[34259];
        AF = reg16low(AF, A);
        A |= 31;
        AF = reg16low(AF, A);
        A &= 254;
        AF = reg16low(AF, A);
        mem[34259] = A;
        nextAddress = 38043;
        int[] var32 = new int[]{AF, BC1, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var32;
      }

      A = mem[34258];
      AF = reg16low(AF, A);
      if(A != 3) {
        A = A + 1 & 255;
        AF = reg16low(AF, A);
        AF = reg16high(AF, A);
        mem[34258] = A;
        int[] var31 = new int[]{AF, BC, DE, HL, IX, IY, A, A, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var31;
      }

      A = mem[34257];
      AF = reg16low(AF, A);
      BC1 = 0;
      C = BC1 & 255;
      B = BC1 >> 8;
      A |= A;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        HL = mem16(34259);
        L = HL & 255;
        H = HL >> 8;
        A = mem[32986];
        AF = reg16low(AF, A);
        A = A - 1 & 255;
        AF = reg16low(AF, A);
        A |= 157;
        AF = reg16low(AF, A);
        A ^= 191;
        AF = reg16low(AF, A);
        E = A;
        DE = reg16high(DE, A);
        D = 0;
        DE = reg16low(DE, D);
        HL = HL + DE & 65535;
        L = HL & 255;
        H = HL >> 8;
        A = mem[32964];
        AF = reg16low(AF, A);
        int var30 = mem[HL];
        if(A == var30) {
          BC1 = 32;
          C = BC1 & 255;
          B = BC1 >> 8;
          A = mem[32986];
          AF = reg16low(AF, A);
          A |= A;
          AF = reg16low(AF, A);
          if(A << 1 != 0) {
            BC1 = 65504;
            C = BC1 & 255;
            B = BC1 >> 8;
          }
        }
      }

      HL = mem16(34259);
      L = HL & 255;
      H = HL >> 8;
      HL = HL + BC1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      AF = reg16low(AF, L);
      A = L & 31;
      AF = reg16low(AF, A);
      if(A << 1 != 0) {
        DE1 = 32;
        E = DE1 & 255;
        D = DE1 >> 8;
        A = mem[32946];
        AF = reg16low(AF, A);
        HL = HL + DE1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        int var21 = mem[HL];
        if(A == var21) {
          int[] var29 = new int[]{AF, BC1, DE1, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var29;
        }

        A = mem[34255];
        AF = reg16low(AF, A);
        int var22 = C >> 1;
        int var23 = C & 128;
        C = var22 | var23;
        BC = reg16high(BC1, C);
        A = A + C & 255;
        AF = reg16low(AF, A);
        B = A;
        BC = reg16low(BC, A);
        A &= 15;
        AF = reg16low(AF, A);
        if(A << 1 != 0) {
          A = mem[32946];
          AF = reg16low(AF, A);
          HL = HL + DE1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          int var27 = mem[HL];
          if(A == var27) {
            int[] var28 = new int[]{AF, BC, DE1, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
            return var28;
          }

          A |= A;
          AF = reg16low(AF, A);
          HL = HL - DE1 & 65535;
          L = HL & 255;
          H = HL >> 8;
        }

        A = mem[32946];
        AF = reg16low(AF, A);
        A |= A;
        AF = reg16low(AF, A);
        HL = HL - DE1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        int var24 = mem[HL];
        if(A == var24) {
          int[] var26 = new int[]{AF, BC, DE1, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var26;
        }

        HL = HL - 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        wMem16(34259, HL);
        A ^= A;
        AF = reg16low(AF, A);
        F = A << 1;
        AF = reg16high(AF, F);
        mem[34258] = A;
        AF = reg16low(AF, B);
        mem[34255] = B;
        int[] var25 = new int[]{AF, BC, DE1, HL, IX, IY, B, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var25;
      }

      A = mem[33002];
      AF = reg16low(AF, A);
      mem[33824] = A;
      A = mem[34259];
      AF = reg16low(AF, A);
      A &= 224;
      AF = reg16low(AF, A);
      mem[34259] = A;
      nextAddress = 38061;
      int[] var20 = new int[]{AF, BC1, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var20;
    }

    A = mem[34255];
    AF = reg16low(AF, A);
    A = A + 16 & 255;
    AF = reg16low(AF, A);
    A &= 240;
    AF = reg16low(AF, A);
    mem[34255] = A;
    int[] var66 = $36508(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var66[0];
    BC = var66[1];
    DE = var66[2];
    HL = var66[3];
    IX = var66[4];
    IY = var66[5];
    A = var66[6];
    F = var66[7];
    B = var66[8];
    C = var66[9];
    D = var66[10];
    E = var66[11];
    H = var66[12];
    L = var66[13];
    IXL = var66[14];
    IXH = var66[15];
    IYL = var66[16];
    IYH = var66[17];
    A2 = 2;
    AF = reg16low(AF, A2);
    mem[34257] = A2;
    HL1 = 34256;
    L = HL1 & 255;
    H = HL1 >> 8;
    int var67 = mem[HL1] & -3;
    mem[HL1] = var67;
    int[] var68 = new int[]{AF, BC, DE, HL1, IX, IY, A2, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var68;
  }

  public int[] $36508(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A &= 240;
    AF = reg16low(AF, A);
    L = A;
    HL = reg16high(HL, A);
    A ^= A;
    AF = reg16low(AF, A);
    F = A << 1;
    AF = reg16high(AF, F);
    int[] var19 = rl(L, F);
    L = var19[0];
    HL = reg16high(HL, L);
    F = var19[1];
    int var20 = A + 92;
    int var21 = carry(F) & 255;
    A = var20 + var21;
    AF = reg16low(AF, A);
    H = A;
    HL = reg16low(HL, A);
    A = mem[34259];
    AF = reg16low(AF, A);
    A &= 31;
    AF = reg16low(AF, A);
    A |= L;
    AF = reg16low(AF, A);
    F = A << 1;
    AF = reg16high(AF, F);
    HL = reg16high(HL, A);
    wMem16(34259, HL);
    int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, A, IXL, IXH, IYL, IYH};
    return var22;
  }

  public int[] $37056(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    IX = 33024;
    IXL = IX & 255;
    IXH = IX >> 8;

    while(true) {
      A = mem[IX];
      AF = reg16low(AF, A);
      if(A == 255) {
        int[] var46 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var46;
      }

      A &= 3;
      AF = reg16low(AF, A);
      if(A << 1 != 0) {
        if(A != 1) {
          if(A != 2) {
            if((mem[IX] & 128) != 0) {
              int var45 = IX + 1;
              A = mem[var45];
              AF = reg16low(AF, A);
              if((A & 128) != 0) {
                A = A - 2 & 255;
                AF = reg16low(AF, A);
                if(A < 148) {
                  A = A - 2 & 255;
                  AF = reg16low(AF, A);
                  if(A == 128) {
                    A ^= A;
                    AF = reg16low(AF, A);
                  }
                }
              } else {
                A = A + 2 & 255;
                AF = reg16low(AF, A);
                if(A < 18) {
                  A = A + 2 & 255;
                  AF = reg16low(AF, A);
                }
              }
            } else {
              int var41 = IX + 1;
              A = mem[var41];
              AF = reg16low(AF, A);
              if((A & 128) == 0) {
                A = A - 2 & 255;
                AF = reg16low(AF, A);
                if(A < 20) {
                  A = A - 2 & 255;
                  AF = reg16low(AF, A);
                  A |= A;
                  AF = reg16low(AF, A);
                  if(A << 1 == 0) {
                    A = 128;
                    AF = reg16low(AF, A);
                  }
                }
              } else {
                A = A + 2 & 255;
                AF = reg16low(AF, A);
                if(A < 146) {
                  A = A + 2 & 255;
                  AF = reg16low(AF, A);
                }
              }
            }

            int var42 = IX + 1;
            mem[var42] = A;
            A &= 127;
            AF = reg16low(AF, A);
            int var43 = IX + 7;
            int var44 = mem[var43];
            if(A == var44) {
              A = mem[IX];
              AF = reg16low(AF, A);
              A ^= 128;
              AF = reg16low(AF, A);
              mem[IX] = A;
            }
          } else {
            label81: {
              A = mem[IX];
              AF = reg16low(AF, A);
              A ^= 8;
              AF = reg16low(AF, A);
              mem[IX] = A;
              A &= 24;
              AF = reg16low(AF, A);
              if(A << 1 != 0) {
                A = mem[IX];
                AF = reg16low(AF, A);
                A = A + 32 & 255;
                AF = reg16low(AF, A);
                mem[IX] = A;
              }

              int var29 = IX + 3;
              A = mem[var29];
              AF = reg16low(AF, A);
              int var30 = IX + 4;
              int var31 = mem[var30];
              A = A + var31 & 255;
              AF = reg16low(AF, A);
              int var32 = IX + 3;
              mem[var32] = A;
              int var33 = IX + 7;
              int var34 = mem[var33];
              if(A < var34) {
                int var37 = IX + 6;
                int var38 = mem[var37];
                if(A != var38 && A >= var38) {
                  break label81;
                }

                int var39 = IX + 6;
                A = mem[var39];
                AF = reg16low(AF, A);
                int var40 = IX + 3;
                mem[var40] = A;
              }

              int var35 = IX + 4;
              A = mem[var35];
              AF = reg16low(AF, A);
              A = -A & 255;
              AF = reg16low(AF, A);
              int var36 = IX + 4;
              mem[var36] = A;
            }
          }
        } else if((mem[IX] & 128) == 0) {
          A = mem[IX];
          AF = reg16low(AF, A);
          A = A - 32 & 255;
          AF = reg16low(AF, A);
          A &= 127;
          AF = reg16low(AF, A);
          mem[IX] = A;
          if(A >= 96) {
            int var24 = IX + 2;
            A = mem[var24];
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            int var25 = IX + 6;
            int var26 = mem[var25];
            if(A != var26) {
              int var27 = IX + 2;
              int var28 = mem[var27] - 1 & 255;
              mem[var27] = var28;
            } else {
              mem[IX] = 129;
            }
          }
        } else {
          A = mem[IX];
          AF = reg16low(AF, A);
          A = A + 32 & 255;
          AF = reg16low(AF, A);
          A |= 128;
          AF = reg16low(AF, A);
          mem[IX] = A;
          if(A < 160) {
            int var19 = IX + 2;
            A = mem[var19];
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            int var20 = IX + 7;
            int var21 = mem[var20];
            if(A != var21) {
              int var22 = IX + 2;
              int var23 = mem[var22] + 1 & 255;
              mem[var22] = var23;
            } else {
              mem[IX] = 97;
            }
          }
        }
      }

      DE = 8;
      E = DE & 255;
      D = DE >> 8;
      IX = IX + DE & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
    }
  }

  public int[] $37310(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    IX = 33024;
    IXL = IX & 255;
    IXH = IX >> 8;

    while(true) {
      A = mem[IX];
      AF = reg16low(AF, A);
      if(A == 255) {
        int[] var102 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var102;
      }

      A &= 7;
      AF = reg16low(AF, A);
      if(A << 1 != 0) {
        byte C1;
        if(A != 3) {
          short D1;
          if(A != 4) {
            int var87 = IX + 3;
            E = mem[var87];
            DE = reg16high(DE, E);
            D1 = 130;
            DE = reg16low(DE, D1);
            A = mem[DE];
            AF = reg16low(AF, A);
            L = A;
            HL = reg16high(HL, A);
            int var88 = IX + 2;
            A = mem[var88];
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            A = A + L & 255;
            AF = reg16low(AF, A);
            HL = reg16high(HL, A);
            AF = reg16low(AF, E);
            int[] var89 = rlc(E, F);
            A = var89[0];
            AF = reg16low(AF, A);
            F = var89[1];
            A &= 1;
            AF = reg16low(AF, A);
            A |= 92;
            AF = reg16low(AF, A);
            HL = reg16low(HL, A);
            byte DE1 = 31;
            E = DE1 & 255;
            D = DE1 >> 8;
            int var90 = IX + 1;
            A = mem[var90];
            AF = reg16low(AF, A);
            A &= 15;
            AF = reg16low(AF, A);
            A = A + 56 & 255;
            AF = reg16low(AF, A);
            A &= 71;
            AF = reg16low(AF, A);
            C = A;
            BC = reg16high(BC, A);
            A = mem[HL];
            AF = reg16low(AF, A);
            A &= 56;
            AF = reg16low(AF, A);
            A ^= C;
            AF = reg16low(AF, A);
            C = A;
            BC = reg16high(BC, A);
            mem[HL] = A;
            HL = HL + 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            mem[HL] = A;
            HL = HL + DE1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            mem[HL] = A;
            HL = HL + 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            mem[HL] = A;
            int var91 = IX + 3;
            A = mem[var91];
            AF = reg16low(AF, A);
            A &= 14;
            AF = reg16low(AF, A);
            if(A << 1 != 0) {
              HL = HL + DE1 & 65535;
              L = HL & 255;
              H = HL >> 8;
              mem[HL] = C;
              HL = HL + 1 & 65535;
              L = HL & 255;
              H = HL >> 8;
              mem[HL] = C;
            }

            C1 = 1;
            BC = reg16high(BC, C1);
            int var92 = IX + 1;
            A = mem[var92];
            AF = reg16low(AF, A);
            int var93 = mem[IX];
            A &= var93;
            AF = reg16low(AF, A);
            int var94 = IX + 2;
            int var95 = mem[var94];
            A |= var95;
            AF = reg16low(AF, A);
            A &= 224;
            AF = reg16low(AF, A);
            E = A;
            DE = reg16high(DE1, A);
            int var96 = IX + 5;
            D = mem[var96];
            DE = reg16low(DE, D);
            short H1 = 130;
            HL = reg16low(HL, H1);
            int var97 = IX + 3;
            L = mem[var97];
            HL = reg16high(HL, L);
            int var98 = IX + 2;
            A = mem[var98];
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            int var99 = mem[HL];
            A |= var99;
            AF = reg16low(AF, A);
            HL = HL + 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            H = mem[HL];
            HL = reg16low(HL, H);
            HL = reg16high(HL, A);
            int[] var100 = $37974(AF, BC, DE, HL, IX, IY, A, F, B, C1, D, E, H, A, IXL, IXH, IYL, IYH);
            AF = var100[0];
            BC = var100[1];
            DE = var100[2];
            HL = var100[3];
            IX = var100[4];
            IY = var100[5];
            A = var100[6];
            F = var100[7];
            B = var100[8];
            C = var100[9];
            D = var100[10];
            E = var100[11];
            H = var100[12];
            L = var100[13];
            IXL = var100[14];
            IXH = var100[15];
            IYL = var100[16];
            IYH = var100[17];
            if(F != 0) {
              nextAddress = 37048;
              int[] var101 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
              return var101;
            }
          } else {
            if((mem[IX] & 128) == 0) {
              int var85 = IX + 4;
              int var86 = mem[var85] - 1 & 255;
              mem[var85] = var86;
              C = 44;
              BC = reg16high(BC, C);
            } else {
              int var70 = IX + 4;
              int var71 = mem[var70] + 1 & 255;
              mem[var70] = var71;
              C = 244;
              BC = reg16high(BC, C);
            }

            int var72 = IX + 4;
            A = mem[var72];
            AF = reg16low(AF, A);
            if(A != C) {
              A &= 224;
              AF = reg16low(AF, A);
              if(A << 1 == 0) {
                int var73 = IX + 2;
                E = mem[var73];
                DE = reg16high(DE, E);
                D1 = 130;
                DE = reg16low(DE, D1);
                A = mem[DE];
                AF = reg16low(AF, A);
                int var74 = IX + 4;
                int var75 = mem[var74];
                A = A + var75 & 255;
                AF = reg16low(AF, A);
                L = A;
                HL = reg16high(HL, A);
                AF = reg16low(AF, E);
                A = E & 128;
                AF = reg16low(AF, A);
                int[] var76 = rlc(A, F);
                A = var76[0];
                AF = reg16low(AF, A);
                F = var76[1];
                A |= 92;
                AF = reg16low(AF, A);
                HL = reg16low(HL, A);
                int var77 = IX + 5;
                mem[var77] = 0;
                A = mem[HL];
                AF = reg16low(AF, A);
                A &= 7;
                AF = reg16low(AF, A);
                if(A == 7) {
                  int var83 = IX + 5;
                  int var84 = mem[var83] - 1 & 255;
                  mem[var83] = var84;
                }

                A = mem[HL];
                AF = reg16low(AF, A);
                A |= 7;
                AF = reg16low(AF, A);
                mem[HL] = A;
                DE = DE + 1 & 65535;
                E = DE & 255;
                D = DE >> 8;
                A = mem[DE];
                AF = reg16low(AF, A);
                HL = reg16low(HL, A);
                H = A - 1 & 255;
                HL = reg16low(HL, H);
                int var78 = IX + 6;
                A = mem[var78];
                AF = reg16low(AF, A);
                mem[HL] = A;
                H = H + 1 & 255;
                HL = reg16low(HL, H);
                A = mem[HL];
                AF = reg16low(AF, A);
                int var79 = IX + 5;
                int var80 = mem[var79];
                A &= var80;
                AF = reg16low(AF, A);
                if(A << 1 != 0) {
                  nextAddress = 37048;
                  int[] var82 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
                  return var82;
                }

                mem[HL] = 255;
                H = H + 1 & 255;
                HL = reg16low(HL, H);
                int var81 = IX + 6;
                A = mem[var81];
                AF = reg16low(AF, A);
                mem[HL] = A;
              }
            } else {
              BC = 640;
              C = BC & 255;
              B = BC >> 8;
              A = mem[32990];
              AF = reg16low(AF, A);

              do {
                A ^= 24;
                AF = reg16low(AF, A);

                do {
                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);

                B = C;
                BC = reg16low(BC, C);
                C = C - 1 & 255;
                BC = reg16high(BC, C);
              } while(C != 0);
            }
          }
        } else {
          IY = 33280;
          IYL = IY & 255;
          IYH = IY >> 8;
          int var19 = IX + 9;
          mem[var19] = 0;
          int var20 = IX + 2;
          A = mem[var20];
          AF = reg16low(AF, A);
          int var21 = IX + 3;
          mem[var21] = A;
          int var22 = IX + 5;
          mem[var22] = 128;

          while(true) {
            label114: {
              A = mem[IY];
              AF = reg16low(AF, A);
              int var23 = IX + 3;
              int var24 = mem[var23];
              A = A + var24 & 255;
              AF = reg16low(AF, A);
              L = A;
              HL = reg16high(HL, A);
              int var25 = IY + 1;
              H = mem[var25];
              HL = reg16low(HL, H);
              A = mem[34262];
              AF = reg16low(AF, A);
              A |= A;
              AF = reg16low(AF, A);
              if(A << 1 == 0) {
                int var65 = IX + 5;
                A = mem[var65];
                AF = reg16low(AF, A);
                int var66 = mem[HL];
                A &= var66;
                AF = reg16low(AF, A);
                if(A << 1 == 0) {
                  break label114;
                }

                int var67 = IX + 9;
                A = mem[var67];
                AF = reg16low(AF, A);
                mem[34262] = A;
                int var68 = IX + 11;
                int var69 = mem[var68] | 1;
                mem[var68] = var69;
              }

              int var26 = IX + 9;
              int var27 = mem[var26];
              if(A == var27) {
                int var61 = IX + 11;
                if((mem[var61] & 1) != 0) {
                  int var62 = IX + 3;
                  B = mem[var62];
                  BC = reg16low(BC, B);
                  int var63 = IX + 5;
                  A = mem[var63];
                  AF = reg16low(AF, A);
                  C1 = 1;
                  BC = reg16high(BC, C1);
                  if(A >= 4) {
                    C1 = 0;
                    BC = reg16high(BC, C1);
                    if(A >= 16) {
                      B = B - 1 & 255;
                      BC = reg16low(BC, B);
                      C1 = 3;
                      BC = reg16high(BC, C1);
                      if(A >= 64) {
                        C1 = 2;
                        BC = reg16high(BC, C1);
                      }
                    }
                  }

                  wMem16(34258, BC);
                  AF = reg16low(AF, IYL);
                  A = IYL - 16 & 255;
                  AF = reg16low(AF, A);
                  mem[34255] = A;
                  push(HL);
                  int[] var64 = $36508(AF, BC, DE, HL, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
                  AF = var64[0];
                  BC = var64[1];
                  DE = var64[2];
                  HL = var64[3];
                  IX = var64[4];
                  IY = var64[5];
                  A = var64[6];
                  F = var64[7];
                  B = var64[8];
                  C = var64[9];
                  D = var64[10];
                  E = var64[11];
                  H = var64[12];
                  L = var64[13];
                  IXL = var64[14];
                  IXH = var64[15];
                  IYL = var64[16];
                  IYH = var64[17];
                  HL = pop();
                  L = HL & 255;
                  H = HL >> 8;
                }
              }
            }

            int var28 = IX + 5;
            A = mem[var28];
            AF = reg16low(AF, A);
            int var29 = mem[HL];
            A |= var29;
            AF = reg16low(AF, A);
            mem[HL] = A;
            int var30 = IX + 9;
            A = mem[var30];
            AF = reg16low(AF, A);
            int var31 = IX + 1;
            int var32 = mem[var31];
            A = A + var32 & 255;
            AF = reg16low(AF, A);
            HL = reg16high(HL, A);
            L = A | 128;
            HL = reg16high(HL, L);
            H = 131;
            HL = reg16low(HL, H);
            E = mem[HL];
            DE = reg16high(DE, E);
            D = 0;
            DE = reg16low(DE, D);
            IY = IY + DE & 65535;
            IYL = IY & 255;
            IYH = IY >> 8;
            L &= -129;
            HL = reg16high(HL, L);
            A = mem[HL];
            AF = reg16low(AF, A);
            A |= A;
            AF = reg16low(AF, A);
            if(A << 1 != 0) {
              B = A;
              BC = reg16low(BC, A);
              int var47 = IX + 1;
              if((mem[var47] & 128) != 0) {
                do {
                  int var54 = IX + 5;
                  int var55 = mem[var54];
                  int[] var56 = rlc(var55, F);
                  int var57 = var56[0];
                  mem[var54] = var57;
                  F = var56[1];
                  int var58 = IX + 5;
                  if((mem[var58] & 1) != 0) {
                    int var59 = IX + 3;
                    int var60 = mem[var59] - 1 & 255;
                    mem[var59] = var60;
                  }

                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);
              } else {
                do {
                  int var48 = IX + 5;
                  int var49 = mem[var48];
                  int var50 = rrc(var49);
                  mem[var48] = var50;
                  int var51 = IX + 5;
                  if((mem[var51] & 128) != 0) {
                    int var52 = IX + 3;
                    int var53 = mem[var52] + 1 & 255;
                    mem[var52] = var53;
                  }

                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);
              }
            }

            int var33 = IX + 9;
            A = mem[var33];
            AF = reg16low(AF, A);
            int var34 = IX + 4;
            int var35 = mem[var34];
            if(A == var35) {
              A = mem[34262];
              AF = reg16low(AF, A);
              if((A & 128) != 0) {
                A = A + 1 & 255;
                AF = reg16low(AF, A);
                mem[34262] = A;
                int var43 = IX + 11;
                int var44 = mem[var43] & -2;
                mem[var43] = var44;
              } else {
                int var36 = IX + 11;
                if((mem[var36] & 1) != 0) {
                  A = mem[34256];
                  AF = reg16low(AF, A);
                  if((A & 2) != 0) {
                    A = rrc(A);
                    AF = reg16low(AF, A);
                    int var37 = mem[IX];
                    A ^= var37;
                    AF = reg16low(AF, A);
                    int[] var38 = rlc(A, F);
                    A = var38[0];
                    AF = reg16low(AF, A);
                    F = var38[1];
                    int[] var39 = rlc(A, F);
                    A = var39[0];
                    AF = reg16low(AF, A);
                    F = var39[1];
                    A &= 2;
                    AF = reg16low(AF, A);
                    A = A - 1 & 255;
                    AF = reg16low(AF, A);
                    HL = 34262;
                    L = HL & 255;
                    H = HL >> 8;
                    int var40 = mem[HL];
                    A = A + var40 & 255;
                    AF = reg16low(AF, A);
                    mem[HL] = A;
                    A = mem[33003];
                    AF = reg16low(AF, A);
                    C = A;
                    BC = reg16high(BC, A);
                    A = mem[33824];
                    AF = reg16low(AF, A);
                    if(A == C) {
                      A = mem[HL];
                      AF = reg16low(AF, A);
                      if(A < 12) {
                        mem[HL] = 12;
                      }
                    }

                    A = mem[HL];
                    AF = reg16low(AF, A);
                    int var41 = IX + 4;
                    int var42 = mem[var41];
                    if(A >= var42 && A != var42) {
                      mem[HL] = 240;
                      A = mem[34255];
                      AF = reg16low(AF, A);
                      A &= 248;
                      AF = reg16low(AF, A);
                      mem[34255] = A;
                      A ^= A;
                      AF = reg16low(AF, A);
                      mem[34257] = A;
                    }
                  }
                }
              }
              break;
            }

            int var45 = IX + 9;
            int var46 = mem[var45] + 1 & 255;
            mem[var45] = var46;
          }
        }
      }

      DE = 8;
      E = DE & 255;
      D = DE >> 8;
      IX = IX + DE & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
    }
  }

  public int[] $37841(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    H = 164;
    HL = reg16low(HL, H);
    A = mem[41983];
    AF = reg16low(AF, A);
    L = A;
    HL = reg16high(HL, A);

    do {
      C = mem[HL];
      BC = reg16high(BC, C);
      C &= -129;
      BC = reg16high(BC, C);
      A = mem[33824];
      AF = reg16low(AF, A);
      A |= 64;
      AF = reg16low(AF, A);
      if(A == C) {
        A = mem[HL];
        AF = reg16low(AF, A);
        int[] var20 = rlc(A, F);
        A = var20[0];
        AF = reg16low(AF, A);
        F = var20[1];
        A &= 1;
        AF = reg16low(AF, A);
        A = A + 92 & 255;
        AF = reg16low(AF, A);
        D = A;
        DE = reg16low(DE, A);
        H = H + 1 & 255;
        HL = reg16low(HL, H);
        E = mem[HL];
        DE = reg16high(DE, E);
        H = H - 1 & 255;
        HL = reg16low(HL, H);
        A = mem[DE];
        AF = reg16low(AF, A);
        A &= 7;
        AF = reg16low(AF, A);
        if(A != 7) {
          A = mem[34251];
          AF = reg16low(AF, A);
          A = A + L & 255;
          AF = reg16low(AF, A);
          A &= 3;
          AF = reg16low(AF, A);
          A = A + 3 & 255;
          AF = reg16low(AF, A);
          C = A;
          BC = reg16high(BC, A);
          A = mem[DE];
          AF = reg16low(AF, A);
          A &= 248;
          AF = reg16low(AF, A);
          A |= C;
          AF = reg16low(AF, A);
          mem[DE] = A;
          A = mem[HL];
          AF = reg16low(AF, A);
          int[] var21 = rlc(A, F);
          A = var21[0];
          AF = reg16low(AF, A);
          F = var21[1];
          int[] var22 = rlc(A, F);
          A = var22[0];
          AF = reg16low(AF, A);
          F = var22[1];
          int[] var23 = rlc(A, F);
          A = var23[0];
          AF = reg16low(AF, A);
          F = var23[1];
          int[] var24 = rlc(A, F);
          A = var24[0];
          AF = reg16low(AF, A);
          F = var24[1];
          A &= 8;
          AF = reg16low(AF, A);
          A = A + 96 & 255;
          AF = reg16low(AF, A);
          DE = reg16low(DE, A);
          push(HL);
          char HL1 = 32993;
          L = HL1 & 255;
          H = HL1 >> 8;
          byte B1 = 8;
          BC = reg16low(BC, B1);
          int[] var25 = $38555(AF, BC, DE, HL1, IX, IY, A, F, B1, C, A, E, H, L, IXL, IXH, IYL, IYH);
          AF = var25[0];
          BC = var25[1];
          DE = var25[2];
          HL = var25[3];
          IX = var25[4];
          IY = var25[5];
          A = var25[6];
          F = var25[7];
          B = var25[8];
          C = var25[9];
          D = var25[10];
          E = var25[11];
          H = var25[12];
          L = var25[13];
          IXL = var25[14];
          IXH = var25[15];
          IYL = var25[16];
          IYH = var25[17];
          HL = pop();
          L = HL & 255;
          H = HL >> 8;
        } else {
          IX = 34172;
          IXL = IX & 255;
          IXH = IX >> 8;

          while(true) {
            int var26 = IX + 2;
            int var27 = mem[var26] + 1 & 255;
            mem[var26] = var27;
            int var28 = IX + 2;
            A = mem[var28];
            AF = reg16low(AF, A);
            if(A != 58) {
              A = mem[32990];
              AF = reg16low(AF, A);
              C = 128;
              BC = reg16high(BC, C);

              do {
                A ^= 24;
                AF = reg16low(AF, A);
                E = A;
                DE = reg16high(DE, A);
                short A1 = 144;
                AF = reg16low(AF, A1);
                A = A1 - C & 255;
                AF = reg16low(AF, A);
                B = A;
                BC = reg16low(BC, A);
                A = E;
                AF = reg16low(AF, E);

                do {
                  B = B - 1 & 255;
                  BC = reg16low(BC, B);
                } while(B != 0);

                C = C - 1 & 255;
                BC = reg16high(BC, C);
                C = C - 1 & 255;
                BC = reg16high(BC, C);
              } while(C != 0);

              A = mem[34270];
              AF = reg16low(AF, A);
              A = A + 1 & 255;
              AF = reg16low(AF, A);
              F = A;
              AF = reg16high(AF, A);
              mem[34270] = A;
              if(A == 0) {
                A = 1;
                AF = reg16low(AF, A);
                mem[34271] = A;
              }

              int var29 = mem[HL] & -65;
              mem[HL] = var29;
              break;
            }

            int var30 = IX + 2;
            mem[var30] = 48;
            IX = IX - 1 & 65535;
            IXL = IX & 255;
            IXH = IX >> 8;
          }
        }
      }

      L = L + 1 & 255;
      HL = reg16high(HL, L);
    } while(L != 0);

    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $37974(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    B = 16;
    BC = reg16low(BC, B);

    do {
      F = C & 1;
      AF = reg16high(AF, F);
      A = mem[DE];
      AF = reg16low(AF, A);
      if(F != 0) {
        int var23 = mem[HL];
        A &= var23;
        AF = reg16low(AF, A);
        if(A << 1 != 0) {
          int[] var25 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var25;
        }

        A = mem[DE];
        AF = reg16low(AF, A);
        int var24 = mem[HL];
        A |= var24;
        AF = reg16low(AF, A);
      }

      mem[HL] = A;
      L = L + 1 & 255;
      HL = reg16high(HL, L);
      DE = DE + 1 & 65535;
      E = DE & 255;
      D = DE >> 8;
      F = C & 1;
      AF = reg16high(AF, F);
      A = mem[DE];
      AF = reg16low(AF, A);
      if(F != 0) {
        int var20 = mem[HL];
        A &= var20;
        AF = reg16low(AF, A);
        if(A << 1 != 0) {
          int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var22;
        }

        A = mem[DE];
        AF = reg16low(AF, A);
        int var21 = mem[HL];
        A |= var21;
        AF = reg16low(AF, A);
      }

      mem[HL] = A;
      L = L - 1 & 255;
      HL = reg16high(HL, L);
      H = H + 1 & 255;
      HL = reg16low(HL, H);
      DE = DE + 1 & 65535;
      E = DE & 255;
      D = DE >> 8;
      AF = reg16low(AF, H);
      A = H & 7;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        AF = reg16low(AF, H);
        A = H - 8 & 255;
        AF = reg16low(AF, A);
        H = A;
        HL = reg16low(HL, A);
        AF = reg16low(AF, L);
        A = L + 32 & 255;
        AF = reg16low(AF, A);
        L = A;
        HL = reg16high(HL, A);
        A &= 224;
        AF = reg16low(AF, A);
        if(A << 1 == 0) {
          AF = reg16low(AF, H);
          A = H + 8 & 255;
          AF = reg16low(AF, A);
          H = A;
          HL = reg16low(HL, A);
        }
      }

      B = B - 1 & 255;
      BC = reg16low(BC, B);
    } while(B != 0);

    A ^= A;
    AF = reg16low(AF, A);
    F = A << 1;
    AF = reg16high(AF, F);
    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $38064(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[33003];
    AF = reg16low(AF, A);
    mem[33824] = A;
    A = mem[34259];
    AF = reg16low(AF, A);
    A &= 31;
    AF = reg16low(AF, A);
    A = A + 160 & 255;
    AF = reg16low(AF, A);
    mem[34259] = A;
    byte A1 = 93;
    AF = reg16low(AF, A1);
    mem[34260] = A1;
    short A2 = 208;
    AF = reg16low(AF, A2);
    mem[34255] = A2;
    A = A2 ^ A2;
    AF = reg16low(AF, A);
    mem[34257] = A;
    nextAddress = 38095;
    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $38137(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    HL = mem16(32983);
    L = HL & 255;
    H = HL >> 8;
    AF = reg16low(AF, H);
    A = H & 1;
    AF = reg16low(AF, A);
    int[] var19 = rlc(A, F);
    A = var19[0];
    AF = reg16low(AF, A);
    F = var19[1];
    int[] var20 = rlc(A, F);
    A = var20[0];
    AF = reg16low(AF, A);
    F = var20[1];
    int[] var21 = rlc(A, F);
    A = var21[0];
    AF = reg16low(AF, A);
    F = var21[1];
    A = A + 112 & 255;
    AF = reg16low(AF, A);
    H = A;
    HL = reg16low(HL, A);
    E = L;
    DE = reg16high(DE, L);
    D = A;
    DE = reg16low(DE, A);
    A = mem[32985];
    AF = reg16low(AF, A);
    A |= A;
    AF = reg16low(AF, A);
    if(A << 1 == 0) {
      int[] var27 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, L, H, L, IXL, IXH, IYL, IYH};
      return var27;
    } else {
      B = A;
      BC = reg16low(BC, A);
      A = mem[32982];
      AF = reg16low(AF, A);
      A |= A;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        A = mem[HL];
        AF = reg16low(AF, A);
        int[] var25 = rlc(A, F);
        A = var25[0];
        AF = reg16low(AF, A);
        F = var25[1];
        int[] var26 = rlc(A, F);
        A = var26[0];
        AF = reg16low(AF, A);
        F = var26[1];
        H = H + 1 & 255;
        HL = reg16low(HL, H);
        H = H + 1 & 255;
        HL = reg16low(HL, H);
        C = mem[HL];
        BC = reg16high(BC, C);
        C = rrc(C);
        BC = reg16high(BC, C);
        C = rrc(C);
        BC = reg16high(BC, C);
      } else {
        A = mem[HL];
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        H = H + 1 & 255;
        HL = reg16low(HL, H);
        H = H + 1 & 255;
        HL = reg16low(HL, H);
        C = mem[HL];
        BC = reg16high(BC, C);
        int[] var22 = rlc(C, F);
        C = var22[0];
        BC = reg16high(BC, C);
        F = var22[1];
        int[] var23 = rlc(C, F);
        C = var23[0];
        BC = reg16high(BC, C);
        F = var23[1];
      }

      do {
        mem[DE] = A;
        mem[HL] = C;
        L = L + 1 & 255;
        HL = reg16high(HL, L);
        E = E + 1 & 255;
        DE = reg16high(DE, E);
        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);

      int[] var24 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var24;
    }
  }

  public int[] $38196(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[33824];
    AF = reg16low(AF, A);
    short HL1;
    short D1;
    if(A == 35) {
      A = mem[34271];
      AF = reg16low(AF, A);
      A |= A;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        A = mem[34251];
        AF = reg16low(AF, A);
        A &= 2;
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A |= 128;
        AF = reg16low(AF, A);
        E = A;
        DE = reg16high(DE, A);
        A = mem[34255];
        AF = reg16low(AF, A);
        if(A != 208) {
          E = 192;
          DE = reg16high(DE, E);
          if(A < 192) {
            E = 224;
            DE = reg16high(DE, E);
          }
        }

        D1 = 156;
        DE = reg16low(DE, D1);
        HL1 = 26734;
        L = HL1 & 255;
        H = HL1 >> 8;
        byte C1 = 1;
        BC = reg16high(BC, C1);
        int[] var24 = $37974(AF, BC, DE, HL1, IX, IY, A, F, B, C1, D1, E, H, L, IXL, IXH, IYL, IYH);
        AF = var24[0];
        BC = var24[1];
        DE = var24[2];
        HL = var24[3];
        IX = var24[4];
        IY = var24[5];
        A = var24[6];
        F = var24[7];
        B = var24[8];
        C = var24[9];
        D = var24[10];
        E = var24[11];
        H = var24[12];
        L = var24[13];
        IXL = var24[14];
        IXH = var24[15];
        IYL = var24[16];
        IYH = var24[17];
        if(F != 0) {
          nextAddress = 37048;
          int[] var26 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var26;
        } else {
          HL1 = 17733;
          L = HL1 & 255;
          H = HL1 >> 8;
          wMem16(23918, HL1);
          HL1 = 1799;
          L = HL1 & 255;
          H = HL1 >> 8;
          wMem16(23950, HL1);
          int[] var25 = new int[]{AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var25;
        }
      } else {
        A = mem[34259];
        AF = reg16low(AF, A);
        A &= 31;
        AF = reg16low(AF, A);
        if(A >= 6) {
          int[] var23 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var23;
        } else {
          byte A1 = 2;
          AF = reg16low(AF, A1);
          mem[34271] = A1;
          int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A1, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var22;
        }
      }
    } else {
      A = mem[33824];
      AF = reg16low(AF, A);
      if(A != 33) {
        int[] var21 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var21;
      } else {
        A = mem[34251];
        AF = reg16low(AF, A);
        A &= 1;
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        A = rrc(A);
        AF = reg16low(AF, A);
        E = A;
        DE = reg16high(DE, A);
        A = mem[34271];
        AF = reg16low(AF, A);
        if(A == 3) {
          E |= 64;
          DE = reg16high(DE, E);
        }

        D1 = 166;
        DE = reg16low(DE, D1);
        char IX1 = 33488;
        IXL = IX1 & 255;
        IXH = IX1 >> 8;
        short BC1 = 4124;
        C = BC1 & 255;
        B = BC1 >> 8;
        int[] var19 = $38504(AF, BC1, DE, HL, IX1, IY, A, F, B, C, D1, E, H, L, IXL, IXH, IYL, IYH);
        AF = var19[0];
        BC = var19[1];
        DE = var19[2];
        HL = var19[3];
        IX = var19[4];
        IY = var19[5];
        A = var19[6];
        F = var19[7];
        B = var19[8];
        C = var19[9];
        D = var19[10];
        E = var19[11];
        H = var19[12];
        L = var19[13];
        IXL = var19[14];
        IXH = var19[15];
        IYL = var19[16];
        IYH = var19[17];
        HL1 = 1799;
        L = HL1 & 255;
        H = HL1 >> 8;
        wMem16(23996, HL1);
        wMem16(24028, HL1);
        int[] var20 = new int[]{AF, BC, DE, HL1, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var20;
      }
    }
  }

  public int[] $38276(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[33824];
    AF = reg16low(AF, A);
    if(A != 33) {
      int[] var21 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var21;
    } else {
      A = mem[34259];
      AF = reg16low(AF, A);
      if(A != 188) {
        int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var20;
      } else {
        A ^= A;
        AF = reg16low(AF, A);
        F = A << 1;
        AF = reg16high(AF, F);
        mem[34251] = A;
        byte A1 = 3;
        AF = reg16low(AF, A1);
        mem[34271] = A1;
        int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A1, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var19;
      }
    }
  }

  public int[] $38344(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    HL = mem16(34259);
    L = HL & 255;
    H = HL >> 8;
    B = 0;
    BC = reg16low(BC, B);
    A = mem[32986];
    AF = reg16low(AF, A);
    A &= 1;
    AF = reg16low(AF, A);
    A = A + 64 & 255;
    AF = reg16low(AF, A);
    DE = reg16high(DE, A);
    byte D1 = 0;
    DE = reg16low(DE, D1);
    HL = HL + DE & 65535;
    L = HL & 255;
    H = HL >> 8;
    A = mem[32964];
    AF = reg16low(AF, A);
    int var19 = mem[HL];
    if(A == var19) {
      A = mem[34257];
      AF = reg16low(AF, A);
      A |= A;
      AF = reg16low(AF, A);
      if(A << 1 == 0) {
        A = mem[34258];
        AF = reg16low(AF, A);
        A &= 3;
        AF = reg16low(AF, A);
        int[] var32 = rlc(A, F);
        A = var32[0];
        AF = reg16low(AF, A);
        F = var32[1];
        int[] var33 = rlc(A, F);
        A = var33[0];
        AF = reg16low(AF, A);
        F = var33[1];
        B = A;
        BC = reg16low(BC, A);
        A = mem[32986];
        AF = reg16low(AF, A);
        A &= 1;
        AF = reg16low(AF, A);
        A = A - 1 & 255;
        AF = reg16low(AF, A);
        A ^= 12;
        AF = reg16low(AF, A);
        A ^= B;
        AF = reg16low(AF, A);
        A &= 12;
        AF = reg16low(AF, A);
        B = A;
        BC = reg16low(BC, A);
      }
    }

    HL = mem16(34259);
    L = HL & 255;
    H = HL >> 8;
    byte DE1 = 31;
    E = DE1 & 255;
    D = DE1 >> 8;
    byte C1 = 15;
    BC = reg16high(BC, C1);
    int[] var20 = $38430(AF, BC, DE1, HL, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var20[0];
    BC = var20[1];
    DE = var20[2];
    HL = var20[3];
    IX = var20[4];
    IY = var20[5];
    A = var20[6];
    F = var20[7];
    B = var20[8];
    C = var20[9];
    D = var20[10];
    E = var20[11];
    H = var20[12];
    L = var20[13];
    IXL = var20[14];
    IXH = var20[15];
    IYL = var20[16];
    IYH = var20[17];
    if(isNextPC(37047)) {
      nextAddress = 37048;
      int[] var31 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var31;
    } else {
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      int[] var21 = $38430(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var21[0];
      BC = var21[1];
      DE = var21[2];
      HL = var21[3];
      IX = var21[4];
      IY = var21[5];
      A = var21[6];
      F = var21[7];
      B = var21[8];
      C = var21[9];
      D = var21[10];
      E = var21[11];
      H = var21[12];
      L = var21[13];
      IXL = var21[14];
      IXH = var21[15];
      IYL = var21[16];
      IYH = var21[17];
      if(isNextPC(37047)) {
        nextAddress = 37048;
        int[] var30 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var30;
      } else {
        HL = HL + DE & 65535;
        L = HL & 255;
        H = HL >> 8;
        int[] var22 = $38430(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
        AF = var22[0];
        BC = var22[1];
        DE = var22[2];
        HL = var22[3];
        IX = var22[4];
        IY = var22[5];
        A = var22[6];
        F = var22[7];
        B = var22[8];
        C = var22[9];
        D = var22[10];
        E = var22[11];
        H = var22[12];
        L = var22[13];
        IXL = var22[14];
        IXH = var22[15];
        IYL = var22[16];
        IYH = var22[17];
        HL = HL + 1 & 65535;
        L = HL & 255;
        H = HL >> 8;
        int[] var23 = $38430(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
        AF = var23[0];
        BC = var23[1];
        DE = var23[2];
        HL = var23[3];
        IX = var23[4];
        IY = var23[5];
        A = var23[6];
        F = var23[7];
        B = var23[8];
        C = var23[9];
        D = var23[10];
        E = var23[11];
        H = var23[12];
        L = var23[13];
        IXL = var23[14];
        IXH = var23[15];
        IYL = var23[16];
        IYH = var23[17];
        if(isNextPC(37047)) {
          nextAddress = 37048;
          int[] var29 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
          return var29;
        } else {
          A = mem[34255];
          AF = reg16low(AF, A);
          A = A + B & 255;
          AF = reg16low(AF, A);
          BC = reg16high(BC, A);
          HL = HL + DE & 65535;
          L = HL & 255;
          H = HL >> 8;
          int[] var24 = $38430(AF, BC, DE, HL, IX, IY, A, F, B, A, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var24[0];
          BC = var24[1];
          DE = var24[2];
          HL = var24[3];
          IX = var24[4];
          IY = var24[5];
          A = var24[6];
          F = var24[7];
          B = var24[8];
          C = var24[9];
          D = var24[10];
          E = var24[11];
          H = var24[12];
          L = var24[13];
          IXL = var24[14];
          IXH = var24[15];
          IYL = var24[16];
          IYH = var24[17];
          HL = HL + 1 & 65535;
          L = HL & 255;
          H = HL >> 8;
          int[] var25 = $38430(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
          AF = var25[0];
          BC = var25[1];
          DE = var25[2];
          HL = var25[3];
          IX = var25[4];
          IY = var25[5];
          A = var25[6];
          F = var25[7];
          B = var25[8];
          C = var25[9];
          D = var25[10];
          E = var25[11];
          H = var25[12];
          L = var25[13];
          IXL = var25[14];
          IXH = var25[15];
          IYL = var25[16];
          IYH = var25[17];
          if(isNextPC(37047)) {
            nextAddress = 37048;
            int[] var28 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
            return var28;
          } else {
            A = mem[34255];
            AF = reg16low(AF, A);
            A = A + B & 255;
            AF = reg16low(AF, A);
            short IXH1 = 130;
            IX = reg16low(IX, IXH1);
            IXL = A;
            IX = reg16high(IX, A);
            A = mem[34256];
            AF = reg16low(AF, A);
            A &= 1;
            AF = reg16low(AF, A);
            A = rrc(A);
            AF = reg16low(AF, A);
            E = A;
            DE = reg16high(DE, A);
            A = mem[34258];
            AF = reg16low(AF, A);
            A &= 3;
            AF = reg16low(AF, A);
            A = rrc(A);
            AF = reg16low(AF, A);
            A = rrc(A);
            AF = reg16low(AF, A);
            A = rrc(A);
            AF = reg16low(AF, A);
            A |= E;
            AF = reg16low(AF, A);
            E = A;
            DE = reg16high(DE, A);
            short D2 = 157;
            DE = reg16low(DE, D2);
            A = mem[33824];
            AF = reg16low(AF, A);
            if(A == 29) {
              D2 = 182;
              DE = reg16low(DE, D2);
              AF = reg16low(AF, E);
              A = E ^ 128;
              AF = reg16low(AF, A);
              E = A;
              DE = reg16high(DE, A);
            }

            byte B1 = 16;
            BC = reg16low(BC, B1);
            A = mem[34259];
            AF = reg16low(AF, A);
            A &= 31;
            AF = reg16low(AF, A);
            BC = reg16high(BC, A);
            int[] var26 = $38504(AF, BC, DE, HL, IX, IY, A, F, B1, A, D2, E, H, L, IXL, IXH1, IYL, IYH);
            AF = var26[0];
            BC = var26[1];
            DE = var26[2];
            HL = var26[3];
            IX = var26[4];
            IY = var26[5];
            A = var26[6];
            F = var26[7];
            B = var26[8];
            C = var26[9];
            D = var26[10];
            E = var26[11];
            H = var26[12];
            L = var26[13];
            IXL = var26[14];
            IXH = var26[15];
            IYL = var26[16];
            IYH = var26[17];
            int[] var27 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
            return var27;
          }
        }
      }
    }
  }

  public int[] $38430(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[32928];
    AF = reg16low(AF, A);
    int var19 = mem[HL];
    if(A == var19) {
      AF = reg16low(AF, C);
      A = C & 15;
      AF = reg16low(AF, A);
      if(A << 1 != 0) {
        A = mem[32928];
        AF = reg16low(AF, A);
        A |= 7;
        AF = reg16low(AF, A);
        mem[HL] = A;
      }
    }

    A = mem[32955];
    AF = reg16low(AF, A);
    int var20 = mem[HL];
    if(A == var20) {
      nextAddress = 37047;
      int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var22;
    } else {
      F = A - var20;
      AF = reg16high(AF, F);
      int[] var21 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
      return var21;
    }
  }

  public int[] $38504(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    do {
      A = mem[IX];
      AF = reg16low(AF, A);
      int var19 = IX + 1;
      H = mem[var19];
      HL = reg16low(HL, H);
      A |= C;
      AF = reg16low(AF, A);
      HL = reg16high(HL, A);
      A = mem[DE];
      AF = reg16low(AF, A);
      int var20 = mem[HL];
      A |= var20;
      AF = reg16low(AF, A);
      mem[HL] = A;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      DE = DE + 1 & 65535;
      E = DE & 255;
      D = DE >> 8;
      A = mem[DE];
      AF = reg16low(AF, A);
      int var21 = mem[HL];
      A |= var21;
      AF = reg16low(AF, A);
      F = A << 1;
      AF = reg16high(AF, F);
      mem[HL] = A;
      IX = IX + 1 & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
      IX = IX + 1 & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
      DE = DE + 1 & 65535;
      E = DE & 255;
      D = DE >> 8;
      B = B - 1 & 255;
      BC = reg16low(BC, B);
    } while(B != 0);

    int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var22;
  }

  public int[] $38528(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    do {
      A = mem[IX];
      AF = reg16low(AF, A);
      int[] var19 = $38545(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
      AF = var19[0];
      BC = var19[1];
      DE = var19[2];
      HL = var19[3];
      IX = var19[4];
      IY = var19[5];
      A = var19[6];
      F = var19[7];
      B = var19[8];
      C = var19[9];
      D = var19[10];
      E = var19[11];
      H = var19[12];
      L = var19[13];
      IXL = var19[14];
      IXH = var19[15];
      IYL = var19[16];
      IYH = var19[17];
      IX = IX + 1 & 65535;
      IXL = IX & 255;
      IXH = IX >> 8;
      E = E + 1 & 255;
      DE = reg16high(DE, E);
      AF = reg16low(AF, D);
      A = D - 8 & 255;
      AF = reg16low(AF, A);
      D = A;
      DE = reg16low(DE, A);
      C = C - 1 & 255;
      BC = reg16high(BC, C);
    } while(C != 0);

    int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, A, E, H, L, IXL, IXH, IYL, IYH};
    return var20;
  }

  public int[] $38545(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    byte H1 = 7;
    HL = reg16low(HL, H1);
    HL = reg16high(HL, A);
    L = A | 128;
    HL = reg16high(HL, L);
    HL = HL + HL & 65535;
    L = HL & 255;
    H = HL >> 8;
    HL = HL + HL & 65535;
    L = HL & 255;
    H = HL >> 8;
    HL = HL + HL & 65535;
    L = HL & 255;
    H = HL >> 8;
    byte B1 = 8;
    BC = reg16low(BC, B1);
    int[] var19 = $38555(AF, BC, DE, HL, IX, IY, A, F, B1, C, D, E, H, L, IXL, IXH, IYL, IYH);
    AF = var19[0];
    BC = var19[1];
    DE = var19[2];
    HL = var19[3];
    IX = var19[4];
    IY = var19[5];
    A = var19[6];
    F = var19[7];
    B = var19[8];
    C = var19[9];
    D = var19[10];
    E = var19[11];
    H = var19[12];
    L = var19[13];
    IXL = var19[14];
    IXH = var19[15];
    IYL = var19[16];
    IYH = var19[17];
    int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var20;
  }

  public int[] $38555(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    do {
      A = mem[HL];
      AF = reg16low(AF, A);
      mem[DE] = A;
      HL = HL + 1 & 65535;
      L = HL & 255;
      H = HL >> 8;
      D = D + 1 & 255;
      DE = reg16low(DE, D);
      B = B - 1 & 255;
      BC = reg16low(BC, B);
    } while(B != 0);

    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $38562(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    while(true) {
      A = mem[HL];
      AF = reg16low(AF, A);
      if(A == 255) {
        int[] var22 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var22;
      }

      BC = 100;
      C = BC & 255;
      B = BC >> 8;
      A ^= A;
      AF = reg16low(AF, A);
      E = mem[HL];
      DE = reg16high(DE, E);
      D = E;
      DE = reg16low(DE, E);

      while(true) {
        D = D - 1 & 255;
        DE = reg16low(DE, D);
        if(D == 0) {
          D = E;
          DE = reg16low(DE, E);
          A ^= 24;
          AF = reg16low(AF, A);
        }

        B = B - 1 & 255;
        BC = reg16low(BC, B);
        if(B == 0) {
          AF = exAF(AF);
          F = AF & 255;
          A = AF >> 8;
          AF = reg16low(AF, C);
          if(C == 50) {
            F = C - 50;
            AF = reg16high(AF, F);
            int[] var21 = rl(E, F);
            E = var21[0];
            DE = reg16high(DE, E);
            F = var21[1];
          }

          AF = exAF(AF);
          F = AF & 255;
          A = AF >> 8;
          C = C - 1 & 255;
          BC = reg16high(BC, C);
          if(C == 0) {
            int[] var19 = $38601(AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH);
            AF = var19[0];
            BC = var19[1];
            DE = var19[2];
            HL = var19[3];
            IX = var19[4];
            IY = var19[5];
            A = var19[6];
            F = var19[7];
            B = var19[8];
            C = var19[9];
            D = var19[10];
            E = var19[11];
            H = var19[12];
            L = var19[13];
            IXL = var19[14];
            IXH = var19[15];
            IYL = var19[16];
            IYH = var19[17];
            if(F != 0) {
              int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
              return var20;
            }

            HL = HL + 1 & 65535;
            L = HL & 255;
            H = HL >> 8;
            break;
          }
        }
      }
    }
  }

  public int[] $38601(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    A = mem[34254];
    AF = reg16low(AF, A);
    A |= A;
    AF = reg16low(AF, A);
    if(A << 1 != 0) {
      A = in(31);
      AF = reg16low(AF, A);
      if((A & 16) != 0) {
        int[] var20 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
        return var20;
      }
    }

    char BC1 = 45054;
    C = BC1 & 255;
    B = BC1 >> 8;
    A = in(BC1);
    AF = reg16low(AF, A);
    A &= 1;
    AF = reg16low(AF, A);
    F = A - 1;
    AF = reg16high(AF, F);
    int[] var19 = new int[]{AF, BC1, DE, HL, IX, IY, A, F, B, C, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }

  public int[] $38622(int AF, int BC, int DE, int HL, int IX, int IY, int A, int F, int B, int C, int D, int E, int H, int L, int IXL, int IXH, int IYL, int IYH) {
    E = A;
    DE = reg16high(DE, A);
    short C1 = 254;
    BC = reg16high(BC, C1);

    do {
      DE = reg16low(DE, A);
      D = A & -17;
      DE = reg16low(DE, D);
      D &= -9;
      DE = reg16low(DE, D);
      B = E;
      BC = reg16low(BC, E);

      do {
        if(A == B) {
          D = 24;
          DE = reg16low(DE, D);
        }

        B = B - 1 & 255;
        BC = reg16low(BC, B);
      } while(B != 0);

      A = A - 1 & 255;
      AF = reg16low(AF, A);
    } while(A != 0);

    int[] var19 = new int[]{AF, BC, DE, HL, IX, IY, A, F, B, C1, D, E, H, L, IXL, IXH, IYL, IYH};
    return var19;
  }
}
