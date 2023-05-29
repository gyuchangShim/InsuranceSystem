package contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

	public Contract retrieve( int contractID ){
		
		return null;
	}

	public void update( contract.Contract contract ){

	}
	
	public Vector<Contract> retrieveAll(){
		return null;
	}

	public List<Contract> retrieveAll() {return contractList;}

	private int generateContractId(){
		return contractIdGenerator++;
	}
}