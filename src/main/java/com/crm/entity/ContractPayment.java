package com.crm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 合同回款表
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("contract_payment")
@ApiModel(value = "ContractPayment对象", description = "合同回款表")
public class ContractPayment {

    @ApiModelProperty("回款ID（主键）")
    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;

    @ApiModelProperty("关联合同ID（外键）")
    @TableField("contract_id")
    private Integer contractId;

    @ApiModelProperty("回款编号（唯一）")
    @TableField("payment_no")
    private String paymentNo;

    @ApiModelProperty("回款金额")
    @TableField("payment_amount")
    private BigDecimal paymentAmount;

    @ApiModelProperty("回款日期")
    @TableField("payment_date")
    private LocalDate paymentDate;

    @ApiModelProperty("收款方式（银行转账/在线收款等）")
    @TableField("payment_method")
    private String paymentMethod;

    @ApiModelProperty("审批状态（0-待审批，1-通过，2-拒绝）")
    @TableField("audit_status")
    private Byte auditStatus;

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
