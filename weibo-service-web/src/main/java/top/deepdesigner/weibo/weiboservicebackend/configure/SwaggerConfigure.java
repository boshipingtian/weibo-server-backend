/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */
package top.deepdesigner.weibo.weiboservicebackend.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com.cn
 * @Date: 2023/5/31 15:06
 * @Version: 1.0.0
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfigure {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
            .host("http://docs.deepdesigner.top")
            .apiInfo(apiInfo())
            .groupName("1.0版本")
            .enable(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("top.deepdesigner.weibo.weiboservicebackend.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .description("无")
            .termsOfServiceUrl("http://docs.deepdesigner.top")
            .contact("1619794359@qq.com")
            .version("1.0")
            .build();
    }

}