package site.rentofficevn.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.BuildingTypesService;
import site.rentofficevn.service.impl.DistrictService;
import site.rentofficevn.service.impl.UserService;

@Controller
@RequestMapping("/admin")
public class BuildingController {

    private final BuildingService buildingService;
    private final UserService userService;
    private final BuildingTypesService buildingTypesService;
    private final DistrictService districtService;

    @Autowired
    public BuildingController(BuildingService buildingService, UserService userService, BuildingTypesService buildingTypesService, DistrictService districtService) {
        this.buildingService = buildingService;
        this.userService = userService;
        this.buildingTypesService = buildingTypesService;
        this.districtService = districtService;
    }

    @GetMapping("/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
            ModelAndView mav = new ModelAndView("admin/building/list");
            mav.addObject("buildings", buildingService.findAll(buildingSearchRequest));
            mav.addObject("districts", districtService.getAllDistrict());
            mav.addObject("staffmaps", userService.getAll());
            mav.addObject("buildingTypes", buildingTypesService.getAll());
            return mav;
    }
    @GetMapping("/building-edit")
    public ModelAndView buildingEdit(@RequestParam(name = "buildingid", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("modelBuildingEdit", buildingService.getBuildingDetails(id));
        return mav;
    }
}
