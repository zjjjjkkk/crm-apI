package com.crm.controller;

import com.crm.common.result.PageResult;
import com.crm.common.result.Result;
import com.crm.entity.FollowUp;
import com.crm.entity.Lead;
import com.crm.query.IdQuery;
import com.crm.query.LeadQuery;
import com.crm.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author crm  // 保留你本地的作者信息（也可以改成自己的名字）
 * @since 2025-10-12
 */
@Tag(name = "线索管理")  // 保留Swagger接口文档注解（方便接口调试）
@RestController
@RequestMapping("lead")  // 统一用远程的路径规范（加/crm前缀，避免接口冲突）
@AllArgsConstructor  // 保留lombok注入，简化依赖注入代码
public class LeadController {

    private final LeadService leadService;  // 保留Service注入

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<PageResult<Lead>> getPage(@RequestBody @Validated LeadQuery query) {
        return Result.ok(leadService.getPage(query));
    }

    @PostMapping("saveOrEdit")
    @Operation(summary = "保存或修改")
    public Result saveOrEdit(@RequestBody @Validated Lead lead) {
        leadService.saveOrEdit(lead);
        return Result.ok();
    }

    @PostMapping("followLead")
    @Operation(summary = "跟进线索")
    public Result followLead(@RequestBody @Validated FollowUp followUp) {
        leadService.followLead(followUp);
        return Result.ok();
    }

    @PostMapping("convertToCustomer")
    @Operation(summary = "线索转客户")
    public Result convertToCustomer(@RequestBody @Validated IdQuery idQuery) {
        leadService.convertToCustomer(idQuery);
        return Result.ok();
    }
}