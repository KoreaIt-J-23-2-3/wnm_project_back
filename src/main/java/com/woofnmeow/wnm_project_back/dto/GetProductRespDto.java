package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Category;
import com.woofnmeow.wnm_project_back.entity.PetType;
import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetProductRespDto {
    private String productName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private String createDate;

    private List<ProductDtl> productDtlList;
}
