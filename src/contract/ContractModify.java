package contract;

import java.time.LocalDateTime;

import util.Constants.Result;

public class ContractModify {
	
	private int contractModifyID;
	private int contractID;
	private Result result;
	private LocalDateTime applyDate;
	private String content;				// 사유 및 내용
	private String newSpecialization;
	
	public int getContractModifyID() {
		return contractModifyID;
	}
	public void setContractModifyID(int contractModifyID) {
		this.contractModifyID = contractModifyID;
	}
	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public LocalDateTime getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(LocalDateTime applyDate) {
		this.applyDate = applyDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewSpecialization() {
		return newSpecialization;
	}
	public void setNewSpecialization(String newSpecialization) {
		this.newSpecialization = newSpecialization;
	}
}
