import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Snake {

	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	private int lengthOfSnake = 3;
	private int moves = 0;

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	public Snake() {

		snakeXlength[2] = 50;
		snakeXlength[1] = 75;
		snakeXlength[0] = 100;

		snakeYlength[2] = 100;
		snakeYlength[1] = 100;
		snakeYlength[0] = 100;

	}

	public void drawSnake(Component c, Graphics g) {
		ImageIcon leftmouth = new ImageIcon("leftmouth.png");
		ImageIcon rigthmouth = new ImageIcon("rightmouth.png");
		ImageIcon upmouth = new ImageIcon("upmouth.png");
		ImageIcon downmouth = new ImageIcon("downmouth.png");
		ImageIcon snakeImage = new ImageIcon("snakeimage.png");

		for (int i = 0; i < lengthOfSnake; i++) {

			if (i == 0 && right || i == 0 && lengthOfSnake == 3) {
				rigthmouth.paintIcon(c, g, getSnakeXlength()[i], getSnakeYlength()[i]);
			}

			if (i == 0 && left) {
				leftmouth.paintIcon(c, g, getSnakeXlength()[i], getSnakeYlength()[i]);
			}

			if (i == 0 && up) {
				upmouth.paintIcon(c, g, getSnakeXlength()[i], getSnakeYlength()[i]);
			}

			if (i == 0 && down) {
				downmouth.paintIcon(c, g, getSnakeXlength()[i], getSnakeYlength()[i]);
			}

			if (i != 0) {
				snakeImage.paintIcon(c, g, getSnakeXlength()[i], getSnakeYlength()[i]);
			}
		}
	}

	public void moveRight() {
		moves++;
		right = true;

		if (!left) {

			right = true;

		} else {

			right = false;
			left = true;
		}

		up = false;
		down = false;
	}

	public void moveLeft() {

		moves++;
		left = true;

		if (!right) {
			left = true;

		} else {
			left = false;
			right = true;
		}

		up = false;
		down = false;
	}

	public void moveUp() {
		moves++;
		up = true;

		if (!down) {
			up = true;

		} else {
			up = false;
			down = true;
		}

		left = false;
		right = false;
	}

	public void moveDown() {
		moves++;
		down = true;

		if (!up) {
			down = true;
		} else {
			down = false;
			up = true;
		}

		left = false;
		right = false;
	}

	public void restartSnake() {

		moves = 0;
		lengthOfSnake = 3;

		snakeXlength[2] = 50;
		snakeXlength[1] = 75;
		snakeXlength[0] = 100;

		snakeYlength[2] = 100;
		snakeYlength[1] = 100;
		snakeYlength[0] = 100;
	}

	public int[] getSnakeXlength() {
		return snakeXlength;
	}

	public void setSnakeXlength(int position, int value) {
		this.snakeXlength[position] = value;
	}

	public int[] getSnakeYlength() {
		return snakeYlength;
	}

	public void setSnakeYlength(int position, int value) {
		this.snakeYlength[position] = value;
	}

	public int getLengthOfSnake() {
		return lengthOfSnake;
	}

	public void setLengthOfSnake(int lengthOfSnake) {
		this.lengthOfSnake = lengthOfSnake;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
