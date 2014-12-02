package Implementation;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.RTypeDAO;
import Implementation.DAO.RegisterDAO;
import Model.Instruction;
import Model.RType;
import Model.Register;
import Utilities.Utilities;

public class RTypeConverter {
	
	public void convert(Instruction inst) {
		
		RTypeDAO rtd = new RTypeDAO();
		String str = inst.getCommand();
		RType rt;
		rt = rtd.getRType(str);
		
		String name = rt.getName();
		
		switch(name) {
		
			case "DSUBU":
				dsubu(inst);
				break;
				
			case "DDIV":
				ddiv(inst);
				break;
				
			case "MFHI":
				mfhi(inst);
				break;
				
			case "MFLO":
				mflo(inst);
				break;
				
			case "AND":
				and(inst);
				break;
				
			case "DSRLV":
				dsrlv(inst);
				break;
				
			case "SLT":
				slt(inst);
				break;
		}		
	}
	
	private void dsubu(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "DSUBU";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		int rs;
		int rt;
		
		if(parts.length == 1 || parts[0].matches("DSUBU") == false) {
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
				String strRd;
				String strRs;
				String strRt;
				strRd = parts[0].trim();
				strRs = parts[1].trim();
				strRt = parts[2].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRd) == false)
					found = false;
				else if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRd);					
					rd = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));				
					
					String hexOpcode = solveOpcode(opcode, rs, rt, rd, func);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of DSUBU
	
	
	private void ddiv(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "DDIV";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rs;
		int rt;
		
		if(parts.length == 1 || parts[0].matches("DDIV") == false) {
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
				String strRt;
				strRs = parts[0].trim();
				strRt = parts[1].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRs) == false)
					found = false;
				if(rdao.searchRegister(strRt) == false)
					found = false;
				
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
					
					String hexOpcode = solveOpcode(opcode, rs, rt, 0, func);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of DDIV
	
	private void mfhi(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "MFHI";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		
		if(parts.length == 1 || parts[0].matches("MFHI") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {					
			String strRd;
			strRd = parts[1].trim();
			boolean found = false;
			
			found = rdao.searchRegister(strRd);
				
			if(found == false) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}
			else {
				Register reg = new Register();
				
				reg = rdao.getRegister(strRd);					
				rd = Integer.parseInt(reg.getName().substring(1));		
				
				String hexOpcode = solveOpcode(opcode, 0, 0, rd, func);					
				inst.setOpcode(hexOpcode);
				cdao.editCommand(inst);					
			}// end of else part of checking if there is such register							
		}// end of else part of checking if there is a command		
	}// end of MFHI
	
	private void mflo(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "MFLO";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		
		if(parts.length == 1 || parts[0].matches("MFLO") == false) {
			String error_msg = "Wrong syntax!";
			inst.setError_msg(error_msg);
			inst.setError(true);
			cdao.editCommand(inst);
			return;
		}		
		else {					
			String strRd;
			strRd = parts[1].trim();
			boolean found = false;
			
			found = rdao.searchRegister(strRd);
				
			if(found == false) {
				String error_msg = "Wrong syntax!";
				inst.setError_msg(error_msg);
				inst.setError(true);
				cdao.editCommand(inst);
				return;
			}
			else {
				Register reg = new Register();
				
				reg = rdao.getRegister(strRd);					
				rd = Integer.parseInt(reg.getName().substring(1));		
				
				String hexOpcode = solveOpcode(opcode, 0, 0, rd, func);					
				inst.setOpcode(hexOpcode);
				cdao.editCommand(inst);					
			}// end of else part of checking if there is such register							
		}// end of else part of checking if there is a command		
	}// end of MFLO
	
	private void and(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "AND";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		int rs;
		int rt;
		
		if(parts.length == 1 || parts[0].matches("AND") == false) {
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
				String strRd;
				String strRs;
				String strRt;
				strRd = parts[0].trim();
				strRs = parts[1].trim();
				strRt = parts[2].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRd) == false)
					found = false;
				else if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRd);					
					rd = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));				
					
					String hexOpcode = solveOpcode(opcode, rs, rt, rd, func);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of AND
	
	private void dsrlv(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "DSRLV";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		int rs;
		int rt;
		
		if(parts.length == 1 || parts[0].matches("DSRLV") == false) {
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
				String strRd;
				String strRs;
				String strRt;
				strRd = parts[0].trim();
				strRs = parts[1].trim();
				strRt = parts[2].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRd) == false)
					found = false;
				else if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRd);					
					rd = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));				
					
					String hexOpcode = solveOpcode(opcode, rs, rt, rd, func);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of DSRLV
	
	private void slt(Instruction inst) {
		String cmd = inst.getCommand();
		String[] parts;		
		parts = cmd.split(" +|\t+", 2);//splits the string into two.
		
		String str = "SLT";
		RTypeDAO rtd = new RTypeDAO();
		RType rT = rtd.getRType(str);
		
		RegisterDAO rdao = new RegisterDAO();
		CommandDAO cdao = new CommandDAO();
		
		int opcode = rT.getOpcode();
		int func = rT.getFunction();
		int rd;
		int rs;
		int rt;
		
		if(parts.length == 1 || parts[0].matches("SLT") == false) {
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
				String strRd;
				String strRs;
				String strRt;
				strRd = parts[0].trim();
				strRs = parts[1].trim();
				strRt = parts[2].trim();
				boolean found = true;
				
				if(rdao.searchRegister(strRd) == false)
					found = false;
				else if(rdao.searchRegister(strRs) == false)
					found = false;
				else if(rdao.searchRegister(strRt) == false)
					found = false;
				
				if(found == false) {
					String error_msg = "Wrong syntax!";
					inst.setError_msg(error_msg);
					inst.setError(true);
					cdao.editCommand(inst);
					return;
				}
				else {
					Register reg = new Register();
					
					reg = rdao.getRegister(strRd);					
					rd = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRs);					
					rs = Integer.parseInt(reg.getName().substring(1));
					
					reg = rdao.getRegister(strRt);					
					rt = Integer.parseInt(reg.getName().substring(1));				
					
					String hexOpcode = solveOpcode(opcode, rs, rt, rd, func);					
					inst.setOpcode(hexOpcode);
					cdao.editCommand(inst);					
				}// end of else part of checking if there is such register				
			}// separation of rd, rs, rt			
		}// end of else part of checking if there is a command		
	}// end of SLT
	
	private String solveOpcode(int opcode, int rs, int rt, int rd, int func) {		
		String output;
		String strOpcode;
		String strRS;
		String strRT;
		String strRD;
		String strZeros;
		String strFunc;
		
		int zeros = 0;
		
		Utilities c = new Utilities();
		
		strOpcode = c.decimalToBinary(opcode, 6);
		strRS = c.decimalToBinary(rs, 5);
		strRT = c.decimalToBinary(rt, 5);
		strRD = c.decimalToBinary(rd, 5);
		strZeros = c.decimalToBinary(zeros, 5);
		strFunc = c.decimalToBinary(func, 6);
		output = strOpcode + strRS + strRT + strRD + strZeros + strFunc;
		
		//binary to hex
		output = c.binaryToHex(output);
		output = output.toUpperCase();
				
		//pads the hex value to make it 32-bit
		//output = c.padMSB(output, 8) + "h";		
		output = c.pad0(output, 8) + "h";
		
		return output;
	}

}
