package Implementation;

public class Globals {
	
	public static final String instructions[] = new String[16];
	//public static final int opcode;
	
	//public static final instructions[0] = "DADDU";
	
	public Globals() {
		instructions[0] = "DSUBU";
		instructions[1] = "DDIV";
		instructions[2] = "MFHI";
		instructions[3] = "MFLO";
		instructions[4] = "AND";
		instructions[5] = "DSRLV";
		instructions[6] = "SLT";
		
		instructions[7] = "BNEZ";
		instructions[8] = "LW";
		instructions[9] = "LWU";
		instructions[10] = "LD";
		instructions[11] = "SW";
		instructions[12] = "SD";
		instructions[13] = "DADDIU";
		instructions[14] = "ORI";
		
		instructions[15] = "J";		
		/*
		opcode[0] = 0;
		opcode[1] = 0;
		opcode[2] = 0;
		opcode[3] = 0;
		opcode[4] = 0;
		opcode[5] = 0;
		opcode[6] = 0;
		opcode[7] = 5;
		opcode[8] = 35;
		opcode[9] = 39;
		opcode[10] = 55;
		opcode[11] = 43;
		opcode[12] = 63;
		opcode[13] = 25;
		opcode[14] = 13;
		opcode[15] = 2;
		*/
		
	}

}
