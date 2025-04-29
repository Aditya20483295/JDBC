package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertUsingStatement {

	public static void main(String[] args) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");       
		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Aditya007!");
		
		Statement st=con.createStatement(); 
		
//		String query="insert into employeee values(3,'Chris',1000.0,'Python','F','2003-07-16')";
//		String query1="insert into employeee values(4,'David',1200.0,'Java','M','2001-10-23')";
//		String query2="insert into employeee values(5,'Elise',800.0,'Mern','F','2000-10-30')";
//		String query3="insert into employeee values(6,'Frank',1100.0,'Java','M','2002-08-21')";
//		String query4="insert into employeee values(7,'George',900.0,'Mean','M','2005-09-05')";
		String query5="insert into employeee values(1,'Alice',2000.0,'AIML','F','2001-12-12')";
		String query6="insert into employeee values(2,'Ben',1000.0,'Java','M','2003-10-08')";
		
//		st.addBatch(query);
//		st.addBatch(query1);
//		st.addBatch(query2);
//		st.addBatch(query3);
//		st.addBatch(query4);							
		st.addBatch(query5);							
		st.addBatch(query6);							
		
		int rows[]=st.executeBatch();					
		if(rows.length>0)
			System.out.println(rows.length +" Records Inserted");
		else
			System.out.println("Record Not Inserted");
		
		st.close();
		con.close();
	}

}