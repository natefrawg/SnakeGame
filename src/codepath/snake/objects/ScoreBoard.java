package codepath.snake.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreBoard {
	private int playerScore;
	Color scoreColor = Color.black;
	
	public ScoreBoard(int playerScore) {
		this.playerScore = playerScore;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int incrementPlayerScore(){
		playerScore++;
		return playerScore;
	}
	
	public void drawScore(Graphics canvas, boolean gameOver){
		String playerScoreStr=Integer.toString(playerScore);
		Font f = new Font("Helvetica Nueue", Font.BOLD, 24);
		canvas.setFont(f);
		canvas.setColor(scoreColor);
		if (gameOver == false) {
			canvas.drawString("Score: ", 50, 19);
			canvas.drawString(playerScoreStr, 195, 19);
		} else {
			canvas.drawString("Game Over: ", 50, 19);
			canvas.drawString(playerScoreStr, 195, 19);
		}
	}
	
}
