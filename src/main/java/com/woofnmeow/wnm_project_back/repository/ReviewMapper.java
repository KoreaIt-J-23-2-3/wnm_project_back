package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    // C
    public Integer addReview(Review review);

    // R
    public List<Review> selectReviewsByProductMstId(int productMstId);
    public List<Review> selectReviewsByUserId(int userId);

    // U
    public Integer editReview(Review review);

    // D
    public Integer deleteReview(int reviewId);

}
