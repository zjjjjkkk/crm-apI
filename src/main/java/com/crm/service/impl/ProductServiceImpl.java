package com.crm.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.exception.ServerException;
import com.crm.common.result.PageResult;
import com.crm.entity.Product;
import com.crm.mapper.ProductMapper;
import com.crm.query.ProductQuery;
import com.crm.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public PageResult<Product> getPage(ProductQuery query) {
        Page<Product> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper< Product> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(query.getName())){
            wrapper.like(Product::getName, query.getName());
        }
        if(query.getStatus() != null){
            wrapper.eq(Product::getStatus, query.getStatus());
        }
        Page<Product> result = baseMapper.selectPage(page, wrapper);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    public void saveOrEdit(Product product) {
        // 1. 校验商品名称唯一性
        LambdaQueryWrapper<Product> checkWrapper = new LambdaQueryWrapper<>();
        checkWrapper.eq(Product::getName, product.getName());
        if (product.getId() != null) {
            checkWrapper.ne(Product::getId, product.getId()); // 排除当前编辑的商品
        }
        checkWrapper.eq(Product::getDeleteFlag, 0); // 只校验未删除的商品

        Product existProduct = baseMapper.selectOne(checkWrapper);
        if (existProduct != null) {
            throw new ServerException("商品名称已存在，请修改后重试");
        }

        // 2. 区分新增和编辑逻辑
        if (product.getId() == null) {
            // 新增：直接插入
            baseMapper.insert(product);
        } else {
            // 编辑：构造更新条件（按ID更新，确保精准性）
            LambdaUpdateWrapper<Product> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Product::getId, product.getId())
                    .eq(Product::getDeleteFlag, 0); // 只更新未删除的商品

            // 调用 update(更新字段对象, 更新条件)
            baseMapper.update(product, updateWrapper);
        }
    }
    @Override
    public void batchUpdateProductState() {
//        定时下架时间早于当前定时任务执行的时间，修改商品状态
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<Product>()
                .lt(Product::getOffShelfTime, LocalDateTime.now());
        Product offProduct = new Product();
        offProduct.setStatus(2);
        offProduct.setOffShelfTime(null);
        baseMapper.update(offProduct, wrapper);

        wrapper.clear();
        wrapper.lt(Product::getOnShelfTime, LocalDateTime.now());
        Product onProduct = new Product();
        onProduct.setStatus(1);
        onProduct.setOnShelfTime(null);
        baseMapper.update(onProduct, wrapper);

    }
}