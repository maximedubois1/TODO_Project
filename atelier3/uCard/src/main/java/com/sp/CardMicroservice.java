package com.sp;

import com.sp.service.PeriodicEnergyRegain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class CardMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(CardMicroservice.class, args);
//		PeriodicEnergyRegain.main(args);
	}

}
