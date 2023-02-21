package cn.iocoder.yudao.module.system.service.tenantconfig;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 租户参数配置 Service 接口
 *
 * @author 六楼的雨
 */
public interface TenantConfigService {

    /**
     * 创建租户参数配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createTenantConfig(@Valid TenantConfigCreateReqVO createReqVO);

    /**
     * 更新租户参数配置
     *
     * @param updateReqVO 更新信息
     */
    void updateTenantConfig(@Valid TenantConfigUpdateReqVO updateReqVO);

    /**
     * 删除租户参数配置
     *
     * @param id 编号
     */
    void deleteTenantConfig(Integer id);

    /**
     * 获得租户参数配置
     *
     * @param id 编号
     * @return 租户参数配置
     */
    TenantConfigDO getTenantConfig(Integer id);

    /**
     * 获得租户参数配置列表
     *
     * @param ids 编号
     * @return 租户参数配置列表
     */
    List<TenantConfigDO> getTenantConfigList(Collection<Integer> ids);

    /**
     * 获得租户参数配置分页
     *
     * @param pageReqVO 分页查询
     * @return 租户参数配置分页
     */
    PageResult<TenantConfigDO> getTenantConfigPage(TenantConfigPageReqVO pageReqVO);

    /**
     * 获得租户参数配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 租户参数配置列表
     */
    List<TenantConfigDO> getTenantConfigList(TenantConfigExportReqVO exportReqVO);

}
