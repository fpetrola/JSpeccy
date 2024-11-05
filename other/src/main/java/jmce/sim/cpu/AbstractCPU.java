/**
   $Id: AbstractCPU.java 695 2011-09-21 06:09:11Z mviara $

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
import jmce.sim.terminal.Terminal;
import jmce.util.*;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serial;
import java.util.TreeMap;
import java.util.Vector;

class SortedVector<E> extends TreeMap<Long,Vector<E>>
{
	private static final long serialVersionUID = 1L;
	Long counters[] = null;
	
	public void put(Long l,E o)
	{
		Vector <E> v = get(l);

		if (v == null)
		{
			v = new Vector<E>();
			put(l,v);
		}
		
		v.add(o);

	}

	public void createArray()
	{
		counters = new Long[0];
		counters = keySet().toArray(counters);
		
	}
	
	public long getCounterAt(int i)
	{	
		return counters[size() - i - 1];
	}

	public Vector<E> getVectorAt(int i)
	{
		return get(counters[i]);
	}

	public int getVectorSizeAt(int i)
	{
		return getVectorAt(i).size();
	}

	public E getAt(int i,int j)
	{
		Vector<E> v = getVectorAt(i);
		return v.elementAt(j);
	}
}


/**
 * Abstract implementation of CPU.
 * <p>
 * <h2>Introduction</h2>
 * This class implements all method not hardware depending for the
 * interface <tt>CPU</tt> , add some helper method for access to the data from
 * subclass. Also implements all method usable from one external class
 * to implement debug,reset set/get register and so on.
 * <p>
 * For performance reason log are conditioned to static flag and can be
 * changed only recompiling this class.
 * <p>
 * At default the cpu will not start in real time mode but will use all the
 * resource available from the Java Virtual Machine. This can be a
 * little problem for play game, in this case use the method {@link
 * #setRealTime} to enable the real time emulation.
 * <p>
 *
 * @author Mario Viara
 * @version 1.01
 *
 * @see #setRealTime
 */
public abstract class AbstractCPU extends AbstractHardware implements CPU,BreakPointListener
{
	
	private static final java.util.logging.Logger log = java.util.logging.Logger.global;

	/** Number of ns in 1 ms */
	static public final long NS1MS = 1000000;

	/** Number of ns in 100 ms */
	static public final long NS100MS = NS1MS * 100;
	
	private CallListener callListeners[] = null;
	private FastArray<ResetListener> resets = new FastArray<ResetListener>();
//	private FastArray<Loader> loaders = new FastArray<Loader>();
	/** Fast array with all cycle listener */
	private FastArray<CycleListener> cycleListeners = new FastArray<CycleListener>();

	/** Vector with all cycle listener for performance */
	private CycleListener cycleListenersVector[] = new CycleListener[0];
	
//	private FastArray<Decoder> decoders = new FastArray<Decoder>();
	private FastArray<ExceptionListener> exceptionListeners = new FastArray<ExceptionListener>();
	private FastArray<Register> regs = new FastArray<Register>();
	
	/** Array with all installed interrupt */
	protected FastArray<Interrupt> interrupts = new FastArray<Interrupt>();
	
	private Terminal terminal = null;
	private Timeout timeoutUsage = new Timeout(5000);
	private long oldCycle;
	protected MultiOpcode opcodes = new MultiOpcode(0);
	private Thread thread = null;
	private boolean running = false;
	private volatile boolean checkInterrupt = true;
	private int maxOpcodeLen = -1;
	private long clock;
	private int  clockPerCycle = 1;
	private long cycle;
	
	/** "endianess" of the cpu */
	private int endian = LITTLE_ENDIAN;
	private long sleepTime;
	private int resetAddress = 0;
	private volatile String abort = null;
	private CpuRuntime runtimeExec;
	
	/**
	 * Real time flag if set the CPU try to emulate the processor
	 * speed in real time.
	 */
	private boolean realTime = false;
	
	/**
	 * Main memory.
	 */
	protected Memory	memory = null;

	/**
	 * I/O memory
	 */
	protected Memory	io = null;

	/** % of VM cpu usage */
	private volatile double cpuUsage = 0;

