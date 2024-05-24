package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.repository.custom.CustomerRepositoryCustom;

import java.util.List;

public interface CustomerRepository extends CustomerRepositoryCustom,JpaRepository<CustomerEntity, Long>{
    Long countByIdIn(List<Long> customerIds);
    void deleteByIdIn(List<Long> ids);
    @Query("select t from TransactionEntity t where t.customer.id = :customerId")
    List<TransactionEntity> findTransactionByCustomerId(@Param("customerId") Long customerId);
}
