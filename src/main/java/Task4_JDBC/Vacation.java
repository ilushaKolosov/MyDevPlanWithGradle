package Task4_JDBC;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data@Getter@ToString@Setter
class Vacation {
    private int vacationId;
    private String employeeName;
    private Date startDate;
    private Date endDate;
    private String vacationType;

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setVacationType(String vacationType) {
        this.vacationType = vacationType;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setVacationId(int vacationId) {
        this.vacationId = vacationId;
    }

    private boolean approved;
}