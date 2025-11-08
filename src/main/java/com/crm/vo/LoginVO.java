package com.crm.vo;

import lombok.Data;

@Data
public class LoginVO {
    private Integer userId;
    private String username;
    private Integer deptId; // 用户所属部门ID
    private String deptName; // 用户所属部门名称
    private String deptLevel; // 部门层级（格式：父部门ID,子部门ID，如“1,3”）
    private String token;
}