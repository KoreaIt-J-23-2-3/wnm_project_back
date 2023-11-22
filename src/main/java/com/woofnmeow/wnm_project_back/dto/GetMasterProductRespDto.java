package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import com.woofnmeow.wnm_project_back.vo.GetProductVo;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class GetMasterProductRespDto {
    private int productMstId;
    private String productName;
    private String petTypeName;
    private String productCategoryName;
    private String productThumbnailUrl;
    private String sizeAndPrice;

}
