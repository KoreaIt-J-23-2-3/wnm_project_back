package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    public Integer addProduct(Product product);
    public Product getProductByProductId(int productId);
    public List<Product> getProducts(Map<String, Object> reqMap);
    public Integer updateProduct(Product product);
    public Integer deleteProduct(int productId);
}
