package snake;

public class GameOptions {
    enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
    public static final int B_WIDTH = 900;
    public static final int B_HEIGHT = 900;
    public static final int CELL_SIZE = 30;
    public static final int WIDTH_CELLS = B_WIDTH/CELL_SIZE;
    public static final int HEIGHT_CELLS = B_HEIGHT/CELL_SIZE;
    public static final int SNAKE_DEFAULT_LENGTH = 3;

}