	/**
	 * Real clock calculate in runtime.
	 */
	private volatile long realClock;

	/** Value of PC where stop the program */
	private int  till = -1;

	/** Timer cpu clock based */
	private TimerManager cycleTimer = new TimerManager("Cycle timer");

	/** Timer ms based (on cpu clock) */
	private TimerManager msTimer = new TimerManager("Sec/1000 timer");

	/** Used to feed the msTimer */
	private Timer msTimerFeed = null;

	/** Status line timer */
	private Timer timerStatus = null;
	
	/** Cycle for one ms must be calculate */
	private int cycleMs;

	/** Break point array */
	private FastArray<BreakPoint> bps = new FastArray<BreakPoint>();

	/** Flag when a new break point is triggered */
	private boolean bpReceived = false;

	/** Break point description */
	private String bpText = null;

	/** Trace enabled */
	private boolean trace = false;

	/** Trace listener */
	private FastArray<TraceListener> traces = new FastArray<TraceListener>();

	public AbstractCPU(String name)
	{
		super(name);

		/** Add default supported file extension */

//		/** Motorola S19 since version 1.02 */
//		addLoader(new MotorolaLoader(".s19"));
//		
//		addLoader(new IntelLoader(".hex"));
//		addLoader(new BinaryLoader(".bin"));
//		addLoader(new BinaryLoader(".rom"));
//		addLoader(new BinaryLoader(".sys"));
//		
//		/** Intel hex file from SDCC since 1.02 */
//		addLoader(new IntelLoader(".ihx"));

		/**
		 * Timer used to feed the ms timer it will be added when
		 * only 1 or more timer with sec/1000 clock are present.
		 */
		msTimerFeed = new Timer(cycleMs,false,new TimerListener()
		{
			public void timerExpired() throws SIMException
			{
				msTimer.elapsed(1);
				if (msTimer.getSize() > 0)
				{
					msTimerFeed.setRepeat(true);
					msTimerFeed.setTime(cycleMs);
				}
				else
				{
					log.info("Removed msTimerFeed "+toString());
					msTimerFeed.setRepeat(false);
				}
			}
		});

	}

	/**
	 * Add a 8 bit signed offset to a 16 bit word
	 *
	 * @param word - Source word
	 * @param offset - 8 bit signed offset
	 * 
	 * @return The word plus/minus the offset
	 */
	public final int addOffset(int word,int offset)
	{
		return (word + (offset - ((offset & 128) << 1))) & 0xffff;
	}
	
	public long getCycle()
	{
		return cycle;
	}
	
	public long getClock()
	{
		return clock;
	}
	
	public void setClock(long clock)
	{
		this.clock = clock;
		if (getClockPerCycle() > 0)
			cycleMs = (int)(clock / 1000 / clockPerCycle);
	}
	

	public void setClockPerCycle(int n)
	{
		clockPerCycle = n;
		cycleMs = (int)(clock / 1000 / clockPerCycle);
	}
	
	public int  getClockPerCycle()
	{
		return clockPerCycle;
	}

//	public int getDecoderCount()
//	{
//		return decoders.getSize();
//	}

//	public Decoder addDecoder(Decoder d)
//	{
//		decoders.add(d);
//		return d;
//		
//	}

//	public Decoder getDecoderAt(int i)
//	{
//		return decoders.get(i);
//	}
//	
	public int getRegisterCount()
	{
		return regs.getSize();
	}

	public Register getRegisterForName(String name)
	{
		for (int i = 0 ; i < regs.getSize() ; i++)
		{
			Register r = getRegisterAt(i);
			if (r.getName().equalsIgnoreCase(name))
				return r;
		}

		return null;
	}
	
	public Register addRegister(Register r)
	{
		regs.add(r);
		
		return r;
	}

	public Register getRegisterAt(int i)
	{
		return regs.get(i);
	}

	public void setEndian(int n)
	{
		endian = n;
	}

	public final int getEndian()
	{
		return endian;
	}

	public final boolean isLittleEndian()
	{
		return endian == LITTLE_ENDIAN;
	}

	public final boolean isBigEndian()
	{
		return endian == BIG_ENDIAN;
	}
	


