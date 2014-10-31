package Model;

public class Instruction {
	private String label = null;
	private String command;
	private int memloc;
	private boolean empty = false;
	private boolean error = false;
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getMemloc() {
		return memloc;
	}
	public void setMemloc(int memloc) {
		this.memloc = memloc;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
}
