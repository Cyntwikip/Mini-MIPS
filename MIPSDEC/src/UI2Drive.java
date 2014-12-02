
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
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
public class UI2Drive implements ActionListener{
    
    UI2 GUI;
    ArrayList<history> past;
    history temp;
    ArrayList<String> LineOfCodes;
    ArrayList<Boolean> Rs;
    ArrayList<Regz> storage;
    Boolean j;
    ALU func;
    int cc;
    opinit opcod;
    UI2Drive2 combobox;
    String IF;
    String ID;
    String EX;
    String MEM;
    ArrayList<String> inCaseOfDiv;
    
    public UI2Drive(ArrayList<String> a){
    func = new ALU();
    GUI = new UI2();
    GUI.setVisible(true);
    GUI.getjButton1().addActionListener(this);
    past = new ArrayList();
    cc = 1;
    LineOfCodes = a;
    Rs = new ArrayList();
    storage = new ArrayList();
    opcod = new opinit();
    j = false;
    combobox = new UI2Drive2(this);
    while(Rs.size()<34){
        Rs.add(false);
    }
    
    while(storage.size()<49)
        storage.add(new Regz());
    add();
    IF = "Penguin";
    ID = "Giraffe";
    EX = "Elephant";
    MEM = "Squirrel";
    }
    
    public void Reset(ArrayList<String> a){
        GUI.dispose();
        GUI = new UI2();
        GUI.getjButton1().addActionListener(this);
        GUI.setVisible(true);
        cc = 1;
        past = new ArrayList();
        LineOfCodes = a;
        Rs = new ArrayList();
        storage = new ArrayList();
        combobox = new UI2Drive2(this);
        while(Rs.size()<32){
          Rs.add(false);
          storage.add(new Regz());
        }
        add();
    IF = "Penguin";
    ID = "Giraffe";
    EX = "Elephant";
    MEM = "Squirrel";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.Restore();
        GUI.getjComboBox1().addItem(Integer.toString(cc));
        GUI.getjComboBox1().setSelectedIndex(cc);
        cc++;

        //WB MEM EX DECODE FETCH
        if(j && MEM.equals(EX) && MEM.equals(ID)){
            System.out.println("HI");
              if(temp.getItem(11).contains("1")){
                  GUI.getPC().setText(GUI.getEXALU().getText());
                  j = false;
                }
              else if(!temp.getItem(11).contains("1")){
                  GUI.getPC().setText(GUI.getIDNPC().getText());
                  j = false;
              }
         }
        /////////////////////////////////////////////
        
            MEMORY();
        if(!EX.equals(GUI.getIDIR().getText()))
            Execute();
        if(!ID.equals(GUI.getIFIR().getText()))
          InstructionDecode();
        InstructionFetch();
        System.out.println(ID);
        System.out.println(EX);
        System.out.println(MEM);
        add();
    }
    
    public void add(){
        int i;
        temp = new history();
        
        for(i=0;i<49;i++){
            temp.addItem(getInfo(i));
//            storage.get(i).setNumh(temp.getItem(i));
        }
        past.add(temp);
    }
    
