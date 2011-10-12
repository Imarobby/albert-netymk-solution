public class Card implements Comparable {
	String suit;
	int value;
	

	public Card() {
	}

	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}

	public int compareTo(Object another) {
		if ((this.value == ((Card)another).value)
				&& (this.suit.compareTo(((Card)another).suit) == 0)) {
			return 0;
		} else {
			return this.value - ((Card)another).value;
		}
	}
	
	public String toString() {
		return value + " " + suit;
	}
}

