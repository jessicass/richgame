package thoughtworks;

import thoughtworks.players.Player;

public interface MapObject {
	public String getSymbol();
	public boolean hasBlock();
	public boolean hasBomb();
	public void setBlock();
	public void resetBlock();
	public void setBomb();
	public void resetBomb();
	public int getPosition();
	public MapObject upgrade();
	public void playerPassOnHere(Player player, Game game);
}
