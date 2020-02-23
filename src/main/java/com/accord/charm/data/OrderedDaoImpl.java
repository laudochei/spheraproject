/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.data;


/**
 *
 * @author laud.c.ochei
 */



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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


import com.accord.charm.model.Ordered;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class OrderedDaoImpl implements OrderedDao{

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
	public Ordered findByNo(Integer orderedid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderedid", orderedid);
                String sql = "select * from ordered WHERE orderedid=:orderedid";
		Ordered result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new OrderedMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Ordered> findAll() {
		String sql = "SELECT * FROM ordered";
		List<Ordered> result = namedParameterJdbcTemplate.query(sql, new OrderedDaoImpl.OrderedMapper());
		return result;
	}
        
        @Override
        public int OrderedIDExists(Integer orderedid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orderedid", orderedid);
                   
            String sql = "SELECT count(*) FROM ordered WHERE ordereddid = :orderedid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        @Override
	public void save(Ordered ordered) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO BAR(BARID, NAME, LATITUDE, LONGITUDE, IMAGEURL) "
				+ "VALUES ( :name, :latitude, :longitude, :imageurl)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(ordered), keyHolder, new String[]{"orderedid"});
                ordered.setOrderid(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Ordered ordered) {
            String sql = "UPDATE Ordered SET BARID=:orderedid, NAME=:name, LATITUDE=:latitude, LONGITUDE=:latitude WHERE orderedid=:orderedid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(ordered));    
	}
        
        
        @Override
	public void delete(Integer orderedid) {
		String sql = "DELETE FROM Ordered  WHERE orderedid= :orderedid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("orderedid", orderedid));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Ordered ordered) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("orderedid", ordered.getOrderid());
		paramSource.addValue("roundid", ordered.getRoundid());
                paramSource.addValue("productid", ordered.getProductid());
                paramSource.addValue("actualprice", ordered.getActualprice());
                return paramSource;
	}

        
        
	private static final class OrderedMapper implements RowMapper<Ordered> {

		public Ordered mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ordered ordered = new Ordered();
                        ordered.setOrderid(rs.getInt("orderedid"));
                        ordered.setRoundid(rs.getInt("roundid"));
                        ordered.setProductid(rs.getInt("productid"));
                        ordered.setActualprice(rs.getDouble("actualprice"));
                        return ordered;
		}
	}   
}

