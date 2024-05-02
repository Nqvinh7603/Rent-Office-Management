package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends BuildingRepositoryCustom,JpaRepository<BuildingEntity, Long> {
}
