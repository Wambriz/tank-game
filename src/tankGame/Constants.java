package tankGame;

public class Constants {
    public static final double TANK_WIDTH = 55.0;
    public static final double TANK_HEIGHT = 47.0;
    public static  double PLAYER_ONE_TANK_MOVEMENT_SPEED = 2.0;
    public static  double PLAYER_TWO_TANK_MOVEMENT_SPEED = 2.0;
    public static final double TANK_TURN_SPEED = Math.toRadians(3.0);

    public static final double SHELL_WIDTH = 12.0;
    public static final double SHELL_HEIGHT = 8.0;
    public static final double SHELL_MOVEMENT_SPEED = 4.0;

    public static final double WALL_WIDTH = 32.0;
    public static final double WALL_HEIGHT = 32.0;

    public static final String PLAYER_ONE_TANK_ID = "resources/player-one-tank";
    public static final String PLAYER_TWO_TANK_1_ID = "resources/player-two-tank-1";


    public static final double PLAYER_ONE_TANK_INITIAL_X = 250.0;
    public static final double PLAYER_ONE_TANK_INITIAL_Y = 200.0;
    public static final double PLAYER_ONE_TANK_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final double PLAYER_TWO_TANK_1_INITIAL_X = 700.0;
    public static final double PLAYER_TWO_TANK_1_INITIAL_Y = 500.0;
    public static final double PLAYER_TWO_TANK_1_INITIAL_ANGLE = Math.toRadians(180.0);


    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = 924.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = 648.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = 1024.0;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = 768.0;

}