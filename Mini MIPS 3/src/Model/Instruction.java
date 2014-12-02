package Model;

public class Instruction {
	//private int id = -1;
	private String label = "";
	private String command = "";
	private int memloc;
	private boolean empty = false;
	private boolean error = false;
	private String error_msg = null;
	private String opcode = "";
	
	
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
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	/*
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	*/
}
