package codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class SnapperChain {
	private int[] chain;
	private int k;

	// public getResul
	public SnapperChain(int n, int k) {
		chain = new int[n];
		this.k = k;
	}

	public void process() {
		for (int i = 0; i < k; i++) {
			int j = 0;
			if (chain[0] == 0) {
				chain[0] = 1;
			} else {
				chain[0] = 0;
				j++;
				while (j < chain.length && chain[j] != 0) {
					chain[j] = 0;
					j++;
				}
				if (j != chain.length) {
					chain[j] = 1;
				}
			}

		}
	}

	public int result() {
		int i = 0;
		while (i < chain.length && chain[i] != 0) {
			i++;
		}
		if (i == chain.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// the time of cases;
		int cases;
		try {
			FileReader fin = new FileReader("small.in");
			BufferedReader in = new BufferedReader(fin);
			FileWriter fout = new FileWriter("small.out");
			BufferedWriter out = new BufferedWriter(fout);
			String line = in.readLine();
			cases = Integer.valueOf(line);
			StringTokenizer st;
			for (int i = 0; i < cases; i++) {
				line = in.readLine();
				st = new StringTokenizer(line);
				SnapperChain chain = new SnapperChain(Integer.valueOf(st
						.nextToken()), Integer.valueOf(st.nextToken()));
				chain.process();
				int result = chain.result();
				int j = i + 1;
				if (result == 1) {
					out.write("Case #" + j + ": ON\n");
				} else
					out.write("Case #" + j + ": OFF\n");
			}
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
