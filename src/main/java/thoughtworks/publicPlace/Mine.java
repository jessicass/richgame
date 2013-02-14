package thoughtworks.publicPlace;

import thoughtworks.MapObject;

public class Mine implements MapObject {
	public static final String symbol = "$";
	public static final int position1 = 64;
	public static final int position2 = 65;
	public static final int position3 = 66;
	public static final int position4 = 67;
	public static final int position5 = 68;
	public static final int position6 = 69;
	private int position;
	private int points;
	
	public Mine(int position){
		this.position = position;
		switch(position){
		    case position1:
		    	points = 60;
		    	break;
		    case position2:
		    	points = 80;
		    	break;
		    case position3:
		    	points = 40;
		    	break;
		    case position4:
		    	points = 100;
		    	break;
		    case position5:
		    	points = 80;
		    	break;
		    case position6:
		    	points = 20;
		    	break;
		}
	}
	
	public int getPosition(){
		return position;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String getSymbol(){
		return symbol;
	}
}
