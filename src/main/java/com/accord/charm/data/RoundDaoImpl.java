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


import com.accord.charm.model.Round;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class RoundDaoImpl implements RoundDao{

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
	public Round findByNo(Integer roundid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roundid", roundid);
                String sql = "select * from round WHERE roundid=:roundid";
		Round result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new RoundMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Round> findAll() {
		String sql = "SELECT * FROM round";
		List<Round> result = namedParameterJdbcTemplate.query(sql, new RoundDaoImpl.RoundMapper());
		return result;
	}
        
        @Override
        public int RoundIDExists(Integer roundid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("roundid", roundid);
                   
            String sql = "SELECT count(*) FROM round WHERE rounddid = :roundid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        
        @Override
	public void save(Round round) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO BAR(BARID, NAME, LATITUDE, LONGITUDE, IMAGEURL) "
				+ "VALUES ( :name, :latitude, :longitude, :imageurl)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(round), keyHolder, new String[]{"roundid"});
                round.setRoundid(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Round round) {
            String sql = "UPDATE BAR SET BARID=:roundid, NAME=:name, LATITUDE=:latitude, LONGITUDE=:latitude WHERE roundid=:roundid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(round));    
	}
        
        
        @Override
	public void delete(Integer roundid) {
		String sql = "DELETE FROM BAR WHERE roundid= :roundid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("roundid", roundid));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Round round) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("roundid", round.getRoundid());
		paramSource.addValue("barid", round.getBarid());
                paramSource.addValue("orderedat", round.getOrderedat());
                return paramSource;
	}

        
        
	private static final class RoundMapper implements RowMapper<Round> {

		public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
			Round round = new Round();
                        round.setRoundid(rs.getInt("roundid"));
                        round.setBarid(rs.getInt("barid"));
                        round.setOrderedat(rs.getString("orderedat"));
                        return round;
		}
	}   
}
