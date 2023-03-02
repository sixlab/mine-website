package cn.iocoder.yudao.module.toolbox.service.checklist;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkService;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistCreateReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistExportReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistPageReqVO;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.ChecklistUpdateReqVO;
import cn.iocoder.yudao.module.toolbox.convert.checklist.ChecklistConvert;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import cn.iocoder.yudao.module.toolbox.dal.mysql.checklist.ChecklistMapper;
import cn.iocoder.yudao.module.toolbox.enums.checklist.ChecklistTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.toolbox.enums.ErrorCodeConstants.CHECKLIST_NOT_EXISTS;

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
    
    @Resource
    private DingtalkFrameworkService dingtalkFrameworkService;
    
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
    
    // 自定义方法
    
    /**
     * 获取未启用任务的文本清单
     *
     * @return 未启用任务的文本
     */
    @Override
    public String taskChecklistText() {
        // 未启用的任务
        List<ChecklistDO> todoList = checklistMapper.selectList("status", CommonStatusEnum.DISABLE.getStatus());
        
        int index = 0;
        
        StringBuilder sb = new StringBuilder();
        sb.append("您好，您的未启用清单：\n\n");
        
        for (ChecklistDO stTodo : todoList) {
            stTodo.setChecklistIndex(--index);
            checklistMapper.updateById(stTodo);
            
            sb.append(index).append(". ").append(stTodo.getName()).append("\n");
        }
        
        return sb.toString();
    }
    
    
    /**
     * 获取已启用任务的文本清单
     *
     * @return 已启用任务的文本
     */
    @Override
    public String todoChecklistText() {
        // 启用的任务
        List<ChecklistDO> todoList = checklistMapper.selectList("status", CommonStatusEnum.ENABLE.getStatus());
        
        int index = 0;
        
        StringBuilder sb = new StringBuilder();
        sb.append("您好，您的提醒清单：\n\n");
    
        sb.append("<<<<<<<<<<\n");
        for (ChecklistDO stTodo : todoList) {
            if(ChecklistTypeEnum.TIPS.getType().equals(stTodo.getChecklistType())){
                stTodo.setChecklistIndex(++index);
                checklistMapper.updateById(stTodo);
    
                sb.append(index).append(". ").append(stTodo.getName()).append("\n");
            }
        }
        sb.append(">>>>>>>>>>\n");
    
        sb.append("您好，您的待办清单：\n\n");
        for (ChecklistDO stTodo : todoList) {
            if(!ChecklistTypeEnum.TIPS.getType().equals(stTodo.getChecklistType())){
                stTodo.setChecklistIndex(++index);
                checklistMapper.updateById(stTodo);
    
                sb.append(index).append(". ").append(stTodo.getName()).append("\n");
            }
        }
        
        return sb.toString();
    }
    
    @Override
    public void help(String dingUserId) {
        StringBuilder sb = new StringBuilder();

        sb.append("回复 h/help: 返回帮助内容\n");
        sb.append("回复 l/list: 返回待办列表\n");
        sb.append("回复 ll: 返回所有任务列表\n");
        sb.append("回复 数字（大于零）: 将指定编号的待办完成\n");
        sb.append("回复 数字（小于零）: 将指定编号的任务置为待办\n");
    
        dingtalkFrameworkService.sendText(dingUserId, sb.toString());
        sb.setLength(0);
    
        sb.append("回复 以“d/delete/删除”开头的字符串: 删除任务，多个参数以空格分割，示例：\n");
        sb.append("    - 删除 1：删除序号是1的任务\n");
        sb.append("    - 删除 1 -2：删除序号是1、-2的任务\n\n");
    
        sb.append("回复 以“b/batch/批量”开头的字符串: 批量完成任务，示例：\n");
        sb.append("    - 批量 1 2 5：将序号是1/2/5的任务完成\n\n");
        sb.append("回复 以“t/tip/提示”开头的字符串: 设置常驻提醒任务，多个参数以换行符分割，示例：\n");
        sb.append("    - 提示 xxx yyy：设置常驻提醒任务\n\n");
        sb.append("回复 以“o/once”开头的字符串: 批量添加一次性任务，多个参数以换行符分割，示例：\n");
        sb.append("    - once xxx yyy：一次添加多个一次性任务\n");
    
        dingtalkFrameworkService.sendText(dingUserId, sb.toString());
        sb.setLength(0);
    
        sb.append("回复 以“a/add/添加”开头的字符串: 添加任务，多个参数以换行符分割，示例：\n");
        sb.append("    - a 任务名称 cron表达式：添加循环任务，并默认不启用，等下次cron表达式生效才启用\n");
        sb.append("    - a 任务名称 cron表达式 1：添加循环任务，并默认启用\n\n");
        sb.append("    - 59 59 23 * * ?\n");
        sb.append("    - 59 59 23 ? * MON,TUE,WED,THU,FRI,SAT,SUN\n");
    
        dingtalkFrameworkService.sendText(dingUserId, sb.toString());
    }
    
    @Override
    public void update(String dingUserId, String user) {
    
    }
    
    @Override
    public void restart(String dingUserId, String user) {
    
    }
    
    @Override
    public void listTodo(String dingUserId) {
        String todoText = todoChecklistText();
        dingtalkFrameworkService.sendText(dingUserId, todoText);
    }
    
    @Override
    public void listTask(String dingUserId) {
        String taskText = taskChecklistText();
        dingtalkFrameworkService.sendText(dingUserId, taskText);
    
        String todoText = todoChecklistText();
        dingtalkFrameworkService.sendText(dingUserId, todoText);
    }
    
    @Override
    public void status(String dingUserId, Integer indexNo) {
        ChecklistDO checklistDO = checklistMapper.selectOne("checklist_index", indexNo);
    
        if (null != checklistDO) {
            checklistDO.setChecklistIndex(null);
        
            StringBuilder sb = new StringBuilder();
            if (indexNo > 0) {
                // 大于0的任务，禁用这个任务
                checklistDO.setStatus(1);
                checklistMapper.updateById(checklistDO);
    
                sb.append("编号[").append(indexNo).append("]任务完成：").append(checklistDO.getName());
            } else {
                // 反之，启用这个任务
                checklistDO.setStatus(0);
                checklistMapper.updateById(checklistDO);
            
                sb.append("编号[").append(indexNo).append("]任务已启用：").append(checklistDO.getName());
            }
    
            dingtalkFrameworkService.sendText(dingUserId, sb.toString());
        } else {
            dingtalkFrameworkService.sendText(dingUserId, "未发现任务编号：" + indexNo);
        }
    }
    
    @Override
    public void addTask(String dingUserId, String[] params, Integer checklistType) {
        ChecklistDO checklistDO = new ChecklistDO();
    
        checklistDO.setChecklistCode(dingUserId + new Date().getTime());
    
        // 先设置为默认启用
        checklistDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        checklistDO.setChecklistIndex(0);
        checklistDO.setChecklistType(checklistType);
        checklistDO.setRemark("");
        checklistDO.setCreateTime(LocalDateTime.now());
        
        if(ChecklistTypeEnum.CRON.getType().equals(checklistType)){
            checklistDO.setName(params[1]);
            checklistDO.setChecklistCron(params[2]);
    
            if (params.length >= 4 && "1".equals(params[3])) {
                // 第四个参数是 1 的，才启用
                checklistDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
            }else{
                // 否则 cron 任务默认先禁用
                checklistDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
            }
            
            insertTask(dingUserId, checklistDO);
        }else{
            boolean task = false;
            for (String param : params) {
                // 第一个不是任务，跳过
                if(task){
                    checklistDO.setName(param);
        
                    insertTask(dingUserId, checklistDO);
                }else{
                    task = true;
                }
            }
        }
        
        // 列出当前所有任务
        dingtalkFrameworkService.sendText(dingUserId, taskChecklistText());
        dingtalkFrameworkService.sendText(dingUserId, todoChecklistText());
    }
    
    private void insertTask(String dingUserId, ChecklistDO checklistDO){
        checklistDO.setId(null);
    
        checklistMapper.insert(checklistDO);
    
        dingtalkFrameworkService.sendText(dingUserId, "任务添加完成：" + checklistDO.getName());
    }
    
    @Override
    public void delete(String dingUserId, Integer indexNo) {
        ChecklistDO checklistDO = checklistMapper.selectOne("checklist_index", indexNo);
    
        if (null != checklistDO) {
            checklistMapper.deleteById(checklistDO.getId());
        
            dingtalkFrameworkService.sendText(dingUserId, "编号[" + indexNo + "]任务已删除：" + checklistDO.getName());
        } else {
            dingtalkFrameworkService.sendText(dingUserId, "未发现任务编号：" + indexNo);
        }
    }
    
}
