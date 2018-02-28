import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	final static int[] enemyXpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	final static int[] enemyYpos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon leftmouth;
	private ImageIcon rigthmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakeImage;
	private ImageIcon titleImage;
	private ImageIcon enemyImage;
	private ImageIcon wallImage;

	private Timer timer;

	private int delay = 100;
	private int score = 0;

	private Random random = new Random();
	private int Xpos = random.nextInt(34);
	private int Ypos = random.nextInt(23);

	private boolean isColission = false;

	Snake snake = new Snake();
	CollisionDetector coldet = new CollisionDetector();
	
	List<Wall> wallList = new ArrayList<Wall>();

	public Gameplay() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics g) {

		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		// draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		// draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);

		// draw background for gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);

		// draw score
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);

		rigthmouth = new ImageIcon("rightmouth.png");
		rigthmouth.paintIcon(this, g, snake.getSnakeXlength()[0], snake.getSnakeYlength()[0]);

		for (int i = 0; i < snake.getLengthOfSnake(); i++) {

			if (i == 0 && right) {
				rigthmouth = new ImageIcon("rightmouth.png");
				rigthmouth.paintIcon(this, g, snake.getSnakeXlength()[i], snake.getSnakeYlength()[i]);
			}

			if (i == 0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snake.getSnakeXlength()[i], snake.getSnakeYlength()[i]);
			}

			if (i == 0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snake.getSnakeXlength()[i], snake.getSnakeYlength()[i]);
			}

			if (i == 0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snake.getSnakeXlength()[i], snake.getSnakeYlength()[i]);
			}

			if (i != 0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snake.getSnakeXlength()[i], snake.getSnakeYlength()[i]);
			}
		}

		enemyImage = new ImageIcon("enemy.png");
		wallImage = new ImageIcon("wall.png");

		if (enemyXpos[Xpos] == snake.getSnakeXlength()[0] && enemyYpos[Ypos] == snake.getSnakeYlength()[0]) {
			score++;
			snake.setLengthOfSnake(snake.getLengthOfSnake() + 1);
			Xpos = random.nextInt(34);
			Ypos = random.nextInt(23);

			wallList.add(new Wall());
		}

		enemyImage.paintIcon(this, g, enemyXpos[Xpos], enemyYpos[Ypos]);

		for (int i = 0; i < wallList.size(); i++) {
			wallImage.paintIcon(this, g, wallList.get(i).getX(), wallList.get(i).getY());
		}

		

			if (coldet.collision(snake) || coldet.collision(snake, wallList)) {
				right = false;
				left = false;
				up = false;
				down = false;

				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game over", 300, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART", 350, 340);
				
				timer.stop();
			}
		

		g.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			snake.restartSnake();
			score = 0;
			snake.setLengthOfSnake(3);
			isColission = false;
			wallList.clear();
			timer.start();
			repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			snake.setMoves(snake.getMoves() + 1);
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

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			snake.setMoves(snake.getMoves() + 1);
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

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			snake.setMoves(snake.getMoves() + 1);
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

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			snake.setMoves(snake.getMoves() + 1);
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		timer.start();

		if (right) {

			for (int i = snake.getLengthOfSnake() - 1; i >= 0; i--) {
				snake.setSnakeYlength(i+1, snake.getSnakeYlength()[i]);
			}

			for (int i = snake.getLengthOfSnake(); i >= 0; i--) {

				if (i == 0) {
					snake.setSnakeXlength(i, snake.getSnakeXlength()[i]+25);
				} else {
					snake.setSnakeXlength(i, snake.getSnakeXlength()[i-1]);
				}

				if (snake.getSnakeXlength()[i] > 850) {
					snake.setSnakeXlength(i,25);
				}
			}

			repaint();

		}

		if (left) {

			for (int i = snake.getLengthOfSnake() - 1; i >= 0; i--) {
				snake.setSnakeYlength(i+1, snake.getSnakeYlength()[i]);
			}

			for (int i = snake.getLengthOfSnake(); i >= 0; i--) {

				if (i == 0) {
					snake.setSnakeXlength(i, snake.getSnakeXlength()[i]-25);
				} else {
					snake.setSnakeXlength(i, snake.getSnakeXlength()[i-1]);
				}

				if (snake.getSnakeXlength()[i] < 25) {
					snake.setSnakeXlength(i,850); 
				}
			}

			repaint();
		}

		if (up) {

			for (int i = snake.getLengthOfSnake() - 1; i >= 0; i--) {
				snake.setSnakeXlength(i+1, snake.getSnakeXlength()[i]);
			}

			for (int i = snake.getLengthOfSnake(); i >= 0; i--) {

				if (i == 0) {
					snake.setSnakeYlength(i, snake.getSnakeYlength()[i]-25);
				} else {
					snake.setSnakeYlength(i, snake.getSnakeYlength()[i-1]);
				}

				if (snake.getSnakeYlength()[i] < 75) {
					snake.setSnakeYlength(i,625); 
				}
			}

			repaint();
		}

		if (down) {

			for (int i = snake.getLengthOfSnake() - 1; i >= 0; i--) {
				snake.setSnakeXlength(i+1, snake.getSnakeXlength()[i]);
			}

			for (int i = snake.getLengthOfSnake(); i >= 0; i--) {

				if (i == 0) {
					snake.setSnakeYlength(i, snake.getSnakeYlength()[i]+25);
				} else {
					snake.setSnakeYlength(i, snake.getSnakeYlength()[i-1]);
				}

				if (snake.getSnakeYlength()[i] > 625) {
					snake.setSnakeYlength(i,75);
				}
			}

			repaint();
		}

	}

}
