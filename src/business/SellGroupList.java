package business;

import java.util.List;

public interface SellGroupList {
    void add(SellGroup sellGroup);
    void delete(int sellGroupId);
    SellGroup retrieve(int sellGroupId);
    void update(SellGroup sellGroup);
    List<SellGroup> retrieveAll();
}