	public void addMemoryWriteListener(MemoryWriteListener l)
	{
		memory.addMemoryWriteListener(l);
	}
	
	public final Memory getMemory()
	{
		return memory;
	}
	
	protected final void setMemory(Memory m)
	{
		memory = m;
	}

	public void addCycleListener(CycleListener l)
	{
		cycleListeners.add(l);
		cycleListenersVector = cycleListeners.toArray(cycleListenersVector);
	}


	
	public void setOpcode(AbstractOpcode o)
	{
		opcodes.setOpcode(o);
		
	}

	public final AbstractOpcode getOpcodeAt(int pc) throws SIMException
	{
		/** Start from the opcode array */
		AbstractOpcode base = opcodes;
		AbstractOpcode o;
		
		for (;;)
		{
			o = base.getOpcode(fetch(base.fetchAddres(pc)));
			
			if (o == null)
				return null;
			
			if (o.isMultiOpcode())
			{
				base = o;
				pc++;
			}
			else
				return o;
		}

	}

	/**
	 * Return the lenght of one instruction
	 */
	public final int getLenghtAt(int pc) throws SIMException
	{
		AbstractOpcode o = getOpcodeAt(pc);

		if (o instanceof MultiOpcode)
		  System.out.println("dgdag");
		if (o == null)
			return 1;
		else if (o.runtimeOpcode)
		{
			CpuRuntime rt = createRuntime();
			rt.clear();
			rt.pc = pc;
			RuntimeOpcode ro = (RuntimeOpcode)o;
			ro.decode(this,rt);
			return rt.length + o.getLength();
		}
		else
			return  o.getLength();
	
	}
	
	
	public final void setByte(int a,int value) throws SIMException
	{
		memory.setMemory(a,value);
	}


	public final int fetch(int a) throws SIMException
	{
		return memory.getMemory(a);
	}

	public final int getByte(int a) throws SIMException
	{
		return memory.getMemory(a);
	}

	public final int getWordLittle(int a) throws SIMException
	{
		return memory.getMemory(a+0) | (memory.getMemory(a+1) << 8);
	}

	public final int getWordBig(int a) throws SIMException
	{
		return memory.getMemory(a+1) | (memory.getMemory(a+0) << 8);
	}


	/**
	 *  Reset all register to default value.
	 *  <p>
	 *  Sub class can override this method to reset additional non
	 *  conventional register. This method is called  during
	 *  reset().
	 */
	protected void resetRegisters() throws SIMException
	{
		// Reset register
		for (int i = 0 ; i < getRegisterCount() ; i++)
		{
			getRegisterAt(i).reset();
		}
		
	}

	/**
	 * Reset the CPU and call all installed ResetListener.
	 * <p>
	 * Reset is performed in this order :
	 * <ul>
	 *  <li>Reset register.</li>
	 *  <li>Clear internal variable.</li>
	 *  <li>Reset peripheral.</li>
	 *  <li>Call reset listener.</li>
	 *  <li>If Terminal is defined show configuration.</li>
	 * </ul>
	 */
	public void reset() throws SIMException
	{
		resetRegisters();

		
		// Reset cycle
		cycle = 0;

		till = -1;

		/** Reset all interrupt */
		for (int i = 0 ; i < interrupts.getSize() ;i++)
		{
			Interrupt isr = interrupts.get(i);
			isr.setActive(false);
			isr.resetCounter();
		}
		
		super.reset();

		/*
		 * Now the cpu is completly reset and ResetListener can
		 * be called.
		 */
		for (int i = resets.getSize() ; --i >= 0 ;)
			resets.get(i).reset(this);

		/** Set PC to reset address */
		pc(getResetAddress());


		/**
		 * If it is possible show the configuration
		 */
		if (terminal != null)
			jmce.Jmce.showConfig(this,terminal);

	}

	public void setTill(int till)
	{
		this.till = till;
	}


	public long getCycleMillis()
	{
		return cycleMs;
	}

	public void addTimerMs(Timer t)
	{
		msTimer.add(t);
		if (msTimerFeed.getStatus() != Timer.QUEUED)
		{
			log.fine("Add msTimerFeed "+msTimerFeed.toString());
			addTimerCycle(msTimerFeed);
		}
	}

