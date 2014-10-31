package Implementation;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Instruction;
import Utilities.Utilities;

public class LabelHandler {
	
	private Instruction[] inst;
	private int memcounter = 0;
	//ArrayList<Instruction> inst = new ArrayList<Instruction>();
	//ArrayList<String> parts = new ArrayList<String>();
	
	
	public Instruction[] splitLabel(String[] text) {
		//ArrayList<Instruction> inst = new ArrayList<Instruction>();		
		//Conversion c = new Conversion();
		
		inst = new Instruction[text.length];
		
		for(int i=0; i<text.length; i++) {
			//from: http://stackoverflow.com/questions/4208655/empty-an-array-in-java-processing
			//Arrays.fill(parts, null);
			//parts.clear();
			
			//parts = text[i].split(":");			
			//from: http://stackoverflow.com/questions/10530353/convert-string-array-to-arraylist
			//parts = (ArrayList<String>) Arrays.asList(text[i].split(":"));
			
			//inst.add(storeInst(text, i));
			//storeInst(text, i);
			
			inst[i] = storeInst(text, i);
			
		}
		
		return inst;
	}
	
	private Instruction storeInst(String[] text, int i) {
		Utilities c = new Utilities();
		String[] parts;
		//String str;
		Instruction in = new Instruction();
		
		parts = text[i].split(":");			
		//from: http://stackoverflow.com/questions/10530353/convert-string-array-to-arraylist
		//parts = (ArrayList<String>) Arrays.asList(text[i].split(":"));
		
		//System.out.println(parts[0]);
		//System.out.println(parts.get(0));
		
		
		//if(parts.length == 2 && c.isAlphaNumeric(parts[0]) == true ) {
		if(parts.length == 2) {
			parts[0] = parts[0].trim();
			if(c.isAlphaNumeric(parts[0])) {
				in.setLabel(parts[0]);
				parts[1] = parts[1].trim();
				in.setCommand(parts[1]);
				in.setMemloc(memcounter);
				memcounter++;
			}
			else
				in.setError(true);
		}
		
		else if(parts.length == 1) {		
			//from: http://stackoverflow.com/questions/3247067/how-to-check-that-java-string-is-not-all-whitespaces
			//if (string.trim().length() > 0)
			
			if(parts[0].trim().length() > 0) {
				parts[0] = parts[0].trim();			
				in.setCommand(parts[0]);
				in.setMemloc(memcounter);
				memcounter++;
			}
			else 
				in.setEmpty(true);			
		}
		
		else {
			in.setError(true);	
		}		
		
		return in;
	}
	
	public boolean lookForLabel(Instruction[] instlist, String label) {
		
		for(int i=0; i<instlist.length; i++) {
			if(instlist[i].getLabel().equals(label))
				return true;
		}		
		return false;
	}
	
	public int memlocLabel(Instruction[] instlist, String label) {
		
		for(int i=0; i<instlist.length; i++) {
			if(instlist[i].getLabel().equals(label))
				return instlist[i].getMemloc();
		}	
		return -1;
	}
	

}