    public String getInfo(int i){
        String x;
        switch(i){
          case 0:  x = GUI.getPC().getText(); break;
          case 1:  x = GUI.getIFIR().getText(); break;
          case 2:  x = GUI.getIFNPC().getText(); break;
          case 3:  x = GUI.getIDIR().getText(); break;
          case 4:  x = GUI.getIDA().getText(); break;
          case 5:  x = GUI.getIDB().getText(); break;
          case 6:  x = GUI.getIDIMM().getText(); break;
          case 7:  x = GUI.getIDNPC().getText(); break;
          case 8:  x = GUI.getEXIR().getText(); break;
          case 9: x = GUI.getEXALU().getText(); break;
          case 10: x = GUI.getEXB().getText(); break;
          case 11: x = GUI.getEXCOND().getText(); break;
          case 12: x = GUI.getMEMIR().getText(); break;
          case 13: x = GUI.getMEMALU().getText(); break;
          case 14: x = GUI.getWBOUT().getText(); break;   
          case 15: x = GUI.getR0().getText(); break;
          case 16: x = GUI.getR1().getText(); break;
          case 17: x = GUI.getR2().getText(); break;
          case 18: x = GUI.getR3().getText(); break;
          case 19: x = GUI.getR4().getText(); break;
          case 20: x = GUI.getR5().getText(); break;
          case 21: x = GUI.getR6().getText(); break;
          case 22: x = GUI.getR7().getText(); break;
          case 23: x = GUI.getR8().getText(); break;
          case 24: x = GUI.getR9().getText(); break;
          case 25: x = GUI.getR10().getText(); break;
          case 26: x = GUI.getR11().getText(); break;
          case 27: x = GUI.getR12().getText(); break;
          case 28: x = GUI.getR13().getText(); break;
          case 29: x = GUI.getR14().getText(); break;
          case 30: x = GUI.getR15().getText(); break;
          case 31: x = GUI.getR16().getText(); break;
          case 32: x = GUI.getR17().getText(); break;
          case 33: x = GUI.getR18().getText(); break;
          case 34: x = GUI.getR19().getText(); break;
          case 35: x = GUI.getR20().getText(); break;
          case 36: x = GUI.getR21().getText(); break;
          case 37: x = GUI.getR22().getText(); break;
          case 38: x = GUI.getR23().getText(); break;
          case 39: x = GUI.getR24().getText(); break;
          case 40: x = GUI.getR25().getText(); break;
          case 41: x = GUI.getR26().getText(); break;
          case 42: x = GUI.getR27().getText(); break;
          case 43: x = GUI.getR28().getText(); break;
          case 44: x = GUI.getR29().getText(); break;
          case 45: x = GUI.getR30().getText(); break;
          case 46: x = GUI.getR31().getText(); break;
          case 47: x = GUI.getHI().getText(); break;
          case 48: x = GUI.getLO().getText(); break;
          default: x = "error";
        }
        return x;
    }
    
    public String FetchCode(String a){
       String temp = a;
       String temp2 = String.format("%"+64+"s", "100").replace(' ', '0');
       temp = func.div2(temp,temp2 ).get(1);
       long x = parseLong(temp,2);
       if((int)x<LineOfCodes.size()){
        int y = (int)x;
        temp = LineOfCodes.get(y);
        while(temp.length()<64)
          temp = "0".concat(temp);
        return temp;
       }
           int size = 64;
       String temp3 = String.format("%"+size+"s", "0").replace(' ','0');
       return temp3;
    }
    
    public void InstructionFetch(){
        String a;
        String inc = "100";
        while(inc.length()<64)
            inc = "0".concat(inc);
        a = func.HEXTOBIN(GUI.getPC().getText());
        GUI.getIFIR().setText(func.BINTOHEX(FetchCode(a)));
        a = func.BINTOHEX(func.ADD(a, inc));       
        GUI.getPC().setText(a);
        GUI.getIFNPC().setText(a);
    }
    
    public void InstructionDecode(){
        String Opcode;
        String Type;
        ArrayList<String> vars;
        
        if(!j){
            ID = GUI.getIFIR().getText();
            GUI.getIDIR().setText(GUI.getIFIR().getText());
            GUI.getIDNPC().setText(GUI.getIFNPC().getText());
            Opcode = func.HEXTOBIN(GUI.getIDIR().getText());
            if(Opcode.contains("1"))
              {    
              Opcode = func.getPart(Opcode, 32, 6);
              Type = "I";
              if(Opcode.equals("000101"))
                  j = true;
              if(Opcode.equals("000000")){
                Opcode = func.HEXTOBIN(GUI.getIDIR().getText());
                Opcode = func.getPart(Opcode, 63-6, 6);
                Type = "R";
              }
              else if(Opcode.equals("000010")){
                  j = true;
                  Type = "J";
              }
            System.out.println("TYPE="+ Type);
            vars = this.getVars(func.HEXTOBIN(GUI.getIDIR().getText()),Type);
            
            if(Type.equals("I") || Type.equals("R")){
              GUI.getIDA().setText(vars.get(0));
              GUI.getIDB().setText(vars.get(1));
              GUI.getIDIMM().setText(vars.get(2));
            }
            else{
               GUI.getIDA().setText(this.getReg("111111"));
               GUI.getIDB().setText(this.getReg("111111"));
               GUI.getIDIMM().setText(vars.get(0));
            }
           
            }
        }
        System.out.println("LETTER J= "+ j);
    }
    
