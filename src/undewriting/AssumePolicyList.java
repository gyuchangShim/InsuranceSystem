package undewriting;

import java.util.List;

import dao.AssumePolicyDao;
import undewriting.AssumePolicy;

public interface AssumePolicyList {

    void add(AssumePolicy assumePolicy);

    void delete(int assumePolicyId);

    AssumePolicy retreive(int policyId);

    List<AssumePolicy> retreiveAll();

    void update(AssumePolicy assumePolicy);
}