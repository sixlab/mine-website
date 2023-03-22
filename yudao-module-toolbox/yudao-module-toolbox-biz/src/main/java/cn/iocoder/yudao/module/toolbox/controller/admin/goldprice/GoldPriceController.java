package cn.iocoder.yudao.module.toolbox.controller.admin.goldprice;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.*;
import cn.iocoder.yudao.module.toolbox.convert.goldprice.GoldPriceConvert;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import cn.iocoder.yudao.module.toolbox.service.goldprice.GoldPriceService;
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

@Tag(name = "管理后台 - 金价监控")
@RestController
@RequestMapping("/toolbox/gold-price")
@Validated
public class GoldPriceController {

    @Resource
    private GoldPriceService goldPriceService;

    @PostMapping("/create")
    @Operation(summary = "创建金价监控")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:create')")
    public CommonResult<Long> createGoldPrice(@Valid @RequestBody GoldPriceCreateReqVO createReqVO) {
        return success(goldPriceService.createGoldPrice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新金价监控")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:update')")
    public CommonResult<Boolean> updateGoldPrice(@Valid @RequestBody GoldPriceUpdateReqVO updateReqVO) {
        goldPriceService.updateGoldPrice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除金价监控")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:delete')")
    public CommonResult<Boolean> deleteGoldPrice(@RequestParam("id") Long id) {
        goldPriceService.deleteGoldPrice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得金价监控")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:query')")
    public CommonResult<GoldPriceRespVO> getGoldPrice(@RequestParam("id") Long id) {
        GoldPriceDO goldPrice = goldPriceService.getGoldPrice(id);
        return success(GoldPriceConvert.INSTANCE.convert(goldPrice));
    }

    @GetMapping("/list")
    @Operation(summary = "获得金价监控列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:query')")
    public CommonResult<List<GoldPriceRespVO>> getGoldPriceList(@RequestParam("ids") Collection<Long> ids) {
        List<GoldPriceDO> list = goldPriceService.getGoldPriceList(ids);
        return success(GoldPriceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得金价监控分页")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:query')")
    public CommonResult<PageResult<GoldPriceRespVO>> getGoldPricePage(@Valid GoldPricePageReqVO pageVO) {
        PageResult<GoldPriceDO> pageResult = goldPriceService.getGoldPricePage(pageVO);
        return success(GoldPriceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出金价监控 Excel")
    @PreAuthorize("@ss.hasPermission('toolbox:gold-price:export')")
    @OperateLog(type = EXPORT)
    public void exportGoldPriceExcel(@Valid GoldPriceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GoldPriceDO> list = goldPriceService.getGoldPriceList(exportReqVO);
        // 导出 Excel
        List<GoldPriceExcelVO> datas = GoldPriceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "金价监控.xls", "数据", GoldPriceExcelVO.class, datas);
    }

}
