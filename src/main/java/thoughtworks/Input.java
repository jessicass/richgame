package thoughtworks;

import java.util.Random;
import java.util.Scanner;

public class Input {
	public static String getString(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		scanner.close();
		return input;
	}
	
	public static char getChar(){
		String input = Input.getString();
		return (char)(input.charAt(0));
	}
	
	public static int getInteger() {
		while (true) {
			try {
				int input = Integer.parseInt(getString());
				return input;
			} catch (Exception e) {
				System.out.println("请输入数字");
			}
		}
	}

	public static boolean isIntegerInArea(int number, int max, int min) {
		if(number > max || number < min){
			System.out.println("请输入" + min + "~" + max + "以内的数字");
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isInputCommandEquals(String command){
		if(getString().matches(command)){
			return true;
	    }
	    else{
	    	return false;
	    }
	}
	
	public static boolean isInputAnInteger(String input){
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			System.out.println("请输入数字");
			return false;
		}
	}
	
	public static boolean isInputAnIntegerInArea(String input, int max, int min){
		if(isInputAnInteger(input)){
			if(isIntegerInArea(Integer.parseInt(input), max, min)){
				return true;
			}
		}
		return false;
	}
	
	public static int throwDice() {
		Random random = new Random();
		return random.nextInt(5) + 1;
	}
}
