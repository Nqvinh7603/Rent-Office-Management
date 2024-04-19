package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.service.impl.BuildingService;

@RestController(value = "buildingAPIOfAmin")
public class BuildingAPI {
    @Autowired
    BuildingService buildingService;
    @PostMapping("/api/building")
    public BuildingDTO addBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
        return newBuilding;
    }
}