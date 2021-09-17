package cn.lloml.destinyrecruit.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class WebClientUtil {

    private  final MediaType MEDIATYPE_JSON = MediaType.APPLICATION_JSON;

    private  final MediaType MEDIATYPE_FORM = MediaType.APPLICATION_FORM_URLENCODED;

    @Resource
    private WebClient webClient;

    /**
     *
     * @param parameter
     *            请求参数
     * @param url
     *            请求路径
     * @param resultType
     *            返回结果类型
     * @return
     */
    public  <T> T post(Object parameter, String url, Class<T> resultType) {

        return post(spec(url, HttpMethod.POST), parameter, resultType);
    }

    /**
     *
     * @param parameter
     *            请求参数
     * @param url
     *            请求路径
     * @param header
     *            请求头
     * @param resultType
     *            返回结果类型
     * @return
     */
    public  <T> T post(Object parameter, String url, Map<String, String> header, Class<T> resultType) {

        RequestBodySpec uri = spec(url, HttpMethod.POST);
        addHeader(header, uri);
        return post(uri, parameter, resultType);
    }

    private  <T> T post(WebClient.RequestBodySpec uri, Object paramter, Class<T> resultType) {

        return uri.contentType(MEDIATYPE_JSON).body(Mono.just(paramter), Object.class).retrieve().bodyToMono(resultType)
                .block();
    }

    /**
     *
     * @param parameter
     *            请求参数
     * @param url
     *            请求路径
     * @param header
     *            请求头
     * @param resultType
     *            返回结果类型
     * @return
     */
    public  <T> T postForm(Map<String,String> parameter, String url, Map<String, String> header, Class<T> resultType) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.setAll(parameter);
        RequestBodySpec uri = spec(url, HttpMethod.POST);
        addHeader(header, uri);
        return postForm(uri, formData, resultType);
    }

    private  <T> T postForm(RequestBodySpec uri, MultiValueMap<String, String> formData , Class<T> resultType) {

        return uri.contentType(MEDIATYPE_FORM).body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(resultType)
                .block();
    }

    /**
     *
     * @param url
     *            请求路径
     * @param resultType
     *            返回结果类型
     * @return
     */
    public  <T> T get(String url, Class<T> resultType) {

        return spec(url, HttpMethod.GET).retrieve().bodyToMono(resultType).block();
    }

    /**
     *
     * @param url
     *            请求路径
     * @param header
     *            请求头
     * @param resultType
     *            返回结果类型
     * @return
     */
    public  <T> ResponseEntity<T> get(String url, Map<String, String> header, Class<T> resultType) {

        RequestBodySpec requestBodySpec = spec(url, HttpMethod.GET);
        addHeader(header, requestBodySpec);
        return requestBodySpec.retrieve().toEntity(resultType).block();
    }

    private  RequestBodySpec spec(String url, HttpMethod method) {

        return webClient.method(method).uri(url);
    }

    private  void addHeader(Map<String, String> header, RequestBodySpec requestBodySpec) {
        if (!CollectionUtils.isEmpty(header)) {
            header.forEach(requestBodySpec::header);
        }
    }

}
