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

import com.bilgeadam.SpringBootRestJDBC.model.Ders;

@Repository
public class DersRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Ders> getAll() {

		List<Ders> liste;
		String sql = "SELECT * FROM \"OBS\".\"DERS\"";
		RowMapper<Ders> row_mapper = new RowMapper<Ders>() {

			@Override
			public Ders mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ders(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("KONU_ID"));
			}
		};
		liste = jdbcTemplate.query(sql, row_mapper);
		return liste;
	}

	public Ders getById(long id) {
		String sql = "SELECT * FROM \"OBS\".\"DERS\" WHERE \"ID\" = :ID";
		RowMapper<Ders> row_mapper = new RowMapper<Ders>() {
			@Override
			public Ders mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ders(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("KONU_ID"));
			}
		};
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, row_mapper);
	}

	public boolean save(Ders ders) {
		String sql = "INSERT INTO \"OBS\".\"DERS\"(\"OGR_ID\",\"KONU_ID\") VALUES (:OGR_ID,:KONU_ID)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OGR_ID", ders.getOGR_ID());
		paramMap.put("KONU_ID", ders.getKONU_ID());
	
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	public boolean deleteById(Long id)
	{
		String sql = "DELETE FROM \"OBS\".\"DERS\" WHERE \"ID\" = :ID"; 
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("ID",id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
}
