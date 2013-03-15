package thoughtworks.players;

public enum DefaultPlayer {
	Q("钱夫人",1),A("阿土伯",2),S("孙小美",3),J("金贝贝",4);
	
	private String name;
	private int index;  
  
	private DefaultPlayer(String name, int index) {
	    this.name = name;
		this.index = index;  
	}  

	public static String getPlayerName(int index) {
		for (DefaultPlayer c : DefaultPlayer.values()) {
			if (c.getIndex() == index) {
				return c.getName();  
	        }  
	    }  
	    return null;  
	}
	
	public static String getShortName(int index) {
		for (DefaultPlayer c : DefaultPlayer.values()) {
			if (c.getIndex() == index) {
				return c.toString();  
	        }  
	    }  
	    return null;  
	}
	
    public int getIndex() {  
        return index;  
    } 
    
    public String getName() {  
        return name;  
    }
}
