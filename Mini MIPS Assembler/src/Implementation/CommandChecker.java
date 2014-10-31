package Implementation;

import Model.Instruction;
import Model.Instructions.I_Type;
import Model.Instructions.J_Type;

public class CommandChecker {

	private String output = ""; 
	
	public String convert(Instruction[] instlist, int index) {
		
		int i=0;
		boolean done = false;
		
		//Instruction instruction = instlist[index];
		
		//String cmd = instruction.getCommand();
		String cmd = instlist[index].getCommand();
		String[] parts;

		if(isWhitespace(cmd))
			return "";
		
		//this part is for comparing the instruction set used
		do {			
			//if(instruction.equals(Globals.instructions[i])) {
			//string.indexOf("substring")>0 -> if string contains substring...
			
			parts = cmd.split(" +", 2);
			
			//if(cmd.indexOf(Globals.instructions[i])>=0) {	
			if(parts[0].equals(Globals.instructions[i])) {
				i--;
				done = true;		
				instlist[index].setCommand(parts[1]);
			}
			
			i++;			
		} while(i<(Globals.instructions).length && done == false);
		//end of comparison
		
		switch(i) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				//output = "R Type";
				RTypeConverter rT = new RTypeConverter();
				output = rT.convert(instlist, index, i);
				break;
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				//output = "I Type";
				ITypeConverter iT = new ITypeConverter();
				output = iT.convert(instlist, index, i);
				break;
			case 15:
				//output = jType(instruction);
				JTypeConverter jT = new JTypeConverter();
				output = jT.convert(instlist, index);
				break;
			default:
				output = "Invalid";
		
		}
				
		return output;
	}
		
	//from: http://www.java2s.com/Code/Java/Data-Type/ChecksiftheStringcontainsonlywhitespace.htm
	public static boolean isWhitespace(String str) {
	      if (str == null) {
	          return false;
	      }
	      int sz = str.length();
	      for (int i = 0; i < sz; i++) {
	          if ((Character.isWhitespace(str.charAt(i)) == false)) {
	              return false;
	          }
	      }
	      return true;
	}	

}
