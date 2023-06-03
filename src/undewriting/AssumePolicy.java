package undewriting;


public class AssumePolicy {

	private String name; // 인수정책 이름
	private int policyID; // 인수정책 ID - DB에서 자동 추가
	private String content; // 인수정책 내용
	private PolicyType policyType; // 인수정책의 종류(일반 인수, 공동 인수) - 시나리오에 존재하지만 설계에서 attribute 제외되어 추가함

	public AssumePolicy(){

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPolicyID() {
		return policyID;
	}

	public void setPolicyID(int policyID) {
		this.policyID = policyID;
	}

	public void setPolicyType(PolicyType type) {
		this.policyType = type;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void finalize() throws Throwable {

	}
}