package View;

import javax.swing.*;
import tankGame.KeyboardReader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class MainScreen
{
    private  JFrame mainJFrame;
    private  JPanel mainPanel;
    private  CardLayout mainPanelLayout;
    private RunGameView runGameView;


    public MainScreen(ActionListener startMenuListener)
    {
        mainJFrame=new JFrame();
        mainJFrame.setTitle("Tank Wars Game");
        mainJFrame.setResizable(false);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setVisible(false);
        mainJFrame.addKeyListener(KeyboardReader.instance());


        mainPanel=new JPanel();
        mainPanelLayout = new CardLayout();
        mainPanel.setLayout(mainPanelLayout);

        StartMenuScreen startMenuScreen=new StartMenuScreen("Start Game",startMenuListener);
        mainPanel.add(startMenuScreen,Screen.START_GAME_SCREEN.getScreenName());

        StartMenuScreen endMenuScreen=new StartMenuScreen("Restart Game",startMenuListener);
        mainPanel.add(endMenuScreen,Screen.END_MENU_SCREEN.getScreenName());

        runGameView = new RunGameView();
        mainPanel.add(runGameView, Screen.RUN_GAME_SCREEN.getScreenName());

        mainJFrame.add(mainPanel);
    }

    public RunGameView getRunGameView() {
        return runGameView;
    }

    public void setScreen(Screen screen) {
        mainJFrame.setVisible(false);

        Dimension screenSize = switch (screen) {
            case START_GAME_SCREEN, END_MENU_SCREEN -> StartMenuScreen.SCREEN_DIMENSIONS;
            case RUN_GAME_SCREEN -> RunGameView.SCREEN_DIMENSIONS;
        };
        mainJFrame.setSize(screenSize);
        mainPanelLayout.show(mainPanel, screen.getScreenName());

        mainJFrame.setVisible(true);
    }

    public void closeGame() {
        mainJFrame.dispatchEvent(new WindowEvent(mainJFrame, WindowEvent.WINDOW_CLOSING));
    }
}
