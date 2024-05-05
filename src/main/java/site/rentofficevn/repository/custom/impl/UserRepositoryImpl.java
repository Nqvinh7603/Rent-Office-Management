package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.repository.custom.UserRepositoryCustom;
import site.rentofficevn.repository.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserEntity> findByRole(String roleCode) {
        //JPQL
        String jqpl = "FROM UserEntity where roles.code= ?1";
        return entityManager.createQuery(jqpl, UserEntity.class)
                .setParameter(1, roleCode)
                .getResultList();
    }
}
