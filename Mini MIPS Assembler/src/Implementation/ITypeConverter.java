package Implementation;

import Model.Instruction;
import Utilities.Utilities;

public class ITypeConverter {
	
	private int reglimit = 32;
	private int opcode;
	private int rs;
	private int rt;
	private int immediate;
	private boolean syntax_error = false;
	private String output;
	
	public String convert(Instruction[] instlist, int index, int type) {
		
		String[] parts;
		
		Instruction inst = instlist[index];  
		LabelHandler lh = new LabelHandler();
		
		switch(type) {
		
			//BNEZ
			case 7:
				parts = inst.getCommand().split(",");
				if(parts.length != 2)
					return "Wrong Syntax";
								
				opcode = 5;
				
				rs = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rt = 0;
				
				immediate = getOffset(instlist, parts[1]);	
				if(syntax_error)
					return output;
				
				break;
			
			//LW
			case 8:
				parts = inst.getCommand().split(",|(");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 35;			
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				if(lh.lookForLabel(instlist, parts[1])) {
					immediate = lh.memlocLabel(instlist, parts[1]);					
				}
				else 
					return "No such Label found";
				
				break;
			
			//LWU
			case 9:								
				parts = inst.getCommand().split(",|(");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 39;			
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				if(lh.lookForLabel(instlist, parts[1])) {
					immediate = lh.memlocLabel(instlist, parts[1]);					
				}
				else 
					return "No such Label found";
				
				break;
				
			//LD
			case 10:
				parts = inst.getCommand().split(",|(");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 55;			
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				if(lh.lookForLabel(instlist, parts[1])) {
					immediate = lh.memlocLabel(instlist, parts[1]);					
				}
				else 
					return "No such Label found";
				
				break;				
				
			//SW
			case 11:
				parts = inst.getCommand().split(",|(");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 43;			
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				if(lh.lookForLabel(instlist, parts[1])) {
					immediate = lh.memlocLabel(instlist, parts[1]);					
				}
				else 
					return "No such Label found";
				
				break;			
				
			//SD
			case 12:
				parts = inst.getCommand().split(",|(");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 63;			
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[2]);
				if(syntax_error)
					return output;
				
				if(lh.lookForLabel(instlist, parts[1])) {
					immediate = lh.memlocLabel(instlist, parts[1]);					
				}
				else 
					return "No such Label found";
				
				break;
						
			//DADDIU
			case 13:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 25;
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				immediate = getImmediate(parts[2]);
				if(syntax_error)
					return output;		
				
				break;		
				
			//ORI
			case 14:
				parts = inst.getCommand().split(",");
				if(parts.length != 3)
					return "Wrong Syntax";
				
				opcode = 13;
				
				rt = registerNumber(parts[0]);
				if(syntax_error)
					return output;
				
				rs = registerNumber(parts[1]);
				if(syntax_error)
					return output;
				
				immediate = getImmediate(parts[2]);
				if(syntax_error)
					return output;		
				
				break;	
				
			default:
				
				break;
		}
		
		
		String strOpcode;
		String strRS;
		String strRT;
		String strImmediate;
		
		Utilities c = new Utilities();
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strRS = c.decimalToBinary(rs, 5);
		strRT = c.decimalToBinary(rt, 5);
		strImmediate = c.decimalToBinary(immediate, 16);
		output = strOpcode + strRS + strRT + strImmediate;
		
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
		
		reg = c.removeSpaces(reg);
		
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
	
	public int getOffset(Instruction[] instlist, String offset) {
		Utilities c = new Utilities();
		LabelHandler lh = new LabelHandler();
		
		//offset = c.removeSpaces(offset);		
		offset = offset.trim();
		
		//System.out.println(offset);
		if(lh.lookForLabel(instlist, offset)) {
			return lh.memlocLabel(instlist, offset);			
		}
		
		else {
			syntax_error = true;
			output = "No such Label found";
		}					
		
		return -1;
	}	
	
	public int getOffsetReg(Instruction[] instlist, String offset) {
		Utilities c = new Utilities();
		LabelHandler lh = new LabelHandler();
		
		//offset = c.removeSpaces(offset);
		offset = offset.trim();
		
		if(offset.endsWith(")")) {
			offset = offset.replace(")", "");
			//System.out.println(offset);
			if(lh.lookForLabel(instlist, offset)) {
				return lh.memlocLabel(instlist, offset);			
			}
			else {
				syntax_error = true;
				output = "No such Label found";
			}
		}
		else
			syntax_error = true;
		
		output = "Syntax Error";		
		
		return -1;
	}	
	
	public int getImmediate(String num) {
		
		num = num.trim();
		
		if(num.startsWith("#")) {
			num = num.replace("#", "");
			if(num.matches("^[0-9]+$"))
				return Integer.parseInt(num);
			else {
				syntax_error = true;
				output = "Wrong immediate number";
			}
		}
		
		else {
			syntax_error = true;
			output = "Immediate value must start with #";
		}
		
		return -1;
	}
	
}
