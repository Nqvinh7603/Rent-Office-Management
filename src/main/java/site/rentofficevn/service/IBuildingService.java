package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;

import java.util.List;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findBuildingById(Long id);
    BuildingDTO updateBuilding(BuildingDTO buildingDTO);
    void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingId);
    void delete(List<Long> buildingIds);
    BuildingDTO getBuildingDetails(Long id);
}
