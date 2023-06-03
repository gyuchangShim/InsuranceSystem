package business;

import java.util.List;

public interface OperationPolicyList {
    void add(OperationPolicy operationPolicy);
    void delete(int operationPolicyId);
    OperationPolicy retrieve(int operationPolicyId);
    void update(OperationPolicy operationPolicy);
    List<OperationPolicy> retrieveAll();
}
