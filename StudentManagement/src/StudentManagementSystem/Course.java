package StudentManagementSystem;

public class Course {
private int courseId;
private String courseName;
private double fees;
public Course(int courseId,String courseName,double fees) {
	this.courseId=courseId;
	this.courseName=courseName;
	this.fees=fees;
	
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId=courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName=courseName;
}
public double getFees() {
	return fees;
}
public void setFees(double fees) {
	this.fees=fees;
}
}
