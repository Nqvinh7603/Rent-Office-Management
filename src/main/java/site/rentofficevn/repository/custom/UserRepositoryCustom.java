package site.rentofficevn.repository.custom;

import site.rentofficevn.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> getAllStaff();
    List<UserEntity> getAllStaffByBuilding(Long buildingId);
}
