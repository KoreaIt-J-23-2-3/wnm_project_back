package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
import com.woofnmeow.wnm_project_back.entity.Product;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addProduct(AddProductReqDto addProductReqDto) {
        Map<String, Object> map = new HashMap<>();
        Product product = addProductReqDto.toEntity();
        productMapper.addProductMaster(product);

        map.put("productMstId", product.getProductMstId());
        map.put("price", addProductReqDto.getPrice());
        if(addProductReqDto.getProductCategoryId() == 4 && addProductReqDto.getPetTypeId() == 1) {
            map.put("sizeId", 2);
            for(int i = 0; i < 6; i++) {
                productMapper.addProductDetail(map);
                map.replace("sizeId", i + 3);
            }
        }else {
            map.put("sizeId", 1);
            productMapper.addProductDetail(map);
        }
        return true;
    }

    public boolean incomingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        return productMapper.incomingQuantity(map) > 0;
    }

    public boolean outgoingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        return productMapper.outgoingQuantity(map) > 0;
    }

    public GetProductRespDto getProductByProductId(int productId) {
        return productMapper.getProductByProductId(productId).toProductRespDto();
    }

    public List<GetProductRespDto> getProducts(String petTypeName,
                                               String productCategoryName,
                                               String option,
                                               String value,
                                               String sort,
                                               int page) {
        List<GetProductRespDto> respList = new ArrayList<>();
        Map<String, Object> reqMap = new HashMap<>();



        reqMap.put("petTypeName", petTypeName);
        reqMap.put("productCategoryName", productCategoryName);
        reqMap.put("pageIndex", (page - 1) * 10);
        reqMap.put("searchOption", option);
        reqMap.put("searchValue", value);
        reqMap.put("sortOption", sort);
        System.out.println(reqMap);
        productMapper.getProducts(reqMap).forEach(product -> {
            respList.add(product.toProductRespDto());
        });

        return respList;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editProduct(int productId, EditProductReqDto editProductReqDto) {
        Product product = Product.builder()
                .build();

        return productMapper.updateProduct(product) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeProduct(int productId) {
        return productMapper.deleteProduct(productId) > 0;
    }
}
