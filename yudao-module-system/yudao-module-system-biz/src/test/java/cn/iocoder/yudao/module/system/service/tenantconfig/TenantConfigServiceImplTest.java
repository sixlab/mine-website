package cn.iocoder.yudao.module.system.service.tenantconfig;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.tenantconfig.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.tenantconfig.TenantConfigDO;
import cn.iocoder.yudao.module.system.dal.mysql.tenantconfig.TenantConfigMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link TenantConfigServiceImpl} 的单元测试类
*
* @author 六楼的雨
*/
@Import(TenantConfigServiceImpl.class)
public class TenantConfigServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TenantConfigServiceImpl tenantConfigService;

    @Resource
    private TenantConfigMapper tenantConfigMapper;

    @Test
    public void testCreateTenantConfig_success() {
        // 准备参数
        TenantConfigCreateReqVO reqVO = randomPojo(TenantConfigCreateReqVO.class);

        // 调用
        Long tenantConfigId = tenantConfigService.createTenantConfig(reqVO);
        // 断言
        assertNotNull(tenantConfigId);
        // 校验记录的属性是否正确
        TenantConfigDO tenantConfig = tenantConfigMapper.selectById(tenantConfigId);
        assertPojoEquals(reqVO, tenantConfig);
    }

    @Test
    public void testUpdateTenantConfig_success() {
        // mock 数据
        TenantConfigDO dbTenantConfig = randomPojo(TenantConfigDO.class);
        tenantConfigMapper.insert(dbTenantConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TenantConfigUpdateReqVO reqVO = randomPojo(TenantConfigUpdateReqVO.class, o -> {
            o.setId(dbTenantConfig.getId()); // 设置更新的 ID
        });

        // 调用
        tenantConfigService.updateTenantConfig(reqVO);
        // 校验是否更新正确
        TenantConfigDO tenantConfig = tenantConfigMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, tenantConfig);
    }

    @Test
    public void testUpdateTenantConfig_notExists() {
        // 准备参数
        TenantConfigUpdateReqVO reqVO = randomPojo(TenantConfigUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> tenantConfigService.updateTenantConfig(reqVO), TENANT_CONFIG_NOT_EXISTS);
    }

    @Test
    public void testDeleteTenantConfig_success() {
        // mock 数据
        TenantConfigDO dbTenantConfig = randomPojo(TenantConfigDO.class);
        tenantConfigMapper.insert(dbTenantConfig);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTenantConfig.getId();

        // 调用
        tenantConfigService.deleteTenantConfig(id);
       // 校验数据不存在了
       assertNull(tenantConfigMapper.selectById(id));
    }

    @Test
    public void testDeleteTenantConfig_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> tenantConfigService.deleteTenantConfig(id), TENANT_CONFIG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTenantConfigPage() {
       // mock 数据
       TenantConfigDO dbTenantConfig = randomPojo(TenantConfigDO.class, o -> { // 等会查询到
           o.setCategory(null);
           o.setType(null);
           o.setName(null);
           o.setConfigKey(null);
           o.setValue(null);
           o.setVisible(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       tenantConfigMapper.insert(dbTenantConfig);
       // 测试 category 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setCategory(null)));
       // 测试 type 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setType(null)));
       // 测试 name 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setName(null)));
       // 测试 configKey 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setConfigKey(null)));
       // 测试 value 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setValue(null)));
       // 测试 visible 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setVisible(null)));
       // 测试 remark 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setCreateTime(null)));
       // 准备参数
       TenantConfigPageReqVO reqVO = new TenantConfigPageReqVO();
       reqVO.setCategory(null);
       reqVO.setType(null);
       reqVO.setName(null);
       reqVO.setConfigKey(null);
       reqVO.setValue(null);
       reqVO.setVisible(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TenantConfigDO> pageResult = tenantConfigService.getTenantConfigPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTenantConfig, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTenantConfigList() {
       // mock 数据
       TenantConfigDO dbTenantConfig = randomPojo(TenantConfigDO.class, o -> { // 等会查询到
           o.setCategory(null);
           o.setType(null);
           o.setName(null);
           o.setConfigKey(null);
           o.setValue(null);
           o.setVisible(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       tenantConfigMapper.insert(dbTenantConfig);
       // 测试 category 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setCategory(null)));
       // 测试 type 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setType(null)));
       // 测试 name 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setName(null)));
       // 测试 configKey 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setConfigKey(null)));
       // 测试 value 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setValue(null)));
       // 测试 visible 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setVisible(null)));
       // 测试 remark 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       tenantConfigMapper.insert(cloneIgnoreId(dbTenantConfig, o -> o.setCreateTime(null)));
       // 准备参数
       TenantConfigExportReqVO reqVO = new TenantConfigExportReqVO();
       reqVO.setCategory(null);
       reqVO.setType(null);
       reqVO.setName(null);
       reqVO.setConfigKey(null);
       reqVO.setValue(null);
       reqVO.setVisible(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TenantConfigDO> list = tenantConfigService.getTenantConfigList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTenantConfig, list.get(0));
    }

}
