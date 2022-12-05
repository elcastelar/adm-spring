package com.adm.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppContextConfiguration {
    private final Logger log = LoggerFactory.getLogger(AppContextConfiguration.class);

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final String userDBEnv = System.getenv("ADM_USER_DB");
        final String pwDBEnv = System.getenv("ADM_PW_DB");
        final String urlDBEnv = System.getenv("ADM_DB_URL");

        final String URLDBCon = urlDBEnv != null ? "jdbc:postgresql://" + urlDBEnv : "jdbc:postgresql://localhost:5432/adm";
        final String userDBCon = userDBEnv != null ? userDBEnv : "admin";
        final String userPWDBCon = pwDBEnv != null ? pwDBEnv : "123456";

        DataSource dataSource = new DriverManagerDataSource(URLDBCon, userDBCon, userPWDBCon);
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
//        props.put("hibernate.hbm2ddl.auto", "create");
        props.put("show_sql", true);
        props.put("connection.pool_size", 5);

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.adm");
        sessionFactoryBean.setHibernateProperties(props);

        return sessionFactoryBean;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }


}
