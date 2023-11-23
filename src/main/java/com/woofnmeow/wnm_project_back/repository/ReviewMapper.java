package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public Integer addReview(Review review);
    public Integer editReview(Review review);
    public Integer deleteReview(int reviewId);
    List<Review> selectReviewsByProductMstId(int productMstId);
    List<Review> selectReviewsByUserId(int userId);
}
