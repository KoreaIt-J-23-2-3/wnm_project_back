package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public Integer addProduct(Product product);
    public Product getProductByProductId(int productId);
    public Integer updateProduct(Product product);
    public Integer deleteProduct(int productId);
}
