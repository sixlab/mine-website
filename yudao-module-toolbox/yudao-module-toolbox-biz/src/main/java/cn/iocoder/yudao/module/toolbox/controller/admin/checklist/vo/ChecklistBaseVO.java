package cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 任务清单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ChecklistBaseVO {

    @Schema(description = "名称", required = true, example = "芋艿")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private String checklistCode;

    @Schema(description = "顺序", required = true)
    @NotNull(message = "顺序不能为空")
    private Integer checklistIndex;

    @Schema(description = "类型", required = true, example = "2")
    @NotNull(message = "类型不能为空")
    private Integer checklistType;

    @Schema(description = "cron 表达式", required = true)
    @NotNull(message = "cron 表达式不能为空")
    private String checklistCron;

    @Schema(description = "状态", required = true, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "备注", example = "随便")
    private String remark;

}
