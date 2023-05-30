package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reward.Reward;
import reward.RewardList;
import util.Constants.Result;

public class RewardDao implements RewardList {
	
	private Dao dao;

    public RewardDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void add(Reward reward) {
		// rewardID, appliDate, contractID, customerName. content, 
		// 		appliResult accidentProfile, identifyProfile, reward
		String query = "INSERT into Reward values("
	            + reward.getAppliDate() +  ", "
	            + reward.getContractID() + ", '" 
	            + reward.getCustomerName() + "', '" 
	            + reward.getContent() + "', '" 
	            + reward.getAppliResult().getString() + "', '" 
	            + reward.getAccidentProfile() + "', '" 
	            + reward.getIdentifyProfile() + "', " 
	            + reward.getReward() + "); ";
	    dao.create(query);
	}

	@Override
	public void delete(int rewardID) {
		String query = "DELETE FROM Reward WHERE rewardID = " + rewardID + ";";
        dao.delete(query);
	}

	@Override
	public Reward retrieve(int rewardID) {
		String query = "SELECT * FROM Reward WHERE rewardID=" + rewardID + ";";
		ResultSet resultSet = dao.retrieve( query );
		Reward reward = new Reward();
		try {
			reward.setRewardID(resultSet.getInt("rewardID"));
			reward.setReward(resultSet.getInt("reward"));
			reward.setIdentifyProfile(resultSet.getString("identifyProfile"));
			reward.setCustomerName(resultSet.getString("customerName"));
	    	reward.setContractID(resultSet.getInt("contractID"));
	    	reward.setContent(resultSet.getString("content"));
	    	Date appliDate = resultSet.getDate("appliDate");
	    	reward.setAppliDate(dao.dateNullCheck(appliDate));
	    	reward.setAccidentProfile(resultSet.getString("accidentProfile"));
	    	String appliResult = resultSet.getString("appliResult");
	    	reward.setAppliResult(dao.enumNullCheck(appliResult, () -> Result.valueOf(appliResult)));
		} catch (SQLException e) {throw new RuntimeException(e);}
		return reward;
	}

	@Override
	public List<Reward> retrieveAll() {
		String query = "SELECT * from Reward;";
        ResultSet resultSet = dao.retrieve(query);
        List<Reward> rewardList = new ArrayList<Reward>();
        try {
            while (resultSet.next()){
            	Reward reward = new Reward();
            	reward.setRewardID(resultSet.getInt("rewardID"));
            	reward.setReward(resultSet.getInt("reward"));
            	reward.setIdentifyProfile(resultSet.getString("identifyProfile"));
            	reward.setCustomerName(resultSet.getString("customerName"));
            	reward.setContractID(resultSet.getInt("contractID"));
            	reward.setContent(resultSet.getString("content"));
            	Date appliDate = resultSet.getDate("appliDate");
            	reward.setAppliDate(dao.dateNullCheck(appliDate));
            	reward.setAccidentProfile(resultSet.getString("accidentProfile"));
            	String appliResult = resultSet.getString("appliResult");
            	reward.setAppliResult(dao.enumNullCheck(appliResult, () -> Result.valueOf(appliResult))); 
            	rewardList.add( reward );
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return rewardList;
	}

	@Override
	public void update(Reward reward) {
		String query = "UPDATE Reward SET reward = " + reward.getReward() + ", "
				+ "identifyProfile = '" + reward.getIdentifyProfile() +  "', " 
				+ "customerName = '" + reward.getCustomerName() + "', " 
				+ "contractID = " + reward.getContractID() + ", " 
				+ "content = '" + reward.getContent() + "', " 
				+ "appliDate = " + reward.getAppliDate() + ", " 
				+ "accidentProfile = '" + reward.getAccidentProfile() + "', " 
				+ "appliResult = '" + reward.getAppliResult().getString() + "' "
				+ "WHERE rewardID = " + reward.getRewardID() + ";";
		dao.update( query );
	}

}
