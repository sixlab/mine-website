package cn.iocoder.yudao.module.toolbox.service.checklist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.toolbox.convert.checklist.ChecklistConvert;
import cn.iocoder.yudao.module.toolbox.dal.mysql.checklist.ChecklistMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.toolbox.enums.ErrorCodeConstants.*;

/**
 * 任务清单 Service 实现类
 *
 * @author 六楼的雨
 */
@Service
@Validated
public class ChecklistServiceImpl implements ChecklistService {

    @Resource
    private ChecklistMapper checklistMapper;

    @Override
    public Long createChecklist(ChecklistCreateReqVO createReqVO) {
        // 插入
        ChecklistDO checklist = ChecklistConvert.INSTANCE.convert(createReqVO);
        checklistMapper.insert(checklist);
        // 返回
        return checklist.getId();
    }

    @Override
    public void updateChecklist(ChecklistUpdateReqVO updateReqVO) {
        // 校验存在
        validateChecklistExists(updateReqVO.getId());
        // 更新
        ChecklistDO updateObj = ChecklistConvert.INSTANCE.convert(updateReqVO);
        checklistMapper.updateById(updateObj);
    }

    @Override
    public void deleteChecklist(Long id) {
        // 校验存在
        validateChecklistExists(id);
        // 删除
        checklistMapper.deleteById(id);
    }

    private void validateChecklistExists(Long id) {
        if (checklistMapper.selectById(id) == null) {
            throw exception(CHECKLIST_NOT_EXISTS);
        }
    }

    @Override
    public ChecklistDO getChecklist(Long id) {
        return checklistMapper.selectById(id);
    }

    @Override
    public List<ChecklistDO> getChecklistList(Collection<Long> ids) {
        return checklistMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ChecklistDO> getChecklistPage(ChecklistPageReqVO pageReqVO) {
        return checklistMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ChecklistDO> getChecklistList(ChecklistExportReqVO exportReqVO) {
        return checklistMapper.selectList(exportReqVO);
    }

}
