package Controller;

import Implementation.Globals;
import Implementation.InputHandler;
import Implementation.OpcodeConverter;
import Viewer.CommandLine;

public class Assembler {

	public void compile() {
		
		new Globals();
		InputHandler ih;
		OpcodeConverter oc;
		//boolean done;
		//int i;
		String[] input;
		String[] output;
		
		oc = new OpcodeConverter();
		ih = new InputHandler();
		input = ih.getInput("src/Command");
		output = input;	//so that output will have contents...
		
		
		
		
		
		for(int index=0; index<input.length; index++) {
			output[index] = oc.convert(input[index]);
		}
		
		CommandLine cl = new CommandLine();
		cl.display(output);
		
	}
	
}
