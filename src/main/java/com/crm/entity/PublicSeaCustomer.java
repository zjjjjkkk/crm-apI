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
 * 公海客户表
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("public_sea_customer")
@ApiModel(value = "PublicSeaCustomer对象", description = "公海客户表")
public class PublicSeaCustomer {

    @ApiModelProperty("公海客户ID（主键）")
    @TableId(value = "public_customer_id", type = IdType.AUTO)
    private Integer publicCustomerId;

    @ApiModelProperty("客户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("客户级别")
    @TableField("level")
    private Byte level;

    @ApiModelProperty("客户行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty("客户来源")
    @TableField("source")
    private String source;

    @ApiModelProperty("进入公海时间")
    @TableField("enter_public_time")
    private LocalDateTime enterPublicTime;

    @ApiModelProperty("回收原因")
    @TableField("recycle_reason")
    private String recycleReason;

    @ApiModelProperty("领取人ID（为空表示未领取）")
    @TableField("owner_user_id")
    private Integer ownerUserId;

    @ApiModelProperty("领取时间")
    @TableField("claim_time")
    private LocalDateTime claimTime;

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
