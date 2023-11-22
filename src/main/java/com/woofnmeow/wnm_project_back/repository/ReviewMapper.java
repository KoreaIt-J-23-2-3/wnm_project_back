package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    public Integer addReview(Review review);
    public Integer editReview(Review review);
    public Integer deleteReview(int reviewId);
}
