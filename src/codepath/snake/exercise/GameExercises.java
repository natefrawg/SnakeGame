package codepath.snake.exercise;
/*Snakes Game
Exercise 4 - Loops

Goal: Practice using loops.

Description: In this exercise, we will refactor our code to use loops,
so that we can change the length of the snake.

Instructions:
  1) Open GameExercises.java
  2) Refactor the snake drawing code to use a loop instead of manually
     drawing the snake.
  3) Try changing the length of the snake to 8.
  4) Refactor the move code to use a for loop.
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import codepath.snake.scaffold.*;

@SuppressWarnings("serial")
public class GameExercises extends FirstGamePanel {
	int xSnakePos[] = {300, 300, 300, 300};
	int ySnakePos[] = {300, 320, 340, 360};
	int gridWidth = 20;
	int snakeWidth = 18;
	int snakeMargin = 2;
	Color snakeColor = Color.GREEN;
	Color outOfBounds = Color.BLUE;
	Color inBounds = Color.GRAY;
	int xApplePos = 100;
	int yApplePos = 100;
	int appleWidth = 18;
	int timeCounter = 0;
	int xPixels = 600;
	int yPixels = 600;
	boolean gameOver = false;
	int playerScore = 0;
	Direction direction = Direction.UP;
	
	public enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}
	
	public int randomXCoord(){
		Random rand = new Random();
		return rand.nextInt(xPixels/gridWidth-2)+1; // e.g. get a random number from 1 to 29
	}
	
	public int randomYCoord(){
		Random rand = new Random();
		return rand.nextInt(yPixels/gridWidth-2)+1; // e.g. get a random number from 1 to 29
	}
	
	public void checkCollision() {
		// make sure snake doesn't hit walls
		if (xSnakePos[0] <= 0 || ySnakePos[0] <= 0 || xSnakePos[0] >= (xPixels-gridWidth) || ySnakePos[0] >= (yPixels-gridWidth)) {
			gameOver = true;
		}
		// make sure snake doesn't hit itself
		for (int i=1; i<(xSnakePos.length); i++) {
			if (xSnakePos[0] == xSnakePos[i] && ySnakePos[0] == ySnakePos[i]) {
				gameOver = true;
			}
		}
	}
		
	public void checkApple(){
		// if snake is on the apple, then grow the snake by 1 and move the apple
		if (xSnakePos[0] == xApplePos && ySnakePos[0] == yApplePos) {
			// grow the snake by 1 square
			xSnakePos = Arrays.copyOf(xSnakePos, xSnakePos.length+1);
			ySnakePos = Arrays.copyOf(ySnakePos, ySnakePos.length+1);
			xSnakePos[xSnakePos.length-1] = xSnakePos[xSnakePos.length-2];
			ySnakePos[ySnakePos.length-1] = ySnakePos[ySnakePos.length-2];
			// move the apple
			xApplePos = randomXCoord()*gridWidth;
			yApplePos = randomYCoord()*gridWidth;
			// update score
			playerScore++;
		}
	}
	
	// draw the snake
	public void drawSnake(Graphics canvas){
   	    canvas.setColor(snakeColor);	    
   	    for (int i=0; i<(xSnakePos.length); i++) {
   	    	canvas.fillRect(xSnakePos[i], ySnakePos[i], snakeWidth, snakeWidth);
	    }
	}
			
	public void moveSnake(){
		// Move snake tail pieces forward to previous piece location
		int i = ySnakePos.length-1;
		while (i > 0) {
			xSnakePos[i] = xSnakePos[i-1];
			ySnakePos[i] = ySnakePos[i-1];
			i--;
		}
		// Move head to a new coordinate
		if (direction == Direction.UP) { //Move North
			ySnakePos[i] = ySnakePos[i] - gridWidth;		
		} else if (direction == Direction.RIGHT) { //Move East
			xSnakePos[i] = xSnakePos[i] + gridWidth;
		} else if (direction == Direction.DOWN) { //Move South
			ySnakePos[i] = ySnakePos[i] + gridWidth;
		} else if (direction == Direction.LEFT) { //Move West
			xSnakePos[i] = xSnakePos[i] - gridWidth;
		}
	}
		
	public void drawApple(Graphics canvas, int xPos, int yPos){
		canvas.setColor(Color.RED);
		canvas.fillOval(xPos, yPos, appleWidth, appleWidth);
	}
	
	public void drawScore(Graphics canvas){
		String playerScoreStr=Integer.toString(playerScore);
		Font f = new Font("Helvetica Nueue", Font.BOLD, 24);
		canvas.setFont(f);
		if (gameOver == false) {
			canvas.drawString("Score: ", 50, 19);
			canvas.drawString(playerScoreStr, 195, 19);
		} else {
			canvas.drawString("Game Over: ", 50, 19);
			canvas.drawString(playerScoreStr, 195, 19);
		}
	}
	
	public void drawBorder(Graphics canvas) {
		canvas.setColor(outOfBounds);	    
   	    canvas.fillRect(0, 0, xPixels, yPixels);
   	    canvas.setColor(inBounds);
   	    canvas.fillRect(gridWidth, gridWidth, xPixels-gridWidth*2, yPixels-gridWidth*2);
	}
	
	// This method is called once a second to redraw the canvas,
	// so you can do things like draw the snake.
	public void redrawCanvas(Graphics canvas) {
		drawBorder(canvas);
		drawSnake(canvas);
		drawApple(canvas, xApplePos, yApplePos);	
	    drawScore(canvas);
	}

	// This method is called once a second, and it is a good place to
	// do things like check if the snake has collided into the wall
	// or the apple.
	public void onTimer() {
		timeCounter = timeCounter+1;
		if (timeCounter % 1 == 0 && gameOver != true) { 
			moveSnake();
			checkCollision();
			checkApple();
		}
	}

	// This method is called whenever a keyboard button is pressed
	// within your game. The keyCode represents the key the actual key pushed.
	// You can check which keyCode using 'KeyEvent' constants.
	// i.e keyCode == KeyEvent.VK_G
	protected void onKeyPress(int keyCode) {
		// Rotate direction clockwise
		if (keyCode == KeyEvent.VK_RIGHT) {
			direction = Direction.values()[(direction.ordinal()+1) % Direction.values().length];
		// Rotate direction counter-clockwise
		} else if (keyCode == KeyEvent.VK_LEFT) {
			direction = Direction.values()[(direction.ordinal()+3) % Direction.values().length];
		}
	}
}
