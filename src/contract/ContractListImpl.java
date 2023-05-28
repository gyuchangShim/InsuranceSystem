package contract;

import java.util.ArrayList;
import java.util.List;

public class ContractListImpl implements ContractList {

	private List<Contract> contractList;
	private int contractIdGenerator;

	public ContractListImpl(){
		this.contractList = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}

	public void add(Contract contract){
		contract.setContractID(generateContractId());
		contractList.add(contract);
	}

	public void delete(){

	}

	public void retrieve(){

	}

	public void update(){

	}

	public List<Contract> retrieveAll() {return contractList;}

	private int generateContractId(){
		return contractIdGenerator++;
	}
}