    public void Execute(){
        String Opcode;
        String Type;
        Opcode = func.HEXTOBIN(GUI.getIDIR().getText());
        EX = GUI.getIDIR().getText();
        GUI.getEXIR().setText(func.BINTOHEX(Opcode));
        if(Opcode.contains("1"))
        {
          Opcode = func.getPart(Opcode, 32+0, 6);
          Type = "I";
              if(Opcode.equals("000000")){
                Opcode = func.HEXTOBIN(GUI.getIDIR().getText());
                Opcode = func.getPart(Opcode, 32+31-6, 6);
                Type = "R";
              }
              else if(Opcode.equals("000010")){
                  Type = "J";
              }
              
          if(Type.equals("J")){
              String a;
              String b;
              a = func.HEXTOBIN(GUI.getIDNPC().getText());
              b = func.HEXTOBIN(GUI.getIDIMM().getText());
              a = func.ADD(a, b);
              GUI.getEXALU().setText(func.BINTOHEX(a));
              GUI.getEXCOND().setText(func.BINTOHEX(String.format("%"+64+"s", "1").replace(' ', '0')));
          }
          else if(Type.equals("I") && Opcode.equals("000101")){
              String a;
              String b;
              String temp;
              a = func.HEXTOBIN(GUI.getIDNPC().getText());
              b = func.HEXTOBIN(GUI.getIDIMM().getText());
              temp = func.ADD(a, b);
              GUI.getEXALU().setText(func.BINTOHEX(temp));
              temp = String.format("%"+64+"s", "1").replace(' ', '0');
              if(b.contains("1"))
                 temp = String.format("%"+64+"s", "0").replace(' ', '0');
              GUI.getEXCOND().setText(func.BINTOHEX(temp));
          }
          else if(Type.equals("R")){
              //RD <--  RS FUNC RT 
              String a;
              String b;
              a = func.HEXTOBIN(GUI.getIDA().getText());
              b = func.HEXTOBIN(GUI.getIDIMM().getText());
              a = RTypes(Opcode,a,b);
              GUI.getEXALU().setText(func.BINTOHEX(a));
          }
    
          
        }
    }
    
    public void MEMORY(){
        String opcode;
        opcode = GUI.getEXIR().getText();
        opcode = func.HEXTOBIN(opcode);
        opcode = func.getPart(opcode,32+0,6);
        if(!MEM.equals(EX)){
          opcode = GUI.getEXIR().getText();    
          MEM = opcode;
          GUI.getMEMIR().setText(opcode);
          
        }
    }
    
    public String RTypes(String opcode, String a, String b){
        String temp = "";
        ArrayList<String> temp2 = new ArrayList();
        switch(opcode){
            case "101111":  temp = func.ADD(a, func.NEG(b)); 
                            break;
            case "011110": temp2 = func.div2(a, b);
                           temp = func.getPart(temp2.get(0),32+16,16);
                           temp = temp.concat(func.getPart(temp2.get(1),32+16,16));
                           inCaseOfDiv = temp2;
                           break;
            case "010000": 
            case "010010":temp = String.format("%"+64+"s","0").replace(' ', '0'); 
                            break;
            case "100100":temp = func.AND(a, b); break;
            case "010110": int x = Integer.parseInt(func.getPart(b, 58, 6));
                            temp = func.shiftRightU(a, x); break;
            case "101010": a = func.ADD(a,func.NEG(b));
                                temp = String.format("%"+64+"s","0").replace(' ', '0');
                            if(a.charAt(0) == '1')
                                temp = String.format("%"+64+"s","1").replace(' ', '0');
                             break;
            case "011001": temp = func.ADD(a,b); 
                            break;
            case "001101": temp = func.OR(a, b);break;
        }
        return temp;
    }
    public ArrayList<String> getVars(String Opcode, String type){
        ArrayList<String> temp = new ArrayList();
        String dummy;
        if(type.equals("I")){
          dummy = func.getPart(Opcode, 32+6, 5);
          dummy = this.getReg(dummy);
          temp.add(dummy);
          dummy = func.getPart(Opcode, 32+11, 5);
          dummy = this.getReg(dummy);
          temp.add(dummy);
          
          dummy = func.getPart(Opcode, 32+16, 16);
          while(dummy.length()<64)
              dummy = "0".concat(dummy);
          dummy = func.BINTOHEX(dummy);
          temp.add(dummy);
          //RS RT IMM
        }
        else if(type.equals("R")){
          dummy = func.getPart(Opcode, 32+6, 5);
          dummy = this.getReg(dummy);
          temp.add(dummy);
          dummy = func.getPart(Opcode, 32+11, 5);
          dummy = this.getReg(dummy);
          temp.add(dummy);
          
          dummy = func.getPart(Opcode, 32+16, 5);
          dummy = this.getReg(dummy);
          temp.add(dummy);
          //RS RT RD  
        }
        else{
          dummy = func.getPart(Opcode, 32+6, 26);        
          dummy = dummy.concat("00");
          String pad = Character.toString(dummy.charAt(0));
          while(dummy.length()<64)
              dummy = pad.concat(dummy);
          dummy = func.BINTOHEX(dummy);
          temp.add(dummy);
          //Offset
        }
        return temp;
    }
    
