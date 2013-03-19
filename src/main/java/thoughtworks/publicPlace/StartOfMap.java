package thoughtworks.publicPlace;

import thoughtworks.MapObject;

public class StartOfMap extends Terrain implements MapObject {
	public static final String symbol = "S";
	
	public StartOfMap(int position) {
		super(position);
	}

	@Override
	public String getSymbol(){
		return symbol;
	}
}
