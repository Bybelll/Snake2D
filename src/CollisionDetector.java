import java.util.List;

public class CollisionDetector {

	public boolean collision(Snake snake, List<Wall> wallList) {

		boolean isCollision = false;

		for (int i = 0; i < wallList.size(); i++) {
			if (wallList.get(i).getX() == snake.getSnakeXlength()[0]
					&& wallList.get(i).getY() == snake.getSnakeYlength()[0]) {
				isCollision = true;
			}
		}
		return isCollision;
	}

	
	public boolean collision(Snake snake) {

		boolean isCollision = false;

		for (int i = 1; i < snake.getLengthOfSnake(); i++) {

			if (snake.getSnakeXlength()[i] == snake.getSnakeXlength()[0]
					&& snake.getSnakeYlength()[i] == snake.getSnakeYlength()[0]) {
				isCollision = true;
			}
		}
		return isCollision;
	}
	
	
}