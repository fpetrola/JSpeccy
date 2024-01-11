/**
   $Id: CPU.java 695 2011-09-21 06:09:11Z mviara $

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

import jmce.sim.cpu.MultiOpcode;
import jmce.util.Timer;

/**
 * This interface rappresent one CPU.
 * 
 * <h2>Minimun CPU requirement</h2>
 * <ul>
 *   <li> Must have a program counter.
 *   <li> The program counter is incremented after every instructions.
 *   <li> Must have one memory to store code.
 *   <li> Can optionally have one memory for I/O.
 * </ul>
 *
 *
 * @author Mario Viara
 * @version 1.02
 *
 */
public interface CPU extends Hardware,Runnable,InterruptManager
{
	/** Name used for main memory */
	static public final String MAIN_MEMORY = "MEMORY";

	/** Name used for i/o memory */
	static public final String IO_MEMORY = "IO";

	/** Little endian cpu - lowest byte first */
	static public final int LITTLE_ENDIAN	= 0;
	
	/** Big endian cpu - higghest byte first */
	static public final int BIG_ENDIAN	= 1;
  public int getInterruptCounter(int n);
	/**
	 * Set cpu 'endianess'
	 *
	 * @see #LITTLE_ENDIAN
	 * @see #BIG_ENDIAN
	 */
	public void setEndian(int n);

	/**
	 * Get the cpu 'endianess'
	 *
	 * @see #LITTLE_ENDIAN
	 * @see #BIG_ENDIAN
	 */
	public int  getEndian();

	/**
	 * Return true if the byte order of the cpu is Big Endian
	 */
	public boolean isBigEndian();

	/**
	 * Return true if the byte order of the cpu is Little Endian
	 */
	public boolean isLittleEndian();

	/**
	 * Return che CPU clock in Hz.
	 */
	public long getClock();

	/**
	 * Set the CPU clock in Hz.
	 */
	public void setClock(long clock);

	/**
	 * Set the number of clock used for cycle during instruction
	 * execution.
	 */
	public void setClockPerCycle(int n);

	/**
	 * Get the number of clock used for cycle
	 */
	public int  getClockPerCycle();

	/**
	 * Return the current clock cycle of the CPU. This value will
	 * be set to 0 after any reset.
	 */
	public long getCycle();

	/**
	 * Register listener so that it will receive cycle event.
	 *
	 * <p>
	 * This event is fired  after  the execution of every
	 * instructions. Please use only when necessary because can have
	 * large impact on simulator performance.
	 *
	 * @param l - The listener to register.
	 * 
	 */
	public void addCycleListener(CycleListener l);

	/**
	 * Add a Register
	 */
	public Register addRegister(Register r);

	/**
	 * Return the specified Register
	 */
	public Register getRegisterAt(int i);

	/**
	 * Get the number of register present in the CPU
	 */
	public int  getRegisterCount();

	/**
	 * Search one register for name
	 */
	public Register getRegisterForName(String name);


	/**
	 * Return true is the CPU is running.
	 *
	 * @return <tt>true</tt> if the CPU is running
	 */
	public boolean isRunning();

	/**
	 * Start the CPU
	 * 
	 * @see #isRunning()
	 */
	public void start();

	/**
	 * Stop the CPU
	 *
	 * @see #isRunning()
	 */
	public void stop();

	/**
	 * Add a new listener called when one new exception is detected
	 */
	public void addExceptionListener(ExceptionListener l);

	/**
	 * Remove one exception listener.
	 */
	public void removeExceptionListener(ExceptionListener l);

	/**
	 * Return the number of exception listener installed.
	 */
	public int getExceptionListenerCount();

	/**
	 * Return the specified exception listener.
	 */
	public ExceptionListener getExceptionListenerAt(int i);


	/**
	 * Exec one step and return the number of cycle used.
	 */
	public int step() throws SIMException;

	/**
	 * Release the cpu for few ms. Return the number of ms elapsed.
	 * Normally called only from peripheral that run in thread
	 * context of the cpu.
	 */
	public int idle() throws SIMException;

	/**
	 * Set the break address.
	 */
	public void setTill(int address);

	/**
	 * Return the register used as program counter
	 *
	 * @return - Program counter.
	 */
	public int pc() throws SIMException;

	/**
	 * Set the program counter.
	 *
	 * @param  value - New value for the program counter.
	 */
	public void pc(int value) throws SIMException;

