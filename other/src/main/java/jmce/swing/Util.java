/**
   $Id: Util.java 691 2011-09-02 07:57:21Z mviara $

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

import jmce.util.RingBuffer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Swing utility class.
 *
 * @author Mario Viara
 * @version 1.02
 */
public class Util 
{
	static RingBuffer<Repaintable> cmps = new RingBuffer<Repaintable>(1024);
	static int repaintDelay = 100;
	static Timer timer = null;
	static public final Font fontTitle = new Font("Monospaced",Font.BOLD,14);
	static public final Font fontLabel = new Font("Monospaced",Font.PLAIN,12);

	/** Default parent icon package */
	static private Class defaultIconParent = jmce.Jmce.class;
	
	/**
	 * Set the delay for repaint ms.
	 *
	 * @since 1.01
	 */
	static public void setRepaintDelay(int n)
	{
		repaintDelay = n;
		
		/** If the timer already exist update it */
		if (timer != null)
		{
			timer.setDelay(repaintDelay);
		}
	}
	
	/**
	 * When a component updated very fast need to change the
	 * appearance can call this method and at regular interval
	 * (tipically 100 ms) the component will be update in the swing
	 * thread. It is very important that all component use this
	 * method to change the UI otherwise the performance of the
	 * simulator will be very poor.
	 */
	static public void repaintLater(Repaintable c)
	{
		if (cmps.contains(c) == false)
		{
			cmps.put(c);

			/**
			 * The first time create the timer
			 */
			if (timer == null)
				timer = new Timer(repaintDelay,new ActionListener()
				{
					public void actionPerformed(ActionEvent ev)
					{
						repaints();
					}
				});

			/**
			 * Start the timer if necessary
			 */
			if (timer.isRunning() == false)
				timer.start();
		}
	}

	/**
	 * Update the component in the swint thread.
	 */
	static private void repaints()
	{
		for (Repaintable r = cmps.get() ; r != null ; r = cmps.get())
			r.updateComponent();
	}
	
	public static void setBox(JComponent j)
	{
		j.setBorder(BorderFactory.createEtchedBorder());

	}

	public static TitledBorder setTitle(JComponent j,String title)
	{
		return setTitle(j,title,null);
	}
	
	public static TitledBorder setTitle(JComponent j,String title,Color c)
	{
		TitledBorder titled= BorderFactory.createTitledBorder(title);
		//c = Color.blue;
		if (c != null)
			titled.setTitleColor(c);
		j.setBorder(titled);

		return titled;
	}



	/**
	 * Return an icon using the default parent package
	 * @since 1.02
	 */
	static public Icon getIcon(String name)
	{
		return getIcon(defaultIconParent,name);
	}
	
	@SuppressWarnings("rawtypes")
	static public Icon getIcon(Class parent,String name)
	{
		URL url = parent.getResource("images/" + name);

		if (url == null)
		{
//			log.info("getIcon : Package="+parent+" name="+name+" - not found");
			return null;
		}
		
		Icon icon =  new ImageIcon(url);

		return icon;
	}

	/**
	 * Set a component location center to the parent
	 *
	 * @since 1.02
	 */
	static public void setLocationCenterTo(Component c,Container parent)
	{
		Point topLeft = parent.getLocationOnScreen();
		Dimension parentSize = parent.getSize();
		int x,y;

		Dimension mySize = c.getSize();

		if (parentSize.width > mySize.width) 
			x = ((parentSize.width - mySize.width)/2) + topLeft.x;
		else 
			x = topLeft.x;

		if (parentSize.height > mySize.height) 
			y = ((parentSize.height - mySize.height)/2) + topLeft.y;
		else 
			y = topLeft.y;

		c.setLocation (x, y);		

	}
	
}
