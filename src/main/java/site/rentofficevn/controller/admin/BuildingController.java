package site.rentofficevn.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.service.impl.BuildingService;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView ("admin/building/list");
        mav.addObject("modelSearch", buildingDTO);
        mav.addObject("buildings", buildingService.findAll());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit() {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel", new BuildingDTO());
        return mav;
    }
}
