/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class opcodes {
    private final String opcode;
    private final String number;
    private final String type;
    private final int n;

    public opcodes(String opcode, String number, String type, int n) {
        this.opcode = opcode;
        this.number = number;
        this.type = type;
        this.n = n;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public int getN(){
        return n;
    }
    
}
