package com.sun.members.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasicEntity implements Serializable {
    private Integer id;

    /** 用户表id，表示是由谁创建的 */
    private Integer parentId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 公司id，表示该员工属于哪个公司 */
    private Integer companyId;

    /** 用户状态，1：正常，2：冻结 */
    private Integer status;

    /** 用户姓名 */
    private String name;

    /** 手机号 */
    private String mobileno;

    /** 邮箱 */
    private String email;

    /** 到期时间 */
    private Date expiredtime;

    private static final long serialVersionUID = 1L;
}