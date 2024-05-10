package kr.or.kosa.nux2.global.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Nux2WebApplicationInitializer implements WebApplicationInitializer {
    @Configuration
    @EnableWebMvc
    @EnableTransactionManagement
    @ComponentScan(basePackages = "kr.or.kosa.nux2.web")
    static class ServletConfig implements WebMvcConfigurer, ApplicationContextAware {
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        }


    }

    @Configuration
    @EnableTransactionManagement
    @ComponentScan(basePackages = "kr.or.kosa.nux2.domain")
    static class RootConfig{}

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // rootApplicationContext 생성
        var rootApplicationContext = new AnnotationConfigWebApplicationContext();
        rootApplicationContext.register(RootConfig.class); // WebApplicationContext의 Bean 생성을 위한 메타데이터(설정정보) 전달
        var loaderListener = new ContextLoaderListener(rootApplicationContext);
        servletContext.addListener(loaderListener);


        // ServletApplicationContext 생성
        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ServletConfig.class);
        var dispatcherServlet = new DispatcherServlet(applicationContext);// DispatcherServlet 쓰려면 WebApplicationContext 필요

    }
}
