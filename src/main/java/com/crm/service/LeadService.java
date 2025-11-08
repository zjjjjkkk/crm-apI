package com.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.common.result.PageResult;
import com.crm.entity.FollowUp;
import com.crm.entity.Lead;
import com.crm.query.IdQuery;
import com.crm.query.LeadQuery;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author crm  // 保留你本地的作者信息，也可改为自己的名字（如 zjjjjkkk）
 * @since 2025-10-12
 */
public interface LeadService extends IService<Lead> {
    // 分页查询线索
    PageResult<Lead> getPage(LeadQuery query);

    // 保存或修改线索
    void saveOrEdit(Lead lead);

    // 跟进线索
    void followLead(FollowUp followUp);

    // 线索转客户
    void convertToCustomer(IdQuery idQuery);
}