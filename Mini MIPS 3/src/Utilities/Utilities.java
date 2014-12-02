package Utilities;

import java.math.BigInteger;



public class Utilities {

	//from: http://www.javawithus.com/programs/decimal-to-binary
	public String toBinary(int n) {
		if (n == 0) {
			return "0";
	    }
	    
		String binary = "";
	    
		while (n > 0) {
			int rem = n % 2;
	        binary = rem + binary;
	        n = n / 2;
	    }
	    return binary;
	}
	
	//from: http://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	public String binaryToHex(String binary) {
		//from: http://stackoverflow.com/questions/5759999/translating-a-string-containing-a-binary-value-to-hex
		//String bin = Integer.toHexString(Integer.parseInt(binOutput, 2));
		//return Integer.toHexString(Integer.parseInt(binary, 2));	
		return Long.toHexString(Long.parseLong(binary, 2));
	}
	
	//from: http://stackoverflow.com/questions/9246326/convert-hexadecimal-string-hex-to-a-binary-string
	public static String hexToBin(String s) {
		  return new BigInteger(s, 16).toString(2);
		}
	
	public String padMSB(String s, int n) {
		//from: http://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java
		char pad;
		int msb = Character.getNumericValue(s.charAt(0));
		if(msb <= 8)
			pad = '0';
		else
			pad = '1';
		return String.format("%"+n+"s", s).replace(' ', pad);		
	}
	
	public String pad0(String s, int n) {
		//from: http://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java
		char pad = '0';
		return String.format("%"+n+"s", s).replace(' ', pad);		
	}
	
	public String decimalToBinary(int decimal, int size) {
		//from: http://stackoverflow.com/questions/4421400/how-to-get-0-padded-binary-representation-of-an-integer-in-java
		//String.format("%16s", Integer.toBinaryString(decimal)).replace(' ', '0')
		
		return String.format("%"+size+"s", Integer.toBinaryString(decimal)).replace(' ', '0');
	}
	
	public String removeSpaces(String s) {
		//from: http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		return s.replaceAll("\\s","");		
	}
	
	public String removeWords(String s) {
		//from: http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		return s.replaceAll("\\w","");
	}
	
	//from: http://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric
	public boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9]*$";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
	
	//from: http://www.java2s.com/Code/Java/Data-Type/ChecksiftheStringcontainsonlywhitespace.htm
	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
		    }
		}
		return true;
	}	
}
