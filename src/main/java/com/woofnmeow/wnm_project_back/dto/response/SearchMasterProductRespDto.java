package com.woofnmeow.wnm_project_back.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SearchMasterProductRespDto {
    private int productMstId;
    private String productName;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private String createDate;
    private String no;
    @JsonProperty("XS")
    private String XS;
    @JsonProperty("S")
    private String S;
    @JsonProperty("M")
    private String M;
    @JsonProperty("L")
    private String L;
    @JsonProperty("XL")
    private String XL;
    @JsonProperty("XXL")
    private String XXL;
}
