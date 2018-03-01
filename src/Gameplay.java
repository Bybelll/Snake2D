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


	private ImageIcon titleImage;
	private ImageIcon enemyImage;
	private ImageIcon wallImage;

	private Timer timer;

	private int delay = 100;
	private int score = 0;

	private Random random = new Random();
	private int Xpos = random.nextInt(34);
	private int Ypos = random.nextInt(23);


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
		
		snake.drawSnake(this, g);

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
				snake.setRight(false);
				snake.setLeft(false);
				snake.setUp(false);
				snake.setDown(false);

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
			wallList.clear();
			timer.start();
			repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			snake.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.moveLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.moveDown();
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

		if (snake.isRight()) {

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

		if (snake.isLeft()) {

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

		if (snake.isUp()) {

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

		if (snake.isDown()) {

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
