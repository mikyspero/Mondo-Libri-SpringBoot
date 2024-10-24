package com.generation.mondolibri.configuration;

import com.generation.mondolibri.filter.AccessCartFilter;
import com.generation.mondolibri.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AccessCartFilter> accessCartFilter() {
        FilterRegistrationBean<AccessCartFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessCartFilter());
        registrationBean.addUrlPatterns("/privata/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> accessAdminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/privata/admin/*"); // Matches anything under /privata/admin
        return registrationBean;
    }
}