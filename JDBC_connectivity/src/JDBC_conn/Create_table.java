package JDBC_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Create_table {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "Rohith@8096";
		Class.forName("com.mysql.cj.jdbc.Driver");
		//create table
		String sql = "create table students(rollno int," + "" + "name varchar(50)," + "per int," + "email varchar(50))";
		//insert table
		String insertSQL="insert into students values(101,'Rohith',89,'abc@gmail.com')";
		String updateTable = "UPDATE students SET name = 'Rohith Reddy' WHERE rollno = 101";

		try {//driver connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection created");
			//create statement for query execution
			java.sql.Statement stmt = con.createStatement();
			//call create query using statement
			stmt.executeUpdate(sql);
			System.out.println("Students table created");
			//call insert query using statement
			int rowInserted=stmt.executeUpdate(insertSQL);
			if(rowInserted>0) {
				System.out.println("New Record Inserted");
				int rowUpdated = stmt.executeUpdate(updateTable);
				if (rowUpdated > 0) {
					System.out.println("✅ Record updated successfully");
				}
				
			}//print table
			ResultSet rs = stmt.executeQuery("Select * from students");
			System.out.println("rollno\t name\t Percentage\t Email");
			//while loop for fetching all the table records
			while(rs.next()) {
				int rollno=rs.getInt("rollno");
				String name=rs.getString("name");
				int per=rs.getInt("per");
				String email=rs.getString("email");
				System.out.println(rollno+"\t"+name+"\t"+per+"\t"+email);
				//System.out.println("rollno:"+rollno +"\tname:"+name+" \tper:"+per+"\temail"+email);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);

		}
	}
}
