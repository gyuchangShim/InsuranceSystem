package contract;

import java.util.List;

public interface ContractList {

    void add(Contract contract);
    void delete(int contractId);
    Contract retrieve(int contractId);
    List<Contract> retrieveAll();
    void update(Contract contract);

}