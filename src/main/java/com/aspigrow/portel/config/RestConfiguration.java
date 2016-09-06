package com.aspigrow.portel.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Resource configuration properties loaded from various property files
 * 
 * @author Ramachandran.K
 */
@Configuration
@ComponentScan(RestConfiguration.BASE_PACKAGE)
public class RestConfiguration{
 
    public static final String SWAGGER_BASE_PATH = "/";
    
	public static final String BASE_PACKAGE = "com.aspigrow.*";
    
    /**
     * Bean configuration for swagger api host and base path to referes the api
     * documents in the rest
     */
    @Bean
    public BeanConfig beanConfig(){
	BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion( "1.0.0" );
        beanConfig.setResourcePackage("com.aspigrow.portel");
        beanConfig.setScan( true );
        beanConfig.setBasePath( SWAGGER_BASE_PATH );
        beanConfig.setDescription( "Swagger api models for aspigrow developers. Which help developer to easy way to test their api" );
        beanConfig.setTitle( "Fashioned Documents" );
        return beanConfig;
	}
    
    @Bean
    public JavaMailSender mailSender() {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.elasticemail.com");
    	mailSender.setPort(2525);
    	mailSender.setProtocol("smtp");
    	mailSender.setUsername("ramachandran.shinnedhawks@gmail.com"); // specify in molgenis-server.properties
    	mailSender.setPassword("a0026de0-d957-4fc0-bdcb-57eb0fddf706"); // specify in molgenis-server.properties
    	Properties javaMailProperties = new Properties();
    	javaMailProperties.setProperty("mail.smtp.auth", "true");
    	javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
    	javaMailProperties.setProperty("mail.transport.protocol", "smtp");
    	mailSender.setJavaMailProperties(javaMailProperties);
    	return mailSender;
    }
}
