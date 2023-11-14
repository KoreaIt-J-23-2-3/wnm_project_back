package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    // product_dtl_tb
    private int productDtlId;
    private int productMstId;
    private int price;
    private int sizeId;

    // product_mst_tb
    private String productName;
    private int petTypeId;
    private int productCategoryId;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private LocalDateTime createDate;

    // size_tb
    private String sizeName;

    // pet_type_tb
    private String petTypeName;

    // product_category_tb
    private String productCategoryName;


    // 실제 재고 수량
    private int currentQuantity;
    // 가용 재고 수량 (실제 재고 - 주문 재고)
    private int availableQuantity;


    public GetProductRespDto toProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetProductRespDto.builder()
                .productDtlId(productDtlId)
                .productMstId(productMstId)
                .price(price)
                .sizeId(sizeId)
                .productName(productName)
                .petTypeId(petTypeId)
                .productCategoryId(productCategoryId)
                .productDetailText(productDetailText.replaceAll("\n", "<br>"))
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .createDate(createDate.format(formatter))
                .sizeName(sizeName)
                .petTypeName(petTypeName)
                .productCategoryName(productCategoryName)
                .currentQuantity(currentQuantity)
                .availableQuantity(availableQuantity)
                .build();
    }




}
