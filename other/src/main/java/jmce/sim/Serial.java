/**
   $Id: Serial.java 419 2010-10-07 08:25:23Z mviara $

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
 * Sample abstract class for serial chars device.
 *
 * @author Mario Viara
 * @version 1.00
 */
abstract public class Serial {
  public Serial() {
  }

  public void putchar(int c) throws SIMException {
  }

  public final void println() throws SIMException {
    print("\n\r");
  }

  public final void println(Object s) throws SIMException {
    print(s);
    println();
  }

  public final void print(Object o) throws SIMException {
    String s = String.valueOf(o);

    for (int i = 0; i < s.length(); i++)
      putchar(s.charAt(i));
  }

}
