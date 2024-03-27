package site.rentofficevn.repository;

import site.rentofficevn.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findOneByCode(String code);
}
