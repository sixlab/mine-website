package cn.iocoder.yudao.framework.dingtalk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉配置
 *
 */
@ConfigurationProperties(prefix = "dingtalk")
@Data
public class DingtalkProperties {

    /**
     * 钉钉API的APP key
     */
    private String appKey;
    
    /**
     * 钉钉API的APP secret
     */
    private String appSecret;
    
}
