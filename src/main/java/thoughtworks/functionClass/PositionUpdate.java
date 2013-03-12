package thoughtworks.functionClass;

import thoughtworks.Map;

public class PositionUpdate {
	public static int getCurrentPositionWithDistance(int position, int distance){
		if((position + distance) > Map.MAX_POSITION){
			return (position + distance) % (Map.MAX_POSITION + 1);
		}
		if((position + distance) < 0){
			return position + distance + Map.MAX_POSITION + 1;
		}
		return position + distance;
	}
	
	public static int getNextPosition(int position){
		if(position == Map.MAX_POSITION){
			return 0;
		}
		return position + 1; 
	}
	
	public static boolean isPositionInMapArea(int position){
		return (position >= 0) && (position <= Map.MAX_POSITION);
	}
}
