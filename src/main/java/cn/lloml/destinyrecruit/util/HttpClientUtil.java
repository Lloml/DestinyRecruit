package cn.lloml.destinyrecruit.util;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * http客户端工具类
 */
@Repository
@Slf4j
public class HttpClientUtil {
    @Resource
    HttpClient httpClient;

    public JSONObject get(String url, Map<String,String> headers){
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url));
        headers.forEach(builder::header);

        HttpRequest request = builder.build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.toString(),this);
            return null;
        }
        if(response.statusCode() >=200 && response.statusCode() <300){
            return JSONObject.parseObject(response.body());
        }else {
            log.error(String.valueOf(response.statusCode()),this);
            log.error(response.body(),this);
            return null;
        }
    }

}
