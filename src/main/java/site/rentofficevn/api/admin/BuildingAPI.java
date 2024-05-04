package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.StaffResponseDTO;
import site.rentofficevn.service.impl.BuildingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    BuildingService buildingService;

    // update building
    @PutMapping
    public BuildingDTO updateBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) {
        BuildingDTO buildingUpdate = buildingService.updateBuilding(buildingDTO);
        return buildingUpdate;
    }

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) {
        return buildingService.createBuilding(buildingDTO);
    }
}