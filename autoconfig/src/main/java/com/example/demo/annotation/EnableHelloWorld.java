package com.example.demo.annotation;

import com.example.demo.selector.DefineImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DefineImportSelector.class)
public @interface EnableHelloWorld {
}