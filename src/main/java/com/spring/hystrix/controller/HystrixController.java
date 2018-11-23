package com.spring.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HystrixController {

    RestTemplate restTemplate = new RestTemplate();

    //Get the weather details by using openweathermap api
    //triggering Faults or success.

    @HystrixCommand(fallbackMethod = "fallBackGetWeather", commandKey = "fallback", groupKey = "fallback")
    @GetMapping(value = "/weather")
    public Object getWeather(){
        return restTemplate.getForObject("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22", String.class);
    }


    public List<Object> fallBackGetWeather(){

        System.out.println("Call the fallback method");
        return Collections.emptyList();
    }
}
