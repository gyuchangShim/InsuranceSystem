package dao;

import insurance.Insurance;
import insurance.InsuranceList;
import java.util.List;

public class InsuranceDao implements InsuranceList {

    private Dao dao;

    public InsuranceDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Insurance insurance) {
        String query = "insert into insurance values("
            + insurance.getInsuranceID() + ", "
            + insurance.getInsuranceName() +  ");";
        dao.create(query);
    }

    @Override
    public void delete(int insuranceId) {
    	
    }

    @Override
    public Insurance retrieve(int insuranceId) {
        return null;
    }

    @Override
    public List<Insurance> retrieveAll() {
        return null;
    }

    @Override
    public void update(Insurance insurance) {

    }



}
