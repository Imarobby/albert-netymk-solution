package mysrc;
import java.util.ArrayList;
import java.util.Comparator;

public class Sort {
	public static <T extends Comparable<T>> void mergeSort(T[] array) {
		ArrayList<T> tmparray = new ArrayList<T>(array.length);
		mergeSort(array, tmparray, 0, array.length - 1);
	}

	private static <T extends Comparable<T>> void mergeSort(T[] array,
			ArrayList<T> tmparray, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(array, tmparray, low, mid);
			mergeSort(array, tmparray, mid + 1, high);
			merge(array, tmparray, low, mid, high);
		}
	}

	private static <T extends Comparable<T>> void merge(T[] array,
			ArrayList<T> tmparray, int low, int mid, int high) {
		int begin = low;
		int b_2 = mid + 1;
		int num = high - low + 1;
		while (low <= mid && b_2 <= high) {
			if (array[low].compareTo(array[b_2]) < 0) {
				tmparray.add(begin++, array[low++]);
			} else
				tmparray.add(begin++, array[b_2++]);
		}
		while (low <= mid)
			tmparray.add(begin++, array[low++]);
		while (b_2 <= high)
			tmparray.add(begin++, array[b_2++]);
		for (; num > 0; num--) {
			array[high] = tmparray.get(high);
			high--;
		}
	}

	public static <T> void mergeSort(T[] array, Comparator<T> t) {
		ArrayList<T> tmparray = new ArrayList<T>(array.length);
		mergeSort(array, tmparray, 0, array.length - 1, t);
	}

	private static <T> void mergeSort(T[] array, ArrayList<T> tmparray,
			int low, int high, Comparator<T> t) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(array, tmparray, low, mid, t);
			mergeSort(array, tmparray, mid + 1, high, t);
			merge(array, tmparray, low, mid, high, t);
		}
	}

	private static <T> void merge(T[] array, ArrayList<T> tmparray, int low,
			int mid, int high, Comparator<T> t) {
		int begin = low;
		int b_2 = mid + 1;
		int num = high - low + 1;
		while (low <= mid && b_2 <= high) {
			if (t.compare(array[low], (array[b_2])) < 0) {
				tmparray.add(begin++, array[low++]);
			} else
				tmparray.add(begin++, array[b_2++]);
		}
		while (low <= mid)
			tmparray.add(begin++, array[low++]);
		while (b_2 <= high)
			tmparray.add(begin++, array[b_2++]);
		for (; num > 0; num--) {
			array[high] = tmparray.get(high);
			high--;
		}
	}

	public static <T extends Comparable<T>> void quickSort(T[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static <T extends Comparable<T>> void quickSort(T[] array, int low,
			int high) {
		if (high - low <= 10) {
			Sort.insertionSort(array);
		} else {
			int pivot = pivot(array);
			swap(array, pivot, high);
			int i, j;
			for (i = low, j = high - 1;;) {
				while (array[i++].compareTo(array[pivot]) < 0)
					;
				while (array[j--].compareTo(array[pivot]) > 0)
					;
				if (i < j)
					swap(array, i, j);
				else
					break;
			}
			swap(array, i, high);
			quickSort(array, low, i - 1);
			quickSort(array, i + 1, high);
		}
	}

	public static <T> void quickSort(T[] array, Comparator<T> t) {
		quickSort(array, 0, array.length - 1, t);
	}

	private static <T> void quickSort(T[] array, int low, int high,
			Comparator<T> t) {
		if (high - low <= 10) {
			Sort.insertionSort(array, t);
		} else {
			int pivot = pivot(array);
			swap(array, pivot, high);
			int i, j;
			for (i = low, j = high - 1;;) {
				while (t.compare(array[i++], array[pivot]) < 0)
					;
				while (t.compare(array[j--], array[pivot]) > 0)
					;
				if (i < j)
					swap(array, i, j);
				else
					break;
			}
			swap(array, i, high);
			quickSort(array, low, i - 1, t);
			quickSort(array, i + 1, high, t);
		}
	}

	private static <T> void swap(T[] array, int ind1, int ind2) {
		T tmp = array[ind1];
		array[ind1] = array[ind2];
		array[ind2] = tmp;
	}

	private static <T> Integer pivot(T[] array) {
		return (array.length) / 2;
	}

	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i - 1; j >= 0 && array[j + 1].compareTo(array[j]) < 0; j--) {
				swap(array, j + 1, j);
			}
		}
	}

	public static <T> void insertionSort(T[] array, Comparator<T> t) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i - 1; j >= 0 && t.compare(array[j + 1], array[j]) < 0; j--) {
				swap(array, j + 1, j);
			}
		}
	}
}