	/**
	 * Set a byte on the Main memory.
	 *
	 * Set one byte on the main memory.
	 * 
	 * @param a - Address
	 * @param v - Value.
	 */
	public void setByte(int a,int v) throws SIMException;

	/**
	 *
	 * Set one word (16 bit) in the main memory
	 *
	 * @param a - Address
	 * @param v - value
	 */
	public void setWord(int a,int v) throws SIMException;

	/**
	 * Read one word (16 bit) from the main memory.
	 *
	 * @param a - Address
	 *
	 * @return The word at the specified address.
	 */
	public int getWord(int a) throws SIMException;

	/**
	 * Return a byte of memory from the main memory.
	 *
	 * @param a - Address
	 *
	 * @return byte read from the memory.
	 */
	public int getByte(int a) throws SIMException;

	/**
	 * Fecth a byte from the memory for exec istructions. For not
	 * segmented CPU do the same of getByte.
	 *
	 * @since 1.01
	 */
	public int fetch(int pc) throws SIMException;

	/**
	 * Decode the istruction at the specified address.
	 * <p>
	 * @return The decoded istruction of null if undefined.
	 */
	public String decodeAt(int pc) throws SIMException;

	/**
	 * Return the lenght in byte of the istruction at the specified
	 * address.
	 */
	public int getLenghtAt(int pc) throws SIMException;

	/**
	 * Load a program in the specified memory.
	 * <p>
	 * @param m - Memory where to load.
	 * @param name - Name of the file.
	 * @param base - Base address.
	 * @param info - Information filled when return.
	 */
	public void load(Memory m,String name,int base,LoadInfo info) throws SIMException;

	/**
	 * Load a program in the default memory.
	 * <p>
	 * @param name - Name of the file.
	 * @param base - Base address.
	 * @param info - Information filled when return.
	 */
	public void load(String name,int base,LoadInfo info) throws SIMException;
	
	/**
	 * Add a new timer as clock the machine cycle.
	 * <p>
	 * After every clock machine the timer is decremented and when
	 * reach 0 the timer is fired.
	 *
	 * @param t - the timer.
	 *
	 * @see jmce.util.Timer
	 */
	public void addTimerCycle(Timer t);

	/**
	 * Add a new timer as clock sec/1000 but using the machine
	 * cycle.
	 *<p>
	 * After every sec/1000 calculated using the clock machine the
	 * timer is decremeted and when reach 0 the timer is fired.
	 * 
	 * @param t - the timer.
	 * 
	 * @see jmce.util.Timer
	 * 
	 */
	public void addTimerMs(Timer t);

	/**
	 * Return the number of cycle per ms.
	 */
	public long getCycleMillis();

	/**
	 * Return the status of the interrupt enable
	 */
	public boolean isInterruptEnabled();
	
	/**
	 * Get CPU usage return the CPU usage of the simulator thread,
	 * high value (more than 90.00) means the current java machine
	 * is too slow to execute the simulator.
	 *
	 * @return cpu usage %
	 */
	public double getUsage();

	/**
	 * Get CPU usage in string format.
	 *<p>
	 * This method is like <tt>getUsage()</tt> but return one
	 * string with nome information.
	 * 
	 * @return One string with current cpu usage.
	 */
	public String getUsageDesc();
			
	/**
	 * Add a new reset listener. This type of listener will be
	 * called after inizialization and when the lister is called
	 * the machine is ready to run.<p>
	 *
	 * @param  l - Listener
	 */
	public void addResetListener(ResetListener l);

	/**
	 * Return the number of memory installed in the cpu
	 */
	public int getMemoryCount();

	/**
	 * Return the main memory
	 *
	 * @since 1.02
	 */
	public Memory getMemory();
	
	/**
	 * Return the memory at the specified position
	 */
	public Memory getMemoryAt(int i);

	/**
	 * Return the memory for name.
	 * <p>
	 * If no memory with the specified name is present return null.
	 *
	 * @param name - Name of the memory to be searched.
	 *
	 * @return The memory or null
	 */
	public Memory getMemoryForName(String name);

	/**
	 * Return the number of installed break point.
	 */
	public int getBreakPointCount();

	/**
	 * Return the specified break point.
	 */
	public BreakPoint getBreakPointAt(int i);

	/**
	 * Remove the specified break point.
	 */
	public void removeBreakPoint(int i);

