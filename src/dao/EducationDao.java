package dao;

import exception.CustomException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessEducation.Education;
import businessEducation.EducationList;

public class EducationDao implements EducationList{
	private Dao dao;

    public EducationDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
			throw new CustomException(e);
        }
    }

	@Override
	public void add(Education education) {
		String query = "INSERT INTO Education ( budget, content, duration, exResult, name, place, teacherName, teacherPhoneNumber ) VALUES ("
				+ education.getBudget() + ", '"
				+ education.getContent() + "', "
				+ education.getDuration() + ", '"
				+ education.getExResult() + "', '"
				+ education.getName() + "', '"
				+ education.getPlace() + "', '"
				+ education.getTeacherName() + "', '"
				+ education.getTeacherPhoneNumber() + "');";
		dao.create( query );
	}

	@Override
	public void delete(int educationID) {
		String query = "DELETE FROM Education WHERE educationID = " + educationID + ";";
		dao.delete( query );
	}

	@Override
	public Education retrieve(int educationID) {
		String query = "SELECT * FROM Education WHERE educationID=" + educationID + ";";
		ResultSet resultSet = dao.retrieve( query );
		Education education = new Education();
		try {
			education.setBudget( resultSet.getInt("budget"));
			education.setContent( resultSet.getString("content"));
			education.setDuration( resultSet.getInt("duration"));
			education.setEducationID( resultSet.getInt("educationID"));
			education.setExResult( resultSet.getString("exResult"));
			education.setName( resultSet.getString("name"));
			education.setPlace( resultSet.getString("place"));
			education.setTeacherName( resultSet.getString("teacherName"));
			education.setTeacherPhoneNumber( resultSet.getString("teacherPhoneNumber" ));
		} catch (SQLException e) {throw new RuntimeException(e);}
		return education;
	}

	@Override
	public void update(Education education ) {
		String query = "UPDATE Education SET budget = " + education.getBudget() + ", "
				+ "content = '" + education.getContent() + "', "
				+ "duration = " + education.getDuration() + ", "
				+ "exResult = '" + education.getExResult() + "', "
				+ "name = '" + education.getName() + "', "
				+ "place = '" + education.getPlace() + "', "
				+ "teacherName = '" + education.getTeacherName() + "', "
				+ "teacherPhoneNumber = '" + education.getTeacherPhoneNumber() + "' "
				+ "WHERE educationID = " + education.getEducationID() + ";";
		dao.update( query );		
	}

	@Override
	public List<Education> retrieveAll() {
		String query = "SELECT * from Education;";
        ResultSet resultSet = dao.retrieve(query);
        List<Education> educationList = new ArrayList<Education>();
        try {
            while (resultSet.next()){
            	Education education = new Education();
            	education.setBudget( resultSet.getInt("budget"));
    			education.setContent( resultSet.getString("content"));
    			education.setDuration( resultSet.getInt("duration"));
    			education.setEducationID( resultSet.getInt("educationID"));
    			education.setExResult( resultSet.getString("exResult"));
    			education.setName( resultSet.getString("name"));
    			education.setPlace( resultSet.getString("place"));
    			education.setTeacherName( resultSet.getString("teacherName"));
    			education.setTeacherPhoneNumber( resultSet.getString("teacherPhoneNumber" ));
            	educationList.add( education );
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return educationList;
	}

}
