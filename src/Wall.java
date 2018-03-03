import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Wall {

	private int x; // wall coordinates
	private int y;

	public Wall() {
		Random random = new Random();
		this.x = Gameplay.enemyXpos[random.nextInt(Gameplay.enemyXpos.length)];
		this.y = Gameplay.enemyYpos[random.nextInt(Gameplay.enemyYpos.length)];
	}

	public void drawWall(Component c, Graphics g) {
		ImageIcon wallImage = new ImageIcon("wall.png");

			wallImage.paintIcon(c, g, this.x, this.y);
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
