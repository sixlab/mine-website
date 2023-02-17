package cn.iocoder.yudao.module.toolbox.service.checklist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistCreateReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistExportReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistPageReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistUpdateReqVO;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import cn.iocoder.yudao.module.toolbox.dal.mysql.checklist.ChecklistMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.module.toolbox.enums.ErrorCodeConstants.CHECKLIST_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link ChecklistServiceImpl} 的单元测试类
*
* @author 六楼的雨
*/
@Import(ChecklistServiceImpl.class)
public class ChecklistServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ChecklistServiceImpl checklistService;

    @Resource
    private ChecklistMapper checklistMapper;

    @Test
    public void testCreateChecklist_success() {
        // 准备参数
        ChecklistCreateReqVO reqVO = randomPojo(ChecklistCreateReqVO.class);

        // 调用
        Long checklistId = checklistService.createChecklist(reqVO);
        // 断言
        assertNotNull(checklistId);
        // 校验记录的属性是否正确
        ChecklistDO checklist = checklistMapper.selectById(checklistId);
        assertPojoEquals(reqVO, checklist);
    }

    @Test
    public void testUpdateChecklist_success() {
        // mock 数据
        ChecklistDO dbChecklist = randomPojo(ChecklistDO.class);
        checklistMapper.insert(dbChecklist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ChecklistUpdateReqVO reqVO = randomPojo(ChecklistUpdateReqVO.class, o -> {
            o.setId(dbChecklist.getId()); // 设置更新的 ID
        });

        // 调用
        checklistService.updateChecklist(reqVO);
        // 校验是否更新正确
        ChecklistDO checklist = checklistMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, checklist);
    }

    @Test
    public void testUpdateChecklist_notExists() {
        // 准备参数
        ChecklistUpdateReqVO reqVO = randomPojo(ChecklistUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> checklistService.updateChecklist(reqVO), CHECKLIST_NOT_EXISTS);
    }

    @Test
    public void testDeleteChecklist_success() {
        // mock 数据
        ChecklistDO dbChecklist = randomPojo(ChecklistDO.class);
        checklistMapper.insert(dbChecklist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbChecklist.getId();

        // 调用
        checklistService.deleteChecklist(id);
       // 校验数据不存在了
       assertNull(checklistMapper.selectById(id));
    }

    @Test
    public void testDeleteChecklist_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> checklistService.deleteChecklist(id), CHECKLIST_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetChecklistPage() {
       // mock 数据
       ChecklistDO dbChecklist = randomPojo(ChecklistDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setChecklistCode(null);
           o.setChecklistIndex(null);
           o.setChecklistType(null);
           o.setChecklistCron(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       checklistMapper.insert(dbChecklist);
       // 测试 name 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setName(null)));
       // 测试 checklistCode 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistCode(null)));
       // 测试 checklistIndex 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistIndex(null)));
       // 测试 checklistType 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistType(null)));
       // 测试 checklistCron 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistCron(null)));
       // 测试 status 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setCreateTime(null)));
       // 准备参数
       ChecklistPageReqVO reqVO = new ChecklistPageReqVO();
       reqVO.setName(null);
       reqVO.setChecklistCode(null);
       reqVO.setChecklistIndex(null);
       reqVO.setChecklistType(null);
       reqVO.setChecklistCron(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ChecklistDO> pageResult = checklistService.getChecklistPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbChecklist, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetChecklistList() {
       // mock 数据
       ChecklistDO dbChecklist = randomPojo(ChecklistDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setChecklistCode(null);
           o.setChecklistIndex(null);
           o.setChecklistType(null);
           o.setChecklistCron(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       checklistMapper.insert(dbChecklist);
       // 测试 name 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setName(null)));
       // 测试 checklistCode 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistCode(null)));
       // 测试 checklistIndex 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistIndex(null)));
       // 测试 checklistType 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistType(null)));
       // 测试 checklistCron 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setChecklistCron(null)));
       // 测试 status 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       checklistMapper.insert(cloneIgnoreId(dbChecklist, o -> o.setCreateTime(null)));
       // 准备参数
       ChecklistExportReqVO reqVO = new ChecklistExportReqVO();
       reqVO.setName(null);
       reqVO.setChecklistCode(null);
       reqVO.setChecklistIndex(null);
       reqVO.setChecklistType(null);
       reqVO.setChecklistCron(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ChecklistDO> list = checklistService.getChecklistList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbChecklist, list.get(0));
    }

}
