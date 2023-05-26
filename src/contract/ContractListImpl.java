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

	public Vector<Contract> retrieve( String query ){
		
		return null;
	}

	public void update( contract.Contract contract ){

	}

	public List<contract.Contract> getAllregist() {return contractList;}

}