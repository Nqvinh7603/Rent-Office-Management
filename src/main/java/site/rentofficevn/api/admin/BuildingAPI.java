package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.exception.MyException;
import site.rentofficevn.service.impl.BuildingService;

import java.util.*;


@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    BuildingService buildingService;

    //create and update
    @PostMapping
    public ResponseEntity<BuildingDTO> save(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.save(buildingDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuildings(@RequestBody List<Long> ids) { // @RequestBody
        buildingService.delete(ids);
        return ResponseEntity.noContent().build();
    }
    // assigment building to staff
    @PostMapping("/assignment-building")
    public ResponseEntity<Void> assignmentBuilding(@RequestBody AssignmentBuildingRequest assignmentBuilding){
        buildingService.assignmentBuildingToStaffs(assignmentBuilding);
        return ResponseEntity.noContent().build();
    }
    // api load satff
    @GetMapping("/{buildingId}/staffs")
    public ResponseEntity<List<AssignmentStaffResponse>> loadStaffByBuilding(@PathVariable Long buildingId) {
        return ResponseEntity.ok(buildingService.loadStaffByBuildingId(buildingId));
    }
}