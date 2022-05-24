  package com.bilgeadam.SpringBootRestJDBC.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestJDBC.model.Ogretmen;

@Repository
public class OgretmenRepo {
	@Autowired
	public JdbcTemplate jdbcTemplatee;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Ogretmen> getAll() {
		List<Ogretmen> liste;
		String sql = "SELECT * FROM \"OBS\".\"OGRETMEN\"";
		RowMapper<Ogretmen> row_mapper = new RowMapper<Ogretmen>() {
			@Override
			public Ogretmen mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
			}
		};
		liste = jdbcTemplatee.query(sql, row_mapper);
		return liste;
	}

	public Ogretmen getById(long id) {
		String sql = "SELECT * FROM \"OBS\".\"OGRETMEN\" WHERE \"ID\" = :ID";
		RowMapper<Ogretmen> row_mapper = new RowMapper<Ogretmen>() {
			@Override
			public Ogretmen mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
			}
		};
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, row_mapper);
	}
	
	public boolean save(Ogretmen ogretmen)
	{
		String sql = "INSERT INTO \"OBS\".\"OGRETMEN\"(\"NAME\", \"IS_GICIK\") VALUES (:NAME, CAST(:IS_GICIK AS bit))";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogretmen.getNAME());
		paramMap.put("IS_GICIK", ogretmen.isIS_GICIK() ? 1 : 0);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	
	public boolean deleteById(Long id)
	{
		String sql = "DELETE FROM \"OBS\".\"OGRETMEN\" WHERE \"ID\" = :ID"; 
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("ID",id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	

}
