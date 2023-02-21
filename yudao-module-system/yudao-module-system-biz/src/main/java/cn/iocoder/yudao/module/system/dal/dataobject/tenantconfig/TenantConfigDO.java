package cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 租户参数配置 DO
 *
 * @author 六楼的雨
 */
@TableName("system_tenant_config")
@KeySequence("system_tenant_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantConfigDO extends BaseDO {

    /**
     * 参数主键
     */
    @TableId
    private Integer id;
    /**
     * 参数分组
     */
    private String category;
    /**
     * 参数类型
     *
     * 枚举 {@link TODO infra_config_type 对应的类}
     */
    private Integer type;
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String value;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 备注
     */
    private String remark;

}
