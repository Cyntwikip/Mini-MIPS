package Implementation.DAOInterface;

import java.util.ArrayList;

import Model.Instruction;

public interface LabelDAOInterface {
	
	public void addLabel(Instruction inst);
	public boolean searchLabel(Instruction inst);
	public Instruction getLabel(Instruction inst);
	public ArrayList<Instruction> getAllLabels();
	public void deleteAllLabels();
	
}