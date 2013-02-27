package codepath.snake.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Apple {
	// This is the size of the apple to draw
	private int drawSize;
		
	// This is the coordinate position of the apple
	private Point pos;
	
	// This is the Apple Color
	Color appleColor = Color.RED;
	
	// Constructor for apple
	// Setup initial position of the apple
	// Store the draw size in drawSize field
	public Apple(int drawSize, int x, int y) {
		this.drawSize = drawSize;
		pos = new Point(x, y);
	}
	
	// Returns the x coordinate position for the apple
	public int getPosX() {
		return pos.x;
	}	
	
	
	// Returns the y coordinate position for the apple
	public int getPosY() {
		return pos.y;
	}
	
	// Update the position for the apple given the x and y coordinates
	public void setPos(int x, int y) {
		System.out.println(x);
		System.out.println(y);
		pos.x = x;
		pos.y = y;
	}
	
	
	// Given the canvas, draw the item on screen using coordinates
	public void draw(Graphics canvas) {
		// Set the draw color
		// Fill an oval based on the apple coordinates
		canvas.setColor(appleColor);	    
	   	canvas.fillOval(getPosX(), getPosY(), drawSize, drawSize);
	}
	
}