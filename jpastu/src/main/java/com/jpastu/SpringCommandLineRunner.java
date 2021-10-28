package com.jpastu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandLineRunner implements CommandLineRunner {

	@Autowired
	public SpringCommandLineRunner() {
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
