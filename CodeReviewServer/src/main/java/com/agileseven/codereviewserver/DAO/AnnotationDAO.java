/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import com.agileseven.codereviewserver.DTO.ReviewAnnotationDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jin Xutong
 */
public interface AnnotationDAO {
    public ArrayList<ReviewAnnotationDTO> getAnnotationsByReviewId(int reviewId);
}
