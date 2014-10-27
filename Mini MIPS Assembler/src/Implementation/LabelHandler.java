package Implementation;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Instruction;

public class LabelHandler {
	
	Instruction[] inst;
	String[] parts;
	
	public Instruction[] splitLabel(String[] text) {
		//ArrayList<Instruction> inst = new ArrayList<Instruction>();
		
		for(int i=0; i<text.length; i++) {
			//from: http://stackoverflow.com/questions/4208655/empty-an-array-in-java-processing
			Arrays.fill(parts, null);
			parts = text[i].split(":");
			
			if(parts.length>28) {
				inst[i].setError(true);				
			}
			
		}
		
		return null;
	}

}
