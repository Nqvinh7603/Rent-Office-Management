package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.AssignmentBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    List<AssignmentBuildingEntity> findByBuildingId(Long buildingId);
}
