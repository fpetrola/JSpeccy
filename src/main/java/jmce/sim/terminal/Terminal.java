/**
   $Id: Terminal.java 510 2011-01-18 09:25:07Z mviara $

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
package jmce.sim.terminal;

import jmce.sim.*;

/**
 * Abstract Device for Terminal.
 * <p>
 * Terminal is implemented using 2 Memory one for characters and one for
 * attributes all operation are made in the memories and change are notfied to
 * the children <tt>CRT</tt>.
 * 
 * <p>
 * The terminal operate on monochome display and each char have one or more
 * attribute.
 * <p>
 * 
 * @author Mario Viara
 * @version 1.01
 */
abstract public class Terminal extends jmce.sim.Serial {
  /** Attribute normal */
  static public final byte NORMAL = 0x00;

  /** Attribute reverse */
  static public final byte REVERSE = 0x01;

  /** Attribute hi */
  static public final byte HI = 0x02;

  /** Attribute underline */
  static public final byte UNDERLINE = 0x04;

  /** Attribute blink */
  static public final byte BLINK = 0x08;

  static public final byte SPACE = (byte) ' ';

  abstract public java.awt.Font getFont();

  abstract public void setFont(java.awt.Font font);

  abstract public void setFontSize(int size);

  abstract public int getFontSize();

  abstract public int getRow();

  abstract public int getCol();

  abstract public int getNumStatus();

  abstract public void setNumStatus(int n);

  abstract public void setNumRow(int row);

  abstract public void setNumCol(int col);

  abstract public int getNumRow();

  abstract public int getNumCol();

  abstract public boolean getCursor();

  abstract public void setCursor(int row, int col);

  /**
   * Print a string on the status line.
   * <p>
   * The previous value of the status line will be lost. If the status line is not
   * available no text will be print.
   * 
   * @param r - Status line
   * @param o - Object to print
   */
  abstract public void printStatusLine(int r, Object o);

  /**
   * Print a String at the specified position of the status line.
   * <p>
   * 
   * @param r - Status line
   * @param c - Column
   * @param o - Object to print
   */
  abstract public void setStatusLine(int r, int c, Object o);

  abstract public void putchar(int c) throws SIMException;

  abstract public void defineFunctionKey(int key, String s);

  abstract public String getFunctionKey(int key);

  abstract public Memory getCharMemory();

  abstract public Memory getAttMemory();

  abstract public void setEchoFile(String file);

  abstract public String getEchoFile();

  public Terminal(String name) {
  }

  /**
   * Return the default instance of terminal class.
   * <p>
   * 
   * This versione return a <tt>TV100</tt> terminal.
   * <p>
   *
   */

}
