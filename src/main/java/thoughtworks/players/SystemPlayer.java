package thoughtworks.players;

import java.awt.*;

public enum SystemPlayer {
    Q("钱夫人", 1, Color.red), A("阿土伯", 2, Color.yellow), S("孙小美", 3, Color.blue), J("金贝贝", 4, Color.green);

    private String name;
    private int id;
    private Color color;

    public Color getColor() {
        return color;
    }

    private SystemPlayer(String name, int id, Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    public static String getPlayerName(int index) {
        for (SystemPlayer c : SystemPlayer.values()) {
            if (c.getId() == index) {
                return c.getName();
            }
        }
        return null;
    }

    public static String getShortName(int index) {
        for (SystemPlayer c : SystemPlayer.values()) {
            if (c.getId() == index) {
                return c.toString();
            }
        }


        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static SystemPlayer getPlayer(final int id) {
        for (SystemPlayer systemPlayer : SystemPlayer.values()) {
            if (systemPlayer.getId() == id) {
                return systemPlayer;
            }
        }
        return null;
    }
}
