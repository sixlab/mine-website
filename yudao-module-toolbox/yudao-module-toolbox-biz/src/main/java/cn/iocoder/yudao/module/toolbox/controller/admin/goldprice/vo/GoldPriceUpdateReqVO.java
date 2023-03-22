package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 金价监控更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoldPriceUpdateReqVO extends GoldPriceBaseVO {

    @Schema(description = "编号", required = true, example = "10884")
    @NotNull(message = "编号不能为空")
    private Long id;

}
