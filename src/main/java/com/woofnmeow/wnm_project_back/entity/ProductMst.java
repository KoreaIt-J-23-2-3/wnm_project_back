package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetMasterProductRespDto;
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

    public GetMasterProductRespDto toMasterProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetMasterProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .petTypeId(petTypeId)
                .petTypeName(petTypeName)
                .productCategoryId(productCategoryId)
                .productCategoryName(productCategoryName)
                .createDate(createDate.format(formatter))
                .productDetailText(productDetailText.replaceAll("\n", "<br>"))
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailData(productDtlList)
                .build();
    }
}
