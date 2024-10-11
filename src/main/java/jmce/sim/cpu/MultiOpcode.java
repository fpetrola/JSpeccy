/**
   $Id: MultiOpcode.java 694 2011-09-02 12:01:08Z mviara $

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

import jmce.sim.SIMException;
import jmce.util.Hex;

/**
 * Multi opcode.
 * <p>
 * Special implementation of <tt>Opcode</tt> can hold other 256
 * opcodes.<p>
 * For performance reason the real implementation is in the parent
 * class from version 1.02.
 * 
 * @author Mario Viara
 * @version 1.02
 */
public class MultiOpcode extends AbstractOpcode
{
	private static java.util.logging.Logger log = java.util.logging.Logger.global;
	
	public MultiOpcode(int opcode)
	{
		super(opcode,0,0,"Prefix "+Hex.formatByte(opcode));
		multiOpcode = true;
		opcodes = new AbstractOpcode[256];
	}
	
	public int exec(int pc) throws SIMException
	{
		throw new SIMException("Invalid instruction prefix "+Hex.formatByte(opcode));
	}

}
