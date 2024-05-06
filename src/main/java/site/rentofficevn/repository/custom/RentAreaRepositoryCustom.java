package site.rentofficevn.repository.custom;

import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import java.util.*;
public interface RentAreaRepositoryCustom {
    List<RentAreaEntity> findByRentArea(Long id);

    void saveRentAreas(List<RentAreaEntity> listRentAreaEntity, BuildingEntity buildingEntity);
}
