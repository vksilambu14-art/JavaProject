package StudentManagementSystem;
import java.io.*;
import java.util.ArrayList;
public class StudentFileService {
	private String fileName = "C:\\Users\\Dell\\Desktop\\sampledata\\students.txt";

    // SAVE
    public void saveStudents(ArrayList<Student> students) {

        try {
        	
			

            FileWriter fw = new FileWriter(fileName);
            

            for (Student student : students) {

                fw.write(
                    student.getId() + "," +
                    student.getName() + "," +
                    student.getEmail() + "," +
                    student.getMarks() + "," +
                    student.getAttendance() + "," +
                    student.getStatus() + "," +
                    student.getCourse().getCourseId() + "," +
                    student.getCourse().getCourseName() + "," +
                    student.getCourse().getFees() +
                    "\n"
                );
            }

            fw.close();

            System.out.println("Students saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving.");
        }
    }

    // LOAD
    public ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        try {

            File file = new File(fileName);

            if (!file.exists()) {

                return students;
            }

            BufferedReader br =
                new BufferedReader(
                    new FileReader(fileName)
                );

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id =
                    Integer.parseInt(data[0]);

                String name =
                    data[1];

                String email =
                    data[2];

                double marks =
                    Double.parseDouble(data[3]);

                int attendance =
                    Integer.parseInt(data[4]);

                StudentStatus status =
                    StudentStatus.valueOf(data[5]);

                int courseId =
                    Integer.parseInt(data[6]);

                String courseName =
                    data[7];

                double fee =
                    Double.parseDouble(data[8]);

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

                students.add(student);
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error while loading.");
        }

        return students;
    }
}
