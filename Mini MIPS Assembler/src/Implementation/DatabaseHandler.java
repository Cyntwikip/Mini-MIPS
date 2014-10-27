package Implementation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseHandler {

	private String filename;
	
	public DatabaseHandler(String filename) {
		this.filename = filename;		
	}
	
	public void storeData(String text) {
		//from: http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		try
		{
		    //String filename= "MyFile.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    //fw.write("add a line\n");//appends the string to the file
		    fw.write(text);
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public void emptyFile() {
		//from: http://stackoverflow.com/questions/6994518/how-to-delete-the-content-of-text-file-without-deleting-itself
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename);
			writer.print("");
			writer.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
