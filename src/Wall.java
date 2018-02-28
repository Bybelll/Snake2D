import java.util.Random;

public class Wall {
	
	private int x;    // wall coordinates
	private int y;

	
	public Wall() {
		Random random = new Random();
		this.x = Gameplay.enemyXpos[random.nextInt(34)];
		this.y = Gameplay.enemyYpos[random.nextInt(23)];
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
