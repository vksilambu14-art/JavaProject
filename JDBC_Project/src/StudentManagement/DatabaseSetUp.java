package StudentManagement;
import java.sql.*;
import StudentManagement.StudentDB_connection;
public class DatabaseSetUp {
	public static void createDatabase() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/";

        try (Connection con = DriverManager.getConnection(
                    url, "root", "root");
             Statement st = con.createStatement()) {

            st.executeUpdate(
                "CREATE DATABASE IF NOT EXISTS student_management");
        }

        createTable();
    }

	public static void createTable()throws SQLException{
		try(Connection con=StudentDB_connection.getConnection();
				Statement st=con.createStatement()){
				st.executeUpdate(
							"CREATE TABLE IF NOT EXISTS students("+
							"student_id INT PRIMARY KEY AUTO_INCREMENT,"+
							"name varchar(50) NOT NULL,"+
							"email varchar(100) UNIQUE NOT NULL,"+	
							"phone varchar(15))");
				 st.executeUpdate(
			                "CREATE TABLE IF NOT EXISTS courses (" +
			                "course_id INT PRIMARY KEY AUTO_INCREMENT, " +
			                "course_name VARCHAR(100) NOT NULL, " +
			                "max_marks INT NOT NULL)AUTO_INCREMENT=101"); 
				 st.executeUpdate(
			                "CREATE TABLE IF NOT EXISTS enrollments (" +
			                "enrollment_id INT PRIMARY KEY AUTO_INCREMENT, " +
			                "student_id INT NOT NULL, " +
			                "course_id INT NOT NULL, " +
			                "enrollment_date DATE NOT NULL, " +
			                "UNIQUE(student_id, course_id), " +
			                "FOREIGN KEY(student_id) REFERENCES students(student_id), " +
			                "FOREIGN KEY(course_id) REFERENCES courses(course_id))");
				 st.executeUpdate(
			                "CREATE TABLE IF NOT EXISTS attendance (" +
			                "attendance_id INT PRIMARY KEY AUTO_INCREMENT, " +
			                "enrollment_id INT NOT NULL, " +
			                "attendance_date DATE NOT NULL, " +
			                "status ENUM('Present','Absent') NOT NULL, " +
			                "UNIQUE(enrollment_id, attendance_date), " +
			                "FOREIGN KEY(enrollment_id) " +
			                "REFERENCES enrollments(enrollment_id))");
				 st.executeUpdate(
			                "CREATE TABLE IF NOT EXISTS marks (" +
			                "mark_id INT PRIMARY KEY AUTO_INCREMENT, " +
			                "enrollment_id INT NOT NULL, " +
			                "exam_name VARCHAR(50) NOT NULL, " +
			                "marks_obtained DECIMAL(6,2) NOT NULL, " +
			                "UNIQUE(enrollment_id, exam_name), " +
			                "FOREIGN KEY(enrollment_id) " +
			                "REFERENCES enrollments(enrollment_id))");
				 System.out.println("Database and tables created successfully!");
			}
	
		
	}

	
}
