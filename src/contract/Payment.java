package contract;

import java.time.LocalDate;

import util.Constants.PayWay;

public class Payment {
	
	private int paymentID;
	private int contractID;
	private int duration;				// 누적 납부 기간
	private int contractDuration;		// 가입 기간
	private LocalDate expireDate;		// 만기 기간
	private String content;				// 내용
	private int amount;					// 납부해야하는 금액
	private int accidentCount;			// 현재 사고 발생으로 보험금을 타간 횟수
	private PayWay payway;				// 납부 방법
	private boolean result;				// 이번 달 납부 여부
	
	public boolean getResult() {
		return this.result;
	}
	public void setResult( boolean result) {
		this.result = result;
	}
	public int getContractDuration() {
		return this.contractDuration;
	}
	public void setContractDuration( int duration ) {
		this.contractDuration = duration;
	}
	public PayWay getPayway() {
		return payway;
	}
	public void setPayway(PayWay payway) {
		this.payway = payway;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAccidentCount() {
		return accidentCount;
	}
	public void setAccidentCount(int accidentCount) {
		this.accidentCount = accidentCount;
	}
}
