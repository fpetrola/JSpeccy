/*
 *
 *  * Copyright (c) 2023-2024 Fernando Damian Petrola
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Sbc<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final TableAluOperation sbc8TableAluOperation = new TableAluOperation() {
    public int execute(int a, int value, int carry) {
      data = carry;
      int local_reg_A = a;
      setHalfCarryFlagSub(local_reg_A, value, carry);
      setOverflowFlagSub(local_reg_A, value, carry);
      local_reg_A = local_reg_A - value - carry;
      setS((local_reg_A & 0x0080) != 0);
      setC((local_reg_A & 0xff00) != 0);
      local_reg_A = local_reg_A & 0x00ff;
      setZ(local_reg_A == 0);
      setN();
      int reg_A = local_reg_A;
      setUnusedFlags(reg_A);

      return reg_A;
    }
  };

  public Sbc(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value, reg_A) -> sbc8TableAluOperation.executeWithCarry(value, reg_A, tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingSbc(this);
  }
}
