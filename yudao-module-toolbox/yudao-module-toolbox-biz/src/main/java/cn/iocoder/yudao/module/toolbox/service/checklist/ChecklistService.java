package cn.iocoder.yudao.module.toolbox.service.checklist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistCreateReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistExportReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistPageReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistUpdateReqVO;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 任务清单 Service 接口
 *
 * @author 六楼的雨
 */
public interface ChecklistService {

    /**
     * 创建任务清单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChecklist(@Valid ChecklistCreateReqVO createReqVO);

    /**
     * 更新任务清单
     *
     * @param updateReqVO 更新信息
     */
    void updateChecklist(@Valid ChecklistUpdateReqVO updateReqVO);

    /**
     * 删除任务清单
     *
     * @param id 编号
     */
    void deleteChecklist(Long id);

    /**
     * 获得任务清单
     *
     * @param id 编号
     * @return 任务清单
     */
    ChecklistDO getChecklist(Long id);

    /**
     * 获得任务清单列表
     *
     * @param ids 编号
     * @return 任务清单列表
     */
    List<ChecklistDO> getChecklistList(Collection<Long> ids);

    /**
     * 获得任务清单分页
     *
     * @param pageReqVO 分页查询
     * @return 任务清单分页
     */
    PageResult<ChecklistDO> getChecklistPage(ChecklistPageReqVO pageReqVO);

    /**
     * 获得任务清单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 任务清单列表
     */
    List<ChecklistDO> getChecklistList(ChecklistExportReqVO exportReqVO);
    
    // 自定义方法
    String taskChecklistText();
    
    /**
     * 获得任务清单当前任务的文本列表
     *
     * @return 任务清单当前任务的文本列表
     */
    String todoChecklistText();
    
    void help(String dingUserId);
    void listTodo(String dingUserId);
    void listTask(String dingUserId);
    void status(String dingUserId, Integer indexNo);
    
    void addTask(String dingUserId, String[] params, Integer checklistType);
    
    void delete(String dingUserId, Integer indexNo);
    
    void taskInfo(String dingUserId, String[] params);
}
