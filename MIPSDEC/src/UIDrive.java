
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
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
public class UIDrive implements ActionListener {
    UI GUI;                         //UI...
    opinit stuffs;                  //List of OpCodes/ALU
    ArrayList<String> lineOfCodes;  //MemBlock :)
    UI2Drive next;
    ALU a;
    public UIDrive(){
      stuffs = new opinit();
      GUI = new UI();
      GUI.getjButton1().addActionListener(this);
      GUI.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event){
            BufferedReader a;
            String line;
            lineOfCodes = new ArrayList();
            a = new BufferedReader(new StringReader(GUI.getjTextArea1().getText()));
            try {
              line = a.readLine();
              while( line != null)
              {
              lineOfCodes.add(line);
              line = a.readLine();
              }
              if(!lineOfCodes.isEmpty()){
                if(next != null){
                  next.Reset(lineOfCodes);
                }
                else{
                  next = new UI2Drive(lineOfCodes);
                }
              }
            } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
   
    }

}
