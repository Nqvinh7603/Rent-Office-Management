package site.rentofficevn.dto.response;

import site.rentofficevn.dto.AbstractDTO;

public class AssignmentStaffResponse extends AbstractDTO {
    private Long staffId;
    private String fullName;
    private String checked = "";

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
