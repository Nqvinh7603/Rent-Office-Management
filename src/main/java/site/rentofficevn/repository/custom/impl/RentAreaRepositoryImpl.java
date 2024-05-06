package site.rentofficevn.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.custom.RentAreaRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    @Transactional
    public void saveRentAreas(List<RentAreaEntity> listRentAreaEntity, BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentareas = new ArrayList<>();
        if (listRentAreaEntity.size() > 0) {
            for (RentAreaEntity item : listRentAreaEntity) {
                rentareas.add(item);
            }
        }
        List<RentAreaEntity> rentareaEntities = rentAreaRepository.findByBuilding(buildingEntity) ;

        if (rentareas.size() > 0) {
            for (RentAreaEntity item : rentareaEntities) {
                entityManager.remove(item);
            }

            for (RentAreaEntity rentarea : rentareas) {
                entityManager.persist(rentarea);
            }
        }
    }

    @Override
    public List<RentAreaEntity> findByRentArea(Long id) {
        StringBuilder sql = new StringBuilder("select * from rentarea as r  inner join building as b on r.buildingid = b.id ");
        sql.append(" where b.id =  ").append(id);

        Query query = entityManager.createNativeQuery(sql.toString(), RentAreaEntity.class);
        return query.getResultList();
    }

}


