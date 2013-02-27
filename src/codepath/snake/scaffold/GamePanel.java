package codepath.snake.scaffold;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public abstract class GamePanel extends JPanel implements ActionListener {
	// Sets the width and height of the canvas to 600x600
	private final int WIDTH = 600;
	private final int HEIGHT = 600;

	int drawSize = 25;
	boolean isGameRunning;

	// Creates a timer which 'ticks' and will trigger
	// the actionPerformed method every X interval
	// This is how the game progresses, the snake moves in
	// the specified direction every 'tick' of the timer.
	private Timer timer;

	public GamePanel() {
		setFocusable(true);
		addKeyListener(new GameKeyListener());
		timer = new Timer(150, this);
		startGame();
	}

	protected void startGame() {
		isGameRunning = true;
		timer.start();
	}

	protected void pauseGame() {
		timer.stop();
	}

	protected int getPanelHeight() {
		return HEIGHT;
	}

	protected int getPanelWidth() {
		return WIDTH;
	}

	// Draws every time the component is painted
	// This contains the contents of the frame
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		this.redrawCanvas(canvas);
		Toolkit.getDefaultToolkit().sync();
		canvas.dispose();
	}

	// This fires for every 'tick' of the timer
	// which means every specified interval. When this fires,
	// we should move the game forward which means checking
	// conditions (apple hit or game over) and moving the snake.
	public void actionPerformed(ActionEvent e) {
		onTimer();
		repaint();
	}

	protected abstract void onTimer();
	protected abstract void redrawCanvas(Graphics canvas);
	protected abstract void onKeyPress(int keyCode);

	// LISTENER
	private class GameKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			onKeyPress(e.getKeyCode());
		}
	}
}
