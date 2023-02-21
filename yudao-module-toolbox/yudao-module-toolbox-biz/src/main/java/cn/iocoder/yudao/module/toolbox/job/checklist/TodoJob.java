package cn.iocoder.yudao.module.toolbox.job.checklist;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.toolbox.dal.mysql.checklist.ChecklistMapper;
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
public class TodoJob implements JobHandler {
    
    @Resource
    private ChecklistMapper checklistMapper;
    
    @Override
    public String execute(String param) throws Exception {
        Long tenantId = TenantContextHolder.getTenantId();
        System.out.println("当前租户：" + tenantId);
        
        // ----
        return "租户：" + tenantId + " 更新任务数量：";
    }
    
}
