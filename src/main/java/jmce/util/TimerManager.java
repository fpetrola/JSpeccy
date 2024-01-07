/**
   $Id: TimerManager.java 630 2011-06-09 07:12:05Z mviara $

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
 * Timer manager for Timer.
 * <p>
 * Like java.util.TimerTask but it is possible
 * to change the base time and make timer based on every unit of time. The
 * default implementation is for time milliseconds based. To create a
 * TimerManager in any other time unit the method elapsed() must be
 * called at regular interval with a number of unit time elapsed as
 * parameter.<p>
 * For performance reason the Timer are inserted in one double linked
 * list managed from the TimerManager itelf. Method are non sincronized
 * so be carefully if used from more than one thread.
 * 
 * 
 * @author Mario Viara
 * @version 1.02
 *
 * @see jmce.util.Timer
 *
 */
public class TimerManager extends Thread
{
//	private static Logger log = Logger.getLogger(TimerManager.class);
	private String name;
	private static TimerManager msTimer = null;
	private FastLinkedList<Timer> queue = new FastLinkedList<Timer>();
	
	/**
	 * Standard constructor
	 */
	public TimerManager(String name)
	{
		this.name = name;
	}

	/**
	 * Return the number of timer queued.
	 */
	public final int getSize()
	{
		return queue.getSize();
	}

	/**
	 * Add a new timer to the standard timer queue.
	 */
	static public  void addTimer(Timer t)
	{
		if (msTimer == null)
		{
			msTimer = new TimerManager("ms Timer");
			msTimer.start();
		}

		msTimer.add(t);
	}

	/**
	 * Add a timer to the queue.
	 */
	public final  void add(Timer t)
	{
		t.manager = this;
		t.remainingTime = t.initialTime;
		t.status = Timer.QUEUED;

		for (FastLinkedList.Node<Timer> l = queue.getFirst(); !queue.isHead(l) ; l = l.Next())
		{
			int r = l.get().remainingTime;
			
			if (t.remainingTime <= r)
			{
				l.get().remainingTime -= t.remainingTime;
				queue.addBefore(l,t);
				return;
			}

			t.remainingTime -= r;
			
		}
		
		queue.addToEnd(t);
	}

	
	/**
	 * Remove a timer from the queue.
	 */
	public final  void remove(Timer t)
	{
		t.status = Timer.CANCELLED;
		t.manager = null;
		
		FastLinkedList.Node<Timer> l = queue.search(t);

		if (l != null)
			queue.remove(l);

	}

	/**
	 * Called to advance the queue
	 */
	public final void elapsed(int n) throws jmce.sim.SIMException
	{
		Timer t;

		/** Advance the timer */
		while (n > 0)
		{
			FastLinkedList.Node<Timer> l = queue.getFirst();
			if (queue.isHead(l))
				break;
			
			t = l.get();
			
			if (n >= t.remainingTime)
			{
				n -= t.remainingTime;
				queue.remove(l);

				/** Do not fire cancelled timer */
				if (t.status == Timer.QUEUED)
				{
					t.target.timerExpired();
					t.status = Timer.IDLE;
					if (t.isRepeat())
						add(t);
					
				}
			}
			else
			{
				t.remainingTime -= n;
				break;
			}
		}
	
	}


	/**
	 * Thread to manage the standard  ms based timer manager.
	 */
	public void run()
	{
		long time = System.currentTimeMillis();
		
		for (;;)
		{
			try
			{
				Thread.yield();
				long now = System.currentTimeMillis();
				time = now - time;
				if (time > 0)
					elapsed((int)time);

				time = now;
			}
			catch (Throwable e)
			{
				System.out.println(e);
			}
		}

	}

	public String toString()
	{
		return name;
	}
}

