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

import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;

@Repository
//@Component
public class OgrenciRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Ogrenci> getAll() {

		List<Ogrenci> liste;
		String sql = "SELECT * FROM \"OBS\".\"OGRENCI\"";
		RowMapper<Ogrenci> row_mapper = new RowMapper<Ogrenci>() {

			@Override
			public Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("NUMBER"),
						result.getLong("YEAR"));
			}
		};
		liste = jdbcTemplate.query(sql, row_mapper);
		return liste;
	}

	public Ogrenci getById(long id) {
		String sql = "SELECT * FROM \"OBS\".\"OGRENCI\" WHERE \"ID\" = :ID";
		RowMapper<Ogrenci> row_mapper = new RowMapper<Ogrenci>() {
			@Override
			public Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("NUMBER"),
						result.getLong("YEAR"));
			}
		};
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, row_mapper);
	}

	public boolean save(Ogrenci ogrenci) {
		String sql = "INSERT INTO \"OBS\".\"OGRENCI\"(\"NAME\", \"NUMBER\",\"YEAR\") VALUES (:NAME,:NUMBER,:YEAR)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogrenci.getNAME());
		paramMap.put("NUMBER", ogrenci.getNUMBER());
		paramMap.put("YEAR", ogrenci.getYEAR());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	public boolean deleteById(Long id)
	{
		String sql = "DELETE FROM \"OBS\".\"OGRENCI\" WHERE \"ID\" = :ID"; 
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("ID",id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
}
