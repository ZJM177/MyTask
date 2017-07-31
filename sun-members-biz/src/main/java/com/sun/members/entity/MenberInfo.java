package com.sun.members.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenberInfo extends BasicEntity implements Serializable {
    private Integer id;

    /** 用户名，唯一 */
    private String username;

    /** 会员密码 */
    private String password;

    private String mobileno;

    /** 微信号，可扩展微信 */
    private String openid;

    private Integer gender;

    private String address;

    /** 证件类型
1：身份证，2：学生证，3：护照，4：驾驶证 */
    private Integer idType;

    /** 证件号码 */
    private String idNo;

    /** 备注 */
    private String remark;

    private static final long serialVersionUID = 1L;
}