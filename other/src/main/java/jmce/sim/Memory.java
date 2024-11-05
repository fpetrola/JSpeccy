/**
   $Id: Memory.java 450 2010-12-01 09:59:14Z mviara $

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
 * Memory device.
 * <p>
 * 
 * Interface used to simulate every memory mapped device like ram.
 * eprom , I/O space and so on. The width of the memory is 8 bit.
 *
 * <p>Memory can have global <code>MemoryWriteListener</code> , called
 * at every memory write and a specific listener called when a specific
 * location is written.
 * 
 * <p>Memory can have <code>MemoryReadListener</code> called when one
 * memory location must be read. Multiple MemoryReadListener can be
 * installed and are called in installation order.
 * 
 * @author Mario Viara
 * @version 1.01
 * 
 */
public interface Memory extends Peripheral
{
	/**
	 * Set a location of memory.
	 *
	 * @param address - Memory address
	 * @param value - Value to set only 8 bit are used
	 *
	 * 
	 */
	public void	setMemory(int address,int value) throws SIMException;

	/**
	 * Get a location of memory.
	 *
	 * @param address - Location
	 *
	 * @return A byte from the memory.
	 */
	public int	getMemory(int address) throws SIMException;

	/**
	 * Return the size of the memory in byte.
	 */
	public int	getSize();

	/**
	 * Set the size of the memory.
	 *
	 */
	public void	setSize(int size);

	/**
	 * Add a global MemoryWriteListener.
	 *
	 * <p>
	 * The listener will be called when any location of the memory
	 * is writed.
	 *
	 * @param l - Memory write listener called when the memory is
	 * writed.
	 */
	public void	addMemoryWriteListener(MemoryWriteListener l);

	/**
	 * Add a specific MemoryWriteListener.
	 * <p>
	 * The listener will be called when the specified memory location
	 * is written.
	 *
	 * @param address - Address where to install the listener.
	 * @param l - Memory write listener called when the memory is
	 * writed.
	 */
	public void	addMemoryWriteListener(int address,MemoryWriteListener l);

	/**
	 * Remove a previous global installed memory write listener.
	 */
	public void	removeMemoryWriteListener(MemoryWriteListener l);

	/**
	 * Remove a previus specific installed memory write listener.
	 */
	public void	removeMemoryWriteListener(int a,MemoryWriteListener l);

	/**
	 * Return the number of global memory write listener installed.
	 */
	public int	getMemoryWriteListenerCount();

	/**
	 * Return the number of specific memory write listener
	 * installed at the specified affress.
	 */
	public int	getMemoryWriteListenerCount(int a);
	public MemoryWriteListener  getMemoryWriteListenerAt(int i);
	public MemoryWriteListener  getMemoryWriteListenerAt(int i,int a);
	
	public void	addMemoryReadListener(MemoryReadListener l);
	public void	removeMemoryReadListener(MemoryReadListener l);
	public void	removeMemoryReadListener(int a,MemoryReadListener l);
	public void	addMemoryReadListener(int a,MemoryReadListener l);
	public int	getMemoryReadListenerCount();
	public int	getMemoryReadListenerCount(int a);
	public MemoryReadListener getMemoryReadListenerAt(int i);
	public MemoryReadListener getMemoryReadListenerAt(int i,int a);
	
	public void	setMemoryName(int address,String name);
	public String	getMemoryName(int address);
	public void	setBit(int a,int mask) throws SIMException;
	public void	clrBit(int a,int mask) throws SIMException;
	public boolean  isBit(int a,int mask) throws SIMException;

	/**
	 * Set the flag read only for the specified range
	 *
	 * @since 1.01
	 */
	public void setReadOnly(int start,int len);

	/**
	 * Set the flag read only for a single address.
	 *
	 * @since 1.01
	 */
	public void setReadOnly(int address);
	
	/**
	 * Set the flag read only for all the memory
	 *
	 * @since 1.01
	 */
	public void	setReadOnly();

	/**
	 * Check if a speficied location is read only.
	 *
	 * @since 1.01
	 */
	public boolean	getReadOnly(int add);
}
