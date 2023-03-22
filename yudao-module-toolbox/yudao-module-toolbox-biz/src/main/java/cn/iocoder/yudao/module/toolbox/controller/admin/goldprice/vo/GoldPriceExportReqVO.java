package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 金价监控 Excel 导出 Request VO，参数和 GoldPricePageReqVO 是一致的")
@Data
public class GoldPriceExportReqVO {

    @Schema(description = "金价", example = "10313")
    private BigDecimal goldPrice;

    @Schema(description = "涨价幅度", example = "31383")
    private BigDecimal risePrice;

    @Schema(description = "状态", example = "2")
    private Byte status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
