import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Food {

	private int x;
	private int y;

	public Food() {
		Random random = new Random();
		
		this.x = Gameplay.enemyXpos[random.nextInt(Gameplay.enemyXpos.length)];
		this.y = Gameplay.enemyYpos[random.nextInt(Gameplay.enemyYpos.length)];
	}

	public void drawFood(Component c, Graphics g) {
		ImageIcon enemyImage = new ImageIcon("enemy.png");
		enemyImage.paintIcon(c, g, x, y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
