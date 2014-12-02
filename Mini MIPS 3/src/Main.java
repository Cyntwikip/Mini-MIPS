

import java.math.*;
import java.util.ArrayList;

import Viewer.GUI.Home;
import Controller.Assembler;
import Controller.Assembler2;



public class Main {
	
	public static void main(String[] args) {
		
				
		//Assembler a = new Assembler();
		//Assembler2 a = new Assembler2();
		//a.compile();
		
		Home h = new Home();
		h.run();
		
		/*
		String n1 = "0100000000000000000000000000000000000000000000000000000000000001";
		String n2 = "0100000000000000000000000000000000000000000000000000000000000111";
		
		System.out.println(n1.length());
		
		ALU al = new ALU();
		ArrayList<String> arrList = new ArrayList<String>();
		
		arrList = al.div(n1, n2);
		
		for(int i=0; i<arrList.size(); i++) {
			System.out.println(arrList.get(i));
		}
		*/
		
		/*
		String s = "1111111111111111111111111111111111111111111111111111111110000101";
		long l = parseLong(s, 2);
		System.out.println(s +" => " + l);

		String s2 = "1000000000000000000000000000000000000000000000000000000000000001";
		long l2 = parseLong(s2, 2);
		System.out.println(s2 +" => " + l2);
		*/
	}
	
	

}
