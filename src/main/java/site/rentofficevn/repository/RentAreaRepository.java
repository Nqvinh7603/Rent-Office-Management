package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.repository.custom.RentAreaRepositoryCustom;
import site.rentofficevn.repository.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuildingId(Long id);
}
