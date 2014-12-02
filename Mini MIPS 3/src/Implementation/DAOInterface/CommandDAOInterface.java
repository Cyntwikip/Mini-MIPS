package Implementation.DAOInterface;

import java.util.ArrayList;

import Model.Instruction;

public interface CommandDAOInterface {
	
	public void addCommand(Instruction inst);
	public void editCommand(Instruction inst);
	public ArrayList<Instruction> getAllCommands();
	public void deleteAllCommands();
	
}