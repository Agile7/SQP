
package com.agileseven.codereview.client;

import com.agileseven.codereview.client.DTO.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mahmoud AL NAJAR
 */
@SpringBootApplication
public class ServiceConsumer implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceConsumer.class).web(false).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
       
    }
    
    private void testmethod(RestTemplate restTemplate) {
        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/pushCode", Integer.class);

        System.out.println(responseEntity.getBody());
    }

   public Integer sendCode(CodeDTO code)
    {
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<CodeDTO> entity = new HttpEntity<>(code, headers);
        final ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/code", code, Integer.class);
        return responseEntity.getBody();
    }
    
    public ArrayList<CodeDTO> getUnreadCodes(int projectId) {
        
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CodeDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/unreviewed/"+projectId, CodeDTO[].class);

        ArrayList<CodeDTO> codeList = new ArrayList<>();
        codeList.addAll(Arrays.asList(responseEntity.getBody()));

        return codeList;
    }

    public ArrayList<UserstoryDTO> getUserStories() {

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserstoryDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/userStories", UserstoryDTO[].class);

        ArrayList<UserstoryDTO> userStoryList = new ArrayList<>();
        userStoryList.addAll(Arrays.asList(responseEntity.getBody()));

        return userStoryList;
    }

    public ArrayList<UserstoryDTO> getUserStories(int projectId) {

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserstoryDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/userStories/" + projectId, UserstoryDTO[].class);

        ArrayList<UserstoryDTO> userStoryList = new ArrayList<>();
        userStoryList.addAll(Arrays.asList(responseEntity.getBody()));

        return userStoryList;
    }

    public CodeDTO getCodeById(int codeId) {
        CodeDTO code = null;

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CodeDTO> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/"+codeId, CodeDTO.class);

        code = responseEntity.getBody();
        return code;
    }
    
    public int approveCode(int codeId) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/review/approve/"+codeId, Integer.class);

        return responseEntity.getBody();
    }
      
//    public int addReview(String review) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(review, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        final ResponseEntity<Integer> responseEntity
//        = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/review", entity, Integer.class);
//
//        return responseEntity.getBody();
//    }

    public int addReview(ReviewDTO review) {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(review, headers);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
                = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/review", review, Integer.class);

        return responseEntity.getBody();
    }
    
    public ArrayList<UserDTO> getUsersList(int projectId){
        
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/project/users/"+projectId, UserDTO[].class);
        
        ArrayList<UserDTO> usersList = new ArrayList<>();
        usersList.addAll(Arrays.asList(responseEntity.getBody()));
        
        return usersList;
    }

    public ArrayList<ProjectDTO> getProjectList(){

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ProjectDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/projects", ProjectDTO[].class);

        ArrayList<ProjectDTO> projectList = new ArrayList<>();
 
        projectList.addAll(Arrays.asList(responseEntity.getBody()));
        
        return projectList;
    }
    
    public ProjectDTO getProjectById(String projectId) {
        ProjectDTO project = null;

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ProjectDTO> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/project/"+projectId, ProjectDTO.class);

        project = responseEntity.getBody();
        return project;
    }
    
    public UserDTO getUserById(int userId) {
        UserDTO user = null;

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserDTO> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/user/"+userId, UserDTO.class);

        user = responseEntity.getBody();
        return user;
    }
    
   
    public ArrayList<ReviewDTO> getReviewList(int userId, int projectId){

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReviewDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/review/reviews?userId="+userId+"&projectId="+projectId
                        , ReviewDTO[].class);

        ArrayList<ReviewDTO> reviewList = new ArrayList<>();
 
        reviewList.addAll(Arrays.asList(responseEntity.getBody()));
        
        return reviewList;
    }
    
    public ArrayList<ReviewAnnotationDTO> getAnnotationsByReviewId(int reviewId){
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReviewAnnotationDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/review/annotations/"+reviewId
                        , ReviewAnnotationDTO[].class);

        ArrayList<ReviewAnnotationDTO> reviewAnnotationList = new ArrayList<>();
 
        reviewAnnotationList.addAll(Arrays.asList(responseEntity.getBody()));
        
        return reviewAnnotationList;
    }

    public ArrayList<RuleDTO> getAllRules(){
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<RuleDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/review/rules", RuleDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }
    
    public int setCodeStatus(int codeId, int status) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/status?codeId="
                        +codeId+"&status="+status
                        , Integer.class);

        return responseEntity.getBody();
    }
    
    
    public LinkedHashMap<String, Integer> getCountCodesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<LinkedHashMap> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codePushed/?startDate="+startDate+"&endDate="+endDate+"&period="+period+"&projectId="+projectId, LinkedHashMap.class);
        
        LinkedHashMap<String, Integer> map = responseEntity.getBody();
       
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        
        
        return map;
    }
    
    public LinkedHashMap<String, Integer> getNumberLinesPushedByTeam(String startDate, String endDate, int period, int projectId) {
        
        System.out.println("csal;dmvsdvsdvsav");
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<LinkedHashMap> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/linesPushed/?startDate="+startDate+"&endDate="+endDate+"&period="+period+"&projectId="+projectId, LinkedHashMap.class);
        
        LinkedHashMap<String, Integer> map = responseEntity.getBody();
        
        
        return map;
    }
    
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeRejected(String startDate, String endDate, int period, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<LinkedHashMap> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/numCodeRejected/?startDate="+startDate+"&endDate="+endDate+"&period="+period+"&userId="+userId, LinkedHashMap.class);
        
        LinkedHashMap<String, Integer> map = responseEntity.getBody();
       
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        
        
        return map;
    }
    
    public LinkedHashMap<String, Integer> getNumberOfPersonalCodeApproved(String startDate, String endDate, int period, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<LinkedHashMap> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/numCodeApproved/?startDate="+startDate+"&endDate="+endDate+"&period="+period+"&userId="+userId, LinkedHashMap.class);
        
        LinkedHashMap<String, Integer> map = responseEntity.getBody();
       
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        
        
        return map;
    }
  
}
