package site.rentofficevn.api;/*
package site.rentofficevn.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.rentofficevn.model.dto.RentAreaDTO;
import site.rentofficevn.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/rent-area")
public class RentAreaAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping
    public List<RentAreaDTO> getRentArea(@RequestParam(value = "buildingId", required = false) Long buildingId) {
        return  buildingService.getRentAreaListByBuilding(buildingId);
    }

}
*/
