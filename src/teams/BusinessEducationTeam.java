package teams;
import java.util.ArrayList;
import java.util.List;

import businessEducation.Education;
import businessEducation.EducationList;
import businessEducation.EducationStudent;
import businessEducation.EducationStudentList;
import util.Constants.Crud;
import util.Constants.Target;

public class BusinessEducationTeam extends Team {

	public Education m_Education;
	public EducationStudent m_EducationStudent;
	private EducationList educationListImpl;
	private EducationStudentList studentListImpl;

	public BusinessEducationTeam( EducationList educationList, EducationStudentList studentList ){
		this.educationListImpl = educationList;
		this.studentListImpl = studentList;
	}

	@Override
	public void establishPolicy(Target target, Crud crud) {

	}

	@Override
	public void manage(Target target, Crud crud) {
		switch( target ) {
		case EDUCATION :
			switch( crud ) {
			case CREATE:
				this.educationListImpl.add( this.m_Education );
				break;
			case READ:
				break;
			case UPDATE:
				this.educationListImpl.update( this.m_Education );
				break;
			case DELETE:
				this.educationListImpl.delete( this.m_Education.getEducationID() );
				break;
			}
			break;
		case EDUCATION_STUDENT :
			switch( crud ) {
			case CREATE:
				this.studentListImpl.add( this.m_EducationStudent );
				break;
			case READ:
				this.studentListImpl.retrieveAll();
				break;
			case UPDATE:
				this.studentListImpl.update( this.m_EducationStudent );
				break;
			case DELETE:
				this.studentListImpl.delete( this.m_EducationStudent.getStudentID() );
				break;
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void plan(Target target, Crud crud) {

	}

	@Override
	public void process(Target target, Crud crud) {

	}

	public void calculatingExamination() {

	}
	public List<Education> getAllEducation(){
		return this.educationListImpl.retrieveAll();
	}
	public List<EducationStudent> getStduentByEducation( int educationID ){
		List<EducationStudent> studentList = this.studentListImpl.retrieveAll();
		List<EducationStudent> result = new ArrayList<EducationStudent>();
		for( EducationStudent student: studentList ) {
			if( student.getEducationID()==educationID ) result.add( student );
		}
		return result;
	}
	public List<EducationStudent> getAllStudent(){
		return this.studentListImpl.retrieveAll();
	}
	public void setStudent( EducationStudent student ) {
		this.m_EducationStudent = student;
	}
	public void setEducation( Education education ) {
		this.m_Education = education;
	}

}