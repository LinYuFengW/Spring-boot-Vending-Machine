package com.ecommerce.config;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional; 

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "oracleEntityManagerFactory",
	transactionManagerRef = "oracleTransactionManager", 
	basePackages = { "com.ecommerce.oracle.dao" } //設置DAO放置路徑
)
public class OracleDataSourceConfig { 

   @Autowired
   @Qualifier("oracle")
   private DataSource dataSource; 

   @Autowired
   private HibernateProperties hibernateProperties; 

   @Autowired
   private JpaProperties jpaProperties; 

   @Primary
   @Bean(name = "oracleEntityManager")
   public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
      return entityManagerFactory(builder).getObject().createEntityManager();
   } 

   @Primary // 建立實體管理工廠
   @Bean(name = "oracleEntityManagerFactory") 
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
      return builder.dataSource(dataSource)
    		  .properties(getVendorProperties())
    		  .packages("com.ecommerce.entity").build(); //設置entity放置路徑
   } 
   
   // 取得pom.yml內的hibernate設置
   private Map<String, Object> getVendorProperties() {
      return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
   } 

   @Primary // 可在 Service method 上標注使用 @Transactional
   @Bean(name = "oracleTransactionManager")  
   public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
      return new JpaTransactionManager(entityManagerFactory(builder).getObject());
   }

}
