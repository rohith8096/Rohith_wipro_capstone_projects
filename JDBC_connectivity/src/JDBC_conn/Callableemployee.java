package JDBC_conn;

import java.sql.*;

public class Callableemployee {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "Rohith@8096";

		try (Connection con = DriverManager.getConnection(url, user, password)) {

			// 1. Call addBonus()
			CallableStatement addBonus = con.prepareCall("{CALL addBonus()}");
			addBonus.executeUpdate();
			System.out.println(" Bonus of 5000 added to all employees.\n");

			// 2. Call getDuplicateNames()
			CallableStatement getDupNames = con.prepareCall("{CALL getDuplicateNames()}");
			ResultSet rs1 = getDupNames.executeQuery();

			System.out.println(" Employees with Duplicate Names:");
			System.out.println("ID\tName\t\t\tSalary");
			System.out.println("----------------------------------------------");
			while (rs1.next()) {
				int emp_id = rs1.getInt("emp_id");
				String name = rs1.getString("name");
				int salary = rs1.getInt("salary");
				System.out.printf("%d\t%-20s\t%d\n", emp_id, name, salary);
			}

			// 3. Call getHighLowSalary()
			CallableStatement getHighLow = con.prepareCall("{CALL getHighLowSalary()}");
			ResultSet rs2 = getHighLow.executeQuery();

			System.out.println("\nEmployees with Highest and Lowest Salary:");
			System.out.println("ID\tName\t\t\tSalary");
			System.out.println("----------------------------------------------");
			while (rs2.next()) {
				int emp_id = rs2.getInt("emp_id");
				String name = rs2.getString("name");
				int salary = rs2.getInt("salary");
				System.out.printf("%d\t%-20s\t%d\n", emp_id, name, salary);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
