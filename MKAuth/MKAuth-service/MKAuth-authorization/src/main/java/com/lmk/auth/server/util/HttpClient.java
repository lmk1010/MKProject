package com.lmk.auth.server.util;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName HttpClient
 * @Description TODO
 * @Author liumingkang
 * @Date 2019-04-18 23:03
 * @Version 1.0
 **/
public class HttpClient {

    public static Map<String,Object> sendPostRequest(String url, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<Map> response = client.exchange(url, method, requestEntity, Map.class);

        return response.getBody();
    }

}
