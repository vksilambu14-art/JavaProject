package StudentManagement;
import StudentManagement.StudentDB_connection;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Enrollment_System {
public  static void enRoll(int studentId, int courseId)throws SQLException{
	String sql="INSERT INTO enrollments(student_id,course_id,enrollment_date)"+"Values(?,?,?)";
	try(Connection con =StudentDB_connection.getConnection()){
		con.setAutoCommit(false);
		try(PreparedStatement ps=con.prepareStatement(sql)){
			ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, Date.valueOf(
                java.time.LocalDate.now()));

            ps.executeUpdate();
            con.commit();
            System.out.println("Enrollment successful!");
		}
		catch(SQLException e) {
			con.rollback();
			throw e;
		}
		finally {
			con.setAutoCommit(true);
		}
	}		
}

public static void viewEnrollments()throws SQLException{
	 String sql =
		        "SELECT e.enrollment_id, s.name, c.course_name, e.enrollment_date " +
		        "FROM enrollments e " +
		        "JOIN students s ON e.student_id = s.student_id " +
		        "JOIN courses c ON e.course_id = c.course_id " +
		        "ORDER BY e.enrollment_id";

		    try (Connection con = StudentDB_connection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {
		            System.out.println(
		                rs.getInt("enrollment_id") + " | " +
		                rs.getString("name") + " | " +
		                rs.getString("course_name") + " | " +
		                rs.getDate("enrollment_date")
		            );
		        }
		    }
}

public static void updateEnrollment(int enrollmentId, int newCourseId)
        throws SQLException{
	 String sql ="UPDATE enrollments SET course_id=? WHERE enrollment_id=?";
	 try (Connection con = StudentDB_connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
		 ps.setInt(1, newCourseId);
         ps.setInt(2, enrollmentId);
         int rows = ps.executeUpdate();
         if(rows>0) {
         	System.out.println("Enrollment updated successfully!");
         	
         }
         else {
                  System.out.println("Enrollment  not found.");
         }
	 }
}

public static void deleteEnrollment(int enrollmentId) throws SQLException {
	String sql ="DELETE FROM enrollments WHERE enrollment_id=?";
	try (Connection con =StudentDB_connection.getConnection()){
		con.setAutoCommit(false);
		try(PreparedStatement ps=con.prepareStatement(sql)){
			 ps.setInt(1, enrollmentId);
             int rows = ps.executeUpdate();

             con.commit();
             if(rows>0) {
	            	System.out.println("Enrollment cancelled successfully!");
	            	
	            }
	            else {
	                     System.out.println("Enrollment not found.");
	            }
		}
		catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
		
	}
}










}
