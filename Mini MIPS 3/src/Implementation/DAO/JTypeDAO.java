package Implementation.DAO;

import java.sql.*;

import Implementation.DAOInterface.JTypeDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.JType;

public class JTypeDAO implements JTypeDAOInterface {

	@Override
	public boolean searchJType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from jtype where name like '" + str + "%'";
			query = "select * from jtype where '" + str + "' like name||'%'";	
			
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
	public JType getJType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from jtype where name like '" + str + "%'";
			query = "select * from jtype where '" + str + "' like name||'%'";	
			
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				JType jt = new JType();
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int opcode = rs.getInt("opcode");
				
				jt.setId(id);
				jt.setName(name);
				jt.setOpcode(opcode);
				
				rs.close();
				ps.close();
				c.close();
				
				return jt;				
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