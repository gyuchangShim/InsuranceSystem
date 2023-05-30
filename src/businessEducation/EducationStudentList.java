package businessEducation;

import java.util.List;

public interface EducationStudentList {
	
	void add( EducationStudent student );
	void delete( int studentID );
	EducationStudent retrieve( int studentID );
	List<EducationStudent> retrieveAll();
	void update( EducationStudent student );

}