	/**
	 * Remove the specified breakpoint
	 *
	 * @since 1.02
	 */
	public void removeBreakPoint(BreakPoint b);

	/**
	 * Add a new exec break point.<p>
	 * Triggered when the specified location is executed from the
	 * cpu.
	 */
	public BreakPoint addExecBreakPoint(int m,int a);

	/**
	 * Add a new read break point.<p>
	 * Trigger when the specified location is readed from the cpu.
	 */
	public BreakPoint addReadBreakPoint(int m,int a);

	/**
	 * Add a new write break point.<p>
	 * Triggered when the specified location si written from the
	 * cpu.
	 */
	public BreakPoint addWriteBreakPoint(int m,int a);

	/**
	 * Add a new trace listener.
	 * <p>
	 * When a trace listener is installed it will be called with
	 * one or more string with the cpu trace in human reeadable
	 * form.
	 * 
	 */
	public void addTraceListener(TraceListener t);

	/**
	 * Remove the specified trace listener
	 */
	public void removeTraceListener(TraceListener t);

	/**
	 * Add a MemoryReadListener to the I/O memory.
	 */
	public void addIOReadListener(int a,MemoryReadListener l);

	/**
	 * Add a global MemoryReadListener to the I/O memory,
	 *
	 * @since 1.02
	 */
	public void addIOReadListener(MemoryReadListener l);

	/**
	 * Add a global MemoryWriteListener to the I/O memory,
	 *
	 * @since 1.02
	 */
	public void addIOWriteListener(MemoryWriteListener l);

	/**
	 * Add a memory write listener to the system memory.
	 *
	 * @since 1.01
	 */
	public void addMemoryWriteListener(MemoryWriteListener l);
	
	/**
	 * Add a Memory write listener to the I/O memory.
	 */
	public void addIOWriteListener(int a,MemoryWriteListener l);

	/**
	 * Set a byte in the I/O memory.
	 */
	public void setIOByte(int a,int v) throws SIMException;

	/**
	 * Get a byte from the I/O memory.
	 */
	public int getIOByte(int a) throws SIMException;

	/**
	 * Set the value of the PC at reset.
	 */
	public void setResetAddress(int address) throws SIMException;

	/**
	 * Return the value of PC at reset.
	 */
	public int getResetAddress();

	/**
	 * Set the real time emulation mode.
	 * <p>
	 * If the realtime mode is selected the cpu will run at the
	 * current clock.
	 * original speed.
	 * <p>
	 *
	 * @param mode - Real time emulation mode.
	 * 
	 * @see #setClock
	 */
	public void setRealTime(boolean mode);

	/**
	 * Return the real time emulation mode.
	 *
	 * @see #setRealTime
	 */
	public boolean getRealTime();

	/**
	 * Abort the cpu execution.
	 *<p>
	 * This method can be called from any thread.
	 */
	public void abort(String s);

	/**
	 * Create a file with the cpu usage statistics
	 */
	public void dumpStatistics(String filename) throws SIMException;

	/**
	 * Set a char on the status line
	 * <p>
	 * The status line is the last line of the display and is
	 * reserved for JMCE message the first part of the line will
	 * display the type of cpu, the configured clock, the current
	 * clock and the VM usage. The last char of the line display a
	 * character with the last I/O operation :
	 * <p>
	 * <ul>
	 *  <li>R - Read from disk</li>
	 *  <li>W - Write to disk</li>
	 *  <li>r - Read from network</li>
	 *  <li>w - Write to network</li>
	 *  <li>P - Print to printer</li>
	 *  <li>S - The cpu is stopped</li>
	 *  <li>W - Cpu is started</li>
	 *  <li>* - The cpu is Idle</li>
	 * </ul>
	 * <p>
	 * The status line will display the last status till do not
	 * change.
	 * 
	 * @since 1.01
	 */
	public void setStatusLine(char c);

	/**
	 * Fire a new ISR
	 *
	 * @since 1.01
	 */
	public int fireISR(Interrupt isr) throws SIMException;

	/**
	 * Notity the cpu the specified isr can have changed state.
	 *
	 * @since 1.01
	 */
	public void notifyInterrupt(Interrupt isr);

	/**
	 * Add a new interrupt to this CPU
	 *
	 * @since 1.01
	 */
	public void addInterrupt(Interrupt isr);
  public int getInterruptCount();
  public String getInterruptName(int i);
  public MultiOpcode getOpcodes();
}
