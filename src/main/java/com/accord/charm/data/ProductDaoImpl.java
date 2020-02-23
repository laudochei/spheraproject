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


import com.accord.charm.model.Product;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class ProductDaoImpl implements ProductDao{

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
	public Product findByNo(Integer productid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productid", productid);
                String sql = "select * from product WHERE productid=:productid";
		Product result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}
        
        
        @Override
	public List<Product> findAll() {
		String sql = "SELECT * FROM product";
		List<Product> result = namedParameterJdbcTemplate.query(sql, new ProductDaoImpl.ProductMapper());
		return result;
	}
        
        @Override
        public int ProductIDExists(Integer productid) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("productid", productid);
                   
            String sql = "SELECT count(*) FROM product WHERE productdid = :productid";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        @Override
        public int ProductNameExists(String name) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", name);
            String sql = "select count(*) from product where name LIKE '" + name + "%'";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;
        }
        
        
        
        
        @Override
	public void save(Product product) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO BAR(BARID, NAME, LATITUDE, LONGITUDE, IMAGEURL) "
				+ "VALUES ( :name, :latitude, :longitude, :imageurl)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product), keyHolder, new String[]{"productid"});
                product.setProductid(keyHolder.getKey().intValue());
	}
        
        
        @Override
	public void update(Product product) {
            String sql = "UPDATE BAR SET BARID=:productid, NAME=:name, LATITUDE=:latitude, LONGITUDE=:latitude WHERE productid=:productid";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));    
	}
        
        
        @Override
	public void delete(Integer productid) {
		String sql = "DELETE FROM BAR WHERE productid= :productid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("productid", productid));
	}
        
               
        private SqlParameterSource getSqlParameterByModel(Product product) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("productid", product.getProductid());
		paramSource.addValue("name", product.getName());
                paramSource.addValue("imageurl", product.getImageUrl());
                return paramSource;
	}

        
        
	private static final class ProductMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
                        product.setProductid(rs.getInt("productid"));
                        product.setName(rs.getString("name"));
                        product.setImageUrl(rs.getString("imageurl"));
                        return product;
		}
	}   
}
