/**
   $Id: Property.java 690 2011-09-01 14:40:34Z mviara $

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

import java.io.*;

/**
 * Property manager for all JMCE class.
 *
 * The property are atored in a file jmce.properties and can be
 * accessed with static method. When a property is set the fill is
 * automatically audated.
 *
 * @author Mario Viara
 * @version 1.00
 * @since 1.02
 */
public class Property 
{
	static private final String file = "jmce.properties";
	static private  java.util.Properties prps = new java.util.Properties();
	
	static public final String fileDirectory = "file.directory";
	static public final String lookAndFeel   = "lookAndFeel";
	
	static
	{
		prps.setProperty(fileDirectory,".");
		load();
	}
	

	static String getProperty(String key,String def)
	{
		return prps.getProperty(key,def);
	}
	
	static String getProperty(String key)
	{
		return prps.getProperty(key);
	}

	static void setProperty(String key,String value)
	{
		prps.setProperty(key,value);
		save();
	}
	
	private static void load()
	{
		InputStream is = null;
		
		try
		{
			is = new FileInputStream(file);
			prps.load(is);
		}
		catch (Exception ex)
		{
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (Exception ignore)
			{
			}
		}
				
	}

	private static void save()
	{
		OutputStream os = null;

		try
		{
			os = new FileOutputStream(file);
			prps.store(os,"");
		}
		catch (Exception ex)
		{
		}
		finally
		{
			try
			{
				os.close();
			}
			catch (Exception ignore)
			{
			}
		}
	}
	
}


