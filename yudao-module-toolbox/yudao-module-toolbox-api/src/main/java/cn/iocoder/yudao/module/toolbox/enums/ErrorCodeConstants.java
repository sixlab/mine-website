package cn.iocoder.yudao.module.toolbox.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 工具箱 错误码枚举类
 *
 * 工具箱，使用 99-01-0000 段
 */
public interface ErrorCodeConstants {
    ErrorCode CHECKLIST_NOT_EXISTS = new ErrorCode(99010000, "任务清单不存在");
    ErrorCode GOLD_PRICE_NOT_EXISTS = new ErrorCode(99010100, "金价监控不存在");
    
}
