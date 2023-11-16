package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetMasterProductRespDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductMst {
    private int productMstId;
    private String productName;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private LocalDateTime createDate;
    private List<ProductDtl> productDtlList;

    public GetProductRespDto toProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String detailText = productDetailText;
        if(productDetailText != null) {
            detailText = productDetailText.replaceAll("\n", "<br>");
        }
        return GetProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .petTypeId(petTypeId)
                .petTypeName(petTypeName)
                .productCategoryId(productCategoryId)
                .productCategoryName(productCategoryName)
                .createDate(createDate.format(formatter))
                .productDetailText(detailText)
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .productDetailData(productDtlList)
                .build();
    }

    public GetMasterProductRespDto toMasterProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetMasterProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .petTypeName(petTypeName)
                .productCategoryName(productCategoryName)
                .productThumbnailUrl(productThumbnailUrl)
                .createDate(createDate.format(formatter))
                .build();
    }

}
