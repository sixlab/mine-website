package cn.iocoder.yudao.module.toolbox.dal.mysql.checklist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.*;

/**
 * 任务清单 Mapper
 *
 * @author 六楼的雨
 */
@Mapper
public interface ChecklistMapper extends BaseMapperX<ChecklistDO> {

    default PageResult<ChecklistDO> selectPage(ChecklistPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChecklistDO>()
                .likeIfPresent(ChecklistDO::getName, reqVO.getName())
                .likeIfPresent(ChecklistDO::getChecklistCode, reqVO.getChecklistCode())
                .eqIfPresent(ChecklistDO::getChecklistIndex, reqVO.getChecklistIndex())
                .eqIfPresent(ChecklistDO::getChecklistType, reqVO.getChecklistType())
                .likeIfPresent(ChecklistDO::getChecklistCron, reqVO.getChecklistCron())
                .eqIfPresent(ChecklistDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ChecklistDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ChecklistDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ChecklistDO::getId));
    }

    default List<ChecklistDO> selectList(ChecklistExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ChecklistDO>()
                .likeIfPresent(ChecklistDO::getName, reqVO.getName())
                .likeIfPresent(ChecklistDO::getChecklistCode, reqVO.getChecklistCode())
                .eqIfPresent(ChecklistDO::getChecklistIndex, reqVO.getChecklistIndex())
                .eqIfPresent(ChecklistDO::getChecklistType, reqVO.getChecklistType())
                .likeIfPresent(ChecklistDO::getChecklistCron, reqVO.getChecklistCron())
                .eqIfPresent(ChecklistDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ChecklistDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ChecklistDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ChecklistDO::getId));
    }

}
