package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DAO.AnnotationDAO;
import com.agileseven.codereviewserver.DAO.AnnotationDAOImpl;
import com.agileseven.codereviewserver.DAO.ReviewDAO;
import com.agileseven.codereviewserver.DAO.ReviewDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mahmoud AL NAJAR
 */
public class XpCalculator {

    final static AnnotationDAO annotationDAO = new AnnotationDAOImpl();
    final static ReviewDAO reviewDAO = new ReviewDAOImpl();

    private int userId;
    private List<CodeDTO> listCodes;
    private List<ReviewDTO> listReviews;
    List<ReviewDTO> reviewsOfUserCodes;

    public XpCalculator(int userId, List<CodeDTO> listCodes, List<ReviewDTO> listReviews){
        this.userId = userId;
        this.listCodes = listCodes;
        this.listReviews = listReviews;
        this.reviewsOfUserCodes = reviewDAO.getReviewsOfUserCodes(this.userId);
    }

    /***
     * each review: +5
     * each annotation: +1
     * each peace of code:
     *      if ($version == 1){
     *          $xp_difference += ceil($number_of_lines / 10);
     *      } else {
     *          $xp_difference += (ceil($number_of_lines / 20) - ($version - 2));
     *      }
     */
    public int calculateXpGainBetweenDates(LocalDate from, LocalDate to){

        int newXp = 0;

        List<ReviewDTO> listReviewsDuringRange = filterReviewsList(listReviews, from, to);;
        for (ReviewDTO review : listReviewsDuringRange){
            newXp += 5;
            if (review.getAnnotationList() == null) {
                review.setAnnotationList(annotationDAO.getAnnotationsByReviewId(review.getReviewId()));
            }
            newXp += review.getAnnotationList().size();
        }

        List<ReviewDTO> codeReviews = filterReviewsList(reviewsOfUserCodes, from, to);
        for (ReviewDTO review : codeReviews){
            if(review.getApproved() == 1) {
                if (review.getCode().getVersion() == 1) newXp += Math.ceil(review.getCode().getNumLines() / 10.0);
                else newXp += (Math.ceil(review.getCode().getNumLines() / 20.0)) - (review.getCode().getVersion() - 2);
            }
        }

        return newXp;
    }

    private List<ReviewDTO> filterReviewsList(List<ReviewDTO> reviews, LocalDate from, LocalDate to){
        return reviews.stream()
                .filter(reviewDTO -> !reviewDTO.getSubmitDate().toLocalDate().isBefore(from) && !reviewDTO.getSubmitDate().toLocalDate().isAfter(to))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XpCalculator that = (XpCalculator) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
