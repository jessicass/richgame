package thoughtworks;

import java.util.ArrayList;

import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.RoleNumberTransfer;
import thoughtworks.players.Player;

public class PlayerList {	

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
		System.out.println(GlobalSettings.HINT_OF_PLAYER_INITIAL + "\n");
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
		System.out.println(GlobalSettings.ERROR_OF_PLAYER_NUMBERS + "\n");
		return false;
	}

	private static boolean isRoleNumbersBeRight(char[] roleNumbers) {
		return RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(roleNumbers)
				&& RoleNumberTransfer.isRoleNumbersWithinTheLimits(roleNumbers)
				&& RoleNumberTransfer.isRoleNumbersNotRepeat(roleNumbers);
	}
	
	public static String obtainInputToCreatePlayerList() {
		System.out.println(GlobalSettings.HINT_OF_PLAYER_CHOICE + "\n");
		String input;
		do {
			input = Input.getString();
		} while (!isCreatPlayerListSuccess(input));
		return input;
	}
}
