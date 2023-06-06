package dao;

import exception.CustomException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessEducation.EducationStudent;
import businessEducation.EducationStudentList;
import util.Constants.Gender;

public class EducationStudentDao implements EducationStudentList {
	private Dao dao;

    public EducationStudentDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
			throw new CustomException(e);
        }
    }

	@Override
	public void add(EducationStudent student) {
		String query = "INSERT into EducationStudent ( age, gender, name, phone, examination, studentScore, educationID ) values("
	            + student.getAge() + ", '"
	            + student.getGender().getString() + "', '"
	            + student.getName() + "', '"
	            + student.getPhone() + "', '"
	            + student.getExamination() + "', "
	            + student.getStudentScore() + ", "
	            + student.getEducationID() + ");";
	    dao.create(query);		
	}

	@Override
	public void delete(int studentID) {
		String query = "DELETE FROM EducationStudent WHERE studentID = " + studentID + ";";
		dao.delete( query );
	}

	@Override
	public EducationStudent retrieve(int studentID) {
		String query = "SELECT * FROM EducationStudent WHERE studentID=" + studentID + ";";
		ResultSet resultSet = dao.retrieve( query );
		EducationStudent student = new EducationStudent();
		try {
			student.setAge( resultSet.getInt("age"));
			student.setName( resultSet.getString("name"));
			student.setPhone( resultSet.getString("phone"));
			student.setStudentID( resultSet.getInt("studentID"));
			student.setEducationID( resultSet.getInt("educationID"));
			student.setExamination( resultSet.getString("examination"));
			student.setStudentScore( resultSet.getInt("studentScore"));
	    	String gender = resultSet.getString("gender");
	    	student.setGender(dao.enumNullCheck(gender, () -> Gender.valueOf(gender)));
		} catch (SQLException e) {throw new RuntimeException(e);}
		return student;
	}

	@Override
	public List<EducationStudent> retrieveAll() {
		String query = "SELECT * from EducationStudent;";
        ResultSet resultSet = dao.retrieve(query);
        List<EducationStudent> studentList = new ArrayList<EducationStudent>();
        try {
            while (resultSet.next()){
            	EducationStudent student = new EducationStudent();
            	student.setAge( resultSet.getInt("age"));
    			student.setName( resultSet.getString("name"));
    			student.setPhone( resultSet.getString("phone"));
    			student.setStudentID( resultSet.getInt("studentID"));
    			student.setEducationID( resultSet.getInt("educationID"));
    			student.setExamination( resultSet.getString("examination"));
    			student.setStudentScore( resultSet.getInt("studentScore"));
    	    	String gender = resultSet.getString("gender");
    	    	if( gender.equals("Male") ) {
    	    		student.setGender( Gender.MALE );
    	    	} else if( gender.equals("Female" ) ) {
    	    		student.setGender( Gender.FEMALE );
    	    	}
            	studentList.add( student );
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return studentList;
	}

	@Override
	public void update(EducationStudent student) {
		String query = "UPDATE EducationStudent SET age = " + student.getAge() + ", "
				+ "name = '" + student.getName() + "', "
				+ "phone = '" + student.getPhone() + "', "
				+ "educationID = " + student.getEducationID() + ", "
				+ "examination = '" + student.getExamination() + "', "
				+ "studentScore = " + student.getStudentScore() + ", "
				+ "gender= '" + student.getGender().getString()
				+ "' WHERE studentID = " + student.getStudentID() + ";";
		dao.update( query );		
	}

}
