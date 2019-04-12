package com.lmk.ai.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author liumingkang
 * @Date 2019-04-12 21:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/")
public class AuthController {

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String toTest(){
        return "test";
    }

}
