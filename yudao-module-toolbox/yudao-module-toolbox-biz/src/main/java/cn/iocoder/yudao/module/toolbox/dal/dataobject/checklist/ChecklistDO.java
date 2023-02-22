package cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 任务清单 DO
 *
 * @author 六楼的雨
 */
@TableName("toolbox_checklist")
@KeySequence("toolbox_checklist_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String checklistCode;
    /**
     * 顺序
     */
    private Integer checklistIndex;
    /**
     * 类型
     *
     * 枚举 {@link TODO toolbox-checklist-type 对应的类}
     */
    private Integer checklistType;
    /**
     * cron 表达式
     */
    private String checklistCron;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
