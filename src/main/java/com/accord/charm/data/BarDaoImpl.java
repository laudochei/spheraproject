/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;

import com.accord.charm.model.Bar;

/**
 *
 * @author laud.c.ochei
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


import com.accord.charm.model.Bar;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class BarDaoImpl implements BarDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
        

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;      
	}
        
        
        DataSource dataSource;
        @Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
                
	}
        
        
        
        
        
	@Override
	public Bar findByNo(Integer barid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("barid", barid);
                String sql = "select * from bar WHERE barid=:barid";
		Bar result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new BarMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Bar> findAll() {
		String sql = "SELECT * FROM bar";
		List<Bar> result = namedParameterJdbcTemplate.query(sql, new BarDaoImpl.BarMapper());
		return result;
	}
        
        @Override
        public int BarIDExists(Integer barid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("barid", barid);
                   
            String sql = "SELECT count(*) FROM bar WHERE bardid = :barid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        @Override
        public int BarNameExists(String name) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            String sql = "select count(*) from bar where name LIKE '" + name + "%'";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        /*
        @Override
        public int checkForeignKey(String orgid) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orgid", orgid);
            String sql = "SELECT count(*) FROM company WHERE orgid = :orgid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        */
        
        
        
        @Override
	public void saveBar(Bar bar) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO BAR(BARID, NAME, LATITUDE, LONGITUDE, IMAGEURL) "
				+ "VALUES ( :name, :latitude, :longitude, :imageurl)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(bar), keyHolder, new String[]{"barid"});
                bar.setBarid(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Bar bar) {
            String sql = "UPDATE BAR SET BARID=:barid, NAME=:name, LATITUDE=:latitude, LONGITUDE=:latitude WHERE barid=:barid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(bar));    
	}
        
        
        @Override
	public void delete(Integer barid) {
		String sql = "DELETE FROM BAR WHERE barid= :barid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("barid", barid));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Bar bar) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("barid", bar.getBarid());
		paramSource.addValue("name", bar.getName());
                paramSource.addValue("latitude", bar.getLatitude());
		paramSource.addValue("longitude", bar.getLongitude());
                paramSource.addValue("imageurl", bar.getImageurl());
                return paramSource;
	}

        
        
	private static final class BarMapper implements RowMapper<Bar> {

		public Bar mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bar bar = new Bar();
                        bar.setBarid(rs.getInt("barid"));
                        bar.setName(rs.getString("name"));
                        bar.setLatitude(rs.getDouble("latitude"));
                        bar.setLongitude(rs.getDouble("longitude"));
                        bar.setImageurl(rs.getString("imageurl"));
                        return bar;
		}
	}   
}
