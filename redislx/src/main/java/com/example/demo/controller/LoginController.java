package com.example.demo.controller;

import com.example.demo.dto.NanChangResult;
import com.example.demo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags = "用户登录相关接口")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public String login() {
      return "login" ;
    }

    @GetMapping("/loginIn")
    public String loginIn() {
        return "login_success" ;
    }

    @PostMapping ("/login")
    @ApiOperation("校验登录接口")
    @ResponseBody
    public String loginIn(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request ,Model model) {
        Subject subject = SecurityUtils.getSubject();
      UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            model.addAttribute("msg","欢迎");
            return "/index";
        }
        catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "/login";
        }
        catch (IncorrectCredentialsException e){ //密码错误
            model.addAttribute("msg","密码错误");
            return  "/login";
        }

    }

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","HelloShiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }


}
