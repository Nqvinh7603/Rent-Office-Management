package site.rentofficevn.service;

import javassist.NotFoundException;
import site.rentofficevn.dto.AssignmentDTO;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.exception.MyException;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findBuildingById(Long id);
    BuildingDTO createAndUpdateBuilding(BuildingDTO buildingDTO) throws MyException;
    void assignmentBuilding(AssignmentDTO assignmentDTO) throws NotFoundException;
    void delete(List<Long> buildingIds);
    BuildingDTO getBuildingDetails(Long id);
    List<BuildingSearchResponse> pageBuilding(Pageable pageable, BuildingSearchRequest buildingSearchRequest);
    int getTotalItems();
}
