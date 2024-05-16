package site.rentofficevn.dto.request;
import java.util.*;

public class AssignmentBuildingRequest {
    private List<Long> staffIds = new ArrayList<>();
    private Long buildingId;
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

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
