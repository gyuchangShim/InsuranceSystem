package reward;

import java.util.List;

public interface RewardList {
	void add(Reward reward);
    void delete(int rewardID);
    Reward retrieve(int rewardID);
    List<Reward> retrieveAll();
    void update(Reward reward);
}