package thoughtworks;

import com.meyling.console.*;

/**
 * Test Jcons!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final Console console = ConsoleFactory.getConsole();
        console.resetColors();
		console.setForegroundColor(ConsoleForegroundColor.LIGHT_RED);
		System.out.println("请输入要出售的房产编号：");
		console.setForegroundColor(ConsoleForegroundColor.LIGHT_GREEN);
		System.out.println("请输入要出售的房产编号：");
        console.resetColors();
    }
}
