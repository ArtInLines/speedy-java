package lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.Predicate;

public class UnorderedList<E> implements List<E>, RandomAccess, Cloneable {
	private static final int DEFAULT_CAPACITY = 16;

	private int cap = DEFAULT_CAPACITY;
	private int len = 0;
	private Object[] arr;

	public UnorderedList(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " +
					capacity);
		this.arr = new Object[capacity];
		this.cap = capacity;
	}

	public UnorderedList() {
		this.arr = new Object[DEFAULT_CAPACITY];
	}

	public UnorderedList(Collection<? extends E> c) {
		this.cap = c.size();
		this.len = cap;
		this.arr = c.toArray();
	}

	public int capacity() {
		return cap;
	}

	public int size() {
		return len;
	}

	public boolean isEmpty() {
		return len == 0;
	}

	public Object[] toArray() {
		return Arrays.copyOf(arr, len);
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < len)
			// Make a new array of a's runtime type, but my contents:
			return (T[]) Arrays.copyOf(arr, len, a.getClass());
		System.arraycopy(arr, 0, a, 0, len);
		if (a.length > len)
			a[len] = null;
		return a;
	}

	public int indexOfRange(Object o, int low, int high) {
		if (o == null) {
			for (int i = low; i < high; i++) {
				if (arr[i] == null)
					return i;
			}
		} else {
			for (int i = low; i < high; i++) {
				if (o.equals(arr[i]))
					return i;
			}
		}
		return -1;
	}

	public int indexOf(Object o) {
		return indexOfRange(o, 0, len);
	}

	public int lastIndexOfRange(Object o, int low, int high) {
		if (o == null) {
			for (int i = high - 1; i >= low; i--) {
				if (arr[i] == null)
					return i;
			}
		} else {
			for (int i = high - 1; i >= low; i--) {
				if (o.equals(arr[i]))
					return i;
			}
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		return lastIndexOfRange(o, 0, len);
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		Objects.checkIndex(index, len);
		return (E) arr[index];
	}

	@SuppressWarnings("unchecked")
	public E set(int index, E element) {
		Objects.checkIndex(index, len);
		E old = (E) arr[index];
		arr[index] = element;
		return old;
	}

	public boolean ensureCapacity(int minCapacity) {
		if (cap >= minCapacity)
			return false;
		grow(minCapacity);
		return true;
	}

	public void grow(int minCapacity) {
		int newCap = Math.max(cap + (cap >> 1), minCapacity);
		arr = Arrays.copyOf(arr, newCap);
	}

	public void grow() {
		cap = Math.max(DEFAULT_CAPACITY, cap + (cap >> 1));
		arr = Arrays.copyOf(arr, cap);
	}

	public boolean shrink(int maxCapacity) {
		if (len >= maxCapacity)
			return false;
		Arrays.copyOf(arr, maxCapacity);
		cap = maxCapacity;
		return true;
	}

	public boolean shrink() {
		int maxCapacity = Math.max(len, cap >> 1);
		Arrays.copyOf(arr, maxCapacity);
		cap = maxCapacity;
		return true;
	}

	public void add(int index, E element) {
		if (len == cap)
			grow();
		if (index == len) {
			arr[len] = element;
		} else {
			arr[len] = arr[index];
			arr[index] = element;
		}
		len++;
	}

	public boolean add(E element) {
		add(len, element);
		return true;
	}

	@SuppressWarnings("unchecked")
	public E remove(int index) {
		Objects.checkIndex(index, len);
		arr[index] = arr[len - 1];
		len--;
		E res = (E) arr[len];
		arr[len] = null;
		return res;
	}

	public boolean remove(Object o) {
		int idx = indexOf(o);
		if (idx == -1)
			return false;
		remove(idx);
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		boolean res = false;
		for (Object x : c) {
			if (remove(x))
				res = true;
		}
		shrink();
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean removeIf(Predicate<? super E> filter) {
		boolean res = false;
		int i = 0;
		while (i < len) {
			if (filter.test((E) arr[i])) {
				remove(i);
				res = true;
			} else {
				i++;
			}
		}
		shrink();
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> c) {
		boolean res = false;
		int i = 0;
		while (i < len) {
			if (!c.contains((E) arr[i])) {
				remove(i);
				res = true;
			} else {
				i++;
			}
		}
		shrink();
		return res;
	}

	public void clear() {
		for (int i = 0; i < len; i++)
			arr[i] = null;
		len = 0;
		shrink();
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	public boolean containsAll(Collection<?> c) {
		for (Object x : c) {
			if (indexOf(x) == -1)
				return false;
		}
		return true;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (index >= len)
			return addAll(c);
		ensureCapacity(len + c.size());
		int i = index;
		for (Object x : c) {
			arr[len] = arr[i];
			arr[i] = x;
			len++;
			i++;
		}
		return i != index;
	}

	public boolean addAll(Collection<? extends E> c) {
		ensureCapacity(len + c.size());
		for (Object x : c) {
			arr[len] = x;
			len++;
		}
		return c.size() > 0;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		// TODO
		throw new UnsupportedOperationException();
	}

	public Iterator<E> iterator() {
		return new Iter();
	}

	public ListIterator<E> listIterator() {
		return new ListIter(0);
	}

	public ListIterator<E> listIterator(int index) {
		return new ListIter(index);
	}

	private class Iter implements Iterator<E> {
		int cur = 0;

		// prevent creating a synthetic constructor
		Iter() {
		}

		@SuppressWarnings("unchecked")
		public E next() {
			if (cur >= UnorderedList.this.len)
				throw new NoSuchElementException();
			return (E) UnorderedList.this.arr[cur++];
		}

		@Override
		public boolean hasNext() {
			return cur < UnorderedList.this.len;
		}

		public void remove() {
			if (cur == 0)
				throw new NoSuchElementException();
			int l = UnorderedList.this.len;
			UnorderedList.this.arr[cur - 1] = UnorderedList.this.arr[l - 1];
			UnorderedList.this.arr[l - 1] = null;
			if (l != UnorderedList.this.len)
				throw new ConcurrentModificationException();
			UnorderedList.this.len--;
		}
	}

	private class ListIter extends Iter implements ListIterator<E> {
		ListIter(int index) {
			super();
			cur = index;
		}

		public boolean hasPrevious() {
			return cur != 0;
		}

		public int previousIndex() {
			return cur - 1;
		}

		public int nextIndex() {
			return cur;
		}

		@SuppressWarnings("unchecked")
		public E previous() {
			cur--;
			return (E) UnorderedList.this.arr[cur];
		}

		public void set(E e) {
			UnorderedList.this.set(cur - 1, e);
		}

		public void add(E e) {
			UnorderedList.this.add(cur, e);
			cur++;
		}

	}
}
