package com.crm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_manager")
public class SysManager {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("account")
    private String account;
    @TableField("nickname")
    private String nickname;
    @TableField("password")
    private String password;
    @TableField("status")
    private Integer status;

    // 新增：用户所属部门ID（关联 t_department 表的 id）
    @TableField("dept_id")
    private Integer deptId;

    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}