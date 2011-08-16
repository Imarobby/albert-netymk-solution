package algorithm;

import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

class Pirates {
	private static int N;		// the number of pirates
	private static final int DIAMONDS = 500;		// the number of DIAMONDS

	public int[] array;

	private int i;		// iteration variable through the array
	private int j;		// iteration variable through the array
	private int out = 0;	// the number of DIAMONDS we give out

	private PriorityQueue<Person> queue;
	private Person current;

	public Pirates(int N) {
		this.N = N;
		array = new int[N];
		queue = new PriorityQueue<Person>(N);
		// the case for N <= 4 is trivial, so we begin from N >= 5
		array[0] = 0;
		array[1] = 0;
		array[2] = 0;
		array[3] = DIAMONDS;
		for (i = 4; i < N; ++i) {
			// put previous result in the queue
			for (j = 0; j < i; ++j) {
				queue.add(new Person(array[j], j));
			}

			Set<Integer> set = new HashSet<Integer>(i/2+1);

			// we need i/2 + 1 votes, so we pick up the first i smallest numbers in the array
			for (j = 0; j < i/2+1; ++j) {
				// firstly, find the first i smallest numbers
				current = queue.poll();
				set.add(current.position);
				// so long as that the next situation is better than the previous one, it is OK
				// secondly, increment the result we get by one, and figure it out
				array[current.position] = current.number + 1;
				out = out + array[current.position];
			}
			// we have find one solution, but it is probably not unique
			// TODO find all other solutions

			// update the array
			for (j = 0; j < i; ++j) {
				if (!set.contains(j)) {
					array[j] = 0;
				}
			}

			// how many DIAMONDS do we left
			array[i] = DIAMONDS - out;

			// get ready for the next iteration
			set.clear();
			queue.clear();
			out = 0;
		}
	}

	private static class Person implements Comparable<Person> {
		public int number;
		public int position;

		public Person(int number, int position) {
			this.number = number;	
			this.position = position;	
		}
		@Override
			public int compareTo(Person T) {
				if(this.number < T.number)
					return -1;
				else if (this.number > T.number)
					return 1;
				else return 0;
			}
	}

	public static class Test{
		public static void main(String[] args) {
			Pirates pirates = new Pirates(Integer.parseInt(args[0]));

			// the proposal is done
			for (int i = N-1; i >= 0; --i) {
				System.out.print(pirates.array[i] + " ");
			}
			System.out.println();
		}
	}
}
