package codepath.snake.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import codepath.snake.exercise.GameExercises.Direction;

public class Snake {
	
	// This defines the size of the snake to draw
	private int drawSize;
	
	// The number of squares that make up this snake
	private int length = 4;
	
	// The list of coordinates of the snake's body
	private Point[] pos;
	
	// The direction of the snake (left, right, up, down)
	private String direction = "up";
	
	private int INITIALXPOS = 300;
	
	private int INITIALYPOS = 300;
	
	// The color of the snake
	Color snakeColor = Color.GREEN;
	
    // This is the constructor where we need to initialize everything
	// We need to initialize the direction, pos array, and the length
	// Also need to setup the initial location of the snake
	public Snake(int drawSize, GameBoard gameBoard) {
		pos = new Point[length];
		for (int i=0; i<pos.length; i++){
			pos[i] = new Point(INITIALXPOS+(i*gameBoard.getGridWidth()),INITIALYPOS);
		}
		this.drawSize = drawSize;
	}

	// Returns the x coordinate position of the snake's head
	public int getHeadPosX() {
		return pos[0].x;
	}

	// Returns the y coordinate position of the snake's head
	public int getHeadPosY() {
		return pos[0].y;
	}

	// Increases the length of the snake's tail by 1
	// Used to make the snake longer once an apple is eaten
	public void growTail() {
		// Needs to increment snake length
		// Instantiate a new coordinate array of with larger size
		//pos = Arrays.<Point>copyOf(pos, pos.length+1);
		//pos[pos.length-1].x = pos[pos.length-2].x;
		//pos[pos.length-1].y = pos[pos.length-2].y;		
		length++;
		Point[] temp = new Point[length];
		for (int i = length - 2; i >= 0; --i) {
		    Point p = pos[i];
		    if (p != null) {
		        temp[i] = new Point(p);
		    }
		}
		temp[length-1] = pos[length-2];
		pos = new Point[temp.length];
		pos = temp;
	
	}

	// Returns the direction the snake is headed
	// Direction is up, down, left or right
	public String getDirection() {
		return direction;
	}

	// Updates the direction the snake is headed
	// Accepts up, down, left or right
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	// Returns true if the snake's head has hit a wall; false otherwise
	// This is a game over condition when the head runs into any wall
	public boolean hasHitWall(GameBoard gameBoard) {
		if ((getHeadPosX() <=0) || (getHeadPosY() <= 0) || 
				(getHeadPosX() >= (gameBoard.getXPixels()-gameBoard.getGridWidth())) || 
				(getHeadPosY() >= (gameBoard.getYPixels()-gameBoard.getGridWidth()))) {
			return true;
		} else return false;
	}

	// Returns true if the snake's head has hit the body; false otherwise
	// This is a game over condition when the head runs into any part of the body
	public boolean hasHitTail() {
		// Check the position of every part of the tail
		// Returns true if any part has collided with the head
		
		for (int i=1; i<(pos.length); i++) {
			if (getHeadPosX() == pos[i].x && getHeadPosY() == pos[i].y) {
				return true;
			}
		} 
		return false;
	}

	public boolean hasHitApple(Apple apple) {
		if ((getHeadPosX() == apple.getPosX()) && (getHeadPosY() == apple.getPosY())) {
			System.out.println("It hit it!!!!!!");
			growTail();
			return true;
		} else return false;
	}
	
	// Moves the snake position coordinates in the direction he is heading.
	// Needs to move the tail and adjust the head in his direction.
	public void move(GameBoard gameBoard) {
		// First since a snake slides across the tiles,
		// move each tile (except the head) so that every tile is now equal
		// to the previous tile (to move all tiles).
		int i = pos.length-1;
		while (i > 0) {
			pos[i].x = pos[i-1].x;
			pos[i].y = pos[i-1].y;
			i--;
		}
		// Based on the current direction, we need to
		// move the head tile towards the next tile in that direction...
		if (direction == "up") { //Move North
			pos[0].y = pos[0].y - gameBoard.getGridWidth();
		} else if (direction == "right") { //Move East
			pos[0].x = pos[0].x + gameBoard.getGridWidth();
		} else if (direction == "down") { //Move South
			pos[0].y = pos[0].y + gameBoard.getGridWidth();
		} else if (direction == "left") { //Move West
			pos[0].x = pos[0].x - gameBoard.getGridWidth();
		}
	}	

	// Given the canvas, draw the snake on screen using known coordinates
	public void draw(Graphics canvas) {
		// Set the draw color
		// For every tile of the snake's body, draw a rectangle at that position
		 canvas.setColor(snakeColor);	    
	   	 for (int i=0; i<length; i++) {
	   	  	canvas.fillRect(pos[i].x, pos[i].y, drawSize, drawSize);
	   	 }
		
	}
}
