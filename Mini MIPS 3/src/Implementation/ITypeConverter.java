package Implementation;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.ITypeDAO;
import Implementation.DAO.LabelDAO;
import Implementation.DAO.RegisterDAO;
import Model.IType;
import Model.Instruction;
import Model.Register;
import Utilities.Utilities;

public class ITypeConverter {
	
	public void convert(Instruction inst) {
		
		ITypeDAO itd = new ITypeDAO();
		String str = inst.getCommand();
		IType it;
		it = itd.getIType(str);
		
		String name = it.getName();
		
		switch(name) {
		
			case "BNEZ":
				bnez(inst);
				break;
				
			case "LW":
				lw(inst);
				break;
				
			case "LWU":
				lwu(inst);
				break;
				
			case "LD":
				ld(inst);
				break;
				
			case "SW":
				sw(inst);
				break;
				
			case "SD":
				sd(inst);
				break;
				
			case "DADDIU":
				daddiu(inst);
				break;
				
			case "ORI":
				ori(inst);
				break;		
		}		
	}
	
	private void bnez(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "BNEZ";
		ITypeDAO rtd = new ITypeDAO();
		IType iT = rtd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		LabelDAO ldao = new LabelDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("BNEZ") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRs;
				String strImm;
				strRs = parts[0].trim();
				strImm = parts[1].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRs) == false)
					found = false;
				Instruction dummy = new Instruction();
				dummy.setLabel(strImm);
				
				if(ldao.searchLabel(dummy) == false)
					found = false;
				
				if(found == false) {
					String error_msg = "No such label found in the database!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					dummy = ldao.getLabel(dummy);		
					int num = dummy.getMemloc() - inst.getMemloc();
					immediate = (num/4)-1;
					
					String hexOpcode = solveOpcode(opcode, rs, 0, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of BNEZ
	
	private void lw(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "LW";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("LW") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRt;
				strRt = parts[0].trim();
				String temp = parts[1];
				parts = temp.split("\\(");
				
				if(parts.length != 2) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;					
				}
							
				String strImm;
				String strRs;
				strImm = parts[0].trim();
				strRs = parts[1].trim();
				strRs = strRs.substring(0, strRs.length()-1);	//to remove the ')'
				boolean found = false;
				
				found = rdao.searchRegister(strRs);
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					immediate = Integer.parseInt(strImm, 16);	// hex to Integer
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of LW
	
	private void lwu(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "LWU";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("LWU") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRt;
				strRt = parts[0].trim();
				String temp = parts[1];
				parts = temp.split("\\(");
				
				if(parts.length != 2) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;					
				}
							
				String strImm;
				String strRs;
				strImm = parts[0].trim();
				strRs = parts[1].trim();
				strRs = strRs.substring(0, strRs.length()-1);	//to remove the ')'
				boolean found = false;
				
				found = rdao.searchRegister(strRs);
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					immediate = Integer.parseInt(strImm, 16);	// hex to Integer
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of LWU
	
	private void ld(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "LD";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("LD") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRt;
				strRt = parts[0].trim();
				String temp = parts[1];
				parts = temp.split("\\(");
				
				if(parts.length != 2) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;					
				}
							
				String strImm;
				String strRs;
				strImm = parts[0].trim();
				strRs = parts[1].trim();
				strRs = strRs.substring(0, strRs.length()-1);	//to remove the ')'
				boolean found = false;
				
				found = rdao.searchRegister(strRs);
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					immediate = Integer.parseInt(strImm, 16);	// hex to Integer
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of LD
	
	private void sw(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "SW";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("SW") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRt;
				strRt = parts[0].trim();
				String temp = parts[1];
				parts = temp.split("\\(");
				
				if(parts.length != 2) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;					
				}
							
				String strImm;
				String strRs;
				strImm = parts[0].trim();
				strRs = parts[1].trim();
				strRs = strRs.substring(0, strRs.length()-1);	//to remove the ')'
				boolean found = false;
				
				found = rdao.searchRegister(strRs);
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					immediate = Integer.parseInt(strImm, 16);	// hex to Integer
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of SW
	
	private void sd(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "SD";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate;
		
		if(parts.length == 1 || parts[0].matches("SD") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 2) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRt;
				strRt = parts[0].trim();
				String temp = parts[1];
				parts = temp.split("\\(");
				
				if(parts.length != 2) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;					
				}
							
				String strImm;
				String strRs;
				strImm = parts[0].trim();
				strRs = parts[1].trim();
				strRs = strRs.substring(0, strRs.length()-1);	//to remove the ')'
				boolean found = false;
				
				found = rdao.searchRegister(strRs);
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					immediate = Integer.parseInt(strImm, 16);	// hex to Integer
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command			
	}// end of SD
	
	private void daddiu(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "DADDIU";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate = 0;
		
		if(parts.length == 1 || parts[0].matches("DADDIU") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 3) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRs;
				String strRt;
				String strImm;
				strRt = parts[0].trim();
				strRs = parts[1].trim();
				strImm = parts[2].trim();
				boolean found = true;
	
				if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				if(strImm.charAt(0) == '#') {
					//System.out.println(strImm);
					found = true;
					strImm = strImm.substring(1, strImm.length());
					//System.out.println(strImm);
				}
				else found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();					
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					for(int i=0; i<strImm.length(); i++) {
						int num = Integer.parseInt(strImm.substring(i, i+1), 16);	//hex to Integer
						//System.out.print(strImm.substring(i, i+1));
						//System.out.println(" " + num);
						immediate += num*Math.pow(16, strImm.length()-1-i);	//the MIPS representation of immediate value
					}
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of DADDIU	
	
	private void ori(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "ORI";
		ITypeDAO itd = new ITypeDAO();
		IType iT = itd.getIType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = iT.getOpcode();
		int rs;
		int rt;
		int immediate = 0;
		
		if(parts.length == 1 || parts[0].matches("ORI") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {
			String cmd2 = parts[1];
			parts = cmd2.split(",");
			
			if(parts.length != 3) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}			
			else {
				String strRs;
				String strRt;
				String strImm;
				strRt = parts[0].trim();
				strRs = parts[1].trim();
				strImm = parts[2].trim();
				boolean found = true;
	
				if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				else if(strImm.charAt(0) == '#') {
					//System.out.println(strImm);
					found = true;
					strImm = strImm.substring(1, strImm.length());
					//System.out.println(strImm);
				}
				else found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();					
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));		
					
					for(int i=0; i<strImm.length(); i++) {
						int num = Integer.parseInt(strImm.substring(i, i+1), 16);	//hex to Integer
						//System.out.print(strImm.substring(i, i+1));
						//System.out.println(" " + num);
						immediate += num*Math.pow(16, strImm.length()-1-i);	//the MIPS representation of immediate value
					}
					
					String hexOpcode = solveOpcode(opcode, rs, rt, immediate);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of ORI
	
	private String solveOpcode(int opcode, int rs, int rt, int immediate) {
		String output;
		String strOpcode;
		String strRS;
		String strRT;
		String strImmediate;
		
		Utilities c = new Utilities();
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strRS = c.decimalToBinary(rs, 5);
		strRT = c.decimalToBinary(rt, 5);
		strImmediate = c.decimalToBinary(immediate, 16);
		output = strOpcode + strRS + strRT + strImmediate;
		
		//binary to hex
		output = c.binaryToHex(output);
		output = output.toUpperCase();
				
		//pads the hex value to make it 32-bit
		//output = c.padMSB(output, 8) + "h";		
		output = c.pad0(output, 8) + "h";
		
		return output;
	}

}
