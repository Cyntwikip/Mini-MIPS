package Implementation.DAO;

import java.sql.*;

import Implementation.DAOInterface.ITypeDAOInterface;
import Implementation.DatabaseHandler.SQLiteJDBC;
import Model.IType;

public class ITypeDAO implements ITypeDAOInterface {

	@Override
	public boolean searchIType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from itype where name like '" + str + "%'";
			query = "select * from itype where '" + str + "' like name||'%'";		
			
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
	public IType getIType(String str) {
		// TODO Auto-generated method stub
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			//stmt = c.createStatement();
			String query;
			
			c = sql.connect();
			//query = "select * from itype where name like '" + str + "%'";
			query = "select * from itype where '" + str + "' like name||'%'";	
			
			ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				IType it = new IType();
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int opcode = rs.getInt("opcode");
				
				it.setId(id);
				it.setName(name);
				it.setOpcode(opcode);
				
				rs.close();
				ps.close();
				c.close();
				
				return it;							
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