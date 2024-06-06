package site.rentofficevn.repository.custom;

import org.springframework.data.domain.Pageable;
import site.rentofficevn.builder.CustomerSearchBuilder;
import site.rentofficevn.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> findCustomer(CustomerSearchBuilder customerSearchBuilder, Pageable pageable);
    int countByCondition(CustomerSearchBuilder customerSearchBuilder);
}
