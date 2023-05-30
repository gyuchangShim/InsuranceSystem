package teams;
import java.util.ArrayList;
import java.util.List;

import businessEducation.Education;
import businessEducation.EducationListImpl;
import businessEducation.EducationStudent;
import businessEducation.EducationStudentListImpl;
import util.Constants.Crud;
import util.Constants.Target;

public class BusinessEducationTeam extends Team {

	public Education m_Education;
	public EducationStudent m_EducationStudent;
	private EducationListImpl educationListImpl;
	private EducationStudentListImpl studentListImpl;

	public BusinessEducationTeam(){
		this.educationListImpl = new EducationListImpl();
		this.studentListImpl = new EducationStudentListImpl();
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
				break;
			}
			break;
		case EDUCATION_STUDENT :
			switch( crud ) {
			case CREATE:
				this.studentListImpl.add( this.m_EducationStudent );
				break;
			case READ:
				break;
			case UPDATE:
				break;
			case DELETE:
				break;
			}
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
		// 모든 교육 정보를 가져온다.
		return this.educationListImpl.retrieveAll();
	}
	public List<EducationStudent> getStduentByEducation( int educationID ){
		// 교육 ID가 해당 id인 수료자의 정보들을 가져온다.
		List<EducationStudent> studentList = this.studentListImpl.retrieveAll();
		List<EducationStudent> result = new ArrayList<EducationStudent>();
		for( EducationStudent student: studentList ) {
			if( student.getEducationID()==educationID ) result.add( student );
		}
		return result;
	}
	public List<EducationStudent> getAllStudent(){
		// 모든 학생들의 정보를 가져온다.
		return this.studentListImpl.retrieveAll();
	}
	public void setStudent( EducationStudent student ) {
		this.m_EducationStudent = student;
	}
	public void setEducation( Education education ) {
		this.m_Education = education;
	}

}