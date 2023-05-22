package userPersona;

import util.Constants.Gender;

public class UserPersona {

    private int userPersonaId;
    private int insuranceId;
    private Gender sex;
    private int age;
    private String job;
    private int incomeLevel;

    public int getUserPersonaId() {
        return userPersonaId;
    }

    public void setUserPersonaId(int userPersonaId) {
        this.userPersonaId = userPersonaId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
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
