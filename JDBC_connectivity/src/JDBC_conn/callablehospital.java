package JDBC_conn;

import java.sql.*;

public class callablehospital {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "Rohith@8096";

		try (Connection con = DriverManager.getConnection(url, user, password)) {

			// 1. Average Patient Count per Day
			CallableStatement avgStmt = con.prepareCall("{CALL avgPatientCountPerDay()}");
			ResultSet rs1 = avgStmt.executeQuery();
			if (rs1.next()) {
				double avg = rs1.getDouble("average_daily_patients");
				System.out.println("Average Patients Per Day: " + avg);
			}

			// 2. Patients from same ward
			CallableStatement sameWardStmt = con.prepareCall("{CALL getPatientsFromSameWard()}");
			ResultSet rs2 = sameWardStmt.executeQuery();
			System.out.println("\nPatients from Same Wards:");
			while (rs2.next()) {
				System.out.printf("ID: %d, Name: %s, Ward: %s, Date: %s\n", rs2.getInt("patient_id"),
						rs2.getString("name"), rs2.getString("ward"), rs2.getDate("admission_date"));
			}

			// 3. Sorted by admission date
			CallableStatement sortedStmt = con.prepareCall("{CALL getPatientsSortedByAdmissionDate()}");
			ResultSet rs3 = sortedStmt.executeQuery();
			System.out.println("\nPatients Sorted by Admission Date:");
			while (rs3.next()) {
				System.out.printf("ID: %d, Name: %s, Ward: %s, Date: %s\n", rs3.getInt("patient_id"),
						rs3.getString("name"), rs3.getString("ward"), rs3.getDate("admission_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
