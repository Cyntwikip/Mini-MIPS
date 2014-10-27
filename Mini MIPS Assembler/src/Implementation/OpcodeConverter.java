package Implementation;

import Model.Instructions.I_Type;
import Model.Instructions.J_Type;

public class OpcodeConverter {
	private String output = null; 
	private int memloc = -1;
	
	public String convert(String instruction) {
		
		int i=0;
		boolean done = false;

		if(isWhitespace(instruction))
			return "";
		
		//this part is for comparing the instruction set used
		do {			
			//if(instruction.equals(Globals.instructions[i])) {
			//string.indexOf("substring")>0 -> if string contains substring...
			if(instruction.indexOf(Globals.instructions[i])>=0) {	
				i--;
				done = true;
				memloc++;
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
				output = rType(instruction);
				break;
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				output = iType(instruction);
				break;
			case 15:
				output = jType(instruction);
				break;
			default:
				output = "Invalid";
		
		}
		
		
		return output;
	}
	
	public String rType(String instruction) {
		
		output = "R Type";
		return output;
	}
	
	public String iType(String instruction) {
		I_Type i = new I_Type();
		output = i.convert(instruction);
		//output = "I Type";
		return output;
	}
	
	public String jType(String instruction) {
		J_Type j = new J_Type();
		output = j.convert(instruction);
		//output = "J Type";
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
