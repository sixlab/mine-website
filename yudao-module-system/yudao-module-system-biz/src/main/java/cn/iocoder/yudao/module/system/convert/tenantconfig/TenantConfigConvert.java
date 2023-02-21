package cn.iocoder.yudao.module.system.convert.tenantconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;

/**
 * 租户参数配置 Convert
 *
 * @author 六楼的雨
 */
@Mapper
public interface TenantConfigConvert {

    TenantConfigConvert INSTANCE = Mappers.getMapper(TenantConfigConvert.class);

    TenantConfigDO convert(TenantConfigCreateReqVO bean);

    TenantConfigDO convert(TenantConfigUpdateReqVO bean);

    TenantConfigRespVO convert(TenantConfigDO bean);

    List<TenantConfigRespVO> convertList(List<TenantConfigDO> list);

    PageResult<TenantConfigRespVO> convertPage(PageResult<TenantConfigDO> page);

    List<TenantConfigExcelVO> convertList02(List<TenantConfigDO> list);

}
