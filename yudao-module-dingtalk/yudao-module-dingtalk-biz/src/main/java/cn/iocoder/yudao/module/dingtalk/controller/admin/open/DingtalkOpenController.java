package cn.iocoder.yudao.module.dingtalk.controller.admin.open;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.dingtalk.core.dal.redis.DingtalkKeyConstants;
import cn.iocoder.yudao.framework.dingtalk.core.service.DingtalkFrameworkService;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.dingtalk.mq.producer.ChecklistProducer;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import cn.iocoder.yudao.module.system.api.tenant.dto.TenantConfigReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Tag(name = "管理后台 - 钉钉接口")
@RestController
@RequestMapping("/dingtalk/open")
@Validated
@Slf4j
public class DingtalkOpenController {
    
    @Resource
    private TenantApi tenantApi;
    
    @Resource
    private ChecklistProducer checklistProducer;
    
    @PostMapping("/callback")
    @Operation(summary = "钉钉回调")
    public CommonResult<Long> callback(@RequestBody JSONObject param) {
        // 输出回调内容
        log.info(param.toStringPretty());
        CommonResult<Long> respVO = new CommonResult<>();
        
        // 获取当前回调所属用户
        String dingUserId = param.getStr("senderStaffId");
        
        TenantConfigReqDTO configReqDTO = tenantApi.getTenantConfig(DingtalkKeyConstants.DINGTALK_USER_ID, dingUserId);
        
        if (null == configReqDTO) {
            respVO.setCode(100);
            return respVO;
        }
        
        Long tenantId = configReqDTO.getTenantId();
        TenantContextHolder.setTenantId(tenantId);
        TenantContextHolder.setIgnore(false);
        
        String msgType = param.getStr("msgtype");
        if ("text".equals(msgType)) {
            JSONObject text = param.getJSONObject("text");
            if (null != text) {
                String content = text.getStr("content");
                checklistProducer.textCallback(dingUserId, content);
            }
        }
        
        respVO.setCode(400);
        return respVO;
    }
    
}
