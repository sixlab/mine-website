package cn.iocoder.yudao.framework.mq.message;

import cn.iocoder.yudao.framework.mq.core.stream.AbstractStreamMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChecklistMessage extends AbstractStreamMessage {
    
    private String dingUserId;
    
    private String content;
    
    @Override
    public String getStreamKey() {
        return "toolbox.checklist.dingtalk.callback";
    }
}
