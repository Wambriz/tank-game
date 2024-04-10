package model;

import tankGame.Constants;
import tankGame.KeyboardReader;

public class PlayerOneTank extends Tank
{
    private static int health = 5;
    private static int life=2;
    
    private boolean player1right = false;
	private boolean player1left = false;
	private boolean player1down = false;
	private boolean player1up = true;

    public PlayerOneTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld) {
        xprevious = getX();
        yprevious = getY();

        KeyboardReader keyboard = KeyboardReader.instance();
        if (keyboard.WPressed() ) {
            moveForward(Constants.PLAYER_ONE_TANK_MOVEMENT_SPEED);
        }
        if (keyboard.SPressed()) {
            moveBackward(Constants.PLAYER_ONE_TANK_MOVEMENT_SPEED);
        }
        if (keyboard.APressed()) {
            turnLeft(Constants.TANK_TURN_SPEED);
        }
        if (keyboard.DPressed()) {
            turnRight(Constants.TANK_TURN_SPEED);
        }
        if (keyboard.spacePressed()) {
            fireShell(gameWorld);
        }
        decrementCoolDown();
    }

    public static int getHealth() {
        return health;
    }
    public  void setHealth() {
        health=5;
    }

    public void decreaseHealth() {
        health--;
    }

    public static int getLife(){
        return life;
    }

    public void decreaseLife()
    {
        life--;
    }

    public static void increaseLife() {
        life++;
    }
    public static void increaseHealth() {
        health++;
    }

    public static void setHealth(int h) {
        health = h;
    }
    public static void setLife(int l)
    {
        life=l;
    }
}
