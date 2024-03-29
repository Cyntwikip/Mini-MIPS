/**
 * 
 */
package Model.Instructions;

import Utilities.Utilities;

/**
 * @author Cyntwikip
 *
 */
public class J_Type implements InstructionFormat {

	/* (non-Javadoc)
	 * @see Model.InstructionFormat#convert(java.lang.String)
	 */
	
	private int opcode = 2;
	private int offset = 0;
	
	@Override
	public String convert(String instruction) {
		// TODO Auto-generated method stub
		
		Utilities c = new Utilities();
		
		String strOpcode;
		String strOffset;
		String output;
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strOffset = c.decimalToBinary(offset, 26);
		output = strOpcode + strOffset;
		
		//binary to hex
		output = c.binaryToHex(output);
		
		//pads the hex value to make it 32-bit
		output = c.padMSB(output, 8) + "h";		
		
		return output;
	}

}
