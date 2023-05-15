package Reward;

public class RewardListImpl implements RewardList {

	public Reward m_Reward;

	public RewardListImpl(){

	}

	public void finalize() throws Throwable {

	}

	public void add( Reward newReward ){
		// 먼저 엔티티를 완성하고 오면 여기서 추가하는 방법
	}

	public void delete( int id ){
		// 삭제할 엔티티의 ID를 입력받아 삭제
	}

	public Reward retrieve(){
		// case 1. 하나씩 가져올지 -> return Reward
		// case 2. 전부 가져올지 -> return Vector<Reward>
		return null;
	}

	public void update( Reward reward ){
		// 받은 Reward의 ID를 확인해 기존 Reward와 변경
	}

}