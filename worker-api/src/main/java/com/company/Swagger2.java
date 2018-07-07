package com.company;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置初始化
 * 
 * @author chenxin
 * @date 2017年3月25日 下午1:08:34
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("RESTful API文档 - 家居健康机器人")
				.description("提供给前端调用者接口参考和查看API详细信息").termsOfServiceUrl("http://www.test.com/")
				.contact("chenxin").version("1.0").build();
	}

}
