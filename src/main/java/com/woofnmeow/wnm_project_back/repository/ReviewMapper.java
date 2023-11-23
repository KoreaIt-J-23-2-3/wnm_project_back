package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    // C
    public Integer addReview(Review review);

    // R


    // U
    public Integer editReview(Review review);

    // D
    public Integer deleteReview(int reviewId);
}
