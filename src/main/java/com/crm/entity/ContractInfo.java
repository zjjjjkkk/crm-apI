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
 * 合同信息表
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("contract_info")
@ApiModel(value = "ContractInfo对象", description = "合同信息表")
public class ContractInfo {

    @ApiModelProperty("合同ID（主键）")
    @TableId(value = "contract_id", type = IdType.AUTO)
    private Integer contractId;

    @ApiModelProperty("合同编号（唯一）")
    @TableField("contract_no")
    private String contractNo;

    @ApiModelProperty("合同名称")
    @TableField("contract_name")
    private String contractName;

    @ApiModelProperty("关联客户ID（外键）")
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty("合同金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("合同开始日期")
    @TableField("start_date")
    private LocalDate startDate;

    @ApiModelProperty("合同结束日期")
    @TableField("end_date")
    private LocalDate endDate;

    @ApiModelProperty("公司签约人")
    @TableField("sign_person")
    private String signPerson;

    @ApiModelProperty("创建人ID")
    @TableField("create_user")
    private Integer createUser;

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
