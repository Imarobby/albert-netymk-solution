import java.util.*;

public class HighCard {
	/**
	 * This method will return a list of all possible cards belonging to HighCards category.
	 * (4^5 - 4) * (C(13,5) - 10)
	 */
	public static List<Card[]> allHighCards() {
		List<Card[]> result = new ArrayList<Card[]>();
		Card[] cards;
		int[] indices;
		CombinationGenerator x = new CombinationGenerator(Poker.ranks.length, 5);
		while(x.hasMore()) {
			indices = x.getNext();
			if(!isStraight(indices)) {
				for(int n=0; n<Math.pow(4, 5); ++n) {
					// Get rid of flush
					// 341 = 4^4 + 4^3 + 4^2 + 4^1 + 4^0
					if(n%341 == 0) {
						// System.out.println("Flush " + n);
						continue;
					}	   
					cards = new Card[indices.length];
					int number = n;
					for(int i=0; i<indices.length; ++i) {
						int base = (int)Math.pow(4,indices.length-i-1);
						int value = number/base;
						cards[i] = new Card(Poker.ranks[indices[i]], Poker.suits[value]);
						number -= base*value;
					}
					result.add(cards);
				}
			}
		}
		return result;
	}

	private static boolean isStraight(int[] indices) {
		for(int i=0; i<indices.length-1; ++i) {
			if(indices[i]+1 != indices[i+1]) {
				break;
			}
			if(i == indices.length-2) {
				return true;
			}
		}
		// Including Ace
		if(indices[0] == 0) {
			for(int i=1; i<indices.length; ++i) {
				if(indices[i] != i+8) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
