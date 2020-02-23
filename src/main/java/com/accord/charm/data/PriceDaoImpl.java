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


import com.accord.charm.model.Price;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class PriceDaoImpl implements PriceDao{

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
	public Price findByNo(Integer priceid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("priceid", priceid);
                String sql = "select * from price WHERE priceid=:priceid";
		Price result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new PriceMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Price> findAll() {
		String sql = "SELECT * FROM price";
		List<Price> result = namedParameterJdbcTemplate.query(sql, new PriceDaoImpl.PriceMapper());
		return result;
	}
        
        @Override
        public int PriceIDExists(Integer priceid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("priceid", priceid);
                   
            String sql = "SELECT count(*) FROM price WHERE pricedid = :priceid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        
        @Override
	public void save(Price price) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO CURRENTPRICES(BARID, PRODUCTID, CURRENTPRICE) "
				+ "VALUES ( :BARID, :productid, :currentprice)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(price), keyHolder, new String[]{"priceid"});
                price.setPriceid(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Price price) {
            String sql = "UPDATE currentpries SET barid=:barid, productid=:productid, currentprice=:currentprice WHERE priceid=:priceid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(price));    
	}
        
        
        @Override
	public void delete(Integer priceid) {
		String sql = "DELETE FROM Currentprice WHERE priceid= :priceid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("priceid", priceid));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Price price) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("priceid", price.getPriceid());
		paramSource.addValue("barid", price.getBarid());
                paramSource.addValue("productid", price.getProductid());
                paramSource.addValue("currentprice", price.getCurrentprice());
                return paramSource;
	}

        
        
	private static final class PriceMapper implements RowMapper<Price> {

		public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
			Price price = new Price();
                        price.setPriceid(rs.getInt("priceid"));
                        price.setBarid(rs.getInt("barid"));
                        price.setProductid(rs.getInt("productid"));
                        price.setCurrentprice(rs.getDouble("currentprice"));
                        return price;
		}
	}   
}
