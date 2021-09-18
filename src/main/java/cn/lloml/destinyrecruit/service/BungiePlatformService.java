package cn.lloml.destinyrecruit.service;

import cn.lloml.destinyrecruit.util.RestTemplateUtil;
import cn.lloml.destinyrecruit.util.WebClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;
import java.util.*;

/**
 * 棒鸡平台服务
 */
@Repository
public class BungiePlatformService {
    @Resource
    WebClientUtil webClientUtil;

    final String BungiePlatformHost = "www.bungie.net";

    final String BungiePlatformPath = "/Platform";

    final int membershipType = 3;

    /**
     * @param pathList 路径列表
     * @return 返回uri字符串
     */
    private String getURIString(List<String> pathList) {
        var uriStringBuilder = new StringBuilder();
        uriStringBuilder.append("https://");
        uriStringBuilder.append(BungiePlatformHost);
        uriStringBuilder.append(BungiePlatformPath);
        pathList.forEach(path-> uriStringBuilder.append("/").append(path));
        uriStringBuilder.append("/");

        return uriStringBuilder.toString();
    }

    private Map<String, String> getDefaultHeaders() {
        var headers = new HashMap<String, String>();
        headers.put("X-API-Key", "6b5703eeebce45c8911a1ec357abec52");
        return headers;
    }

    public Long searchUser(String bungieName) {
        var pathList = new ArrayList<String>();

        pathList.add("Destiny2");
        pathList.add("SearchDestinyPlayer");
        pathList.add(String.valueOf(membershipType));
        pathList.add(bungieName);

        System.out.println(getURIString(pathList));
        System.out.println(bungieName);
        System.out.println(UriEncoder.encode(bungieName));
        try {
            var res = webClientUtil.get(
                    getURIString(pathList),
                    getDefaultHeaders(),
                    String.class
            );
            System.out.println("test");
            System.out.println(res);
            var resObject = new JSONObject(Boolean.parseBoolean(res.getBody()));
            var responseArray = resObject.getJSONArray("Response");
            if (responseArray.toArray().length == 0) {
                return null;
            } else {
                return responseArray.getJSONObject(0).getLong("membershipId");
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

}
