package com.fintech.contractor.config;

import com.fintech.contractor.auditor.AuditorAwareImpl;
import com.fintech.contractor.repository.sql.SQLContractorRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Configuration file for spring boot application
 * @author Matushkin Anton
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.fintech.contractor.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AppConfig {

    @Bean
    public org.springframework.data.domain.AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public SQLContractorRepository sqlContractorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new SQLContractorRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Contractor API")
                        .description("An API for serving operations with contractors.")
                        .version("0.0.1-SNAPSHOT")
                );
    }

}
