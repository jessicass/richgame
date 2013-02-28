package thoughtworks;

import java.util.ArrayList;

import thoughtworks.command.*;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.*;
import thoughtworks.publicPlace.Hospital;

public class Game {
	public static final String HINT_OF_START = "��������Ϸ��ʼָ�";
	public static final String ERROR_OF_START = "ָ�����������������Ϸ��ʼָ�";
	public static final String HINT_OF_PLAYER_CHOICE = "��ѡ��2~4λ"
			+ "���ظ���ң������ż��ɡ���1.Ǯ���ˣ�2.��������3.��С����4.�𱴱�����";
	public static final String HINT_OF_PLAYER_INITIAL = "��������ҳ�ʼ"
			+ "�ʽ𣬷�Χ" + Player.INITIAL_MIN_FUNDS + "~" + Player.INITIAL_MAX_FUNDS +
			"��Ĭ��" + Player.INITIAL_FUNDS + "����";
	public static final String ERROR_OF_PLAYER_NUMBERS = "��ұ������������������룡";

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
	
	public Player getTheOwnerOfSpace(int spacePosition){
		return playerList.getTheOwnerOfSpace(spacePosition);
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
					System.out.println("��ϲ���" + getWinner().getPlayerName() + "��ʤ��");
					return;
			    }
				
				player.decreasHospitalizedTimes();
				player.decreasLuckyTimes();
				player.decreasTrappedInPrisonTimes();
				
				if (player.isBombed() || player.isTrappedInPrison() ||
						player.isBankrupt()) {
					continue;
				}
				
				while (true) {
					System.out.println(player.getPlayerName() + ">");
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
				if (player.isOwnerOfSpace(player.getPosition())) {
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
		while (!space.isSafeForPlayerPassOnOtherSpace(player, this)) {
			player.testBankrupt(space.getPassToll());
			if (player.isBankrupt()) {
				System.out.println("���" + player.getPlayerName() + "�Ʋ�");
				playerList.getPlayers().remove(player);
			} else {
				System.out.println("������Ҫ���۵ķ�����ţ�");
				commandManager.sellFixedAssetsWithCommand(Input.getString(), player, this);
			}
		}
	}

	public void updatePlayerPosition(Player player, int step) {
		int position = player.getPosition();
		for (int i = 0; i < step; i++) {
			position = PositionUpdate.getNextPosition(position);
			if (map.getMapObjectWithIndex(position).hasBlock()) {
				System.out.println("�ǳ����ң���·�����أ�");
				player.updatePosition(position);
				map.getMapObjectWithIndex(position).resetBlock();
				return;
			}
			if (map.getMapObjectWithIndex(position).hasBomb()) {
				System.out.println("�ǳ����ң���ը��ը�ˣ�");
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
			System.out.println("���������ɹ���");
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
		while(true){
			int input = Input.getInteger();
			if(Input.isIntegerInArea(input, Player.INITIAL_MIN_FUNDS, 
					Player.INITIAL_MAX_FUNDS)){
				Player.INITIAL_FUNDS = input;
				break;
			}
		}
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
