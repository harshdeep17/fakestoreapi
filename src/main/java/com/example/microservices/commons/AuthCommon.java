package com.example.microservices.commons;

import com.example.microservices.dtos.BaseSuccessResponseDto;
import com.example.microservices.dtos.ValidateTokenDto;
import com.example.microservices.dtos.UserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommon {
    private RestTemplate restTemplate;
    public AuthCommon(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){
        ValidateTokenDto validateTokenDto = new ValidateTokenDto();
        validateTokenDto.setToken(tokenValue);
        HttpEntity<ValidateTokenDto> requestEntity = new HttpEntity<>(validateTokenDto);
//        ParameterizedTypeReference<BaseSuccessResponseDto<UserDto>> responseType =
//                new ParameterizedTypeReference<BaseSuccessResponseDto<UserDto>>() {};
//        ResponseEntity<BaseSuccessResponseDto<UserDto>> responseEntity =
//                restTemplate.exchange("http://localhost:8081/users/validate",
//                        HttpMethod.POST,
//                        requestEntity, responseType);
        ResponseEntity<UserDto> responseEntity =
                restTemplate.exchange("http://localhost:8081/users/validate",
                        HttpMethod.POST,
                        requestEntity,
                        UserDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            UserDto responseBody = responseEntity.getBody();
            if (responseBody != null) {
//                return responseBody.getData(); // Assuming getData() returns UserDto
                return responseBody; // Assuming getData() returns UserDto
            }
        }
        return null;
    }
}
