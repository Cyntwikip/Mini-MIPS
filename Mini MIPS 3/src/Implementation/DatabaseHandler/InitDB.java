package Implementation.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Implementation.DAO.CommandDAO;
import Implementation.DAO.LabelDAO;
import Implementation.DAO.RegisterDAO;
import Model.Instruction;

public class InitDB {

	public void initialize() {
		RegisterDAO r = new RegisterDAO();
		CommandDAO c = new CommandDAO();
		LabelDAO l = new LabelDAO();
				
		r.initRegisters();
		c.deleteAllCommands();
		l.deleteAllLabels();
		
		setID();
		
		System.out.println("Database Initialized!");
	}
	
	private void setID() {	
		try {
			SQLiteJDBC sql = new SQLiteJDBC();
			Connection c;
			
			PreparedStatement ps = null;
			
			String query;
				
			c = sql.connect();
			
			/*
			query = "insert into command (cmdid)" 
					+ "values (?)";
			*/
			query = "update sqlite_sequence set seq = " + 0 + " where name = 'command'";
			
			ps = c.prepareStatement(query);
			
			//ps.setInt(1, 0);			
			ps.executeUpdate();
			
			/*
			query = "insert into label (labid)" 
					+ "values (?)";
			*/
			query = "update sqlite_sequence set seq = " + 0 + " where name = 'label'";
			
			ps = c.prepareStatement(query);
			
			//ps.setInt(1, 0);			
			ps.executeUpdate();
		
			ps.close();
			c.close();				
				
		} catch ( Exception e ) {
	      	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      	System.out.println("Nope! Did not work!");
	      	System.exit(0);
		}				
	}
}
