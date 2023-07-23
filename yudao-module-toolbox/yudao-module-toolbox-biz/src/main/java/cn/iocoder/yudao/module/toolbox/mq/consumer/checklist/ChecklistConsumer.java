package cn.iocoder.yudao.module.toolbox.mq.consumer.checklist;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkService;
import cn.iocoder.yudao.framework.mq.core.stream.AbstractStreamMessageListener;
import cn.iocoder.yudao.framework.mq.message.ChecklistMessage;
import cn.iocoder.yudao.module.toolbox.enums.checklist.ChecklistTypeEnum;
import cn.iocoder.yudao.module.toolbox.service.checklist.ChecklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ChecklistConsumer extends AbstractStreamMessageListener<ChecklistMessage> {
    
    @Resource
    private ChecklistService checklistService;
    
    @Resource
    private DingtalkFrameworkService dingtalkFrameworkService;
    
    @Override
    public void onMessage(ChecklistMessage message) {
        String dingUserId = message.getDingUserId();
        String content = message.getContent();
        // Long tenantId = TenantContextHolder.getTenantId();
        
        // 以下第一部分是完全匹配内容的
        if (StrUtil.equalsAny(content, "h", "help")) {
            checklistService.help(dingUserId);
        } else if (StrUtil.equalsAny(content, "l", "list")) {
            checklistService.listTodo(dingUserId);
        } else if (StrUtil.equalsAny(content, "ll")) {
            checklistService.listTask(dingUserId);
            // 以下是内容为数字的
        } else if (StrUtil.equalsAny(content, "i", "info")) {
            String[] params = StrUtil.splitToArray(content, " ");
            checklistService.taskInfo(dingUserId, params);
            // 以下是内容为数字的
        } else if (NumberUtil.isNumber(content)) {
            Integer indexNo = Integer.valueOf(content);
            checklistService.status(dingUserId, indexNo);
            
            checklistService.listTask(dingUserId);
            // 以下是以具体内容开头的
        } else if (StrUtil.startWithAny(content, "a", "add", "添加")) {
            String[] params = StrUtil.splitToArray(content, "\n");
            if (params.length >= 3) {
                checklistService.addTask(dingUserId, params, ChecklistTypeEnum.CRON.getType());
            } else {
                dingtalkFrameworkService.sendText(dingUserId, "参数无效，长度小于2");
            }
        } else if (StrUtil.startWithAny(content, "o", "once")) {
            String[] params = StrUtil.splitToArray(content, "\n");
            if (params.length >= 2) {
                checklistService.addTask(dingUserId, params, ChecklistTypeEnum.ONCE.getType());
            } else {
                dingtalkFrameworkService.sendText(dingUserId, "参数无效，长度小于2");
            }
        } else if (StrUtil.startWithAny(content, "t", "tips", "提示")) {
            String[] params = StrUtil.splitToArray(content, "\n");
            if (params.length >= 2) {
                checklistService.addTask(dingUserId, params, ChecklistTypeEnum.TIPS.getType());
            } else {
                dingtalkFrameworkService.sendText(dingUserId, "参数无效，长度小于2");
            }
        } else if (StrUtil.startWithAny(content, "b", "batch", "批量")) {
            String[] params = StrUtil.splitToArray(content, " ");
            if (params.length >= 2) {
                for (int i = 1; i < params.length; i++) {
                    if (NumberUtil.isNumber(params[i])) {
                        Integer indexNo = Integer.valueOf(params[i]);
                        checklistService.status(dingUserId, indexNo);
                    }
                }
                
                checklistService.listTask(dingUserId);
            }
        } else if (StrUtil.startWithAny(content, "d", "delete", "删除")) {
            String[] params = StrUtil.splitToArray(content, " ");
            if (params.length >= 2) {
                for (int i = 1; i < params.length; i++) {
                    if (NumberUtil.isNumber(params[i])) {
                        Integer indexNo = Integer.valueOf(params[i]);
                        checklistService.delete(dingUserId, indexNo);
                    }
                }
                
                checklistService.listTask(dingUserId);
            }
        }
    }
    
}
