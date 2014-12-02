package Implementation.DAO;

import java.util.ArrayList;
import java.sql.*;

import Implementation.DAOInterface.RegisterDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.Register;

public class RegisterDAO implements RegisterDAOInterface {

	@Override
	public void initRegisters() {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "update register set value = '' where name not like 'R0'";
			query = "update register set value = '0'";
			
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
	public boolean searchRegister(String reg) {
		// TODO Auto-generated method stub
		
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "select * from register where name = '" + reg + "'";
				
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
	public Register getRegister(String reg) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "select * from register where name = '" + reg +"'";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int regid = rs.getInt("regid");
				String name = rs.getString("name");
				int value = rs.getInt("value");
					
				Register r = new Register();
				
				r.setId(regid);
				r.setName(name);
				r.setValue(value);
				
				rs.close();
				ps.close();
				c.close();				
				return r;
			}
			rs.close();
			ps.close();
			c.close();			
			return null;			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}		
		return null;
	}

	@Override
	public ArrayList<Register> getAllRegisters() {
		// TODO Auto-generated method stub
		ArrayList<Register> reglist = new ArrayList<Register>();
		
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			query = "select * from register";
				
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Register reg = new Register();
				
				int regid = rs.getInt("regid");
				String name = rs.getString("name");
				int value = rs.getInt("value");
				
				reg.setId(regid);
				reg.setName(name);
				reg.setValue(value);
				
				reglist.add(reg);		
			}
			
			rs.close();
			ps.close();
			c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return reglist;		
	}
	
	
}