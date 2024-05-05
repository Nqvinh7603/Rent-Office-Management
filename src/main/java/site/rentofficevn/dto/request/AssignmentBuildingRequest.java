package site.rentofficevn.dto.request;
import java.util.*;

public class AssignmentBuildingRequest {
    private List<Integer> staffIds = new ArrayList<>();

    public List<Integer> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Integer> staffIds) {
        this.staffIds = staffIds;
    }
    public void sanitize() {
        if (staffIds != null) {
            staffIds.removeIf(Objects::isNull);
        }
    }
}
