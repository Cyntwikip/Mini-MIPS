package Implementation.Pipeline;

import Model.Instruction;

public class PipelineInitialization {
	
	private boolean invalid = false;
	
	public boolean checkIfInvalid(Instruction[] instlist) {
		
		for(int i=0; i<instlist.length; i++) {
			if(instlist[i].isError())
				invalid = true;
		}
		
		return invalid;
	}
	

}
