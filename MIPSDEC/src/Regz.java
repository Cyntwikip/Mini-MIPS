
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class Regz {
    private ArrayList<Integer> num;
    
    public Regz(){
        int i;
        num = new ArrayList();
        for(i=0;i<16;i++){
          num.add(0);
        }
    }
    
    public void add(Regz x){
        for(int i = 0; i<16; i++)
            num.set(i, num.get(i)+x.num.get(i));
        this.check();
    }
    
    public void sub(Regz x){
        for(int i = 0; i<16; i++)
            num.set(i, num.get(i)-x.num.get(i));
        this.check2();
    }
    
    private void check(){
        int temp;
        for(int i = 0; i<15;i++)
        {
            temp = num.get(i);
            num.set(i, temp%16);
            num.set(i+1,num.get(i+1)+(temp/16));
        }
          num.set(15, num.get(15)%16);
    }
    
    private void check2(){
        int temp;
        for(int i = 0; i<14;i++){
            temp = num.get(i);
            if(temp < 0){
                num.set(i, num.get(i)+16);
                num.set(i+1, num.get(i+1)-1);
            }
        }
        if(num.get(15)<0)
            num.set(15, num.get(15)+16);
    }
    public String getNum(){
        String temp = "";
        int i;
        check();
        check2();
        for(i=0;i<16;i++){
            temp = tabcon(num.get(i)).concat(temp);
        }
        return temp;
    }
    
    public void setNumh(String a){
        for(int i=0; i<16; i++){
            num.set(i, this.HEXTODEC(a.charAt(a.length()-1-i)));
        }
    }
    
    public void setNumb(String a){
        String temp;
        temp = BINTOHEX(a);
        setNumh(temp);
    }
    
    public String BINTOHEX(String a){
            int i=0;
            String temp = "";
            
            while(i<a.length()){
                temp = BINTOHEXtab(temp.concat(this.getPart(a, i, 4)));
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
    
    private int HEXTODEC(char a){
        int dummy;
            switch(a){
                case '0' : dummy = 0; break;
                case '1' : dummy = 1; break;
                case '2' : dummy = 2; break;
                case '3' : dummy = 3; break;
                case '4' : dummy = 4; break;
                case '5' : dummy = 5; break;
                case '6' : dummy = 6; break;
                case '7' : dummy = 7; break;
                case '8' : dummy = 8; break;
                case '9' : dummy = 9; break;
                case 'A' : dummy = 10; break;
                case 'B' : dummy = 11; break;
                case 'C' : dummy = 12; break;
                case 'D' : dummy = 13; break;
                case 'E' : dummy = 14; break;
                case 'F' : dummy = 15; break;
                default : dummy = 0;
            }
            return dummy;
    }
    
    private String tabcon(int i){
        switch(i){
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            case 13: return "D";
            case 14: return "E";
            case 15: return "F";
            default: return "0";
        }
    }
}
