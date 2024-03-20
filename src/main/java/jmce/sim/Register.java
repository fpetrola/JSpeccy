/**
   $Id: Register.java 371 2010-09-28 01:41:15Z mviara $

   Copyright (c) 2010, Mario Viara

   Permission is hereby granted, free of charge, to any person obtaining a
   copy of this software and associated documentation files (the "Software"),
   to deal in the Software without restriction, including without limitation
   the rights to use, copy, modify, merge, publish, distribute, sublicense,
   and/or sell copies of the Software, and to permit persons to whom the
   Software is furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
   ROBERT M SUPNIK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
   IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

   Except as contained in this notice, the name of Mario Viara shall not be
   used in advertising or otherwise to promote the sale, use or other dealings
   in this Software without prior written authorization from Mario Viara.
*/
package jmce.sim;

/**
 * Interface to rappresent a CPU register.
 * <p>
 * 
 * All register used in one CPU
 * must implement this interface. The CPU simulator internally can use
 * more fast method to access the register this interface is
 * primary used by external program like monitor or debugger to show or
 * set the CPU register.
 * 
 * @author Mario Viara
 * @version 1.00
 */
public interface Register  extends Hardware 
{
	/** Family register for program counter */
	public final int FAMILY_PC		= 1;
	
	/** Family register for accumulator */
	public final int FAMILY_ACC		= 2;
	
	/** Family register for index register */
	public final int FAMILY_INDEX		= 3;
	
	/** Family register for stack pointer */
	public final int FAMILY_SP		= 4;

	/** Family register  for control register */
	public final int FAMILY_CONTROL		= 5;
	
	/** Family register for Program Status Word */
	public final int FAMILY_PSW		= 6;
	
	/** Family register for other category. */
	public final int FAMILY_GENERAL		= 10;

	/**
	 * Set the value of the register.
	 */
	public void setRegister(int value) throws SIMException;

	/**
	 * Set the value used at reset.
	 */
	public void setResetValue(int value);

	/**
	 * Return the value of the register.
	 */
	public int getRegister() throws SIMException;

	/**
	 * Return the size of the register in bit.
	 */
	public int getWidth();

	/**
	 * Return the family of this register.
	 */
	public int getFamily();
	public String hexValue(int value);
	public String hexValue() throws SIMException;
	public String descValue() throws SIMException;
	public void   addRegisterWriteListener(RegisterWriteListener l);
	public void   addRegisterReadListener(RegisterReadListener l);
}
