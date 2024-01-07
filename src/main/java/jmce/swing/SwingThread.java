/**
   $Id: SwingThread.java 814 2012-03-29 11:07:49Z mviara $

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
package jmce.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple class to handle time consuming process in a separate thread
 * avoiding to block the swing thread.
 *
 * @author Mario Viara
 * @version 1.00
 * @since 1.02
 */
public abstract class SwingThread  extends JDialog implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton cancel = new JButton("Cancel");
	JProgressBar pb = new JProgressBar(0,100);
	private Exception ex = null;
	private boolean cancelled = false;

	/**
	 * Constructor
	 *
	 * @param parent - Parent frame.
	 * @param title - Dialog title.
	 * @param info - Dialog information
	 */
	public SwingThread(JFrame parent,String title,String info)
	{
		super(parent,true);
		setTitle(title);
		GridBagConstraints g = new GridBagConstraints();
		JPanel p = new JPanel(new GridBagLayout());
		g.gridx = 0;g.gridy = 0; g.gridwidth = 1;g.gridheight = 1;
		g.insets = new Insets(10,10,10,10);
		g.fill = GridBagConstraints.BOTH;

		pb.setStringPainted(true);

		p.add(new JLabel(info),g);g.gridy++;
		p.add(pb,g);g.gridy++;
		g.fill = GridBagConstraints.NONE;p.add(cancel,g);
		setContentPane(p);
		pack();

		Icon icon = Util.getIcon("cancel.gif");
		cancel.setIcon(icon);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				cancel.setEnabled(false);
				cancelled = true;
			}
		});

		jmce.swing.Util.setLocationCenterTo(this,parent);
	}

	/**
	 * Set the maximum value for the progrsss bar
	 */
	public void setMaximum(int v)
	{
		pb.setMaximum(v);
	}

	/**
	 * Set the current value for the profress bar
	 */
	public void setValue(int v)
	{
		pb.setValue(v);
	}

	/**
	 * Thread implementation
	 */
	public final void run()
	{

		try
		{
			doWork();
		}
		catch (Exception ex)
		{
			this.ex = ex;
		}

		setVisible(false);
	}

	/**
	 * Open the dialog box and start the thread
	 */
	public final void start()
	{
		Thread t = new Thread(this);
		t.start();
		setVisible(true);
	}

	/**
	 * Return true if the cancel button is pressed
	 */
	public final boolean isCancelled()
	{
		return cancelled;
	}

	/**
	 * Return the exception throws from the doWork method.
	 */
	public final Exception getException()
	{
		return ex;
	}

	/**
	 * Sub classed by user to implement work in different thread
	 */
	public abstract void doWork() throws Exception;

}
