package thoughtworks.functionClass;

import java.util.ArrayList;

public class RoleNumberTransfer {
	public static final int MIN_NUMBER_OF_PLAYERS = 2;
	public static final int MAX_NUMBER_OF_PLAYERS = 4;
	public static final int MIN_ROLE_NUMBER = 1;
	public static final int MAX_ROLE_NUMBER = 4;
	
	public static int[] transferRoleNumberListToArray(ArrayList<Integer> roleNumbers){
		int[] roleNumberArray = new int[roleNumbers.size()];
		for(int i = 0; i < roleNumbers.size(); i++){
			roleNumberArray[i] = roleNumbers.get(i).intValue(); 
		}
		return roleNumberArray;
	}
	
	public static boolean isNumberOfPlayersWithinTheLimits(char[] roleNumbers){
	    if(roleNumbers.length < MIN_NUMBER_OF_PLAYERS || 
	    		roleNumbers.length > MAX_NUMBER_OF_PLAYERS){
				return false;
		}
		return true;
	}
	
	public static boolean isRoleNumbersWithinTheLimits(char[] roleNumbers){
		for(int i = 0; i < roleNumbers.length; i++){
			if(Integer.valueOf(String.valueOf(roleNumbers[i])) < MIN_ROLE_NUMBER || 
					Integer.valueOf(String.valueOf(roleNumbers[i])) > MAX_ROLE_NUMBER){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isRoleNumbersNotRepeat(char[] roleNumbers){
		for(int i = 0; i < roleNumbers.length; i++){
			for(int j = i + 1; j < roleNumbers.length; j++){
				if(roleNumbers[i] == roleNumbers[j]){
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] transferInputToRoleNumberArray(String input){
		ArrayList<Integer> roleNumbers = new ArrayList<Integer>();
		for(int i = 0; i < input.length(); i++){
			roleNumbers.add(Integer.valueOf(String.valueOf(input.charAt(i))));
		}
		return transferRoleNumberListToArray(roleNumbers);
	}
}
