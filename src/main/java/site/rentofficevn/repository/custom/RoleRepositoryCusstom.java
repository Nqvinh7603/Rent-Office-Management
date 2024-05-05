package site.rentofficevn.repository.custom;

import site.rentofficevn.repository.entity.RoleEntity;

public interface RoleRepositoryCusstom {
    RoleEntity findByCode(String code);
}
