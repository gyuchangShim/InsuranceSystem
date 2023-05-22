package undewriting;

import java.util.List;
import undewriting.AssumePolicy;

public interface AssumePolicyList {

    void add(AssumePolicy assumePolicy);

    void delete(AssumePolicy assumePolicy);

    List<AssumePolicy> getAllPolicy();
}