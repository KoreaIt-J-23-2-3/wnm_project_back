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
    private int productCategoryId;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private LocalDateTime createDate;

    private PetType petType;
    private Category category;
    private List<ProductDtl> productDtlList;

    public GetProductRespDto toProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(productDetailText != null) {
            productDetailText.replaceAll("\n", "<br>");
        }
        return GetProductRespDto.builder()
                .productName(productName)
                .productDetailText(productDetailText)
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .createDate(createDate.format(formatter))
                .productDtlList(productDtlList)
                .build();
    }

    public GetMasterProductRespDto toMasterProductRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetMasterProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .petTypeName(petType.getPetTypeName())
                .productCategoryName(category.getProductCategoryName())
                .productThumbnailUrl(productThumbnailUrl)
                .createDate(createDate.format(formatter))
                .build();
    }

}
