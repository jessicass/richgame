package thoughtworks.publicPlace;

import thoughtworks.MapObject;

public class Hospital extends Terrain implements MapObject {
	public static final String symbol = "H";
	
	
	public Hospital(int position) {
		super(position);
	}

	@Override
	public String getSymbol(){
		return symbol;
	}
}
