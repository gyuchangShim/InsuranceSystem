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

	@Override
	public void delete(int contractId) {

	}

	@Override
	public Contract retrieve(int contractId) {
		return null;
	}


	public List<Contract> retrieveAll() {return contractList;}

	@Override
	public void update(Contract contract) {

	}

	private int generateContractId(){
		return contractIdGenerator++;
	}
}