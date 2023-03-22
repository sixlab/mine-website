package cn.iocoder.yudao.module.toolbox.service.goldprice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.toolbox.controller.admin.goldprice.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice.GoldPriceDO;
import cn.iocoder.yudao.module.toolbox.dal.mysql.goldprice.GoldPriceMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.toolbox.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link GoldPriceServiceImpl} 的单元测试类
*
* @author 六楼的雨
*/
@Import(GoldPriceServiceImpl.class)
public class GoldPriceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GoldPriceServiceImpl goldPriceService;

    @Resource
    private GoldPriceMapper goldPriceMapper;

    @Test
    public void testCreateGoldPrice_success() {
        // 准备参数
        GoldPriceCreateReqVO reqVO = randomPojo(GoldPriceCreateReqVO.class);

        // 调用
        Long goldPriceId = goldPriceService.createGoldPrice(reqVO);
        // 断言
        assertNotNull(goldPriceId);
        // 校验记录的属性是否正确
        GoldPriceDO goldPrice = goldPriceMapper.selectById(goldPriceId);
        assertPojoEquals(reqVO, goldPrice);
    }

    @Test
    public void testUpdateGoldPrice_success() {
        // mock 数据
        GoldPriceDO dbGoldPrice = randomPojo(GoldPriceDO.class);
        goldPriceMapper.insert(dbGoldPrice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GoldPriceUpdateReqVO reqVO = randomPojo(GoldPriceUpdateReqVO.class, o -> {
            o.setId(dbGoldPrice.getId()); // 设置更新的 ID
        });

        // 调用
        goldPriceService.updateGoldPrice(reqVO);
        // 校验是否更新正确
        GoldPriceDO goldPrice = goldPriceMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, goldPrice);
    }

    @Test
    public void testUpdateGoldPrice_notExists() {
        // 准备参数
        GoldPriceUpdateReqVO reqVO = randomPojo(GoldPriceUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> goldPriceService.updateGoldPrice(reqVO), GOLD_PRICE_NOT_EXISTS);
    }

    @Test
    public void testDeleteGoldPrice_success() {
        // mock 数据
        GoldPriceDO dbGoldPrice = randomPojo(GoldPriceDO.class);
        goldPriceMapper.insert(dbGoldPrice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbGoldPrice.getId();

        // 调用
        goldPriceService.deleteGoldPrice(id);
       // 校验数据不存在了
       assertNull(goldPriceMapper.selectById(id));
    }

    @Test
    public void testDeleteGoldPrice_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> goldPriceService.deleteGoldPrice(id), GOLD_PRICE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGoldPricePage() {
       // mock 数据
       GoldPriceDO dbGoldPrice = randomPojo(GoldPriceDO.class, o -> { // 等会查询到
           o.setGoldPrice(null);
           o.setRisePrice(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       goldPriceMapper.insert(dbGoldPrice);
       // 测试 goldPrice 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setGoldPrice(null)));
       // 测试 risePrice 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setRisePrice(null)));
       // 测试 status 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setCreateTime(null)));
       // 准备参数
       GoldPricePageReqVO reqVO = new GoldPricePageReqVO();
       reqVO.setGoldPrice(null);
       reqVO.setRisePrice(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<GoldPriceDO> pageResult = goldPriceService.getGoldPricePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbGoldPrice, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGoldPriceList() {
       // mock 数据
       GoldPriceDO dbGoldPrice = randomPojo(GoldPriceDO.class, o -> { // 等会查询到
           o.setGoldPrice(null);
           o.setRisePrice(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       goldPriceMapper.insert(dbGoldPrice);
       // 测试 goldPrice 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setGoldPrice(null)));
       // 测试 risePrice 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setRisePrice(null)));
       // 测试 status 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       goldPriceMapper.insert(cloneIgnoreId(dbGoldPrice, o -> o.setCreateTime(null)));
       // 准备参数
       GoldPriceExportReqVO reqVO = new GoldPriceExportReqVO();
       reqVO.setGoldPrice(null);
       reqVO.setRisePrice(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<GoldPriceDO> list = goldPriceService.getGoldPriceList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbGoldPrice, list.get(0));
    }

}
