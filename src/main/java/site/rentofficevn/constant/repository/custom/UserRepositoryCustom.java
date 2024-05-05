package site.rentofficevn.constant.repository.custom;

import site.rentofficevn.constant.repository.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findByRole(String roleCode);
}
