package Implementation.DAO;

import java.util.ArrayList;
import java.sql.*;

import Implementation.DAOInterface.LabelDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.Instruction;


public class LabelDAO implements LabelDAOInterface {

	@Override
	public void addLabel(Instruction inst) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			String query;		
			
			c = sql.connect();
					
			query = "insert into label (labid, name, memloc)" 
					+ "values (?, ?, ?)";
				
			ps = c.prepareStatement(query);
			
			//ps.setInt(1, inst.getId());
			ps.setString(2, inst.getLabel());
			ps.setInt(3, inst.getMemloc());
			ps.executeUpdate();
					
			ps.close();
			c.close();						
						
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}		
	}		

	@Override
	public boolean searchLabel(Instruction inst) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			String label = inst.getLabel();
			
			c = sql.connect();
			query = "select * from label where name like '" + label + "'";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				rs.close();
				ps.close();
				c.close();
				
				return true;				
			}
			
			rs.close();
			ps.close();
			c.close();			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}	
		return false;
	}

	@Override
	public ArrayList<Instruction> getAllLabels() {
		// TODO Auto-generated method stub
		ArrayList<Instruction> instlist = new ArrayList<Instruction>();
		
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "select * from label";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Instruction in = new Instruction();
				
				int labid = rs.getInt("labid");
				String label = rs.getString("name");
				int memloc = rs.getInt("memloc");
				
				//in.setId(labid);
				in.setLabel(label);
				in.setMemloc(memloc);
						
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
	public void deleteAllLabels() {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "delete from label";
			
			ps = c.prepareStatement(query);
			
			ps.executeUpdate();
						
			ps.close();
			c.close();
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}		
	}

	@Override
	public Instruction getLabel(Instruction inst) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			String str = inst.getLabel();
			
			c = sql.connect();
			query = "select * from label where name like '" + str + "'";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				Instruction in = new Instruction();
				
				int labid = rs.getInt("labid");
				String label = rs.getString("name");
				int memloc = rs.getInt("memloc");
				
				//in.setId(labid);
				in.setLabel(label);
				in.setMemloc(memloc);				
				
				rs.close();
				ps.close();
				c.close();
				
				return in;				
			}
			
			rs.close();
			ps.close();
			c.close();			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}	
		return null;
	}	
}