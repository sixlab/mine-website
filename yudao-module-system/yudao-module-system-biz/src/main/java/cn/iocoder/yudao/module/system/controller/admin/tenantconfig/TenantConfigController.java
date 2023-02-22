package cn.iocoder.yudao.module.system.controller.admin.tenantconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;
import cn.iocoder.yudao.module.system.convert.tenantconfig.TenantConfigConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import cn.iocoder.yudao.module.system.service.tenantconfig.TenantConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 租户参数配置")
@RestController
@RequestMapping("/system/tenant-config")
@Validated
public class TenantConfigController {

    @Resource
    private TenantConfigService tenantConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建租户参数配置")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:create')")
    public CommonResult<Long> createTenantConfig(@Valid @RequestBody TenantConfigCreateReqVO createReqVO) {
        return success(tenantConfigService.createTenantConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户参数配置")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:update')")
    public CommonResult<Boolean> updateTenantConfig(@Valid @RequestBody TenantConfigUpdateReqVO updateReqVO) {
        tenantConfigService.updateTenantConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除租户参数配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:tenant-config:delete')")
    public CommonResult<Boolean> deleteTenantConfig(@RequestParam("id") Long id) {
        tenantConfigService.deleteTenantConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得租户参数配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:query')")
    public CommonResult<TenantConfigRespVO> getTenantConfig(@RequestParam("id") Long id) {
        TenantConfigDO tenantConfig = tenantConfigService.getTenantConfig(id);
        return success(TenantConfigConvert.INSTANCE.convert(tenantConfig));
    }

    @GetMapping("/list")
    @Operation(summary = "获得租户参数配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:query')")
    public CommonResult<List<TenantConfigRespVO>> getTenantConfigList(@RequestParam("ids") Collection<Long> ids) {
        List<TenantConfigDO> list = tenantConfigService.getTenantConfigList(ids);
        return success(TenantConfigConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得租户参数配置分页")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:query')")
    public CommonResult<PageResult<TenantConfigRespVO>> getTenantConfigPage(@Valid TenantConfigPageReqVO pageVO) {
        PageResult<TenantConfigDO> pageResult = tenantConfigService.getTenantConfigPage(pageVO);
        return success(TenantConfigConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出租户参数配置 Excel")
    @PreAuthorize("@ss.hasPermission('system:tenant-config:export')")
    @OperateLog(type = EXPORT)
    public void exportTenantConfigExcel(@Valid TenantConfigExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TenantConfigDO> list = tenantConfigService.getTenantConfigList(exportReqVO);
        // 导出 Excel
        List<TenantConfigExcelVO> datas = TenantConfigConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "租户参数配置.xls", "数据", TenantConfigExcelVO.class, datas);
    }

}
