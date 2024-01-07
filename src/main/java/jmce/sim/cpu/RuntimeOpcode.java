/**
   $Id: RuntimeOpcode.java 632 2011-06-14 11:17:35Z mviara $

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
package jmce.sim.cpu;

import jmce.sim.*;

/**
 * Opcode with Runtime<p>
 *
 * This type of opcode is used when the instructions require a runtime.
 * For example the intel 8086 require a runtime to track istruction
 * prefix and segment override.
 *
 * @author Mario Viara
 * @version 1.00
 */
abstract public class RuntimeOpcode extends AbstractOpcode
{
	Runtime runtime = null;

	public RuntimeOpcode(int opcode,int length,int times,String desc)
	{
		super(opcode,length,times,desc);
		runtimeOpcode = true;

	}

	public final int exec(int pc) throws SIMException
	{
		throw new SIMException("Implementation error");
	}

	abstract public void decode(CPU cpu,CpuRuntime r) throws SIMException;
	abstract public int exec(CpuRuntime r) throws SIMException;

}
