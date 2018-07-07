package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.company.base.support.ApiRepositoryFactoryBean;
import com.comtom.bc.common.Constants;

/**
 * Web服务入口类，提供Web容器初始化
 * 
 * @author chenxin
 * @date 2017年3月25日 下午1:08:43
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = ApiRepositoryFactoryBean.class)
@EnableAsync
@ImportResource("classpath:application-jpa.xml")
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CharacterEncodingFilter initializeCharacterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding(Constants.CHARACTER_ENCODING);
		filter.setForceEncoding(true);
		return filter;
	}

}
