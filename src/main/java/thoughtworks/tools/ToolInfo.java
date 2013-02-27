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
		String info = "����" + "\t" + "���" + "\t" + "��ֵ��������" + "\t" +
	                      "��ʾ��ʽ" + "\n";
		for(Tool tool: toolList){
			info += tool.getName() + "\t" + tool.getToolNumber() + "\t" +
		        tool.getBuyPoints() + "\t" + tool.getSymbol();
			info += "\n";
		}
		return info;
	}
}
