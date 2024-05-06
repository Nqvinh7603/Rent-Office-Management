package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.AssignBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignBuildingEntity, Long> {
    AssignBuildingEntity findByBuildingAndUser(BuildingEntity building, UserEntity user);
    void deleteByBuildingIdIn(List<Long> ids);
    void deleteByBuildingId(Long id);
}
