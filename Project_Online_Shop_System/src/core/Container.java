package core;

public interface Container {

	boolean add(Object o);

	Object get(int index);

	int size();

	boolean remove(Object o);

	boolean equals(Object o);

	void clear();

	default boolean contains(Object o) throws NullPointerException {
		if (o == null) {
			throw (new NullPointerException());
		}
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	default Object[] toArray() {
		Object[] arr = new Object[size()];
		for (int i = 0; i < size(); i++) {
			arr[i] = get(i);
		}
		return arr;
	}

	default boolean isEmpty() {
		return size() == 0;
	}
}