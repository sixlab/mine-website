package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 金价监控 Excel VO
 *
 * @author 六楼的雨
 */
@Data
public class GoldPriceExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("金价")
    private BigDecimal goldPrice;

    @ExcelProperty("涨价幅度")
    private BigDecimal risePrice;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Byte status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
