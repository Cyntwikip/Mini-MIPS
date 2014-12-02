
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
public class opinit {
    private final ArrayList<opcodes> opcode;
    
    public opinit(){
        opcode = new ArrayList();
        opcode.add(new opcodes("DSUBU","101111","R",4));
        opcode.add(new opcodes("DDIV","011110","R",3));
        opcode.add(new opcodes("MFHI","010000","R",2));
        opcode.add(new opcodes("MFLO","010010","R",2));
        opcode.add(new opcodes("AND","100100","R",4));
        opcode.add(new opcodes("DSRLV","010110","R",4));
        opcode.add(new opcodes("SLT","101010","R",4));
        opcode.add(new opcodes("BNEZ","000101","I",3));
        opcode.add(new opcodes("LW","100011","I", 4));
        opcode.add(new opcodes("LWU","100111","I",4));
        opcode.add(new opcodes("LD","110111","I",4));
        opcode.add(new opcodes("SW","101011","I",4));
        opcode.add(new opcodes("SD","111111","I",4));
        opcode.add(new opcodes("DADDIU","011001","I",4));
        opcode.add(new opcodes("ORI","001101","I",4));
        opcode.add(new opcodes("J","000010","J",2));
    }
    
    public String outputz(String Opcode, int Off){
        if(Off < 0)   
             return Opcode+" "+ Off + " lines above me";
        else
            return Opcode+" "+ Off + " Below me";
    }
    
    public String outputz(String Opcode, String RS, String RT, String RD, String Ex, String Func){
        return Opcode + " "+ RD + " "+ RS+ " "+RT;
    }
    
    public String outputz(String Opcode, String RS, String RT, String IMM){
        return Opcode + " " + RS + " " + RT + " " + IMM;
    }
    
    public String getOpcode(String name){
        int i = 0;
        while( i < opcode.size())
        {
          if(!name.equals(opcode.get(i).getNumber())){
              i++;
            }
          else if(name.equals("000000"))
              return "000000";
          else
              return opcode.get(i).getNumber();
        }
        return "ERROR";
    }
    
    public String getName(String name){
        int i = 0;
        while( i < opcode.size())
        {
          if(!name.equals(opcode.get(i).getOpcode())){
              i++;
            }
          else if(name.equals("000000"))
              return "000000";
          else
              return opcode.get(i).getOpcode();
        }
        return "ERROR";        
    }
    
    public int BinToDec(String bin){
        int l = bin.length();
        int num = 0;
        int cn = 0;
        int base;
        int i;
        while(l>0)
            {
            base = 1;
            if(bin.charAt(cn) == '1') {
              for(i=0; i<l-1; i++)
                base *=2;
              num += base;
            }
            l--;
            cn++;
            }
        return num;
    }
}
