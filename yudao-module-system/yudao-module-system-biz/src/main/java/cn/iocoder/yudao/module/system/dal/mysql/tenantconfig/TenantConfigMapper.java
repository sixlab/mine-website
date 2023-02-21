package cn.iocoder.yudao.module.system.dal.mysql.tenantconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;

/**
 * 租户参数配置 Mapper
 *
 * @author 六楼的雨
 */
@Mapper
public interface TenantConfigMapper extends BaseMapperX<TenantConfigDO> {

    default PageResult<TenantConfigDO> selectPage(TenantConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TenantConfigDO>()
                .likeIfPresent(TenantConfigDO::getCategory, reqVO.getCategory())
                .eqIfPresent(TenantConfigDO::getType, reqVO.getType())
                .likeIfPresent(TenantConfigDO::getName, reqVO.getName())
                .likeIfPresent(TenantConfigDO::getConfigKey, reqVO.getConfigKey())
                .likeIfPresent(TenantConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(TenantConfigDO::getVisible, reqVO.getVisible())
                .eqIfPresent(TenantConfigDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(TenantConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantConfigDO::getId));
    }

    default List<TenantConfigDO> selectList(TenantConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TenantConfigDO>()
                .likeIfPresent(TenantConfigDO::getCategory, reqVO.getCategory())
                .eqIfPresent(TenantConfigDO::getType, reqVO.getType())
                .likeIfPresent(TenantConfigDO::getName, reqVO.getName())
                .likeIfPresent(TenantConfigDO::getConfigKey, reqVO.getConfigKey())
                .likeIfPresent(TenantConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(TenantConfigDO::getVisible, reqVO.getVisible())
                .eqIfPresent(TenantConfigDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(TenantConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TenantConfigDO::getId));
    }

}
