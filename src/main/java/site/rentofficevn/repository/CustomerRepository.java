package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

}
