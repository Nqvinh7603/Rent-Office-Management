package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import java.util.*;
public interface BuildingRepository extends BuildingRepositoryCustom,JpaRepository<BuildingEntity, Long> {
    void deleteByIdIn(List<Long> ids);
    @Query(value="select count(*) from building", nativeQuery = true)
    long countAllBuilding();
    Long countByIdIn(List<Long> buildingIds);
}
