package yandex.eventservice.conf;

import com.mchange.v2.c3p0.AbstractComboPooledDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws Exception {
        AbstractComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.connection.driver_class"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.connection.url"));
        dataSource.setDataSourceName("event-service");
//        dataSource.setProperties(new Properties() {
//            {
//                setProperty("user", env.getProperty("jdbc.connection.username"));
//                setProperty("password", env.getProperty("jdbc.connection.password"));
//            }
//        });
        dataSource.setUser(env.getProperty("jdbc.connection.username"));
        dataSource.setPassword(env.getProperty("jdbc.connection.password"));
        return dataSource;
    }

    @Bean
    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
            }
        };
    }
}
