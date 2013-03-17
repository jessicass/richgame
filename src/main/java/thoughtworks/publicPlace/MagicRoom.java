package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class MagicRoom extends Terrain implements MapObject {
	public static final String symbol = "M";
	public MagicRoom(int position) {
        super(position);
	}
}
