package cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 租户参数配置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TenantConfigUpdateReqVO extends TenantConfigBaseVO {

    @Schema(description = "参数主键", required = true, example = "27486")
    @NotNull(message = "参数主键不能为空")
    private Long id;

}
