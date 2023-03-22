package cn.iocoder.yudao.module.toolbox.service.goldprice;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.toolbox.convert.goldprice.GoldPriceConvert;
import cn.iocoder.yudao.module.toolbox.dal.mysql.goldprice.GoldPriceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.toolbox.enums.ErrorCodeConstants.*;

/**
 * 金价监控 Service 实现类
 *
 * @author 六楼的雨
 */
@Service
@Validated
public class GoldPriceServiceImpl implements GoldPriceService {

    @Resource
    private GoldPriceMapper goldPriceMapper;

    @Override
    public Long createGoldPrice(GoldPriceCreateReqVO createReqVO) {
        // 插入
        GoldPriceDO goldPrice = GoldPriceConvert.INSTANCE.convert(createReqVO);
        goldPriceMapper.insert(goldPrice);
        // 返回
        return goldPrice.getId();
    }

    @Override
    public void updateGoldPrice(GoldPriceUpdateReqVO updateReqVO) {
        // 校验存在
        validateGoldPriceExists(updateReqVO.getId());
        // 更新
        GoldPriceDO updateObj = GoldPriceConvert.INSTANCE.convert(updateReqVO);
        goldPriceMapper.updateById(updateObj);
    }

    @Override
    public void deleteGoldPrice(Long id) {
        // 校验存在
        validateGoldPriceExists(id);
        // 删除
        goldPriceMapper.deleteById(id);
    }

    private void validateGoldPriceExists(Long id) {
        if (goldPriceMapper.selectById(id) == null) {
            throw exception(GOLD_PRICE_NOT_EXISTS);
        }
    }

    @Override
    public GoldPriceDO getGoldPrice(Long id) {
        return goldPriceMapper.selectById(id);
    }

    @Override
    public List<GoldPriceDO> getGoldPriceList(Collection<Long> ids) {
        return goldPriceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GoldPriceDO> getGoldPricePage(GoldPricePageReqVO pageReqVO) {
        return goldPriceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GoldPriceDO> getGoldPriceList(GoldPriceExportReqVO exportReqVO) {
        return goldPriceMapper.selectList(exportReqVO);
    }

}
