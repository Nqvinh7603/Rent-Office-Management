package site.rentofficevn.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.UserService;


@Controller
@RestController("/admin")
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    @Autowired
    UserService userService;

    @GetMapping("/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
        ModelAndView mav = new ModelAndView ("admin/building/list");
        mav.addObject("buildings", buildingService.findAll(buildingSearchRequest));
        //mav.addObject("staffmaps",userService.getStaffMaps());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit() {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel", new BuildingDTO());
        return mav;
    }
}
