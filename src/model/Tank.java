package model;

import View.Sounds;

import tankGame.Constants;

public abstract class Tank extends Entity {
    // TODO: Implement. A lot of what's below is relevant to all Entity types, not just Tanks. Move it accordingly to
    //       Entity class.
    private String id;
    private double x;
    private double y;
    protected double xprevious;
    protected double yprevious;
    private double angle;

    boolean shot = false;
    private int coolDownTime = 50;
    private int coolDownLeft = coolDownTime;

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    // TODO: The methods below are provided so you don't have to do the math for movement. You should call these methods
    //       from the various subclasses of Entity in their implementations of move.

    protected void moveForward(double movementSpeed) {
        x += movementSpeed * Math.cos(angle);
        y += movementSpeed * Math.sin(angle);
    }

    protected void moveBackward(double movementSpeed) {
        x -= movementSpeed * Math.cos(angle);
        y -= movementSpeed * Math.sin(angle);
    }

    protected void turnLeft(double turnSpeed) {
        angle -= turnSpeed;
    }

    protected void turnRight(double turnSpeed) {
        angle += turnSpeed;
    }

    //
    //
    //

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    private double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

    private double getShellAngle() {
        return getAngle();
    }

    private Sounds whoosh;

    void fireShell(GameWorld gameWorld) {
        if (shot == true) {
            Shell shell = new Shell(getId(), getShellX(), getShellY(), getShellAngle());
            gameWorld.addShell(shell);
            shot = false;
            coolDownLeft = coolDownTime;
            whoosh.clipSound1();
        }
    }

    void decrementCoolDown(){
        if(shot == false){
            coolDownLeft--;
            if(coolDownLeft < 0){
                shot = true;
                System.out.println(shot);
            }
        }
    }

    @Override
    public double getXBound() {
        return getX() + Constants.TANK_WIDTH;
    }

    @Override
    public double getYBound() {
        return getY() + Constants.TANK_HEIGHT;
    }

    @Override
    public boolean checkBounds() {
        if (getX() > Constants.TANK_X_UPPER_BOUND) {
            setX(Constants.TANK_X_UPPER_BOUND);
        }
        if (getX() < Constants.TANK_X_LOWER_BOUND) {
            setX(Constants.TANK_X_LOWER_BOUND);
        }
        if (getY() > Constants.TANK_Y_UPPER_BOUND) {
            setY(Constants.TANK_Y_UPPER_BOUND);
        }
        if (getY() < Constants.TANK_Y_LOWER_BOUND) {
            setY(Constants.TANK_Y_LOWER_BOUND);
        }
        return false;
    }
}
