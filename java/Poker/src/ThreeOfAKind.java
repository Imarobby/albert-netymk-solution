import java.util.*;
public class ThreeOfAKind {
	/**
	 * This method will return a list of all possible cards belonging to Three of a kind category.
	 * C(13,1)*C(4,1)*C(12,2)*4^2
	 */
	public static List<Card[]> allThreeOfAKind() {
		List<Card[]> result = new ArrayList<Card[]>();
		Card[] cards;
		Integer[] leftRanks;
		int[] indices;
		for(int i=0; i<Poker.ranks.length; ++i) {
			List<Integer> tmpList = new ArrayList<Integer>(Arrays.asList(Poker.ranks));
			tmpList.remove(i);
			leftRanks = tmpList.toArray(new Integer[0]);
			CombinationGenerator x = new CombinationGenerator(leftRanks.length, 2);
			while(x.hasMore()) {
				indices = x.getNext();
				for(int j=0; j<Math.pow(4,2); ++j) {
					for(int k=0; k<4; ++k) {
						cards = new Card[5];
						List<String> leftSuits = new ArrayList<String>(Arrays.asList(Poker.suits));
						leftSuits.remove(k);
						// first three cards
						for(int c=0; c<3; ++c) {
							cards[c] = new Card(i, leftSuits.get(c));
						}
						int number = j;
						for(int c=0; c<indices.length; ++c) {
							int base = (int)Math.pow(4, indices.length-c-1);
							int value = number/base;
							cards[3+c] = new Card(indices[c], Poker.suits[value]);
							number -= base*value;
						}
						result.add(cards);
					}
				}
			}
		}
		return result;
	}
}
