package cn.iocoder.yudao.module.toolbox.dal.dataobject.goldprice;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 金价监控 DO
 *
 * @author 六楼的雨
 */
@TableName("toolbox_gold_price")
@KeySequence("toolbox_gold_price_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoldPriceDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 金价
     */
    private BigDecimal goldPrice;
    /**
     * 涨价幅度
     */
    private BigDecimal risePrice;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Byte status;
    /**
     * 备注
     */
    private String remark;

}
