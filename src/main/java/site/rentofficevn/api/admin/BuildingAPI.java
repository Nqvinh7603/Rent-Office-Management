package site.rentofficevn.api.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingDeleteRequest;
import site.rentofficevn.dto.response.StaffResponseDTO;
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


    @PutMapping
    public BuildingDTO updateBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) {
        BuildingDTO buildingUpdate = buildingService.updateBuilding(buildingDTO);
        return buildingUpdate;
    }

    @DeleteMapping
    public BuildingDeleteRequest deleteBuilding(@RequestBody BuildingDeleteRequest buildingDeleteRequest) throws NotFoundException {
        buildingService.removeBuilding(buildingDeleteRequest);
        return buildingDeleteRequest;
    }

    // assigment building to staff
    @PostMapping("/{id}/assignment")
    public AssignmentBuildingRequest assignmentBuilding(@RequestBody(required = false) AssignmentBuildingRequest assignmentBuilding
            ,@PathVariable("id") Long buildingId) {
        assignmentBuilding.sanitize();
        buildingService.assignmentBuilding(assignmentBuilding, buildingId);
        return assignmentBuilding;
    }

    // api load satff
    @GetMapping("/{id}/staff")
    public List<StaffResponseDTO> loadStaffByBuilding(@PathVariable("id") Long id) {
        return userService.finAllStaffByBuilding(id);
    }

}