package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	static Connection con;
	public static Connection getConnection() throws Exception
	{
		
		String driverName="com.mysql.cj.jdbc.Driver";
		String dbUrl="jdbc:mysql://localhost:3306/employee_management";
		String dbUserName="root";
		String dbPassword="Aditya007!";
		
		Class.forName(driverName); 		
		con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
}