package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int initial;
  public int[] memory;

  public void $9534() {
    int F = 100000;
    int var2 = this.memory['\u8420'];
    int F_L38199 = var2 - 35;
    if (F_L38199 == 0) {
      int var9 = this.memory['\u85df'];
      var9 |= var9;
      if (var9 == 0) {
        var9 = this.memory['\u85cb'];
        var9 &= 2;
        var9 |= 128;
        var9 = this.memory['\u85cf'];
        F_L38199 = var9 - 208;
        if (F_L38199 != 0) {
          F_L38199 = var9 - 192;
          if (F_L38199 < 0) {
          }
        }

        this.$9456();
      } else {
        var9 = this.memory['\u85d3'];
        var9 &= 31;
        F_L38199 = var9 - 6;
        if (F_L38199 < 0) {
          this.memory['\u85df'] = var9;
        }
      }
    } else {
      int A_L38196 = this.memory['\u8420'];
      F_L38199 = A_L38196 - 33;
      if (F_L38199 == 0) {
        A_L38196 = this.memory['\u85cb'];
        A_L38196 &= 1;
        A_L38196 = this.memory['\u85df'];
        F_L38199 = A_L38196 - 3;
        if (F_L38199 == 0) {
          int var22 = A_L38196 | 64;
        }

        this.$9668();
      }
    }
  }

  public void $9456() {
  }

  public void $9668() {
  }
}