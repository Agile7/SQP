/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereview.client;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vilosh_na
 */
public class Utils {
    
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
    
      public static Date convertStringToDate(String dateString, String format){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date;
    }


    public static String addLineNumbersToCodeString(String code){
        int count = 1;
        String[] lines = code.split("\\r?\\n");
        StringBuilder newCodeText = new StringBuilder();
        for (String line : lines) {
            newCodeText.append(count).append(line).append("\n");
            count++;
        }
        return newCodeText.toString();
    }

    public static int[] splitToComponentTimes(BigDecimal biggy)
    {
        long longVal = biggy.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        return new int[]{hours , mins , secs};
    }

}
