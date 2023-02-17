package cn.iocoder.yudao.module.toolbox.convert.checklist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.toolbox.controller.admin.checklist.vo.*;
import cn.iocoder.yudao.module.toolbox.dal.dataobject.checklist.ChecklistDO;

/**
 * 任务清单 Convert
 *
 * @author 六楼的雨
 */
@Mapper
public interface ChecklistConvert {

    ChecklistConvert INSTANCE = Mappers.getMapper(ChecklistConvert.class);

    ChecklistDO convert(ChecklistCreateReqVO bean);

    ChecklistDO convert(ChecklistUpdateReqVO bean);

    ChecklistRespVO convert(ChecklistDO bean);

    List<ChecklistRespVO> convertList(List<ChecklistDO> list);

    PageResult<ChecklistRespVO> convertPage(PageResult<ChecklistDO> page);

    List<ChecklistExcelVO> convertList02(List<ChecklistDO> list);

}
