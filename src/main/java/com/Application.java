package com;

import com.springplugin.EnableMyAnnotation;
import com.springplugin.merge.EnableAceMerge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.lz.cloud.fegin")
@EnableAsync
@EnableAceMerge
@EnableMyAnnotation
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("=====PROJECT START=====");
	}
}
