package StudentManagementSystem;
import java.util.ArrayList;
import java.util.Scanner;
public class StudentMain {
	public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);

     StudentService service =new StudentService();

     StudentFileService fileService =new StudentFileService();

     int choice;

     do {

         System.out.println();
         System.out.println("================================");
         System.out.println("    STUDENT MANAGEMENT SYSTEM");
         System.out.println("================================");

         System.out.println("1. Add Student");
         System.out.println("2. Display All Students");
         System.out.println("3. Search by ID");
         System.out.println("4. Search by Name");
         System.out.println("5. Update Marks");
         System.out.println("6. Update Attendance");
         System.out.println("7. Calculate Grade");
         System.out.println("8. Remove Student");
         System.out.println("9. Display Topper");
         System.out.println("10. Save Students");
         System.out.println("11. Exit");

         System.out.print("Enter choice : ");
         choice = sc.nextInt();
         sc.nextLine();

         switch (choice) {

             case 1:

                 System.out.print("Enter Student ID : ");
                 int id = sc.nextInt();
                 sc.nextLine();

                 System.out.print("Enter Name : ");
                 String name = sc.nextLine();

                 System.out.print("Enter Email : ");
                 String email = sc.nextLine();

                 System.out.print("Enter Marks : ");
                 double marks = sc.nextDouble();

                 System.out.print("Enter Attendance : ");
                 int attendance = sc.nextInt();

                 System.out.println();
                 System.out.println("1. ACTIVE");
                 System.out.println("2. INACTIVE");
                 System.out.println("3. COMPLETED");

                 System.out.print("Enter Status : ");
                 int statusChoice = sc.nextInt();

                 StudentStatus status;

                 if (statusChoice == 1) {

                     status = StudentStatus.ACTIVE;

                 } else if (statusChoice == 2) {

                     status = StudentStatus.INACTIVE;

                 } else {

                     status = StudentStatus.COMPLETED;
                 }

                 System.out.println();
                 System.out.println("----- COURSE DETAILS -----");

                 System.out.print("Enter Course ID : ");
                 int courseId = sc.nextInt();
                 sc.nextLine();

                 System.out.print("Enter Course Name : ");
                 String courseName = sc.nextLine();

                 System.out.print("Enter Course Fee : ");
                 double fee = sc.nextDouble();

                 Course course =
                     new Course(
                         courseId,
                         courseName,
                         fee
                     );

                 Student student =
                     new Student(
                         id,
                         name,
                         email,
                         marks,
                         attendance,
                         status,
                         course
                     );

                 service.addStudent(student);

                 break;

             case 2:

                 service.displayAllStudents();

                 break;

             case 3:

                 System.out.print("Enter Student ID : ");
                 int searchId = sc.nextInt();

                 Student found =
                     service.searchById(searchId);

                 if (found != null) {

                     System.out.println(
                         "Student Name : " +
                         found.getName()
                     );

                     System.out.println(
                         "Course : " +
                         found.getCourse().getCourseName()
                     );

                     System.out.println(
                         "Marks : " +
                         found.getMarks()
                     );

                 } else {

                     System.out.println(
                         "Student not found."
                     );
                 }

                 break;

             case 4:

                 System.out.print("Enter Name : ");
                 String searchName = sc.nextLine();

                 service.searchByName(searchName);

                 break;

             case 5:

                 System.out.print("Enter Student ID : ");
                 int marksId = sc.nextInt();

                 System.out.print("Enter New Marks : ");
                 double newMarks = sc.nextDouble();

                 service.updateMarks(
                     marksId,
                     newMarks
                 );

                 break;

             case 6:

                 System.out.print("Enter Student ID : ");
                 int attendanceId = sc.nextInt();

                 System.out.print(
                     "Enter New Attendance : "
                 );

                 int newAttendance = sc.nextInt();

                 service.updateAttendance(
                     attendanceId,
                     newAttendance
                 );

                 break;

             case 7:

                 System.out.print("Enter Marks : ");
                 double gradeMarks = sc.nextDouble();

                 System.out.println(
                     "Grade : " +
                     service.calculateGrade(gradeMarks)
                 );

                 break;

             case 8:

                 System.out.print("Enter Student ID : ");
                 int removeId = sc.nextInt();

                 service.removeStudent(removeId);

                 break;

             case 9:

                 service.displayTopper();

                 break;

             case 10:

                 fileService.saveStudents(
                     service.getStudents()
                 );

                 break;

             case 11:

                 fileService.saveStudents(
                     service.getStudents()
                 );

                 System.out.println(
                     "Thank you!"
                 );

                 break;

             default:

                 System.out.println(
                     "Invalid choice."
                 );
         }

     } while (choice != 11);

     sc.close();
 }

}
