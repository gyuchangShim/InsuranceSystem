package dao;

import exception.CustomException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contract.AdviceNote;
import contract.AdviceNoteList;
import util.Constants.Result;

public class AdviceNoteDao implements AdviceNoteList {
	private Dao dao;
	
    public AdviceNoteDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
			throw new CustomException(e);
        }
    }
	@Override
	public void add(AdviceNote adviceNote) {
		String query = "INSERT into AdviceNote ( content, result, contractID, customerID ) VALUES ('"
				+ adviceNote.getContent() + "', '"
				+ adviceNote.getResult().getString() + "', "
				+ adviceNote.getContractID() + ", " 
				+ adviceNote.getCustomerID() + "); ";
	    dao.create(query);		
	}

	@Override
	public List<AdviceNote> retrieveAll() {
		String query = "SELECT * from AdviceNote;";
        ResultSet resultSet = dao.retrieve(query);
        List<AdviceNote> adviceNoteList = new ArrayList<AdviceNote>();
        try {
            while (resultSet.next()){
            	AdviceNote adviceNote = new AdviceNote();
            	adviceNote.setAdviceNoteID( resultSet.getInt("adviceNoteID"));
    			adviceNote.setContent( resultSet.getString("content"));
    			adviceNote.setCustomerID( resultSet.getInt( "customerID"));
    			String result = resultSet.getString("result");
    			adviceNote.setResult(dao.enumNullCheck(result, () -> Result.valueOf(result)));
    			adviceNote.setContractID( resultSet.getInt("contractID"));
            	adviceNoteList.add( adviceNote );
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return adviceNoteList;
	}

	@Override
	public void delete(int adviceNoteID) {
		String query = "DELETE FROM AdviceNote WHERE adviceNoteID = " + adviceNoteID + ";";
        dao.delete(query);		
	}

	@Override
	public void update(AdviceNote adviceNote) {
		String query = "UPDATE AdviceNote SET content = '" + adviceNote.getContent() + "', "
				+ "customerID = " + adviceNote.getCustomerID() +  ", " 
				+ "result = '" + adviceNote.getResult().getString() + "', " 
				+ "contractID = " + adviceNote.getContractID() + " " 
				+ "WHERE adviceNoteID = " + adviceNote.getAdviceNoteID()+ ";";
		dao.update( query );		
	}

	@Override
	public AdviceNote retrieve(int adviceNoteID) {
		String query = "SELECT * FROM AdviceNote WHERE adviceNoteID=" + adviceNoteID + ";";
		ResultSet resultSet = dao.retrieve( query );
		AdviceNote adviceNote = new AdviceNote();
		try {
			adviceNote.setAdviceNoteID( resultSet.getInt("adviceNoteID"));
			adviceNote.setContent( resultSet.getString("content"));
			adviceNote.setCustomerID( resultSet.getInt( "customerID"));
			String result = resultSet.getString("result");
			adviceNote.setResult(dao.enumNullCheck(result, () -> Result.valueOf(result)));
			adviceNote.setContractID( resultSet.getInt("contractID"));
		} catch (SQLException e) {throw new RuntimeException(e);}
		return adviceNote;
	}

}
