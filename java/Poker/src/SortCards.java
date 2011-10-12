
public class SortCards {
	
		static private void swap(Card[] cards, int a, int b) {
			Card temp = cards[a];
			cards[a] = cards[b];
			cards[b] = temp;
		}

		static private int partition(Card[] cards, int left, int right,
				int pivotIndex) {
			Card pivotValue = cards[pivotIndex];
			swap(cards, pivotIndex, right);
			int storeIndex = left;
			for (int ii = left; ii < right; ii++) {
				if (cards[ii].value < pivotValue.value) {
					swap(cards, ii, storeIndex);
					storeIndex++;
				}
			}
			swap(cards, storeIndex, right);
			return storeIndex;
		}

		static public void quicksort(Card[] cards, int left, int right) {
			if (left < right) {
				int pivotIndex = (left + right) / 2;
				int pivotNewIndex = partition(cards, left, right, pivotIndex);
				quicksort(cards, left, pivotNewIndex - 1);
				quicksort(cards, pivotNewIndex + 1, right);
			}
		}

		static public void sort(Card[] cards) {
			quicksort(cards, 0, cards.length - 1);
		}

}
