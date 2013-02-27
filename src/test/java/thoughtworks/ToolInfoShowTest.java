package thoughtworks;

import org.junit.Test;

import thoughtworks.tools.ToolInfo;

public class ToolInfoShowTest {
	@Test
	public void toolInoShow(){
		System.out.println((new ToolInfo()).toolInfoShow());
	}
}
