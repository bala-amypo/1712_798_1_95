package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

import java.util.Map;

@Configuration
public class JpaConfig implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );
    }
}
