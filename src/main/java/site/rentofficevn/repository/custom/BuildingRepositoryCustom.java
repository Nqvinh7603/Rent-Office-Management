package site.rentofficevn.repository.custom;

import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder);
}
