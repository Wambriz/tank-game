package View;

import model.PlayerOneTank;
import model.PlayerTwoTank;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class RunGameView extends JPanel
{
    private PlayerOneTank player1;
    private PlayerTwoTank player2;



    // Constants for the image file names for various sprites.
    public static final String PLAYER_ONE_TANK_IMAGE_FILE = "resources/player-one-tank.png";
    public static final String PLAYER_TWO_TANK_IMAGE_FILE = "resources/player-two-tank.png";
    public static final String SHELL_IMAGE_FILE = "resources/shell.png";


    public static final String SPEED_IMAGE_FILE = "resources/speed.png";
    public static final String LIFE_IMAGE_FILE = "resources/life.png";
    public static final String REPAIR_IMAGE_FILE = "resources/repair.png";


    static final Dimension SCREEN_DIMENSIONS = new Dimension(1224, 800);

    private static final String SHELL_EXPLOSION_FILE_PREFIX = "resources/shell-explosion-";
    private static final String SHELL_EXPLOSION_FILE_SUFFIX = ".png";

    // These constants may be used if you add support for animations on screen. See the addAnimation method.
    public static final AnimationResource SHELL_EXPLOSION_ANIMATION =
            new AnimationResource(SHELL_EXPLOSION_FILE_PREFIX, SHELL_EXPLOSION_FILE_SUFFIX, 6);
    public static final int SHELL_EXPLOSION_FRAME_DELAY = 3;
    public static final double SHELL_EXPLOSION_WIDTH = 32.0;
    public static final double SHELL_EXPLOSION_HEIGHT = 32.0;

    private static final String BIG_EXPLOSION_FILE_PREFIX = "resources/big-explosion-";
    private static final String BIG_EXPLOSION_FILE_SUFFIX = ".png";

    // These constants may be used if you add support for animations on screen. See the addAnimation method.
    public static final AnimationResource BIG_EXPLOSION_ANIMATION =
            new AnimationResource(BIG_EXPLOSION_FILE_PREFIX, BIG_EXPLOSION_FILE_SUFFIX, 7);
    public static final int BIG_EXPLOSION_FRAME_DELAY = 4;
    public static final double BIG_EXPLOSION_WIDTH = 64.0;
    public static final double BIG_EXPLOSION_HEIGHT = 64.0;

    private final BufferedImage worldImage;
    private final Map<String, Sprite> spritesById = new HashMap<>();
    private final List<Animation> animations = new LinkedList<Animation>();

    public RunGameView() {
        worldImage = new BufferedImage(SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height, BufferedImage.TYPE_INT_RGB);
        setBackground(Color.BLACK);
    }

    public void reset() {
        synchronized (spritesById) {
            spritesById.clear();
        }
    }

    /**
     * Adds a new image on screen with the given unique ID. Once added, this sprite will be tracked by the RunGameView
     * until the sprite is explicitly removed with removeSprite, or until reset is called.
     */
    public void addSprite(
            String id, String entityImageFile, double initialX, double initialY, double initialAngle) {
        synchronized (spritesById) {
            if (spritesById.containsKey(id)) {
                throw new RuntimeException(
                        "A sprite with id '" + id + "' already exists. Use setSpriteLocationAndAngle instead.");
            }
            Sprite sprite = new Sprite(entityImageFile);
            sprite.setLocationAndAngle(initialX, initialY, initialAngle);
            spritesById.put(id, sprite);
        }
    }

    /**
     * Removes the sprite with the given ID from the screen. If no sprite with the ID exists, nothing will happen. This
     * should be used when an image should no longer be shown, like if an entity is destroyed, or if a shell goes off
     * screen.
     */
    public void removeSprite(String id) {
        synchronized (spritesById) {
            spritesById.remove(id);

        }
    }

    public boolean isSpriteExit(String id)
    {
        synchronized (spritesById){
            if(spritesById.containsKey(id))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the location and angle of the sprite with the given ID. The sprite must have already been added
     * previously with addSprite.
     */
    public void setSpriteLocationAndAngle(String id, double x, double y, double angle) {
        synchronized (spritesById) {
            if (!spritesById.containsKey(id)) {
                throw new RuntimeException("No sprite with id '" + id + "' was added. addSprite must be called first.");
            }
            spritesById.get(id).setLocationAndAngle(x, y, angle);
        }
    }

    /**
     * Adds an animation (specified with an AnimationResource -- see constants above for choices) at the given position
     * with the provided delay between each frame in the animation. Once the animation finishes, the RunGameView will
     * automatically remove the animation.
     */
    public void addAnimation(AnimationResource animationResource, int frameDelay, double x, double y) {
        synchronized (animations) {
            animations.add(new Animation(animationResource, frameDelay, x, y));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D buffer = worldImage.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height);

        synchronized (spritesById) {
            for (Sprite sprite : spritesById.values()) {
                buffer.drawImage(sprite.getEntityImage(), sprite.getAffineTransform(), null);
            }
        }

        synchronized (animations) {
            ListIterator<Animation> animationIterator = animations.listIterator();
            while (animationIterator.hasNext()) {
                Animation animation = animationIterator.next();
                Optional<BufferedImage> nextImage = animation.getImage();
                if (nextImage.isPresent()) {
                    buffer.drawImage(nextImage.get(), (int) animation.getX(), (int) animation.getY(), null);
                } else {
                    animationIterator.remove();
                }
            }
        }

        g.drawImage(worldImage, 0, 0, null);

        // Display life
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        g.setColor(Color.green);
        g.drawString("Player 1: ", 1100, 30);
        g.fillRect(1080, 40, player1.getHealth() * 20, 10);
        g.drawRect(10800, 40, 100, 10);

//        g.setColor(Color.red);
//        g.drawString(": ", 380, 30);
//        g.fillRect(500, 10, st.getHealth() * 20, 30);
//        g.drawRect(500, 10, 100, 30);

        g.setColor(Color.red);
        g.drawString("Player 2: ", 1100, 300);
        g.fillRect(1080, 310, player2.getHealth() * 20, 10);
        g.drawRect(1080, 310, 100, 10);

        



        if (player1.getLife() == 0) {

            JFrame f;
            JLabel l;  // label to display text

            f = new JFrame("YOU LOSE");
            ImageIcon i = new ImageIcon("lose.png");

            l = new JLabel(i);  // create a label to display image
            JPanel p = new JPanel();    // create a panel

            p.add(l);   // add label to panel
            f.add(p);   // add panel to frame

            f.setSize(500, 500); // set the size of frame

            f.show();
        }

        if (player2.getLife() == 0) {

            JFrame f;
            JLabel l;

            f = new JFrame("YOU WIN");
            ImageIcon i = new ImageIcon("win.png");
            l = new JLabel(i);
            JPanel p = new JPanel();
            p.add(l);
            f.add(p);
            f.setSize(500, 500);

            f.show();
        }
    }

}
