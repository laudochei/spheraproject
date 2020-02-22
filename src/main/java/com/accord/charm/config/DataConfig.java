package com.accord.charm.config;

/*
import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DataConfig {
      
        
        //mysql db on local machine 
    
        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("jdbc:mysql://localhost:3306/charm");
            db.setUsername("root");
            db.setPassword("welcome");
            return db;
        }
      
    
    
    
        
        //postgres db on local machine 
        /*
        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("org.postgresql.Driver");
            //db.setUrl("jdbc:postgresql://localhost:5432/jsondb");
            db.setUrl("jdbc:postgresql://localhost:5432/assetc");
            //db.setUrl("jdbc:postgresql://localhost:5432/products");
            db.setUsername("postgres");
            db.setPassword("welcome");
            return db;  
        }
       */
    
        
        
        

       /*
       //mysql db on heroku 
       @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("com.mysql.jdbc.Driver");
            db.setUrl("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_7ac45a05bab0085");
            db.setUsername("b4778f9d520c68");
            db.setPassword("132d6c92");
            return db;
        }
        */
        
        
        

        // postgresql db on heroku for TESTING 
        // remote connection
        // jdbc:postgresql://<host>:<port>/<dbname>?sslmode=require&user=<username>&password=<password>
        // jdbc:postgresql://ec2-23-21-246-25.compute-1.amazonaws.com:5432/d4qibc8i706shq?sslmode=require
        /*
        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("org.postgresql.Driver");
            db.setUrl("jdbc:postgresql://ec2-34-202-14-65.compute-1.amazonaws.com:5432/d5gbbc1ugiinuf?sslmode=require");
            //db.setUrl("jdbc:postgresql://ec2-23-21-246-25.compute-1.amazonaws.com:5432/d4qibc8i706shq?sslmode=require"); 
            db.setUsername("ub1fgcmafo7e80");
            db.setPassword("p3f642f9dc82e53e088eab26121669f9d054a9247a8ed614c9795893abdb2a9ed");
            return db;   
        }
        */
       
    
        // postgresql db on heroku for PRODUCTION
        /*
        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource db = new DriverManagerDataSource();
            db.setDriverClassName("org.postgresql.Driver");
            db.setUrl("jdbc:postgresql://ec2-18-213-81-211.compute-1.amazonaws.com:5432/daas7n4mb828ed?sslmode=require");
            db.setUsername("u71nkotuflvgkl");
            db.setPassword("p6afc0d31a6c1a17e102ec61c68b2183dc3944a1c492cb56adbee01d2efeeb377");
            return db;   
        }
        */
        

 /*
        @Bean
        public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
	}
  
        
        @Bean
        public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
            LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
            sfb.setDataSource(dataSource);
            sfb.setPackagesToScan(new String[] { "com.accord.charm.model"});
            Properties props = new Properties();
            props.setProperty("dialet", "org.hibernate.dialect.MySQL5InnoDBDialect");
            sfb.setHibernateProperties(props);
            return sfb;
        }
}


*/