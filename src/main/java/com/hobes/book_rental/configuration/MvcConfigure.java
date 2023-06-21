package com.hobes.book_rental.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfigure implements WebMvcConfigurer{
	
	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(source());
		return validator;
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
			"classpath:message-src/Author/author",
			"classpath:message-src/Category/category",
			"classpath:message-src/Member/member",
			"classpath:message-src/Book/book"
		};
		
		source.setBasenames(baseNames);
		source.setDefaultEncoding("UTF-8");
		return source;
	}

}
