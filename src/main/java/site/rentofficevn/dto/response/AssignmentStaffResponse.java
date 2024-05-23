package site.rentofficevn.dto.response;

import site.rentofficevn.dto.AbstractDTO;
import site.rentofficevn.dto.UserDTO;

public class AssignmentStaffResponse extends UserDTO {
    private String checked = "";

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}