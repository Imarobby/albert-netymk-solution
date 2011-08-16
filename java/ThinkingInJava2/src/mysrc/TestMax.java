package mysrc;

public class TestMax implements java.util.Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return o1.length() - o2.length();
	}

	public static void main(String[] args) {
		String a = new String("abcsss");
		String b = new String("bcddssss");
		String c = new String("cdef");
		String ans = Max.max(a, b);
		String[] arr = { a, b, c };
		TestMax T = new TestMax();
		String ans1 = Max.max("abcss", "cdd", T);
		int ans2 = Max.max(2, 3);
		System.out.println(a.compareTo(b));
		System.out.println(Max.maxAll(arr));
		System.out.println(ans);
		System.out.println(ans1);
		System.out.println(ans2);
	}
}