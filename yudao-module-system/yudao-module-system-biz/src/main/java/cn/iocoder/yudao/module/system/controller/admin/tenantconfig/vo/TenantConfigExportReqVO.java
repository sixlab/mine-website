package cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 租户参数配置 Excel 导出 Request VO，参数和 TenantConfigPageReqVO 是一致的")
@Data
public class TenantConfigExportReqVO {

    @Schema(description = "参数分组")
    private String category;

    @Schema(description = "参数类型", example = "1")
    private Integer type;

    @Schema(description = "参数名称", example = "芋艿")
    private String name;

    @Schema(description = "参数键名")
    private String configKey;

    @Schema(description = "参数键值")
    private String value;

    @Schema(description = "是否可见")
    private Boolean visible;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
