package BusinessEducation;

public class EducationStudent {

	@SuppressWarnings("unused")
	private enum gender{ M, FEMALE };
	private int age;
	private String name;
	private String phone;
	private String studentID;

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

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public void finalize() throws Throwable {

	}

	public void evaluateEducation(){

	}

}