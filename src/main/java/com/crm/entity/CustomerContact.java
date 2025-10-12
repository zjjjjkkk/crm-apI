package com.crm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客户联系人表
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("customer_contact")
@ApiModel(value = "CustomerContact对象", description = "客户联系人表")
public class CustomerContact {

    @ApiModelProperty("联系人ID（主键）")
    @TableId(value = "contact_id", type = IdType.AUTO)
    private Integer contactId;

    @ApiModelProperty("关联客户ID（外键）")
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty("联系人姓名")
    @TableField("contact_name")
    private String contactName;

    @ApiModelProperty("职位")
    @TableField("position")
    private String position;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除（0-未删除，1-已删除）")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
