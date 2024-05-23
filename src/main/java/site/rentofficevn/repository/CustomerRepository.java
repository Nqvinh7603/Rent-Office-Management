package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.repository.custom.CustomerRepositoryCustom;

public interface CustomerRepository extends CustomerRepositoryCustom,JpaRepository<CustomerEntity, Long>{

}
