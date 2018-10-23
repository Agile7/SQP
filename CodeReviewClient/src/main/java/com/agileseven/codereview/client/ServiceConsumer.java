package com.agileseven.codereview.client;

import com.agileseven.codereview.client.DTO.ProjectDTO;
import com.agileseven.codereview.client.DTO.UserstoryDTO;
import com.agileseven.codereview.client.DTO.CodeDTO;
import com.agileseven.codereview.client.DTO.UserDTO;

import java.util.ArrayList;

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
         RestTemplate restTemplate = new RestTemplate();
         getUnreadCodes();
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CodeDTO> entity = new HttpEntity<>(code, headers);
        final ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/code/", entity, Integer.class);

        return (Integer)responseEntity.getBody();
    }
    
    public ArrayList<CodeDTO> getUnreadCodes() {
        
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CodeDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/unreviewed", CodeDTO[].class);

        ArrayList<CodeDTO> codeList = new ArrayList<CodeDTO>();
        for (CodeDTO code : responseEntity.getBody()) {
            codeList.add(code);
        }

        return codeList;
    }

    public ArrayList<UserstoryDTO> getUserStories() {

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserstoryDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/userStories", UserstoryDTO[].class);

        ArrayList<UserstoryDTO> userStoryList = new ArrayList<>();
        for (UserstoryDTO userStory : responseEntity.getBody()) {
            userStoryList.add(userStory);
        }

        return userStoryList;
    }

    public ArrayList<UserstoryDTO> getUserStories(int projectId) {

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserstoryDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/userStories/" + projectId, UserstoryDTO[].class);

        ArrayList<UserstoryDTO> userStoryList = new ArrayList<>();
        for (UserstoryDTO userStory : responseEntity.getBody()) {
            userStoryList.add(userStory);
        }

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
        System.out.println(review);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(review, headers);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Integer> responseEntity
        = restTemplate.postForEntity("http://localhost:9000/CodeReviewer/review", entity, Integer.class);

        return responseEntity.getBody();
    }
    
    public ArrayList<UserDTO> getUsersList(){
        
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<UserDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/users", UserDTO[].class);
        
        ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
        for (UserDTO user : responseEntity.getBody()) {
            usersList.add(user);
        }
        
        return usersList;
    }

    public ArrayList<ProjectDTO> getProjectList(){

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ProjectDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/projects", ProjectDTO[].class);

        ArrayList<ProjectDTO> projectList = new ArrayList<ProjectDTO>();
        //System.out.println("----------------");
        for (ProjectDTO project : responseEntity.getBody()) {
            //System.out.println(project.getProjectName());
            projectList.add(project);
        }
        //System.out.println("----------------");
        //System.out.println("responseEntity.getBody().length: " + responseEntity.getBody()[0].getProjectName());
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

}
