package ua.boretskyi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = "Spring Boot Lab Work #5";
    private static final String description = "Documentation for the project";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket desksApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.any())
                .build();
    }
}
