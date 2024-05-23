package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import site.rentofficevn.converter.CustomerConverter;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.repository.CustomerRepository;
import site.rentofficevn.security.utils.SecurityUtils;
import site.rentofficevn.service.ICustomerService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter ;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public List<CustomerSearchResponse> findByCondition(CustomerSearchRequest customerSearchRequest, PageRequest pageRequest) {
        try {
            if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
                Long staffId = SecurityUtils.getPrincipal().getId();
                customerSearchRequest.setStaffId(staffId);
            }
            List<CustomerEntity> foundCustomers = customerRepository.findCustomer(customerConverter.convertParamToBuilder(customerSearchRequest), pageRequest);
            return foundCustomers.stream().map(customerConverter::toSearchResponse).collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public int countByCondition(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countByCondition(customerConverter.convertParamToBuilder(customerSearchRequest));
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = Optional.of(customerRepository.findById(id)).get()
                .orElseThrow(() -> new NotFoundException("Buliding not found!"));
        return customerConverter.toDTO(customerEntity);
    }


}
