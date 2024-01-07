/**
   $Id: Timeout.java 630 2011-06-09 07:12:05Z mviara $

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
package jmce.util;

/**
 * Timeout manager.
 * <p>
 * This class implements software timeout. Track time using the current
 * system milliseconds / nanoseconds timer.
 *
 * @author Mario Viara
 * @version 1.01
 * 
 */
public class Timeout
{
	private long start;
	private long timeout;
	private boolean nano = false;
	
	/**
	 * Default constructor. 
	 */
	public Timeout()
	{
		this(0,false);
	}

	/**
	 * Constructor with specified timeout in ms
	 */
	public Timeout(long n)
	{
		this(n,false);
	}
	

	/**
	 * Constructor with specified unit
	 *
	 * @since 1.01
	 */
	public Timeout(boolean nano)
	{
		this(0,nano);
	}
	

	/**
	 * Constructor with specified timeout and unit of mesaures.
	 *
	 * @param timeout - The specified timeout
	 * @param nano - If true the unit of measures will be nano
	 * seconds else milliseconds.
	 */
	public Timeout(long timeout,boolean nano)
	{
		this.timeout = timeout;
		this.nano = nano;
		restart();
	}

		
	/**
	 * Return the current time in the correct unit of measures.
	 *
	 * @since 1.01
	 */
	private final long getTime()
	{
		return nano ? System.nanoTime() : System.currentTimeMillis();
	}
	
	/**
	 * Restart the timer.
	 */
	public final void restart()
	{
		start = getTime();
	}

	/**
	 * Return the elapsed time as long
	 *
	 * @since 1.01
	 */
	public final long getElapsedEx()
	{
		return getTime() - start;
	}
	
	/**
	 * Return the number time as int.
	 */
	public final int getElapsed()
	{
		return (int)getElapsedEx();
	}

	/**
	 * Return true if the timer is expired.
	 */
	public final boolean isExpired()
	{
		return getElapsed() >= timeout;
	}


	/**
	 * Main method for simple test
	 */
	static public void main(String argv[])
	{
		final int delay = 50;
		Timeout tms = new Timeout();
		Timeout tns = new Timeout(true);

		for (int i = 0 ; i < 10 ; i++)
		{
			System.out.println("Test timeout $Id: Timeout.java 630 2011-06-09 07:12:05Z mviara $ # "+i);
			
			tms.restart();
			tns.restart();
			
			try
			{
				Thread.sleep(delay);
			}
			catch (Exception ignore)
			{
			}

			long n = tns.getElapsedEx();
			long m = tms.getElapsedEx();

			System.out.println("Sleep "+delay+" as "+m+" ms and "+n+" ns");
		}
	}
	
}
