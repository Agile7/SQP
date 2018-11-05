/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver;
import com.agileseven.codereviewserver.DAO.ProjectDAO;
import com.agileseven.codereviewserver.DAO.ProjectDAOImpl;
import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.Utilities.EmailReminderService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * @author vilosh_na
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
            Timer timer = new Timer();
            Calendar cal = Calendar.getInstance(); 
            int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23
            int delayInHour = hour < 23 ? 23 - hour : 24 - (hour - 23);
            TimerTask tt = new TimerTask(){
                public void run(){
                    if(hour == 23){
                        ProjectDAO projectDAO = new ProjectDAOImpl();
                        ArrayList<ProjectDTO> listProject = projectDAO.getProjectList();
                        listProject.forEach((pj) -> {
                            EmailReminderService emailReminderService = new EmailReminderService(pj);
                            emailReminderService.sendReminder(pj);
                        });
                    }
                }
            };
            timer.schedule(tt, delayInHour, 1000*60*60*24);//	delay the task until 11pm, and then run task every 24h
	}
}
    
