package site.rentofficevn.dto.request;


import site.rentofficevn.dto.AbstractDTO;

import java.util.*;

public class AssignmentBuildingRequest extends AbstractDTO{

    private Long buildingId;
    private List<Long> staffIds = new ArrayList<>();

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long  buildingId) {
        this.buildingId = buildingId;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }
}
