package snake;

public class SnakeCell extends GameObject{
    private final String IMAGE_URL = "src/resources/mehdi.png";

    public SnakeCell(int x, int y){
        this.x = x;
        this.y = y;
        setImage(IMAGE_URL);
    }

    public SnakeCell(int x, int y, String imageUrl){
        this.x = x;
        this.y = y;
        setImage(imageUrl);
    }
}
