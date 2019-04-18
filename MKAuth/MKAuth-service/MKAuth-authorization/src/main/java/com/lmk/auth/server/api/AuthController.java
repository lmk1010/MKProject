package com.lmk.auth.server.api;

import com.lmk.auth.server.util.HttpClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName AuthController
 * @Description TODO this is getAccessToken
 * @Author liumingkang
 * @Date 2019-04-18 22:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/auth/")
public class AuthController {


    @RequestMapping(value = "getAccessToken",method = RequestMethod.GET)
    public Map<String,Object> toGetAccessToken(String code){

        String tokenUrl = "http://localhost:8881/oauth/token";
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String, String>();

        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8881/auth/getAccessToken");
        params.add("client_id", "mkhome");
        params.add("client_secret", "minisec");

        Map<String, Object> res = HttpClient.sendPostRequest(tokenUrl, params);

        return res;
    }



}
