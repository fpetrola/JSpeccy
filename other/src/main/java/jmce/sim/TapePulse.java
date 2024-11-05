/**
   $Id: TapePulse.java 626 2011-06-08 08:29:30Z mviara $

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
 * Class for single tape pulse event.  The output must be changed after
 * the pulse is elapsed.
 * 
 * @author Mario Viara
 * @version 1.00
 *
 * @since 1.02
 */
public class TapePulse
{
	/** The output must be unchanged */
	static public final int DATA_NONE	= 0;

	/** The output must be set to low */
	static public final int DATA_LOW	= 1;

	/** The output must be set to high */
	static public final int DATA_HIGH	= 2;

	/** The output must be changed */
	static public final int DATA_TOGGLE	= 3;
	
	private int width,type;

	/**
	 * Constructor with type and pulse width.
	 */
	public TapePulse(int type,int width)
	{
		this.type = type;
		this.width = width;
	}

	/**
	 * Constructor with only pulse width the pulse type will be
	 * DATA_TOGGLE.
	 */
	public TapePulse(int width)
	{
		this(DATA_TOGGLE,width);
	}

	/**
	 * Return the pulse width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Return the pulse type
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Return true if the pulse type is DATA_TOGGLE
	 */
	public boolean isToggle()
	{
		return type == DATA_TOGGLE;
	}

	/**
	 * Return true if the pulse type is DATA_LOW
	 */
	public boolean isLow()
	{
		return type == DATA_LOW;
	}

	/**
	 * Return true if the pulse type is DATA_HIGH
	 */
	public boolean isHigh()
	{
		return type == DATA_HIGH;
	}

	
}
