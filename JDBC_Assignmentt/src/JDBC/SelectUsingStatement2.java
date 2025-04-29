package JDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectUsingStatement2 {

	public static void main(String[] args) throws Exception
	{
		Connection con=MySQLConnection.getConnection();
		//display name and salary of emps having sal>=1000
		
		String query="select ename,salary from employeee where salary>=1200";
		//String query="select sal,ename from emp where eno=8";
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query);
		System.out.println(String.format("%-15s", "Employee Name") + String.format("%-15s", "Employee Salary"));
		while(rs.next())
			System.out.println(String.format("%-15s", rs.getString("ename")) + String.format("%-15s", rs.getDouble("salary")));
		
		st.close();
		con.close();
		
	}

}