	public void addTimerCycle(Timer t)
	{
		cycleTimer.add(t);
	}
	

	public int step() throws SIMException
	{
		return stepNoBreak();
		
	}

	public final  int stepNoBreak() throws SIMException
	{
		disableBreakPoints();

		return step0();

		
	}


	/**
	 * Check if interrupt are enabled and fire the interrupt if new
	 * one is ready.
	 *
	 * @since 1.01
	 */
	private  final void checkInterrupt() throws SIMException
	{
		
		/**
		 * Check interrupt if necessary
		 */
		if (checkInterrupt && isInterruptEnabled())
		{

			for (int i = interrupts.getSize() ; --i >= 0 ;)
			{
				Interrupt irq = interrupts.get(i);
				if (irq.isReady())
				{
					irq.startISR();
					fireISR(irq);
					if (irq.isAutoReset())
						irq.setActive(false);
					return;
				}

			}

			checkInterrupt = false;
		}
				
	}

	/**
	 * Exec one instruction.
	 */
	public final int step0() throws SIMException
	{
		int i;

		checkBreakPoint();
		checkInterrupt();

		int pc = pc();
		
		if (pc == till)
		{
			till = -1;
			throw new CPUException(this,"Till break");
		}

		AbstractOpcode o = getOpcodeAt(pc);
		
		if (o == null)
			throw new CPUException(this,decodeAt(pc));
		
		checkBreakPoint();

		if (trace) 
			trace(decodeAt(pc));
		

		int t;
		int oldPC = pc;
		CpuRuntime rt = null;
		
		try
		{
			/** Istruction with runtime */
			if (o.runtimeOpcode)
			{
				if (rt == null)
				{
					rt = runtimeExec;
					rt.clear();
				}
				
				rt.pc = pc;
				RuntimeOpcode ro = (RuntimeOpcode)o;
				t = ro.exec(rt);
				pc(pc+rt.length+ro.getLength());
			}
			else
			{
				pc(pc+o.getLength());
				t = o.exec(pc);
			}
			
			o.incCounter();

		}
		catch (SIMInterrupted ie)
		{
			log.fine(ie.toString()+" AT "+Hex.formatWord(pc())+" ==> "+Hex.formatWord(oldPC));
			pc(oldPC);
			return 0;
		}
		catch (SIMException e)
		{
			pc(oldPC);
			throw e;
		}

		if (trace) 
		{
			String s = "";

			for (i = 0 ; i < getRegisterCount () ; i++)
			{
				Register r = getRegisterAt(i);
				s += r.getName()+"="+r.descValue()+" ";
			}


			trace(s);
		}
		

		// CPU timing
		elapsedCycle(t);

		return t;

	}

	/**
	 * Advance timer based on cpu cycle called after every
	 * instruction or in idle loop.
	 */
	private final void elapsedCycle(int t) throws SIMException
	{
		cycle += t;
		
		// Timer listener
		cycleTimer.elapsed(t);

		for (int i = cycleListenersVector.length ; --i >= 0 ;)
			cycleListenersVector[i].cycle(t);
	}

	/**
	 * Check if one break point has been thrown.
	 */
	private final void checkBreakPoint() throws SIMException
	{
		if (bpReceived)
		{
			CPUException ex = new CPUException(this,bpText);
			bpReceived = false;
			bpText = null;
			throw ex;
		}
		
	}
	
	private final void checkAbort() throws CPUAbortException
	{
		if (abort != null)
		{
			String s = abort;
			abort = null;
			throw new CPUAbortException(this,s);
		}
	}
	


