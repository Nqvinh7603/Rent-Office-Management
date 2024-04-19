package site.rentofficevn.api.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.rentofficevn.dto.BuildingDTO;

@RestController(value = "buildingAPIOfAmin")
public class BuildingAPI {
    @PostMapping("/api/building")
    public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding){
        return newBuilding;
    }
}