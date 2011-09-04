public class SimpleReflexAgent extends Agent {
	public void move() {
		switch (Direction.values()[rand.nextInt(4)]) {
		case UP: {
			if (row > 0) {
				row--;
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

	public SimpleReflexAgent(Environment env) {
		super(env);
	}

	public static class Test {
		public static void main(String[] args) {
			Environment env = new Environment();
			SimpleReflexAgent agent = new SimpleReflexAgent(env);
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
