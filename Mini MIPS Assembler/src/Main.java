

import Controller.Assembler;
import Controller.Assembler2;
import Implementation.DatabaseHandler;


public class Main {
	
	public static void main(String[] args) {
		
				
		//Assembler a = new Assembler();
		Assembler2 a = new Assembler2();
		a.compile();
		
		/*
		String filename = "src/Database";
		String text = "Hello\n";
		DatabaseHandler db = new DatabaseHandler(filename);
		db.storeData(text);
		db.emptyFile();
		*/
	}
	

}
