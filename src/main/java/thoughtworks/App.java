package thoughtworks;

import java.awt.Color;

import enigma.console.*;
import enigma.core.*;

/**
 *Hello,World!
 *
 */
public class App 
{
    public static void main( String[] args )
    {       	
    	TextAttributes attrs = new TextAttributes(Color.BLUE, Color.WHITE);
    	console.setTextAttributes(attrs);
    	System.out.println("Hello,World");
    }
    private static final Console console=Enigma.getConsole("");
}
