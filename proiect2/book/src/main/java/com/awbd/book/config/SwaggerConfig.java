package com.awbd.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Collection<VendorExtension> VENDOR_EXTENSION =
            new LinkedList();
    private static final Contact CONTACT = new
            Contact("apbdoo", "http://awbd", "proiect2@awbd");
    public static final ApiInfo API_INFO =
            new ApiInfo("API Proiect2 documentation",
                    "API proiect2", "1.0", "urn:tos",
                    CONTACT, "Apache 2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0",
                    VENDOR_EXTENSION);
    private static final List<String> ACCEPT_TYPES =
            Arrays.asList("application/xml", "application/json");
    private static final Set<String> PRODUCES =
            new HashSet<String>(ACCEPT_TYPES);

    @Bean(name = "docket")
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(API_INFO)
                .produces(PRODUCES);
        }

    @Bean
    public LinkDiscoverers discoverers() {

        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
}



//    http://localhost:8087/swagger-ui/
//    http://localhost:8087/v2/api-docs