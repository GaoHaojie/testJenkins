package com.test.shiro.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author gaohaojie
 * @Date 2019/7/12
 * @Des:
 */
@Controller
public class ShiroController {

    @RequestMapping("/loginPage")
    public String loginPage(){
        //shiro那些认证操作
//        Subject subject = SecurityUtils.getSubject();
        return "/login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String logi(@Param("account") String account,@Param("password") String password, Model model){
        //shiro白那些认证操作
        Subject subject = SecurityUtils.getSubject();
        System.out.println(account+"pw:"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(account,password);
        try{
            subject.login(token);
            //登录成功
            return "/home";
        }catch (UnknownAccountException e){
            //登录失败：用户名不存在
            e.printStackTrace();
            model.addAttribute("msg","账号不存在");
            return "/login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "/login";
        }

    }
    @RequestMapping("/home")
    public String home(){
        return "/home";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(){
        return "/update";
    }
    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        return "/add";
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }
}
