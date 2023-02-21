package cn.iocoder.yudao.framework.dingtalk.core.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.dingtalk.config.DingtalkProperties;
import cn.iocoder.yudao.framework.dingtalk.core.dal.redis.DingtalkKeyConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class DingtalkFrameworkServiceImpl implements DingtalkFrameworkService {
    
    @Resource(name ="stringRedisTemplate" )
    private StringRedisTemplate redisTemplate;
    
    @Resource
    private DingtalkProperties dingtalkProperties;
    
    public String accessToken() {
        String token = redisTemplate.opsForValue().get(DingtalkKeyConstants.DINGTALK_TOKEN_KEY);
        
        if (ObjectUtil.isEmpty(token)) {
            token = requestAccessToken();
            if (ObjectUtil.isNotEmpty(token)) {
                redisTemplate.opsForValue().set(DingtalkKeyConstants.DINGTALK_TOKEN_KEY, token);
            } else {
                return "";
            }
        }
        return token.toString();
    }
    
    public String requestAccessToken() {
        Dict param = Dict.of("appKey", dingtalkProperties.getAppKey(), "appSecret", dingtalkProperties.getAppSecret());
        
        String resp = HttpUtil.post("https://api.dingtalk.com/v1.0/oauth2/accessToken", JSONUtil.toJsonStr(param));
        
        log.info("请求 Token 返回：" + resp);
        
        Map json = JSONUtil.toBean(resp, Map.class);
        
        return MapUtil.getStr(json, "accessToken");
    }
    
    public void sendText(String userId, String text) {
        String accessToken = accessToken();
        if (StrUtil.isNotEmpty(accessToken)) {
            Map<String, Object> param = new HashMap<>();
            param.put("robotCode", dingtalkProperties.getAppKey());
            param.put("userIds", new String[]{userId});
            param.put("msgKey", "sampleText");
            param.put("msgParam", JSONUtil.toJsonStr(Dict.of("content", text)));
            
            String resp = HttpUtil.createPost("https://api.dingtalk.com/v1.0/robot/oToMessages/batchSend")
                    .header("x-acs-dingtalk-access-token", accessToken)
                    .body(JSONUtil.toJsonStr(param)).execute().body();
            
            log.info("请求返回：" + resp);
        }
    }
}
