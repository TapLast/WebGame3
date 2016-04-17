import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Application extends Applet implements Runnable, KeyListener {

    private final Set<Character> pressed = new HashSet<Character>();

    private final int FPS = 60;
    public static final int GRAVITY = -1;

    public static int width = 800;
    public static int height = 600;

    public static int sideWallWidth = 2;

    Player player;

    int x = 0;
    int y = 0;
    int speedX = 2;
    int speedY = 2;
    int radius = 10;
    private Image i;
    private Graphics doubleBuffer;

    public void init() {
        setSize(width, height);
        addKeyListener(this);

    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        player = new Player(25, 100, 10, 20, Color.RED);
        Terrain floor = new Terrain(0, 400, 800, 800, Color.BLACK);
        Terrain wallLeft = new Terrain(0, 0, sideWallWidth, height, Color.BLACK);
        Terrain wallRight = new Terrain(width-sideWallWidth, 0, sideWallWidth,height, Color.BLACK);
        Terrain leftChunk = new Terrain(0, 200, 50, 800, Color.BLACK);
        Terrain stair1 = new Terrain (300, 350, 50, 50, Color.BLACK);
        Terrain stair2 = new Terrain (350, 300, 50, 100, Color.BLACK);
        Terrain stair3 = new Terrain (400, 250, 50, 150, Color.BLACK);
        Terrain stair4 = new Terrain (450, 200, 50, 200, Color.BLACK);
        Terrain island = new Terrain (200, 275, 100, 10, Color.BLACK);
    }

    public void run() {
        while(true)
        {
            repaint();
            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void destroy() {

    }

    public void update(Graphics g)
    {
        if(i == null)
        {
            i = createImage(width, height);
            doubleBuffer = i.getGraphics();
        }

        doubleBuffer.setColor(getBackground());
        doubleBuffer.fillRect(0, 0, width, height);
        doubleBuffer.setColor(getForeground());
        paint(doubleBuffer);

        g.drawImage(i, 0, 0, this);
    }

    public void paint(Graphics g) {
        for(RenderObj render: RenderObj.allRenderObj)
        {
            if (pressed.size() > 0) {
                for(char key: pressed)
                {
                    if(key == 'd') {
                        player.setNeedRight(true);
                    }
                    if(key == 'a') {
                        player.setNeedLeft(true);
                    }
                    if(key == 'w')
                    {
                        player.jump();
                    }
                }
            }
            render.update();
            render.render(g);
        }
    }



    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());

    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
