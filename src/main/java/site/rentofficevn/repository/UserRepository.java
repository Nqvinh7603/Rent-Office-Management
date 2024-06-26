package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.rentofficevn.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status, Pageable pageable);
    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
    long countByStatusNot(int status);
    UserEntity findOneByUserName(String userName);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
    List<UserEntity> findByIdIn(List<Long> ids);

    @Query("SELECT u.fullName " +
            "FROM CustomerEntity c " +
            "JOIN c.userEntities u " +
            "WHERE c.id = :customerId")
    List<String> findFullNameByCustomerId(@Param("customerId") Long customerId);

}
