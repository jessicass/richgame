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
		System.out.println("������Ҫ���۵ķ�����ţ�");
		console.setForegroundColor(ConsoleForegroundColor.LIGHT_GREEN);
		System.out.println("������Ҫ���۵ķ�����ţ�");
        console.resetColors();
    }
}
