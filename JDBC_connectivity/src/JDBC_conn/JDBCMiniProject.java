package JDBC_conn;

import java.sql.*;

public class JDBCMiniProject {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "Rohith@8096";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();

			// Create student table
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS student (id INT PRIMARY KEY, name VARCHAR(50), city VARCHAR(50), percentage FLOAT)");

			// Insert records
			st.executeUpdate(
					"INSERT INTO student (id, name, city, percentage) VALUES (1, 'Rohith', 'Hyderabad', 80), (2, 'Ajay', 'Warangal', 80.2), (3, 'Nithin', 'Hyderabad', 85), (4, 'Bhanu', 'SangaReddy', 81.4)");

			// Printing student records
			ResultSet rs = st.executeQuery("SELECT * FROM student");
			System.out.println("All Students:");
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getFloat(4));
			}

			// Update a record
			st.executeUpdate("UPDATE student SET percentage = 84.0 WHERE id = 3");
			System.out.println("Updated percentage to 84.0. in id=3");

			// highest percentage students
			rs = st.executeQuery("SELECT * FROM student WHERE percentage = (SELECT MAX(percentage) FROM student)");
			System.out.println("\nHighest Percentage Student(s):");
			while (rs.next()) {
				System.out.println(rs.getString("name") + " - " + rs.getFloat("percentage"));
			}

			// ascending order by percentage
			rs = st.executeQuery("SELECT * FROM student ORDER BY percentage ASC");
			System.out.println("\nStudents Ordered by Percentage:");
			while (rs.next()) {
				System.out.println(rs.getString("name") + " - " + rs.getFloat("percentage"));
			}

			// students from the same city
			rs = st.executeQuery("SELECT * FROM student WHERE city = 'Hyderabad'");
			System.out.println("\nStudents from Hyderabad:");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}

			
			  // Adding new column
			  st.executeUpdate("ALTER TABLE student ADD COLUMN age INT");
			  System.out.println("Column 'age' added.");
			  
			 
			 

			// Modify column datatype
			st.executeUpdate("ALTER TABLE student MODIFY COLUMN name VARCHAR(100)");
			System.out.println("Column 'name' modified to VARCHAR(100).");

			
			  // Changing table name
			  st.executeUpdate("RENAME TABLE student TO student_info");
			  System.out.println("Table name changed to student_info");
			 

			// Delete column
			st.executeUpdate("ALTER TABLE student_info DROP COLUMN age");
			System.out.println("age column deleted");

			// Deleting single row
			st.executeUpdate("DELETE FROM student_info WHERE id = 2");
			System.out.println("Deleted student with id = 2");
			// Delete all records
			st.executeUpdate("DELETE FROM student_info");
			System.out.println("All records deleted from student_info");


			// Delete entire table
			st.executeUpdate("DROP TABLE IF EXISTS student_info");
			System.out.println("Dropped table student_info");

			// Drop old student table (if exists)
			st.executeUpdate("DROP TABLE IF EXISTS student");
			System.out.println("Dropped table student");

			// Creating new tables for joins
			st.executeUpdate("CREATE TABLE IF NOT EXISTS student (id INT PRIMARY KEY, name VARCHAR(50))");
			st.executeUpdate("INSERT INTO student (id, name) VALUES (1, 'Rohith'), (2, 'Ajay'), (3, 'Nithin')");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS institute (id INT PRIMARY KEY, name VARCHAR(50))");
			st.executeUpdate("INSERT INTO institute (id, name) VALUES (1, 'NIT'), (2, 'IIT'), (3, 'BITS')");

			System.out.println("Join data inserted into student and institute.");

			// INNER JOIN
			rs = st.executeQuery("SELECT s.name FROM student s INNER JOIN institute i ON s.id = i.id");
			System.out.println("\nInner Join (Common Records):");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			// LEFT JOIN
			rs = st.executeQuery("SELECT s.name FROM student s LEFT JOIN institute i ON s.id = i.id");
			System.out.println("\nLeft Join (All Students):");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			// RIGHT JOIN
			rs = st.executeQuery("SELECT i.name FROM student s RIGHT JOIN institute i ON s.id = i.id");
			System.out.println("\nRight Join (All Institutes):");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			// FULL JOIN
			rs = st.executeQuery("SELECT s.name FROM student s LEFT JOIN institute i ON s.id = i.id " + "UNION "
					+ "SELECT i.name FROM student s RIGHT JOIN institute i ON s.id = i.id");
			System.out.println("\nFull Join (All Records):");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 
