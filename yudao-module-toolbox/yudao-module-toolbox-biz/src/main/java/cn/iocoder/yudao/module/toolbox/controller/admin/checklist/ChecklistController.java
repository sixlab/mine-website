package cn.iocoder.yudao.module.toolbox.controller.admin.checklist;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import cn.iocoder.yudao.module.toolbox.convert.checklist.ChecklistConvert;
import cn.iocoder.yudao.module.toolbox.service.checklist.ChecklistService;

@Tag(name = "管理后台 - 任务清单")
@RestController
@RequestMapping("/toolbox/checklist")
@Validated
public class ChecklistController {

    @Resource
    private ChecklistService checklistService;

    @PostMapping("/create")
    @Operation(summary = "创建任务清单")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:create')")
    public CommonResult<Long> createChecklist(@Valid @RequestBody ChecklistCreateReqVO createReqVO) {
        return success(checklistService.createChecklist(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务清单")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:update')")
    public CommonResult<Boolean> updateChecklist(@Valid @RequestBody ChecklistUpdateReqVO updateReqVO) {
        checklistService.updateChecklist(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务清单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:delete')")
    public CommonResult<Boolean> deleteChecklist(@RequestParam("id") Long id) {
        checklistService.deleteChecklist(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务清单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:query')")
    public CommonResult<ChecklistRespVO> getChecklist(@RequestParam("id") Long id) {
        ChecklistDO checklist = checklistService.getChecklist(id);
        return success(ChecklistConvert.INSTANCE.convert(checklist));
    }

    @GetMapping("/list")
    @Operation(summary = "获得任务清单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:query')")
    public CommonResult<List<ChecklistRespVO>> getChecklistList(@RequestParam("ids") Collection<Long> ids) {
        List<ChecklistDO> list = checklistService.getChecklistList(ids);
        return success(ChecklistConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务清单分页")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:query')")
    public CommonResult<PageResult<ChecklistRespVO>> getChecklistPage(@Valid ChecklistPageReqVO pageVO) {
        PageResult<ChecklistDO> pageResult = checklistService.getChecklistPage(pageVO);
        return success(ChecklistConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务清单 Excel")
    @PreAuthorize("@ss.hasPermission('toolbox:checklist:export')")
    @OperateLog(type = EXPORT)
    public void exportChecklistExcel(@Valid ChecklistExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ChecklistDO> list = checklistService.getChecklistList(exportReqVO);
        // 导出 Excel
        List<ChecklistExcelVO> datas = ChecklistConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "任务清单.xls", "数据", ChecklistExcelVO.class, datas);
    }

}
