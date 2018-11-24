package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ReviewDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Mahmoud AL NAJAR
 */
public class XpCalculator {

    private int userId;
    private List<CodeDTO> listCodes;
    private List<ReviewDTO> listReviews;

    public XpCalculator(int userId, List<CodeDTO> listCodes, List<ReviewDTO> listReviews){
        this.userId = userId;
        this.listCodes = listCodes;
        this.listReviews = listReviews;
    }

    // TODO: calculate XP gain between these dates
    public int calculateXpGainBetweenDates(LocalDate from, LocalDate to){
        return new Random().nextInt((100 - 10) + 1) + 10;
    }

}
