package contract;

import java.util.List;

public interface AdviceNoteList {
	
	void add(AdviceNote adviceNote);
    List<AdviceNote> retrieveAll();
    void delete( int adviceNoteID );
    void update( AdviceNote adviceNote );
    AdviceNote retrieve( int adviceNoteID );
}
