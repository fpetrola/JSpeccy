/**
   $Id: AbstractOpcode.java 637 2011-06-16 17:05:47Z mviara $

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

import jmce.sim.Opcode;
import jmce.util.Hex;

/**
 * Abstract implementation of <tt>Opcode</tt> interface.
 * <p>
 * For performance reason this class contain boolean flag to identify
 * subclass it is not very much elegant but use or <tt>instanceof</tt>
 * is to slow for simulator performance. For example the subclass
 * MultiOpcode in the costructor set the multiOpcode flag and at
 * runtime to identify the class it is enough check the flag and do not
 * require use of instanceof.
 * <p>
 * 
 * @author Mario Viara
 * @version 1.02
 */
public abstract class AbstractOpcode implements Opcode
{
	private static java.util.logging.Logger log = java.util.logging.Logger.global;
	protected int opcode,length,times;
	protected String desc;
	private long execCounter = 0;

	/** Used for MultiOpcode implementation */
	protected AbstractOpcode opcodes[] = null;

	/** Flag to identify subclass MultiOpcode */
	protected boolean multiOpcode = false;

	/** Flag to identify subclass RuntimeOpcode */
	protected boolean runtimeOpcode = false;

	/** Flag to identify subclass PrefixOpcode */
	protected boolean prefixOpcode = false;

	
	public AbstractOpcode(int opcode,int length,int times,String desc)
	{
		this.opcode = opcode;
		this.desc = desc;
		this.length = length;
		this.times = times;
	}

	public final int getOpcode()
	{
		return opcode;
	}

	public final int getLength()
	{
		return length;
	}

	public final int getTimes()
	{
		return times;
	}

	public void setDescription(String s)
	{
		desc = s;
	}
	
	public String getDescription()
	{
		return desc;
	}

	public void clearCounter()
	{
		execCounter = 0;
	}

	public long getCounter()
	{
		return execCounter;
	}

	public final void incCounter()
	{
		execCounter++;
	}
	public String toString()
	{
		return Hex.formatByte(getOpcode())+" ("+getDescription()+")";
	}

	public final boolean isMultiOpcode()
	{
		return multiOpcode;
	}

	/**
	 * Return the opcode with the specified code
	 *
	 * @see MultiOpcode
	 */
	public final AbstractOpcode getOpcode(int code)
	{
	    return opcodes[code];
	}

	/**
	 * Set a new opcode
	 *
	 * @see MultiOpcode
	 */
	public void setOpcode(AbstractOpcode o)
	{
	    int opcode = o.getOpcode();

	    if (opcodes[opcode] != null)
	    {
		log.warning("Duplicate opcode new "+o+" old "+opcodes[opcode]);
		System.exit(1);
	    }

	    opcodes[opcode] = o;

	}

	/**
	 * Return the maximum lenght instructions.
	 *
	 * @see MultiOpcode
	 */
	public int getMaxLength()
	{
	    int len = - 1;

	    for (int i = 0 ; i < 256 ; i++)
	    {
		AbstractOpcode o = opcodes[i];
		int len1 = 0;

		if (o != null)
		{
		    if (o.isMultiOpcode())
		    {
			len1 = o.getMaxLength();
		    }
		    else
			len1 = o.getLength();
		}

		if (len1 > len)
		    len = len1;
	    }

	    return len;

	}

	/**
	 * Get the opcode count
	 *
	 * @see MultiOpcode
	 */
	public int getOpcodeCount()
	{
		int count = 0 ; 
		for (int i = 0 ; i < 256 ; i++)
		{
			AbstractOpcode o = opcodes[i];

			if (o != null)
			{
			    if (o.isMultiOpcode())
			    {
				count += o.getOpcodeCount();
			    }
			    else
				count ++;
			}
		}

		return count;
	}

}

