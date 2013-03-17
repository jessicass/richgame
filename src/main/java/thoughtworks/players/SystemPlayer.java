package thoughtworks.players;

import java.awt.*;

public enum SystemPlayer {
    Q("钱夫人", 1, Color.red), A("阿土伯", 2, Color.yellow), S("孙小美", 3, Color.blue), J("金贝贝", 4, Color.green);

    private String name;
    private int id;
    private Color color;

    private SystemPlayer(String name, int id, Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    private int getId() {
    	return id;
    }
    
    public Color getColor() {
    	return color;
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
