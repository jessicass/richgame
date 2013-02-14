package thoughtworks;

import java.util.ArrayList;

public class RoleNumberTransfer {
	public static final int MIN_NUMBER_OF_PLAYERS = 2;
	public static final int MAX_NUMBER_OF_PLAYERS = 4;
	public static final int MIN_ROLE_NUMBER = 1;
	public static final int MAX_ROLE_NUMBER = 4;
	
	public static ArrayList<Integer> getRoleNumberListFromInput(String input){
		ArrayList<Integer> roleNumbers = new ArrayList<Integer>();
		for(int i = 0; i < input.length(); i++){
			roleNumbers.add(Integer.parseInt(String.valueOf(input.charAt(i))));
		}
		return roleNumbers;
	}
	
	public static int[] parseRoleNumberListToArray(ArrayList<Integer> roleNumbers){
		int[] roleNumberArray = new int[roleNumbers.size()];
		for(int i = 0; i < roleNumbers.size(); i++){
			roleNumberArray[i] = roleNumbers.get(i).intValue(); 
		}
		return roleNumberArray;
	}
	
	public static boolean isNumberOfPlayersWithinTheLimits(int[] roleNumberArray){
	    if(roleNumberArray.length < MIN_NUMBER_OF_PLAYERS || 
	    		roleNumberArray.length > MAX_NUMBER_OF_PLAYERS){
				return false;
		}
		return true;
	}
	
	public static boolean isRoleNumbersWithinTheLimits(int[] roleNumberArray){
		for(int i = 0; i < roleNumberArray.length; i++){
			if(roleNumberArray[i] < MIN_ROLE_NUMBER || 
					roleNumberArray[i] > MAX_ROLE_NUMBER){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isRoleNumbersNotRepeat(int[] roleNumberArray){
		for(int i = 0; i < roleNumberArray.length; i++){
			for(int j = i + 1; j < roleNumberArray.length; j++){
				if(roleNumberArray[i] == roleNumberArray[j]){
					return false;
				}
			}
		}
		return true;
	}
}
