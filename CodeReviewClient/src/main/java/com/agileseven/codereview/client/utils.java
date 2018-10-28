/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vilosh_na
 */
public class utils {
    
    public static String dateDiff(Date start, Date end){
       

        int minutes =0;
        int hours = 0;
        int days = 0;
        
        long diff = end.getTime() - start.getTime(); 
	days = (int) (diff / (24 * 60 * 60 * 1000));
        
        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end = Calendar.getInstance();
        calendar_start.setTime(start);
        calendar_end.setTime(end);
       
        
        if(calendar_start.get(Calendar.HOUR_OF_DAY) > calendar_end.get(Calendar.HOUR_OF_DAY)){
            hours = 24-calendar_start.get(Calendar.HOUR_OF_DAY) + calendar_end.get(Calendar.HOUR_OF_DAY);

        }
        else{
            hours = calendar_end.get(Calendar.HOUR_OF_DAY) - calendar_start.get(Calendar.HOUR_OF_DAY);
        }
        
        if(calendar_start.get(Calendar.MINUTE) > calendar_end.get(Calendar.MINUTE)){
            minutes = 60 -calendar_start.get(Calendar.MINUTE) + calendar_end.get(Calendar.MINUTE);

        }
        else{
            minutes = calendar_end.get(Calendar.MINUTE) - calendar_start.get(Calendar.MINUTE);
        }
       

        
        return days + " days "+ hours + " Hrs "+ minutes + " mins.";
        
    }
    
        
    public static String convertDatetoString(Date date, String format){
           System.out.println(date);

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String jsonDate = sdf.format(date);
        
        return jsonDate;
 
 }       
    
}
