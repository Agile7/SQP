/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client.views;

import com.agileseven.codereview.client.DTO.UserDTO;
import com.agileseven.codereview.client.ServiceConsumer;
import com.agileseven.codereview.client.Session;
import com.agileseven.codereview.client.Utils;
import com.agileseven.codereview.client.ProjectReviewsResponse;
import com.agileseven.codereview.client.UserDTOWithXPGains;
import com.agileseven.codereview.client.UserXpSingleInterval;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author vilosh_na
 */
public class FrameDashBoard extends javax.swing.JFrame {

    /**
     * Creates new form FrameDashBoard
     */

    private JDatePickerImpl datePickerFrom;
    private JDatePickerImpl datePickerTo;
    private ServiceConsumer service = new ServiceConsumer();
    private ChartPanel codePushChartPanel;
    private ChartPanel linePushChartPanel;
    private ChartPanel codePushByIndividualChartPanel;
    private ChartPanel linePushByIndividualChartPanel;
    private ChartPanel stackedBarIndividualRejectedApprovedPanel;
    private ChartPanel stackedBarTeamRejectedApprovedPanel;
    private ChartPanel codesRejectedByTeamAreaPanel;
    private ChartPanel gamificationChartPanel;

    private JDatePickerImpl mnDatePickerFrom;
    private JDatePickerImpl mnDatePickerTo;

    private ChartPanel annotationChartPanel;

    private JDatePickerImpl datePickerFromPersonal;
    private JDatePickerImpl datePickerToPersonal;


    public FrameDashBoard() {
        initComponents();
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        jLabel2.setText("");
        jLabel4.setText(Session.currentUser.getFirstName() + " " + Session.currentUser.getLastName());

        if(Session.currentUser.getPositionId() !=2){
            jComboBox1.setVisible(false);
        }
        Properties p = new Properties();
	p.put("text.today", "today");
	p.put("text.month", "month");
	p.put("text.year", "year");

	UtilDateModel model1 = new UtilDateModel();
	JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
        datePanel1.setBackground(Color.WHITE);
	datePickerFrom = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
        datePickerFrom.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));

        UtilDateModel model2 = new UtilDateModel();
	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
        datePanel2.setBackground(Color.WHITE);
        datePickerTo = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        datePickerTo.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));

        jPanel7.setLayout(new FlowLayout());
        jPanel7.add(datePickerFrom);

        jPanel8.setLayout(new FlowLayout());
        jPanel8.add(datePickerTo);

        //jPanel2.setSize(100, 200);
        jButton1.setVisible(false);
        jButton3.setVisible(false);
        jPanel2.setLayout(new FlowLayout());
        jPanel2.setBorder(null);

        mnPanel.setLayout(new FlowLayout());

        /*Personal Dashboard*//*Personal Dashboard*//*Personal Dashboard*/
//      Properties p3 = new Properties();
//	p3.put("text.today", "today");
//	p3.put("text.month", "month");
//	p3.put("text.year", "year");

//	UtilDateModel model3 = new UtilDateModel();
//	JDatePanelImpl datePanel3 = new JDatePanelImpl(model3, p);
//      datePanel3.setBackground(Color.WHITE);
//      datePickerFromPersonal = new JDatePickerImpl(datePanel3, new DateComponentFormatter());
//      datePickerFromPersonal.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        datePickerFromPersonal = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
        datePickerFromPersonal.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        jPanel14.setLayout(new FlowLayout());
        jPanel14.add(datePickerFromPersonal);

