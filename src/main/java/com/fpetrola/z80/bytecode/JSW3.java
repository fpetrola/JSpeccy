package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int[] memory;

  public JSW3() {
  }

  public void $90C0() {
    int i = 0;
    int i0 = 33024;
    while (true) {
      int i2 = this.memory[i0 + 0];
      int i3 = i2 - 255;
      if (i3 != 0) {
        int i5 = (i2 & 3) - 1;
        if (i5 == 0) {
          if (i5 != 0) {
            int i11 = this.memory[i0 + 0] + 32 | 128;
            this.memory[i0 + 0] = i11;
            if (i11 - 160 < 0) {
              if ((this.memory[i0 + 2] & 31) - this.memory[i0 + 7] == 0) {
                this.memory[i0 + 0] = 97;
              }
            }
          } else {
            int i21 = this.memory[i0 + 0] & 127;
            this.memory[i0 + 0] = i21;
            if (i21 - 96 >= 0) {
              int i7 = (this.memory[i0 + 2] & 31) - this.memory[i0 + 6];
              if (i7 == 0) {
                this.memory[i0 + 0] = 129;
              }
            }
          }
        } else {
          int i41 = (i2 & 3) - 2;
          if (i41 == 0) {
            this.extracted0(i0, i41, 0, 0);
          } else {
            int i51 = 0;
            if (i41 == 0) {
              i51 = this.memory[i0 + 1];
              if (i41 != 0) {
                i51 = i51 + 2;
                if (i51 - 146 < 0) {
                  i51 = i51 + 2;
                }
              } else {
                int i7 = i51 - 20;
                if (i7 < 0) {
                  i51 = i51 | i51;
                  if (i7 == 0) {
                  }
                }
              }
            } else {
              i51 = this.memory[i0 + 1];
              if (i41 == 0) {
                i51 = i51 + 2;
                if (i51 - 18 < 0) {
                  i51 = i51 + 2;
                }
              } else if (i51 - 148 < 0 && i51 - 128 == 0) {
                i51 = i51 ^ i51;
              }
            }
            this.memory[0 + 1] = i51;
            if ((i51 & 127) - this.memory[0 + 7] == 0) {
              this.memory[0 + 0] = this.memory[0 + 0] ^ 128;
            }
          }
        }
      }
      i0 = i + 8;
      i = i0;
    }
  }

  private void extracted0(int i, int i0, int i1, int i2) {
    this.memory[i + 0] = this.memory[i + 0] ^ 8;
    if (i0 != 0) {
      this.memory[i + 0] = this.memory[i + 0] + 32;
    }
    int i12 = this.memory[i1 + 3] + this.memory[i1 + 4];
    this.memory[i1 + 3] = i12;
    if (i12 - this.memory[i1 + 7] < 0) {
      if (i12 - this.memory[i1 + 6] > 0) {
        return;
      }
      this.memory[i1 + 3] = this.memory[i1 + 6];
    }
    this.memory[i2 + 4] = this.memory[i2 + 4];
  }

  public static void main(String[] a) {
    com.fpetrola.z80.bytecode.JSW3 a0 = new com.fpetrola.z80.bytecode.JSW3();
    a0.memory = new int[65536];
    java.util.Arrays.fill(a0.memory, 1);
    a0.$90C0();
  }
}