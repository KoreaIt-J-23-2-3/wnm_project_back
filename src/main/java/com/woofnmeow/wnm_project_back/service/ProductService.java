package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
import com.woofnmeow.wnm_project_back.dto.SearchProductsReqDto;
import com.woofnmeow.wnm_project_back.entity.Product;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return productMapper.addProduct(addProductReqDto.toEntity()) > 0;
    }

    public GetProductRespDto getProductByProductId(int productId) {
        return productMapper.getProductByProductId(productId).toProductRespDto();
    }

    public List<GetProductRespDto> getProducts(String petTypeName, String productCategoryName, int pageIndex, SearchProductsReqDto searchProductsReqDto) {
        List<GetProductRespDto> respList = new ArrayList<>();
        Map<String, Object> reqMap = new HashMap<>();

        if(petTypeName == "dog") {
            petTypeName = "강아지";
        }else if(petTypeName == "cat") {
            petTypeName = "고양이";
        }

        if(productCategoryName == "homeliving") {
            productCategoryName = "홈·리빙";
        }else if(productCategoryName == "outdoor") {
            productCategoryName = "산책";
        }else if(productCategoryName == "carrier") {
            productCategoryName = "이동";
        }else if(productCategoryName == "fashion") {
            productCategoryName = "패션";
        }else if(productCategoryName == "toy") {
            productCategoryName = "장난감";
        }

        reqMap.put("petTypeName", petTypeName);
        reqMap.put("productCategoryName", productCategoryName);
        reqMap.put("pageIndex", (pageIndex - 1) * 10);
        reqMap.put("searchOption", searchProductsReqDto.getSearchOption());
        reqMap.put("searchValue", searchProductsReqDto.getSearchValue());
        reqMap.put("sortOption", searchProductsReqDto.getSortOption());
        System.out.println(reqMap);
        productMapper.getProducts(reqMap).forEach(product -> {
            respList.add(product.toProductRespDto());
        });

        return respList;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editProduct(int productId, EditProductReqDto editProductReqDto) {
        Product product = Product.builder()
                .productId(productId)
                .productName(editProductReqDto.getProductName())
                .productPrice(editProductReqDto.getProductPrice())
                .productDetailText(editProductReqDto.getProductDetailText())
                .productThumbnail(editProductReqDto.getProductThumbnail())
                .productDetailImg(editProductReqDto.getProductDetailImg())
                .petTypeId(editProductReqDto.getPetTypeId())
                .productCategoryId(editProductReqDto.getProductCategoryId())
                .noSize(editProductReqDto.getNoSize())
                .productSizeXS(editProductReqDto.getProductSizeXS())
                .productSizeS(editProductReqDto.getProductSizeS())
                .productSizeM(editProductReqDto.getProductSizeM())
                .productSizeL(editProductReqDto.getProductSizeL())
                .productSizeXL(editProductReqDto.getProductSizeXL())
                .productSizeXXL(editProductReqDto.getProductSizeXXL())
                .build();

        return productMapper.updateProduct(product) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeProduct(int productId) {
        return productMapper.deleteProduct(productId) > 0;
    }
}
