package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.ProductRespDto;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public boolean addProduct(AddProductReqDto addProductReqDto) {
        return productMapper.addProduct(addProductReqDto.toEntity()) > 0;
    }

    public ProductRespDto getProductByProductId(int productId) {
        return productMapper.getProductByProductId(productId).toProductRespDto();
    }
}
