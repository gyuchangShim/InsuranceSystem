package business;

import java.util.ArrayList;
import java.util.List;

public class SellGroupListImpl implements SellGroupList {

	private List<SellGroup> sellGroupList;
	private int selGroupId;

	public SellGroupListImpl() {
		sellGroupList = new ArrayList<>();
		selGroupId = 1;
	}

	@Override
	public void add(SellGroup sellGroup) {
		sellGroup.setGroupID(generateSellGroupId());
		sellGroupList.add(sellGroup);
	}

	@Override
	public void delete(int sellGroupId) {

	}

	@Override
	public SellGroup retrieve(int sellGroupId) {
		return sellGroupList.stream()
			.filter(sellGroup -> sellGroup.getGroupID() == sellGroupId)
			.findFirst()
			.get();
	}

	@Override
	public void update(SellGroup sellGroup) {

	}

	@Override
	public List<SellGroup> retrieveAll() {
		return sellGroupList;
	}

	private int generateSellGroupId() {
		return selGroupId++;
	}
}