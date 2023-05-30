package contractManagement;

import java.util.List;

public interface ContractManagementPolicyList {

    List<ContractManagementPolicy> retrieveAll();
    void add( ContractManagementPolicy contractManagementPolicy );
    void delete( int contractManagementPolicyID );
    ContractManagementPolicy retrieve( int contractManagementPolicyID );
    void update( ContractManagementPolicy contractManagementPolicy );
}