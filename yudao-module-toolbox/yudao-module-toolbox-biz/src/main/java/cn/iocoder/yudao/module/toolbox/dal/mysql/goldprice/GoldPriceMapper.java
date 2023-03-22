package cn.iocoder.yudao.module.toolbox.dal.mysql.goldprice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPriceExportReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPricePageReqVO;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 金价监控 Mapper
 *
 * @author 六楼的雨
 */
@Mapper
public interface GoldPriceMapper extends BaseMapperX<GoldPriceDO> {

    default PageResult<GoldPriceDO> selectPage(GoldPricePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GoldPriceDO>()
                .eqIfPresent(GoldPriceDO::getGoldPrice, reqVO.getGoldPrice())
                .eqIfPresent(GoldPriceDO::getRisePrice, reqVO.getRisePrice())
                .eqIfPresent(GoldPriceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GoldPriceDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GoldPriceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GoldPriceDO::getId));
    }

    default List<GoldPriceDO> selectList(GoldPriceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GoldPriceDO>()
                .eqIfPresent(GoldPriceDO::getGoldPrice, reqVO.getGoldPrice())
                .eqIfPresent(GoldPriceDO::getRisePrice, reqVO.getRisePrice())
                .eqIfPresent(GoldPriceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GoldPriceDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GoldPriceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GoldPriceDO::getId));
    }

}
