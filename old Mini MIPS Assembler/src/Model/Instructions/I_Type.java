package Model.Instructions;

import Implementation.Globals;
import Utilities.Conversion;

public class I_Type implements InstructionFormat {

	private int opcode;
	private int rs;
	private int rt;
	private int immediate;
	private boolean syntax_error = false;
	private String output;
	
	@Override
	public String convert(String instruction) {
		// TODO Auto-generated method stub
		
		boolean done = false;
		int i=7;
		do {			
			//if(instruction.equals(Globals.instructions[i])) {
			//string.indexOf("substring")>0 -> if string contains substring...
			if(instruction.indexOf(Globals.instructions[i])>=0) {	
				i--;
				done = true;
			}
			i++;			
		} while(i<=14 && done == false);
		
		split(instruction, i);
		
		if(syntax_error == true)
			return output;
		
		String strOpcode;
		String strRS;
		String strRT;
		String strImmediate;
		
		Conversion c = new Conversion();
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strRS = c.decimalToBinary(rs, 5);
		strRT = c.decimalToBinary(rt, 5);
		strImmediate = c.decimalToBinary(immediate, 16);
		output = strOpcode + strRS + strRT + strImmediate;
		
		//binary to hex
		output = c.binaryToHex(output);
				
		//pads the hex value to make it 32-bit
		output = c.padMSB(output, 8) + "h";		
	
		return output;
	}
	
	public void split(String instruction, int type) {
		//from: http://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java 
		//from: http://stackoverflow.com/questions/12597458/how-can-i-split-a-string-by-two-delimiters
		
		String[] parts;
		
		switch(type) {
			//BNEZ
			case 7:
				parts = instruction.split(" |\t|,|\\(");
				opcode = 5;
				rs = registerNumber(parts[1]);
				//rs = 31;
				rt = 0;
				immediate = 0;
				break;
			default:
				opcode = 0;
				rs = 0;
				rt = 0;
				immediate = 0;
				break;
				
		}
		
		
		//String[] parts = instruction.split(" |,");
		
		//rs = 
		
	}
	
	public int registerNumber(String reg) {
		Conversion c = new Conversion();
		
		reg = c.removeSpaces(reg);
		
		if(reg.startsWith("R")) {
			reg = reg.replace("R", "");
			//System.out.println(reg);
			if (reg.matches("[0-9]+") && reg.length() >= 1) {
				//from: http://stackoverflow.com/questions/5585779/how-to-convert-string-to-int-in-java
				return Integer.parseInt(reg);
			}
			else
				syntax_error = true;
		}
		else
			syntax_error = true;
		
		output = "Syntax Error";
		return -1;
	}
	
	public int getOffset(String offset) {
		Conversion c = new Conversion();
		
		offset = c.removeSpaces(offset);
		
		if(offset.endsWith(")")) {
			offset = offset.replace(")", "");
			//System.out.println(offset);
			if (offset.matches("[0-9]+") && offset.length() >= 1) {
				//from: http://stackoverflow.com/questions/5585779/how-to-convert-string-to-int-in-java
				return Integer.parseInt(offset);
			}
			else
				syntax_error = true;
		}
		else
			syntax_error = true;
		
		output = "Syntax Error";		
		
		return -1;
	}

}
