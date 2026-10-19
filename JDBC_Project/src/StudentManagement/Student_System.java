package StudentManagement;
import java.sql.*;
import java.util.*;
import StudentManagement.StudentDB_connection;      
public class Student_System {
	
	 Scanner sc= new Scanner(System.in);
	public static   void addStudents(String name,String email, String phone) throws SQLException{
        String sql="INSERT INTO students(name,email,phone)VALUES(?,?,?)";
        try(Connection con =StudentDB_connection.getConnection();
        	PreparedStatement ps=con.prepareStatement(sql)){
        	ps.setString(1,name);
        	ps.setString(2, email);
        	ps.setString(3,phone);
        	ps.executeUpdate();
        	System.out.println("Student added successfully!");
        }
        
	}
	public static  void viewStudents()throws SQLException{
		String sql="SELECT * FROM students ORDER BY student_id";
		try(Connection con =StudentDB_connection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery()){
			System.out.println("\n--- STUDENT DETAILS ---");
			while(rs.next()) {
				System.out.println(
	                    "ID: " + rs.getInt("student_id") +
	                    " | Name: " + rs.getString("name") +
	                    " | Email: " + rs.getString("email") +
	                    " | Phone: " + rs.getString("phone"));
	            }
		}
	}
	
	public static void updateStudent(int id, String name,String email, String phone)throws SQLException{
        String sql="UPDATE students SET name=?,email=?,phone=?"+"WHERE student_id=?";
        try(Connection con=StudentDB_connection.getConnection();
        	PreparedStatement ps=con.prepareStatement(sql)){
        	ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);
            int rows=ps.executeUpdate();
            if(rows>0) {
            	System.out.println("Student updated successfully!");
            }
            else {
                     System.out.println("Student ID not found.");
            }
        }
	}
	public static void searchByID(int id)throws SQLException{
		String sql="SELECT * FROM students WHERE student_id=?";
		try(Connection con =StudentDB_connection.getConnection();
				PreparedStatement ps=con.prepareStatement(sql)){
				ps.setInt(1,id);
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						System.out.println(
		                        rs.getInt("student_id") + " | " +
		                        rs.getString("name") + " | " +
		                        rs.getString("email") + " | " +
		                        rs.getString("phone"));
		                }
					else {
	                    System.out.println("Student not found.");
	                }
					}
				}		
	}
	public static void deleteStudents(int id)throws SQLException{
		String sql="DELETE FROM students WHERE student_id=?";
		try(Connection con =StudentDB_connection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql)){
			ps.setInt(1,id);
			int rows=ps.executeUpdate();
            if(rows>0) {
            	System.out.println("Student Deleted successfully!");
            }
            else {
                     System.out.println("Student ID not found.");
            }
			
		}
	}
	

}
