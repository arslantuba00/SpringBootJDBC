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

import com.bilgeadam.SpringBootRestJDBC.model.Konu;

@Repository
public class KonuRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Konu> getAll() {

		List<Konu> liste;
		String sql = "SELECT * FROM \"OBS\".\"KONU\"";
		RowMapper<Konu> row_mapper = new RowMapper<Konu>() {

			@Override
			public Konu mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Konu(result.getLong("ID"), result.getString("NAME"));
			}
		};
		liste = jdbcTemplate.query(sql, row_mapper);
		return liste;
	}

	public Konu getById(long id) {
		String sql = "SELECT * FROM \"OBS\".\"KONU\" WHERE \"ID\" = :ID";
		RowMapper<Konu> row_mapper = new RowMapper<Konu>() {
			@Override
			public Konu mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Konu(result.getLong("ID"), result.getString("NAME"));
			}
		};
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, row_mapper);
	}

	public boolean save(Konu konu) {
		String sql = "INSERT INTO \"OBS\".\"KONU\"(\"NAME\") VALUES (:NAME)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", konu.getNAME());
	
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	public boolean deleteById(Long id)
	{
		String sql = "DELETE FROM \"OBS\".\"KONU\" WHERE \"ID\" = :ID"; 
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("ID",id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
}
