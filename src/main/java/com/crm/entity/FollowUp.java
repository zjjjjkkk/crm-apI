package com.crm.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crm.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author crm  // 保留本地作者信息，可改为自己的名字（如 zjjjjkkk）
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("t_follow_up")
@ApiModel(value = "FollowUp对象", description = "")
public class FollowUp {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("0-客户跟进，1-线索跟进")
    @TableField("target_type")
    private Byte targetType;  // 统一为远程的Byte类型（更节省存储，符合小数值字段规范）

    @ApiModelProperty("跟进客户id")
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty("跟进类型")
    @TableField("follow_type")
    @NotNull(message = "跟进类型不能为空")  // 保留本地参数校验（避免空值提交）
    private Integer followType;

    @ApiModelProperty("跟进内容")
    @TableField("content")
    @NotBlank(message = "跟进内容不能为空")  // 保留本地参数校验（避免空内容提交）
    private String content;

    @ApiModelProperty("下次跟进时间")
    @TableField("next_follow_type")
    @NotNull(message = "请选择下次跟进时间")  // 保留本地参数校验
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")  // 保留时间格式化（前端交互更友好）
    private LocalDateTime nextFollowType;

    @ApiModelProperty("逻辑删除0-未删除，1-已删除")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Byte deleteFlag;  // 统一为远程的Byte类型（逻辑删除字段常用Byte）

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}