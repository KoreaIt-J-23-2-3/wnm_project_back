package com.woofnmeow.wnm_project_back.service;

import com.google.gson.Gson;
import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.ProductRespDto;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import com.woofnmeow.wnm_project_back.test.ProductData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

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

    @Transactional(rollbackFor = Exception.class)
    public boolean crowling() throws FileNotFoundException {
        Gson gson = new Gson();
        ProductData productData = new ProductData();

        productData.crowling().forEach(json -> {
            AddProductReqDto addProductReqDto = gson.fromJson(json, AddProductReqDto.class);
            productMapper.addProduct(addProductReqDto.toEntity());
        });

        return 1 > 0;
    }
}