	/**
	 * Run the cpu till one exception occour.
	 */
	public final void run0() throws SIMException
	{
		Timeout timeoutNice  = new Timeout(1000);
		Timeout timeoutSleep = new Timeout(0,true);
		
		long count = 0;
		sleepTime = 0;
		abort = null;
					
		stepNoBreak();
		enableBreakPoints();
							
		while (running)
		{
			int n = cycleMs ;

			/**
			 * Run the cpu for 1 ms
			 */
			do
			{
				n -= step0();
			}
			while (n > 0);


			count += NS1MS;
			
			/**
			 * Every 100 ms of cpu time adjust sleep and
			 * check all the timers.
			 */
			if (count >= NS100MS)
			{
				checkAbort();


				if (!realTime)
				{
					count = 0;
					if (timeoutNice.isExpired())
					{
						sleepTime += idle0();
						timeoutNice.restart();
					}
				}
				else
				{
					count = timeoutSleep.getElapsedEx();
					
					long tmp;
					
					while (count < NS100MS)
					{
						tmp = idle0();
						count += tmp;
						sleepTime += tmp;
					}

					count -= NS100MS;
					timeoutSleep.restart();

				}

				calculateUsage();
			}
		}
	}

	/**
	 * Calculate CPU usage called from idle or run loop.
	 * 
	 * @since 1.01
	 */
	private void calculateUsage()
	{
		if (timeoutUsage.isExpired())
		{
			int delta = timeoutUsage.getElapsed();

			sleepTime /= NS1MS;
			

			realClock = (((cycle - oldCycle) * clockPerCycle)*1000)/delta;
			oldCycle = cycle;
			if (sleepTime > delta)
				sleepTime = delta;
			log.fine("Calc cpu usage: Delta="+delta+" Sleep="+sleepTime);
			cpuUsage = (int)((10000 *(delta - sleepTime)) / (delta  ));
			cpuUsage /= 100.0;

			sleepTime = 0;
			timeoutUsage.restart();
		}
	}
	
	public final void run()
	{
		while (thread != null)
		{
			try
			{
				if (running == false)
					Thread.yield();
				else
					run0();
			}
			catch (Throwable e)
			{
				stop();
				disableBreakPoints();
				fireException(e);

				try
				{
					PrintStream ps = new PrintStream(new FileOutputStream("ex.txt"));
					e.printStackTrace(ps);
					ps.close();
				}
				catch (Exception ignore)
				{
				}
			}
		}

	}
	
	
	public boolean isRunning()
	{
		return running;
	}

	public void start()
	{
		if (thread == null)
		{
			thread = new Thread(this);
			thread.setName(getName());
			thread.start();
		}

		running = true;
		setStatusLine('W');
	}

	public void stop()
	{
		setStatusLine('H');
		running = false;
	}

	/**
	 * Dump a value on the speficied stream.
	 */
	protected void dumpValue(PrintStream ps,String s1,String s2)
	{
		while (s1.length() < 10)
			s1 = " "+s1;

		ps.println(s1+" "+s2);
	}

	/**
	 * Dump the title of new section.
	 */
	protected void dumpTitle(PrintStream ps,String s1,String s2)
	{
		ps.println();
		ps.println("===============================================");
		dumpValue(ps,s1,s2);
		ps.println("===============================================");
	}

	/**
	 * Dump a vale on the specificed stream.
	 */
	protected void dumpValue(PrintStream ps,long n,String s)
	{
		dumpValue(ps,""+n,s);
	}

	/**
	 * Dump cpu execution statistics in the specified file.
	 *
	 * @param file - File name where dump cpu statistics.
	 */
	public void dumpStatistics(String file) throws SIMException
	{
		try
		{
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			dumpStatistics(ps);
			ps.close();
		}
		catch (java.io.IOException e)
		{
			throw new SIMIOException(file," Writing");
		}
	}
			

	/**
	 * Dump cpu execution statistics in the specified stream.
	 *
	 * @param ps - The print stream.
	 *
	 * @since 1.01
	 */
	public void dumpStatistics(PrintStream ps) 
	{
		int i;
		SortedVector<Opcode> v = getExecStatistics();

		for (i = 0 ; i < interrupts.getSize() ;i++)
		{
			if (i == 0)
				dumpTitle(ps,"Execution#","Interrupt");
			if (getInterruptCounter(i) > 0)
				dumpValue(ps,getInterruptCounter(i),getInterruptName(i));
		}

		dumpTitle(ps,"Execution#","Opcode");
		for (i = 0 ; i < v.size() ; i++)
		{
			long l = v.getCounterAt(i);

			for (int j = 0 ; j < v.getVectorSizeAt(i) ; j++)
				dumpValue(ps,l,v.getAt(i,j).getDescription());
		}

	}


