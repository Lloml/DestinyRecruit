package cn.lloml.destinyrecruit.service;

import cn.lloml.destinyrecruit.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 棒鸡平台服务
 */
@Service
public class BungiePlatformService {
    @Resource
    HttpClientUtil httpClientUtil;

    final String BungiePlatformHost = "www.bungie.net";

    final String BungiePlatformPath = "/Platform";

    final int membershipType = 3;

    final String X_API_Key = "6b5703eeebce45c8911a1ec357abec52";

    /**
     * @param pathList 路径列表
     * @return 返回uri字符串
     */
    private String getURIString(List<String> pathList) {
        var uriStringBuilder = new StringBuilder();
        uriStringBuilder.append("https://");
        uriStringBuilder.append(BungiePlatformHost);
        uriStringBuilder.append(BungiePlatformPath);
        pathList.forEach(path -> uriStringBuilder.append("/").append(path));
        uriStringBuilder.append("/");

        return uriStringBuilder.toString();
    }

    private Map<String, String> getDefaultHeaders() {
        var headers = new HashMap<String, String>();
        headers.put("X-API-Key", X_API_Key);
        return headers;
    }

    public Long searchUser(String bungieName) {
        var pathList = new ArrayList<String>();

        pathList.add("Destiny2");
        pathList.add("SearchDestinyPlayer");
        pathList.add(String.valueOf(membershipType));
        pathList.add(UriEncoder.encode(bungieName));
        var res = httpClientUtil.get(
                getURIString(pathList),
                getDefaultHeaders()
        );
        if (res == null) {
            return null;
        }
        var responseArray = res.getJSONArray("Response");
        if (responseArray.toArray().length == 0) {
            return null;
        } else {
            return responseArray.getJSONObject(0).getLong("membershipId");
        }
    }

}
