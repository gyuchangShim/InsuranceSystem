package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import userPersona.UserPersona;
import userPersona.UserPersonaList;
import util.Constants.Gender;

public class UserPersonaDao implements UserPersonaList {

    private Dao dao;

    public UserPersonaDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(UserPersona userPersona) {
        String query = "INSERT INTO UserPersona (insuranceID, sex, age, job, incomeLevel) VALUES ("
                + userPersona.getInsuranceId() + ", '"
                + userPersona.getSex() + "', "
                + userPersona.getAge() + ", '"
                + userPersona.getJob() + "', "
                + userPersona.getIncomeLevel() + ");";
        dao.create(query);
    }

    @Override
    public void delete(int userPersonaId) {
        String query = "DELETE FROM UserPersona WHERE userPersonaID = " + userPersonaId + ";";
        dao.delete(query);
    }

    @Override
    public UserPersona retrieve(int userPersonaId) {
        String query = "SELECT * FROM UserPersona WHERE userPersonaID = " + userPersonaId + ";";
        try {
            ResultSet resultSet = dao.retrieve(query);
            if (resultSet.next()) {
                return getUserPersona(resultSet);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserPersona> retrieveAll() {
        String query = "SELECT * FROM UserPersona;";
        try {
            ResultSet resultSet = dao.retrieve(query);
            List<UserPersona> userPersonaList = new ArrayList<>();
            while(resultSet.next()) {
                userPersonaList.add(getUserPersona(resultSet));
            }
            return userPersonaList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UserPersona userPersona) {
        String query = "UPDATE USerPersona SET insuranceID = " + userPersona.getInsuranceId() + ", "
            + "sex = '" + userPersona.getSex() + "', "
            + "age = " + userPersona.getAge() + ", "
            + "job = '" + userPersona.getJob() + "', "
            + "incomeLevel = " + userPersona.getIncomeLevel() + " "
            + "WHERE userPersonaID = " + userPersona.getUserPersonaId() + ";";
        dao.update(query);
    }

    private UserPersona getUserPersona(ResultSet resultSet) throws SQLException {
        UserPersona userPersona = new UserPersona();
        userPersona.setUserPersonaId(resultSet.getInt("userPersonaID"));
        userPersona.setInsuranceId(resultSet.getInt("insuranceID"));
        userPersona.setAge(resultSet.getInt("age"));
        String sex = resultSet.getString("sex");
        userPersona.setSex(dao.enumNullCheck(sex, () -> Gender.valueOf(sex)));
        userPersona.setJob(resultSet.getString("job"));
        userPersona.setIncomeLevel(resultSet.getInt("incomeLevel"));
        return userPersona;
    }
}
