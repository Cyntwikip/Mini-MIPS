package Implementation.DAO;

import java.sql.*;
import java.util.ArrayList;

import Implementation.DAOInterface.CommandDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.Instruction;

public class CommandDAO implements CommandDAOInterface {

	@Override
	public void addCommand(Instruction inst) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			
			String query;
				
			c = sql.connect();
				
			query = "insert into command (cmdid, instruction, memloc, error, message, opcode)" 
					+ "values (?, ?, ?, ?, ?, ?)";
				
			ps = c.prepareStatement(query);
			
			//ps.setInt(1, inst.getId());
			ps.setString(2, inst.getCommand());
			ps.setInt(3, inst.getMemloc());
			if(inst.isError()) {
				ps.setBoolean(4, true);
				ps.setString(5, inst.getError_msg());
			}
			ps.setString(6, inst.getOpcode());
			
			ps.executeUpdate();
		
			ps.close();
			c.close();				
				
		} catch ( Exception e ) {
	      	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      	System.exit(0);
		}				
	}

	@Override
	public void editCommand(Instruction inst) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			
			String query;
				
			c = sql.connect();
				
			query = "update command set error = ?, message = ?, opcode = ? where instruction = ?";
				
			ps = c.prepareStatement(query);
				
			ps.setBoolean(1, inst.isError());
			ps.setString(2, inst.getError_msg());
			ps.setString(3, inst.getOpcode());
			ps.setString(4, inst.getCommand());
			
			ps.executeUpdate();
		
			ps.close();
			c.close();				
				
		} catch ( Exception e ) {
	      	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      	System.exit(0);
		}				
	}

	@Override
	public ArrayList<Instruction> getAllCommands() {
		// TODO Auto-generated method stub
		
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "select * from command";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Instruction in = new Instruction();
				
				int cmdid = rs.getInt("cmdid");
				String instruction = rs.getString("instruction");
				int memloc = rs.getInt("memloc");
				boolean error = rs.getBoolean("error");
				String error_msg = rs.getString("message");
				String opcode = rs.getString("opcode");
				
				//in.setId(cmdid);
				in.setCommand(instruction);
				in.setMemloc(memloc);
				in.setError(error);
				in.setError_msg(error_msg);
				in.setOpcode(opcode);
						
				instlist.add(in);
				//System.out.println(cmdid + "|" + instruction + "|" + memloc);		
			}
			
			rs.close();
			ps.close();
			c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return instlist;
	}

	@Override
	public void deleteAllCommands() {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "delete from command";
				
			ps = c.prepareStatement(query);
			
			ps.executeUpdate();
						
			ps.close();
			c.close();
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}				
	}	
	
}