package StudentManagement;
import java.util.*;
import java.sql.*;
import StudentManagement.StudentDB_connection;
import java.sql.Date;
public class Attendance_System {
	 public static void markAttendance(int enrollmentId,String date, String status)throws SQLException {
		 if (!status.equalsIgnoreCase("Present")&& !status.equalsIgnoreCase("Absent")) {
		        throw new IllegalArgumentException("Status must be Present or Absent.");
		    }

		    Date attendanceDate = Date.valueOf(date);
		    String sql = "INSERT INTO attendance " + "(enrollment_id, attendance_date, status) "+ "VALUES (?, ?, ?)";
		    try (Connection con = StudentDB_connection.getConnection()) {
		        try (PreparedStatement ps = con.prepareStatement(sql)) {
		        	ps.setInt(1, enrollmentId);
		            ps.setDate(2, attendanceDate);
		            ps.setString(3,status.equalsIgnoreCase("Present") ? "Present" : "Absent");

		            int rows = ps.executeUpdate();
		            if (rows > 0) {
		                System.out.println("Attendance marked successfully!");
		            } else {
		                System.out.println("Attendance was not inserted.");
		            }
		        }
		        catch (SQLException e) {
		            System.out.println("Attendance Error: " + e.getMessage());
		            throw e;
		        }
		    }
		        
		    
		}
	 
	 public static void viewAttendance() throws SQLException{
		 String sql ="SELECT a.attendance_id, e.enrollment_id, s.name, c.course_name, "
		            + "a.attendance_date, a.status "
		            + "FROM attendance a "
		            + "JOIN enrollments e ON a.enrollment_id = e.enrollment_id "
		            + "JOIN students s ON e.student_id = s.student_id "
		            + "JOIN courses c ON e.course_id = c.course_id "
		            + "ORDER BY a.attendance_date DESC";

		    try (Connection con = StudentDB_connection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        boolean found = false;

		        while (rs.next()) {
		            found = true;

		            System.out.println(
		                    "Attendance ID: " + rs.getInt("attendance_id")
		                    + " | Enrollment ID: " + rs.getInt("enrollment_id")
		                    + " | Student: " + rs.getString("name")
		                    + " | Course: " + rs.getString("course_name")
		                    + " | Date: " + rs.getDate("attendance_date")
		                    + " | Status: " + rs.getString("status"));
		        }

		        if (!found) {
		            System.out.println("No attendance records found.");
		        }
		    }
		}
	 
	 
	 public static void updateAttendance(int attendanceId, String status)throws SQLException {

	        if (!status.equalsIgnoreCase("Present") &&!status.equalsIgnoreCase("Absent")) {
	            throw new IllegalArgumentException("Status must be Present or Absent.");
	        }

	        String finalStatus = status.equalsIgnoreCase("Present")? "Present" : "Absent";

	        String sql ="UPDATE attendance SET status=? WHERE attendance_id=?";
	        try (Connection con = StudentDB_connection.getConnection();
	                PreparedStatement ps = con.prepareStatement(sql)) {

	               ps.setString(1, finalStatus);
	               ps.setInt(2, attendanceId);

	               int rows = ps.executeUpdate();
	               if(rows>0) {
	                	System.out.println("Attendance updated successfully!");
	                	
	                }
	                else {
	                         System.out.println("Attendance  not found.");
	                }
	 
	 
	 }
	 }
	 public static void deleteAttendance(int attendanceId) throws SQLException {

	        String sql ="DELETE FROM attendance WHERE attendance_id=?";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, attendanceId);

	            int rows = ps.executeUpdate();
	 
	            if(rows>0) {
                	System.out.println("Attendance Deleted successfully!");
                	
                }
                else {
                         System.out.println("Attendance  not found.");
                }
	        }
	 }
	 
	 
}
