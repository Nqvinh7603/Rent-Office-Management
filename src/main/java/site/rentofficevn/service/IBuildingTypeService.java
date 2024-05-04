package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.BuildingTypesResponse;

import java.util.List;

public interface IBuildingTypeService {
    List<BuildingTypesResponse> getAll();  // hiển thị all type building
    List<BuildingTypesResponse> getAllByBuilding(BuildingDTO buildingDTO); // checked =

}
