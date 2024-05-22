package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
}
