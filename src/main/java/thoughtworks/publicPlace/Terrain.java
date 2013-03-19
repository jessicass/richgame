package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

import java.util.ArrayList;

public abstract class Terrain implements MapObject {
    public boolean hasBlock;
    public boolean hasBomb;
    public int position;

    public Terrain(int position) {
        this.position = position;
    }

    abstract protected String getSymbol();

    public String getSymbol(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPosition() == position) {
                return player.getShortName();
            }
        }
        if (hasBlock) {
            return Block.symbol;
        }
        if (hasBomb) {
            return Bomb.symbol;
        }
        return getSymbol();
    }

    public int getPosition() {
        return position;
    }

    public void setBlock() {
        hasBlock = true;
    }

    public void resetBlock() {
        hasBlock = false;
    }

    public boolean hasBlock() {
        return hasBlock;
    }

    public void setBomb() {
        hasBomb = true;
    }

    public void resetBomb() {
        hasBomb = false;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void playerPassOnHere(Player player, Game game) {

    }
}