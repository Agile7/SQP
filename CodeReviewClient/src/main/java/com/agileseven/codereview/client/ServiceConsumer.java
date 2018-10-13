package com.agileseven.codereview.client;

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
         
         testmethod(restTemplate);
         

    }
    
      private void testmethod(RestTemplate restTemplate) {
     

        final ResponseEntity<Integer> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/CodeReviewer/pushCode", Integer.class);

        System.out.println(responseEntity.getBody());
    }


}
