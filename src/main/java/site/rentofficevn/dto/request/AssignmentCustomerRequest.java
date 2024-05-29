package site.rentofficevn.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssignmentCustomerRequest {
    private List<Long> staffIds = new ArrayList<>();
    private Long CustomerId;
    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }

    public void sanitize() {
        if (staffIds != null) {
            staffIds.removeIf(Objects::isNull);
        }
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }
}
