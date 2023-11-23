package com.woofnmeow.wnm_project_back.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditProductReqDto {
    private String productName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;

    // 사이즈별 가격
    @JsonProperty("no")
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