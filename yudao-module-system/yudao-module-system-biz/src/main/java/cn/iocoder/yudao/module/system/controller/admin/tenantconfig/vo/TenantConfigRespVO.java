package cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 租户参数配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TenantConfigRespVO extends TenantConfigBaseVO {

    @Schema(description = "参数主键", required = true, example = "27486")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
