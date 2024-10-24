package com.generation.mondolibri.configuration;

import com.generation.mondolibri.interceptors.AdminInterceptor;
import com.generation.mondolibri.interceptors.AuthInterceptor;
import jakarta.servlet.SessionTrackingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    private final AdminInterceptor adminInterceptor;

    @Autowired
    public WebConfig(AuthInterceptor authInterceptor, AdminInterceptor adminInterceptor) {
        this.authInterceptor = authInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Bean
    public ServletContextInitializer sessionTrackingConfigurer() {
        return servletContext ->
                servletContext.setSessionTrackingModes(
                        Collections.singleton(SessionTrackingMode.COOKIE)
                );
    }

    // No @Bean annotation here
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Apply the interceptor to specific paths
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/cart/**"); // Change paths as needed
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/**/admin/**");
    }
}
