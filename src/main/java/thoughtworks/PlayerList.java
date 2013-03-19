package thoughtworks;

import java.util.ArrayList;

import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.RoleNumberTransfer;
import thoughtworks.players.Player;

public class PlayerList {	
	public static final String HINT_OF_PLAYER_CHOICE = "请选择2~4位"
			+ "不重复玩家，输入编号即可。（1.钱夫人；2.阿土伯；3.孙小美；4.金贝贝）：";
	public static final String HINT_OF_PLAYER_INITIAL = "请输入玩家初始"
			+ "资金，范围" + GlobalSettings.INITIAL_MIN_FUNDS + "~" + GlobalSettings.INITIAL_MAX_FUNDS +
			"（默认" + GlobalSettings.INITIAL_FUNDS + "）：";
	public static final String ERROR_OF_PLAYER_NUMBERS = "玩家编号输入错误，请重新输入！";
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public PlayerList(int[] roleNumbers){
		for(int i = 0; i < roleNumbers.length; i++){
			playerList.add(new Player(roleNumbers[i]));
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return playerList;
	}
	
	public Player getPlayer(int index){
		return playerList.get(index-1);
	}

	public static void obtainInputToInitialFunds() {
		System.out.println(HINT_OF_PLAYER_INITIAL + "\n");
		while(true){
			String input = Input.getString();
			if(input.matches(""))
				return;
			if(Input.isInputAnIntegerInArea(input, GlobalSettings.INITIAL_MIN_FUNDS,
					GlobalSettings.INITIAL_MAX_FUNDS)){
				GlobalSettings.INITIAL_FUNDS = Integer.parseInt(input);
				break;
			}
		}
	}

	public static boolean isCreatPlayerListSuccess(String input) {
		if (isRoleNumbersBeRight(input.toCharArray())) {
			return true;
		}
		System.out.println(ERROR_OF_PLAYER_NUMBERS + "\n");
		return false;
	}

	private static boolean isRoleNumbersBeRight(char[] roleNumbers) {
		return RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(roleNumbers)
				&& RoleNumberTransfer.isRoleNumbersWithinTheLimits(roleNumbers)
				&& RoleNumberTransfer.isRoleNumbersNotRepeat(roleNumbers);
	}
	
	public static String obtainInputToCreatePlayerList() {
		System.out.println(PlayerList.HINT_OF_PLAYER_CHOICE + "\n");
		String input;
		do {
			input = Input.getString();
		} while (!isCreatPlayerListSuccess(input));
		return input;
	}
}
