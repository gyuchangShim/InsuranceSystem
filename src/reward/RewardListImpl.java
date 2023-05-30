package reward;

import java.util.List;

public class RewardListImpl implements RewardList {
	public Reward m_Reward;
	// Connection connect = null;
	// Statement statement;

	public RewardListImpl(){}
	public void finalize() throws Throwable {}
	
	public void add( Reward newReward ){
		// 먼저 엔티티를 완성하고 오면 여기서 추가하는 방법
		
		// Class.forName( "com.mysql.cj.jdbc.Driver");
		// connect = DriverManager.getConnection( "jdbc:mysql:///````", "root", "pw" );
		// statement = connect.createStatement();
		// statement.execute( "Query문" );
	}
	public void delete( int id ){
		// 삭제할 엔티티의 ID를 입력받아 삭제
	}
	public Reward retrieve( int rewardID ){
		// rewardList에 모든 데이터를 끌어옴 ( 근데 이러면 너무 큰 데이터를 들고 움직여야 하지 않을까... )
		return null;
	}
	public void update( Reward reward ){
		// 받은 Reward의 ID를 확인해 기존 Reward와 변경
	}
	public List<Reward> retrieveAll(){
		return null;
	}
}