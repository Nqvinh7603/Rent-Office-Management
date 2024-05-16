package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.dto.response.BuildingSearchResponse;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingSearchResponse> findByCondition(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findById(Long id);
    void delete(List<Long> buildingIds);
    Map<String, String> getDistrictMap();
    Map<String, String> getBuildingTypeMap();
    BuildingDTO save(BuildingDTO buildingDTO);
    List<AssignmentStaffResponse> loadStaffByBuildingId(Long buildingId);
    void assignmentBuildingToStaffs(AssignmentBuildingRequest assignmentBuildingRequest);
}
