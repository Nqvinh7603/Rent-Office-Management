package site.rentofficevn.dto.request;

import site.rentofficevn.dto.AbstractDTO;

public class CustomerSearchRequest extends AbstractDTO {
    private String fullName;
    private String phonge;
    private String email;
    private Long staffId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhonge() {
        return phonge;
    }

    public void setPhonge(String phonge) {
        this.phonge = phonge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
