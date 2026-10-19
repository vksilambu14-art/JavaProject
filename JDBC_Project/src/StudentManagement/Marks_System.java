package StudentManagement;
import java.util.*;
import java.sql.*;
public class Marks_System {
	   public static void addMarks(int enrollmentId, String exam, double marks)throws SQLException {
	        if (marks < 0) 
	        {
	            throw new IllegalArgumentException("Marks cannot be negative.");
	        }

	        String checkSql ="SELECT c.max_marks FROM enrollments e " +
	        				 "JOIN courses c ON e.course_id=c.course_id " +
	        				 "WHERE e.enrollment_id=?";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement check = con.prepareStatement(checkSql)) {

	            check.setInt(1, enrollmentId);

	            try (ResultSet rs = check.executeQuery()) {

	                if (!rs.next()) {
	                    throw new IllegalArgumentException( "Enrollment ID not found.");
	                }

	                int maxMarks = rs.getInt("max_marks");

	                if (marks > maxMarks) {
	                    throw new IllegalArgumentException("Marks cannot exceed " + maxMarks);
	                }
	            }

	            String sql ="INSERT INTO marks(enrollment_id,exam_name,marks_obtained) " +"VALUES(?,?,?)";

	            try (PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setInt(1, enrollmentId);
	                ps.setString(2, exam);
	                ps.setDouble(3, marks);
	                ps.executeUpdate();

	                System.out.println("Marks saved!");
	            }
	        }
	    }

	    public static void viewMarks() throws SQLException {
	        String sql ="SELECT m.mark_id,s.name,c.course_name,m.exam_name," +
	            "m.marks_obtained,c.max_marks " +
	            "FROM marks m " +
	            "JOIN enrollments e ON m.enrollment_id=e.enrollment_id " +
	            "JOIN students s ON e.student_id=s.student_id " +
	            "JOIN courses c ON e.course_id=c.course_id " +
	            "ORDER BY m.mark_id";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                System.out.println("Mark ID: " + rs.getInt("mark_id") +
	                    " | " + rs.getString("name") +
	                    " | " + rs.getString("course_name") +
	                    " | " + rs.getString("exam_name") +
	                    " | " + rs.getDouble("marks_obtained") +
	                    "/" + rs.getInt("max_marks"));
	            }
	        }
	    }

	    public static void updateMarks(int markId, double newMarks)throws SQLException {
	        if (newMarks < 0) {
	            throw new IllegalArgumentException("Marks cannot be negative.");
	        }

	        String sql ="UPDATE marks m " +"JOIN enrollments e ON m.enrollment_id=e.enrollment_id " +
	            "JOIN courses c ON e.course_id=c.course_id " +
	            "SET m.marks_obtained=? " +
	            "WHERE m.mark_id=? AND ? <= c.max_marks";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setDouble(1, newMarks);
	            ps.setInt(2, markId);
	            ps.setDouble(3, newMarks);

	            int rows = ps.executeUpdate();

	            if(rows>0) {
                	System.out.println("Marks Updated successfully!");
                	
                }
                else {
                         System.out.println("Marks not updated. Check ID and maximum marks.");
                }
	        }
	    }

	    public static void deleteMarks(int markId) throws SQLException {

	        String sql = "DELETE FROM marks WHERE mark_id=?";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, markId);

	            int rows = ps.executeUpdate();
	            if(rows>0) {
                	System.out.println("Marks Deleted successfully!");
                	
                }
                else {
                         System.out.println("Marks not Found.");
                }
	        }
	    }

	    public static void examResult(int enrollmentId) throws SQLException {
	    		String sql ="SELECT s.name,c.course_name,c.max_marks," +
	            "m.exam_name,m.marks_obtained " +
	            "FROM marks m " +
	            "JOIN enrollments e ON m.enrollment_id=e.enrollment_id " +
	            "JOIN students s ON e.student_id=s.student_id " +
	            "JOIN courses c ON e.course_id=c.course_id " +
	            "WHERE e.enrollment_id=? ORDER BY m.mark_id";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, enrollmentId);

	            try (ResultSet rs = ps.executeQuery()) {

	                boolean found = false;

	                while (rs.next()) {
	                    found = true;

	                    double marks = rs.getDouble("marks_obtained");
	                    int max = rs.getInt("max_marks");
	                    double percentage = marks * 100.0 / max;

	                    System.out.println("\n--- EXAM RESULT ---");
	                    System.out.println("Student: " + rs.getString("name"));
	                    System.out.println("Course: " + rs.getString("course_name"));
	                    System.out.println("Exam: " + rs.getString("exam_name"));
	                    System.out.println("Marks: " + marks + "/" + max);
	                    System.out.printf("Percentage: %.2f%%%n", percentage);
	                    if(percentage >= 40) {
	                    	System.out.println(  "PASS" );
	                    }
	                    else {
	                    	System.out.println(  "FAIL");
	                    }
	                    
	                }

	                if (!found) {
	                    System.out.println("No marks found.");
	                }
	            }
	        }
	    }

	    public  static void overallResult(int enrollmentId) throws SQLException {

	        String sql ="SELECT s.name,c.course_name," +
	            "SUM(m.marks_obtained) AS total_obtained," +
	            "SUM(c.max_marks) AS total_max," +
	            "SUM(CASE WHEN m.marks_obtained < c.max_marks * 0.40 " +
	            "THEN 1 ELSE 0 END) AS failed_exams," +
	            "COUNT(m.mark_id) AS exam_count " +
	            "FROM marks m " +
	            "JOIN enrollments e ON m.enrollment_id=e.enrollment_id " +
	            "JOIN students s ON e.student_id=s.student_id " +
	            "JOIN courses c ON e.course_id=c.course_id " +
	            "WHERE e.enrollment_id=? " +
	            "GROUP BY s.name,c.course_name";

	        try (Connection con = StudentDB_connection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, enrollmentId);

	            try (ResultSet rs = ps.executeQuery()) {

	                if (rs.next()) {

	                    double total = rs.getDouble("total_obtained");
	                    double max = rs.getDouble("total_max");
	                    int failed = rs.getInt("failed_exams");
	                    int count = rs.getInt("exam_count");

	                    double percentage = total * 100.0 / max;

	                    System.out.println("\n===== OVERALL RESULT =====");
	                    System.out.println("Student: " + rs.getString("name"));
	                    System.out.println("Course: " + rs.getString("course_name"));
	                    System.out.println("Exams completed: " + count);
	                    System.out.println("Total marks: " + total + "/" + max);
	                    System.out.printf("Overall percentage: %.2f%%%n",percentage);
	                    if(failed == 0) {
	                    	System.out.println("OVERALL RESULT: PASS");
	                    }
	                    else {
	                    	System.out.println(  "OVERALL RESULT: FAIL");
	                    }
	                } 
	                else {
	                    System.out.println("No marks found for this enrollment.");
	                }
	            }
	        }
	    }
}
