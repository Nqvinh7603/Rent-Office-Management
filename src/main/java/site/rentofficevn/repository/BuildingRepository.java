package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.repository.entity.BuildingEntity;
public interface BuildingRepository extends BuildingRepositoryCustom,JpaRepository<BuildingEntity, Long> {
}