	public void destroy() throws SIMException
	{
		dumpStatistics("cpu.txt");

		running = false;
		thread = null;
		super.destroy();
	}

	private void fireException(Throwable e)
	{
		ExceptionEvent ev = new ExceptionEvent(e);
		for (int i = 0 ; i < exceptionListeners.getSize() ; i++)
			exceptionListeners.get(i).exceptionEvent(ev);
		
		if (terminal != null)
			if (terminal.getNumStatus() > 0)
			{
				String s = e.getMessage();
				if (s == null || s.length() < 10)
					s = e.toString();
				terminal.printStatusLine(0,s);
			}

		
	}

	public ExceptionListener getExceptionListenerAt(int i)
	{
		return exceptionListeners.get(i);
	}
	
	public void addExceptionListener(ExceptionListener l)
	{
		exceptionListeners.add(l);
	}

	public int getExceptionListenerCount()
	{
		return exceptionListeners.getSize();
	}
	
	public void removeExceptionListener(ExceptionListener l)
	{
		exceptionListeners.remove(l);
	}

	/**
	 * Wait for at least 1 ms and return the number of ns elapsed
	 *
	 * @since 1.01
	 */
	private final long idle0() throws SIMException
	{
		Timeout t = new Timeout(true);
		long elapsed;
		
		do
		{
			Thread.yield();
			elapsed = t.getElapsedEx();
		}
		while (elapsed < NS1MS);

		return elapsed;

	}

	/**
	 * Idle cycle.
	 * <p>
	 * When called from the cpu emulation thread advance counter
	 * and check for new interrupt available if a new interrupt is
	 * available throw SIMInterrupted
	 *
	 * @return The number of ms elapsed.
	 * 
	 * @throws SIMInterrupted When a new interrupt is available.
	 */
	public final int idle() throws SIMException
	{
		long n = idle0();

		if (Thread.currentThread() != thread)
			return (int)(n / NS1MS);

		sleepTime += n;

		checkAbort();
		
		elapsedCycle((int)(cycleMs*(n/NS1MS)));

		if (checkInterrupt && isInterruptEnabled())
			throw new SIMInterrupted("Interrupt ready");
		
		setStatusLine('*');
		calculateUsage();
		return (int)(n / NS1MS);
	}

	public Memory getMemoryForName(String name)
	{
		Object memories[] = getHardwareInstances(Memory.class);
		for (int i = 0 ; i < memories.length ; i++)
		{
			Memory m = (Memory)memories[i];
			if (m.getName().equalsIgnoreCase(name))
			{
				return m;
			}
		}

		return null;
	}

	

//	public void addLoader(Loader loader)
//	{
//		loaders.add(loader);
//	}

	public void load(String name,int base,LoadInfo info) throws SIMException
	{
		load(getMemory(),name,base,info);
	}

//	public void load(Memory m,String name,int base,LoadInfo info) throws SIMException
//	{
//		
//		for (int i = 0 ; i < loaders.getSize() ; i++)
//		{
//			Loader l = loaders.get(i);
//			if (l.isFileSupported(name))
//			{
//				l.setCPU(this);
//				l.load(m,name,base,info);
//				return;
//			}
//		}
//
//		throw new SIMIOException(name," Unsupported extension");
//	}


	/**
	 * This function must be overridden from sub class that
	 * implements interrupt.
	 */
	public int fireISR(Interrupt i) throws SIMException
	{
		throw new SIMException("ISR not supported");
	}

	

	static public final String formatFrequence(long hz)
	{
		long n1,n2;
		
		if (hz < 1000000)
			return hz+"Hz";

		n1 = hz / 1000000;
		n2 = (hz % 1000000) / 1000;
		String s = ""+n2;
		while (s.length() < 3)
			s = "0"+s;

		return n1+"."+s+"MHz";
	}
	
	
	public String getUsageDesc()
	{
		StringBuffer s;

		s = new StringBuffer(getName());
		if (realTime)
			s.append("-R");
		
		s.append(" CLK="+formatFrequence(clock));

		s.append(" REAL="+formatFrequence(realClock));

		if (realTime)
			s.append(" ("+formatFrequence(realClock - clock)+")");
		
		s.append(" VM="+cpuUsage+"%");


		return s.toString();
	}
	

