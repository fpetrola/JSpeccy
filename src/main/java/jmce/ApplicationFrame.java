/**
   $Id: ApplicationFrame.java 814 2012-03-29 11:07:49Z mviara $

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
import jmce.sim.LoadInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Base JFrame for all graphics JMCE application.
 *
 * @author Mario Viara
 * @version 1.00
 * @since 1.02
 */
public class ApplicationFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	protected CPU cpu;
	protected JFileChooser   fc = null;
	private JLabel usage = new JLabel("100.00%");
	private boolean restoreRealTime = false;
	
	protected AbstractAction actionFileLoad;
	protected AbstractAction actionFileExit;

	public ApplicationFrame(CPU _cpu)
	{
		this.cpu = (CPU)_cpu;

		fc = new JFileChooser();
	
//		String cd = Property.getProperty(Property.fileDirectory,".");
//		fc.setCurrentDirectory(new File(cd));
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				performExit();
			}
		});
	}

	/**
	 * Load a program in the cpu memory
	 */
	protected void performLoad()
	{
		try
		{

			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				LoadInfo info = new LoadInfo();

				cpu.load(fc.getSelectedFile().getCanonicalPath(),0,info);
				showMessage("File :"+fc.getSelectedFile(),"Loaded : "+info);
				Property.setProperty(Property.fileDirectory,fc.getCurrentDirectory().getPath());
			}
		}
		catch (java.lang.Throwable ex)
		{
			showError(ex);
		}
	}


	/**
	 * Exit from the application
	 */
	protected final void performExit()
	{
		setVisible(false);

		cpu.stop();
		try
		{
			cpu.destroy();
			System.exit(0);

		}
		catch (Exception ex)
		{
			System.exit(0);
		}

	}

	/**
	 * Add an icon to one action
	 */
	protected final void addIcon(Action action,String name)
	{
		Icon icon = jmce.swing.Util.getIcon(this.getClass(),name);
		if (icon != null)
			action.putValue(Action.SMALL_ICON,icon);
	}

	/**
	 * Add a key accellerator to one menu
	 */
	protected final void addKey(JMenuItem item,char m)
	{
		item.setMnemonic(m);
		item.setAccelerator(KeyStroke.getKeyStroke(m, KeyEvent.ALT_MASK));
	}

	/**
	 * Create the file menu
	 */
	protected JMenu createMenuFile()
	{
		actionFileLoad = new AbstractAction("Load")
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				performLoad();
			}

		};

		actionFileExit = new AbstractAction("Exit")
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				performExit();
			}
		};

		addIcon(actionFileLoad,"load.gif");
		addIcon(actionFileExit,"exit.gif");

		JMenu menu = new JMenu("File");
		menu.setMnemonic('F');
		menu.setIcon(jmce.swing.Util.getIcon(this.getClass(),"file.gif"));
		addKey(menu.add(actionFileLoad),'L');
		addKey(menu.add(actionFileExit),'X');

		return menu;
	}

	/**
	 * Show a message
	 */
	protected void showMessage(String title,String msg)
	{
		JOptionPane.showMessageDialog(this,msg,title,JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Show an error
	 */
	void showError(String msg)
	{
		JOptionPane.showMessageDialog(this,msg,"Error JMCE Ver. "+Jmce.versionNumber,JOptionPane.ERROR_MESSAGE);
	}


	void showError(ExceptionEvent e)
	{
		showError(e.ex);
	}

	void showError(Throwable e)
	{
		String s =e.toString();
		if (s == null || s.length() == 0)
			s = e.toString();
		showError(s);
	}

	/**
	 * Create the toolbar
	 */
	protected final JToolBar createToolBar()
	{

		JToolBar p = new JToolBar();

		usage.setFont(new Font("Monospaced",Font.BOLD,20));
		usage.setForeground(Color.yellow);
		usage.setBackground(Color.black);
		usage.setOpaque(true);

		p.add(new JLabel(jmce.swing.Util.getIcon(this.getClass(),"Cpu.gif")));
		p.add(usage);
		
		return p;
	}

	/**
	 * Called at period interval from swing thread to update the
	 * graphics interface.
	 */
	protected void swingTimer()
	{
		if (cpu.isRunning())
		{
			double d = cpu.getUsage();

			usage.setForeground(Color.white);

			if (d > 90.0)
				usage.setForeground(Color.red);
			else if (d > 50.0)
				usage.setForeground(Color.yellow);
			else if (d > 25.0)
				usage.setForeground(Color.green);
			else if (d > 10.0)
				usage.setForeground(Color.cyan);

			String s = d+"%";
			while (s.length() < 7)
				s =" "+s;
			usage.setText(s);
		}

	}

	/**
	 * Set the emulation mode
	 */
	protected void setEmulation(boolean e)
	{
		if (actionFileLoad != null)
		{
			actionFileLoad.setEnabled(!e);
		}
	}
}

