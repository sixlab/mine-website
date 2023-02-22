package cn.iocoder.yudao.module.system.service.tenantconfig;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.tenantconfig.TenantConfigConvert;
import cn.iocoder.yudao.module.system.dal.mysql.tenantconfig.TenantConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 租户参数配置 Service 实现类
 *
 * @author 六楼的雨
 */
@Service
@Validated
public class TenantConfigServiceImpl implements TenantConfigService {

    @Resource
    private TenantConfigMapper tenantConfigMapper;

    @Override
    public Long createTenantConfig(TenantConfigCreateReqVO createReqVO) {
        // 插入
        TenantConfigDO tenantConfig = TenantConfigConvert.INSTANCE.convert(createReqVO);
        tenantConfigMapper.insert(tenantConfig);
        // 返回
        return tenantConfig.getId();
    }

    @Override
    public void updateTenantConfig(TenantConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateTenantConfigExists(updateReqVO.getId());
        // 更新
        TenantConfigDO updateObj = TenantConfigConvert.INSTANCE.convert(updateReqVO);
        tenantConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteTenantConfig(Long id) {
        // 校验存在
        validateTenantConfigExists(id);
        // 删除
        tenantConfigMapper.deleteById(id);
    }

    private void validateTenantConfigExists(Long id) {
        if (tenantConfigMapper.selectById(id) == null) {
            throw exception(TENANT_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public TenantConfigDO getTenantConfig(Long id) {
        return tenantConfigMapper.selectById(id);
    }

    @Override
    public List<TenantConfigDO> getTenantConfigList(Collection<Long> ids) {
        return tenantConfigMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TenantConfigDO> getTenantConfigPage(TenantConfigPageReqVO pageReqVO) {
        return tenantConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TenantConfigDO> getTenantConfigList(TenantConfigExportReqVO exportReqVO) {
        return tenantConfigMapper.selectList(exportReqVO);
    }

}
