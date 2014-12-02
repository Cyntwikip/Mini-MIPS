package Controller;

import java.util.ArrayList;

import Implementation.DAO.CommandDAO;
import Implementation.DatabaseHandler.InitDB;
import Model.Instruction;
import Viewer.CommandLine;

public class Assembler {

	public void compile() {
		
		InitDB idb = new InitDB();
		CommandDAO cdao = new CommandDAO();
		Instruction inst = new Instruction();
		
		idb.initialize();
		
		//int id;
		String command = "DADDU R0, R0, R0";
		int memloc = 0;
		
		inst.setCommand(command);
		inst.setMemloc(memloc);
		
		cdao.addCommand(inst);
		
		
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		instlist = null;
		instlist = cdao.getAllCommands();
		
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i=0; i<instlist.size(); i++) {
			//id = instlist.get(i).get
			command = instlist.get(i).getCommand();
			memloc = instlist.get(i).getMemloc();
			
			String str = command + "|" + memloc;
			
			output.add(str);
		}
		
		CommandLine cl = new CommandLine();
		
		cl.display(output);
		
		
	}
	
	
}
