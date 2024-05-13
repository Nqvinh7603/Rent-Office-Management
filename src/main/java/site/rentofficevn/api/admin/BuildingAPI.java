package site.rentofficevn.api.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.AssignmentDTO;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.dto.response.StaffResponseDTO;
import site.rentofficevn.exception.MyException;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.UserService;

import java.util.*;
@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    BuildingService buildingService;

    @Autowired
    private UserService userService;
    //create and update
    @PutMapping
    public BuildingDTO createAndUpdateBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) throws MyException {
        BuildingDTO newBuilding = buildingService.createAndUpdateBuilding(buildingDTO);
        return newBuilding;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuildings(@RequestBody List<Long> ids) { // @RequestBody
        if (ids.size() != 0) {
            buildingService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }
    // assigment building to staff
    @PostMapping("/assignment")
    public Long assignmentBuilding(@RequestBody(required = false) AssignmentDTO assignmentDTO) {
        buildingService.assignmentBuilding(assignmentDTO);
        return assignmentDTO.getBuildingid();
        //return null;
    }
    // api load satff
    @GetMapping("/{id}/staff")
    public List<StaffResponseDTO> loadStaffByBuilding(@PathVariable("id") Long id) {
        return userService.finAllStaffByBuilding(id);
    }

    @PostMapping("/page")
    public List<BuildingSearchResponse> pageBuilding(Pageable pageable, BuildingSearchRequest buildingSearch) {
        return buildingService.pageBuilding(pageable, buildingSearch);
    }

}