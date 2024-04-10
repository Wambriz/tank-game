package View;

public enum Screen {
    START_GAME_SCREEN("start"),
    RUN_GAME_SCREEN("game"),
    END_MENU_SCREEN("end");

    private final String screenName;

    Screen(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
