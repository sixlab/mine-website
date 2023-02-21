package cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 租户参数配置 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class TenantConfigBaseVO {

    @Schema(description = "参数分组", required = true)
    @NotNull(message = "参数分组不能为空")
    private String category;

    @Schema(description = "参数类型", required = true, example = "1")
    @NotNull(message = "参数类型不能为空")
    private Integer type;

    @Schema(description = "参数名称", required = true, example = "芋艿")
    @NotNull(message = "参数名称不能为空")
    private String name;

    @Schema(description = "参数键名", required = true)
    @NotNull(message = "参数键名不能为空")
    private String configKey;

    @Schema(description = "参数键值", required = true)
    @NotNull(message = "参数键值不能为空")
    private String value;

    @Schema(description = "是否可见", required = true)
    @NotNull(message = "是否可见不能为空")
    private Boolean visible;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
