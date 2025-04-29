package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class InsertUsingPrepareStatement {

	public static void main(String[] args) throws Exception
	{
		Connection con=MySQLConnection.getConnection();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Emp No");
		int a=sc.nextInt();
		
		System.out.println("Enter Emp Name");
		String b=sc.next();
		
		System.out.println("Enter Emp Salary");
		double c=sc.nextDouble();
		
		System.out.println("Enter Emp department");
		String d=sc.next();

		System.out.println("Enter Emp Gender");
		String e=sc.next();
		
		System.out.println("Enter Emp DOB (yyyy-mm-dd)");
		String f=sc.next();
		
		String query="insert into employeee values(?,?,?,?,?,?)";
		
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setInt(1, a);
		ps.setString(2, b);
		ps.setDouble(3, c);
		ps.setString(4,d);
		ps.setString(5,e);
		ps.setString(6,f);
		
		
		int rows=ps.executeUpdate();					
		if(rows>0)
			System.out.println(rows +" Records Inserted");
		else
			System.out.println("Record Not Inserted");
		
		ps.close();
		con.close();
		
		
	}

}