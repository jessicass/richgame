package thoughtworks;

public class GlobalSettings {
    public static int INITIAL_FUNDS = 10000;
    public static int INITIAL_MIN_FUNDS = 500;
    public static int INITIAL_MAX_FUNDS = 50000;
    
	public static final int MAX_POSITION = 69;
	public static final int HosipitalPosition = 14;	
	
	public static final int presentFunds = 2000;
	public static final int presentPoints = 200;
	public static final int MAX_GIFT_NUMBER = 3;
	public static final int MIN_GIFT_NUMBER = 1;
	
	public static final int timesToPauseWhenPlayerBeHospitalized = 3;
	public static final int timesPlayerBeTrappedInPrison = 2;
	
	public static final int LIMIT_NUMBER_OF_TOOLS = 10;
	public static final String WELCOME_TO_TOOLROOM = "欢迎光临道具屋，请输入" +
			"您所需要的道具的编号：";
	public static final String NUMBER_OF_TOOLS_BEYOND_LIMIT = "您已经拥有10个道具";
	public static final String QUIT_TOOLROOM = "f";
	public static final int MAX_TOOL_NUMBER = 3;
	public static final int MIN_TOOL_NUMBER = 1;
	
	public static final String WELCOME_TO_GIFTROOM = "欢迎光临礼品屋，请输入" +
			"您所需要的礼品的编号：";
	
	public static final String HINT_OF_PLAYER_CHOICE = "请选择2~4位"
			+ "不重复玩家，输入编号即可。（1.钱夫人；2.阿土伯；3.孙小美；4.金贝贝）：";
	public static final String HINT_OF_PLAYER_INITIAL = "请输入玩家初始"
			+ "资金，范围" + INITIAL_MIN_FUNDS + "~" + INITIAL_MAX_FUNDS +
			"（默认" + INITIAL_FUNDS + "）：";
	public static final String ERROR_OF_PLAYER_NUMBERS = "玩家编号输入错误，请重新输入！";
}
