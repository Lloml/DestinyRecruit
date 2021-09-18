package cn.lloml.destinyrecruit.util;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

@Repository
public class HttpClientUtil {
    @Resource
    HttpClient httpClient;

    public HttpResponse<String> get(String url, Map<String,String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(5009));
        headers.forEach(builder::header);

        HttpRequest request = builder.build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
