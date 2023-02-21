package cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 租户参数配置 Excel VO
 *
 * @author 六楼的雨
 */
@Data
public class TenantConfigExcelVO {

    @ExcelProperty("参数主键")
    private Integer id;

    @ExcelProperty("参数分组")
    private String category;

    @ExcelProperty(value = "参数类型", converter = DictConvert.class)
    @DictFormat("infra_config_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer type;

    @ExcelProperty("参数名称")
    private String name;

    @ExcelProperty("参数键名")
    private String configKey;

    @ExcelProperty("参数键值")
    private String value;

    @ExcelProperty("是否可见")
    private Boolean visible;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
