package util;

public class Constants {	 
	public enum Crud{ CREATE, READ, UPDATE, DELETE; }
	public enum Target{
		INSURANCE,						// 상품
		BUSINESS_PLAN,					// 운영 계획
		BUSINESS_PERSONA,				// 운영 유저 페르소나
		CONSULT,						// 상담
		OPERATION_POLICY,				// 운영 정책
		CONTRACT_MANAGEMENT_POLICY,		// 계약 관리 정책
		COLLECTION,						// 수금
		PAYMENT,						// 분납
		EXPIRATION_CONTRACT,			// 만기 계약
		CONTRACT,						// 계약
		CONTRACT_STATISTIC,				// 계약 통계
		EDUCATION,						// 교육
		EDUCATION_STUDENT,				// 교육 수료자
		CAMPAIGN_PROGRAM,				// 캠페인
		ASSUME_POLICY,					// 인수 정책
		CUSTOMER,						// 고객
		REWARD,							// 보상
		SELL_GROUP						// 판매 그룹
	}
	public enum Result{ 
		PROCESS( "PROCESS" ), 
		DENY( "DENY" ), 
		ACCEPT( "ACCEPT" );
		private String result;
		
		Result( String result ){ this.result = result; }
		public String getString() { return this.result; }
	}
	public enum Gender{
		MALE( "Male" ),
		FEMALE( "Female" );
		private String gender;

		Gender( String gender ){this.gender = gender;}
		public String getString() {return this.gender;}
	}
}
