package cn.iocoder.yudao.module.system.api.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 商户配置 ReqDTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantConfigReqDTO {
    
    /**
     * 参数分组
     */
    @NotNull(message = "参数分组")
    private String category;
    
    /**
     * 参数类型
     */
    // @InEnum(SocialTypeEnum.class)
    @NotNull(message = "参数类型")
    private Integer type;
    
    /**
     * 参数名称
     */
    @NotNull(message = "参数名称")
    private String name;
    
    /**
     * 参数键名
     */
    @NotNull(message = "参数键名")
    private String configKey;
    
    /**
     * 参数键值
     */
    @NotNull(message = "参数键值")
    private String value;
    
    /**
     * 是否可见
     */
    @NotNull(message = "是否可见")
    private Boolean visible;
    
    /**
     * 备注
     */
    @NotNull(message = "备注")
    private String remark;
    
    /**
     * 租户ID
     */
    @NotNull(message = "租户ID")
    private Long tenantId;
    
}
