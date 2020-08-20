package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Properties;

@Controller
@Validated
public class TestController {
    @GetMapping("/test")
    @ResponseBody
    public String test1(@NotBlank(message = "{required}") String name,
                        @Email(message = "{invalid}") String email){
        return  "success";
    }

    @RequestMapping(value = "/test1", consumes = "text/plain")
    public Properties test(Properties properties) {
        return properties;
    }
}
