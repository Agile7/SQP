/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client.listeners;

import com.agileseven.codereview.client.views.FrameReview;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author vilosh_na
 */
public class CodeListMouseListener implements MouseListener {
    
    private int codeId;
    private JFrame frame;

    public CodeListMouseListener(int codeId, JFrame frame) {
        this.codeId=codeId;
        this.frame = frame;
    }
    
    
    
    @Override
     public void mouseClicked(MouseEvent e) {
       this.frame.setVisible(false);
       new FrameReview(this.codeId).setVisible(true);
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
