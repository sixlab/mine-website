package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 金价监控 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoldPriceRespVO extends GoldPriceBaseVO {

    @Schema(description = "编号", required = true, example = "10884")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
