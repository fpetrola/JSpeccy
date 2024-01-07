/**
   $Id: BreakPoint.java 371 2010-09-28 01:41:15Z mviara $

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

import jmce.util.Hex;


/**
 * Base class for all type of break point.
 * <p>
 *
 * Every break point have 2 enable the first one using the standard
 * method setEnabled/getEnabled is used from the application to control
 * the break point the second,using the method setEnabled1/getEnabled1.
 * used from the CPU simulator to avoid break point detection during
 * critical code execution.
 *
 * @author Mario Viara
 * @version 1.00
 */
public abstract class BreakPoint
{
	protected int address;
	protected boolean enabled;
	protected boolean enabled1;
	protected Memory memory;
	protected int fireCounter;
	protected BreakPointListener listener;
	protected String name;
	protected CPU cpu;
	
	/**
	 * Standard contructor.
	 *
	 * <p>Is package protected and can be called only from class
	 * inside at this package.
	 * 
	 *
	 * @param name - Name for the break poinr.
	 * @param l - Listener to be call when the break point is fired.
	 * @param memory - Memory involved.
	 * @param address - Break point address.
	 */
	BreakPoint(CPU cpu,String name,BreakPointListener l,Memory memory,int address)
	{
		this.cpu = cpu;
		this.name = name;
		this.memory = memory;
		this.address = address;
		this.enabled = true;
		this.fireCounter = 0;
		this.listener = l;
	}

	/**
	 * Return the address where the break point is inserted.
	 */
	public final int getAddress()
	{
		return address;
	}

	/**
	 * Return the Memory where the break point is connected.
	 */
	public final Memory getMemory()
	{
		return memory;
	}

	/*
	 * Enable/disable this break point
	 */
	public final void setEnabled(boolean mode)
	{
		enabled = mode;
	}

	/**
	 * Enable/disable the break point. Using the second enabled
	 * this method must be called only from the CPU simulator
	 */
	public final void setEnabled1(boolean mode)
	{
		enabled1 = mode;
	}

	/**
	 * Return the status of the break point. The method check only
	 * the first enabled.
	 *
	 * @see #setEnabled
	 */
	public final boolean isEnabled()
	{
		return enabled;
	}


	/**
	 * Prepare a string for this break point
	 */
	protected String formatMsg(int address,int value)
	{
		return name+" Memory="+memory.getName()+" at "+Hex.formatWord(address)+" Value="+Hex.formatByte(value);
		
	}

	/**
	 * Return the number of times the break point has been fired.
	 */
	public int getFireCount()
	{
		return fireCounter;
	}

	/**
	 * Fire the break point.
	 *
	 * @param msg - Messagge string.
	 */
	public void fireBreakPoint(String msg)
	{
		fireCounter++;
		listener.breakPointEvent(this,msg);
	}

	/**
	 * Destroy the break point.
	 * <p>
	 * Must unregister all memory read / write listener and free
	 * all used resources.
	 */
	public abstract void destroy();	

	public String toString()
	{
		return "BP "+name+" Memory="+memory.getName()+"  at "+Hex.formatWord(address);
	}
		
}
