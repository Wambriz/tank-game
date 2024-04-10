package tankGame;

import View.*;
import model.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameDriver {
    private final MainScreen mainView;
    private final RunGameView runGameView;
    private final GameWorld gameWorld;
    TankLifeIcon icon1,icon2,icon3,icon4,icon5,icon6;

    public GameDriver() {
        mainView = new MainScreen(this::startMenuActionPerformed);
        runGameView = mainView.getRunGameView();
        gameWorld = new GameWorld();
    }

    public void start() {
        mainView.setScreen(Screen.START_GAME_SCREEN);
    }

    private void startMenuActionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case StartMenuScreen.START_BUTTON_ACTION_COMMAND -> runGame();
            case StartMenuScreen.EXIT_BUTTON_ACTION_COMMAND -> mainView.closeGame();
            default -> throw new RuntimeException("Unexpected action command: " + actionEvent.getActionCommand());
        }
    }


    private void addPlayerOneLifeTank(int life)
    {

        for (int i=0;i<life;i++)
        {
            if(i==0)
            {
                icon1=new TankLifeIcon("tank1",1100,50,0);
                if(runGameView.isSpriteExit(icon1.getId()))
                {
                    runGameView.removeSprite(icon1.getId());
                }
                runGameView.addSprite(icon1.getId(),
                        runGameView.PLAYER_ONE_TANK_IMAGE_FILE,
                        icon1.getX(),
                        icon1.getY(),
                        icon1.getAngle());
                //gameWorld.addEntity(icon1);

            }

            if(i==1)
            {
                icon2=new TankLifeIcon("tank2",1100,100,0);
                if(runGameView.isSpriteExit(icon2.getId()))
                {
                    runGameView.removeSprite(icon2.getId());
                }
                runGameView.addSprite(icon2.getId(),
                        runGameView.PLAYER_ONE_TANK_IMAGE_FILE,
                        icon2.getX(),
                        icon2.getY(),
                        icon2.getAngle());
                //gameWorld.addEntity(icon2);

            }

            if (i==2)
            {
                icon3=new TankLifeIcon("tank3",1100,150,0);
                if(runGameView.isSpriteExit(icon3.getId()))
                {
                    runGameView.removeSprite(icon3.getId());
                }
                runGameView.addSprite(icon3.getId(),
                        runGameView.PLAYER_ONE_TANK_IMAGE_FILE,
                        icon3.getX(),
                        icon3.getY(),
                        icon3.getAngle());
                //gameWorld.addEntity(icon3);
            }

        }



    	
    }

    private void addPlayerTwoLifeTank(int life)
    {
        for(int i=0;i<life;i++)
        {
            if(i==0)
            {
                icon4=new TankLifeIcon("tank4",1100,320,0);
                if(runGameView.isSpriteExit(icon4.getId()))
                {
                    runGameView.removeSprite(icon4.getId());
                }
                runGameView.addSprite(icon4.getId(),runGameView.PLAYER_TWO_TANK_IMAGE_FILE,icon4.getX(),icon4.getY(),icon4.getAngle());

            }

            if(i==1)
            {
                icon5=new TankLifeIcon("tank5",1100,370,0);
                if(runGameView.isSpriteExit(icon5.getId()))
                {
                    runGameView.removeSprite(icon5.getId());
                }
                runGameView.addSprite(icon5.getId(),
                        runGameView.PLAYER_TWO_TANK_IMAGE_FILE,
                        icon5.getX(),
                        icon5.getY(),
                        icon5.getAngle());
                
            }

            if (i==2)
            {
                icon6=new TankLifeIcon("tank6",1100,420,0);
                if(runGameView.isSpriteExit(icon6.getId()))
                {
                    runGameView.removeSprite(icon6.getId());
                }
                runGameView.addSprite(icon6.getId(),
                        runGameView.PLAYER_TWO_TANK_IMAGE_FILE,
                        icon6.getX(),
                        icon6.getY(),
                        icon6.getAngle());
                
            }
        }

    }

    private void runGame() {
        mainView.setScreen(Screen.RUN_GAME_SCREEN);
        Runnable gameRunner = () -> {
            setUpGame();
            while (updateGame()) {
                runGameView.repaint();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }

                KeyboardReader keyboard = KeyboardReader.instance();
                if (keyboard.escapePressed() || hasDied() ){
                    break;
                }
            }
            mainView.setScreen(Screen.END_MENU_SCREEN);
            addPlayerOneLifeTank(2);
            addPlayerTwoLifeTank(2);
            resetGame();
        };
        new Thread(gameRunner).start();
    }

    /**
     * setUpGame is called once at the beginning when the game is started. Entities that are present from the start
     * should be initialized here, with their corresponding sprites added to the RunGameView.
     */
    private void setUpGame() {
        // TODO: Implement.
        PlayerOneTank playerOneTank =
                new PlayerOneTank(
                        Constants.PLAYER_ONE_TANK_ID,
                        Constants.PLAYER_ONE_TANK_INITIAL_X,
                        Constants.PLAYER_ONE_TANK_INITIAL_Y,
                        Constants.PLAYER_ONE_TANK_INITIAL_ANGLE);

        PlayerTwoTank playerTwoTank =
                new PlayerTwoTank(
                        Constants.PLAYER_TWO_TANK_1_ID,
                        Constants.PLAYER_TWO_TANK_1_INITIAL_X,
                        Constants.PLAYER_TWO_TANK_1_INITIAL_Y,
                        Constants.PLAYER_TWO_TANK_1_INITIAL_ANGLE);



        SpeedBooster speedBooster=new SpeedBooster("speedBooster1",50,600,0);
        SpeedBooster speedBooster2=new SpeedBooster("speedBooster2",950,50,0);

        Life life=new Life("life",490,75,0);
        Life life1=new Life("life1",490,590,0);

        Repair repair=new Repair("repair",300,55,0);
        Repair repair2=new Repair("repair1",600,55,0);
        Repair repair3=new Repair("repair2",300,600,0);
        Repair repair4=new Repair("repair3",760,175,0);
        Repair repair5=new Repair("repair4",600,600,0);

        gameWorld.addEntity(playerOneTank);
        gameWorld.addEntity(playerTwoTank);
        gameWorld.addEntity(speedBooster);
        gameWorld.addEntity(speedBooster2);
        gameWorld.addEntity(life);
        gameWorld.addEntity(life1);
        gameWorld.addEntity(repair);
        gameWorld.addEntity(repair2);
        gameWorld.addEntity(repair3);
        gameWorld.addEntity(repair4);
        gameWorld.addEntity(repair5);

        runGameView.addSprite(
                playerOneTank.getId(),
                runGameView.PLAYER_ONE_TANK_IMAGE_FILE,
                playerOneTank.getX(),
                playerOneTank.getY(),
                playerOneTank.getAngle());

        runGameView.addSprite(
                playerTwoTank.getId(),
                RunGameView.PLAYER_TWO_TANK_IMAGE_FILE,
                playerTwoTank.getX(),
                playerTwoTank.getY(),
                playerTwoTank.getAngle());


        runGameView.addSprite(
                speedBooster.getId(),
                RunGameView.SPEED_IMAGE_FILE,
                speedBooster.getX(),
                speedBooster.getY(),
                speedBooster.getAngle());

        runGameView.addSprite(
                speedBooster2.getId(),
                RunGameView.SPEED_IMAGE_FILE,
                speedBooster2.getX(),
                speedBooster2.getY(),
                speedBooster2.getAngle());

        runGameView.addSprite(
                life.getId(),
                RunGameView.LIFE_IMAGE_FILE,
                life.getX(),
                life.getY(),
                life.getAngle());

        runGameView.addSprite(
                life1.getId(),
                RunGameView.LIFE_IMAGE_FILE,
                life1.getX(),
                life1.getY(),
                life1.getAngle());

        runGameView.addSprite(
                repair.getId(),
                RunGameView.REPAIR_IMAGE_FILE,
                repair.getX(),
                repair.getY(),
                repair.getAngle());

        runGameView.addSprite(
                repair2.getId(),
                RunGameView.REPAIR_IMAGE_FILE,
                repair2.getX(),
                repair2.getY(),
                repair2.getAngle());

        runGameView.addSprite(
                repair3.getId(),
                RunGameView.REPAIR_IMAGE_FILE,
                repair3.getX(),
                repair3.getY(),
                repair3.getAngle());

        runGameView.addSprite(
                repair4.getId(),
                RunGameView.REPAIR_IMAGE_FILE,
                repair4.getX(),
                repair4.getY(),
                repair4.getAngle());

        runGameView.addSprite(
                repair5.getId(),
                RunGameView.REPAIR_IMAGE_FILE,
                repair5.getX(),
                repair5.getY(),
                repair5.getAngle());

        addPlayerOneLifeTank(playerOneTank.getLife());
        addPlayerTwoLifeTank(playerTwoTank.getLife());

        int i = 0;
        for (WallInformation wall : WallInformation.readWalls()) {
            Wall wallInfos = new Wall("wall"+i,wall.getX(),wall.getY(),0,wall.isBreakAble());
            gameWorld.addEntity(wallInfos);
            runGameView.addSprite("wall"+i,wall.getImageFile(),wall.getX(),wall.getY(),0);
            i++;
        }
    }

    public boolean entitiesOverLap(Entity e1, Entity e2) {
        return e1.getX() < e2.getXBound() &&
                e1.getXBound() > e2.getX() &&
                e1.getY() < e2.getYBound() &&
                e1.getYBound() > e2.getY();
    }

    private Sounds boom;

    public void collisionHandling(Entity e1, Entity e2, GameWorld gameWorld) {
        // A tank colliding with a tank
        if (e1 instanceof Tank && e2 instanceof Tank) {
            TankTankCollide(e1, e2);
        }

        // shell w non shell entity
        if (e1 instanceof PlayerOneTank && e2 instanceof Shell) {
            ShellToPlayerOneTankCollide(e1, e2, gameWorld);
            boom.clipSound2();
        }
        if (e1 instanceof PlayerTwoTank && e2 instanceof Shell) {
            ShellToPlayerTwoTankCollide(e1,e2,gameWorld);
            boom.clipSound2();
        }


        if (e1 instanceof Wall && e2 instanceof Shell) {
            ShellToWallCollide(e1, e2, gameWorld);
            boom.clipSound2();
        }

        // A shell colliding with a shell
        if (e1 instanceof Shell && e2 instanceof Shell) {
            ShellToShellCollide(e1, e2, gameWorld);
            boom.clipSound2();
        }

        // tank to wall
        if (e1 instanceof Tank && e2 instanceof Wall) {
            TankToWallCollide(e1, e2);
        }

        //playerTank to powerup
        if (e1 instanceof Tank && e2 instanceof PowerUps) {
            TankToPowerUpCollide(e1, e2);
        }
    }

    public void ShellToShellCollide(Entity e1, Entity e2, GameWorld gameWorld) {
        List<Entity> entitiesToRemove = new ArrayList<>();

        entitiesToRemove.add(e1);
        entitiesToRemove.add(e2);

        for (Entity entity: entitiesToRemove) {
            gameWorld.removeEntity(entity.getId());
            gameWorld.addToGarbage(entity);
        }

        runGameView.addAnimation(
                RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                e1.getX(),
                e1.getY());

        runGameView.addAnimation(
                RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                e2.getX(),
                e2.getY());
    }

    public void ShellToWallCollide(Entity e1, Entity e2, GameWorld gameWorld) {
        List<Entity> entitiesToRemove = new ArrayList<>();
        Wall wall = (Wall) e1;
        if(wall.isBreakAble()){
            entitiesToRemove.add(e1);
            runGameView.addAnimation(
                    RunGameView.BIG_EXPLOSION_ANIMATION,
                    RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                    e1.getX(),
                    e1.getY());
        }

        entitiesToRemove.add(e2);
        for (Entity entity: entitiesToRemove) {
            gameWorld.removeEntity(entity.getId());
            gameWorld.addToGarbage(entity);
        }
        runGameView.addAnimation(
                RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                e2.getX(),
                e2.getY());
    }

    public void ShellToPlayerOneTankCollide(Entity e1, Entity e2, GameWorld gameWorld) {
        List<Entity> entitiesToRemove = new ArrayList<>();
        PlayerOneTank tank = (PlayerOneTank) e1;
        if(tank.getHealth() == 0 && tank.getLife()>0)
        {

            runGameView.addAnimation(
                    RunGameView.BIG_EXPLOSION_ANIMATION,
                    RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                    e1.getX(),
                    e1.getY());


            if(tank.getLife()==1)
            {
                entitiesToRemove.add(icon1);
            }
            else if(tank.getLife()==2)
            {
                entitiesToRemove.add(icon2);
            }
            else
            {
                entitiesToRemove.add(icon3);
            }

            tank.setX(Constants.PLAYER_ONE_TANK_INITIAL_X);
            tank.setY(Constants.PLAYER_ONE_TANK_INITIAL_Y);


            tank.decreaseLife();
            tank.setHealth();
            tank.setAngle(0);

            addPlayerOneLifeTank(tank.getLife());


        }
        else {
            tank.decreaseHealth();
        }

        entitiesToRemove.add(e2);
        for (Entity entity: entitiesToRemove) {
            gameWorld.removeEntity(entity.getId());
            gameWorld.addToGarbage(entity);
        }

        runGameView.addAnimation(
                RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                e2.getX(),
                e2.getY());
    }



    public void ShellToPlayerTwoTankCollide(Entity e1, Entity e2, GameWorld gameWorld) {
        List<Entity> entitiesToRemove = new ArrayList<>();
        PlayerTwoTank tank = (PlayerTwoTank) e1;
        if(tank.getHealth() == 0 && tank.getLife()>0){

            runGameView.addAnimation(
                    RunGameView.BIG_EXPLOSION_ANIMATION,
                    RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                    e1.getX(),
                    e1.getY());
            if(tank.getLife()==1)
            {
                entitiesToRemove.add(icon4);
            }
            else if(tank.getLife()==2)
            {
                entitiesToRemove.add(icon5);
            }
            else
            {
                entitiesToRemove.add(icon6);
            }

            tank.setX(Constants.PLAYER_TWO_TANK_1_INITIAL_X);
            tank.setY(Constants.PLAYER_TWO_TANK_1_INITIAL_Y);
            tank.setAngle(180);

            tank.decreaseLife();
            tank.setHealth();

            addPlayerTwoLifeTank(tank.getLife());
        }
        else {
            tank.decreaseHealth();
        }

        entitiesToRemove.add(e2);
        for (Entity entity: entitiesToRemove) {
            gameWorld.removeEntity(entity.getId());
            gameWorld.addToGarbage(entity);
        }

        runGameView.addAnimation(
                RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                e2.getX(),
                e2.getY());
    }



    public void TankToPowerUpCollide(Entity e1, Entity e2) {
        List<Entity> entitiesToRemove = new ArrayList<>();

        if(e1 instanceof PlayerOneTank)
        {
            if(e2 instanceof Life)
            {
                if(PlayerOneTank.getLife()<3)
                {
                    PlayerOneTank.increaseLife();
                    addPlayerOneLifeTank( ((PlayerOneTank) e1).getLife());
                }
            }
            else if(e2 instanceof SpeedBooster)
            {
                Constants.PLAYER_ONE_TANK_MOVEMENT_SPEED=3;
            }
            else if(e2 instanceof Repair)
            {
                if(PlayerOneTank.getHealth()<5)
                {
                    PlayerOneTank.increaseHealth();
                }
            }

        }
        else if(e1 instanceof PlayerTwoTank)
        {

            if(e2 instanceof Life)
            {
                if(PlayerTwoTank.getLife()<3)
                {
                    PlayerTwoTank.increaseLife();
                    addPlayerTwoLifeTank(((PlayerTwoTank) e1).getLife());
                }
            }
            else if(e2 instanceof SpeedBooster)
            {
                Constants.PLAYER_TWO_TANK_MOVEMENT_SPEED=3;
            }
            else if(e2 instanceof Repair)
            {
                if(PlayerTwoTank.getHealth()<5)
                {
                    PlayerTwoTank.increaseHealth();
                }
            }
        }

        entitiesToRemove.add(e2);
        for (Entity entity: entitiesToRemove) {
            gameWorld.removeEntity(entity.getId());
            gameWorld.addToGarbage(entity);
        }
    }

    public void TankTankCollide(Entity e1, Entity e2) {
        double num1 = e1.getXBound() - e2.getX();
        double num2 = e2.getXBound() - e1.getX();
        double num3 = e1.getYBound() - e2.getY();
        double num4 = e2.getYBound() - e1.getY();
        double [] findMin = {num1, num2, num3, num4};
        double smallest = num1;

        // Find smallest number
        for (int i = 0; i < 4; i++) {
            if (smallest > findMin[i]) {
                smallest = findMin[i];
            }
        }

        // If number 1 is smallest
        if (num1 == smallest) {
            e1.setX(e1.getX() - smallest / 2);
            e2.setX(e2.getX() + smallest / 2);
        }

        // If number 2 is smallest
        if (num2 == smallest) {
            e1.setX(e1.getX() + smallest / 2);
            e2.setX(e2.getX() - smallest / 2);
        }

        // If number 3 is smallest
        if (num3 == smallest) {
            e1.setY(e1.getY() - smallest / 2);
            e2.setY(e2.getY() + smallest / 2);
        }

        // If number 4 is smallest
        if (num4 == smallest) {
            e1.setY(e1.getY() + smallest / 2);
            e2.setY(e2.getY() - smallest / 2);
        }
    }

    public void TankToWallCollide(Entity e1, Entity e2) {
        double num1 = e1.getXBound() - e2.getX();
        double num2 = e2.getXBound() - e1.getX();
        double num3 = e1.getYBound() - e2.getY();
        double num4 = e2.getYBound() - e1.getY();
        double [] findMin = {num1, num2, num3, num4};
        double smallest = num1;

        // Find smallest number
        for (int i = 0; i < 4; i++) {
            if (smallest > findMin[i]) {
                smallest = findMin[i];
            }
        }

        // If number 1 is smallest
        if (num1 == smallest) {
            e1.setX(e1.getX() - smallest);
        }

        // If number 2 is smallest
        if (num2 == smallest) {
            e1.setX(e1.getX() + smallest);
        }

        // If number 3 is smallest
        if (num3 == smallest) {
            e1.setY(e1.getY() - smallest);
        }

        // If number 4 is smallest
        if (num4 == smallest) {
            e1.setY(e1.getY() + smallest);
        }
    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     */
    private boolean updateGame() {
        // TODO: Implement.


        for (Entity entity: new ArrayList<>(gameWorld.getEntities())){
            entity.move(gameWorld);
            entity.checkBounds();
        }

        List<Entity> tempShells = gameWorld.getTempEntities();
        for (Entity newShellEntity : tempShells) {
            runGameView.addSprite(
                    newShellEntity.getId(),
                    RunGameView.SHELL_IMAGE_FILE,
                    newShellEntity.getX(),
                    newShellEntity.getY(),
                    newShellEntity.getAngle());
            System.out.println(newShellEntity.getId());
        }

        for (Entity entity : tempShells) {
            gameWorld.addEntity(entity);
        }

        tempShells.removeAll(tempShells);

        for (Entity entity : gameWorld.getEntities()) {
            runGameView.setSpriteLocationAndAngle(
                    entity.getId(),
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle());
        }

        for (Entity entity : gameWorld.getGarbageList()) {
            gameWorld.removeEntity(entity.getId());
            runGameView.removeSprite(entity.getId());
        }
        gameWorld.getGarbageList().clear();

        List<Entity> deleteShells = new ArrayList<>();
        for (Entity entity: gameWorld.getEntities()) {
            if(entity.checkBounds()) {
                deleteShells.add(entity);
            }
        }

        for (Entity entity: deleteShells) {
            gameWorld.removeEntity(entity.getId());
            runGameView.removeSprite(entity.getId());
        }

        // Collision handling
        for (int i = 0; i < gameWorld.getEntities().size(); i++) {
            for (int j = i + 1; j < gameWorld.getEntities().size(); j++) {
                if (entitiesOverLap(gameWorld.getEntities().get(i), gameWorld.getEntities().get(j))) {
                    collisionHandling(gameWorld.getEntities().get(i), gameWorld.getEntities().get(j), gameWorld);
                }
            }
        }
        return true;
    }

    public boolean hasDied(){
        if (PlayerOneTank.getLife() == 0 || PlayerTwoTank.getLife() == 0) {
            return true;
        }
        return false;
    }

    /**
     * resetGame is called at the end of the game once the gameplay loop exits. This should clear any existing data from
     * the game so that if the game is restarted, there aren't any things leftover from the previous run.
     */
    private void resetGame() {
        // TODO: Implement.
        runGameView.reset();
        gameWorld.reset();
        PlayerOneTank.setHealth(5);
        PlayerOneTank.setLife(2);
        PlayerTwoTank.setHealth(5);
        PlayerTwoTank.setLife(2);

    }

    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }
}

