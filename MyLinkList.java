import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.text.Position;

import org.ietf.jgss.Oid;

public class MyLinkList<E> implements List<E> {

	public class ListNode<T> {
		T data;
		ListNode<T> next;

		public ListNode(T data) {
			this.data = data;
			this.next = null;
		}

	}

	public class MyIterator<T> implements ListIterator<T> {

		ListNode<T> position = null;
		ListNode<T> prev = null;

		@SuppressWarnings("unchecked")
		@Override
		public void add(T e) {
			state = MOD;
			if (first == null) {
				position = new ListNode<T>(e);
				first = (ListNode<E>) new ListNode<T>(e);
				last = first;
			} else if (position == last) {
				/*
				 * ListNode<T> position.next = new ListNode<T>(position.data);
				 * position.data = e; position = position.next; last =
				 * (MyLinkList<E>.ListNode<E>) position;
				 */

				ListNode<T> nextel = new ListNode<T>(position.data);
				position.data = e;
				position.next = nextel;
				prev = position;
				position = nextel;
				last = (ListNode<E>) nextel;
			} else {
				if (position == null) {
					throw new IllegalArgumentException();
				}
				ListNode<T> temp = position.next;
				ListNode<T> nextel = new ListNode<T>(position.data);
				nextel.next = temp;
				position.data = e;
				position.next = nextel;
				prev = position;
				position = nextel;
			}
			return;
		}

		@Override
		public boolean hasNext() {
			return position != null;
		}

		@Override
		public boolean hasPrevious() {
			throw new UnsupportedOperationException();
			/*
			 * if(position>=0) return true; else return true;
			 */
		}

		@Override
		public T next() {
			state = NEXT;
			if (position == null)
				throw new NoSuchElementException();
			T data = position.data;
			prev = position;
			position = position.next;
			return data;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
			// return position++;
		}

		@Override
		public T previous() {
			throw new UnsupportedOperationException();
			// state = PREV;
			// return (T) array[position--];
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
			// return position-1;
		}

		@Override
		public void remove() {
			state = MOD;
			// for (int pos = position; pos<size-1;pos++){
			// array[pos]=array[pos+1];
			// }
		}

		static final int NEXT = 0, PREV = 1, MOD = 2;
		int state = NEXT;

		@Override
		public void set(T e) {
			if (prev == null)
				throw new IllegalStateException();
			if (state == NEXT)
				prev.data = e;
			// else if(state == PREV)
			// position.next.data = e;
			else if (state == MOD)
				throw new IllegalStateException();
		}

	} // end of the inner class

	ListNode<E> first = null;
	ListNode<E> active = null;
	ListNode<E> last = null;
	int size = 0;

	@Override
	public boolean add(E arg0) {
		ListNode<E> newlist = new ListNode<E>(arg0);
		if (first == null) {
			first = newlist;
			last = newlist;
		} else {
			last.next = newlist;
			last = newlist;

		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E data) {
		ListNode<E> position = first;
		if (index >= size) {
			add(data);
			return;
		}
		if (index == 0) {
			ListNode<E> newNode = new ListNode<E>(data);
			if (first == null) {
				last = newNode;
			}

			newNode.next = first;
			first = newNode;
			size++;
			return;
		}
		for (int i = 0; i < index - 1; i++) {
			position = position.next;
			if (position == null) {
				return;
			}
		}
		ListNode<E> newNode = new ListNode<E>(data);
		newNode.next = position.next;
		position.next = newNode;
		size++;
	}
	
	
	public String toString() {
		ListNode<E> position = first;
		String result = "";
		for (int i = 0; i < size; i++) {
			result += "[" + position.data + "]";
			position = position.next;
		}
		return result;

	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object arg0) {
		ListNode<E> position = first;
		for (int i = 0; i < size; i++) {
			if (position.data.equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {

		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		ListNode<E> remove = first;

		if (index >= size) {
			return null;
		}

		if (index == 0) {
			first = remove.next;
			size--;
			return remove.data;
		}
		for (int i = 0; i < index - 1; i++) {
			remove = remove.next;

		}
		if (index == size - 1) {
			last = remove;
		}
		E data = remove.next.data;
		remove.next = remove.next.next;

		size--;
		return data;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**		
	 * takes a list as parameter 
	 * @param sec1
	 * returns an intersections of two list objects  
	 * @return
	 */
	public LinkedList<E> intersection (LinkedList<E> listTwo)
	{
		ListNode<E> listOne = first;
		LinkedList<E> Answer = new LinkedList<E>();
		
		/**
		 * test  whether listOne is not pointer to an empty space
		 */
		while(listOne!=null) 
		{
			/**
			 * test whether the current data of  listOne matches any data in listTwo 
			 * and adds it to the answer list variable 
			 */
			if(listTwo.contains(listOne.data)) 
			{
				Answer.add(listOne.data);
			}
			/**
			 *  changes the pointer to next 
			 */
			listOne = listOne.next;  
		}
		
		/**
		 * returns answer
		 */
		return  Answer;
	}


}
