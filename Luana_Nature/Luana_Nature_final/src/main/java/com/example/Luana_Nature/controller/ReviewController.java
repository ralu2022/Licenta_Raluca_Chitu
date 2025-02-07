/*
 * Controller used to handle CRUD (create, read, update, delete) operations upon Review entity.
 *
 *
 * */


package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.Review;
import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.service.EmailService;
import com.example.Luana_Nature.service.ReviewService;
import com.example.Luana_Nature.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    /* Viualizare review-uri */

    @GetMapping("/reviewsadmin")
    public String allReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "reviewsadmin";
    }

    /* Adăugare review */
    @GetMapping("/review")
    public String reviewPage(Model model) {
        model.addAttribute("review", new Review());
        return "reviewformuser";
    }

    @PostMapping("/addReview")
    public String addReview(@RequestParam int rating,
                            @RequestParam String comment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilizatorul nu a fost găsit!");
        }

        reviewService.addReview(rating, comment, user.getUserId());

        return "redirect:/reviews/review";
    }

    /* Ștergere review */

    @DeleteMapping("/deleteReview")
    public String deleteReview(@RequestParam Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            reviewService.deleteReview(reviewId);
        }

        return "redirect:/reviews/reviewsadmin";
    }

    /* Statistică review-uri */
    @GetMapping("/countReviewsByRating")
    @ResponseBody
    public List<Map<String, Object>> getReviewCountByRating() {
        return reviewService.getReviewCountByRating();
    }

    @GetMapping("reviewChart")
    public String reviewChart(Model model) {
        List<Map<String, Object>> reviewData = reviewService.getReviewCountByRating();
        model.addAttribute("reviewData", reviewData);
        return "redirect:/mainpageuser";
    }
}
