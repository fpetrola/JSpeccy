/**
   $Id: KFixedField.java 814 2012-03-29 11:07:49Z mviara $

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


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * JTextField extension using MonoSpaced font.<p>
 * 
 * This class extends the standard JTextField and add 2 features, use
 * monospaced font and display the  value in red when changed.
 *
 * @author Mario Viara
 *
 * @since 1.02
 */
public class KFixedField extends JTextField implements FocusListener
{
	private static final long serialVersionUID = 1L;
	protected int len;
	private boolean selected = false;
	private Color changedColor = Color.red;
	private Color normalColor = Color.black;

	public KFixedField(String s)
	{
		this(s.length());
		setText(s);
		setText(s);
	}


	public KFixedField(int len)
	{
		setFontMonospace();
		
		this.len = len;
		setEditable(false);
		setText("");
		addFocusListener(this);

	}

	/**
	 * Set the current font as monospace and save the current
	 * foreground colot.
	 */
	private void setFontMonospace()
	{
		Font font = getFont();
		int style = font.getStyle();
		font = new Font("Monospaced",style,font.getSize());
		setFont(font);
		normalColor = getForeground();
		
	}
	
	public void setText(String s)
	{
		if (s == null)
			s = "";
		
		while (s.length() < len)
		{
			if (getHorizontalAlignment() == RIGHT)
				s = " "+s;
			else
				s += " ";
		}
		s = s.substring(0,len);

		String old = getText();

		if (old == null)
			old = "";
		
		if (s.equals(old))
		{
			setForeground(normalColor);
		}
		else
		{
			setForeground(changedColor);
			super.setText(s);
		}
	}


	@Override
	public void updateUI()
	{
		setFont(null);
		setForeground(null);
		super.updateUI();
		setFontMonospace();
	}
	
	public void setEditable(boolean mode)
	{
		super.setEditable(mode);
		setFocusable(mode);
	}


	public void focusLost(FocusEvent e)
	{
	}

	public void focusGained(FocusEvent e)
	{
		if (isEditable() && isEnabled())
			selectAll();
	}

}

