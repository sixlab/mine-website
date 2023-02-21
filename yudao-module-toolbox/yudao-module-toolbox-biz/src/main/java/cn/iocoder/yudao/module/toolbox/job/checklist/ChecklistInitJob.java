package cn.iocoder.yudao.module.toolbox.job.checklist;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import cn.iocoder.yudao.module.toolbox.dal.mysql.checklist.ChecklistMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 *
 */
@Component
@TenantJob // 多租户
@Slf4j
public class ChecklistInitJob implements JobHandler {

    @Resource
    private ChecklistMapper checklistMapper;

    @Override
    public String execute(String param) throws Exception {
        Long tenantId = TenantContextHolder.getTenantId();
        System.out.println("当前租户：" + tenantId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        List<ChecklistDO> todoList = checklistMapper.selectList("status", 1);

        int count = 0;
        for (ChecklistDO todo : todoList) {
            if (StrUtil.isNotEmpty(todo.getChecklistCron())) {
                CronExpression cronExpression = CronExpression.parse(todo.getChecklistCron());
                LocalDateTime next = cronExpression.next(now);
                log.info("定时任务：" + todo.getName() + " ,cron:" + todo.getChecklistCron() + " ,下次时间:" + next.format(formatter));
                log.info("定时任务：" + todo.getName() + " ,cron:" + todo.getChecklistCron() + " ,日期间隔:" + Duration.between(now, next).toDays());
                if (Duration.between(now, next).toDays() == 0) {
                    todo.setStatus(0);
                    checklistMapper.updateById(todo);
                    count++;
                }
            }
        }

        return "租户：" + tenantId + " 更新任务数量：" + count;
    }

}
