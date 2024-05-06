package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.repository.custom.RentAreaRepositoryCustom;

import java.util.List;

public interface RentAreaRepository extends RentAreaRepositoryCustom,JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuilding(BuildingEntity buildingEntity);
    void deleteByBuilding_Id(Long id);
    void deleteByBuilding_IdIn(List<Long> id);
}
