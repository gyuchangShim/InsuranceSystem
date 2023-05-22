package Teams;
import ContractManagement.ContractManagementPolicy;
import Teams.Team;
import util.Constants.Target;
import util.Constants.Crud;

public class ContractManagementTeam extends Team {
	public ContractManagementPolicy m_ContractManagementPolicy;

	public ContractManagementTeam(){}
	public void finalize() throws Throwable { super.finalize(); }

	@Override
	public void establishPolicy(Target target, Crud crud) {}
	@Override
	public void manage(Target target, Crud crud) {}
	@Override
	public void plan(Target target, Crud crud) {}
	@Override
	public void process(Target target, Crud crud) {}
}