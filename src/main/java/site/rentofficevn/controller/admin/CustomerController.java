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
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import site.rentofficevn.service.impl.CustomerService;
import site.rentofficevn.service.impl.TransactionService;
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
    private final CustomerService customerService;
    private final UserService userService;
    private final TransactionService transactionService;
    private final MessageUtils messageUtils;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, TransactionService transactionService, MessageUtils messageUtils) {
        this.customerService = customerService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.messageUtils = messageUtils;
    }


    @GetMapping("/customer-list")
    public ModelAndView listController(@ModelAttribute("modelSearch") CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        customerSearchRequest.setTableId("customerList");
        DisplayTagUtils.of(request, customerSearchRequest);

        List<CustomerSearchResponse> foundCustomers = customerService.findByCondition(customerSearchRequest,
                PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));


        customerSearchRequest.setListResult(foundCustomers);
        customerSearchRequest.setTotalItems(customerService.countByCondition(customerSearchRequest));
        customerSearchRequest.setTotalPage((int) Math.ceil((double) customerSearchRequest.getTotalItems() / customerSearchRequest.getMaxPageItems()));

        mav.addObject(SystemConstant.CUSTOMERS, foundCustomers);
        mav.addObject(SystemConstant.STAFF_MAP, userService.getStaffMap());

        initMessageResponse(mav, request);
        return mav;
    }

    @GetMapping("/customer-edit")
    public ModelAndView createCustomer(){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = new CustomerDTO();
        mav.addObject(SystemConstant.CUSTOMER, customerDTO);
        return mav;
    }
    @GetMapping("/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable(value="id",required = false) Long customerId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findById(customerId);
        customerDTO.setId(customerId);

        mav.addObject(SystemConstant.CUSTOMER, customerDTO);
        mav.addObject(SystemConstant.CUSTOMER_ID, customerId);
        mav.addObject(SystemConstant.TRANSACTION_DATA, transactionService.getTransactionData(customerId));

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
