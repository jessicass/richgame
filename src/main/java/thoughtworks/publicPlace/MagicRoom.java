package thoughtworks.publicPlace;

import thoughtworks.MapObject;

public class MagicRoom extends Terrain implements MapObject {
    public static final String symbol = "M";

    public MagicRoom(int position) {
        super(position);
    }

    @Override
    protected String getSymbol() {
        return MagicRoom.symbol;
    }
}
