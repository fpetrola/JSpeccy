/**
   $Id: Hardware.java 598 2011-05-24 07:58:17Z mviara $

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
 * Generic hardware interface.
 *
 * <p>Every class involved  in the simulator must implements this
 * interface, the structure is like a tree and every Hardware have zero
 * or more children.<p>
 * Normally the children depend from the parent.
 * For example a Simulator have one CPU children, the CPU one Disk
 * Controller and the Disk Controller 4 Harddisk. This type of link is
 * checked at runtime and is responsability of the implementation to
 * add correct children class.
 * <p>
 * To permit serialization every hardware instance must add children
 * in the <tt>init</tt> method after have verified that children
 * is not present, this is not so comfortable but permit to use the XML
 * Enmcoder/Decoder to configure new system using simple text file.
 * <p>
 * It is very important to make all the initialization in the iniit()
 * method and not in the constructor, the constructor can only call
 * method to set and get properties.
 * 
 * @author Mario Viara
 * @version 1.00
 *
 * @see #init
 */
public interface Hardware
{
	/**
	 * Set the name.
	 */
	public void setName(String name);
	
	/**
	 * Return the name.
	 */
	public String getName();

	/**
	 * Initialize the hardware. This method must be called before
	 * any use of the interface. Only set ... / get ... method are
	 * allowed to be called before the  intialization.
	 *
	 * @param parent Parent of this hardware.
	 *
	 */
	public void init(Hardware parent) throws SIMException;

	/**
	 * initialize if present the swing hardware.
	 *
	 * Initialize all <code>Hardware</code> implementing
	 * SwingHardware.
	 *
	 * @param parent - Parent Hardware
	 * @see SwingHardware
	 */
	public void		initSwing(Hardware parent) throws SIMException;
	
	/**
	 * Reset the the device to the initial state. This method will
	 * be called after init.
	 */
	public void		reset() throws SIMException;

	/**
	 * Destroy the device. This method can be called only one time
	 */
	public void		destroy() throws SIMException;

	/**
	 * Return the parent
	 */
	public Hardware getParent();
	
	/**
	 * Add a new child.
	 */
	public Hardware		addHardware(Hardware h);

	/**
	 * Return the number of child
	 */
	public int		getHardwareCount();

	/**
	 * Remove one child if present.
	 */
	public void		removeHardware(Hardware h);

	/**
	 * Remove the child at postion.
	 */
	public void		removeHardware(int n);

	/**
	 * Return one array with all instance of the specified Class
	 */
	@SuppressWarnings("rawtypes")
	public Object[]		getHardwareInstances(Class c);

	/**
	 * Return  a class walking the current Hardware as a tree.
	 *
	 * @param classes - List of class to search.
	 */
	public Hardware getHardwareTree(@SuppressWarnings("rawtypes") Class ... classes);
	
	/**
	 * Get the first child of the specified Class.
	 *
	 * @param clazz		Class of the object to be retrivied.
	 *
	 * @return the firsrt child of the specified Class or
	 * null if no child is found.
	 */
	@SuppressWarnings("rawtypes")
	public Hardware		getHardware(Class clazz);

	/**
	 * Get the n occuurence of the specified class.
	 *
	 * @param clazz Class to be retrivied.
	 * @param n	Occurence.
	 *
	 * @return The occurence n of Class clazz or null if no
	 * occurence can be found.
	 *
	 */
	@SuppressWarnings("rawtypes")
	public Hardware		getHardware(Class clazz,int n);

	public void		setHardware(int n,Hardware h);
	public Hardware		getHardware(int n);
	public Hardware[]	getHardware();
	public void		setHardware(Hardware h[]);
	
}
