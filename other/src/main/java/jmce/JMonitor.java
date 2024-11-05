/**
   $Id: JMonitor.java 814 2012-03-29 11:07:49Z mviara $

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
package jmce;


import jmce.sim.CPU;
import jmce.sim.ExceptionEvent;
import jmce.sim.ExceptionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Swing version of monitor
 * <p>
 * Also implement a frame to contains SwingHardware from the root
 * container.
 *
 * @author Mario Viara
 * @version 1.01
 */
public class JMonitor extends ApplicationFrame implements ExceptionListener
{
	private static final long serialVersionUID = 1L;
	private AbstractAction actionToolsStop;
	private AbstractAction actionToolsGo;
	private AbstractAction actionToolsReset;
	
	
	public JMonitor(CPU _cpu,JComponent frame)
	{
		super(_cpu);


		createActions();

		JPanel p = new JPanel(new BorderLayout());
		p.add(frame,BorderLayout.CENTER);
		
		if (cpu != null)
		{
			if (cpu.getExceptionListenerCount() == 0)
			{
				cpu.addExceptionListener(this);
				createMenuBar();
				setEmulation(true);
				Timer t = new Timer(1000,new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						swingTimer();
					}
					
				});
				t.setRepeats(true);
				t.start();
				p.add(createToolBar(),BorderLayout.PAGE_START);
			}
		}

		setContentPane(p);
	}


	@Override
	protected void setEmulation(boolean e)
	{
		super.setEmulation(e);
		actionToolsStop.setEnabled(e);
		actionToolsGo.setEnabled(!e);
		actionToolsReset.setEnabled(!e);
	}
	
	private void performStart()
	{
		cpu.start();
		setEmulation(true);
	}

	JButton addToBar(JToolBar bar,Action action)
	{
		action.putValue(Action.MNEMONIC_KEY,null);
		JButton b = bar.add(action);
		String s = (String)action.getValue(Action.NAME);
		if (s != null)
			b.setToolTipText(s);

		return b;
	}


	public void createMenuBar()
	{
		JMenu m;
		JMenuBar bar = new JMenuBar();
		bar.add(createMenuFile());
		bar.add(createMenuTools());

		setJMenuBar(bar);


	}

	JMenu createMenuTools()
	{
		JMenu menu = new JMenu("Tools");
		menu.setMnemonic('T');
		menu.setIcon(jmce.swing.Util.getIcon(this.getClass(),"Cpu.gif"));
		
		addKey(menu.add(actionToolsGo),'G');
		addKey(menu.add(actionToolsStop),'S');
		addKey(menu.add(actionToolsReset),'R');


		return menu;
	}

	
	
	


	protected void createActions()
	{

		actionToolsStop = new AbstractAction("Stop")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				performStop();
			}
		};
		
		actionToolsGo = new AbstractAction("Go")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				performStart();
			}
			
		};
		
		actionToolsReset = new AbstractAction("Reset")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				performReset();
			}
		};

		addIcon(actionToolsGo,"play.gif");
		addIcon(actionToolsStop,"stop.gif");
		addIcon(actionToolsReset,"reset.gif");
	}


	public boolean isRunning()
	{
		return cpu.isRunning();
	}

	

	void performStop()
	{
		cpu.stop();
		setEmulation(false);
	}


	void performReset()
	{


	}

	public 	void exceptionEvent(ExceptionEvent ev)
	{
		if (isVisible())
			showError(ev);
		setEmulation(cpu.isRunning());
	}

}

