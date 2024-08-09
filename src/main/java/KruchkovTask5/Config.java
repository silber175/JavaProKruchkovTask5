package KruchkovTask5;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class Config {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?");
        config.setUsername("student");
        config.setPassword("student");
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setAutoCommit(true);
        return new HikariDataSource(config);
    }

    @Bean
    public CustProductDao custProductDao(DataSource dataSource) {
        return new CustProductDao(dataSource);
    }

    @Bean @DependsOn("custProductDao")
    public CustProductService custProductService(CustProductDao custProductDao) {
        return new CustProductService(custProductDao);
    }
}
