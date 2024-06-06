package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.converter.CustomerConverter;
import site.rentofficevn.converter.UserConverter;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.AssignmentCustomerRequest;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.repository.CustomerRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.security.utils.SecurityUtils;
import site.rentofficevn.service.ICustomerService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter, UserRepository userRepository, UserConverter userConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<CustomerSearchResponse> findByCondition(CustomerSearchRequest customerSearchRequest, PageRequest pageRequest) {
        try {
            if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
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

    @Override
    @Transactional
    public void delete(List<Long> customerIds) {
        if (!customerIds.isEmpty()) {
            Long count = customerRepository.countByIdIn(customerIds);
            if (count != customerIds.size()) {
                throw new NotFoundException("Some customers not found!");
            }
            customerRepository.deleteByIdIn(customerIds);
        }
    }

    @Override
    @Transactional
    public void assignmentCustomerToStaffs(AssignmentCustomerRequest assignmentCustomerRequest) {
        Long customerId = assignmentCustomerRequest.getCustomerId();
        List<Long> staffIdRequest = assignmentCustomerRequest.getStaffIds();
        CustomerEntity foundCustomer = Optional.of(customerRepository.findById(customerId)).get()
                .orElseThrow(() -> new NotFoundException("Customer not found!"));
        List<UserEntity> foundUsers = userRepository.findByIdIn(staffIdRequest);
        foundCustomer.setUserEntities(foundUsers);
        customerRepository.save(foundCustomer);
    }

    @Override
    public List<AssignmentStaffResponse> loadStaffByCustomerId(Long customerId) {
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(SystemConstant.ACTIVE_STATUS, "STAFF");

        return allStaffs.stream().map(staff ->
                {
                    AssignmentStaffResponse assignmentStaffResponse = userConverter.toAssignmentStaffResponse(staff);
                    for (CustomerEntity customer : staff.getCustomer()) {
                        if (customerId.equals(customer.getId())) {
                            assignmentStaffResponse.setChecked("checked");
                            break;
                        }
                    }
                    return assignmentStaffResponse;
                }
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        Long customerId = customerDTO.getId();
        CustomerEntity customerEntity = customerConverter.toEntity(customerDTO);
        if (customerId != null) {
            CustomerEntity foundCustomer = Optional.of(customerRepository.findById(customerId)).get()
                    .orElseThrow(() -> new NotFoundException("Customer not found!"));
            customerEntity.setCreatedDate(foundCustomer.getCreatedDate());
            customerEntity.setCreatedBy(foundCustomer.getCreatedBy());
            customerEntity.setModifiedDate(new Date());
            customerEntity.setModifiedBy(foundCustomer.getModifiedBy());
            customerEntity.setUserEntities(foundCustomer.getUserEntities());
            customerEntity.setTransactionEntities(foundCustomer.getTransactionEntities());
        }
        return customerConverter.toDTO(customerRepository.save(customerEntity));
    }
}
