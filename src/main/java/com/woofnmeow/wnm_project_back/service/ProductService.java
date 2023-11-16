package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.GetMasterProductRespDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addProduct(AddProductReqDto addProductReqDto) {
        Map<String, Object> map = new HashMap<>();
        ProductMst productMst = addProductReqDto.toEntity();
        productMapper.addProductMaster(productMst);

        map.put("productMstId", productMst.getProductMstId());
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

    public boolean updateIncomingQuantity(int incomingHistoryId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("incomingHistoryId", incomingHistoryId);
        map.put("count", count);
        return productMapper.updateIncomingQuantity(map) > 0;
    }

    public boolean deleteIncomingQuantity(int incomingHistoryId) {
        return productMapper.deleteIncomingQuantity(incomingHistoryId) > 0;
    }


    public boolean outgoingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        return productMapper.outgoingQuantity(map) > 0;
    }

    public GetProductRespDto getProductByProductDtlId(int productDtlId) {
        return productMapper.getProductByProductDtlId(productDtlId).toProductRespDto();
    }

    public GetProductRespDto getProductByProductMstId(int productMstId) {
        return productMapper.getProductByProductMstId(productMstId).toProductRespDto();
    }

    public List<GetMasterProductRespDto> getProducts(String petTypeName,
                                               String productCategoryName,
                                               String option,
                                               String value,
                                               String sort,
                                               int page) {
        Map<String, Object> reqMap = new HashMap<>();

        reqMap.put("petTypeName", petTypeName);
        reqMap.put("productCategoryName", productCategoryName);
        reqMap.put("pageIndex", (page - 1) * 10);
        reqMap.put("searchOption", option);
        reqMap.put("searchValue", value);
        reqMap.put("sortOption", sort);
        System.out.println(reqMap);

        return productMapper.getMasterProductList(reqMap).stream().map(ProductMst::toMasterProductRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editProduct(int productDtlId, EditProductReqDto editProductReqDto) {
        Map<String, Object> map = new HashMap<>();
        productMapper.updateProductDtl(editProductReqDto.toProductDtlEntity(productDtlId));
        map.put("productName", editProductReqDto.getProductName());
        map.put("productDetailText", editProductReqDto.getProductDetailText());
        map.put("productThumbnailUrl", editProductReqDto.getProductThumbnailUrl());
        map.put("productDetailUrl", editProductReqDto.getProductDetailUrl());
        map.put("productMstId", editProductReqDto.getProductMstId());
        productMapper.updateProductMst(map);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeProduct(int productMstId) {
        return productMapper.deleteProduct(productMstId) > 0;
    }
}
