package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.repository.custom.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> getAllStaff() {
        StringBuilder sql = new StringBuilder("Select * from user as u inner join user_role as ur on u.id = ur.userid ");
        sql.append(" where ur.roleid = 2 and u.status = 1 ");
        Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> getAllStaffByBuilding(Long buildingId) {
        StringBuilder sql = new StringBuilder("select * from user as u inner join assignmentbuilding as ab on u.id = ab.staffid ");
        sql.append(" where ab.buildingid = ").append(buildingId);
        Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
        return query.getResultList();
    }
}
