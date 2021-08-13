/**
 * 
 */
package application;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.cloud.vision.v1.ImageAnnotatorClient;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Billy Bissic
 *
 */
@EnableSwagger2
@SpringBootApplication
public class AssessmentService extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(AssessmentService.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AssessmentService.class);
	}
	
    @Bean
    public Docket swaggerConfiguration() {
    
    	return new Docket(DocumentationType.SWAGGER_2)
    			.select()
    			.paths(PathSelectors.any())
    			.apis(RequestHandlerSelectors.any())
    			.build()
    			.apiInfo(apiDetails());
    }
    
    private ApiInfo apiDetails() {
    	return new ApiInfo(
    			"Yelp Wrapper",
    			"API for interfacing with yelp.",
    			"1.0",
    			"Demo",
    			new springfox.documentation.service.Contact("Billy Bissic", "https://github.com/billybissic", "billy@magnificenteyes.com"),
    			"API License",
    			"http://magnificenteyes.com/license",
    			Collections.emptyList());
    }
    
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
