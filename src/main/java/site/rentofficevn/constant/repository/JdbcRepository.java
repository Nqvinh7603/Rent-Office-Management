package site.rentofficevn.constant.repository;

import java.util.List;

public interface JdbcRepository<T> {
	T findById(Long id);
	List<T> findByCondition(String sql);
	List<T> findAll();
}
