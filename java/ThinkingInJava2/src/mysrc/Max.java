package mysrc;

class Max {

	public static final <E extends Comparable<E>> E max(E a, E b) {
		if (a.compareTo(b) <= 0)
			return b;
		else
			return a;
	}

	public static final <E> E max(E a, E b, java.util.Comparator<E> c) {
		if (c.compare(a, b) <= 0)
			return b;
		else
			return a;
	}

	public static <E extends Comparable<E>> E maxAll(E[] arr) {
		E m = arr[0];
		for (int i = 1; i < arr.length; i++) {
			m = Max.max(m, arr[i]);
		}
		return m;
	}

	public static <E> E maxAll(E[] arr, java.util.Comparator<E> c) {
		E m = arr[0];
		for (int i = 1; i < arr.length; i++) {
			m = Max.max(m, arr[i], c);
		}
		return m;
	}
}
