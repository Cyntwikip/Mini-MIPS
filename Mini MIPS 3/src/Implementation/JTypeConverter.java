package Implementation;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.JTypeDAO;
import Implementation.DAO.LabelDAO;
import Model.Instruction;
import Model.JType;
import Utilities.Utilities;

public class JTypeConverter {

	public void convert(Instruction inst) {
		
		JTypeDAO jtd = new JTypeDAO();
		String str = inst.getCommand();
		JType jt;					
		jt = jtd.getJType(str);
		
		String name = jt.getName();
		String cmd = inst.getCommand();
		String[] parts;
		
		parts = cmd.split(" +", 2);//splits the string into two.
		
		if(parts.length != 2) {
			String error_msg = "There must be a label";
			
			inst.setError(true);
			inst.setError_msg(error_msg);
			
			CommandDAO cdao = new CommandDAO();
			cdao.editCommand(inst);
			
			return;
		}
		
		else {
			String label = parts[1];			
			Instruction dummy = new Instruction();
			dummy.setLabel(label);
			LabelDAO ldao = new LabelDAO();
			
			if(ldao.searchLabel(dummy) == false) {
				String error_msg = "No such label found in the database";
				
				inst.setError(true);
				inst.setError_msg(error_msg);
				
				CommandDAO cdao = new CommandDAO();
				cdao.editCommand(inst);
				
				return;				
			}
			
			//Checkpoint! passed the label test.	
		
			String strOpcode;
			String strOffset;
			String output;
			int opcode = 0;
			int offset = 0;
			Utilities u = new Utilities();
			
			switch(name) {
		
				case "J": 
					//System.out.println("This is J!");					
					Instruction in = ldao.getLabel(dummy);					
					
					opcode = jt.getOpcode();
					offset = in.getMemloc();					
					
					break;			
			}
			
			strOpcode = u.decimalToBinary(opcode, 6);
			strOffset = u.decimalToBinary(offset, 26);
			output = strOpcode + strOffset;
			
			//binary to hex
			output = u.binaryToHex(output);
			output = output.toUpperCase();
			
			//pads the hex value to make it 32-bit
			//output = c.padMSB(output, 8) + "h";		
			output = u.pad0(output, 8) + "h";
			
			inst.setOpcode(output);			
			CommandDAO cdao = new CommandDAO();
			cdao.editCommand(inst);
			
		}//end of big else statement
		
		return;
	}//end of convert()
	
}
