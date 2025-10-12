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
 * 客户信息表
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("customer_info")
@ApiModel(value = "CustomerInfo对象", description = "客户信息表")
public class CustomerInfo {

    @ApiModelProperty("客户ID（主键）")
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    @ApiModelProperty("客户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("客户级别（1-普通，2-重要，3-VIP）")
    @TableField("level")
    private Byte level;

    @ApiModelProperty("客户行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty("客户来源（线上引流/线下活动等）")
    @TableField("source")
    private String source;

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
