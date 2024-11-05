/**
   $Id: Interrupt.java 598 2011-05-24 07:58:17Z mviara $

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

import jmce.util.FastArray;

/**
 * General interrupt.
 * <p>
 * 
 * Since version 1.01 is not an interface but a complete class.
 * <p>
 * When the constructor is called the interrupt is added to the
 * specified InterruptManager (normally the CPU) and the interrupt will be
 * fired when the 2 properties enabled and active are both true.
 * 
 * @author Mario Viara
 * @version 1.03
 * 
 */
public class Interrupt
{
	/** Interrupt counter */
	private int counter;
	protected int vector=0;
	private String name = "";
	private boolean active = false;
	private boolean enabled = false;
	private boolean nmi;
	private boolean autoReset;

	/** Array with all manager installed */
	private FastArray<InterruptManager> mgrs = new FastArray<InterruptManager>();
	
	/**
	 * Constructor with all parameter
	 */
	public Interrupt(InterruptManager mgr,String name,int vector,boolean nmi)
	{
		this.name = name;
		this.nmi = nmi;
		this.vector = vector;

		if (nmi == false)
			addInterruptManager(mgr);

	}

	public Interrupt(InterruptManager cpu,String name,int vector)
	{
		this(cpu,name,vector,false);
	}

	public Interrupt(InterruptManager cpu,String name)
	{
		this(cpu,name,0);
	}

	/**
	 * Add a new interrupt manager to this interrupt.
	 *
	 * @since 1.02
	 */
	public void addInterruptManager(InterruptManager mgr)
	{
		mgr.addInterrupt(this);
		mgrs.add(mgr);
	}
	
	/**
	 * Return true if this interrupt is NMI interrupt.
	 *
	 * @since 1.01
	 */
	public boolean isNmi()
	{
		return nmi;
	}

	/**
	 * Called when the interrupt is started
	 *
	 * @since 1.01
	 */
	public void startISR() throws SIMException
	{
		counter++;
	}
	
	/**
	 * Return the number of interrupt occured.
	 */
	public int getCounter()
	{
		return counter;
	}


	/**
	 * Reset the counter of number of interrupt fired.
	 */
	public void resetCounter()
	{
		counter = 0;
	}

	/**
	 * Set the interrupt vector.
	 *
	 * @since 1.01
	 */
	public void setVector(int n)
	{
		vector = n;
	}
	
	
	/**
	 * Return the interrupt vector. The interrupt vector is CPU
	 * depending and no assumtion is made from the emulation
	 * framework.
	 *
	 * @since 1.01
	 */
	public int getVector() throws SIMException
	{
		return vector;
	}
	

	/**
	 * Return the interrupt name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Return true if the interrupt is enabled.
	 *
	 * @since 1.01
	 */
	public boolean isEnabled()
	{
		return enabled;
	}

	/**
	 * Return true if the interrupt is active
	 *
	 * @since 1.01
	 */
	public boolean isActive() throws SIMException
	{
		return active;
	}

	/**
	 * Set the enabled property.
	 *
	 * @since 1.01
	 */
	public final void  setEnabled(boolean mode) throws SIMException
	{
		enabled = mode;
		if (enabled)
			checkReady();
	}


	/**
	 * Set the active property.
	 *
	 * @since 1.01
	 */
	public final void setActive(boolean mode)  throws SIMException
	{
		active = mode;
		if (active)
			checkReady();
	}


	/**
	 * Return true if the interrupt is ready to be fired.
	 *
	 * @since 1.01
	 */
	public boolean isReady() throws SIMException
	{
		return isActive() & isEnabled();
	}

	/**
	 * Called when the enabled or the active property is changed.
	 * Notify the CPU that this interrupt require attention.
	 *
	 * @since 1.01
	 */
	protected final void checkReady() throws SIMException
	{
		for (int i = mgrs.getSize() ; --i >= 0 ;)
			mgrs.get(i).notifyInterrupt(this);
	}

	/**
	 * Set the property autoReset.
	 * <p>
	 * If this property is set to true automatically the CPU call
	 * the method setActive(false) after the interrupt is fired.
	 * Can be used when the interrupt source cannot be reset
	 * programmatically.
	 *
	 * @since 1.01
	 */
	public void setAutoReset(boolean mode)
	{
		autoReset = mode;
	}

	/**
	 * Return the property autoReset.
	 *
	 * @since 1.01
	 */
	public boolean isAutoReset()
	{
		return autoReset;
	}
	
	public String toString()
	{
		return getName();
	}
}
