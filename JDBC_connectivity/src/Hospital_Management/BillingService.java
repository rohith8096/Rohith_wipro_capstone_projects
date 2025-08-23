package Hospital_Management;

import java.sql.*;
import java.util.Scanner;

public class BillingService {
	public static void generateBill(Scanner sc) {
		try (Connection con = DBConnection.getConnection()) {
			System.out.print("Enter Appointment ID: ");
			int appointmentId = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter Patient ID: ");
			int patientId = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter Total Amount: ");
			double amount = sc.nextDouble();
			sc.nextLine();
			System.out.print("Enter Discharge Date (YYYY-MM-DD): ");
			String date = sc.nextLine();
			System.out.print("Enter Summary: ");
			String summary = sc.nextLine();

			String sql = "INSERT INTO billing (patient_id, appointment_id, total_amount, discharge_date, summary) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			ps.setInt(2, appointmentId);
			ps.setDouble(3, amount);
			ps.setDate(4, Date.valueOf(date));
			ps.setString(5, summary);

			ps.executeUpdate();
			System.out.println("✅ Billing generated.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
