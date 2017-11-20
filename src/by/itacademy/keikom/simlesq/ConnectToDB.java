package by.itacademy.keikom.simlesq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/test";
	private final static String user = "root";
	private final static String password = "40381172";
	
	public static ConnectToDB connectToDB = new ConnectToDB();
	private Connection con;
	
	private ConnectToDB(){};
	
	public Connection getConnect() {
		
		try {
			Class.forName(driver);
			//System.out.println("Driver loaded");
		}
		catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
			//System.out.println("Database connected !!!");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	public void closeConnect() {
		
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
