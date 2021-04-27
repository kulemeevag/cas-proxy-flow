package com.example.casproxyflow.config;

import com.kakawait.spring.boot.security.cas.autoconfigure.CasHttpSecurityConfigurer;
import com.kakawait.spring.boot.security.cas.autoconfigure.CasSecurityCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@Conditional(CasSecurityCondition.class)
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();
        // Applying CAS security on current HttpSecurity (FilterChain)
        // I'm not using .apply() from HttpSecurity due to following issue
        // https://github.com/spring-projects/spring-security/issues/4422
        CasHttpSecurityConfigurer.cas().configure(http);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}
