package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.TransactionEntity;
import java.util.List;
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
    List<TransactionEntity> findByCustomerId(Long customerId);
}
