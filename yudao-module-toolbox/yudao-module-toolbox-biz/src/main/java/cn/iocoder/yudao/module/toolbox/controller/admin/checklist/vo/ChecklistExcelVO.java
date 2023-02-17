package cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 任务清单 Excel VO
 *
 * @author 六楼的雨
 */
@Data
public class ChecklistExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("编号")
    private String checklistCode;

    @ExcelProperty("顺序")
    private Integer checklistIndex;

    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("toolbox-checklist-type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer checklistType;

    @ExcelProperty("cron 表达式")
    private String checklistCron;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
