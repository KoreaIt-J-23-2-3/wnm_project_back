package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    @Options(useGeneratedKeys = true, keyProperty = "productMstId")
    public Integer addProductMaster(Product product);
    public Integer addProductDetail(Map<String, Object> map);


    // public Integer incomingQuantity()  productDtlId, count
    // public Integer outgoingQuantity()  productDtlId, count
    public Product getProductByProductId(int productId);
    public Integer updateProduct(Product product);
    public Integer deleteProduct(int productId);
    public List<Product> getProducts(Map<String, Object> reqMap);
}
