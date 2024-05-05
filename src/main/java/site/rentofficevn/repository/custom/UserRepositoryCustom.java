package site.rentofficevn.repository.custom;

import site.rentofficevn.repository.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findByRole(String roleCode);
}
