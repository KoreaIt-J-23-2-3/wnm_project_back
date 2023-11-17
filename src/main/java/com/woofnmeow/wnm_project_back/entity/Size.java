package com.woofnmeow.wnm_project_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Size {
    private int sizeId;
    private String sizeName;
}
