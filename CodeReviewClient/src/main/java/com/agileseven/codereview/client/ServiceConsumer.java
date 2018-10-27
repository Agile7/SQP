
package com.agileseven.codereview.client;

import com.agileseven.codereview.client.DTO.ProjectDTO;
import com.agileseven.codereview.client.DTO.UserstoryDTO;
import com.agileseven.codereview.client.DTO.CodeDTO;
import com.agileseven.codereview.client.DTO.ReviewDTO;
import com.agileseven.codereview.client.DTO.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void testNotification() {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/test", Integer.class);
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
      
    public int addReview(String review) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(review, headers);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
        = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/review", entity, Integer.class);

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
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/reviews?userId="+userId+"&projectId="+projectId
                        , ReviewDTO[].class);

        ArrayList<ReviewDTO> reviewList = new ArrayList<>();
 
        reviewList.addAll(Arrays.asList(responseEntity.getBody()));
        
        return reviewList;
    }
  
}
