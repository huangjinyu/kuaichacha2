package com.kcc.conf;

//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//
@Configuration
// 如果启用 @EnableWebMvc，会全面接管系统默认配置，需要自己手动配置
//@EnableWebMvc
//@ConditionalOnProperty()
// WebMvcAutoConfiguration
public class WebConfiguration implements WebMvcConfigurer {
//    @Autowired
//    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8613")
//                .allowedHeaders("abc")
//                .allowedMethods("*")
//                .maxAge(20);


//        registry.addMapping("/**")
////                .allowedOrigins("*")
//                .allowedOrigins("http://localhost:8080", "http://localhost:8613", "http://localhost:8615")
////                .allowedMethods("*")
////                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedMethods(HttpMethod.GET.name(),
//                        HttpMethod.POST.name(),
//                        HttpMethod.PUT.name(),
//                        HttpMethod.PATCH.name(),
//                        HttpMethod.DELETE.name(),
//                        HttpMethod.HEAD.name(),
//                        HttpMethod.OPTIONS.name())
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .exposedHeaders("aaa", "bbb", "ccc", "ddd")
//                .maxAge(220);
    }

    //    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/jsp/", ".jsp");
//        registry.enableContentNegotiation(new MappingJackson2JsonView());
//    }
//
//    @Bean
//    public HttpMessageConverter<String> responseBodyStringConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        return converter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////        converters.add(responseBodyStringConverter());
//
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        converters.add(converter);
//
//        //1.需要定义一个convert转换消息的对象;
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteDateUseDateFormat);
//        //3处理中文乱码问题
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        //       fastMediaTypes.add(MediaType.APPLICATION_JSON);
//        //4.在convert中添加配置信息.
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        //5.将convert添加到converters当中.
//        converters.add(fastJsonHttpMessageConverter);
//    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (Integer i = converters.size() - 1; i >= 0; i--) {
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                converters.remove(converters.get(i));
            }
        }
//
//        System.out.println("aaaaa");
    }


//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        // configurer.defaultContentType(MediaType.APPLICATION_JSON);
//        // 如果请求的是图片
//        // 如果图片是静态资源，是不是不流程
//        // configurer.defaultContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
//    }
}


//@Configuration
//public class MyConfiguration implements WebMvcConfigurer {
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        /* 是否通过请求Url的扩展名来决定media type */
//        configurer.favorPathExtension(true)
//                /* 不检查Accept请求头 */
//                .ignoreAcceptHeader(true)
//                .parameterName("mediaType")
//                /* 设置默认的media yype */
//                .defaultContentType(MediaType.TEXT_HTML)
//                /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
//                .mediaType("html", MediaType.TEXT_HTML)
//                /* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON*/
//                .mediaType("json", MediaType.APPLICATION_JSON);
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/jsp/", ".jsp");
//        registry.enableContentNegotiation(new MappingJackson2JsonView());
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resource/**").addResourceLocations("/WEB-INF/static/");
//    }
//}
