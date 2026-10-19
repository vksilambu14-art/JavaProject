package StudentManagement;
import java.sql.*;
public class StudentDB_connection {
	static final String url="jdbc:mysql://localhost:3306/student_management";
	static final String user="root";
	static final String pass="root";
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}






