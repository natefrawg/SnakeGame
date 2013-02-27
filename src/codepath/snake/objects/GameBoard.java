package codepath.snake.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GameBoard {
	
	private int gridWidth;
	private int xPixels;
	private int yPixels;
	 
	Color outerBounds = Color.BLUE;
	Color innerBounds = Color.GRAY;		
			
	public GameBoard(int xPixels, int yPixels, int gridWidth) {
		this.xPixels = xPixels;
		this.yPixels = yPixels;
		this.gridWidth = gridWidth;
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
	
	public int getXPixels() {
		return xPixels;
	}
	
	public int getYPixels() {
		return yPixels;
	}
	
	public int getRandomXCoord(){
		Random rand = new Random();
		return (rand.nextInt(xPixels/gridWidth-2)+1)*gridWidth; // e.g. get a random number from 1 to 29, times 20
	}
	
	public int getRandomYCoord(){
		Random rand = new Random();
		return (rand.nextInt(yPixels/gridWidth-2)+1)*gridWidth; // e.g. get a random number from 1 to 29, times 20
	}
	
	public void draw(Graphics canvas) {
		// Set the draw color
		// Draw the out of bounds border
	   	canvas.setColor(outerBounds);	    
   	    canvas.fillRect(0, 0, xPixels, yPixels);
   	    
   	    // Set the draw color
   		// Draw the in bounds area
   	    canvas.setColor(innerBounds);
   	    canvas.fillRect(gridWidth, gridWidth, xPixels-gridWidth*2, yPixels-gridWidth*2);
	}
	   	
}
