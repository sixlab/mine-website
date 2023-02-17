package cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务清单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChecklistRespVO extends ChecklistBaseVO {

    @Schema(description = "编号", required = true, example = "23182")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
