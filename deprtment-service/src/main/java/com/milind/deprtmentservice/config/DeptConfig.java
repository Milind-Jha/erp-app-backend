package com.milind.deprtmentservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableFeignClients
public class DeptConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(data -> data.getSource()!=null);
        return modelMapper;
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }


}
