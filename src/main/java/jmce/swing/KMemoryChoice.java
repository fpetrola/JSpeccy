/**
   $Id: KMemoryChoice.java 814 2012-03-29 11:07:49Z mviara $

   Copyright (c) 2011, Mario Viara

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
package jmce.swing;


import java.awt.event.*;
import javax.swing.*;
import jmce.sim.*;

/**
 * Combo box to choice one cpu memory.
 *
 * @author Mario Viara
 * @since 1.02
 */
public class KMemoryChoice extends JComboBox implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private CPU cpu;

	/**
	 * Constructor with CPU.
	 */
	public KMemoryChoice(CPU _cpu)
	{
		this.cpu = _cpu;

		setEditable(false);

		for (int i = 0 ; i < cpu.getMemoryCount() ;i++)
		{
			Memory m = cpu.getMemoryAt(i);
			String s = m.getName();
			addItem(s);
		}

		addActionListener(this);

		for (int i = 0 ; i < cpu.getMemoryCount() ;i++)
		{
			Memory m = cpu.getMemoryAt(i);
			if (m.getSize() > 0)
			{
				setSelectedIndex(i);
				break;
			}


		}

	}

	/**
	 * Return the current selected memory.
	 */
	public Memory getMemory()
	{
		int n = getSelectedIndex();
		return cpu.getMemoryAt(n);
	}

	/**
	 * When a memory is selected use the memory description as
	 * tooltip text for the combo box.
	 */
	public void actionPerformed(ActionEvent ev)
	{
		setToolTipText(getMemory().toString());
	}


}

