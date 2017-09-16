package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by hdy on 2017/9/16.
 */
@SpringBootApplication
//public class ApplicationStartClass {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ApplicationStartClass.class, args);
//    }
//}
public class ApplicationStartClass extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartClass.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationStartClass.class);
    }
}
