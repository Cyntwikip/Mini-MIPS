package Implementation;

import java.io.*;
import java.util.*;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.LabelDAO;
import Model.Instruction;
import Utilities.Utilities;

public class InputHandler {

	public ArrayList<String> processInput(String line) {
		String[] str;
		//String line = null;
		
		//System.out.println(line);
		
		line = line.toUpperCase();
		
		str = stringSeparator(line);
		str = removeComments(str);
		str = removeEmptyLines(str);
		
		ArrayList<String> aList = new ArrayList<String>();
		
		aList = stringToArrayList(str);
		
		return aList;
	}
	
	public void storeToDB(ArrayList<String> strList) {
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		CommandDAO cdao = new CommandDAO();
		LabelDAO ldao = new LabelDAO();
		Utilities u = new Utilities();
		
		instlist = splitData(strList);
		
		/*
		//store Commands
		for(int i=0; i<instlist.size(); i++) {
			Instruction inst = instlist.get(i);
			cdao.addCommand(inst);			
		}
		
		//store Labels
		for(int i=0; i<instlist.size(); i++) {
			Instruction inst = instlist.get(i);
			String label = inst.getLabel();
			//System.out.println(label);
			if(u.isWhitespace(label) == false)
				ldao.addLabel(inst);			
		}
		*/		
		
		for(int i=0; i<instlist.size(); i++) {
			Instruction inst = instlist.get(i);
			String label = inst.getLabel();
			if(u.isWhitespace(label) == false) {	//means there is a label
				if(ldao.searchLabel(inst) == false)	{	//means no duplicate label
					ldao.addLabel(inst);	
					cdao.addCommand(inst);
				}
				else {
					String error_msg = "Duplicate Labels";
					inst.setError(true);
					inst.setError_msg(error_msg);
					cdao.addCommand(inst);
				}
			}
			else {	//no label
				cdao.addCommand(inst);
			}			
		}
		
		
	}
	
	public ArrayList<Instruction> splitData(ArrayList<String> strList) {
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		Utilities u = new Utilities();
		String str;
		String[] parts;
		int memcounter = 0;
		
		for(int i=0; i<strList.size(); i++) {
			Instruction in = new Instruction();
			str = strList.get(i);
			
			parts = str.split(":");
			
			if(parts.length == 2) {
				parts[0] = parts[0].trim();
				if(u.isAlphaNumeric(parts[0])) {
					in.setLabel(parts[0]);
					parts[1] = parts[1].trim();
					in.setCommand(parts[1]);
					in.setMemloc(memcounter);
					memcounter+=4;
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
					memcounter+=4;
				}
				else 
					in.setEmpty(true);			
			}			
			else {
				in.setError(true);	
			}			
			instlist.add(in);
		}//end of for loop
		
		return instlist;
	}//end of splitData()

	//from: http://stackoverflow.com/questions/16027229/reading-from-a-text-file-and-storing-in-a-string
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public String[] stringSeparator(String line) {		
		String[] str;
		
		str = line.split("\n+", 0);
		
		return str;
	}
	
	public String[] removeEmptyLines(String[] str) {
		//from: http://stackoverflow.com/questions/4150233/remove-null-value-from-string-array-in-java
		
		//String[] firstArray = {"test1","","test2","test4",""};
		ArrayList<String> list = new ArrayList<String>();
		for (String s : str) {
		    if (Utilities.isWhitespace(s) == false) {
		        list.add(s);
		        //System.out.println("Not empty!");
		    }
		}
		
		return arrayListToString(list);
	}
	
	public String[] arrayListToString(ArrayList<String> stockList) {
		//from: http://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string
		
		String[] stockArr = new String[stockList.size()];
		stockArr = stockList.toArray(stockArr);
		
		return stockArr;	
	}
	
	public ArrayList<String> stringToArrayList(String[] strList) {
		
		ArrayList<String> aList = new ArrayList<String>(Arrays.asList(strList));
		
		return aList;
	}
	
	public String[] removeComments(String[] str) {
		//from: http://www.java-examples.com/java-string-arraylist-example
		String[] parts;
		
		for(int i=0; i<str.length; i++) {
			parts = str[i].split(";");
			str[i] = parts[0];
		}
		
		return str;
	}
	
		
	
	
	
}
