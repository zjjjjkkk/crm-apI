package com.crm.service.impl;

import com.crm.entity.CustomerContact;
import com.crm.mapper.CustomerContactMapper;
import com.crm.service.CustomerContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户联系人表 服务实现类
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Service
public class CustomerContactServiceImpl extends ServiceImpl<CustomerContactMapper, CustomerContact> implements CustomerContactService {

}
