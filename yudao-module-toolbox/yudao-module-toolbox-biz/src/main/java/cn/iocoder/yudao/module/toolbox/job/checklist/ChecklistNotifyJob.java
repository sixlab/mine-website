package cn.iocoder.yudao.module.toolbox.job.checklist;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.dingtalk.core.dal.redis.DingtalkKeyConstants;
import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkService;
import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import cn.iocoder.yudao.module.toolbox.service.checklist.ChecklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 *
 */
@Component
@TenantJob // 多租户
@Slf4j
public class ChecklistNotifyJob implements JobHandler {
    
    @Resource
    private ChecklistService checklistService;
    
    @Resource
    private DingtalkFrameworkService dingtalkFrameworkService;
    
    @Resource
    private TenantApi tenantApi;
    
    @Override
    public String execute(String param) throws Exception {
        Long tenantId = TenantContextHolder.getTenantId();
        System.out.println("当前租户：" + tenantId);
    
        String dingTalkId = tenantApi.getTenantConfig(DingtalkKeyConstants.DINGTALK_GROUP_ID);

        if(StrUtil.isNotEmpty(dingTalkId)){
            String message = checklistService.checkListText();
    
            dingtalkFrameworkService.sendText(dingTalkId, message);
    
            return "租户：" + tenantId + " 已通知 checkList ";
        }else{
            return "租户：" + tenantId + " 未设置消息组 ";
        }
    }
    
}