//      UtilDateModel model4 = new UtilDateModel();
//	JDatePanelImpl datePanel4 = new JDatePanelImpl(model4, p);
//      datePanel4.setBackground(Color.WHITE);
//      datePickerToPersonal = new JDatePickerImpl(datePanel4, new DateComponentFormatter());
//      datePickerToPersonal.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        datePickerToPersonal = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        datePickerToPersonal.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        jPanel15.setLayout(new FlowLayout());
        jPanel15.add(datePickerToPersonal);

        mnDatePickerFrom = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
        mnDatePickerFrom.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        mnPanelFrom.setLayout(new FlowLayout());
        mnPanelFrom.add(mnDatePickerFrom);

        mnDatePickerTo = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        mnDatePickerTo.getJFormattedTextField().setPreferredSize(new Dimension(200, 40));
        mnPanelTo.setLayout(new FlowLayout());
        mnPanelTo.add(mnDatePickerTo);

        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jPanel17.setLayout(new FlowLayout());
        jPanel17.setBorder(null);
        jPanel18.setLayout(new FlowLayout());
        jPanel18.setBorder(null);
        jPanel6.setLayout(new FlowLayout());
        jPanel6.setBorder(null);
        jPanel11.setLayout(new FlowLayout());
        jPanel11.setBorder(null);
        jPanel19.setLayout(new FlowLayout());
        jPanel19.setBorder(null);
        jPanel10.setBorder(null);
        jPanel9.setBorder(null);

        jPanel19.setVisible(false);
        lbTotalAnnotation.setVisible(false);
        lbTotalLineReviewed.setVisible(false);
        lbPercentage.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        ArrayList<UserDTO> userList = service.getUsersList(Session.currentProject.getProjectId());

        for(UserDTO user : userList){
            jComboBox1.addItem(user.getFirstName() + " " + user.getLastName());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lbTotalAnnotation = new javax.swing.JLabel();
        lbTotalLineReviewed = new javax.swing.JLabel();
        lbPercentage = new javax.swing.JLabel();
        lbValueTotalAnnotation = new javax.swing.JLabel();
        lbValueTotalLineReviewed = new javax.swing.JLabel();
        lbValuePercentage = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        totalTimeReviewed = new javax.swing.JLabel();
        maxTimeReviewed = new javax.swing.JLabel();
        minTimeReviewed = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        mnPanelFrom = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        mnPanelTo = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        mnMenuPeriod = new javax.swing.JComboBox<>();
        mnBtnOk = new javax.swing.JButton();
        mnPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(2634, 918));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1600, 850));

        jScrollPane1.setBackground(new java.awt.Color(248, 238, 231));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setDoubleBuffered(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("From: ");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("To:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Period:");

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 204, 0));
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbTotalAnnotation.setText("Number of Annotations in Total:");

        lbTotalLineReviewed.setText("Number of Lines reviewed in Total:");

        lbPercentage.setText("Percentage of Annotation per line:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTotalLineReviewed)
                            .addComponent(lbTotalAnnotation)
                            .addComponent(lbPercentage))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbValueTotalAnnotation)
                            .addComponent(lbValueTotalLineReviewed)
                            .addComponent(lbValuePercentage)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalAnnotation)
                    .addComponent(lbValueTotalAnnotation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalLineReviewed)
                    .addComponent(lbValueTotalLineReviewed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPercentage)
                    .addComponent(lbValuePercentage))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Total Review Time:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("MIN: ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("MAX:");

        totalTimeReviewed.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        maxTimeReviewed.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        minTimeReviewed.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minTimeReviewed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalTimeReviewed, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxTimeReviewed, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(totalTimeReviewed, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(maxTimeReviewed, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minTimeReviewed, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("Number of Lines");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 102, 255));
        jButton3.setText("Number of Codes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane1.setViewportView(jPanel4);

        jTabbedPane1.addTab("               Team   Dashboard               ", jScrollPane1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        mnPanelFrom.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mnPanelFromLayout = new javax.swing.GroupLayout(mnPanelFrom);
        mnPanelFrom.setLayout(mnPanelFromLayout);
        mnPanelFromLayout.setHorizontalGroup(
            mnPanelFromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        mnPanelFromLayout.setVerticalGroup(
            mnPanelFromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("From: ");

        mnPanelTo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mnPanelToLayout = new javax.swing.GroupLayout(mnPanelTo);
        mnPanelTo.setLayout(mnPanelToLayout);
        mnPanelToLayout.setHorizontalGroup(
            mnPanelToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        mnPanelToLayout.setVerticalGroup(
            mnPanelToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("To:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Period:");

        mnMenuPeriod.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mnMenuPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly" }));
        mnMenuPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMenuPeriodActionPerformed(evt);
            }
        });

        mnBtnOk.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mnBtnOk.setForeground(new java.awt.Color(51, 204, 0));
        mnBtnOk.setText("OK");
        mnBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBtnOkActionPerformed(evt);
            }
        });

        mnPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mnPanelLayout = new javax.swing.GroupLayout(mnPanel);
        mnPanel.setLayout(mnPanelLayout);
        mnPanelLayout.setHorizontalGroup(
            mnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        mnPanelLayout.setVerticalGroup(
            mnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mnPanelFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mnPanelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mnMenuPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(mnBtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(mnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel20))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(mnMenuPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mnBtnOk)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel19))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(mnPanelFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mnPanelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(mnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(815, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel5);

        jTabbedPane1.addTab("               Team  Performance             ", jScrollPane4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("From:");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(265, 48));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("To:");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(265, 48));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Period:");

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly" }));

        jButton4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 204, 0));
        jButton4.setText("Ok");
        jButton4.setMaximumSize(new java.awt.Dimension(59, 31));
        jButton4.setMinimumSize(new java.awt.Dimension(59, 31));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(1400, 1300));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setPreferredSize(new java.awt.Dimension(706, 520));
        jPanel17.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setPreferredSize(new java.awt.Dimension(706, 520));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jButton5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 102, 255));
        jButton5.setText("Number of Codes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 102, 255));
        jButton6.setText("Number of Lines");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(722, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(305, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox3)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        jTabbedPane1.addTab("               Personal   Dashboard               ", jScrollPane2);

        jPanel12.setBackground(new java.awt.Color(73, 39, 74));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dashboards");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel4");

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6BackButtonClicked(evt);
            }
        });

        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel2)
                .addGap(462, 462, 462)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(284, 284, 284))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel6))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1010, 1010, 1010))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(629, 629, 629)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(916, 916, 916))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6BackButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6BackButtonClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new FrameHomePage().setVisible(true);
    }//GEN-LAST:event_jLabel6BackButtonClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        //////////////
        jLabel14.setVisible(true);
        jLabel15.setVisible(true);
        jLabel16.setVisible(true);
        String start = datePickerFrom.getModel().getYear() + "-" + (datePickerFrom.getModel().getMonth() + 1) + "-" + datePickerFrom.getModel().getDay();
        String end = datePickerTo.getModel().getYear() + "-" + (datePickerTo.getModel().getMonth() + 1) + "-" + datePickerTo.getModel().getDay();

        ServiceConsumer consumer = new ServiceConsumer();
        ProjectReviewsResponse reviewsResponse = consumer.getProjectReviewsStatistics(Session.currentProject.getProjectId(), start, end);

        int[] min = Utils.splitToComponentTimes(BigDecimal.valueOf(reviewsResponse.getMin()));
        int[] max = Utils.splitToComponentTimes(BigDecimal.valueOf(reviewsResponse.getMax()));
        int[] total = Utils.splitToComponentTimes(BigDecimal.valueOf(reviewsResponse.getTotal()));

        String minText = min[0] + "h " + min[1] +"m " + min[2] + "s";
        String maxText = max[0] + "h " + max[1] +"m " + max[2] + "s";
        String totalText = total[0] + "h " + total[1] +"m " + total[2] + "s";

        totalTimeReviewed.setText(totalText);
        maxTimeReviewed.setText(maxText);
        minTimeReviewed.setText(minText);



        //////////////

        String dateFrom = datePickerFrom.getJFormattedTextField().getText();
        String dateTo = datePickerTo.getJFormattedTextField().getText();
        int period = jComboBox2.getSelectedIndex();

        Date from = Utils.convertStringToDate(dateFrom, "dd-MMM-yyyy");
        Date to = Utils.convertStringToDate(dateTo, "dd-MMM-yyyy");

        if(from.compareTo(to) > 0){

            JOptionPane.showMessageDialog(this,"The end date should be greater than start date!",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        else{

            //Code to generate graphs
            jPanel2.removeAll();
            jPanel6.removeAll();
            jPanel11.removeAll();
            jPanel19.setVisible(true);
            jPanel19.removeAll();
            displayCodesPushedLineGraph(dateFrom,dateTo,period,Session.currentProject.getProjectId());
            displayLinesPushedLineGraph(dateFrom,dateTo,period,Session.currentProject.getProjectId());
            displayNumOfRejectedApprovedCodeOfTeam(dateFrom, dateTo, period, Session.currentProject.getProjectId());
            displayRejectedCodesPercentAreaGraph(dateFrom, dateTo, period, Session.currentProject.getProjectId());
            // PIECHART FOR ANNOTATION
            displayAnnotationPieChart(dateFrom ,dateTo, Session.currentProject.getProjectId());
            jPanel19.add(annotationChartPanel);
            jPanel2.add(codePushChartPanel);
            jPanel2.add(linePushChartPanel);
            jPanel6.add(stackedBarTeamRejectedApprovedPanel);
            jPanel11.add(codesRejectedByTeamAreaPanel);
            linePushChartPanel.setVisible(false);
            jButton1.setVisible(true);
            jButton3.setVisible(true);

            lbTotalAnnotation.setVisible(true);
            lbTotalLineReviewed.setVisible(true);
            lbPercentage.setVisible(true);
            // TEXT FOR ANNOTATION
            Integer nbOfAnnotation = consumer.getNumberOfAnnotation(dateFrom, dateTo, Session.currentProject.getProjectId());
            Integer nbOfLineReviewed = consumer.getNumberOfLineReviewed(dateFrom, dateTo, Session.currentProject.getProjectId());
            lbValueTotalAnnotation.setText(nbOfAnnotation.toString());
            lbValueTotalLineReviewed.setText(nbOfLineReviewed.toString());
            float percentageAnnotationPerLine = (float) ((float)nbOfAnnotation*100.0 / (float)nbOfLineReviewed);
            String numberAsString = String.format ("%.2f", percentageAnnotationPerLine) + " %";
            lbValuePercentage.setText(numberAsString);

            //annotationChartPanel.setVisible(true);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void displayAnnotationPieChart(String dateFrom, String dateTo, int projectId){
        LinkedHashMap<String, Integer> mapRule = service.getNumberOfRuleAnnotation(dateFrom, dateTo, projectId);
        int totalNbOfRule = 0;
        DefaultPieDataset ds = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : mapRule.entrySet()) {
            totalNbOfRule += entry.getValue();
        }
        System.out.println("totalNbOfRule: " + totalNbOfRule);
        for (Map.Entry<String, Integer> entry : mapRule.entrySet()) {
            ds.setValue(entry.getKey(), entry.getValue()*100 / totalNbOfRule);
        }
        JFreeChart pieChart = ChartFactory.createPieChart3D(
            "Percentage of Rules used in Annotation",
            ds,   // data
            true,  // include legend
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        annotationChartPanel = new ChartPanel(pieChart);
        annotationChartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
    }


    private void displayCodesPushedLineGraph(String dateFrom, String dateTo, int period, int projectId){

        LinkedHashMap<String, Integer> map = service.getCountCodesPushedByTeam(dateFrom,dateTo,period,projectId);


        JFreeChart lineChart = ChartFactory.createLineChart(
         "Number of Codes Pushed",
         "Date","Number of Codes",
         createDataset(map,period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.setBackgroundPaint( Color.WHITE );

        codePushChartPanel = new ChartPanel( lineChart );
        codePushChartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );



    }

    private void displayCodesPushedByIndividualLineGraph(String dateFrom, String dateTo, int period, int userId){

        LinkedHashMap<String, Integer> map = service.getCountCodesPushedByIndividual(dateFrom,dateTo,period,userId);


        JFreeChart lineChart = ChartFactory.createLineChart(
         "Number of Codes Pushed",
         "Date","Number of Codes",
         createDataset(map,period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.setBackgroundPaint( Color.WHITE );

        codePushByIndividualChartPanel = new ChartPanel( lineChart );
        codePushByIndividualChartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );

    }

    private void displayLinesPushedLineGraph(String dateFrom, String dateTo, int period, int projectId){
        System.out.println("here");

        LinkedHashMap<String, Integer> map = service.getNumberLinesPushedByTeam(dateFrom,dateTo,period,projectId);


        JFreeChart lineChart = ChartFactory.createLineChart(
         "Total Lines Pushed",
         "Date","Number of Lines",
         createDataset(map, period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(11,102,35));
        plot.setBackgroundPaint( Color.WHITE );

        linePushChartPanel = new ChartPanel( lineChart );
        linePushChartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );

    }

    private void displayLinesPushedByIndividualLineGraph(String dateFrom, String dateTo, int period, int userId){
        System.out.println("here");

        LinkedHashMap<String, Integer> map = service.getNumberLinesPushedByIndividual(dateFrom,dateTo,period,userId);


        JFreeChart lineChart = ChartFactory.createLineChart(
         "Total Lines Pushed",
         "Date","Number of Lines",
         createDataset(map, period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(11,102,35));
        plot.setBackgroundPaint( Color.WHITE );

        linePushByIndividualChartPanel = new ChartPanel( lineChart );
        linePushByIndividualChartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );

    }

    private void displayNumOfRejectedApprovedCode(String dateFrom, String dateTo, int period, int userId){

        LinkedHashMap<String, Integer> approved = service.getNumberOfPersonalCodeApproved(dateFrom, dateTo, period, userId);
        LinkedHashMap<String, Integer> rejected = service.getNumberOfPersonalCodeRejected(dateFrom, dateTo, period, userId);

        JFreeChart stackBarChart = ChartFactory.createStackedBarChart(
         "Approved/Rejected Codes",
         "Date","Number of Codes",
         createStackedBarChartDataset(approved, rejected, period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) stackBarChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(11,102,35));
        plot.getRenderer().setSeriesPaint(1, Color.ORANGE);
        plot.setBackgroundPaint( Color.WHITE );


        stackedBarIndividualRejectedApprovedPanel = new ChartPanel( stackBarChart );
        stackedBarIndividualRejectedApprovedPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );

    }
     private void displayRejectedCodesPercentAreaGraph(String dateFrom, String dateTo, int period, int projectId) {
        System.out.println("here");

        LinkedHashMap<String, Integer> rejected = service.getNumberOfTeamCodeRejected(dateFrom, dateTo, period, projectId);
        LinkedHashMap<String, Integer> pushed = service.getCountCodesPushedByTeam(dateFrom, dateTo, period, projectId);
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for(HashMap.Entry<String, Integer> rejectedEntry : rejected.entrySet()){
            System.out.println("reject\n"+ rejectedEntry);
            for(HashMap.Entry<String, Integer> pushedEntry : pushed.entrySet()){
                //String periodPush = ""+pushedEntry.getKey();
                //String periodReject = ""+rejectedEntry.getKey();
                //System.out.println("push\n"+pushedEntry);
                if(pushedEntry.getKey() == rejectedEntry.getKey()){
                    Integer percentage = rejectedEntry.getValue() * 100 / pushedEntry.getValue() ;
                    //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeee111\n"+percentage);
                    map.put(pushedEntry.getKey(), percentage);
                }
            }
        }

        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeee\n"+map);
        JFreeChart chart = ChartFactory.createAreaChart(
        "Codes Rejected",
        "Date",
        "Percentage(%)",
        createDataset(map, period),PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(11, 102, 35));
        plot.setBackgroundPaint(Color.WHITE);

        codesRejectedByTeamAreaPanel = new ChartPanel(chart);
        codesRejectedByTeamAreaPanel.setPreferredSize(new java.awt.Dimension(1100, 500));

    }
    private void displayNumOfRejectedApprovedCodeOfTeam(String dateFrom, String dateTo, int period, int projectId){

        LinkedHashMap<String, Integer> approved = service.getNumberOfTeamCodeApproved(dateFrom, dateTo, period, projectId);
        LinkedHashMap<String, Integer> rejected = service.getNumberOfTeamCodeRejected(dateFrom, dateTo, period, projectId);

        JFreeChart stackBarChart = ChartFactory.createStackedBarChart(
         "Approved/Rejected Codes",
         "Date","Number of Codes",
         createStackedBarChartDataset(approved, rejected, period),
         PlotOrientation.VERTICAL,
         true,true,false);

        CategoryPlot plot = (CategoryPlot) stackBarChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(11,102,35));
        plot.getRenderer().setSeriesPaint(1, Color.ORANGE);
        plot.setBackgroundPaint( Color.WHITE );


        stackedBarTeamRejectedApprovedPanel = new ChartPanel( stackBarChart );
        stackedBarTeamRejectedApprovedPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );

    }


    private DefaultCategoryDataset createStackedBarChartDataset(LinkedHashMap<String, Integer> approved, LinkedHashMap<String, Integer> rejected, int period) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");



        for (Map.Entry<String, Integer> entry : approved.entrySet()) {

           if(period ==1){
               cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(entry.getKey().substring(0, 2)));
               System.out.println(entry.getKey());
               dataset.addValue( entry.getValue() , "Approved" , formatter.format(cal.getTime()));
           }
           else{
               dataset.addValue( entry.getValue() , "Approved" , entry.getKey());
           }
        }

        for (Map.Entry<String, Integer> entry : rejected.entrySet()) {

           if(period ==1){
               cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(entry.getKey().substring(0, 2)));
               System.out.println(entry.getKey());
               dataset.addValue( entry.getValue() , "rejected" , formatter.format(cal.getTime()));
           }
           else{
               dataset.addValue( entry.getValue() , "rejected" , entry.getKey());
           }
        }

        return dataset;
    }

     private DefaultCategoryDataset createDataset(LinkedHashMap<String, Integer> map, int period) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


       for (Map.Entry<String, Integer> entry : map.entrySet()) {

           if(period ==1){
               cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(entry.getKey().substring(0, 2)));
               System.out.println(entry.getKey());
               dataset.addValue( entry.getValue() , "Codes" , formatter.format(cal.getTime()));
           }
           else{
               dataset.addValue( entry.getValue() , "Codes" , entry.getKey());
           }

        }

      return dataset;
   }
     /*
     private DefaultCategoryDataset createAreaChartDataset(LinkedHashMap<String, Double> map, int period) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


       for (Map.Entry<String, Double> entry : map.entrySet()) {

           if(period ==1){
               cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(entry.getKey().substring(0, 2)));
               System.out.println(entry.getKey());
               dataset.addValue( entry.getValue() , "Codes" , formatter.format(cal.getTime()));
           }
           else{
               dataset.addValue( entry.getValue() , "Codes" , entry.getKey());
           }

        }

      return dataset;
   }
     */



    private void BackClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new FrameHomePage().setVisible(true);
    }//GEN-LAST:event_BackClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        codePushChartPanel.setVisible(false);
        linePushChartPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        codePushChartPanel.setVisible(true);
        linePushChartPanel.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        codePushByIndividualChartPanel.setVisible(false);
        linePushByIndividualChartPanel.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        codePushByIndividualChartPanel.setVisible(true);
        linePushByIndividualChartPanel.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String dateFrom = datePickerFrom.getJFormattedTextField().getText();
        String dateTo = datePickerTo.getJFormattedTextField().getText();
        int period = jComboBox3.getSelectedIndex();
        int userNumber = jComboBox1.getSelectedIndex();
        ArrayList<UserDTO> userList = service.getUsersList(Session.currentProject.getProjectId());

        Date from = Utils.convertStringToDate(dateFrom, "dd-MMM-yyyy");
        Date to = Utils.convertStringToDate(dateTo, "dd-MMM-yyyy");

        if(from.compareTo(to) > 0){

            JOptionPane.showMessageDialog(this,"The end date should be greater than start date!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        else{
            //Code to generate graphs
            jPanel17.removeAll();
            jPanel18.removeAll();

            if(Session.currentUser.getPositionId() == 2){
                displayCodesPushedByIndividualLineGraph(dateFrom,dateTo,period,userList.get(userNumber).getUserId());
                displayLinesPushedByIndividualLineGraph(dateFrom,dateTo,period,userList.get(userNumber).getUserId());
                displayNumOfRejectedApprovedCode(dateFrom,dateTo,period,userList.get(userNumber).getUserId());
            }else{
                displayCodesPushedByIndividualLineGraph(dateFrom,dateTo,period,Session.currentUser.getUserId());
                displayLinesPushedByIndividualLineGraph(dateFrom,dateTo,period,Session.currentUser.getUserId());
                displayNumOfRejectedApprovedCode(dateFrom,dateTo,period,Session.currentUser.getUserId());
            }
            jPanel17.add(codePushByIndividualChartPanel);
            jPanel17.add(linePushByIndividualChartPanel);
            jPanel18.add(stackedBarIndividualRejectedApprovedPanel);
            linePushByIndividualChartPanel.setVisible(false);
            //stackedBarIndividualRejectedApprovedPanel.setVisible(true);
            jButton5.setVisible(true);
            jButton6.setVisible(true);

//            jPanel18.removeAll();
//            displayNumOfRejectedApprovedCode(dateFrom,dateTo,period,Session.currentUser.getUserId());
//            jPanel18.add(stackedBarIndividualRejectedApprovedPanel);
//            stackedBarIndividualRejectedApprovedPanel.setVisible(true);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void mnMenuPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMenuPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnMenuPeriodActionPerformed

    private void mnBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBtnOkActionPerformed
        String dateFrom = datePickerFrom.getJFormattedTextField().getText();
        String dateTo = datePickerTo.getJFormattedTextField().getText();
        int period = mnMenuPeriod.getSelectedIndex();

        Date from = Utils.convertStringToDate(dateFrom, "dd-MMM-yyyy");
        Date to = Utils.convertStringToDate(dateTo, "dd-MMM-yyyy");

        if(from.compareTo(to) > 0){

            JOptionPane.showMessageDialog(this,"The end date should be greater than start date!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {

            mnPanel.removeAll();
            List<UserDTOWithXPGains> userXpGainsList = service.getUserXpGainsList(Session.currentProject.getProjectId(), dateFrom, dateTo, period);
            prepareGamificationChartPanel(userXpGainsList, period);
            mnPanel.add(gamificationChartPanel);
            mnPanel.updateUI();
        }
    }//GEN-LAST:event_mnBtnOkActionPerformed

    private void prepareGamificationChartPanel(List<UserDTOWithXPGains> userXpGainsList, int period) {

        String categoryLabel;
        if (period == 2) categoryLabel = "Months";
        else if (period == 1) categoryLabel = "Weeks";
        else categoryLabel = "Days";

        JFreeChart chart = ChartFactory.createLineChart(
                "Members' XP gain",
                categoryLabel,"Xp points gained",
                createGamficationDataset(userXpGainsList),
                PlotOrientation.VERTICAL,
                true,true,false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint( Color.WHITE );
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        gamificationChartPanel = new ChartPanel( chart );
        gamificationChartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );
    }

    private DefaultCategoryDataset createGamficationDataset(List<UserDTOWithXPGains> userXpGainsList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (UserDTOWithXPGains user : userXpGainsList){
            dataset.addValue(0, user.getFirstName() + " " + user.getLastName(), user.getXpGainIntervalsList().get(0).getDateFrom());
            for (UserXpSingleInterval interval : user.getXpGainIntervalsList()){
                dataset.addValue(interval.getXpGained(), user.getFirstName() + " " + user.getLastName(), interval.getDateTo());
            }
        }

        return dataset;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbPercentage;
    private javax.swing.JLabel lbTotalAnnotation;
    private javax.swing.JLabel lbTotalLineReviewed;
    private javax.swing.JLabel lbValuePercentage;
    private javax.swing.JLabel lbValueTotalAnnotation;
    private javax.swing.JLabel lbValueTotalLineReviewed;
    private javax.swing.JLabel maxTimeReviewed;
    private javax.swing.JLabel minTimeReviewed;
    private javax.swing.JLabel totalTimeReviewed;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton mnBtnOk;
    private javax.swing.JComboBox<String> mnMenuPeriod;
    private javax.swing.JPanel mnPanel;
    private javax.swing.JPanel mnPanelFrom;
    private javax.swing.JPanel mnPanelTo;
    // End of variables declaration//GEN-END:variables
}
