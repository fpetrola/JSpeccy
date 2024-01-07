/**
   $Id: Hex.java 589 2011-05-18 16:42:27Z mviara $

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
package jmce.util;

import jmce.sim.*;

/**
 * Class to format work with hex number. Provide conversion from
 * inteer to String and String to interger. The class cannot be
 * instanced and all method are static.
 * 
 * @author Mario Viara
 * @version 1.01
 */
public class Hex
{
	/**
	 * Only static method no constructor available
	 */
	private Hex()
	{
	}
	
	static private String toHex(int value,int len)
	{
		String s = Integer.toHexString(value);
		while (s.length() < len)
			s = "0"+ s;

		return s.toUpperCase();
	}
	
	public static String formatValue(int value,int len)
	{
		return toHex(value,len);
	}
			
	public static String formatByte(int value)
	{
		return toHex(value,2);
	}

	static public String formatDword(int value)
	{
		return toHex(value,8);
	}

	public static String formatWord(int value)
	{
		return toHex(value,4);
	}

	/**
	 * Return the binary value of variable lenghet hex string.
	 *
	 * @param line - String with the hex number.
	 * @param pos - Start position
	 * @param len - Len in digit.
	 *
	 * @return The binary value.
	 *
	 * @since 1.02
	 */
	static public int getHex(String line,int pos,int len) throws SIMException
	{
		int i;
		int value = 0;
		
		for (i = 0 ; i < len ; i++)
		{
			value <<= 4;
			value |= getDigit(line,pos++);
		}

		return value;
	}
	
	static public int getWord(String line,int pos) throws SIMException
	{
		return getHex(line,pos,4);
	}
	
	static public int getByte(String line,int pos) throws SIMException
	{
		return getHex(line,pos,2);
	}
				
	static public int getDigit(String line,int pos) throws SIMException
	{
		char c = line.charAt(pos);

		if (c >= '0' && c <= '9')
			return c - '0';
		else if (c >= 'a' && c <= 'f')
			return c - 'a' + 10;
		else if (c >= 'A' && c <= 'F')
			return c - 'A' + 10;
		throw new SIMException(c+" is not a valid Hex digit!");
	}
	
}
