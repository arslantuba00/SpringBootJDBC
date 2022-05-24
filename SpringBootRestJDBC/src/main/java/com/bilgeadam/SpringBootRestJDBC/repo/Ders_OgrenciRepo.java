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

import com.bilgeadam.SpringBootRestJDBC.model.Ders_Ogrenci;

@Repository
public class Ders_OgrenciRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Ders_Ogrenci> getAll() {

		List<Ders_Ogrenci> liste;
		String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\"";
		RowMapper<Ders_Ogrenci> row_mapper = new RowMapper<Ders_Ogrenci>() {

			@Override
			public Ders_Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ders_Ogrenci(result.getLong("ID"), result.getLong("DERS_ID"), result.getLong("OGR_ID"), result.getLong("DEVAMSIZLIK"),
						result.getLong("NOT"));
			}
		};
		liste = jdbcTemplate.query(sql, row_mapper);
		return liste;
	}

	public Ders_Ogrenci getById(long id) {
		String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"ID\" = :ID";
		RowMapper<Ders_Ogrenci> row_mapper = new RowMapper<Ders_Ogrenci>() {
			@Override
			public Ders_Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Ders_Ogrenci(result.getLong("ID"), result.getLong("DERS_ID"), result.getLong("OGR_ID"),
						result.getLong("DEVAMSIZLIK"), result.getLong("NOT"));
			}
		};
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, row_mapper);
	}

	public boolean save(Ders_Ogrenci ders_ogrenci) {
		String sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\"(\"DERS_ID\",\"OGR_ID\",\"DEVAMSIZLIK\",\"NOT\") VALUES (:DERS_ID,:OGR_ID,:DEVAMSIZLIK,:NOT)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("DERS_ID", ders_ogrenci.getDERS_ID());
		paramMap.put("OGR_ID", ders_ogrenci.getOGR_ID());
		paramMap.put("DEVAMSIZLIK", ders_ogrenci.getDEVAMSIZLIK());
		paramMap.put("NOT", ders_ogrenci.getNOT());
	
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
	
	public boolean deleteById(Long id)
	{
		String sql = "DELETE FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"ID\" = :ID"; 
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("ID",id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}
}
