package codepath.snake.exercise;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import codepath.snake.exercise.GameExercises.Direction;
import codepath.snake.objects.*;
import codepath.snake.scaffold.*;

public class GameObjectExercises extends GamePanel {
	private Snake snake;
	private Apple apple;
	private GameBoard gameBoard;
	private ScoreBoard scoreBoard;
	private boolean gameOver = false;
	
	// Constructor for GameObjectExercises
	public GameObjectExercises() {
		// Instantiate apple object
		apple = new Apple(18, 200, 200);
		// Instantiate gameBoard object
		gameBoard = new GameBoard(620, 620, 20);
		// Instantiate scoreBoard object
		scoreBoard = new ScoreBoard(0);
		// Instantiate snake object
		snake = new Snake(18, gameBoard);
				
	}
	
	// This method is called once a second to redraw the canvas,
	// so you can do things like draw the snake.
	public void redrawCanvas(Graphics canvas) {
		gameBoard.draw(canvas);
		scoreBoard.drawScore(canvas, gameOver);
		snake.draw(canvas);
		apple.draw(canvas);
	}

	// This method is called once a second, and it is a good place to
	// do things like check if the snake has collided into the wall
	// or the apple.
	public void onTimer() {
		if ((snake.hasHitTail() == false) && (snake.hasHitWall(gameBoard) == false)) {
			snake.move(gameBoard);
			if (snake.hasHitApple(apple)) {
				apple.setPos(gameBoard.getRandomXCoord(), gameBoard.getRandomYCoord());
				System.out.println("It hit the apple");
				scoreBoard.incrementPlayerScore();
			} 
		} else gameOver = true;
	}

	// This method is called whenever a keyboard button is pressed
	// within your game. The keyCode represents the key the actual key pushed.
	// You can check which keyCode using 'KeyEvent' constants.
	// i.e keyCode == KeyEvent.VK_G
	protected void onKeyPress(int keyCode) {
		// Move Up
		if (keyCode == KeyEvent.VK_UP) {
			snake.setDirection("up");
		}
		// Move Right
		if (keyCode == KeyEvent.VK_RIGHT) {
			snake.setDirection("right");
		}
		// Move Down
		if (keyCode == KeyEvent.VK_DOWN) {
			snake.setDirection("down");
		}
		// Move Left	 
		if (keyCode == KeyEvent.VK_LEFT) {
			snake.setDirection("left");
		}
	}
}
