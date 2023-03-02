package cn.iocoder.yudao.module.toolbox.enums.checklist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChecklistTypeEnum {
    
    /**
     * 一次性任务
     */
    ONCE(0),
    
    /**
     * cron 任务
     */
    CRON(1),
    
    /**
     * 常驻提醒
     */
    TIPS(2);
    
    private final Integer type;
    
}
