package codepath.snake.scaffold;

@SuppressWarnings("serial")
public abstract class FirstGamePanel extends GamePanel {
	int[] x;
	int[] y;
	int xStart = 300;
	int yStart = 300;
	String direction;
	int snakeLength;
	int appleX;
	int appleY;

	public FirstGamePanel() {
		snakeLength = 3;
		direction = "up";
		x = new int[] { xStart, xStart, xStart, xStart };
		y = new int[] { yStart, yStart + drawSize,
				yStart + (drawSize * 2),
				yStart + (drawSize * 3) };
		appleX = 150;
		appleY = 150;
	}
}