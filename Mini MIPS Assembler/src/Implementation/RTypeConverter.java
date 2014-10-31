package Implementation;

import Model.Instruction;
import Utilities.Utilities;

public class RTypeConverter {
	
	private int reglimit = 32;
	private int opcode = 0;
	private int rs;
	private int rt;
	private int rd;
	private int zeros = 0;
	private int func;
	private boolean syntax_error = false;
	private String output;
	
	public String convert(Instruction[] instlist, int index, int type) {
		
		String[] parts;
		
		Instruction inst = instlist[index];  
		
		switch(type) {
		
			//DSUBU
			case 0:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				rd = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				rt = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				func = 47;				
				
				break;
			
			//DDIV
			case 1:
				parts = inst.getCommand().split(",");
				if(parts.length != 2)
					return "Wrong Syntax";
				
				rd = 0;
				
				rs = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rt = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				func = 30;				
				
				break;
			
			//MFHI
			case 2:								
				rs = 0;
				rt = 0;
				
				rd = registerNumber(inst.getCommand());
				if(syntax_error)
					return output;
				
				func = 16;		
				
				break;
				
			//MFLO
			case 3:
				rs = 0;
				rt = 0;
				
				rd = registerNumber(inst.getCommand());
				if(syntax_error)
					return output;
				
				func = 18;		
				
				break;				
				
			//AND
			case 4:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				rd = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				rt = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				func = 36;				
				
				break;			
				
			//DSRLV
			case 5:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				rd = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				rt = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				func = 22;				
				
				break;
						
			//SLT
			case 6:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				rd = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				rt = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				func = 42;				
				
				break;		
		}
		
		
		String strOpcode;
		String strRS;
		String strRT;
		String strRD;
		String strZeros;
		String strFunc;
		
		Utilities c = new Utilities();
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strRS = c.decimalToBinary(rs, 5);
		strRT = c.decimalToBinary(rt, 5);
		strRD = c.decimalToBinary(rd, 5);
		strZeros = c.decimalToBinary(zeros, 5);
		strFunc = c.decimalToBinary(func, 6);
		output = strOpcode + strRS + strRT + strRD + strZeros + strFunc;
		
		//binary to hex
		output = c.binaryToHex(output);
		output = output.toUpperCase();
				
		//pads the hex value to make it 32-bit
		//output = c.padMSB(output, 8) + "h";		
		output = c.pad0(output, 8) + "h";
		
		return output;
		
		//return "";
	}	
	
	public int registerNumber(String reg) {
		Utilities c = new Utilities();
		
		//reg = c.removeSpaces(reg);
		reg = reg.trim();
		
		if(reg.startsWith("R")) {
			reg = reg.replace("R", "");
			//System.out.println(reg);
			if (reg.matches("[0-9]+") && reg.length() >= 1) {
				//from: http://stackoverflow.com/questions/5585779/how-to-convert-string-to-int-in-java
				int regnum =  Integer.parseInt(reg);
				if(regnum < reglimit)
					return regnum;
				else {
					output = "Register number is too large";
					syntax_error = true;
				}
			}
			else {
				output = "No such register";
				syntax_error = true;
			}
		}
		else {
			syntax_error = true;
			output = "Syntax Error";
		}
		return -1;
	}
	
}
