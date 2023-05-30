package businessEducation;

import java.util.List;

public interface EducationList {
	
	void add( Education education );
	void delete( int educationID );
	Education retrieve( int educationID );
	void update( Education education );
	List<Education> retrieveAll();

}