    public String getReg(String num){
        String x;
        int y;
        y = Integer.parseInt(num, 2);
        System.out.println("REGISTER #"+y);
        switch (y){
          case 0: x = GUI.getR0().getText(); break;
          case 1: x = GUI.getR1().getText(); break;
          case 2: x = GUI.getR2().getText(); break;
          case 3: x = GUI.getR3().getText(); break;
          case 4: x = GUI.getR4().getText(); break;
          case 5: x = GUI.getR5().getText(); break;
          case 6: x = GUI.getR6().getText(); break;
          case 7: x = GUI.getR7().getText(); break;
          case 8: x = GUI.getR8().getText(); break;
          case 9: x = GUI.getR9().getText(); break;
          case 10: x = GUI.getR10().getText(); break;
          case 11: x = GUI.getR11().getText(); break;
          case 12: x = GUI.getR12().getText(); break;
          case 13: x = GUI.getR13().getText(); break;
          case 14: x = GUI.getR14().getText(); break;
          case 15: x = GUI.getR15().getText(); break;
          case 16: x = GUI.getR16().getText(); break;
          case 17: x = GUI.getR17().getText(); break;
          case 18: x = GUI.getR18().getText(); break;
          case 19: x = GUI.getR19().getText(); break;
          case 20: x = GUI.getR20().getText(); break;
          case 21: x = GUI.getR21().getText(); break;
          case 22: x = GUI.getR22().getText(); break;
          case 23: x = GUI.getR23().getText(); break;
          case 24: x = GUI.getR24().getText(); break;
          case 25: x = GUI.getR25().getText(); break;
          case 26: x = GUI.getR26().getText(); break;
          case 27: x = GUI.getR27().getText(); break;
          case 28: x = GUI.getR28().getText(); break;
          case 29: x = GUI.getR29().getText(); break;
          case 30: x = GUI.getR30().getText(); break;
          case 31: x = GUI.getR31().getText(); break;
          case 32: x = GUI.getHI().getText(); break;
          case 33: x = GUI.getLO().getText(); break;  
          default: x = "0000000000000000000000000000000000000000000000000000000000000000";
        }
        return x;
    }
    
