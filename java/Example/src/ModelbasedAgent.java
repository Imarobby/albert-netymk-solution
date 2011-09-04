public class ModelbasedAgent extends Agent {
	private boolean[][] model;
	private boolean preStart;
	private Direction initDirection;
	private Direction currentDirection;

	private void calculateInitDirection() {
		int minRow = row;
		Direction horizontal = Direction.LEFT;
		int minCol = col;
		Direction vertical = Direction.UP;
		if (row > Environment.HEIGHT / 2) {
			minRow = Environment.HEIGHT - row;
			horizontal = Direction.RIGHT;
		}
		if (col > Environment.WIDTH / 2) {
			minCol = Environment.WIDTH - col;
			vertical = Direction.DOWN;
		}
		if (minRow > minCol) {
			initDirection = vertical;
		} else {
			initDirection = horizontal;
		}
	}

	public ModelbasedAgent(Environment env) {
		super(env);
		preStart = true;
		model = new boolean[Environment.HEIGHT][Environment.WIDTH];
		for (int i = 0; i < Environment.HEIGHT; ++i) {
			for (int j = 0; j < Environment.WIDTH; ++j) {
				model[i][j] = false;
			}
		}
		model[row][col] = true;
		calculateInitDirection();
	}

	public void move() {
		if (preStart) {
			switch (initDirection) {
			case UP: {
				if (row > 0) {
					row--;
				} else {
					preStart = false;
				}
				break;
			}
			case DOWN: {
				if (row < Environment.HEIGHT - 1) {
					row++;
				} else {
					preStart = false;
				}
				break;
			}
			case LEFT: {
				if (col > 0) {
					col--;
				} else {
					preStart = false;
				}
				break;
			}
			case RIGHT: {
				if (col < Environment.WIDTH - 1) {
					col++;
				} else {
					preStart = false;
				}
				break;
			}
			}
		} else {
			switch (currentDirection) {
			case UP: {
				if (row > 0) {
					row--;
				} else {

				}
				break;
			}
			case DOWN: {
				if (row < Environment.HEIGHT - 1) {
					row++;
				}
				break;
			}
			case LEFT: {
				if (col > 0) {
					col--;
				}
				break;
			}
			case RIGHT: {
				if (col < Environment.WIDTH - 1) {
					col++;
				}
				break;
			}
			}
		}
	}

	public static class Test {
		public static void main(String[] args) {
			Environment env = new Environment();
			Agent agent = new ModelbasedAgent(env);
			System.out.println(env.toString());
			for (int i = 0; i < 100; ++i) {
				if (agent.IsDirty()) {
					agent.suck();
				} else {
					System.out.println("Row: " + agent.row + " Column: "
							+ agent.col);
					agent.move();
				}
			}
			System.out.println(env.toString());
		}
	}
}
