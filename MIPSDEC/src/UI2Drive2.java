
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class UI2Drive2 implements ActionListener {
    UI2Drive hi;
    public UI2Drive2(UI2Drive hi){
        this.hi = hi;
        this.hi.GUI.getjComboBox1().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x;
        String temp;
    temp = hi.GUI.getjComboBox1().getSelectedItem().toString();
    x = Integer.parseInt(temp, 10);
    System.out.println(x);
    hi.View(x);
    
    }
    
}
