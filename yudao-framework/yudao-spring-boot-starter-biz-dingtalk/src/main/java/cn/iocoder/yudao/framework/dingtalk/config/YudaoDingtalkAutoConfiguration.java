package cn.iocoder.yudao.framework.dingtalk.config;

import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkService;
import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(DingtalkProperties.class)
public class YudaoDingtalkAutoConfiguration {

    @Bean
    public DingtalkFrameworkService dingtalkFrameworkService() {
        return new DingtalkFrameworkServiceImpl();
    }

}
