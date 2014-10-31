package Controller;

import Implementation.CommandChecker;
import Implementation.Globals;
import Implementation.InputHandler;
import Implementation.LabelHandler;
import Implementation.OpcodeConverter;
import Model.Instruction;
import Viewer.CommandLine;

public class Assembler2 {
	
public void compile() {
		
		new Globals();
		InputHandler ih;
		CommandChecker cc;
		//OpcodeConverter oc;
		//boolean done;
		//int i;
		String[] input;
		String[] output;
		
		//oc = new OpcodeConverter();
		cc = new CommandChecker();
		ih = new InputHandler();
		input = ih.getInput("src/Command");
		output = input;	//so that output will have contents...
		
		LabelHandler lh = new LabelHandler();
		Instruction[] instlist;
		
		instlist = lh.splitLabel(input);
		
		/*
		for(int i=0; i<instlist.length; i++) {
			//if(inst[i].getLabel() != null)
			if(instlist[i].getCommand() != null)
				System.out.println(instlist[i].getCommand());
				//System.out.println(inst[i].isEmpty());			
		}
		*/
		
		for(int index=0; index<input.length; index++) {
			output[index] = cc.convert(instlist, index);
		}
		
		/*
		for(int index=0; index<input.length; index++) {
			output[index] = oc.convert(input[index]);
		}
		*/
		
		CommandLine cl = new CommandLine();
		cl.display(output);
		
	}
	
}
