/**
   $Id: RingBuffer.java 691 2011-09-02 07:57:21Z mviara $

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

/*
 * Ring buffer.
 *
 * <p>For performance reason is better use RingBuffer than
 * array because when when one element is removed the array must change
 * and the remaining element copied, with ring buffer only a integer
 * pointer is incremented. As usual no syncronization.
 *
 * @author Mario Viara
 * @version 1.01
 *
 */
public class RingBuffer<E>
{
	private Object buffer[];
	private int pl,ps;

	/**
	 * Constructor with specified the size
	 */
	public RingBuffer(int size)
	{
		buffer = new Object[size];
		pl = ps = 0;
	}

	/**
	 * Default constructor.
	 * <p>
	 * A new RingBuffer will be created with the default size of
	 * 256 element.
	 */
	public RingBuffer()
	{
		this(256);
	}

	/**
	 * Remove all element from the buffer.
	 */
	public final void purge()
	{
		ps = pl = 0;
	}

	/**
	 * Return the first element in the buffer without advancing the
	 * pointer. If no element is present return null.
	 */
	@SuppressWarnings("unchecked")
	public final E peek()
	{
		if (ps == pl)
			return null;
		return (E)buffer[pl];
	}

	/**
	 * Return the first element from the buffer and advance the
	 * pointer to the next one
	 */
	@SuppressWarnings("unchecked")
	public  final E get()
	{
		if (ps == pl)
			return null;

		E o = (E)buffer[pl];
		if (++pl >= buffer.length)
			pl = 0;
		return o;
	}

	/**
	 * Return the number of element in the buffer.
	 */
	public final int count()
	{
		int count = 0;
		int newPl = pl;

		while (newPl != ps)
		{
			count ++;
			if (newPl++ > buffer.length)
				newPl = 0;
			
		}

		return count;
	}

	/**
	 * Add a new element to the buffer.
	 */
	public final boolean put(E o)
	{
		int newPs = ps + 1;
		
		if (newPs >= buffer.length)
			newPs = 0;
		
		if (newPs == pl)
			return false;
		
		buffer[ps] = o;
		ps = newPs;

		return true;
	}

	/**
	 * Return true if the buffer is empty.
	 */
	public final boolean isEmpty()
	{
		return ps == pl;
	}

	/**
	 * Return true if the buffer is full.
	 */
	public final boolean isFull()
	{
		int newPs = ps + 1;
		if (newPs > buffer.length)
			newPs = 0;

		return newPs == pl ? true : false;

	}

	/**
	 * Return the size of the buffer.
	 */
	public final int getSize()
	{
		return buffer.length;
	}
	

	/**
	 * Check if the specified element exist in the queue
	 *
	 * @param o - Element to check.
	 * @since 1.02
	 */
	public final boolean contains(Object o)
	{
		int i = pl;

		while (i != ps)
		{
			if (buffer[i] == o)
				return true;
			
			if (++i >= buffer.length)
				i = 0;
		}

		return false;
	}
}
