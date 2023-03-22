package cn.iocoder.yudao.module.toolbox.convert.goldprice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPriceCreateReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPriceExcelVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPriceRespVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.GoldPriceUpdateReqVO;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 金价监控 Convert
 *
 * @author 六楼的雨
 */
@Mapper
public interface GoldPriceConvert {

    GoldPriceConvert INSTANCE = Mappers.getMapper(GoldPriceConvert.class);

    GoldPriceDO convert(GoldPriceCreateReqVO bean);

    GoldPriceDO convert(GoldPriceUpdateReqVO bean);

    GoldPriceRespVO convert(GoldPriceDO bean);

    List<GoldPriceRespVO> convertList(List<GoldPriceDO> list);

    PageResult<GoldPriceRespVO> convertPage(PageResult<GoldPriceDO> page);

    List<GoldPriceExcelVO> convertList02(List<GoldPriceDO> list);

}
