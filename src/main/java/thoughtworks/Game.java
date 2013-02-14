package thoughtworks;

import java.util.ArrayList;
import java.util.Scanner;

import thoughtworks.players.*;

public class Game {
	private PlayerList playerList;
	//private Map map;
	
	public static final String HINT_OF_START = "请输入游戏开始指令：";
	public static final String ERROR_OF_START = "指令错误，请重新输入游戏开始指令：";
	public static final String HINT_OF_PLAYER_CHOICE = "请选择2~4位" +
			"不重复玩家，输入编号即可。（1.钱夫人；2.阿土伯；3.孙小美；" +
			"4.金贝贝）：";
	public static final String HINT_OF_PLAYER_INITIAL = "请输入玩家初始" +
			"资金，范围1000~50000（默认10000）：";
	public static final String ERROR_OF_PLAYER_NUMBERS = 
			"玩家编号输入错误，请重新输入！";
	
	public static final String START_COMMAND = "rich";
	
	public ArrayList<Player> getPlayers(){
		return playerList.getPlayers();
	}
	
	public void start(){
		System.out.println(HINT_OF_START + "\n");

		while(true){
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			scanner.close();
			
			if(input.matches(START_COMMAND)){
				obtainInputToCreatPlayerList();
		        System.out.println(HINT_OF_PLAYER_INITIAL + "\n");
		        Scanner initialFunds = new Scanner(System.in);
		        Player.INITIAL_FUNDS = initialFunds.nextInt();
		        initialFunds.close();
		        break;
		    }
		    else{
			    System.out.println(ERROR_OF_START + "\n");
		    }
		}
	}
	
	public void obtainInputToCreatPlayerList(){
		System.out.println(HINT_OF_PLAYER_CHOICE + "\n");
		boolean isSuccess;
		do{
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			scanner.close();
			isSuccess = isCreatPlayerListSuccess(input);
		}while(!isSuccess);
	}
	
	public boolean isCreatPlayerListSuccess(String input){
		int[] roleNumberArray = RoleNumberTransfer.parseRoleNumberListToArray(
				RoleNumberTransfer.getRoleNumberListFromInput(input));
		if(RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(roleNumberArray) && 
				RoleNumberTransfer.isRoleNumbersWithinTheLimits(roleNumberArray) && 
				RoleNumberTransfer.isRoleNumbersNotRepeat(roleNumberArray)){
			playerList = new PlayerList(roleNumberArray);
		    return true;
	    }
	    else{
	    	System.out.println(ERROR_OF_PLAYER_NUMBERS + "\n");
	    	return false;
	    }
	}
}
