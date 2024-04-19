package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;

import java.util.List;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    void save(BuildingDTO buildingDTO);
}
