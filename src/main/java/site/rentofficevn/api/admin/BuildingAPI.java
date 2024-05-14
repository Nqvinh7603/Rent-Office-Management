package site.rentofficevn.api.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
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
    public ResponseEntity<BuildingDTO> createAndUpdateBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) throws MyException {
        BuildingDTO newBuilding = buildingService.createAndUpdateBuilding(buildingDTO);
        return ResponseEntity.ok(newBuilding);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuildings(@RequestBody List<Long> ids) { // @RequestBody
        if (ids.size() != 0) {
            buildingService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }
    // assigment building to staff
    @PostMapping("/{id}/assignment")
    public ResponseEntity<AssignmentBuildingRequest> assignmentBuilding(@RequestBody(required = false) AssignmentBuildingRequest assignmentBuilding
            ,@PathVariable("id") Long buildingId) throws NotFoundException {
        assignmentBuilding.sanitize();
        buildingService.assignmentBuilding(assignmentBuilding, buildingId);
        return ResponseEntity.ok(assignmentBuilding);
    }
    // api load satff
    @GetMapping("/{id}/staff")
    public ResponseEntity<List<StaffResponseDTO>> loadStaffByBuilding(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.finAllStaffByBuilding(id));
    }
}