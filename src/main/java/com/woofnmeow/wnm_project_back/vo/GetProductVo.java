package com.woofnmeow.wnm_project_back.vo;

import com.woofnmeow.wnm_project_back.dto.GetMasterProductRespDto;
import com.woofnmeow.wnm_project_back.dto.SearchMasterProductRespDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
public class GetProductVo {
    private int productMstId;
    private String productName;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;
    private LocalDateTime createDate;
    private String sizeAndPrice;

    public SearchMasterProductRespDto toRespDto(Map<String, Object> map) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String text = "";
        if(productDetailText != null) {
            text = productDetailText.replaceAll("\n", "<br>");
        }

        String[] sizes = {"no", "XS", "S", "M", "L", "XL", "XXL"};

        for (int i = 0; i < sizes.length; i++) {
            if(map.get(sizes[i]) == null) {
                map.put(sizes[i], "");
            }
        }


        SearchMasterProductRespDto test = SearchMasterProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .productDetailText(text)
                .productThumbnailUrl(productThumbnailUrl)
                .productDetailUrl(productDetailUrl)
                .petTypeId(petTypeId)
                .petTypeName(petTypeName)
                .productCategoryId(productCategoryId)
                .productCategoryName(productCategoryName)
                .createDate(createDate.format(formatter))
                .no((String) map.get("no"))
                .XS((String) map.get("XS"))
                .S((String) map.get("S"))
                .M((String) map.get("M"))
                .L((String) map.get("L"))
                .XL((String) map.get("XL"))
                .XXL((String) map.get("XXL"))
                .build();
//        System.out.println(test);
//        System.out.println("test");
        return test;
    }

    public GetMasterProductRespDto toSearchProductRespDto(Map<String, Object> map) {
        System.out.println("size" + sizeAndPrice);
        GetMasterProductRespDto test = GetMasterProductRespDto.builder()
                .productMstId(productMstId)
                .productName(productName)
                .productThumbnailUrl(productThumbnailUrl)
                .sizeAndPrice(sizeAndPrice)
                .build();
        return test;
    }
}
