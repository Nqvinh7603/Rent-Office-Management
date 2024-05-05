package site.rentofficevn.constant.repository.custom;

import site.rentofficevn.constant.repository.entity.RoleEntity;

public interface RoleRepositoryCusstom {
    RoleEntity findByCode(String code);
}
