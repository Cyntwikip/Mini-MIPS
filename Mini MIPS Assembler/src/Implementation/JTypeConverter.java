package Implementation;

import Model.Instruction;
import Utilities.Utilities;

public class JTypeConverter {

	
	private int opcode;
	private int offset;
	
	public String convert(Instruction[] instlist, int index) {
		
		Utilities c = new Utilities();
		LabelHandler lh = new LabelHandler();
		
		String strOpcode;
		String strOffset;
		String output = null;
		String parts[];
		
		Instruction instruction = instlist[index];
		
		//from: http://stackoverflow.com/questions/18288760/string-split-how-do-i-treat-consecutive-delimiters-as-one
		//parts = instruction.getCommand().split(" +");
		
		/*
		if(parts.length != 2) {
			instruction.setError(true);
			instruction.setError_msg("Label must have no spaces");
		}
		
		else if(c.isAlphaNumeric(parts[1]) != true) {
			instruction.setError(true);
			instruction.setError_msg("Label can only be alphanumeric");
		}
		
		else if(parts[0].equals("J")) {
		*/	
			//if(lh.lookForLabel(instlist, parts[1])) {
			if(lh.lookForLabel(instlist, instruction.getCommand())) {
				offset = lh.memlocLabel(instlist, instruction.getCommand());
				opcode = Globals.opcode[15];
				
				strOpcode = c.decimalToBinary(opcode, 6);
				strOffset = c.decimalToBinary(offset, 26);
				output = strOpcode + strOffset;
				
				//binary to hex
				output = c.binaryToHex(output);
				output = output.toUpperCase();
				
				//pads the hex value to make it 32-bit
				//output = c.padMSB(output, 8) + "h";		
				output = c.pad0(output, 8) + "h";
			}
			
			else {
				instruction.setError(true);
				instruction.setError_msg("No such Label found");
			}
		/*
		}
		
		else {
			instruction.setError(true);
			instruction.setError_msg("Wrong syntax");			
		}
		*/
		
		if(instruction.isError()) 
			output = instruction.getError_msg();
		
		return output;
	}
	
}
