package site.rentofficevn.dto.request;

import java.util.*;

public class BuildingDeleteRequest {
    private List<Long> buildingId = new ArrayList<>();

    public List<Long> getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(List<Long> buildingId) {
        this.buildingId = buildingId;
    }
}
