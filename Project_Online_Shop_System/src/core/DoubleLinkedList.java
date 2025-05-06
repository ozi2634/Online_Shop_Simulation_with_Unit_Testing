package core;

class Node {
	Node next;
	Node previous;
	Object payload;

	Node(Object o) {
		payload = o;
	}
}

public class DoubleLinkedList extends AbstractContainer {

	private Node head;
	private Node tail;

	private int size = 0;

	@Override
	public boolean add(Object o) {

		if (o != null) {
			if (size == 0) {
				head = new Node(o);
				tail = new Node(o);

				head.next = tail;
				tail.previous = head;
			} else if (size == 1) {
				tail.payload = o;
			} else {
				Node newNode = new Node(o);

				newNode.previous = tail;
				tail.next = newNode;

				tail = newNode;
			}
			size++;
			return true;
		}
		return false;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index > size - 1 || index < 0) {
			throw (new IndexOutOfBoundsException());
		}
		if (index < size / 2) {
			return getFromHead(index);
		} else {
			return getFromTail(index);
		}
	}

	private Object getFromHead(int index) {
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.payload;
	}

	private Object getFromTail(int index) {
		Node current = tail;
		for (int i = 0; i < size - index - 1; i++) {
			current = current.previous;
		}
		return current.payload;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(Object o) throws NullPointerException {
		if (o == null) {
			throw (new NullPointerException());
		}
		if (size > 0) {

			if (head.payload.equals(o)) {
				head = head.next;
				size--;
				return true;
			} else if (tail.payload.equals(o)) {
				tail = tail.previous;
				size--;
				return true;
			}

			Node current = head.next;
			while (!current.payload.equals(o) && current.next != null) {
				current = current.next;
			}

			if (current.equals(tail)) {
				return false;
			}

			current.previous.next = current.next;
			current.next.previous = current.previous;
			size--;
			return true;

		}

		return false;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public boolean contains(Object o) throws NullPointerException {
		if (o == null) {
			throw (new NullPointerException());
		}
		if (size > 0) {

			if (head.payload.equals(o)) {
				return true;
			} else if (tail.payload.equals(o)) {
				return true;
			}

			Node current = head.next;
			while (!current.payload.equals(o) && current.next != null) {
				current = current.next;
			}

			if (current.equals(tail)) {
				return false;
			}

			return true;

		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		if (size == 0) {
			return arr;
		}
		Node current = head;
		int index = 0;
		while (current != null) {
			arr[index++] = current.payload;
			current = current.next;
		}
		return arr;
	}
}
