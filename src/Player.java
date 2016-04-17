import java.awt.*;

/**
 * Created by Expo5 on 4/16/2016.
 */
public class Player extends RenderObj {

    private int width;
    private int height;
    private Color color;

    private int hMoveSpeed;
    private int jumpPower;

    private int xPos;
    private int yPos;
    private int xSpeed;
    private int ySpeed;

    private boolean needRight;
    private boolean needLeft;


    private boolean isGrounded;

    public Player(int xPos, int yPos, int width, int height, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        xSpeed = 0;
        ySpeed = 0;
        this.color = color;
        this.width = width;
        this.height = height;
        isGrounded = false;
        hMoveSpeed = 3;
        jumpPower = 10;

        needRight = false;
        needRight = true;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setNeedRight(boolean doI)
    {
        needRight = doI;
    }

    public void setNeedLeft(boolean doI)
    {
        needLeft = doI;
    }

    public int checkGroundCollisions()
    {
        for(Terrain terrain: Terrain.terrains)
        {
            int playerMidPoint = xPos+(width/2);
            int playerFeetPoint = yPos + height;


            if(playerMidPoint > terrain.getxPos() && playerMidPoint < terrain.getxPos() + terrain.getWidth())
            {
                if(playerFeetPoint >= terrain.getyPos() && playerFeetPoint < terrain.getyPos() + terrain.getHeight())
                {
                    return terrain.getyPos();
                }
            }
        }
        return 0;
    }

    public int checkSideCollisions()
    {
        if(xPos <= 0)
        {
            xPos = Application.sideWallWidth;
        }
        if(xPos >= Application.width)
        {
            xPos = Application.width - Application.sideWallWidth;
        }
        for(Terrain terrain: Terrain.terrains)
        {
            if(yPos + height > terrain.getyPos() && yPos < terrain.getyPos() + terrain.getHeight()) {
                if (xPos <= terrain.getxPos() + terrain.getWidth() && xPos > terrain.getxPos()) {
                    return terrain.getxPos() + terrain.getWidth();
                }

                if (xPos + width >= terrain.getxPos() && xPos < terrain.getxPos() + terrain.getWidth()) {
                    return terrain.getxPos() - width;
                }
            }


        }
        return 0;
    }

    public int checkTopCollisions() {
        for(Terrain terrain: Terrain.terrains)
        {
            if(yPos < terrain.getyPos() + terrain.getHeight() && yPos + height > terrain.getyPos())
            {
                if(xPos + width > terrain.getxPos() && xPos < terrain.getxPos() + terrain.getWidth())
                {
                    System.out.println("!");
                    return terrain.getyPos() + terrain.getHeight();

                }
            }
        }
        return 0;
    }


    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, width, height);
    }

    public void moveX(int pixels)
    {
        for(int i = 0; i < Math.abs(pixels); i++)
        {
            int nextXPos = xPos + (pixels / Math.abs(pixels));
            if(nextXPos <= 0)
            {
                return;
            }
            if(nextXPos >= Application.width)
            {
                return;
            }

            for(Terrain terrain: Terrain.terrains)
            {
                if(yPos + height > terrain.getyPos() && yPos < terrain.getyPos() + terrain.getHeight()) {
                    if (nextXPos <= terrain.getxPos() + terrain.getWidth() && nextXPos > terrain.getxPos()) {
                        return;
                    }

                    if (xPos + width >= terrain.getxPos() && xPos < terrain.getxPos() + terrain.getWidth()) {
                        return;
                    }
                }
            }
            xPos = nextXPos;

        }
    }

    public void moveRight()
    {
        xPos += hMoveSpeed;
    }

    public void moveLeft()
    {
        xPos -= hMoveSpeed;
    }

    public void jump()
    {
        if(isGrounded)
        {
            ySpeed = jumpPower;
        }
    }

    @Override
    public void update() {
        System.out.println("___________________");
        System.out.println(xPos + " " + yPos);
        xPos += xSpeed;
        if(needRight)
        {
            moveRight();
            needRight = false;
        }
        int sideCollisionResult = checkSideCollisions();
        if(sideCollisionResult != 0)
        {
            xSpeed = 0;
            xPos = sideCollisionResult;
        }
        if(needLeft)
        {
            moveLeft();
            needLeft = false;
        }
        yPos -= ySpeed;
        if(isGrounded == false){
            ySpeed += Application.GRAVITY;
        }

        int collisionResult = checkGroundCollisions();
        if(collisionResult != 0)
        {
            isGrounded = true;
            yPos = collisionResult - height;
            ySpeed = 0;
        }
        else
        {
            isGrounded = false;
        }

        int topCollisionResult = checkTopCollisions();
        if(topCollisionResult != 0)
        {
            yPos = collisionResult;
            ySpeed = 0;
        }

        System.out.println(xPos + " " + yPos);
        System.out.println("___________________");
    }
}
