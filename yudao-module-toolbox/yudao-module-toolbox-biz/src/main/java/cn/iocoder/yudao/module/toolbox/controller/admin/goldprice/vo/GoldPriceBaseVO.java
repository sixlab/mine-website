package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* 金价监控 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class GoldPriceBaseVO {

    @Schema(description = "金价", required = true, example = "10313")
    @NotNull(message = "金价不能为空")
    private BigDecimal goldPrice;

    @Schema(description = "涨价幅度", required = true, example = "31383")
    @NotNull(message = "涨价幅度不能为空")
    private BigDecimal risePrice;

    @Schema(description = "状态", required = true, example = "2")
    @NotNull(message = "状态不能为空")
    private Byte status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
