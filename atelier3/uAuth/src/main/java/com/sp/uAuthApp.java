package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
<<<<<<<< HEAD:atelier3/uAuth/src/main/java/com/sp/uAuthApp.java
@EnableDiscoveryClient
public class uAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(uAuthApp.class, args);
========
public class RoomsMicroService {

	public static void main(String[] args) {
		SpringApplication.run(RoomsMicroService.class, args);
>>>>>>>> f7cb743 (feat: micro service room):atelier3/uRoom/src/main/java/com/sp/RoomsMicroService.java
	}

}
