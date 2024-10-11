/**
   $Id: FastLinkedList.java 630 2011-06-09 07:12:05Z mviara $

   Copyright (c) 201, Mario Viara

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

/**
 * Fast double linked list<p>
 * This class implements a generic double linked list very fast because
 * the link removed from the list are inserted in one ring buffer and
 * insertion of one new element normally do not require to create a new
 * link if one already used is present in the ring buffer.
 * <p>To iterate
 * the list be carefully to remove element because the pointer are
 * destroyed so the next element must be saved BEFORE REMOVING the link
 * from the list.
 *
 * @author Mario Viara
 * @since 1.02
 */
public class FastLinkedList<T>
{
	/**
	 * Inner class to hold a link and the element.
	 */
	public static class Node<T>
	{
		private T element;
		private Node<T> next;
		private Node<T> prev;

		/**
		 * Default constructor
		 */
		private Node()
		{
			this.next = this.prev = this;
			element = null;
		}

		/**
		 * Constructor with element and pointer
		 */
		private Node(T e,Node<T> next,Node<T> prev)
		{
			this.element =e;
			this.next = next;
			this.prev = prev;
		}

		/**
		 * Return the element of this node
		 */
		public final T get()
		{
			return element;
		}

		/**
		 * Return the next node
		 */
		public final Node<T> Next()
		{
			return next;
		}

		/**
		 * Return the previous node
		 */
		public final Node<T> Previous()
		{
			return prev;
		}

		/**
		 * Return true if the node has a valid next
		 */
		public final boolean hasNext()
		{
			return next != this;
		}

	};


	/** Ring buffer with a pool of Entry */
	private RingBuffer<Node<T>> nodePool = new RingBuffer<Node<T>>(256);

	/** Head of the linked list */
	private Node<T> head = new Node<T>();

	/** Count of element */
	private int count = 0;

	/**
	 * Remove one element
	 */
	public void remove(Node<T> e)
	{
		e.prev.next = e.next;
		e.next.prev = e.prev;
		e.element = null;

		/** Return the link to the pool  */
		nodePool.put(e);

		count--;
	}

	/**
	 * Return a new link. <p>
	 * Try to get it from the ringbuffer, if empty create a new
	 * link.
	 */
	private Node<T> newLink(T e,Node<T> next,Node<T> prev)
	{
		Node<T> l = nodePool.get();


		if (l == null)
			l = new Node<T>(e,next,prev);
		else
		{
			l.element = e;
			l.next = next;
			l.prev = prev;
		}

		count++;
		
		return l;
	}

	void addBefore(Node<T> l,T element)
	{
		Node<T> l1 = newLink(element,l,l.prev);
		l1.prev.next = l1;
		l1.next.prev = l1;
	}

	public final void addToBegin(T element)
	{
		addBefore(head.next,element);
	}

	public final void addToEnd(T element)
	{
		addBefore(head,element);
	}

	public final Node<T> getHead()
	{
		return head;
	}
	
	public final Node<T> getFirst()
	{
		return head.next;
	}

	public final Node<T> getLast()
	{
		return head.prev;
	}

	public final boolean isHead(Node<T> l)
	{
		return l == head ? true : false;
	}

	public final int getSize()
	{
		return count;
	}

	public final Node<T> search(T element)
	{
		for (Node<T> e = head.next ; e != head ;e =e.next)
		{
			if (e.element == element)
				return e;
		}

		return  null;
	}

}
