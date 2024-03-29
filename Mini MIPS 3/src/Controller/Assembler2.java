package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Implementation.*;
import Implementation.DAO.*;
import Implementation.DatabaseHandler.*;
import Model.*;
import Utilities.Utilities;
import Viewer.CommandLine;
import Viewer.GUI.Home;

public class Assembler2 {

	public void compile() {
		
		//System.out.println("HI!!");
		
		InitDB idb = new InitDB();
		CommandDAO cdao = new CommandDAO();
		Instruction inst = new Instruction();
		
		idb.initialize();		
		InputHandler ih = new InputHandler();
		
		ArrayList<String> output = new ArrayList<String>();
		
		/*
		String path = "src/Command";
		String line = null;
		
		try {
			line = ih.readFile(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		*/
		
		Home h = new Home();
		String line = h.getText();
		System.out.println(line);
		output = ih.processInput(line);
		
		/*
		for(int i=0; i<output.size(); i++) {
			System.out.println(output.get(i));
		}
		*/
		
		ih.storeToDB(output);
		
		CommandHandler ch = new CommandHandler();
		ch.checkCommand();
		ArrayList<String> opcodes = new ArrayList<String>();
 		opcodes = ch.convert();
		
		CommandLine cl = new CommandLine();
		cl.display(opcodes);
		
		String answerHex = "";
		
		for(int i=0; i<opcodes.size(); i++) {
			answerHex += opcodes.get(i) +"\n";
		}
		
		h.setAnswerHex(answerHex);
		
		//for Binary
		
		Utilities u = new Utilities();
		String answerBin = "";
		
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		
		instlist = cdao.getAllCommands();
		String str;
		boolean error = false;
		
		for(int i=0; i<instlist.size(); i++) {
			inst = instlist.get(i);
			if(inst.isError() == true)
				error = true;
		}
		
		if(error == true) {
			h.setAnswerBin(answerBin);
		}
		
		else {		
			for(int i=0; i<opcodes.size(); i++) {
				String temp = opcodes.get(i);
				temp = temp.substring(0, temp.length()-1);
				temp = u.hexToBin(temp);
				temp = u.pad0(temp, 32);
				
				answerBin += temp + "\n";
			}		
			h.setAnswerBin(answerBin);
		}
		
		//String test = Utilities.hexToBin("FAC0");
		//System.out.println(test);
		
		/*
		String command = "J haha";
		Instruction in = new Instruction();
		in.setCommand(command);
		in.setMemloc(0);
		cdao.addCommand(in);
		
		JType jt = new JType();
		jt.setName("J");
		
		JTypeConverter jtc = new JTypeConverter();
		jtc.convert(in, jt);
		*/
		
		
	}
	
}
