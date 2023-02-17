package cn.iocoder.yudao.module.toolbox.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 工作流 错误码枚举类
 *
 * 工作流系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode CHECKLIST_NOT_EXISTS = new ErrorCode(99010000, "任务清单不存在");

}
