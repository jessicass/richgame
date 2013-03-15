package thoughtworks;

import java.util.ArrayList;

import thoughtworks.players.Player;

public interface MapObject {
	public String getSymbol(ArrayList<Player> players);
	public boolean hasBlock();
	public boolean hasBomb();
	public void setBlock();
	public void resetBlock();
	public void setBomb();
	public void resetBomb();
	public int getPosition();
//修改
	public MapObject upgrade();
	public void playerPassOnHere(Player player, Game game);
}
