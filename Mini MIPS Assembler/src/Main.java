

import Controller.Assembler;
import Implementation.DatabaseHandler;


public class Main {
	
	public static void main(String[] args) {
		
				
		Assembler a = new Assembler();
		a.compile();
		
		String filename = "src/Database";
		String text = "Hello\n";
		DatabaseHandler db = new DatabaseHandler(filename);
		db.storeData(text);
		db.emptyFile();
		
	}
	

}
