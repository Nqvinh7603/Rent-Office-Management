package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.repository.custom.CustomerRepositoryCustom;

import java.util.List;

public interface CustomerRepository extends CustomerRepositoryCustom,JpaRepository<CustomerEntity, Long>{
    Long countByIdIn(List<Long> customerIds);
    void deleteByIdIn(List<Long> ids);
}
