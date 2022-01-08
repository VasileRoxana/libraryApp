package com.awbd.book;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.util.ArrayList;
import java.util.List;


@EnableFeignClients
@SpringBootApplication
@EnableHystrix
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

//	@Bean(name = "API")
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI()
//				.info(new Info()
//						.title("Spring Cloud application API")
//						.version("1")
//						.description("demo Spring Cloud")
//						.termsOfService("http://swagger.io/terms/")
//						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
//	}

//	@Bean(name = "links")
//	public LinkDiscoverers discoverers() {
//		List<LinkDiscoverer> plugins = new ArrayList<>();
//		plugins.add(new CollectionJsonLinkDiscoverer());
//		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//
//	}
}
