package model;

import tankGame.Constants;
import tankGame.KeyboardReader;

public class PlayerTwoTank  extends Tank
{
    private static int health = 5;

    private static int life=2;

    public PlayerTwoTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld) {
        xprevious = getX();
        yprevious = getY();

        KeyboardReader keyboard = KeyboardReader.instance();
        if (keyboard.upPressed() ) {
            moveForward(Constants.PLAYER_TWO_TANK_MOVEMENT_SPEED);
        }
        if (keyboard.downPressed()) {
            moveBackward(Constants.PLAYER_TWO_TANK_MOVEMENT_SPEED);
        }
        if (keyboard.leftPressed()) {
            turnLeft(Constants.TANK_TURN_SPEED);
        }
        if (keyboard.rightPressed()) {
            turnRight(Constants.TANK_TURN_SPEED);
        }
        if (keyboard.EnterPressed()) {
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

    public static void increaseHealth() {
        health++;
    }

    public static void setHealth(int h) {
        health = h;
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
    public static void setLife(int l)
    {
        life=l;
    }
}
