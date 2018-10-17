package com.agileseven.codereview.client;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import java.util.ArrayList;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
      
    public ArrayList<CodeDTO> getUnreadCodes() {
        
        RestTemplate restTemplate = new RestTemplate();
     
        ArrayList<CodeDTO> codeList = new ArrayList<CodeDTO>();
        final ResponseEntity<CodeDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/unreviewed", CodeDTO[].class);
       
        
        for (CodeDTO code : responseEntity.getBody()) {
            codeList.add(code);
        }


        return codeList;
    }
    
    public CodeDTO getCodeById(int codeId) {
        
        RestTemplate restTemplate = new RestTemplate();
     
        CodeDTO code = null;
        final ResponseEntity<CodeDTO> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/codes/"+codeId, CodeDTO.class);
       
        
        code = responseEntity.getBody();
        return code;
    }
    
      public int approveCode(int codeId) {
        
        RestTemplate restTemplate = new RestTemplate();
     
        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/approve/"+codeId, Integer.class);
       
        
        return responseEntity.getBody();
     
    }


}
