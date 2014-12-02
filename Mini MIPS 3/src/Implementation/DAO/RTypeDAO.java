package Implementation.DAO;

import java.sql.*;

import Implementation.DAOInterface.RTypeDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.RType;

public class RTypeDAO implements RTypeDAOInterface {

	@Override
	public boolean searchRType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from rtype where name like '" + str + "%'";
			query = "select * from rtype where '" + str + "' like name||'%'";		
			
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
	public RType getRType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from rtype where name like '" + str + "%'";
			query = "select * from rtype where '" + str + "' like name||'%'";	
			
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				RType rt = new RType();
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int opcode = rs.getInt("opcode");
				int function = rs.getInt("func");
				
				rt.setId(id);
				rt.setName(name);
				rt.setOpcode(opcode);
				rt.setFunction(function);
				
				rs.close();
				ps.close();
				c.close();
				
				return rt;							
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