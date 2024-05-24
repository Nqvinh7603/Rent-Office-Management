package site.rentofficevn.service;

import org.springframework.data.domain.PageRequest;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.AssignmentCustomerRequest;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import java.util.List;
import java.util.Map;

public interface ICustomerService {
    List<CustomerSearchResponse> findByCondition(CustomerSearchRequest customerSearchRequest, PageRequest pageRequest);
    int countByCondition(CustomerSearchRequest customerSearchRequest);
    CustomerDTO findById(Long id);
    void delete(List<Long> customerIds);
    void assignmentCustomerToStaffs(AssignmentCustomerRequest assignmentCustomerRequest);
    List<AssignmentStaffResponse> loadStaffByCustomerId(Long customerId);
    CustomerDTO save(CustomerDTO customerDTO);
    Map<String,String> getTransactionMap();
}
