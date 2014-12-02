
package Implementation.DAOInterface;

import java.util.ArrayList;

import Model.Register;

public interface RegisterDAOInterface {
	
	public void initRegisters();
	public boolean searchRegister(String reg);
	public Register getRegister(String reg);
	public ArrayList<Register> getAllRegisters();
	
}