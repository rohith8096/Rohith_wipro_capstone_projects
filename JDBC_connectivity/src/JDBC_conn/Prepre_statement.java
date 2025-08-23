package JDBC_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Prepre_statement {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "Rohith@8096";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("Connection created");
		String query = "Insert into student1 (rollno, name, age) values (?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student(101, "Rohith Reddy", 20));
		students.add(new Student(102, "Ajay", 21));
		students.add(new Student(103, "Sai", 22));

		for (Student s : students) {
			pst.setInt(1, s.rollno);
			pst.setString(2, s.name);
			pst.setInt(3, s.age);
			pst.executeUpdate();
		}

		System.out.println("Students inserted successfully.");
		String selectQuery = "SELECT * FROM student1";
		PreparedStatement selectPst = con.prepareStatement(selectQuery);
		ResultSet rs = selectPst.executeQuery();

		System.out.println("\nStudent Records from Database:");
		while (rs.next()) {
			int rollno = rs.getInt("rollno");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println("Roll No: " + rollno + ", Name: " + name + ", Age: " + age);
		}

		con.close();
	}
}
