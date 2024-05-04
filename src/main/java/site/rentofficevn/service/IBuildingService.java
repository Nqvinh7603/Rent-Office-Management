package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    BuildingDTO createBuilding(BuildingDTO buildingDTO);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findBuildingById(Long id);
    BuildingDTO updateBuilding(BuildingDTO buildingDTO);
}
