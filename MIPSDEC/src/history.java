
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
public class history {
    ArrayList<String> a;
    
    public history(){
        a = new ArrayList();
    }
    
    public void addItem(String a){
        this.a.add(a);
    }
    
    public ArrayList<String> getItem(){
        return a;
    }
    
    public String getItem(int i){
        return a.get(i);
    }
}
