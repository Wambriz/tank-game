package tankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardReader implements KeyListener
{
    private static final KeyboardReader instance = new KeyboardReader();

    public static KeyboardReader instance() {
        return instance;
    }

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private boolean escapePressed;
    private boolean wPressed;
    private boolean sPressed;
    private boolean aPressed;
    private boolean dPressed;
    private boolean enterPressed;


    private KeyboardReader() {
        // Empty constructor. We define this private constructor to enforce the Singleton pattern.
    }

    public boolean upPressed() {
        return upPressed;
    }

    public boolean downPressed() {
        return downPressed;
    }

    public boolean leftPressed() {
        return leftPressed;
    }

    public boolean rightPressed() {
        return rightPressed;
    }

    public boolean spacePressed() {
        return spacePressed;
    }

    public boolean escapePressed() {
        return escapePressed;
    }

    public boolean WPressed() {
        return wPressed;
    }

    public boolean SPressed() {
        return sPressed;
    }

    public boolean APressed() {
        return aPressed;
    }

    public boolean DPressed() {
        return dPressed;
    }

    public boolean EnterPressed() {
        return enterPressed;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Ignored.
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        handleKeyEvent(keyEvent.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        handleKeyEvent(keyEvent.getKeyCode(), false);
    }

    private void handleKeyEvent(int keyCode, boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = pressed;
            case KeyEvent.VK_DOWN -> downPressed = pressed;
            case KeyEvent.VK_LEFT -> leftPressed = pressed;
            case KeyEvent.VK_RIGHT -> rightPressed = pressed;
            case KeyEvent.VK_SPACE -> spacePressed = pressed;
            case KeyEvent.VK_ESCAPE -> escapePressed = pressed;
            case KeyEvent.VK_W -> wPressed = pressed;
            case KeyEvent.VK_S-> sPressed = pressed;
            case KeyEvent.VK_A -> aPressed = pressed;
            case KeyEvent.VK_D -> dPressed = pressed;
            case KeyEvent.VK_ENTER -> enterPressed=pressed;
        }
    }
}
