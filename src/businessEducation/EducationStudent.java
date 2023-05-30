package businessEducation;

import util.Constants.Gender;

public class EducationStudent {

	private Gender gender;;
	private int age;
	private String name;
	private String phone;
	private int studentID;
	private int educationID;
	private String examination;
	private int studentScore;
	
	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public int getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(int studentScore) {
		this.studentScore = studentScore;
	}

	public EducationStudent(){

	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getEducationID() {
		return this.educationID;
	}
	
	public void setEducationID( int educationID ) {
		this.educationID = educationID;
	}
	
	public Gender getGender() {
		return this.gender;
	}
	public void setGender( Gender gender ) {
		this.gender = gender;
	}

	public void evaluateEducation(){

	}

}