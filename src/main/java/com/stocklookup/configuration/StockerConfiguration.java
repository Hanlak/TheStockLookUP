package com.stocklookup.configuration;

import com.stocklookup.dao.BuySellDao;
import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.dao.BuySellUpdateDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.stocklookup")
public class StockerConfiguration {
  @Bean
  public BuySellDao buySellDao() {
    return new BuySellDao();
  }

  @Bean
  public BuySellGetterDao buySellGetterDao() {
    return new BuySellGetterDao();
  }

  @Bean
  public BuySellUpdateDao buySellUpdateDao() {
    return new BuySellUpdateDao();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/thestocker");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    /**
     * To CREATE THE TABLE IF NOT EXISTS AT THE STARTUP OF THE APPLICATION
     *
     * <p>the script file schema-sql.sql will execute here
     */
    // SCHEMA INIT
    Resource intischema = new ClassPathResource("/scripts/schema-mysql.sql");
    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(intischema);
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
