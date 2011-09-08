import java.util.Random;

public class Environment {
	final public static int HEIGHT = 4;
	final public static int WIDTH = 4;
	final private static Random rand = new Random();

	public static Environment copy(Environment env) {
		Environment result = new Environment();
		for (int i = 0; i < HEIGHT; ++i) {
			for (int j = 0; j < WIDTH; ++j) {
				result.floor[i][j] = env.floor[i][j];
			}
		}
		return result;
	}

	// True : clean;
	private boolean[][] floor;

	public Environment() {
		floor = new boolean[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; ++i) {
			for (int j = 0; j < WIDTH; ++j) {
				floor[i][j] = true;
			}
		}
	}

	public boolean getFloor(int i, int j) {
		return floor[i][j];
	}

	public void setFloor(int i, int j, boolean f) {
		floor[i][j] = f;
	}

	public void random() {
		for (int i = 0; i < HEIGHT; ++i) {
			for (int j = 0; j < WIDTH; ++j) {
				floor[i][j] = rand.nextBoolean();
			}
		}
	}

	public boolean IsDirty(int row, int col) {
		return !floor[row][col];
	}

	public void clean(int row, int col) {
		floor[row][col] = true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < HEIGHT; ++i) {
			for (int j = 0; j < WIDTH; ++j) {
				if (floor[i][j]) {
					sb.append("E" + " ");
				} else {
					sb.append("D" + " ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
