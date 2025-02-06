/**
 * Service class for managing reviews received via Recenzie nouă page. This class interacts with the ReviewRepository
 * to perform CRUD operations of create, read, update and delete.
 */


package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Review;
import com.example.Luana_Nature.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public void addReview(int rating, String comment) {

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);

        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException(
                "Review-ul nu există!"));
        reviewRepository.deleteById(reviewId);
    }

    public List<Map<String, Object>> getReviewCountByRating() {
        return reviewRepository.countReviewsByRating();
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

}
