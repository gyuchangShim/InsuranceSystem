package customer;

import java.time.LocalDateTime;

public class CustomerCounseling {

    private int counselingID;
    private int customerID;
    private String counselingPlace;
    private LocalDateTime counselingTime;
    private CounselingState counselingState;

    public int getCounselingId() {
        return counselingID;
    }

    public void setCounselingId(int counselingID) {
        this.counselingID = counselingID;
    }

    public int getCustomerId() {
        return customerID;
    }

    public void setCustomerId(int customerID) {
        this.customerID = customerID;
    }

    public String getCounselingPlace() {
        return counselingPlace;
    }

    public void setCounselingPlace(String counselingPlace) {
        this.counselingPlace = counselingPlace;
    }

    public LocalDateTime getCounselingTime() {
        return counselingTime;
    }

    public void setCounselingTime(LocalDateTime counselingTime) {
        this.counselingTime = counselingTime;
    }

    public CounselingState getCounselingState() {
        return counselingState;
    }

    public void setCounselingState(CounselingState counselingState) {
        this.counselingState = counselingState;
    }
}
