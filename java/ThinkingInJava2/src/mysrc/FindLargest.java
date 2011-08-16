public class FindLargest {
	/*
	 * @Override public int compare(String o1, String o2) { return
	 * o1.length()-o2.length(); }
	 */
	public static void main(String[] argv) {
		String[] arr1 = { "abcd", "ba", "cba" };
		String m1 = Max.<String> maxAll(arr1);
		Integer[] arr2 = { 1, 2, 3 };
		int m2 = Max.max(arr2[0], arr2[1]);
		m2 = Max.<Integer> maxAll(arr2);
		System.out.println(m2);
		FindLargest T = new FindLargest();
		// m1=Max.maxAll(arr, T);
		System.out.println(m2);
	}

}