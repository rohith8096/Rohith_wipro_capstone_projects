package JDBC_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_conn {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//import packages
		//create object for conection
		//Retrive data
		//close connection
		String url="jdbc:mysql://localhost:3306/mydb";
		String user="root";
		String password="Rohith@8096";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection(url,user,password);
		System.out.println("Connection created");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("Select * from emp");
		System.out.println("ID\t name\t Salary");
		while(rs.next()) {
			int id=rs.getInt("Id");
			String name=rs.getString("name");
			int salary=rs.getInt("salary");
			System.out.println("ID:"+id +"\tname:"+name+" \tSalary:"+salary);
		}
		rs.close();
		stmt.close();
		con.close();
	}

}
