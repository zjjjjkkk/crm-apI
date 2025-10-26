package com.crm.service;

import com.crm.common.result.PageResult;
import com.crm.entity.Product;
import com.crm.query.ProductQuery;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author crm
 * @since 2025-10-12
 */
public interface ProductService {
    PageResult<Product> getPage(ProductQuery query);

    void saveOrEdit(Product product);

    void batchUpdateProductState();
}
