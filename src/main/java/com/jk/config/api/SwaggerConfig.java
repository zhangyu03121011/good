package com.jk.config.api;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

/**
 * @ClassName: SwaggerConfig 
 * @Description: Swagger2配置类
 * @author zhangxy
 * @date 2017年9月7日 下午5:06:20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * @Description: swagger摘要bean
	 * @Api：修饰整个类，描述Controller的作用
	 * @ApiOperation：描述一个类的一个方法，或者说一个接口
	 * @ApiParam：单个参数描述
	 * @ApiModel：用对象来接收参数
	 * @ApiProperty：用对象接收参数时，描述对象的一个字段
	 * @ApiResponse：HTTP响应其中1个描述
	 * @ApiResponses：HTTP响应整体描述
	 * @ApiIgnore：使用该注解忽略这个API
	 * @DateTime:2017年9月7日 下午4:56:36
	 * @return Docket
	 * @throws
	 */
	@Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)// 排除
                    return false;
                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
//              .apis(RequestHandlerSelectors.basePackage("com.jk.modules.api"))//过滤的接口
                .apis(predicate)
                .build();
    }

    /**
     * API文档主信息对象
     * @return
     */
    private ApiInfo apiInfo(){
        ApiInfo apiInfo= (new ApiInfoBuilder())
                .title("ivplay-manager相关接口文档V1")  ////大标题
                .description("v1.0接口测试") ////详细描述
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("jacky", "", "zhangyu03121011@163.com"))//作者
                .version("1.0") //版本
                .build();
        return apiInfo;
    }
}
