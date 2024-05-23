package site.rentofficevn.service;

import org.springframework.data.domain.PageRequest;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import java.util.List;
public interface ICustomerService {
    List<CustomerSearchResponse> findByCondition(CustomerSearchRequest customerSearchRequest, PageRequest pageRequest);
    int countByCondition(CustomerSearchRequest customerSearchRequest);
    CustomerDTO findById(Long id);
}
