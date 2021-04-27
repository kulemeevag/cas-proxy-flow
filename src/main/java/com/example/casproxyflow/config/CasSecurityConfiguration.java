package com.example.casproxyflow.config;

import com.kakawait.spring.boot.security.cas.autoconfigure.CasSecurityCondition;
import com.kakawait.spring.boot.security.cas.autoconfigure.CasSecurityConfigurerAdapter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Conditional(CasSecurityCondition.class)
public class CasSecurityConfiguration extends CasSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}
