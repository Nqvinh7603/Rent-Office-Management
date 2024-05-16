package site.rentofficevn.repository.custom;

import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
    int countByCondition(BuildingSearchBuilder buildingSearchBuilder);
}
