import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Poker {
	public static Integer[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	public static int fileIndex = 4;
	public static int period = 100000;
	public static int prefix = 2540 + (fileIndex-1)*period;
	public static BufferedWriter out;
	public static boolean writeToFile = false;
	private static final double STRAIGHT_FLUSH = 64974;
	private static final double FOUR_OF_A_KIND = 4165;
	private static final double FULL_HOUSE = 694.17;
	private static final double FLUSH = 504.85;
	private static final double STRAIGHT = 253.8;
	private static final double THREE_OF_A_KIND = 47.33;
	private static final double TWO_PAIR = 21.035;
	private static final double ONE_PAIR = 2.3665;
	private static final double HIGH_CARDS = 1.9953;

	public static final String[] suits = { "SPADES", "HEARTS", "DIAMONDS", "CLUBS" };

	private static Card[] cards;
	private static double score = 0;
	private static String category;
	// cards in the deck, which are available for exchanging.
	private static ArrayList cardsList = new ArrayList();

	/**
	 * This method will identify which category this hand belongs to and return the score for this category.
	 * TODO; Using "Hand" instead of Card[] in the argument.
	 */
	public static int calculateScore(Card[] cardsArg) {
		double result;
		int number_of_pair = 0;
		int number_of_cards_with_the_same_value = 0;

		Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);
		Arrays.sort(cards);
		result = HIGH_CARDS;// the highest value
		// of the cards
		// is the initial result
		category = "HIGH_CARDS";

		for (int ii = 0; ii < cards.length - 1; ii++) {
			if (cards[ii].value == cards[ii + 1].value) {
				number_of_pair++;
				if (((ii > 0) && (cards[ii - 1].value != cards[ii].value))
						|| ii == 0) {
					number_of_cards_with_the_same_value++;
						}
				number_of_cards_with_the_same_value++;
			}
		}

		// if it is STRAIGHT
		if (number_of_pair == 0) {
			for (int ii = 0; ii < cards.length - 1; ii++) {
				if ((cards[ii].value + 1) != cards[ii + 1].value) {
					break;
				}
				if (ii == (cards.length - 2)) {
					category = "STRAIGHT";
					result = STRAIGHT;
				}
			}
			// if it is FLUSH or STRAIGHT_FLUSH
			for (int jj = 0; jj < cards.length - 1; jj++) {
				if ((cards[jj].suit.compareTo(cards[jj + 1].suit)) != 0) {
					break;
				}

				if (jj == (cards.length - 2)) {
					if (category.compareTo("STRAIGHT") == 0) {
						category = "STRAIGHT_FLUSH";
						result = STRAIGHT_FLUSH;
					} else {
						category = "FLUSH";
						result = FLUSH;
					}
				}
			}

			if(!category.equals("FLUSH") && !category.equals("STRAIGHT_FLUSH")) {	
				if(cards[0].value == 1){
					cards[0].value = 14;
					Arrays.sort(cards);
				}


				for (int ii = 0; ii < cards.length - 1; ii++) {
					if ((cards[ii].value + 1) != cards[ii + 1].value) {
						break;
					}
					if (ii == (cards.length - 2)) {
						category = "STRAIGHT";
						result = STRAIGHT;
					}
				}
				// if it is FLUSH or STRAIGHT_FLUSH
				for (int jj = 0; jj < cards.length - 1; jj++) {
					if ((cards[jj].suit.compareTo(cards[jj + 1].suit)) != 0) {
						break;
					}

					if (jj == (cards.length - 2)) {
						if (category.compareTo("STRAIGHT") == 0) {
							category = "STRAIGHT_FLUSH";
							result = STRAIGHT_FLUSH;
						} else {
							category = "FLUSH";
							result = FLUSH;
						}
					}
				}
			}

		}

		// if it is ONE_PAIR
		else if (number_of_pair == 1) {
			category = "ONE_PAIR";
			// result = result + ONE_PAIR;
			result = ONE_PAIR;
		}

		// if it is THREE_OF_A_KIND
		else if (number_of_pair == 2) {
			if (number_of_cards_with_the_same_value == 3) {
				category = "THREE_OF_A_KIND";
				// result = result + THREE_OF_A_KIND;
				result = THREE_OF_A_KIND;
			}
			// if it is TWO_PAIR
			if (number_of_cards_with_the_same_value == 4) {
				category = "TWO_PAIR";
				// result = result + TWO_PAIR;
				result = TWO_PAIR;
			}
		}

		// if it is FULL_HOUSE or FOUR_OF_A_KIND
		else if (number_of_pair == 3) {
			if (number_of_cards_with_the_same_value == 5) {
				category = "FULL_HOUSE";
				// result = result + FULL_HOUSE;
				result = FULL_HOUSE;
			} else if (number_of_cards_with_the_same_value == 4) {
				category = "FOUR_OF_A_KIND";
				// result = result + FOUR_OF_A_KIND;
				result = FOUR_OF_A_KIND;
			}
		}

		return (int)result;
	}

	private static int[] newScore = new int[3];
	private static Map solutions = new HashMap(newScore.length);

	public static boolean[] suggestionForThisHand(Card[] cards) throws IOException {
		long before;
		init(cards);
		score = calculateScore(cards);
		if(writeToFile) {
			out.write(score+"\t");
		} else {
			System.out.print(score+"\t");
		}

		boolean[] result = new boolean[cards.length];
		for (int i = 0; i < newScore.length; i++) {
			newScore[i] = 0;
		}
		solutions.clear();

		// initialize the result (true -> change), no card is changed when
		// initialized
		for (int i = 0; i < cards.length; i++) {
			result[i] = false;
		}

		// one card is changed
		int[] index_1 = new int[1];
		for (int a = 0; a < cards.length; a++) {
			index_1[0] = a;
			int tempScore = expectedScore(index_1);
			if (newScore[0] < tempScore) {
				// System.out.println("With changing " + index_1[0] + " Expected new score is " + tempScore);
				newScore[0] = tempScore;
				solutions.put(0, new int[]{a});
			}
		}

		// two cards are changed
		int[] index_2 = new int[2];
		for (int a = 0; a < cards.length; a++) {
			index_2[0] = a;
			for (int b = a + 1; b < cards.length; b++) {
				index_2[1] = b;
				int tempScore = expectedScore(index_2);
				if (newScore[1] < tempScore) {
					newScore[1] = tempScore;
					solutions.put(1, new int[]{a, b});
				}
			}
		}

		// three cards are changed
		int[] index_3 = new int[3];
		for (int a = 0; a < cards.length; a++) {
			index_3[0] = a;
			for (int b = a + 1; b < cards.length; b++) {
				index_3[1] = b;
				for (int c = b + 1; c < cards.length; c++) {
					index_3[2] = c;
					int tempScore = expectedScore(index_3);
					if (newScore[2] < tempScore) {
						newScore[2] = tempScore;
						solutions.put(2, new int[]{a, b, c});
					}
				}
			}
		}

		/*
		// four cards are changed
		int[] index_4 = new int[4];
		for (int a = 0; a < cards.length; a++) {
		index_4[0] = a;
		for (int b = a + 1; b < cards.length; b++) {
		index_4[1] = b;
		for (int c = b + 1; c < cards.length; c++) {
		index_4[2] = c;
		for (int d = c + 1; d < cards.length; d++) {
		index_4[3] = d;
		int tempScore = expectedScore(index_4);
		if (score < tempScore) {
		score = tempScore;
		tempIndex[0] = a;
		tempIndex[1] = b;
		tempIndex[2] = c;
		tempIndex[3] = d;
		}
		}
		}
		}
		}

		// five cards are changed
		int[] index_5 = new int[5];
		for (int a = 0; a < cards.length; a++) {
		index_5[0] = a;
		for (int b = a + 1; b < cards.length; b++) {
		index_5[1] = b;
		for (int c = b + 1; c < cards.length; c++) {
		index_5[2] = c;
		for (int d = c + 1; d < cards.length; d++) {
		index_5[3] = d;
		for (int e = d + 1; e < cards.length; e++) {
		index_5[4] = e;
		int tempScore = expectedScore(index_5);
		if (score < tempScore) {
		score = tempScore;
		tempIndex[0] = a;
		tempIndex[1] = b;
		tempIndex[2] = c;
		tempIndex[3] = d;
		tempIndex[4] = e;
		}
		}
		}
		}
		}
		}
		*/


		int maxIndex = 0;
		int maxScore = newScore[0];
		for(int i=1; i<newScore.length; ++i) {
			if(newScore[i] > maxScore) {
				maxScore = newScore[i];
				maxIndex = i;
			}
		}
		if(maxScore > score) {
			for (int i = 0; i < ((int[])solutions.get(maxIndex)).length; i++) {
				result[((int[])solutions.get(maxIndex))[i]] = true;
			}
			if(writeToFile) {
				out.write(maxScore+"\t");
			} else {
				System.out.print(maxScore+"\t");
			}
		}
		return result;
	}

	public static void printCards() {
		for(int i=0; i<5; i++) {
			System.out.println(cards[i]);
		}
	}

	public static void printCards(Card[] cards) {
		for(int i=0; i<5; i++) {
			System.out.println(cards[i]);
		}
	}


	private static void init(Card[] cardsArg) {
		cards = cardsArg;
		addAll();
	}

	/*
	 * To test if the changed card is the same as any of the five in hand
	 */
	private static boolean notIncluded(Card test) {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].compareTo(test) == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Put all the rest 47 cards in the list
	 */
	private static void addAll() {
		cardsList.clear();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < suits.length; j++) {
				Card newCard = new Card(i, suits[j]);
				if (notIncluded(newCard)) {
					cardsList.add(newCard);
				}
			}
		}
	}

	/**
	 * The length of index array indicates how many cards will be changed, and the content of the array indicates which cards will be
	 * changed.
	 * index.length: [1, 5].
	 * index[i]: [0, 4].
	 */
	private static int expectedScore(int[] index) {
		ArrayList clonedList = new ArrayList(cardsList);
		int finalScore = 0;
		int tSindex = 0;
		Card[] cardsCopy = Arrays.copyOf(cards, cards.length);
		switch(index.length) {
			case 1:
				for(int a=0; a<clonedList.size(); ++a) {
					cardsCopy[index[0]] = (Card) clonedList.get(a);
					int tmpScore = calculateScore(cardsCopy);
					// printCards(cardsCopy);
					// System.out.println("The score for the above hand is " + tmpScore);
					finalScore += tmpScore;
					tSindex++;
				}
				break;
			case 2:
				for(int a=0; a<clonedList.size(); ++a) {
					cardsCopy[index[0]] = (Card) clonedList.get(a);
					for(int b=a+1; b<clonedList.size(); ++b) {
						cardsCopy[index[1]] = (Card) clonedList.get(b);
						int tmpScore = calculateScore(cardsCopy);
						finalScore += tmpScore;
						tSindex++;
					}
				}
				break;
			case 3:
				for(int a=0; a<clonedList.size(); ++a) {
					cardsCopy[index[0]] = (Card) clonedList.get(a);
					for(int b=a+1; b<clonedList.size(); ++b) {
						cardsCopy[index[1]] = (Card) clonedList.get(b);
						for(int c=b+1; c<clonedList.size(); ++c) {
							cardsCopy[index[2]] = (Card) clonedList.get(c);
							int tmpScore = calculateScore(cardsCopy);
							finalScore += tmpScore;
							tSindex++;
						}
					}
				}
				break;
				/*
				   case 4:
				   for(int a=0; a<clonedList.size(); ++a) {
				   cardsCopy[index[0]] = (Card) clonedList.get(a);
				   for(int b=a+1; b<clonedList.size(); ++b) {
				   cardsCopy[index[1]] = (Card) clonedList.get(b);
				   for(int c=b+1; c<clonedList.size(); ++c) {
				   cardsCopy[index[2]] = (Card) clonedList.get(c);
				   for(int d=c+1; d<clonedList.size(); ++d) {
				   cardsCopy[index[3]] = (Card) clonedList.get(d);
				   int tmpScore = calculateScore(cardsCopy);
				   finalScore += tmpScore;
				   tSindex++;
				   }
				   }
				   }
				   }
				   break;
				   case 5:
				   for(int a=0; a<clonedList.size(); ++a) {
				   cardsCopy[index[0]] = (Card) clonedList.get(a);
				   for(int b=a+1; b<clonedList.size(); ++b) {
				   cardsCopy[index[1]] = (Card) clonedList.get(b);
				   for(int c=b+1; c<clonedList.size(); ++c) {
				   cardsCopy[index[2]] = (Card) clonedList.get(c);
				   for(int d=c+1; d<clonedList.size(); ++d) {
				   cardsCopy[index[3]] = (Card) clonedList.get(d);
				   for(int e=d+1; e<clonedList.size(); ++e) {
				   cardsCopy[index[4]] = (Card) clonedList.get(e);

				   int tmpScore = calculateScore(cardsCopy);
				   finalScore += tmpScore;
				   tSindex++;
				   }
				   }
				   }
				   }
				   }
				   break;
				   */
			default:
				new RuntimeException("Length of index is not in the range of [1, 5]");
		}
		return finalScore/tSindex;
	}

	public static void main(String[] agrs) throws IOException{
		/*
		   Card[] myCards = new Card[5];
		   myCards[0] = new Card(9,"SPADES");
		   myCards[1] = new Card(9,"CLUBS");
		   myCards[2] = new Card(9,"DIAMONDS");
		   myCards[3] = new Card(7,"CLUBS");
		   myCards[4] = new Card(1,"DIAMONDS");

		   boolean[] chgcd = Poker.suggestionForThisHand(myCards);
		   for (int ii = 0; ii < chgcd.length; ii++){
		   System.out.println(chgcd[ii]);
		   }
		   */
		try {
			Poker.out = new BufferedWriter(new FileWriter("output" + fileIndex));
			Poker.writeToFile = true;
		} catch (IOException e) {
			System.err.println(e);
		}
		List<Card[]> list = HighCard.allHighCards();
		// System.out.println(list.size());
		System.out.println("Index\tCurrent score\tExpected new score\tNumber of dropped cards");
		for(int i=prefix; i<prefix+period; ++i) {
			Card[] cards = list.get(i);
			// printCards(cards);
			// System.out.println(fileIndex*period + i);
			System.out.println(i);
			// System.out.print((fileIndex*period + i)+"\t");
			// out.write((fileIndex*period + i)+"\t");
			out.write(i+"\t");
			boolean[] change = Poker.suggestionForThisHand(cards);
			int numberOfTrue=0;
			for(boolean bool : change) {
				if(bool) {
					numberOfTrue++;
				}
			}
			// System.out.print(numberOfTrue);
			// System.out.println();
			out.write(numberOfTrue+"");
			out.newLine();
			out.flush();
		}
		out.close();
		/*
		List<Card[]> list = ThreeOfAKind.allThreeOfAKind();
		System.out.println(list.size());
		*/
	}
}
