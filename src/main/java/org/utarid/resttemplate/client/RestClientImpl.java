package org.utarid.resttemplate.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.utarid.resttemplate.UserDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestClientImpl implements RestClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public RestClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public void listUsersEntity() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ArrayList> stringResponse = restTemplate.getForEntity("http://localhost:8080/user/get", ArrayList.class);
        System.out.println(stringResponse.getBody());
    }

    @Override
    public void listUsersObject() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<UserDTO> userDTO = restTemplate.getForObject("http://localhost:8080/user/get", List.class);
        System.out.println(userDTO);
    }

    @Override
    public void createUser() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UserDTO newUser = new UserDTO("Mehmet", 35);
        ResponseEntity<String> stringResponse = restTemplate.postForEntity("http://localhost:8080/user/create", newUser, String.class);
        System.out.println(stringResponse.getBody());
    }

    @Override
    public void exchange() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/get";

        HttpHeaders headers = new HttpHeaders();// istegin header bilgisi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        String response = responseEntity.getBody();
        System.out.println(response);
    }

    @Override
    public void execute() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/get";

        ResponseEntity<String> response = restTemplate.execute(
                url,
                HttpMethod.GET,
                request -> {
                    // request header ve body degeri degistirilir
                    request.getHeaders().add("testKey", "testValue");
                },
                response1 -> {
                    // response degeri okunur
                    String responseBody = null;
                    try {
                        InputStream inputStream = response1.getBody();
                        responseBody = new String(inputStream.readAllBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return ResponseEntity.status(response1.getStatusCode())
                            .headers(response1.getHeaders())
                            .body(responseBody);
                }
        );

        System.out.println(response.getBody());
    }
}
