package cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务清单 Excel 导出 Request VO，参数和 ChecklistPageReqVO 是一致的")
@Data
public class ChecklistExportReqVO {

    @Schema(description = "名称", example = "芋艿")
    private String name;

    @Schema(description = "编号")
    private String checklistCode;

    @Schema(description = "顺序")
    private Integer checklistIndex;

    @Schema(description = "类型", example = "2")
    private Integer checklistType;

    @Schema(description = "cron 表达式")
    private String checklistCron;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