	public double getUsage()
	{
		return cpuUsage;


	}

	/**
	 * Return the CPU opocode table.
	 *
	 * @since 1.02
	 */
	public final MultiOpcode getOpcodes()
	{
		return opcodes;
	}
	
	public void init(Hardware parent) throws SIMException
	{
		
		maxOpcodeLen = opcodes.getMaxLength();
		log.info("Number of opcodes = "+opcodes.getOpcodeCount()+", Max opcode length = "+maxOpcodeLen);
		super.init(parent);


		terminal = (Terminal)getHardwareTree(Serial.class,Terminal.class);

		Timer t = new Timer(10000,true,new TimerListener()
		{
			public void timerExpired()
			{
				if (isRunning())
				{
					String s;
					
					s =getUsageDesc();
					
					
					if (terminal != null)
						if (terminal.getNumStatus() > 0)
						{
							terminal.printStatusLine(0,s);
						}



				}
			}
		});

		TimerManager.addTimer(t);

		runtimeExec = createRuntime();
	}

	public void addResetListener(ResetListener l)
	{
		resets.add(l);
	}

	/**
	 * Debug interface
	 */
	public int getMemoryCount()
	{
		Object memories[] = getHardwareInstances(Memory.class);
		
		return memories.length;
	}

	public Memory getMemoryAt(int i)
	{
		Object memories[] = getHardwareInstances(Memory.class);
		return (Memory)memories[i];
	}

	public int getBreakPointCount()
	{
		return bps.getSize();
	}
	
	public BreakPoint getBreakPointAt(int i)
	{
		return bps.get(i);
	}

	public void removeBreakPoint(BreakPoint b)
	{
		bps.remove(b);
	}
	
	public void removeBreakPoint(int i)
	{
		bps.remove(i);
	}

	public BreakPoint addExecBreakPoint(int m,int a)
	{
		BreakPoint bp =  new BreakPointExec(this,this,getMemoryAt(m),a);
		bps.add(bp);
		return bp;

	}

	public BreakPoint addReadBreakPoint(int m,int a)
	{
		BreakPoint bp = new BreakPointRead(this,this,getMemoryAt(m),a);
		bps.add(bp);
		return bp;
	}

	
	public BreakPoint addWriteBreakPoint(int m,int a)
	{
		BreakPoint bp = new BreakPointWrite(this,this,getMemoryAt(m),a);
		bps.add(bp);
		return bp;
	}

	public void breakPointEvent(BreakPoint bp,String msg)
	{
		if (bpText == null)
			bpText = "";
	
		bpText += msg;
		try
		{
			bpText += " PC "+Hex.formatWord(pc());
		}
		catch (Exception  ignore)
		{
		}
		bpText += "\n";
				
		bpReceived = true;
		
	}

	/**
	 * Disable all break point listener. This function is called
	 * for the execution of first step() and use the second enabled
	 * method of <code>BreakPoint</code>.
	 */
	void disableBreakPoints()
	{
		
		for (int i = bps.getSize(); --i >= 0 ;)
			bps.get(i).setEnabled1(false);
		bpReceived = false;
		bpText = null;
	}

	/**
	 * Enable all previus enabled listener using the second enable
	 * method of <code>BreakPoint</code>
	 */
	void enableBreakPoints()
	{
		bpReceived = false;
		bpText = null;
		
		for (int i = bps.getSize(); --i >= 0 ;)
			bps.get(i).setEnabled1(true);
	}


	public void addTraceListener(TraceListener t)
	{
		traces.add(t);
		trace = true;
	}

	public void removeTraceListener(TraceListener t)
	{
		traces.remove(t);

		trace = traces.getSize() > 0;
	}

	private void trace(String s) throws SIMException
	{
		for (int i = traces.getSize() ; --i >= 0 ;)
			traces.get(i).trace(s);
	}

	public void setIO(Memory io)
	{
		this.io = io;
	}
	
