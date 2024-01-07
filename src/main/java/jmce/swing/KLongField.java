/**
   $Id: KLongField.java 814 2012-03-29 11:07:49Z mviara $

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

/**
 * Extension of JFixedFiled to handle long number.
 *
 * @author Mario Viara
 * @version 1.00
 *
 * @since 1.02
 */
public class KLongField extends KFixedField  implements KeyListener
{
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public KLongField()
	{
		this(10);
	}

	/**
	 * Constructor with specified lenght.
	 */
	public KLongField(int len)
	{
		super(len);
		setHorizontalAlignment(RIGHT);
		setValue(0);
		addKeyListener(this);
	}

	public long getValue() 
	{
		String s = getText();
		long value = 0;

		try
		{
			value = Long.parseLong(s);
		}
		catch (Exception e)
		{
		}

		return value;
	}

	public void setValue(long value)
	{
		String s = "0";
		long old = 0;

		try
		{
			s = Long.toString(value);
		}
		catch (Exception e)
		{
		}

		try
		{
			old = getValue();
		} catch (Exception e)
		{
		}


		setText(s);

	}

	public void keyPressed(KeyEvent e)
	{
		key(e);
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

	private void key(KeyEvent e)
	{
		int offset = 0;

		if (isEditable())
		{

			switch (e.getKeyCode())
			{
				case	KeyEvent.VK_PAGE_UP:
					offset = 16;
					break;
				case	KeyEvent.VK_PAGE_DOWN:
					offset = -16;
					break;
				case	KeyEvent.VK_UP:
					offset = 1;
					break;
				case	KeyEvent.VK_DOWN:
					offset = -1;
					break;
			}

			if (offset != 0)
			{
				try
				{
					long value = getValue();
					setValue((value+offset) & 0xffff);
				}
				catch (Exception ex)
				{
				}
			}
		}

		if (offset != 0)
		{
			e.setKeyCode(KeyEvent.VK_ENTER);
		}
	}

}

