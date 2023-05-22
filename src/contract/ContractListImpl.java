package Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractListImpl implements ContractList {

	public static ArrayList<Contract> contractList;

	public ContractListImpl(){
		this.contractList = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}

	public void add(Contract contract){
		contractList.add(contract);
	}

	public void delete(){

	}

	public void retrieve(){

	}

	public void update(){

	}

	public List<Contract> getAllregist() {return contractList;}

}