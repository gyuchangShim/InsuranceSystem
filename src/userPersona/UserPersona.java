package userPersona;

import util.Constants.Gender;

public class UserPersona {

    private int userPersonaID;
    private int insuranceID;
    private Gender sex;
    private int age;
    private String job;
    private int incomeLevel;

    public int getUserPersonaId() {
        return userPersonaID;
    }

    public void setUserPersonaId(int userPersonaID) {
        this.userPersonaID = userPersonaID;
    }

    public int getInsuranceId() {
        return insuranceID;
    }

    public void setInsuranceId(int insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(int incomeLevel) {
        this.incomeLevel = incomeLevel;
    }
}
