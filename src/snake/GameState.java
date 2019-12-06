package snake;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GameState {
    private static GameState instance = new GameState();
    private GameState(){};
    public static GameState getInstance(){return instance;}

    private GameOptions.Direction currentDirection;
    private boolean inGame = true;
    public int speed = 150;
    public Food food;
    public Snake snake ;

    public GameOptions.Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(GameOptions.Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    public void initGameState(){
        snake = new Snake();
        food = new Food();
        currentDirection = GameOptions.Direction.RIGHT;
    }
    public boolean checkFoodEaten(){
        if(food.isEaten(snake))
        {
            snake.addSnakeCell(food.x, food.y);
            food.randomLocate();
            return true;

        }
        return false;
    }
    public void draw(Graphics g, ImageObserver imageObserver){
        food.draw(g, imageObserver);
        snake.draw(g, imageObserver);
    }
}
