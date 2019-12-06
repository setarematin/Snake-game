package snake;

public class Wall extends GameObject{
    private final String IMAGE_URL = "src/resources/head.png";

    public Wall(){
        randomLocate();
        setImage(IMAGE_URL);
    }

    public Wall(int x, int y){
        this.x = x;
        this.y = y;
        setImage(IMAGE_URL);
    }

    public Wall(int x, int y, String imageUrl){
        this.x = x;
        this.y = y;
        setImage(imageUrl);
    }

    public boolean isCollide(Snake snake){
        if(snake.head().x == x && snake.head().y == y) {
            System.out.println("wall is collided");
            return true;
        }
        return false;
    }
    public void randomLocate(){
        x = (int) (Math.random() * (GameOptions.WIDTH_CELLS -1 ));
        System.out.println(x);
        y = (int) (Math.random() * (GameOptions.HEIGHT_CELLS -1 ));
        System.out.println(y);

    }
}
