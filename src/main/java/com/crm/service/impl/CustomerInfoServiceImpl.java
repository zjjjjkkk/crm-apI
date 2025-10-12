package com.crm.service.impl;

import com.crm.entity.CustomerInfo;
import com.crm.mapper.CustomerInfoMapper;
import com.crm.service.CustomerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author vact
 * @since 2025-10-12
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

}
