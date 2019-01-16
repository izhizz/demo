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


/**
 * @version V1.0
 * @Title: Swagger配置类
 * @ClassName: com.newcapec.config.swagger.Swagger2Configuration.java
 * @Description:
 * @Copyright
 * @author: ff
 * @date:2019
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = "cc.demo.controller")
public class SwaggerConfiguration {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo()).forCodeGeneration(true)
                .select()
                //要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("cc.demo.controller"))

//                .paths(PathSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @param
     * @return springfox.documentation.service.ApiInfo
     * @Title: 构建API基本信息
     * @methodName: buildApiInfo
     * @Description:
     * @author: ff
     * @date: 2019
     */
    private ApiInfo buildApiInfo() {

        return new ApiInfoBuilder()
                .title("用户信息API文档")
                .description("这里除了查看接口功能外，还提供了调试测试功能")
                .contact("困死了")
                .version("1.0")
                .build();

    }

}