package Dao;

import insurance.Insurance;
import insurance.InsuranceList;

public class InsuranceDao extends Dao{
    public InsuranceDao() {
        try {
            super.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Insurance insurance) {
        // create query
        String query = "insert into insurance values ("
                + insurance.getInsuranceID() + ", "
                + insurance.getInsuranceName() + ");";
        System.out.println(query);
    }

    public InsuranceList retrieveAll() {
        // resultset에 InsuranceList에 setting하는 부분
        return ;
    }

    public InsuranceList retrieveById(int insuranceId) {
        return null;
    }

    public void update(Insurance insurance) {
        // create query
        String query = "update into insurance values ("
                + insurance.getInsuranceID() + ", "
                + insurance.getInsuranceName() + ");";
        System.out.println(query);
    }

    public void delete(Insurance insurance) {
        // create query
        String query = "delete from insurance values ("
                + insurance.getInsuranceID() + ", "
                + insurance.getInsuranceName() + ");";
        System.out.println(query);
    }



}
