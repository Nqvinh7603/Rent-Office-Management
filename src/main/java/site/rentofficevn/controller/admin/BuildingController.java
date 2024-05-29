package site.rentofficevn.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.service.impl.BuildingService;
import site.rentofficevn.service.impl.UserService;
import org.springframework.data.domain.PageRequest;
import site.rentofficevn.utils.MessageUtils;
import site.rentofficevn.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BuildingController {

    private final BuildingService buildingService;
    private final UserService userService;
    private final MessageUtils messageUtils;

    @Autowired
    public BuildingController(BuildingService buildingService, UserService userService,MessageUtils messageUtils) {
        this.buildingService = buildingService;
        this.userService = userService;
        this.messageUtils = messageUtils;
    }

    @GetMapping("/building/list")
    public ModelAndView listBuilding(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");

        buildingSearchRequest.setTableId("buildingList");
        List< BuildingSearchResponse> foundBuildings = buildingService.findByCondition(buildingSearchRequest,
                PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));

        buildingSearchRequest.setListResult(foundBuildings);
        buildingSearchRequest.setTotalItems(buildingService.countByCondition(buildingSearchRequest));
        buildingSearchRequest.setTotalPage((int) Math.ceil((double) buildingSearchRequest.getTotalItems() / buildingSearchRequest.getMaxPageItems()));

        mav.addObject(SystemConstant.BUILDINGS, foundBuildings);
        mav.addObject(SystemConstant.DISTRICT_MAP, buildingService.getDistrictMap());
        mav.addObject(SystemConstant.STAFF_MAP, userService.getStaffMap());
        mav.addObject(SystemConstant.BUILDING_TYPE_MAP, buildingService.getBuildingTypeMap());

        initMessageResponse(mav, request);
        return mav;
    }

    @GetMapping("/building/edit")
    public ModelAndView createBuilding(){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = new BuildingDTO();

        mav.addObject(SystemConstant.BUILDING, buildingDTO);
        mav.addObject(SystemConstant.DISTRICT_MAP, buildingService.getDistrictMap());
        mav.addObject(SystemConstant.BUILDING_TYPE_MAP, buildingService.getBuildingTypeMap());

        return mav;
    }
    @GetMapping("/building/edit/{id}")
    public ModelAndView updateBuilding(@PathVariable(value="id",required = false) Long buildingId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(buildingId);
        buildingDTO.setId(buildingId);

        mav.addObject(SystemConstant.BUILDING, buildingDTO);
        mav.addObject(SystemConstant.BUILDING_ID, buildingId);
        mav.addObject(SystemConstant.DISTRICT_MAP, buildingService.getDistrictMap());
        mav.addObject(SystemConstant.BUILDING_TYPE_MAP, buildingService.getBuildingTypeMap());

        initMessageResponse(mav, request);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter(SystemConstant.MESSAGE);
        if(!StringUtils.isNullOrEmpty(message)){
            Map<String, String> messageMap = messageUtils.getMessageForBuilding(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE));
        }
    }
}