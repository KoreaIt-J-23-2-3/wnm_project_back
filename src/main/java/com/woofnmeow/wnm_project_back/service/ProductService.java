package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.ProductRespDto;
import com.woofnmeow.wnm_project_back.entity.Product;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addProduct(AddProductReqDto addProductReqDto) {
        return productMapper.addProduct(addProductReqDto.toEntity()) > 0;
    }

    public ProductRespDto getProductByProductId(int productId) {
        return productMapper.getProductByProductId(productId).toProductRespDto();
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
