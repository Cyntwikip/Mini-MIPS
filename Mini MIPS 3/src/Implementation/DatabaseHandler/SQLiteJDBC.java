package Implementation.DatabaseHandler;

import java.sql.*;

public class SQLiteJDBC
{
  public Connection connect()
  {
	String db = ".\\src\\database\\mips.db";	// the dot (.) -> .\\src -> this means relative path
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:"+db);
      return c;
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    //System.out.println("Opened database successfully");
    return null;
  }
}
