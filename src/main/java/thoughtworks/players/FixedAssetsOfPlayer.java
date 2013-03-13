package thoughtworks.players;


public class FixedAssetsOfPlayer {
	private int numberOfSpaces = 0;
	private int numberOfCottages = 0;
	private int numberOfHouses = 0;
	private int numberOfSkyscrapers = 0;
	
	public int getNumberOfSpaces(){
		return numberOfSpaces;
	}
	
	public int getNumberOfCottages(){
		return numberOfCottages;
	}
	
	public int getNumberOfHouses(){
		return numberOfHouses;
	}
	
	public int getNumberOfSkyscrapers(){
		return numberOfSkyscrapers;
	}

	public int getTotalNumberOfFixedAssets() {
		return numberOfSpaces + numberOfCottages + 
				numberOfHouses + numberOfSkyscrapers;
	}
	
	public void addNewSpace(){
		numberOfSpaces++;
	}

	public void upgradeSpace(int level){
		switch(level){
	        case 0 :
	    	    numberOfSpaces--;
	    	    numberOfCottages++;
				return;
	        case 1 :
	        	numberOfCottages--;
	    	    numberOfHouses++;
				return;
	        case 2 :
	        	numberOfHouses--;
	    	    numberOfSkyscrapers++;
				return;
		}
	}

	public void sellSpace(int level) {
		switch(level){
	        case 0 :
	    	    numberOfSpaces--;
				return;
	        case 1 :
	        	numberOfCottages--;
				return;
	        case 2 :
	        	numberOfHouses--;
				return;
	        case 3 :
	        	numberOfSkyscrapers--;
				return;
		}
	}
}
