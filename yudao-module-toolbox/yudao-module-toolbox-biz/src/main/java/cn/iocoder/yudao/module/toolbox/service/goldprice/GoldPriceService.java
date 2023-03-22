package cn.iocoder.yudao.module.toolbox.service.goldprice;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 金价监控 Service 接口
 *
 * @author 六楼的雨
 */
public interface GoldPriceService {

    /**
     * 创建金价监控
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGoldPrice(@Valid GoldPriceCreateReqVO createReqVO);

    /**
     * 更新金价监控
     *
     * @param updateReqVO 更新信息
     */
    void updateGoldPrice(@Valid GoldPriceUpdateReqVO updateReqVO);

    /**
     * 删除金价监控
     *
     * @param id 编号
     */
    void deleteGoldPrice(Long id);

    /**
     * 获得金价监控
     *
     * @param id 编号
     * @return 金价监控
     */
    GoldPriceDO getGoldPrice(Long id);

    /**
     * 获得金价监控列表
     *
     * @param ids 编号
     * @return 金价监控列表
     */
    List<GoldPriceDO> getGoldPriceList(Collection<Long> ids);

    /**
     * 获得金价监控分页
     *
     * @param pageReqVO 分页查询
     * @return 金价监控分页
     */
    PageResult<GoldPriceDO> getGoldPricePage(GoldPricePageReqVO pageReqVO);

    /**
     * 获得金价监控列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 金价监控列表
     */
    List<GoldPriceDO> getGoldPriceList(GoldPriceExportReqVO exportReqVO);

}
