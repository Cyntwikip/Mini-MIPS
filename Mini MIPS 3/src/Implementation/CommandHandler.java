package Implementation;

import java.util.ArrayList;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.ITypeDAO;
import Implementation.DAO.JTypeDAO;
import Implementation.DAO.RTypeDAO;
import Model.Instruction;
import Model.JType;

public class CommandHandler {

	public void checkCommand() {
		
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		
		CommandDAO cdao = new CommandDAO();
		
		instlist = cdao.getAllCommands();
		
		Instruction inst;
		
		for(int i=0; i<instlist.size(); i++) {
			inst = instlist.get(i);
			
			if(inst.isError() == true) {
				//Well.. do nothing..
			}			
			else {				
				JTypeDAO jtd = new JTypeDAO();
				ITypeDAO itd = new ITypeDAO();
				RTypeDAO rtd = new RTypeDAO();
				
				String str = inst.getCommand();
				//System.out.println(str);
				
				//for J Type instructions
				if(jtd.searchJType(str)) {
					//do nothing
					//System.out.println("Match!");
				}
				//for I Type instructions
				else if(itd.searchIType(str)) {
					//do nothing
				}
				//for R Type instructions
				else if(rtd.searchRType(str)) {
					//do nothing
					//System.out.println("Halloo");
				}
				//if not an instruction...
				else {
					String error_msg = "No such command found in the database";
					//String error_msg = "LOL!";
					
					inst.setError(true);
					inst.setError_msg(error_msg);
					
					cdao.editCommand(inst);					
				}				
			}//end of big else statement. :P
		}//end of for loop
	}//end of checkCommand()
	
	public ArrayList<String> convert() {
		
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		
		CommandDAO cdao = new CommandDAO();
		
		instlist = cdao.getAllCommands();
		
		Instruction inst = new Instruction();
		
		for(int i=0; i<instlist.size(); i++) {
			inst = instlist.get(i);
			
			if(inst.isError() == true) {
				//output.set(i, inst.getError_msg()); 
			}			
			else {				
				JTypeDAO jtd = new JTypeDAO();
				ITypeDAO itd = new ITypeDAO();
				RTypeDAO rtd = new RTypeDAO();
				
				String str = inst.getCommand();
				
				//for J Type instructions
				if(jtd.searchJType(str)) {
					//System.out.println("J Type");
					JTypeConverter jtc = new JTypeConverter();
					jtc.convert(inst);
				}
				//for I Type instructions
				else if(itd.searchIType(str)) {
					ITypeConverter itc = new ITypeConverter();
					itc.convert(inst);
				}
				//for R Type instructions
				else if(rtd.searchRType(str)) {
					RTypeConverter rtc = new RTypeConverter();
					rtc.convert(inst);
				}
				//if not an instruction... this will never happen if checkCommand() was used before this.
				else {
					output.set(i, "Error? LOL");
				}				
			}//end of big else statement. :P
		}//end of for loop
		
		output = getOpcode();
		
		return output;
	}//end of convert()		
	
	private ArrayList<String> getOpcode() {
		
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		CommandDAO cdao = new CommandDAO();
		
		instlist = cdao.getAllCommands();
		String str;
		
		for(int i=0; i<instlist.size(); i++) {
			Instruction inst = instlist.get(i);
			if(inst.isError() == false)
				str = inst.getOpcode();
			else
				str = inst.getError_msg();
			output.add(str);
			//System.out.println(i);
		}
				
		return output;
	}
	
	
}//end of class
