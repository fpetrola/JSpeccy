/**
   $Id: Peripheral.java 695 2011-09-21 06:09:11Z mviara $

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
 * Extension to Hardware interface for Peripheral.
 *
 * <p>Peripheral are like  <code>Hardware</code> with 2 main difference :
 * <ul>
 *  <li>One of the ancestor must be a CPU
 *  <li>Can became busy. When busy the Peripheral must call the {@link
 *  CPU#idle()} method of the ancestor CPU.
 * </ul>
 *
 * @author Mario Viara
 * @version 1.01
 *
 * @see Hardware
 */
public interface Peripheral extends Hardware
{
	/**
	 * Register a parent CPU for future reference.
	 */
	public void registerCPU(CPU cpu) throws SIMException;

	/**
	 * Must be called from the peripheral when the simulator is
	 * blocked in waiting of some event. For example I/O
	 * completation, the simulator can use the idle time for other
	 * task.
	 *
	 * @return Number of millisecond waited. Normally the caller is
	 * blocked for not more than five ms.
	 */
	public int idle() throws SIMException;
}




