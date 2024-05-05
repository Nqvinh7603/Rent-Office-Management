package site.rentofficevn.constant.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.constant.repository.custom.RoleRepositoryCusstom;
import site.rentofficevn.repository.entity.RoleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleRepositoryImpl implements RoleRepositoryCusstom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public site.rentofficevn.constant.repository.entity.RoleEntity findByCode(String code) {
        //String sql = "SELECT * FROM role WHERE code = '" + code + "'"; //
        String jpql = "SELECT r FROM RoleEntity r WHERE r.code = :code";
        Query query = entityManager.createQuery(jpql, site.rentofficevn.constant.repository.entity.RoleEntity.class);
        query.setParameter("code", code);
        return (site.rentofficevn.constant.repository.entity.RoleEntity) query.getSingleResult();
    }
}
