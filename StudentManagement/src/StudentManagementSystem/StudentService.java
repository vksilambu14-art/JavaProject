package StudentManagementSystem;
import java.util.ArrayList;
public class StudentService {
	private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    // ADD
    public void addStudent(Student student) {

        students.add(student);

        System.out.println("Student added successfully.");
    }

    // DISPLAY
    public void displayAllStudents() {

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {

            System.out.println("--------------------------------");
            System.out.println("Student ID   : " + student.getId());
            System.out.println("Name         : " + student.getName());
            System.out.println("Email        : " + student.getEmail());
            System.out.println("Marks        : " + student.getMarks());
            System.out.println("Attendance   : " + student.getAttendance());
            System.out.println("Status       : " + student.getStatus());

            System.out.println(
                "Course ID    : " +
                student.getCourse().getCourseId()
            );

            System.out.println(
                "Course Name  : " +
                student.getCourse().getCourseName()
            );

            System.out.println(
                "Course Fee   : " +
                student.getCourse().getFees()
            );

            System.out.println(
                "Grade        : " +
                calculateGrade(student.getMarks())
            );
        }
    }

    // SEARCH BY ID
    public Student searchById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                return student;
            }
        }

        return null;
    }

    // SEARCH BY NAME
    public void searchByName(String name) {

        boolean found = false;

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {

                System.out.println("--------------------------------");
                System.out.println("Student ID  : " + student.getId());
                System.out.println("Name        : " + student.getName());
                System.out.println("Email       : " + student.getEmail());
                System.out.println("Marks       : " + student.getMarks());
                System.out.println("Attendance  : " + student.getAttendance());
                System.out.println("Status      : " + student.getStatus());
                System.out.println("Course      : " +
                        student.getCourse().getCourseName());

                found = true;
            }
        }

        if (!found) {

            System.out.println("Student not found.");
        }
    }

    // UPDATE MARKS
    public void updateMarks(int id, double newMarks) {

        Student student = searchById(id);

        if (student != null) {

            student.setMarks(newMarks);

            System.out.println("Marks updated successfully.");

        } else {

            System.out.println("Student not found.");
        }
    }


    public void updateAttendance(int id, int newAttendance) {

        Student student = searchById(id);

        if (student != null) {

            student.setAttendance(newAttendance);

            System.out.println(
                "Attendance updated successfully."
            );

        } else {

            System.out.println("Student not found.");
        }
    }

    public String calculateGrade(double marks) {

        if (marks >= 90) {

            return "A";

        } else if (marks >= 80) {

            return "B";

        } else if (marks >= 70) {

            return "C";

        } else if (marks >= 60) {

            return "D";

        } else {

            return "F";
        }
    }

    public void removeStudent(int id) {

        Student student = searchById(id);

        if (student != null) {

            students.remove(student);

            System.out.println(
                "Student removed successfully."
            );

        } else {

            System.out.println("Student not found.");
        }
    }

    public void displayTopper() {

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        Student topper = students.get(0);

        for (Student student : students) {

            if (student.getMarks() > topper.getMarks()) {

                topper = student;
            }
        }

        System.out.println("========== TOPPER ==========");
        System.out.println("ID     : " + topper.getId());
        System.out.println("Name   : " + topper.getName());
        System.out.println("Marks  : " + topper.getMarks());
        System.out.println("Course : " +
                topper.getCourse().getCourseName());
        System.out.println("Grade  : " +
                calculateGrade(topper.getMarks()));
    }

    public ArrayList<Student> getStudents() {

        return students;
    }
}
