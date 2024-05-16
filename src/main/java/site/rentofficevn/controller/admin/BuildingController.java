package site.rentofficevn.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.BuildingTypesService;
import site.rentofficevn.service.impl.DistrictService;
import site.rentofficevn.service.impl.UserService;
import site.rentofficevn.utils.DisplayTagUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ModelAndView buildingList(HttpServletRequest request, @ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest) {
        try {
            ModelAndView mav = new ModelAndView("admin/building/list");

            DisplayTagUtils.of(request, buildingSearchRequest);
            Pageable pageable = PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems());
            List<BuildingSearchResponse> pageBuilding = buildingService.pageBuilding(pageable, buildingSearchRequest);

            buildingSearchRequest.setListResult(pageBuilding);
            buildingSearchRequest.setTotalItems(buildingService.getTotalItems()); // set tổng số item trong

            mav.addObject(SystemConstant.BUILDINGS, buildingSearchRequest);
            mav.addObject(SystemConstant.STAFF_MAP, userService.getStaffMaps());
            mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getAllDistrict());
            mav.addObject(SystemConstant.BUILDING_TYPE_MAP, buildingTypesService.getAll());
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/building-edit")
    public ModelAndView buildingEdit(@RequestParam(name = "buildingid", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject(SystemConstant.MODEL_BUILDING_EDIT, buildingService.getBuildingDetails(id));
        return mav;
    }
}
