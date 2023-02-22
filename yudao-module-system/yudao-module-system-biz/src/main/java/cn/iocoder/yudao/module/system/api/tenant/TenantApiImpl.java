package cn.iocoder.yudao.module.system.api.tenant;

import cn.iocoder.yudao.module.system.api.tenant.dto.TenantConfigReqDTO;
import cn.iocoder.yudao.module.system.service.tenant.TenantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多租户的 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class TenantApiImpl implements TenantApi {

    @Resource
    private TenantService tenantService;

    @Override
    public List<Long> getTenantIdList() {
        return tenantService.getTenantIdList();
    }

    @Override
    public void validateTenant(Long id) {
        tenantService.validTenant(id);
    }
    
    @Override
    public String getTenantConfig(String key) {
        return tenantService.getTenantConfig(key);
    }
    
    @Override
    public TenantConfigReqDTO getTenantConfig(String key, String val) {
        return tenantService.getTenantConfig(key, val);
    }
    
}
