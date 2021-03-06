package cc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "cc")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目接口文档")
                .description("bingo")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();

    }
}

//@EnableWebMvc
//@EnableSwagger2
//@Configuration
//@ComponentScan(basePackages = "cc")
//public class SwaggerConfig {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // 注意修改此处的包名
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("接口列表 v1.1.0") // 任意，请稍微规范点
//                .description("接口测试") // 任意，请稍微规范点
//                .termsOfServiceUrl("http://127.0.0.1:8080/demo/") // 将“url”换成自己的ip:port
//                .contact("laowu") // 无所谓（这里是作者的别称）
//                .version("1.1.0")
//                .license("")
//                .licenseUrl("")
//                .build();
//    }
//}