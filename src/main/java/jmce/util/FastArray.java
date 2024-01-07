/**
   $Id: FastArray.java 946 2012-12-02 11:01:18Z mviara $

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

import java.util.Arrays;

/**
 * 
 * A class like  standard java.util.ArrayList but without implementation of list
 * and synchronization for maximum performance.
 * 
 * @author Mario Viara
 * @version 1.02
 * 
 */
public class FastArray<E>
{
	private int growSize;
	private Object objects[];
	private int currentSize;

	/**
	 * Constructor with initial capacity of the array and growing
	 * size.
	 *
	 * @param initialCapacity - Initial capacity of the array.
	 * @param growSize - When the array is full it will be grow of
	 * growSize element. If the growing size is 0 the array will
	 * double it's capacity.
	 */
	public FastArray(int initialCapacity,int growSize)
	{
		objects = new Object[initialCapacity];
		currentSize = 0;
		this.growSize = growSize;
	}

	/**
	 * Constructor with the initial capacity specified and a
	 * default growing size of 10.
	 */
	public FastArray(int n)
	{
		this(n,10);
	}

	/**
	 * Default constructor.
	 */
	public FastArray()
	{
		this(0);
	}

	/**
	 * Set the grow size. If 0 when the array must be expanded the
	 * size will be doubled.
	 */
	public final void setGrowSize(int n)
	{
		growSize = n;
	}

	/**
	 * Return the grow size.
	 */
	public final int getGrowSize()
	{
		return growSize;
	}

	/**
	 * Return the size of the array.
	 *
	 * @deprecated Use getSize() method.
	 *
	 */
	@Deprecated
	public final int size()
	{
		return currentSize;
	}

	/**
	 * Return the size of the array.
	 *
	 * @since 1.01
	 */
	public final int getSize()
	{
		return currentSize;
	}
	
	/**
	 * Check if the array can store one more object. If the vector
	 * is not enough big grow it of growSize object and copy the old
	 * vector.
	 */
	private final void checkSize()
	{
		if (currentSize >= objects.length)
		{
			int n = growSize == 0 ? objects.length : growSize;
			Object newObjects[] = new Object[objects.length+n];
			System.arraycopy(objects,0,newObjects,0,objects.length);
			objects = newObjects;
			newObjects = null;
		}

	}

	/**
	 * Clear the array element
	 */
	public final void clear()
	{
		currentSize = 0;
	}

	/**
	 * Add a new element to the end of the array
	 */
	public final void add(E o)
	{
		checkSize();
		objects[currentSize++] = o;
	}

	/**
	 * Insert a new element at the specified position.
	 */
	public final void add(int i,Object o)
	{
		checkSize();
		System.arraycopy(objects,i,objects,i+1,currentSize - i);
		objects[i] = o;
		currentSize++;
	}

	/**
	 * Return the index of the specified element or -1 if the
	 * element is not present in the array.
	 */
	public final int indexOf(Object o)
	{
		for (int i = currentSize - 1 ; i >= 0 ; i --)
			if (objects[i] == o)
				return i;

		return -1;
	}


	/**
	 * Remove one element from the arrag.
	 */
	public final void remove(int i)
	{
		if (i < currentSize && i >= 0)
		{
			objects[i] = null;

			int num = currentSize - i - 1;
			if (num > 0)
				System.arraycopy(objects,i+1,objects,i,num);
			currentSize--;
		}

	}


	/**
	 * Remove the specified object from the array.
	 */
	public final void remove(Object o)
	{
		int i = indexOf(o);
		if (i != -1)
			remove(i);
	}

	/**
	 * Return the element at the specified position
	 */
	@SuppressWarnings("unchecked")
	public final E get(int i)
	{
		return (E)objects[i];
	}

	/**
	 * Return true if the array contains the specified object.
	 */
	public final boolean contains(Object o)
	{
		return indexOf(o) != -1;
	}

	/**
	 * Return an array with all elements.
	 *
	 * @since 1.02
	 */
	@SuppressWarnings("unchecked")
	public final E[] toArray(E[] a)
	{
		return (E[]) Arrays.copyOf(objects, currentSize, a.getClass());
	}
}
