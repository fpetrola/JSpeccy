package com.fpetrola.z80.bytecode;

public class JSW2 {
  public int initial;
  public int[] memory;

  public void $90C0() {
    int F = '\uffff';
    int IX_L37056 = 33024;

    while (true) {
      int var3 = this.memory[IX_L37056];
      F = var3 - 255;
      if (F == 0) {
        return;
      }

      int A = var3 & 3;
      if (A != 0) {
        F = A - 1;
        if (F != 0) {
          F = A - 2;
          if (F != 0) {
            this.memory[IX_L37056] = this.memory[IX_L37056] & 128;
            if (this.memory[IX_L37056] != 0) {
              F = this.memory[IX_L37056 + 1] & 128;
              if (F != 0) {
                A = this.memory[IX_L37056 + 1] - 2;
                F = A - 148;
                if (F < 0) {
                  A -= 2;
                  F = A - 128;
                  if (F == 0) {
                    A ^= A;
                  }
                }
              } else {
                A = this.memory[IX_L37056 + 1] + 2;
                F = A - 18;
                if (F < 0) {
                  A += 2;
                }
              }
            } else {
              F = this.memory[IX_L37056 + 1] & 128;
              if (F == 0) {
                A = this.memory[IX_L37056 + 1] - 2;
                F = A - 20;
                if (F < 0) {
                  A -= 2;
                  A |= A;
                  if (A == 0) {
                    A = 128;
                  }
                }
              } else {
                A = this.memory[IX_L37056 + 1] + 2;
                F = A - 146;
                if (F < 0) {
                  A += 2;
                }
              }
            }

            this.memory[IX_L37056 + 1] = A;
            A &= 127;
            F = A - this.memory[IX_L37056 + 7];
            if (F == 0) {
              A = this.memory[IX_L37056] ^ 128;
              this.memory[IX_L37056] = A;
            }
          } else {
            label81:
            {
              A = this.memory[IX_L37056] ^ 8;
              this.memory[IX_L37056] = A;
              A &= 24;
              if (A != 0) {
                A = this.memory[IX_L37056] + 32;
                this.memory[IX_L37056] = A;
              }

              A = this.memory[IX_L37056 + 3] + this.memory[IX_L37056 + 4];
              this.memory[IX_L37056 + 3] = A;
              F = A - this.memory[IX_L37056 + 7];
              if (F < 0) {
                F = A - this.memory[IX_L37056 + 6];
                if (F != 0 && F >= 0) {
                  break label81;
                }

                this.memory[IX_L37056 + 3] = this.memory[IX_L37056 + 6];
              }

              this.memory[IX_L37056 + 4] = this.memory[IX_L37056 + 4];
            }
          }
        } else {
          this.memory[IX_L37056] = this.memory[IX_L37056] & 128;
          if (this.memory[IX_L37056] == 0) {
            int var15 = this.memory[IX_L37056];
            A = var15 - 32;
            A &= 127;
            this.memory[IX_L37056] = A;
            F = A - 96;
            if (F >= 0) {
              A = this.memory[IX_L37056 + 2] & 31;
              F = A - this.memory[IX_L37056 + 6];
              int var10000 = this.memory[IX_L37056 + 6];
              if (F != 0) {
                var10000 = IX_L37056 + 2;
                var10000 = IX_L37056 + 2;
              } else {
                this.memory[IX_L37056] = 129;
              }
            }
          } else {
            A = this.memory[IX_L37056] + 32;
            A |= 128;
            this.memory[IX_L37056] = A;
            F = A - 160;
            if (F < 0) {
              A = this.memory[IX_L37056 + 2] & 31;
              F = A - this.memory[IX_L37056 + 7];
              int var86 = this.memory[IX_L37056 + 7];
              if (F != 0) {
                var86 = IX_L37056 + 2;
              } else {
                this.memory[IX_L37056] = 97;
              }
            }
          }
        }
      }

      int DE_L37302 = 8;
      IX_L37056 += DE_L37302;
    }
  }
}
