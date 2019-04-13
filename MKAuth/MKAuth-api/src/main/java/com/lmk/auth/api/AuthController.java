package com.lmk.auth.api;

import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author liumingkang
 * @Date 2019-04-12 22:56
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/auth/")
public class AuthController {


    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String toRegister(){
        return "success";
    }

    @RequestMapping(value = "access",method = RequestMethod.GET)
    public Principal toAuth(Principal principal){
        return principal;
    }







}
