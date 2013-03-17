package thoughtworks;

import java.util.ArrayList;

import thoughtworks.command.*;
import thoughtworks.fixedAssets.Space;
import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.functionClass.RoleNumberTransfer;
import thoughtworks.players.*;

public class Game {
	public static final String HINT_OF_START = "请输入游戏开始指令：";
	public static final String ERROR_OF_START = "指令错误，请重新输入游戏开始指令：";
	public static final String HINT_OF_PLAYER_CHOICE = "请选择2~4位"
			+ "不重复玩家，输入编号即可。（1.钱夫人；2.阿土伯；3.孙小美；4.金贝贝）：";
	public static final String HINT_OF_PLAYER_INITIAL = "请输入玩家初始"
			+ "资金，范围" + GlobalSettings.INITIAL_MIN_FUNDS + "~" + GlobalSettings.INITIAL_MAX_FUNDS +
			"（默认" + GlobalSettings.INITIAL_FUNDS + "）：";
	public static final String ERROR_OF_PLAYER_NUMBERS = "玩家编号输入错误，请重新输入！";

	public static final String START_COMMAND = "rich";

	private PlayerList playerList;
	private Map map = new Map();
	private CommandManager commandManager = new CommandManager();

	public ArrayList<Player> getPlayers() {
		return playerList.getPlayers();
	}
	
	public MapObject getMapObjectWithIndex(int index) {
		return map.getMapObjectWithIndex(index);
	}
	
	public Map getMap() {
		return map;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public Player getTheOwnerOfSpace(Space space){
        return space.getOwner();
    }

	public void start() {
		System.out.println(HINT_OF_START + "\n");
		while (true) {
			if (Input.isInputCommandEquals(START_COMMAND)) {
				obtainInputToInitialFunds();
				obtainInputToCreatPlayerList();
				gameProcess();
				break;
			} else {
				System.out.println(ERROR_OF_START + "\n");
			}
		}
	}

	public void gameProcess() {
		while (true) {			
			for (Player player : playerList.getPlayers()) {
				map.drawMap(playerList.getPlayers());
				if(isWinnerProduced()){
					System.out.println("恭喜玩家" + getWinner().getName() + "获胜！");
					return;
			    }
				
				player.decreaseHospitalizedTimes();
				player.decreaseLuckyTimes();
				player.decreaseTrappedInPrisonTimes();
				
				if (player.isBombed() || player.isTrappedInPrison() ||
						player.isBankrupt()) {
					continue;
				}
				
				while (true) {
					System.out.println(player.getName() + ">");
					String input = Input.getString();
					if (commandManager.isCommandRunEnd(input, player, this)) {
						break;
					}
				}
				
				if (!isPlayerPassOnSpaceOwned(player)) {
					map.getMapObjectWithIndex(player.getPosition())
							.playerPassOnHere(player, this);
					continue;
				}
				Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
				if (space.isOwnedBy(player)) {
					playerPassOnOwnSpace(player);
					continue;
				}
				playerPassOnOtherSpace(player);
			}
		}
	}
	
	public boolean isWinnerProduced() {
		int playerNumber = 0;
		for (Player player : playerList.getPlayers()) {
			if(player.isBankrupt()){
				continue;
			}
			playerNumber++;
		}
		if(playerNumber > 1){
			return false;
		}
		return true;
	}
	
	public Player getWinner() {
		for (Player player : playerList.getPlayers()) {
			if(!player.isBankrupt()){
				return player;
			}
		}
		return null;
	}

	public boolean isPlayerPassOnSpaceOwned(Player player){
		if(map.isSpaceWithPositionOf(player.getPosition())){
			Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
			if(space.isOwned()){
				return true;
			}
		}
		return false;
	}
	
	public void playerPassOnOwnSpace(Player player) {
		Space space = (Space) map.getMapObjectWithIndex(player.getPosition());
		if (space.isPlayerToUpgradeOwnSpace(player)) {
			upgradeFixedAssetsWithPositionOf(player.getPosition());
		}
		return;
	}
	
	public void playerPassOnOtherSpace(Player player) {
		Space space = (Space) map.getMapObjectWithIndex(player.getPosition());
		while (!space.isSafeForPlayerPassOnOtherSpace(player)) {
			player.testBankrupt(space.getPassToll());
			if (player.isBankrupt()) {
				System.out.println("玩家" + player.getName() + "破产");
				playerList.getPlayers().remove(player);
			} else {
				System.out.println("请输入要出售的房产编号：");
				commandManager.sellFixedAssetsWithCommand(Input.getString(), player, this);
			}
		}
	}

	public void updatePlayerPosition(Player player, int step) {
		int position = player.getPosition();
		for (int i = 0; i < step; i++) {
			position = PositionUpdate.getNextPosition(position);
			if (map.getMapObjectWithIndex(position).hasBlock()) {
				System.out.println("非常不幸，被路障拦截！");
				player.updatePosition(position);
				map.getMapObjectWithIndex(position).resetBlock();
				return;
			}
			if (map.getMapObjectWithIndex(position).hasBomb()) {
				System.out.println("非常不幸，被路障拦截！");
				player.updatePosition(Map.HosipitalPosition);
				player.toBeBombed();
				map.getMapObjectWithIndex(position).resetBomb();
				return;
			}
		}
		player.updatePositionWithStep(step);
	}

	public void upgradeFixedAssetsWithPositionOf(int position){
		Space oldSpace = (Space)map.getMapObjectWithIndex(position);
		Space newSpace = getUpgradeSpace(oldSpace);
		if (newSpace != null) {
            oldSpace.getOwner().upgradeOwnFixedAssets(
                    oldSpace);
			map.upgradeFixedAssets(newSpace);
			System.out.println("房产升级成功！");
		}
	}
	
	public void sellSpaceWithPositionOf(int position){
		Space oldSpace = (Space)map.getMapObjectWithIndex(position);
        Player player = oldSpace.getOwner();
		Space newSpace = getNewSpaceAfterSelled(oldSpace);
		player.sellSpace(oldSpace);
		map.upgradeFixedAssets(newSpace);
	}

	public Space getUpgradeSpace(Space space){
		return space.upgrade();
	}
	
	public Space getNewSpaceAfterSelled(Space space){
		return space.sell();
	}

	public void obtainInputToInitialFunds() {
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

	public void obtainInputToCreatPlayerList() {
		System.out.println(HINT_OF_PLAYER_CHOICE + "\n");
		boolean isSuccess;
		do {
			isSuccess = isCreatPlayerListSuccess(Input.getString());
		} while (!isSuccess);
	}

	public boolean isCreatPlayerListSuccess(String input) {
		int[] roleNumberArray = RoleNumberTransfer
				.transferInputToRoleNumberArray(input);		
		if (RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(input.toCharArray())
				&& RoleNumberTransfer
						.isRoleNumbersWithinTheLimits(input.toCharArray())
				&& RoleNumberTransfer.isRoleNumbersNotRepeat(input.toCharArray())) {
			playerList = new PlayerList(roleNumberArray);
			return true;
		} else {
			System.out.println(ERROR_OF_PLAYER_NUMBERS + "\n");
			return false;
		}
	}
}
