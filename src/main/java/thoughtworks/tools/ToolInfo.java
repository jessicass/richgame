package thoughtworks.tools;

import java.util.ArrayList;

public class ToolInfo {
	private ArrayList<Tool> toolList= new ArrayList<Tool>();
	
	public ToolInfo(){
		toolList.add(new Block());
		toolList.add(new Robot());
		toolList.add(new Bomb());
	}
	
	public ArrayList<Tool> getTools(){
		return toolList;
	}
	
	public String toolInfoShow(){
		String info = "道具" + "\t" + "编号" + "\t" + "价值（点数）" + "\t" +
                "显示方式" + "\n";
		for(Tool tool: toolList){
			info += tool.getName() + "\t" + tool.getToolNumber() + "\t" +
		        tool.getBuyPoints() + "\t" + tool.getSymbol();
			info += "\n";
		}
		return info;
	}
}
