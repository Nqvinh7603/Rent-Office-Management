package site.rentofficevn.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.service.impl.CustomerService;
import site.rentofficevn.service.impl.UserService;
import site.rentofficevn.utils.DisplayTagUtils;
import site.rentofficevn.utils.MessageUtils;
import site.rentofficevn.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    private final CustomerService buildingService;
    private final UserService userService;
    private final MessageUtils messageUtils;

    @Autowired
    public CustomerController(CustomerService buildingService, UserService userService, MessageUtils messageUtils) {
        this.buildingService = buildingService;
        this.userService = userService;
        this.messageUtils = messageUtils;
    }

    @GetMapping("/customer-list")
    public ModelAndView listBuilding(@ModelAttribute("modelSearch") CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        customerSearchRequest.setTableId("buildingList");
        DisplayTagUtils.of(request, customerSearchRequest);

        List<BuildingSearchResponse> foundCustomers = buildingService.findByCondition(customerSearchRequest,
                PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));


        customerSearchRequest.setListResult(foundCustomers);
        customerSearchRequest.setTotalItems(buildingService.countByCondition(buildingSearchRequest));
        customerSearchRequest.setTotalPage((int) Math.ceil((double) customerSearchRequest.getTotalItems() / customerSearchRequest.getMaxPageItems()));

        mav.addObject(SystemConstant.BUILDINGS, foundCustomers);
        mav.addObject(SystemConstant.STAFF_MAP, userService.getStaffMap());

        initMessageResponse(mav, request);
        return mav;
    }

    @GetMapping("/customer-edit")
    public ModelAndView createBuilding(){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        BuildingDTO buildingDTO = new BuildingDTO();

        mav.addObject(SystemConstant.BUILDING, buildingDTO);

        return mav;
    }
    @GetMapping("/customer-edit-{id}")
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
            Map<String, String> messageMap = messageUtils.getMessageForCustomer(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE));
        }
    }
}
