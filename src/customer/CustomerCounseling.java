package customer;

import java.time.LocalDateTime;

public class CustomerCounseling {

    private int counselingId;
    private int customerId;
    private String counselingPlace;
    private LocalDateTime counselingTime;
    private CounselingState counselingState;

    public int getCounselingId() {
        return counselingId;
    }

    public void setCounselingId(int counselingId) {
        this.counselingId = counselingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
