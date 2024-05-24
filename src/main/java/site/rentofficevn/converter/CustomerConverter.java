package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.builder.CustomerSearchBuilder;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.CustomerSearchRequest;
import site.rentofficevn.dto.response.CustomerSearchResponse;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.utils.DateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public CustomerConverter(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public CustomerDTO toDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    public CustomerEntity toEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }

    public CustomerSearchBuilder convertParamToBuilder(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder result = new CustomerSearchBuilder.Builder()
                .setFullName(customerSearchRequest.getFullName())
                .setPhone(customerSearchRequest.getPhone())
                .setEmail(customerSearchRequest.getEmail())
                .setStaffId(customerSearchRequest.getStaffId())
                .build();
        return result;
    }

    public CustomerSearchResponse toSearchResponse(CustomerEntity customerEntity) {
        CustomerSearchResponse result = modelMapper.map(customerEntity, CustomerSearchResponse.class);

        result.setCreatedDate(getFormattedDate(customerEntity));
        result.setStaffName(getFormattedStaffName(customerEntity.getId()));
        return result;
    }

    private String getFormattedDate(CustomerEntity customerEntity) {
        String createdDate = DateUtils.convertDateToString(customerEntity.getCreatedDate());
        if (createdDate != null) {
            return createdDate;
        } else {
            return DateUtils.convertDateToString(customerEntity.getModifiedDate());
        }
    }

    private String getFormattedStaffName(Long id) {
        List<String> staffNames = userRepository.findFullNameByCustomerId(id);
        if (staffNames != null && !staffNames.isEmpty()) {
            return staffNames.stream().collect(Collectors.joining(", "));
        }
        return null;
    }
}
