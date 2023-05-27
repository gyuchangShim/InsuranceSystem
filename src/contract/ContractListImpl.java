package contract;

import java.util.ArrayList;
import java.util.List;

public class ContractListImpl implements ContractList {

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

	public void retrieve(){

	}

	public void update(){

	}

	public List<contract.Contract> getAllregist() {return contractList;}

}