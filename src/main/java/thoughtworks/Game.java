package thoughtworks;

import java.util.ArrayList;

import thoughtworks.command.*;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.*;
import thoughtworks.publicPlace.Hospital;

public class Game {
	public static final String HINT_OF_START = "请输入游戏开始指令：";
	public static final String ERROR_OF_START = "指令错误，请重新输入" + "游戏开始指令：";
	public static final String HINT_OF_PLAYER_CHOICE = "请选择2~4位"
			+ "不重复玩家，输入编号即可。（1.钱夫人；2.阿土伯；3.孙小美；" + "4.金贝贝）：";
	public static final String HINT_OF_PLAYER_INITIAL = "请输入玩家初始"
			+ "资金，范围1000~50000（默认10000）：";
	public static final String ERROR_OF_PLAYER_NUMBERS = "玩家编号输入错误，请重新输入！";

	public static final String START_COMMAND = "rich";

	private PlayerList playerList;
	private Map map = new Map();

	public ArrayList<Player> getPlayers() {
		return playerList.getPlayers();
	}
	
	public MapObject getMapObjectWithIndex(int index) {
		return map.getMapObjectWithIndex(index);
	}
	
	public Map getMap() {
		return map;
	}
	
	public Player getTheOwnerOfSpace(int spacePosition){
		return playerList.getTheOwnerOfSpace(spacePosition);
	}

	public void start() {
		System.out.println(HINT_OF_START + "\n");
		while (true) {
			if (Input.isInputCommandEquals(START_COMMAND)) {
				obtainInputToCreatPlayerList();
				obtainInputToInitialFunds();
				gameProcess();
				break;
			} else {
				System.out.println(ERROR_OF_START + "\n");
			}
		}
	}

	public void gameProcess() {
		for (Player player : playerList.getPlayers()) {
			player.decreasHospitalizedTimes();
			player.decreasLuckyTimes();
			player.decreasTrappedInPrisonTimes();
		}

		for (Player player : playerList.getPlayers()) {
			if (!player.isBombed() && !player.isTrappedInPrison()) {
				while (true) {
					System.out.println(player.getPlayerName() + ">");
					String input = Input.getString();
					if (CommandManager.isCommandRunEnd(input, player, this)) {
						break;
					}
				}
				/*
			    if(map.isSpaceWithPositionOf(player.getPosition())){
			    	Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
			    	if(space.isOwned()){
			    		playerPassOnSpace(player);
			    	}
				}
				map.getMapObjectWithIndex(player.getPosition()).playerPassOnHere(player, this);
				*/
			}
			map.drawMap(playerList.getPlayers());
		}
	}
/*
	public void playerPassOnMapObject(MapObject mapObject){
		
	}
	
	public boolean isPlayerPassOnSpaceOwned(MapObject mapObject){
		
	}
	
	public void playerPassOnSpace(Player player) {
		Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
		if(!space.isOwned()){
			space.playerPassOnHere(player, this);
			return;
		}
		if (player.isOwnerOfSpace(space.getPosition())) {
			if (space.isPlayerToUpgradeOwnSpace(player)) {
				upgradeFixedAssetsWithPositionOf(player.getPosition());
			}
			return;
		}
		while (!space.isSafeForPlayerPassOnOtherSpace(player, this)) {
			if (player.isBankrupt()) {

			} else {

			}
		}
	}
//////	
	public boolean isPlayerBankrupt(Player player) {
		if(player.isBankrupt()){
			
		}
		else{
			
		}
	}*/

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
				System.out.println("非常不幸，被炸弹炸伤！");
				player.updatePosition(Hospital.position);
				player.toBeBombed();
				map.getMapObjectWithIndex(position).resetBomb();
				return;
			}
		}
		player.updatePositionWithStep(step);
	}

	public void upgradeFixedAssetsWithPositionOf(int position){
		MapObject mapObject = getUpgradeMapObject(map.getMapObjectWithIndex(position));
		if (mapObject != null) {
			playerList.getTheOwnerOfSpace(position).upgradeOwnFixedAssets(
					mapObject);
			map.upgradeFixedAssets(mapObject);
		}
	}
	
	public void sellSpaceWithPositionOf(int position){
		Player player = playerList.getTheOwnerOfSpace(position);
		Space space = getNewSpaceAfterSelled((Space)map.getMapObjectWithIndex(position));
		player.sellSpace(space.getPosition());
		map.upgradeFixedAssets(space);
	}

	public MapObject getUpgradeMapObject(MapObject mapObject){
		return mapObject.upgrade();
	}
	
	public Space getNewSpaceAfterSelled(Space space){
		return space.sell();
	}

	public void obtainInputToInitialFunds() {
		System.out.println(HINT_OF_PLAYER_INITIAL + "\n");
		Player.INITIAL_FUNDS = Input.getInteger();
	}

	public void obtainInputToCreatPlayerList() {
		System.out.println(HINT_OF_PLAYER_CHOICE + "\n");
		boolean isSuccess;
		do {
			isSuccess = isCreatPlayerListSuccess(Input.getInteger());
		} while (!isSuccess);
	}

	public boolean isCreatPlayerListSuccess(int input) {
		int[] roleNumberArray = RoleNumberTransfer
				.transferInputToRoleNumberArray(input);
		if (RoleNumberTransfer
				.isNumberOfPlayersWithinTheLimits(roleNumberArray)
				&& RoleNumberTransfer
						.isRoleNumbersWithinTheLimits(roleNumberArray)
				&& RoleNumberTransfer.isRoleNumbersNotRepeat(roleNumberArray)) {
			playerList = new PlayerList(roleNumberArray);
			return true;
		} else {
			System.out.println(ERROR_OF_PLAYER_NUMBERS + "\n");
			return false;
		}
	}
}
