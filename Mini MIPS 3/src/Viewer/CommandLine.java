package Viewer;

import java.util.ArrayList;

public class CommandLine {

	public void display(ArrayList<String> text) {
		
		for(int index=0; index<text.size(); index++) {
			System.out.println(text.get(index));
		}
				
	}
}
