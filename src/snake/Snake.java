package snake;

import exceptions.SnakeException;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Snake {
    private ArrayList<SnakeCell> cells= new ArrayList<>();
    private int defaultLength = GameOptions.SNAKE_DEFAULT_LENGTH;

    //TODO: handle direction and exceptions
    public Snake(){
        for (int i = 0; i < defaultLength; i++) {
            SnakeCell snakeCell = new SnakeCell(defaultLength - i , 0);
            addSnakeCell(snakeCell);
        }
        System.out.println("head");
        System.out.println(head().x);
        System.out.println(head().y);
    }

    public ArrayList<SnakeCell> getSnake(){
        return cells;
    }

    public void addSnakeCell(SnakeCell cell){
        cells.add(cell);
    }

    public void addSnakeCell(int x, int y){
        SnakeCell cell =  new SnakeCell(x, y);
        cells.add(cell);
        System.out.println(this);
    }

    public int length(){
        return cells.size();
    }

    public SnakeCell head(){
        if (!cells.isEmpty())
            return cells.get(0);
        return null;
    }
    public boolean isObjectCollided(int _x, int _y){
        for(int i = 0; i < length(); i++)
            if(_x == cells.get(i).x && _y == cells.get(i).y) {
                System.out.println("object collide");
                return true;
            }
        return false;
    }
    public boolean isSelfCollided(){
        for(int i = 1; i < length(); i++)
            if(head().x == cells.get(i).x && head().y == cells.get(i).y) {
                System.out.println("self collide");
                return true;
            }
            return false;
    }

    public boolean isWallCollided(){
        if(head().x == 0 && GameState.getInstance().getCurrentDirection() ==  GameOptions.Direction.LEFT)
            return true;
        if(head().x == GameOptions.WIDTH_CELLS - 1 && GameState.getInstance().getCurrentDirection() ==  GameOptions.Direction.RIGHT)
            return true;
        if(head().y == 0 && GameState.getInstance().getCurrentDirection() ==  GameOptions.Direction.UP)
            return true;
        if(head().y == GameOptions.HEIGHT_CELLS - 1 && GameState.getInstance().getCurrentDirection() ==  GameOptions.Direction.DOWN)
            return true;
        return false;
    }

    public boolean isCollied(){
        if(isSelfCollided() || isWallCollided())
            return true;
        return false;
    }

    public void move(GameOptions.Direction direction)  throws SnakeException{

        for (int z = length()-1; z > 0; z--) {
            cells.get(z).x = cells.get(z-1).x;
            cells.get(z).y = cells.get(z-1).y;
        }
        if (direction == GameOptions.Direction.LEFT) {
            if (head().x == 0)
                throw new SnakeException("snake cant move to left wall");
            head().x -= 1;

        }

        if (direction == GameOptions.Direction.RIGHT) {
            if (head().x == GameOptions.B_WIDTH - 1)
                throw new SnakeException("snake cant move to right wall");
            head().x += 1;
        }

        if (direction == GameOptions.Direction.UP) {
            if (head().y == 0)
                throw new SnakeException("snake cant move to top wall");
            head().y -= 1;
        }

        if (direction == GameOptions.Direction.DOWN) {
            if (head().y == GameOptions.B_HEIGHT - 1)
                throw new SnakeException("snake cant move to bottom wall");
            head().y += 1;
        }
    }

    public void draw(Graphics g, ImageObserver imageObserver){
        for(SnakeCell snakeCell: cells)
            snakeCell.draw(g, imageObserver);
    }

    @Override
    public String toString() {
        String s = "";
        for (SnakeCell snakeCell: cells)
            s += ", " + Integer.toString(snakeCell.x) + Integer.toString(snakeCell.y);
        return s;
    }
}
