package site.rentofficevn.constant.repository.custom.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import site.rentofficevn.annotation.Entity;
import site.rentofficevn.annotation.Table;
import site.rentofficevn.mapper.ResultsetMapper;
import site.rentofficevn.repository.JdbcRepository;
import site.rentofficevn.utils.ConnectionUtils;

public class JdbcRepositoryImpl<T> implements JdbcRepository<T> {
	private Class<T> tClass;

	public JdbcRepositoryImpl() {
		tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T findById(Long id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtils.getConnection();
			String tableName = null;

			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}

			String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
			List<T> results = resultsetMapper.maprow(rs, tClass);

			return results.size() > 0 ? results.get(0) : null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<T> findByCondition(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
			return resultsetMapper.maprow(rs, tClass);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	@Override
	public List<T> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String tableName = null;
			if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			//ParameterizedType để lấy cái mảng
			//
			String sql = "select * from "+tableName+"";
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
				
			ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
			return resultsetMapper.maprow(rs, tClass);
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(conn != null) {
			conn.close();}
				if(stmt != null) {
			stmt.close();}
				if(rs != null) {
			rs.close();}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
