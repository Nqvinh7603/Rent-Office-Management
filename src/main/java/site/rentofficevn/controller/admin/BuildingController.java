package site.rentofficevn.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.service.IDistrictService;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.BuildingTypesService;
import site.rentofficevn.service.impl.DistrictService;
import site.rentofficevn.service.impl.UserService;

@Controller
@RequestMapping("/admin")
public class BuildingController {
    @Autowired
    BuildingService buildingService;

    @Autowired
    UserService userService;

    @Autowired
    private BuildingTypesService buildingTypesService;

    @Autowired
    private DistrictService districtService;

    @GetMapping("/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
            ModelAndView mav = new ModelAndView("admin/building/list");
            mav.addObject("buildings", buildingService.findAll(buildingSearchRequest));
            mav.addObject("districts", districtService.getAllDistrict());
            mav.addObject("staffmaps", userService.getAllStaff());
            mav.addObject("buildingTypes", buildingTypesService.getAll());
            return mav;
    }
    @GetMapping("/building-edit")
    public ModelAndView buildingEdit() {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel", new BuildingDTO());
        return mav;
    }
}
