import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Expo5 on 4/16/2016.
 */
public class Terrain extends RenderObj {

    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private int xPos;
    private int yPos;
    private int height;
    private int width;
    private Color color;

    public Terrain(int xPos, int yPos, int width,  int height, Color color)
    {
        terrains.add(this);
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.color = color;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, width, height);

    }

    @Override
    public void update() {

    }
}
