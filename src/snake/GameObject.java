package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class GameObject {
    public int x;
    public int y;
    private Image image;

    public GameObject(){

    }
    public void setImage(Image image) {
        this.image = image;
    }
    public void setImage(String imageUrl){
        ImageIcon icon = new ImageIcon(imageUrl);
        this.image = icon.getImage();
    }

    public Image getImage() {
        return image;
    }

    public void draw(Graphics g, ImageObserver imageObserver){
        g.drawImage(image, x * GameOptions.CELL_SIZE, y * GameOptions.CELL_SIZE, imageObserver);
    }
}
