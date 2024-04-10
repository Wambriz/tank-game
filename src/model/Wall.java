package model;

import tankGame.Constants;

public class Wall extends Entity
{
    private boolean breakAble;

    public Wall(String id, double x, double y, double angle,boolean breakAble) {
        super(id, x, y, angle);
        this.breakAble=breakAble;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public double getAngle() {
        return super.getAngle();
    }

    @Override
    public double getXBound(){
        return getX() + Constants.WALL_WIDTH;
    }

    @Override
    public double getYBound(){
        return getY() + Constants.WALL_HEIGHT;
    }

    public boolean isBreakAble() {
        return breakAble;
    }

    public void setBreakAble(boolean breakAble) {
        this.breakAble = breakAble;
    }

    @Override
    public void move(GameWorld gameWorld) {

    }

    @Override
    public boolean checkBounds() {
        return false;
    }

}
