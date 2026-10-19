package StudentManagement;
import java.util.*;
import java.sql.*;
import StudentManagement.StudentDB_connection;
public class Course_System {
 public static void addCourse(String name, int maxMarks)throws SQLException{
	 if(maxMarks<=0) {
		 throw new IllegalArgumentException("Maximum marks must be positive.");
	 }
	 String sql="INSERT INTO courses(course_name,max_marks)Values(?,?)";
	 try(Connection con=StudentDB_connection.getConnection();
		 PreparedStatement ps=con.prepareStatement(sql)){
		 ps.setString(1, name);
         ps.setInt(2, maxMarks);
         ps.executeUpdate();
         System.out.println("Course added!");
	 }
 }
 public static void viewCourses()throws SQLException{
	 String sql="SELECT * FROM courses Order BY course_id";
	 try(Connection con=StudentDB_connection.getConnection();
			 PreparedStatement ps=con.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery()){
		 while(rs.next()) {
			 System.out.println(
	                    rs.getInt("course_id") + " | " +
	                    rs.getString("course_name") + " | Max: " +
	                    rs.getInt("max_marks"));
		 }
	 }
 }
 public static void searchCourse(int id)throws SQLException{
	 String sql="SELECT * FROM courses WHERE course_id=?";
	 try(Connection con =StudentDB_connection.getConnection();
				PreparedStatement ps=con.prepareStatement(sql)){
				ps.setInt(1,id);
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						System.out.println(
		                        rs.getInt("course_id") + " | " +
		                        rs.getString("course_name") + " | " +
		                        rs.getInt("max_marks"));
		                } else {
		                    System.out.println("Course not found.");
		                }
				}
	 }
 }
 public static void updateCourse(int id, String name, int maxMarks)
         throws SQLException{
	 if (maxMarks <= 0) {
         throw new IllegalArgumentException("Maximum marks must be positive.");
     }
	 String sql="Update courses SET course_name=?,max_marks=? Where course_id=? ";
	 try (Connection con = StudentDB_connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, maxMarks);
            ps.setInt(3, id);
            int rows=ps.executeUpdate();
            if(rows>0) {
            	System.out.println("Course updated successfully!");
            	
            }
            else {
                     System.out.println("Course not found.");
            }
	 }
         }
 
 public static void deleteCourse(int id)throws SQLException{
	 String sql="DELETE FROM courses WHERE course_id=?";
	 try(Connection con =StudentDB_connection.getConnection();
				PreparedStatement ps=con.prepareStatement(sql)){
				ps.setInt(1,id);
				int rows=ps.executeUpdate();
	            if(rows>0) {
	            	System.out.println("Course Deleted successfully!");
	            	
	            }
	            else {
	                     System.out.println("Course not found.");
	            }
	 }
 }
 
 
 
}