    public void Restore(){
        int i;
        for(i=0;i<49;i++){
          switch(i){
            case 0:  GUI.getPC().setText(past.get(cc-1).getItem(i)); break;
            case 1:  GUI.getIFIR().setText(past.get(cc-1).getItem(i)); break;
            case 2:  GUI.getIFNPC().setText(past.get(cc-1).getItem(i)); break;
            case 3:  GUI.getIDIR().setText(past.get(cc-1).getItem(i)); break;
            case 4:  GUI.getIDA().setText(past.get(cc-1).getItem(i)); break;
            case 5:  GUI.getIDB().setText(past.get(cc-1).getItem(i)); break;
            case 6:  GUI.getIDIMM().setText(past.get(cc-1).getItem(i)); break;
            case 7:  GUI.getIDNPC().setText(past.get(cc-1).getItem(i)); break;
            case 8:  GUI.getEXIR().setText(past.get(cc-1).getItem(i)); break;
            case 9:  GUI.getEXALU().setText(past.get(cc-1).getItem(i)); break;
            case 10: GUI.getEXB().setText(past.get(cc-1).getItem(i)); break;
            case 11: GUI.getEXCOND().setText(past.get(cc-1).getItem(i)); break;
            case 12: GUI.getMEMIR().setText(past.get(cc-1).getItem(i)); break;
            case 13: GUI.getMEMALU().setText(past.get(cc-1).getItem(i)); break;
            case 14: GUI.getWBOUT().setText(past.get(cc-1).getItem(i)); break;   
            case 15: GUI.getR0().setText(past.get(cc-1).getItem(i)); break;
            case 16: GUI.getR1().setText(past.get(cc-1).getItem(i)); break;
            case 17: GUI.getR2().setText(past.get(cc-1).getItem(i)); break;
            case 18: GUI.getR3().setText(past.get(cc-1).getItem(i)); break;
            case 19: GUI.getR4().setText(past.get(cc-1).getItem(i)); break;
            case 20: GUI.getR5().setText(past.get(cc-1).getItem(i)); break;
            case 21: GUI.getR6().setText(past.get(cc-1).getItem(i)); break;
            case 22: GUI.getR7().setText(past.get(cc-1).getItem(i)); break;
            case 23: GUI.getR8().setText(past.get(cc-1).getItem(i)); break;
            case 24: GUI.getR9().setText(past.get(cc-1).getItem(i)); break;
            case 25: GUI.getR10().setText(past.get(cc-1).getItem(i)); break;
            case 26: GUI.getR11().setText(past.get(cc-1).getItem(i)); break;
            case 27: GUI.getR12().setText(past.get(cc-1).getItem(i)); break;
            case 28: GUI.getR13().setText(past.get(cc-1).getItem(i)); break;
            case 29: GUI.getR14().setText(past.get(cc-1).getItem(i)); break;
            case 30: GUI.getR15().setText(past.get(cc-1).getItem(i)); break;
            case 31: GUI.getR16().setText(past.get(cc-1).getItem(i)); break;
            case 32: GUI.getR17().setText(past.get(cc-1).getItem(i)); break;
            case 33: GUI.getR18().setText(past.get(cc-1).getItem(i)); break;
            case 34: GUI.getR19().setText(past.get(cc-1).getItem(i)); break;
            case 35: GUI.getR20().setText(past.get(cc-1).getItem(i)); break;
            case 36: GUI.getR21().setText(past.get(cc-1).getItem(i)); break;
            case 37: GUI.getR22().setText(past.get(cc-1).getItem(i)); break;
            case 38: GUI.getR23().setText(past.get(cc-1).getItem(i)); break;
            case 39: GUI.getR24().setText(past.get(cc-1).getItem(i)); break;
            case 40: GUI.getR25().setText(past.get(cc-1).getItem(i)); break;
            case 41: GUI.getR26().setText(past.get(cc-1).getItem(i)); break;
            case 42: GUI.getR27().setText(past.get(cc-1).getItem(i)); break;
            case 43: GUI.getR28().setText(past.get(cc-1).getItem(i)); break;
            case 44: GUI.getR29().setText(past.get(cc-1).getItem(i)); break;
            case 45: GUI.getR30().setText(past.get(cc-1).getItem(i)); break;
            case 46: GUI.getR31().setText(past.get(cc-1).getItem(i)); break;
            case 47: GUI.getHI().setText(past.get(cc-1).getItem(i)); break;
            case 48: GUI.getLO().setText(past.get(cc-1).getItem(i)); break;
            }
        }
    }
    
