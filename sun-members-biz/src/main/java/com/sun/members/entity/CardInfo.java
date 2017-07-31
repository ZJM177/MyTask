package com.sun.members.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInfo extends BasicEntity implements Serializable {
    private Integer id;

    /** 关联会员id */
    private Integer memberId;

    /** 卡号 */
    private String cardCode;

    /** 注册时间 */
    private Date registerTime;

    /** 状态。1：正常，2：已冻结，3：已挂失 */
    private Integer status;

    /** 冻结，锁定时间 */
    private Date lockTime;

    /** 卡等级，类型 */
    private String level;

    /** 卡内余额 */
    private BigDecimal balance;

    /** 折扣 */
    private BigDecimal discount;

    /** 积分值 */
    private BigDecimal point;

    private String cardColor;

    /** 备注 */
    private String remark;

    private static final long serialVersionUID = 1L;
}