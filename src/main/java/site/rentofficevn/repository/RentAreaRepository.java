package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuilding(BuildingEntity buildingEntity);
    List<RentAreaEntity> findByBuildingId(Long id);
    void deleteByBuilding_Id(Long id);
    void deleteByBuilding_IdIn(List<Long> id);
}
