package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectUsingPreparedStatement {

	public static void main(String[] args) throws Exception
	{
		Connection con=MySQLConnection.getConnection();
		//accept eno and display name and salary of that emp
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Emp No");
		int a=sc.nextInt();
		
		String query="select ename,salary, gender, dob, dept from employeee where eno=?";
		
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setInt(1, a);
		
		ResultSet rs=ps.executeQuery();
		
		System.out.println(String.format("%-15s", "Employee Name") + String.format("%-15s", "Employee Salary") + String.format("%-15s", "Employee Gender") + String.format("%-15s", "Employee DOB")+ String.format("%-15s", "Employee Department"));
		if(rs.next())
			System.out.println(String.format("%-15s", rs.getString("ename")) + String.format("%-15s", rs.getDouble("salary"))+ String.format("%-15s", rs.getString("gender")) +String.format("%-15s", rs.getString("dob")) +String.format("%-15s", rs.getString("dept")));
		
		
		ps.close();
		con.close();
		
		
	}

}