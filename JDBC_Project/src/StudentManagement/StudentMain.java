package StudentManagement;
import java.util.*;
import java.sql.*;

public class StudentMain {
	static Scanner sc = new Scanner(System.in);
	public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }
	 public static String readText(String message) {
	        System.out.print(message);
	        return sc.nextLine().trim();
	    }
	 public static double readDouble(String message) {
	        while (true) {
	            try {
	                System.out.print(message);
	                return Double.parseDouble(sc.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Enter a valid number.");
	            }
	        }
	    }
	public static void main(String[] args) {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            DatabaseSetUp.createDatabase();
	            while (true) {

	                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
	                System.out.println("1.  Register Student");
	                System.out.println("2.  View Students");
	                System.out.println("3.  Search Student");
	                System.out.println("4.  Update Student");
	                System.out.println("5.  Delete Student");

	                System.out.println("6.  Add Course");
	                System.out.println("7.  View Courses");
	                System.out.println("8.  Search Course");
	                System.out.println("9.  Update Course");
	                System.out.println("10. Delete Course");

	                System.out.println("11. Enroll Student");
	                System.out.println("12. View Enrollments");
	                System.out.println("13. Update Enrollment");
	                System.out.println("14. Cancel Enrollment");

	                System.out.println("15. Mark Attendance");
	                System.out.println("16. View Attendance");
	                System.out.println("17. Update Attendance");
	                System.out.println("18. Delete Attendance");

	                System.out.println("19. Enter Marks");
	                System.out.println("20. View Marks");
	                System.out.println("21. Update Marks");
	                System.out.println("22. Delete Marks");

	                System.out.println("23. Generate Exam Result");
	                System.out.println("24. Generate Overall Result");
	                System.out.println("0.  Exit");

	                int choice = readInt("Enter your choice: ");
	                try {
	                    switch (choice) {

	                        case 1:
	                        	Student_System.addStudents(
	                                readText("Student name: "),
	                                readText("Email: "),
	                                readText("Phone: "));
	                            break;

	                        case 2:
	                        	Student_System.viewStudents();
	                            break;

	                        case 3:
	                        	Student_System.searchByID(
	                                readInt("Student ID: "));
	                            break;

	                        case 4:
	                        	Student_System.updateStudent(
	                                readInt("Student ID: "),
	                                readText("New name: "),
	                                readText("New email: "),
	                                readText("New phone: "));
	                            break;

	                        case 5:
	                        	Student_System.deleteStudents(
	                                readInt("Student ID to delete: "));
	                            break;

	                        case 6:
	                        	Course_System.addCourse(
	                                readText("Course name: "),
	                                readInt("Maximum marks per exam: "));
	                            break;

	                        case 7:
	                        	Course_System.viewCourses();
	                            break;

	                        case 8:
	                        	Course_System.searchCourse(
	                                readInt("Course ID: "));
	                            break;

	                        case 9:
	                        	Course_System.updateCourse(
	                                readInt("Course ID: "),
	                                readText("New course name: "),
	                                readInt("New maximum marks: "));
	                            break;

	                        case 10:
	                        	Course_System.deleteCourse(
	                                readInt("Course ID to delete: "));
	                            break;

	                        case 11:
	                        	Enrollment_System.enRoll(
	                                readInt("Student ID: "),
	                                readInt("Course ID: "));
	                            break;

	                        case 12:
	                        	Enrollment_System.viewEnrollments();
	                            break;

	                        case 13:
	                        	Enrollment_System.updateEnrollment(
	                                readInt("Enrollment ID: "),
	                                readInt("New course ID: "));
	                            break;

	                        case 14:
	                        	Enrollment_System.deleteEnrollment(
	                                readInt("Enrollment ID to cancel: "));
	                            break;

	                        case 15:
	                        	Attendance_System.markAttendance(
	                                readInt("Enrollment ID: "),
	                                readText("Date (YYYY-MM-DD): "),
	                                readText("Status (Present/Absent): "));
	                            break;

	                        case 16:
	                        	Attendance_System.viewAttendance();
	                            break;

	                        case 17:
	                        	Attendance_System.updateAttendance(
	                                readInt("Attendance ID: "),
	                                readText("New status (Present/Absent): "));
	                            break;

	                        case 18:
	                        	Attendance_System.deleteAttendance(
	                                readInt("Attendance ID: "));
	                            break;

	                        case 19:
	                        	Marks_System.addMarks(
	                                readInt("Enrollment ID: "),
	                                readText("Exam name: "),
	                                readDouble("Marks obtained: "));
	                            break;

	                        case 20:
	                        	Marks_System.viewMarks();
	                            break;

	                        case 21:
	                        	Marks_System.updateMarks(
	                                readInt("Mark ID: "),
	                                readDouble("New marks: "));
	                            break;

	                        case 22:
	                        	Marks_System.deleteMarks(
	                                readInt("Mark ID: "));
	                            break;

	                        case 23:
	                        	Marks_System.examResult(
	                                readInt("Enrollment ID: "));
	                            break;

	                        case 24:
	                        	Marks_System.overallResult(
	                                readInt("Enrollment ID: "));
	                            break;

	                        case 0:
	                            System.out.println("Thank you!");
	                            sc.close();
	                            return;

	                        default:
	                            System.out.println("Invalid choice.");
	                    }

	                } catch (SQLException e) {
	                    System.out.println("Database operation failed: " + e.getMessage());

	                } catch (IllegalArgumentException e) {
	                    System.out.println(
	                        "Invalid input: " + e.getMessage());
	                }
	            }

	        } catch (ClassNotFoundException e) {
	            System.out.println("MySQL JDBC driver not found. Check the JAR file.");

	        } catch (SQLException e) {
	            System.out.println("Database setup failed: " + e.getMessage());

	        } finally {
	            // Scanner is closed on normal exit in case 0.
	        }
		 }

	

}