    public void View(int x){
        int i;
        if(x == cc)
            x--;
        for(i=0;i<49;i++){
          switch(i){
            case 0:  GUI.getPC().setText(past.get(x).getItem(i)); break;
            case 1:  GUI.getIFIR().setText(past.get(x).getItem(i)); break;
            case 2:  GUI.getIFNPC().setText(past.get(x).getItem(i)); break;
            case 3:  GUI.getIDIR().setText(past.get(x).getItem(i)); break;
            case 4:  GUI.getIDA().setText(past.get(x).getItem(i)); break;
            case 5:  GUI.getIDB().setText(past.get(x).getItem(i)); break;
            case 6:  GUI.getIDIMM().setText(past.get(x).getItem(i)); break;
            case 7:  GUI.getIDNPC().setText(past.get(x).getItem(i)); break;
            case 8:  GUI.getEXIR().setText(past.get(x).getItem(i)); break;
            case 9:  GUI.getEXALU().setText(past.get(x).getItem(i)); break;
            case 10: GUI.getEXB().setText(past.get(x).getItem(i)); break;
            case 11: GUI.getEXCOND().setText(past.get(x).getItem(i)); break;
            case 12: GUI.getMEMIR().setText(past.get(x).getItem(i)); break;
            case 13: GUI.getMEMALU().setText(past.get(x).getItem(i)); break;
            case 14: GUI.getWBOUT().setText(past.get(x).getItem(i)); break;   
            case 15: GUI.getR0().setText(past.get(x).getItem(i)); break;
            case 16: GUI.getR1().setText(past.get(x).getItem(i)); break;
            case 17: GUI.getR2().setText(past.get(x).getItem(i)); break;
            case 18: GUI.getR3().setText(past.get(x).getItem(i)); break;
            case 19: GUI.getR4().setText(past.get(x).getItem(i)); break;
            case 20: GUI.getR5().setText(past.get(x).getItem(i)); break;
            case 21: GUI.getR6().setText(past.get(x).getItem(i)); break;
            case 22: GUI.getR7().setText(past.get(x).getItem(i)); break;
            case 23: GUI.getR8().setText(past.get(x).getItem(i)); break;
            case 24: GUI.getR9().setText(past.get(x).getItem(i)); break;
            case 25: GUI.getR10().setText(past.get(x).getItem(i)); break;
            case 26: GUI.getR11().setText(past.get(x).getItem(i)); break;
            case 27: GUI.getR12().setText(past.get(x).getItem(i)); break;
            case 28: GUI.getR13().setText(past.get(x).getItem(i)); break;
            case 29: GUI.getR14().setText(past.get(x).getItem(i)); break;
            case 30: GUI.getR15().setText(past.get(x).getItem(i)); break;
            case 31: GUI.getR16().setText(past.get(x).getItem(i)); break;
            case 32: GUI.getR17().setText(past.get(x).getItem(i)); break;
            case 33: GUI.getR18().setText(past.get(x).getItem(i)); break;
            case 34: GUI.getR19().setText(past.get(x).getItem(i)); break;
            case 35: GUI.getR20().setText(past.get(x).getItem(i)); break;
            case 36: GUI.getR21().setText(past.get(x).getItem(i)); break;
            case 37: GUI.getR22().setText(past.get(x).getItem(i)); break;
            case 38: GUI.getR23().setText(past.get(x).getItem(i)); break;
            case 39: GUI.getR24().setText(past.get(x).getItem(i)); break;
            case 40: GUI.getR25().setText(past.get(x).getItem(i)); break;
            case 41: GUI.getR26().setText(past.get(x).getItem(i)); break;
            case 42: GUI.getR27().setText(past.get(x).getItem(i)); break;
            case 43: GUI.getR28().setText(past.get(x).getItem(i)); break;
            case 44: GUI.getR29().setText(past.get(x).getItem(i)); break;
            case 45: GUI.getR30().setText(past.get(x).getItem(i)); break;
            case 46: GUI.getR31().setText(past.get(x).getItem(i)); break;
            case 47: GUI.getHI().setText(past.get(x).getItem(i)); break;
            case 48: GUI.getLO().setText(past.get(x).getItem(i)); break;
            }
        }
    }
    
    	private static long parseLong(String s, int base) {
	    return new BigInteger(s, base).longValue();
	}
}


/*
-MISSING needs improvement on R and I types...
Opcodes that involves jump is workin...



Missing/Incomplete functions
-public void WRITEBACK()
-public void MEMORY()

#Well... the flush works =))
#O_o most functions are set to read in BINARY...
#so yeah one must use func.HEXTOBIN() to get from the label....
# and func.BINTOHEX() as well...

# :D there's not much error handler as in 0% error handler....

# but must methods in ALU will work well... as long as the lengths are the same...



OH... IF YOU CAN UNDERSTAND THE CODE... WOW... XD
class to look at... that could help to understand XDD:
1. THIS
2. ALU
3. opinit


some pointless class... since not enough time... to change...
Regz <-- pretty much will work even without this class
*/