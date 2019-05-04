package com.pentalog.BT;

import com.pentalog.BT.exception.ValidationException;
import com.pentalog.BT.view.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BtApplication implements CommandLineRunner {

	@Autowired
	private Console console;

	public static void main(String[] args) {
		SpringApplication.run(BtApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		try {
			console.loginScreen();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
