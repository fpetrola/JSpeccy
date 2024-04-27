package com.fpetrola.z80.bytecode;

public class JSW2 {
  public int initial;
  public int[] memory;

  public JSW2() {
  }

  public void $90C0() {
    int i = 33024;
    while (true) {
      if (this.memory[i] - 255 == 0) {
        return;
      }
      label0:
      if (this.memory[i] - 255 != 0) {
        if ((this.memory[i] & 3) - 1 == 0) {
          if (this.initial != 0) {
            if ((this.memory[i] = this.memory[i] + 32 | 128) - 160 < 0) {
              if ((this.memory[i + 2] & 31) - this.memory[i + 7] == 0) {
                this.memory[i] = 97;
              }
            }
          } else {
            if ((this.memory[i] = this.memory[i] - 32 & 127) - 96 >= 0) {
              if ((this.memory[i + 2] & 31) - this.memory[i + 6] == 0) {
                this.memory[i] = 129;
              }
            }
          }
        } else if ((this.memory[i] & 3) - 2 == 0) {
          if ((this.memory[i] = this.memory[i] ^ 8) - 8 != 0) {
            this.memory[i] = this.memory[i] + 32;
          }
          this.memory[i + 3] = this.memory[i + 3] + this.memory[i + 4];
          {
            label2:
            {
              if (this.memory[i + 3] - this.memory[i + 7] >= 0) {
                break label2;
              }
              this.memory[i + 3] = this.memory[i + 3] - this.memory[i + 6];
              label1:
              {
                if (this.memory[i + 3] == 0) {
                  break label1;
                }
                if (this.memory[i + 3] >= 0) {
                  break label0;
                }
              }
              this.memory[i + 3] = this.memory[i + 6];
            }
            this.memory[i + 4] = this.initial;
          }
        } else {
          if (this.initial == 0) {
            if (this.initial != 0) {
              if (this.memory[i + 1] + 2 - 146 < 0) {
              }
            } else if (this.memory[i + 1] - 2 - 20 < 0) {
            }
          } else {
            if (this.initial == 0) {
              if (this.memory[i + 1] + 2 - 18 < 0) {
              }
            } else {
              int i40 = this.memory[i + 1] - 2;
              if (i40 - 148 < 0 && i40 - 2 - 128 == 0) {
              }
            }
          }
          this.memory[i + 1] = this.memory[i] & 3;
          if ((this.memory[i] & 3 & 127) - this.memory[i + 7] == 0) {
            this.memory[i] = this.memory[i] ^ 128;
          }
        }
      }
      i = i + 8;
    }
  }
}