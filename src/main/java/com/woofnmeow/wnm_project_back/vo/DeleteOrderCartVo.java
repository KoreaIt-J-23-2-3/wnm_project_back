package com.woofnmeow.wnm_project_back.vo;

import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class DeleteOrderCartVo {
    private int userId;
    private List<Map<String, Object>> products;
}