	public void addIOReadListener(MemoryReadListener l)
	{
		io.addMemoryReadListener(l);
	}

	public void addIOReadListener(int a,MemoryReadListener l)
	{
		io.addMemoryReadListener(a,l);
	}

	public void addIOWriteListener(int a,MemoryWriteListener l)
	{
		io.addMemoryWriteListener(a,l);
	}

	public void addIOWriteListener(MemoryWriteListener l)
	{
		io.addMemoryWriteListener(l);
	}


	public final void setIOByte(int a,int v) throws SIMException
	{
		io.setMemory(a,v);
	}

	public final int getIOByte(int a) throws SIMException
	{
		int v = io.getMemory(a);
		return v;
	}

	public void setResetAddress(int value)
	{
		resetAddress = value;
	}

	public int getResetAddress()
	{
		return resetAddress ;
	}

	public int getInterruptCount()
	{
		return interrupts.getSize();
	}

	public String getInterruptName(int n)
	{
		return interrupts.get(n).toString();
	}

	public int getInterruptCounter(int n)
	{
		return interrupts.get(n).getCounter();
	}


	public void setRealTime(boolean mode)
	{
		realTime = mode;
	}

	public boolean getRealTime()
	{
		return realTime;
	}

	public void abort(String s)
	{
		abort = s;
	}

	void updateExecStatistics(SortedVector<Opcode> s,AbstractOpcode o)
	{
		if (o == null)
			return;
		
		if (o.isMultiOpcode())
		{
			MultiOpcode mo = (MultiOpcode)o;
			for (int i = 0 ; i < 256 ; i++)
				updateExecStatistics(s,mo.getOpcode(i));
		}
		else
		{
			if (o.getCounter() > 0)
				s.put(o.getCounter(),o);
			
		}
	}
	
	public SortedVector<Opcode> getExecStatistics()
	{
		SortedVector<Opcode> s = new SortedVector<Opcode>();
		updateExecStatistics(s,opcodes);
		s.createArray();
		
		return s;
	}

	/**
	 * Special istruction general pourpse HALT
	 * <p>
	 * If interrupt are disable one exception is thrown otherwise
	 * call the idle() method till one interrupt will be fired.
	 * <p>
	 * This instructions is the only istruction multi cpu !!
	 *
	 */
	protected void halt() throws SIMException
	{
		if (!isInterruptEnabled())
			throw new CPUException(this,"HALT with Interrupt disabled");

		for (;;)
			idle();

		
	}

	/**
	 * CPU implementing istruction with RuntimeOpcode must in this
	 * method create the appropriate runtime class.
	 */
	protected CpuRuntime createRuntime() throws SIMException
	{
		return null;
	}

	public void setCallListener(int a,CallListener l)
	{
		if (callListeners == null)
			callListeners = new CallListener[memory.getSize()];
		callListeners[a] = l;
	}

	public CallListener getCallListener(int a)
	{
		return callListeners == null ? null : callListeners[a];
	}

	public void setStatusLine(char c)
	{
		/**
		 * Setup a timer to clear the status after 100 ms
		 **/
		if (c != ' ')
		{
			if (timerStatus == null)
				timerStatus = new Timer(100,false,new TimerListener()
				{
					public void timerExpired() throws SIMException
					{
						setStatusLine(' ');
					}
				});
			
		}
		
		if (terminal != null)
			if (terminal.getNumStatus() > 0)
				terminal.setStatusLine(0,terminal.getNumCol() - 1,c);
	}

	public final void addInterrupt(Interrupt irq)
	{
		interrupts.add(irq);
	}

	public final void notifyInterrupt(Interrupt irq)
	{
		checkInterrupt = true;
	}

	/**
	 * Check the 7 bit of a value.
	 *
	 * @return true if the bit is set.
	 *
	 * @since 1.02
	 */
	static public final boolean bit7(int value)
	{
		return (value & 0x80) != 0;
	}

	/**
	 * Check the bit 0 of a value.
	 *
	 * @return true if the bit is set.
	 *
	 * @since 1.02
	 */
	static public final boolean bit0(int value)
	{
		return (value & 0x01) != 0;
	}

}

