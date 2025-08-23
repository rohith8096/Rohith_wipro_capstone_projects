package Hospital_Management;
import java.sql.*;
import java.util.Scanner;

public class AppointmentService {
    public static void bookAppointment(Scanner sc) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Patient ID: ");
            int patientId = sc.nextInt();
            System.out.print("Enter Doctor ID: ");
            int doctorId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Reason: ");
            String reason = sc.nextLine();

            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, reason) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, reason);

            ps.executeUpdate();
            System.out.println("✅ Appointment booked.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

