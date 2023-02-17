package cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 任务清单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChecklistUpdateReqVO extends ChecklistBaseVO {

    @Schema(description = "编号", required = true, example = "23182")
    @NotNull(message = "编号不能为空")
    private Long id;

}
