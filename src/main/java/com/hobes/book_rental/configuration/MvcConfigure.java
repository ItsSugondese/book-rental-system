package com.hobes.book_rental.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfigure implements WebMvcConfigurer{
	
	private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
	
	
	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(source());
		return validator;
	}
	
	
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods(GET, POST, PUT, DELETE)
		.allowedHeaders("*")
		.allowedOriginPatterns("*");
	}




	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
	}

    @Bean
    LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");

        return interceptor;
    }

	@Bean(name="localeResolver")
	LocaleResolver resolver() {
		final SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
		sessionResolver.setDefaultLocale(Locale.US);
		return sessionResolver ;
	}

	@Bean
	public MessageSource source() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		
		String[] baseNames = {
			"classpath:message-src/validation/error-alert"
		};
		
		source.setBasenames(baseNames);
		source.setDefaultEncoding("UTF-8");
		return source;
	}

}
