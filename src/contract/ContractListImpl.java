package contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ContractListImpl implements contract.ContractList {

	public static List<contract.Contract> contractList;

	public ContractListImpl(){
		this.contractList = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}

	public void add(contract.Contract contract){
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

	public List<contract.Contract> getAllregist() {return contractList;}

}