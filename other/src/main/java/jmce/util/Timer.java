/**
   $Id: Timer.java 946 2012-12-02 11:01:18Z mviara $

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
 * Asyncronous timer.
 * <p>
 * Every timer require a <tt>TimerManager</tt>  to manage the timer queue.
 * <p>
 * Static method are provided to access a standard TimerManager for
 * milliseconds based <tt>Timer<tt>.
 * 
 * @author Mario Viara
 * @version 1.01
 *
 * @see TimerManager
 *
 */
public class Timer 
{
//	private static Logger log = Logger.getLogger(Timer.class);

	/** Standard ms timer manager */
	static TimerManager timerManager = null;
	
	static public final int IDLE = 0;
	static public final int QUEUED = 1;
	static public final int READY = 2;
	static public final int CANCELLED = 3;

	
	/**
	 * Member variable are package protected because used by
	 * TimerManager for performance reason.
	 */
	int status;
	int remainingTime,initialTime;
	boolean repeat;
	TimerListener target;
	TimerManager manager = null;

	/**
	 * Package protected constructor use by TimerManager
	 *
	 * @since 1.02
	 */
	Timer()
	{
		this(0,null);
	}
	
	/**
	 * Standard constructor.
	 * <p>
	 * Create a new time with specified time and repeat.
	 * <p>
	 * @param time - Time when it will expire.
	 * @param repeat - True if the timer must be repeated
	 * @param run - Listener to be called.
	 */
	public Timer(int time,boolean repeat,TimerListener run)
	{
		initialTime = time;
		target = run;
		status = IDLE;
		this.repeat = repeat;
	}


	/**
	 * Constructor without the repeat flag.
	 */
	public Timer(int time,TimerListener run)
	{
		this(time,false,run);
	}

	/**
	 * Return true if the timer is active
	 */
	public boolean isRunning()
	{
		return status == QUEUED || status == READY;
	}

	/**
	 * Return the repeart flag.
	 */
	public final boolean isRepeat()
	{
		return repeat;
	}

	/**
	 * Set the repeat flag
	 */
	public void setRepeat(boolean mode)
	{
		repeat = mode;
	}

	/**
	 * Return the time
	 */
	public long getTime()
	{
		return initialTime;
	}

	/**
	 * Set the time
	 */
	public void setTime(int t)
	{
		initialTime = t;
	}

	/**
	 * Cancel the timer.
	 * <p>
	 * Remove the timer from the TimerManager if queued else do
	 * nothing.
	 */
	public final void cancel()
	{
		if (manager != null)
			manager.remove(this);
	}

	/**
	 * Return the status of the timer
	 */
	public final int getStatus()
	{
		return status;
	}

	/**
	 * Add the timer to the standard TimerManager
	 */
	static public void addTimer(Timer timer)
	{
		if (timerManager == null)
		{
			timerManager = new TimerManager("Standard timer");
			timerManager.start();
		}

		timerManager.add(timer);
	}

	/**
	 * Create a timer and add it to the standard milliseconds based TimerManager
	 */
	static public Timer createTimer(int time,boolean repeat,TimerListener run)
	{
		Timer t = new Timer(time,repeat,run);
		addTimer(t);
		return t;
	}

	/**
	 * Create a timer and add it to the standard TimerManager
	 */
	static public Timer createTimer(int time ,TimerListener run)
	{
		return createTimer(time,false,run);
	}


	/**
	 * Main method for simple test
	 */
	static public void main(String argv[])
	{
		try
		{
		createTimer(1000,true,new TimerListener()
		{
			public void timerExpired()
			{
//				log.info("Expired 1000");
			}
		});

		createTimer(2000,true,new TimerListener()
		{
			public void timerExpired()
			{
//				log.info("Expired 2000");
			}
		});

		createTimer(5000,true,new TimerListener()
		{
			public void timerExpired()
			{
//				log.info("Expired 5000");
				createTimer(500,true,new TimerListener()
				{
					public void timerExpired()
					{
//						log.info("Expired 500");
					}
				});
				
			}
		});

		createTimer(10030,true,new TimerListener()
		{
			public void timerExpired()
			{
//				log.info("Expired 10030");
				System.exit(0);
			}
		});
		}
		catch (Throwable e)
		{
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public String toString()
	{
		return "Timer "+initialTime+" counter "+remainingTime+", repeat "+repeat;
	}
	
}