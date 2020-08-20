package com.example.demo.annotation;

import com.example.demo.config.DefineAnnoationConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DefineAnnoationConfig.class})
public @interface DefineAnnoation {
    String value() default  "" ;
}
