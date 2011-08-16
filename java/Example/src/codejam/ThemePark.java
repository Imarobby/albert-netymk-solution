package codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class ThemePark {
	private int r, k;
	private int[] groups;
	private int money;

	public ThemePark(int r, int k, int n) {
		this.r = r;
		this.k = k;
		groups = new int[n];
		money = 0;
	}

	public void fill(int n, int group) {
		groups[n] = group;
	}

	public int result() {
		int sum = 0;
		int l = 0;
		int j = 0;
		int flag = 0;
		for (int i = 0; i < r; i++) {
			while ((sum + groups[j]) <= k) {
				sum = sum + groups[j];
				j++;
				if (j == groups.length) {
					flag = 1;
					j = 0;
				}
				if (flag == 1) {
					if (j >= l) {
						break;
					}
				}
			}
			l = j;
			money = money + sum;
			sum = 0;
			flag = 0;
		}
		return money;

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
				int r = Integer.valueOf(st.nextToken());
				int k = Integer.valueOf(st.nextToken());
				int n = Integer.valueOf(st.nextToken());
				ThemePark park = new ThemePark(r, k, n);
				line = in.readLine();
				st = new StringTokenizer(line);
				for (int j = 0; j < n; j++) {
					park.fill(j, Integer.valueOf(st.nextToken()));
				}
				int result = park.result();
				int j = i + 1;
				out.write("Case #" + j + ": " + result + "\n");
			}
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
