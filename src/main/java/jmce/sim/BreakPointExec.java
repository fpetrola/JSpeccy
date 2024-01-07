/**
   $Id: BreakPointExec.java 510 2011-01-18 09:25:07Z mviara $

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
 * Breakpoint on memory exec (read).
 *
 * @author Mario Viara
 * @version 1.00
 */
public class BreakPointExec extends BreakPointRead
{
	/**
	 * Execution break point constructor.
	 * <p>
	 * Constructor for a break point that must be fired when one
	 * istruction is executed.
	 *
	 * @param cpu - Cpu
	 * @param l - Listener for break point event.
	 * @param memory - Memory where put the break point.
	 * @param address - Address of this break point in the
	 * specified memory.
	 */
	public BreakPointExec(CPU cpu,BreakPointListener l,Memory memory,int address)
	{
		super(cpu,"EXEC",l,memory,address);
	}

	/**
	 * Called when the memory where the break point is connecected
	 * is read. Check if the program counter of the CPU is set at
	 * this address and if so fire the  break point if enabled
	 */
	public int readMemory(int a,int value) throws Exception
	{
		if (enabled && enabled1 && cpu.pc() == a)
			fireBreakPoint(formatMsg(a,value));
		return value;
	}

}
