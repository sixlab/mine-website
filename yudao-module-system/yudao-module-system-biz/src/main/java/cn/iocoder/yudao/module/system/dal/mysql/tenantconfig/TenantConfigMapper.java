package cn.iocoder.yudao.module.system.dal.mysql.tenantconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.api.tenant.dto.TenantConfigReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.TenantConfigExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.TenantConfigPageReqVO;
import cn.iocoder.yudao.module.system.convert.tenant.TenantConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    
    default TenantConfigReqDTO selectByVal(String key, String val){
        QueryWrapper<TenantConfigDO> wrapper = new QueryWrapper<>();
        
        wrapper.select(" tenant_id AS id, category,type,name,config_key,value, visible,remark ");
        
        wrapper.eq("config_key", key).eq("value", val);
        
        TenantConfigDO result = selectOne(wrapper);
        
        return TenantConvert.INSTANCE.convert03(result);
    }
}
