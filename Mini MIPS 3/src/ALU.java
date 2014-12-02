import java.math.BigInteger;
import java.util.ArrayList;


public class ALU {

	
	public ArrayList<String> div(String strDividend, String strDivisor) {
		/*
		double msb1 = 0;
		double msb2 = 0;
		
		if(strDividend.length() >= 64 ) {
			if(strDividend.charAt(0) == '1') {
				msb1 = Math.pow(2, 63);
			}
			else if(strDividend.charAt(0) == '0') {
				msb1 = 0;
			}
			
			if(strDivisor.charAt(0) == '1') {
				msb2 = Math.pow(2, 63);
				System.out.println((double) Math.pow(2, 63)/Math.pow(2, 62));
				System.out.println(msb2);
				System.out.println((long)msb2);
				//System.out.println("Divisor's MSB is 1!");
			}
			else if(strDivisor.charAt(0) == '0') {
				msb2 = 0;
			}
			
			StringBuilder sb1 = new StringBuilder(strDividend);
			StringBuilder sb2 = new StringBuilder(strDivisor);
			
			sb1.deleteCharAt(0);
			sb2.deleteCharAt(0);
			
			strDividend = sb1.toString();
			strDivisor = sb2.toString();
			
			System.out.println(strDividend);
			System.out.println(strDivisor);
		}
		*/
		
		
		
		//long dividend = Long.parseLong(strDividend, 2) + (long)msb1;
		//long divisor = Long.parseLong(strDivisor, 2) + (long)msb2;
		
		long dividend = parseLong(strDividend, 2);
		long divisor = parseLong(strDivisor, 2);
		
		//System.out.println(dividend);
		//System.out.println(divisor);
		
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
