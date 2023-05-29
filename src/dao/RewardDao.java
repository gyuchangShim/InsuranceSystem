package dao;

import java.util.List;

import reward.Reward;
import reward.RewardList;

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
		String query = "insert into reward values("
	            + reward.getRewardID() + ", "
	            + reward.getAppliDate() +  ", "
	            + reward.getContractID() + ", " 
	            + reward.getCustomerName() + ", " 
	            + reward.getContent() + ", " 
	            + reward.getAppliResult().getString() + ", " 
	            + reward.getAccidentProfile() + ", " 
	            + reward.getIdentifyProfile() + ", " 
	            + reward.getReward() + "); ";
	        dao.create(query);
	}

	@Override
	public void delete(int rewardID) {
		
	}

	@Override
	public Reward retrieve(int rewardID) {
		return null;
	}

	@Override
	public List<Reward> retrieveAll() {
		return null;
	}

	@Override
	public void update(Reward reward) {
		
	}

}
