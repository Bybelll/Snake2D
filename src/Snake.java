
public class Snake {

	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	private int lengthOfSnake = 3;
	private int moves = 0;
	


	public Snake() {

			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;

			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		
	}
	
	public void restartSnake() {
		
		moves = 0;
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
	
}
