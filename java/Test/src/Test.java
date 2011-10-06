import java.util.*;

public class Test {
	private static Comparator<Card> comparator = new Comparator<Card>() {
		public int compare(Card o1, Card o2) {
			return o1.rank - o1.rank;
		}
	};
	private static TreeSet<Card> sortedHand = new TreeSet<Card>(comparator);

	public static int getUtility(List<Card> cards) {
		sortedHand.clear();
		sortedHand.addAll(cards);
		Card previous = null;
		for(Card current : cards) {
			if(previous != null) {
			}
			previous = current;
		}
		return 0;
	}
}
