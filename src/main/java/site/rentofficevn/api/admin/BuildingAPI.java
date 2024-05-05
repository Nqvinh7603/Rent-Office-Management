package site.rentofficevn.api.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingDeleteRequest;
import site.rentofficevn.service.impl.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    BuildingService buildingService;

    @PutMapping
    public BuildingDTO updateBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) {
        BuildingDTO buildingUpdate = buildingService.updateBuilding(buildingDTO);
        return buildingUpdate;
    }

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody(required = false) BuildingDTO buildingDTO) {
        return buildingService.createBuilding(buildingDTO);
    }

    @DeleteMapping
    public BuildingDeleteRequest deleteBuilding(@RequestBody BuildingDeleteRequest buildingDeleteRequest) throws NotFoundException {
        buildingService.removeBuilding(buildingDeleteRequest);
        return buildingDeleteRequest;
    }

}