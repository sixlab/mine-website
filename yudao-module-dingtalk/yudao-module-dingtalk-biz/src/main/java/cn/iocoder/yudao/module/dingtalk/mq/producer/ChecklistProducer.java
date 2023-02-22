package cn.iocoder.yudao.module.dingtalk.mq.producer;

import cn.iocoder.yudao.framework.mq.core.RedisMQTemplate;
import cn.iocoder.yudao.framework.mq.message.ChecklistMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ChecklistProducer {
    
    @Resource
    private RedisMQTemplate redisMQTemplate;
    
    public void textCallback(String dingUserId, String content){
        ChecklistMessage message = new ChecklistMessage();
        message.setDingUserId(dingUserId).setContent(content);
        redisMQTemplate.send(message);
    }
}
