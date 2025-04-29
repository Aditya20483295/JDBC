package JDBC;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
public class Create_Table {

	public static void main(String[] args) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");  //8 onwards
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Aditya007!");
		
		String query="CREATE TABLE employeee (\r\n"
				+ "    eno INT PRIMARY KEY,\r\n"
				+ "    ename VARCHAR(100),\r\n"
				+ "    salary DECIMAL(10,2),\r\n"
				+ "    dept VARCHAR(50),\r\n"
				+ "    gender VARCHAR(10),\r\n"
				+ "    dob DATE\r\n"
				+ ");";
		
		Statement st=con.createStatement();
		
		st.execute(query);
		
		System.out.println("Table Created");
		
		st.close();
		con.close();

	}

	public static void createTable() {
		
	}

}