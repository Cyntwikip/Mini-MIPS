
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class ALU {
    
    public String AND(String a, String b){
        StringReader r1,r2;
        String temp = "";
        String temp2;
        int x,y=0;
        r1 = new StringReader(a);
        r2 = new StringReader(b);
        while(y<a.length())
        {
            try {
                x = (r1.read()-'0')*(r2.read()-'0');
                temp2 = Integer.toString(x);
                temp = temp.concat(temp2);
                y++;
            } catch (IOException ex) {
                Logger.getLogger(ALU.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return temp;
    }
    
    public String OR(String a, String b){
        StringReader r1,r2;
        String temp = "";
        String temp2;
        int x,y=0;
        r1 = new StringReader(a);
        r2 = new StringReader(b);
        while(y<a.length())
        {
            try {
                x = (r1.read()-'0') + (r2.read()-'0');
                if(x>0)
                  x = 1;
                else 
                  x = 0;
                temp2 = Integer.toString(x);
                temp = temp.concat(temp2);
                y++;
            } catch (IOException ex) {
                Logger.getLogger(ALU.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return temp;       
    }
    
    public String XOR(String a, String b)
    {
        String temp = "";
        String temp2;
        int x,y=0;
        while(y<a.length())
        {
            if(a.charAt(y) == b.charAt(y))
                x = 0;
            else
                x = 1;
            temp2 = Integer.toString(x);
            temp = temp.concat(temp2);
            y++;
        }
        return temp;
    }
    
    public String NOT(String a){
        String temp = "";
        int y=0;
        while(y<a.length())
        {
         if(a.charAt(y) == '1')
             temp = temp.concat("0");
         else
             temp = temp.concat("1");
         y++;
        }
        return temp;
    }
    
    public String ADD(String a, String b){
        String temp = "";
        String temp2;
        int x,z,y,cb = 0,ans;
        y = a.length();
        y--;
        while(y>-1)
        {
            x = a.charAt(y)-'0';
            z = b.charAt(y)-'0';
            ans = (x+z+cb)%2;
            cb = (x+z+cb)/2;
            temp2 = Integer.toString(ans);
            temp = temp2.concat(temp);
            y--;
        }
        return temp;
    }
    
    public String NEG(String a){
        String b = "";
        while(b.length()< a.length()-1)
            b = b.concat("0");
        b = b.concat("1");
        return this.ADD(this.NOT(a), b);
    }
    
    
    public String getPart(String a, int indx, int length){
        String temp = "";
        
        while(length>0)
        {
            temp = temp.concat(Character.toString(a.charAt(indx)));
            length--;
            indx++;
        }
        return temp;
    }
    
        public String HEXTOBIN(String a){
        int i=0;
        String temp = "";
        String dummy;
        while(i<a.length())
        {
            switch(a.charAt(i)){
                case '0' : dummy = "0000"; break;
                case '1' : dummy = "0001"; break;
                case '2' : dummy = "0010"; break;
                case '3' : dummy = "0011"; break;
                case '4' : dummy = "0100"; break;
                case '5' : dummy = "0101"; break;
                case '6' : dummy = "0110"; break;
                case '7' : dummy = "0111"; break;
                case '8' : dummy = "1000"; break;
                case '9' : dummy = "1001"; break;
                case 'A' : dummy = "1010"; break;
                case 'B' : dummy = "1011"; break;
                case 'C' : dummy = "1100"; break;
                case 'D' : dummy = "1101"; break;
                case 'E' : dummy = "1110"; break;
                case 'F' : dummy = "1111"; break;
                default : dummy = "";
            }
            temp = temp.concat(dummy);
            i++;
        }
        return temp;    
    }
        public String BINTOHEX(String a){
            int i=0;
            String temp = "";
            while(i<a.length()){
                temp = temp.concat(BINTOHEXtab(this.getPart(a, i, 4)));
                i = i + 4;
            }
            return temp;
        }
        
        
        public String BINTOHEXtab(String a){
        char i=0;
        String temp = "";
        switch(a){
                case "0000": i = '0';break;
                case "0001": i = '1';break;
                case "0010": i = '2';break;
                case "0011": i = '3';break;
                case "0100": i = '4';break;
                case "0101": i = '5';break;
                case "0110": i = '6';break;
                case "0111": i = '7';break;
                case "1000": i = '8';break;
                case "1001": i = '9';break;
                case "1010": i = 'A';break;
                case "1011": i = 'B';break;
                case "1100": i = 'C';break;
                case "1101": i = 'D';break;
                case "1110": i = 'E';break;
                case "1111": i = 'F';break;
                default : i = ' ';
            }
        temp = Character.toString(i);
        return temp;    
    }
        
        public String shiftRightU(String a, int b){
            String temp;
            temp = this.getPart(a, 0, a.length()-b);
            while(temp.length()<64)
                temp = "0".concat(temp);
            return temp;
        }
        
        public String shiftRight(String a, int b){
            String temp;
            String pad ;
            pad = Character.toString(a.charAt(0));
            temp = this.getPart(a, 0, a.length()-b);
            while(temp.length()<64)
                temp = pad.concat(temp);
            return temp;
        }
        
        public String shiftLeft(String a, int b){
            String temp;
            temp = this.getPart(a, 0+b, a.length()-b);
            while(temp.length()<64)
                temp = temp.concat("0");
            return temp;
        }
        
        public ArrayList<String> div2(String strDividend, String strDivisor) {
		
		
		long dividend = parseLong(strDividend, 2);
		long divisor = parseLong(strDivisor, 2);
		
		
		long quotient = dividend/divisor;
		long remainder = dividend%divisor;
		
		int size = 64;
		
		String hi = String.format("%"+size+"s", Long.toBinaryString(remainder)).replace(' ', '0');
		String lo = String.format("%"+size+"s", Long.toBinaryString(quotient)).replace(' ', '0');
		
		ArrayList<String> arrList = new ArrayList<String>();
		
		arrList.add(hi);
		arrList.add(lo);
		return arrList;
	}
	
	//from: http://stackoverflow.com/questions/8352671/how-could-i-convert-a-64-width-binary-string-to-long-in-java
	private static long parseLong(String s, int base) {
	    return new BigInteger(s, base).longValue();
	}
}
