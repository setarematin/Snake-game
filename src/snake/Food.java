package snake;

public class Food extends GameObject{
    private final String IMAGE_URL = "src/resources/seti.png";

    public Food(){
        randomLocate();
        setImage(IMAGE_URL);
    }

    public Food(int x, int y){
        this.x = x;
        this.y = y;
        setImage(IMAGE_URL);
    }

    public Food(int x, int y, String imageUrl){
        this.x = x;
        this.y = y;
        setImage(imageUrl);
    }

    public boolean isEaten(Snake snake){
        if(snake.head().x == x && snake.head().y == y) {
            System.out.println("food is eaten");
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
