package com.victorskg.redditclonecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RedditCloneCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneCoreApplication.class, args);
	}

}
