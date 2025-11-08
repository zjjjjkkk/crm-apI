package com.crm.vo;
import com.crm.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "管理员（员工）VO实体")
public class SysManagerVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "员工ID")
    private Integer id;

    @Schema(description = "账号", required = true)
    @NotBlank(message = "账号不能为空")
    private String account;

    @Schema(description = "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "状态 0-禁用，1-启用")
    private Integer status;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 序列化时隐藏密码，避免返回给前端
    private String password;

    @Schema(description = "角色ID")
    private Integer roleId;

    // 新增：部门ID（关联t_department表id）
    @Schema(description = "部门ID")
    private Integer departId;

    @Schema(description = "启用状态 0：正常，1：停用", required = true)
    @Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer isEnabled;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN) // DateUtils需项目已定义，格式如"yyyy-MM-dd HH:mm:ss"
    private LocalDateTime createTime;
}